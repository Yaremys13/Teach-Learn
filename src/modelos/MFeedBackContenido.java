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

public class MFeedBackContenido implements Serializable {


	private static final long serialVersionUID = 1L;
	private Long idFBC;
	private MFeedBack idFB;
	private MPregunta idPr;
	private Integer estatus;
	
	public MFeedBackContenido() {
		idFBC = new Long(0);
		idFB = null;
		idPr = null;
		estatus = 0;
	}

	public Long getIdFBC() {
		return idFBC;
	}

	public void setIdFBC(Long idFBC) {
		this.idFBC = idFBC;
	}

	public MFeedBack getIdFB() {
		return idFB;
	}

	public void setIdFB(MFeedBack idFB) {
		this.idFB = idFB;
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

	public MFeedBackContenido buscarFeedBackContenido(MFeedBackContenido val)
	{	MFeedBackContenido ret = null;
		
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from feedback_contenido where idfbc = ?");
			ps.setLong(1, val.getIdFBC());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarFeedBackContenido(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MFeedBackContenido> buscarFeedBackContenidos()
	{	List<MFeedBackContenido> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from feedback_contenido");
			ResultSet rs = ps.executeQuery();
			MFeedBackContenido m = new MFeedBackContenido();
			ret = new ArrayList<MFeedBackContenido>();
			while (rs.next())
			{	m = cargarFeedBackContenido(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarFeedBackContenido(MFeedBackContenido val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into feedback_contenido values (?,?,?,?)");
					ps.setLong(1, val.getIdFBC());
					ps.setLong(2, val.getIdFB().getIdFB());
					ps.setLong(3, val.getIdPr().getIdPr());
					ps.setInt(4, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update feedback_contenido set "
							+ " idfb = ?,"
							+ " idpr = ?,"
							+ " estatus = ?"
							+ " where idfbc = ?");
					ps.setLong(4, val.getIdFBC());
					ps.setLong(1, val.getIdFB().getIdFB());
					ps.setLong(2, val.getIdPr().getIdPr());
					ps.setInt(3, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update feedback_contenido set estatus = -1 where idfbc = ?");
					ps.setLong(1, val.getIdFBC());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return ret;
	}
	
	public MFeedBackContenido cargarFeedBackContenido(ResultSet rs)
	{	MFeedBackContenido ret = null;
	
		if (rs != null)
		{	ret = new MFeedBackContenido();
			try {
				ret.setIdFBC(rs.getLong(1));
				
				MFeedBack fb = new MFeedBack();
				fb.setIdFB(rs.getLong(2));
				fb = fb.buscarFeedBack(fb);
				ret.setIdFB(fb);
				
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
