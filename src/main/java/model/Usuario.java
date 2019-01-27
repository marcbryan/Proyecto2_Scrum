package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	@Column(name = "id_usuario")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usuario;
	private String nombre_usuario;
	private String password_usuario;
	private String nombre_apellidos;
	private String tipo_usuario;
	private String correo_usuario;
	private int id_grupo;

	public Usuario() {}

	public Usuario(int id_usuario, String nombre_usuario, String password_usuario, String nombre_apellidos,
			String tipo_usuario, String correo_usuario, int id_grupo) {
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
		this.password_usuario = password_usuario;
		this.nombre_apellidos = nombre_apellidos;
		this.tipo_usuario = tipo_usuario;
		this.correo_usuario = correo_usuario;
		this.id_grupo = id_grupo;
	}

	public Usuario(String nombre_usuario, String password_usuario, String nombre_apellidos, String tipo_usuario,
			String correo_usuario, int id_grupo) {
		this.nombre_usuario = nombre_usuario;
		this.password_usuario = password_usuario;
		this.nombre_apellidos = nombre_apellidos;
		this.tipo_usuario = tipo_usuario;
		this.correo_usuario = correo_usuario;
		this.id_grupo = id_grupo;
	}

	// getters
	public int getId_usuario() {
		return id_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public String getPassword_usuario() {
		return password_usuario;
	}

	public String getNombre_apellidos() {
		return nombre_apellidos;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public String getCorreo_usuario() {
		return correo_usuario;
	}

	public int getId_grupo() {
		return id_grupo;
	}

	// setters
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public void setPassword_usuario(String password_usuario) {
		this.password_usuario = password_usuario;
	}

	public void setNombre_apellidos(String nombre_apellidos) {
		this.nombre_apellidos = nombre_apellidos;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public void setCorreo_usuario(String correo_usuario) {
		this.correo_usuario = correo_usuario;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

}
