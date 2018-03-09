package cr.go.ice.interrupciones.BO.impl;

import java.util.ArrayList;
import java.util.List;

import cr.go.ice.interrupciones.BO.CorreoBO;
import cr.go.ice.interrupciones.DAO.CorreoDAO;
import cr.go.ice.interrupciones.DAO.OficinaDAO;
import cr.go.ice.interrupciones.domain.Correo;
import cr.go.ice.interrupciones.domain.Oficina;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.CorreoBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CorreoBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Correo.</p>
 * <p>Fecha creación: 20/02/2007</p>
 * <p>Ultima actualización: 20/02/2007</p>
 * @author Vista Verde Soft (Nano y Doc Cristian)
 * @version 1.1
 */
public class CorreoBOImpl implements CorreoBO {
	
	/**
	 * <code>correoDAO</code> Correo Dao
	 */
	private CorreoDAO correoDAO;
	/**
	 * <code>oficinaDAO</code> oficina DAO
	 */	
	private OficinaDAO oficinaDAO;
	
    /**
     * Asigna CorreoDAO
     * @param correoDAO El correoDAO a modificar.
     */
    public void setCorreoDAO(CorreoDAO correoDAO) {
        this.correoDAO = correoDAO;
    }

    /**
     * Asigna oficinaDAO.
     * @param oficinaDAO El oficinaDAO a modificar.
     */
    public void setOficinaDAO(OficinaDAO oficinaDAO) {
        this.oficinaDAO = oficinaDAO;
    }	
    
	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#agregar(cr.go.ice.interrupciones.domain.Correo)
	 */
	public void agregar(Correo correo) {
		this.correoDAO.agregar(correo);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#modificar(cr.go.ice.interrupciones.domain.Correo)
	 */
	public void modificar(Correo correo) {
		this.correoDAO.modificar(correo);

	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#eliminar(cr.go.ice.interrupciones.domain.Correo)
	 */
	public void eliminar(Correo correo) {
		this.correoDAO.eliminar(correo);

	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#buscar(java.lang.String)
	 */
	public Correo buscar(String direccionCorreo) {
		Correo correo = this.correoDAO.buscar(direccionCorreo);
		return correo;
	}
	
	
	
	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#buscarNombre(java.lang.String)
	 */
	public Correo buscarNombre(String nombre) {
		
		Correo correo = this.correoDAO.buscarNombre(nombre);
		return correo;
		
	}
		
	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#existe(cr.go.ice.interrupciones.domain.Correo)
	 */
	public boolean existe(Correo correo) {
		return this.correoDAO.existe(correo);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#getCorreos()
	 */
	public List getCorreos() {
		List correos = this.correoDAO.getCorreos();
		List resultado = new ArrayList();
		if(correos != null){
		    for(int i = 0; i < correos.size(); i++){
		        Correo correo = (Correo)correos.get(i);
		        Oficina oficina = this.oficinaDAO.buscar(correo.getCodigoOficina());
                if(oficina != null)
                    correo.setNombreOficina(oficina.getNombreOficina());
                else
                    correo.setNombreOficina("");
		        resultado.add(correo);
		    }
		}
		return resultado;
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#getCorreos(java.lang.Integer)
	 */
	public List getCorreos(Integer oficina) {
		List correos = this.correoDAO.getCorreos(oficina);
		List resultado = new ArrayList();
		if(correos != null){
		    for(int i = 0; i < correos.size(); i++){
		        Correo correo = (Correo)correos.get(i);
		        Oficina oficinaObj = this.oficinaDAO.buscar(correo.getCodigoOficina());
                if(oficina != null)
                    correo.setNombreOficina(oficinaObj.getNombreOficina());
                else
                    correo.setNombreOficina("");
		        resultado.add(correo);
		    }
		}
		return resultado;
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#getCorreosCorreo(java.lang.String)
	 */
	public List getCorreosCorreo(String direccionCorreo) {
		List correos = this.correoDAO.getCorreosCorreo(direccionCorreo);
		List resultado = new ArrayList();
		if(correos != null){
		    for(int i = 0; i < correos.size(); i++){
		        Correo correo = (Correo)correos.get(i);
		        Oficina oficina = this.oficinaDAO.buscar(correo.getCodigoOficina());
                if(oficina != null)
                    correo.setNombreOficina(oficina.getNombreOficina());
                else
                    correo.setNombreOficina("");
		        resultado.add(correo);
		    }
		}
		return resultado;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.CorreoBO#getCorreosNombre(java.lang.String)
	 */
	public List getCorreosNombre(String nombre) {
		List correos = this.correoDAO.getCorreosNombre(nombre);
		List resultado = new ArrayList();
		if(correos != null){
		    for(int i = 0; i < correos.size(); i++){
		        Correo correo = (Correo)correos.get(i);
		        Oficina oficina = this.oficinaDAO.buscar(correo.getCodigoOficina());
                if(oficina != null)
                    correo.setNombreOficina(oficina.getNombreOficina());
                else
                    correo.setNombreOficina("");
		        resultado.add(correo);
		    }
		}		
		return resultado;
	}
	

    /**
     * @see cr.go.ice.interrupciones.BO.CorreoBO#getCorreosParaEnviar(java.lang.Integer)
     */
    public List<Correo> getCorreosParaEnviar(Integer oficina) {
        return this.correoDAO.getCorreosParaEnviar(oficina);
    }
}
