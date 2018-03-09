package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.TiposCausaEspecifica;

public interface TiposCausaEspecificaBO {
	
	public void agregar(TiposCausaEspecifica tiposCausaEspecifica);
	
	public void modificar(TiposCausaEspecifica tiposCausaEspecifica);
	
	public void eliminar(TiposCausaEspecifica tiposCausaEspecifica);
	
	public boolean existe(Integer codigo, Integer tipoCausa);
	
	public TiposCausaEspecifica buscar(TiposCausaEspecifica tiposCausaEspecifica);
	
	public List<TiposCausaEspecifica> buscarTodos();
	
	public List<TiposCausaEspecifica> buscarTodosTipoCausa(Integer tipoCausa);

}
