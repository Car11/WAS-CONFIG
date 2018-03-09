package cr.go.ice.interrupciones.servlets;

import java.io.*;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;



import com.vvs.jasperreports.JasperReportVVS;
import com.vvs.utilidades.Fechas;
import com.vvs.utilidades.Utilidades;

import cr.go.ice.interrupciones.domain.Animal;
import cr.go.ice.interrupciones.domain.Causa;
import cr.go.ice.interrupciones.domain.Circuito;
import cr.go.ice.interrupciones.domain.Dano;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.domain.Interrupcion;
import cr.go.ice.interrupciones.domain.Material;
import cr.go.ice.interrupciones.domain.Oficina;
import cr.go.ice.interrupciones.domain.Proteccion;
import cr.go.ice.interrupciones.domain.Region;
import cr.go.ice.interrupciones.domain.Reporte;
import cr.go.ice.interrupciones.domain.Seccion;
import cr.go.ice.interrupciones.domain.SubEstacion;
import cr.go.ice.interrupciones.domain.SubRegion;
import cr.go.ice.interrupciones.domain.TipoVoltaje;
import cr.go.ice.interrupciones.domain.Vehiculo;
import cr.go.ice.interrupciones.utils.UtilidadesFaces;
import cr.go.ice.interrupciones.web.controller.ReporteFusibleInstRetController;
import cr.go.ice.interrupciones.web.controller.ReporteTrafoInstRetController;
import cr.go.ice.obras.domain.Agencia;
import cr.go.ice.sace.sacecon.domain.Pueblo;

/**
 * <p>Clase cr.go.ice.interrupciones.servlets.ServletReporte.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ServletReporte.java</code> Servlet para la creación de reportes.</p>
 * <p>Fecha creación: 02/06/2007</p>
 * <p>Ultima actualización: 02/06/2007</p>
 * @author Vista Verde Soft (David)
 * @version 1.1
 */
public class ServletReporte extends HttpServlet {
    
    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -3146537886495170683L;
    /**atributo <code>reporteMenorCincoMin</code>*/
    public static final int reporteMenorCincoMin = 1;
    /**atributo <code>reporteMayorCincoMin</code>*/
    public static final int reporteMayorCincoMin = 2;
    /**atributo <code>reportePorCircuito</code>*/
    public static final int reportePorCircuito = 3;
    /**atributo <code>reportePorPeriodo</code>*/
    public static final int reportePorPeriodo = 4;
    /**atributo <code>reportePorCausa</code>*/
    public static final int reportePorCausa = 5;    
    /**atributo <code>reportePorCausa</code>*/
    public static final int reporteIndicadorGlobal = 6;    
    /**atributo <code>reporteIndicadorCausa</code>*/
    public static final int reporteIndicadorCausa = 7;   
    /**atributo <code>reporteIndicadorTrifasico</code>*/
    public static final int reporteIndicadorTrifasico = 8;   
    /**atributo <code>reporteIndicadorPropioNoPropio</code>*/
    public static final int reporteIndicadorPropioNoPropio = 9;   
    /**atributo <code>reporteDiferenciaTiempos</code>*/
    public static final int reporteDiferenciaTiempos = 10;     
    /**atributo <code>reporteProteccionesCircuito</code>*/
    public static final int reporteProteccionesCircuito = 11;      
    /**atributo <code>reporteFusiblesInstRet</code>*/
    public static final int reporteFusiblesInstRet = 12;   
    /**atributo <code>reporteFusiblesInstRetSeccion</code>*/
    public static final int reporteFusiblesInstRetSeccion = 13;   
    /**atributo <code>reporteIndiceGlobalSeccion</code>*/
    public static final int reporteIndiceGlobalSeccion = 14;    
    /**atributo <code>reporteIndiceMesAcum</code>*/
    public static final int reporteIndiceMesAcum = 15;        
    /**atributo <code>reporteTiposCausa</code>*/
    public static final int reporteTiposCausa = 16;        
    /**atributo <code>reporteTiposDano</code>*/
    public static final int reporteTiposDano = 17; 
    /**atributo <code>reporteTiposProteccion</code>*/
    public static final int reporteTiposProteccion = 18; 
    /**atributo <code>reporteTiposProteccion</code>*/
    public static final int reporteTiposOficina = 19;  
    /**atributo <code>reporteTiposAnimal</code>*/
    public static final int reporteTiposAnimal = 20;  
    /**atributo <code>reporteTiposCorreo</code>*/
    public static final int reporteTiposCorreo = 21;   
    /**atributo <code>reporteTiposProvolma</code>*/
    public static final int reporteTiposProvolma = 22;   
    /**atributo <code>reporteTiposMaterial</code>*/
    public static final int reporteTiposMaterial = 23;      
    /**atributo <code>reporteTiposVoltaje</code>*/
    public static final int reporteTiposVoltaje = 24;   
    /**atributo <code>reporteInterrupcionesPorAnimales</code>*/
    public static final int reporteInterrupcionesPorAnimales = 25;      
    /**atributo <code>reporteTrafosInstRetSeccion</code>*/
    public static final int reporteTrafosInstRetSeccion = 26;
    /**atributo <code>reporteTrafosInstRet</code>*/
    public static final int reporteTrafosInstRet = 27;    
    /** Atributo <code>reportePorCircuitoMenorCincoMin</code> */
    public static final int reportePorCircuitoMenorCincoMin = 28;
    
    public static final int reporteClientesAfectadosPorEvento = 29;
    public static final int reporteMaterialPostes = 30;
    public static final int reporteTiempoVehiculo = 31;
	
	public static final int reporteAuditoria = 32;
	
	public static final int reporteIndiceContinuidadServicio = 33;
	private static Logger theLogger =
		 Logger.getLogger(ServletReporte.class.getName());
    /**
     * <code>ServletImagepath</code> Servlet Image path
     */
    public String servletImagepath;
    /**
     * <code>ServletJasperPath</code> Servlet Jasper Path
     */
    public String servletJasperPath;	
    
    public Integer reporteTipoPorCircuito;
    //private static DataSource ds;
    
	
    /**
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doAll(request, response);
    }
    /**
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doAll(request, response);
    }
    
    /**
     * 
     * @see javax.servlet.GenericServlet#init()
     */
    public void init(){
        servletImagepath = this.getServletContext().getRealPath("images"+File.separatorChar);
        servletJasperPath = "/jasperReports/";
        //ds = (DataSource) Utilidades.getContextVariable("jdbc/interrupciones2DS");
    }    
    
    /**
     * Metodo doAll, para procesar las peticiones al servlet sea por post o get
     * @param request El objeto HttpServletRequest que encapsula la peticion al servlet
     * @param response El objeto HttpServletResponse que encapsula la respuesta del servlet
     * @throws ServletException
     * @throws IOException
     */
    public void doAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{        
        
        //Connection connection=null;
        try {
            //Obtener conexion a base de datos
            //connection = ds.getConnection();
            //Ejecutar reporte solicitado
            runReport(request,response);
        }catch(Exception e){
            //Reporte termino anormalmente por alguna Exception           
            e.printStackTrace();
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("Error500.jsf").forward(request,response);
        } finally {
            //Cerrar conexion a base de datos
        }
        
    }
    
    
    /**
     * Método runReport
     * Ejecuta el reporte solicitado
     * @param request
     * @param response
     * @param connection
     * @throws ServletException
     * @throws IOException
     */
    public void runReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        
       try{                             
           response.reset();
           HashMap<String, Object> parametrosReporte = new HashMap<String, Object>();
           InputStream reportStream = null;
           String nombreArchivo = "";
           String nombreArchivoJasper = "";
           String formato = "";
           this.reporteTipoPorCircuito=Integer.valueOf(0);
           boolean usarConexion = true;
           boolean consulta = false;
           JRDataSource jrDataSource = null;
           
           
            FacesContext ctx = FacesContext.getCurrentInstance();
            Map sessionMap = ctx.getExternalContext().getSessionMap();
            Integer tipoReporte = (Integer)sessionMap.remove("tipoReporte");
            
            theLogger.finest("TIPO DE REPORTE: "+tipoReporte);
            //REPORTE DE INTERRUPCION - 5 MIN
            if(tipoReporte.intValue() == ServletReporte.reporteMenorCincoMin){
                String consecutivoInterrupcion = (String)sessionMap.remove("consecutivoInterrupcion");
                String valoresLlave[] = consecutivoInterrupcion.split("-");
                Integer ano = new Integer(valoresLlave[0].trim());
                Integer codigoOficina = new Integer(valoresLlave[1].trim());
                Long numeroInterrupcion = new Long(valoresLlave[2].trim());
                
                formato = "PDF";
                nombreArchivo = "IntMen5-" + ano + "-" + codigoOficina + "-" + numeroInterrupcion + ".pdf";

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE
                
                parametrosReporte.put("DIR_IMAGENES",servletImagepath);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);                               
                
                //CARGA EL JASPER COMPILADO
                nombreArchivoJasper = "SigeItrRepMenorCincoMin.jasper";
                                     
                //OBTIENE BEANS
                Reporte reporte = (Reporte)sessionMap.remove("reporte");
                List<Serializable> interrupciones = new ArrayList<Serializable>();
                interrupciones.add(reporte);
                List cuadrillas = (List)sessionMap.remove("cuadrillas");
                Vehiculo vehiculo = (Vehiculo)sessionMap.remove("vehiculo");
                List<Serializable> vehiculos = new ArrayList<Serializable>();
                vehiculos.add(vehiculo);
                
                Region reg = (Region)sessionMap.remove("region");
                SubRegion subreg = (SubRegion)sessionMap.remove("subregion");
                SubEstacion sub = (SubEstacion)sessionMap.remove("subEstacion");
                Circuito cir = (Circuito)sessionMap.remove("circuito");
                Seccion sec = (Seccion)sessionMap.remove("seccion");
                Empleado emp = (Empleado)sessionMap.remove("empleado");
                Oficina oficina = (Oficina)sessionMap.remove("oficina");
                Pueblo puebloFalla = (Pueblo)sessionMap.remove("puebloFalla");
                Pueblo puebloEquipo = (Pueblo)sessionMap.remove("puebloEquipo");
                Agencia agencia = (Agencia)sessionMap.remove("agencia");
                TipoVoltaje tipoVoltaje = (TipoVoltaje)sessionMap.remove("tipoVoltaje");
                Proteccion proteccion = (Proteccion)sessionMap.remove("proteccion");
                Material material = (Material)sessionMap.remove("material");
                Dano dano = (Dano)sessionMap.remove("dano");
                Causa causa1 = (Causa)sessionMap.remove("causa1");
                Causa causa2 = (Causa)sessionMap.remove("causa2");
                Animal animal = (Animal)sessionMap.remove("animal");
                
                //PASAMOS LOS PARAMETROS BEAN
                parametrosReporte.put("CONSECUTIVO",ano + "-" + codigoOficina + "-" + numeroInterrupcion);
                
                parametrosReporte.put("CUADRILLA", new JRBeanCollectionDataSource(cuadrillas));
                if(vehiculo != null)
                    parametrosReporte.put("VEHICULO",new JRBeanCollectionDataSource(vehiculos));
                else
                    parametrosReporte.put("VEHICULO",new JRBeanCollectionDataSource(new ArrayList()));
                parametrosReporte.put("ANO",ano);
                parametrosReporte.put("OFICINA",codigoOficina);
                parametrosReporte.put("NUMERO_REPORTE",numeroInterrupcion);
                
                parametrosReporte.put("NOMBRE_REGION",reg.getRegion() + " - " + reg.getNombreRegion());
                parametrosReporte.put("NOMBRE_SUBREGION",subreg.getSubRegionID().getSubRegion() + " - " + subreg.getNombreSubRegion());
                
                parametrosReporte.put("NOMBRE_SUBESTACION",sub.getCodigoSubEstacion() + " - " + sub.getNombreSubEstacion());                
                parametrosReporte.put("NOMBRE_CIRCUITO",cir.getCircuitoID().getCircuito() + " - " + cir.getNombreCircuito());
                parametrosReporte.put("NOMBRE_SECCION",sec.getSeccionID().getSeccion() + " - " + sec.getNombreSeccion());
                parametrosReporte.put("NOMBRE_EMPLEADO",emp.getNombreCompleto());
                parametrosReporte.put("NOMBRE_OFICINA",oficina.getNombreOficina());
                
                
                if(puebloFalla != null)
                    parametrosReporte.put("NOMBRE_PUEBLOFALLA",puebloFalla.getNombre());
                else
                    parametrosReporte.put("NOMBRE_PUEBLOFALLA","DESCONOCIDO");
                if(puebloEquipo != null)
                    parametrosReporte.put("NOMBRE_PUEBLOEQUIPO",puebloEquipo.getNombre());
                else
                    parametrosReporte.put("NOMBRE_PUEBLOEQUIPO","DESCONOCIDO");                
                
                if(agencia != null)
                    parametrosReporte.put("NOMBRE_AGENCIA",agencia.getNombreAgencia());
                else
                    parametrosReporte.put("NOMBRE_AGENCIA","NINGUNA");
                parametrosReporte.put("NOMBRE_TIPOVOLTAJE",tipoVoltaje.getCodigoDescripcion());
                parametrosReporte.put("NOMBRE_PROTECCION",proteccion.getCodigoProteccion() + " - " + proteccion.getNombreProteccion());
                parametrosReporte.put("NOMBRE_MATERIAL",material.getCodigoMaterial() + " - " + material.getNombreMaterial());
                parametrosReporte.put("NOMBRE_DANO",dano.getCodigoDano() + " - " + dano.getNombreDano());
                parametrosReporte.put("NOMBRE_CAUSA1",causa1.getCodigoCausa() + " - " + causa1.getNombreCausa());
                if(causa2 != null)
                    parametrosReporte.put("NOMBRE_CAUSA2",causa2.getCodigoCausa() + " - " + causa2.getNombreCausa());
                else
                    parametrosReporte.put("NOMBRE_CAUSA2","NINGUNA");
                if(animal != null)
                    parametrosReporte.put("NOMBRE_ANIMAL",animal.getNombreAnimal());
                else
                    parametrosReporte.put("NOMBRE_ANIMAL","NINGUNO");
                
                usarConexion = false;   
                consulta = true;
                
                jrDataSource = new JRBeanCollectionDataSource(interrupciones);
                                          
            }
            
            
            //REPORTE DE INTERRUPCION + 5 MIN
            if(tipoReporte.intValue() == ServletReporte.reporteMayorCincoMin){       
                
                String consecutivoInterrupcion = (String)sessionMap.remove("consecutivoInterrupcion");            
                String valoresLlave[] = consecutivoInterrupcion.split("-");   
                Integer ano = new Integer(valoresLlave[0].trim());
                Integer codigoOficina = new Integer(valoresLlave[1].trim());
                Long numeroInterrupcion = new Long(valoresLlave[2].trim());  
                
                nombreArchivo = "IntMay5-" + ano + "-" + codigoOficina + "-" + numeroInterrupcion + ".pdf";
                //HASHMAP PARA LOS PARAMETROS DEL REPORTE 
                                
                parametrosReporte.put("DIR_IMAGENES",servletImagepath);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);                               
                
