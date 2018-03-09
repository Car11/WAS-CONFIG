package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.UbicacionGeneralDanoDAO;
import cr.go.ice.interrupciones.domain.UbicacionGeneralDano;

public class UbicacionGeneralDanoDAOImpl extends HibernateDaoSupport implements UbicacionGeneralDanoDAO
{
	public void agregar(UbicacionGeneralDano ubicacionGeneralDano)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.save(ubicacionGeneralDano);
		hibernate.flush();
	}
	
	public void modificar(UbicacionGeneralDano ubicacionGeneralDano)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.update(ubicacionGeneralDano);
		hibernate.flush();
	}
	
	public void eliminar(UbicacionGeneralDano ubicacionGeneralDano)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.delete(ubicacionGeneralDano);
		hibernate.flush();
	}
	
	@SuppressWarnings("rawtypes")
	public boolean existe(Integer codigo)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from UbicacionGeneralDano WHERE (codigo =?) ";
		Object[] values = {codigo};
		List ubicaciones = hibernate.find(hql, values);
		if(ubicaciones.size() > 0){
			return true;
		}else
		{
			return false;
		}   
	}
	
	@SuppressWarnings("rawtypes")
	public UbicacionGeneralDano buscar(UbicacionGeneralDano ubicacionGeneralDano)
	{
		UbicacionGeneralDano ubicacionGeneralDanoTemp = new UbicacionGeneralDano();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from UbicacionGeneralDano WHERE (codigo =?) ";
		Object[] values = {ubicacionGeneralDano.getCodigo()};
		List ubicaciones = hibernate.find(hql, values);
		if(ubicaciones.size() > 0){
			ubicacionGeneralDanoTemp = (UbicacionGeneralDano) ubicaciones.get(0);
		}
		return ubicacionGeneralDanoTemp;   
	}
	
	@SuppressWarnings({ "unused", "unchecked" })
	public List<UbicacionGeneralDano> buscarTodos()
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from UbicacionGeneralDano where estado = 0 order by codigo ASC ";
		List<UbicacionGeneralDano> ubicaciones = hibernate.find(hql);
		if(!ubicaciones.isEmpty())
		{
			return ubicaciones;
		}else
		{
			return new ArrayList<UbicacionGeneralDano>();
		} 
	}
}
