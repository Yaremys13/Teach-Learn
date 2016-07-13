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

public class MFavorito implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idFa;
	private MUsuario idU;
	private Date fechaRFa;
	private String tipoFa;
	private Integer estatus;
	
	public MFavorito() {
		idFa = new Long(0);
		idU = null;
		fechaRFa = null;
		tipoFa = "";
		estatus = 0;
	}

	public Long getIdFa() {
		return idFa;
	}

	public void setIdFa(Long idFa) {
		this.idFa = idFa;
	}

	public MUsuario getIdU() {
		return idU;
	}

	public void setIdU(MUsuario idU) {
		this.idU = idU;
	}

	public Date getFechaRFa() {
		return fechaRFa;
	}

	public void setFechaRFa(Date fechaRFa) {
		this.fechaRFa = fechaRFa;
	}

	public String getTipoFa() {
		return tipoFa;
	}

	public void setTipoFa(String tipoFa) {
		this.tipoFa = tipoFa;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MFavorito buscarFavorito(MFavorito val)
	{	MFavorito ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from favoritos where idfa = ?");
			ps.setLong(1, val.getIdFa());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarFavorito(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

		return  ret;
	}
	
	public List<MFavorito> buscarFavoritos()
	{	List<MFavorito> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from favoritos");
			ResultSet rs = ps.executeQuery();
			MFavorito m = new MFavorito();
			ret = new ArrayList<MFavorito>();
			while (rs.next())
			{	m = cargarFavorito(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarFavorito(MFavorito val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into favoritos values (?,?,?,?,?)");
					ps.setLong(1, val.getIdFa());
					ps.setLong(2, val.getIdU().getIdU());
					ps.setDate(3, val.getFechaRFa());
					ps.setString(4, val.getTipoFa());
					ps.setInt(5, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update favoritos set "
							+ " idu = ?,"
							+ " fecharfa = ?,"
							+ " tipofa = ?,"
							+ " estatus = ?"
							+ " where idfa = ?");
					ps.setLong(5, val.getIdFa());
					ps.setLong(1, val.getIdU().getIdU());
					ps.setDate(2, val.getFechaRFa());
					ps.setString(3, val.getTipoFa());
					ps.setInt(4, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update favoritos set estatus = -1 where idfa = ?");
					ps.setLong(1, val.getIdFa());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MFavorito cargarFavorito(ResultSet rs)
	{	MFavorito ret = null;
	
		if (rs != null)
		{	ret = new MFavorito();
			try {
				ret.setIdFa(rs.getLong(1));
				
				MUsuario u = new MUsuario();
				u.setIdU(rs.getLong(2));
				u = u.buscarUsuario(u);
				ret.setIdU(u);
				
				ret.setFechaRFa(rs.getDate(3));
				ret.setTipoFa(rs.getString(4));
				ret.setEstatus(rs.getInt(5));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}

	
		return ret;
	}
	
	

}
