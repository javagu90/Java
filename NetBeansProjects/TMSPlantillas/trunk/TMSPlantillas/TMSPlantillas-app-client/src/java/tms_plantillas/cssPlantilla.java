package tms_plantillas;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import plantilla.entidad.TmsAutobusPlantLineasTbl;

public class cssPlantilla {
    // LIMITES DE UNA PLANTILLA
    private final float fminCONDUCTOR=(float)0.8;
    private final float fmaxCONDUCTOR=(float)1.2;
    private final float fminPUERTA   =(float)0.8;
    private final int   iCOLDERECHA  =1;
    private final int   iCOLIZQUIERDA=1;
    private final float fPASILLO     =(float)0.4;
    public final float fminASIENTOX =(float)0.4;
    public final float fminASIENTOZ =(float)0.5;
    public final float fmaxASIENTOX =(float)1.1;
    public final float fmaxASIENTOZ =(float)1.2;
    public final float fmaxBUSX      =(float)2.6;
    public final float fminBUSX        =(float)2.47;
    public final float fminBUSZ     =fminCONDUCTOR+fminASIENTOZ;
    public final float fmaxBUSZ     =(float)18.0;
    private final float fminESPACIOASIENTOS=(float)0.16;
    public final int imaxASIENTOSFILA=(int)(fminBUSX/fminASIENTOX)-1;

    // CAMPOS MIEMBRO
    private int iPasajeros;
    private int iAsientosFila;
    private int iColDerecha;
    private int iColIzquierda;
    private int iAsientosRestantes;
    private int iTotalFilas;
    public int iFI;
    public int iFD;
    public int iFIReal;
    public int iFDReal;
    //
    private float fConductor;
    private float fPasillo;
    private int iPasilloR;
    private float fAsientoX;
    private float fAsientoZ;
    private int fAsientoXR;
    private int fAsientoZR;
    private float fEspacioAsientosColDerecha;
    private float fEspacioAsientosColIzquierda;
    private float fAreaColDerecha;
    private float fAreaColIzquierda;
    private int iAreaColDerechaR;
    private int iAreaColIzquierdaR;
    private float fBusZ;
    private float fBusX;
    private int iBusX;
    private int iBusZ;
    private int iAlineacionZ;
    private boolean bDistribucion;
    private float fEspacioAsientoI;
    private float fEspacioAsientoD;
    private int iEspacioAsientoIR;
    private int iEspacioAsientoDR;
    private int iColCompo;
    private int iUbicarInsercion;
    private int iUbicarFila;
    private int iPX;
    private int iPZ;
    private int icIndice;
    private int icPZ;
    private int iVariable;
    //
    private String strError;
    public Asiento[][] mAI;
    public Asiento[][] mAD;
    private int[] mARespaldo;
    public Asiento[] mAR;
    
    public Vector<Compo> vCompoAereo = new Vector<Compo>();
    public Vector<Compo> vCompoLocal = new Vector<Compo>();
    
    Vector<Componente> vVectorUnico;
    
    public cssPlantilla(){ }
 
    public cssPlantilla(int piBusX, int piBusZ){
        this.setBusCanvasX(piBusX);
        this.setBusCanvasZ(piBusZ);
    }
    
    public void setPasajeros(int piPasajeros){ this.iPasajeros=piPasajeros; }
    
    public void setAsientosFila(int piAsientosFila){ this.iAsientosFila=piAsientosFila; }
    
    public void setColDerecha(int piColDerecha){ this.iColDerecha=piColDerecha; }
    
    public void setColIzquierda(int piColIzquierda){ this.iColIzquierda=piColIzquierda; }

    public void setAsientosRestantes(int piAsientosRestantes){ this.iAsientosRestantes=piAsientosRestantes; }
    
    public void setDistribucion(boolean pbD){ this.bDistribucion=pbD; }
    
    private void setConductor(float pfConductor){ this.fConductor=pfConductor; }
    
    private void setPasillo(float pfPasillo){ this.fPasillo=pfPasillo; }

    private void setPasilloR(int piPasilloR){ this.iPasilloR=piPasilloR; }
    
    public void setAsientoX(float pfAsientoX){ this.fAsientoX=pfAsientoX; }
    
    public void setAsientoZ(float pfAsientoZ){ this.fAsientoZ=pfAsientoZ; }
    
    public void setAsientoXR(int piAsientoXR){ this.fAsientoXR=piAsientoXR; }
    
    public void setAsientoZR(int piAsientoZR){ this.fAsientoZR=piAsientoZR; }
    
    public void setEspacioAsientosColDerecha(float pfEspacioAsientosDerecha){ this.fEspacioAsientosColDerecha=pfEspacioAsientosDerecha; }

    public void setEspacioAsientosColIzquierda(float pfEspacioAsientosIzquierda){ this.fEspacioAsientosColIzquierda=pfEspacioAsientosIzquierda; }
    
