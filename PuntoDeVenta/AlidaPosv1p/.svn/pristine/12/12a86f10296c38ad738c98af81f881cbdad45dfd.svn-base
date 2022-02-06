package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.PromocionDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Empleado;
import com.alidasoftware.pos.model.Promocion;

public class PromocionFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private PromocionDao promocionDao = new PromocionDao();
	
	public void createPromocion(Promocion promocion) throws AlidaPosException {
		try {
			promocionDao.beginTransaction();
			promocionDao.save(promocion);
			promocionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			promocionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			promocionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updatePromocion(Promocion promocion) throws AlidaPosException {
		try {
			promocionDao.beginTransaction();
			Promocion persistedObject = promocionDao.find(promocion.getIdpromocion());
			persistedObject.setNombre(promocion.getNombre());
			persistedObject.setDescuento(promocion.getDescuento());
			persistedObject.setIdproducto(promocion.getIdproducto());
			persistedObject.setFechainicio(promocion.getFechainicio());
			persistedObject.setFechafin(promocion.getFechafin());
			persistedObject.setComentarios(promocion.getComentarios());
			persistedObject.setActiva(promocion.getActiva());
			persistedObject.setOtrasPromociones(promocion.isOtrasPromociones());
			persistedObject.setCategoriaproductos(promocion.getCategoriaproductos());
			
			promocionDao.update(persistedObject);
			promocionDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			promocionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			promocionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Promocion findPromocion(int idPromocion) throws AlidaPosException {
    	try {
    		promocionDao.beginTransaction();
    		Promocion promocion = promocionDao.find(idPromocion);
    		promocionDao.closeTransaction();
    		return promocion;
    	} catch (Exception ex) {
    		try {
    			promocionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			promocionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Promocion findPromocionByProduct(int idProduct) throws AlidaPosException {
    	try {
    		promocionDao.beginTransaction();
    		Promocion promocion = promocionDao.findByIdProduct(idProduct);
    		promocionDao.closeTransaction();
    		return promocion;
    	} catch (Exception ex) {
    		try {
    			promocionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			promocionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Promocion> listAll() throws AlidaPosException {
    	try {
    		promocionDao.beginTransaction();
    		List<Promocion> result = promocionDao.findAll();
    		promocionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			promocionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			promocionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deletePromocion(Promocion promocion) throws AlidaPosException {
    	try {
	    	promocionDao.beginTransaction();
	    	Promocion persistedObject = promocionDao.findReferenceOnly(promocion.getIdpromocion());
	        promocionDao.delete(persistedObject);
	        promocionDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				promocionDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (promocionDao != null) {
				promocionDao.closeTransaction();
			}
		}
    }
    
    public Promocion findPromocionByNombre(String nombre) throws AlidaPosException {
    	try {
    		promocionDao.beginTransaction();
    		Promocion result = promocionDao.findPromocionByNombre(nombre);
    		promocionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			promocionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			promocionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
     }

	public List<Promocion> findByNombreDescuentoEstatus(String searchNombre,
			String searchClave, String searchEstatus) throws AlidaPosException {
		try {
			promocionDao.beginTransaction();
    		List<Promocion> result = promocionDao.findByNombreDescuentoEstatus(searchNombre, searchClave, searchEstatus);
    		promocionDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		System.out.println("Error al filtrar : " + ex.getMessage());
    		try {
    			promocionDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			promocionDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
    

}
