package tmsbloqueoporlotePlantillaPTS;

import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tms_bloqueo.entidad.TmsAutobusPlantLineasTbl;
import tms_bloqueo.entidad.TmsAutobusPlantillasEncTbl;
import tms_bloqueo.entidad.TmsComponenteBusTbl;


public class cssConsultasPTS {
    public final int OCUPADO = 1;
    public final int RESERVADO = 2;
    public final int RESERVADONC = 3;

    private Vector<Componente> vVectorUnicoReal;
    private Vector<Componente> vVectorUnico;
    private Vector<Componente> vAsientosOcupados;
    private Vector<Componente> vAsientosReservados;
    private Vector<Componente> vAsientosReservadosNoCancelables;
    private String NombrePlantilla;
    private String DescripcionPlantilla;
    private int Capacidad=0;
    private int AnchoBus;
    private int LargoBus;
    private int AnchoAsientoReal;
    private int LargoAsientoReal;
    private int AnchoAsiento;
    private int LargoAsiento;
    
    public cssConsultasPTS(){ }
    // OBTENER PLANTILLA

    private void obtenerPlantilla(List<TmsComponenteBusTbl> componentes,
                                TmsAutobusPlantillasEncTbl encabezado,
                                List<TmsAutobusPlantLineasTbl> lineas) {
            vVectorUnico = new Vector<Componente>();
            Componente c;
            int i, j;
            // ENCABEZADO
            this.NombrePlantilla = encabezado.getNombreCorto();
            this.DescripcionPlantilla = encabezado.getDescripcion();
            this.Capacidad = (int)Math.round(encabezado.getCapacidadAsientos());
            this.AnchoBus = (int)Math.round(encabezado.getAnchoCanvas());
            this.LargoBus = (int)Math.round(encabezado.getLargoCanvas());
            this.AnchoAsiento = (int)Math.round(encabezado.getAnchoAsiento());
            this.LargoAsiento = (int)Math.round(encabezado.getLargoAsiento());
            // INFO COMPONENTE ASIENTO
            String nombre = "";
            int tipo = 0;
            long compoid1, compoid2;

            for (j = 0; j < componentes.size(); j++) {
                compoid1 = lineas.get(0).getComponenteBusId();
                compoid2 = componentes.get(j).getComponenteBusId();
                if (compoid1 == compoid2) {
                    nombre = componentes.get(j).getNombreComponente();
                    tipo = (int)Math.round(componentes.get(j).getTipo());
                    break;
                }
            }
            // LINEAS     
            for (i = 0; i < this.Capacidad; i++) {
                c = new Componente();
                c.setId((int)Math.round(lineas.get(i).getComponenteBusId()));
                c.setNombre(nombre);
                c.setTipo(tipo);
                c.setUbicacion((int)Math.round(lineas.get(i).getNumeroAsiento()));
                c.setX((int)Math.round(lineas.get(i).getPosicionX()));
                c.setZ((int)Math.round(lineas.get(i).getPosicionZ()));
                this.vVectorUnico.addElement(c);
            }

            for (i = this.Capacidad; i < lineas.size(); i++) {
                c = new Componente();
                c.setId((int)Math.round(lineas.get(i).getComponenteBusId()));
                for (j = 0; j < componentes.size(); j++) {
                    compoid1 = c.getId();
                    compoid2 = componentes.get(j).getComponenteBusId();
                    if (compoid1 == compoid2) {
                        nombre = componentes.get(j).getNombreComponente();
                        tipo = (int)Math.round(componentes.get(j).getTipo());
                        break;
                    }
                }
                c.setNombre(nombre);
                c.setTipo(tipo);
                c.setUbicacion((int)Math.round(lineas.get(i).getNumeroAsiento()));
                c.setX((int)Math.round(lineas.get(i).getPosicionX()));
                c.setZ((int)Math.round(lineas.get(i).getPosicionZ()));
                this.vVectorUnico.addElement(c);
            }
    }

    // CREAR PLANTILLA
    public void crearPlantilla(int canvasX, int canvasZ, List<TmsComponenteBusTbl> componentes,
                                                        TmsAutobusPlantillasEncTbl encabezado,
                                                        List<TmsAutobusPlantLineasTbl> lineas) {
        this.obtenerPlantilla(componentes, encabezado, lineas);
        RespaldoVector();
        if (canvasX != this.AnchoBus)
            trasladarX(canvasX);
        if (canvasZ != this.LargoBus)
            trasladarZ(canvasZ);
    }

