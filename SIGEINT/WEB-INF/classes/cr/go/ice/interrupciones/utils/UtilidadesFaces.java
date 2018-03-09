package cr.go.ice.interrupciones.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import cr.go.ice.cia.dominio.UsuarioCia;
import cr.go.ice.interrupciones.domain.Interrupcion;

/**
 * <p>Clase cr.go.ice.interrupciones.utils.UtilidadesFaces.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>UtilidadesFaces.java</code>.</p>
 * <p>Fecha creación: 21/11/2007</p>
 * <p>Ultima actualización: 21/11/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class UtilidadesFaces {

    /**
     * <code>tiposInterrupciones</code> tipos Interrupciones
     */
    public static final HashMap tiposInterrupciones = inicializar();
    
    /**
     * <code>FORMATO_PDF</code> Formato para archivo pdf
     */    
    public static final Integer FORMATO_PDF = new Integer(1);
    /**
     * <code>FORMATO_XLS</code> Formato para archivo xls
     */      
    public static final Integer FORMATO_XLS = new Integer(0);
    /**
     * Constante para el combo de nivel de red.
     */
    public static final String NIVEL_RED_REGION = "region";
    /**
     * Constante para el combo de nivel de red.
     */
    public static final String NIVEL_RED_SUB_REGION = "subregion";
    /**
     * Constante para el combo de nivel de red.
     */
    public static final String NIVEL_RED_SUB_ESTACION = "subestacion";
    /**
     * Constante para el combo de nivel de red.
     */
    public static final String NIVEL_RED_CIRCUITO = "circuito";
    /**
     * Constante para el combo de nivel de red.
     */
    public static final String NIVEL_RED_SECCION = "seccion";
    
    /**
     * <code>meses</code> meses
     */    
    public static final HashMap meses = inicializarMeses();
        
    private static HashMap inicializar(){
        HashMap hash = new HashMap();
        hash.put(Interrupcion.INTERRUPCION_SUMINISTRO, "Interrupción de suministro");
        hash.put(Interrupcion.INTERRUPCION_PRIMARIA, "Interrupción primaria");
        hash.put(Interrupcion.INTERRUPCION_SECUNDARIA, "Interrupción secundaria");
        hash.put(Interrupcion.INTERRUPCION_CATASTROFE, "Interrupción por catástrofe");
        hash.put(Interrupcion.INTERRUPCION_DISTRIBUCION, "Interrupción por distribución");
        return hash;
        
    }
    
    private static HashMap inicializarMeses(){
        HashMap hash = new HashMap();
        hash.put(new Integer(1), "ENERO");
        hash.put(new Integer(2), "FEBRERO");
        hash.put(new Integer(3), "MARZO");
        hash.put(new Integer(4), "ABRIL");
        hash.put(new Integer(5), "MAYO");
        hash.put(new Integer(6), "JUNIO");
        hash.put(new Integer(7), "JULIO");
        hash.put(new Integer(8), "AGOSTO");
        hash.put(new Integer(9), "SETIEMBRE");
        hash.put(new Integer(10), "OCTUBRE");
        hash.put(new Integer(11), "NOVIEMBRE");
        hash.put(new Integer(12), "DICIEMBRE");        
        return hash;
    }    
        
    /**
     * Retorna una lista de select item de los diferentes niveles de red
     * @return Lista de niveles de red
     */ 
    public static List getListaNivelesRed(){
        List niveles = new ArrayList();        
        niveles.add(new SelectItem(NIVEL_RED_REGION, "Región"));
        niveles.add(new SelectItem(NIVEL_RED_SUB_REGION, "Subregión"));
        niveles.add(new SelectItem(NIVEL_RED_SUB_ESTACION, "Subestación"));
        niveles.add(new SelectItem(NIVEL_RED_CIRCUITO, "Circuito"));
        niveles.add(new SelectItem(NIVEL_RED_SECCION, "Sección"));        
        return niveles;   
    }
    
    /**
     * Retorna una lista de select item de los diferentes niveles de red a partir de circuito
     * @return Lista de niveles de red
     */ 
    public static List getListaNivelesRedCircuito(){
        List niveles = new ArrayList();        
        niveles.add(new SelectItem(NIVEL_RED_CIRCUITO, "Circuito"));
        niveles.add(new SelectItem(NIVEL_RED_SECCION, "Sección"));        
        return niveles;   
    }    
    
    /**
     * Lista de meses en formato select item
     * @return lista de meses
     */
    public static List getListaMes(){
        List mesSI = new ArrayList();
        mesSI.add(new SelectItem(new Integer(1), "ENERO"));
        mesSI.add(new SelectItem(new Integer(2), "FEBRERO"));
        mesSI.add(new SelectItem(new Integer(3), "MARZO"));
        mesSI.add(new SelectItem(new Integer(4), "ABRIL"));
        mesSI.add(new SelectItem(new Integer(5), "MAYO"));
        mesSI.add(new SelectItem(new Integer(6), "JUNIO"));
        mesSI.add(new SelectItem(new Integer(7), "JULIO"));
        mesSI.add(new SelectItem(new Integer(8), "AGOSTO"));
        mesSI.add(new SelectItem(new Integer(9), "SEPTIEMBRE"));
        mesSI.add(new SelectItem(new Integer(10), "OCTUBRE"));
        mesSI.add(new SelectItem(new Integer(11), "NOVIEMBRE"));
        mesSI.add(new SelectItem(new Integer(12), "DICIEMBRE"));
        return mesSI;
    }
    
    /**
     * Determina la descripción de un tipo de interrupcion
     * @return tipoInterrupcion
     */
    public static String getTipoInterrupcion(Integer codigoTipoInterrupcion){        
        String tipoInterrupcion = (String)tiposInterrupciones.get(codigoTipoInterrupcion);
        return tipoInterrupcion;
    }
    
    /**
     * Determina la descripción de un mes
     * @return descripcionMe
     */
    public static String getDescripcionMes(Integer mes){        
        String mesSTR = (String)meses.get(mes);
        return mesSTR;
    }  
    /**
     * Método getCurrentUser
     * Busca el usuarioCia en session por medio del atributo KEY_USUARIO_CIA 
     * @return El usuarioCia en session. Si no se encuentra el usuario devuelve null
     */
    public static UsuarioCia getCurrentUser() {
        UsuarioCia usuarioCia=null;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        if (session != null) {usuarioCia  =  (UsuarioCia)session.getAttribute(UsuarioCia.KEY_USUARIO_CIA);}
        return usuarioCia;
    }
    
    /**
     * Método getCurrentUserId
     * Obtiene la cédula del usaurio CIA logeado en la sección
     * @return El número de cédula del usaurio CIA logeado en la sección o cero en caso contrario
     */

    public static Long getCurrentUserId() {
       Long id = Long.valueOf(0);
       UsuarioCia usuario  =  getCurrentUser();
       if(usuario!=null){
           id = usuario.getCedula();
       }
       return id;
   }  
    
    
}
