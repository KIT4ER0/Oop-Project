package main;


import entity.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    // SCREEN SETTING
    final int originalTileSize = 48; // 16*16 tile
    final int scale = 1;
    
    public final int tileSize = originalTileSize * scale; // 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    //set FPS draw 60 frames per second
    int FPS = 60;
    
    //to make backgroud
    TileManager tileM = new TileManager(this);
    
    //to input key event
    KeyHandler keyH = new KeyHandler();
    //run
    Thread gameThread;
    
    //collision
    public CollisionChecker cChecker = new CollisionChecker(this);
    //object pos
    public AssetSetter aSetter = new AssetSetter(this);
    
    //from Player Class
    public Player player = new Player(this, keyH);
    
    //Object
    public SuperObject obj[] = new SuperObject[10];
    
    //customer
    public Entity cust[] = new Entity[10];
    
    public GamePanel(){
        //if use setSize -> size be specified but setPreferredSize -> size is depending on the size of container
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        
        //darwing gaphic in off-screen and copy all to the screen once
        this.setDoubleBuffered(true); 
        
        //key input
        this.addKeyListener(keyH);
        
        //focused to recieve key input
        this.setFocusable(true);
    }
    
    public void setupGame(){
        aSetter.setObject();
        aSetter.setCustomer();
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override
    public void run() {
        // 10000000000 nanosec = 1 sec
        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        //// double nextDrawTime = System.nanoTime() + drawInterval;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        
        while(gameThread != null){
            //System.nanoTime : Return time source in nanoseconds
            currentTime = System.nanoTime();
            // currebt - last = how much time passed
            delta += (currentTime - lastTime) / drawInterval;
            //check FPS
            timer += (currentTime - lastTime);
            
            lastTime = currentTime;
            if(delta >= 1){
                //1. UPDATE : update info ex. char position -> update
                update();
                //2. DRAW : draw in screen w updated info -> paintComponent
                repaint();
                delta--;
                drawCount++;
            }
            // nano gvb= 1 sec
            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }
    
    public void update(){
        player.update();
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //extends Graphic class
        //change Graphic -> Graphics2D (Graphic2D has more function)
        Graphics2D g2 = (Graphics2D)g;
        
         //draw tile before player (tile will be behind player)
        tileM.draw(g2);
        
        //draw obj
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        //draw customer
        for(int i = 0; i < cust.length; i++){
            if(cust[i] != null){
                cust[i].draw(g2, this);
            }
        }
        //draw player
        player.draw(g2);
        //dispose graphic context and release sys rerource that it using
        g2.dispose();
    }
}
