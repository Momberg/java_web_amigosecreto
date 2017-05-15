package br.com.fiap.bean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GruposDao;
import br.com.fiap.dao.UsuariosDao;
import br.com.fiap.entity.Grupos;
import br.com.fiap.entity.Usuarios;
import br.com.fiap.model.Nivel;
import br.com.fiap.repository.RepositoryDao;

@ManagedBean
@RequestScoped
public class CadastroUsuariosBean {

	@ManagedProperty(value="#{beanUsuario}")
	private Usuarios usuario;

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	//action
	public String incluirUsuario(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		try {
			UsuariosDao dao = RepositoryDao.getUsuariosDao();
			dao.salvar(usuario);
			msg.setSummary("OK");
			msg.setDetail("Usu�rio " + usuario.getNome() + " inclu�do");
			msg.setSeverity(FacesMessage.SEVERITY_INFO);
			
		} catch (Exception e) {
			
			msg.setSummary("ERRO:");
			msg.setDetail(e.getMessage());
			msg.setSeverity(FacesMessage.SEVERITY_INFO);			
		}
		context.addMessage(null, msg);
		
		return "/index";
	}
	
	public List<Nivel> getNiveis(){
		List<Nivel> niveis = new ArrayList<Nivel>();
		niveis.add(new Nivel(1, "Administrador"));
		niveis.add(new Nivel(2, "Cliente"));
		return niveis;
	}
	
}
