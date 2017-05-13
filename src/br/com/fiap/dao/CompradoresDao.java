package br.com.fiap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.entity.Compradores;
import br.com.fiap.entity.Livros;

public class CompradoresDao {
	Session session = null;
	Transaction transaction = null;
	
    public String salvar(Compradores cmp){
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		session.save(cmp);
    		transaction.commit();
    		
    		return "Livro salvo";
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }

    public String salvar(Compradores cmp, int codLivro){
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		Livros livro = (Livros)session.load(Livros.class, codLivro);
    		cmp.setLivro(livro);
    		livro.getCompradores().add(cmp);
    		session.save(cmp);
    		transaction.commit();
    		
    		return "Livro salvo";
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }    
    
    public Compradores buscar(Integer id){
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	transaction = session.beginTransaction();
    	Compradores cmp = (Compradores)session.load(Compradores.class, id);
    	transaction.commit();
    	return cmp;
    }    
    
    
	@SuppressWarnings("unchecked")
	public List<Compradores> listar(){
		List<Compradores> lista = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
    		Query q = session.createQuery("FROM Compradores");
    		lista = q.list();
    		transaction.commit();    		 			
		} catch (Exception e) {		
		}    	
		return lista;
    }	
}
