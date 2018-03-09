package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.DetalleCausaBO;
import cr.go.ice.interrupciones.DAO.DetalleCausaDAO;
import cr.go.ice.interrupciones.domain.DetalleCausa;

public class DetalleCausaBOImpl implements DetalleCausaBO{
	
	private DetalleCausaDAO detalleCausaDAO;

	
	public void agregar(DetalleCausa detalleCausa)
	{
		this.detalleCausaDAO.agregar(detalleCausa);
	}
	
	public void modificar(DetalleCausa detalleCausa)
	{
		this.detalleCausaDAO.modificar(detalleCausa);
	}
	
	public void eliminar(DetalleCausa detalleCausa)
	{
		this.detalleCausaDAO.eliminar(detalleCausa);
	}
	public boolean existe(Integer codigo, Integer tipoCausaEspecifica, Integer tipoCausa)
	{
		return this.detalleCausaDAO.existe(codigo, tipoCausaEspecifica, tipoCausa);
	}
	
	public DetalleCausa buscar(DetalleCausa detalleCausa)
	{
		return this.detalleCausaDAO.buscar(detalleCausa);
	}
	public List<DetalleCausa> buscarTodos()
	{
		return this.detalleCausaDAO.buscarTodos();
	}
	
	public List<DetalleCausa> buscarTodosEspecifica(Integer tipoCausa, Integer tipoCausaEspecifica)
	{
		return this.detalleCausaDAO.buscarTodosEspecifica(tipoCausa, tipoCausaEspecifica);
	}
	
	public DetalleCausaDAO getDetalleCausaDAO() {
		return detalleCausaDAO;
	}

	public void setDetalleCausaDAO(DetalleCausaDAO detalleCausaDAO) {
		this.detalleCausaDAO = detalleCausaDAO;
	}
	
	

}
