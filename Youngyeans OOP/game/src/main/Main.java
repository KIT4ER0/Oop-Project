package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("game");
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        
        //pack window w panel(window will have same size w panel)
        window.pack();
        
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        
        // set object and customer
        gamePanel.setupGame();
        //to make game loop
        gamePanel.startGameThread();
    }
}
