package com.javagu.pos.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.javagu.pos.model.Persona;

public class PersonaDao 
{	
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public PersonaDao()
	{
		emf=Persistence.createEntityManagerFactory("EEPrueba");
		em=emf.createEntityManager();
	}

	public Persona insert(Persona persona) 
	{
		System.out.println("Ya llegue");
		try
		{
			em.getTransaction().begin();
			em.merge(persona);
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			em.getTransaction().rollback();
			System.out.println("No pude "+ e.getMessage().toString());
		}
		finally
		{
			emf.close();
		}
		return persona;
	}

	
	public void insert(String nombre, String sexo) 
	{
		System.out.println("Ya llegue");
		try
		{
			Persona persona= new Persona();
			persona.setNombre(nombre);
			persona.setSexo(sexo);
			em.getTransaction().begin();
			em.merge(persona);
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			em.getTransaction().rollback();
			System.out.println("No pude "+ e.getMessage().toString());
		}
		finally
		{
			emf.close();
		}
	}
	
	public Object update(Object entity) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void delete(Object entity) {
		// TODO Auto-generated method stub
		
	}

	
	public Object findById(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@SuppressWarnings("unchecked")
	public List<Persona> findAll() {
		List<Persona> personas=null;
		try
		{
			em.getTransaction();
			em.getTransaction().begin();
			Query consulta= em.createQuery("select persona from Persona persona");
			personas=consulta.getResultList();
			em.getTransaction().commit();
		}
		catch(Exception e)
		{
			em.getTransaction().rollback();
			System.out.println("No pude "+ e.getMessage().toString());
		}
		finally
		{
			emf.close();
		}
		return personas;
	}

	
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	
	public Object findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

}
