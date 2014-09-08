/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.agents.examples.labyrinth.teseo.simple;

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
    public void asignarNodos(Nodo nodo, int pos ){
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
    
    
    
}
