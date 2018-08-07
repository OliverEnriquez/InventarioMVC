package Inventrio;

import java.sql.Date;

public class Usuario {
	public String nombre;
	public String apellido;
	public String password;
	public String usuario;
	public Integer permisos;
	public Date fecha;

	public Usuario(String nombre, String apellido, String password, String usuario, Integer permisos, Date fecha) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.usuario = usuario;
		this.permisos = permisos;
		this.fecha = fecha;
	}
	
	public Usuario() {
		// TODO Auto-generated constructor stub
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Integer getPermisos() {
		return permisos;
	}

	public void setPermisos(Integer permisos) {
		this.permisos = permisos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