                //CARGA EL JASPER COMPILADO
                nombreArchivoJasper = "SigeItrRepMayorCincoMin.jasper";
                                     
                //OBTIENE BEANS
                Interrupcion interrupcion = (Interrupcion)sessionMap.remove("interrupcion");
                List<Serializable> interrupciones = new ArrayList<Serializable>();
                interrupciones.add(interrupcion);
                List cuadrillas = (List)sessionMap.remove("cuadrillas");
                Vehiculo vehiculo = (Vehiculo)sessionMap.remove("vehiculo");
                List<Serializable> vehiculos = new ArrayList<Serializable>();
                vehiculos.add(vehiculo);
                
                List postes = (List)sessionMap.remove("postes");
                List equipos = (List)sessionMap.remove("equipos");
                List fusibles = (List)sessionMap.remove("fusibles");
                List trafos = (List)sessionMap.remove("trafos");
                List gemelas = (List)sessionMap.remove("gemelas");
                List seccionamientos = (List)sessionMap.remove("seccionamientos");                             
                
                Region reg = (Region)sessionMap.remove("region");
                SubRegion subreg = (SubRegion)sessionMap.remove("subregion");
                SubEstacion sub = (SubEstacion)sessionMap.remove("subEstacion");               
                Circuito cir = (Circuito)sessionMap.remove("circuito");
                Seccion sec = (Seccion)sessionMap.remove("seccion");
                Empleado emp = (Empleado)sessionMap.remove("empleado");
                Oficina oficina = (Oficina)sessionMap.remove("oficina");
                Pueblo puebloFalla = (Pueblo)sessionMap.remove("puebloFalla");
                Pueblo puebloEquipo = (Pueblo)sessionMap.remove("puebloEquipo");
                Agencia agencia = (Agencia)sessionMap.remove("agencia");
                TipoVoltaje tipoVoltaje = (TipoVoltaje)sessionMap.remove("tipoVoltaje");
                
                Proteccion proteccion = (Proteccion)sessionMap.remove("proteccion");
                Material material = (Material)sessionMap.remove("material");
                Dano dano = (Dano)sessionMap.remove("dano");
                Causa causa1 = (Causa)sessionMap.remove("causa1");
                Causa causa2 = (Causa)sessionMap.remove("causa2");
                Animal animal = (Animal)sessionMap.remove("animal");
                
                //PASAMOS LOS PARAMETROS BEAN
                parametrosReporte.put("CONSECUTIVO",ano + "-" + codigoOficina + "-" + numeroInterrupcion);
                
                parametrosReporte.put("CUADRILLA",new JRBeanCollectionDataSource(cuadrillas));
                if(vehiculo != null)
                    parametrosReporte.put("VEHICULO",new JRBeanCollectionDataSource(vehiculos));
                else
                    parametrosReporte.put("VEHICULO",new JRBeanCollectionDataSource(new ArrayList()));
                
                parametrosReporte.put("POSTES",new JRBeanCollectionDataSource(postes));
                parametrosReporte.put("EQUIPOS",new JRBeanCollectionDataSource(equipos));
                parametrosReporte.put("FUSIBLES",new JRBeanCollectionDataSource(fusibles));
                parametrosReporte.put("TRAFOS",new JRBeanCollectionDataSource(trafos));
                parametrosReporte.put("GEMELAS",new JRBeanCollectionDataSource(gemelas));
                parametrosReporte.put("SECCIONAMIENTOS",new JRBeanCollectionDataSource(seccionamientos));
                
                parametrosReporte.put("ANO",ano);
                parametrosReporte.put("OFICINA",codigoOficina);
                parametrosReporte.put("NUMERO_REPORTE",numeroInterrupcion);
                                
                parametrosReporte.put("NOMBRE_REGION",reg.getRegion() + " - " + reg.getNombreRegion());
                parametrosReporte.put("NOMBRE_SUBREGION",subreg.getSubRegionID().getSubRegion() + " - " + subreg.getNombreSubRegion());
                
                parametrosReporte.put("NOMBRE_SUBESTACION",sub.getCodigoSubEstacion() + " - " + sub.getNombreSubEstacion());                
                parametrosReporte.put("NOMBRE_CIRCUITO",cir.getCircuitoID().getCircuito() + " - " + cir.getNombreCircuito());
                parametrosReporte.put("NOMBRE_SECCION",sec.getSeccionID().getSeccion() + " - " + sec.getNombreSeccion());
                if(emp != null)
                    parametrosReporte.put("NOMBRE_EMPLEADO",emp.getNombreCompleto());
                else
                    parametrosReporte.put("NOMBRE_EMPLEADO","INHABILITADO");
                parametrosReporte.put("NOMBRE_OFICINA",oficina.getNombreOficina());
                if(puebloFalla != null)
                    parametrosReporte.put("NOMBRE_PUEBLOFALLA",puebloFalla.getNombre());
                else
                    parametrosReporte.put("NOMBRE_PUEBLOFALLA","DESCONOCIDO");
                if(puebloEquipo != null)
                    parametrosReporte.put("NOMBRE_PUEBLOEQUIPO",puebloEquipo.getNombre());
                else
                    parametrosReporte.put("NOMBRE_PUEBLOEQUIPO","DESCONOCIDO");
                if(agencia != null)
                    parametrosReporte.put("NOMBRE_AGENCIA",agencia.getCodigoAgencia() + " - " + agencia.getNombreAgencia());
                else
                    parametrosReporte.put("NOMBRE_AGENCIA","NINGUNA");
                parametrosReporte.put("NOMBRE_TIPOVOLTAJE",tipoVoltaje.getCodigoDescripcion());
                parametrosReporte.put("NOMBRE_PROTECCION",proteccion.getCodigoProteccion() + " - " + proteccion.getNombreProteccion());
                parametrosReporte.put("NOMBRE_MATERIAL",material.getCodigoMaterial() + " - " + material.getNombreMaterial());
                parametrosReporte.put("NOMBRE_DANO",dano.getCodigoDano() + " - " + dano.getNombreDano());
                parametrosReporte.put("NOMBRE_CAUSA1",causa1.getCodigoCausa() + " - " + causa1.getNombreCausa());
                if(causa2 != null)
                    parametrosReporte.put("NOMBRE_CAUSA2",causa2.getCodigoCausa() + " - " + causa2.getNombreCausa());
                else
                    parametrosReporte.put("NOMBRE_CAUSA2","NINGUNA");
                if(animal != null)
                    parametrosReporte.put("NOMBRE_ANIMAL",animal.getNombreAnimal());
                else
                    parametrosReporte.put("NOMBRE_ANIMAL","NINGUNO");
                
                formato = "PDF";

                usarConexion = false;   
                consulta = true;

