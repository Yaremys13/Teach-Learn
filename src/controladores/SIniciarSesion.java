package controladores;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.MUsuario;

/**
 * Servlet implementation class SIniciarSesion
 */
public class SIniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MUsuario u = new MUsuario();
		u.setLoginU(request.getParameter("login"));
		u.setPasswordU(request.getParameter("password"));
		
		u = u.buscarUsuarioPorCampos(u);
		
		if (u != null)
		{	HttpSession sesion = request.getSession();
			sesion.setAttribute("usuarioLogeado", u);
			response.sendRedirect("home.jsp");
			
		}
		else
		{	PrintWriter out = response.getWriter();
			out.println("<script>alert('Usuario no encontrado');location.href='index.jsp'</script>");
		}
		
	}

}
