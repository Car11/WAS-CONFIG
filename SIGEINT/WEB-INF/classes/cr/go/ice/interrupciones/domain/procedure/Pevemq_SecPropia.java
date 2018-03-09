package cr.go.ice.interrupciones.domain.procedure;

import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * <p>Clase cr.go.ice.interrupciones.domain.procedure.Pevemq_SecPropia.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Pevemq_SecPropia.java</code>.</p>
 * <p>Fecha creación: 21/09/2007</p>
 * <p>Ultima actualización: 21/09/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class Pevemq_SecPropia  extends StoredProcedure {

    private static final String NOMBRE_PROCEDIMIENTO = "pevemq_secpropia";
    
    /**
     * Constructor  
     */
    public  Pevemq_SecPropia(DataSource ds){
        super(ds, NOMBRE_PROCEDIMIENTO);
        declareParameter(new SqlParameter("mes_var", Types.NUMERIC));
        declareParameter(new SqlParameter("ano", Types.NUMERIC));
        declareParameter(new SqlOutParameter("res", Types.VARCHAR));
        compile();
    }
    
    /**
     * Metodo que ejecuta el procedimiento almacenado
     * @param ano
     * @param mes
     * @return resultado de ejecutar el procedimiento almacenado
     */    
    public String execute(Integer ano, Integer mes){
        Map inParams = new HashMap();
        inParams.put("mes_var", mes);
        inParams.put("ano", ano);

        Map outParams = execute(inParams);
        if (outParams.size() > 0) {
            return outParams.get("res").toString();
        } else {
            return "N";
        }
    }
    
}
