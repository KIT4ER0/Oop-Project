
package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject{
    public OBJ_Key(){
        name = "Key";
        width = 48;
        height = 48;
        try{
            System.out.println("key");
            image = ImageIO.read(new File("res/objects/ring.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = false;
    }
}
