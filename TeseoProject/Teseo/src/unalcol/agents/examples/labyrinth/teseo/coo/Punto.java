/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package unalcol.agents.examples.labyrinth.teseo.coo;

/**
 *
 * @author Camilo
 */
public class Punto implements Comparable<Punto> {
    
    private int x;
    private int y;
    
    public Punto(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public void disminuirY(){
        this.y--;
    }
    
    public void aumentarY(){
        this.y++;
    }
    
    public void disminuirX(){
        this.x--;
    }
    
    public void aumentarX(){
       this.x++;
    }
    
    @Override
    public int compareTo(Punto o) {
        if(this.x == o.x && this.y == o.y){
            return 1;
        } else {
            return 0;
        }
    }
    
}
