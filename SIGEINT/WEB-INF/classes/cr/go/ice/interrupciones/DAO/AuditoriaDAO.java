package cr.go.ice.interrupciones.DAO;

import java.util.Date;


/**
 * <p>Interface cr.go.ice.interrupciones.DAO.AuditoriaDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>AuditoriaDAO.java</code>Define los metodos de los Datos para Auditoria.</p>
 * <p>Descricion de <code>AuditoriaBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para Auditoria.</p>
 * <p>Fecha creación: 25/09/2012</p>
 * <p>Ultima actualización: 25/09/2012</p>
 * @author Grettel
 * @version 1.1
 */
public interface AuditoriaDAO {

	   
    public Long getAuditorias(Integer codigoOficina, Date fechaInicio, Date fechaFinal,String nivelRed, Integer region, Integer subRegion, Integer subEstacion, Integer circuito, Long seccion);


}