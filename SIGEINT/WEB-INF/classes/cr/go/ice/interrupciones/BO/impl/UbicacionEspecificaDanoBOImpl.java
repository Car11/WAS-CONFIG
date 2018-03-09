package cr.go.ice.interrupciones.BO.impl;

import java.util.List;

import cr.go.ice.interrupciones.BO.UbicacionEspecificaDanoBO;
import cr.go.ice.interrupciones.DAO.UbicacionEspecificaDanoDAO;
import cr.go.ice.interrupciones.domain.UbicacionEspecificaDano;

public class UbicacionEspecificaDanoBOImpl implements UbicacionEspecificaDanoBO{
	
	private UbicacionEspecificaDanoDAO ubicacionEspecificaDanoDAO;
	
	public void agregar(UbicacionEspecificaDano ubicacionEspecificaDano)
	{
		this.ubicacionEspecificaDanoDAO.agregar(ubicacionEspecificaDano);
	}
	
	public void modificar(UbicacionEspecificaDano ubicacionEspecificaDano)
	{
		this.ubicacionEspecificaDanoDAO.modificar(ubicacionEspecificaDano);
	}
	
	public void eliminar(UbicacionEspecificaDano ubicacionEspecificaDano)
	{
		this.ubicacionEspecificaDanoDAO.eliminar(ubicacionEspecificaDano);
	}
	public boolean existe(Integer codigo, Integer codigoGeneral)
	{
		return this.ubicacionEspecificaDanoDAO.existe(codigo, codigoGeneral);
	}
	
	public UbicacionEspecificaDano buscar(UbicacionEspecificaDano ubicacionEspecificaDano)
	{
		return this.ubicacionEspecificaDanoDAO.buscar(ubicacionEspecificaDano);
	}
	public List<UbicacionEspecificaDano> buscarTodos()
	{
		return this.ubicacionEspecificaDanoDAO.buscarTodos();
	}
	
	public List<UbicacionEspecificaDano> buscarTodosGeneral(Integer ubicacionGeneral)
	{
		return this.ubicacionEspecificaDanoDAO.buscarTodosGeneral(ubicacionGeneral);
	}
	
	public UbicacionEspecificaDanoDAO getUbicacionEspecificaDanoDAO() {
		return ubicacionEspecificaDanoDAO;
	}

	public void setUbicacionEspecificaDanoDAO(UbicacionEspecificaDanoDAO ubicacionEspecificaDanoDAO) {
		this.ubicacionEspecificaDanoDAO = ubicacionEspecificaDanoDAO;
	}
}
