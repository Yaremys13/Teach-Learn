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

public class MExamenParticipante implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idEP;
	private MExamen idE;
	private MParticipante idP;
	private String calificacionOPE;
	private Integer estatus;
	
	public MExamenParticipante() {
		idEP = new Long(0);
		idE = null;
		idP = null;
		calificacionOPE = "";
		estatus = 0;
	}

	public Long getIdEP() {
		return idEP;
	}

	public void setIdEP(Long idEP) {
		this.idEP = idEP;
	}

	public MExamen getIdE() {
		return idE;
	}

	public void setIdE(MExamen idE) {
		this.idE = idE;
	}

	public MParticipante getIdP() {
		return idP;
	}

	public void setIdP(MParticipante idP) {
		this.idP = idP;
	}

	public String getCalificacionOPE() {
		return calificacionOPE;
	}

	public void setCalificacionOPE(String calificacionOPE) {
		this.calificacionOPE = calificacionOPE;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MExamenParticipante buscarExamenParticipante(MExamenParticipante val)
	{	MExamenParticipante ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from examen_participantes where idep = ?");
			ps.setLong(1, val.getIdEP());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarExamenParticipante(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		return  ret;
	}
	
	public List<MExamenParticipante> buscarExamenParticipantes()
	{	List<MExamenParticipante> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from examen_participantes");
			ResultSet rs = ps.executeQuery();
			MExamenParticipante m = new MExamenParticipante();
			ret = new ArrayList<MExamenParticipante>();
			while (rs.next())
			{	m = cargarExamenParticipante(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarExamenParticipante(MExamenParticipante val, Integer opcion)
	{	Boolean ret = false;
		
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into examen_participantes values (?,?,?,?,?)");
					ps.setLong(1, val.getIdEP());
					ps.setLong(2, val.getIdE().getIdE());
					ps.setLong(3, val.getIdP().getIdP());
					ps.setString(4, val.getCalificacionOPE());
					ps.setInt(5, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update examen_participantes set "
							+ " ide = ?,"
							+ " idp = ?,"
							+ " calificacioope = ?,"
							+ " estatus = ?"
							+ " where idc = ?");
					ps.setLong(5, val.getIdEP());
					ps.setLong(1, val.getIdE().getIdE());
					ps.setLong(2, val.getIdP().getIdP());
					ps.setString(3, val.getCalificacionOPE());
					ps.setInt(4, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update examen_participantes set estatus = -1 where idep = ?");
					ps.setLong(1, val.getIdEP());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		return ret;
	}
	
	public MExamenParticipante cargarExamenParticipante(ResultSet rs)
	{	MExamenParticipante ret = null;
	
		if (rs != null)
		{	ret = new MExamenParticipante();
			try {
				ret.setIdEP(rs.getLong(1));
				
				MExamen e = new MExamen();
				e.setIdE(rs.getLong(2));
				e = e.buscarExamen(e);
				ret.setIdE(e);
				
				MParticipante p = new MParticipante();
				p.setIdP(rs.getLong(3));
				p = p.buscarParticipante(p);
				ret.setIdP(p);
				
				ret.setCalificacionOPE(rs.getString(4));
				
				ret.setEstatus(rs.getInt(5));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}
	
	

}
