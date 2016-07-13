package modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import excepciones.SQLExcepcion;
import utilities.Conexion;

public class MPago implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idPa;
	private Date fechaRPa;
	private BigDecimal montoPa;
	private MServicio idS;
	private MUsuario idUPagador;
	private String comprobantePa;
	private String tipoPa;
	private Integer cuotaNPa;
	private Integer estatus;
	
	public MPago() {
		idPa = new Long(0);
		fechaRPa = null;
		montoPa = null;
		idS = null;
		idUPagador = null;
		comprobantePa = "";
		tipoPa = "";
		cuotaNPa = 0;
		estatus = 0;
	}

	public Long getIdPa() {
		return idPa;
	}

	public void setIdPa(Long idPa) {
		this.idPa = idPa;
	}

	public Date getFechaRPa() {
		return fechaRPa;
	}

	public void setFechaRPa(Date fechaRPa) {
		this.fechaRPa = fechaRPa;
	}

	public BigDecimal getMontoPa() {
		return montoPa;
	}

	public void setMontoPa(BigDecimal montoPa) {
		this.montoPa = montoPa;
	}

	public MServicio getIdS() {
		return idS;
	}

	public void setIdS(MServicio idS) {
		this.idS = idS;
	}

	public MUsuario getIdUPagador() {
		return idUPagador;
	}

	public void setIdUPagador(MUsuario idUPagador) {
		this.idUPagador = idUPagador;
	}

	public String getComprobantePa() {
		return comprobantePa;
	}

	public void setComprobantePa(String comprobantePa) {
		this.comprobantePa = comprobantePa;
	}

	public String getTipoPa() {
		return tipoPa;
	}

	public void setTipoPa(String tipoPa) {
		this.tipoPa = tipoPa;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public Integer getCuotaNPa() {
		return cuotaNPa;
	}

	public void setCuotaNPa(Integer cuotaNPa) {
		this.cuotaNPa = cuotaNPa;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MPago buscarPago(MPago val)
	{	MPago ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from pagos where idpa = ?");
			ps.setLong(1, val.getIdPa());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarPago(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MPago> buscarPagos()
	{	List<MPago> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from pagos");
			ResultSet rs = ps.executeQuery();
			MPago m = new MPago();
			ret = new ArrayList<MPago>();
			while (rs.next())
			{	m = cargarPago(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public Boolean actualizarPago(MPago val, Integer opcion)
	{	Boolean ret = false;
		
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into pagos values (?,?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdPa());
					ps.setDate(2, val.getFechaRPa());
					ps.setBigDecimal(3, val.getMontoPa());
					ps.setLong(4, val.getIdS().getIdS());
					ps.setLong(5, val.getIdUPagador().getIdU());
					ps.setString(6, val.getComprobantePa());
					ps.setString(7, val.getTipoPa());
					ps.setInt(8, val.getCuotaNPa());
					ps.setInt(9, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update pagos set "
							+ " fecharpa = ?,"
							+ " montopa = ?,"
							+ " ids = ?,"
							+ " idupagador = ?,"
							+ " comprobantepa = ?,"
							+ " tipopa = ?,"
							+ " estatus = ?"
							+ " where idpa = ?");
					ps.setLong(9, val.getIdPa());
					ps.setDate(1, val.getFechaRPa());
					ps.setBigDecimal(2, val.getMontoPa());
					ps.setLong(3, val.getIdS().getIdS());
					ps.setLong(4, val.getIdUPagador().getIdU());
					ps.setString(5, val.getComprobantePa());
					ps.setString(6, val.getTipoPa());
					ps.setInt(7, val.getCuotaNPa());
					ps.setInt(8, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update pagos set estatus = -1 where idpa = ?");
					ps.setLong(1, val.getIdPa());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		return ret;
	}
	
	public MPago cargarPago(ResultSet rs)
	{	MPago ret = null;
	
		if (rs != null)
		{	ret = new MPago();
			try {
				ret.setIdPa(rs.getLong(1));
				
				ret.setFechaRPa(rs.getDate(2));
				ret.setMontoPa(rs.getBigDecimal(3));
				
				MServicio s = new MServicio();
				s.setIdS(rs.getLong(4));
				s = s.buscarServicio(s);
				ret.setIdS(s);
				
				MUsuario u = new MUsuario();
				u.setIdU(rs.getLong(4));	
				u = u.buscarUsuario(u);
				ret.setIdUPagador(u);
				
				ret.setComprobantePa(rs.getString(6));
				ret.setTipoPa(rs.getString(7));
				ret.setCuotaNPa(rs.getInt(8));
				ret.setEstatus(rs.getInt(9));
				
			} catch (SQLException e) {
				//TODO: manejo de mensajes
				new SQLExcepcion("Error ejecutando el SQL");
			}
		}
			return ret;
		}
	

}
