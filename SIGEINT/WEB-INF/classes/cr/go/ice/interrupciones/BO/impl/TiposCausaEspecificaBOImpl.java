package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.TiposCausaEspecificaBO;
import cr.go.ice.interrupciones.DAO.TiposCausaDAO;
import cr.go.ice.interrupciones.DAO.TiposCausaEspecificaDAO;
import cr.go.ice.interrupciones.domain.TiposCausa;
import cr.go.ice.interrupciones.domain.TiposCausaEspecifica;

public class TiposCausaEspecificaBOImpl implements TiposCausaEspecificaBO{
	
	private TiposCausaEspecificaDAO tiposCausaEspecificaDAO;
	
	public void agregar(TiposCausaEspecifica tiposCausaEspecifica)
	{
		this.tiposCausaEspecificaDAO.agregar(tiposCausaEspecifica);
	}
	
	public void modificar(TiposCausaEspecifica tiposCausaEspecifica)
	{
		this.tiposCausaEspecificaDAO.modificar(tiposCausaEspecifica);
	}
	
	public void eliminar(TiposCausaEspecifica tiposCausaEspecifica)
	{
		this.tiposCausaEspecificaDAO.eliminar(tiposCausaEspecifica);
	}
	public boolean existe(Integer codigo, Integer tipoCausa)
	{
		return this.tiposCausaEspecificaDAO.existe(codigo, tipoCausa);
	}
	
	public TiposCausaEspecifica buscar(TiposCausaEspecifica tiposCausaEspecifica)
	{
		return this.tiposCausaEspecificaDAO.buscar(tiposCausaEspecifica);
	}
	public List<TiposCausaEspecifica> buscarTodos()
	{
		return this.tiposCausaEspecificaDAO.buscarTodos();
	}
	
	public List<TiposCausaEspecifica> buscarTodosTipoCausa(Integer tipoCausa)
	{
		return this.tiposCausaEspecificaDAO.buscarTodosTipoCausa(tipoCausa);
	}

	public TiposCausaEspecificaDAO getTiposCausaEspecificaDAO() {
		return tiposCausaEspecificaDAO;
	}

	public void setTiposCausaEspecificaDAO(TiposCausaEspecificaDAO tiposCausaEspecificaDAO) {
		this.tiposCausaEspecificaDAO = tiposCausaEspecificaDAO;
	}
	
	

}