    public void setAreaColDerecha(float pfAreaColDerecha){ this.fAreaColDerecha=pfAreaColDerecha; }

    public void setAreaColIzquierda(float pfAreaColIzquierda){ this.fAreaColIzquierda=pfAreaColIzquierda; }
    
    public void setAreaColDerechaR(int piAreaColDerecha){ this.iAreaColDerechaR=piAreaColDerecha; }

    public void setAreaColIzquierdaR(int piAreaColIzquierda){ this.iAreaColIzquierdaR=piAreaColIzquierda; }
    
    public void setBusZ(float pfBusZ){ this.fBusZ=pfBusZ; }
    
    public void setBusX(float pfBusX){ this.fBusX=pfBusX; }
    
    public void setBusCanvasX(int piBusX){ this.iBusX=piBusX; }
    
    public void setBusCanvasZ(int piBusZ){ this.iBusZ=piBusZ; }
    
    public void setAlineacionZ(int piAlineacionZ){ this.iAlineacionZ=piAlineacionZ; }
       
    private void setEspacioAsientoI(float piEAI){ this.fEspacioAsientoI=piEAI; }
    
    private void setEspacioAsientoD(float piEAD){ this.fEspacioAsientoD=piEAD; }
    
    private void setEspacioAsientoIR(int piEAI){ this.iEspacioAsientoIR=piEAI; }
    
    private void setEspacioAsientoDR(int piEAD){ this.iEspacioAsientoDR=piEAD; }

    public int getPasajeros(){ return this.iPasajeros; }

    public int getAsientosFila(){ return this.iAsientosFila; }
    
    public int getColDerecha(){ return this.iColDerecha; }
    
    public int getColIzquierda(){ return this.iColIzquierda; }
    
    public int getAsientosRestantes(){ return this.iAsientosRestantes; }
    
    public boolean getDistribucion(){ return this.bDistribucion; }
    
    public float getConductor(){ return this.fConductor; }
    
    public float getPasillo(){ return this.fPasillo; }
    
    public int getPasilloR(){ return this.iPasilloR; }
    
    public float getAsientoX(){ return this.fAsientoX; }
    
    public float getAsientoZ(){ return this.fAsientoZ; }
    
    public int getAsientoXR(){ return this.fAsientoXR; }
    
    public int getAsientoZR(){ return this.fAsientoZR; }

    public float getEspacioAsientosColDerecha(){ return this.fEspacioAsientosColDerecha; }

    public float getEspacioAsientosColIzquierda(){ return this.fEspacioAsientosColIzquierda; }

    public float getAreaColDerecha(){ return this.fAreaColDerecha; }

    public float getAreaColIzquierda(){ return this.fAreaColIzquierda; }
    
    public int getAreaColDerechaR(){ return this.iAreaColDerechaR; }

    public int getAreaColIzquierdaR(){ return this.iAreaColIzquierdaR; }
    
    public float getBusX(){ return this.fBusX; }
    
    public float getBusZ(){ return this.fBusZ; }
    
    public int getBusCanvasX(){ return this.iBusX; }
    
    public int getBusCanvasZ(){ return this.iBusZ; }

    public int getAlineacionZ(){ return this.iAlineacionZ; }

    private float getEspacioAsientoI(){ return this.fEspacioAsientoI; }
    
    private float getEspacioAsientoD(){ return this.fEspacioAsientoD; }
    
    private int getEspacioAsientoIR(){ return this.iEspacioAsientoIR; }
    
    private int getEspacioAsientoDR(){ return this.iEspacioAsientoDR; }
    
    public void setError(int piError){
        switch(piError){
            case -1: this.strError="NO EXISTE ESPACIO SUFICIENTE PARA UBICAR LA PUERTA PRINCIPAL \n"+
            " DEBIDO A QUE EXISTEN DEMASIADAS FILAS DE ASIENTOS EN LA COLUMNA DERECHA"; break;
            case 0:  this.strError="NO EXISTE ESPACIO SUFICIENTE PARA UBICAR AL CONDUCTOR \n"+
            " DEBIDO A QUE EXISTEN DEMASIADAS FILAS DE ASIENTOS EN LA COLUMNA IZQUIERDA"; break;
            case 1:  this.strError="NO EXISTE ESPACIO SUFICIENTE PARA EL PASILLO. \n" + 
            " EL ASIENTO ES MUY ANCHO PARA QUE QUEPA EL NUMERO DE ASIENTOS POR FILA ESPECIFICADO"; break;
            case 2:  this.strError="EL ASIENTO DEBE SER MENOS ANCHO PARA QUE SE PUEDA FORMAR LA ULTIMA FILA"; break;
            case 3:  this.strError="NO EXISTE ESPACIO ADECUADO ENTRE FILAS DE ASIENTOS. \n"+
            "MINIMICE EL LARGO DEL ASIENTO O LA CAPACIDAD DE PASAJEROS"; break;
            case 4:  this.strError="NO EXISTE ESPACIO PARA UBICAR ESTE COMPONENTE"; break;
            case 5:  this.strError="YA EXISTE UN COMPONENTE EN ESTA UBICACION"; break;
            case 6:  this.strError="LAS FILAS EN CADA COLUMNA SON DE DIFERENTE NUMERO DE ASIENTOS"; break;
            case 7:  this.strError="EL NUMERO DE FILAS EN CADA COLUMNA NO ES EL MISMO"; break;
            case 8:  this.strError="HA ALCANZADO EL NUMERO MINIMO DE FILAS PERMITIDO"; break;
        }
    }
    
