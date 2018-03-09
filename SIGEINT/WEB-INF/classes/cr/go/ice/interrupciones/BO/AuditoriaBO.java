package cr.go.ice.interrupciones.BO;

import java.util.Date;



/**
 * <p>Interface cr.go.ice.interrupciones.BO.AuditoriaBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>AuditoriaBO.java</code>Define los Metodos de Logica y Negocio de las Clases para Auditoria eve19m.</p>
 * <p>Fecha creación: 25/09/2012</p>
 * <p>Ultima actualización: 25/09/2012</p>
 * @author Grettel
 * @version 1.1
 */
public interface AuditoriaBO {

	   
     /**
     * Retorna si existen movimientos dentro un determinado período de tiempo.  
     * @param codigoOficina Oficina por la cual se filtrarán los movimientos
     * @param fechaInicio Fecha de inicio a partir de la cual se obtendrán los movimientos
     * @param fechaFinal Fecha de fin hasta la cual se obtendrán los movimientos
     * @return Tramo de red
     */
	
	
    public Long getAuditorias(Integer codigoOficina, Date fechaInicio, Date fechaFinal,String nivelRed, Integer region, Integer subRegion, Integer subEstacion, Integer circuito, Long seccion);

	
}
