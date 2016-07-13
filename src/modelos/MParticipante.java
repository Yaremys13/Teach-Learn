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

public class MParticipante implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idP;
	private MUsuario idU;
	private Integer estatus;
	
	
	public MParticipante() {
		idP = new Long(0);
		idU = null;
		estatus = 0;
	}


	public Long getIdP() {
		return idP;
	}


	public void setIdP(Long idP) {
		this.idP = idP;
	}


	public MUsuario getIdU() {
		return idU;
	}


	public void setIdU(MUsuario idU) {
		this.idU = idU;
	}


	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MParticipante buscarParticipante(MParticipante val)
	{	MParticipante ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from participantes where idp = ?");
			ps.setLong(1, val.getIdP());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarParticipante(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MParticipante> buscarParticipantes()
	{	List<MParticipante> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from participantes");
			ResultSet rs = ps.executeQuery();
			MParticipante m = new MParticipante();
			ret = new ArrayList<MParticipante>();
			while (rs.next())
			{	m = cargarParticipante(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarParticipante(MParticipante val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into participantes values (?,?,?)");
					ps.setLong(1, val.getIdP());
					ps.setLong(2, val.getIdU().getIdU());
					ps.setInt(3, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update participantes set "
							+ " idu = ?,"
							+ " estatus = ?"
							+ " where idp = ?");
					ps.setLong(3, val.getIdP());
					ps.setLong(1, val.getIdU().getIdU());
					ps.setInt(2, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update participantes set estatus = -1 where idp = ?");
					ps.setLong(1, val.getIdP());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	

	}
	
	public MParticipante cargarParticipante(ResultSet rs)
	{	MParticipante ret = null;
	
		if (rs != null)
		{	ret = new MParticipante();
			try {
				ret.setIdP(rs.getLong(1));
				
				MUsuario u = new MUsuario();
				u.setIdU(rs.getLong(2));	
				u = u.buscarUsuario(u);
				ret.setIdU(u);
				
				ret.setEstatus(rs.getInt(3));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}

		return ret;
	}
	

}
