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

public class MCvs implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idCvs;
	private Date fechaCCvs;
	private Date fechaACvs;
	private MFacilitador idF;
	private Boolean esVisibleCVS;
	private Integer estatus;
	
	public MCvs() {
		idCvs = new Long(0);
		fechaCCvs = null;
		fechaACvs = null;
		idF = null;
		esVisibleCVS = false;
		estatus = 0;
	}

	public Long getIdCvs() {
		return idCvs;
	}

	public void setIdCvs(Long idCvs) {
		this.idCvs = idCvs;
	}

	public Date getFechaCCvs() {
		return fechaCCvs;
	}

	public void setFechaCCvs(Date fechaCCvs) {
		this.fechaCCvs = fechaCCvs;
	}

	public Date getFechaACvs() {
		return fechaACvs;
	}

	public void setFechaACvs(Date fechaACvs) {
		this.fechaACvs = fechaACvs;
	}

	public MFacilitador getIdF() {
		return idF;
	}

	public void setIdF(MFacilitador idF) {
		this.idF = idF;
	}

	public Boolean getEsVisibleCVS() {
		return esVisibleCVS;
	}

	public void setEsVisibleCVS(Boolean esVisibleCVS) {
		this.esVisibleCVS = esVisibleCVS;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MCvs buscarCvs(MCvs val)
	{	MCvs ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from cvs where idcvs = ?");
			ps.setLong(1, val.getIdCvs());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarCvs(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

	
	
		return  ret;
	}
	
	public List<MCvs> buscarCvss()
	{	List<MCvs> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from cvs");
			ResultSet rs = ps.executeQuery();
			MCvs m = new MCvs();
			ret = new ArrayList<MCvs>();
			while (rs.next())
			{	m = cargarCvs(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		
	
		return  ret;
	}

	public Boolean actualizarCvs(MCvs val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into cvs values (?,?,?,?,?,?)");
					ps.setLong(1, val.getIdCvs());
					ps.setDate(2, val.getFechaCCvs());
					ps.setDate(3, val.getFechaACvs());
					ps.setLong(4, val.getIdF().getIdF());
					ps.setBoolean(5, val.getEsVisibleCVS());
					ps.setInt(6, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update cvs set "
							+ " fechaccvs = ?,"
							+ " fechaacvs = ?,"
							+ " idf = ?,"
							+ " esvisiblecvs = ?,"
							+ " estatus = ?"
							+ " where idcvs = ?");
					ps.setLong(6, val.getIdCvs());
					ps.setDate(1, val.getFechaCCvs());
					ps.setDate(2, val.getFechaACvs());
					ps.setLong(3, val.getIdF().getIdF());
					ps.setBoolean(4, val.getEsVisibleCVS());
					ps.setInt(5, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update cvs set estatus = -1 where idcvs = ?");
					ps.setLong(1, val.getIdCvs());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

	
		return ret;
	}
	
	public MCvs cargarCvs(ResultSet rs)
	{	MCvs ret = null;
	
		if (rs != null)
		{	ret = new MCvs();
			try {
				ret.setIdCvs(rs.getLong(1));
				ret.setFechaCCvs(rs.getDate(2));
				ret.setFechaACvs(rs.getDate(3));
				
				MFacilitador f = new MFacilitador();
				f.setIdF(rs.getLong(4));
				f = f.buscarFacilitador(f);
				ret.setIdF(f);
				
				ret.setEsVisibleCVS(rs.getBoolean(5));
				ret.setEstatus(rs.getInt(6));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}
	

}
