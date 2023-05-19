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
import timer.CustomerACooldown;
import timer.CustomerBCooldown;
import timer.CustomerCCooldown;
import timer.CustomerDCooldown;

public class Entity {
    private GamePanel gp;
    protected int x, y;
    protected int speed;
    protected int moodA, moodB, moodC, moodD;
    
    protected CustomerACooldown custACooldown;
    protected CustomerBCooldown custBCooldown;
    protected CustomerCCooldown custCCooldown;
    protected CustomerDCooldown custDCooldown;
    
    protected BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, walk1, walk2, walk3, walk4;
    protected BufferedImage updish1, updish2, downdish1, downdish2, leftdish1, leftdish2, rightdish1, rightdish2;
    protected String direction;
    
    protected int spriteCounter = 0;
    protected int spriteNum = 1;
    
    protected Rectangle solidArea;
    
    protected int solidAreaDefaultX, solidAreaDefaultY;
    protected boolean collisionOn = false;
    
    protected int width, height;
    protected boolean hasTable;
    protected String table;
    
    public Entity(GamePanel gp){
        this.gp = gp;
        custACooldown = new CustomerACooldown(5000);
        custBCooldown = new CustomerBCooldown(5000);
        custCCooldown = new CustomerCCooldown(5000);
        custDCooldown = new CustomerDCooldown(5000);
        solidArea = new Rectangle(0, 0, 48, 48);
    }

