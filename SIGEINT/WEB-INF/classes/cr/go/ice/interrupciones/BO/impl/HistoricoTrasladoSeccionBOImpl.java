package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.HistoricoTrasladoSeccionBO;
import cr.go.ice.interrupciones.DAO.HistoricoTrasladoSeccionDAO;
import cr.go.ice.interrupciones.domain.HistoricoTrasladoSeccion;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.BO.impl.HistoricoTrasladoSeccionBOImpl.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>HistoricoTrasladoSeccionBOImpl.java</code>.</p>
 * <p>Fecha creación: 02/03/2011</p>
 * <p>Última actualización: 02/03/2011</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class HistoricoTrasladoSeccionBOImpl implements HistoricoTrasladoSeccionBO{
    
    private HistoricoTrasladoSeccionDAO historicoTrasladoSeccionDAO;

    public HistoricoTrasladoSeccionDAO getHistoricoTrasladoSeccionDAO() {
        return this.historicoTrasladoSeccionDAO;
    }

    public void setHistoricoTrasladoSeccionDAO(HistoricoTrasladoSeccionDAO historicoTrasladoSeccionDAO) {
        this.historicoTrasladoSeccionDAO = historicoTrasladoSeccionDAO;
    }
    
    
    public void agregar(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        if(!this.historicoTrasladoSeccionDAO.existe(historicoTrasladoSeccion)){
            this.historicoTrasladoSeccionDAO.agregar(historicoTrasladoSeccion); 
        }
    }
    
    public void modificar(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        if(this.historicoTrasladoSeccionDAO.existe(historicoTrasladoSeccion)){
            this.historicoTrasladoSeccionDAO.modificar(historicoTrasladoSeccion);
        }
    }
    
    public void eliminar(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        if(this.historicoTrasladoSeccionDAO.existe(historicoTrasladoSeccion)){
            this.historicoTrasladoSeccionDAO.eliminar(historicoTrasladoSeccion);
        }
    }
    
    public HistoricoTrasladoSeccion buscar(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        return this.historicoTrasladoSeccionDAO.buscar(historicoTrasladoSeccion);
    }
    
    public boolean existe(HistoricoTrasladoSeccion historicoTrasladoSeccion){
        return this.historicoTrasladoSeccionDAO.existe(historicoTrasladoSeccion);
    }
    
    public List getHistoricoTrasladoSeccion(){
        return this.historicoTrasladoSeccionDAO.getHistoricoTrasladoSeccion();
    }

}
