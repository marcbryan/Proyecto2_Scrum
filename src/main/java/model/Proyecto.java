package model;

import java.util.Date;

public class Proyecto {
	private int id_proyecto;
	private String nombre_proyecto;
	private String descripcion_proyecto;
	private Date fecha_inicio_proyecto;
	private Date fecha_final_proyecto;
	private int scrum_master_proyecto;
	private int product_owner_proyecto;
	
	public Proyecto(int id_proyecto, String nombre_proyecto, String descripcion_proyecto, Date fecha_inicio_proyecto,
			Date fecha_final_proyecto, int scrum_master_proyecto, int product_owner_proyecto) {
		this.id_proyecto = id_proyecto;
		this.nombre_proyecto = nombre_proyecto;
		this.descripcion_proyecto = descripcion_proyecto;
		this.fecha_inicio_proyecto = fecha_inicio_proyecto;
		this.fecha_final_proyecto = fecha_final_proyecto;
		this.scrum_master_proyecto = scrum_master_proyecto;
		this.product_owner_proyecto = product_owner_proyecto;
	}
	
	//getters
	public int getId_proyecto() {
		return id_proyecto;
	}

	public String getNombre_proyecto() {
		return nombre_proyecto;
	}

	public String getDescripcion_proyecto() {
		return descripcion_proyecto;
	}

	public Date getFecha_inicio_proyecto() {
		return fecha_inicio_proyecto;
	}

	public Date getFecha_final_proyecto() {
		return fecha_final_proyecto;
	}

	public int getScrum_master_proyecto() {
		return scrum_master_proyecto;
	}

	public int getProduct_owner_proyecto() {
		return product_owner_proyecto;
	}
	
	//setters
	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public void setNombre_proyecto(String nombre_proyecto) {
		this.nombre_proyecto = nombre_proyecto;
	}

	public void setDescripcion_proyecto(String descripcion_proyecto) {
		this.descripcion_proyecto = descripcion_proyecto;
	}

	public void setFecha_inicio_proyecto(Date fecha_inicio_proyecto) {
		this.fecha_inicio_proyecto = fecha_inicio_proyecto;
	}

	public void setFecha_final_proyecto(Date fecha_final_proyecto) {
		this.fecha_final_proyecto = fecha_final_proyecto;
	}

	public void setScrum_master_proyecto(int scrum_master_proyecto) {
		this.scrum_master_proyecto = scrum_master_proyecto;
	}

	public void setProduct_owner_proyecto(int product_owner_proyecto) {
		this.product_owner_proyecto = product_owner_proyecto;
	}
	
}
