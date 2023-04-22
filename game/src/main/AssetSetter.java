package main;

import object.OBJ_Chest;
import object.OBJ_Key;

public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].x = 8 * gp.tileSize;
        gp.obj[0].y = 7 * gp.tileSize;
        
        gp.obj[1] = new OBJ_Chest();
        gp.obj[1].x = 10 * gp.tileSize;
        gp.obj[1].y = 8 * gp.tileSize;
    }
}
