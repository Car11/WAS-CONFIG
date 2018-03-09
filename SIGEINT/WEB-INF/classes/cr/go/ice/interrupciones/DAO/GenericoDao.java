package cr.go.ice.interrupciones.DAO;

import java.util.List;

import com.vvs.generic.dao.GenericUtilityDao;
import com.vvs.reporte.ReportesDinamicos;



public interface GenericoDao extends GenericUtilityDao{
  
    public void eliminarAsociacion (ReportesDinamicos reportesDinamicos);
	
}
