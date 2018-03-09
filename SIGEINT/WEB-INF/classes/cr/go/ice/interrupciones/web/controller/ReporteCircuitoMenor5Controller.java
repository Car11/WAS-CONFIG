package cr.go.ice.interrupciones.web.controller;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.vvs.jsf.AbstractFacesController;
import com.vvs.mail.MassiveMailer;
import com.vvs.utilidades.Fechas;
import com.vvs.utilidades.Utilidades;
import com.vvs.xml.XMLGenerator;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.CorreoBO;
import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.BO.OficinaBO;
import cr.go.ice.interrupciones.BO.ReporteBO;
import cr.go.ice.interrupciones.BO.UsuarioOficinaBO;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.CircuitoID;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.UsuarioOficina;
import cr.go.ice.interrupciones.servlets.ServletPath;
import cr.go.ice.interrupciones.utils.Usuario;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.Recurso;
import cr.go.ice.sige.confmailer.bo.MailerBo;
import cr.go.ice.cia.dominio.UsuarioCia;

/**
 * <p>Clase cr.go.ice.interrupciones.web.controller.ReporteCircuitoMenor5Controller.java</p>
 * <p>M�dulo (subsistema): InterrupcionesWeb</p>
 * <p>(Descripci�n) La Clase <code>ReporteCircuitoMenor5Controller.java</code>.</p>
 * <p>Fecha creaci�n: 23/07/2009</p>
 * <p>�ltima actualizaci�n: 23/07/2009</p>
 * @author Vista Verde Tecnolog�a (Natalia)
 * @version 1.0
 */
public class ReporteCircuitoMenor5Controller extends AbstractFacesController {
    
    private CircuitoBO circuitoBO;    
    private OficinaBO oficinaBO;
    private EmpleadoBO empleadoBO;
    private CorreoBO correoBO;
    private ReporteBO reporteBO;
    
    private MailerBo mailerBo;
    private UsuarioOficinaBO usuarioOficinaBO;
    
    private Integer codigoOficina;
    private Integer tipoInterrupcion;
    private Integer tipoBitacora;
    private Date fechaInicio;
    private Date fechaFinal;
    private Boolean email;
    private Boolean causa411;
    
    private Long cedula;
    private Circuito circuito; 
    
    private Boolean comboOficina;
    
    String error;
   
   public static String servletImagepath;

   public static String servletJasperPath = "/jasperReports/";  ;    
   private  static final String jasperPath = "/jasperReports/";  
    /**
     * Constructor
     */
    public ReporteCircuitoMenor5Controller() {
        this.fechaInicio = new Date();
        this.fechaFinal = new Date();        
        this.tipoBitacora = new Integer(1);
        this.email = Boolean.valueOf(false);
        this.codigoOficina = new Integer(0);
        this.causa411= Boolean.valueOf(false);
        
        this.circuito = new Circuito();
        CircuitoID cirID = new CircuitoID();
        cirID.setCircuito(new Integer(0));
        SubEstacion sub = new SubEstacion();
        sub.setCodigoSubEstacion(new Integer(0));
        cirID.setSubEstacion(sub);
        this.circuito.setCircuitoID(cirID); 
        this.circuito.setNombreCircuito("x");     
        
        this.codigoOficina = new Integer(0);
        
        this.comboOficina = Boolean.valueOf(true);
    }
    
    public String getInit(){
    	FacesContext context = FacesContext.getCurrentInstance();
   	 String nombreUsuarioSession = (String) context.getApplication().createValueBinding("#{autorizacionController.nombreUsuario}").getValue(this.getFacesContext());
        String[] valores = nombreUsuarioSession.split("-");
        this.cedula = new Long(valores[0].trim());
    	return "success";
    }
    
