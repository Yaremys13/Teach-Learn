package modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.SQLExcepcion;
import utilities.Conexion;

public class MServicio implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idS;
	private MUsuario idUCreadorS;
	private String tituloS;
	private Integer duracionS;
	private Date fechaIS;
	private Date fechaFS;
	private Integer cantidadMinPS;
	private Integer cantidadMaxPS;
	private BigDecimal costoS;
	private String monedaS;
	private String formaPS;
	private Float cuotasPS;
	private Integer estatus;
	
	public MServicio() {
		idS = new Long(0);
		idUCreadorS = null;
		tituloS = "";
		duracionS = 0;
		fechaIS = null;
		fechaFS = null;
		cantidadMinPS = 0;
		cantidadMaxPS = 0;
		costoS = null;
		monedaS = "";
		formaPS = "";
		cuotasPS = null;
		estatus = 0;
	}

	public Long getIdS() {
		return idS;
	}

	public void setIdS(Long idS) {
		this.idS = idS;
	}

	public MUsuario getIdUCreadorS() {
		return idUCreadorS;
	}

	public void setIdUCreadorS(MUsuario idUCreadorS) {
		this.idUCreadorS = idUCreadorS;
	}

	public String getTituloS() {
		return tituloS;
	}

	public void setTituloS(String tituloS) {
		this.tituloS = tituloS;
	}

	public Integer getDuracionS() {
		return duracionS;
	}

	public void setDuracionS(Integer duracionS) {
		this.duracionS = duracionS;
	}

	public Date getFechaIS() {
		return fechaIS;
	}

	public void setFechaIS(Date fechaIS) {
		this.fechaIS = fechaIS;
	}

	public Date getFechaFS() {
		return fechaFS;
	}

	public void setFechaFS(Date fechaFS) {
		this.fechaFS = fechaFS;
	}

	public Integer getCantidadMinPS() {
		return cantidadMinPS;
	}

	public void setCantidadMinPS(Integer cantidadMinPS) {
		this.cantidadMinPS = cantidadMinPS;
	}

	public Integer getCantidadMaxPS() {
		return cantidadMaxPS;
	}

	public void setCantidadMaxPS(Integer cantidadMaxPS) {
		this.cantidadMaxPS = cantidadMaxPS;
	}

	public BigDecimal getCostoS() {
		return costoS;
	}

	public void setCostoS(BigDecimal costoS) {
		this.costoS = costoS;
	}

	public String getMonedaS() {
		return monedaS;
	}

	public void setMonedaS(String monedaS) {
		this.monedaS = monedaS;
	}

	public String getFormaPS() {
		return formaPS;
	}

	public void setFormaPS(String formaPS) {
		this.formaPS = formaPS;
	}

	public Float getCuotasPS() {
		return cuotasPS;
	}

	public void setCuotasPS(Float cuotasPS) {
		this.cuotasPS = cuotasPS;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MServicio buscarServicio(MServicio val)
	{	MServicio ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from servicios where ids = ?");
			ps.setLong(1, val.getIdS());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarServicio(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MServicio> buscarServicios()
	{	List<MServicio> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from servicios");
			ResultSet rs = ps.executeQuery();
			MServicio m = new MServicio();
			ret = new ArrayList<MServicio>();
			while (rs.next())
			{	m = cargarServicio(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

	
		return  ret;
	}

	public Boolean actualizarServicio(MServicio val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into servicios values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdS());
					ps.setLong(2, val.getIdUCreadorS().getIdU());
					ps.setString(3, val.getTituloS());
					ps.setFloat(4, val.getDuracionS());
					ps.setDate(5, val.getFechaIS());
					ps.setDate(6, val.getFechaFS());
					ps.setInt(7, val.getCantidadMinPS());
					ps.setInt(8, val.getCantidadMaxPS());
					ps.setBigDecimal(9, val.getCostoS());
					ps.setString(10, val.getMonedaS());
					ps.setString(11, val.getFormaPS());
					ps.setFloat(12, val.getCuotasPS());
					ps.setInt(13, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update servicios set "
							+ " iducreadors = ?,"
							+ " titulos = ?,"
							+ " duracions = ?,"
							+ " fechais = ?,"
							+ " fechafs = ?,"
							+ " cantidadminps = ?,"
							+ " cantidadmaxps = ?,"
							+ " costos = ?,"
							+ " monedas = ?,"
							+ " formaps = ?,"
							+ " cuotaps = ?,"
							+ " estatus = ?"
							+ " where ids = ?");
					ps.setLong(13, val.getIdS());
					ps.setLong(1, val.getIdUCreadorS().getIdU());
					ps.setString(2, val.getTituloS());
					ps.setFloat(3, val.getDuracionS());
					ps.setDate(4, val.getFechaIS());
					ps.setDate(5, val.getFechaFS());
					ps.setInt(6, val.getCantidadMinPS());
					ps.setInt(7, val.getCantidadMaxPS());
					ps.setBigDecimal(8, val.getCostoS());
					ps.setString(9, val.getMonedaS());
					ps.setString(10, val.getFormaPS());
					ps.setFloat(11, val.getCuotasPS());
					ps.setInt(12, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update servicios set estatus = -1 where ids = ?");
					ps.setLong(1, val.getIdS());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MServicio cargarServicio(ResultSet rs)
	{	MServicio ret = null;
	
		if (rs != null)
		{	ret = new MServicio();
			try {
				ret.setIdS(rs.getLong(1));
				
				MUsuario u = new MUsuario();
				u.setIdU(rs.getLong(2));	
				u = u.buscarUsuario(u);
				ret.setIdUCreadorS(u);
				
				ret.setTituloS(rs.getString(3));
				ret.setDuracionS(rs.getInt(4));
				ret.setFechaIS(rs.getDate(5));
				ret.setFechaFS(rs.getDate(6));
				ret.setCantidadMinPS(rs.getInt(7));
				ret.setCantidadMaxPS(rs.getInt(8));
				ret.setCostoS(rs.getBigDecimal(9));
				ret.setMonedaS(rs.getString(10));
				ret.setFormaPS(rs.getString(11));
				ret.setCuotasPS(rs.getFloat(12));
				ret.setEstatus(rs.getInt(13));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
		
		return ret;
	}
	

}
