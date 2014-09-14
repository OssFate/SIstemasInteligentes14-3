package unalcol.agents.examples.labyrinth.teseo.cris;

import java.util.TreeMap;

public class TeseoTest extends SimpleTeseoAgentProgramNew {

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
    private TreeMap<String, Nodo> m_mapa;

    public TeseoTest() {
        //Inicializacion de Variables
        m_pos = new int[2];
        m_pos[0] = 0;
        m_pos[1] = 0;

        m_f = true;
        m_dir = 0;
        m_lDir = 0;
        this.m_mapa = new TreeMap<String, Nodo>();

        //Crear nodo Incial (0,0) con las paredes que se tengan en el nodo
        //guiando de tal manera que @true seria si hay camino y @false si no hay camino
        //Nodo actual = New Nodo();
        m_mapa.put(actualPos(), new Nodo(actualPos()));
        m_anterior = actualPos();
    }

    @Override
    public int accion(boolean PF, boolean PD, boolean PA, boolean PI, boolean MT) {

        //Inicio
        //System.out.println(m_dir + " - " + m_lDir + " - " + m_mapa);
        if (MT) {
            return -1;
        }

        if (bifurcacion(PF, PD, PA, PI)) {
            System.out.println("pos bif " + actualPos());
            //Existe nodo en esta posicion?
            if (m_mapa.containsKey(actualPos())) {
                //Sí existe!
                
                //si no es un callejon sin salida
                m_mapa.get(actualPos()).printWalls();
                if (!m_anterior.equals(actualPos())) {

                    m_mapa.get(actualPos()).asignarNodos(m_mapa.get(m_anterior), newDir(2));
                    m_mapa.get(m_anterior).asignarNodos(m_mapa.get(actualPos()), m_lDir);
                    //se actualiza el nodo anterior

                } //si es un callejon sin salida
                else {
                    //cierra ese camino sin salida
                    m_mapa.get(actualPos()).sealWall(newDir(2), 2);
                }
                m_mapa.get(actualPos()).printWalls();
                //Tiene vias?
                if (!m_mapa.get(actualPos()).hasNoWays()) {
                //SI tiene vias!!

                    changeDir(m_mapa.get(actualPos()).takeWay(m_dir));
                    String aux = actualPos();

                    changePos();
                    m_f = true;
                    System.out.println("paredes " + m_mapa.get(aux).printWalls());
                    //el problema esta aca...tal vez!
                    System.out.println("direccion tomo " + m_mapa.get(aux).takeWay(m_dir));
                    return m_mapa.get(aux).takeWay(m_dir);
                } else {
                    //NO tiene vias!!
                    System.out.println("TOMO POR NO TIENE VIAS Y YA EXISTE!!!");
                    changeDir(2);
                    m_f = true;
                    changePos();
                    return 2;
                }
            } //NO existe!!
            else {
                //agrega el nuevo nodo en el mapa
                m_mapa.put(actualPos(), new Nodo(actualPos()));
                m_mapa.get(actualPos()).sealWalls(PF, PD, PI, m_dir);
                m_mapa.get(actualPos()).asignarNodos(m_mapa.get(m_anterior), newDir(2));
                m_mapa.get(m_anterior).asignarNodos(m_mapa.get(actualPos()), m_lDir);

                m_anterior = actualPos();
                m_f = true;

                System.out.println("paredes " + m_mapa.get(actualPos()).printWalls());

            }
        }
        //si no es bifurcacion
        System.out.println("walk normal");
        return walkAlgorithm(PF, PD, PI);
    }

    /**
     * Recibe los parametros de las paredes para la posicion actual y retorna
     *
     * @true si esta posicion tiene una bifurcacion o @false en caso contrario.
     * @param PF
     * @param PD
     * @param PA
     * @param PI
     * @return
     */
    boolean bifurcacion(boolean PF, boolean PD, boolean PA, boolean PI) {
        int sum = 0;
        if (!PF) {
            sum++;
        }
        if (!PI) {
            sum++;
        }
        if (!PD) {
            sum++;
        }

        return sum > 1 && sum < 4;
    }

    /**
     * Funcion que cambia los valores de posicion del agente, para asi saber en
     * cual posicion estamos actualmente.
     *
     * @param pos es aquel que posee la posicion actual la cual vamos a cambiar
     * al final de la funcion.
     * @param dir es aquel que posee la direccion en la cual esta viendo el
     * agente actualmente.
     */
    private void changePos() {
        switch (m_dir) {
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

    /**
     * actualiza la orientacion del agente en el mapa
     *
     * @param i
     */
    private void changeDir(int i) {
        m_dir = (byte) ((m_dir + i) % 4);
    }

    private int walkAlgorithm(boolean PF, boolean PD, boolean PI) {
        System.out.println("actualpos " + actualPos() + " pf " + PF + " " + " PD " + PD + " " + " pi " + PI);
        if (!PI) {
            changeDir(3);
            changePos();
            if (m_f) {
                m_lDir = m_dir;
                m_f = false;
            }
            System.out.println("tomo 3");
            return 3;
        }
        if (!PF) {
            changeDir(0);
            changePos();
            if (m_f) {
                m_lDir = m_dir;
                m_f = false;
            }
            System.out.println("tomo 0");
            return 0;
        }
        if (!PD) {
            changeDir(1);
            changePos();
            if (m_f) {
                m_lDir = m_dir;
                m_f = false;
            }
            System.out.println("tomo 1");
            return 1;
        }

        changeDir(2);
        changePos();
        if (m_f) {
            m_lDir = m_dir;
            m_f = false;
        }
        System.out.println("tomo 2");
        return 2;
    }

    /**
     * Retorna un @String como la key para el treemap
     *
     * @return
     */
    private String actualPos() {
        return m_pos[0] + "," + m_pos[1];
    }

    /**
     * Retorna una posicion de guardado para @Nodo dependiendo de la direccion
     * actual
     *
     * @param i
     * @return
     */
    private byte newDir(int i) {
        return (byte) ((m_dir + i) % 4);
    }

}
