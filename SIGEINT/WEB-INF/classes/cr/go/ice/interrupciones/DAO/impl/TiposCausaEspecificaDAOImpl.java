package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.TiposCausaEspecificaDAO;
import cr.go.ice.interrupciones.domain.TiposCausa;
import cr.go.ice.interrupciones.domain.TiposCausaEspecifica;

public class TiposCausaEspecificaDAOImpl extends HibernateDaoSupport implements TiposCausaEspecificaDAO{
	
	public void agregar(TiposCausaEspecifica tiposCausaEspecifica)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.save(tiposCausaEspecifica);
		hibernate.flush();
	}
	
	public void modificar(TiposCausaEspecifica tiposCausaEspecifica)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.update(tiposCausaEspecifica);
		hibernate.flush();
	}
	
	public void eliminar(TiposCausaEspecifica tiposCausaEspecifica)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.delete(tiposCausaEspecifica);
		hibernate.flush();
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public boolean existe(Integer codigo, Integer tipoCausa)
	{
		TiposCausaEspecifica tiposCausaTemp = new TiposCausaEspecifica();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TiposCausaEspecifica WHERE tiposCausaEspecificaID.causaEspecifica= ? AND tiposCausaEspecificaID.tiposCausa.codigo = ? ";
		Object[] values = {codigo, tipoCausa};
		List tiposCausas = hibernate.find(hql, values);
		if(tiposCausas.size() > 0){
			return true;
		}else
		{
			return false;
		}   
	}
	
	@SuppressWarnings("rawtypes")
	public TiposCausaEspecifica buscar(TiposCausaEspecifica tiposCausaEspecifica)
	{
		TiposCausaEspecifica tiposCausaTemp = new TiposCausaEspecifica();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TiposCausaEspecifica WHERE tiposCausaEspecificaID.causaEspecifica= ? AND tiposCausaEspecificaID.tiposCausa.codigo = ? ";
		Object[] values = {tiposCausaEspecifica.getTiposCausaEspecificaID().getCausaEspecifica() , tiposCausaEspecifica.getTiposCausaEspecificaID().getTiposCausa().getCodigo()};
		List tiposCausas = hibernate.find(hql, values);
		if(tiposCausas.size() > 0){
			tiposCausaTemp = (TiposCausaEspecifica) tiposCausas.get(0);
		}
		return tiposCausaTemp;   
	}
	
	public List<TiposCausaEspecifica> buscarTodos()
	{
		TiposCausaEspecifica tiposCausaTemp = new TiposCausaEspecifica();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TiposCausaEspecifica ";
		List<TiposCausaEspecifica> tiposCausas = hibernate.find(hql);
		if(!tiposCausas.isEmpty())
		{
			return tiposCausas;
		}else
		{
			return new ArrayList<TiposCausaEspecifica>();
		} 
	}
	public List<TiposCausaEspecifica> buscarTodosTipoCausa(Integer tipoCausa)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from TiposCausaEspecifica where (tiposCausaEspecificaID.tiposCausa.codigo = ? and estado = 0) order by tiposCausaEspecificaID.tiposCausa.codigo, tiposCausaEspecificaID.causaEspecifica ASC ";
		Object[] values = {tipoCausa};
		List<TiposCausaEspecifica> tiposCausas = hibernate.find(hql, values);
		if(!tiposCausas.isEmpty())
		{
			return tiposCausas;
		}else
		{
			return new ArrayList<TiposCausaEspecifica>();
		} 
	}

}
