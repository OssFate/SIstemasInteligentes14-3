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
            walls = (byte)(getWalls() | 1);
        }
        if(pos == 1){
            this.derecha = nodo;
            walls = (byte)(getWalls() | 2);
        }
        if(pos == 2){
            this.abajo = nodo;
            walls = (byte)(getWalls() | 4);
        }
        
        if(pos == 3){
            this.izquierda = nodo;
            walls = (byte)(getWalls() | 8);
        }
        
    }

    protected boolean hasNoWays() {
        return getWalls() == 15;
    }

    protected int takeWay() {
        if((getWalls() & 1) != 0) return 0;
        if((getWalls() & 2) != 0) return 1;
        if((getWalls() & 8) != 0) return 3;
        return 2;
    }

    void sealWalls(boolean PF, boolean PD, boolean PI, byte dir) {
        if(dir == 0){
        if(PF) walls = (byte) (getWalls() | 1);
        if(PD) walls = (byte) (getWalls() | 2);
        if(PI) walls = (byte) (getWalls()| 8);
        walls =        (byte) (getWalls() | 4);
        }
        
        if(dir == 1){
        if(PF) walls = (byte) (getWalls() | 2);
        if(PD) walls = (byte) (getWalls() | 4);
        if(PI) walls = (byte) (getWalls() | 1);
        walls =        (byte) (getWalls() | 8);
        }
        
        if(dir == 2){
        if(PF) walls = (byte) (getWalls() | 4);
        if(PD) walls = (byte) (getWalls() | 8);
        if(PI) walls = (byte) (getWalls() | 2);
        walls =        (byte) (getWalls() | 1);
        }
        
        if(dir == 3){
        if(PF) walls = (byte) (getWalls() | 8);
        if(PD) walls = (byte) (getWalls() | 1);
        if(PI) walls = (byte) (getWalls() | 4);
        walls =        (byte) (getWalls() | 2);
        }
        System.out.println("WALLZ SEALED!!!! = " + getWalls() + " , " + PF + " , " + PD + " , " + PI);
    }
    
    @Override
    public String toString() {
        boolean[] lol = new boolean[4];
        lol[0] = this.getArriba() != null;
        lol[1] = this.getDerecha() != null;
        lol[2] = this.getAbajo() != null;
        lol[3] = this.getIzquierda() != null;
        
        return "[" + lol[0] + "," + lol[1] + "," + lol[2] + "," + lol[3] + " - " + Integer.toBinaryString(getWalls()) + "]";
    }

    void sealWall(byte newDir) {
        walls = (byte) (getWalls() | 2^newDir);
    }

    /**
     * @return the arriba
     */
    public Nodo getArriba() {
        return arriba;
    }

    /**
     * @return the abajo
     */
    public Nodo getAbajo() {
        return abajo;
    }

    /**
     * @return the derecha
     */
    public Nodo getDerecha() {
        return derecha;
    }

    /**
     * @return the izquierda
     */
    public Nodo getIzquierda() {
        return izquierda;
    }

    /**
     * @return the walls
     */
    public byte getWalls() {
        return walls;
    }
    
}
