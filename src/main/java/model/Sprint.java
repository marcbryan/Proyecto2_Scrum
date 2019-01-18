package model;

import java.util.Date;

public class Sprint {
	private int id_sprint;
	private int id_proyecto;
	private Date Fecha_Inicio_Sprint;
	private Date Fecha_Final_Sprint;
	private int duracion_sprint;
	private String estado_sprint;
	
	public Sprint(int id_sprint, int id_proyecto, Date fecha_Inicio_Sprint, Date fecha_Final_Sprint,
			int duracion_sprint, String estado_sprint) {
		this.id_sprint = id_sprint;
		this.id_proyecto = id_proyecto;
		Fecha_Inicio_Sprint = fecha_Inicio_Sprint;
		Fecha_Final_Sprint = fecha_Final_Sprint;
		this.duracion_sprint = duracion_sprint;
		this.estado_sprint = estado_sprint;
	}
	
	//getters
	public int getId_sprint() {
		return id_sprint;
	}

	public int getId_proyecto() {
		return id_proyecto;
	}

	public Date getFecha_Inicio_Sprint() {
		return Fecha_Inicio_Sprint;
	}

	public Date getFecha_Final_Sprint() {
		return Fecha_Final_Sprint;
	}

	public int getDuracion_sprint() {
		return duracion_sprint;
	}

	public String getEstado_sprint() {
		return estado_sprint;
	}

	public void setId_sprint(int id_sprint) {
		this.id_sprint = id_sprint;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}
	
	//setters
	public void setFecha_Inicio_Sprint(Date fecha_Inicio_Sprint) {
		Fecha_Inicio_Sprint = fecha_Inicio_Sprint;
	}

	public void setFecha_Final_Sprint(Date fecha_Final_Sprint) {
		Fecha_Final_Sprint = fecha_Final_Sprint;
	}

	public void setDuracion_sprint(int duracion_sprint) {
		this.duracion_sprint = duracion_sprint;
	}

	public void setEstado_sprint(String estado_sprint) {
		this.estado_sprint = estado_sprint;
	}
}
