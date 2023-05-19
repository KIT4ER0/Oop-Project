package main;

import entity.Customer;
import object.OBJ_Clean;
import object.OBJ_Key;
import object.ShopnoNoodle;
import object.Slot;
import object.Table;

public class AssetSetter {
    private GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        gp.setValueInOBJ(gp.getObj(), 0, new ShopnoNoodle());
        gp.getValueFromOBJ(gp.getObj(), 0).setX(7 * gp.getTileSize() + 23);
        gp.getValueFromOBJ(gp.getObj(), 0).setY(0);
        
        gp.setValueInOBJ(gp.getObj(), 1, new Table());
        gp.getValueFromOBJ(gp.getObj(), 1).setX(5 * gp.getTileSize() + 13);
        gp.getValueFromOBJ(gp.getObj(), 1).setY(3 * gp.getTileSize() + 14);
        gp.getValueFromOBJ(gp.getObj(), 1).setEmpty(true);
        
        gp.setValueInOBJ(gp.getObj(), 2, new Table());
        gp.getValueFromOBJ(gp.getObj(), 2).setX(11 * gp.getTileSize() - 14);
        gp.getValueFromOBJ(gp.getObj(), 2).setY(3 * gp.getTileSize() + 14);
        gp.getValueFromOBJ(gp.getObj(), 2).setEmpty(true);
        
        gp.setValueInOBJ(gp.getObj(), 3, new Table());
        gp.getValueFromOBJ(gp.getObj(), 3).setX(5 * gp.getTileSize() + 13);
        gp.getValueFromOBJ(gp.getObj(), 3).setY(7 * gp.getTileSize() + 7);
        gp.getValueFromOBJ(gp.getObj(), 3).setEmpty(true);
        
        gp.setValueInOBJ(gp.getObj(), 4, new Table());
        gp.getValueFromOBJ(gp.getObj(), 4).setX(11 * gp.getTileSize() - 14);
        gp.getValueFromOBJ(gp.getObj(), 4).setY(7 * gp.getTileSize() + 7);
        gp.getValueFromOBJ(gp.getObj(), 4).setEmpty(true);
        
        gp.setValueInOBJ(gp.getObj(), 5, new OBJ_Key());
        gp.getValueFromOBJ(gp.getObj(), 5).setX(11 * gp.getTileSize() + 20);
        gp.getValueFromOBJ(gp.getObj(), 5).setY(1 * gp.getTileSize() + 20);
        
        gp.setValueInOBJ(gp.getObj(), 6, new OBJ_Clean());
        gp.getValueFromOBJ(gp.getObj(), 6).setX(11/2 * gp.getTileSize());
        gp.getValueFromOBJ(gp.getObj(), 6).setY(1 * gp.getTileSize() + 17);
        
        gp.setValueInOBJ(gp.getObj(), 7, new Slot());
        gp.getValueFromOBJ(gp.getObj(), 7).setX(gp.getTileSize() - 80);
        gp.getValueFromOBJ(gp.getObj(), 7).setY(9 * gp.getTileSize() + 40);
    }
    
    public void setCustomer(){
        gp.getCust().add(0, new Customer(gp));
        gp.getCust().add(1, new Customer(gp));
        gp.getCust().add(2, new Customer(gp));
        gp.getCust().add(3, new Customer(gp));
        
        gp.getCust().get(0).setX(gp.getTileSize());
        gp.getCust().get(0).setY(0);
        gp.getCust().get(0).setHasTable(false);
        
        gp.getCust().get(1).setX(gp.getTileSize());
        gp.getCust().get(1).setY(0);
        gp.getCust().get(1).setHasTable(false);
        
        gp.getCust().get(2).setX(gp.getTileSize());
        gp.getCust().get(2).setY(0);
        gp.getCust().get(2).setHasTable(false);
        
        gp.getCust().get(3).setX(gp.getTileSize());
        gp.getCust().get(3).setY(0);
        gp.getCust().get(3).setHasTable(false);
    }
}
