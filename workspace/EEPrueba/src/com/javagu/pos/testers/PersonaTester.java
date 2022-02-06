package com.javagu.pos.testers;

import java.util.List;

import com.javagu.pos.facade.PersonaFacade;
import com.javagu.pos.model.Persona;

public class PersonaTester {

	public static void main(String[] args) 
	{
		System.out.println("Empecemos");
		
		PersonaFacade personaFacade= new PersonaFacade();
		
		/*personaFacade.setNombre("Javagu90");
		personaFacade.setSexo("M");
		personaFacade.insert(personaFacade.getPersona());
		
		personaFacade= new PersonaFacade();
		personaFacade.setNombre("Dalia");
		personaFacade.setSexo("F");
		personaFacade.insert(personaFacade.getPersona());
		
		
		personaFacade= new PersonaFacade();
		personaFacade.insert("Lia", "F");*/
		
		
		
		List<Persona> personas=personaFacade.findAll();
			for(Persona persona: personas)
			{
				int codigo=persona.getCodigo();
				String nombre=persona.getNombre();
				String sexo= persona.getSexo();
				System.out.println(codigo+" "+nombre+" "+sexo);
			}
		
		System.out.println("Fin");
	}

}
