package cr.go.ice.interrupciones.BO.impl;

import java.util.ArrayList;
import java.util.List;

import cr.go.ice.interrupciones.BO.ProteccionBO;
import cr.go.ice.interrupciones.DAO.ProteccionDAO;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.Proteccion;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.ProteccionBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ProteccionBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases Proteccion.</p>
 * <p>Fecha creación: 22/01/2007</p>
 * <p>Ultima actualización: 22/01/2007</p>
 * @author Vista Verde Soft (Administrador y Doc Cristian)
 * @version 1.1
 */
public class ProteccionBOImpl implements ProteccionBO {
    
    /**
     * <code>proteccionDAO</code> Proteccion Dao
     */
    private ProteccionDAO proteccionDAO;
    
    /**
     * Constructor
     */
    public ProteccionBOImpl(){
    }

    
    /**
     * Asigna ProteccionDAO
     * @param proteccionDAO
     */
    public void setProteccionDAO(ProteccionDAO proteccionDAO) {
        this.proteccionDAO = proteccionDAO;
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#agregar(cr.go.ice.interrupciones.domain.Proteccion)
     */
    public void agregar(Proteccion proteccion) {
        this.proteccionDAO.agregar(proteccion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#modificar(cr.go.ice.interrupciones.domain.Proteccion)
     */
    public void modificar(Proteccion proteccion) {
        this.proteccionDAO.modificar(proteccion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#eliminar(cr.go.ice.interrupciones.domain.Proteccion)
     */
    public void eliminar(Proteccion proteccion) {
        this.proteccionDAO.eliminar(proteccion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#buscar(java.lang.Integer)
     */
    public Proteccion buscar(Integer codigo) {
        return this.proteccionDAO.buscar(codigo);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#buscar(java.lang.String)
     */
    public Proteccion buscar(String descripcion) {
        return this.proteccionDAO.buscar(descripcion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#getProtecciones()
     */
    public List getProtecciones() {
        return this.proteccionDAO.getProtecciones();
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#proteccionExiste(cr.go.ice.interrupciones.domain.Proteccion)
     */
    public boolean proteccionExiste(Proteccion proteccion) {
        return this.proteccionDAO.proteccionExiste(proteccion);
    }


    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#registrosAsociados(java.lang.Integer)
     */
    public Long registrosAsociados(Integer codigoProteccion) {
        return this.proteccionDAO.registrosAsociados(codigoProteccion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.ProteccionBO#getProtecciones(java.lang.Integer)
     */
	public List getProtecciones(Integer tipoVoltaje) {
		if(tipoVoltaje.equals(Interrupcion.INTERRUPCION_PRIMARIA)){
			return this.proteccionDAO.getProtecciones("100, 101, 102, 103, 104, 107, 109");
		}else if(tipoVoltaje.equals(Interrupcion.INTERRUPCION_SECUNDARIA)){
			return this.proteccionDAO.getProtecciones("100, 105, 109");
		}else{
			return new ArrayList();
		}
	}
}