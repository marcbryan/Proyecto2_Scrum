package idao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Proyecto;
import model.Usuario;

public interface IScrumConfig {
	//Métodos que implementarán las clases que implementen esta interface
	/**
	 * Comprueba si hay conexión con la base remota o no (en la clase SQLiteDAOImpl no hará nada) 
	 * @return Devolverá <b>true</b> si hay conexión con la base de datos remota y <b>false</b> si no hay conexión (tardará un poco en cargar si no hay conexión)
	 */
	public boolean bd_online();
	
	/**
	 * Comprueba si se puede hacer login (o no) con el nombre de usuario y contraseña que se introduzcan
	 * @param nombre_usuario - El nombre de usuario con el que se quiere hacer login
	 * @param password - La contraseña con la que se quiere hacer login
	 * @return Devolverá un usuario nulo si no existe en la base de datos y devolverá el usuario y sus datos si el login es correcto
	 */
	public Usuario login(String nombre_usuario, String password);
	
	/**
	 * Inserta un usuario en la base de datos
	 * @param usuario - El usuario a insertar
	 */
	public void insertarUsuario(Usuario usuario);
	
	/**
	 * Comprueba si el nombre de usuario existe en la base de datos, ya que no se pueden repetir
	 * @param username - El nombre de usuario a comprobar
	 * @return Devuelve <b>true</b> si el nombre de usuario ya existe y devolverá <b>false</b> si el nombre de usuario está disponible
	 */
	public boolean comprobarUsuario(String username);
	
	/**
	 * Inserta un proyecto en la base de datos
	 * @param proyecto - El proyecto a insertar
	 */
	public void insertarProyecto(Proyecto proyecto);
	
	/**
	 * Comprueba si el nombre del proyecto existe en la base de datos, ya que no se puede repetir
	 * @param nombre_proyecto - El nombre del proyecto a comprobar
	 * @return Devuelve <b>true</b> si el nombre del proyecto ya existe y devolverá <b>false</b> si el nombre del proyecto está disponible 
	 */
	public boolean comprobarProyecto(String nombre_proyecto);
	
	/**
	 * Se utiliza para poner los Scrum Master en el JComboBox del InternalFrame de Crear Proyecto
	 * @return Devuelve una lista de los Scrum Master (solo devolverá su ID y su nombre) que haya en la base de datos
	 */
	public List<Object[]> getScrumMasters();
	
	/**
	 * Se utiliza para poner los Product Owner en el JComboBox del InternalFrame de Crear Proyecto
	 * @return Devuelve una lista de los Product Owner (solo devolverá su ID y su nombre) que haya en la base de datos
	 */
	public List<Object[]> getProductOwners();
	
	/**
	 * Se utiliza para conseguir los proyectos que hay y mostrarlos en la ventana de InfoProyectos
	 * @return Devuelve una lista de proyectos
	 */
	public List<Proyecto> getProyectos();
	
	/**
	 * Método para conseguir los proyectos pasando una id de grupo
	 * @param id_grupo - El id de grupo para ver los proyectos de ese grupo
	 * @return Devuelve un list de proyectos que sean del id de grupo que se haya pasado
	 */
	public List<Proyecto> getProyectos(int id_grupo);

	/**
	 * Se utiliza para conseguir el nombre y apellido del ScrumMaster o Product Owner pasandole un id de usuario.
	 * @param id_usuario - El id del Scrum Master o Product Owner
	 * @return Devuelve un string que contiene el nombre y los apellidos
	 */
	public String getNombre(int id_usuario);
	
	/**
	 * Este metodo se usa para conseguir el id del Grupo y luego usar ese id para conseguir todos los proyectos de la tabla Proyectos
	 * @param nombre_usuario - El nombre de usuario (username)
	 * @return Devuelve el id de grupo del usuario
	 */
	public int getIdGrupo(String nombre_usuario);

	/**
	 * Aplicará los cambios que se hayan realizado (si hay cambios) en la base de datos embebida en la remota, en caso contrario, no se hará nada
	 */
	public void aplicarCambios();
	
}
