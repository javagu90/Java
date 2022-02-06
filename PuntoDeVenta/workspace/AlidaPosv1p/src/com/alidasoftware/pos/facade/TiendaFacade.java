package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.TiendaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Tienda;

public class TiendaFacade implements Serializable {

	private static final long serialVersionUID = 1798296057023509293L;
	
	private TiendaDao tiendaDao = new TiendaDao();
	
	public void createTienda(Tienda tienda) throws AlidaPosException {
		try {
			tiendaDao.beginTransaction();
			tiendaDao.save(tienda);
			tiendaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			tiendaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tiendaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateTienda(Tienda tienda) throws AlidaPosException {
		try {
			tiendaDao.beginTransaction();
			Tienda persistedObject = tiendaDao.find(tienda.getIdtienda());
			persistedObject.setComentarios(tienda.getComentarios());
			persistedObject.setDescripcion(tienda.getDescripcion());
			persistedObject.setNombre(tienda.getNombre());
			persistedObject.setTraspasos1(tienda.getTraspasos1());
			persistedObject.setTraspasos2(tienda.getTraspasos2());	
			persistedObject.setContacto(tienda.getContacto());
			persistedObject.setResponsable(tienda.getResponsable());
			persistedObject.setActivo(tienda.isActivo());
			tiendaDao.update(persistedObject);
			tiendaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			tiendaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tiendaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Tienda findTienda(int idTienda) throws AlidaPosException {
    	try {
    		tiendaDao.beginTransaction();
    		Tienda tienda = tiendaDao.findbyId(idTienda);
    		System.out.println("la tienda es : " + tienda.getNombre());
    		tiendaDao.closeTransaction();
    		return tienda;
    	} catch (Exception ex) {
    		try {
    			System.out.println("error al encontrar la tienda " + ex.getMessage());
    			tiendaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tiendaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Tienda> listAll() throws AlidaPosException {
    	try {
    		tiendaDao.beginTransaction();
    		List<Tienda> result = tiendaDao.findAll();
    		tiendaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			tiendaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tiendaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Tienda> listAllActive() throws AlidaPosException {
    	try {
    		tiendaDao.beginTransaction();
    		List<Tienda> result = tiendaDao.findAllActive();
    		tiendaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			tiendaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tiendaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteTienda(Tienda tienda) throws AlidaPosException {
    	try {
	    	tiendaDao.beginTransaction();
	    	Tienda persistedObject = tiendaDao.findReferenceOnly(tienda.getIdtienda());
	        tiendaDao.delete(persistedObject);
	        tiendaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				tiendaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (tiendaDao != null) {
				tiendaDao.closeTransaction();
			}
		}
    }
    
    public List<Tienda> findByNombre(String searchNombre) throws AlidaPosException {
    	try {
    		tiendaDao.beginTransaction();
    		List<Tienda> result = tiendaDao.findByNombre(searchNombre);
    		tiendaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			tiendaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tiendaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Tienda> findTiendasDestino(String nombre) throws AlidaPosException{
    	try {
    		tiendaDao.beginTransaction();
    		List<Tienda> result = tiendaDao.findTiendasDestino(nombre);
    		tiendaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		System.out.println("Error al ejecutar findTiendasDestino " + ex.getMessage());
    		try {
    			tiendaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tiendaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

	public Tienda findTiendaByNombre(String nombreTienda) throws AlidaPosException {
		try {
    		tiendaDao.beginTransaction();
    		Tienda result = tiendaDao.findTiendaByNombre(nombreTienda);
    		tiendaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			tiendaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			tiendaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

}
