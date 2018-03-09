package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.UbicacionEspecificaDanoDAO;
import cr.go.ice.interrupciones.domain.UbicacionEspecificaDano;

public class UbicacionEspecificaDanoDAOImpl extends HibernateDaoSupport implements UbicacionEspecificaDanoDAO{
	
	public void agregar(UbicacionEspecificaDano ubicacionEspecificaDano)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.save(ubicacionEspecificaDano);
		hibernate.flush();
	}
	
	public void modificar(UbicacionEspecificaDano ubicacionEspecificaDano)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.update(ubicacionEspecificaDano);
		hibernate.flush();
	}
	
	public void eliminar(UbicacionEspecificaDano ubicacionEspecificaDano)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.delete(ubicacionEspecificaDano);
		hibernate.flush();
	}
	
	@SuppressWarnings("rawtypes")
	public boolean existe(Integer codigo, Integer codigoGeneral)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from UbicacionEspecificaDano WHERE (ubicacionEspecificaDanoID.danoEspecifico = ? AND ubicacionEspecificaDanoID.ubicacionGeneralDano.codigo =? )";
		Object[] values = {codigo, codigoGeneral};
		List tiposCausas = hibernate.find(hql, values);
		if(tiposCausas.size() > 0){
			return true;
		}else
		{
			return false;
		}   
	}
	
	@SuppressWarnings("rawtypes")
	public UbicacionEspecificaDano buscar(UbicacionEspecificaDano ubicacionEspecificaDano)
	{
		UbicacionEspecificaDano ubicacionEspecificaDanoTemp = new UbicacionEspecificaDano();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		String hql = "from UbicacionEspecificaDano WHERE ubicacionEspecificaDanoID.danoEspecifico = ? AND ubicacionEspecificaDanoID.ubicacionGeneralDano.codigo =? ";
		Object[] values = {ubicacionEspecificaDano.getUbicacionEspecificaDanoID().getDanoEspecifico(), ubicacionEspecificaDano.getUbicacionEspecificaDanoID().getUbicacionGeneralDano().getCodigo()};
		List ubicaciones = hibernate.find(hql, values);
		if(ubicaciones.size() > 0){
			ubicacionEspecificaDanoTemp = (UbicacionEspecificaDano) ubicaciones.get(0);
		}
		return ubicacionEspecificaDanoTemp;   
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	public List<UbicacionEspecificaDano> buscarTodos()
	{
		UbicacionEspecificaDano ubicacionEspecificaDanoTemp = new UbicacionEspecificaDano();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from UbicacionEspecificaDano ";
		List<UbicacionEspecificaDano> ubicaciones = hibernate.find(hql);
		if(!ubicaciones.isEmpty())
		{
			return ubicaciones;
		}else
		{
			return new ArrayList<UbicacionEspecificaDano>();
		} 
	}
	@SuppressWarnings({ "unused", "unchecked" })
	public List<UbicacionEspecificaDano> buscarTodosGeneral(Integer ubicacionGeneral)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from UbicacionEspecificaDano where ubicacionEspecificaDanoID.ubicacionGeneralDano.codigo =? ";
		Object[] values = {ubicacionGeneral};
		List<UbicacionEspecificaDano> ubicaciones = hibernate.find(hql, values);
		if(!ubicaciones.isEmpty())
		{
			return ubicaciones;
		}else
		{
			return new ArrayList<UbicacionEspecificaDano>();
		} 
	}
}
