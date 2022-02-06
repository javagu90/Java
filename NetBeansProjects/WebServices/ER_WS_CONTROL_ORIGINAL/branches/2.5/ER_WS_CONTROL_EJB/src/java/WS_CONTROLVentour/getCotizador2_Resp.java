/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROLVentour;

import WS_CONTROL.Ventour.entidades.ProductoCotiza;
import WS_CONTROL.Ventour.entidades.Unidad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author brojas
 */
public class getCotizador2_Resp implements Serializable{
private Vector  datos;
private List<ProductoCotiza> lista_ProdCotiza;
private List<Unidad> lista_Unidades;

  private boolean OperacionExitosa;
  private String  ErrorCode;
  private String  ErrorMsg;

    /**
     * @return the datos
     */
    public Vector getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(Vector datos) {
        this.datos = datos;
    }

    /**
     * @return the OperacionExitosa
     */
    public boolean isOperacionExitosa() {
        return OperacionExitosa;
    }

    /**
     * @param OperacionExitosa the OperacionExitosa to set
     */
    public void setOperacionExitosa(boolean OperacionExitosa) {
        this.OperacionExitosa = OperacionExitosa;
    }

    /**
     * @return the ErrorCode
     */
    public String getErrorCode() {
        return ErrorCode;
    }

    /**
     * @param ErrorCode the ErrorCode to set
     */
    public void setErrorCode(String ErrorCode) {
        this.ErrorCode = ErrorCode;
    }

    /**
     * @return the ErrorMsg
     */
    public String getErrorMsg() {
        return ErrorMsg;
    }

    /**
     * @param ErrorMsg the ErrorMsg to set
     */
    public void setErrorMsg(String ErrorMsg) {
        this.ErrorMsg = ErrorMsg;
    }

    /**
     * @return the lista_ProdCotiza
     */
    public List<ProductoCotiza> getLista_ProdCotiza() {
        if (lista_ProdCotiza == null )
            lista_ProdCotiza= new ArrayList<ProductoCotiza>();
        
        return lista_ProdCotiza;
    }


    /**
     * @param lista_ProdCotiza the lista_ProdCotiza to set
     */
    public void setLista_ProdCotiza(List<ProductoCotiza> lista_ProdCotiza) {
        this.lista_ProdCotiza = lista_ProdCotiza;
    }

    /**
     * @return the lista_Unidades
     */
    public List<Unidad> getLista_Unidades() {
          if (lista_Unidades == null )
            lista_Unidades= new ArrayList<Unidad>();

        return lista_Unidades;
    }

    /**
     * @param lista_Unidades the lista_Unidades to set
     */
    public void setLista_Unidades(List<Unidad> lista_Unidades) {
        this.lista_Unidades = lista_Unidades;
    }

}
