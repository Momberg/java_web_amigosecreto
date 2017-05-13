package br.com.fiap.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.CompradoresDao;
import br.com.fiap.entity.Compradores;
import br.com.fiap.entity.Livros;
import br.com.fiap.repository.RepositoryDao;

@ManagedBean(name="compBean")
@RequestScoped
public class CompradoresBean {
	
	@ManagedProperty(value="#{beanComp}")
	private Compradores comprador;
	private int codComprador;
	
	public Compradores getComprador() {
		return comprador;
	}

	public void setComprador(Compradores comprador) {
		this.comprador = comprador;
	}
	
	
	
	public int getCodComprador() {
		return codComprador;
	}

	public void setCodComprador(int codComprador) {
		this.codComprador = codComprador;
	}

	public void incluirComprador() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			CompradoresDao dao = RepositoryDao.getCompradoresDao();
			int codLivro = this.getCodComprador();
			
			dao.salvar(comprador,codLivro);
			msg.setSummary("OK");
			msg.setDetail("Comprador " + comprador.getNome() + " incluído");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (Exception e) {

			msg.setSummary("ERRO:");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		}
		context.addMessage(null, msg);
	}
	
	public List<Compradores> getListaCompradores() throws Exception {
		return RepositoryDao.getCompradoresDao().listar();
	}
		
	
	
}
