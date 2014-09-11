/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.agents.examples.labyrinth.teseo.cris;

/**
 *
 * @author Camilo
 */
public class Nodo {
    
    private Nodo arriba;
    private Nodo abajo;
    private Nodo derecha;
    private Nodo izquierda;
    
    //4 bits menos significativos representan las 4 paredes
    //visitado es 1, no visitado es 0
    //si ya ha sido visitado y el nodo esta nullo es que no hay via
    //de lo contrario no se ha visitado (no se ha tomado esa via)
    private byte walls;

    public Nodo() {
        this.walls = 0;
    }
    
    /**
     * 8 bits 0000
     * el orden va del bit menos significativo al mas significativo 
     * bit 0 = arriba
     * bit 1 = derecha
     * bit 2 = abajo
     * bit 3 = izquierda
     * los demas no se utilizaran
     * @param nodo
     * @param pos 
     * 
     */
    public void asignarNodos(Nodo nodo, byte pos ){
        if(pos == 0){
            this.arriba = nodo;
            walls = (byte)(walls | 1);
        }
        if(pos == 1){
            this.derecha = nodo;
            walls = (byte)(walls | 2);
        }
        if(pos == 2){
            this.abajo = nodo;
            walls = (byte)(walls | 4);
        }
        
        if(pos == 3){
            this.izquierda = nodo;
            walls = (byte)(walls | 8);
        }
        
    }

    protected boolean hasNoWays() {
        return walls == 15;
    }

    protected int takeWay() {
        if((walls & 1) != 0) return 0;
        if((walls & 2) != 0) return 1;
        if((walls & 8) != 0) return 3;
        return 2;
    }

    void sealWalls(boolean PF, boolean PD, boolean PI, byte dir) {
        if(dir == 0){
        if(PF) walls = (byte) (walls | 1);
        if(PD) walls = (byte) (walls | 2);
        if(PI) walls = (byte) (walls| 8);
        walls =        (byte) (walls | 4);
        }
        
        if(dir == 1){
        if(PF) walls = (byte) (walls | 2);
        if(PD) walls = (byte) (walls | 4);
        if(PI) walls = (byte) (walls | 1);
        walls =        (byte) (walls | 8);
        }
        
        if(dir == 2){
        if(PF) walls = (byte) (walls | 4);
        if(PD) walls = (byte) (walls | 8);
        if(PI) walls = (byte) (walls | 2);
        walls =        (byte) (walls | 1);
        }
        
        if(dir == 3){
        if(PF) walls = (byte) (walls | 8);
        if(PD) walls = (byte) (walls | 1);
        if(PI) walls = (byte) (walls | 4);
        walls =        (byte) (walls | 2);
        }
        System.out.println("WALLZ SEALED!!!! = " + walls + " , " + PF + " , " + PD + " , " + PI);
    }
    
    @Override
    public String toString() {
        boolean[] lol = new boolean[4];
        lol[0] = this.arriba != null;
        lol[1] = this.derecha != null;
        lol[2] = this.abajo != null;
        lol[3] = this.izquierda != null;
        
        return "[" + lol[0] + "," + lol[1] + "," + lol[2] + "," + lol[3] + " - " + Integer.toBinaryString(walls) + "]";
    }

    void sealWall(byte newDir) {
        walls = (byte) (walls | 2^newDir);
    }
    
}
