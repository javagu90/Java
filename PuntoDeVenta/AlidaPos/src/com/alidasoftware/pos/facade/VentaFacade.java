package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.List;

import com.alidasoftware.pos.dao.VentaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Venta;

public class VentaFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private VentaDao ventaDao = new VentaDao();
	
	public void createVenta(Venta venta) throws AlidaPosException {
		try {
			ventaDao.beginTransaction();
			ventaDao.save(venta);
			ventaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			ventaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			ventaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateVenta(Venta venta) throws AlidaPosException {
		try {
			ventaDao.beginTransaction();
			Venta persistedObject = ventaDao.find(venta.getIdventa());			
			persistedObject.setTienda(venta.getTienda());
			persistedObject.setCliente(venta.getCliente());
			persistedObject.setComentarios(venta.getComentarios());
			persistedObject.setDetalleventas(venta.getDetalleventas());
			persistedObject.setFecha(venta.getFecha());
			persistedObject.setFolio(venta.getFolio());
			persistedObject.setIva(venta.getIva());
			persistedObject.setPagoventas(venta.getPagoventas());
			//persistedObject.setAdeudoventas(venta.getAdeudoventas());
			persistedObject.setStatus(venta.getStatus());
			persistedObject.setSubtotal(venta.getSubtotal());
			persistedObject.setTotal(venta.getTotal());
			persistedObject.setUsuario(venta.getUsuario());		
			persistedObject.setFacturado(venta.isFacturado());
			persistedObject.setTasaIva(venta.getTasaIva());
			ventaDao.update(persistedObject);
			ventaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			ventaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			ventaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Venta findVenta(int idVenta) throws AlidaPosException {
    	try {
    		ventaDao.beginTransaction();
    		Venta venta = ventaDao.find(idVenta);
    		ventaDao.closeTransaction();
    		return venta;
    	} catch (Exception ex) {
    		try {
    			ventaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			ventaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Venta> listByStatus(int status) throws AlidaPosException {
    	try {
    		ventaDao.beginTransaction();
    		List<Venta> result = ventaDao.findByStatus(status);
    		ventaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			ventaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			ventaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Venta> listAll() throws AlidaPosException {
    	try {
    		ventaDao.beginTransaction();
    		List<Venta> result = ventaDao.findAll();
    		ventaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			ventaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			ventaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteVenta(Venta venta) throws AlidaPosException {
    	try {
	    	ventaDao.beginTransaction();
	    	Venta persistedObject = ventaDao.findReferenceOnly(venta.getIdventa());
	        ventaDao.delete(persistedObject);
	        ventaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				ventaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (ventaDao != null) {
				ventaDao.closeTransaction();
			}
		}
    }
    
    public Venta findVentaByFolio(String folio) throws AlidaPosException {
    	try {
    		ventaDao.beginTransaction();
    		Venta venta = ventaDao.findByFolio(folio);
    		ventaDao.closeTransaction();
    		return venta;
    	} catch (Exception ex) {
    		try {
    			ventaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			ventaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }

}
