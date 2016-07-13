package modelos;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.SQLExcepcion;
import utilities.Conexion;

public class MExamen implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idE;
	private MUsuario idUCreadorE;
	private Date fechaCE;
	private MServicio idS;
	private String tipoCE; //'Tipo de Examen' ,
	private Boolean esCertificacionE;
	private Integer estatus;
	
	public MExamen() {
		idE = new Long(0);
		idUCreadorE = null;
		fechaCE = null;
		idS = null;
		tipoCE = "";
		esCertificacionE = false;
		estatus = 0;
	}

	public Long getIdE() {
		return idE;
	}

	public void setIdE(Long idE) {
		this.idE = idE;
	}

	public MUsuario getIdUCreadorE() {
		return idUCreadorE;
	}

	public void setIdUCreadorE(MUsuario idUCreadorE) {
		this.idUCreadorE = idUCreadorE;
	}

	public Date getFechaCE() {
		return fechaCE;
	}

	public void setFechaCE(Date fechaCE) {
		this.fechaCE = fechaCE;
	}

	public MServicio getIdS() {
		return idS;
	}

	public void setIdS(MServicio idS) {
		this.idS = idS;
	}

	public String getTipoCE() {
		return tipoCE;
	}

	public void setTipoCE(String tipoCE) {
		this.tipoCE = tipoCE;
	}

	public Boolean getEsCertificacionE() {
		return esCertificacionE;
	}

	public void setEsCertificacionE(Boolean esCertificacionE) {
		this.esCertificacionE = esCertificacionE;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	
	public MExamen buscarExamen(MExamen val)
	{	MExamen ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from examenes where ide = ?");
			ps.setLong(1, val.getIdE());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarExamen(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;		
	}
	
	public List<MExamen> buscarExamenes()
	{	List<MExamen> ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from examenes");
			ResultSet rs = ps.executeQuery();
			MExamen m = new MExamen();
			ret = new ArrayList<MExamen>();
			while (rs.next())
			{	m = cargarExamen(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public Boolean actualizarExamen(MExamen val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into examenes values (?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdE());
					ps.setLong(2, val.getIdUCreadorE().getIdU());
					ps.setDate(3, val.getFechaCE());
					ps.setLong(4, val.getIdS().getIdS());
					ps.setString(5, val.getTipoCE());
					ps.setBoolean(6, val.getEsCertificacionE());
					ps.setInt(7, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update examenes set "
							+ " iducreadore = ?,"
							+ " fechace = ?,"
							+ " ids = ?,"
							+ " tipoce = ?,"
							+ " escertificacione = ?,"
							+ " estatus = ?"
							+ " where ide = ?");
					ps.setLong(7, val.getIdE());
					ps.setLong(1, val.getIdUCreadorE().getIdU());
					ps.setDate(2, val.getFechaCE());
					ps.setLong(3, val.getIdS().getIdS());
					ps.setString(4, val.getTipoCE());
					ps.setBoolean(5, val.getEsCertificacionE());
					ps.setInt(6, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update examenes set estatus = -1 where ide = ?");
					ps.setLong(1, val.getIdE());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		
	
		return ret;
	}

	public MExamen cargarExamen(ResultSet rs)
	{	MExamen ret = null;
		
		if (rs != null)
		{	ret = new MExamen();
			try {
				ret.setIdE(rs.getLong(1));
				
				MUsuario u = new MUsuario();
				u.setIdU(rs.getLong(2));
				u = u.buscarUsuario(u);
				ret.setIdUCreadorE(u);
				
				ret.setFechaCE(rs.getDate(3));
				
				MServicio s = new MServicio();
				s.setIdS(rs.getLong(4));
				s = s.buscarServicio(s);
				ret.setIdS(s);
				
				ret.setTipoCE(rs.getString(5));
				ret.setEsCertificacionE(rs.getBoolean(6));
				ret.setEstatus(rs.getInt(7));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
		
		return ret;
	}
	
}
