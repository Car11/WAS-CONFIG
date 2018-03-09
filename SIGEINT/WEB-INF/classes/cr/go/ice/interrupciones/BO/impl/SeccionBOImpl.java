package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.SeccionBO;
import cr.go.ice.interrupciones.DAO.SeccionDAO;
import cr.go.ice.interrupciones.domain.CambioSeccion;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SeccionID;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.SeccionBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>SeccionBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Seccion.</p>
 * <p>Fecha creación: 23/02/2007</p>
 * <p>Ultima actualización: 23/02/2007</p>
 * @author Vista Verde Tecnologia (Cesar y Doc Cristian)
 * @version 1.1
 */
public class SeccionBOImpl implements SeccionBO{

	/** atributo <code>seccionDAO</code>*/
	private SeccionDAO seccionDAO;
	
	/**
	 * Asigna SeccionDAO
	 * @param seccionDAO
	 */
	public void setSeccionDAO(SeccionDAO seccionDAO) {
		this.seccionDAO = seccionDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.SeccionBO#agregar(cr.go.ice.interrupciones.domain.Seccion)
	 */
	public void agregar(Seccion seccion) {
	    //this.seccionDAO.actualizarTramo();
		this.seccionDAO.agregar(seccion);
	}
	
	/**
	 * @see cr.go.ice.interrupciones.BO.SeccionBO#modificar(cr.go.ice.interrupciones.domain.Seccion)
	 */
	public void modificar(Seccion seccion) {
	    //this.seccionDAO.actualizarTramo();
		this.seccionDAO.modificar(seccion);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.SeccionBO#eliminar(cr.go.ice.interrupciones.domain.Seccion)
	 */
	public void eliminar(Seccion seccion) {
	    //this.seccionDAO.actualizarTramo();
		this.seccionDAO.eliminar(seccion);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.SeccionBO#buscar(cr.go.ice.interrupciones.domain.SeccionID)
	 */
	public Seccion buscar(SeccionID seccionID) {		
		return this.seccionDAO.buscar(seccionID);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.SeccionBO#getSecciones()
	 */
	public List getSecciones() {
		return this.seccionDAO.getSecciones();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.SeccionBO#getSecciones(java.lang.Integer, java.lang.Integer)
	 */
	public List getSecciones(Integer subEstacion, Integer circuito) {
		return this.seccionDAO.getSecciones(subEstacion, circuito);	
	}	
	
	/**
	 * 
	 * @see cr.go.ice.interrupciones.BO.SeccionBO#getBorrarSecciones(java.lang.Integer, java.lang.Integer)
	 */
	public long getBorrarSecciones(Integer subEstacion, Integer circuito){
	    return this.seccionDAO.getBorrarSecciones(subEstacion, circuito);
	}
	
    /**
     * @see cr.go.ice.interrupciones.BO.SeccionBO#getSecciones(java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public List getSecciones(Integer subEstacion, Integer circuito,
            Integer orden) {
        return this.seccionDAO.getSecciones(subEstacion, circuito, orden);
    }
    
    public List getSecciones(Integer subEstacion, Integer circuito, Integer subregion, Integer region, Integer orden){
        return this.seccionDAO.getSecciones(region, subEstacion, circuito, subregion, orden);
    }
    
    /**
     * 
     * @see cr.go.ice.interrupciones.BO.SeccionBO#getSeccionesFiltro(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    @SuppressWarnings("rawtypes")
    public List getSeccionesFiltro(Integer region, Integer subregion, Integer subEstacion, Integer circuito){
        return this.seccionDAO.getSeccionesFiltro(region, subregion, subEstacion, circuito);
    }
    
    public List getSeccionesOrigen(Integer region, Integer subregion, Integer subEstacion, Integer circuito){
        return this.seccionDAO.getSeccionesOrigen(region, subregion, subEstacion, circuito);
    }

	/**
	 * @see cr.go.ice.interrupciones.BO.SeccionBO#existe(cr.go.ice.interrupciones.domain.Seccion)
	 */
	public boolean existe(Seccion seccion){
        return this.seccionDAO.existe(seccion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SeccionBO#getSeccionesEscalar(java.lang.Integer, java.lang.Integer)
     */
    public long getSeccionesEscalar(Integer subEstacion, Integer circuito) {
        return this.seccionDAO.getSeccionesEscalar(subEstacion, circuito);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SeccionBO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        return this.seccionDAO.ejecutarIndicesGlobales(ano,mes);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.SeccionBO#ejecutarIndicesCausa(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesCausa(Integer ano, Integer mes) {
        return this.seccionDAO.ejecutarIndicesCausa(ano,mes);        
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SeccionBO#ejecutarIndicesTrifasicos(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesTrifasicos(Integer ano, Integer mes) {
        return this.seccionDAO.ejecutarIndicesTrifasicos(ano,mes);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.SeccionBO#registrosAsociados(cr.go.ice.interrupciones.domain.SeccionID)
     */
    public Long registrosAsociados(SeccionID seccionID) {
        return this.seccionDAO.registrosAsociados(seccionID);        
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SeccionBO#getTodasSecciones(java.lang.Integer, java.lang.Integer)
     */
    public List getTodasSecciones(Integer subEstacion, Integer circuito) {
        return this.seccionDAO.getTodasSecciones(subEstacion, circuito);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.SeccionBO#getSeccionesPorOficina(java.lang.Integer)
     */
    public List getSeccionesPorOficina(Integer codigoOficina) {
        return this.seccionDAO.getSeccionesPorOficina(codigoOficina);
    }
    
    public List getSeccionesDestino(Integer subEstacion, Integer circuito){
        return this.seccionDAO.getSeccionesDestino(subEstacion, circuito);
    }
    
    public List getSeccionesDestino(Integer region, Integer subregion, Integer subEstacion, Integer circuito){
        return this.seccionDAO.getSeccionesDestino(region, subregion, subEstacion, circuito);
    }
    
    public String cambioSecciones(Seccion seccion, Integer tipo, String cedula){
    	
        String resultado = "error";
        try{
            resultado = this.seccionDAO.cambioSecciones(seccion, tipo, cedula);
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            resultado = "error";
        }
        return resultado;
    }
    
    public Boolean agregarTodo(List<Seccion> cambioSeccion, String cedula){
    	
        try{
            for(int i=0; i<cambioSeccion.size(); i++){
                CambioSeccion sec = new CambioSeccion();
                Seccion seccion = cambioSeccion.get(i);
                sec.getId().setSubEstacion(seccion.getSeccionID().getSubEstacion());
                sec.getId().setCircuito(seccion.getSeccionID().getCircuito());
                sec.getId().setSeccion(seccion.getSeccionID().getSeccion());
                sec.setAbonadoSeccion(seccion.getAbonadoSeccion());
                sec.setCodigoOficina(seccion.getCodigoOficina());
                sec.setCongelado(seccion.getCongelado());
                sec.setKmsSeccion(seccion.getKmsSeccion());
                sec.setNombreSeccion(seccion.getNombreSeccion());
                sec.setRegion(seccion.getRegion());
                sec.setSubRegion(seccion.getSubRegion());
                sec.getId().setCedula(cedula);
                this.seccionDAO.agregar(sec);
            }
            return Boolean.TRUE;
        }catch (Exception e) {
            e.printStackTrace();
            return Boolean.FALSE;
        }
    }
    
    public Boolean elimiarSecciones(String cedula){
        if(this.seccionDAO.buscarCedula(cedula)){
            return this.seccionDAO.elimiarSecciones(cedula);
        }else{
            return Boolean.TRUE;
        }
        
    }
    
}
