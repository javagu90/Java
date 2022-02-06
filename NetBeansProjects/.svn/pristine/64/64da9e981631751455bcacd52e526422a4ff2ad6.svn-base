/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package TMSVtaProductosER.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author vgonzalez
 */
public class Producto implements java.io.Serializable{
    private long productoId=-1;
    private String productoClave = "";
    private String productoNombre = "";
    private String productoDescipcion = "";
    private String productoCategoria = "";
    //private Serializable productoImagen;
    private byte[] productoImagen;
    private String productoImagenRuta = "";
    private long productoExistencia;
    private long empresaId;
    private String empresaNombre="";
    private String empresaDescripcion="";
    private boolean eliminado;
    private String adicional1 = "";
    private String adicional2 = "";
    private String adicional3 = "";
    private String adicional4 = "";
    private String adicional5 = "";
    private List<Tarifa> productoTarifas = new ArrayList<Tarifa>();
    private float importeTarifaMostrador=0;

    public Producto()
    {
        
    }

    public Producto(Vector vp, Vector terminales,Vector empresas)
    {
       this.productoId = Long.valueOf(vp.get(0).toString());
       this.productoClave = vp.get(1)!=null?vp.get(1).toString():"";
       this.productoNombre = vp.get(2).toString();
       this.productoDescipcion = vp.get(3)!=null?vp.get(3).toString():"";
       this.productoCategoria = vp.get(4)!=null?vp.get(4).toString():"";
       this.productoImagen = vp.get(5)!=null?(byte[])vp.get(5):null;
       this.productoExistencia = vp.get(6)!=null?Long.valueOf(vp.get(6).toString()):-1;
       this.empresaId = vp.get(7)!=null?Long.valueOf(vp.get(7).toString()):-1;
       this.empresaNombre = "NO ASIGNADA";
       if(vp.get(7)!=null)
       {
           for(int i=0;i<empresas.size();i++)
           {
               Vector ve = (Vector)empresas.get(i);
               //System.out.println(vp.get(7).toString()+" ==> Empresa: "+ve);
               if(vp.get(7).toString().equals(ve.get(0).toString()))
               {
                   this.empresaNombre = ve.get(1).toString();
                   this.empresaDescripcion = (ve.get(1)==null?"":ve.get(1).toString().toUpperCase());
               }
           }
        }
       this.eliminado = vp.get(8).toString().equals("S");
       System.out.println("");
       if(vp.get(9)!=null && !vp.get(9).equals(""))
       {
           Tarifa t = new Tarifa(vp,terminales);
           this.productoTarifas.add(t);
       }

    }
    public Producto(Vector vp)
    {
       this.productoId = Long.valueOf(vp.get(0).toString());
       this.productoClave = vp.get(1)!=null?vp.get(1).toString():"";
       this.productoNombre = vp.get(2).toString();
       this.productoDescipcion = vp.get(3)!=null?vp.get(3).toString():"";
       this.productoCategoria = vp.get(4)!=null?vp.get(4).toString():"";
       this.productoImagen = vp.get(5)!=null?(byte[])vp.get(5):null;
       this.productoExistencia = vp.get(6)!=null?Long.valueOf(vp.get(6).toString()):-1;
       if(vp.get(7)!=null && !vp.get(7).equals(""))
       {
           Tarifa t = new Tarifa(vp);
           if(vp.get(7).toString().equals("CADA UNO"))
               this.setImporteTarifaMostrador(Float.valueOf(vp.get(8).toString()));
           this.productoTarifas.add(t);
       }
      this.empresaNombre = vp.get(11)==null?"":vp.get(11).toString();
      this.adicional1 = vp.get(12).toString();
      this.adicional2 = vp.get(13).toString();
      this.adicional3= vp.get(14).toString();
      this.adicional4 = vp.get(15).toString();
      this.adicional5 = vp.get(16).toString();
      this.empresaDescripcion = vp.get(17)==null?"":vp.get(17).toString();
    }

    /**
     * @return the productoId
     */
    public long getProductoId() {
        return productoId;
    }
    

