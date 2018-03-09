package cr.go.ice.interrupciones.web.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cr.go.ice.interrupciones.BO.EmpleadoBO;
import cr.go.ice.interrupciones.domain.Empleado;
import cr.go.ice.interrupciones.web.filters.EmpleadoFilterException;
import cr.go.ice.cia.dominio.UsuarioCia;

/**
 * <p>
 * Clase cr.go.ice.interrupciones.web.filters.InterrupcionesLoginFilter.java
 * </p>
 * <p>
 * Modulo (subsistema): InterrupcionesWeb
 * </p>
 * <p>
 * Descricion de <code>InterrupcionesLoginFilter.java</code> Provee un filtro
 * para la carga de objetos requeridos en el momento de la autenticacion del
 * usuario.
 * </p>
 * <p>
 * Fecha creación: 17/12/2008
 * </p>
 * <p>
 * Ultima actualización: 17/12/2008
 * </p>
 * 
 * @author Vista Verde Tecnologia (David)
 * @version 1.1
 */
public class InterrupcionesLoginFilter implements Filter {

	private EmpleadoBO empleadoBO;

	/**
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		// Cargando instancia de empleadoBO desde el contexto de Spring
		WebApplicationContext springContext = WebApplicationContextUtils
				.getWebApplicationContext(filterConfig.getServletContext());
		this.empleadoBO = (EmpleadoBO) springContext.getBean("empleadoBO");
	}

	public EmpleadoBO getEmpleadoBO() {
		return empleadoBO;
	}

	public void setEmpleadoBO(EmpleadoBO empleadoBO) {
		this.empleadoBO = empleadoBO;
	}

	/**
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 *      javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@SuppressWarnings("unused")
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		try {

			HttpServletRequest requestr = (HttpServletRequest) request;
			HttpSession session = requestr.getSession(true);
			UsuarioCia usuarioCia = (UsuarioCia) session.getAttribute(UsuarioCia.KEY_USUARIO_CIA);

			if (usuarioCia == null) // Si no tenemos el usuarioCia en session,
									// es que aún no se ha autenticado
			{
				chain.doFilter(request, response); // Continuamos para permitir
													// logon del CIA
				return;

			} else {

				Empleado empleadoInterrup = (Empleado) session.getAttribute("empleado");
				if (empleadoInterrup == null) {
					try {
						empleadoInterrup = new Empleado();
						empleadoInterrup.setCedula((usuarioCia.getCedula()));
						empleadoInterrup = empleadoBO.buscar(usuarioCia.getCedula());

					} catch (Exception e) {

						e.printStackTrace();
						this.forward(request, response,
								"ERROR: Falló la busqueda del usuario autenticado. ==> " + e.getMessage());
						return;
					}

					if (empleadoInterrup.getCedula() == null) {

						this.forward(request, response, "El usuario autenticado no existe en Interrupcinoes");
						return;

					} else {

						session.setAttribute("empleado", empleadoInterrup);
					}

					if (empleadoInterrup.getCongelado() == null
							|| empleadoInterrup.getCongelado().intValue() == Empleado.ESTADO_CONGELADO) {
						this.forward(request, response,
								"El usuario autenticado no se encuentra activo en Interrupciones");
						return;
					} else {

						session.setAttribute("empleado", empleadoInterrup);
					}

				}
			}

		} catch (Exception e) {

			System.out.println(" ----------------------------------------------------------------------------------");
			System.out.println("  ERROR: El usuario autenticado no existe en la Base de Datos de Interrupciones.");
			System.out.println(" ----------------------------------------------------------------------------------");
			this.forward(request, response, "ERROR FATAL: " + e.getMessage());
			return;
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		this.empleadoBO = null;

	}

	private void forward(ServletRequest req, ServletResponse res, String mensaje) throws ServletException, IOException {

		System.err.println(mensaje);

		EmpleadoFilterException exception = new EmpleadoFilterException(mensaje);
		req.setAttribute("ERROR", exception);
		System.out.println(" ----------------------------------");
		System.out.println("  ERROR FATAL: " + exception.getMessage());
		System.out.println(" ----------------------------------");
		req.getRequestDispatcher("interrupciones_error_autenticacion.jsf").forward(req, res);
	}
}