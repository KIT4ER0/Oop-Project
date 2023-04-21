package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;
    
    public Player(GamePanel gp, KeyHandler keyH){
        this.gp = gp;
        this.keyH = keyH;
        
        setDefaultValues();
        getPlayerImage();
    }
    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
    }
    public void getPlayerImage(){
        try{
            front1 = ImageIO.read(new File("res/player/front1.png"));
            front2 = ImageIO.read(new File("res/player/front2.png"));
            back1 = ImageIO.read(new File("res/player/back1.png"));
            back2 = ImageIO.read(new File("res/player/back2.png"));
            left1 = ImageIO.read(new File("res/player/left1.png"));
            left2 = ImageIO.read(new File("res/player/left2.png"));
            right1 = ImageIO.read(new File("res/player/right1.png"));
            right2 = ImageIO.read(new File("res/player/right2.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true 
                || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed == true){
                direction = "down";
                y += speed;
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }
            spriteCounter++;
            if(spriteCounter > 10){
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
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = back1;
                }
                if(spriteNum == 2){
                    image = back2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = front1;
                }
                if(spriteNum == 2){
                    image = front2;
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
        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
    }
}