    public String getError(){ return this.strError; }
    
    public boolean coordenadasDemo(){
        this.iTotalFilas=this.getPasajeros()/this.getAsientosFila();
        this.setAsientosRestantes(this.getPasajeros()%this.getAsientosFila());
        
        this.iFIReal=this.iTotalFilas;
        this.iFDReal=this.iTotalFilas;
        
        if(!evaluacionAsientos()) return false;
        return true;
    }
    
    private boolean evaluacionAsientos(){
        //FILAS IZQ Y DERECHA
        this.iFI=this.iFIReal;
        this.iFD=this.iFDReal;
        if(this.getAsientosRestantes()!=0){
            switch(this.iAlineacionZ){
                case 0:
                    if(this.getAsientosRestantes()==1 && !this.getDistribucion()) this.iFI=this.iFIReal+1;
                    if(this.getAsientosRestantes()!=1) this.iFI=this.iFIReal+1;
                    break;
                case 2:
                    if(this.getAsientosRestantes()==1 && !this.getDistribucion()) this.iFD=this.iFDReal+1;
                    if(this.getAsientosRestantes()!=1) this.iFD=this.iFDReal+1;
                    break;
            }
        }
        // RESTRICCION DE AREA CONDUCTOR
        float fEspacioSobrante=this.getBusZ()-(this.getAsientoZ()*this.iFI);
        if(fEspacioSobrante<this.fminCONDUCTOR){ this.setError(0); return false; }
        if(fEspacioSobrante>this.fmaxCONDUCTOR) this.setConductor(this.fmaxCONDUCTOR);
        else this.setConductor(this.fminCONDUCTOR);
        // RESTRICCION DE AREA PUERTA
        fEspacioSobrante=this.getBusZ()-(this.getAsientoZ()*this.iFD);
        if(fEspacioSobrante<this.fminPUERTA){ this.setError(-1); return false; }
        // AREA DE TRABAJO
        this.setAreaColIzquierda(this.getBusZ()-this.getConductor());
        this.setAreaColDerecha(this.getBusZ()-this.fminPUERTA);
        //
        fEspacioSobrante=(this.getAreaColIzquierda()-(this.getAsientoZ()*this.iFI))/this.iFI;
        this.setEspacioAsientosColIzquierda(fEspacioSobrante);
        //RESTRICCION ESPACIO ENTRE ASIENTOS
        if(this.getEspacioAsientosColIzquierda()<this.fminESPACIOASIENTOS){ this.setError(3); return false; }
        //
        fEspacioSobrante=(this.getAreaColDerecha()-(this.getAsientoZ()*this.iFD))/this.iFD;
        this.setEspacioAsientosColDerecha(fEspacioSobrante);
        // DISTRIBUCION VERTICAL ENTRE COLUMNAS
        if(!this.getDistribucion() && this.iAlineacionZ!=2 && this.getAsientosRestantes()!=1)
            this.setEspacioAsientosColDerecha(this.getEspacioAsientosColIzquierda());
        // PASILLO
        this.setPasillo(this.getBusX()-(this.getAsientoX()*this.getAsientosFila()));
        if(this.getPasillo()+0.001<this.fPASILLO){ this.setError(1); return false; }
        // ASIENTO REAL
        this.setAsientoZR(Math.round((this.getAsientoZ()*this.getBusCanvasZ())/this.getBusZ()));
        this.setAsientoXR(Math.round((this.getAsientoX()*this.getBusCanvasX())/this.getBusX()));
        this.setPasilloR(Math.round((this.getPasillo()*this.getBusCanvasX())/this.getBusX())-1);
        // VECTOR UNICO DE COORDENADAS REALES
        float factor1=this.getAsientoZ()+this.getEspacioAsientosColIzquierda();
        float factor2=this.getBusCanvasZ()/this.getBusZ();
        float factor3=this.getAsientoZ()+this.getEspacioAsientosColDerecha();
        float factor4=this.getConductor()+this.getEspacioAsientosColIzquierda();
        float factor5=this.fminPUERTA+this.getEspacioAsientosColDerecha();
        
        this.setEspacioAsientoI(factor1); this.setEspacioAsientoD(factor3);
        this.setEspacioAsientoIR((int)(factor1*factor2)); this.setEspacioAsientoDR((int)(factor3*factor2));
        this.setAreaColIzquierdaR((int)(this.getAreaColIzquierda()*factor2)); this.setAreaColDerechaR((int)(this.getAreaColDerecha()*factor2));
        
        mAI = new Asiento[this.iFIReal][this.getColIzquierda()];
        mAD = new Asiento[this.iFDReal][this.getColDerecha()];
        mAR = new Asiento[this.getAsientosRestantes()];
        
        int iX, iZ, numero=1; float fZ; Asiento a;
        for(int i=0; i<this.iFIReal; i++){
            fZ=factor4 + (this.getEspacioAsientoI()*i);
            iZ=(int)(fZ*factor2);
            for(int j=0; j<this.getColIzquierda(); j++){
                iX=j*this.getAsientoXR();
                a=new Asiento();
                a.setXZ(iX,iZ);
                mAI[i][j]=a;
            }
            numero+=this.getAsientosFila();
        }
        numero=this.getColIzquierda()+1;
        for(int i=0; i<this.iFDReal; i++){
            fZ=factor5 + (this.getEspacioAsientoD()*i);
            iZ=(int)(fZ*factor2);
            for(int j=0, h=this.getColDerecha(); j<this.getColDerecha(); j++,h--){
                iX=this.getBusCanvasX()-(h*this.getAsientoXR());
                a=new Asiento();
                a.setXZ(iX,iZ);
                mAD[i][j]=a;
            }
            numero+=this.getAsientosFila();
        }
        int j=0;
        switch(this.getAlineacionZ()){
            case 0: j=0; break;
            case 1: j=this.getAsientosRestantes()*this.getAsientoXR(); j=this.getBusCanvasX()-j; break;
            case 2: j=this.getBusCanvasX()-(this.getAsientosRestantes()*this.getAsientoXR()); break;
        }
        
        // UBICAR ULTIMA FILA DE ASIENTOS
        iZ=mAI[this.iFIReal-1][0].getZ();
        if(this.iAlineacionZ==2) iZ=mAD[this.iFDReal-1][0].getZ();
        if(this.getAsientosRestantes()!=0 && this.getAsientosRestantes()!=1){
            if(this.iAlineacionZ==0) iZ+=this.getEspacioAsientoIR(); else iZ+=this.getEspacioAsientoDR();
        }
        if(!this.getDistribucion() && this.getAsientosRestantes()==1){
            if(this.iAlineacionZ==0) iZ+=this.getEspacioAsientoIR(); else iZ+=this.getEspacioAsientoDR();
        }
        if(this.getDistribucion() && this.getAsientosRestantes()==1){
                            j=(this.getBusCanvasX()-(this.getAsientoXR()*(this.getAsientosFila()+1)))/2;                                                                     
                            j=j+(this.getColIzquierda()*this.getAsientoXR()); }
        // POSICION XZ DE ULTIMA FILA DE ASIENTOS
        int limite=(this.getPasajeros()-this.getAsientosRestantes())+1;
        for(int i=0; i<this.getAsientosRestantes(); i++){
            iX=j+(i*this.getAsientoXR());
            a= new Asiento();
            a.setXZ(iX,iZ);
            mAR[i]=a;
        }
        numeracionDeAsientos(1);
        return true;
    }
    
// AGREGAR FILA DE ASIENTO
    public boolean agregarFilaDeAsientoI(){
        if(this.iFDReal==1){ this.setError(8); return false; }
        if(this.getColIzquierda()!=this.getColDerecha()){ this.setError(6); return false; }
        float fEspacioSobrante=(this.getAreaColIzquierda()-(this.getAsientoZ()*this.iFI+1))/(this.iFI+1);
        if(fEspacioSobrante<this.fminESPACIOASIENTOS){ this.setError(3); return false; }
        this.iFIReal++;
        this.iFDReal--;
        return this.evaluacionAsientos();
    }
    
