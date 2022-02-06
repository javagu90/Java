/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WS_CONTROL.Ventour.entidades;

import java.io.Serializable;

/**
 *
 * @author brojas
 */
public class Unidad implements Serializable{
    private Long  Unidad_ID           ;
    private String Nombre;
    private String Titulo;
    private String Unidad;
    private String Linea1;
    private String Linea2;
    private String Linea3;
    private String Linea4;
    private String Linea5;
    private String Linea6;
    private String Linea7;
    private String Linea8;
    private String Linea9;
    private String Linea10;
    private String Linea11;
    private String Linea12;
    private String Linea13;
    private String Linea14;
    private String Linea15;
    private String Television;
    private String Banio;
    private String AireAcondicionado;
    private String Activo;
    private int No_unidades = 0;
    private String Otros1;
    private String Otros2;
    private String TipoFlotilla;

    /**
     * @return the Config_Plantlla
     */

    public Unidad() {}

     public Unidad(String[] campos) {

        this.setUnidad_ID(new Long(campos[0]));
        this.setNombre(campos[1]);
        this.setTitulo(campos[2]);
        this.setUnidad(campos[3]);
        this.setLinea1((campos[4]!=null?campos[4]:""));
        this.setLinea2((campos[5]!=null?campos[5]:""));
        this.setLinea3((campos[6]!=null?campos[6]:""));
        this.setLinea4((campos[7]!=null?campos[7]:""));
        this.setLinea5((campos[8]!=null?campos[8]:""));
        this.setLinea6((campos[9]!=null?campos[9]:""));
        this.setLinea7((campos[10]!=null?campos[10]:""));
        this.setLinea8((campos[11]!=null?campos[11]:""));
        this.setLinea9((campos[12]!=null?campos[12]:""));
        this.setLinea10((campos[13]!=null?campos[13]:""));
        this.setLinea11((campos[14]!=null?campos[14]:""));
        this.setLinea12((campos[15]!=null?campos[15]:""));
        this.setLinea13((campos[16]!=null?campos[16]:""));
        this.setLinea14((campos[17]!=null?campos[17]:""));
        this.setLinea15((campos[18]!=null?campos[18]:""));

        this.setTelevision((campos[19]!=null?campos[19]:""));
        this.setBanio((campos[20]!=null?campos[20]:""));
        this.setAireAcondicionado((campos[21]!=null?campos[21]:""));
        this.setOtros1((campos[22]!=null?campos[22]:""));
        this.setOtros2((campos[23]!=null?campos[23]:""));
         this.setTipoFlotilla((campos[24]!=null?campos[24]:""));
  
       
    }

    public Long getUnidad_ID() {
        return Unidad_ID;
    }

    /**
     * @param Config_Plantlla the Config_Plantlla to set
     */
    public void setUnidad_ID(Long Unidad_ID) {
        this.Unidad_ID = Unidad_ID;
    }

    /**
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Titulo
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * @param Titulo the Titulo to set
     */
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    /**
     * @return the Unidad
     */
    public String getUnidad() {
        return Unidad;
    }

    /**
     * @param Unidad the Unidad to set
     */
    public void setUnidad(String Unidad) {
        this.Unidad = Unidad;
    }

    /**
     * @return the Linea1
     */
    public String getLinea1() {
        return Linea1;
    }

    /**
     * @param Linea1 the Linea1 to set
     */
    public void setLinea1(String Linea1) {
        this.Linea1 = Linea1;
    }

    /**
     * @return the Linea2
     */
    public String getLinea2() {
        return Linea2;
    }

    /**
     * @param Linea2 the Linea2 to set
     */
    public void setLinea2(String Linea2) {
        this.Linea2 = Linea2;
    }

    /**
     * @return the Linea3
     */
    public String getLinea3() {
        return Linea3;
    }

    /**
     * @param Linea3 the Linea3 to set
     */
    public void setLinea3(String Linea3) {
        this.Linea3 = Linea3;
    }

    /**
     * @return the Linea4
     */
    public String getLinea4() {
        return Linea4;
    }

    /**
     * @param Linea4 the Linea4 to set
     */
    public void setLinea4(String Linea4) {
        this.Linea4 = Linea4;
    }

    /**
     * @return the Linea5
     */
    public String getLinea5() {
        return Linea5;
    }

    /**
     * @param Linea5 the Linea5 to set
     */
    public void setLinea5(String Linea5) {
        this.Linea5 = Linea5;
    }

    /**
     * @return the Linea6
     */
    public String getLinea6() {
        return Linea6;
    }

