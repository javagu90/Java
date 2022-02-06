package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.alidasoftware.pos.dao.PagoApartadoDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.helper.PagoApartadoHelper;
import com.alidasoftware.pos.helper.PagoVentaHelper;
import com.alidasoftware.pos.model.Pagoapartado;

public class PagoApartadoFacade implements Serializable {

	private static final long serialVersionUID = 1400795180117147904L;
	
	private PagoApartadoDao pagoApartadoDao = new PagoApartadoDao();
	
	public void createPago(Pagoapartado pago) throws AlidaPosException {
		try {
			pagoApartadoDao.beginTransaction();
			pagoApartadoDao.save(pago);
			pagoApartadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updatePago(Pagoapartado pago) throws AlidaPosException {
		try {
			pagoApartadoDao.beginTransaction();
			Pagoapartado persistedObject = pagoApartadoDao.find(pago.getIdpago());
			persistedObject.setCantidad(pago.getCantidad());
			persistedObject.setComentarios(pago.getComentarios());
			persistedObject.setFecha(pago.getFecha());
			persistedObject.setFormapago(pago.getFormapago());
			persistedObject.setReferenciapago(pago.getReferenciapago());
			persistedObject.setCambio(pago.getCambio());
			persistedObject.setApartado(pago.getApartado());
			persistedObject.setCaja(pago.getCaja());
			pagoApartadoDao.update(persistedObject);
			pagoApartadoDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Pagoapartado findPago(int idPagoApartado) throws AlidaPosException {
    	try {
    		pagoApartadoDao.beginTransaction();
    		Pagoapartado pagoApartado = pagoApartadoDao.find(idPagoApartado);
    		pagoApartadoDao.closeTransaction();
    		return pagoApartado;
    	} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<Pagoapartado> listByIdApartado(int idApartado) throws AlidaPosException {
    	try {
    		pagoApartadoDao.beginTransaction();
    		List<Pagoapartado> result = pagoApartadoDao.findByIdApartado(idApartado);
    		pagoApartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public List<Pagoapartado> listAll() throws AlidaPosException {
    	try {
    		pagoApartadoDao.beginTransaction();
    		List<Pagoapartado> result = pagoApartadoDao.findAll();
    		pagoApartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    
    public List getMaxId() throws AlidaPosException {
    	try {
    		pagoApartadoDao.beginTransaction();
    		List result = pagoApartadoDao.getMaxId();
    		pagoApartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public void deletePago(Pagoapartado pago) throws AlidaPosException {
    	try {
	    	pagoApartadoDao.beginTransaction();
	    	Pagoapartado persistedObject = pagoApartadoDao.findReferenceOnly(pago.getIdpago());
	        pagoApartadoDao.delete(persistedObject);
	        pagoApartadoDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				pagoApartadoDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (pagoApartadoDao != null) {
				pagoApartadoDao.closeTransaction();
			}
		}
    }
    public List<PagoApartadoHelper> listBySumFormaPago(int ultimobtenido, int ultimorealizado, Date fecha, int idCaja) throws AlidaPosException {
    	try {
    		pagoApartadoDao.beginTransaction();
    		List<PagoApartadoHelper> result = pagoApartadoDao.listBySumFormaPago(ultimobtenido, ultimorealizado, fecha, idCaja);
    		pagoApartadoDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			pagoApartadoDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			pagoApartadoDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public List<PagoApartadoHelper> listByFirstCP(int ultimorealizado, Date fecha, int idCaja) throws AlidaPosException {
        try {
         pagoApartadoDao.beginTransaction();
         List<PagoApartadoHelper> result = pagoApartadoDao.listByFirstCP(ultimorealizado, fecha, idCaja);
         pagoApartadoDao.closeTransaction();
         return result;
        } catch (Exception ex) {
         try {
          pagoApartadoDao.rollback();
         } catch (Exception ex1) {
          throw new AlidaPosException(ex1.getMessage());
         }
         throw new AlidaPosException(ex.getMessage());
        } finally {
         try {
          pagoApartadoDao.closeTransaction();
         } catch (Exception ex) {
          throw new AlidaPosException(ex.getMessage());
         }
        }
       }
    
    @SuppressWarnings({ "rawtypes" })
	public List getMaxIdUltimoPagoApartadoOfFirstCP(Date fecha, int idCaja) throws AlidaPosException
	{
        try {
         pagoApartadoDao.beginTransaction();
         List result = pagoApartadoDao.getMaxIdUltimoPagoApartadoOfFirstCP(fecha, idCaja);
         							   
         pagoApartadoDao.closeTransaction();
         return result;
        } catch (Exception ex) {
         try {
        	 pagoApartadoDao.rollback();
         } catch (Exception ex1) {
          throw new AlidaPosException(ex1.getMessage());
         }
         throw new AlidaPosException(ex.getMessage());
        } finally {
         try {
        	 pagoApartadoDao.closeTransaction();
         } catch (Exception ex) {
          throw new AlidaPosException(ex.getMessage());
         }
        }
       }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
   	public List getMaxIdUltimoPagoApartado( int ultimo, Date fecha, int idCaja) throws AlidaPosException {
           try {
            pagoApartadoDao.beginTransaction();
            List<PagoVentaHelper> result = pagoApartadoDao.getMaxIdUltimoPagoApartado(ultimo, fecha, idCaja);
            pagoApartadoDao.closeTransaction();
            return result;
           } catch (Exception ex) {
            try {
            	pagoApartadoDao.rollback();
            } catch (Exception ex1) {
             throw new AlidaPosException(ex1.getMessage());
            }
            throw new AlidaPosException(ex.getMessage());
           } finally {
            try {
            	pagoApartadoDao.closeTransaction();
            } catch (Exception ex) {
             throw new AlidaPosException(ex.getMessage());
            }
           }
          }


}
