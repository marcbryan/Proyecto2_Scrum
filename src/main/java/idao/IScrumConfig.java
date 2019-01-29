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
	
}
