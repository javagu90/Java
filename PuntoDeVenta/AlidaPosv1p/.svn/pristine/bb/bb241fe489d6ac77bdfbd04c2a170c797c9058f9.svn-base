package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.PrecioVentaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Precioventa;

public class PrecioVentaFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private PrecioVentaDao precioVentaDao = new PrecioVentaDao();
	
	public void createPrecioventa(Precioventa precioVenta) throws AlidaPosException {
		try {
			precioVentaDao.beginTransaction();
			precioVentaDao.save(precioVenta);
			precioVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			precioVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			precioVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updatePrecioventa(Precioventa precioVenta) throws AlidaPosException {
		try {
			precioVentaDao.beginTransaction();
			Precioventa persistedObject = precioVentaDao.find(precioVenta.getIdprecioventa());
			persistedObject.setComentarios(precioVenta.getComentarios());
			persistedObject.setModoprecio(precioVenta.getModoprecio());
			persistedObject.setProducto(precioVenta.getProducto());
			persistedObject.setTipoprecio(precioVenta.getTipoprecio());
			persistedObject.setValor(precioVenta.getValor());
			precioVentaDao.update(persistedObject);
			precioVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			precioVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			precioVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Precioventa findPrecioventa(int idPrecioventa) throws AlidaPosException {
    	try {
    		precioVentaDao.beginTransaction();
    		Precioventa precioVenta = precioVentaDao.find(idPrecioventa);
    		precioVentaDao.closeTransaction();
    		return precioVenta;
    	} catch (Exception ex) {
    		try {
    			precioVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			precioVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Precioventa findPrecioVentaByIdProduct(int idProduct) throws AlidaPosException {
    	try {
    		precioVentaDao.beginTransaction();
    		Precioventa precioVenta = precioVentaDao.findByIdProduct(idProduct);
    		precioVentaDao.closeTransaction();
    		return precioVenta;
    	}  catch (Exception ex) {
    		try {
    			precioVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			precioVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Precioventa findPrecioVentaByIdProductQuantity(int idProduct, float quantity) throws AlidaPosException {
    	try {
    		precioVentaDao.beginTransaction();
    		List<Precioventa> precioVentaList = precioVentaDao.findByIdProductQuantity(idProduct, quantity);
    		precioVentaDao.closeTransaction();
    		if (precioVentaList != null && precioVentaList.size() > 0) {
    			for (int i = 0; i < precioVentaList.size(); i++) {
					System.out.println("precio lista : " + 
										precioVentaList.get(i).getCantidadproductos() + " - " +
										precioVentaList.get(i).getValor());
				}
    			return precioVentaList.get(0);
    		}
    		return null;
    	}  catch (Exception ex) {
    		try {
    			precioVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			precioVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Precioventa> listAll() throws AlidaPosException {
    	try {
    		precioVentaDao.beginTransaction();
    		List<Precioventa> result = precioVentaDao.findAll();
    		precioVentaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			precioVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			precioVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deletePrecioventa(Precioventa precioVenta) throws AlidaPosException {
    	try {
	    	precioVentaDao.beginTransaction();
	    	Precioventa persistedObject = precioVentaDao.findReferenceOnly(precioVenta.getIdprecioventa());
	        precioVentaDao.delete(persistedObject);
	        precioVentaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				precioVentaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (precioVentaDao != null) {
				precioVentaDao.closeTransaction();
			}
		}
    }
    

}
