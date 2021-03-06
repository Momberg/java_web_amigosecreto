package br.com.fiap.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.fiap.entity.Grupos;
import br.com.fiap.entity.Pessoas;

public class GruposDao {
	Session session = null;
	Transaction transaction = null;
	
    public String salvar(Grupos grupo){
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		session.save(grupo);
    		transaction.commit();
    		
    		return "grupo salvo";
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }
    
    public Grupos buscar(Integer id){
    	session = HibernateUtil.getSessionFactory().getCurrentSession();
    	transaction = session.beginTransaction();
    	Grupos grupo = (Grupos)session.load(Grupos.class, id);
    	transaction.commit();
    	
    	return grupo;
    }    
    
    
	@SuppressWarnings("unchecked")
	public List<Grupos> listar(){
		List<Grupos> lista = null;
		try {
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		transaction = session.beginTransaction();
    		Query q = session.createQuery("FROM Grupos");
    		lista = q.list();
    		transaction.commit();
    		
		} catch (Exception e) {		
		}    	
		return lista;
    }
	
	public Grupos pesquisarGrupo(String cod) {
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
    	transaction = session.beginTransaction();
    	
    	Query q =  session.createQuery("FROM Grupos WHERE cod_grupo=:codigo");
    	q.setParameter("codigo", cod);
    	
    	return (Grupos)q.uniqueResult();
		
	}
	
	public Grupos pesquisarGrupoAdmin(String cod, String cpf) {
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
    	transaction = session.beginTransaction();
    	
    	Query q =  session.createQuery("FROM Grupos WHERE cod_grupo=:codigo and cpf_usuario=:cpf");
    	q.setParameter("codigo", cod);
    	q.setParameter("cpf", cpf);
    	
    	return (Grupos)q.uniqueResult();
		
	}
	
	public String update(Grupos grupo){
    	try{
    		session = HibernateUtil.getSessionFactory().getCurrentSession();
    		Transaction t = session.beginTransaction();
            session.update(grupo);
            t.commit();
    		return "Grupo atualizado";
    	}catch(Exception e){
    		return e.getMessage();
    	}
    }
	
}
