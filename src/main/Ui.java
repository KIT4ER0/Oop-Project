package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import object.OBJ_Key;

public class Ui {
    private GamePanel gp;
    private Graphics2D g2;
    private Font arial_30, arial_50, arial_40;
    private BufferedImage keyImage;
    private boolean messageOn = false;
    private String message = "";
    private int messageCounter = 0;
    private int x, y = 0;
    
    public Ui(GamePanel gp){
        this.gp = gp;
        arial_50 = new Font("Arial", Font.PLAIN, 100);
        arial_30 = new Font("Arial", Font.PLAIN, 30);
        arial_40 = new Font("Arial", Font.PLAIN, 30);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.getImage();
    }
    
    public void showMessage(String text){
        
        message = text;
        messageOn = true;
        
    }
    public void draw(Graphics2D g2){
//        g2.setFont(new Font("Arial", Font.PLAIN, 30));
        g2.setFont(arial_30);
        g2.setColor(Color.black);
        g2.drawImage(keyImage, 80, 40, gp.getTileSize(), gp.getTileSize(), null);
        g2.drawString("x "+ gp.getPlayer().getHasKey(), 130, 75);
        
        // message
        if(messageOn == true){
            
            g2.setFont(g2.getFont().deriveFont(20F));
            g2.drawString(message, x, y);
            
            messageCounter++;
            
            if(messageCounter > 120 ){
                System.out.println("hi");
                messageCounter = 0;
                messageOn = false;
            }
        }
        
//        g2.setFont(arial_40);
//        g2.setColor(Color.white);
//        g2.drawString("Noodle = "+ gp.player.hasKey, 80, 115);
        
        this.g2 = g2;
        g2.setFont(arial_50);
        g2.setColor(Color.red);
        
        //show time
        g2.setFont(arial_40);
        g2.setColor(Color.black);
        g2.drawString(""+ gp.getGameTimer().getRemainingTime(), 80, 115);
        
        //show point
        g2.setFont(arial_40);
        g2.setColor(Color.black);
        g2.drawString(""+ gp.getPlayer().getPoint(), 80, 200);
        
        //Title State
        if(gp.getGameState() == gp.getTitleState()){
            drawTitleScreen();
            
        }
   
        //Play State
        if(gp.getGameState() == gp.getPlayState()){
            
        }//Pause State
        if(gp.getGameState() == gp.getPauseState()){
            drawPauseScreen();
        }
        
        if(gp.getGameState() == gp.getFinishState()){
            String text = "time up";
            int x = getXforCenteredText(text);
            int y = gp.getScreenHeight() / 2;
            
            g2.drawString(text, x, y);
        }
        
        
       
    }
    public void drawTitleScreen(){
        //Title Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 96F));
        String text = "Blue boy Adventure";
        int x = getXforCenteredText(text);
        int y = gp.getTileSize() * 3;
        
        
    }
    
    
    
    public void drawPauseScreen(){
            String text = "PAUSED";
            int x = getXforCenteredText(text);
            int y = gp.getScreenHeight() / 2;
            
            g2.drawString(text, x, y);
        }
    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.getScreenWidth() / 2 - length/2;
        return  x;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
}
