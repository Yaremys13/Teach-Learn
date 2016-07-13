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

public class MServicioParticipante implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idSP;
	private MServicio idS;
	private MParticipante idP;
	private Integer tipoP; //'1- Participante\n2- Facilitador\n3- Oyente\n4- Otro' ,
	private Integer estatus;
	
	public MServicioParticipante() {
		idSP = new Long(0);
		idS = null;
		idP = null;
		tipoP = 0;
		estatus = 0;
		
	}

	public Long getIdSP() {
		return idSP;
	}

	public void setIdSP(Long idSP) {
		this.idSP = idSP;
	}

	public MServicio getIdS() {
		return idS;
	}

	public void setIdS(MServicio idS) {
		this.idS = idS;
	}

	public MParticipante getIdP() {
		return idP;
	}

	public void setIdP(MParticipante idP) {
		this.idP = idP;
	}

	public Integer getTipoP() {
		return tipoP;
	}

	public void setTipoP(Integer tipoP) {
		this.tipoP = tipoP;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	
	public MServicioParticipante buscarServicioParticipante(MServicioParticipante val)
	{	MServicioParticipante ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from servicio_participantes where idsp = ?");
			ps.setLong(1, val.getIdSP());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarServicioParticipante(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

		return  ret;
	}
	
	public List<MServicioParticipante> buscarServicioParticipantes()
	{	List<MServicioParticipante> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from servicio_participantes");
			ResultSet rs = ps.executeQuery();
			MServicioParticipante m = new MServicioParticipante();
			ret = new ArrayList<MServicioParticipante>();
			while (rs.next())
			{	m = cargarServicioParticipante(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarServicioParticipante(MServicioParticipante val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into servicio_participantes values (?,?,?,?,?)");
					ps.setLong(1, val.getIdSP());
					ps.setLong(2, val.getIdS().getIdS());
					ps.setLong(3, val.getIdP().getIdP());
					ps.setInt(4, val.getTipoP());
					ps.setInt(5, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update servicio_participantes set "
							+ " ids = ?,"
							+ " idp = ?,"
							+ " tipop = ?,"
							+ " estatus = ?"
							+ " where idsp = ?");
					ps.setLong(5, val.getIdSP());
					ps.setLong(1, val.getIdS().getIdS());
					ps.setLong(2, val.getIdP().getIdP());
					ps.setInt(3, val.getTipoP());
					ps.setInt(4, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update servicio_participantes set estatus = -1 where idsp = ?");
					ps.setLong(1, val.getIdSP());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MServicioParticipante cargarServicioParticipante(ResultSet rs)
	{	MServicioParticipante ret = null;
	
		if (rs != null)
		{	ret = new MServicioParticipante();
			try {
				ret.setIdSP(rs.getLong(1));
				
				MServicio s = new MServicio();
				s.setIdS(rs.getLong(2));	
				s = s.buscarServicio(s);
				ret.setIdS(s);
				
				MParticipante p = new MParticipante();
				p.setIdP(rs.getLong(3));
				p = p.buscarParticipante(p);
				ret.setIdP(p);
				
				ret.setTipoP(rs.getInt(4));
				ret.setEstatus(rs.getInt(5));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}
	
	
	
}
