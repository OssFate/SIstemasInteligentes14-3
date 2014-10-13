package unalcol.agents.examples.labyrinth.teseo.coo;

import java.util.TreeMap;

public class Testing {
    
    public static void main(String[] args) {
        
        byte lol = 0;
        
        System.out.println(lol);
        System.out.println((lol+2)%4);
        lol = (byte) ((lol+2)%4);
        System.out.println((lol+3)%4);
        lol = (byte) ((lol+3)%4);
        System.out.println(lol );
        
    }
     
}
