package cr.go.ice.interrupciones.BO;

import java.util.Date;
import java.util.List;

import cr.go.ice.interrupciones.domain.Reporte;
import cr.go.ice.interrupciones.domain.ReporteID;

/**
 * <p>Interface cr.go.ice.interrupciones.BO.ReporteBO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ReporteBO.java</code>Define los Metodos de Logica y Negocio de las Clases para Reporte.</p>
 * <p>Fecha creación: 20/04/2007</p>
 * <p>Ultima actualización: 20/04/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public interface ReporteBO {

    /**
     * Agrega un reporte
     * @param reporte a agregar
     */
    public void agregar(Reporte reporte);
    
    /**
     * Modifica un reporte
     * @param reporte a modificar
     */
    public void modificar(Reporte reporte);
    
    /**
     * Elimina un reporte
     * @param reporte a eliminar
     */
    public void eliminar(Reporte reporte);
    
    /**
     * Retorna un reporte deseado
     * @param reporteID indica el reporte que se desea obtener
     * @return Reporte si existe en la base de datos, sino null
     */
    public Reporte getReporte(ReporteID reporteID);    
    
    /**
     * Retorna una lista de los reportes de la base de datos
     * @return Lista de Reportes
     */
    public List getReportes();        
    
    /**
     * Determina la existencia de un reporte
     * @param reporteID indica el reporte que se desea determinar su existencia
     * @return La existencia o no del reporte
     */
    public boolean reporteExiste(ReporteID reporteID);
    
    /**
     * Retorna un lista con los años en que han ocurrido interrupciones menores a cinco minutos
     * @return Lista de años
     */
    public List getAnosDeReportes();  
    
    /**
     * Retorna las interrupciones que sucedieron dentro un determinado período de tiempo menores a cinco minutos.  Tales interrupciones filtradas por
     * oficina, tipo de interrupción, bitácora y circuito
     * @param codigoOficina Oficina por la cual se filtrarán las interrupciones
     * @param fechaInicio Fecha de inicio a partir de la cual se obtendrán las interrupciones
     * @param fechaFinal Fecha de fin hasta la cual se obtendrán las interrupciones
     * @param voltajes Lista de tipos de voltajes, en formato String, para los cuales se filtrarán las interrupciones
     * @param causa1 Indica si se debe filtrar por la causa 417 o no
     * @param bitacora Indica si debe filtrar por bitácora o no, o si más bien es indiferente
     * @param codigoCircuito Indica el circuito para el cual se obtendrán las interrupciones
     * @param subEstacion Indica la subestacion para el cual se obtendrán las interrupciones 
     * @return Lista de interrupciones deseadas
     */
    public Long getInterrupcionesPorCircuitoMenor5Min(Integer codigoOficina, Date fechaInicio, Date fechaFinal, String voltajes, Boolean causa1, Integer bitacora, Integer codigoCircuito, Integer subEstacion, Boolean causa411);

    public boolean existeReportes(Integer reg, Integer subR, Integer sub, Integer cir, Long sec);
}
