package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ShopnoNoodle extends SuperObject{
    public ShopnoNoodle(){
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
