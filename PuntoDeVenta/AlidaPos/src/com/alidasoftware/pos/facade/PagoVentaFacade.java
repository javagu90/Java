package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alidasoftware.pos.dao.PagoVentaDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.helper.PagoVentaHelper;
import com.alidasoftware.pos.model.Pagoventa;

public class PagoVentaFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private PagoVentaDao pagoVentaDao = new PagoVentaDao();
	
	public void createPago(Pagoventa pago) throws AlidaPosException {
		try {
			pagoVentaDao.beginTransaction();
			pagoVentaDao.save(pago);
			pagoVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updatePago(Pagoventa pago) throws AlidaPosException {
		try {
			pagoVentaDao.beginTransaction();
			Pagoventa persistedObject = pagoVentaDao.find(pago.getIdpago());
			persistedObject.setCantidad(pago.getCantidad());
			persistedObject.setComentarios(pago.getComentarios());
			persistedObject.setFecha(pago.getFecha());
			persistedObject.setFormapago(pago.getFormapago());
			persistedObject.setReferenciapago(pago.getReferenciapago());
			persistedObject.setCambio(pago.getCambio());
			persistedObject.setVenta(pago.getVenta());
			persistedObject.setCaja(pago.getCaja());
			pagoVentaDao.update(persistedObject);
			pagoVentaDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Pagoventa findPago(int idPagoVenta) throws AlidaPosException {
    	try {
    		pagoVentaDao.beginTransaction();
    		Pagoventa pagoVenta = pagoVentaDao.find(idPagoVenta);
    		pagoVentaDao.closeTransaction();
    		return pagoVenta;
    	} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Pagoventa> listByIdVenta(int idVenta) throws AlidaPosException {
    	try {
    		pagoVentaDao.beginTransaction();
    		List<Pagoventa> result = pagoVentaDao.findByIdVenta(idVenta);
    		pagoVentaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<PagoVentaHelper> listBySumFormaPago(int ultimobtenido, int ultimorealizado, Date fecha, int idCaja) throws AlidaPosException {
        try {
         System.out.println("EMPEZANDO LA TRANSACCION DENTRO DEL PAGO VENTA FACADE");
         pagoVentaDao.beginTransaction();
         System.out.println("TERMINADNO LA TRANSACCION DENTRO DEL PAGO VENTA FACADE");
         System.out.println("CREANDO RESULTADO DENTRO DEL PAGO VENTA FACADE");
         List<PagoVentaHelper> result = pagoVentaDao.listBySumFormaPago(ultimobtenido, ultimorealizado, fecha, idCaja);
         System.out.println("RESULTADO COMPLETADO DENTRO DEL PAGO VENTA FACADE");
         System.out.println("CERRANDO LA TRANSACCION DENTRO DEL PAGO VENTA FACADE");
         pagoVentaDao.closeTransaction();
         System.out.println("TRANSACCION CERRADA DENTRO DEL PAGO VENTA FACADE");
         System.out.println("REGRESANDO RESULTADO DENTRO DEL PAGO VENTA FACADE");
         return result;
        } catch (Exception ex) {
        	System.out.println("PRIMER CATCH DENTRO DEL PAGO VENTA FACADE");
         try {
        	 System.out.println("HACIENDO ROLLBACK DENTRO DEL PAGO VENTA FACADE");
          pagoVentaDao.rollback();
          System.out.println("TERMINE ROLLBACK DENTRO DEL PAGO VENTA FACADE");
         } catch (Exception ex1) {
        	 System.out.println("CATCH 2 DENTRO DEL PAGO VENTA FACADE");
          throw new AlidaPosException(ex1.getMessage());
         }
         throw new AlidaPosException(ex.getMessage());
        } finally {
         try {
        	 System.out.println("CERRANDO FINALLY LA TRANSACCION DENTRO DEL PAGO VENTA FACADE");
          pagoVentaDao.closeTransaction();
          System.out.println("CERRADA FINALLY TRANSACCION DENTRO DEL PAGO VENTA FACADE");
         } catch (Exception ex) {
          throw new AlidaPosException(ex.getMessage());
         }
        }
       }

    public List<PagoVentaHelper> listByFirstCP(int ultimorealizado, Date fecha, int idCaja) throws AlidaPosException {
        try {
         pagoVentaDao.beginTransaction();
         List<PagoVentaHelper> result = pagoVentaDao.listByFirstCP(ultimorealizado, fecha, idCaja);
         pagoVentaDao.closeTransaction();
         return result;
        } catch (Exception ex) {
         try {
          pagoVentaDao.rollback();
         } catch (Exception ex1) {
          throw new AlidaPosException(ex1.getMessage());
         }
         throw new AlidaPosException(ex.getMessage());
        } finally {
         try {
          pagoVentaDao.closeTransaction();
         } catch (Exception ex) {
          throw new AlidaPosException(ex.getMessage());
         }
        }
       }
 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List getMaxIdUltimoPagoVenta( int ultimo, Date fecha, int idCaja) throws AlidaPosException {
        try {
         pagoVentaDao.beginTransaction();
         List<PagoVentaHelper> result = pagoVentaDao.getMaxIdUltimoPagoVenta(ultimo, fecha, idCaja);
         pagoVentaDao.closeTransaction();
         return result;
        } catch (Exception ex) {
         try {
          pagoVentaDao.rollback();
         } catch (Exception ex1) {
          throw new AlidaPosException(ex1.getMessage());
         }
         throw new AlidaPosException(ex.getMessage());
        } finally {
         try {
          pagoVentaDao.closeTransaction();
         } catch (Exception ex) {
          throw new AlidaPosException(ex.getMessage());
         }
        }
       }

    
    @SuppressWarnings({ "rawtypes" })
	public List getMaxIdUltimoPagoVentaOfFirstCP(Date fecha, int idCaja) throws AlidaPosException
	{
        try {
         pagoVentaDao.beginTransaction();
         List result = pagoVentaDao.getMaxIdUltimoPagoVentaOfFirstCP(fecha, idCaja);
         pagoVentaDao.closeTransaction();
         return result;
        } catch (Exception ex) {
         try {
          pagoVentaDao.rollback();
         } catch (Exception ex1) {
          throw new AlidaPosException(ex1.getMessage());
         }
         throw new AlidaPosException(ex.getMessage());
        } finally {
         try {
          pagoVentaDao.closeTransaction();
         } catch (Exception ex) {
          throw new AlidaPosException(ex.getMessage());
         }
        }
       }
    
    public List<Pagoventa> listAll() throws AlidaPosException {
    	try {
    		pagoVentaDao.beginTransaction();
    		List<Pagoventa> result = pagoVentaDao.findAll();
    		pagoVentaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    
    @SuppressWarnings("rawtypes")
	public List getMaxId() throws AlidaPosException {
    	try {
    		pagoVentaDao.beginTransaction();
    		List result = pagoVentaDao.getMaxId();
    		pagoVentaDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoVentaDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoVentaDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    
    public void deletePago(Pagoventa pago) throws AlidaPosException {
    	try {
	    	pagoVentaDao.beginTransaction();
	    	Pagoventa persistedObject = pagoVentaDao.findReferenceOnly(pago.getIdpago());
	        pagoVentaDao.delete(persistedObject);
	        pagoVentaDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				pagoVentaDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (pagoVentaDao != null) {
				pagoVentaDao.closeTransaction();
			}
		}
    }
}