    public boolean agregarFilaDeAsientoD(){
        if(this.iFIReal==1){ this.setError(8); return false; }
        if(this.getColIzquierda()!=this.getColDerecha()){ this.setError(6); return false; }
        float fEspacioSobrante=(this.getAreaColDerecha()-(this.getAsientoZ()*this.iFD+1))/(this.iFD+1);
        if(fEspacioSobrante<this.fminESPACIOASIENTOS){ this.setError(3); return false; }        
        this.iFDReal++;
        this.iFIReal--;
        return this.evaluacionAsientos();
    }
// ALINEAR COLUMNAS
    public boolean AlinearColumnas(){
        //if(this.iFDReal!=this.iFIReal){ this.setError(7); return false; }
        if(this.iFD!=this.iFI){ this.setError(7); return false; }
        int i,j;
        mARespaldo = new int[this.iTotalFilas];
        
        for(i=0; i<this.iTotalFilas; i++){
            mARespaldo[i]=this.mAD[i][0].getZ();
            for(j=0; j<this.getColDerecha();j++)
                this.mAD[i][j].setZ(this.mAI[i][0].getZ());
        }
        return true;
    }

    public boolean cargarRespaldo(){
        if(this.iFDReal!=this.iFIReal){ this.setError(7); return false; }
        int i,j;
        for(i=0; i<this.iTotalFilas; i++)
            for(j=0; j<this.getColDerecha();j++)
                this.mAD[i][j].setZ(this.mARespaldo[i]);
        return true;
    }
// BUSQUEDA DE ASIENTO
    public boolean buscarAsiento(int iNumero){
        int i,j;
        this.iUbicarInsercion=0;
        
        for(i=0; i<this.iFIReal; i++){
            for(j=0; j<this.getColIzquierda(); j++)
                if(mAI[i][j].getNumero()==iNumero){
                    this.iColCompo=0;
                    this.iUbicarFila=i;
                    this.iPX=mAI[i][j].getX(); this.iPZ=mAI[i][j].getZ();
                    if(i==this.iFIReal-1) this.iUbicarInsercion=1;
                    return true;
                }
        }

        for(i=0; i<this.iFDReal; i++){
            for(j=0; j<this.getColDerecha(); j++)
                if(mAD[i][j].getNumero()==iNumero){
                    this.iColCompo=2;
                    this.iUbicarFila=i;
                    this.iPX=mAD[i][j].getX(); this.iPZ=mAD[i][j].getZ();
                    if(i==this.iFDReal-1) this.iUbicarInsercion=1;
                    return true;
                }
        }
        
        for(i=0; i<this.getAsientosRestantes(); i++){
                if(mAR[i].getNumero()==iNumero){
                    this.iPX=mAR[i].getX(); this.iPZ=mAR[i].getZ();
                    this.iUbicarInsercion=2;
                    if(this.iAlineacionZ==0) this.iColCompo=0;
                    else this.iColCompo=2;
                    return true;
                }
        }

        return false;
    }
    //INSERTAR COMPONENTE
    public boolean insertarComponente(int iTipo, String nombre, int iDespuesDeAsiento, int compon_bus_id){
        System.out.println(" insertarComponente "+compon_bus_id+" tIPO "+iTipo);
        switch(iTipo){
            case 0: this.buscarAsiento(iDespuesDeAsiento);
                    if(!ExisteEspacioLibre()){ this.setError(4); return false; }
                    int iDespuesDeAsientoReal= this.AsientoExtremo(iDespuesDeAsiento);
                    this.buscarAsiento(iDespuesDeAsientoReal);
                    InsertaCompoLocal(nombre, iDespuesDeAsiento, this.iPX, this.iPZ, this.iColCompo, iDespuesDeAsientoReal);
                    return true;
            case 1: if(this.buscarCompoAereo(iDespuesDeAsiento)){ this.setError(5); return false; }
                    this.buscarAsiento(iDespuesDeAsiento);
                    this.InsertarCompoAero(1, nombre,iDespuesDeAsiento,this.iPX,this.iPZ,compon_bus_id);
                    return true;
        }
      return true;
    }
    
