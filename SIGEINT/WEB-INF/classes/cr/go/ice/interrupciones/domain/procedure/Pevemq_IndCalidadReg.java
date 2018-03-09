package cr.go.ice.interrupciones.domain.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

import javax.sql.DataSource;

import com.vvs.bussiness.BussinessObject;
import com.vvs.utilidades.Utilidades;

public class Pevemq_IndCalidadReg extends BussinessObject
{
	public Pevemq_IndCalidadReg()
	{
		super();
	}
    @SuppressWarnings("rawtypes")
    public boolean IndicesCalidadRegional(HashMap parametrosProcedimiento)
    {
    	boolean exito = true;
        Integer mes = (Integer) parametrosProcedimiento.get("mes");
        Integer ano = (Integer) parametrosProcedimiento.get("ano");

        Connection conn = null;
        CallableStatement func = null;

        try {

            DataSource data = (DataSource)Utilidades.getContextVariable("jdbc/interrupciones2DS");
            conn = data.getConnection();
            func = conn.prepareCall("{call PEVEMQ_INDCALIDADREGIONALES (?,?,?)}");

            func.setInt(1, mes.intValue());
            func.setInt(2, ano.intValue());
            func.registerOutParameter(3, java.sql.Types.VARCHAR);
            
            func.execute();
            String res = func.getString(3);
            func.close();
            conn.commit();
            conn.close();
            if(res.equals("N"))
            {
            	exito = false;
            }

        } catch (Exception e) {
        	exito = false;
            e.printStackTrace();
            try {
                conn.rollback();
                if (func != null) {
                    func.close();
                }
                conn.close();

            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
        	//exito = false;
            try {
                if (func != null) {
                    func.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return exito;
    }    

}
