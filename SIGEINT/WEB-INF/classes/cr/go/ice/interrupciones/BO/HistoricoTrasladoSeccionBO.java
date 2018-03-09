package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.HistoricoTrasladoSeccion;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.BO.HistoricoTrasladoSeccionBO.java</p>
 * <p>M�dulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripci�n) La <<Interface|Clase>> <code>HistoricoTrasladoSeccionBO.java</code>.</p>
 * <p>Fecha creaci�n: 02/03/2011</p>
 * <p>�ltima actualizaci�n: 02/03/2011</p>
 * @author Vista Verde Tecnolog�a (root)
 * @version 1.0
 */
public interface HistoricoTrasladoSeccionBO {

    public void agregar(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public void modificar(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public void eliminar(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public HistoricoTrasladoSeccion buscar(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public boolean existe(HistoricoTrasladoSeccion historicoTrasladoSeccion);
    
    public List getHistoricoTrasladoSeccion();
}