    private boolean ExisteEspacioLibre(){
        int iArea=this.getAreaColIzquierdaR(), espacio, iVar=this.iFI+1, iVar2;
        int factor1 = (int) (this.fminESPACIOASIENTOS * (this.getBusCanvasZ()/this.getBusZ()));
        if(this.iColCompo==2){ iArea=this.getAreaColDerechaR(); iVar=this.iFD+1; }
        iVar=iVar+espacioCompoLocal();
        espacio=iVar*this.getAsientoZR();
        iVar2=(iArea-espacio)/iVar;
        if(iVar2<factor1) return false;
        this.iVariable=iVar2+this.getAsientoZR();
        return true;
    }

    // BUSQUEDA COMPONENTE AEREO
    private boolean buscarCompoAereo(int iUbicacion){
        if(vCompoAereo.isEmpty()) return false;
        for(int i=0; i<vCompoAereo.size(); i++)
            if(iUbicacion==vCompoAereo.get(i).getUbicacion()) return true;
        return false;
    }    
    private void InsertarCompoAero(int iTipo, String nombre, int iDespuesDeAsiento, int pX, int pZ, int comp_bus_id){
        System.out.println(comp_bus_id);
        Compo compo = new Compo();
        compo.setTipo(iTipo);
        compo.setNombre(nombre);
        compo.setUbicacion(iDespuesDeAsiento);
        compo.setComp_Bus_id(comp_bus_id);
        if(iDespuesDeAsiento==0){ pX=(this.getBusCanvasX()/2)-8; pZ=mAD[0][0].getZ(); }
        compo.setXZ(pX,pZ-6);
        int i= vCompoAereo.size();
        vCompoAereo.insertElementAt(compo,i);
    }
    // BUSQUEDA COMPONENTE LOCAL
    private int buscarCompoLocal(int iInicio){
        if(vCompoLocal.isEmpty()) return 0;
        for(int i=iInicio; i<vCompoLocal.size(); i++)
            if(this.iColCompo==vCompoLocal.get(i).getCol()){
                this.icPZ = vCompoLocal.get(i).getZ();
                this.icIndice=i;
                return vCompoLocal.get(i).getUbicacion();
            }
        return 0;
    }
     
