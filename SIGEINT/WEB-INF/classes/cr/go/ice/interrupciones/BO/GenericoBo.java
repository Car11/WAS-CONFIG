package cr.go.ice.interrupciones.BO;

import java.util.List;

import com.vvs.generic.bo.GenericUtilityBo;
import com.vvs.reporte.ReportesDinamicos;
import com.vvs.reporte.ReportesDinamicosSub;



public interface GenericoBo extends GenericUtilityBo{

    
    void agregar(ReportesDinamicos reportesDinamicos, List<ReportesDinamicosSub> listSubReporte);

    
    void modificar(ReportesDinamicos reportesDinamicos, List<ReportesDinamicosSub> listSubReporte);
    
   
    public String eliminar(ReportesDinamicos reportesDinamicos);
}
