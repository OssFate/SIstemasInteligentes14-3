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
public class Nodo {
    
    private Nodo n_nodoArriba;
    private Nodo n_nodoAbajo;
    private Nodo n_nodoDerecha;
    private Nodo n_nodoIzquierda;
    
    /**
     * 4 bits menos significativos representan las 4 paredes
     * visitado es 1, no visitado es 0
     * si ya ha sido visitado y el nodo esta nullo es que no hay via
     * de lo contrario no se ha visitado (no se ha tomado esa via)
     */
    
    private byte n_viasVisitadas;
    

    public Nodo() {
        this.n_viasVisitadas = 0;
    }
    
    /**
     * 8 bits 0000
     * el orden va del bit menos significativo al mas significativo 
     * bit 0 = arriba
     * bit 1 = derecha
     * bit 2 = abajo
     * bit 3 = izquierda
     * los demas no se utilizaran
     * @param porAsignar
     * @param direccion 
     * 
     */
    public void asignarNodo(Nodo porAsignar, byte direccion ){
        boolean porAsignarTieneVias;
        
        porAsignarTieneVias = porAsignar.noTieneVias();
        
        if(direccion == 0){
            this.n_nodoArriba = porAsignar;
            if(porAsignarTieneVias) n_viasVisitadas = (byte)(darVias() | 1);
        }
        if(direccion == 1){
            this.n_nodoDerecha = porAsignar;
            if(porAsignarTieneVias) n_viasVisitadas = (byte)(darVias() | 2);
        }
        if(direccion == 2){
            this.n_nodoAbajo = porAsignar;
            if(porAsignarTieneVias) n_viasVisitadas = (byte)(darVias() | 4);
        }
        if(direccion == 3){
            this.n_nodoIzquierda = porAsignar;
            if(porAsignarTieneVias) n_viasVisitadas = (byte)(darVias() | 8);
        }
        
    }

    protected boolean noTieneVias() {
        return (darVias() == 15);
    }

    protected int tomarVia() {
        if((darVias() & 1) == 0) return 0;
        if((darVias() & 2) == 0) return 1;
        if((darVias() & 8) == 0) return 3;
        return 2;
    }

    void sellarVias(boolean PF, boolean PD, boolean PI, byte direccion) {
        if(direccion == 0){
            if(PF) n_viasVisitadas = (byte) (darVias() | 1);
            if(PD) n_viasVisitadas = (byte) (darVias() | 2);
            if(PI) n_viasVisitadas = (byte) (darVias() | 8);
            //walls = (byte) (getWalls() | 4);
        }
        
        if(direccion == 1){
            if(PF) n_viasVisitadas = (byte) (darVias() | 2);
            if(PD) n_viasVisitadas = (byte) (darVias() | 4);
            if(PI) n_viasVisitadas = (byte) (darVias() | 1);
            //walls = (byte) (getWalls() | 8);
        }
        
        if(direccion == 2){
            if(PF) n_viasVisitadas = (byte) (darVias() | 4);
            if(PD) n_viasVisitadas = (byte) (darVias() | 8);
            if(PI) n_viasVisitadas = (byte) (darVias() | 2);
            //walls = (byte) (getWalls() | 1);
        }
        
        if(direccion == 3){
            if(PF) n_viasVisitadas = (byte) (darVias() | 8);
            if(PD) n_viasVisitadas = (byte) (darVias() | 1);
            if(PI) n_viasVisitadas = (byte) (darVias() | 4);
            //walls =        (byte) (getWalls() | 2);
        }
        System.out.println("WALLZ SEALED!!!! = " + darVias() + " , " + PI + " , " + PF + " , " + PD);
    }
    
    @Override
    public String toString() {
        boolean[] lol = new boolean[4];
        lol[0] = this.darNodoArriba() != null;
        lol[1] = this.darNodoDerecha() != null;
        lol[2] = this.darNodoAbajo() != null;
        lol[3] = this.darNodoIzquierda() != null;
        
        return "[" + lol[0] + "," + lol[1] + "," + lol[2] + "," + lol[3] + " - " + Integer.toBinaryString(darVias()) + "]";
    }

    void sellarVia(byte viaASellar) {
        n_viasVisitadas = (byte) (darVias() | 2^viaASellar);
    }

    /**
     * @return the arriba
     */
    public Nodo darNodoArriba() {
        return n_nodoArriba;
    }

    /**
     * @return the abajo
     */
    public Nodo darNodoAbajo() {
        return n_nodoAbajo;
    }

    /**
     * @return the derecha
     */
    public Nodo darNodoDerecha() {
        return n_nodoDerecha;
    }

    /**
     * @return the izquierda
     */
    public Nodo darNodoIzquierda() {
        return n_nodoIzquierda;
    }

    /**
     * @return the walls
     */
    public byte darVias() {
        return n_viasVisitadas;
    }
    
}
