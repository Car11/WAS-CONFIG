package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.UbicacionGeneralDanoBO;
import cr.go.ice.interrupciones.DAO.UbicacionGeneralDanoDAO;
import cr.go.ice.interrupciones.domain.UbicacionGeneralDano;

public class UbicacionGeneralDanoBOImpl implements UbicacionGeneralDanoBO{
	
	private UbicacionGeneralDanoDAO ubicacionGeneralDanoDAO;
	
	public void agregar(UbicacionGeneralDano ubicacionGeneralDano)
	{
		this.ubicacionGeneralDanoDAO.agregar(ubicacionGeneralDano);
	}
	
	public void modificar(UbicacionGeneralDano ubicacionGeneralDano)
	{
		this.ubicacionGeneralDanoDAO.modificar(ubicacionGeneralDano);
	}
	
	public void eliminar(UbicacionGeneralDano ubicacionGeneralDano)
	{
		this.ubicacionGeneralDanoDAO.eliminar(ubicacionGeneralDano);
	}
	public boolean existe(Integer codigo)
	{
		return this.ubicacionGeneralDanoDAO.existe(codigo);
	}
	
	public UbicacionGeneralDano buscar(UbicacionGeneralDano ubicacionGeneralDano)
	{
		return this.ubicacionGeneralDanoDAO.buscar(ubicacionGeneralDano);
	}
	public List<UbicacionGeneralDano> buscarTodos()
	{
		return this.ubicacionGeneralDanoDAO.buscarTodos();
	}

	public UbicacionGeneralDanoDAO getUbicacionGeneralDanoDAO() {
		return ubicacionGeneralDanoDAO;
	}

	public void setUbicacionGeneralDanoDAO(UbicacionGeneralDanoDAO ubicacionGeneralDanoDAO) {
		this.ubicacionGeneralDanoDAO = ubicacionGeneralDanoDAO;
	}
	
	

}
