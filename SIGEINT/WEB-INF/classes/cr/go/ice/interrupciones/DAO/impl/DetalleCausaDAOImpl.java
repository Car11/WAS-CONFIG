package cr.go.ice.interrupciones.DAO.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cr.go.ice.interrupciones.DAO.DetalleCausaDAO;
import cr.go.ice.interrupciones.domain.DetalleCausa;


public class DetalleCausaDAOImpl extends HibernateDaoSupport implements DetalleCausaDAO{
	
	public void agregar(DetalleCausa detalleCausa)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.save(detalleCausa);
		hibernate.flush();
	}
	
	public void modificar(DetalleCausa detalleCausa)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.update(detalleCausa);
		hibernate.flush();
	}
	
	public void eliminar(DetalleCausa detalleCausa)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		hibernate.clear();
		hibernate.delete(detalleCausa);
		hibernate.flush();
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	public boolean existe(Integer codigo, Integer tipoCausaEspecifica, Integer tipoCausa)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from DetalleCausa WHERE (detalleCausaID.detalleCausa= ? AND detalleCausaID.tiposCausaEspecifica.tiposCausaEspecificaID.causaEspecifica = ? AND detalleCausaID.tiposCausaEspecifica.tiposCausaEspecificaID.tiposCausa.codigo = ?) ";
		Object[] values = {codigo, tipoCausaEspecifica, tipoCausa};
		List detalleCausas = hibernate.find(hql, values);
		if(detalleCausas.size() > 0){
			return true;
		}else
		{
			return false;
		}   
	}
	
	@SuppressWarnings("rawtypes")
	public DetalleCausa buscar(DetalleCausa detalleCausa)
	{
		DetalleCausa detalleCausaTemp = new DetalleCausa();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from  DetalleCausa WHERE (detalleCausaID.detalleCausa= ? AND detalleCausaID.tiposCausaEspecifica.tiposCausaEspecificaID.causaEspecifica = ? AND detalleCausaID.tiposCausaEspecifica.tiposCausaEspecificaID.tiposCausa.codigo = ?) ";
		Object[] values = {detalleCausa.getDetalleCausaID().getDetalleCausa(), detalleCausa.getDetalleCausaID().getTiposCausaEspecifica().getTiposCausaEspecificaID().getCausaEspecifica(), detalleCausa.getDetalleCausaID().getTiposCausaEspecifica().getTiposCausaEspecificaID().getTiposCausa().getCodigo()};
		List detalleCausas = hibernate.find(hql, values);
		if(detalleCausas.size() > 0)
		{
			detalleCausaTemp = (DetalleCausa) detalleCausas.get(0);
		}
		return detalleCausaTemp;   
	}
	
	public List<DetalleCausa> buscarTodos()
	{
		DetalleCausa detalleCausaTemp = new DetalleCausa();
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from DetalleCausa ";
		List<DetalleCausa> detalleCausas = hibernate.find(hql);
		if(!detalleCausas.isEmpty())
		{
			return detalleCausas;
		}else
		{
			return new ArrayList<DetalleCausa>();
		} 
	}
	
	@SuppressWarnings("unchecked")
	public List<DetalleCausa> buscarTodosEspecifica(Integer tipoCausa, Integer tipoCausaEspecifica)
	{
		HibernateTemplate hibernate = this.getHibernateTemplate();
		String hql = "from DetalleCausa where detalleCausaID.tiposCausaEspecifica.tiposCausaEspecificaID.causaEspecifica = ? AND detalleCausaID.tiposCausaEspecifica.tiposCausaEspecificaID.tiposCausa.codigo = ? ";
		Object[] values = {tipoCausaEspecifica, tipoCausa};
		List<DetalleCausa> detalleCausas = hibernate.find(hql,values);
		if(!detalleCausas.isEmpty())
		{
			return detalleCausas;
		}else
		{
			return new ArrayList<DetalleCausa>();
		} 
	}

}