    private int espacioCompoLocal(){
        if(vCompoLocal.isEmpty()) return 0;
        int suma=0;
        for(int i=0; i<vCompoLocal.size(); i++)
            if(this.iColCompo==vCompoLocal.get(i).getCol()) suma++;
        return suma;
    }
    // INSERTAR COMPO LOCAL
    private void InsertaCompoLocal(String nombre, int iDespuesDeAsiento, int pX, int pZ, int iCol, int iDespuesDeAsientoReal){
        switch(this.iUbicarInsercion){
            case 0: insertarDentro(iCol); this.buscarAsiento(iDespuesDeAsiento); pZ=this.iPZ+this.iVariable; break;
            case 1: insertarFinalFila(iCol); break;
            case 2: insertarFinal(iCol); break;
        }
        this.SubInsertarCompoLocal(nombre, iDespuesDeAsiento, pX, pZ, iCol, iDespuesDeAsientoReal);
    }
    
    private void SubInsertarCompoLocal(String nombre, int iDespuesDeAsiento, int pX, int pZ, int iCol, int iDespuesDeAsientoReal){
        int ubicacion, nc=0, i;
        
        for(i=0; i<this.vCompoLocal.size(); i++)
            if(this.vCompoLocal.get(i).getCol()==iCol) nc++;
        nc++;
        
        ubicacion=buscarCompoLocal(0);
        if(ubicacion!=0){
            if(iDespuesDeAsientoReal<=ubicacion) this.vCompoLocal.set(this.icIndice,this.vCompoLocal.get(this.icIndice)).setZ(this.icPZ+(this.iVariable/nc));
            else this.vCompoLocal.set(this.icIndice,this.vCompoLocal.get(this.icIndice)).setZ(this.icPZ-(this.iVariable/nc));
        }

        if(nc>2)
        for(i=0; i<this.vCompoLocal.size(); i++)
            if(this.vCompoLocal.get(i).getCol()==iCol)
                vCompoLocal.get(i).setZ(vCompoLocal.get(i).getZ()-(this.iVariable/nc));
        
        int iTam=vCompoLocal.size();
        Compo compo = new Compo();
        compo.setTipo(0);
        compo.setCol(iCol);
        compo.setNombre(nombre);
        compo.setUbicacion(iDespuesDeAsiento);
        compo.setXZ(pX,pZ);
        vCompoLocal.insertElementAt(compo,iTam);
    }
    
    public void insertarDentro(int iCol){
        int cols=this.getColIzquierda(), filasR=this.iFIReal, filas=this.iFI;
        Asiento[][] mAux=this.mAI;
        if(iCol==2){ mAux=this.mAD; cols=this.getColDerecha(); filasR=this.iFDReal; filas=this.iFD; }
        
        int iRetro=this.iVariable/filasR;
        int i,j, iFactorRetro;
        for(i=0; i<=this.iUbicarFila; i++){
            iFactorRetro=iRetro*(i+1);
            for(j=0; j<cols; j++) mAux[i][j].setZ(mAux[i][j].getZ()-iFactorRetro);
        }

        int h;
        for(i=filasR-2, h=1; i>this.iUbicarFila; i--, h++){
            iFactorRetro=iRetro*h;
            for(j=0; j<cols; j++) mAux[i][j].setZ(mAux[i][j].getZ()+iFactorRetro);
        }
    }
    
    public void insertarFinalFila(int iCol){
        int cols=this.getColIzquierda(), filasR=this.iFIReal, filas=this.iFI;
        Asiento[][] mAux=this.mAI;
        if(iCol==2){ mAux=this.mAD; cols=this.getColDerecha(); filasR=this.iFDReal; filas=this.iFD; }
        int iRetro=this.iVariable/filasR;
        int i,j, iFactorRetro;
        for(i=0; i<filasR; i++){
            iFactorRetro=iRetro*(i+1);
            for(j=0; j<cols; j++) mAux[i][j].setZ(mAux[i][j].getZ()-iFactorRetro);
        }
    }
    
