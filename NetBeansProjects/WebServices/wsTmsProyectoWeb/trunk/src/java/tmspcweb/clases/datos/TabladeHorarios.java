/*
 * TabladeHorarios.java
 *
 * Created on 27 de noviembre de 2008, 09:48 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package tmspcweb.clases.datos;

/**
 *
 * @author ocruz
 */
public class TabladeHorarios {
    private int Codigo;
    private String Descripcion;
    private String Horario;
    private String Hora1;
    private String Hora2;
    /** Creates a new instance of TabladeHorarios */
    public TabladeHorarios() {
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int Codigo) {
        this.Codigo = Codigo;
        switch(this.Codigo){
            case 0: setDescripcion("Todos");     setHorario("00:00 a 23:59"); setHora1("00:00"); setHora2("23:59"); break;
            case 1: setDescripcion("Madrugada"); setHorario("00:00 a 06:00"); setHora1("00:00"); setHora2("06:00"); break;
            case 2: setDescripcion("Mañana");    setHorario("06:01 a 12:00"); setHora1("06:01"); setHora2("12:00"); break;
            case 3: setDescripcion("Tarde");     setHorario("12:01 a 18:00"); setHora1("12:01"); setHora2("18:00"); break;
            case 4: setDescripcion("Noche");     setHorario("18:01 a 23:59"); setHora1("18:01"); setHora2("23:59"); break;
        }
    }

    public String getDescripcion() {
        return Descripcion;
    }

    private void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getHorario() {
        return Horario;
    }

    private void setHorario(String Horario) {
        this.Horario = Horario;
    }

    public String getHora1() {
        return Hora1;
    }

    private void setHora1(String Hora1) {
        this.Hora1 = Hora1;
    }

    public String getHora2() {
        return Hora2;
    }

    private void setHora2(String Hora2) {
        this.Hora2 = Hora2;
    }
    
}
