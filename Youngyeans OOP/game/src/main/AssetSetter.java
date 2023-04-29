package main;

import entity.Customer;
//import object.OBJ_Chest;
import object.OBJ_Key;
import object.Table;
import object.Shop;
// set pos for object
public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
//        gp.obj[0] = new OBJ_Key();
//        gp.obj[0].x = 8 * gp.tileSize;
//        gp.obj[0].y = 7 * gp.tileSize;
//        
//        gp.obj[1] = new OBJ_Chest();
//        gp.obj[1].x = 10 * gp.tileSize;
//        gp.obj[1].y = 8 * gp.tileSize;

        //shop
        gp.obj[0] = new Shop();
        gp.obj[0].x = 7 * gp.tileSize + 23;
        gp.obj[0].y = 0;
        
        //table A
        gp.obj[1] = new Table();
        gp.obj[1].x = 5 * gp.tileSize + 13;
        gp.obj[1].y = 3 * gp.tileSize + 14;
        
        //table B
        gp.obj[2] = new Table();
        gp.obj[2].x = 11 * gp.tileSize - 14;
        gp.obj[2].y = 3 * gp.tileSize + 14;
        
        //tabel C
        gp.obj[3] = new Table();
        gp.obj[3].x = 5 * gp.tileSize + 13;
        gp.obj[3].y = 7 * gp.tileSize + 7;
        
        //tabel D
        gp.obj[4] = new Table();
        gp.obj[4].x = 11 * gp.tileSize - 14;
        gp.obj[4].y = 7 * gp.tileSize + 7;
    }
    
    public void setCustomer(){
        gp.cust[0] = new Customer(gp);
        gp.cust[0].x = gp.tileSize * 21;
        gp.cust[0].y = gp.tileSize * 21;
    }
}