                jrDataSource = new JRBeanCollectionDataSource(interrupciones);
                                                                 
            }     
            
            //REPORTE POR CIRCUITO
            if(tipoReporte.intValue() == ServletReporte.reportePorCircuito){               
                
                nombreArchivo = "SigeItrRepInterrupcionPorCircuito.xls";
                //HASHMAP PARA LOS PARAMETROS DEL REPORTE 
                                
                
                parametrosReporte.put("DIR_IMAGENES",servletImagepath);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);                                              
                
                //CARGA EL JASPER COMPILADO
                nombreArchivoJasper = "SigeItrRepInterrupcionPorCircuito.jasper";
                                     
                //OBTIENE BEANS             
                String titulo = (String)sessionMap.remove("titulo");
                String oficina = (String)sessionMap.remove("oficina");
                
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                Integer tipoBitacora = (Integer)sessionMap.remove("tipoBitacora");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");
                Integer codigoCausa = (Integer)sessionMap.remove("codigoCausa");
                String codigosVoltajeSTR = (String)sessionMap.remove("codigosVoltajeSTR");
                Integer codigoCircuito = (Integer)sessionMap.remove("codigoCircuito");
                Integer codigoSubestacion = (Integer)sessionMap.remove("codigoSubestacion");                
                
                parametrosReporte.put("TITULO",titulo);
                parametrosReporte.put("OFICINA",oficina);
                
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("tipoBitacora",tipoBitacora);
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("codigoCausa",codigoCausa);
                parametrosReporte.put("codigosVoltajeSTR",codigosVoltajeSTR);
                parametrosReporte.put("codigoCircuito",codigoCircuito);
                parametrosReporte.put("codigoSubestacion",codigoSubestacion);                
                
                formato = "XLS";

                usarConexion = true;   
                consulta = true;
                                  
            }   
            
            //REPORTE POR CIRCUITO MENOR 5 MINUTOS
            if(tipoReporte.intValue() == ServletReporte.reportePorCircuitoMenorCincoMin){               
                
                nombreArchivo = "SigeItrRepInterrupcionPorCircuito.xls";
                //HASHMAP PARA LOS PARAMETROS DEL REPORTE 
                                
                
                parametrosReporte.put("DIR_IMAGENES",servletImagepath);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);                                              
                
                //CARGA EL JASPER COMPILADO
                nombreArchivoJasper = "SigeItrRepInterrupcionPorCircuitoMenor5Minutos.jasper";
                                     
                //OBTIENE BEANS             
                String titulo = (String)sessionMap.remove("titulo");
                String oficina = (String)sessionMap.remove("oficina");
                
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                Integer tipoBitacora = (Integer)sessionMap.remove("tipoBitacora");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");
                Integer codigoCausa = (Integer)sessionMap.remove("codigoCausa");
                String codigosVoltajeSTR = (String)sessionMap.remove("codigosVoltajeSTR");
                Integer codigoCircuito = (Integer)sessionMap.remove("codigoCircuito");
                Integer codigoSubestacion = (Integer)sessionMap.remove("codigoSubestacion");   
                Boolean causa411 = (Boolean)sessionMap.remove("causa411");  
                
                parametrosReporte.put("TITULO",titulo);
                parametrosReporte.put("OFICINA",oficina);
                
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("tipoBitacora",tipoBitacora);
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("codigoCausa",codigoCausa);
                parametrosReporte.put("codigosVoltajeSTR",codigosVoltajeSTR);
                parametrosReporte.put("codigoCircuito",codigoCircuito);
                parametrosReporte.put("codigoSubestacion",codigoSubestacion);              
                this.reporteTipoPorCircuito=ServletReporte.reportePorCircuitoMenorCincoMin;
                
                
               /* String sql= "SELECT interrupcion.aa as aa, interrupcion.cod_oficina as cod_oficina, "+
                "interrupcion.numrep as numinterrup, 'PROPIA' AS TIPO, interrupcion.SECCION as seccion, interrupcion.REG as region, "+
                "interrupcion.SUBREGION as subregion, interrupcion.SUB as subestacion, interrupcion.CIR as circuito, " +
                "(select nom_circuito from eve01c where sub = interrupcion.SUB and cir = interrupcion.CIR) as nombrecircuito, "+
                "(select nom_seccion from eve06c where reg = interrupcion.REG and subregion = interrupcion.SUBREGION " +
                "and sub = interrupcion.SUB and cir = interrupcion.CIR and seccion = interrupcion.SECCION) as nombreseccion, "+
                "interrupcion.TIEFUE as tiefue, interrupcion.COD_VOLTAJE as codigovoltaje, interrupcion.CAUSA1 as causa1, " +
                "(select nom_causa from eve09c where cod_causa = interrupcion.CAUSA1) as nombrecausa, " +
                "interrupcion.ABO_AFECTA as abonadosafectados, interrupcion.horarepor as horainicio, " +
                "interrupcion.hora_cierre as horafin, interrupcion.fecrepor as fechainicio, interrupcion.feccierre as fechafin, " +
                "interrupcion.HRS_ABO as horasabonado, interrupcion.COMENTARIO as comentario from eve02m  interrupcion " +
                "where interrupcion.COD_OFICINA=$P{codigoOficina} and TO_DATE(interrupcion.fecrepor) >= to_date($P{fechaInicio},'dd/mm/yyyy') " +
                "and TO_DATE(interrupcion.feccierre) <= to_date($P{fechaFinal},'dd/mm/yyyy')" +
                "and (interrupcion.COD_VOLTAJE in ($P!{codigosVoltajeSTR})) and (interrupcion.causa1=$P{codigoCausa}  or $P{codigoCausa}=0) " +
                "and (interrupcion.BITACORA=$P{tipoBitacora} or $P{tipoBitacora}=2)  and ((interrupcion.cir=$P{codigoCircuito} " +
                "and interrupcion.sub=$P{codigoSubestacion}) or ($P{codigoCircuito}=0 and $P{codigoSubestacion}=0)) ";
                
                if (causa411){
                    sql+="AND interrupcion.CAUSA1 <> 411 ";
                }
                sql+="order by subestacion, circuito, aa, cod_oficina, numinterrup, tipo desc, seccion, fechainicio";*/
                if (causa411){
                    parametrosReporte.put("causa411",Integer.valueOf(411));
                }else{
                    parametrosReporte.put("causa411",Integer.valueOf(0));
                }
                formato = "XLS";

                usarConexion = true;   
                consulta = true;                              
            }     
            
            //REPORTE POR PERIODO
            if(tipoReporte.intValue() == ServletReporte.reportePorPeriodo){               
                   
                nombreArchivo = "SigeItrRepInterrupcionPorPeriodo.xls";
                //HASHMAP PARA LOS PARAMETROS DEL REPORTE 
                                
                
                parametrosReporte.put("DIR_IMAGENES",servletImagepath);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);                               
                
                //CARGA EL JASPER COMPILADO
                nombreArchivoJasper = "SigeItrRepInterrupcionPorPeriodo.jasper";
                                     
                //OBTIENE BEANS
                //List interrupciones = (List)sessionMap.remove("interrupciones");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                Integer codigoAgencia = (Integer)sessionMap.remove("codigoAgencia");

                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("codigoAgencia",codigoAgencia);
                
                formato = "XLS";

                usarConexion = true;   
                consulta = true;                                    
            }   
            
            //REPORTE INTERRUPCIONES POR TIPO DE CAUSA
            if(tipoReporte.intValue() == ServletReporte.reportePorCausa){              
                                            
                
                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                                 
                                               
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);
                
                //OBTIENE BEANS
                String nivelRedActual = (String)sessionMap.remove("nivelRedActual");
                
                String tipoInterrupcion = (String)sessionMap.remove("tipoInterrupcion");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");
                
                Integer anoFechaFinal = (Integer)sessionMap.remove("anoFechaFinal");
                Integer mesFechaFinal = (Integer)sessionMap.remove("mesFechaFinal");
                
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                String nombreOficina = (String)sessionMap.remove("nombreOficina");                
                
                String itemsSTR = (String)sessionMap.remove("itemsSTR");
                String voltajesSTR = (String)sessionMap.remove("voltajesSTR");
                Integer codigoCausa = (Integer)sessionMap.remove("codigoCausa");
                Integer cantidadVoltajes = (Integer)sessionMap.remove("cantidadVoltajes");
                Integer codigoCausaCatastrofe = (Integer)sessionMap.remove("codigoCausaCatastrofe");
                Integer cantidadItems = (Integer)sessionMap.remove("cantidadItems");                
                
                Boolean utilizarDatosHistoricos = (Boolean)sessionMap.remove("utilizarDatosHistoricos");
                
                Boolean DPIR = (Boolean)sessionMap.remove("DPIR");
                Boolean Cantidad = (Boolean)sessionMap.remove("Cantidad");
                Boolean Comparativo = (Boolean)sessionMap.remove("Comparativo");
                
                Boolean pdf = (Boolean)sessionMap.remove("PDF");
                
                //HASHMAP PARA LOS PARAMETROS DEL REPORTE 
                parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
                parametrosReporte.put("fechaInicio",fechaInicio);
                
                parametrosReporte.put("anoFechaFinal",anoFechaFinal);
                parametrosReporte.put("mesFechaFinal",mesFechaFinal);
                
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("nombreOficina",nombreOficina);
                
                Integer region = (Integer)sessionMap.remove("region");
                //Integer region = 1;
                Integer subregion = (Integer)sessionMap.remove("subregion");
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");
                parametrosReporte.put("region",region);
                parametrosReporte.put("subregion",subregion);
                parametrosReporte.put("subestacion",subestacion);
                parametrosReporte.put("circuito",circuito);
                 
                parametrosReporte.put("DPIR", DPIR);
                parametrosReporte.put("Cantidad", Cantidad);
                parametrosReporte.put("Comparativo", Comparativo);
                
                
                if(nivelRedActual.equals("region")){
                    parametrosReporte.put("regionesSTR",itemsSTR);
                    parametrosReporte.put("cantidadRegiones",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaRegionHistorico";
                    else
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaRegion";
                    
                    nombreArchivo = "InterrupcionPorCausaRegion";
                }
                if(nivelRedActual.equals("subregion")){
                    parametrosReporte.put("subregionesSTR",itemsSTR);
                    parametrosReporte.put("cantidadSubregiones",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaSubregionHistorico";
                    else
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaSubregion";
                    
                    nombreArchivo = "InterrupcionPorCausaSubregion";
                }   
                if(nivelRedActual.equals("subestacion")){
                    parametrosReporte.put("subestacionesSTR",itemsSTR);
                    parametrosReporte.put("cantidadSubestaciones",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaSubestacionHistorico";
                    else
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaSubestacion";
                    
                    nombreArchivo = "InterrupcionPorCausaSubestacion";
                }   
                if(nivelRedActual.equals("circuito")){
                    parametrosReporte.put("circuitosSTR",itemsSTR);
                    parametrosReporte.put("cantidadCircuitos",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaCircuitoHistorico";
                    else
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaCircuito";
                    
                    nombreArchivo = "InterrupcionPorCausaCircuito";
                }   
                if(nivelRedActual.equals("seccion")){
                    parametrosReporte.put("seccionesSTR",itemsSTR);
                    parametrosReporte.put("cantidadSecciones",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaSeccionHistorico";
                    else
                        nombreArchivoJasper = "SigeItrRepInterrupcionPorCausaSeccion";
                    
                    nombreArchivo = "InterrupcionPorCausaSeccion";
                }                 
                
                
                parametrosReporte.put("voltajesSTR",voltajesSTR);
                parametrosReporte.put("codigoCausa",codigoCausa);
                parametrosReporte.put("cantidadVoltajes",cantidadVoltajes);
                parametrosReporte.put("codigoCausaCatastrofe",codigoCausaCatastrofe);
                              
                           
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR;
                
                nombreArchivoJasper += ".jasper";
                
                if(pdf.booleanValue()){
                    
                    nombreArchivo += ".pdf";
                    formato = "PDF";

                    usarConexion = true;   
                    consulta = true;
                                            
                }
                else{
                    
                    nombreArchivo += ".xls";
                    formato = "XLS";

                    usarConexion = true;   
                    consulta = true;
                }
    
            }            
            
            //REPORTE DE INDICADORES GLOBALES
            if(tipoReporte.intValue() == ServletReporte.reporteIndicadorGlobal){               
                                   
                                              
                //OBTIENE BEANS
                String nivelRedActual = (String)sessionMap.remove("nivelRedActual");                               
                String tipoInterrupcion = (String)sessionMap.remove("tipoInterrupcion");
                Integer ano = (Integer)sessionMap.remove("ano");                
                Integer mes = (Integer)sessionMap.remove("mes");
                
                Boolean utilizarDatosHistoricos = (Boolean)sessionMap.remove("utilizarDatosHistoricos");
                Integer anoHistorico = (Integer)sessionMap.remove("anoHistorico");
                Integer mesHistorico = (Integer)sessionMap.remove("mesHistorico");                  
                String itemsSTR = (String)sessionMap.remove("itemsSTR");
                Integer cantidadItems = (Integer)sessionMap.remove("cantidadItems");                
                Integer tipoVoltaje = (Integer)sessionMap.remove("tipoVoltaje");
                Integer codigoVoltaje = (Integer)sessionMap.remove("codigoVoltaje");
                String mesSTR = (String)sessionMap.remove("mesSTR");

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
                parametrosReporte.put("ano",ano);
                parametrosReporte.put("mes",mes);                                
                parametrosReporte.put("anoHistorico",anoHistorico);
                parametrosReporte.put("mesHistorico",mesHistorico);   
                parametrosReporte.put("tipoVoltaje",tipoVoltaje);
                parametrosReporte.put("codigoVoltaje",codigoVoltaje);
                parametrosReporte.put("mesSTR",mesSTR);
                
                Integer region = (Integer)sessionMap.remove("region");
                Integer subregion = (Integer)sessionMap.remove("subregion");
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");
                parametrosReporte.put("region",region);
                parametrosReporte.put("subregion",subregion);
                parametrosReporte.put("subestacion",subestacion);
                parametrosReporte.put("circuito",circuito);
                
                if(nivelRedActual.equals("region")){
                    parametrosReporte.put("regionesSTR",itemsSTR);
                    parametrosReporte.put("cantidadRegiones",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalRegionHistorico";
                    else
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalRegion";
                    
                    nombreArchivo = "IndGlobalesRegion";
                }
                if(nivelRedActual.equals("subregion")){
                    parametrosReporte.put("subregionesSTR",itemsSTR);
                    parametrosReporte.put("cantidadSubregiones",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalSubregionHistorico";
                    else
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalSubregion";
                    
                    nombreArchivo = "IndGlobalesSubregion";
                }   
                if(nivelRedActual.equals("subestacion")){
                    parametrosReporte.put("subestacionesSTR",itemsSTR);
                    parametrosReporte.put("cantidadSubestaciones",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalSubestacionHistorico";
                    else                    
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalSubestacion";
                    
                    nombreArchivo = "IndGlobalesSubestacion";
                }   
                if(nivelRedActual.equals("circuito")){
                    parametrosReporte.put("circuitosSTR",itemsSTR);
                    parametrosReporte.put("cantidadCircuitos",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalCircuitoHistorico";
                    else                          
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalCircuito";
                    
                    nombreArchivo = "IndGlobalesCircuito";
                }   
                if(nivelRedActual.equals("seccion")){
                    parametrosReporte.put("seccionesSTR",itemsSTR);
                    parametrosReporte.put("cantidadSecciones",cantidadItems);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalSeccionHistorico";
                    else                         
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorGlobalSeccion";
                    
                    nombreArchivo = "IndGlobalesSeccion";
                }                 
                               
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";

                usarConexion = true;   
                consulta = true;
                                 
            }   
            
            //REPORTE DE INDICADORES CAUSA
            if(tipoReporte.intValue() == ServletReporte.reporteIndicadorCausa){               
                                         
                //OBTIENE BEANS
                String nivelRedActual = (String)sessionMap.remove("nivelRedActual");                               
                String tipoInterrupcion = (String)sessionMap.remove("tipoInterrupcion");
                Integer ano = (Integer)sessionMap.remove("ano");            
                Integer mes = (Integer)sessionMap.remove("mes");
                
                Boolean utilizarDatosHistoricos = (Boolean)sessionMap.remove("utilizarDatosHistoricos");
                Integer anoHistorico = (Integer)sessionMap.remove("anoHistorico");
                Integer mesHistorico = (Integer)sessionMap.remove("mesHistorico");
                
                String subestacionesSTR = (String)sessionMap.remove("subestacionesSTR");
                String circuitosSTR = (String)sessionMap.remove("circuitosSTR");
                Integer cantidadCircuitos = (Integer)sessionMap.remove("cantidadCircuitos");
                String seccionesSTR = (String)sessionMap.remove("seccionesSTR");
                Integer cantidadSecciones = (Integer)sessionMap.remove("cantidadSecciones");
                
                Integer tipoVoltaje = (Integer)sessionMap.remove("tipoVoltaje");
                Integer codigoVoltaje = (Integer)sessionMap.remove("codigoVoltaje");
                Integer codigoCausa = (Integer)sessionMap.remove("codigoCausa");
                String mesSTR = (String)sessionMap.remove("mesSTR");
                String nombreCausa = (String)sessionMap.remove("nombreCausa");

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
                parametrosReporte.put("ano",ano);
                parametrosReporte.put("mes",mes);                                
                parametrosReporte.put("anoHistorico",anoHistorico);
                parametrosReporte.put("mesHistorico",mesHistorico);   
                parametrosReporte.put("tipoVoltaje",tipoVoltaje);
                parametrosReporte.put("codigoVoltaje",codigoVoltaje);
                parametrosReporte.put("causa",codigoCausa);
                parametrosReporte.put("mesSTR",mesSTR);
                parametrosReporte.put("nombreCausa",nombreCausa);
                  
                String restriccionSQL = "";
                
                
                if(nivelRedActual.equals("circuito")){
                    if(cantidadCircuitos.intValue() > 0)
                        restriccionSQL = this.getSQLReporteIndicadorCircuito(subestacionesSTR, circuitosSTR);
                    parametrosReporte.put("restriccionSQL",restriccionSQL);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorCausaCircuitoHistorico";
                    else                          
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorCausaCircuito";
                                        
                }   
                if(nivelRedActual.equals("seccion")){
                    if(cantidadSecciones.intValue() > 0)
                        restriccionSQL = this.getSQLReporteIndicadorSeccion(subestacionesSTR, circuitosSTR, seccionesSTR);
                    else
                        restriccionSQL = this.getSQLReporteIndicadorCircuito(subestacionesSTR, circuitosSTR);
                    parametrosReporte.put("restriccionSQL",restriccionSQL);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorCausaSeccionHistorico";
                    else                         
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorCausaSeccion";
                    
                }                    
                
                nombreArchivo = "IndCausaMT";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                                     
                formato = "PDF";

                usarConexion = true;   
                consulta = true;
                                 
            }      
            
            
            //REPORTE DE INDICADORES TRIFASICO
            if(tipoReporte.intValue() == ServletReporte.reporteIndicadorTrifasico){               
                                                 
                //OBTIENE BEANS
                String nivelRedActual = (String)sessionMap.remove("nivelRedActual");                               
                Integer ano = (Integer)sessionMap.remove("ano");            
                Integer mes = (Integer)sessionMap.remove("mes");
                
                Boolean utilizarDatosHistoricos = (Boolean)sessionMap.remove("utilizarDatosHistoricos");
                Integer anoHistorico = (Integer)sessionMap.remove("anoHistorico");
                Integer mesHistorico = (Integer)sessionMap.remove("mesHistorico");                               
                Integer tipoVoltaje = (Integer)sessionMap.remove("tipoVoltaje");
                Integer codigoVoltaje = (Integer)sessionMap.remove("codigoVoltaje");
                String mesSTR = (String)sessionMap.remove("mesSTR");

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("ano",ano);
                parametrosReporte.put("mes",mes);                                
                parametrosReporte.put("anoHistorico",anoHistorico);
                parametrosReporte.put("mesHistorico",mesHistorico);   
                parametrosReporte.put("tipoVoltaje",tipoVoltaje);
                parametrosReporte.put("codigoVoltaje",codigoVoltaje);
                parametrosReporte.put("mesSTR",mesSTR);
                
                String subestacionesSTR = (String)sessionMap.remove("subestacionesSTR");
                String circuitosSTR = (String)sessionMap.remove("circuitosSTR");
                Integer cantidadCircuitos = (Integer)sessionMap.remove("cantidadCircuitos");
                String seccionesSTR = (String)sessionMap.remove("seccionesSTR");
                Integer cantidadSecciones = (Integer)sessionMap.remove("cantidadSecciones");
                
                String restriccionSQL = "";
                
                if(nivelRedActual.equals("circuito")){
                    if(cantidadCircuitos.intValue() > 0)
                        restriccionSQL = this.getSQLReporteIndicadorCircuito(subestacionesSTR, circuitosSTR);
                    parametrosReporte.put("restriccionSQL",restriccionSQL);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorTrifasicoCircuitoHistorico";
                    else                          
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorTrifasicoCircuito";
                                        
                }   
                if(nivelRedActual.equals("seccion")){
                    if(cantidadSecciones.intValue() > 0)
                        restriccionSQL = this.getSQLReporteIndicadorSeccion(subestacionesSTR, circuitosSTR, seccionesSTR);
                    else
                        restriccionSQL = this.getSQLReporteIndicadorCircuito(subestacionesSTR, circuitosSTR);
                    parametrosReporte.put("restriccionSQL",restriccionSQL);
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorTrifasicoSeccionHistorico";
                    else                         
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorTrifasicoSeccion";
                    
                }                 
                
                nombreArchivo = "IndTrifasico";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                
                formato = "PDF";

                usarConexion = true;   
                consulta = true;  
                                 
            }
            
            
            //REPORTE DE INDICADORES PROPIOS Y NO PROPIOS
            if(tipoReporte.intValue() == ServletReporte.reporteIndicadorPropioNoPropio){               
                                                 
                //OBTIENE BEANS
                Integer tipoIndicador = (Integer)sessionMap.remove("tipoIndicador");                               
                String tipoInterrupcion = (String)sessionMap.remove("tipoInterrupcion");
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                Integer ano = (Integer)sessionMap.remove("ano");            
                Integer mes = (Integer)sessionMap.remove("mes");
                
                Boolean utilizarDatosHistoricos = (Boolean)sessionMap.remove("utilizarDatosHistoricos");
                Integer anoHistorico = (Integer)sessionMap.remove("anoHistorico");
                Integer mesHistorico = (Integer)sessionMap.remove("mesHistorico");                                                 
                Integer tipoVoltaje = (Integer)sessionMap.remove("tipoVoltaje");
                Integer codigoVoltaje = (Integer)sessionMap.remove("codigoVoltaje");
                String mesSTR = (String)sessionMap.remove("mesSTR");

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("tipoInterrupcion",tipoInterrupcion);
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("ano",ano);
                parametrosReporte.put("mes",mes);                                
                parametrosReporte.put("anoHistorico",anoHistorico);
                parametrosReporte.put("mesHistorico",mesHistorico);   
                parametrosReporte.put("tipoVoltaje",tipoVoltaje);
                parametrosReporte.put("codigoVoltaje",codigoVoltaje);
                parametrosReporte.put("mesSTR",mesSTR);
                
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");
                Long seccion = (Long)sessionMap.remove("seccion");
                parametrosReporte.put("subestacion",subestacion);
                parametrosReporte.put("circuito",circuito);
                parametrosReporte.put("seccion",seccion);
                                
                if(tipoIndicador.intValue() == 1){
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorPropioSeccionHistorico";
                    else                          
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorPropioSeccion";
                    nombreArchivo = "IndPropio";                   
                }   
                if(tipoIndicador.intValue() == 2){
                    if(utilizarDatosHistoricos.booleanValue())
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorNoPropioSeccionHistorico";
                    else                         
                        nombreArchivoJasper = "SigeItrRepInterrupcionIndicadorNoPropioSeccion";
                    nombreArchivo = "IndNoPropio";
                }                      
                
                 
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                                
                nombreArchivoJasper += ".jasper";
                
                formato = "PDF";

                usarConexion = true;   
                consulta = true; 
                                 
            }       
            
            //REPORTE DE DIFERENCIA DE TIEMPOS
            if(tipoReporte.intValue() == ServletReporte.reporteDiferenciaTiempos){                                                       
                                        
                //OBTIENE BEANS
                String nivelRedActual = (String)sessionMap.remove("nivelRedActual");                               
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                String nombreOficina = (String)sessionMap.remove("nombreOficina");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");  
                
                Integer region = (Integer)sessionMap.remove("region");            
                Integer subregion = (Integer)sessionMap.remove("subregion");
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");
                Long seccion = (Long)sessionMap.remove("seccion");
                
                String nombreCircuito = (String)sessionMap.remove("nombreCircuito");
                String nombreSeccion = (String)sessionMap.remove("nombreSeccion");
                
                Integer actor = (Integer)sessionMap.remove("actor");
                Integer tiempo = (Integer)sessionMap.remove("tiempo");
                Integer formato2 = (Integer)sessionMap.remove("formato");                

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("nombreOficina",nombreOficina);
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("region",region);
                parametrosReporte.put("subregion",subregion);                                
                parametrosReporte.put("subestacion",subestacion);
                parametrosReporte.put("circuito",circuito);   
                parametrosReporte.put("seccion",seccion);
                parametrosReporte.put("nombreCircuito",nombreCircuito);
                parametrosReporte.put("nombreSeccion",nombreSeccion);
                
                parametrosReporte.put("actor",actor);
                parametrosReporte.put("tiempo",tiempo);
                parametrosReporte.put("formato",formato2);
                
                                                
                if(nivelRedActual.equals("circuito"))
                    nombreArchivoJasper = "SigeItrRepDiferenciaTiemposCircuito";
                else                          
                    nombreArchivoJasper = "SigeItrRepDiferenciaTiemposSeccion";
              
                
                nombreArchivo = "DifTiempos";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR;

                
                nombreArchivoJasper += ".jasper";
                
                usarConexion = true;   
                consulta = true; 
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += ".xls";
                    formato = "XLS";
                }                                 
            }    
            
			//REPORTE DE AUDITORIA
            if(tipoReporte.intValue() == ServletReporte.reporteAuditoria){                                                       
                                        
                //OBTIENE BEANS
                String nivelRed = (String)sessionMap.remove("nivelRed");                               
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                String nombreOficina = (String)sessionMap.remove("nombreOficina");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");  
                
                Integer region = (Integer)sessionMap.remove("region");            
                Integer subregion = (Integer)sessionMap.remove("subregion");
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");
                Long seccion = (Long)sessionMap.remove("seccion");
              
                Integer formato2 = (Integer)sessionMap.remove("formato"); 
                String sqlFiltro = (String)sessionMap.remove("sqlFiltro");
                String tituloNivelRed = (String)sessionMap.remove("tituloNivelRed"); 
              
              

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("nombreOficina",nombreOficina);
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("region",region);
                parametrosReporte.put("subregion",subregion);                                
                parametrosReporte.put("subestacion",subestacion);
                parametrosReporte.put("circuito",circuito);   
                parametrosReporte.put("seccion",seccion);
                parametrosReporte.put("formato",formato2);
                parametrosReporte.put("nivelRed",nivelRed);
                parametrosReporte.put("sqlFiltro",sqlFiltro);
                parametrosReporte.put("tituloNivelRed",tituloNivelRed);
                
              
                               
                nombreArchivo = "Auditoria";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR;

                
                nombreArchivoJasper += "SigeAuditoria.jasper";
                
                usarConexion = true;   
                consulta = true; 
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += ".xls";
                    formato = "XLS";
                }                                 
            }    
			
            //REPORTE DE PROTECCIONES POR CIRCUITO
            if(tipoReporte.intValue() == ServletReporte.reporteProteccionesCircuito){               
                                                
                //OBTIENE BEANS                         
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");                
                Integer tipoVoltaje = (Integer)sessionMap.remove("tipoVoltaje");            
                Integer formato2 = (Integer)sessionMap.remove("formato");
                
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                String circuitosSTR = (String)sessionMap.remove("circuitosSTR");
                Integer cantidadCircuitos = (Integer)sessionMap.remove("cantidadCircuitos");
                String proteccionesSTR = (String)sessionMap.remove("proteccionesSTR");
                Integer cantidadProtecciones = (Integer)sessionMap.remove("cantidadProtecciones");
                
                String codigoVoltaje = (String)sessionMap.remove("codigoVoltaje");
                            

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("tipoVoltaje",tipoVoltaje);
                parametrosReporte.put("codigoVoltaje",codigoVoltaje);                                
                parametrosReporte.put("subestacion",subestacion);   
                parametrosReporte.put("circuitosSTR",circuitosSTR);
                parametrosReporte.put("cantidadCircuitos",cantidadCircuitos);
                parametrosReporte.put("proteccionesSTR",proteccionesSTR);
                parametrosReporte.put("cantidadProtecciones",cantidadProtecciones); 
                                                                
                nombreArchivoJasper = "SigeItrRepProtecciones";              
                
                nombreArchivo = "Protecciones";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR;

                nombreArchivoJasper += ".jasper";
                usarConexion = true;   
                consulta = true;
                
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += ".xls";
                    formato = "XLS";

                }
                                 
            }      
            
            //REPORTE DE FUSIBLES INSTALADOS Y RETIRADOS
            if(tipoReporte.intValue() == ServletReporte.reporteFusiblesInstRet){               
                                                                
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);    
                                              
                //OBTIENE BEANS      
                String nivelRedActual = (String)sessionMap.remove("nivelRedActual");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");                
                Integer tipoFusible = (Integer)sessionMap.remove("tipoFusible");            
                Integer formato2 = (Integer)sessionMap.remove("formato");
                
                Integer region = (Integer)sessionMap.remove("region");
                Integer subregion = (Integer)sessionMap.remove("subregion");
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                
                String itemsSTR = (String)sessionMap.remove("itemsSTR");
                Integer cantidadItems = (Integer)sessionMap.remove("cantidadItems");         
                            

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("tipoFusible",tipoFusible);
                parametrosReporte.put("region",region);                                
                parametrosReporte.put("subregion",subregion);
                parametrosReporte.put("subestacion",subestacion);   
                parametrosReporte.put("itemsSTR",itemsSTR);
                parametrosReporte.put("cantidadItems",cantidadItems);                
                
                if(nivelRedActual.equals("region")){
                    if(tipoFusible.equals(ReporteFusibleInstRetController.FUSIBLE_INSTALADO))
                        nombreArchivoJasper = "SigeItrRepFusiblesInstaladosRegion";
                    else
                        nombreArchivoJasper = "SigeItrRepFusiblesRetiradosRegion";
                }
                if(nivelRedActual.equals("subregion")){
                    if(tipoFusible.equals(ReporteFusibleInstRetController.FUSIBLE_INSTALADO))
                        nombreArchivoJasper = "SigeItrRepFusiblesInstaladosSubregion";
                    else
                        nombreArchivoJasper = "SigeItrRepFusiblesRetiradosSubregion";
                }   
                if(nivelRedActual.equals("circuito")){
                    if(tipoFusible.equals(ReporteFusibleInstRetController.FUSIBLE_INSTALADO))
                        nombreArchivoJasper = "SigeItrRepFusiblesInstaladosCircuito";
                    else
                        nombreArchivoJasper = "SigeItrRepFusiblesRetiradosCircuito";
                }  
                nombreArchivo = "FusiblesInstRet";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR;
                
                nombreArchivoJasper += ".jasper";

                usarConexion = true;   
                consulta = true;
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    formato = "PDF";
                    nombreArchivo += ".pdf";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += ".xls";
                    formato = "XLS";
                }
            }     
            
            //REPORTE DE FUSIBLES INSTALADOS Y RETIRADOS  POR SECCION
            if(tipoReporte.intValue() == ServletReporte.reporteFusiblesInstRetSeccion){               
                                   
         
                //OBTIENE BEANS      
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                String nombreOficina = (String)sessionMap.remove("nombreOficina");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");                         
                Integer formato2 = (Integer)sessionMap.remove("formato");
                
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");                
                String itemsSTR = (String)sessionMap.remove("itemsSTR");
                Integer cantidadItems = (Integer)sessionMap.remove("cantidadItems");         
                            

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE    
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("nombreOficina",nombreOficina);
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("subestacion",subestacion);
                parametrosReporte.put("circuito",circuito);                                
                parametrosReporte.put("itemsSTR",itemsSTR);
                parametrosReporte.put("cantidadItems",cantidadItems);  
                
                nombreArchivoJasper = "SigeItrRepFusiblesInstaladosRetiradosSeccion";                      
                
                nombreArchivo = "FusiblesInstRetSeccion";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR;
                
                nombreArchivoJasper += ".jasper";
                usarConexion = true;   
                consulta = true;
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += ".xls";
                    formato = "XLS";
                }                                 
            }   
            
            //REPORTE DE INDICES GLOBALES POR GRUPO O RANGO DE SECCIONES
            if(tipoReporte.intValue() == ServletReporte.reporteIndiceGlobalSeccion){               
                                                        
                //OBTIENE BEANS      
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                String nombreOficina = (String)sessionMap.remove("nombreOficina");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");                         
                Integer formato2 = (Integer)sessionMap.remove("formato");
                String tipoInterrupcionSTR = (String)sessionMap.remove("tipoInterrupcionSTR");
                String tipoVoltaje = (String)sessionMap.remove("tipoVoltaje");
                Integer causaCatastrofe1 = (Integer)sessionMap.remove("causaCatastrofe1");
                Integer causaCatastrofe2 = (Integer)sessionMap.remove("causaCatastrofe2");
                
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");                
                String itemsSTR = (String)sessionMap.remove("itemsSTR");
                Integer cantidadItems = (Integer)sessionMap.remove("cantidadItems");    
                
                Long seccion = (Long)sessionMap.remove("seccion");
                Long seccionFinal = (Long)sessionMap.remove("seccionFinal");
                String nombreSeccionInicial = (String)sessionMap.remove("nombreSeccionInicial");
                String nombreSeccionFinal = (String)sessionMap.remove("nombreSeccionFinal");
                Boolean utilizarDatosHistoricos = (Boolean)sessionMap.remove("utilizarDatosHistoricos");
                            

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE    
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("nombreOficina",nombreOficina);
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("tipoInterrupcionSTR",tipoInterrupcionSTR);
                parametrosReporte.put("tipoVoltaje",tipoVoltaje);
                parametrosReporte.put("causaCatastrofe1",causaCatastrofe1);
                parametrosReporte.put("causaCatastrofe2",causaCatastrofe2);
                parametrosReporte.put("subestacion",subestacion);
                parametrosReporte.put("circuito",circuito);                                
                parametrosReporte.put("itemsSTR",itemsSTR);
                parametrosReporte.put("cantidadItems",cantidadItems);
                parametrosReporte.put("seccionInicial",seccion);    
                parametrosReporte.put("seccionFinal",seccionFinal);
                parametrosReporte.put("nombreSeccionInicial",nombreSeccionInicial);
                parametrosReporte.put("nombreSeccionFinal",nombreSeccionFinal);
                
                if(utilizarDatosHistoricos.booleanValue())
                    nombreArchivoJasper = "SigeItrRepRangoGrupoSeccionH";
                else
                    nombreArchivoJasper = "SigeItrRepRangoGrupoSeccionC";
                
                nombreArchivo = "RanGrupSecciones";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR;

                
                nombreArchivoJasper += ".jasper";
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += ".xls";
                    formato = "XLS";
                }  
                usarConexion = true;   
                consulta = true;
            }      
            
            //REPORTE DE INDICES MENSUALES Y ACUMULADOS
            if(tipoReporte.intValue() == ServletReporte.reporteIndiceMesAcum){               
                                                         
                //OBTIENE BEANS     
                String nivelRedActual = (String)sessionMap.remove("nivelRedActual");
                Integer ano = (Integer)sessionMap.remove("ano");
                Integer tipoVoltaje = (Integer)sessionMap.remove("tipoInterrupcion");
                String tipoInterrupcionSTR = (String)sessionMap.remove("tipoInterrupcionSTR");
                String nombreNivelRed = (String)sessionMap.remove("nombreNivelRed");
                Integer codigoVoltaje = (Integer)sessionMap.remove("codigoVoltaje");
                
                Integer region = (Integer)sessionMap.remove("region");
                Integer subregion = (Integer)sessionMap.remove("subregion");  
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");
                            

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE    
                parametrosReporte.put("ano",ano);
                parametrosReporte.put("tipoVoltaje",tipoVoltaje);
                parametrosReporte.put("codigoVoltaje",codigoVoltaje);
                parametrosReporte.put("tipoInterrupcionSTR",tipoInterrupcionSTR);
                parametrosReporte.put("region",region);
                parametrosReporte.put("subregion",subregion);
                parametrosReporte.put("subestacion",subestacion);
                parametrosReporte.put("circuito",circuito);
                parametrosReporte.put("nombreNivelRed",nombreNivelRed);
                
                if(nivelRedActual.equals("region")){
                        nombreArchivoJasper = "SigeItrRepResumenIndiceMensualAcumuladoRegion";
                }
                if(nivelRedActual.equals("subregion")){
                    nombreArchivoJasper = "SigeItrRepResumenIndiceMensualAcumuladoSubregion";
                }   
                if(nivelRedActual.equals("circuito")){
                    nombreArchivoJasper = "SigeItrRepResumenIndiceMensualAcumuladoCircuito";
                }            
                
                nombreArchivo = "IndResumenMA";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";

                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";

                usarConexion = true;   
                consulta = true; 
                                 
            }                                
            
            //REPORTE DE TIPOS CAUSA
            if(tipoReporte.intValue() == ServletReporte.reporteTiposCausa){               
                                   
                nombreArchivoJasper = "SigeItrRepTiposCausa";                

                nombreArchivo = "TiposCausa";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";

                usarConexion = true;   
                consulta = true;   
                                 
            }   
            
            //REPORTE DE TIPOS DAÑO
            if(tipoReporte.intValue() == ServletReporte.reporteTiposDano){               
                                   
                nombreArchivoJasper = "SigeItrRepTiposDano";                

                nombreArchivo = "TiposDaño";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                

                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";

                usarConexion = true;   
                consulta = true; 
                                 
            }    
            
            //REPORTE DE TIPOS PROTECCION
            if(tipoReporte.intValue() == ServletReporte.reporteTiposProteccion){               
                                   
                nombreArchivoJasper = "SigeItrRepTiposProteccion";                

                nombreArchivo = "TiposProteccion";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";

                usarConexion = true;   
                consulta = true; 
                                 
            }  
            
            //REPORTE DE TIPOS OFICINA
            if(tipoReporte.intValue() == ServletReporte.reporteTiposOficina){               
                                   
                nombreArchivoJasper = "SigeItrRepTiposOficina";                

                nombreArchivo = "TiposOficina";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";

                usarConexion = true;   
                consulta = true;  
                                 
            }        
            
            
            //REPORTE DE TIPOS ANIMAL
            if(tipoReporte.intValue() == ServletReporte.reporteTiposAnimal){               
                                   
                nombreArchivoJasper = "SigeItrRepTiposAnimal";                

                nombreArchivo = "TiposAnimal";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                 nombreArchivoJasper += ".jasper";
                     
                 formato = "PDF";
    
                 usarConexion = true;   
                 consulta = true;  
                                 
            }  
            
            //REPORTE DE TIPOS CORREO
            if(tipoReporte.intValue() == ServletReporte.reporteTiposCorreo){               
                                   
                nombreArchivoJasper = "SigeItrRepTiposCorreo";                

                nombreArchivo = "TiposCorreo";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";
                
                usarConexion = true;   
                consulta = true;   
                                 
            }    
            
            //REPORTE DE INDICE DE CONTINUIDAD DEL SERVICIO
            if(tipoReporte.intValue() == ServletReporte.reporteIndiceContinuidadServicio)
            {
                nombreArchivoJasper = "REPORTEINDICECONTINUIDAD";                

                //OBTIENE BEANS      
                Integer mesInicial = (Integer)sessionMap.remove("mesInicial");
                Integer mesFinal = (Integer)sessionMap.remove("mesFinal");
                Integer AAinicial = (Integer)sessionMap.remove("AAinicial");
                Integer AAfinal = (Integer)sessionMap.remove("AAfinal");                         
                            

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE    
                parametrosReporte.put("mesInicial",1);
                parametrosReporte.put("mesFinal",1);
                parametrosReporte.put("AAinicial",2015);
                parametrosReporte.put("AAfinal",2015);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);

                /*
                 * parametrosReporte.put("mesInicial",mesInicial);
                parametrosReporte.put("mesFinal",mesFinal);
                parametrosReporte.put("AAinicial",AAinicial);
                parametrosReporte.put("AAfinal",AAfinal);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);

                 */
                
                nombreArchivo = "REPORTEINDICECONTINUIDAD123";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".xls";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "XLS";
                
                usarConexion = true;   
                consulta = true;   
                                             	
            }
            
            
            //REPORTE DE TIPOS PROVOLMA
            if(tipoReporte.intValue() == ServletReporte.reporteTiposProvolma){               
                                   
                nombreArchivoJasper = "SigeItrRepTiposProvolma";                

                nombreArchivo = "TiposProvolma";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";
                
                usarConexion = true;   
                consulta = true;  
                                 
            }         
            
            //REPORTE DE TIPOS MATERIAL
            if(tipoReporte.intValue() == ServletReporte.reporteTiposMaterial){               

                nombreArchivoJasper = "SigeItrRepTiposMaterial";                
                
                nombreArchivo = "TiposMaterial";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";
                
                usarConexion = true;   
                consulta = true;   
                                 
            }    
            
            //REPORTE DE TIPOS VOLTAJE
            if(tipoReporte.intValue() == ServletReporte.reporteTiposVoltaje){               
                                   
                nombreArchivoJasper = "SigeItrRepTiposVoltaje";                

                nombreArchivo = "TiposVoltaje";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                nombreArchivo += fechaActualSTR + ".pdf";
                
                nombreArchivoJasper += ".jasper";
                     
                formato = "PDF";
                
                usarConexion = true;   
                consulta = true;   
                                 
            }        
            
            
            //REPORTE DE TRAFOS INSTALADOS Y RETIRADOS  POR SECCION
            if(tipoReporte.intValue() == ServletReporte.reporteTrafosInstRetSeccion){               
                                   
                nombreArchivoJasper = "";                
                                   
                //OBTIENE BEANS      
                Integer codigoOficina = (Integer)sessionMap.remove("codigoOficina");
                String nombreOficina = (String)sessionMap.remove("nombreOficina");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");                         
                Integer formato2 = (Integer)sessionMap.remove("formato");
                
                String itemsSubestacionSTR = (String)sessionMap.remove("itemsSubestacionSTR");
                String itemsCircuitoSTR = (String)sessionMap.remove("itemsCircuitoSTR");                
                String itemsSTR = (String)sessionMap.remove("itemsSTR");
                Integer cantidadItems = (Integer)sessionMap.remove("cantidadItems");         
                            

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE    
                parametrosReporte.put("codigoOficina",codigoOficina);
                parametrosReporte.put("nombreOficina",nombreOficina);
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("itemsSubestacionSTR",itemsSubestacionSTR);
                parametrosReporte.put("itemsCircuitoSTR",itemsCircuitoSTR);                                
                parametrosReporte.put("itemsSTR",itemsSTR);
                parametrosReporte.put("cantidadItems",cantidadItems);  
                
                String restriccionSQL = null;
                if(itemsSubestacionSTR.equals("0") && itemsCircuitoSTR.equals("0") && itemsSTR.equals("0"))
                    restriccionSQL = "";
                else
                    restriccionSQL = this.getSQLReporteTrafosInstRetirSeccion(itemsSubestacionSTR, itemsCircuitoSTR, itemsSTR);
                parametrosReporte.put("restriccionSQL",restriccionSQL);
                
                nombreArchivoJasper = "SigeItrRepTrafosInstaladosRetiradosSeccion";                      
                
                nombreArchivo = "InterrupTransformadores";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                 
                nombreArchivoJasper += ".jasper";
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += fechaActualSTR + ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += fechaActualSTR + ".xls";
                    formato = "XLS";
                }
                
                
                usarConexion = true;   
                consulta = true; 
                                 
            }    
            
            //REPORTE DE DE INTERRUPCIONES GENERADAS POR ANIMALES
            if(tipoReporte.intValue() == ServletReporte.reporteInterrupcionesPorAnimales){                         
           
                //OBTIENE BEANS      
                String nivelRed = (String)sessionMap.remove("nivelRedActual");  
                Integer animal= (Integer)sessionMap.remove("animal");  
                String fechaInicio= (String)sessionMap.remove("fechaInicio");  
                String fechaFinal= (String)sessionMap.remove("fechaFinal");
                Integer region = (Integer)sessionMap.remove("region");
                Integer subRegion = (Integer)sessionMap.remove("subregion");
                Integer subEstacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");
                Long seccion= (Long)sessionMap.remove("seccion");
                Integer formato2=(Integer)sessionMap.remove("formato");  
                
//              HASHMAP PARA LOS PARAMETROS DEL REPORTE 
                parametrosReporte.put("animal",animal);
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFin",fechaFinal);
                parametrosReporte.put("region",region);
                
                
                if(nivelRed.equals("subregion")){
                    parametrosReporte.put("subregion",subRegion);
                    parametrosReporte.put("nivelRed","Subregión");
                    nombreArchivoJasper = "SigeItrRepCausaAnimales-SubRegion";
                }else{
                    if(nivelRed.equals("subestacion")){
                        parametrosReporte.put("subregion",subRegion);
                        parametrosReporte.put("subestacion",subEstacion);
                        parametrosReporte.put("nivelRed","Subestación");
                        nombreArchivoJasper = "SigeItrRepCausaAnimales-SubEstacion";
                    }else{
                        if(nivelRed.equals("circuito")){
                            parametrosReporte.put("subregion",subRegion);
                            parametrosReporte.put("subestacion",subEstacion);
                            parametrosReporte.put("circuito",circuito);
                            parametrosReporte.put("nivelRed","Circuito");
                            nombreArchivoJasper = "SigeItrRepCausaAnimales-Circuito";
                        }else{
                            if(nivelRed.equals("seccion")){
                                parametrosReporte.put("subregion",subRegion);
                                parametrosReporte.put("subestacion",subEstacion);
                                parametrosReporte.put("circuito",circuito);
                                parametrosReporte.put("nivelRed","Sección");
                                parametrosReporte.put("seccion",new Integer(Integer.parseInt(seccion.toString())));
                            }else{
                                parametrosReporte.put("nivelRed","Región");
                                nombreArchivoJasper = "SigeItrRepCausaAnimales-Region";
                            }
                        }
                    }
                }      
                
                nombreArchivoJasper += ".jasper";
                
                nombreArchivo = "InterrupCausaAnimales";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += fechaActualSTR + ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += fechaActualSTR + ".xls";
                    formato = "XLS";
                }
                
                
                usarConexion = true;   
                consulta = true; 
                                 
            }
            
            ///////////////////////////////////
            //REPORTE DE DE INTERRUPCIONES GENERADAS POR CLIENTES AFECTADOS POR EVENTOS
            if(tipoReporte.intValue() == ServletReporte.reporteClientesAfectadosPorEvento){               
                
            
                //OBTIENE BEANS      
                Integer medidor = (Integer)sessionMap.remove("medidor");
                Integer pueblo = (Integer)sessionMap.remove("pueblo");
                Integer calle = (Integer)sessionMap.remove("calle");
                Integer poste = (Integer)sessionMap.remove("poste");
                Integer secue = (Integer)sessionMap.remove("secue");
                Integer nivelRed = (Integer)sessionMap.remove("nivelRedActual");   
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");  
                
              
                
               // Date fechaInicio= (Date)sessionMap.remove("fechaInicio");  
               // Date fechaFinal= (Date)sessionMap.remove("fechaFinal");
                Integer region = (Integer)sessionMap.remove("region");
                Integer subRegion = (Integer)sessionMap.remove("subregion");
                Integer subEstacion = (Integer)sessionMap.remove("subestacion");
                String sqlFiltro = (String)sessionMap.remove("sqlFiltro");
                
                Integer circuitoP = null;
                if(sessionMap.get("circuito")!=null){
                 circuitoP = (Integer) sessionMap.remove("circuito"); 
                }
                
                Long seccionP = null;
                if(sessionMap.get("seccion")!=null){
                	seccionP =(Long) sessionMap.remove("seccion");
                	}
                
                Integer formato2=(Integer)sessionMap.remove("formato");  
                
//              HASHMAP PARA LOS PARAMETROS DEL REPORTE 
               
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");
                
                parametrosReporte.put("psqlFiltro",sqlFiltro);
                parametrosReporte.put("pMedidor",medidor);
                parametrosReporte.put("pLocaFallaPueblo",pueblo);
                parametrosReporte.put("pLocaFallaCalle",calle);
                parametrosReporte.put("pLocaFallaPoste",poste);
                parametrosReporte.put("pLocaFallaSecuencia",secue);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);
                
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                
                //parametrosReporte.put("pFechaInicio",Fechas.dateToString(fechaInicio, "yyyyMMdd"));
                //parametrosReporte.put("pFechaFin",Fechas.dateToString(fechaFinal, "yyyyMMdd")); 
                parametrosReporte.put("pRegion",region);
                parametrosReporte.put("pSubRegion",subRegion);
                parametrosReporte.put("pSubEstacion",subEstacion);
                parametrosReporte.put("pCircuito", circuitoP);
                parametrosReporte.put("pTipoNivelRed",nivelRed);
                
               

                
                nombreArchivoJasper = "SigeItrRepInterrupcionClientesAfectadosEventos";
                
                if(nivelRed.equals(new Integer(1))){
	               parametrosReporte.put("pSeccion",seccionP);
	            }else
	            	if(nivelRed.equals(new Integer(0))){
	            		parametrosReporte.put("pSeccion",null); 
                }
                
                
                nombreArchivo = "SigeItrRepInterrupcionClientesAfectadosEventos";
                Date fechaActual = new Date();                              
                String fechaActualSTR = sf.format(fechaActual);
                
                nombreArchivoJasper += ".jasper";
                
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += fechaActualSTR + ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += fechaActualSTR + ".xls";
                    formato = "XLS";
                }
                
                
                usarConexion = true;   
                consulta = true; 
            }
            
