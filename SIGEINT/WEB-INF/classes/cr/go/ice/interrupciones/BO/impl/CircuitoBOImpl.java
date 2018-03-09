package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.DAO.CircuitoDAO;
import cr.go.ice.interrupciones.domain.Circuito;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.CircuitoBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>CircuitoBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Circuito.</p>
 * <p>Fecha creación: 08/01/2007</p>
 * <p>Ultima actualización: 08/01/2007</p>
 * @author Vista Verde Tecnologia (Mario Leon y Doc Cristian)
 * @version 1.1
 */
public class CircuitoBOImpl implements CircuitoBO {
    private CircuitoDAO circuitoDAO;
    
    /**
     * Constructor.
     */
    public CircuitoBOImpl(){
        
    }

    /**
     * Asigna circuitoDAO.
     * @param circuitoDAO El circuitoDAO a modificar.
     */
    public void setCircuitoDAO(CircuitoDAO circuitoDAO) {
        this.circuitoDAO = circuitoDAO;
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#agregar(cr.go.ice.interrupciones.domain.Circuito)
     */
    public void agregar(Circuito circuito) {
        this.circuitoDAO.agregar(circuito);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#modificar(cr.go.ice.interrupciones.domain.Circuito)
     */
    public void modificar(Circuito circuito) {
        this.circuitoDAO.modificar(circuito);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#eliminar(cr.go.ice.interrupciones.domain.Circuito)
     */
    public void eliminar(Circuito circuito) {
        this.circuitoDAO.eliminar(circuito);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#buscar(java.lang.Integer, java.lang.Integer)
     */
    public Circuito buscar(Integer subEstacion, Integer circuito) {
        return this.circuitoDAO.buscar(subEstacion, circuito);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#buscar(java.lang.Integer)
     */
    public List buscar(Integer subEstacion) {
        return this.circuitoDAO.buscar(subEstacion);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#getCircuitos()
     */
    public List getCircuitos() {
        return this.circuitoDAO.getCircuitos();
    }

    
    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#getCircuitos(java.lang.Integer)
     */
    public List getCircuitos(Integer subEstacion){
    	return this.circuitoDAO.getCircuitos(subEstacion);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#getCircuitos(java.lang.Integer)
     */
    public List getCircuitosActivas(Integer subEstacion){
    	return this.circuitoDAO.getCircuitosActivas(subEstacion);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#circuitoExiste(java.lang.Integer, java.lang.Integer)
     */
    public boolean circuitoExiste(Integer subEstacion, Integer circuito) {
        return this.circuitoDAO.circuitoExiste(subEstacion, circuito);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#ejecutarIndicesGlobales(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesGlobales(Integer ano, Integer mes) {
        return this.circuitoDAO.ejecutarIndicesGlobales(ano,mes);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#ejecutarIndicesCausa(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesCausa(Integer ano, Integer mes) {
        return this.circuitoDAO.ejecutarIndicesCausa(ano,mes);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#ejecutarIndicesTrifasicos(java.lang.Integer, java.lang.Integer)
     */
    public String ejecutarIndicesTrifasicos(Integer ano, Integer mes) {
        return this.circuitoDAO.ejecutarIndicesTrifasicos(ano,mes);
    }
    
    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#getCircuitosPorOficina(java.lang.Integer)
     */
    public List getCircuitosPorOficina(Integer codigoOficina) {
        return this.circuitoDAO.getCircuitosPorOficina(codigoOficina);
    }

    
    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#getNombreTipo(java.lang.Integer)
     */
    public String getNombreTipo(Integer tipo) {
        return this.circuitoDAO.getNombreTipo(tipo);
    }

    /**
     * @see cr.go.ice.interrupciones.BO.CircuitoBO#getCircuitosOrdenPorCodigo()
     */
    public List getCircuitosOrdenPorCodigo() {
        return this.circuitoDAO.getCircuitosOrdenPorCodigo();
    }
}
