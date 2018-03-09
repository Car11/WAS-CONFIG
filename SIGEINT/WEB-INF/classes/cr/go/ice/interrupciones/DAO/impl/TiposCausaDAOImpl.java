package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.TiposCausaDAO;
import cr.go.ice.interrupciones.domain.TiposCausa;

public class TiposCausaDAOImpl extends HibernateDaoSupport implements TiposCausaDAO{
	
	public void agregar(TiposCausa tiposCausa)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.save(tiposCausa);
		hibernate.flush();
	}
	
	public void modificar(TiposCausa tiposCausa)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.update(tiposCausa);
		hibernate.flush();
	}
	
	public void eliminar(TiposCausa tiposCausa)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.delete(tiposCausa);
		hibernate.flush();
	}
	
	@SuppressWarnings("rawtypes")
	public boolean existe(Integer codigo)
	{
		TiposCausa tiposCausaTemp = new TiposCausa();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TiposCausa WHERE codigo = ?";
		Object[] values = {codigo};
		List tiposCausas = hibernate.find(hql, values);
		if(tiposCausas.size() > 0){
			return true;
		}else
		{
			return false;
		}   
	}
	
	@SuppressWarnings("rawtypes")
	public TiposCausa buscar(TiposCausa tiposCausa)
	{
		TiposCausa tiposCausaTemp = new TiposCausa();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TiposCausa WHERE codigo = ?";
		Object[] values = {tiposCausa.getCodigo()};
		List tiposCausas = hibernate.find(hql, values);
		if(tiposCausas.size() > 0){
			tiposCausaTemp = (TiposCausa) tiposCausas.get(0);
		}
		return tiposCausaTemp;   
	}
	
	public List<TiposCausa> buscarTodos()
	{
		TiposCausa tiposCausaTemp = new TiposCausa();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TiposCausa where estado = 0 order by codigo asc ";
		List<TiposCausa> tiposCausas = hibernate.find(hql);
		if(!tiposCausas.isEmpty())
		{
			return tiposCausas;
		}else
		{
			return new ArrayList<TiposCausa>();
		} 
	}

}
