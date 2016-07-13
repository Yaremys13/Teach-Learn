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

public class MMaterial implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idM;
	private String descripcionM;
	private String archivoM;
	private String tituloM;
	private MUsuario idUCreadorM;
	private Boolean permiteDM;
	private Date fechaSM;
	private Date fechaAM;
	private MServicio idS;
	private Integer estatus;
	
	public MMaterial() {
		idM = new Long(0);
		descripcionM = "";
		archivoM = "";
		tituloM = "";
		idUCreadorM = null;
		permiteDM = false;
		fechaSM = null;
		fechaAM = null;
		idS = null;
		estatus = 0;
	}

	public Long getIdM() {
		return idM;
	}

	public void setIdM(Long idM) {
		this.idM = idM;
	}

	public String getDescripcionM() {
		return descripcionM;
	}

	public void setDescripcionM(String descripcionM) {
		this.descripcionM = descripcionM;
	}

	public String getArchivoM() {
		return archivoM;
	}

	public void setArchivoM(String archivoM) {
		this.archivoM = archivoM;
	}

	public String getTituloM() {
		return tituloM;
	}

	public void setTituloM(String tituloM) {
		this.tituloM = tituloM;
	}

	public MUsuario getIdUCreadorM() {
		return idUCreadorM;
	}

	public void setIdUCreadorM(MUsuario idUCreadorM) {
		this.idUCreadorM = idUCreadorM;
	}

	public Boolean getPermiteDM() {
		return permiteDM;
	}

	public void setPermiteDM(Boolean permiteDM) {
		this.permiteDM = permiteDM;
	}

	public Date getFechaSM() {
		return fechaSM;
	}

	public void setFechaSM(Date fechaSM) {
		this.fechaSM = fechaSM;
	}

	public Date getFechaAM() {
		return fechaAM;
	}

	public void setFechaAM(Date fechaAM) {
		this.fechaAM = fechaAM;
	}

	public MServicio getIdS() {
		return idS;
	}

	public void setIdS(MServicio idS) {
		this.idS = idS;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MMaterial buscarMaterial(MMaterial val)
	{	MMaterial ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from materiales where idm = ?");
			ps.setLong(1, val.getIdM());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarMaterial(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MMaterial> buscarMateriales()
	{	List<MMaterial> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from materiales");
			ResultSet rs = ps.executeQuery();
			MMaterial m = new MMaterial();
			ret = new ArrayList<MMaterial>();
			while (rs.next())
			{	m = cargarMaterial(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarMaterial(MMaterial val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into materiales values (?,?,?,?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdM());
					ps.setString(2, val.getDescripcionM());
					ps.setString(3, val.getArchivoM());
					ps.setString(4, val.getTituloM());
					ps.setLong(5, val.getIdUCreadorM().getIdU());
					ps.setBoolean(6, val.getPermiteDM());
					ps.setDate(7, val.getFechaSM());
					ps.setDate(8, val.getFechaAM());
					ps.setLong(9, val.getIdS().getIdS());
					ps.setInt(10, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update materiales set "
							+ " descripcionm = ?,"
							+ " archivom = ?,"
							+ " titulom = ?,"
							+ " iducreadorm = ?,"
							+ " permitedm = ?,"
							+ " fechasm = ?,"
							+ " fechaam = ?,"
							+ " ids = ?,"
							+ " estatus = ?"
							+ " where idm = ?");
					ps.setLong(10, val.getIdM());
					ps.setString(1, val.getDescripcionM());
					ps.setString(2, val.getArchivoM());
					ps.setString(3, val.getTituloM());
					ps.setLong(4, val.getIdUCreadorM().getIdU());
					ps.setBoolean(5, val.getPermiteDM());
					ps.setDate(6, val.getFechaSM());
					ps.setDate(7, val.getFechaAM());
					ps.setLong(8, val.getIdS().getIdS());
					ps.setInt(9, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update materiales set estatus = -1 where idm = ?");
					ps.setLong(1, val.getIdM());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MMaterial cargarMaterial(ResultSet rs)
	{	MMaterial ret = null;
	
		if (rs != null)
		{	ret = new MMaterial();
			try {
				ret.setIdM(rs.getLong(1));
				
				ret.setDescripcionM(rs.getString(2));
				ret.setArchivoM(rs.getString(3));
				ret.setTituloM(rs.getString(4));
				
				MUsuario u = new MUsuario();
				u.setIdU(rs.getLong(5));	
				u = u.buscarUsuario(u);
				ret.setIdUCreadorM(u);
				
				ret.setPermiteDM(rs.getBoolean(6));
				ret.setFechaSM(rs.getDate(7));
				ret.setFechaAM(rs.getDate(8));
				
				MServicio s = new MServicio();
				s.setIdS(rs.getLong(9));
				s = s.buscarServicio(s);
				ret.setIdS(s);
				
				ret.setEstatus(rs.getInt(10));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}
	

}
