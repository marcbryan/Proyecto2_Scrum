package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.IScrumConfig;
import model.Proyecto;
import model.Usuario;

public class ScrumDAOImpl implements IScrumConfig {
    
	public boolean bd_online() {	
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
	        EntityManager entityManager = factory.createEntityManager();
	        entityManager.getTransaction().begin();
	        //Realizamos una consulta con la sintaxis de SQL (Native Query) a la base de datos para comprobar si hay conexión
	        entityManager.createNativeQuery("SELECT 1").toString();
	        entityManager.getTransaction().commit();
	        entityManager.close();
	        factory.close();
	        return true;
		} catch (Exception e) {
			System.out.println("No hay conexión con la base de datos remota");
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
	        //System.out.println("Usuario insertado!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
	        factory.close();
		}
	}

	public boolean comprobarUsuario(String username) {
		boolean check = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
        EntityManager entityManager = factory.createEntityManager();
        try {
	        entityManager.getTransaction().begin();
	        //Consultamos si el nombre de usuario y la password son correctos
	        //JPQL opera entre objetos Java en vez de con las tablas de la base de datos
	        String sql = "SELECT u.nombre_usuario from Usuario u where u.nombre_usuario = :username";
	        Query query = entityManager.createQuery(sql);
	        query.setParameter("username", username);
	        //Guardamos el resultado en el objeto Usuario
	        try {
	        	String usrname = (String) query.getSingleResult();
	        	//System.out.println(El usuario "+usrname+" ya existe");
	        	check = true;
	        } catch (NoResultException noRes) {
	        	//System.out.println("El usuario está disponible");
	        }
	    	entityManager.getTransaction().commit();
        } catch (Exception e){
        	e.printStackTrace();
        } finally {
        	entityManager.close();
        	factory.close();
        }
		return check;
	}
	
	public void insertarProyecto(Proyecto proyecto) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		try {
	        entityManager.getTransaction().begin();
	        entityManager.persist(proyecto);
	        entityManager.getTransaction().commit();
	        //System.out.println("Proyecto insertado!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
	        factory.close();
		}
	}
	
	public List<Usuario> getScrumMasters() {
		List <Usuario>scrum_masters = null;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
        EntityManager entityManager = factory.createEntityManager();
        try {
        	entityManager.getTransaction().begin();
        	scrum_masters = entityManager.createQuery("SELECT u from Usuario u where u.tipo_usuario = 'Scrum Master'").getResultList();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scrum_masters;
		
	}
	
	//Falta desarrollarlo
	public List<Usuario> getProductOwners(){
		List <Usuario>product_owners = null;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
        EntityManager entityManager = factory.createEntityManager();
        try {
        	entityManager.getTransaction().begin();
        	product_owners = entityManager.createQuery("SELECT u from Usuario u where u.tipo_usuario = 'Product Owner'").getResultList();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
		return product_owners;
	}
	
	//Métodos propios de esta clase
	
	/**
	 * Este método solo lo utilizará este otro método {@link SQLiteDAOImpl#syncRemotaAlCrearla(String)}
	 * @return Devuelve los usuarios de la base de datos remota en un list de usuarios
	 */
	protected static List<Usuario> getUsuariosFromRemoteDB() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		List <Usuario> usuarios = new ArrayList<Usuario>();
		try {
			String sql = "SELECT u from Usuario u";
			Query query = entityManager.createQuery(sql);
			try {
	        	usuarios = query.getResultList();
	        } catch (NoResultException noRes) {
	        	System.out.println("No hay usuarios en la base de datos");
	        }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
	        factory.close();
		}
		return usuarios;
	}
	
}
