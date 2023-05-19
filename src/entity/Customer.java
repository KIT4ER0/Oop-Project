package entity;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Customer extends Entity{
    public Customer(GamePanel gp){
        super(gp);
        direction = "walk";
        speed = 5;
        width = 110;
        height = 85;
        solidArea = new Rectangle();
        solidArea.x = 0;
        solidArea.y = 0;
        solidArea.width = height;
        solidArea.height = height - 20;
        getImage();
    }
    
    public void getImage(){
        try{
            walk1 = ImageIO.read(new File("res/customer/down1.png"));
            walk2 = ImageIO.read(new File("res/customer/down2.png"));
            walk3 = ImageIO.read(new File("res/customer/right1.png"));
            walk4 = ImageIO.read(new File("res/customer/right2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
