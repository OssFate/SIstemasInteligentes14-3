package unalcol.agents.examples.labyrinth.teseo.cris;

import java.util.TreeMap;

public class TeseoTest extends SimpleTeseoAgentProgramNew{
    
    /**
     * posicion actual
     */
    private int[] m_pos;
    
    /**
     * Direccion en la que esta mirando, absoluta en base al inicio
     * inicializandolo con 0 hacia donde comienza mirando.
     */
    private byte m_dir;
    
    /**
     * Direccion de la cual sale de un nodo.
     */
    private byte m_lDir;
    
    /**
     * Flag, para actualizar @m_lDir.
     */
    private boolean m_f;
    
    /**
     * Key en el Treemap referente al ultimo nodo visitado.
     */
    private String m_anterior;
    
    /**
     * Estructura de Datos con los NODOS
     */
    private TreeMap<String,Nodo> m_mapa;
    
    

    public TeseoTest() {
        //Inicializacion de Variables
        m_pos = new int[2];
        m_pos[0] = 0; m_pos[1] = 0;
        
        m_f = true;
        m_dir = 0;
        m_lDir = 0;
        this.m_mapa = new TreeMap<String,Nodo>();
        
        //Crear nodo Incial (0,0) con las paredes que se tengan en el nodo
        //guiando de tal manera que @true seria si hay camino y @false si no hay camino
        //Nodo actual = New Nodo();
        m_mapa.put(actualPos(), new Nodo());
        m_anterior = actualPos();
    }
    
  
    
    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT) {
        
        Nodo lux = new Nodo();
        
        //Inicio
        System.out.println(m_dir + " - " + m_lDir + " - " + m_mapa);
        
        if (MT) return -1;
        
        if(bifurcacion(PF, PD, PA, PI)){
            //Existe nodo en esta posicion?
            if(m_mapa.containsKey(actualPos())){
            //Si!
                lux = m_mapa.get(actualPos());
                
                if(!m_anterior.equals(actualPos())){
                    lux.asignarNodos(m_mapa.get(m_anterior), newDir(2));
                    m_mapa.get(m_anterior).asignarNodos(lux, m_lDir);
                }
                else lux.sealWall(newDir(2));
                //Tiene vias?
                System.out.println("Lux has NO FUCKING WAYZ!!! = " + lux.hasNoWays());
                if(!lux.hasNoWays()){
                //SI!!
                    //changeDir(<Valor del return subsiguiente>);
                    changeDir(lux.takeWay());
                    //Take one of those --AQUI RETURN--
                    changePos();
                    m_f = true;
                    return lux.takeWay();
                }
                else{
                //NO!!
                    changeDir(2);
                    m_f = true;
                    changePos();
                    return 2;
                }
            }
            //NO!!
            else {
                //Create New Node
                m_mapa.put(actualPos(), new Nodo());
                m_mapa.get(actualPos()).sealWalls(PF, PD, PI);
                m_mapa.get(actualPos()).asignarNodos(m_mapa.get(m_anterior), newDir(2));
                m_mapa.get(m_anterior).asignarNodos(m_mapa.get(actualPos()), m_lDir);
                m_anterior = actualPos();
                m_f = true;
            }
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
                break;
            case 2:
                m_pos[1]--;
                break;
            case 3:
                m_pos[0]--;
                break;
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
            if(m_f){
                m_lDir = m_dir;
                m_f = false;
            }
            return 3;
        }
        if (!PF){
            changeDir(0);
            changePos();
            if(m_f){
                m_lDir = m_dir;
                m_f = false;
            }
            return 0;
        }
        if (!PD){
            changeDir(1);
            changePos();
            if(m_f){
                m_lDir = m_dir;
                m_f = false;
            }
            return 1;
        }
        
        changeDir(2);
        changePos();
        if(m_f){
            m_lDir = m_dir;
            m_f = false;
        }
        return 2;
    }

    /**
     * Retorna un @String como la key para el treemap
     * @return 
     */
    private String actualPos() {
        return m_pos[0] + "," + m_pos[1];
    }

    /**
     * Retorna una posicion de guardado para @Nodo dependiendo de la direccion actual
     * @param i
     * @return 
     */
    private byte newDir(int i) {
        return (byte) ((m_dir + i) % 4);
    }

}