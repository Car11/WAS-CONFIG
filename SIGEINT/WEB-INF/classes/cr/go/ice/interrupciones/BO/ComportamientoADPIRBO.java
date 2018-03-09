package cr.go.ice.interrupciones.BO;

import java.util.List;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.BO.ComportamientoADPIRBO.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ComportamientoADPIRBO.java</code>.</p>
 * <p>Fecha creación: 26/10/2010</p>
 * <p>Última actualización: 26/10/2010</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public interface ComportamientoADPIRBO {

    public List getComportamientoADPIR(Integer anoInicio, Integer anoFinal, Integer region, Integer subregion, Integer subestacion, Integer circuito);
}
