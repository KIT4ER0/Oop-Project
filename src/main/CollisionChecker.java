package main;

import entity.Entity;
import java.util.ArrayList;

public class CollisionChecker {
    private GamePanel gp;
    
    public CollisionChecker(GamePanel gp){
        this.gp = gp;
    }
    
    public void checkTile(Entity entity){
        int entityLeftX = entity.getX() + entity.getSolidAreaX();
        int entityRightX = entity.getX() + entity.getSolidAreaX()+ entity.getSolidAreaWidth();
        int entityTopY = entity.getY() + entity.getSolidAreaY();
        int entityBottomY = entity.getY() + entity.getSolidAreaY()+ entity.getSolidAreaHeight();
        
        int entityLeftCol = entityLeftX / gp.getTileSize();
        int entityRightCol = entityRightX / gp.getTileSize();
        int entityTopRow= entityTopY / gp.getTileSize();
        int entityBottomRow = entityBottomY / gp.getTileSize();
        
        int tileNum1, tileNum2;
        switch(entity.getDirection()){
            case "up":
                entityTopRow = (entityTopY - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                if(gp.getTileM().getTile()[tileNum1].isCollision() == true || 
                   gp.getTileM().getTile()[tileNum2].isCollision() == true){
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
                if(gp.getTileM().getTile()[tileNum1].isCollision() == true || 
                   gp.getTileM().getTile()[tileNum2].isCollision() == true){
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityLeftCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityLeftCol][entityBottomRow];
                if(gp.getTileM().getTile()[tileNum1].isCollision() == true || 
                   gp.getTileM().getTile()[tileNum2].isCollision() == true){
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.getSpeed()) / gp.getTileSize();
                tileNum1 = gp.getTileM().getMapTileNum()[entityRightCol][entityTopRow];
                tileNum2 = gp.getTileM().getMapTileNum()[entityRightCol][entityBottomRow];
                if(gp.getTileM().getTile()[tileNum1].isCollision() == true || 
                   gp.getTileM().getTile()[tileNum2].isCollision() == true){
                    entity.setCollisionOn(true);
                }
                break;
        }
    }
    
    public int checkObject(Entity entity, boolean player){
        int index = 999;
        for(int i = 0; i < gp.getObj().length; i++){
            if(gp.getValueFromOBJ(gp.getObj(), i) != null){
                entity.setSolidAreaX(entity.getX() + entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getY() + entity.getSolidAreaY());
                gp.getValueFromOBJ(gp.getObj(), i).setSolidAreaX(gp.getValueFromOBJ(gp.getObj(), i).getX() + gp.getValueFromOBJ(gp.getObj(), i).getSolidAreaX());
                gp.getValueFromOBJ(gp.getObj(), i).setSolidAreaY(gp.getValueFromOBJ(gp.getObj(), i).getY()+ gp.getValueFromOBJ(gp.getObj(), i).getSolidAreaY());
                switch(entity.getDirection()){
                    case "up":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(gp.getValueFromOBJ(gp.getObj(), i).getSolidArea())){
                            if(gp.getValueFromOBJ(gp.getObj(), i).isCollision() == true){
                                entity.setCollisionOn(true);
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(gp.getValueFromOBJ(gp.getObj(), i).getSolidArea())){
                            if(gp.getValueFromOBJ(gp.getObj(), i).isCollision() == true){
                                entity.setCollisionOn(true);
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.setSolidAreaX(entity.getSolidAreaX() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(gp.getValueFromOBJ(gp.getObj(), i).getSolidArea())){
                            if(gp.getValueFromOBJ(gp.getObj(), i).isCollision() == true){
                                entity.setCollisionOn(true);
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.setSolidAreaX(entity.getSolidAreaX() + entity.getSpeed());
                        if(entity.getSolidArea().intersects(gp.getValueFromOBJ(gp.getObj(), i).getSolidArea())){
                            if(gp.getValueFromOBJ(gp.getObj(), i).isCollision() == true){
                                entity.setCollisionOn(true);
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());
                gp.getValueFromOBJ(gp.getObj(), i).setSolidAreaX(gp.getValueFromOBJ(gp.getObj(), i).getSolidAreaDefaultX());
                gp.getValueFromOBJ(gp.getObj(), i).setSolidAreaY(gp.getValueFromOBJ(gp.getObj(), i).getSolidAreaDefaultY());
            }
        }
        return index;
    }
    
    public int checkEntity(Entity entity, ArrayList<Entity> target){
        int index = 999;
        for(int i = 0; i < target.size(); i++){
            if(target.get(i) != null){
                entity.setSolidAreaX(entity.getX() + entity.getSolidAreaX());
                entity.setSolidAreaY(entity.getY() + entity.getSolidAreaY());
                target.get(i).setSolidAreaX(target.get(i).getX() + target.get(i).getSolidAreaX());
                target.get(i).setSolidAreaY(target.get(i).getY() + target.get(i).getSolidAreaY());
                
                switch(entity.getDirection()){
                    case "up":
                        entity.setSolidAreaY(entity.getSolidAreaY() - entity.getSpeed());
                        if(entity.getSolidArea().intersects(target.get(i).getSolidArea())){
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "down":
                        entity.setSolidAreaY(entity.getSolidAreaY()+ entity.getSpeed());
                        if(entity.getSolidArea().intersects(target.get(i).getSolidArea())){
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "left":
                        entity.setSolidAreaX(entity.getSolidAreaX()- entity.getSpeed());
                        if(entity.getSolidArea().intersects(target.get(i).getSolidArea())){
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                    case "right":
                        entity.setSolidAreaX(entity.getSolidAreaX()+ entity.getSpeed());
                        if(entity.getSolidArea().intersects(target.get(i).getSolidArea())){
                            entity.setCollisionOn(true);
                            index = i;
                        }
                        break;
                }
                entity.setSolidAreaX(entity.getSolidAreaDefaultX());
                entity.setSolidAreaY(entity.getSolidAreaDefaultY());
                target.get(i).setSolidAreaX(target.get(i).getSolidAreaDefaultX());
                target.get(i).setSolidAreaY(target.get(i).getSolidAreaDefaultY());
            }
        }
        return index;
    }
    
    public void checkPlayer(Entity entity){
        entity.setSolidAreaX(entity.getX() + entity.getSolidAreaX());
        entity.setSolidAreaY(entity.getY() + entity.getSolidAreaY());
        
        gp.getPlayer().setSolidAreaX(gp.getPlayer().getX() + gp.getPlayer().getSolidAreaX());
        gp.getPlayer().setSolidAreaY(gp.getPlayer().getY() + gp.getPlayer().getSolidAreaY());
        
        switch(entity.getDirection()){
            case "walk":
                entity.setSolidAreaY(entity.getSolidAreaY() + entity.getSpeed());
                if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())){
                    entity.setCollisionOn(true);
                }
                break;
            case "to":
                entity.setSolidAreaX(entity.getSolidAreaX()+ entity.getSpeed());
                if(entity.getSolidArea().intersects(gp.getPlayer().getSolidArea())){
                    entity.setCollisionOn(true);
                }
                break;
        }
        entity.setSolidAreaX(entity.getSolidAreaDefaultX());
        entity.setSolidAreaY(entity.getSolidAreaDefaultY());
        gp.getPlayer().setSolidAreaX(gp.getPlayer().getSolidAreaDefaultX());
        gp.getPlayer().setSolidAreaY(gp.getPlayer().getSolidAreaDefaultY());
    }
}
