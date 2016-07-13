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

public class MPregunta implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idPr;
	private String tituloPr;
	private String enunciadoPr;
	private String tipoRPr;
	private Integer estatus;
	
	
	public MPregunta() {
		idPr = new Long(0);
		tituloPr = "";
		enunciadoPr = "";
		tipoRPr = "";
		estatus = 0;
	}


	public Long getIdPr() {
		return idPr;
	}


	public void setIdPr(Long idPr) {
		this.idPr = idPr;
	}


	public String getTituloPr() {
		return tituloPr;
	}


	public void setTituloPr(String tituloPr) {
		this.tituloPr = tituloPr;
	}


	public String getEnunciadoPr() {
		return enunciadoPr;
	}


	public void setEnunciadoPr(String enunciadoPr) {
		this.enunciadoPr = enunciadoPr;
	}


	public String getTipoRPr() {
		return tipoRPr;
	}


	public void setTipoRPr(String tipoRPr) {
		this.tipoRPr = tipoRPr;
	}

	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}

	public MPregunta buscarPregunta(MPregunta val)
	{	MPregunta ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from preguntas where idpr = ?");
			ps.setLong(1, val.getIdPr());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarPregunta(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		return  ret;
	}
	
	public List<MPregunta> buscarPreguntas()
	{	List<MPregunta> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from preguntas");
			ResultSet rs = ps.executeQuery();
			MPregunta m = new MPregunta();
			ret = new ArrayList<MPregunta>();
			while (rs.next())
			{	m = cargarPregunta(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarPregunta(MPregunta val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into preguntas values (?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdPr());
					ps.setString(2, val.getTituloPr());
					ps.setString(3, val.getEnunciadoPr());
					ps.setString(4, val.getTipoRPr());
					ps.setInt(5, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update preguntas set "
							+ " titulopr = ?,"
							+ " enunciadopr = ?,"
							+ " tiporpr = ?,"
							+ " valorlpr = ?,"
							+ " valornpr = ?,"
							+ " estatus = ?"
							+ " where idp = ?");
					ps.setLong(5, val.getIdPr());
					ps.setString(1, val.getTituloPr());
					ps.setString(2, val.getEnunciadoPr());
					ps.setString(3, val.getTipoRPr());
					ps.setInt(4, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update preguntas set estatus = -1 where idpr = ?");
					ps.setLong(1, val.getIdPr());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MPregunta cargarPregunta(ResultSet rs)
	{	MPregunta ret = null;
	
		if (rs != null)
		{	ret = new MPregunta();
			try {
				ret.setIdPr(rs.getLong(1));
				ret.setTituloPr(rs.getString(2));
				ret.setEnunciadoPr(rs.getString(3));
				ret.setTipoRPr(rs.getString(4));
				ret.setEstatus(rs.getInt(5));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}
	
	
	
}
