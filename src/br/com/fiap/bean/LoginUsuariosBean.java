package br.com.fiap.bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.UsuariosDao;
import br.com.fiap.entity.Usuarios;
import br.com.fiap.repository.RepositoryDao;

@ManagedBean(name="loginUsuarios")
@SessionScoped
public class LoginUsuariosBean {

	private Usuarios usuario;

	public LoginUsuariosBean(){
		usuario = new Usuarios();
	}
	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	//action
	public String validarUsuario() throws Exception{
		UsuariosDao dao = RepositoryDao.getUsuariosDao();
		if(dao.validar(usuario)){
			return "/admin/menu";
		}
		else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage msg = new FacesMessage();
			msg.setSummary("Erro Login: ");
			msg.setDetail("Usuário ou senha inválidos");
			msg.setSeverity(FacesMessage.SEVERITY_FATAL);
			
			context.addMessage(null, msg);
			return "/index";
		}
	}
}
