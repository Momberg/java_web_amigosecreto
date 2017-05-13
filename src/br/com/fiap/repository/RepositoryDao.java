package br.com.fiap.repository;

import br.com.fiap.dao.CompradoresDao;
import br.com.fiap.dao.LivrosDao;
import br.com.fiap.dao.UsuariosDao;

public class RepositoryDao {
	static UsuariosDao usuariosDao;
	static LivrosDao livrosDao;
	static CompradoresDao compDao;
	
	public static UsuariosDao getUsuariosDao() {
		if (usuariosDao == null) {
			usuariosDao = new UsuariosDao();
		}
		return usuariosDao;
	}

	public static LivrosDao getLivrosDao() {
		if (livrosDao == null) {
			livrosDao = new LivrosDao();
		}
		return livrosDao;
	}
	
	public static CompradoresDao getCompradoresDao() {
		if (compDao == null) {
			compDao = new CompradoresDao();
		}
		return compDao;
	}
	
}
