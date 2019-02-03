package daoImpl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import idao.IScrumConfig;
import model.Especificacion;
import model.Proyecto;
import model.Usuario;

public class ScrumDAOImpl implements IScrumConfig {

	public boolean bd_online() {
		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
			EntityManager entityManager = factory.createEntityManager();
			entityManager.getTransaction().begin();
			// Realizamos una consulta con la sintaxis de SQL (Native Query) a la base de
			// datos para comprobar si hay conexión
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
			// Consultamos si el nombre de usuario y la password son correctos
			// JPQL opera entre objetos Java en vez de con las tablas de la base de datos
			String sql = "SELECT u from Usuario u where u.nombre_usuario = :username and u.password_usuario = :password";
			Query query = entityManager.createQuery(sql);
			query.setParameter("username", nombre_usuario);
			query.setParameter("password", password);
			// Guardamos el resultado en el objeto Usuario
			try {
				usr = (Usuario) query.getSingleResult();
			} catch (NoResultException noRes) {
				System.out.println("Usuario incorrecto");
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
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
			// System.out.println("Usuario insertado!");
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
			// Consultamos si el nombre de usuario y la password son correctos
			// JPQL opera entre objetos Java en vez de con las tablas de la base de datos
			String sql = "SELECT u.nombre_usuario from Usuario u where u.nombre_usuario = :username";
			Query query = entityManager.createQuery(sql);
			query.setParameter("username", username);
			try {
				String usrname = (String) query.getSingleResult();
				// System.out.println(El usuario "+usrname+" ya existe");
				check = true;
			} catch (NoResultException noRes) {
				// System.out.println("El usuario está disponible");
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
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
			// System.out.println("Proyecto insertado!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}
	}

	public boolean comprobarProyecto(String nombre_proyecto) {
		boolean check = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			// JPQL opera entre objetos Java en vez de con las tablas de la base de datos
			String sql = "SELECT p.nombre_proyecto from Proyecto p where p.nombre_proyecto = :nombre_proyecto";
			Query query = entityManager.createQuery(sql);
			query.setParameter("nombre_proyecto", nombre_proyecto);
			try {
				// Si consigue recoger un resultado, el proyecto existe
				String nmProyecto = (String) query.getSingleResult();
				check = true;
			} catch (NoResultException noRes) {
				// System.out.println("El proyecto está disponible");
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}
		return check;
	}

	public List<Object[]> getScrumMasters() {
		List<Object[]> scrum_masters = null;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			scrum_masters = entityManager.createQuery(
					"SELECT u.id_usuario, u.nombre_apellidos from Usuario u where u.tipo_usuario = 'Scrum Master'")
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return scrum_masters;
	}

	public List<Object[]> getProductOwners() {
		List<Object[]> product_owners = null;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			product_owners = entityManager.createQuery(
					"SELECT u.id_usuario, u.nombre_apellidos from Usuario u where u.tipo_usuario = 'Product Owner'")
					.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return product_owners;
	}
	
	public List<Proyecto> getProyectos() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		List<Proyecto> proyectos = new ArrayList<Proyecto>();
		try {
			String sql = "SELECT p from Proyecto p";
			Query query = entityManager.createQuery(sql);
			try {
				proyectos = query.getResultList();
			} catch (NoResultException noRes) {
				System.out.println("No hay proyectos en la base de datos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}
		return proyectos;
	}

	public String getNombre(int id_usuario) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		String nombreapellido = null;
		try {
			String sql = "SELECT u.nombre_apellidos from Usuario u where u.id_usuario=:id";
			Query query = entityManager.createQuery(sql);
			query.setParameter("id", id_usuario);
			nombreapellido = (String) query.getSingleResult();

		} catch (Exception e) {

			e.printStackTrace();
		}
		return nombreapellido;

	}

	public int getIdGrupo(String nombre_usuario) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		int id_grupo = 0;
		try {
			String sql = "SELECT u.id_grupo from Usuario u where u.nombre_usuario=:nom_usr";// and
																							// u.tipo_usuario=:type_user";
			Query query = entityManager.createQuery(sql);
			query.setParameter("nom_usr", nombre_usuario);
			id_grupo = (Integer) query.getSingleResult();
			// query.setParameter("type_user", tipo_usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id_grupo;
	}
	
	public List<Proyecto> getProyectos(int id_grupo) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		List<Proyecto> proyectos = new ArrayList<Proyecto>();
		try {
			String sql = "SELECT p from Proyecto p where p.id_grupo=:id";
			Query query = entityManager.createQuery(sql);
			query.setParameter("id", id_grupo);
			try {
				proyectos = query.getResultList();
			} catch (NoResultException noRes) {
				System.out.println("No hay proyectos en la base de datos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}
		return proyectos;
	}
	
	public void aplicarCambios() {
		SQLiteDAOImpl ac = new SQLiteDAOImpl();
		ac.syncDBs();
	}
	
	public void insertarEspecificacion(Especificacion espec) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(espec);
			entityManager.getTransaction().commit();
			// System.out.println("Especificación insertada!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}
	}
	
	// Métodos propios de esta clase

	/**
	 * Este método solo lo utilizará este otro método
	 * {@link SQLiteDAOImpl#syncRemotaAlCrearla(String)}
	 * 
	 * @return Devuelve los usuarios de la base de datos remota en un list de
	 *         usuarios
	 */
	protected static List<Usuario> getUsuariosFromRemoteDB() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		List<Usuario> usuarios = new ArrayList<Usuario>();
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

	/**
	 * Este método solo lo utilizará este otro método
	 * {@link SQLiteDAOImpl#syncRemotaAlCrearla(String)}
	 * 
	 * @return Devuelve los proyectos de la base de datos remota en un list de
	 *         proyectos
	 */
	protected static List<Proyecto> getProyectosFromRemoteDB() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		List<Proyecto> proyectos = new ArrayList<Proyecto>();
		try {
			String sql = "SELECT p from Proyecto p";
			Query query = entityManager.createQuery(sql);
			try {
				proyectos = query.getResultList();
			} catch (NoResultException noRes) {
				System.out.println("No hay proyectos en la base de datos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}
		return proyectos;
	}
	
	/**
	 * Este método solo lo utilizará este otro método
	 * {@link SQLiteDAOImpl#syncRemotaAlCrearla(String)}
	 * 
	 * @return Devuelve las especificaciones de la base de datos remota en un list de
	 *         especificaciones
	 */
	protected static List<Especificacion> getEspecificacionesFromRemoteDB() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		List<Especificacion> especificaciones = new ArrayList<Especificacion>();
		try {
			String sql = "SELECT e from Especificacion e";
			Query query = entityManager.createQuery(sql);
			try {
				especificaciones = query.getResultList();
			} catch (NoResultException noRes) {
				System.out.println("No hay especificaciones en la base de datos");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}
		return especificaciones;
	}
	
	/**
	 * Este método ejecutará la sentencia que le pasemos en la base de datos remota.
	 * Solo lo utilizará el método {@link SQLiteDAOImpl#syncDBs()} para que los cambios que se hicieron en la base de datos embebida se realizen en la remota. 
	 * @param sentencia - La que queremos ejecutar en la base de datos remota
	 * @return Devuelve <b>true</b> si la sentencia se ha realizado correctamente y <b>false</b> si no ha funcionado.
	 */
	protected static boolean addOfflineChanges(String sentencia) {
		boolean inserted = false;
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
		EntityManager entityManager = factory.createEntityManager();
		try {
			entityManager.getTransaction().begin();
			// Realizamos un insert con la sintaxis de SQL (Native Query) a la base de datos remota
			Query query = entityManager.createNativeQuery(sentencia);
			//System.out.println(query.executeUpdate());
			int status = query.executeUpdate();
			if (status > 0) {
				inserted = true;
			}
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close();
			factory.close();
		}
		return inserted;
	}
	
}
