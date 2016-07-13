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

public class MPreguntaRespuestas implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idPrR;
	private MPregunta idPr;
	private String opcionR;
	private String valorLR;
	private Float valorNR;
	private Boolean esCorrectaR;
	private Integer estatus;
	
	
	public MPreguntaRespuestas() {
		idPrR = new Long(0);
		idPr = null;
		opcionR = "";
		valorLR = "";
		valorNR = null;
		esCorrectaR = false;
		estatus = 0;
	}


	public Long getIdPrR() {
		return idPrR;
	}


	public void setIdPrR(Long idPrR) {
		this.idPrR = idPrR;
	}


	public MPregunta getIdPr() {
		return idPr;
	}


	public void setIdPr(MPregunta idPr) {
		this.idPr = idPr;
	}


	public String getOpcionR() {
		return opcionR;
	}


	public void setOpcionR(String opcionR) {
		this.opcionR = opcionR;
	}


	public String getValorLR() {
		return valorLR;
	}


	public void setValorLR(String valorLR) {
		this.valorLR = valorLR;
	}


	public Float getValorNR() {
		return valorNR;
	}


	public void setValorNR(Float valorNR) {
		this.valorNR = valorNR;
	}


	public Boolean getEsCorrectaR() {
		return esCorrectaR;
	}


	public void setEsCorrectaR(Boolean esCorrectaR) {
		this.esCorrectaR = esCorrectaR;
	}


	public Integer getEstatus() {
		return estatus;
	}


	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MPreguntaRespuestas buscarPreguntaRespuestas(MPreguntaRespuestas val)
	{	MPreguntaRespuestas ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from preguntas_respuestas where idprr = ?");
			ps.setLong(1, val.getIdPrR());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarPreguntaRespuestas(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MPreguntaRespuestas> buscarPreguntasRespuestas()
	{	List<MPreguntaRespuestas> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from preguntas_respuestas");
			ResultSet rs = ps.executeQuery();
			MPreguntaRespuestas m = new MPreguntaRespuestas();
			ret = new ArrayList<MPreguntaRespuestas>();
			while (rs.next())
			{	m = cargarPreguntaRespuestas(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarPreguntaRespuestas(MPreguntaRespuestas val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into preguntas_respuestas values (?,?,?,?,?,?)");
					ps.setLong(1, val.getIdPrR());
					ps.setLong(2, val.getIdPr().getIdPr());
					ps.setString(3, val.getOpcionR());
					ps.setString(4, val.getValorLR());
					ps.setFloat(5, val.getValorNR());
					ps.setBoolean(6, val.getEsCorrectaR());
					ps.setInt(7, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update preguntas_respuestas set "
							+ " idpr = ?,"
							+ " opcionr = ?,"
							+ " valorlr = ?,"
							+ " valornr = ?,"
							+ " estatus = ?"
							+ " where idprr = ?");
					ps.setLong(7, val.getIdPrR());
					ps.setLong(1, val.getIdPr().getIdPr());
					ps.setString(2, val.getOpcionR());
					ps.setString(3, val.getValorLR());
					ps.setFloat(4, val.getValorNR());
					ps.setBoolean(5, val.getEsCorrectaR());
					ps.setInt(6, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update preguntas_respuestas set estatus = -1 where idprr = ?");
					ps.setLong(1, val.getIdPrR());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
	
		return ret;
	}
	
	public MPreguntaRespuestas cargarPreguntaRespuestas(ResultSet rs)
	{	MPreguntaRespuestas ret = null;
	
		if (rs != null)
		{	ret = new MPreguntaRespuestas();
			try {
				ret.setIdPrR(rs.getLong(1));
				
				MPregunta p = new MPregunta();
				p.setIdPr(rs.getLong(2));	
				p = p.buscarPregunta(p);
				ret.setIdPr(p);
				
				ret.setOpcionR(rs.getString(3));
				ret.setValorLR(rs.getString(4));
				ret.setValorNR(rs.getFloat(5));
				ret.setEsCorrectaR(rs.getBoolean(6));
				ret.setEstatus(rs.getInt(7));
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
	
		return ret;
	}

}
