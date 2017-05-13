package br.com.fiap.bean;

import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.fiap.dao.LivrosDao;
import br.com.fiap.entity.Livros;
import br.com.fiap.repository.RepositoryDao;

@ManagedBean
@RequestScoped
public class LivrosBean {

	@ManagedProperty(value = "#{beanLivro}")
	private Livros livro;
	
	private String local;
	private String idiomas[] = { "portugues", "ingles" };

	public Livros getLivro() {
		return livro;
	}

	public void setLivro(Livros livro) {
		this.livro = livro;
	}

	public String[] getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String[] idiomas) {
		this.idiomas = idiomas;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public void incluirLivro() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			LivrosDao dao = RepositoryDao.getLivrosDao();
			dao.salvar(livro);
			msg.setSummary("OK");
			msg.setDetail("Livro " + livro.getTitulo() + " incluído");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (Exception e) {

			msg.setSummary("ERRO:");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		}
		context.addMessage(null, msg);
	}

	public List<Livros> getListaLivros() throws Exception {
		return RepositoryDao.getLivrosDao().listar();
	}

	// alteracao do idioma
	public void alterarIdioma(ValueChangeEvent e) {
		String local = e.getNewValue().toString();
		if (local.equals("ingles")) {
			FacesContext.getCurrentInstance().getViewRoot()
					.setLocale(Locale.ENGLISH);
		} else {
			FacesContext.getCurrentInstance().getViewRoot()
					.setLocale(new Locale("pt", "BR"));
		}
	}
}
