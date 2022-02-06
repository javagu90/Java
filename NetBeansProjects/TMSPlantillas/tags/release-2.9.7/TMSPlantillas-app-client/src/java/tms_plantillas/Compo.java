package tms_plantillas;

public class Compo {
    private String nombre;
    private int X=0;
    private int Z=0;
    private int Ubicacion;
    private int Tipo;
    private int iCol;
    
    public Compo() {
    }
    
    public void setXZ(int pX, int pZ){
        this.X=pX; this.Z=pZ;
    }

    public void setNombre(String pNombre){ this.nombre=pNombre; }
    
    public void setUbicacion(int pNo){ this.Ubicacion=pNo; }

    public void setTipo(int pNo){ this.Tipo=pNo; }
    
    public void setCol(int piCol){ this.iCol=piCol; }
    
    public int getX(){ return this.X; }
    
    public int getZ(){ return this.Z; }
    
    public int getUbicacion(){ return this.Ubicacion; }
    
    public int getTipo(){ return this.Tipo; }
    
    public int getCol(){ return this.iCol; }
    
    public String getNombre(){ return this.nombre; }
    
    public void setX(int pX){ this.X=pX; }
    
    public void setZ(int pZ){ this.Z=pZ; }
}
