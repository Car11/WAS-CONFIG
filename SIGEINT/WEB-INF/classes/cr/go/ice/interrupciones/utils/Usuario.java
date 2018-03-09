
package cr.go.ice.interrupciones.utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cr.go.ice.cia.dominio.UsuarioCia;



/**
 * <p>Clase cr.go.ice.interrupciones.utils.Usuario.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>Usuario.java</code> Controla lo relativo al usuario de la aplicación.</p>
 * <p>Fecha creación: 25/01/2007</p>
 * <p>Ultima actualización: 25/01/2007</p>
 * @author Vista Verde Soft (David y Doc Cristian)
 * @version 1.1
 */
public class Usuario {

 //   /**
 //    * Comment for getNombreUsuario
 //    * @return "usuario"
 //    */
 //   public static String getNombreUsuario(){
 //       return "usuario";
  //  }
    
    /**
     * Comment for getNombreAdministrador
     * @return "Administrador"
     */     
    public static String getNombreAdministrador(){
        return "ADM_AdministradorAplicacion";
    }
    
    /**
     * Comment for getNombreClor
     * @return "MNT_Clor"
     */
    public static String getNombreClor(){
        return "MNT_Clor";
    }    
    
    /**
     * Comment for getUsuario
     * @return Usuario rq.getUserPrincipal().getName()
     */
    public static String getUsuario(){
       // FacesContext ctx = FacesContext.getCurrentInstance();
      //  HttpServletRequest rq = (HttpServletRequest) ctx.getExternalContext().getRequest();        
      //  return rq.getUserPrincipal().getName();
        return Usuario.getUsuarioObj().getUsuario();
    } 
    
    /**
     * Retorna el <code>Usuario</code> de la sesión
     * @return Usuario
     */
    public static UsuarioCia getUsuarioObj(){
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest rq = (HttpServletRequest) ctx.getExternalContext().getRequest();  
        HttpSession session = rq.getSession(false);        
        
        return (UsuarioCia) session.getAttribute(UsuarioCia.KEY_USUARIO_CIA);
    }     
   
}