//          REPORTE DE DE INTERRUPCIONES GENERADAS POR MATERIAL POSTES
            if(tipoReporte.intValue() == ServletReporte.reporteMaterialPostes){               
                                                 
                //OBTIENE BEANS      
                Integer movimiento = (Integer)sessionMap.remove("tipoMovimiento");
                Integer calidad = (Integer)sessionMap.remove("tipoCalidad");
                String nivelRed = ((Integer)sessionMap.remove("nivelRedActual")).toString();   
                Date fechaInicio= (Date)sessionMap.remove("fechaInicio");  
                Date fechaFinal= (Date)sessionMap.remove("fechaFinal");
                Integer region = (Integer)sessionMap.remove("region");
                Integer subRegion = (Integer)sessionMap.remove("subregion");
                Integer subEstacion = (Integer)sessionMap.remove("subestacion");
                Integer circuito = (Integer)sessionMap.remove("circuito");
                Long seccion= (Long)sessionMap.remove("seccion");
                Integer formato2=(Integer)sessionMap.remove("formato"); 
                
//              HASHMAP PARA LOS PARAMETROS DEL REPORTE 
                parametrosReporte.put("pFechaInicio",fechaInicio);
                parametrosReporte.put("pFechaFin",fechaFinal);
                if(region.equals(new Integer(-1))){
                	parametrosReporte.put("pRegion",null);
                }else{
                	parametrosReporte.put("pRegion",region);
                }
                parametrosReporte.put("pMovimiento",movimiento);
                
                parametrosReporte.put("pCalidad",calidad);
                
                
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);
                
                
                if(nivelRed.equals("0")){
                	parametrosReporte.put("pSubregion",null);
                	parametrosReporte.put("pSubestacion",null);
                	parametrosReporte.put("pCircuito",null);
                	parametrosReporte.put("pSeccion",null);
                	parametrosReporte.put("pTipoNivelRed",new Integer(0));
                	nombreArchivoJasper = "SigeItrRepInterrupcionInformePostes";
                }
                if(nivelRed.equals("1")){
                	if(subRegion.equals(new Integer(-1))){
                		parametrosReporte.put("pSubregion",null);
                    }else{
                    	parametrosReporte.put("pSubregion",subRegion);
                    }
                	parametrosReporte.put("pSubestacion",null);
                	parametrosReporte.put("pCircuito",null);
                	parametrosReporte.put("pSeccion",null);
	                parametrosReporte.put("pTipoNivelRed",new Integer(1));
	                nombreArchivoJasper = "SigeItrRepInterrupcionInformePostes";
	            }else{
	            	if(nivelRed.equals("2")){
	            		parametrosReporte.put("pSubregion",subRegion);
	            		if(subEstacion.equals(new Integer(-1))){
	            			parametrosReporte.put("pSubestacion",null);
	                    }else{
	                    	parametrosReporte.put("pSubestacion",subEstacion);
	                    }
	            		parametrosReporte.put("pCircuito",null);
	                	parametrosReporte.put("pSeccion",null);
		                parametrosReporte.put("pTipoNivelRed",new Integer(2));
		                nombreArchivoJasper = "SigeItrRepInterrupcionInformePostes";
	            	}else{
	            		if(nivelRed.equals("3")){
			                parametrosReporte.put("pSubregion",subRegion);
			                parametrosReporte.put("pSubestacion",subEstacion);
			                if(circuito.equals(new Integer(-1))){
			                	parametrosReporte.put("pCircuito",null);
		                    }else{
		                    	parametrosReporte.put("pCircuito",circuito);
		                    }
		                	parametrosReporte.put("pSeccion",null);
			                parametrosReporte.put("pTipoNivelRed",new Integer(3));
			                nombreArchivoJasper = "SigeItrRepInterrupcionInformePostes";
			            }else{
			                if(nivelRed.equals("4")){
			                    parametrosReporte.put("pSubregion",subRegion);
			                    parametrosReporte.put("pSubestacion",subEstacion);
			                    parametrosReporte.put("pCircuito",circuito);
			                    parametrosReporte.put("pTipoNivelRed",new Integer(4));
			                    if(seccion.equals(new Long(-1))){
			                    	parametrosReporte.put("pSeccion",null);
			                    }else{
			                    	parametrosReporte.put("pSeccion",new Integer(Integer.parseInt(seccion.toString())));
			                    }
			                    nombreArchivoJasper = "SigeItrRepInterrupcionInformePostes"; 
			                }
			            }
	            	}
	            }
                nombreArchivoJasper = "SigeItrRepInterrupcionInformePostes"; 
                nombreArchivo = "SigeItrRepInterrupcionInformePostes";
                
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                
                nombreArchivoJasper += ".jasper";
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += fechaActualSTR + ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += fechaActualSTR + ".xls";
                    formato = "XLS";
                }
                
                
                usarConexion = true;   
                consulta = true; 
                                 
            }
            
         
