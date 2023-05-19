
package object;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class TablewithCust extends SuperObject{
    public TablewithCust(){
        
        name = "TablewithCust";
        width = 134;
        height = 173;
        
        try{
            image = ImageIO.read(new File("res/objects/TablewithCust.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        
        collision = true;
        solidArea = new Rectangle();
        solidArea.x = 7;
        solidArea.y = 29;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 120;
        solidArea.height = 100;
    }
}
