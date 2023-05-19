package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Slot extends SuperObject{
    public Slot(){
        name = "Slot";
        width = 150;
        height = 150;
        try{
            image = ImageIO.read(new File("res/objects/slot.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = false;
    }
}
