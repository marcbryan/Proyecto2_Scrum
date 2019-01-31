package daoImpl;

import java.io.File;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Frames.Login;
import idao.IScrumConfig;
import model.Proyecto;
import model.Usuario;

public class SQLiteDAOImpl implements IScrumConfig {
	
	private final String pathProyecto = File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator;
	private final String nombreBD = "madali_bd.db";
	
	private Connection conn;
	
	public SQLiteDAOImpl() {
		crearBBDD();
	}
	
	//Métodos a implementar
	
	// No hace nada en esta clase
	public boolean bd_online() {
		return false;
	}
	
	public Usuario login(String nombre_usuario, String password) {
		Usuario usr = null;
		String sql = "SELECT * FROM usuarios where Nombre_Usuario = '" + nombre_usuario + "' and Password_Usuario = '" + password + "'";
		Connection conn = connect();
		Statement stmt = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);
				if (rs.next() == false) {
					System.out.println("Usuario incorrecto");
				} else {
					usr = new Usuario(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7));
					//System.out.println(usr.toString());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// Muy importante cerrar Statement y ResultSet
		        if(stmt != null){
		            try{
		                stmt.close();
		            } catch(Exception e){
		                e.printStackTrace();
		            }
		        }
		        if (rs != null) {
		        	try {
						rs.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
		        }
		    }
		}
		return usr;
	}

	public void insertarUsuario(Usuario usuario) {
		Connection conn = connect();
		PreparedStatement pstmt = null;
		//String sql = "INSERT INTO usuarios VALUES (?, ?, ?, ?, ?, ?, ?);";
		String sql = "INSERT INTO usuarios VALUES (null, "
					+ "'" + usuario.getNombre_usuario() + "', "
					+ "'" + usuario.getPassword_usuario() + "', "
					+ "'" + usuario.getNombre_apellidos() + "', "
					+ "'" + usuario.getTipo_usuario() + "', "
					+ "'" + usuario.getCorreo_usuario() + "', "
					+ usuario.getId_grupo() + ");";
		//System.out.println(sql);
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			guardarSentencia(sql, Login.statusDB);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Muy importante cerrar PreparedStatement
	        if(pstmt != null){
	            try{
	                pstmt.close();
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public boolean comprobarUsuario(String username) {
		boolean check = false;
		Connection conn = connect();
		String sql = "SELECT Nombre_Usuario FROM usuarios where Nombre_Usuario = '" + username + "'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next() == true) {
				//System.out.println(username+" = "+rs.getString(2));
				check = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Muy importante cerrar Statement y ResultSet
	        if(stmt != null){
	            try{
	                stmt.close();
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	        if (rs != null) {
	        	try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	        }
	    }
		return check;
	}
	
	public void insertarProyecto(Proyecto proyecto) {
		Connection conn = connect();
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO proyectos VALUES (null, '"
				+ proyecto.getNombre_proyecto() + "', '"
				+ proyecto.getDescripcion_proyecto() + "', " //'"
				+ "null, "
				//+ proyecto.getFecha_inicio_proyecto() + "', '"
				+ "null, "
				//+ proyecto.getFecha_final_proyecto() + "', '"
				+ proyecto.getScrum_master_proyecto() + ", "
				+ proyecto.getProduct_owner_proyecto() + ", "
				+ proyecto.getIdGrupo() + ");";
		//System.out.println(sql);
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			guardarSentencia(sql, Login.statusDB);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Muy importante cerrar PreparedStatement
	        if(pstmt != null){
	            try{
	                pstmt.close();
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	    }
	}
	
	public boolean comprobarProyecto(String nombre_proyecto) {
		boolean check = false;
		Connection conn = connect();
		String sql = "SELECT Nombre_Proyecto FROM proyectos where Nombre_Proyecto = '" + nombre_proyecto + "'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next() == true) {
				check = true;
			}
		} catch (SQLException e) {
			//e.printStackTrace();
			//System.out.println("El nombre del proyecto está disponible");
		} finally {
			// Muy importante cerrar Statement y ResultSet
	        if(stmt != null){
	            try{
	                stmt.close();
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	        if (rs != null) {
	        	try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	        }
	    }
		return check;
	}
	
	public List<Object[]> getScrumMasters() {
		List<Object[]> lista_sm = new ArrayList<Object[]>();
		Connection conn = connect();
		String sql = "SELECT ID_Usuario, Nombre_Apellidos FROM usuarios where Tipo_Usuario = 'Scrum Master'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			Object[] sm;
			while (rs.next()) {
				// Añadimos a la lista del array de Object solo con el ID y su nombre-apellido que es lo que necesitaremos
				sm = new Object [2];
				sm[0] = rs.getInt(1);
				sm[1] = rs.getString(2);
				lista_sm.add(sm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Muy importante cerrar Statement y ResultSet
	        if(stmt != null){
	            try{
	                stmt.close();
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	        if (rs != null) {
	        	try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	        }
	    }
		return lista_sm;
	}
	
	public List<Object[]> getProductOwners() {
		List<Object[]> lista_po = new ArrayList<Object[]>();
		Connection conn = connect();
		String sql = "SELECT ID_Usuario, Nombre_Apellidos FROM usuarios where Tipo_Usuario = 'Product Owner'";
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			Object[] po;
			while (rs.next()) {
				// Añadimos a la lista del array de Object solo con el ID y su nombre-apellido que es lo que necesitaremos
				po = new Object [2];
				po[0] = rs.getInt(1);
				po[1] = rs.getString(2);
				lista_po.add(po);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// Muy importante cerrar Statement y ResultSet
	        if(stmt != null){
	            try{
	                stmt.close();
	            } catch(Exception e){
	                e.printStackTrace();
	            }
	        }
	        if (rs != null) {
	        	try {
					rs.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
	        }
	    }
		return lista_po;
	}
	
	//Métodos de esta clase

	/**
	 * Método para crear la conexión a la base de datos embebida
	 * @return Devuelve el objeto Connection con la conexión que le hemos puesto
	 * @author Marc
	 */
	private Connection connect() {
		File db = new File(new File("").getAbsoluteFile() + pathProyecto + nombreBD);
		String url = "jdbc:sqlite:" + new File("").getAbsolutePath() + pathProyecto + nombreBD;
		// Si la base de datos existe le devuelvo la conexión, si no le devolveré una conexión nula para obligar a que conecte al menos una con la remota
		// porque no puedo crear una base de datos vacía.
		if (db.exists()) {
			try {
				if (conn == null) {
					conn = DriverManager.getConnection(url);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return conn;
	}
	
	/**
	 * Este método generará la base de datos EMBEBIDA y sus tablas, en el caso en que la base de datos embebida no exista y haya conexión 
	 * con la base de datos remota. También realizará una sincronización con los datos que hayan en la remota.
	 * <br>Se necesita conexión con la base de datos remota porque si creáramos la estructura de la base de datos cuando no hay conexión, 
	 * tendríamos una base de datos con las tablas vacías.
	 * <br>Se encarga de crear el fichero madali_bd.db (este fichero contendrá las tablas y los datos de estas).</br></br>
	 * @author Marc
	 */
	private void crearBBDD() {
		File f = new File("");
		File check_db = new File(f.getAbsoluteFile() + pathProyecto + nombreBD);
		if (!check_db.exists() && Login.statusDB.contentEquals("ONLINE")) {
			String url = "jdbc:sqlite:" + f.getAbsolutePath() + pathProyecto + nombreBD;
			Statement stmt = null;
	        try {
	        	Connection conn = DriverManager.getConnection(url);//connect();
	            if (conn != null) {
	                DatabaseMetaData meta = conn.getMetaData();
	                System.out.println("Base de datos embebida "+nombreBD+" creada correctamente!");
	            }
	            
	            //Sentencias SQL que se ejecutarán para crear las tablas
	            String CREATE_TABLE1 = "CREATE TABLE IF NOT EXISTS especificaciones ("
	            		+ "ID_Especificacion INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
	            		+ "Nombre_Especificacion VARCHAR(40) NOT NULL, "
	            		+ "Descripcion_Especificacion VARCHAR(250) NOT NULL, "
	            		+ "Duracion_Especificacion VARCHAR(20) NOT NULL, "
	            		+ "ID_Proyecto INTEGER NOT NULL, "
	            		+ "ID_Sprint INTEGER NOT NULL, "
	            		+ "Estado_Especificacion VARCHAR(30) NOT NULL);";
	            
	            String CREATE_TABLE2 = "CREATE TABLE IF NOT EXISTS grupos_usuarios ("
	           			+ "ID_Grupo INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " 
	           			+ "ID_Proyecto INTEGER NOT NULL);";
	           
	            String CREATE_TABLE3 = "CREATE TABLE IF NOT EXISTS proyectos ("
	           			+ "ID_Proyecto INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " 
	           			+ "Nombre_Proyecto VARCHAR(30) NOT NULL, " 
	           			+ "Descripcion_Proyecto VARCHAR(250) NOT NULL, "
	           			+ "Fecha_Inicio_Proyecto date, " //NOT NULL, "
	           			+ "Fecha_Final_Proyecto date, " //NOT NULL, "
	           			+ "Scrum_Master_Proyecto INTEGER NOT NULL, "
	           			+ "Product_Owner_Proyecto INTEGER NOT NULL, "
	           			+ "ID_Grupo INTEGER NOT NULL);";
	           
	            String CREATE_TABLE4 = "CREATE TABLE IF NOT EXISTS sprints ("
	            		+ "ID_Sprint INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
	           		 	+ "ID_Proyecto INTEGER NOT NULL, "
	            		+ "Fecha_Inicio_Sprint date NOT NULL, "
	           		 	+ "Fecha_Final_Sprint date NOT NULL, "
	            		+ "Duracion_Sprint INTEGER NOT NULL, "
	           		 	+ "Estado_Sprint VARCHAR(20) NOT NULL);";
	            
	           String CREATE_TABLE5 = "CREATE TABLE IF NOT EXISTS usuarios ("
	        		   	+ "ID_Usuario INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
	        		    + "Nombre_Usuario VARCHAR(30) NOT NULL, "
	        		    + "Password_Usuario VARCHAR(50) NOT NULL, "
	        		    + "Nombre_Apellidos VARCHAR(100) NOT NULL, "
	        		    + "Tipo_Usuario VARCHAR(30) NOT NULL, "
	        		    + "Correo_Usuario VARCHAR(50) NOT NULL, "
	        		    + "ID_Grupo INTEGER NOT NULL);";
	           
	           String CREATE_TABLE6 = "CREATE TABLE IF NOT EXISTS sync ("
	        		    + "ID_sentencia INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, "
	        		    + "sentencia VARCHAR(250) NOT NULL, "
	        		    // En el campo sincronizado: 0 será 'no sincronizado' y 1 será 'sincronizado'
	        		    + "sincronizado INTEGER NOT NULL);";
	           
	           //Ejecucción de las sentencias
	           stmt = conn.createStatement();
	           stmt.execute(CREATE_TABLE1);
	           stmt.execute(CREATE_TABLE2);
	           stmt.execute(CREATE_TABLE3);
	           stmt.execute(CREATE_TABLE4);
	           stmt.execute(CREATE_TABLE5);
	           stmt.execute(CREATE_TABLE6);
	           System.out.println("Tablas creadas correctamente!");
	           syncEmbebidaAlCrearla(Login.statusDB);
	        } catch (SQLException e) {
	        	e.printStackTrace();
			} finally {
				// Muy importante cerrar Statement
		        if(stmt != null){
		            try{
		                stmt.close();
		            } catch(Exception e){
		                e.printStackTrace();
		            }
		        }
		    }
		} else {
			if (check_db.exists()) {
				//System.out.println("La base de datos embebida "+nombreBD+" ya existe.");
			} else {
				System.out.println("No se puede crear la base de datos embebida por primera vez, no hay conexión con la remota");
			}
		}
	}
	
	/**
	 * Este método es utilizado por {@link #crearBBDD()}, solo se utilizá al crear la base de datos embebida.
	 * <br>Sirve para guardar los datos de la BBDD remota (de momento solo los que utilizamos) en la embebida cuando esta es creada.<br>
	 * @param status - El estado de la base de datos OFFLINE/ONLINE
	 * @author Marc
	 */
	private void syncEmbebidaAlCrearla(String status) {
		if (status.equals("ONLINE")) {
			Connection conn = connect();
			PreparedStatement pstmt = null;
			List <Usuario> usuarios = ScrumDAOImpl.getUsuariosFromRemoteDB();
			int numUsuarios = usuarios.size();
			String sql = "";
			for (int i = 0; i < numUsuarios; i++) {
				sql = "INSERT INTO Usuarios VALUES (null, '"
						+ usuarios.get(i).getNombre_usuario() + "', '"
						+ usuarios.get(i).getPassword_usuario() + "', '"
						+ usuarios.get(i).getNombre_apellidos() + "', '"
						+ usuarios.get(i).getTipo_usuario()+ "', '"
						+ usuarios.get(i).getCorreo_usuario() + "', "
						+ usuarios.get(i).getId_grupo() + ");";
				try {
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// Muy importante cerrar PreparedStatement
			        if(pstmt != null){
			            try{
			                pstmt.close();
			            } catch(Exception e){
			                e.printStackTrace();
			            }
			        }
			    }
			}
			
			List<Proyecto> proyectos = ScrumDAOImpl.getProyectosFromRemoteDB();
			int numProyectos = proyectos.size();
			for (int i = 0; i < numProyectos; i++) {
				sql = "INSERT INTO Proyectos VALUES (null, '"
						+ proyectos.get(i).getNombre_proyecto() + "', '"
						+ proyectos.get(i).getDescripcion_proyecto() + "', "
						+ "null, "
						+ "null, "
						+ proyectos.get(i).getScrum_master_proyecto() + ", "
						+ proyectos.get(i).getProduct_owner_proyecto() + ", "
						+ proyectos.get(i).getIdGrupo() + ");";
				try {
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					pstmt.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					// Muy importante cerrar PreparedStatement
			        if(pstmt != null){
			            try{
			                pstmt.close();
			            } catch(Exception e){
			                e.printStackTrace();
			            }
			        }
			    }
			}
			//System.out.println("Primera sincronización de usuarios y proyectos realizada con éxito");
		} else {
			System.out.println("No se puede realizar la primera sincronización, no hay conexión");
		}
	}
	
	/**
	 * Sirve para guardar la sentencia SQL que cambie datos de la base de datos (INSERT o UPDATE) cuando estamos trabajando con la base de datos embebida.
	 * Si la base datos remota está ONLINE no se guardará la sentencia SQL.
	 * @param sentencia - La sentencia SQL que se quiere guardar
	 * @param status - El estado de la base de datos (OFFLINE o ONLINE)
	 * @author Marc
	 */
	private void guardarSentencia(String sentencia, String status) {
		if (status.equals("OFFLINE")) {
			// Para guardar la sentencia no podemos colocar las comillas simples ' entre medio de los textos,
			// asi que reemplazaremos las ' por '' asi SQLite podrá guardar la sentencia completa
			if (sentencia.contains("'")) {
			    sentencia = sentencia.replaceAll("'", "''");
			}
			//System.out.println("SQL stmt: "+sentencia);
			Connection conn = connect();
			PreparedStatement pstmt = null;
			try {
				String sql = "INSERT INTO sync VALUES (null, '"
						+ sentencia + "', "
						+ 0 +");";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// Muy importante cerrar PreparedStatement
		        if(pstmt != null){
		            try{
		                pstmt.close();
		            } catch(Exception e){
		                e.printStackTrace();
		            }
		        }
		    }
		}
	}

	public List<Proyecto> getProyectos() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNombre(int id_usuario) {
		// TODO Auto-generated method stub
		return null;
	}

}
