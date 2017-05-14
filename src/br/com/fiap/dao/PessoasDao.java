package br.com.fiap.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.entity.Pessoas;

public class PessoasDao {
	Session session = null;
	Transaction transaction = null;
	
    public String salvar(Pessoas pessoa){
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		session.save(pessoa);
    		transaction.commit();
    		return "Pessoa salvo";
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }

    public Pessoas buscar(int id){
    	return (Pessoas)session.load(Pessoas.class, id);
    }
    
    public boolean validar(Pessoas pessoa) throws Exception{
    	
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	transaction = session.beginTransaction();
    	
    	Query q =  session.createQuery("FROM Pessoas WHERE email=:pessoa AND cpf=:cpf");
    	q.setParameter("pessoa", pessoa.getEmail());
    	q.setParameter("cpf", pessoa.getCpf());
    	return ((Pessoas)q.uniqueResult() != null);
    }
}
