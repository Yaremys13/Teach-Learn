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

public class MExamenContenido implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idEC;
	private MExamen idE;
	private MPregunta idPr;
	private Integer estatus;
	
	public MExamenContenido() {
		idEC = new Long(0);
		idE = null;
		idPr = null;
		estatus = 0;
	}

	public Long getIdEC() {
		return idEC;
	}

	public void setIdEC(Long idEC) {
		this.idEC = idEC;
	}

	public MExamen getIdE() {
		return idE;
	}

	public void setIdE(MExamen idE) {
		this.idE = idE;
	}

	public MPregunta getIdPr() {
		return idPr;
	}

	public void setIdPr(MPregunta idPr) {
		this.idPr = idPr;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public MExamenContenido buscarExamenContenido(MExamenContenido val)
	{	MExamenContenido ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from examen_contenido where idec = ?");
			ps.setLong(1, val.getIdEC());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarExamenContenido(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MExamenContenido> buscarExamenContenidos()
	{	List<MExamenContenido> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from examen_contenido");
			ResultSet rs = ps.executeQuery();
			MExamenContenido m = new MExamenContenido();
			ret = new ArrayList<MExamenContenido>();
			while (rs.next())
			{	m = cargarExamenContenido(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarExamenContenido(MExamenContenido val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into examen_contenido values (?,?,?,?)");
					ps.setLong(1, val.getIdEC());
					ps.setLong(2, val.getIdE().getIdE());
					ps.setLong(3, val.getIdPr().getIdPr());
					ps.setInt(4, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update examen_contenido set "
							+ " ide = ?,"
							+ " idpr = ?,"
							+ " estatus = ?"
							+ " where idec = ?");
					ps.setLong(4, val.getIdEC());
					ps.setLong(1, val.getIdE().getIdE());
					ps.setLong(2, val.getIdPr().getIdPr());
					ps.setInt(3, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update examen_contenido set estatus = -1 where idec = ?");
					ps.setLong(1, val.getIdEC());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
	
		return ret;
	}
	
	public MExamenContenido cargarExamenContenido(ResultSet rs)
	{	MExamenContenido ret = null;
	
		if (rs != null)
		{	ret = new MExamenContenido();
			try {
				ret.setIdEC(rs.getLong(1));
				
				MExamen e = new MExamen();
				e.setIdE(rs.getLong(2));
				e = e.buscarExamen(e);
				ret.setIdE(e);
				
				MPregunta p = new MPregunta();
				p.setIdPr(rs.getLong(3));
				p = p.buscarPregunta(p);
				ret.setIdPr(p);
				
				ret.setEstatus(rs.getInt(4));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}
	
	
}
