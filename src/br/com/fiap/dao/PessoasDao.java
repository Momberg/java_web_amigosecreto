package br.com.fiap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.entity.Grupos;
import br.com.fiap.entity.Pessoas;
import br.com.fiap.entity.Usuarios;

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
    	
    	Query q =  session.createQuery("FROM Pessoas WHERE cpf=:cpf");
    	q.setParameter("cpf", pessoa.getCpf());
    	return ((Pessoas)q.uniqueResult() != null);
    }
    
    @SuppressWarnings("unchecked")
	public List<Pessoas> listar(Usuarios usuario){
		List<Pessoas> lista = null;
		try {
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		Query q = session.createQuery("FROM Pessoas WHERE cpf=:cpf");
    		q.setParameter("cpf", usuario.getCpf());
    		lista = q.list();
    		transaction.commit();
    		
		} catch (Exception e) {		
		}    	
		return lista;
    }
    
    public List<Pessoas> listarPessoaPorGrupo(String cod) {
    	List<Pessoas> lista = null;
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
		transaction = session.beginTransaction();
		Query q = session.createQuery("FROM Pessoas WHERE cod_grupo=:codigo");
		q.setParameter("codigo", cod);
		lista = q.list();
		transaction.commit();
    	
		return lista;
    }
    
    public String update(Pessoas pessoa){
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		Transaction t = session.beginTransaction();
            session.update(pessoa);
            t.commit();
    		return "Pessoa atualizada";
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }
    
    
}