    /**
     * @param Linea6 the Linea6 to set
     */
    public void setLinea6(String Linea6) {
        this.Linea6 = Linea6;
    }

    /**
     * @return the Linea7
     */
    public String getLinea7() {
        return Linea7;
    }

    /**
     * @param Linea7 the Linea7 to set
     */
    public void setLinea7(String Linea7) {
        this.Linea7 = Linea7;
    }

    /**
     * @return the Linea8
     */
    public String getLinea8() {
        return Linea8;
    }

    /**
     * @param Linea8 the Linea8 to set
     */
    public void setLinea8(String Linea8) {
        this.Linea8 = Linea8;
    }

    /**
     * @return the Linea9
     */
    public String getLinea9() {
        return Linea9;
    }

    /**
     * @param Linea9 the Linea9 to set
     */
    public void setLinea9(String Linea9) {
        this.Linea9 = Linea9;
    }

    /**
     * @return the Linea10
     */
    public String getLinea10() {
        return Linea10;
    }

    /**
     * @param Linea10 the Linea10 to set
     */
    public void setLinea10(String Linea10) {
        this.Linea10 = Linea10;
    }

    /**
     * @return the Linea11
     */
    public String getLinea11() {
        return Linea11;
    }

    /**
     * @param Linea11 the Linea11 to set
     */
    public void setLinea11(String Linea11) {
        this.Linea11 = Linea11;
    }

    /**
     * @return the Linea12
     */
    public String getLinea12() {
        return Linea12;
    }

    /**
     * @param Linea12 the Linea12 to set
     */
    public void setLinea12(String Linea12) {
        this.Linea12 = Linea12;
    }

    /**
     * @return the Linea13
     */
    public String getLinea13() {
        return Linea13;
    }

    /**
     * @param Linea13 the Linea13 to set
     */
    public void setLinea13(String Linea13) {
        this.Linea13 = Linea13;
    }

    /**
     * @return the Linea14
     */
    public String getLinea14() {
        return Linea14;
    }

    /**
     * @param Linea14 the Linea14 to set
     */
    public void setLinea14(String Linea14) {
        this.Linea14 = Linea14;
    }

    /**
     * @return the Linea15
     */
    public String getLinea15() {
        return Linea15;
    }

    /**
     * @param Linea15 the Linea15 to set
     */
    public void setLinea15(String Linea15) {
        this.Linea15 = Linea15;
    }

    /**
     * @return the Television
     */
    public String getTelevision() {
        return Television;
    }

    /**
     * @param Television the Television to set
     */
    public void setTelevision(String Television) {
        this.Television = Television;
    }

    /**
     * @return the Banio
     */
    public String getBanio() {
        return Banio;
    }

    /**
     * @param Banio the Banio to set
     */
    public void setBanio(String Banio) {
        this.Banio = Banio;
    }

    /**
     * @return the AireAcondicionado
     */
    public String getAireAcondicionado() {
        return AireAcondicionado;
    }

    /**
     * @param AireAcondicionado the AireAcondicionado to set
     */
    public void setAireAcondicionado(String AireAcondicionado) {
        this.AireAcondicionado = AireAcondicionado;
    }

    /**
     * @return the Activo
     */
    public String getActivo() {
        return Activo;
    }

    /**
     * @param Activo the Activo to set
     */
    public void setActivo(String Activo) {
        this.Activo = Activo;
    }

    /**
     * @return the No_unidades
     */
    public int getNo_unidades() {
        return No_unidades;
    }

    /**
     * @param No_unidades the No_unidades to set
     */
    public void setNo_unidades(int No_unidades) {
        this.No_unidades = No_unidades;
    }

    /**
     * @return the OTROS1
     */
    public String getOtros1() {
        return Otros1;
    }

    /**
     * @param OTROS1 the OTROS1 to set
     */
    public void setOtros1(String Otros1) {
        this.Otros1 = Otros1;
    }

    /**
     * @return the OTROS2
     */  
    public String getOtros2() {
        return Otros2;
    }

    /**
     * @param OTROS2 the OTROS2 to set
     */
    public void setOtros2 (String Otros2) {
        this.Otros2 = Otros2;
    }

    /**
     * @return the TipoFlotilla
     */
    public String getTipoFlotilla() {
        return TipoFlotilla;
    }

    /**
     * @param TipoFlotilla the TipoFlotilla to set
     */
    public void setTipoFlotilla(String TipoFlotilla) {
        this.TipoFlotilla = TipoFlotilla;
    }

}
