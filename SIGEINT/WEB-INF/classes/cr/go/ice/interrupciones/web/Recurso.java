package cr.go.ice.interrupciones.web;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * <p>Clase cr.go.ice.energia.sige.transformadores.web.idioma.Recurso.java</p>
 * <p>Modulo (subsistema): GICE</p>
 * <p>Descricion de <code>Recurso.java</code>. Clase que define los paquetes en que se encuntran los recursos.</p>
 * <p>Fecha creación: 21/12/2009</p>
 * <p>Ultima actualización: 07/01/2010</p>
 * @author Vista Verde Tecnologia (clopez@vistaverdesoft.com)
 * @version 2.0
 */
public class Recurso {
    /** Atributo <code>GICE_PAQUETE_IDIOMA</code> define el la dirección del paquete y del archivo en que se encuentran las etiquetas del programa.*/
    private static final String GICE_PAQUETE_IDIOMA = "cr.go.ice.interrupciones.web.resources";
   
    public static final PropertyResourceBundle rcs = (PropertyResourceBundle) ResourceBundle.getBundle(GICE_PAQUETE_IDIOMA);
    
    /**
     * Método getEtiqueta
     * Se utiliza para obtener una etiqueta del archivo de recursos para mostrarla en la aplicación.
     * Se utiliza el atributo estático <code>rcs</code> que es el que indica el archivo que se utiliza para guardar las etiquetas.
     * @param llave Es el string identificador con el que se busca la etiqueta en el archivo.
     * @return La etiqueta buscada si se encuentra en el archivo o el valor de la llave entre signos de pregunta de la siguiente forma ?llave?
     */
    public static String getEtiqueta(String llave){
        String rtn = "?"+llave+"?";
        
        try{
           rtn = rcs.getString(llave);
        }catch(java.util.MissingResourceException mre){
           System.out.println(mre.getMessage());
        }
        return rtn;
    }
    
    
}
