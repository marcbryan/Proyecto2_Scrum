package daoTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import model.Usuario;

/* FALTA PONER AUTO INCREMENT A TODOS LOS ID DE LA BASE DE DATOS*/


public class ScrumDAOTest {
 
    public static void main(String[] args) {/*
    	try {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
        
        Usuario u1 = new Usuario();
        //u1.setId_usuario(1);
        u1.setCorreo_usuario("u1@gmail.com");
        u1.setId_grupo(1);
        u1.setNombre_apellidos("Ali Murtaza");
        u1.setNombre_usuario("u1");
        u1.setPassword_usuario("u1u1");
        u1.setTipo_usuario("Scrum Master");
        
        entityManager.persist(u1);
         
        entityManager.getTransaction().commit();
         
        entityManager.close();
        factory.close();
    	} catch (PersistenceException e) {
			e.printStackTrace();
		}*/
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory("madali_db");
        EntityManager entityManager = factory.createEntityManager();
         
        entityManager.getTransaction().begin();
    	Integer primaryKey = 1;
    	Usuario user = entityManager.find(Usuario.class, primaryKey);
    	System.out.println("user: "+user.getNombre_apellidos());
    	entityManager.getTransaction().commit();
        
        entityManager.close();
        factory.close();
    }
}