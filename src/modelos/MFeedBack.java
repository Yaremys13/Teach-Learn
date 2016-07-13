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

public class MFeedBack implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idFB;
	private Date fechaCFB;
	private Date fechaCierreFB;
	private MServicio idS;
	private Integer estatus;
	
	public MFeedBack() {
		idFB = new Long(0);
		fechaCFB = null;
		fechaCierreFB = null;
		idS = null;
		estatus = 0;
	}

	public Long getIdFB() {
		return idFB;
	}

	public void setIdFB(Long idFB) {
		this.idFB = idFB;
	}

	public Date getFechaCFB() {
		return fechaCFB;
	}

	public void setFechaCFB(Date fechaCFB) {
		this.fechaCFB = fechaCFB;
	}

	public Date getFechaCierreFB() {
		return fechaCierreFB;
	}

	public void setFechaCierreFB(Date fechaCierreFB) {
		this.fechaCierreFB = fechaCierreFB;
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
	
	public MFeedBack buscarFeedBack(MFeedBack val)
	{	MFeedBack ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from feedback where idfb = ?");
			ps.setLong(1, val.getIdFB());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarFeedBack(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

	
		return  ret;
	}
	
	public List<MFeedBack> buscarFeedBacks()
	{	List<MFeedBack> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from feedback");
			ResultSet rs = ps.executeQuery();
			MFeedBack m = new MFeedBack();
			ret = new ArrayList<MFeedBack>();
			while (rs.next())
			{	m = cargarFeedBack(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarFeedBack(MFeedBack val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into feedback values (?,?,?,?,?)");
					ps.setLong(1, val.getIdFB());
					ps.setDate(2, val.getFechaCFB());
					ps.setDate(3, val.getFechaCierreFB());
					ps.setLong(4, val.getIdS().getIdS());
					ps.setInt(5, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update feedback set "
							+ " fechacfb = ?,"
							+ " fechacierrefb = ?,"
							+ " ids = ?,"
							+ " estatus = ?"
							+ " where idfb = ?");
					ps.setLong(5, val.getIdFB());
					ps.setDate(1, val.getFechaCFB());
					ps.setDate(2, val.getFechaCierreFB());
					ps.setLong(3, val.getIdS().getIdS());
					ps.setInt(4, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update feedback set estatus = -1 where idfb = ?");
					ps.setLong(1, val.getIdFB());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MFeedBack cargarFeedBack(ResultSet rs)
	{	MFeedBack ret = null;
	
		if (rs != null)
		{	ret = new MFeedBack();
			try {
				ret.setIdFB(rs.getLong(1));
				
				ret.setFechaCFB(rs.getDate(2));
				ret.setFechaCierreFB(rs.getDate(3));
				
				MServicio s = new MServicio();
				s.setIdS(rs.getLong(4));
				s = s.buscarServicio(s);
				ret.setIdS(s);
				
				ret.setEstatus(rs.getInt(5));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
	
		return ret;
	}

}
