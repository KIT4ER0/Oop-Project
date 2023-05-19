
package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class OBJ_Clean extends SuperObject{
    public OBJ_Clean(){
        name = "Clean";
        width = 48;
        height = 48;
        try{
            image = ImageIO.read(new File("res/objects/clean.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = false;
    }
}
