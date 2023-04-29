package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.imageio.ImageIO;
import main.GamePanel;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    //from maps text(notepad)
    public int mapTileNum[][];
    
    public TileManager(GamePanel gp){
        this.gp = gp;
        //type of tiles (grass, water , sand)
        tile = new Tile[40];
        //maps text (Matrix)
        mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
        
        getTileImage();
        loadMap("res/maps/map01.txt");
    }
    //get image
    public void getTileImage(){
        try{
            //true = wall
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(new File("res/tiles/1.png"));
            tile[0].collision = true;
            
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(new File("res/tiles/2.png"));
            tile[1].collision = true;
            
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(new File("res/tiles/3.png"));
            tile[2].collision = true;
            
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(new File("res/tiles/4.png"));
            tile[3].collision = true;
            
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(new File("res/tiles/5.png"));
            tile[4].collision = true;
            
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(new File("res/tiles/6.png"));
            tile[5].collision = true;
            
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(new File("res/tiles/7.png"));
            tile[6].collision = true;
            
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(new File("res/tiles/8.png"));
            tile[7].collision = true;
            
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(new File("res/tiles/9.png"));
            tile[8].collision = true;
            
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(new File("res/tiles/10.png"));
            tile[9].collision = true;
            
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(new File("res/tiles/11.png"));
            tile[10].collision = true;
            
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(new File("res/tiles/12.png"));
            tile[11].collision = true;
            
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(new File("res/tiles/13.png"));
            tile[12].collision = true;
            
            tile[13] = new Tile();
            tile[13].image = ImageIO.read(new File("res/tiles/14.png"));
            tile[13].collision = true;
            
            tile[14] = new Tile();
            tile[14].image = ImageIO.read(new File("res/tiles/15.png"));
            tile[14].collision = true;
            
            tile[15] = new Tile();
            tile[15].image = ImageIO.read(new File("res/tiles/16.png"));
            tile[15].collision = true;
            
            tile[16] = new Tile();
            tile[16].image = ImageIO.read(new File("res/tiles/17.png"));
            tile[16].collision = true;
            
            tile[17] = new Tile();
            tile[17].image = ImageIO.read(new File("res/tiles/18.png"));
            
            tile[18] = new Tile();
            tile[18].image = ImageIO.read(new File("res/tiles/19.png"));
            
            tile[19] = new Tile();
            tile[19].image = ImageIO.read(new File("res/tiles/20.png"));
            tile[19].collision = true;
            
            tile[20] = new Tile();
            tile[20].image = ImageIO.read(new File("res/tiles/21.png"));
            
            tile[21] = new Tile();
            tile[21].image = ImageIO.read(new File("res/tiles/22.png"));
            
            tile[22] = new Tile();
            tile[22].image = ImageIO.read(new File("res/tiles/23.png"));
            
            tile[23] = new Tile();
            tile[23].image = ImageIO.read(new File("res/tiles/24.png"));
            
            tile[24] = new Tile();
            tile[24].image = ImageIO.read(new File("res/tiles/25.png"));
            
            tile[25] = new Tile();
            tile[25].image = ImageIO.read(new File("res/tiles/26.png"));
            
            tile[26] = new Tile();
            tile[26].image = ImageIO.read(new File("res/tiles/27.png"));
            
            tile[27] = new Tile();
            tile[27].image = ImageIO.read(new File("res/tiles/28.png"));
            tile[27].collision = true;
            
            tile[28] = new Tile();
            tile[28].image = ImageIO.read(new File("res/tiles/29.png"));
            tile[28].collision = true;
            
            tile[29] = new Tile();
            tile[29].image = ImageIO.read(new File("res/tiles/30.png"));
            
            tile[30] = new Tile();
            tile[30].image = ImageIO.read(new File("res/tiles/31.png"));
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    public void loadMap(String filePath){
        try{
            //file input
            InputStream is = new FileInputStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            
            int col = 0;
            int row = 0;
            
            //change text to be matrix
            while(col < gp.maxScreenCol && row < gp.maxScreenRow){
                //read a line of text maps
                String line = br.readLine();
                
                while(col < gp.maxScreenCol){
                    //split at the space(" ")
                    String numbers[] = line.split(" ");
                    
                    int num = Integer.parseInt(numbers[col]);
                    //collect in []
                    mapTileNum[col][row] = num;
                    col++;
                }
                //read next line
                if(col == gp.maxScreenCol){
                    col =0;
                    row++;
                }
            }
            br.close();
        }catch(Exception e){
            
        }
    }
    
    public void draw(Graphics2D g2){
        
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        
        //to make col and row not be larger than the screeen
        while(col < gp.maxScreenCol && row < gp.maxScreenRow){
            //use map text matrix to make a map
            int tileNum = mapTileNum[col][row];
            
            //draw each tile
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            //drawing the next line
            if(col == gp.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
