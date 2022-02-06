package com.alidasoftware.pos.facade;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import com.alidasoftware.pos.dao.UsuarioDao;
import com.alidasoftware.pos.exception.AlidaPosException;
import com.alidasoftware.pos.model.Usuario;

public class UsuarioFacade implements Serializable {

	private static final long serialVersionUID = 2608690680194315935L;
	
	private UsuarioDao usuarioDao = new UsuarioDao();
	
	public void createUsuario(Usuario usuario) throws AlidaPosException {
		try {
			usuarioDao.beginTransaction();
			usuarioDao.save(usuario);
			usuarioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			usuarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			usuarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}
	
	public void updateUsuario(Usuario usuario) throws AlidaPosException {
		try {
			usuarioDao.beginTransaction();
			Usuario persistedObject = usuarioDao.find(usuario.getIdusuario());
			persistedObject.setClave(usuario.getClave());
			persistedObject.setClaveacceso(usuario.getClaveacceso());
			persistedObject.setComentarios(usuario.getComentarios());
			persistedObject.setCortecajas1(usuario.getCortecajas1());
			persistedObject.setCortecajas2(usuario.getCortecajas2());
			persistedObject.setCorteparcials1(usuario.getCorteparcials1());
			persistedObject.setCorteparcials2(usuario.getCorteparcials2());
			persistedObject.setInventariofisicos(usuario.getInventariofisicos());
			persistedObject.setEmpleado(usuario.getEmpleado());
			persistedObject.setVentas(usuario.getVentas());
			persistedObject.setActivo(usuario.isActivo());
			persistedObject.setTienda(usuario.getTienda());
			persistedObject.setPerfil(usuario.getPerfil());
			persistedObject.setAccesoMenu(usuario.getAccesoMenu());
			usuarioDao.update(persistedObject);
			usuarioDao.commitAndCloseTransaction();
		} catch (Exception ex) {
    		try {
    			System.out.println("Error al hacer el update");
    			usuarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			usuarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Usuario findUsuario(int idUsuario) throws AlidaPosException {
    	try {
    		usuarioDao.beginTransaction();
    		Usuario usuario = usuarioDao.find(idUsuario);
    		usuarioDao.closeTransaction();
    		return usuario;
    	} catch (Exception ex) {
    		try {
    			usuarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			usuarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public Usuario findUsuario(String nombreUsr) throws AlidaPosException {
		List<Usuario> allUsuarios = listAll();
        Iterator<Usuario> iteratorUsuarios = allUsuarios.iterator();
        Usuario  currentUsuario;
        while(iteratorUsuarios.hasNext()){
        	currentUsuario = iteratorUsuarios.next();
        	if (nombreUsr.toLowerCase().equals(currentUsuario.getClave().toLowerCase()) ) {
        		return currentUsuario;
			}
 		}  
        return null;
    }
    
    public Usuario findByClave(String searchClave) throws AlidaPosException{
    	try {
    		usuarioDao.beginTransaction();
    		Usuario usuario = usuarioDao.findByClave(searchClave);
    		usuarioDao.closeTransaction();
    		return usuario;
    	} catch (Exception ex) {
    		try {
    			usuarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			usuarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    public Usuario findUserByNamePwd(String userName, String userPwd) throws AlidaPosException{
    	try {
    		usuarioDao.beginTransaction();
    		Usuario usuario = usuarioDao.findByNamePwd(userName, userPwd);
    		usuarioDao.closeTransaction();
    		return usuario;
    	} catch (Exception ex) {
    		try {
    			usuarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			usuarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
    
    
    public List<Usuario> listAll() throws AlidaPosException {
    	try {
    		usuarioDao.beginTransaction();
    		List<Usuario> result = usuarioDao.findAll();
    		usuarioDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			usuarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			usuarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
    }
 
    public void deleteUsuario(Usuario usuario) throws AlidaPosException {
    	try {
	    	usuarioDao.beginTransaction();
	    	Usuario persistedObject = usuarioDao.findReferenceOnly(usuario.getIdusuario());
	        usuarioDao.delete(persistedObject);
	        usuarioDao.commitAndCloseTransaction();
    	} catch (Exception ex) {
			try {
				usuarioDao.rollback();
			} catch (Exception ex1) {
				throw new AlidaPosException(ex1.getMessage());
			}
			throw new AlidaPosException(ex.getMessage());
		} finally {
			if (usuarioDao != null) {
				usuarioDao.closeTransaction();
			}
		}
    }

	public List<Usuario> searchNombreUsuario(String searchNombre,
			String searchUsuario) throws AlidaPosException {
		try {
    		usuarioDao.beginTransaction();
    		List<Usuario> result = usuarioDao.findByNombreUsuario(searchNombre, searchUsuario);
    		usuarioDao.closeTransaction();
    		return result;
    	} catch (Exception ex) {
    		try {
    			usuarioDao.rollback();
    		} catch (Exception ex1) {
    			throw new AlidaPosException(ex1.getMessage());
    		}
    		throw new AlidaPosException(ex.getMessage());
    	} finally {
    		try {
    			usuarioDao.closeTransaction();
    		} catch (Exception ex) {
    			throw new AlidaPosException(ex.getMessage());
    		}
    	}
	}

}
