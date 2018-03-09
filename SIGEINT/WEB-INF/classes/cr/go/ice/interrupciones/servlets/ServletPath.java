package cr.go.ice.interrupciones.servlets;

import java.io.File;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Clase cr.go.ice.interrupciones.servlets.ServletPath.java</p>
 * <p>Modulo (subsistema): Interrupciones Web</p>
 * <p>Descricion de <code>ServletPath.java</code>Servlet para el manejo del path de la aplicación.</p>
 * <p>Fecha creación: 16/11/2007</p>
 * <p>Ultima actualización: 16/11/2007</p>
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class ServletPath extends HttpServlet implements Servlet {

    /**
     * serialVersionUID <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = 4167623605982606256L;
    /**atributo <code>path</code>*/
    public static String path;
    
    /**
     * Constructor  
     */
	public ServletPath() {
		super();
	}

	/**
     * 
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
     * 
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
     * 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		    path = config.getServletContext().getRealPath("" + File.separatorChar);
	}
	

}