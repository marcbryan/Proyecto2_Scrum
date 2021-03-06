package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "especificaciones")
public class Especificacion {
	@Column (name = "id_especificacion")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_especificacion;
	
	private String nombre_especificacion;
	private String descripcion_especificacion;
	private String duracion_especificacion;
	private int id_proyecto;
	private int id_sprint;
	private String estado_especificacion;
	
	public Especificacion() {}
	
	public Especificacion(int id_especificacion, String nombre_especificacion, String descricion_especificacion,
			String duracion_especificacion, int id_proyecto, int id_sprint, String estado_especificacion) {
		this.id_especificacion = id_especificacion;
		this.nombre_especificacion = nombre_especificacion;
		this.descripcion_especificacion = descricion_especificacion;
		this.duracion_especificacion = duracion_especificacion;
		this.id_proyecto = id_proyecto;
		this.id_sprint = id_sprint;
		this.estado_especificacion = estado_especificacion;
	}
	
	public Especificacion(String nombre_especificacion, String descricion_especificacion, String duracion_especificacion,
			int id_proyecto, int id_sprint, String estado_especificacion) {
		this.nombre_especificacion = nombre_especificacion;
		this.descripcion_especificacion = descricion_especificacion;
		this.duracion_especificacion = duracion_especificacion;
		this.id_proyecto = id_proyecto;
		this.id_sprint = id_sprint;
		this.estado_especificacion = estado_especificacion;
	}

	//getters
	public int getId_especificacion() {
		return id_especificacion;
	}

	public String getNombre_especificacion() {
		return nombre_especificacion;
	}

	public String getDescricion_especificacion() {
		return descripcion_especificacion;
	}

	public String getDuracion_especificacion() {
		return duracion_especificacion;
	}

	public int getId_proyecto() {
		return id_proyecto;
	}

	public int getId_sprint() {
		return id_sprint;
	}

	public String getEstado_especificacion() {
		return estado_especificacion;
	}
	
	//setters
	public void setId_especificacion(int id_especificacion) {
		this.id_especificacion = id_especificacion;
	}

	public void setNombre_especificacion(String nombre_especificacion) {
		this.nombre_especificacion = nombre_especificacion;
	}

	public void setDescricion_especificacion(String descricion_especificacion) {
		this.descripcion_especificacion = descricion_especificacion;
	}

	public void setDuracion_especificacion(String duracion_especificacion) {
		this.duracion_especificacion = duracion_especificacion;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public void setId_sprint(int id_sprint) {
		this.id_sprint = id_sprint;
	}

	public void setEstado_especificacion(String estado_especificacion) {
		this.estado_especificacion = estado_especificacion;
	}
	
}
