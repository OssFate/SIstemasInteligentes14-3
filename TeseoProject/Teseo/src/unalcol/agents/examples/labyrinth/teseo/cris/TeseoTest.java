package unalcol.agents.examples.labyrinth.teseo.cris;

import java.util.TreeMap;

public class TeseoTest extends SimpleTeseoAgentProgramNew{
    
    /**
     * Estructura de Datos, que va a ser una HashTable (ni idea), un TreeMAP***
     * @mapa
     */
    
    
    
    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT) {
        
        //Inicio
        
        //Crear nodo Incial (0,0) con las paredes que se tengan en el nodo
        //guiando de tal manera que @true seria si hay camino y @false si no hay camino
        mapa.add(posicion, !PF, !PD, !PA, !PI);
        
        while(!MT){
            
            if()
            
            if (!PI) return 3;
            if (!PF) return 0;
            if (!PD) return 1;
            return 2;
            
        }
    }
    
}