    public void RedimensionarPlantilla(int canvasX, int canvasZ,
                                List<TmsComponenteBusTbl> componentes,
                                TmsAutobusPlantillasEncTbl encabezado,
                                List<TmsAutobusPlantLineasTbl> lineas) {
        this.obtenerPlantilla(componentes, encabezado, lineas);
        // DIMENSION ORIGINAL
        vVectorUnicoReal.clear();
        for (int i = 0; i < this.vVectorUnico.size(); i++)
            this.vVectorUnicoReal.insertElementAt(this.vVectorUnico.get(i), i);

        this.AnchoAsientoReal = this.AnchoAsiento;
        this.LargoAsientoReal = this.LargoAsiento;

        // TRASLADO DEL NUEVO SIST. DE COORDENADAS
        if (canvasX != this.AnchoBus)
            trasladarX(canvasX);
        if (canvasZ != this.LargoBus)
            trasladarZ(canvasZ);

        if (!this.vAsientosOcupados.isEmpty())
            txEdoAsientos(this.vAsientosOcupados);
        if (!this.vAsientosReservados.isEmpty())
            txEdoAsientos(this.vAsientosReservados);
        if (!this.vAsientosReservadosNoCancelables.isEmpty())
            txEdoAsientos(this.vAsientosReservadosNoCancelables);
    }
    // TRASLADO DE EDO ASIENTOS

    private void txEdoAsientos(Vector<Componente> pvVector) {
        Componente c;
        int iVar1 = pvVector.size();
        int i, j;
        for (i = 0; i < iVar1; i++) {
            c = this.buscarAsiento(pvVector.get(i).getUbicacion());
            pvVector.set(i, c);
        }
    }
    // RESPALDO

    private void RespaldoVector() {
        vAsientosOcupados = new Vector<Componente>();
        vAsientosReservados = new Vector<Componente>();
        vAsientosReservadosNoCancelables = new Vector<Componente>();

        this.AnchoAsientoReal = this.AnchoAsiento;
        this.LargoAsientoReal = this.LargoAsiento;

        vVectorUnicoReal = new Vector<Componente>();
        for (int i = 0; i < this.vVectorUnico.size(); i++)
            this.vVectorUnicoReal.insertElementAt(this.vVectorUnico.get(i), i);
    }
    // TRANSLADO

    private void trasladarX(int canvasX) {
        Componente c;
        float Factor = (float)canvasX / (float)this.AnchoBus;
        int iVar1 = this.vVectorUnicoReal.size();
        for (int i = 0; i < iVar1; i++) {
            c = this.vVectorUnicoReal.get(i);
            c.setX((int)(c.getX() * Factor));
            this.vVectorUnicoReal.set(i, c);
        }
        this.AnchoAsientoReal = (int)(this.AnchoAsientoReal * Factor);
    }

    private void trasladarZ(int canvasZ) {
        Componente c;
        float Factor = (float)canvasZ / (float)this.LargoBus;
        int iVar1 = this.vVectorUnicoReal.size();
        for (int i = 0; i < iVar1; i++) {
            c = this.vVectorUnicoReal.get(i);
            c.setZ((int)(c.getZ() * Factor));
            this.vVectorUnicoReal.set(i, c);
        }
        this.LargoAsientoReal = (int)(this.LargoAsientoReal * Factor);
    }
    // VERIFICA SI EL ASIENTO SELECCIONADO YA SE RESERVO (TRUE)

    private boolean NumeroAsientoEstaReservado(int iNumeroAsiento) {
        if (this.vAsientosReservados.isEmpty())
            return false;

        int i;
        for (i = 0; i < this.vAsientosReservados.size(); i++)
            if (this.vAsientosReservados.get(i).getUbicacion() == 
                iNumeroAsiento)
                return true;

        return false;
    }
    // RESERVA ASIENTO

    private void reservarAsiento(int iNumeroAsiento) {
        Componente c = new Componente();
        c = this.buscarAsiento(iNumeroAsiento);
        this.vAsientosReservados.addElement(c);
    }
    // NO RESERVA ASIENTO

    private void noReservarAsiento(int iNumeroAsiento) {
        Componente c = new Componente();
        c = this.buscarAsiento(iNumeroAsiento);

        this.vAsientosReservados.removeElement(c);
    }

    // VERIFICA SI EL ASIENTO SELECCIONADO YA SE RESERVO COMO NO CANCELABLE(TRUE)

    private boolean NumeroAsientoEstaReservadoNC(int iNumeroAsiento) {
        if (this.vAsientosReservadosNoCancelables.isEmpty())
            return false;

        int i;
        for (i = 0; i < this.vAsientosReservadosNoCancelables.size(); i++)
            if (this.vAsientosReservadosNoCancelables.get(i).getUbicacion() == 
                iNumeroAsiento)
                return true;

        return false;
    }
    // RESERVA ASIENTO NO CANCELABLE

    private void reservarAsientoNoCancelable(int iNumeroAsiento) {
        Componente c = new Componente();
        c = this.buscarAsiento(iNumeroAsiento);
        this.vAsientosReservadosNoCancelables.addElement(c);
    }
    // NO RESERVA ASIENTO NO CANCELABLE

    private void noReservarAsientoNC(int iNumeroAsiento) {
        Componente c = new Componente();
        c = this.buscarAsiento(iNumeroAsiento);
        this.vAsientosReservadosNoCancelables.removeElement(c);
    }

    // VERIFICA SI EL ASIENTO SELECCIONADO YA SE OCUPO (TRUE)

