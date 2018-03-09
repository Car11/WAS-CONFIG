package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.ComportamientoADPIRBO;
import cr.go.ice.interrupciones.DAO.ComportamientoADPIRDAO;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.BO.impl.ComportamientoADPIRBOImpl.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ComportamientoADPIRBOImpl.java</code>.</p>
 * <p>Fecha creación: 26/10/2010</p>
 * <p>Última actualización: 26/10/2010</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class ComportamientoADPIRBOImpl implements ComportamientoADPIRBO{

    private ComportamientoADPIRDAO comportamientoADPIRDAO;
    
    /**
     * @see cr.go.ice.interrupciones.BO.ComportamientoADPIRBO#getComportamientoADPIR(java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer, java.lang.Integer)
     */
    public List getComportamientoADPIR(Integer anoInicio, Integer anoFinal, Integer region, Integer subregion, Integer subestacion, Integer circuito) {
        return this.comportamientoADPIRDAO.getComportamientoADPIR(anoInicio, anoFinal, region, subregion, subestacion, circuito);
    }

    /**
     * Método accesor del atributo comportamientoADPIRDAO.
     * @return Retorna el atributo comportamientoADPIRDAO.
     */
    public ComportamientoADPIRDAO getComportamientoADPIRDAO() {
        return this.comportamientoADPIRDAO;
    }

    /**
     * Método modificador del atributo comportamientoADPIRDAO.
     * @param comportamientoADPIRDAO El dato para modificar el atributo comportamientoADPIRDAO.
     */
    public void setComportamientoADPIRDAO(ComportamientoADPIRDAO comportamientoADPIRDAO) {
        this.comportamientoADPIRDAO = comportamientoADPIRDAO;
    }

    
}
