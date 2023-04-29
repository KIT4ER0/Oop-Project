package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

//stories variables that be used in Player , Customer Class
public class Entity {
    GamePanel gp;
    public int x, y;
    public int speed;
    
    //image
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, stand1, stand2;
    public String direction;
    
    //make animation : change pic
    public int spriteCounter = 0;
    public int spriteNum = 1;
    
    //solid Tiles
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    //solid oject
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    
    //for customer
    public int heart;
    public String state;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(stand1, x, y, gp.tileSize, gp.tileSize, null);
    }
}
