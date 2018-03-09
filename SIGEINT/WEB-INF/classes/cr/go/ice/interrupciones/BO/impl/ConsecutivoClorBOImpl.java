
package cr.go.ice.interrupciones.BO.impl;

import java.util.GregorianCalendar;
import java.util.List;

import cr.go.ice.interrupciones.BO.ConsecutivoClorBO;
import cr.go.ice.interrupciones.DAO.ConsecutivoClorDAO;
import cr.go.ice.interrupciones.DAO.ParametroDAO;
import cr.go.ice.interrupciones.domain.ConsecutivoClor;
import cr.go.ice.interrupciones.domain.ConsecutivoClorID;
import cr.go.ice.interrupciones.domain.Parametro;

/**
 * <p>Clase cr.go.ice.interrupciones.BO.impl.ConsecutivoClorBOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ConsecutivoClorBOImpl.java</code>Define los Metodos de Logica y Negocio de las Clases para ConsecutivoClor.</p>
 * <p>Fecha creación: 18/01/2007</p>
 * <p>Ultima actualización: 18/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class ConsecutivoClorBOImpl implements ConsecutivoClorBO{

	/**
	 * <code>consecutivoClorDAO</code> Consecutivo Clor dao
	 */
	private ConsecutivoClorDAO consecutivoClorDAO;
    /**
     * <code>parametroDAO</code> Parametro dao
     */
    private ParametroDAO parametroDAO;
	
	/**
	 * Constructor
	 */
	public ConsecutivoClorBOImpl(){
				
	}

    /**
     * Asigna parametroDAO.
     * @param parametroDAO El parametroDAO a modificar.
     */
    public void setParametroDAO(ParametroDAO parametroDAO) {
        this.parametroDAO = parametroDAO;
    }

	/**
     * Asigna consecutivoClorDAO 
	 * @param consecutivoClorDAO El consecutivoClorDAO a modificar.
	 */
	public void setConsecutivoClorDAO(ConsecutivoClorDAO consecutivoClorDAO) {
		this.consecutivoClorDAO = consecutivoClorDAO;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#agregar(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public void agregar(ConsecutivoClor consecutivoClor) {
		this.consecutivoClorDAO.agregar(consecutivoClor);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#modificar(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public void modificar(ConsecutivoClor consecutivoClor) {
		this.consecutivoClorDAO.modificar(consecutivoClor);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#eliminar(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public void eliminar(ConsecutivoClor consecutivoClor) {
		this.consecutivoClorDAO.eliminar(consecutivoClor);
		
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#buscar(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public ConsecutivoClor buscar(ConsecutivoClor consecutivoClor) {
		return this.consecutivoClorDAO.buscar(consecutivoClor);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#getConsecutivosClor()
	 */
	public List getConsecutivosClor() {
		return this.consecutivoClorDAO.getConsecutivosClor();
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#consecutivoClorExiste(cr.go.ice.interrupciones.domain.ConsecutivoClor)
	 */
	public boolean consecutivoClorExiste(ConsecutivoClor consecutivoClor) {
		return this.consecutivoClorDAO.consecutivoClorExiste(consecutivoClor);
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#getConsecutivoClor(java.lang.Integer)
	 */
	public ConsecutivoClor getConsecutivoClor(Integer codigoOficina) {
        
        ConsecutivoClor consecutivoClor = null; 
        long consecutivo = 1;       
        
        GregorianCalendar fecha = new GregorianCalendar();
        int anoActual = fecha.get(GregorianCalendar.YEAR);
        int mesActual = fecha.get(GregorianCalendar.MONTH);
        Integer ano = new Integer(anoActual);
        Integer anoAnterior = new Integer(anoActual - 1);
        
        List parametros = parametroDAO.getParametros();
        Parametro parametro = null;
        
        ConsecutivoClor consecutivoAnoAnterior = new ConsecutivoClor();
        ConsecutivoClorID consecutivoID = new ConsecutivoClorID();
        consecutivoID.setCodigoOficina(codigoOficina);   
        consecutivoID.setAno(anoAnterior);
        consecutivoAnoAnterior.setConsecutivoClorID(consecutivoID);  
        consecutivoAnoAnterior = this.consecutivoClorDAO.buscar(consecutivoAnoAnterior);
        
        if(parametros != null && parametros.size() > 0){
            parametro = (Parametro)parametros.get(0);
            if(parametro.getParametroID().getGeneracionIndices().equals("S") && mesActual == 0 && consecutivoAnoAnterior != null && consecutivoAnoAnterior.getAbrirAno().equals(ConsecutivoClor.ABRIR_ANO_SI)){
                ano = new Integer(ano.intValue() - 1);
            }
        }
        
        consecutivoClor = this.consecutivoClorDAO.getConsecutivoClor(codigoOficina, ano);
        if(consecutivoClor != null){
            if(consecutivoClor.getConsecutivoInt() != null){
                consecutivo = consecutivoClor.getConsecutivoInt().longValue();
                consecutivo++;
            }                                   
            consecutivoClor.setConsecutivoInt(new Long(consecutivo));
            consecutivoClorDAO.modificar(consecutivoClor);   
        }else{
            consecutivoClor = new ConsecutivoClor();
            ConsecutivoClorID consecutivoClorID = new ConsecutivoClorID();
            consecutivoClorID.setAno(ano);
            consecutivoClorID.setCodigoOficina(codigoOficina);
            consecutivoClor.setConsecutivoClorID(consecutivoClorID);
            consecutivoClor.setConsecutivoInt(new Long(consecutivo));
            consecutivoClorDAO.agregar(consecutivoClor);
        }
        
        
		return consecutivoClor;
	}

	/**
	 * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#getConsecutivoClorReporte(java.lang.Integer)
	 */
	public ConsecutivoClor getConsecutivoClorReporte(Integer codigoOficina) {        
        ConsecutivoClor consecutivoClor = null; 
        long consecutivo = 1;       
        GregorianCalendar fecha = new GregorianCalendar();
        int anoActual = fecha.get(GregorianCalendar.YEAR);
        int mesActual = fecha.get(GregorianCalendar.MONTH);
        Integer ano = new Integer(anoActual);
        Integer anoAnterior = new Integer(anoActual - 1);
        
        ConsecutivoClor consecutivoAnoAnterior = new ConsecutivoClor();
        ConsecutivoClorID consecutivoID = new ConsecutivoClorID();
        consecutivoID.setCodigoOficina(codigoOficina);   
        consecutivoID.setAno(anoAnterior);
        consecutivoAnoAnterior.setConsecutivoClorID(consecutivoID);  
        consecutivoAnoAnterior = this.consecutivoClorDAO.buscar(consecutivoAnoAnterior);
        
        List parametros = parametroDAO.getParametros();
        Parametro parametro = null;
        if(parametros != null && parametros.size() > 0){
            parametro = (Parametro)parametros.get(0);
            if(parametro.getParametroID().getGeneracionIndices().equals("S") && mesActual == 0 && consecutivoAnoAnterior != null && consecutivoAnoAnterior.getAbrirAno().equals(ConsecutivoClor.ABRIR_ANO_SI)){
                ano = new Integer(ano.intValue() - 1);
            }
        }
        
        consecutivoClor = this.consecutivoClorDAO.getConsecutivoClor(codigoOficina, ano);
        if(consecutivoClor != null){
            if(consecutivoClor.getConsecutivoRep() != null){
                consecutivo = consecutivoClor.getConsecutivoRep().longValue();
                consecutivo++;
            }                                   
            consecutivoClor.setConsecutivoRep(new Long(consecutivo));
            consecutivoClorDAO.modificar(consecutivoClor);             
        }
        else{
            consecutivoClor = new ConsecutivoClor();
            ConsecutivoClorID consecutivoClorID = new ConsecutivoClorID();
            consecutivoClorID.setAno(ano);
            consecutivoClorID.setCodigoOficina(codigoOficina);
            consecutivoClor.setConsecutivoClorID(consecutivoClorID);
            consecutivoClor.setConsecutivoRep(new Long(consecutivo));
            consecutivoClorDAO.agregar(consecutivoClor);
        }
        
		return consecutivoClor;
	}

    /**
     * @see cr.go.ice.interrupciones.BO.ConsecutivoClorBO#getAnoConsecutivo(java.lang.Integer)
     */
    public Integer getAnoConsecutivo(Integer codigoOficina) {
        GregorianCalendar fecha = new GregorianCalendar();
        int anoActual = fecha.get(GregorianCalendar.YEAR);
        int mesActual = fecha.get(GregorianCalendar.MONTH);
        Integer ano = new Integer(anoActual);
        Integer anoAnterior = new Integer(anoActual - 1);
        
        ConsecutivoClor consecutivoAnoAnterior = new ConsecutivoClor();
        ConsecutivoClorID consecutivoID = new ConsecutivoClorID();
        consecutivoID.setCodigoOficina(codigoOficina);   
        consecutivoID.setAno(anoAnterior);
        consecutivoAnoAnterior.setConsecutivoClorID(consecutivoID);  
        consecutivoAnoAnterior = this.consecutivoClorDAO.buscar(consecutivoAnoAnterior);
        
        List parametros = parametroDAO.getParametros();
        Parametro parametro = null;
        if(parametros != null && parametros.size() > 0){
            parametro = (Parametro)parametros.get(0);
            if(parametro.getParametroID().getGeneracionIndices().equals("S") && mesActual == 0 && consecutivoAnoAnterior != null && consecutivoAnoAnterior.getAbrirAno().equals(ConsecutivoClor.ABRIR_ANO_SI)){
                ano = new Integer(ano.intValue() - 1);
            }
        }
        return ano;
    }

}