    /**
     * @param productoId the productoId to set
     */
    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }

    /**
     * @return the porductoNombre
     */
    public String getProductoNombre() {
        return productoNombre;
    }

    /**
     * @param porductoNombre the porductoNombre to set
     */
    public void setProductoNombre(String porductoNombre) {
        this.productoNombre = porductoNombre;
    }

    /**
     * @return the productoDescipcion
     */
    public String getProductoDescipcion() {
        return productoDescipcion;
    }

    /**
     * @param productoDescipcion the productoDescipcion to set
     */
    public void setProductoDescipcion(String productoDescipcion) {
        this.productoDescipcion = productoDescipcion;
    }

    /**
     * @return the productoCategoria
     */
    public String getProductoCategoria() {
        return productoCategoria;
    }

    /**
     * @param productoCategoria the productoCategoria to set
     */
    public void setProductoCategoria(String productoCategoria) {
        this.productoCategoria = productoCategoria;
    }

    /**
     * @return the productoImagen
     */
    public byte[] getProductoImagen() {
        return productoImagen;
    }

    /**
     * @param productoImagen the productoImagen to set
     */
    public void setProductoImagen(byte[] productoImagen) {
        this.productoImagen = productoImagen;
    }

    /**
     * @return the productoExistencia
     */
    public long getProductoExistencia() {
        return productoExistencia;
    }

    /**
     * @param productoExistencia the productoExistencia to set
     */
    public void setProductoExistencia(long productoExistencia) {
        this.productoExistencia = productoExistencia;
    }

    /**
     * @return the empresaId
     */
    public long getEmpresaId() {
        return empresaId;
    }

    /**
     * @param empresaId the empresaId to set
     */
    public void setEmpresaId(long empresaId) {
        this.empresaId = empresaId;
    }

    /**
     * @return the eliminado
     */
    public boolean isEliminado() {
        return eliminado;
    }

    /**
     * @param eliminado the eliminado to set
     */
    public void setEliminado(boolean eliminado) {
        this.eliminado = eliminado;
    }

    /**
     * @return the adicional1
     */
    public String getAdicional1() {
        return adicional1;
    }

    /**
     * @param adicional1 the adicional1 to set
     */
    public void setAdicional1(String adicional1) {
        this.adicional1 = adicional1;
    }

    /**
     * @return the adicional2
     */
    public String getAdicional2() {
        return adicional2;
    }

    /**
     * @param adicional2 the adicional2 to set
     */
    public void setAdicional2(String adicional2) {
        this.adicional2 = adicional2;
    }

    /**
     * @return the adicional3
     */
    public String getAdicional3() {
        return adicional3;
    }

    /**
     * @param adicional3 the adicional3 to set
     */
    public void setAdicional3(String adicional3) {
        this.adicional3 = adicional3;
    }

    /**
     * @return the adicional4
     */
    public String getAdicional4() {
        return adicional4;
    }

    /**
     * @param adicional4 the adicional4 to set
     */
    public void setAdicional4(String adicional4) {
        this.adicional4 = adicional4;
    }

    /**
     * @return the adicional5
     */
    public String getAdicional5() {
        return adicional5;
    }

    /**
     * @param adicional5 the adicional5 to set
     */
    public void setAdicional5(String adicional5) {
        this.adicional5 = adicional5;
    }

    /**
     * @return the productoClave
     */
    public String getProductoClave() {
        return productoClave;
    }

    /**
     * @param productoClave the productoClave to set
     */
    public void setProductoClave(String productoClave) {
        this.productoClave = productoClave;
    }

    /**
     * @return the productoTarifas
     */
    public List<Tarifa> getProductoTarifas() {
        return productoTarifas;
    }

    /**
     * @param productoTarifas the productoTarifas to set
     */
    public void setProductoTarifas(List<Tarifa> productoTarifas) {
        this.productoTarifas = productoTarifas;
    }

    public void addTarifa(Tarifa tarifa)
    {
        this.productoTarifas.add(tarifa);
    }

    /**
     * @return the empresaNombre
     */
    public String getEmpresaNombre() {
        return empresaNombre;
    }

    /**
     * @param empresaNombre the empresaNombre to set
     */
    public void setEmpresaNombre(String empresaNombre) {
        this.empresaNombre = empresaNombre;
    }

    /**
     * @return the productoImagenRuta
     */
    public String getProductoImagenRuta() {
        return productoImagenRuta;
    }

    /**
     * @param productoImagenRuta the productoImagenRuta to set
     */
    public void setProductoImagenRuta(String productoImagenRuta) {
        this.productoImagenRuta = productoImagenRuta;
    }

    /**
     * @return the importeTarifaMostrador
     */
    public float getImporteTarifaMostrador() {
        return importeTarifaMostrador;
    }

    /**
     * @param importeTarifaMostrador the importeTarifaMostrador to set
     */
    public void setImporteTarifaMostrador(float importeTarifaMostrador) {
        this.importeTarifaMostrador = importeTarifaMostrador;
    }

    /**
     * @return the empresaDescripcion
     */
    public String getEmpresaDescripcion() {
        return empresaDescripcion;
    }

    /**
     * @param empresaDescripcion the empresaDescripcion to set
     */
    public void setEmpresaDescripcion(String empresaDescripcion) {
        this.empresaDescripcion = empresaDescripcion;
    }
}
