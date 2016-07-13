package modelos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.SQLExcepcion;
import utilities.Conexion;

public class MFeedBackAplicado implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idFBA;
	private MFeedBack idFB;
	private MUsuario idUCalificado;
	private String calificacionOFBA;
	private MUsuario idUCalificador;
	private Integer estatus;
	
	public MFeedBackAplicado() {
		idFBA = new Long(0);
		idFB = null;
		idUCalificado = null;
		calificacionOFBA = "";
		idUCalificador = null;
		estatus = 0;
		
	}

	public Long getIdFBA() {
		return idFBA;
	}

	public void setIdFBA(Long idFBA) {
		this.idFBA = idFBA;
	}

	public MFeedBack getIdFB() {
		return idFB;
	}

	public void setIdFB(MFeedBack idFB) {
		this.idFB = idFB;
	}

	public MUsuario getIdUCalificado() {
		return idUCalificado;
	}

	public void setIdUCalificado(MUsuario idUCalificado) {
		this.idUCalificado = idUCalificado;
	}

	public String getCalificacionOFBA() {
		return calificacionOFBA;
	}

	public void setCalificacionOFBA(String calificacionOFBA) {
		this.calificacionOFBA = calificacionOFBA;
	}

	public MUsuario getIdUCalificador() {
		return idUCalificador;
	}

	public void setIdUCalificador(MUsuario idUCalificador) {
		this.idUCalificador = idUCalificador;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MFeedBackAplicado buscarFeedBackAplicado(MFeedBackAplicado val)
	{	MFeedBackAplicado ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from feedback_aplicado where idfba = ?");
			ps.setLong(1, val.getIdFBA());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarFeedBackAplicado(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

	
		return  ret;
	}
	
	public List<MFeedBackAplicado> buscarFeedBackAplicados()
	{	List<MFeedBackAplicado> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from feedback_aplicado");
			ResultSet rs = ps.executeQuery();
			MFeedBackAplicado m = new MFeedBackAplicado();
			ret = new ArrayList<MFeedBackAplicado>();
			while (rs.next())
			{	m = cargarFeedBackAplicado(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarFeedBackAplicado(MFeedBackAplicado val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into feedback_aplicado values (?,?,?,?,?,?)");
					ps.setLong(1, val.getIdFBA());
					ps.setLong(2, val.getIdFB().getIdFB());
					ps.setLong(3, val.getIdUCalificado().getIdU());
					ps.setString(4, val.getCalificacionOFBA());
					ps.setLong(5, val.getIdUCalificador().getIdU());
					ps.setInt(6, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update feedback_aplicado set "
							+ " idfb = ?,"
							+ " iducalificado = ?,"
							+ " calificacionofba = ?,"
							+ " iducalificador = ?,"
							+ " estatus = ?"
							+ " where idfba = ?");
					ps.setLong(6, val.getIdFBA());
					ps.setLong(1, val.getIdFB().getIdFB());
					ps.setLong(2, val.getIdUCalificado().getIdU());
					ps.setString(3, val.getCalificacionOFBA());
					ps.setLong(4, val.getIdUCalificador().getIdU());
					ps.setInt(5, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update feedback_aplicado set estatus = -1 where idfba = ?");
					ps.setLong(1, val.getIdFBA());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
	
		return ret;
	}
	
	public MFeedBackAplicado cargarFeedBackAplicado(ResultSet rs)
	{	MFeedBackAplicado ret = null;
	
		if (rs != null)
		{	ret = new MFeedBackAplicado();
			try {
				ret.setIdFBA(rs.getLong(1));
				
				MFeedBack fb = new MFeedBack();
				fb.setIdFB(rs.getLong(2));
				fb = fb.buscarFeedBack(fb);
				ret.setIdFB(fb);
				
				MUsuario u = new MUsuario();
				u.setIdU(rs.getLong(3));	
				u = u.buscarUsuario(u);
				ret.setIdUCalificado(u);
				
				ret.setCalificacionOFBA(rs.getString(4));
				
				u.setIdU(rs.getLong(5));
				u = u.buscarUsuario(u);
				ret.setIdUCalificador(u);
				
				ret.setEstatus(rs.getInt(6));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}

}
