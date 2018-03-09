package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import com.vvs.bussiness.BussinessError;
import com.vvs.generic.bo.impl.GenericUtilityBoImpl;
import com.vvs.generic.dao.GenericUtilityDao;
import com.vvs.generic.domain.Parametro;
import com.vvs.reporte.ReportesDinamicos;
import com.vvs.reporte.ReportesDinamicosSub;

import cr.go.ice.interrupciones.BO.AnimalBO;
import cr.go.ice.interrupciones.BO.GenericoBo;
import cr.go.ice.interrupciones.DAO.AnimalDAO;
import cr.go.ice.interrupciones.DAO.GenericoDao;



	public class GenericoBoImpl extends GenericUtilityBoImpl implements GenericoBo{
    
    private GenericoDao genericoDao;

        
    protected GenericUtilityDao getGenericUtilityDao() {
       // TODO Auto-generated method stub
       return genericoDao;
    }


   
    public void setGenericoDao(GenericoDao genericoDao) {
        this.genericoDao = genericoDao;
    }


    @Override
    public void agregar(ReportesDinamicos reportesDinamicos, List<ReportesDinamicosSub> listSubReporte) {
        try{
            this.genericoDao.agregar(reportesDinamicos);
            if (!listSubReporte.isEmpty())
                this.genericoDao.agregarLista(listSubReporte);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


  
    @Override
    public void modificar(ReportesDinamicos reportesDinamicos, List<ReportesDinamicosSub> listSubReporte) {
        try{
            this.genericoDao.modificar(reportesDinamicos);
            this.genericoDao.eliminarAsociacion(reportesDinamicos);
            if (!listSubReporte.isEmpty()){
                this.genericoDao.agregarLista(listSubReporte);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


  
    @Override
    public String eliminar(ReportesDinamicos reportesDinamicos) {
        try{
            if(reportesDinamicos.getSubreporte().equals(ReportesDinamicos.SUBREPORTE_EXISTE)){
                this.genericoDao.eliminarAsociacion(reportesDinamicos);
            }
            this.genericoDao.eliminar(reportesDinamicos);
            return "success";
        }catch(Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
