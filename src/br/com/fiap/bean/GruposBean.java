package br.com.fiap.bean;

import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.fiap.dao.GruposDao;
import br.com.fiap.entity.Grupos;
import br.com.fiap.repository.RepositoryDao;

@ManagedBean
@RequestScoped
public class GruposBean {

	@ManagedProperty(value = "#{beanGrupo}")
	private Grupos grupo;

	public Grupos getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupos grupo) {
		this.grupo = grupo;
	}


	public void incluirGrupo() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			GruposDao dao = RepositoryDao.getGruposDao();
			dao.salvar(grupo);
			msg.setSummary("OK");
			msg.setDetail("Grupo " + grupo.getNome() + " incluído");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);

		} catch (Exception e) {

			msg.setSummary("ERRO:");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
		}
		context.addMessage(null, msg);
	}

	public List<Grupos> getListaGrupos() throws Exception {
		return RepositoryDao.getGruposDao().listar();
	}

}
