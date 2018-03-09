package cr.go.ice.interrupciones.web.controller;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.ibm.icu.text.SimpleDateFormat;
import com.vvs.jsf.AbstractFacesController;
import com.vvs.utilidades.Utilidades;

import net.sf.jasperreports.engine.JasperRunManager;

import cr.go.ice.interrupciones.BO.CircuitoBO;
import cr.go.ice.interrupciones.BO.ComportamientoADPIRBO;
import cr.go.ice.interrupciones.BO.RegionBO;
import cr.go.ice.interrupciones.BO.SubEstacionBO;
import cr.go.ice.interrupciones.BO.SubRegionBO;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.ComportamientoADPIR;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;

/**
 * <p>TODO <<Interface|Clase>> cr.go.ice.interrupciones.web.controller.InterrupcionParameComportamiADPIRController.java</p>
 * <p>Módulo (subsistema): TODO <<Sistema>></p>
 * <p>TODO (Descripción) La <<Interface|Clase>> <code>InterrupcionParameComportamiADPIRController.java</code>.</p>
 * <p>Fecha creación: 21/10/2010</p>
 * <p>Última actualización: 21/10/2010</p>
 * @author Vista Verde Tecnología (root)
 * @version 1.0
 */
public class InterrupcionParameComportamiADPIRController extends AbstractFacesController{

    private Integer anoInicio;    
    private Integer anoFinal;  
    private Integer nivelRed;
        
    private String fecha;
    private List nivelList;
    private List regionesList;
    private List subRegionesList;
    private List subEstacionesList;
    private List circuitoList;
    private List seccionList;
    

    private Integer region;
    private Integer subregion;
    private Integer subestacion;
    private Integer circuito;
    
    private Circuito circuitoSeleccionado;
    
    private RegionBO regionBO;
    private SubRegionBO subRegionBO;
    private SubEstacionBO subEstacionBO;
    private CircuitoBO circuitoBO;
    private ComportamientoADPIRBO comportamientoADPIRBO;
    
    private boolean mostrarGrafico;
    
    private static String colorBorde = "B7DEE8";
    private  static final String JasperPath = "/jasperReports/";
    private String strXML;

   public InterrupcionParameComportamiADPIRController(){
       reiniciarCampos();
       
   }
   
   /**
    * Metodo que reincia los atributos de la clase
    */
   public void reiniciarCampos(){        
       Calendar calendar = Calendar.getInstance();
       this.anoInicio = new Integer(calendar.get(Calendar.YEAR));
       this.anoFinal = new Integer(calendar.get(Calendar.YEAR));
       
       this.region = new Integer(-1);
       this.subregion = new Integer(-1);
       this.subestacion = new Integer(-1);
       this.circuito = new Integer(-1);
       
       this.circuitoSeleccionado = new Circuito();
       
       SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
       this.fecha = sdf.format(new Date());
       
       this.mostrarGrafico = false;
   }        
   
   public String getInit(){
	   FacesContext context = FacesContext.getCurrentInstance();
	   Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
       if(limpiar != null)
           reiniciarCampos();
       return "success";
   }
   
   /**
    * Precarga la oficina del usuario de la aplicación, si el mismo es un usuario CLOR
    * @param context
    */
//   public void load(FacesContext context){
//       Object limpiar = context.getExternalContext().getRequestParameterMap().get("load");
//       if(limpiar != null)
//           reiniciarCampos();
//                
//   }
       
