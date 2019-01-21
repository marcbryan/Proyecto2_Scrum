package idao;

import model.Usuario;

public interface IScrumConfig {
	public boolean bd_online();
	public Usuario login(String nombre_usuario, String password);
	public void insertarUsuario(Usuario usuario);
}
