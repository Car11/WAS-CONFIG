package cr.go.ice.interrupciones.servicios;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;

import com.ibm.mq.MQMessage;
import com.vvs.mq.lite.MessageManager;

import cr.go.ice.interrupciones.web.Recurso;



import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;

/**
 * 
 * <p>TODO <<Interface|Clase>> cr.go.ice.energia.sige.transformadores.servicios.ServiciosAcemas.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>ServiciosAcemas.java</code>.</p>
 *    Clase en la que se interactua con el broker
 * <p>Fecha creación: 13/07/2011</p>
 * <p>Última actualización: 13/07/2011</p>
 * @author Vista Verde Tecnología (eperaza)
 * @version 1.0
 */
public class ServiciosAcemas{
	
	private MessageManager mq;
	private IMarshallingContext mctx;
    private IUnmarshallingContext uctx;
    private IBindingFactory factory;
    

    /**
     * 
     * Método consultaPueblo
     * TODO (Descripción) 
     * @param consulta
     * @return RespuestaConsultaPueblos
     * @throws JiBXException
     */
	public RespuestaConsultaPueblos consultaPueblo(ConsultaPueblos consulta)  throws JiBXException{
		//recibo una consulta
        //Métodos del jbix para convertir a xml
		this.factory = BindingDirectory.getFactory(ConsultaPueblos.class);
		this.mctx = factory.createMarshallingContext();
        //acá se convierte en xml
		String xml = this.getXml(consulta);
		System.out.println("XML consulta");
		System.out.println(xml);
		this.mq = new MessageManager();//crea el objeto que permite el envió y recibimiento de mensajes mq
		HashMap<String, String> configuracion  = this.conectar(); //Método que carga los parametros de conexión
        //Parametros con los nombres de las colas
		configuracion.put("PutQueueName", Recurso.getEtiqueta("mq.request.consulta_pueblos"));
        configuracion.put("GetQueueName", Recurso.getEtiqueta("mq.reply.respuesta.consulta_pueblos"));
        this.mq.setConfiguration(configuracion);
        try 
        {
        	System.out.println("Antes de conectar");
            //Se conecta con el servidor mq
	        this.mq.connect();
	        System.out.println("Conecta");
            //envia un mensaje de tipo request(esto es un mensaje que requiere respuesta)
	        this.mq.request(xml, 0);
	        long start = System.currentTimeMillis();
	        long end = start + 60 * 1000;
            //esto hace que el while espere como máximo de un mínuto
	        while(System.currentTimeMillis() < end)
	        {
	        	System.out.println("Entra System.currentTimeMillis()");
                //Durante un mínuto va estar preguntandole al broker por una respuesta
	        	MQMessage mensaje = mq.receiveMQMessage(1);
	        	System.out.println("Mensaje: "+mensaje);
	            if (mensaje != null) 
	            {            
                    //Esto se hace una vez obtenido la respuesta
	                String xmlEntrada = mensaje.readLine(); 
	                System.out.println("XML Respuesta");
	        		System.out.println(xmlEntrada);
                    //convierte el xml a objeto respuesta
	                this.factory = BindingDirectory.getFactory(RespuestaConsultaPueblos.class);
	                this.uctx = factory.createUnmarshallingContext();
                    RespuestaConsultaPueblos respuesta = (RespuestaConsultaPueblos)this.getObject(xmlEntrada);
	                this.mq.disconnect(); //realiza la desconexión del servicio
	                return respuesta;
	            }
	        }
        }
        catch (Exception e) 
        {
        	System.err.println(e.getMessage());
        } 
        finally 
        {
        	try 
        	{
                this.mq.disconnect();
            } 
        	catch (Exception e) 
        	{
                System.err.println(e.getMessage());
            }
            
        }
		return null;
	}
    
    /**
     * 
     * Método conectar 
     * @return HashMap
     */
    private HashMap<String, String> conectar()
    {
        HashMap<String, String> configuracion  = new HashMap<String, String>();
        configuracion.put("Host",           Recurso.getEtiqueta("mq.host"));
        configuracion.put("Port",           Recurso.getEtiqueta("mq.port"));
        configuracion.put("QueueManager",   Recurso.getEtiqueta("mq.manager"));
        configuracion.put("Channel",        Recurso.getEtiqueta("mq.channel"));
        configuracion.put("Usuario",        Recurso.getEtiqueta("mq.user"));
        configuracion.put("Clave",          Recurso.getEtiqueta("mq.password"));
        return configuracion;
    }
    
    /**
     * 
     * Método getXml
     * @param obj
     * @return String
     */
    private String getXml(Object obj) 
    {
        String xml = null;
        if(mctx!=null)
        {
            try 
            {   
                ByteArrayOutputStream baos = new ByteArrayOutputStream();   
                mctx.marshalDocument(obj, "UTF-8", null, baos);
                mctx.getXmlWriter().flush();    
                xml = new String(baos.toByteArray());   
            } 
            catch (JiBXException e) 
            {
                e.printStackTrace();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            xml=(String)obj;
        }
        return xml;
    }

    /**
     * 
     * Método getObject
     * @param xml
     * @return Object
     */
    private Object getObject(String xml) 
    {
        Object rtn = null;
        if(uctx!=null)
        {
            try 
            {    
                rtn = uctx.unmarshalDocument(new StringReader(xml), null);    
            } 
            catch (JiBXException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            rtn=xml;
        }
        return rtn;
    }

}
