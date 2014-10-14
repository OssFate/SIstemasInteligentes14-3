package unalcol.agents.examples.labyrinth.teseo.coo;


import java.util.TreeMap;

public class TeseoTest extends SimpleTeseoAgentProgramNew{
    
    /**
     * posicion actual
     */
    private Punto m_posicionActual;
    
    /**
     * Direccion en la que esta mirando, absoluta en base al inicio
     * inicializandolo con 0 hacia donde comienza mirando.
     */
    private byte m_direccionActual;
    
    /**
     * Direccion de la cual sale de un nodo.
     */
    private byte m_direccionAnteriorNodo;
    
    /**
     * Flag, para actualizar @m_lDir.
     */
    private boolean m_actualizarDireccion;
    
    /**
     * Key en el Treemap referente al ultimo nodo visitado.
     */
    private Punto m_posicionAnterior;
    
    /**
     * Estructura de Datos con los NODOS
     */
    private TreeMap<Punto,Nodo> m_mapa;
    
    

    public TeseoTest() {
        //Inicializacion de Variables
        m_posicionActual = new Punto(0, 0);
        
        m_actualizarDireccion = true;
        m_direccionActual = 0;
        m_direccionAnteriorNodo = 0;
        this.m_mapa = new TreeMap<>();
        
        //Crear nodo Incial (0,0) con las paredes que se tengan en el nodo
        //guiando de tal manera que @true seria si hay camino y @false si no hay camino
        //Nodo actual = New Nodo();
        m_mapa.put(posicionActual(), new Nodo());
        m_posicionAnterior = posicionActual();
    }
    
  
    
    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT) {
        
        Nodo nodoActual = new Nodo();
        int direccionLocalATomar = 0;
        
        //Inicio
        System.out.println(m_direccionActual + " - " + m_direccionAnteriorNodo + " - " + m_mapa);
        
        if (MT) return -1;
        
        if(hayBifurcacion(PF, PD, PA, PI)){
            //Existe nodo en esta posicion?
            if(m_mapa.containsKey(posicionActual())){
            //Si!
                nodoActual = m_mapa.get(posicionActual());
                
                if(!m_posicionAnterior.equals(posicionActual())){
                    nodoActual.asignarNodo(m_mapa.get(m_posicionAnterior), nuevaDireccion(2));
                    m_mapa.get(m_posicionAnterior).asignarNodo(nodoActual, m_direccionAnteriorNodo);
                }
                else nodoActual.sellarVia(nuevaDireccion(2));
                //Tiene vias?
                System.out.println("Lux has NO F*CKING WAYZ!!! = " + nodoActual.noTieneVias() + " Walls: " + nodoActual.darVias());
                if(!nodoActual.noTieneVias()){
                //SI!!
                    direccionLocalATomar = direccionATomar(nodoActual.tomarVia());
                    //changeDir(<Valor del return subsiguiente>);
                    actualizarDireccion(direccionLocalATomar);
                    //Take one of those --AQUI RETURN--
                    actualizarPosicion();
                    m_actualizarDireccion = true;
                    return direccionLocalATomar;
                }
                else{
                //NO!!
                    
                    //m_mapa.get(m_anterior);
                    actualizarDireccion(2);
                    m_actualizarDireccion = true;
                    actualizarPosicion();
                    return 2;
                }
            }
            //NO!!
            else {
                //Create New Node
                m_mapa.put(posicionActual(), new Nodo());
                m_mapa.get(posicionActual()).sellarVias(PF, PD, PI, m_direccionActual);
                //nodoActual.sellarVia(nuevaDireccion(2));
                m_mapa.get(posicionActual()).sellarVia(m_direccionAnteriorNodo);
                m_mapa.get(posicionActual()).asignarNodo(m_mapa.get(m_posicionAnterior), nuevaDireccion(2));
                m_mapa.get(m_posicionAnterior).asignarNodo(m_mapa.get(posicionActual()), m_direccionAnteriorNodo);
                m_posicionAnterior = posicionActual();
                m_actualizarDireccion = true;
            }
        }
          
        return caminar(PF, PD, PA, PI);
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
    boolean hayBifurcacion(boolean PF, boolean PD, boolean PA, boolean PI) {
        int viasLibres = 0;
        if (!PF) viasLibres++;
        if (!PI) viasLibres++;
        if (!PD) viasLibres++;
        
        return viasLibres > 1 && viasLibres < 4;
    }
    
    /**
     * Funcion que cambia los valores de posicion del agente, para asi saber en
     * cual posicion estamos actualmente.
     * @param pos es aquel que posee la posicion actual la cual vamos a cambiar
     * al final de la funcion.
     * @param dir es aquel que posee la direccion en la cual esta viendo el
     * agente actualmente.
     */
    private void actualizarPosicion(){
        switch(m_direccionActual){
            case 0:
                m_posicionActual.disminuirY();
                
                break;
            case 1:
                m_posicionActual.aumentarX();
                break;
            case 2:
                m_posicionActual.aumentarY();
                break;
            case 3:
                m_posicionActual.disminuirX();
                break;
            default:
                break;
        }
    }

    private void actualizarDireccion(int direccionLocalATomar) {
        m_direccionActual = (byte) ((m_direccionActual+direccionLocalATomar)%4);
    }

    private int caminar(boolean PF, boolean PD, boolean PA, boolean PI) {
        if (!PD){
            actualizarDireccion(1);
            actualizarPosicion();
            if(m_actualizarDireccion){
                m_direccionAnteriorNodo = m_direccionActual;
                m_actualizarDireccion = false;
            }
            return 1;
        }
        if (!PF){
            actualizarDireccion(0);
            actualizarPosicion();
            if(m_actualizarDireccion){
                m_direccionAnteriorNodo = m_direccionActual;
                m_actualizarDireccion = false;
            }
            return 0;
        }
        if (!PI){
            actualizarDireccion(3);
            actualizarPosicion();
            if(m_actualizarDireccion){
                m_direccionAnteriorNodo = m_direccionActual;
                m_actualizarDireccion = false;
            }
            return 3;
        }
        
        actualizarDireccion(2);
        actualizarPosicion();
        if(m_actualizarDireccion){
            m_direccionAnteriorNodo = m_direccionActual;
            m_actualizarDireccion = false;
        }
        return 2;
    }

    /**
     * Retorna un @String como la key para el treemap
     * @return 
     */
    private Punto posicionActual() {
        return m_posicionActual;
    }

    /**
     * Retorna la dirección actual global a partir de una local
     * @param direccionLocal
     * @return 
     */
    private byte nuevaDireccion(int direccionLocal) {
        return (byte) ((m_direccionActual + direccionLocal) % 4);
    }

    /**
     * (m_direccionActual + direccionLocal) % 4 = direccionGlobal
     * (a + x)%4 = b
     * 
     * x + a = 4*y + b
     * 
     * x = 4*y + b - a
     * 
     * rango de variables [0 .. 3]
     * 
     * @param direccionGlobalATomar
     * @return 
     */
    private int direccionATomar(int direccionGlobalATomar) {
        int viaLocalATomar = 0;
        viaLocalATomar = direccionGlobalATomar - m_direccionActual;
        if(viaLocalATomar < 0) viaLocalATomar += 4;
        return viaLocalATomar;
    }

}