    public void insertarFinal(int iCol){
        int cols=this.getColIzquierda(), filasR=this.iFIReal, filas=this.iFI;
        Asiento[][] mAux=this.mAI;
        if(iCol==2){ mAux=this.mAD; cols=this.getColDerecha(); filasR=this.iFDReal; filas=this.iFD; }
        int iRetro=this.iVariable/filas;
        int i,j, iFactorRetro;
        for(i=0; i<filasR; i++){
            iFactorRetro=iRetro*(i+1);
            for(j=0; j<cols; j++) mAux[i][j].setZ(mAux[i][j].getZ()-iFactorRetro);
        }
        iFactorRetro=iRetro*(i+1);
        for(i=0; i<mAR.length; i++) mAR[i].setZ(mAR[i].getZ()-iFactorRetro);
    }
    // ASIENTO EXTREMO
    private int AsientoExtremo(int iDespuesDeAsiento){
        if(this.getColIzquierda()!=1 || this.getColDerecha()!=1){
            switch(this.iColCompo){
                case 0: if(this.getColIzquierda()!=1){
                            int iVar=iDespuesDeAsiento%this.getAsientosFila();
                            if(iVar!=1){
                                iVar=(iDespuesDeAsiento-iVar)+1;
                                return iVar;
                            }
                        }
                        break;
                case 2: if(this.getColDerecha()!=1){
                            int iVar=iDespuesDeAsiento%this.getAsientosFila();
                            if(iVar!=0){
                                iVar=iDespuesDeAsiento+(this.getAsientosFila()-iVar);
                                return iVar;
                            }
                        }
                        break;
            }
        }
        return iDespuesDeAsiento;
    }
    
    // NUMERACION DE ASIENTOS
    public void numeracionDeAsientos(int iNo){
        if(this.getColIzquierda()==1 && this.getColDerecha()==1 && iNo!=1 && iNo!=3 && iNo!=4) return;
        if(iNo==0 && this.getColIzquierda()==1) return;
        if(iNo==2 && this.getColDerecha()==1) return;
        
        TipoNumeracion numeracion = new TipoNumeracion();
        numeracion.setDatos(this.mAI,this.mAD,this.mAR,this.iFIReal,this.iFDReal,
                            this.getColIzquierda(),this.getColDerecha(),
                            this.getAsientosRestantes(),this.iAlineacionZ,iNo);
        numeracion.AplicarNumeracion();
    }
    // CREACION DE PLANTILLA PARA GUARDAR EN BD
    public void crearVectorFinal(String Datos[][]){
        int i,j,h;
        vVectorUnico=new Vector<Componente>();
        Componente a;
        h=0;
        for(i=0; i<this.iFIReal; i++){
            for(j=0; j<this.getColIzquierda(); j++){
                a=new Componente();
                 a.setXZ(mAI[i][j].getX(),mAI[i][j].getZ());
                a.setUbicacion(mAI[i][j].getNumero());
                a.setNombre("ASIENTO");
                a.setTipo(-1);
                a.setId(4);
                vVectorUnico.insertElementAt(a,h);
                h++;
            }
        }

        for(i=0; i<this.iFDReal; i++){
            for(j=0; j<this.getColDerecha(); j++){
                a=new Componente();
                a.setXZ(mAD[i][j].getX(),mAD[i][j].getZ());
                a.setUbicacion(mAD[i][j].getNumero());
                a.setNombre("ASIENTO");
                a.setTipo(-1);
                a.setId(4);
                vVectorUnico.insertElementAt(a,h);
                h++;
            }
        }
        
        for(i=0; i<this.getAsientosRestantes(); i++){
            a=new Componente();
            a.setXZ(mAR[i].getX(),mAR[i].getZ());
            a.setUbicacion(mAR[i].getNumero());
            a.setNombre("ASIENTO");
            a.setTipo(-1);
            a.setId(4);
            vVectorUnico.insertElementAt(a,h);
            h++;
        }
        
        if(!vCompoLocal.isEmpty())
            for(i=0; i<vCompoLocal.size(); i++){
                a=new Componente();
                a.setXZ(vCompoLocal.get(i).getX(),vCompoLocal.get(i).getZ());
                a.setUbicacion(vCompoLocal.get(i).getUbicacion());
                a.setNombre(vCompoLocal.get(i).getNombre());
                a.setTipo(0);
                for(j=0;j<Datos.length;j++)
                    if(Datos[j][0].equals(a.getNombre())) a.setId(Integer.valueOf(Datos[j][3]));
                vVectorUnico.insertElementAt(a,h);
                h++;
            }
            
        if(!vCompoAereo.isEmpty())
            for(i=0; i<vCompoAereo.size(); i++){
               System.out.println("AEREO "+vCompoAereo.get(i).getNombre()+" - bus_id "+vCompoAereo.get(i).getComp_Bus_id()+" - tipo "+vCompoAereo.get(i).getTipo());

                a=new Componente();
                a.setXZ(vCompoAereo.get(i).getX(),vCompoAereo.get(i).getZ());
                a.setUbicacion(vCompoAereo.get(i).getUbicacion());
                a.setNombre(vCompoAereo.get(i).getNombre());
                a.setNombre("cOMPONENTE AEREO "+vCompoAereo.get(i).getNombre()+"-"+vCompoAereo.get(i).getTipo()+" - ID "+vCompoAereo.get(i).getComp_Bus_id());
                a.setTipo(1);
               // a.setId(2);  Cambio BRA por el componente nueva plantilla
                a.setId(vCompoAereo.get(i).getComp_Bus_id());
                vVectorUnico.insertElementAt(a,h);
                h++;  //
            }
    }
    
