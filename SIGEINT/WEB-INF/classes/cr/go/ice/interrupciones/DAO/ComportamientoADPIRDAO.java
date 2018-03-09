package cr.go.ice.interrupciones.DAO;

import java.util.List;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.DAO.ComportamientoADPIRDAO.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ComportamientoADPIRDAO.java</code>.</p>
 * <p>Fecha creación: 26/10/2010</p>
 * <p>Última actualización: 26/10/2010</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public interface ComportamientoADPIRDAO {

    public List getComportamientoADPIR(Integer anoInicio, Integer anoFinal, Integer region, Integer subregion, Integer subestacion, Integer circuito);
}
