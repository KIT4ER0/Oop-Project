package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;
import object.AtKey;
import timer.tableACooldown;
import timer.tableBCooldown;
import timer.tableCCooldown;
import timer.tableDCooldown;

public class Player extends Entity{
    private GamePanel gp;
    private KeyHandler keyH;
    private tableACooldown cooldownA;
    private tableBCooldown cooldownB;
    private tableCCooldown cooldownC;
    private tableDCooldown cooldownD;
    
    private int hasKey = 0;
    private int hasMob = 0;
    private int tableAPhase, tableBPhase, tableCPhase, tableDPhase;
    private boolean tableA, tableB, tableC, tableD, ordered, cancelMoodA, cancelMoodB, cancelMoodC, cancelMoodD;
    private int justRingOnetimeFx, justCleanOnetimeFx;
    private int point = 0;
    
    public Player(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.gp = gp;
        this.keyH = keyH;
        cooldownA = new tableACooldown(15000);
        cooldownB = new tableBCooldown(15000);
        cooldownC = new tableCCooldown(15000);
        cooldownD = new tableDCooldown(15000);
        
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 50;
        solidArea.height = 35;
        
        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues(){
        x = 13 * gp.getTileSize() + 10;
        y = 2 * gp.getTileSize() + 20;
        speed = 4;
        direction = "down";
    }
    
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(new File("res/player/boy_up_1.png"));
            up2 = ImageIO.read(new File("res/player/boy_up_2.png"));
            down1 = ImageIO.read(new File("res/player/boy_down_1.png"));
            down2 = ImageIO.read(new File("res/player/boy_down_2.png"));
            left1 = ImageIO.read(new File("res/player/boy_left_1.png"));
            left2 = ImageIO.read(new File("res/player/boy_left_2.png"));
            right1 = ImageIO.read(new File("res/player/boy_right_1.png"));
            right2 = ImageIO.read(new File("res/player/boy_right_2.png"));
            updish1 = ImageIO.read(new File("res/player/updish1.png"));
            updish2 = ImageIO.read(new File("res/player/updish2.png"));
            downdish1 = ImageIO.read(new File("res/player/downdish1.png"));
            downdish2 = ImageIO.read(new File("res/player/downdish2.png"));
            leftdish1 = ImageIO.read(new File("res/player/leftdish1.png"));
            leftdish2 = ImageIO.read(new File("res/player/leftdish2.png"));
            rightdish1 = ImageIO.read(new File("res/player/rightdish1.png"));
            rightdish2 = ImageIO.read(new File("res/player/rightdish2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void update(){
        // check if customer A is full
            if(tableA == true){
                if(cooldownA.isFinishedA() == true){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 1).setImage(ImageIO.read(new File("res/objects/fullCustomer.png")));
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    tableAPhase = 3;
                    tableA = false;
                    cooldownA.setFinishedA(false);
                    
                }  
            }
        // check if customer B is full
            if(tableB == true){
                if(cooldownB.isFinishedB() == true){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 2).setImage(ImageIO.read(new File("res/objects/fullCustomer.png")));
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    tableBPhase = 3;
                    tableB = false;
                    cooldownB.setFinishedB(false);
                }
            }
        // check if customer C is full
            if(tableC == true){
                if(cooldownC.isFinishedC() == true){
                    
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 3).setImage(ImageIO.read(new File("res/objects/fullCustomer.png")));
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    tableC = false;
                    cooldownC.setFinishedC(false);
                    tableCPhase = 3;
                }  
            }
        // check if customer D is full
            if(tableD == true){
                if(cooldownD.isFinishedD() == true){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 4).setImage(ImageIO.read(new File("res/objects/fullCustomer.png")));
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    tableD = false;
                    cooldownD.setFinishedD(false);
                    tableDPhase = 3;
                }    
            }
        
        // move
        if(keyH.isUpPressed() == true || keyH.isDownPresed() == true || 
                keyH.isLeftPressed() == true || keyH.isRightPressed() == true || keyH.isPickupPressed() == true){
            if(keyH.isUpPressed() == true){
                direction = "up";
            }
            else if(keyH.isDownPresed() == true){
                direction = "down";
            }
            else if(keyH.isLeftPressed() == true){
                direction = "left";
            }
            else if(keyH.isRightPressed() == true){
                direction = "right";
            }
            
            //check tiles collision
            collisionOn = false;
            gp.getcChecker().checkTile(this);
            
            //check obj collision
            int objIndex = gp.getcChecker().checkObject(this, true);
            pickUpObject(objIndex);
            
            //check customer collision
            int custIndex = gp.getcChecker().checkEntity(this, gp.getCust());
               
            //no collision = can move
            if(collisionOn == false && keyH.isPickupPressed() == false){
                switch(direction){
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }
            }
            //set time to change
            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    
    public void pickUpObject(int i){
        if(keyH.isPickupPressed() == true){
        // ringbell    
            if(i == 5){
                String objectName = gp.getValueFromOBJ(gp.getObj(), i).getName();
  
                switch(objectName){
                    case("Key"): 
                        try {
                            ordered = true;
                            gp.getValueFromOBJ(gp.getObj(), 0).setImage(ImageIO.read(new File("res/objects/shopwnoodle.png")));
                        } catch (IOException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    // play sound only 1 time 
                        if(justRingOnetimeFx == 0){
                            gp.playSoundFX(1);
                            justRingOnetimeFx = 1;
                        }
                        else if (justRingOnetimeFx == 1){}
                        
                }       
            }
        // clean  
            if(i == 6){
                String objectName = gp.getValueFromOBJ(gp.getObj(), i).getName(); 
                
                switch(objectName){
                    case("Clean"):
                        hasMob = 1;
                    // play sound only 1 time 
                        if(justCleanOnetimeFx == 0){
                            justCleanOnetimeFx = 1;
                            gp.playSoundFX(5);
                        }
                        else if (justCleanOnetimeFx == 1){}
                }    
            }
            
        // shop
            if(i == 0 && ordered == true){
                String objectName = gp.getValueFromOBJ(gp.getObj(), i).getName(); 
                
                switch(objectName){
                    case("Shop"):
                        try {
                            gp.getValueFromOBJ(gp.getObj(), 0).setImage(ImageIO.read(new File("res/objects/shop.png")));
           
                        } catch (IOException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
//                        // Atkey
                        hasKey = 1; 
                        gp.setValueInOBJ(gp.getObj(), 8, new AtKey());
                        gp.getValueFromOBJ(gp.getObj(), 8).setX(gp.getTileSize() - 70);
                        gp.getValueFromOBJ(gp.getObj(), 8).setY(10 * gp.getTileSize() - 10);
                        
                        ordered = false;
                        justRingOnetimeFx = 0;    
                }
            }
        
        // table A
            else if(i == 1 && tableAPhase == 1){
                String objectName = gp.getValueFromOBJ(gp.getObj(), i).getName();
            
                switch(objectName){
                    case("Table"):
                    if(hasKey > 0 ){
                        try {
                            gp.getValueFromOBJ(gp.getObj(), 1).setImage(ImageIO.read(new File("res/objects/eat.png")));
           
                        } catch (IOException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                            // atKeyremove
                            hasKey--;
                            try {
                                gp.getValueFromOBJ(gp.getObj(), 8).setImage(ImageIO.read(new File("res/objects/blank.png")));
                            } catch (IOException ex) {
                                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            tableAPhase = 2;
                            cooldownA.startCooldown();
                            tableA = true;
                            cancelMoodA = true;  
                            gp.playSoundFX(3);
                            point += 100;
                            gp.getUi().showMessage("+ 100");
                            gp.getUi().setX(300);
                            gp.getUi().setY(240);
                            System.out.println("point: "+ point);
                            
                    }              
                } 
            }
        // table B
            else if(i == 2  && tableBPhase == 1){
                String objectName = gp.getValueFromOBJ(gp.getObj(), i).getName();
            
                switch(objectName){
                    case("Table"):
                        if(hasKey > 0){
                            try {
                                gp.getValueFromOBJ(gp.getObj(), 2).setImage(ImageIO.read(new File("res/objects/eat.png")));
                            } catch (IOException ex) {
                                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            //atkey remove
                            hasKey-- ;
                            try {
                                gp.getValueFromOBJ(gp.getObj(), 8).setImage(ImageIO.read(new File("res/objects/blank.png")));
                            } catch (IOException ex) {
                                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            tableBPhase = 2;
                            cooldownB.startCooldown();
                            tableB = true;
                            cancelMoodB = true;  
                            gp.playSoundFX(3);
                            point += 100;
                            gp.getUi().showMessage("+ 100");
                            gp.getUi().setX(550);
                            gp.getUi().setY(240);
                            System.out.println("point: "+ point);
                        }
                } 
            }  
        //table c    
            else if(i == 3 && tableCPhase == 1){
                String objectName = gp.getValueFromOBJ(gp.getObj(), i).getName();
            
                switch(objectName){
                    case("Table"):
                        if(hasKey > 0){
                            
                            try {
                                gp.getValueFromOBJ(gp.getObj(), 3).setImage(ImageIO.read(new File("res/objects/eat.png")));
                            } catch (IOException ex) {
                                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                            }       
                            //at keyremove
                            hasKey-- ;
                            try {
                                gp.getValueFromOBJ(gp.getObj(), 8).setImage(ImageIO.read(new File("res/objects/blank.png")));
                            } catch (IOException ex) {
                                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            gp.playSoundFX(3);
                            tableCPhase = 2;
                            cooldownC.startCooldown();
                            tableC = true;
                            cancelMoodC = true;
                            point += 100;
                            gp.getUi().showMessage("+ 100");
                            gp.getUi().setX(300);
                            gp.getUi().setY(430);
                            System.out.println("point: "+ point);
                        }
                } 
            }
        // table D
            else if(i == 4 && tableDPhase == 1){
                String objectName = gp.getValueFromOBJ(gp.getObj(), i).getName();
            
                switch(objectName){
                    case("Table"):
                        if(hasKey > 0){                          
                            // atkey remove
                            hasKey-- ;
                            try {
                                gp.getValueFromOBJ(gp.getObj(), 4).setImage(ImageIO.read(new File("res/objects/eat.png")));
                            } catch (IOException ex) {
                                Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            gp.playSoundFX(3);
                            tableDPhase = 2;
                            cooldownD.startCooldown();
                            tableD = true;
                            cancelMoodD = true;
                            point += 100;
                            gp.getUi().showMessage("+ 100");
                            gp.getUi().setX(550);
                            gp.getUi().setY(430);
                            System.out.println("point: "+ point);
                        }
                } 
            }
            
        // table A after full
            else if (i == 1 && tableAPhase == 3 && hasMob == 1){
                try {
                    gp.getValueFromOBJ(gp.getObj(), 1).setImage(ImageIO.read(new File("res/objects/table1.png")));
                    int pos = gp.getCust().size();
                    gp.getCust().add(new Customer(gp));
                    gp.getCust().get(pos).x = gp.getTileSize();
                    gp.getCust().get(pos).y = 0;
                    gp.getCust().get(pos).hasTable = false;
                    gp.getValueFromOBJ(gp.getObj(), 1).setEmpty(true);
                    tableAPhase = 0;
                    hasMob = 0;
                    gp.getPlayer().cancelMoodA = false;
                    gp.playSoundFX(2);
                    point += 500;
                    justCleanOnetimeFx = 0;
                    gp.getUi().showMessage("+ 500");
                    System.out.println("point : "+point);
                    gp.getUi().setX(300);
                    gp.getUi().setY(240);
                    
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        // table B after full    
            else if (i == 2 && tableBPhase == 3 && hasMob == 1){
                try {
                    gp.getValueFromOBJ(gp.getObj(), 2).setImage(ImageIO.read(new File("res/objects/table1.png")));
                    int pos = gp.getCust().size();
                    gp.getCust().add(new Customer(gp));
                    gp.getCust().get(pos).x = gp.getTileSize();
                    gp.getCust().get(pos).y = 0;
                    gp.getCust().get(pos).hasTable = false;
                    gp.getValueFromOBJ(gp.getObj(), 2).setEmpty(true);
                    tableBPhase = 0;
                    hasMob = 0;
                    gp.getPlayer().cancelMoodB = false;
                    gp.playSoundFX(2);
                    point += 500;
                    justCleanOnetimeFx = 0;
                    justCleanOnetimeFx = 0;
                    gp.getUi().showMessage("+ 500");
                    System.out.println("point : "+point);
                    gp.getUi().setX(550);
                    gp.getUi().setY(240); 
                    
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        // table C after full    
            else if (i == 3 && tableCPhase == 3 && hasMob == 1){
                try {
                    gp.getValueFromOBJ(gp.getObj(), 3).setImage(ImageIO.read(new File("res/objects/table1.png")));
                    int pos = gp.getCust().size();
                    gp.getCust().add(new Customer(gp));
                    gp.getCust().get(pos).x = gp.getTileSize();
                    gp.getCust().get(pos).y = 0;
                    gp.getCust().get(pos).hasTable = false;
                    gp.getValueFromOBJ(gp.getObj(), 3).setEmpty(true);
                    tableCPhase = 0;
                    hasMob = 0;
                    gp.getPlayer().cancelMoodC = false;
                    gp.playSoundFX(2);
                    point += 500;
                    justCleanOnetimeFx = 0;
                    gp.getUi().showMessage("+ 500");
                    System.out.println("point : "+point);
                    gp.getUi().setX(300);
                    gp.getUi().setY(430);
                    
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        // table D after full    
            else if (i == 4 && tableDPhase == 3 && hasMob == 1){
                try {
                    gp.getValueFromOBJ(gp.getObj(), 4).setImage(ImageIO.read(new File("res/objects/table1.png")));
                    int pos = gp.getCust().size();
                    gp.getCust().add(new Customer(gp));
                    gp.getCust().get(pos).x = gp.getTileSize();
                    gp.getCust().get(pos).y = 0;
                    gp.getCust().get(pos).hasTable = false;
                    gp.getValueFromOBJ(gp.getObj(), 4).setEmpty(true);
                    tableDPhase = 0;
                    hasMob = 0;
                    gp.getPlayer().cancelMoodD = false;
                    gp.playSoundFX(2);
                    point += 500;
                    justCleanOnetimeFx = 0;
                    gp.getUi().showMessage("+ 500");
                    System.out.println("point : "+point);
                    gp.getUi().setX(550);
                    gp.getUi().setY(430);
                    
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void draw(Graphics2D g2){        
        BufferedImage image = null;
    // no noodles  
        if(hasKey == 0){
            switch(direction){
                case "up":
                    if(spriteNum == 1){
                        image = up1;
                    }
                    if(spriteNum == 2){
                        image = up2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = down1;
                    }
                    if(spriteNum == 2){
                        image = down2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = left1;
                    }
                    if(spriteNum == 2){
                        image = left2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = right1;
                    }
                    if(spriteNum == 2){
                        image = right2;
                    }
                    break;
            }
        }
        // have noodles
        else if(hasKey >= 1){
            switch(direction){
                case "up":
                    if(spriteNum == 1){
                        image = updish1;
                    }
                    if(spriteNum == 2){
                        image = updish2;
                    }
                    break;
                case "down":
                    if(spriteNum == 1){
                        image = downdish1;
                    }
                    if(spriteNum == 2){
                        image = downdish2;
                    }
                    break;
                case "left":
                    if(spriteNum == 1){
                        image = leftdish1;
                    }
                    if(spriteNum == 2){
                        image = leftdish2;
                    }
                    break;
                case "right":
                    if(spriteNum == 1){
                        image = rightdish1;
                    }
                    if(spriteNum == 2){
                        image = rightdish2;
                    }
                    break;
            }
        }
        g2.drawImage(image, x, y, 69, 69, null);
    }

    public int getHasKey() {
        return hasKey;
    }

    public void setHasKey(int hasKey) {
        this.hasKey = hasKey;
    }

    public int getHasMob() {
        return hasMob;
    }

    public void setHasMob(int hasMob) {
        this.hasMob = hasMob;
    }

    public int getTableAPhase() {
        return tableAPhase;
    }

    public void setTableAPhase(int tableAPhase) {
        this.tableAPhase = tableAPhase;
    }

    public int getTableBPhase() {
        return tableBPhase;
    }

    public void setTableBPhase(int tableBPhase) {
        this.tableBPhase = tableBPhase;
    }

    public int getTableCPhase() {
        return tableCPhase;
    }

    public void setTableCPhase(int tableCPhase) {
        this.tableCPhase = tableCPhase;
    }

    public int getTableDPhase() {
        return tableDPhase;
    }

    public void setTableDPhase(int tableDPhase) {
        this.tableDPhase = tableDPhase;
    }

    public boolean isTableA() {
        return tableA;
    }

    public void setTableA(boolean tableA) {
        this.tableA = tableA;
    }

    public boolean isTableB() {
        return tableB;
    }

    public void setTableB(boolean tableB) {
        this.tableB = tableB;
    }

    public boolean isTableC() {
        return tableC;
    }

    public void setTableC(boolean tableC) {
        this.tableC = tableC;
    }

    public boolean isTableD() {
        return tableD;
    }

    public void setTableD(boolean tableD) {
        this.tableD = tableD;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public boolean isCancelMoodA() {
        return cancelMoodA;
    }

    public void setCancelMoodA(boolean cancelMoodA) {
        this.cancelMoodA = cancelMoodA;
    }

    public boolean isCancelMoodB() {
        return cancelMoodB;
    }

    public void setCancelMoodB(boolean cancelMoodB) {
        this.cancelMoodB = cancelMoodB;
    }

    public boolean isCancelMoodC() {
        return cancelMoodC;
    }

    public void setCancelMoodC(boolean cancelMoodC) {
        this.cancelMoodC = cancelMoodC;
    }

    public boolean isCancelMoodD() {
        return cancelMoodD;
    }

    public void setCancelMoodD(boolean cancelMoodD) {
        this.cancelMoodD = cancelMoodD;
    }

    public int getJustRingOnetimeFx() {
        return justRingOnetimeFx;
    }

    public void setJustRingOnetimeFx(int justRingOnetimeFx) {
        this.justRingOnetimeFx = justRingOnetimeFx;
    }

    public int getJustCleanOnetimeFx() {
        return justCleanOnetimeFx;
    }

    public void setJustCleanOnetimeFx(int justCleanOnetimeFx) {
        this.justCleanOnetimeFx = justCleanOnetimeFx;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }
    
}