    public void insertarPlantilla(
        long alineacionUltfila, long alinearFilas, long anchoAsiento, float anchoAsientoF, float anchoBus,
        long anchoCanvas, long asientosColder, long asientosColizq, long capacidadAsientos,
        String descripcion, long distrAsientos,
        long largoAsiento, float largoAsientoF, float largoBus, long largoCanvas, String nombreCorto, long noFilasDer,
        long noFilasIzq, long noFilasRealDer, long noFilasRealIzq, long tipoNumeracion, long creadoPor
    ){
        Date dt = new Date();
        Timestamp dFecha = new Timestamp(dt.getTime());
        cssConsultas query = new cssConsultas();
        long plantillaEncId=query.TotalEnc();
        long plantillaLineas=query.TotalLineas();
        System.out.println("Id1 id2 "+plantillaEncId+" "+plantillaLineas);
        plantillaEncId++;
        plantillaLineas++;
        System.out.println("final Id1 id2 "+plantillaEncId+" "+plantillaLineas);
        List<TmsAutobusPlantLineasTbl> busPLineas=new ArrayList<TmsAutobusPlantLineasTbl>();
        TmsAutobusPlantLineasTbl busPLinea;
        
        for(int i=0; i<vVectorUnico.size(); i++){
            busPLinea=new TmsAutobusPlantLineasTbl();
            busPLinea.setPlantillaLineaId(i+plantillaLineas);
            busPLinea.setPlantillaEncId(plantillaEncId);
            System.out.println("guardando "+(long)vVectorUnico.get(i).getId());
            busPLinea.setComponenteBusId((long)vVectorUnico.get(i).getId());
            busPLinea.setCreadoPor(creadoPor);
            busPLinea.setFechaCreacion(dFecha);
            busPLinea.setNumeroAsiento((long)vVectorUnico.get(i).getUbicacion());
            busPLinea.setPosicionX((long)vVectorUnico.get(i).getX());
            busPLinea.setPosicionZ((long)vVectorUnico.get(i).getZ());
            busPLinea.setUltimaActualizacionPor(creadoPor);
            busPLinea.setUltimaFechaActualizacion(dFecha);            
            busPLineas.add(i,busPLinea);
        }
        query.InsertarPlantilla(
            alineacionUltfila, alinearFilas, anchoAsiento, anchoAsientoF, anchoBus,
            anchoCanvas, asientosColder, asientosColizq, capacidadAsientos,
            descripcion, distrAsientos,
            largoAsiento, largoAsientoF, largoBus, largoCanvas, nombreCorto, noFilasDer,
            noFilasIzq, noFilasRealDer, noFilasRealIzq, plantillaEncId,
            tipoNumeracion, busPLineas
        );
    }

    // NUMERACION PERSONAL
    public Object[][] EstablecerNumPersonal(){
        int i,j,h;
        Object[][] iDato=new Object[this.getPasajeros()][2];
        h=0;
        for(i=0; i<this.iFIReal; i++){
            for(j=0; j<this.getColIzquierda(); j++){
                iDato[h][0]=mAI[i][j].getNumero();
                h++;
            }
        }

        for(i=0; i<this.iFDReal; i++){
            for(j=0; j<this.getColDerecha(); j++){
                iDato[h][0]=mAD[i][j].getNumero();
                h++;
            }
        }
        
        for(i=0; i<this.getAsientosRestantes(); i++){
            iDato[h][0]=mAR[i].getNumero();
            h++;
        }
        
        for(i=0; i<this.getPasajeros(); i++) iDato[i][1]=iDato[i][0];
    
        return iDato;
    }
    
    // ACEPTAR NUMERACION PERSONAL
    public void AceptarNumPersonal(Object[] iDato){
        int i,j,h;
        h=0;
        for(i=0; i<this.iFIReal; i++){
            for(j=0; j<this.getColIzquierda(); j++){
                mAI[i][j].setNumero(Integer.valueOf(iDato[h].toString()));
                h++;
            }
        }

        for(i=0; i<this.iFDReal; i++){
            for(j=0; j<this.getColDerecha(); j++){
                mAD[i][j].setNumero(Integer.valueOf(iDato[h].toString()));
                h++;
            }
        }
        
        for(i=0; i<this.getAsientosRestantes(); i++){
            mAR[i].setNumero(Integer.valueOf(iDato[h].toString()));
            h++;
        }
    }
}