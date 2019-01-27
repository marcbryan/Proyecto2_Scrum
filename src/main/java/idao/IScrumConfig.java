package idao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Proyecto;
import model.Usuario;

public interface IScrumConfig {
	//M�todos que implementar�n las clases que implementen esta interface
	
	//Seguramente se borrar� y ser� utilizado �nicamente en el dao de la bd remota ya que en el dao de sqlite es in�til
	public boolean bd_online();
	
	/**
	 * Comprueba si se puede hacer login (o no) con el nombre de usuario y contrase�a que se introduzcan
	 * @param nombre_usuario - El nombre de usuario con el que se quiere hacer login
	 * @param password - La contrase�a con la que se quiere hacer login
	 * @return Devolver� un usuario nulo si no existe en la base de datos y devolver� el usuario y sus datos si el login es correcto
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
	 * @return Devuelve <b>true</b> si el nombre de usuario ya existe y devolver� <b>false</b> si el nombre de usuario est� disponible
	 */
	public boolean comprobarUsuario(String username);
	
	
	/**
	 * Inserta un proyecto en la base de datos
	 * @param proyecto - El proyecto a insertar
	 */
	public void insertarProyecto(Proyecto proyecto);
	
	/**
	 * Se utiliza para poner los Scrum Master en el JComboBox del InternalFrame de Crear Proyecto
	 * @return Devuelve una lista de los Scrum Master que haya en la base de datos
	 */
	public List<Usuario> getScrumMasters();
	
	/**
	 * Se utiliza para poner los Product Owner en el JComboBox del InternalFrame de Crear Proyecto
	 * @return Devuelve una lista de los Product Owner que haya en la base de datos
	 */
	public List<Usuario> getProductOwners();
	
}