    /**
     * Retorna una lista de select item de los tipos de interrupci�n
     * @return Lista de tipos de interrupci�n
     */    
    public List getListaTipoInterrupcion(){
        List tramo = new ArrayList();        
        tramo.add(new SelectItem(new Integer(0),"0 - Todos"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_SUMINISTRO,"1 - Interrupci�n de suministro"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_PRIMARIA,"2 - Interrupci�n primaria"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_SECUNDARIA,"3 - Interrupci�n secundaria"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_CATASTROFE,"4 - Interrupci�n por cat�strofe"));
        tramo.add(new SelectItem(Interrupcion.INTERRUPCION_DISTRIBUCION,"5 - Interrupci�n por distribuci�n"));        
        return tramo;       
    } 
    
    /**
     * Retorna una lista de select item de las oficinas existentes
     * @return Lista de oficinas
     */    
    public List getListaOficinas(){     
        Vector voficinas = new Vector();
        List loficinas = oficinaBO.getOficinas();
        
        voficinas.add(new SelectItem(new Integer(0), "Seleccione una oficina"));
        
        for (int i = 0; i < loficinas.size(); i++) {
            Oficina xoficina = (Oficina) loficinas.get(i);
            voficinas.add(new SelectItem(xoficina.getCodigoOficina(), xoficina.getCodigoOficina() + " - " + xoficina.getNombreOficina()));
        }       
        
        return voficinas;       
    }
    
    /**
     * Precarga la oficina del usuario de la aplicaci�n, si el mismo es un usuario CLOR
     * @param context
     */
    public void load(FacesContext context){
//      Comentado la restricci�n cuando se implement� el CIA
//      boolean userClor = Usuario.isUserClor();
      boolean userClor = true;

        Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
        
        if(userClor && limpiar != null){
        	  
            Empleado emp = this.empleadoBO.buscar(this.cedula);
            List<UsuarioOficina> listaPivote = this.usuarioOficinaBO.buscarCedula(this.cedula);
        	if(!listaPivote.isEmpty())
        	{
        		this.codigoOficina = listaPivote.get(0).getId().getOficina().getCodigoOficina();
        	}else
        	{
        		this.codigoOficina = new Integer(0);
        	}
            
        }   
    }
    
    /**
     * Retorna una lista de select item de los circuitos filtrados de acuerdo a la oficina seleccionada
     * @return Lista de circuitos
     */ 
    public List getListaCircuitos(){        
        List circuitosSI = new ArrayList();
        Circuito cir = new Circuito();
        CircuitoID cirID = new CircuitoID();
        cirID.setCircuito(new Integer(0));
        SubEstacion sub = new SubEstacion();
        sub.setCodigoSubEstacion(new Integer(0));
        cirID.setSubEstacion(sub);
        cir.setCircuitoID(cirID);       
        cir.setNombreCircuito("x");        
        circuitosSI.add(new SelectItem(cir,"-- Todos --"));    
        
        
        List circuitos = this.circuitoBO.getCircuitosPorOficina(this.codigoOficina);
        if(circuitos != null){
            for(int i = 0; i < circuitos.size(); i++){
                Circuito circuito = (Circuito)circuitos.get(i);
                circuitosSI.add(new SelectItem(circuito, circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion() + " - " + circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito()));
            }
        }
        return circuitosSI;
    }
    
    /**
     * Asigna el codigo de oficina de acuerdo a el cual se filtraran los circuitos
     * @param v
     * @return success
     */ 
    public String listenerOficina(){
        this.error = "";
        return "success";
    }
    
    
    /**
     * M�todo tipoBitacoraListener
     * TODO (Descripci�n) 
     * @param v
     */
    public void tipoBitacoraListener (){
        if ( this.tipoBitacora.equals(Integer.valueOf(1)))//bit�cora
            this.email= Boolean.TRUE;
        else
            this.email= Boolean.FALSE;
    }
    /**
     * Retorna una lista de select item de los tipos de bit�cora
     * @return Lista de tipos de bit�cora
     */ 
    public List getListaTipoBitacora(){
        List tramo = new ArrayList();
        tramo.add(new SelectItem(new Integer(1),"Bit�cora"));
        tramo.add(new SelectItem(new Integer(0),"No bit�cora"));
        tramo.add(new SelectItem(new Integer(2),"Ambos"));
        return tramo;       
    }   
    