//          REPORTE DE DE INTERRUPCIONES GENERADOS POR TIEMPOS DE VEHICULOS
            if(tipoReporte.intValue() == ServletReporte.reporteTiempoVehiculo){               
                                   
                                              
                //OBTIENE BEANS      
                Integer vehiculo = (Integer)sessionMap.remove("vehiculo"); 
                Date fechaInicio= (Date)sessionMap.remove("fechaInicio");  
                Date fechaFinal= (Date)sessionMap.remove("fechaFinal");
                Integer formato2=(Integer)sessionMap.remove("formato");  
                
//              HASHMAP PARA LOS PARAMETROS DEL REPORTE 
               
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");
                parametrosReporte.put("pVehiculo",vehiculo);
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);
                
                
                SimpleDateFormat sf2 = new SimpleDateFormat();
                sf2.applyPattern("dd/MM/yyyy");                                
                String fechaInicio2 = sf.format(fechaInicio);
                String fechaFinal2 = sf.format(fechaFinal);
                
                
                parametrosReporte.put("pFechaInicio",fechaInicio2);
                parametrosReporte.put("pFechaFin",fechaFinal2); 
                
                nombreArchivoJasper = "SigeItrRepInterrupcionTiemposVehiculos";
                
                nombreArchivo = "SigeItrRepInterrupcionTiemposVehiculos";
                Date fechaActual = new Date();                              
                String fechaActualSTR = sf.format(fechaActual);
                
                nombreArchivoJasper += ".jasper";
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += fechaActualSTR + ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += fechaActualSTR + ".xls";
                    formato = "XLS";
                }
                
                
                usarConexion = true;   
                consulta = true;  
                                 
            }
            //////////////////////////////////
            
            //REPORTE DE TRAFOS INSTALADOS Y RETIRADOS
            if(tipoReporte.intValue() == ServletReporte.reporteTrafosInstRet){                                             
                  
                parametrosReporte.put("SUBREPORT_DIR",servletJasperPath);    
                                              
                //OBTIENE BEANS      
                String nivelRedActual = (String)sessionMap.remove("nivelRedActual");
                String fechaInicio = (String)sessionMap.remove("fechaInicio");
                String fechaFinal = (String)sessionMap.remove("fechaFinal");                
                Integer tipoTrafo = (Integer)sessionMap.remove("tipoTrafo");            
                Integer formato2 = (Integer)sessionMap.remove("formato");
                
                Integer region = (Integer)sessionMap.remove("region");
                Integer subregion = (Integer)sessionMap.remove("subregion");
                Integer subestacion = (Integer)sessionMap.remove("subestacion");
                
                String itemsSTR = (String)sessionMap.remove("itemsSTR");
                Integer cantidadItems = (Integer)sessionMap.remove("cantidadItems");         
                            

                //HASHMAP PARA LOS PARAMETROS DEL REPORTE                 
                parametrosReporte.put("fechaInicio",fechaInicio);
                parametrosReporte.put("fechaFinal",fechaFinal);
                parametrosReporte.put("tipoTrafo",tipoTrafo);
                parametrosReporte.put("region",region);                                
                parametrosReporte.put("subregion",subregion);
                parametrosReporte.put("subestacion",subestacion);   
                parametrosReporte.put("itemsSTR",itemsSTR);
                parametrosReporte.put("cantidadItems",cantidadItems);                
                
                if(nivelRedActual.equals("region")){
                    if(tipoTrafo.equals(ReporteTrafoInstRetController.TRAFO_INSTALADO))
                        nombreArchivoJasper = "SigeItrRepTrafosInstaladosRegion";
                    else
                        nombreArchivoJasper = "SigeItrRepTrafosRetiradosRegion";
                }
                if(nivelRedActual.equals("subregion")){
                    if(tipoTrafo.equals(ReporteTrafoInstRetController.TRAFO_INSTALADO))
                        nombreArchivoJasper = "SigeItrRepTrafosInstaladosSubregion";
                    else
                        nombreArchivoJasper = "SigeItrRepTrafosRetiradosSubregion";
                }   
                if(nivelRedActual.equals("subestacion")){
                    if(tipoTrafo.equals(ReporteTrafoInstRetController.TRAFO_INSTALADO))
                        nombreArchivoJasper = "SigeItrRepTrafosInstaladosSubestacion";
                    else
                        nombreArchivoJasper = "SigeItrRepTrafosRetiradosSubestacion";
                }                 
                if(nivelRedActual.equals("circuito")){
                    if(tipoTrafo.equals(ReporteTrafoInstRetController.TRAFO_INSTALADO))
                        nombreArchivoJasper = "SigeItrRepTrafosInstaladosCircuito";
                    else
                        nombreArchivoJasper = "SigeItrRepTrafosRetiradosCircuito";
                }   

                
                nombreArchivo = "InterrupTransfInstRet";
                Date fechaActual = new Date();
                SimpleDateFormat sf = new SimpleDateFormat();
                sf.applyPattern("dd-MM-yyyy");                                
                String fechaActualSTR = sf.format(fechaActual);
                
                nombreArchivoJasper += ".jasper";
                
                if(formato2.equals(UtilidadesFaces.FORMATO_PDF)){
                    nombreArchivo += fechaActualSTR + ".pdf";
                    formato = "PDF";
                }
                if(formato2.equals(UtilidadesFaces.FORMATO_XLS)){
                    nombreArchivo += fechaActualSTR + ".xls";
                    formato = "XLS";
                }
                
                usarConexion = true;   
                consulta = true; 
                                 
            }     
            
            if(consulta){//Solo cuando la consulta contiene datos se manda a generar el reporte
                
                if(formato.equals("PDF")){
                    response.setContentType("application/pdf");
                }
                else{
                    response.setContentType("application/vnd.ms-excel");
                }
                ServletOutputStream servletOutputStream = response.getOutputStream();
                
                //**********INDICA CUAL ES EL JASPER QUE SE VA A UTILIZAR Y EL NOMBRE QUE TENDRA EL ARCHIVO**********//
                reportStream = this.getServletConfig().getServletContext().getResourceAsStream(servletJasperPath + nombreArchivoJasper);
                
                response.setHeader("Content-disposition","attachment;filename=" + nombreArchivo);
               
                //**********MANDA A GENERAR EL PDF O XLS**********//
                if(usarConexion){
                    //**********OBTIENE CONEXION A LA BASE DE DATOS**********//
                     DataSource data;
                     data = (DataSource)Utilidades.getContextVariable("jdbc/interrupciones2DS");

                     Connection conn = data.getConnection();
                     if(formato.equals("PDF")){
                         JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametrosReporte, conn);
                     }else{
                         this.generarArchivoXlS(reportStream, parametrosReporte, conn, servletOutputStream);
                     }
                     conn.close();

                 }else{
                     if(formato.equals("PDF")){
                         JasperRunManager.runReportToPdfStream(reportStream, servletOutputStream, parametrosReporte, jrDataSource);
                     }else{
                         this.generarArchivoXlS(reportStream, parametrosReporte, jrDataSource, servletOutputStream);
                     }
                 }
                 servletOutputStream.flush();
                 servletOutputStream.close();
                 FacesContext.getCurrentInstance().responseComplete();
             }
             
         }catch(Exception e){
             e.printStackTrace();
         }     
    }
    
    private String getSQLReporteIndicadorCircuito(String subestacionesSTR, String circuitosSTR){
        StringBuffer resultado = new StringBuffer( " AND (");
        String codSubestaciones [] = subestacionesSTR.split(",");
        String codCircuitos [] = circuitosSTR.split(",");
        for(int i = 0; i < codSubestaciones.length; i++){
            resultado.append(" (indicador.sub = " + codSubestaciones[i].trim() +  " AND indicador.cir = " + codCircuitos[i].trim() +  " ) OR");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 2)).append(") ");
        return resultado.toString();
    }
    
    private String getSQLReporteIndicadorSeccion(String subestacionesSTR, String circuitosSTR, String seccionesSTR){
        StringBuffer resultado = new StringBuffer( " AND (");
        String codSubestaciones [] = subestacionesSTR.split(",");
        String codCircuitos [] = circuitosSTR.split(",");
        String codSecciones [] = seccionesSTR.split(",");
        for(int i = 0; i < codSubestaciones.length; i++){
            resultado.append(" (indicador.sub = " + codSubestaciones[i].trim() +  " AND indicador.cir = " + codCircuitos[i].trim() + " AND indicador.seccion = " + codSecciones[i].trim() + " ) OR");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 2)).append(") ");
        return resultado.toString();
    } 
    
    private String getSQLReporteTrafosInstRetirSeccion(String subestacionesSTR, String circuitosSTR, String seccionesSTR){
        StringBuffer resultado = new StringBuffer( " AND (");
        String codSubestaciones [] = subestacionesSTR.split(",");
        String codCircuitos [] = circuitosSTR.split(",");
        String codSecciones [] = seccionesSTR.split(",");
        for(int i = 0; i < codSubestaciones.length; i++){
            resultado.append(" (interup.sub = " + codSubestaciones[i].trim() +  " AND interup.cir = " + codCircuitos[i].trim() +  " AND interup.seccion = " + codSecciones[i].trim() + " ) OR");
        }
        resultado = new StringBuffer(resultado.substring(0,resultado.length() - 2)).append(") ");
        return resultado.toString();
    }
    

