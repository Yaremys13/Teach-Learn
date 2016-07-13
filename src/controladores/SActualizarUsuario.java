package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelos.MDireccion;
import modelos.MFacilitador;
import modelos.MParticipante;
import modelos.MUsuario;
import utilities.Funciones;

public class SActualizarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		HttpSession sesion = request.getSession(false);
		MUsuario usu = (MUsuario) sesion.getAttribute("usuarioLogeado");
		if (usu != null)
		{	MUsuario u = new MUsuario();
			MDireccion d = new MDireccion();
			d.setIdD(new Long(1));
			d = d.buscarDireccionById(d);
			u.setIdU(usu.getIdU());
			u.setNombreU(request.getParameter("nombreU"));
			u.setApellidoU(request.getParameter("apellidoU"));
			u.setTipoDocumentoU(Long.parseLong(request.getParameter("tipoDocumentoU")));
			u.setCiU(request.getParameter("ciU"));
			u.setTelefonoU(request.getParameter("telefonoU"));
			u.setTelefonoCU(request.getParameter("telefonoCU"));
			u.setIdD(d);
			u.setEmailU(request.getParameter("emailU"));
			u.setFechaNU(Date.valueOf(Funciones.formatoFecha(request.getParameter("fechaNU"))));
			u.setLugarNU(request.getParameter("lugarNU"));
			u.setNacionalidadU(request.getParameter("nacionalidadU"));
			u.setLoginU(request.getParameter("loginU"));
			u.setPasswordU(request.getParameter("passwordU"));
			u.setFotoPU("imagenes/fotos/p_"+u.getCiU()+".jpg");
			u.setCalificacionU("0");
			u.setCodigoVerificacionU(Funciones.generarCodigoV());
			u.setCodigoVerificadoU(null);
			u.setRecibeNU(request.getParameter("recibeNU")==null?false:true);
			u.setEstatus(1);
			
			
			String res = "Error registrado Usuario";
			//TODO: manejo de mensajes
			if (u.actualizarUsuario(u, 2))
			{	if (request.getParameter("opcion").equals("F"))
				{	MFacilitador f = new MFacilitador();
					f.setIdU(u);
					f.setEstatus(1);
					if (f.actualizarFacilitador(f, 1))
					{	res = "Facilitador registrado exitosamente";
					}
					else
					{	res = "Error registrando Facilitador";
					}
				}
				else
				{	MParticipante p = new MParticipante();
					p.setIdU(u);
					p.setEstatus(1);
					if (p.actualizarParticipante(p, 1))
					{	res = "Participante registrado exitosamente";
					}
					else
					{	res = "Error registrando Participante";
					}
				}
			}
			
			out.println("<script>alert('"+res+"');</script>");
		}
		else
		{	out.println("<script>alert('Debe iniciar sesión');"
				+ "location.href='home.jsp'</script>");
			
		}
	}

}