    public void update(){
        collisionOn = false;
        gp.getcChecker().checkTile(this);
        gp.getcChecker().checkObject(this, false);
        gp.getcChecker().checkPlayer(this);
    
    // choose table to sit on 
        if(table == null){
            if(gp.getValueFromOBJ(gp.getObj(), 1).isEmpty() == true && hasTable == false){
                table = "A";
            }
            else if(gp.getValueFromOBJ(gp.getObj(), 2).isEmpty() == true && hasTable == false){
                table = "B";   
            }
            else if(gp.getValueFromOBJ(gp.getObj(), 3).isEmpty() == true && hasTable == false){
                table = "C";      
            }
            else if(gp.getValueFromOBJ(gp.getObj(), 4).isEmpty() == true && hasTable == false){
                table = "D";
            }
        }
        
        else{
        // start to walk  
            switch(table){
                case("A"):if(collisionOn == false && gp.getValueFromOBJ(gp.getObj(), 1).isEmpty() == true && hasTable == false){
                // walk path
                    if(y == 0){
                        gp.playSoundFX(2);                    
                    }
                    if(y != 190 && y != 210){
                        y += speed;
                        spriteCounter++;   
                    }
                    else if(x != 128){
                        if(direction != "to"){
                            direction = "to";
                            y += 20;     
                        }
                        x += speed;
                        spriteCounter++;   
                    }
                    else{
                    // customer A has table
                        direction = "";
                        solidArea.height = 0;
                        solidArea.width = 0;
                        hasTable = true;
                        
                    //table A with Customer
                        try {
                            gp.getValueFromOBJ(gp.getObj(), 1).setImage(ImageIO.read(new File("res/objects/TablewithCust.png")));
                        } catch (IOException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    //after has customer   
                        custACooldown.startCooldown();
                        gp.getValueFromOBJ(gp.getObj(), 1).setEmpty(false);
                        gp.getPlayer().setTableAPhase(1);
                    }
                }
                break;
                
                case("B"):if(collisionOn == false && gp.getValueFromOBJ(gp.getObj(), 2).isEmpty() == true && hasTable == false ){
                // walk path
                    if(y == 0){
                        gp.playSoundFX(2);
                    }
                    if(y != 110 && y!= 130){
                        y += speed;
                        spriteCounter++;
                    }
                    else if(x != 403){
                        if(direction != "to"){
                            direction = "to";
                            y += 20;
                        }
                        x += speed;
                        spriteCounter++;
                    }
                    else{
                    // customer B has table
                        direction = "";
                        solidArea.height = 0;
                        solidArea.width = 0;
                        hasTable = true;
                        
                    // table B with Customer
                        try {
                            gp.getValueFromOBJ(gp.getObj(), 2).setImage(ImageIO.read(new File("res/objects/TablewithCust.png")));
                        } catch (IOException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    // after has customer    
                        custBCooldown.startCooldown();
                        gp.getValueFromOBJ(gp.getObj(), 2).setEmpty(false);
                        gp.getPlayer().setTableBPhase(1);
                    }
                }
                break;
                
                case("C"):if(collisionOn == false && gp.getValueFromOBJ(gp.getObj(), 3).isEmpty() == true && hasTable == false){
                // walk path
                    if(y == 0){
                        gp.playSoundFX(2);
                    }
                    if(y != 400 && y != 420){
                        y += speed;
                        spriteCounter++;
                    }
                    else if(x != 123){
                        if(direction != "to"){
                            direction = "to";
                            y += 20;
                        }
                        x += speed;
                        spriteCounter++;
                    }
                    else{
                    // cutomer c has table
                        direction = "";
                        solidArea.height = 0;
                        solidArea.width = 0;
                        hasTable = true;
                        
                    // table c with customer    
                        try {
                            gp.getValueFromOBJ(gp.getObj(), 3).setImage(ImageIO.read(new File("res/objects/TablewithCust.png")));
                        } catch (IOException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        
                    // after has customer   
                        custCCooldown.startCooldown();
                        gp.getValueFromOBJ(gp.getObj(), 3).setEmpty(false);
                        gp.getPlayer().setTableCPhase(1);
                    }
                }
                break;
                
                case("D"): if(collisionOn == false && gp.getValueFromOBJ(gp.getObj(), 4).isEmpty() == true && hasTable == false){
                // walk path
                    if(y == 0){
                        gp.playSoundFX(2);
                    }
                    if(y != 300 && y != 320){
                        y += speed;
                        spriteCounter++;
                    }
                    else if(x != 403){
                        if(direction != "to"){
                            direction = "to";
                            y += 20;
                        }
                        x += speed;
                        spriteCounter++;
                    }
                    else{
                    // customer d has table
                        direction = "";
                        solidArea.height = 0;
                        solidArea.width = 0;
                        hasTable = true;

                        try {
                            gp.getValueFromOBJ(gp.getObj(), 4).setImage(ImageIO.read(new File("res/objects/TablewithCust.png")));
                        } catch (IOException ex) {
                            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                    // after has customer
                        custDCooldown.startCooldown();
                        gp.getValueFromOBJ(gp.getObj(), 4).setEmpty(false);
                        gp.getPlayer().setTableDPhase(1);
                    }
                }
                break;
                
            }    
        }
        
        //spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        
        // table a mood 
            if(gp.getPlayer().isCancelMoodA() == true){ //cancel cooldown when served
                custACooldown.cancelCooldown();
            }
            else{
            // after sit
                if(custACooldown.isFinishedcustA() == true && moodA == 0){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 1).setImage(ImageIO.read(new File("res/objects/mood2.png")));
                        moodA = 2;
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    custACooldown.setFinishedcustA(false);
                    custACooldown.startCooldown();
                }
            // into mood 2 (YELLOW)
                if(custACooldown.isFinishedcustA() == true && moodA == 2){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 1).setImage(ImageIO.read(new File("res/objects/mood3.png")));
                        moodA = 3;
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    custACooldown.setFinishedcustA(false);
                    custACooldown.startCooldown();
                    gp.playSoundFX(4);            
                }
            // into mood 3 (RED)
                if(custACooldown.isFinishedcustA() == true && moodA == 3){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 1).setImage(ImageIO.read(new File("res/objects/table1.png")));
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                // new customer walk in
                    custACooldown.setFinishedcustA(false);
                    gp.getValueFromOBJ(gp.getObj(), 1).setEmpty(true);
                    int pos = gp.getCust().size();
                    gp.getCust().add(new Customer(gp));
                    gp.getCust().get(pos).x = gp.getTileSize() * 2 - 8;
                    gp.getCust().get(pos).y = 0;
                    gp.getCust().get(pos).hasTable = false;
                    gp.getPlayer().setTableAPhase(0);
                    gp.playSoundFX(2);
                    gp.getPlayer().setPoint(gp.getPlayer().getPoint() - 50);
                    gp.getUi().showMessage("- 50");
                    gp.getUi().setX(300);
                    gp.getUi().setY(240);
                    System.out.println("point: " + gp.getPlayer().getPoint());
                }
            }
            
            //table b mood
            if(gp.getPlayer().isCancelMoodB() == true){ //cancel cooldown when served
                custBCooldown.cancelCooldown();
            }
            else{
            // after sit
                if(custBCooldown.isFinishedcustB() == true && moodB == 0){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 2).setImage(ImageIO.read(new File("res/objects/mood2.png")));
                        moodB = 2;
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                    custBCooldown.setFinishedcustB(false);
                    custBCooldown.startCooldown();
                }
            // into mood 2 (YELLOW)
                if(custBCooldown.isFinishedcustB() == true && moodB == 2){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 2).setImage(ImageIO.read(new File("res/objects/mood3.png")));
                        moodB = 3;
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    custBCooldown.setFinishedcustB(false);
                    custBCooldown.startCooldown();
                    gp.playSoundFX(4);
                }
            // into mood 3 (RED)
                if(custBCooldown.isFinishedcustB() == true && moodB == 3){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 2).setImage(ImageIO.read(new File("res/objects/table1.png")));
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }                
                // new customer walk in   
                    custBCooldown.setFinishedcustB(false);
                    gp.getValueFromOBJ(gp.getObj(), 2).setEmpty(true);
                    int pos = gp.getCust().size();
                    gp.getCust().add(new Customer(gp));
                    gp.getCust().get(pos).x = gp.getTileSize() * 2 - 8;
                    gp.getCust().get(pos).y = 0;
                    gp.getCust().get(pos).hasTable = false;
                    gp.getPlayer().setTableBPhase(0);
                    gp.playSoundFX(2);
                    gp.getPlayer().setPoint(gp.getPlayer().getPoint() - 50);
                    gp.getUi().showMessage("- 50");
                    gp.getUi().setX(550);
                    gp.getUi().setY(240);
                    System.out.println("point: " + gp.getPlayer().getPoint());
                }
            }
            
        //table C mood
            if(gp.getPlayer().isCancelMoodC() == true){ //cancel cooldown when served
                custCCooldown.cancelCooldown();
            }
            else{
            // after sit
                if(custCCooldown.isFinishedcustC() == true && moodC == 0){ 
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 3).setImage(ImageIO.read(new File("res/objects/mood2.png")));
                        moodC = 2;
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    custCCooldown.setFinishedcustC(false);
                    custCCooldown.startCooldown();
                }
            // into mood 2 (YELLOW)
                if(custCCooldown.isFinishedcustC() == true && moodC == 2){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 3).setImage(ImageIO.read(new File("res/objects/mood3.png")));
                        moodC = 3;
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    gp.playSoundFX(4);
                    custCCooldown.setFinishedcustC(false);
                    custCCooldown.startCooldown();
                
                }
            // into mood 3 (RED)
                if(custCCooldown.isFinishedcustC() == true && moodC == 3){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 3).setImage(ImageIO.read(new File("res/objects/table1.png")));
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                // new customer walk in
                    custCCooldown.setFinishedcustC(false);
                    gp.getValueFromOBJ(gp.getObj(), 3).setEmpty(true);
                    int pos = gp.getCust().size();
                    gp.getCust().add(new Customer(gp));
                    gp.getCust().get(pos).x = gp.getTileSize() * 2 - 8;
                    gp.getCust().get(pos).y = 0;
                    gp.getCust().get(pos).hasTable = false;
                    gp.getPlayer().setTableCPhase(0);
                    gp.playSoundFX(2);
                    gp.getPlayer().setPoint(gp.getPlayer().getPoint() - 50);
                    gp.getUi().showMessage("- 50");
                    gp.getUi().setX(300);
                    gp.getUi().setY(430);
                    System.out.println("point: " + gp.getPlayer().getPoint());
                }
            }
             
        // table D mood
            if(gp.getPlayer().isCancelMoodD() == true){ //cancel cooldown when served
                custDCooldown.cancelCooldown();
            }
            else{
            // after sit
                if(custDCooldown.isFinishedcustD() == true && moodD == 0){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 4).setImage(ImageIO.read(new File("res/objects/mood2.png")));
                        moodD = 2;                      
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    custDCooldown.setFinishedcustD(false);
                    custDCooldown.startCooldown();
                }
            // into mood 2 (YELLOW)
                if(custDCooldown.isFinishedcustD() == true && moodD == 2){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 4).setImage(ImageIO.read(new File("res/objects/mood3.png")));
                        moodD = 3; 
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    gp.playSoundFX(4);
                    custDCooldown.setFinishedcustD(false);
                    custDCooldown.startCooldown();             
                }
            // into mood 3 (RED)
                if(custDCooldown.isFinishedcustD() == true && moodD == 3){
                    try {
                        gp.getValueFromOBJ(gp.getObj(), 4).setImage(ImageIO.read(new File("res/objects/table1.png")));
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                    }
                // new customer walk in   
                    custDCooldown.setFinishedcustD(false);
                    gp.getValueFromOBJ(gp.getObj(), 4).setEmpty(true);
                    int pos = gp.getCust().size();
                    gp.getCust().add(new Customer(gp));
                    gp.getCust().get(pos).x = gp.getTileSize() * 2 - 8;
                    gp.getCust().get(pos).y = 0;
                    gp.getCust().get(pos).hasTable = false;
                    gp.getPlayer().setTableDPhase(0);
                    gp.playSoundFX(2);
                    gp.getPlayer().setPoint(gp.getPlayer().getPoint() - 50);
                    gp.getUi().showMessage("- 50");
                    gp.getUi().setX(550);
                    gp.getUi().setY(430);
                    System.out.println("point: " + gp.getPlayer().getPoint());
                }
            }    
    }
    
