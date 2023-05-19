package main;

import entity.Entity;
import entity.Player;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;
import timer.FinishGame;

public class GamePanel extends JPanel implements Runnable{
    private int tileSize = 48;
    private int maxScreenCol = 16;
    private int maxScreenRow = 12;
    private int screenWidth = tileSize * maxScreenCol;
    private int screenHeight = tileSize * maxScreenRow;
    private int FPS = 60;
    
    private int gameState;
    private int titleState = 0;
    private int playState = 1;
    private int pauseState = 2;
    private int finishState = 3;
    private int line = 0;
    
    private TileManager tileM;
    private KeyHandler keyH;
    private Thread gameThread;
    private Sound sound;
    private CollisionChecker cChecker;
    private AssetSetter aSetter;
    private Player player;
    private ArrayList<Entity> cust;
    private SuperObject[] obj;
    private Ui ui;
    private FinishGame gameTimer;
    
    public GamePanel(){
        tileM = new TileManager(this);
        keyH = new KeyHandler(this);
        sound = new Sound();
        cChecker = new CollisionChecker(this);
        aSetter = new AssetSetter(this);
        player = new Player(this, keyH);
        cust = new ArrayList();
        obj = new SuperObject[100];
        gameTimer = new FinishGame(180000, this);
        ui = new Ui(this);
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame(){
        aSetter.setObject();
        aSetter.setCustomer();
//        playMusic(0);
        gameState = playState;
        gameTimer.starttimer();
        
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }

    public void update(){
        if(gameState == playState){
            player.update();
            for(int i = 0; i < cust.size(); i++){
                if(i == 0 && cust.get(i) != null){
                    cust.get(i).update();
                }
                else if(cust.get(i) != null && cust.get(i - 1).isHasTable() == true){
                    cust.get(i).update();
                }
            }
            if(gameTimer.isGameFinished() == true){
                System.out.println("time up!!");
                gameState = finishState;
            }
        }
        else if(gameState == pauseState){
            
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if(gameState == titleState){
            ui.draw(g2);
        }
        tileM.draw(g2);
        
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        
        for(int i = 0; i < cust.size(); i++){
            if(i == 0 && cust.get(i) != null){
                cust.get(i).draw(g2);
            }
            else if(cust.get(i) != null && cust.get(i - 1).isHasTable() == true){
                cust.get(i).draw(g2);
            }
        }
        player.draw(g2);
        ui.draw(g2);
        g2.dispose();
    }

    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    
    public void stopMusic(){
        sound.stop();
    }
    
    public void playSoundFX(int i){
        sound.setFile(i);
        sound.play();
    }

    public int getTileSize() {
        return tileSize;
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public int getMaxScreenCol() {
        return maxScreenCol;
    }

    public void setMaxScreenCol(int maxScreenCol) {
        this.maxScreenCol = maxScreenCol;
    }

    public int getMaxScreenRow() {
        return maxScreenRow;
    }

    public void setMaxScreenRow(int maxScreenRow) {
        this.maxScreenRow = maxScreenRow;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public int getFPS() {
        return FPS;
    }

    public void setFPS(int FPS) {
        this.FPS = FPS;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public int getPlayState() {
        return playState;
    }

    public void setPlayState(int playState) {
        this.playState = playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public void setPauseState(int pauseState) {
        this.pauseState = pauseState;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public TileManager getTileM() {
        return tileM;
    }

    public void setTileM(TileManager tileM) {
        this.tileM = tileM;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void setKeyH(KeyHandler keyH) {
        this.keyH = keyH;
    }

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    public Sound getSound() {
        return sound;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public CollisionChecker getcChecker() {
        return cChecker;
    }

    public void setcChecker(CollisionChecker cChecker) {
        this.cChecker = cChecker;
    }

    public AssetSetter getaSetter() {
        return aSetter;
    }

    public void setaSetter(AssetSetter aSetter) {
        this.aSetter = aSetter;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public ArrayList<Entity> getCust() {
        return cust;
    }

    public void setCust(ArrayList<Entity> cust) {
        this.cust = cust;
    }

    public SuperObject[] getObj() {
        return obj;
    }

    public void setObj(SuperObject[] obj) {
        this.obj = obj;
    }
    
    public SuperObject getValueFromOBJ(SuperObject[] obj, int i){
        return obj[i];
    }
    
    public void setValueInOBJ(SuperObject[] obj, int i, SuperObject val){
        obj[i] = val;
    }

    public int getFinishState() {
        return finishState;
    }

    public void setFinishState(int finishState) {
        this.finishState = finishState;
    }

    public FinishGame getGameTimer() {
        return gameTimer;
    }

    public int getTitleState() {
        return titleState;
    }

    public Ui getUi() {
        return ui;
    }
    
}
