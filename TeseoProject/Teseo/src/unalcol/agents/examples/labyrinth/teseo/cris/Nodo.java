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
    
    private String nombre;
    
    //4 bits menos significativos representan las 4 paredes
    //visitado es 1, no visitado es 0
    //si ya ha sido visitado y el nodo esta nullo es que no hay via
    //de lo contrario no se ha visitado (no se ha tomado esa via)
    private byte walls; /*desechado*/
    /**
     * 0 si hay paso o no ha sido explorado
     * 1 si tiene un nodo asignado
     * 2 si hay pared o nodo muerto
     */
    private int []paredes;

    public Nodo() {
        this.walls = 0;
    }
    
    public Nodo(String name) {
        this.nombre = name;
        this.paredes = new int[4];
        paredes[0] = 0;
        paredes[1] = 0;
        paredes[2] = 0;
        paredes[3] = 0;
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
    public void asignarNodos(Nodo nodo, int pos ){
        if(pos == 0){
            this.arriba = nodo;
<<<<<<< HEAD
            walls = (byte)(getWalls() | 1);
        }
        if(pos == 1){
            this.derecha = nodo;
            walls = (byte)(getWalls() | 2);
        }
        if(pos == 2){
            this.abajo = nodo;
            walls = (byte)(getWalls() | 4);
=======
            paredes[0] = 1;
        }
        if(pos == 1){
            this.derecha = nodo;
            paredes[1] = 1;
        }
        if(pos == 2){
            this.abajo = nodo;
            paredes[2] = 1;
>>>>>>> FETCH_HEAD
        }
        
        if(pos == 3){
            this.izquierda = nodo;
<<<<<<< HEAD
            walls = (byte)(getWalls() | 8);
=======
            paredes[3] = 1;
>>>>>>> FETCH_HEAD
        }
        
    }

    protected boolean hasNoWays() {
<<<<<<< HEAD
        return getWalls() == 15;
    }

    protected int takeWay() {
        if((getWalls() & 1) != 0) return 0;
        if((getWalls() & 2) != 0) return 1;
        if((getWalls() & 8) != 0) return 3;
        return 2;
=======
        return (getParedes()[0] == 2 && getParedes()[1] == 2 && getParedes()[2] == 2 && getParedes()[3] == 2 );
    }

    protected int takeWay(int dir) {
        if(dir == 0){
            if(paredes[3] != 2) return 3;
            if(paredes[0] != 2) return 0;
            if(paredes[1] != 2) return 1;     
            return 2;
        }
        else if(dir == 1){
            if(paredes[0] != 2) return 3;
            if(paredes[1] != 2) return 0;
            if(paredes[2] != 2) return 1;     
            return 2;
        }
        else if(dir == 2){
            if(paredes[1] != 2) return 3;
            if(paredes[2] != 2) return 0;
            if(paredes[3] != 2) return 1;     
            return 2;
        }
        else if(dir == 3){
            if(paredes[2] != 2) return 3;
            if(paredes[3] != 2) return 0;
            if(paredes[0] != 2) return 1;     
            return 2;
        }
        System.out.println("jamas deberia por aca");
        return 0;
>>>>>>> FETCH_HEAD
    }

    void sealWalls(boolean PF, boolean PD, boolean PI, byte dir) {
        if(dir == 0){
<<<<<<< HEAD
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
=======
        if(PF) paredes [0] = 2;
        if(PD) paredes [1] = 2;
        if(PI) paredes [3] = 2;
        //walls =        (byte) (walls | 4);
        }
        
        if(dir == 1){
        if(PF) paredes [1] = 2;
        if(PD) paredes [2] = 2;
        if(PI) paredes [0] = 2;
        //walls =        (byte) (walls | 8);
        }
        
        if(dir == 2){
        if(PF)  paredes [2] = 2;
        if(PD)  paredes [3] = 2;
        if(PI)  paredes [1] = 2;
        //walls =        (byte) (walls | 1);
        }
        
        if(dir == 3){
        if(PF)  paredes [3] = 2;
        if(PD)  paredes [0] = 2;
        if(PI)  paredes [2] = 2;
        // walls =        (byte) (walls | 2);
        }
        
        ////////System.out.println("WALLZ SEALED!!!! = " + paredes[0]+" " + this.paredes[1]+" " + this.paredes()[2]+" " + this.paredes[3]+" " + " , " + PF + " , " + PD + " , " + PI);
        
>>>>>>> FETCH_HEAD
    }
    
    @Override
    public String toString() {
<<<<<<< HEAD
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
=======
        int[] lol = {0,0,0,0};
        
        if(this.arriba != null) lol[0] = 1;
        if(this.derecha != null) lol[1] = 1;
        if(this.abajo != null) lol[2] = 1;
        if(this.izquierda != null) lol[3] = 1;
        
        return "[" + lol[0] + "," + lol[1] + "," + lol[2] + "," + lol[3] + " - " +paredes[0]+" "+ paredes[1]+" "+paredes[2]+" "+paredes[3]+" ]\n";   
    }
    /**
     * sella una pared unica de acuerdo a lo que se desee si nodo u otra cosa
     * @param newDir
     * @param i 
     */
    void sealWall(int newDir, int i) {
         paredes [newDir] = i;
    }

    /**
     * @return the paredes
     */
    public int[] getParedes() {
       /* System.out.println("paredes");
        for(int i = 0; i <4; i++){
            System.out.println(paredes[i]);
        }*/
        return paredes;
    }
    
    public String printWalls(){
        return this.getParedes()[0]+" "+this.getParedes()[1]+" "+this.getParedes()[2]+" "+this.getParedes()[3];
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
>>>>>>> FETCH_HEAD
    }
    
}
