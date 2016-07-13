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

public class MFacilitador implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idF;
	private MUsuario idU;
	private Integer estatus;
	
	public MFacilitador() {
		idF = new Long(0);
		idU = null;
		estatus = 0;
	}

	public Long getIdF() {
		return idF;
	}

	public void setIdF(Long idF) {
		this.idF = idF;
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
	
	public MFacilitador buscarFacilitador(MFacilitador val)
	{	MFacilitador ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from facilitadores where idf = ?");
			ps.setLong(1, val.getIdF());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarFacilitador(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MFacilitador> buscarFacilitadores()
	{	List<MFacilitador> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from facilitadores");
			ResultSet rs = ps.executeQuery();
			MFacilitador m = new MFacilitador();
			ret = new ArrayList<MFacilitador>();
			while (rs.next())
			{	m = cargarFacilitador(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarFacilitador(MFacilitador val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into facilitadores values (?,?,?)");
					ps.setLong(1, val.getIdF());
					ps.setLong(2, val.getIdU().getIdU());
					ps.setInt(3, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update facilitadores set "
							+ " idu = ?,"
							+ " estatus = ?"
							+ " where idf = ?");
					ps.setLong(3, val.getIdF());
					ps.setLong(1, val.getIdU().getIdU());
					ps.setInt(2, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update facilitadores set estatus = -1 where idf = ?");
					ps.setLong(1, val.getIdF());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MFacilitador cargarFacilitador(ResultSet rs)
	{	MFacilitador ret = null;
	
		if (rs != null)
		{	ret = new MFacilitador();
			try {
				ret.setIdF(rs.getLong(1));
				
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
