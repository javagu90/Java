package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.alidasoftware.pos.dao.ClienteDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Cliente;

public class ClienteFacade implements Serializable {

	private static final long serialVersionUID = 5005627031064512078L;
	
	private ClienteDao clienteDao = new ClienteDao();
	
	public void createCliente(Cliente cliente) throws AlidaPosException {
		try {
			clienteDao.beginTransaction();
			clienteDao.save(cliente);
			clienteDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			clienteDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			clienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateCliente(Cliente cliente) throws AlidaPosException {
		try {
			clienteDao.beginTransaction();
			Cliente persistedObject = clienteDao.find(cliente.getIdcliente());
			persistedObject.setApartados(cliente.getApartados());
			persistedObject.setCategoriacliente(cliente.getCategoriacliente());
			persistedObject.setClavecliente(cliente.getClavecliente());
			persistedObject.setComentarios(cliente.getComentarios());
			persistedObject.setCredito(cliente.getCredito());
			persistedObject.setLimitecredito(cliente.getLimitecredito());
			persistedObject.setCreditodisponible(cliente.getCreditodisponible());
			persistedObject.setPersona(cliente.getPersona());
			persistedObject.setUltimaoperacion(cliente.getUltimaoperacion());
			persistedObject.setVales(cliente.getVales());
			persistedObject.setVentas(cliente.getVentas());
			persistedObject.setVistas(cliente.getVistas());
			persistedObject.setActivo(cliente.isActivo());
			clienteDao.update(persistedObject);
			clienteDao.commitAndCloseTransaction();
		} catch (Exception ex) {
			System.out.println("Error al actualizar el cliente: " + ex.getMessage());
    		try {
    			clienteDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			clienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Cliente findCliente(int idCliente) throws AlidaPosException {
    	try {
    		clienteDao.beginTransaction();
    		Cliente cliente = clienteDao.find(idCliente);
    		clienteDao.closeTransaction();
    		return cliente;
    	} catch (Exception ex) {
    		try {
    			clienteDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			clienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Cliente findCliente(String nombreCliente) throws AlidaPosException {
		List<Cliente> allCliente = listAll();
        Iterator<Cliente> iteratorClientes = allCliente.iterator();
        Cliente  currentCliente;
        while(iteratorClientes.hasNext()){
        	currentCliente = iteratorClientes.next();
        	if (nombreCliente.toLowerCase().equals(currentCliente.getClavecliente().toLowerCase()) ) {
        		return currentCliente;
			}
 		}  
        return null;
     }

    public List<Cliente> FindLikeClave(String clave) throws AlidaPosException {
    	try {
    		clienteDao.beginTransaction();
    		List<Cliente> result = clienteDao.findLikeClave(clave);    		
    		clienteDao.closeTransaction();
        	return result;
    	} catch (Exception ex) {
    		try {
    			clienteDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			clienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Cliente> listAll() throws AlidaPosException {
    	try {
    		clienteDao.beginTransaction();
    		List<Cliente> result = clienteDao.findAll();
    		clienteDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			clienteDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			clienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteCliente(Cliente cliente) throws AlidaPosException {
    	try {
	    	clienteDao.beginTransaction();
	    	Cliente persistedObject = clienteDao.findReferenceOnly(cliente.getIdcliente());
	        clienteDao.delete(persistedObject);
	        clienteDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				clienteDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (clienteDao != null) {
				clienteDao.closeTransaction();
			}
		}
    }
    
    public Cliente findClienteByClave(String claveCliente) throws AlidaPosException {
    	try {
    		clienteDao.beginTransaction();
    		Cliente result = clienteDao.findClienteByClave(claveCliente);
    		clienteDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		System.out.println("Error al obtener un cliente por clave " + ex.getMessage());
    		try {
    			clienteDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			clienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
     }
    
    public List<Cliente> findByNombreClaveCategoria(String searchNombre,
			String searchClave, String searchCategoria) throws AlidaPosException {
		try {
    		clienteDao.beginTransaction();
    		List<Cliente> result = clienteDao.findByNombreClaveCategoria(searchNombre, searchClave, searchCategoria);
    		clienteDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			clienteDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			clienteDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

}
