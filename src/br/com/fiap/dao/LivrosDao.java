package br.com.fiap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.entity.Livros;

public class LivrosDao {
	Session session = null;
	Transaction transaction = null;
	
    public String salvar(Livros livro){
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		session.save(livro);
    		transaction.commit();
    		
    		return "Livro salvo";
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }
    
    public Livros buscar(Integer id){
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	transaction = session.beginTransaction();
    	Livros livro = (Livros)session.load(Livros.class, id);
    	transaction.commit();
    	
    	return livro;
    }    
    
    
	@SuppressWarnings("unchecked")
	public List<Livros> listar(){
		List<Livros> lista = null;
		try {
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		Query q = session.createQuery("FROM Livros");
    		lista = q.list();
    		transaction.commit();
    		
		} catch (Exception e) {		
		}    	
		return lista;
    }
}