////////////////////////////////////////////////////////////////////////////////
    public void draw(Graphics2D g2){
    
    // change image
        BufferedImage image = null;
        switch(direction){
        // walk down
            case "walk":
                if(spriteNum == 1){
                    image = walk1;
                }
                if(spriteNum == 2){
                    image = walk2;
                }
            break;
        // walk right     
            case "to":
                width = 135;
                height = 50;
                solidArea.width = width;
                solidArea.height = height;
                if(spriteNum == 1){
                    image = walk3;
                }
                if(spriteNum == 2){
                    image = walk4;
                }
            break;

        }
        g2.drawImage(image, x, y, width, height, null);
    }

    public GamePanel getGp() {
        return gp;
    }

    public void setGp(GamePanel gp) {
        this.gp = gp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getMoodA() {
        return moodA;
    }

    public void setMoodA(int moodA) {
        this.moodA = moodA;
    }

    public int getMoodB() {
        return moodB;
    }

    public void setMoodB(int moodB) {
        this.moodB = moodB;
    }

    public int getMoodC() {
        return moodC;
    }

    public void setMoodC(int moodC) {
        this.moodC = moodC;
    }

    public int getMoodD() {
        return moodD;
    }

    public void setMoodD(int moodD) {
        this.moodD = moodD;
    }

    public BufferedImage getUp1() {
        return up1;
    }

    public void setUp1(BufferedImage up1) {
        this.up1 = up1;
    }

    public BufferedImage getUp2() {
        return up2;
    }

    public void setUp2(BufferedImage up2) {
        this.up2 = up2;
    }

    public BufferedImage getDown1() {
        return down1;
    }

    public void setDown1(BufferedImage down1) {
        this.down1 = down1;
    }

    public BufferedImage getDown2() {
        return down2;
    }

    public void setDown2(BufferedImage down2) {
        this.down2 = down2;
    }

    public BufferedImage getLeft1() {
        return left1;
    }

    public void setLeft1(BufferedImage left1) {
        this.left1 = left1;
    }

    public BufferedImage getLeft2() {
        return left2;
    }

    public void setLeft2(BufferedImage left2) {
        this.left2 = left2;
    }

    public BufferedImage getRight1() {
        return right1;
    }

    public void setRight1(BufferedImage right1) {
        this.right1 = right1;
    }

    public BufferedImage getRight2() {
        return right2;
    }

    public void setRight2(BufferedImage right2) {
        this.right2 = right2;
    }

    public BufferedImage getWalk1() {
        return walk1;
    }

    public void setWalk1(BufferedImage walk1) {
        this.walk1 = walk1;
    }

    public BufferedImage getWalk2() {
        return walk2;
    }

    public void setWalk2(BufferedImage walk2) {
        this.walk2 = walk2;
    }

    public BufferedImage getWalk3() {
        return walk3;
    }

    public void setWalk3(BufferedImage walk3) {
        this.walk3 = walk3;
    }

    public BufferedImage getWalk4() {
        return walk4;
    }

    public void setWalk4(BufferedImage walk4) {
        this.walk4 = walk4;
    }

    public BufferedImage getUpdish1() {
        return updish1;
    }

    public void setUpdish1(BufferedImage updish1) {
        this.updish1 = updish1;
    }

    public BufferedImage getUpdish2() {
        return updish2;
    }

    public void setUpdish2(BufferedImage updish2) {
        this.updish2 = updish2;
    }

    public BufferedImage getDowndish1() {
        return downdish1;
    }

    public void setDowndish1(BufferedImage downdish1) {
        this.downdish1 = downdish1;
    }

    public BufferedImage getDowndish2() {
        return downdish2;
    }

    public void setDowndish2(BufferedImage downdish2) {
        this.downdish2 = downdish2;
    }

    public BufferedImage getLeftdish1() {
        return leftdish1;
    }

    public void setLeftdish1(BufferedImage leftdish1) {
        this.leftdish1 = leftdish1;
    }

    public BufferedImage getLeftdish2() {
        return leftdish2;
    }

    public void setLeftdish2(BufferedImage leftdish2) {
        this.leftdish2 = leftdish2;
    }

    public BufferedImage getRightdish1() {
        return rightdish1;
    }

    public void setRightdish1(BufferedImage rightdish1) {
        this.rightdish1 = rightdish1;
    }

    public BufferedImage getRightdish2() {
        return rightdish2;
    }

    public void setRightdish2(BufferedImage rightdish2) {
        this.rightdish2 = rightdish2;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getSpriteCounter() {
        return spriteCounter;
    }

    public void setSpriteCounter(int spriteCounter) {
        this.spriteCounter = spriteCounter;
    }

    public int getSpriteNum() {
        return spriteNum;
    }

    public void setSpriteNum(int spriteNum) {
        this.spriteNum = spriteNum;
    }

    public Rectangle getSolidArea() {
        return solidArea;
    }

    public void setSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    public boolean isCollisionOn() {
        return collisionOn;
    }

    public void setCollisionOn(boolean collisionOn) {
        this.collisionOn = collisionOn;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHasTable() {
        return hasTable;
    }

    public void setHasTable(boolean hasTable) {
        this.hasTable = hasTable;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
    
    public void setSolidAreaX(int solidArea) {
        this.solidArea.x = solidArea;
    }
    
    public void setSolidAreaY(int solidArea) {
        this.solidArea.y = solidArea;
    }
    
    public int getSolidAreaX(){
        return solidArea.x;
    }
    
    public int getSolidAreaY(){
        return solidArea.y;
    }
    
    public void setSolidAreaWidth(int solidArea) {
        this.solidArea.width = solidArea;
    }
    
    public void setSolidAreaHeight(int solidArea) {
        this.solidArea.height = solidArea;
    }
    
    public int getSolidAreaWidth(){
        return solidArea.width;
    }
    
    public int getSolidAreaHeight(){
        return solidArea.height;
    }
}
