package unalcol.agents.examples.labyrinth.teseo.cris;

import java.util.TreeMap;
import unalcol.agents.examples.labyrinth.teseo.simple.Nodo;

public class TeseoTest extends SimpleTeseoAgentProgramNew{
    
    private int[] m_pos;
    private byte m_dir;
    
    private Nodo m_anterior;
    
    private TreeMap<String,Nodo> m_mapa;
    
    

    public TeseoTest() {
        //Inicializacion de Variables
        m_pos = new int[2];
        m_pos[0] = 0; m_pos[1] = 0;
        
        m_dir = 0;
        this.m_mapa = new TreeMap<String,Nodo>();
    }
    
  
    
    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT) {
        
        //Inicio
        
        //Crear nodo Incial (0,0) con las paredes que se tengan en el nodo
        //guiando de tal manera que @true seria si hay camino y @false si no hay camino
        //Nodo actual = New Nodo();
        
        //------mapa.add(pos, !PF, !PD, !PA, !PI);
        
        if (MT) return -1;
        
        if(bifurcacion(PF, PD, PA, PI)){
            //Existe nodo en esta posicion?
            //Si!
                //Tiene vias?
                //SI!!
                    //changeDir(<Valor del return subsiguiente>);
                    //Take one of those --AQUI RETURN--
                //NO!!
                    //changeDir(2);
                    //return 2;
            //NO!!
                //Create New Node
        }
          
        return walkAlgorithm(PF, PD, PA, PI);
    }
    
    /**
     * Resive los parametros de las paredes para la posicion actual y retorna
     * @true si esta posicion tiene una bifurcacion o @false en caso contrario.
     * @param PF
     * @param PD
     * @param PA
     * @param PI
     * @return 
     */
    boolean bifurcacion(boolean PF, boolean PD, boolean PA, boolean PI) {
        int sum = 0;
        if (!PF) sum++;
        if (!PI) sum++;
        if (!PD) sum++;
        
        return sum > 1 && sum < 4;
    }
    
    /**
     * Funcion que cambia los valores de posicion del agente, para asi saber en
     * cual posicion estamos actualmente.
     * @param pos es aquel que posee la posicion actual la cual vamos a cambiar
     * al final de la funcion.
     * @param dir es aquel que posee la direccion en la cual esta viendo el
     * agente actualmente.
     */
    private void changePos(){
        switch(m_dir){
            case 0:
                m_pos[1]++;
                break;
            case 1:
                m_pos[0]++;
            case 2:
                m_pos[1]--;
            case 3:
                m_pos[0]--;
            default:
                break;
        }
    }

    private void changeDir(int i) {
        m_dir = (byte) ((m_dir+i)%4);
    }

    private int walkAlgorithm(boolean PF, boolean PD, boolean PA, boolean PI) {
        if (!PI){
            changeDir(3);
            changePos();
            return 3;
        }
        if (!PF){
            changeDir(0);
            changePos();
            return 0;
        }
        if (!PD){
            changeDir(1);
            changePos();
            return 1;
        }
        
        changeDir(2);
        changePos();
        return 2;
    }

}
