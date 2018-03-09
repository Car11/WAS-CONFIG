
package cr.go.ice.interrupciones.DAO;

import java.util.Date;
import java.util.List;

import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.InterrupcionID;

/**
 * <p>Interface cr.go.ice.interrupciones.DAO.InterrupcionDAO.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionDAO.java</code>Define los metodos de los Datos para Interrupcion.</p>
 * <p>Fecha creaci�n: 26/01/2007</p>
 * <p>Ultima actualizaci�n: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public interface InterrupcionDAO{

    /**
     * Agrega una interrupcion
     * @param interrupcion a agregar
     */
    public void agregar(Interrupcion interrupcion);
    
    /**
     * Modifica una interrupcion
     * @param interrupcion a modificar
     */
    public void modificar(Interrupcion interrupcion);
    
    /**
     * Elimina una interrupcion
     * @param interrupcion a eliminar
     */
    public void eliminar(Interrupcion interrupcion);
    
    /**
     * Retorna una interrupci�n espec�fica
     * @param interrupcionID indica cual es la interrupci�n deseada
     * @return Interrupcion que se requiere
     */
    public Interrupcion getInterrupcion(InterrupcionID interrupcionID);    
    
    /**
     * Retorna la lista de interrupciones almacenadas en la base de datos
     * @return Lista de Interrupciones
     */
    public List getInterrupciones();        
    
    /**
     * Determina si una interrupci�n existe
     * @param interrupcionID indica la interrupci�n a determinar su existencia
     * @return La existencia o no de la interrupci�n
     */
    public boolean interrupcionExiste(InterrupcionID interrupcionID);

	/**
	 * M�todo que ejecuta el procedimiento almacenado de c�lculo de �ndices propios
	 * @param ano A�o a partir del cual se desea realizar el c�lculo de �ndices
	 * @param mes Mes a partir del cual se desea realizar el c�lculo de �ndices
	 * @return S o N de acuerdo a la correcta ejecuci�n o no del procedimiento almacenado
	 */
    public String ejecutarIndicesPropios(Integer ano, Integer mes);
	
    /**
     * Retorna las interrupciones que sucedieron dentro un determinado per�odo de tiempo.  Tales interrupciones filtradas por
     * oficina, tipo de interrupci�n, bit�cora y circuito
     * @param codigoOficina Oficina por la cual se filtrar�n las interrupciones
     * @param fechaInicio Fecha de inicio a partir de la cual se obtendr�n las interrupciones
     * @param fechaFinal Fecha de fin hasta la cual se obtendr�n las interrupciones
     * @param voltajes Lista de tipos de voltajes, en formato String, para los cuales se filtrar�n las interrupciones
     * @param causa1 Indica si se debe filtrar por la causa 417 o no
     * @param bitacora Indica si debe filtrar por bit�cora o no, o si m�s bien es indiferente
     * @param codigoCircuito Indica el circuito para el cual se obtendr�n las interrupciones
     * @param subEstacion Indica la subestacion para el cual se obtendr�n las interrupciones 
     * @return Lista de interrupciones deseadas
     */
    public Long getInterrupcionesPorCircuito(Integer codigoOficina, Date fechaInicio, Date fechaFinal, String voltajes, Boolean causa1, Integer bitacora, Integer codigoCircuito, Integer subEstacion);
    
    /**
     * Retorna las interrupciones que sucedieron dentro un determinado per�odo de tiempo.  Tales interrupciones filtradas por
     * oficina o por agencia
     * @param codigoOficina Oficina por la cual se filtrar�n las interrupciones
     * @param codigoAgencia Agencia por la cual se filtrar�n las interrupciones
     * @param fechaInicio Fecha de inicio a partir de la cual se obtendr�n las interrupciones
     * @param fechaFinal Fecha de fin hasta la cual se obtendr�n las interrupciones
     * @return Cantidad de interrupciones deseadas
     */
    public Long getInterrupcionesPorPeriodo(Integer codigoOficina, Integer codigoAgencia, Date fechaInicio, Date fechaFinal);

    /**
     * Retorna un lista con los a�os en que han ocurrido interrupciones
     * @return Lista de a�os
     */
    public List getAnosDeInterrupciones();
    
    public boolean existeInterrupciones(Integer reg, Integer subR, Integer sub, Integer cir, Long sec);
}
