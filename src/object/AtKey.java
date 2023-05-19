
package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class AtKey extends SuperObject{
    public AtKey(){
        name = "atKey";
        width = 150;
        height = 150;
        try{
            System.out.println("key");
            image = ImageIO.read(new File("res/objects/atKey.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = false;
    }
}
