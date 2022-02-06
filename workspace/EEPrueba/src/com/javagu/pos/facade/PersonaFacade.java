package com.javagu.pos.facade;

import java.util.List;


import com.javagu.pos.dao.PersonaDao;
import com.javagu.pos.model.Persona;

public class PersonaFacade 
{
	private PersonaDao personaDao=new PersonaDao();
	private Persona persona;
	private int codigo;
	private String nombre;
	private String sexo;
	
	
	
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
	
	public PersonaDao getPersonaDao() 
	{
		return personaDao;
	}
	
	public void setPersonaDao(PersonaDao personaDao) 
	{
		
			this.personaDao = personaDao;
		
		
	}
	public Persona getPersona() {
		if(persona==null)
			persona=new Persona();
		return persona;
	}
	
	public void setPersona(Persona persona) 
	{
			this.persona = persona;	
	}
	
	public Persona insert(Persona persona) 
	{
		System.out.println(getNombre());
		System.out.println(getSexo());
		persona.setNombre(getNombre());
		persona.setSexo(getSexo());
		personaDao.insert(persona);
		return persona;
	}
	
	public void insert(String nombre, String sexo) 
	{
		System.out.println(nombre);
		System.out.println(sexo);
		personaDao.insert(nombre, sexo);
	}
	
	
	public List<Persona> findAll() {
		List<Persona> personas=personaDao.findAll();
		System.out.println("ID Nombre Sexo");
		for(Persona person: personas)
		{
			//System.out.println(person.getCodigo());
			person.getCodigo();
			person.getNombre();
			person.getSexo();
		}
		
		return personas;
	}

}
