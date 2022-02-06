/*
 * TMSSesionListaPasajerosBean.java
 *
 * Created on 16 de octubre de 2008, 09:42 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package solicitud;

import entidad.TmsBoletosVentaTbl;
import entidad.TmsEstadosTbl;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author asolis
 */
@Stateless
public class TMSSesionListaPasajerosBean implements solicitud.TMSSesionListaPasajerosRemote {
    @PersistenceContext(unitName = "TMSListaPasajeros-ejbPU")
    private EntityManager manager;
    /**
     * Creates a new instance of TMSSesionListaPasajerosBean
     */
    
    public List<TmsEstadosTbl> cargarEstadosTBL() {
       // System.out.println(" **** CARGAESTADOS *****");
        return manager.createNamedQuery("TmsEstadosTbl.getAll").getResultList();
    }
    
    public Vector consultaPasajeros(String origen, String fecha, String hora, String nombre) {
       // System.out.println(" **** CONSULTA PASAJEROS *****");
        Vector kosa = null;
        //Vector info [] = null;
        Vector info = null;
        try
        {
        /*BUSCAR DBLINK*/
            
         Vector link = (Vector) manager.createNativeQuery("SELECT NOMBRE_DBLINK FROM TMS_BASE_DATOS_CONFIG_TBL WHERE NOMBRE_TERMINAL = '"+origen+"'").getSingleResult();
         String DB_LINK = link.get(0).toString();
         DB_LINK = "@"+DB_LINK;
         System.out.println(DB_LINK);
         /* FINAL BUSCAR DB_LINK*/
                 
        kosa = (Vector) manager.createNativeQuery("SELECT Xer_Tms_Pkg.Listapasajeros"+DB_LINK+"('"+origen+"', '"+fecha+"', '"+nombre+"', '"+hora+"') FROM dual").getResultList();
        //System.out.println(kosa.get(0) + "  \nTamaño "+kosa.size());
        }catch(Exception e){
            System.out.println("HOLAAAAAAAA" +e);
            List<Vector> list = new ArrayList<Vector>();
            Vector temp = new Vector();
            temp.add("No puedo");
            list.add(temp);
            kosa = new Vector();
            kosa.add(list.iterator().next());
        }
        if (kosa != null) {
            Vector temp = new Vector();
            System.out.println(kosa.get(0).toString());
            Vector ll = (Vector) kosa.get(0);
            if(ll.contains(null) == false) {
                 System.out.println("Entrooooooooooooo");
                 if (ll.get(0).toString().equals("No puedo")) {
                    System.out.println("No puedo " + kosa.get(0).toString());
                    return kosa;
                }
                if (ll.get(0).toString().equals("0")) {
                    System.out.println("Longitud 0 de respuesta");
                    kosa = null;
                } else if (ll.get(0).toString().equals("ERROR")) {
                    System.out.println("Longitud 0 de respuesta");
                    kosa = null;
                }else{
                    info = new Vector();
                    //Hacer Tokenizer de los objetitos
                    StringTokenizer primero = null;
                    StringTokenizer segundo = null;
                    String cadenas [] = null;
                    int cont = 0;
                    for (int j = 0; j < kosa.size(); j++) {
                        primero = new StringTokenizer(String.valueOf(kosa.get(j)), "-");
                        primero.nextToken();
                        //info = new Vector[primero.countTokens()];
                        //cadenas = new String[cont];
                        while(primero.hasMoreTokens()) {
                            //System.out.print("i " + i);
                            //cadenas[i] = new String(primero.nextToken());
                            segundo = new StringTokenizer(primero.nextToken(), "|");
                            temp = null;
                            temp = new Vector();
                            //System.out.println("SEGUNDO "+segundo.countTokens());
                            int ctemp = 0;
                            while(segundo.hasMoreTokens()) {
                                if(ctemp == 9) {
                                    String cad = segundo.nextToken();
                                   // System.out.println(cad);
                                    if(cad.equals("VT")||cad.equals("VT]"))
                                        temp.add("Venta");
                                    else if (cad.equals("CN")||cad.equals("CN]"))
                                        temp.add("Cancelacion");
                                    else if (cad.equals("HO")||cad.equals("HO]"))
                                        temp.add("Cambio Horario");
                                    else if (cad.equals("VA")||cad.equals("VA]"))
                                        temp.add("Boleto Abierto");
                                    else if (cad.equals("AC")||cad.equals("AC]"))
                                        temp.add("Canje Boleto Abierto");

                                }else                         
                                    temp.add(segundo.nextToken());
                                ctemp++;
                            }
                            //System.out.println(temp);
                            //info[cont] = new Vector();
                            //info[cont] = new Vector();
                            info.add(temp);
                            cont ++;
                        }
                    }
                    //for(int j = 0; j < info.size(); j++)
                        //for(int k = 0; k < info[j].size();k++)
                            //System.out.println(info.get(j));
                }
            }
            else info = null;
        }else
            info = null;
        return info;
    }

}
