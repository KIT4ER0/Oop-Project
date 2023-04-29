/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author youngyeans
 */
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
public class Customer extends Entity{
    
    public Customer(GamePanel gp){
        //to Entity Class
        super(gp);
        state = "Stand";
        speed = 1;
        
        //coll
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidArea.width = 32;
        solidArea.height = 32;
        
        getImage();
    }
    
    //getimage
    public void getImage(){
        try{
            stand1 = ImageIO.read(new File("res/player/boy_up_1.png"));
            stand2 = ImageIO.read(new File("res/player/boy_up_2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //draw
    public void draw(Graphics2D g2, GamePanel gp){
        BufferedImage image = null;
        
        g2.drawImage(stand1, 50, 50, gp.tileSize, gp.tileSize, null);
    }
    
}
