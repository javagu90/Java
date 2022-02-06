/*
 * JClsSintaxisAcum.java
 *
 * Created on 4 de junio de 2008, 05:10 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package subProcesosAcum;

import java.util.Vector;

/**
 *
 * @author ocruz
 */
public class JClsSintaxisAcum {
    private Vector vFolioBoleto;
    private Vector vAsientos;
    /**
     * Creates a new instance of JClsSintaxisAcum
     */
    public JClsSintaxisAcum() {
    }
    
    public int Valfolios(String folBols) {
        try{
            vFolioBoleto=new Vector();
            String aux = folBols;
            String Salida="";
            int folioOk = 0;
            if (aux.length() != 0) {
                //long folio, ainicio, afinal;
                long folio, ainicio, afinal;
                String folioRef = "";
                String[] Strfolio;
                String[] rango;
                Strfolio = aux.split(" ");
                String StrCorridaId;
                int h, bs;
                boolean yaExiste = true;
                for (int i = 0; i < Strfolio.length; i++) {
                    if (Strfolio[i].contains("-")) { //evaluo que sea un rango
                        rango = Strfolio[i].split("-"); //divido los dos elementos
                        if (rango.length == 2) { //aseguro que sean dos numeros enteros.
                            ainicio = Long.valueOf(rango[0]);
                            afinal = Long.valueOf(rango[1]);
                            if(ainicio==0 || afinal==0){ folioOk = -1; break; }
                            //reviso que sea un rango valido
                            if (ainicio < afinal) {
                                for ( folio = ainicio; folio <= afinal; folio++) { //agrego los valores
                                    //aseguro que no se repita el folio seleccionado
                                    if (!vFolioBoleto.contains(folio) && folio>0) {
                                        vFolioBoleto.addElement(folio);
                                    } else {
                                        Salida += folio + " ";
                                        folioOk = -1;
                                    }
                                }
                            }
                            else{
                                return 2;
                            }
                        }
                    } else { //no es un ciclo
                        //folio = Long.valueOf(Strfolio[i]);
                        folioRef = Strfolio[i];
                        //if(folio==0){folioOk = -1; break; }
                        if(folioRef.equals("0")){folioOk = -1; break; }
                        else{
                            //aseguro que no se repita el folio seleccionado
                            //if(!BoletoAVender.contains(folio) && !boletosVendidos.contains(folio) && folio < CupoMax){
                            if(!vFolioBoleto.contains(folioRef)){
                                vFolioBoleto.addElement(folioRef);
                            } else {
                                Salida += folioRef + " ";
                                folioOk = -1;
                            }
                        }
                    }
                }
            }
            if(vFolioBoleto.size()>0){
                for(int i=0; i<vFolioBoleto.size(); i++){
                    if(vFolioBoleto.get(i).toString().length()>20) return 1;
                }
            }
            return folioOk;
        } catch ( NullPointerException npex ) {
            return -1;
        } catch ( java.lang.ArrayIndexOutOfBoundsException aiex ) {
            return -1;
        }catch(java.util.NoSuchElementException ex){
            return -1;
        }catch(Exception x){
            return -1;
        }
    }
    
    public Vector getVectorFoliosBoletos(){ return this.vFolioBoleto; }
    
    public boolean ValAsientos(String texto) {
        try{
            vAsientos=new Vector();
            String aux = texto;
            String Salida="";
            boolean AsientoOk = true;
            boolean NoAsientoInv = false;
            if (aux.length() != 0) {
                long asiento, ainicio, afinal;
                String[] Strasiento;
                String[] rango;
                Strasiento = aux.split(" ");
                String StrCorridaId;
                int h, bs;
                boolean yaExiste = true;
                for (int i = 0; i < Strasiento.length; i++) {
                    if (Strasiento[i].contains("-")) { //evaluo que sea un rango
                        rango = Strasiento[i].split("-"); //divido los dos elementos
                        if (rango.length == 2) { //aseguro que sean dos numeros enteros.
                            ainicio = Integer.parseInt(rango[0]);
                            afinal = Integer.parseInt(rango[1]);
                            if(ainicio==0 || afinal==0){ NoAsientoInv=true; AsientoOk = false; break; }
                            //reviso que sea un rango valido
                            if (ainicio < afinal) {
                                for ( asiento = ainicio; asiento <= afinal; asiento++) { //agrego los valores
                                    //aseguro que no se repita el asiento seleccionado
                                    //if (!BoletoAVender.contains(asiento) && !boletosVendidos.contains(asiento)&& asiento<CupoMax) {
                                    if (!vAsientos.contains(asiento) && asiento>0) {
                                        vAsientos.addElement(asiento);
                                    } else {
                                        Salida += asiento + " ";
                                        AsientoOk = false;
                                    }
                                }
                            }
                        }
                    } else { //no es un ciclo
                        asiento = Integer.parseInt(Strasiento[i]);
                        if(asiento==0){ NoAsientoInv=true; AsientoOk = false; break; }
                        else{
                            //aseguro que no se repita el asiento seleccionado
                            //if(!BoletoAVender.contains(asiento) && !boletosVendidos.contains(asiento) && asiento < CupoMax){
                            if(!vAsientos.contains(asiento) && asiento>0){
                                vAsientos.addElement(asiento);
                            } else {
                                Salida += asiento + " ";
                                AsientoOk = false;
                            }
                        }
                    }
                }
            }
            
            return AsientoOk;
        } catch ( NullPointerException npex ) {
            return false;
        } catch ( java.lang.ArrayIndexOutOfBoundsException aiex ) {
            return false;
        }catch(java.util.NoSuchElementException ex){
            return false;
        }catch(Exception x){
            return false;
        }
    }
    
    public Vector getVectorAsientos(){ return this.vAsientos; }
}
