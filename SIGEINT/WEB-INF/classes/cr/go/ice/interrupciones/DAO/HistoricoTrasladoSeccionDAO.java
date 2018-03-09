package cr.go.ice.interrupciones.DAO;

import java.util.List;

import cr.go.ice.interrupciones.domain.HistoricoTrasladoSeccion;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.DAO.HistoricoTrasladoSeccionDAO.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>HistoricoTrasladoSeccionDAO.java</code>.</p>
 * <p>Fecha creación: 02/03/2011</p>
 * <p>Última actualización: 02/03/2011</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public interface HistoricoTrasladoSeccionDAO {

    public void agregar(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public void modificar(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public void eliminar(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public HistoricoTrasladoSeccion buscar(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public boolean existe(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public List getHistoricoTrasladoSeccion();
}
