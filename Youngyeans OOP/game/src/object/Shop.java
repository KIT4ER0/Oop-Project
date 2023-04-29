/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

/**
 *
 * @author youngyeans
 */
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class Shop extends SuperObject{
    public Shop(){
        
        name = "Shop";
        width = 192;
        height = 160;
        
        
        try{
            image = ImageIO.read(new File("res/objects/shop.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        collision = true;
        solidArea.width = width;
        solidArea.height = height - 35;
    }
    
}
