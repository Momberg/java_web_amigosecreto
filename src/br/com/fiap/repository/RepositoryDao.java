package br.com.fiap.repository;

import br.com.fiap.dao.GruposDao;
import br.com.fiap.dao.UsuariosDao;

public class RepositoryDao {
	static UsuariosDao usuariosDao;
	static GruposDao gruposDao;

	
	public static UsuariosDao getUsuariosDao() {
		if (usuariosDao == null) {
			usuariosDao = new UsuariosDao();
		}
		return usuariosDao;
	}

	public static GruposDao getGruposDao() {
		if (gruposDao == null) {
			gruposDao = new GruposDao();
		}
		return gruposDao;
	}
	
}
