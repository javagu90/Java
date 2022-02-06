package tms_plantillas;

public class TipoNumeracion {
    private Asiento[][] mAI;
    private Asiento[][] mAD;
    private Asiento[] mAR;
    private int iFIReal;
    private int iFDReal;
    private int iColIzquierda;
    private int iColDerecha;
    private int iAsientosRestantes;
    private int iAlineacion;
    private int iColInv;
    
    public TipoNumeracion() {
    }
    
    public void setDatos(Asiento[][] pmAI, Asiento[][] pmAD, Asiento[] pmAR,
                        int piFIReal, int piFDReal,
                        int piColIzquierda, int piColDerecha, int piAsientosRestantes,
                        int piAlineacion, int piColInv){
        this.mAI=pmAI;
        this.mAD=pmAD;
        this.mAR=pmAR;
        this.iFIReal=piFIReal;
        this.iFDReal=piFDReal;
        this.iColIzquierda=piColIzquierda;
        this.iColDerecha=piColDerecha;
        this.iAsientosRestantes=piAsientosRestantes;
        this.iAlineacion=piAlineacion;
        this.iColInv=piColInv;
    }
    
    public void AplicarNumeracion(){
        switch(this.iColInv){
            case 0: this.invertirNumeracion(this.mAI,this.mAR,this.iColIzquierda); break;
            case 1: this.numeracion1234(); break;
            case 2: this.invertirNumeracion(this.mAD,this.mAR,this.iColDerecha); break;
            case 3: this.numeracion3412(); break;
            case 4: this.numeracionGenerica(); break;
        }
    }

    private void numeracion1234(){ //1,2 --- 3,4
        int i,j, numero=1, iFilasM=this.iFIReal, iFilasm=this.iFDReal, ixCol=0, iLimCol=this.iColIzquierda;
        Asiento[][] mAux=this.mAI;
        
        if(this.iFIReal!=this.iFDReal)
            if(this.iFIReal<this.iFDReal){ iFilasM=this.iFDReal;
                    iFilasm=this.iFIReal; ixCol=2; mAux=this.mAD; iLimCol=this.iColDerecha; }
        
        for(i=0; i<iFilasm; i++){
            for(j=0; j<this.iColIzquierda; j++){
                mAI[i][j].setNumero(numero);
                numero++;
            }
            for(j=0; j<this.iColDerecha; j++){
                mAD[i][j].setNumero(numero);
                numero++;
            }
        }
        
        if(this.iFIReal!=this.iFDReal){
            int h=i;
            for(i=h; i<iFilasM; i++)
                for(j=0; j<iLimCol; j++){
                    mAux[i][j].setNumero(numero);
                    numero++;
                }
        }

        for(i=0; i<this.iAsientosRestantes; i++){
            mAR[i].setNumero(numero);
            numero++;
        }        
    }
    
    private void numeracion3412(){ //3,4 --- 1,2
        int i,j, numero=1, iFilasM=this.iFIReal, iFilasm=this.iFDReal, ixCol=0, iLimCol=this.iColIzquierda;
        Asiento[][] mAux=this.mAI;
        
        if(this.iFIReal!=this.iFDReal)
            if(this.iFIReal<this.iFDReal){ iFilasM=this.iFDReal;
                    iFilasm=this.iFIReal; ixCol=2; mAux=this.mAD; iLimCol=this.iColDerecha; }
        
        for(i=0; i<iFilasm; i++){
            for(j=0; j<this.iColDerecha; j++){
                mAD[i][j].setNumero(numero);
                numero++;
            }
            for(j=0; j<this.iColIzquierda; j++){
                mAI[i][j].setNumero(numero);
                numero++;
            }
        }
        
        if(this.iFIReal!=this.iFDReal){
            int h=i;
            for(i=h; i<iFilasM; i++)
                for(j=0; j<iLimCol; j++){
                    mAux[i][j].setNumero(numero);
                    numero++;
                }
        }

        for(i=0; i<this.iAsientosRestantes; i++){
            mAR[i].setNumero(numero);
            numero++;
        }        
    }

    private void numeracionGenerica(){
        int i,j, numero=1;
        for(i=0; i<this.iFIReal; i++)
            for(j=0; j<this.iColIzquierda; j++){
                mAI[i][j].setNumero(numero);
                numero++;
            }

        for(i=0; i<this.iFDReal; i++)
            for(j=0; j<this.iColDerecha; j++){
                mAD[i][j].setNumero(numero);
                numero++;
            }

        for(i=0; i<this.iAsientosRestantes; i++){
            mAR[i].setNumero(numero);
            numero++;
        }
    }

    private void invertirNumeracion(Asiento[][] pMatriz1, Asiento[] pMatriz2, int inCol){
        int iVar, i, j;
        if(inCol==2){
            for(i=0; i<pMatriz1.length; i++){
                iVar=pMatriz1[i][0].getNumero();
                pMatriz1[i][0].setNumero(pMatriz1[i][1].getNumero());
                pMatriz1[i][1].setNumero(iVar);
            }
        }
        else{
            if(inCol==3){
                for(i=0; i<pMatriz1.length; i++){
                    iVar=pMatriz1[i][0].getNumero();
                    pMatriz1[i][0].setNumero(pMatriz1[i][2].getNumero());
                    pMatriz1[i][2].setNumero(iVar);
                }
            }
        }
        
        if(this.iAsientosRestantes!=1 && this.iAsientosRestantes!=0){
            if(this.iAlineacion==this.iColInv){
                if(inCol==2){
                    iVar=pMatriz2[0].getNumero();
                    pMatriz2[0].setNumero(pMatriz2[1].getNumero());
                    pMatriz2[1].setNumero(iVar);
                }
                else{
                    if(inCol==3){
                        iVar=pMatriz2[0].getNumero();
                        pMatriz2[0].setNumero(pMatriz2[2].getNumero());
                        pMatriz2[2].setNumero(iVar);
                    }
                }
            }
        }
    }
}
