package br.com.fiap.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.entity.Usuarios;

public class UsuariosDao {
	Session session = null;
	Transaction transaction = null;
	
    public String salvar(Usuarios usuario){
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		session.save(usuario);
    		transaction.commit();
    		return "Usuário salvo";
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }

    public Usuarios buscar(int id){
    	return (Usuarios)session.load(Usuarios.class, id);
    }
    
    public boolean validar(Usuarios usuario) throws Exception{
    	
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	transaction = session.beginTransaction();
    	
    	Query q =  session.createQuery("FROM Usuarios WHERE nome=:usuario AND senha=:senha");
    	q.setParameter("usuario", usuario.getNome());
    	q.setParameter("senha", usuario.getSenha());
    	return ((Usuarios)q.uniqueResult() != null);
    }
}
