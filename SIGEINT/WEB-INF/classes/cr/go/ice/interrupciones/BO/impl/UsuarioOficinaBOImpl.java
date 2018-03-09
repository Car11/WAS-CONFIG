package cr.go.ice.interrupciones.BO.impl;


import java.util.List;

import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.DAO.UsuarioOficinaDAO;
import cr.go.ice.interrupciones.domain.UsuarioOficina;


/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.UsuarioOficinaBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>UsuarioOficinaBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para UsuarioOficina.</p>
 * <p>Fecha creación: 14/06/2017</p>
 * <p>Ultima actualización: 14/06/2017</p>
 * @author Rossmon (rhidalgo)
 * @version 1.1
 */
public class UsuarioOficinaBOImpl implements UsuarioOficinaBO {
	
	 private UsuarioOficinaDAO usuarioOficinaDAO;
	 
	public UsuarioOficinaDAO getUsuarioOficinaDAO() {
		return usuarioOficinaDAO;
	}

	public void setUsuarioOficinaDAO(UsuarioOficinaDAO usuarioOficinaDAO) {
		this.usuarioOficinaDAO = usuarioOficinaDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#agregar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void agregar(UsuarioOficina usuarioOficina) {
		this.usuarioOficinaDAO.agregar(usuarioOficina);
	}
	
	public void modificar(UsuarioOficina usuarioOficina) {
		this.usuarioOficinaDAO.modificar(usuarioOficina);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#eliminar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public void eliminar(UsuarioOficina usuarioOficina) {
		this.usuarioOficinaDAO.eliminar(usuarioOficina);	
	}
	
	public void eliminarPorCedula(Integer cedula) {
		this.usuarioOficinaDAO.eliminarPorCedula(cedula);	
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#buscar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public UsuarioOficina buscar(Long cedula, Integer codigoOficina) {		
		return this.usuarioOficinaDAO.buscar(cedula, codigoOficina);
	}
	
	public List<UsuarioOficina> buscarCedula(Long cedula) {		
		return this.usuarioOficinaDAO.buscarCedula(cedula);
	}
	
	public List<UsuarioOficina> buscarOficina(Integer codigoOficina) {		
		return this.usuarioOficinaDAO.buscarOficina(codigoOficina);
	}
	
	public List<UsuarioOficina> buscarOficinaEmpleado(Integer codigoOficina) {		
		return this.usuarioOficinaDAO.buscarOficinaEmpleado(codigoOficina);
	}
	
	public List<UsuarioOficina> buscarNombre(String nombre) {		
		return this.usuarioOficinaDAO.buscarNombre(nombre);
	}
	
	public List<UsuarioOficina> buscarCedulaNombreOficina(Long cedula, String nombre, Integer codigoOficina) {		
		return this.usuarioOficinaDAO.buscarCedulaNombreOficina(cedula, nombre, codigoOficina);
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#buscar(cr.go.ice.interrupciones.domain.Animal)
	 */
	public List<UsuarioOficina> buscarTodos() {		
		return this.usuarioOficinaDAO.buscarTodos();
	}
	/**
	 * @see cr.go.ice.interrupciones.BO.AnimalBO#animalExiste(cr.go.ice.interrupciones.domain.Animal)
	 */
	public boolean existe(UsuarioOficina usuarioOficina){
        return this.usuarioOficinaDAO.existe(usuarioOficina);
    }
	
	public boolean existeCedula(Integer cedula){
        return this.usuarioOficinaDAO.existeCedula(cedula);
    }

}
