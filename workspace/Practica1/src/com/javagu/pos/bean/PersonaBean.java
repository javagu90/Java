package com.javagu.pos.bean;

import java.util.List;

import com.javagu.pos.facade.PersonaFacade;
import com.javagu.pos.model.Persona;

public class PersonaBean 
{
	private int codigo;
	private String nombre;
	private String sexo;
	private PersonaFacade personaFacade;
	private List<Persona> personas;
	
	public List<Persona> getPersonas() {
		return personas;
	}
	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
	public PersonaFacade getPersonaFacade() {
		return personaFacade;
	}
	public void setPersonaFacade(PersonaFacade personaFacade) {
		this.personaFacade = personaFacade;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	/*public List<Persona> mostrarTodos()
	{
		try
		{
			personaFacade= new PersonaFacade ();
			personas=personaFacade.findAll();
			for(Persona persona: personas)
			{
				codigo=persona.getCodigo();
				nombre=persona.getNombre();
				sexo= persona.getSexo();
				//System.out.println(codigo+" "+nombre+" "+sexo);
			}
		}
		catch(Exception e)
		{
			System.out.println("No pude"+ e.getMessage().toString());
		}
		return personas;
	}*/
	
	
}