   private boolean validarOpcionesParametros(){
       boolean correcto = true;
       FacesContext context = FacesContext.getCurrentInstance();
       Calendar calendar = Calendar.getInstance();
       
       int anoActual = calendar.get(Calendar.YEAR);       
       if(this.anoInicio.intValue() > anoActual){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año Inicial no puede ser mayor al actual."));
           correcto=false;
       }
       
       if(this.anoInicio.intValue() < 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año Inicial no puede ser menor que cero."));
           correcto=false;
       }
       
       if(this.anoFinal.intValue() > anoActual){
         
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año Final no puede ser mayor al actual."));
           correcto=false;
       }
       
       if(this.anoFinal.intValue() < 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año Final no puede ser menor que cero."));
           correcto=false;
       }
       
       if(this.anoFinal.intValue() < this.anoInicio.intValue()){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "El año Final no puede ser menor que el año Inicial."));
           correcto=false;
       }
       

       if(this.region.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la región."));
           correcto=false;
       }
  
       if(this.subregion.intValue() <= 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subregión."));
           correcto=false;
       }
       
       if(this.subestacion.intValue() <= 0){
           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar la subestación."));
           correcto=false;
       }
       
       if(this.circuito.intValue() <= 0){
          FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Debe seleccionar el circuito."));
           correcto=false;
       }       
      
       return correcto;
   }
   
   public String graficar(){
         String resultado = "failure";
         if(this.validarOpcionesParametros()){
             List comportamientos = this.comportamientoADPIRBO.getComportamientoADPIR(this.anoInicio, this.anoFinal, this.region, this.subregion, this.subestacion, this.circuito);
             
             if(!comportamientos.isEmpty()){
                 
                 this.circuitoSeleccionado = this.circuitoBO.buscar(this.subestacion, this.circuito);
                 
                 String titulo = "Interrupciones e Indices de Calidad";
                 String subtitulo = "Comportamiento de ADPIR";
                 strXML = this.getDataXML(comportamientos, titulo, subtitulo);
                 resultado = "success";
             }
             else{
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No existen datos para generar el reporte."));
             }
         }

   
       return resultado;
   }
   
   private String getDataXML(List comportamientos, String titulo, String subtitulo){
       


           StringBuffer xml = new StringBuffer( "<chart caption='" + titulo.toString() + "'subcaption='" + subtitulo.toString() + "' numdivlines='9' lineThickness='2' yAxisName='Horas' bgColor='FFFFFF'" +
           "logoScale='40' logoPosition='TR' animation='1' formatNumberScale='0'  numVDivLines='24' showValues='1'  " +
           "decimalSeparator=',' thousandSeparator='.' labelDisplay='ROTATE' slantLabels='0'  anchorRadius='2' " +
           " exportEnabled=\'1\' exportHandler=\'./FCExporter.jsp\' exportAtClient=\'0\' exportAction=\'save\' exportFileName=\'graficoComportamientoADPIR\' "+
           "anchorBgAlpha='17' numVisiblePlot='33' divLineThickness='0' showAlternateVGridColor='1' anchorAlpha='34' animation='1' limitsDecimalPrecision='0' " +
           "divLineDecimalPrecision='1' xAxisName='Meses' yAxisName='Mw' showBorder='1' borderThickness='4' borderColor='"+InterrupcionParameComportamiADPIRController.colorBorde+"' >"); 
           
           
           xml.append("<categories> "+
                   "<category label='Ene'/> "+
                   "<category label='Feb'/> "+
                   "<category label='Mar'/> "+
                   "<category label='Apr'/> "+
                   "<category label='May'/> "+
                   "<category label='Jun'/> "+
                   "<category label='Jul'/> "+
                   "<category label='Ago'/> "+
                   "<category label='Set'/> "+
                   "<category label='Oct'/> "+
                   "<category label='Nov'/> "+
                   "<category label='Dic'/> "+
                "</categories>");
           
           StringBuffer buf = new StringBuffer();
           
           Integer ano = null;
           
           for(int j=0;j < comportamientos.size(); j++){
               
               ComportamientoADPIR comportamientoADPIR = (ComportamientoADPIR) comportamientos.get(j);
               buf = new StringBuffer();
               xml.append("<dataset seriesName='"+comportamientoADPIR.getId().getAnno().toString()+"' anchorRadius='3'> ");
               
               ano = comportamientoADPIR.getId().getAnno();
               
               int i=j;
               while(i < comportamientos.size()){

                   ComportamientoADPIR comporta = (ComportamientoADPIR) comportamientos.get(i);
                   
                   if(comporta.getId().getAnno().equals(ano)){
                       buf.append("<set value='" + comporta.getADPIR().toString() + "'/> ");
                       
                       i++;
                       j=i;
                   }else{
                	   j=i-1;
                       break;
                   }
               }
               
               xml.append(buf.toString());
               xml.append("</dataset> ");
           }
           
        xml = new StringBuffer(this.estilosGraficos(xml.toString()));
        xml.append("</chart>");            
        return xml.toString();
    }
    
    public String estilosGraficos(String xml){       
        xml+="<styles>" +
           "<definition>" +
               "<style name='EstiloTitulo' type='font' face='Arial' size='18' color='1F497D' bold='1' bgColor='FFFFFF' />"+
               "<style name='Categoria' type='font' size='12' bold='1' />"+
           "</definition>" +
           "<application>"+
              "<apply toObject='caption' styles='EstiloTitulo' />"+ 
              "<apply toObject='YAXISNAME' styles='Categoria' />"+
              "<apply toObject='XAXISNAME' styles='Categoria' />"+
           "</application>"+
           "</styles>";
        return xml;
    }
    
    public String regresar(){
        return "success";
    }
    
    @SuppressWarnings("unchecked")
    public String imprimir(){
        try {
           DataSource data = (DataSource) Utilidades.getContextVariable("jdbc/interrupciones2DS");
           Connection conn = data.getConnection();
                         
           HashMap parametros = new HashMap();   
           FacesContext context = FacesContext.getCurrentInstance();                       
           HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
           
           String grafico = "/temp/graficoComportamientoADPIR.jpg";
                       
           String ServletJasperPath = "/jasperReports/";
           String nombreArchivo = "reporte_grafico_comportamiento_ADPIR.jasper";
           
           Circuito circuit = this.circuitoBO.buscar(this.subestacion, this.circuito);
           
           parametros.put("grafico",grafico);
           parametros.put("fecha",this.fecha);
           parametros.put("circuito",circuit.getNombreCircuito());
           parametros.put("añoInicial",this.anoInicio.toString());
           parametros.put("añoFinal",this.anoFinal.toString());

           
           SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           this.fecha = sdf.format(new Date());
           String nombre = "RepCompADPIR" + this.fecha + ".pdf";
           
           response.setContentType("application/pdf");
           
           InputStream reportStream = this.getFacesContext().getExternalContext().getResourceAsStream(ServletJasperPath + nombreArchivo);
           response.setHeader("Content-disposition","attachment;filename=" + nombre);
             
           JasperRunManager.runReportToPdfStream(reportStream, response.getOutputStream(), parametros, conn); // se llena el reporte
           
           conn.close();
           
           response.getOutputStream().flush();

//           if(!FacesContext.getCurrentInstance().getResponseComplete() ) {
//               System.out.println("force responsecomplete");
//               FacesContext.getCurrentInstance().responseComplete();
//           }    
           Integer formato = UtilidadesFaces.FORMATO_PDF;
           if (this.runReport(JasperPath + nombreArchivo, nombre,parametros,this.getConnection(),formato,UtilidadesFaces.getCurrentUserId())){
            
               FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Información!", "Reporte Ejecutado."));
               return  "success";      
               
           }else{
              FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Reporte Error."));
           }
       }catch(Exception e) {
           System.err.println( e);
       }
       return "success";
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
    
   /** Metodo accesor de circuitos.
    * @return Retorna el circuitos.
    */
   public List getCircuitos() {
       if(this.circuitoList == null){
        initCircuitos(this.subestacion);
       }
       return this.circuitoList;
   }
   
   private void initCircuitos(Integer subestacion) {
      
       this.circuitoList = new ArrayList();
       this.circuitoList.add(new SelectItem(new Integer(-1), "Todos"));
       if(subestacion != null){
        List circuitos = this.circuitoBO.getCircuitos(subestacion);
           
           if(circuitos != null && !circuitos.isEmpty()){
               for(int i = 0; i < circuitos.size(); i++){
                   Circuito circuito = (Circuito) circuitos.get(i);
                   SelectItem item = new SelectItem();
                   item.setValue(circuito.getCircuitoID().getCircuito());
                   item.setLabel(circuito.getCircuitoID().getCircuito() + " - " + circuito.getNombreCircuito());
                   this.circuitoList.add(item);
               }
           }
       }
       
       
   }
   
   /**
    * Metodo accesor de regiones.
    * @return Retorna el regiones.
    */
   public List getRegiones() {   
    if(this.regionesList ==null){
        initRegiones();
    }
       return this.regionesList;
   }
   
   private void initRegiones() {   
    System.out.println("Region " + this.region);
       List regiones = null;
       regiones = this.regionBO.getRegiones();
       this.regionesList = new ArrayList();
       this.regionesList.add(new SelectItem(new Integer(-1), "Todas"));
       if(regiones != null && !regiones.isEmpty()){
           for(int i = 0; i < regiones.size(); i++){
               Region region = (Region) regiones.get(i);
               SelectItem item = new SelectItem();
               item.setValue(region.getRegion());
               item.setLabel(region.getRegion() + " - " + region.getNombreRegion());
               this.regionesList.add(item);
           }
       }        
       
   }

   /**
    * Metodo accesor de subEstaciones.
    * @return Retorna el subEstaciones.
    */
   public List getSubEstaciones() {
    if(this.subEstacionesList == null){
        initSubEstaciones(this.region, this.subregion);
    }
       return this.subEstacionesList;
   }
   
   private void initSubEstaciones(Integer region, Integer subregion) {
    System.out.println("Sub " + this.subestacion);
        
       this.subEstacionesList = new ArrayList();
       this.subEstacionesList.add(new SelectItem(new Integer(-1), "Todas"));
       if(subregion !=null && region !=null ){
        List subEstaciones = this.subEstacionBO.getSubEstaciones(region, subregion);
           
           if(subEstaciones != null && !subEstaciones.isEmpty()){
               for(int i = 0; i < subEstaciones.size(); i++){
                   SubEstacion subEstacion = (SubEstacion) subEstaciones.get(i);
                   SelectItem item = new SelectItem();
                   item.setValue(subEstacion.getCodigoSubEstacion());
                   item.setLabel(subEstacion.getCodigoSubEstacion() + " - " + subEstacion.getNombreSubEstacion());
                   this.subEstacionesList.add(item);
               }
           }
       }
       
        
   }
   /**
    * Metodo accesor de subRegiones.
    * @return Retorna el subRegiones.
    */
   public List getSubRegiones() {   
    if(this.subRegionesList == null){
        initSubRegiones(this.region);
    }
      
       return this.subRegionesList;
   }    
   
   private void initSubRegiones(Integer region) {   
      
       this.subRegionesList = new ArrayList();
       this.subRegionesList.add(new SelectItem(new Integer(-1), "Todas"));
       if(region !=null){
        List subRegiones = this.subRegionBO.getSubRegiones(region);
           
           if(subRegiones!= null && !subRegiones.isEmpty()){
               for(int i = 0; i < subRegiones.size(); i++){
                   SubRegion subRegion = (SubRegion) subRegiones.get(i);
                   SelectItem item = new SelectItem();
                   item.setValue(subRegion.getSubRegionID().getSubRegion());
                   item.setLabel(subRegion.getSubRegionID().getSubRegion() + " - " + subRegion.getNombreSubRegion());
                   this.subRegionesList.add(item);
               }
           }
       }
      
   }    
    
   /** Retorna una lista de select item de los diferentes niveles de red
    * @return Lista de niveles de red
    */    
   public List getListaNivelesRed(){
       if(this.nivelList==null){
           this.nivelList = new ArrayList();      
//           this.nivelList.add(new SelectItem(new Integer(0),"Región"));
//           this.nivelList.add(new SelectItem(new Integer(1),"Subregión"));
//           this.nivelList.add(new SelectItem(new Integer(2),"Subestación"));
           this.nivelList.add(new SelectItem(new Integer(3),"Circuito"));
//           this.nivelList.add(new SelectItem(new Integer(4),"Sección"));
//       this.region = new Integer(-1);
       }
       return this.nivelList;
   }
   
   /**
    * Asigna el codigo de nivel de red de acuerdo a el cual se habilitan o deshabilitan los combos de red
    * @param v
    */ 
   public void listenerNivelRed(){
       this.region = new Integer(-1);
       this.subregion = new Integer(-1);
       this.subestacion = new Integer(-1);
       this.circuito = new Integer(-1);
      
   } 
   
   /**
    * Método accesor del atributo circuitoSeleccionado.
    * @return Retorna el atributo circuitoSeleccionado.
    */
   public String getCircuitoSeleccionadoMostrar() {
       return this.circuitoSeleccionado.getNombreCircuito();
   }  
   
   /**
    * Comment for listenerRegion
    * @param v
    */
   public void listenerRegion(){
       initSubRegiones(region);
       initSubEstaciones(null, null);
       initCircuitos(null);
   }
   
   /**
    * Comment for listenerSubregion
    * @param v
    */
   public void listenerSubregion(){

       initSubEstaciones(this.region, subregion);
       initCircuitos(null);
   }    
   
   /**
    * Comment for listenerSubestacion
    * @param v
    */
   public void listenerSubestacion(){
       initCircuitos(subestacion);
   }   
   
   /**
    * Comment for listenerCircuito
    * @param v
    */
   @SuppressWarnings("unused")
public void listenerCircuito(){
       Integer circuito = Integer.valueOf(0);
   }

    /**
     * Método accesor del atributo anoFinal.
     * @return Retorna el atributo anoFinal.
     */
    public Integer getAnoFinal() {
        return this.anoFinal;
    }
    
    /**
     * Método modificador del atributo anoFinal.
     * @param anoFinal El dato para modificar el atributo anoFinal.
     */
    public void setAnoFinal(Integer anoFinal) {
        this.anoFinal = anoFinal;
    }
    
    /**
     * Método accesor del atributo anoInicio.
     * @return Retorna el atributo anoInicio.
     */
    public Integer getAnoInicio() {
        return this.anoInicio;
    }
    
    /**
     * Método modificador del atributo anoInicio.
     * @param anoInicio El dato para modificar el atributo anoInicio.
     */
    public void setAnoInicio(Integer anoInicio) {
        this.anoInicio = anoInicio;
    }
    
    /**
     * Método accesor del atributo circuito.
     * @return Retorna el atributo circuito.
     */
    public Integer getCircuito() {
        return this.circuito;
    }
    
    /**
     * Método modificador del atributo circuito.
     * @param circuito El dato para modificar el atributo circuito.
     */
    public void setCircuito(Integer circuito) {
        this.circuito = circuito;
    }
    
    /**
     * Método accesor del atributo circuitoBO.
     * @return Retorna el atributo circuitoBO.
     */
    public CircuitoBO getCircuitoBO() {
        return this.circuitoBO;
    }
    
    /**
     * Método modificador del atributo circuitoBO.
     * @param circuitoBO El dato para modificar el atributo circuitoBO.
     */
    public void setCircuitoBO(CircuitoBO circuitoBO) {
        this.circuitoBO = circuitoBO;
    }
    
    /**
     * Método accesor del atributo circuitoList.
     * @return Retorna el atributo circuitoList.
     */
    public List getCircuitoList() {
        return this.circuitoList;
    }
    
    /**
     * Método modificador del atributo circuitoList.
     * @param circuitoList El dato para modificar el atributo circuitoList.
     */
    public void setCircuitoList(List circuitoList) {
        this.circuitoList = circuitoList;
    }
    /**
     * Método accesor del atributo nivelList.
     * @return Retorna el atributo nivelList.
     */
    public List getNivelList() {
        return this.nivelList;
    }
    
    /**
     * Método modificador del atributo nivelList.
     * @param nivelList El dato para modificar el atributo nivelList.
     */
    public void setNivelList(List nivelList) {
        this.nivelList = nivelList;
    }
    
    /**
     * Método accesor del atributo nivelRed.
     * @return Retorna el atributo nivelRed.
     */
    public Integer getNivelRed() {
        return this.nivelRed;
    }
    
    /**
     * Método modificador del atributo nivelRed.
     * @param nivelRed El dato para modificar el atributo nivelRed.
     */
    public void setNivelRed(Integer nivelRed) {
        this.nivelRed = nivelRed;
    }
    
    /**
     * Método accesor del atributo region.
     * @return Retorna el atributo region.
     */
    public Integer getRegion() {
        return this.region;
    }
    
    /**
     * Método modificador del atributo region.
     * @param region El dato para modificar el atributo region.
     */
    public void setRegion(Integer region) {
        this.region = region;
    }
    
    /**
     * Método accesor del atributo regionBO.
     * @return Retorna el atributo regionBO.
     */
    public RegionBO getRegionBO() {
        return this.regionBO;
    }
    
    /**
     * Método modificador del atributo regionBO.
     * @param regionBO El dato para modificar el atributo regionBO.
     */
    public void setRegionBO(RegionBO regionBO) {
        this.regionBO = regionBO;
    }
    
    /**
     * Método accesor del atributo regionesList.
     * @return Retorna el atributo regionesList.
     */
    public List getRegionesList() {
        return this.regionesList;
    }
    
    /**
     * Método modificador del atributo regionesList.
     * @param regionesList El dato para modificar el atributo regionesList.
     */
    public void setRegionesList(List regionesList) {
        this.regionesList = regionesList;
    }
    
    /**
     * Método accesor del atributo seccionList.
     * @return Retorna el atributo seccionList.
     */
    public List getSeccionList() {
        return this.seccionList;
    }
    
    /**
     * Método modificador del atributo seccionList.
     * @param seccionList El dato para modificar el atributo seccionList.
     */
    public void setSeccionList(List seccionList) {
        this.seccionList = seccionList;
    }
    
    /**
     * Método accesor del atributo subestacion.
     * @return Retorna el atributo subestacion.
     */
    public Integer getSubestacion() {
        return this.subestacion;
    }
    
    /**
     * Método modificador del atributo subestacion.
     * @param subestacion El dato para modificar el atributo subestacion.
     */
    public void setSubestacion(Integer subestacion) {
        this.subestacion = subestacion;
    }
    
    /**
     * Método accesor del atributo subEstacionBO.
     * @return Retorna el atributo subEstacionBO.
     */
    public SubEstacionBO getSubEstacionBO() {
        return this.subEstacionBO;
    }
    
    /**
     * Método modificador del atributo subEstacionBO.
     * @param subEstacionBO El dato para modificar el atributo subEstacionBO.
     */
    public void setSubEstacionBO(SubEstacionBO subEstacionBO) {
        this.subEstacionBO = subEstacionBO;
    }
    
    /**
     * Método accesor del atributo subEstacionesList.
     * @return Retorna el atributo subEstacionesList.
     */
    public List getSubEstacionesList() {
        return this.subEstacionesList;
    }
    
    /**
     * Método modificador del atributo subEstacionesList.
     * @param subEstacionesList El dato para modificar el atributo subEstacionesList.
     */
    public void setSubEstacionesList(List subEstacionesList) {
        this.subEstacionesList = subEstacionesList;
    }
    
    /**
     * Método accesor del atributo subregion.
     * @return Retorna el atributo subregion.
     */
    public Integer getSubregion() {
        return this.subregion;
    }
    
    /**
     * Método modificador del atributo subregion.
     * @param subregion El dato para modificar el atributo subregion.
     */
    public void setSubregion(Integer subregion) {
        this.subregion = subregion;
    }
    
    /**
     * Método accesor del atributo subRegionBO.
     * @return Retorna el atributo subRegionBO.
     */
    public SubRegionBO getSubRegionBO() {
        return this.subRegionBO;
    }
    
    /**
     * Método modificador del atributo subRegionBO.
     * @param subRegionBO El dato para modificar el atributo subRegionBO.
     */
    public void setSubRegionBO(SubRegionBO subRegionBO) {
        this.subRegionBO = subRegionBO;
    }
    
    /**
     * Método accesor del atributo subRegionesList.
     * @return Retorna el atributo subRegionesList.
     */
    public List getSubRegionesList() {
        return this.subRegionesList;
    }
    
    /**
     * Método modificador del atributo subRegionesList.
     * @param subRegionesList El dato para modificar el atributo subRegionesList.
     */
    public void setSubRegionesList(List subRegionesList) {
        this.subRegionesList = subRegionesList;
    }

    /**
     * Método accesor del atributo comportamientoADPIRBO.
     * @return Retorna el atributo comportamientoADPIRBO.
     */
    public ComportamientoADPIRBO getComportamientoADPIRBO() {
        return this.comportamientoADPIRBO;
    }

    /**
     * Método modificador del atributo comportamientoADPIRBO.
     * @param comportamientoADPIRBO El dato para modificar el atributo comportamientoADPIRBO.
     */
    public void setComportamientoADPIRBO(ComportamientoADPIRBO comportamientoADPIRBO) {
        this.comportamientoADPIRBO = comportamientoADPIRBO;
    }

    /**
     * Método accesor del atributo mostrarGrafico.
     * @return Retorna el atributo mostrarGrafico.
     */
    public boolean isMostrarGrafico() {
        return this.mostrarGrafico;
    }

    /**
     * Método modificador del atributo mostrarGrafico.
     * @param mostrarGrafico El dato para modificar el atributo mostrarGrafico.
     */
    public void setMostrarGrafico(boolean mostrarGrafico) {
        this.mostrarGrafico = mostrarGrafico;
    }

    /**
     * @see com.vvs.jsf.AbstractFacesController#getPropertyFieldName(java.lang.String)
     */
    protected String getPropertyFieldName(String property) {
        return null;
    }

    /**
     * Método accesor del atributo fecha.
     * @return Retorna el atributo fecha.
     */
    public String getFecha() {
        return this.fecha;
    }

    /**
     * Método modificador del atributo fecha.
     * @param fecha El dato para modificar el atributo fecha.
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * Método accesor del atributo circuitoSeleccionado.
     * @return Retorna el atributo circuitoSeleccionado.
     */
    public Circuito getCircuitoSeleccionado() {
        return this.circuitoSeleccionado;
    }

    /**
     * Método modificador del atributo circuitoSeleccionado.
     * @param circuitoSeleccionado El dato para modificar el atributo circuitoSeleccionado.
     */
    public void setCircuitoSeleccionado(Circuito circuitoSeleccionado) {
        this.circuitoSeleccionado = circuitoSeleccionado;
    }

	@Override
	protected void resetController() {
		// TODO Apéndice de método generado automáticamente
		
	}

	/**
	 * Retorna el valor del atributo strXML.
	 * @return El valor del atributo strXML.
	 */
	public String getStrXML() {
		return strXML;
	}

	/**
	 * Establece el valor del atributo strXML.
	 * @param strXML Valor del atributo strXML a establecer.
	 */
	public void setStrXML(String strXML) {
		this.strXML = strXML;
	}   
}
