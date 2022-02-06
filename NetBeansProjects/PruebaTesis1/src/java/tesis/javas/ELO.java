/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tesis.javas;

/**
 *
 * @author Javy
 */
public class ELO {
    public final int aprendiz;
    public final int principiante;
    public final int intermedio;
    public final int avanzado;
    public final int experto;
    public final int master;
    public int resultado;
    public ELO(){
        aprendiz=0;
        principiante=20;
        intermedio=40;
        avanzado=60;
        experto=80;
        master=100;
    }
    
    public int calcularResultado()
    {
        
        return resultado;
    }
    public String calcularNivel(int resultado)
    {
        if(resultado<principiante)
        {
            System.out.println("Aprendiz");
            return "Aprendiz";
        }
        else
        {
            if(resultado<intermedio)
            {
                System.out.println("principiante");
                return "principiante";
            }
            else
            {
                if(resultado<avanzado)
                {
                    System.out.println("intermedio");
                    return "Intermedio";
                }
                else
                {
                    if(resultado<experto)
                    {
                        System.out.println("avanzado");
                        return "avanzado";
                    }
                    else
                    {
                        if(resultado<master)
                        {
                            System.out.println("experto");
                            return "experto";
                        }
                        else
                        {
                            System.out.println("Master");
                            return "master";
                        }
                    }
                }
            }
        }
    }
    
}