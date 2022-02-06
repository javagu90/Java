/*
 * AsientoAutobus.java
 *
 * Created on 27 de noviembre de 2008, 09:39 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases.datos;

/**
 *
 * @author ocruz
 */
public class AsientoAutobus {    
    private int numeroAsiento;
    private String tipoAsiento;
    private String nombreOcupante;
    private String folioBoletoNegocio;
    private String folioReversoNegocio;
    private String folioCancelacionNegocio;
    private String folioBoletoEK;
    private String folioCancelacionEK;
    
    /** Creates a new instance of AsientoAutobus */
    public AsientoAutobus() {
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public String getTipoAsiento() {
        return tipoAsiento;
    }

    public void setTipoAsiento(String tipoAsiento) {        
        this.tipoAsiento = tipoAsiento;
    }

    public String getNombreOcupante() {
        return nombreOcupante;
    }

    public void setNombreOcupante(String nombreOcupante) {
        this.nombreOcupante = nombreOcupante;
    }

    public String getFolioBoletoNegocio() {
        return folioBoletoNegocio;
    }

    public void setFolioBoletoNegocio(String folioBoletoNegocio) {
        this.folioBoletoNegocio = folioBoletoNegocio;
    }

    public String getFolioReversoNegocio() {
        return folioReversoNegocio;
    }

    public void setFolioReversoNegocio(String folioReversoNegocio) {
        this.folioReversoNegocio = folioReversoNegocio;
    }

    public String getFolioCancelacionNegocio() {
        return folioCancelacionNegocio;
    }

    public void setFolioCancelacionNegocio(String folioCancelacionNegocio) {
        this.folioCancelacionNegocio = folioCancelacionNegocio;
    }

    public String getFolioBoletoEK() {
        return folioBoletoEK;
    }

    public void setFolioBoletoEK(String folioBoletoEK) {
        this.folioBoletoEK = folioBoletoEK;
    }

    public String getFolioCancelacionEK() {
        return folioCancelacionEK;
    }

    public void setFolioCancelacionEK(String folioCancelacionEK) {
        this.folioCancelacionEK = folioCancelacionEK;
    }
    
}
