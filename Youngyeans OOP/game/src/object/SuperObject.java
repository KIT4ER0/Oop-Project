package object;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class SuperObject {
    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int x, y;
    public int width,height; 
    public Rectangle solidArea = new Rectangle();
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int posx, posy;
    
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, width, height, null);
    }
}
