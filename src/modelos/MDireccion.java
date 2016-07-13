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

public class MDireccion implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idD;
	  private String paisD;
	  private String ciudadD;
	  private String estadoD;
	  private String calleD;
	  private String urbD;
	  private String sectorD;
	  private String piso_numD;
	  private String zonaPD;
	  private Integer estatus;
	
	public MDireccion() {
		idD = new Long(0);
		paisD = "";
		ciudadD = "";
		estadoD = "";
		calleD = "";
		urbD = "";
		sectorD = "";
		piso_numD = "";
		zonaPD = "";
		estatus = 0;
	}

	public Long getIdD() {
		return idD;
	}

	public void setIdD(Long idD) {
		this.idD = idD;
	}

	public String getPaisD() {
		return paisD;
	}

	public void setPaisD(String paisD) {
		this.paisD = paisD;
	}

	public String getCiudadD() {
		return ciudadD;
	}

	public void setCiudadD(String ciudadD) {
		this.ciudadD = ciudadD;
	}

	public String getEstadoD() {
		return estadoD;
	}

	public void setEstadoD(String estadoD) {
		this.estadoD = estadoD;
	}

	public String getCalleD() {
		return calleD;
	}

	public void setCalleD(String calleD) {
		this.calleD = calleD;
	}

	public String getUrbD() {
		return urbD;
	}

	public void setUrbD(String urbD) {
		this.urbD = urbD;
	}

	public String getSectorD() {
		return sectorD;
	}

	public void setSectorD(String sectorD) {
		this.sectorD = sectorD;
	}

	public String getPiso_numD() {
		return piso_numD;
	}

	public void setPiso_numD(String piso_numD) {
		this.piso_numD = piso_numD;
	}

	public String getZonaPD() {
		return zonaPD;
	}

	public void setZonaPD(String zonaPD) {
		this.zonaPD = zonaPD;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MDireccion buscarDireccionById(MDireccion val)
	{	MDireccion ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from direcciones where idd = ?");
			ps.setLong(1, val.getIdD());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarDireccion(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL 123");
		}
		
		return  ret;
	}
	//TODO: buscador por campos
	public List<MDireccion> buscarDirecciones()
	{	
		List<MDireccion> ret = null;
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from direcciones");
			ResultSet rs = ps.executeQuery();
			MDireccion m = new MDireccion();
			ret = new ArrayList<MDireccion>();
			while (rs.next())
			{	m = cargarDireccion(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarDireccion(MDireccion val, Integer opcion)
	{	Boolean ret = false;
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into direcciones values (?,?,?,?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdD());
					ps.setString(2, val.getPaisD());
					ps.setString(3, val.getCiudadD());
					ps.setString(4, val.getEstadoD());
					ps.setString(5, val.getCalleD());
					ps.setString(6, val.getUrbD());
					ps.setString(7, val.getSectorD());
					ps.setString(8, val.getPiso_numD());
					ps.setString(9, val.getZonaPD());
					ps.setInt(10, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update direcciones set "
							+ " paisd = ?,"
							+ " ciudadd = ?,"
							+ " estadod = ?,"
							+ " called = ?,"
							+ " urbd = ?,"
							+ " sectord = ?,"
							+ " piso_numd = ?,"
							+ " zonapd = ?,"
							+ " estatus = ?"
							+ " where idd = ?");
					ps.setLong(10, val.getIdD());
					ps.setString(1, val.getPaisD());
					ps.setString(2, val.getCiudadD());
					ps.setString(3, val.getEstadoD());
					ps.setString(4, val.getCalleD());
					ps.setString(5, val.getUrbD());
					ps.setString(6, val.getSectorD());
					ps.setString(7, val.getPiso_numD());
					ps.setString(8, val.getZonaPD());
					ps.setInt(9, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update direcciones set estatus = -1 where idd = ?");
					ps.setLong(1, val.getIdD());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		return ret;
	}
	
	public MDireccion cargarDireccion(ResultSet rs)
	{	MDireccion ret = null;
		if (rs != null)
		{	ret = new MDireccion();
			try {
				rs.next();
				ret.setIdD(rs.getLong(1));
				ret.setPaisD(rs.getString(2));
				ret.setCiudadD(rs.getString(3));
				ret.setEstadoD(rs.getString(4));
				ret.setCalleD(rs.getString(5));
				ret.setUrbD(rs.getString(6));
				ret.setSectorD(rs.getString(7));
				ret.setPiso_numD(rs.getString(8));
				ret.setZonaPD(rs.getString(9));
				ret.setEstatus(rs.getInt(10));
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL 45");
			}
		}
	
		return ret;
	}

}
