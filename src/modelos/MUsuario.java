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


public class MUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idU;
	private String nombreU;
	private String apellidoU;
	private Long tipoDocumentoU;
	private String ciU;
	private String telefonoU;
	private String telefonoCU;
	private MDireccion idD;
	private String emailU;
	private Date fechaNU;
	private String lugarNU;
	private String nacionalidadU;
	private String loginU;
	private String passwordU;
	private String fotoPU;
	private String calificacionU;
	private String codigoVerificacionU;
	private String codigoVerificadoU;
	private Boolean recibeNU;
	private Integer estatus;

	public MUsuario() {
		
		idU = new Long(0);
		nombreU = "";
		apellidoU = "";
		tipoDocumentoU = null;
		ciU = "";
		telefonoU = "";
		telefonoCU = "";
		idD = null;
		emailU = "";
		fechaNU = null;
		lugarNU = "";
		nacionalidadU = "";
		loginU = "";
		passwordU = "";
		fotoPU = "";
		calificacionU = "";
		codigoVerificacionU = "";
		codigoVerificadoU = "";
		recibeNU = null;
		estatus = 0;
		
	}

	public Long getIdU() {
		return idU;
	}

	public void setIdU(Long idU) {
		this.idU = idU;
	}

	public String getNombreU() {
		return nombreU;
	}

	public void setNombreU(String nombreU) {
		this.nombreU = nombreU;
	}

	public String getApellidoU() {
		return apellidoU;
	}

	public void setApellidoU(String apellidoU) {
		this.apellidoU = apellidoU;
	}

	public Long getTipoDocumentoU() {
		return tipoDocumentoU;
	}

	public void setTipoDocumentoU(Long tipoDocumentoU) {
		this.tipoDocumentoU = tipoDocumentoU;
	}

	public String getCiU() {
		return ciU;
	}

	public void setCiU(String ciU) {
		this.ciU = ciU;
	}

	public String getTelefonoU() {
		return telefonoU;
	}

	public void setTelefonoU(String telefonoU) {
		this.telefonoU = telefonoU;
	}

	public String getTelefonoCU() {
		return telefonoCU;
	}

	public void setTelefonoCU(String telefonoCU) {
		this.telefonoCU = telefonoCU;
	}

	public MDireccion getIdD() {
		return idD;
	}

	public void setIdD(MDireccion idD) {
		this.idD = idD;
	}

	public String getEmailU() {
		return emailU;
	}

	public void setEmailU(String emailU) {
		this.emailU = emailU;
	}

	public Date getFechaNU() {
		return fechaNU;
	}

	public void setFechaNU(Date fechaNU) {
		this.fechaNU = fechaNU;
	}

	public String getLugarNU() {
		return lugarNU;
	}

	public void setLugarNU(String lugarNU) {
		this.lugarNU = lugarNU;
	}

	public String getNacionalidadU() {
		return nacionalidadU;
	}

	public void setNacionalidadU(String nacionalidadU) {
		this.nacionalidadU = nacionalidadU;
	}

	public String getLoginU() {
		return loginU;
	}

	public void setLoginU(String loginU) {
		this.loginU = loginU;
	}

	public String getPasswordU() {
		return passwordU;
	}

	public void setPasswordU(String passwordU) {
		this.passwordU = passwordU;
	}

	public String getFotoPU() {
		return fotoPU;
	}

	public void setFotoPU(String fotoPU) {
		this.fotoPU = fotoPU;
	}

	public String getCalificacionU() {
		return calificacionU;
	}

	public void setCalificacionU(String calificacionU) {
		this.calificacionU = calificacionU;
	}

	public String getCodigoVerificacionU() {
		return codigoVerificacionU;
	}

	public void setCodigoVerificacionU(String codigoVerificacionU) {
		this.codigoVerificacionU = codigoVerificacionU;
	}

	public String getCodigoVerificadoU() {
		return codigoVerificadoU;
	}

	public void setCodigoVerificadoU(String codigoVerificadoU) {
		this.codigoVerificadoU = codigoVerificadoU;
	}

	public Boolean getRecibeNU() {
		return recibeNU;
	}

	public void setRecibeNU(Boolean recibeNU) {
		this.recibeNU = recibeNU;
	}

	public Integer getEstatus() {
		return estatus;
	}

	public void setEstatus(Integer estatus) {
		this.estatus = estatus;
	}
	
	public MUsuario buscarUsuario(MUsuario val)
	{	MUsuario ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from usuarios where idu = ?");
			ps.setLong(1, val.getIdU());
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarUsuario(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}
	
	public List<MUsuario> buscarUsuarios()
	{	List<MUsuario> ret = null;
	
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from usuarios");
			ResultSet rs = ps.executeQuery();
			MUsuario m = new MUsuario();
			ret = new ArrayList<MUsuario>();
			while (rs.next())
			{	m = cargarUsuario(rs);
				ret.add(m);
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
	
		return  ret;
	}

	public MUsuario buscarUsuarioPorCampos(MUsuario val)
	{	MUsuario ret = null;
		String tabla = "select * from usuarios";
		String where = "";
		if (val.getIdU() != 0)
		{	where += where.equals("")?"":" and ";	
			where += " idu = " + val.getIdU();
		}
		if (!val.getNombreU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " nombreu = '" + val.getNombreU() + "'";
		}
		if (!val.getApellidoU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " apellidou = '" + val.getApellidoU() + "'";
		}
		if (val.getTipoDocumentoU() != null)
		{	where += where.equals("")?"":" and ";	
			where += " tipodocumentou = " + val.getTipoDocumentoU();
		}
		if (!val.getCiU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " ciu = '" + val.getCiU() + "'";
		}
		if (!val.getTelefonoU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " telefonou = '" + val.getTelefonoU() + "'";
		}
		if (!val.getTelefonoCU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " telefonocu = '" + val.getTelefonoCU() + "'";
		}
		if (val.getIdD() != null)
		{	where += where.equals("")?"":" and ";	
			where += " idd = " + val.getIdD();
		}
		if (!val.getEmailU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " emailu = '" + val.getEmailU() + "'";
		}
		if (val.getFechaNU() != null )
		{	where += where.equals("")?"":" and ";	
			where += " fechanu = '" + val.getFechaNU() + "'" ;
		}
		if (!val.getLugarNU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " lugarnu = '" + val.getLugarNU() + "'";
		}
		if (!val.getNacionalidadU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " nacionalidadu = '" + val.getNacionalidadU() + "'";
		}
		if (!val.getLoginU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " loginu = '" + val.getLoginU() + "'";
		}
		if (!val.getPasswordU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " passwordu = '" + val.getPasswordU() + "'";
		}
		if (!val.getFotoPU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " fotopu = '" + val.getFotoPU() + "'";
		}
		if (!val.getCalificacionU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " calificacionu = '" + val.getCalificacionU() + "'";
		}
		if (!val.getCodigoVerificacionU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " codigoverificacionu = '" + val.getCodigoVerificacionU() + "'";
		}
		if (!val.getCodigoVerificadoU().equals(""))
		{	where += where.equals("")?"":" and ";	
			where += " codigoverificadou = '" + val.getCodigoVerificadoU() + "'";
		}
		if (val.getRecibeNU() != null)
		{	where += where.equals("")?"":" and ";	
			where += " recibenu = '" + val.getRecibeNU() + "'";
		}
		if (val.getEstatus() != 0)
		{	where += where.equals("")?"":" and ";	
			where += " estatus = " + val.getEstatus();
		}
		
		if (!where.equals(""))
		{	where = " where " + where;
			
		}
		
		Connection con = Conexion.conectar();
		try
		{	PreparedStatement ps = con.prepareStatement(tabla + where);
			ResultSet rs = ps.executeQuery();
			if (rs!=null)
			{	ret = cargarUsuario(rs);
				
			}
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		return ret;
	}
	
	
	public Boolean actualizarUsuario(MUsuario val, Integer opcion)
	{	Boolean ret = false;
	
		Connection con = Conexion.conectar();
		PreparedStatement ps = null;
		try {
			switch (opcion)
			{	case 1: //Insertar nuevo
					ps = con.prepareStatement("insert into usuarios values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setLong(1, val.getIdU());
					ps.setString(2, val.getNombreU());
					ps.setString(3, val.getApellidoU());
					ps.setLong(4, val.getTipoDocumentoU());
					ps.setString(5, val.getCiU());
					ps.setString(6, val.getTelefonoU());
					ps.setString(7, val.getTelefonoCU());
					ps.setLong(8, val.getIdD().getIdD());
					ps.setString(9, val.getEmailU());
					ps.setDate(10, val.getFechaNU());
					ps.setString(11, val.getLugarNU());
					ps.setString(12, val.getNacionalidadU());
					ps.setString(13, val.getLoginU());
					ps.setString(14, val.getPasswordU());
					ps.setString(15, val.getFotoPU());
					ps.setString(16, val.getCalificacionU());
					ps.setString(17, val.getCodigoVerificacionU());
					ps.setString(18, val.getCodigoVerificadoU());
					ps.setBoolean(19, val.getRecibeNU());
					ps.setInt(20, val.getEstatus());
					break;
				case 2:	//Actualizar existente
					ps = con.prepareStatement("update usuarios set "
							+ " nombreu = ?,"
							+ " apellidou = ?,"
							+ " tipodocumentou = ?,"
							+ " ciu = ?,"
							+ " telefonou = ?,"
							+ " telefonocu = ?,"
							+ " idd = ?,"
							+ " emailu = ?,"
							+ " fechanu = ?,"
							+ " lugarnu = ?,"
							+ " nacionalidadu = ?,"
							+ " loginu = ?,"
							+ " passwordu = ?,"
							+ " fotopu = ?,"
							+ " calificacionu = ?,"
							+ " codigoverificacionu = ?,"
							+ " codigoverificadou = ?,"
							+ " recibenu = ?,"
							+ " estatus = ?"
							+ " where idu = ?");
					ps.setLong(20, val.getIdU());
					ps.setString(1, val.getNombreU());
					ps.setString(2, val.getApellidoU());
					ps.setLong(3, val.getTipoDocumentoU());
					ps.setString(4, val.getCiU());
					ps.setString(5, val.getTelefonoU());
					ps.setString(6, val.getTelefonoCU());
					ps.setLong(7, val.getIdD().getIdD());
					ps.setString(8, val.getEmailU());
					ps.setDate(9, val.getFechaNU());
					ps.setString(10, val.getLugarNU());
					ps.setString(11, val.getNacionalidadU());
					ps.setString(12, val.getLoginU());
					ps.setString(13, val.getPasswordU());
					ps.setString(14, val.getFotoPU());
					ps.setString(15, val.getCalificacionU());
					ps.setString(16, val.getCodigoVerificacionU());
					ps.setString(17, val.getCodigoVerificadoU());
					ps.setBoolean(18, val.getRecibeNU());
					ps.setInt(19, val.getEstatus());
					break;
				case 3: //Eliminar lógicamente
					ps = con.prepareStatement("update usuarios set estatus = -1 where idu = ?");
					ps.setLong(1, val.getIdU());
					break;
			}
			ret = ps.executeUpdate()!=0?true:false;
			
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}

	
		return ret;
	}
	
	public MUsuario cargarUsuario(ResultSet rs)
	{	MUsuario ret = null;
	
		try {
			if (rs.next())
			{	
				ret = new MUsuario();
			
				ret.setIdU(rs.getLong(1));
				ret.setNombreU(rs.getString(2));
				ret.setApellidoU(rs.getString(3));
				
				ret.setTipoDocumentoU(rs.getLong(4));
				
				ret.setCiU(rs.getString(5));
				ret.setTelefonoU(rs.getString(6));
				ret.setTelefonoCU(rs.getString(7));
				
				MDireccion d = new MDireccion();
				d.setIdD(rs.getLong(8));
				d = d.buscarDireccionById(d);
				ret.setIdD(d);
				
				ret.setEmailU(rs.getString(9));
				ret.setFechaNU(rs.getDate(10));
				ret.setLugarNU(rs.getString(11));
				ret.setNacionalidadU(rs.getString(12));
				ret.setLoginU(rs.getString(13));
				ret.setPasswordU(rs.getString(14));
				ret.setFotoPU(rs.getString(15));
				ret.setCalificacionU(rs.getString(16));
				ret.setCodigoVerificacionU(rs.getString(17));
				ret.setCodigoVerificadoU(rs.getString(18));
				ret.setRecibeNU(rs.getBoolean(19));
				ret.setEstatus(rs.getInt(20));
			}
				
		} catch (SQLException e) {
			//TODO: manejo de mensajes
			new SQLExcepcion("Error ejecutando el SQL");
		}
		

	
		return ret;
	}
	public Boolean esFacilitador(MUsuario u)
	{	Boolean ret = false;
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from facilitadores where idu = ?");
			ps.setLong(1, u.getIdU());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{	ret = true;
				
			}
		} catch (SQLException e) {
			new SQLExcepcion("Error ejecutando el SQL");
		}

		return ret;
	}
	public Boolean esParticipante(MUsuario u)
	{	Boolean ret = false;
		Connection con = Conexion.conectar();
		try {
			PreparedStatement ps = con.prepareStatement("select * from participantes where idu = ?");
			ps.setLong(1, u.getIdU());
			ResultSet rs = ps.executeQuery();
			if (rs.next())
			{	ret = true;
				
			}
		} catch (SQLException e) {
			new SQLExcepcion("Error ejecutando el SQL");
		}

		return ret;
	}
}
