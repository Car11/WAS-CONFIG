package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.TiposCausa;

public interface TiposCausaBO {
	
	public void agregar(TiposCausa tiposCausa);
	
	public void modificar(TiposCausa tiposCausa);
	
	public void eliminar(TiposCausa tiposCausa);
	
	public boolean existe(Integer codigo);
	
	public TiposCausa buscar(TiposCausa tiposCausa);
	
	public List<TiposCausa> buscarTodos();

}
