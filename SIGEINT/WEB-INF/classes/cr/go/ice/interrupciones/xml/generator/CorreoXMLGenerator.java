package cr.go.ice.interrupciones.xml.generator;

import com.vvs.files.XmlWriter;
import com.vvs.utilidades.Utilidades;
import com.vvs.xml.XMLGenerator;

import cr.go.ice.interrupciones.domain.Correo;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 * 
 * <p>Clase cr.go.ice.interrupciones.xml.generator.CorreoXMLGenerator.java</p>
 * <p>Modulo (subsistema): InterrupcionesWeb</p>
 * <p>Descricion de <code>CorreoXMLGenerator.java</code> Generador de XML para envio de correos para el reporte de interrupciones por circuito.</p>
 * <p>Fecha creación: 21/04/2009</p>
 * <p>Ultima actualización: 21/04/2009</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class CorreoXMLGenerator extends XMLGenerator {

    private List correosParaEnviar;

    /**
     * Constructor xml generator
     */
    public CorreoXMLGenerator() {
        super();
        this.correosParaEnviar = new ArrayList();
    }

    /**
     * Constructor sobrecargado
     * @param dataSource_p asigna el dataSource deseado
     */
    public CorreoXMLGenerator(DataSource dataSource_p) {
        super(dataSource_p);
        this.correosParaEnviar = new ArrayList();
    }
    /**
     * <p>Cada <code>XMLGenerator</code> debe implementar este metodo para establecer los parametros que necesita.</p>
     * @param params Un <code>HashMap</code> con los parametros que cada <code>XMLGenerator</code> necesita.
     * @throws Exception
     */
    public void setParameters(HashMap params) throws Exception {
        
        this.correosParaEnviar = (List)params.get("correosParaEnviar");

    }

    /**
     * <p>Este metodo recibe el path del archivo donde debe de generar el contenido XML.</p>
     * @param path La direccion donde debe generar el contenido XML
     * @return El archivo que contiene el XML generado
     * @throws Exception
     */
    public File generateXML(String path) throws Exception {

        File file = new File(path);
        XmlWriter xml = new XmlWriter(file.getPath());

        Vector atributesNames = new Vector();
        Vector atributesValues = new Vector();

        atributesNames.clear();
        atributesValues.clear();

        atributesNames.add("from");
        String remitente = (String) Utilidades.getContextVariable(new InitialContext(), "remitente");
        atributesValues.add(remitente);
        atributesNames.add("template");
        
        if(XMLGenerator.contextPath == null){
            throw new Exception("La localizaci&#243;n del contexto no ha sido establecida.");
        }
        
        Object templateHtmlObject = Utilidades.getContextVariable(new InitialContext(), "templateHtml");
        if(templateHtmlObject == null){
            throw new Exception("El archivo template no ha establecido (null).");
        }
        String valorTemplate = XMLGenerator.contextPath + File.separatorChar + "template" + File.separatorChar + templateHtmlObject.toString();
        atributesValues.add(valorTemplate);
        
        

        atributesNames.add("subject");
        atributesValues.add("Reporte de Interrupciones por Circuito");

        xml.addRootElement("correos", atributesNames, atributesValues);
        
        
        for(int i = 0; i < this.correosParaEnviar.size(); i++){
            
            atributesNames.clear();
            atributesValues.clear();
            
            atributesNames.add("to");
            Correo correo = (Correo)this.correosParaEnviar.get(i);            
            atributesValues.add(correo.getCorreo());
            
            atributesNames.add("attachment");
            Object attachmentObject = Utilidades.getContextVariable(new InitialContext(), "attachment");
            if(attachmentObject == null){
                throw new Exception("El archivo adjunto no se ha establecido (null).");
            }
            String valorAttachmentObject = attachmentObject.toString();
            System.out.println(valorAttachmentObject);
            atributesValues.add(valorAttachmentObject);
            
            xml.addRootElement("correo", atributesNames, atributesValues);
            xml.closeRootElement();
        }
        
        xml.closeAllElements();
        xml.close();
        return file;


    }


}