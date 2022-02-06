/*
 * asientosResponse.java
 *
 * Created on 26 de noviembre de 2008, 07:05 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases;

/**
 *
 * @author ocruz
 */
public class asientosResponse {
    
    private String uid;
    private String mapaAsientosIda;
    private String mapaAsientosRegreso;
    private boolean success;
    private int errorCode;
    private String errorMsg;
    private int asientosDispEstudianteIda;
    private int asientosDispMaestroIda;
    private int asientosDispINSENIda;
    private int asientosDispEstudianteRegreso;
    private int asientosDispMaestroRegreso;
    private int asientosDispINSENRegreso;
    
    /** Creates a new instance of asientosResponse */
    public asientosResponse() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getMapaAsientosIda() {
        return mapaAsientosIda;
    }

    public void setMapaAsientosIda(String mapaAsientosIda) {
        this.mapaAsientosIda = mapaAsientosIda;
    }

    public String getMapaAsientosRegreso() {
        return mapaAsientosRegreso;
    }

    public void setMapaAsientosRegreso(String mapaAsientosRegreso) {
        this.mapaAsientosRegreso = mapaAsientosRegreso;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getAsientosDispEstudianteIda() {
        return asientosDispEstudianteIda;
    }

    public void setAsientosDispEstudianteIda(int asientosDispEstudiante) {
        this.asientosDispEstudianteIda = asientosDispEstudiante;
    }

    public int getAsientosDispMaestroIda() {
        return asientosDispMaestroIda;
    }

    public void setAsientosDispMaestroIda(int asientosDispMaestro) {
        this.asientosDispMaestroIda = asientosDispMaestro;
    }

    public int getAsientosDispINSENIda() {
        return asientosDispINSENIda;
    }

    public void setAsientosDispINSENIda(int asientosDispINSEN) {
        this.asientosDispINSENIda = asientosDispINSEN;
    }

    public int getAsientosDispEstudianteRegreso() {
        return asientosDispEstudianteRegreso;
    }

    public void setAsientosDispEstudianteRegreso(int asientosDispEstudiante) {
        this.asientosDispEstudianteRegreso = asientosDispEstudiante;
    }

    public int getAsientosDispMaestroRegreso() {
        return asientosDispMaestroRegreso;
    }

    public void setAsientosDispMaestroRegreso(int asientosDispMaestro) {
        this.asientosDispMaestroRegreso = asientosDispMaestro;
    }

    public int getAsientosDispINSENRegreso() {
        return asientosDispINSENRegreso;
    }

    public void setAsientosDispINSENRegreso(int asientosDispINSEN) {
        this.asientosDispINSENRegreso = asientosDispINSEN;
    }
    
}
