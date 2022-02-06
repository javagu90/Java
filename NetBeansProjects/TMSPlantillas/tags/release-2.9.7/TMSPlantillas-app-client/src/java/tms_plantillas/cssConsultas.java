package tms_plantillas;

import plantilla.solicitud.SsnPlantilla;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import plantilla.entidad.TmsAutobusPlantLineasTbl;
import plantilla.entidad.TmsComponenteBusTbl;

public class cssConsultas {
    private SsnPlantilla PlantillaEJB;
    final Context context=null;
    
    public cssConsultas() {
        try {
            Context context = getInitialContext();
            PlantillaEJB = (SsnPlantilla)context.lookup("plantilla.solicitud.SsnPlantilla");
        } catch (Exception ex) {
            System.out.println("No existe una conexión válida con la Base de Datos. Pregunte al Administrador de la Red");
            System.exit(0);
        }
    }
    // COMPONENTES
    public String[][] obtenerComponentes(){
        List<TmsComponenteBusTbl> componentes= new ArrayList<TmsComponenteBusTbl>(PlantillaEJB.queryTmsComponenteBusTblFindAll());
        String[][] componente = new String[componentes.size()][4];
        for(int i=0; i<componentes.size(); i++){
            componente[i][0]=componentes.get(i).getNombreComponente();
            componente[i][1]=componentes.get(i).getDescripcionComponente();
            componente[i][2]=String.valueOf(componentes.get(i).getTipo());
            componente[i][3]=String.valueOf(componentes.get(i).getComponenteBusId());
        }
        return componente;
    }
    // COUNT
    public long TotalEnc(){ return PlantillaEJB.countEnc(); }
    public long TotalLineas(){ return PlantillaEJB.countLineas(); }
    // INSERTAR PLANTILLA
    public void InsertarPlantilla(
         Long alineacionUltfila, Long alinearFilas, Long anchoAsiento, Float anchoAsientoF, Float anchoBus,
         Long anchoCanvas, Long asientosColder, Long asientosColizq, Long capacidadAsientos,
         String descripcion, Long distrAsientos,
         Long largoAsiento, Float largoAsientoF, Float largoBus, Long largoCanvas, String nombreCorto, Long noFilasDer,
         Long noFilasIzq, Long noFilasRealDer, Long noFilasRealIzq, Long plantillaEncId,
         Long tipoNumeracion, List<TmsAutobusPlantLineasTbl> tmsAutobusPlantLineasTblList
         ){
        try{
            long creadoPor=1;
            Date dt = new Date();
            Timestamp dFecha = new Timestamp(dt.getTime());
            PlantillaEJB.addBPE( alineacionUltfila, alinearFilas, anchoAsiento, anchoAsientoF, anchoBus,
                anchoCanvas, asientosColder, asientosColizq, capacidadAsientos,
                creadoPor, descripcion, distrAsientos, dFecha,
                largoAsiento, largoAsientoF, largoBus, largoCanvas, nombreCorto, noFilasDer,
                noFilasIzq, noFilasRealDer, noFilasRealIzq, plantillaEncId,
                tipoNumeracion, creadoPor, dFecha);
            for(int i=0; i<tmsAutobusPlantLineasTblList.size(); i++)
                PlantillaEJB.addBPL( tmsAutobusPlantLineasTblList.get(i).getPlantillaLineaId(),
                    tmsAutobusPlantLineasTblList.get(i).getPlantillaEncId(),
                    tmsAutobusPlantLineasTblList.get(i).getComponenteBusId(),
                    tmsAutobusPlantLineasTblList.get(i).getCreadoPor(),
                    tmsAutobusPlantLineasTblList.get(i).getFechaCreacion(),
                    tmsAutobusPlantLineasTblList.get(i).getNumeroAsiento(),
                    tmsAutobusPlantLineasTblList.get(i).getPosicionX(),
                    tmsAutobusPlantLineasTblList.get(i).getPosicionZ()                
                );
        }catch(NamingException ne){
            System.out.println("No existe una conexión válida con la Base de Datos. Pregunte al Administrador de la Red");
            System.exit(0);
        }
    }
    // CONTEXTO
    private static Context getInitialContext() throws NamingException {
        return new InitialContext();
    }
}