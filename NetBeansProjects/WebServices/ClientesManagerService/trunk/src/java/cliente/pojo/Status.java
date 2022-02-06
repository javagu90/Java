/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cliente.pojo;

/**
 * Clase que define los status del resultado en las operaciones con la base de datos
 * @author jildefonso
 */
public class Status {

    //<editor-fold defaultstate="collapsed" desc=" Static ">
    public static final int OK = 0;
    public static final int ERROR_COMUNICARSE_BD = 1;
    public static final int FALTAN_DATOS_REQUERIDOS = 2;
    public static final int NO_EXISTE_REGISTRO = 3;
    public static final int REGISTRO_YA_EXISTE = 4;
    public static final int CONTRASENIA_INCORRECTA = 5;

    public static String getMensajeFromCode(int codigo) {
        String mensaje = null;
        switch(codigo) {
            case OK:
                mensaje = "La operación se realizo con exito";
                break;
            case ERROR_COMUNICARSE_BD:
                mensaje = "La operación no pudo realizarse, intente mas tarde";
                break;
            case FALTAN_DATOS_REQUERIDOS:
                mensaje = "Faltan datos requeridos";
                break;
            case NO_EXISTE_REGISTRO:
                mensaje = "El registro no existe";
                break;
            case REGISTRO_YA_EXISTE:
                mensaje = "El registro ya existe";
                break;
            case CONTRASENIA_INCORRECTA:
                mensaje = "La contraseña es incorrecta";
                break;
        }
        return mensaje;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Atributos ">
    private int codigo = 0;
    private String mensaje = null;
    private String detalles = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Propiedades ">
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
        setMensaje(getMensajeFromCode(codigo));
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the detalles
     */
    public String getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Constructores ">
    /**
     * Inicializa los valores del status
     * @param codigo El codigo del status
     * @param mensaje El mensaje del status
     * @param detalles los detalles del status
     */
    public Status(int codigo, String mensaje, String detalles) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }

    /**
     * Inicializa los valores del status, se obtendrá el mensaje deacuerdo al código del status
     * @param codigo El codigo del status
     * @param detalles los detalles del status
     */
    public Status(int codigo, String detalles) {
        this(codigo, getMensajeFromCode(codigo), detalles);
    }

    /**
     * Inicializa los valores del status, se obtendrá el mensaje deacuerdo al código del status
     * @param codigo El codigo del status
     */
    public Status(int codigo) {
        this(codigo, getMensajeFromCode(codigo), "");
    }

    /**
     * Inicializa el status con codigo de error -1
     */
    public Status() {
        this(-1, "", "");
    }
    //</editor-fold>

}
