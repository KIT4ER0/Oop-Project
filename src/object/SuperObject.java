package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;

public class SuperObject {
    protected BufferedImage image;
    protected String name;
    protected boolean collision = false;
    protected int x, y;
    protected int width,height;
    protected Rectangle solidArea;
    protected int solidAreaDefaultX = 0;
    protected int solidAreaDefaultY = 0;
    protected int posX, posY;
    protected boolean empty;
    
    public SuperObject(){
        solidArea = new Rectangle(0, 0, 48, 48);
    }
    
    public void draw(Graphics2D g2, GamePanel gp){
        g2.drawImage(image, x, y, width, height, null);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
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

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
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
