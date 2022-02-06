/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.entidades;

import java.io.Serializable;
import java.util.List;
import javax.ws.rs.Produces;

/**
 *
 * @author brojas
 */
public class CotizacionD  implements  Serializable{
   // private double kmts =0;
    private String FechaInicio = "";
    private String FechaFin = "";
    private String Viajeredondo = "";
    private String TipoCotixacion = "";
    private int no_personas = 1;
    private double kilometros = 0;
    private List<Producto> listaProductos = null;
    private List<Unidad> listaUnidades = null;
    private List<TramoRuta> listaTramos = null;
    private double porceDescuento = 0;
    private double porceIva = 0;
    private String ConceptoForaneo = "";
    private String ConceptoLocal = "";
     private TarifaFija TarifaFija = null;
    

    /**
     * @return the FechaInicio
     */
    public String getFechaInicio() {
        return FechaInicio;
    }

    /**
     * @param FechaInicio the FechaInicio to set
     */
    public void setFechaInicio(String FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    /**
     * @return the FechaFin
     */
    public String getFechaFin() {
        return FechaFin;
    }

    /**
     * @param FechaFin the FechaFin to set
     */
    public void setFechaFin(String FechaFin) {
        this.FechaFin = FechaFin;
    }

    /**
     * @return the Viajeredondo
     */
    public String getViajeredondo() {
        return Viajeredondo;
    }

    /**
     * @param Viajeredondo the Viajeredondo to set
     */
    public void setViajeredondo(String Viajeredondo) {
        this.Viajeredondo = Viajeredondo;
    }

    /**
     * @return the TipoCotixacion
     */
    public String getTipoCotixacion() {
        return TipoCotixacion;
    }

    /**
     * @param TipoCotixacion the TipoCotixacion to set
     */
    public void setTipoCotixacion(String TipoCotixacion) {
        this.TipoCotixacion = TipoCotixacion;
    }

    /**
     * @return the no_personas
     */
    public int getNo_personas() {
        return no_personas;
    }

    /**
     * @param no_personas the no_personas to set
     */
    public void setNo_personas(int no_personas) {
        this.no_personas = no_personas;
    }

    /**
     * @return the kilometros
     */
    public double getKilometros() {
        return kilometros;
    }

    /**
     * @param kilometros the kilometros to set
     */
    public void setKilometros(double kilometros) {
        this.kilometros = kilometros;
    }

    /**
     * @return the listaProductos
     */
    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    /**
     * @param listaProductos the listaProductos to set
     */
    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * @return the listaUnidades
     */
    public List<Unidad> getListaUnidades() {
        return listaUnidades;
    }

    /**
     * @param listaUnidades the listaUnidades to set
     */
    public void setListaUnidades(List<Unidad> listaUnidades) {
        this.listaUnidades = listaUnidades;
    }

    /**
     * @return the listaTramos
     */
    public List<TramoRuta> getListaTramos() {
        return listaTramos;
    }

    /**
     * @param listaTramos the listaTramos to set
     */
    public void setListaTramos(List<TramoRuta> listaTramos) {
        this.listaTramos = listaTramos;
    }

    /**
     * @return the porceDescuento
     */
    public double getPorceDescuento() {
        return porceDescuento;
    }

    /**
     * @param porceDescuento the porceDescuento to set
     */
    public void setPorceDescuento(double porceDescuento) {
        this.porceDescuento = porceDescuento;
    }

    /**
     * @return the porceIva
     */
    public double getPorceIva() {
        return porceIva;
    }

    /**
     * @param porceIva the porceIva to set
     */
    public void setPorceIva(double porceIva) {
        this.porceIva = porceIva;
    }

    /**
     * @return the ConceptoForaneo
     */
    public String getConceptoForaneo() {
        return ConceptoForaneo;
    }

    /**
     * @param ConceptoForaneo the ConceptoForaneo to set
     */
    public void setConceptoForaneo(String ConceptoForaneo) {
        this.ConceptoForaneo = ConceptoForaneo;
    }

    /**
     * @return the ConceptoLocal
     */
    public String getConceptoLocal() {
        return ConceptoLocal;
    }

    /**
     * @param ConceptoLocal the ConceptoLocal to set
     */
    public void setConceptoLocal(String ConceptoLocal) {
        this.ConceptoLocal = ConceptoLocal;
    }

    /**
     * @return the TarifaFija
     */
    public TarifaFija getTarifaFija() {
        return TarifaFija;
    }

    /**
     * @param TarifaFija the TarifaFija to set
     */
    public void setTarifaFija(TarifaFija TarifaFija) {
        this.TarifaFija = TarifaFija;
    }

    
}
