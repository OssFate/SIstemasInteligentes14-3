package pkg8puzzle;

import javax.swing.JFrame;

public class Main {

    public static void main(String[] args) {
        
        JFrame mainFrame = new JFrame("Tile Game");
        panelPuzzle panel = new panelPuzzle();
        //panelP panel = new panelP();
        
        mainFrame.setSize((138+66), (138+90));
        mainFrame.add(panel);
        
        panel.setInicial("459872316");
        
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
