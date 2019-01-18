package model;

public class GrupoUsuarios {
	private int id_grupo;
	private int id_proyecto;
	
	public GrupoUsuarios(int id_grupo, int id_proyecto) {
		this.id_grupo = id_grupo;
		this.id_proyecto = id_proyecto;
	}
	
	//getters
	public int getId_grupo() {
		return id_grupo;
	}

	public int getId_proyecto() {
		return id_proyecto;
	}
	
	//setters
	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}
	
	
}
