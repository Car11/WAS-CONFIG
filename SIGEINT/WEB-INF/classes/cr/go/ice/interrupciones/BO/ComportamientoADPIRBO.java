package cr.go.ice.interrupciones.BO;

import java.util.List;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.BO.ComportamientoADPIRBO.java</p>
 * <p>M�dulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripci�n) La <<Interface|Clase>> <code>ComportamientoADPIRBO.java</code>.</p>
 * <p>Fecha creaci�n: 26/10/2010</p>
 * <p>�ltima actualizaci�n: 26/10/2010</p>
 * @author Vista Verde Tecnolog�a (root)
 * @version 1.0
 */
public interface ComportamientoADPIRBO {

    public List getComportamientoADPIR(Integer anoInicio, Integer anoFinal, Integer region, Integer subregion, Integer subestacion, Integer circuito);
}