    private boolean NumeroAsientoEstaOcupado(int iNumeroAsiento) {
        if (this.vAsientosOcupados.isEmpty())
            return false;

        int i;
        for (i = 0; i < this.vAsientosOcupados.size(); i++)
            if (this.vAsientosOcupados.get(i).getUbicacion() == iNumeroAsiento)
                return true;

        return false;
    }
    // OCUPAR ASIENTO

    private void ocuparAsiento(int iNumeroAsiento) {
        Componente c = new Componente();
        c = this.buscarAsiento(iNumeroAsiento);
        this.vAsientosOcupados.addElement(c);
    }
    // DESOCUPA ASIENTO

    private void desocuparAsiento(int iNumeroAsiento) {
        Componente c = new Componente();
        c = this.buscarAsiento(iNumeroAsiento);
        this.vAsientosOcupados.removeElement(c);
    }
    // BUSCAR INFO DE ASIENTO SELECCIONADO

    private Componente buscarAsiento(int iNumeroAsiento) {
        Componente c;
        int i;
        for (i = 0; i < this.vVectorUnicoReal.size(); i++)
            if (this.vVectorUnicoReal.get(i).getUbicacion() == 
                iNumeroAsiento) {
                c = this.vVectorUnicoReal.get(i);
                return c;
            }
        return null;
    }
    // CONTEXTO
    private static Context getInitialContext() throws NamingException {
        return new InitialContext();
    }
    //

    // FUNCIONES PUBLICAS

    // VECTOR QUE CONTIENE LA PLANTILLA

    public Vector<Componente> getPlantilla() {
        return this.vVectorUnicoReal;
    }

    // INFORMACION GENERAL (NOMBRE, DESCRIPCION DE PLANTILLA, CAPACIDAD Y DIMENSIONES DE ASIENTO)

    public String getNombrePlantilla() {
        return this.NombrePlantilla;
    }

    public String getDescripcionPlantilla() {
        return this.DescripcionPlantilla;
    }

    public int getCapacidad() {
        return this.Capacidad;
    }

    public int getAnchoAsiento() {
        return this.AnchoAsientoReal;
    }

    public int getLargoAsiento() {
        return this.LargoAsientoReal;
    }

    // REALIZAR OPERACION CON ASIENTOS
    // OCUPAR ASIENTO

    public int setAsiento(int iNumeroAsiento, int pESTADO) {
        if (this.NumeroAsientoEstaOcupado(iNumeroAsiento))
            return OCUPADO; // YA ESTA OCUPADO
        if (this.NumeroAsientoEstaReservado(iNumeroAsiento))
            return RESERVADO; // YA ESTA RESERVADO
        if (this.NumeroAsientoEstaReservadoNC(iNumeroAsiento))
            return RESERVADONC; // YA ESTA RESERVADO NO CANCELABLE
        // DESOCUPADO Y SE PROCEDE A 
        switch (pESTADO) {
        case OCUPADO:
            this.ocuparAsiento(iNumeroAsiento);
            break;
        case RESERVADO:
            this.reservarAsiento(iNumeroAsiento);
            break;
        case RESERVADONC:
            this.reservarAsientoNoCancelable(iNumeroAsiento);
            break;
        }
        return 0; // SE REALIZO LA OPERACION CON EXITO
    }
    // LIBERAR ASIENTO

    public int LiberarAsiento(int iNumeroAsiento, int pESTADO) {
        switch (pESTADO) {
        case OCUPADO:
            if (!this.NumeroAsientoEstaOcupado(iNumeroAsiento))
                return OCUPADO;
            this.desocuparAsiento(iNumeroAsiento);
            break;
        case RESERVADO:
            if (!this.NumeroAsientoEstaReservado(iNumeroAsiento))
                return RESERVADO;
            this.noReservarAsiento(iNumeroAsiento);
            break;
        case RESERVADONC:
            if (!this.NumeroAsientoEstaReservadoNC(iNumeroAsiento))
                return RESERVADONC;
            this.noReservarAsientoNC(iNumeroAsiento);
            break;
        }
        return 0;
    }

    // VECTOR DE ESTADO DE ASIENTOS

    public Vector<Componente> getVectorAsientos(int pESTADO) {
        switch (pESTADO) {
        case OCUPADO:
            return this.vAsientosOcupados;
        case RESERVADO:
            return this.vAsientosReservados;
        case RESERVADONC:
            return this.vAsientosReservadosNoCancelables;
        }
        return null; // ESTE CASO SOLO SE DARIA SI SE PASA UN ESTADO QUE NO EXISTA
    }

    public void DefaultPlantilla(int canvasX, int canvasZ, List<TmsComponenteBusTbl> componentes,
                                TmsAutobusPlantillasEncTbl encabezado,
                                List<TmsAutobusPlantLineasTbl> lineas) {
            System.out.println("ancho alto"+canvasX+"   "+canvasZ);
            this.crearPlantilla(canvasX, canvasZ, componentes, encabezado, lineas);
    }
}