package cr.go.ice.interrupciones.web.controller;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.vvs.jsf.AbstractFacesController;

import cr.go.ice.interrupciones.utils.Usuario;


/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.UsuarioController.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>UsuarioController.java</code> Determina el usuario actual de la aplicación</p>
 * <p>Fecha creación: 14/09/2007</p>
 * <p>Ultima actualización: 14/09/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class UsuarioController extends AbstractFacesController  {
    
    /**
     * Constructor  
     */
    public UsuarioController(){
       
    }
    
    public String getInit(){
    	return "success";
    }
    
    /**
     * Si el usuario se encuentra matriculado, determina el usuario actual de la aplicación
     * @return Usuario de la aplicación
     */
    public String getUsuario(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest rq = (HttpServletRequest) ctx.getExternalContext().getRequest();
        if(rq.getUserPrincipal() != null)
            return rq.getUserPrincipal().getName();
        else
            return "";
    }

	@Override
	protected String getPropertyFieldName(String property) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void resetController() {
		// TODO Auto-generated method stub
		
	}
    
    /**
     * Metodo que determina el rol del usuario que se encuentra matriculado en la aplicación
     * @return Rol de usuario
     */
   /** public String getRol(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        if(ctx.getExternalContext().getSessionMap().get("rol") == null){
	        String rol = Usuario.getUserRol();
	        ctx.getExternalContext().getSessionMap().put("rol",rol);
	        return (rol == null ? "" : "Rol: " + rol);
        }
        else{
            String rol = (String)ctx.getExternalContext().getSessionMap().get("rol");
            return (rol == null ? "" : "Rol: " + rol);
        }
    }   **/ 

}
