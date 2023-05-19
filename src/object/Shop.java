package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Shop extends SuperObject{
    public Shop(){
        name = "Shop";
        width = 192;
        height = 160;
        try{
            image = ImageIO.read(new File("res/objects/shopwnoodle.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
        solidArea.width = width;
        solidArea.height = height - 35;
    }
}
