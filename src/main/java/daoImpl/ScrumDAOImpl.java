package daoImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.IScrumConfig;
import model.Usuario;

public class ScrumDAOImpl implements IScrumConfig {
    
	public boolean bd_online() {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
	        EntityManager entityManager = factory.createEntityManager();
	        entityManager.getTransaction().begin();
	        //Realizamos una consulta con la sintaxis de SQL (Native Query) a la base de datos para comprobar si hay conexion
	        entityManager.createNativeQuery("SELECT 1");
	        entityManager.getTransaction().commit();
	        entityManager.close();
	        factory.close();
	        return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Usuario login(String nombre_usuario, String password) {
		Usuario usr = null;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
        EntityManager entityManager = factory.createEntityManager();
        try {
	        entityManager.getTransaction().begin();
	        //Consultamos si el nombre de usuario y la password son correctos
	        //JPQL opera entre objetos Java en vez de con las tablas de la base de datos
	        String sql = "SELECT u from Usuario u where u.nombre_usuario = :username and u.password_usuario = :password";
	        Query query = entityManager.createQuery(sql);
	        query.setParameter("username", nombre_usuario);
	        query.setParameter("password", password);
	        //Guardamos el resultado en el objeto Usuario
	        try {
	        	usr = (Usuario) query.getSingleResult();
	        } catch (NoResultException noRes) {
	        	System.out.println("Usuario incorrecto");
	        }
	    	entityManager.getTransaction().commit();
        } catch (Exception e){
        	e.printStackTrace();
        } finally {
        	entityManager.close();
        	factory.close();
        }
		return usr;
	}

	public void insertarUsuario(Usuario usuario) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		try {
	        entityManager.getTransaction().begin();
	        entityManager.persist(usuario);
	        entityManager.getTransaction().commit();
	        
	        System.out.println("Insertado!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
	        factory.close();
		}
	}
	
	public static void main(String[] args) {
		IScrumConfig dao = new ScrumDAOImpl();
		System.out.println("Online? -> "+dao.bd_online());
		Usuario usr = dao.login("u1", "u1u1");
		if (usr != null) {
			System.out.println("username: "+usr.getNombre_usuario()+", password: "+usr.getPassword_usuario()+", email: "+usr.getCorreo_usuario());
		}
		
		Usuario usr2 = new Usuario();
		usr2.setNombre_usuario("hibernatemola");
		usr2.setPassword_usuario("1234");
		usr2.setNombre_apellidos("Usuario Usuario");
		usr2.setTipo_usuario("Developer");
		usr2.setCorreo_usuario("hibernatemola@test.com");
		usr2.setId_grupo(1);
		dao.insertarUsuario(usr2);
	}
}
