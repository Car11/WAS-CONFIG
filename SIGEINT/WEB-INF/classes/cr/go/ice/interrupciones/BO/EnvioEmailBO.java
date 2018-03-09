package cr.go.ice.interrupciones.BO;

import java.util.List;

import cr.go.ice.interrupciones.domain.Correo;

public interface EnvioEmailBO {
	
	public void enviarCorreoInterrupcionesCircuito(List<Correo> listaCorreos, String ruta);

}
