
package cr.go.ice.interrupciones.DAO.impl;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;
import javax.sql.DataSource;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.vvs.utilidades.Fechas;

import cr.go.ice.interrupciones.DAO.AuditoriaDAO;



/**
 * <p>Clase cr.go.ice.interrupciones.DAO.impl.InterrupcionDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>InterrupcionDAOImpl.java</code>Define los metodos de los Datos para Interrupcion.</p>
 * <p>Fecha creación: 26/09/2012</p>
 * <p>Ultima actualización: 26/09/2012</p>
 * @author Grettel
 * @version 1.1
 */
public class AuditoriaDAOImpl  extends HibernateDaoSupport implements AuditoriaDAO{

   	
	
    /**
     * @see cr.go.ice.interrupciones.DAO.AuditoriaDAO#getAuditorias(Integer codigoOficina, Date fechaInicio, Date fechaFinal,String nivelRed, Integer region, Integer subRegion, 
     *	Integer subEstacion, Integer circuito, Long seccion)
     */
    public Long getAuditorias(Integer codigoOficina, Date fechaInicio, Date fechaFinal,String nivelRed, Integer region, Integer subRegion, 
    		Integer subEstacion, Integer circuito, Long seccion) {
    	
    	List movimientos = null;
		HibernateTemplate hibernate = this.getHibernateTemplate();   
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		  
		
	
        String hql = "SELECT COUNT(*) FROM Auditoria AS interup " +
			" WHERE " +
			" (interup.accion = 'MODIFI_VI' or interup.accion ='MODIFI_NU') and " +
			" (((interup.fechaInicio)) BETWEEN TO_DATE(?,'DD/MM/YYYY') AND TO_DATE(?,'DD/MM/YYYY')) and ";
		    hql += " interup.codigoOficina = ? AND ";
			
		
		 if(nivelRed.equals("region")){
			 hql += " interup.region = ? "; 
			 Object values [] = {sdf.format(fechaInicio),sdf.format(fechaFinal),codigoOficina,region};
			 movimientos = hibernate.find(hql,values);
 		    
	        }else{
	        	 if(nivelRed.equals("subregion")){
	        		 hql += " interup.region = ? AND interup.subregion = ?  "; 
	        		 Object values [] = {sdf.format(fechaInicio),sdf.format(fechaFinal),codigoOficina,region,subRegion};
	     		     movimientos = hibernate.find(hql,values);
	     		    
	 	        }  else{
	 	        	if(nivelRed.equals("subestacion")){
	 	        		 hql += " interup.region = ? AND interup.subregion = ? AND interup.subestacion = ? ";
	 	        		 Object values [] = {sdf.format(fechaInicio),sdf.format(fechaFinal),codigoOficina,region,subRegion,subEstacion};
	 	        		 movimientos = hibernate.find(hql,values);
	 	        		
	 		        }else{
	 		        	 if(nivelRed.equals("circuito")){
	 		        		 hql += " interup.region = ? AND interup.subregion = ? AND interup.subestacion = ? AND interup.circuito = ? ";
	 		        		 Object values [] = {sdf.format(fechaInicio),sdf.format(fechaFinal),codigoOficina,region,subRegion,subEstacion,circuito};
		 	        		 movimientos = hibernate.find(hql,values);
		 	        		 
	 			        }else{
	 			        	 if(nivelRed.equals("seccion")){
	 			        		 hql += " interup.region = ? AND interup.subregion = ?  AND interup.subestacion = ? AND interup.circuito = ? AND interup.seccion = ?";
	 			        		 Object values [] = {sdf.format(fechaInicio),sdf.format(fechaFinal),codigoOficina,region,subRegion,subEstacion,circuito,seccion};
			 	        		 movimientos = hibernate.find(hql,values);
			 	        		
	 	 			        }	
	 			        }
	 		        }
	 	        }
	        	
	        }
	             
		
		
        Long cantidad = new Long(0);
        if(movimientos != null && movimientos.size() > 0)
            cantidad = (Long)movimientos.get(0);
        return cantidad;
    }
    
  
	    
}