    /**
     * Realiza las validaciones de los par�metros de entrada dados por el usuario y genera el reporte de interrupciones
     * por circuito
     * @return error o success de acuerdo a la correctitud de los parametros de entrada o no, de haberse encontrado
     * datos en la base de datos y de la correcta generaci�n del reporte
     */ 
    @SuppressWarnings("unchecked")
    public String generarReporteInterrupcionesPorCircuitoMenor5Min(){
       
        HashMap parametrosReporte = new HashMap();
        if(this.codigoOficina == null || this.codigoOficina.intValue() <= 0){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La oficina es requerida."));
            return "error";
        }       
        if(this.fechaInicio == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de inicio es requerida."));
            return "error";
        }       
        if(this.fechaFinal == null){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin es requerida."));
            return "error";
        }   
        long horasInicio = Fechas.millisToMinutes(this.fechaInicio.getTime());
        long horasFinal = Fechas.millisToMinutes(this.fechaFinal.getTime());
        long diferencia = horasFinal - horasInicio;
        if(diferencia < 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "La fecha de fin debe ser mayor o igual a la fecha de inicio."));
            return "error";
        }       
        
        String voltajes = "";
        Boolean causa = Boolean.valueOf(false);
        
        String todosVoltajes = "10, 20, 30, 40, 50, 60";
        
        switch(this.tipoInterrupcion.intValue()){
            case 0:voltajes = todosVoltajes;break;
            case 1:voltajes = "10";break;
            case 2:voltajes = "20, 30, 40";break;
            case 3:voltajes = "50, 60";break;
            case 4:causa = Boolean.valueOf(true);break;         
            case 5:voltajes = "20, 30, 40, 50, 60";break;           
        }
        
        Long interrupciones = this.reporteBO.getInterrupcionesPorCircuitoMenor5Min(this.codigoOficina, this.fechaInicio, this.fechaFinal, voltajes, causa, this.tipoBitacora, this.circuito.getCircuitoID().getCircuito(),this.circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion(), this.causa411);
        
        if(interrupciones.longValue() == 0){
            String mensaje = "No hay informaci�n que cumpla con los par�metros definidos";
            this.error = "<SCRIPT language='JavaScript1.2'>window.alert('" + mensaje + "');</SCRIPT>";

            return "error";
        }
        else{
            try{    
                
//                FacesContext ctx = FacesContext.getCurrentInstance();
//                ctx.getExternalContext().getSessionMap().put("tipoReporte",new Integer(ServletReporte.reportePorCircuitoMenorCincoMin));
//                ctx.getExternalContext().getSessionMap().put("titulo",this.getTitulo());
//                ctx.getExternalContext().getSessionMap().put("oficina",this.getOficina());
//                
//                ctx.getExternalContext().getSessionMap().put("codigoOficina",this.codigoOficina);                
//                ctx.getExternalContext().getSessionMap().put("tipoBitacora",this.tipoBitacora);
                //ServletImagepath = this.getServletContext().getRealPath("images"+File.separatorChar);
              
                if(causa.booleanValue()){
                    parametrosReporte.put("codigoCausa",Causa.CODIGO_CATASTROFE);
                    parametrosReporte.put("codigosVoltajeSTR",todosVoltajes);
                }
                else{
                    parametrosReporte.put("codigoCausa",new Integer(0));
                    parametrosReporte.put("codigosVoltajeSTR",voltajes);
                }   
                parametrosReporte.put("codigoCircuito",this.circuito.getCircuitoID().getCircuito());
                parametrosReporte.put("codigoSubestacion",this.circuito.getCircuitoID().getSubEstacion().getCodigoSubEstacion());   
                if(this.causa411){
                    parametrosReporte.put("causa411",Integer.valueOf(411));
                }else{
                    parametrosReporte.put("causa411",Integer.valueOf(0));
                }
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd/MM/yyyy");
                String fechaActualSTR = sf.format(fechaActual);
                Integer formato = UtilidadesFaces.FORMATO_XLS;
               String nombreArchivo = "PorCircuito"+ fechaActual +".xls";              
               //parametrosReporte.put("DIR_IMAGENES",ServletImagepath);
               //parametrosReporte.put("SUBREPORT_DIR",ServletJasperPath);  
               
              String nombreArchivoJasper = "SigeItrRepInterrupcionPorCircuitoMenor5Minutos.jasper";
              parametrosReporte.put("TITULO",this.getTitulo());
              parametrosReporte.put("OFICINA",this.getOficina());
              
              parametrosReporte.put("codigoOficina",this.codigoOficina);
              parametrosReporte.put("tipoBitacora",tipoBitacora);
              parametrosReporte.put("fechaInicio",sf.format(fechaInicio));
              parametrosReporte.put("fechaFinal",sf.format(fechaFinal));  
              
              
             
                if (this.runReport(jasperPath + nombreArchivoJasper, nombreArchivo,parametrosReporte,this.getConnection(),formato,UtilidadesFaces.getCurrentUserId())){
                    this.addInfo(null, "reporteEjecutado");
                    return  "success";      
                    
                }else{
                    this.addError(null, "reporteError");
                }
//                FacesContext context = FacesContext.getCurrentInstance();                       
//                HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//                HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/ServletReporte");
//                dispatcher.forward(request, response);  
//
//                response.getOutputStream().flush();

//                if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
//                    FacesContext.getCurrentInstance().responseComplete();
//                } 
                
                if(this.email.booleanValue()){
                    List correosParaEnviar = this.correoBO.getCorreosParaEnviar(this.codigoOficina);                
                    if(correosParaEnviar.size() == 0){
                        
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existen direcciones de correos, por favor verificar a oficina."));
                    }else{              
                        try{
                            enviarCorreo(correosParaEnviar);
                            
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informaci�n!", "El correo ha sido enviado exitosamente a su destinatario."));
                            
                        }catch(Exception e){
                            e.printStackTrace();
                            
                            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El correo no pudo ser enviado a su destinario."));
                        }
                    }
                }
                return "success";
            } catch(Exception se){
                se.printStackTrace();
                return "error";
            } 
        }        
    }
    private Connection getConnection(){
        DataSource data = (DataSource)Utilidades.getContextVariable("jdbc/interrupciones2DS");
        Connection conn = null;
        try {
            conn = data.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
        }
        return conn;
    }
    
        
    /**
     * Crea el t�tulo para el reporte de las interrupciones por circuito
     * @return String titulo del reporte
     */    
    private String getTitulo(){
        String titulo = "INFORME DE INTERRUPCIONES ";

        switch(this.tipoInterrupcion.intValue()){
            case 1:titulo += "POR SUMINISTRO ";break;
            case 2:titulo += "POR PRIMARIO ";break;
            case 3:titulo += "POR SECUNDARIO ";break;
            case 4:titulo += "POR CATASTROFE ";break;
            case 5:titulo += "POR DISTRIBUCION ";break;
            case 0:titulo += "PARA TODOS LOS TIPOS ";break;
        }
              
        switch(this.tipoBitacora.intValue()){
            case 1:titulo += "INCLUIDAS EN BITACORA ";break;
            case 0:titulo += "NO INCLUIDAS EN BITACORA ";break;
            case 2:titulo += "REGISTRADAS O NO EN BITACORA ";break;            
        }                
        
        titulo += "A NIVEL DE CIRCUITO";                
        return titulo;
    }
    
    /**
     * Retorna el nombre de la oficina seleccionada para las interrupciones por circuito
     * @return oficina
     */
    private String getOficina(){
        SimpleDateFormat sf = new SimpleDateFormat();
        sf.applyPattern("dd/MM/yyyy");
        Oficina oficina = this.oficinaBO.buscar(this.codigoOficina);
        String oficinaSTR = "OFICINA: " + oficina.getCodigoOficina() + " - " + oficina.getNombreOficina();
        oficinaSTR += " DEL " +  sf.format(this.fechaInicio) + " AL " + sf.format(this.fechaFinal);
        return oficinaSTR;
    }
    
    /**
     * Metodo que env�a un correo a determinados destinatarios
     * @param correosParaEnviar Lista de destinatarios
     */
    private void enviarCorreo(List correosParaEnviar){
        try{
            
            InitialContext context = new InitialContext();
            String path = ServletPath.path;
            String mailerConfig = path + File.separatorChar + "xml" + File.separatorChar + (String) Utilidades.getContextVariable(context, "MailerConfigurationFile");
            this.mailerBo.getConfigurationFile(mailerConfig);
            
            String modulo = (String) Utilidades.getContextVariable(context, "modulo");
            
            XMLGenerator.generatorsConfigFile = path + File.separatorChar + "xml" + File.separatorChar + (String) Utilidades.getContextVariable(context, "XMLGeneratorsPath");
            XMLGenerator.contextPath = path;
            XMLGenerator gen = XMLGenerator.create(modulo, (DataSource) Utilidades.getContextVariable(context, "jdbc/interrupciones2DS"));
            
            HashMap params = new HashMap();
            params.put("correosParaEnviar", correosParaEnviar);
            
            gen.setParameters(params);
            
            
            String mailerData = path + File.separatorChar + "xml" + File.separatorChar + (String) Utilidades.getContextVariable(context, "MailerDataFile");
            
            MassiveMailer mailer = new MassiveMailer(gen.generateXML(mailerData));
            mailer.setMailerConfiguration(new File(mailerConfig));
    
            mailer.run();
     
    }catch(Exception e){
        e.printStackTrace();
    }        
        
    }
    
    /**
     * Cancela el proceso de la generaci�n de reportes
     * @return success
     */    
    public String cancelar(){
        return "success";
    }


    /**
     * @see com.vvs.jsf.AbstractFacesController#getPropertyFieldName(java.lang.String)
     */
    protected String getPropertyFieldName(String arg0) {
        return null;
    }


    /**
     * M�todo accesor del atributo circuito.
     * @return Retorna el atributo circuito.
     */
    public Circuito getCircuito() {
        return this.circuito;
    }


    /**
     * M�todo modificador del atributo circuito.
     * @param circuito El dato para modificar el atributo circuito.
     */
    public void setCircuito(Circuito circuito) {
        this.circuito = circuito;
    }



    /**
     * M�todo accesor del atributo codigoOficina.
     * @return Retorna el atributo codigoOficina.
     */
    public Integer getCodigoOficina() {
        return this.codigoOficina;
    }


    /**
     * M�todo modificador del atributo codigoOficina.
     * @param codigoOficina El dato para modificar el atributo codigoOficina.
     */
    public void setCodigoOficina(Integer codigoOficina) {
        this.codigoOficina = codigoOficina;
    }


    /**
     * M�todo accesor del atributo comboOficina.
     * @return Retorna el atributo comboOficina.
     */
    public Boolean getComboOficina() {
        return this.comboOficina;
    }


    /**
     * M�todo modificador del atributo comboOficina.
     * @param comboOficina El dato para modificar el atributo comboOficina.
     */
    public void setComboOficina(Boolean comboOficina) {
        this.comboOficina = comboOficina;
    }


    /**
     * M�todo accesor del atributo causa411.
     * @return Retorna el atributo causa411.
     */
    public Boolean getCausa411() {
        return this.causa411;
    }


    /**
     * M�todo modificador del atributo causa411.
     * @param causa411 El dato para modificar el atributo causa411.
     */
    public void setCausa411(Boolean causa411) {
        this.causa411 = causa411;
    }


    /**
     * M�todo accesor del atributo email.
     * @return Retorna el atributo email.
     */
    public Boolean getEmail() {
        if (this.tipoBitacora.equals(Integer.valueOf(1)))//con bit�cora
            this.email= true;
        else 
            this.email= false;
        return this.email;
    }


    /**
     * M�todo modificador del atributo email.
     * @param email El dato para modificar el atributo email.
     */
    public void setEmail(Boolean email) {
        this.email = email;
    }


    /**
     * M�todo accesor del atributo error.
     * @return Retorna el atributo error.
     */
    public String getError() {
        return this.error;
    }


    /**
     * M�todo modificador del atributo error.
     * @param error El dato para modificar el atributo error.
     */
    public void setError(String error) {
        this.error = error;
    }


    /**
     * M�todo accesor del atributo fechaFinal.
     * @return Retorna el atributo fechaFinal.
     */
    public Date getFechaFinal() {
        return this.fechaFinal;
    }


    /**
     * M�todo modificador del atributo fechaFinal.
     * @param fechaFinal El dato para modificar el atributo fechaFinal.
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }


    /**
     * M�todo accesor del atributo fechaInicio.
     * @return Retorna el atributo fechaInicio.
     */
    public Date getFechaInicio() {
        return this.fechaInicio;
    }


    /**
     * M�todo modificador del atributo fechaInicio.
     * @param fechaInicio El dato para modificar el atributo fechaInicio.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    /**
     * M�todo accesor del atributo mailerBo.
     * @return Retorna el atributo mailerBo.
     */
    public MailerBo getMailerBo() {
        return this.mailerBo;
    }


    /**
     * M�todo modificador del atributo mailerBo.
     * @param mailerBo El dato para modificar el atributo mailerBo.
     */
    public void setMailerBo(MailerBo mailerBo) {
        this.mailerBo = mailerBo;
    }


    /**
     * M�todo accesor del atributo tipoBitacora.
     * @return Retorna el atributo tipoBitacora.
     */
    public Integer getTipoBitacora() {
        return this.tipoBitacora;
    }


    /**
     * M�todo modificador del atributo tipoBitacora.
     * @param tipoBitacora El dato para modificar el atributo tipoBitacora.
     */
    public void setTipoBitacora(Integer tipoBitacora) {
        this.tipoBitacora = tipoBitacora;
    }


    /**
     * M�todo accesor del atributo tipoInterrupcion.
     * @return Retorna el atributo tipoInterrupcion.
     */
    public Integer getTipoInterrupcion() {
        return this.tipoInterrupcion;
    }


    /**
     * M�todo modificador del atributo tipoInterrupcion.
     * @param tipoInterrupcion El dato para modificar el atributo tipoInterrupcion.
     */
    public void setTipoInterrupcion(Integer tipoInterrupcion) {
        this.tipoInterrupcion = tipoInterrupcion;
    }



    /**
     * M�todo modificador del atributo circuitoBO.
     * @param circuitoBO El dato para modificar el atributo circuitoBO.
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }


    /**
     * M�todo modificador del atributo correoBO.
     * @param correoBO El dato para modificar el atributo correoBO.
     */
    public void setCorreoBO(CorreoBO correoBO) {
        this.correoBO = correoBO;
    }


    /**
     * M�todo modificador del atributo empleadoBO.
     * @param empleadoBO El dato para modificar el atributo empleadoBO.
     */
    public void setEmpleadoBO(EmpleadoBO empleadoBO) {
        this.empleadoBO = empleadoBO;
    }



    /**
     * M�todo modificador del atributo oficinaBO.
     * @param oficinaBO El dato para modificar el atributo oficinaBO.
     */
    public void setOficinaBO(OficinaBO oficinaBO) {
        this.oficinaBO = oficinaBO;
    }



    /**
     * M�todo modificador del atributo reporteBO.
     * @param reporteBO El dato para modificar el atributo reporteBO.
     */
    public void setReporteBO(ReporteBO reporteBO) {
        this.reporteBO = reporteBO;
    }


	@Override
	protected void resetController() {
		// TODO Ap�ndice de m�todo generado autom�ticamente
		
	}


	public UsuarioOficinaBO getUsuarioOficinaBO() {
		return usuarioOficinaBO;
	}


	public void setUsuarioOficinaBO(UsuarioOficinaBO usuarioOficinaBO) {
		this.usuarioOficinaBO = usuarioOficinaBO;
	}

	public Long getCedula() {
		return cedula;
	}

	public void setCedula(Long cedula) {
		this.cedula = cedula;
	}


}
