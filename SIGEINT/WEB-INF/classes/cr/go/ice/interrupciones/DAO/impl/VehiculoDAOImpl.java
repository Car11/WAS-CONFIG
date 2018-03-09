
package cr.go.ice.interrupciones.DAO.impl;

import java.util.List;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import cr.go.ice.interrupciones.DAO.VehiculoDAO;
import cr.go.ice.interrupciones.domain.Vehiculo;

/**
 * <p>Clase cr.go.ice.obras.DAO.impl.VehiculoDAOImpl.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>VehiculoDAOImpl.java</code>Define los metodos de los Datos para Vehiculo.</p>
 * <p>Fecha creación: 26/01/2007</p>
 * <p>Ultima actualización: 26/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class VehiculoDAOImpl extends HibernateDaoSupport implements VehiculoDAO{

    /**
     * Constructor  
     */
    public VehiculoDAOImpl(){}

	/**
	 * @see cr.go.ice.interrupciones.DAO.VehiculoDAO#agregar(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public void agregar(Vehiculo vehiculo) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    hibernate.save(vehiculo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.VehiculoDAO#modificar(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public void modificar(Vehiculo vehiculo) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    hibernate.update(vehiculo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.VehiculoDAO#eliminar(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public void eliminar(Vehiculo vehiculo) {
	    HibernateTemplate hibernate = this.getHibernateTemplate();
	    hibernate.delete(vehiculo);
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.VehiculoDAO#buscar(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public Vehiculo buscar(Vehiculo vehiculo) {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		Vehiculo vehiculoResultado = null;

        String hql = "from Vehiculo vehiculo " +
        		"WHERE vehiculo.vehiculoID.aa = ? " +
        		"AND vehiculo.vehiculoID.codigoOficina = ? " +
        		"AND vehiculo.vehiculoID.numeroInterrupcion = ? " +
        		"AND vehiculo.vehiculoID.tipo = ?";
        
        Object[] values = {vehiculo.getVehiculoID().getAa(), 
                vehiculo.getVehiculoID().getCodigoOficina(), 
                vehiculo.getVehiculoID().getNumeroInterrupcion(), 
                vehiculo.getVehiculoID().getTipo()};
        
        List resultado = hibernate.find(hql, values);
        if(resultado.size()> 0){
        	vehiculoResultado = (Vehiculo) resultado.get(0);
        }
        return vehiculoResultado;
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.VehiculoDAO#getVehiculos()
	 */
	public List getVehiculos() {
		HibernateTemplate hibernate = this.getHibernateTemplate();
		return hibernate.find("from Vehiculo");
	}

	/**
	 * @see cr.go.ice.interrupciones.DAO.VehiculoDAO#vehiculoExiste(cr.go.ice.interrupciones.domain.Vehiculo)
	 */
	public boolean vehiculoExiste(Vehiculo vehiculo) {
		return (this.buscar(vehiculo) != null)? true : false; 
	}	
}