//  public void generarArchivoXlS(JasperReport jasperReport, HashMap parametros,Connection conexionBD, OutputStream outputStream) throws JRException{
    @SuppressWarnings("unused")
    public void generarArchivoXlS(InputStream reportStream, HashMap parametros,Connection conexionBD, OutputStream outputStream) throws JRException{


        //EN EL CASO DE EXCEL, SE IGNORA LA PAGINACION PARA QUE SALGA EN UNA SOLA "TIRA".
        parametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperPrint jPrint = JasperFillManager.fillReport(reportStream,parametros,conexionBD);

        //CODIGO PARA EXCEL:
        JRXlsExporter exportadorXLS = new JRXlsExporter();
        exportadorXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,jPrint);
        exportadorXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,outputStream);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
       // exportadorXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
        exportadorXLS.exportReport();
        
        if(this.reporteTipoPorCircuito.intValue() == ServletReporte.reportePorCircuitoMenorCincoMin){
            
            FileOutputStream elFichero = null;
            try{
                File f1 = new File (Utilidades.getContextVariable(new InitialContext(), "attachment").toString());
                boolean b = f1.delete ();
                elFichero = new FileOutputStream(Utilidades.getContextVariable(new InitialContext(), "attachment").toString(), true);
                JRXlsExporter exportadorXLS2 = new JRXlsExporter();
                exportadorXLS2.setParameter(JRXlsExporterParameter.JASPER_PRINT,jPrint);
                exportadorXLS2.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,elFichero);
                exportadorXLS2.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
                exportadorXLS2.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
                exportadorXLS2.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
                exportadorXLS2.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
                exportadorXLS2.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
               // exportadorXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
                exportadorXLS2.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
                exportadorXLS2.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
                exportadorXLS2.exportReport();
                
            }catch (IOException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();   
            }finally{
                try{
                    if(elFichero != null)
                        elFichero.close();
                }catch(Exception e){
                    /*nada*/
                   
                }
            }
        }
        
    }   
    

