package br.com.fiap.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GruposDao;
import br.com.fiap.dao.PessoasDao;
import br.com.fiap.entity.Grupos;
import br.com.fiap.entity.Pessoas;
import br.com.fiap.entity.Usuarios;
import br.com.fiap.repository.RepositoryDao;

@ManagedBean
@RequestScoped
public class GruposBean {
	
	@ManagedProperty(value = "#{beanGrupo}")
	private Grupos grupo;

	private Usuarios usuario;
	private int cod_grupo;
	private Pessoas pessoa;
	private String cod_pesquisa;
	
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
			cod_grupo = (int) (Math.random() * 99999) + 1000;
			grupo.setCod_grupo(String.valueOf(cod_grupo));
			usuario = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			grupo.setCpf_usuario(usuario.getCpf());
			GruposDao dao = RepositoryDao.getGruposDao();
			dao.salvar(grupo);
			pessoa = new Pessoas();
			pessoa.setCod_grupo(grupo.getCod_grupo());
			pessoa.setCpf(usuario.getCpf());
			PessoasDao daoP = RepositoryDao.getPessoasDao();
			daoP.salvar(pessoa);
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
	
	public void pesquisarGrupo() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		
		GruposDao dao = RepositoryDao.getGruposDao();
		grupo = dao.pesquisarGrupo(cod_pesquisa);
		
		if(grupo == null) {
			FacesMessage msg = new FacesMessage();
			msg.setDetail("Esse grupo nao existe");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
		
	}

	public String getCod_pesquisa() {
		return cod_pesquisa;
	}

	public void setCod_pesquisa(String cod_pesquisa) {
		this.cod_pesquisa = cod_pesquisa;
	}
	
}
