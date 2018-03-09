package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.TiposCausaBO;
import cr.go.ice.interrupciones.DAO.TiposCausaDAO;
import cr.go.ice.interrupciones.domain.TiposCausa;

public class TiposCausaBOImpl implements TiposCausaBO{
	
	private TiposCausaDAO tiposCausaDAO;
	
	public void agregar(TiposCausa tiposCausa)
	{
		this.tiposCausaDAO.agregar(tiposCausa);
	}
	
	public void modificar(TiposCausa tiposCausa)
	{
		this.tiposCausaDAO.modificar(tiposCausa);
	}
	
	public void eliminar(TiposCausa tiposCausa)
	{
		this.tiposCausaDAO.eliminar(tiposCausa);
	}
	public boolean existe(Integer codigo)
	{
		return this.tiposCausaDAO.existe(codigo);
	}
	
	public TiposCausa buscar(TiposCausa tiposCausa)
	{
		return this.tiposCausaDAO.buscar(tiposCausa);
	}
	public List<TiposCausa> buscarTodos()
	{
		return this.tiposCausaDAO.buscarTodos();
	}

	public TiposCausaDAO getTiposCausaDAO() {
		return tiposCausaDAO;
	}

	public void setTiposCausaDAO(TiposCausaDAO tiposCausaDAO) {
		this.tiposCausaDAO = tiposCausaDAO;
	}

}