//  public void generarArchivoXlS(JasperReport jasperReport, HashMap parametros,Connection conexionBD, OutputStream outputStream) throws JRException{
    public void generarArchivoXlS(InputStream reportStream, HashMap parametros,JRDataSource jrDataSource, OutputStream outputStream) throws JRException{


        //EN EL CASO DE EXCEL, SE IGNORA LA PAGINACION PARA QUE SALGA EN UNA SOLA "TIRA".
        parametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        JasperPrint jPrint = JasperFillManager.fillReport(reportStream,parametros,jrDataSource);

        //CODIGO PARA EXCEL:
        JRXlsExporter exportadorXLS = new JRXlsExporter();
        exportadorXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,jPrint);
        exportadorXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,outputStream);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
       // exportadorXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
        exportadorXLS.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
        exportadorXLS.exportReport();

    }   
    
    
    /**
    *
    * @param jasperReport REPORTE JASPER COMPILADO.
    * @param parametros PARAMETROS DEL REPORTE.
    * @param conexionBD CONEXION A LA BASE DE DATOS.
    * @param outputStream Stream de salida
    * @throws JRException EXCEPCION JASPER    
    */
   public void generarArchivoXls(JasperReport jasperReport, HashMap parametros, java.sql.Connection conexionBD, OutputStream outputStream) throws JRException{


       //EN EL CASO DE EXCEL, SE IGNORA LA PAGINACION PARA QUE SALGA EN UNA SOLA "TIRA".
       parametros.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
       JasperPrint jPrint = JasperFillManager.fillReport(jasperReport,parametros,conexionBD);

       //CODIGO PARA EXCEL:
       JRXlsExporter exportadorXLS = new JRXlsExporter();
       exportadorXLS.setParameter(JRXlsExporterParameter.JASPER_PRINT,jPrint);
       exportadorXLS.setParameter(JRXlsExporterParameter.OUTPUT_STREAM,outputStream);
       exportadorXLS.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE);
       exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
       exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
       exportadorXLS.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE); 
       exportadorXLS.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.TRUE);
      // exportadorXLS.setParameter(JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
       exportadorXLS.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
       exportadorXLS.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.TRUE);
       exportadorXLS.exportReport();

   }
    


    
}