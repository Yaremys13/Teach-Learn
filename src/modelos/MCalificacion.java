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

public class MCalificacion implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idC;
	private MExamen idE;
	private MUsuario idUCalificadoC;
	private Float calificacionNC;
	private String calificacionLC;
	private String observacionC;
	private String soporteC;
	private Integer estatus;
	

	public MCalificacion() {
		idC = new Long(0);
		idE= null;
		idUCalificadoC = null;
		calificacionNC = null;
		calificacionLC = "";
		observacionC = "";
		soporteC = "";
		estatus = 0;
		
	}


	public Long getIdC() {
		return idC;
	}


	public void setIdC(Long idC) {
		this.idC = idC;
	}


	public MExamen getIdE() {
		return idE;
	}


	public void setIdE(MExamen idE) {
		this.idE = idE;
	}


	public MUsuario getIdUCalificadoC() {
		return idUCalificadoC;
	}


	public void setIdUCalificadoC(MUsuario idUCalificadoC) {
		this.idUCalificadoC = idUCalificadoC;
	}


	public Float getCalificacionNC() {
		return calificacionNC;
	}


	public void setCalificacionNC(Float calificacionNC) {
		this.calificacionNC = calificacionNC;
	}


	public String getCalificacionLC() {
		return calificacionLC;
	}


	public void setCalificacionLC(String calificacionLC) {
		this.calificacionLC = calificacionLC;
	}


	public String getObservacionC() {
		return observacionC;
	}


	public void setObservacionC(String observacionC) {
		this.observacionC = observacionC;
	}


	public String getSoporteC() {
		return soporteC;
	}


	public void setSoporteC(String soporteC) {
		this.soporteC = soporteC;
	}


	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public MCalificacion buscarCalificacion(MCalificacion val)
	{	MCalificacion ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from calificaciones where idc = ?");
			ps.setLong(1, val.getIdC());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarCalificacion(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;		
	}
	
	public List<MCalificacion> buscarCalificaciones()
	{	List<MCalificacion> ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from calificaciones");
			ResultSet rs = ps.executeQuery();
			MCalificacion m = new MCalificacion();
			ret = new ArrayList<MCalificacion>();
			while (rs.next())
			{	m = cargarCalificacion(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

	
	
		return ret;
	}
	
	public Boolean actualizarCalificacion(MCalificacion val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into calificaciones values (?,?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdC());
					ps.setLong(2, val.getIdE().getIdE());
					ps.setLong(3, val.getIdUCalificadoC().getIdU());
					ps.setFloat(4, val.getCalificacionNC());
					ps.setString(5, val.getCalificacionLC());
					ps.setString(6, val.getObservacionC());
					ps.setString(7, val.getSoporteC());
					ps.setInt(8, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update calificaciones set "
							+ " ide = ?,"
							+ " iducalificadoc = ?,"
							+ " calificacionnc = ?,"
							+ " calificacionlc = ?,"
							+ " observacionc = ?,"
							+ " soportec = ?,"
							+ " estatus = ?"
							+ " where idc = ?");
					ps.setLong(8, val.getIdC());
					ps.setLong(1, val.getIdE().getIdE());
					ps.setLong(2, val.getIdUCalificadoC().getIdU());
					ps.setFloat(3, val.getCalificacionNC());
					ps.setString(4, val.getCalificacionLC());
					ps.setString(5, val.getObservacionC());
					ps.setString(6, val.getSoporteC());
					ps.setInt(7, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update calificaciones set estatus = -1 where idc = ?");
					ps.setLong(1, val.getIdC());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
	
		return ret;
	}

	public MCalificacion cargarCalificacion(ResultSet rs)
	{	MCalificacion ret = null;
		
		if (rs != null)
		{	ret = new MCalificacion();
			try {
				ret.setIdC(rs.getLong(1));
				
				MExamen e = new MExamen();
				e.setIdE(rs.getLong(2));
				e = e.buscarExamen(e);
				ret.setIdE(e);
				
				MUsuario u = new MUsuario();
				u.setIdU(rs.getLong(3));
				u = u.buscarUsuario(u);
				ret.setIdUCalificadoC(u);
				
				ret.setCalificacionNC(rs.getFloat(4));
				ret.setCalificacionLC(rs.getString(5));
				ret.setObservacionC(rs.getString(6));
				ret.setSoporteC(rs.getString(7));
				ret.setEstatus(rs.getInt(8));
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}
	
}
