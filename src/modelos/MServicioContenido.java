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

public class MServicioContenido implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idSC;
	private MServicio idS;
	private String descripcionSC;
	private String adjuntoSC;
	private Integer estatus;
	
	public MServicioContenido() {
		idSC = new Long(0);
		idS = null;
		descripcionSC = "";
		adjuntoSC = "";
		estatus = 0;
	}

	public Long getIdSC() {
		return idSC;
	}

	public void setIdSC(Long idSC) {
		this.idSC = idSC;
	}

	public MServicio getIdS() {
		return idS;
	}

	public void setIdS(MServicio idS) {
		this.idS = idS;
	}

	public String getDescripcionSC() {
		return descripcionSC;
	}

	public void setDescripcionSC(String descripcionSC) {
		this.descripcionSC = descripcionSC;
	}

	public String getAdjuntoSC() {
		return adjuntoSC;
	}

	public void setAdjuntoSC(String adjuntoSC) {
		this.adjuntoSC = adjuntoSC;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MServicioContenido buscarServicioContenido(MServicioContenido val)
	{	MServicioContenido ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from servicio_contenido where idsc = ?");
			ps.setLong(1, val.getIdSC());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarServicioContenido(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MServicioContenido> buscarServicioContenidos()
	{	List<MServicioContenido> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from servicio_contenido");
			ResultSet rs = ps.executeQuery();
			MServicioContenido m = new MServicioContenido();
			ret = new ArrayList<MServicioContenido>();
			while (rs.next())
			{	m = cargarServicioContenido(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarServicioContenido(MServicioContenido val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into servicio_contenido values (?,?,?,?,?)");
					ps.setLong(1, val.getIdSC());
					ps.setLong(2, val.getIdS().getIdS());
					ps.setString(3, val.getDescripcionSC());
					ps.setString(4, val.getAdjuntoSC());
					ps.setInt(5, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update servicio_contenido set "
							+ " ids = ?,"
							+ " descripcionsc = ?,"
							+ " adjuntosc = ?,"
							+ " estatus = ?"
							+ " where idsc = ?");
					ps.setLong(5, val.getIdSC());
					ps.setLong(1, val.getIdS().getIdS());
					ps.setString(2, val.getDescripcionSC());
					ps.setString(3, val.getAdjuntoSC());
					ps.setInt(4, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update servicio_contenido set estatus = -1 where idsc = ?");
					ps.setLong(1, val.getIdSC());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

	
		return ret;
	}
	
	public MServicioContenido cargarServicioContenido(ResultSet rs)
	{	MServicioContenido ret = null;
	
		if (rs != null)
		{	ret = new MServicioContenido();
			try {
				ret.setIdSC(rs.getLong(1));
				
				MServicio s = new MServicio();
				s.setIdS(rs.getLong(2));	
				s = s.buscarServicio(s);
				ret.setIdS(s);
				
				ret.setDescripcionSC(rs.getString(3));
				ret.setAdjuntoSC(rs.getString(4));
				ret.setEstatus(rs.getInt(5));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}
	

}
