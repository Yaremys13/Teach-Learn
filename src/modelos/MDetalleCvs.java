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

public class MDetalleCvs implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idDCvs;
	private MCvs idCvs;
	private String tipoDCvs;
	private String contenidoDCvs;
	private Date fechaIDCvs;
	private Date fechaFDCvs;
	private Float duracionDCvs;
	private String observacionesDCvs;
	private String lugarDCvs;
	private BigDecimal sueldoDCvs;
	private String contactoPDCvs;
	private String contactoTDCvs;
	private Integer estatus;
	
	public MDetalleCvs() {
		idDCvs = new Long(0);
		idCvs = null;
		tipoDCvs = "";
		contenidoDCvs = "";
		fechaIDCvs = null;
		fechaFDCvs = null;
		duracionDCvs = null;
		observacionesDCvs = "";
		lugarDCvs = "";
		sueldoDCvs = null;
		contactoPDCvs = "";
		contactoTDCvs = "";
		estatus = 0;
		
	}

	public Long getIdDCvs() {
		return idDCvs;
	}

	public void setIdDCvs(Long idDCvs) {
		this.idDCvs = idDCvs;
	}

	public MCvs getIdCvs() {
		return idCvs;
	}

	public void setIdCvs(MCvs idCvs) {
		this.idCvs = idCvs;
	}

	public String getTipoDCvs() {
		return tipoDCvs;
	}

	public void setTipoDCvs(String tipoDCvs) {
		this.tipoDCvs = tipoDCvs;
	}

	public String getContenidoDCvs() {
		return contenidoDCvs;
	}

	public void setContenidoDCvs(String contenidoDCvs) {
		this.contenidoDCvs = contenidoDCvs;
	}

	public Date getFechaIDCvs() {
		return fechaIDCvs;
	}

	public void setFechaIDCvs(Date fechaIDCvs) {
		this.fechaIDCvs = fechaIDCvs;
	}

	public Date getFechaFDCvs() {
		return fechaFDCvs;
	}

	public void setFechaFDCvs(Date fechaFDCvs) {
		this.fechaFDCvs = fechaFDCvs;
	}

	public Float getDuracionDCvs() {
		return duracionDCvs;
	}

	public void setDuracionDCvs(Float duracionDCvs) {
		this.duracionDCvs = duracionDCvs;
	}

	public String getObservacionesDCvs() {
		return observacionesDCvs;
	}

	public void setObservacionesDCvs(String observacionesDCvs) {
		this.observacionesDCvs = observacionesDCvs;
	}
	
	public String getLugarDCvs() {
		return lugarDCvs;
	}

	public void setLugarDCvs(String lugarDCvs) {
		this.lugarDCvs = lugarDCvs;
	}

	public BigDecimal getSueldoDCvs() {
		return sueldoDCvs;
	}

	public void setSueldoDCvs(BigDecimal sueldoDCvs) {
		this.sueldoDCvs = sueldoDCvs;
	}

	public String getContactoPDCvs() {
		return contactoPDCvs;
	}

	public void setContactoPDCvs(String contactoPDCvs) {
		this.contactoPDCvs = contactoPDCvs;
	}

	public String getContactoTDCvs() {
		return contactoTDCvs;
	}

	public void setContactoTDCvs(String contactoTDCvs) {
		this.contactoTDCvs = contactoTDCvs;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MDetalleCvs buscarDetalleCvs(MDetalleCvs val)
	{	MDetalleCvs ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from detalles_cvs where iddcvs = ?");
			ps.setLong(1, val.getIdDCvs());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarDetalleCvs(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		return  ret;
	}
	
	public List<MDetalleCvs> buscarDetallesCvs()
	{	List<MDetalleCvs> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from detalle_cvs");
			ResultSet rs = ps.executeQuery();
			MDetalleCvs m = new MDetalleCvs();
			ret = new ArrayList<MDetalleCvs>();
			while (rs.next())
			{	m = cargarDetalleCvs(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarDetalleCvs(MDetalleCvs val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into detalle_cvs values (?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdDCvs());
					ps.setLong(2, val.getIdCvs().getIdCvs());
					ps.setString(3, val.getTipoDCvs());
					ps.setString(4, val.getContenidoDCvs());
					ps.setDate(5, val.getFechaIDCvs());
					ps.setDate(6, val.getFechaFDCvs());
					ps.setFloat(7, val.getDuracionDCvs());
					ps.setString(8, val.getObservacionesDCvs());
					ps.setString(9, val.getLugarDCvs());
					ps.setBigDecimal(10, val.getSueldoDCvs());
					ps.setString(11, val.getContactoPDCvs());
					ps.setString(12, val.getContactoTDCvs());
					ps.setInt(13, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update detalle_cvs set "
							+ " idcvs = ?,"
							+ " tipodcvs = ?,"
							+ " contenidodcvs = ?,"
							+ " fechaidcvs = ?,"
							+ " fechafdcvs = ?"
							+ " duraciondcvs = ?,"
							+ " observacionesdcvs = ?,"
							+ " estatus = ?"
							+ " where iddcvs = ?");
					ps.setLong(13, val.getIdDCvs());
					ps.setLong(1, val.getIdCvs().getIdCvs());
					ps.setString(2, val.getTipoDCvs());
					ps.setString(3, val.getContenidoDCvs());
					ps.setDate(4, val.getFechaIDCvs());
					ps.setDate(5, val.getFechaFDCvs());
					ps.setFloat(6, val.getDuracionDCvs());
					ps.setString(7, val.getObservacionesDCvs());
					ps.setString(8, val.getLugarDCvs());
					ps.setBigDecimal(9, val.getSueldoDCvs());
					ps.setString(10, val.getContactoPDCvs());
					ps.setString(11, val.getContactoTDCvs());
					ps.setInt(12, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update detalle_cvs set estatus = -1 where iddcvs = ?");
					ps.setLong(1, val.getIdDCvs());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MDetalleCvs cargarDetalleCvs(ResultSet rs)
	{	MDetalleCvs ret = null;
	
		if (rs != null)
		{	ret = new MDetalleCvs();
			try {
				ret.setIdDCvs(rs.getLong(1));
				
				MCvs c = new MCvs();
				c.setIdCvs(rs.getLong(2));
				c = c.buscarCvs(c);
				ret.setIdCvs(c);
				
				ret.setTipoDCvs(rs.getString(3));
				ret.setContenidoDCvs(rs.getString(4));
				ret.setFechaIDCvs(rs.getDate(5));
				ret.setFechaFDCvs(rs.getDate(6));
				ret.setDuracionDCvs(rs.getFloat(7));
				ret.setObservacionesDCvs(rs.getString(8));
				ret.setLugarDCvs(rs.getString(9));
				ret.setSueldoDCvs(rs.getBigDecimal(10));
				ret.setContactoPDCvs(rs.getString(11));
				ret.setContactoTDCvs(rs.getString(12));
				ret.setEstatus(rs.getInt(13));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}

}
