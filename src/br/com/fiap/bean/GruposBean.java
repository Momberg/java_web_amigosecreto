package br.com.fiap.bean;

import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.fiap.dao.GruposDao;
import br.com.fiap.dao.PessoasDao;
import br.com.fiap.dao.UsuariosDao;
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
	private List<Pessoas> pessoas;
	private Usuarios sorteado;
	
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
			msg.setDetail("Grupo " + grupo.getNome() + " incluído" + ", " +"favor guardar código do grupo " + grupo.getCod_grupo());
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
		usuario = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		pessoa = new Pessoas();
		
		grupo = buscarGrupo();
		
		if(grupo == null) {
			FacesMessage msg = new FacesMessage();
			msg.setDetail("Esse grupo nao existe");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, msg);
		}
	}
	
	public void participarGrupo() {
		System.out.println("participarGrupo");
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		
		if(buscarGrupo() != null) {
			PessoasDao dao = RepositoryDao.getPessoasDao();
			usuario = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
			pessoas = dao.listar(usuario);
			boolean temGrupo = false;
				for (Pessoas pessoafor : pessoas) {
					if(pessoafor.getCod_grupo().equals(cod_pesquisa)){
						temGrupo = true;
					}
				}
			
			if(temGrupo) {
				msg.setDetail("Usuario já foi incluido ao grupo");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			}else {
				pessoa = new Pessoas();
				pessoa.setCod_grupo(cod_pesquisa);
				pessoa.setCpf(usuario.getCpf());
				PessoasDao daoP = RepositoryDao.getPessoasDao();
				daoP.salvar(pessoa);
				
				msg.setSummary("");
				msg.setDetail("Usuario " + usuario.getNome()+" " + "incluido com sucesso ao grupo"  );
				msg.setSeverity(FacesMessage.SEVERITY_INFO);
			}
			
		}else {
			msg.setDetail("Grupo não existe");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		context.addMessage(null, msg);
	}

	public void sortearGrupo() {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		usuario = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		if(buscarGrupo() != null) {
			List<Pessoas> listaPessoas = buscarPessoaPorGrupo();
			List<Pessoas> listaSorteados = buscarPessoaPorGrupo();
			Grupos grupo = buscarGrupo(listaPessoas.get(0).getCod_grupo(), usuario.getCpf());
			if(grupo != null){
				Random random = new Random();
				int r;
				boolean sort = false;
				boolean msg_sort = false;
				for (Pessoas pessoas : listaPessoas) {
					if(pessoas.getCpf_sorteado() == null){
						while(!sort){
							r = random.nextInt(listaPessoas.size());
							if(listaPessoas.get(r).getCpf() != pessoas.getCpf()){
								if(verificaListaSorteado(pessoas, listaSorteados, listaPessoas, r)){
									pessoas.setCpf_sorteado(listaPessoas.get(r).getCpf());
									atualizarSorteado(pessoas);
									listaSorteados = buscarPessoaPorGrupo();
									sort = true;
									msg_sort = true;
								}
							}
						}
						sort = false;
					}
				}
				if(msg_sort){
					msg.setDetail("Grupo sorteado");
					msg.setSeverity(FacesMessage.SEVERITY_INFO);
				} else {
					msg.setDetail("Grupo ja foi sorteado");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				}
			} else {
				msg.setDetail("Você não é administrador do grupo para realizar o sorteio");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			}
		} else {
			msg.setDetail("Grupo não existe");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		}
		context.addMessage(null, msg);
	}
	
	public void pesquisarSorteado(){
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage msg = new FacesMessage();
		List<Pessoas> listaPessoas = buscarPessoaPorGrupo();
		usuario = (Usuarios)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		for (Pessoas pessoas : listaPessoas) {
			if(pessoas.getCpf().equals(usuario.getCpf())){
				sorteado = buscaSorteado(pessoas);
				if(pessoas.getCpf_sorteado() == null) {
					msg.setDetail("Seu grupo ainda não realizou o sorteio");
					msg.setSeverity(FacesMessage.SEVERITY_ERROR);
					context.addMessage(null, msg);
				}
			}
		}
	}
	
	public String getCod_pesquisa() {
		return cod_pesquisa;
	}

	public void setCod_pesquisa(String cod_pesquisa) {
		this.cod_pesquisa = cod_pesquisa;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public Pessoas getPessoa() {
		return pessoa;
	}

	public Usuarios getSorteado() {
		return sorteado;
	}

	public void setSorteado(Usuarios sorteado) {
		this.sorteado = sorteado;
	}

	public void setPessoa(Pessoas pessoa) {
		this.pessoa = pessoa;
		
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
	
	private Grupos buscarGrupo () {
		
		GruposDao dao = RepositoryDao.getGruposDao();
		grupo = dao.pesquisarGrupo(cod_pesquisa);
		
		return grupo;
	}
	
	private Grupos buscarGrupo(String cod_grupo, String cpf){
		GruposDao dao = RepositoryDao.getGruposDao();
		grupo = dao.pesquisarGrupoAdmin(cod_grupo, cpf);
		
		return grupo;
	}
	
	private List<Pessoas> buscarPessoaPorGrupo() {
		
		PessoasDao pessoaDao = RepositoryDao.getPessoasDao();
		return pessoaDao.listarPessoaPorGrupo(cod_pesquisa);
	}
	
	private void atualizarSorteado(Pessoas pessoa){
		PessoasDao pessoaDao = RepositoryDao.getPessoasDao();
		pessoaDao.update(pessoa);
	}
	
	private boolean verificaListaSorteado(Pessoas pessoas, List<Pessoas> listaSorteados, List<Pessoas> listaPessoas, int r){
		int i = 0;
		String sorteado;
		while(i < listaSorteados.size() - 1){
			if(listaSorteados.get(i).getCpf_sorteado() == null){
				sorteado = "";
			} else {
				sorteado = listaSorteados.get(i).getCpf_sorteado();
			}
			if(sorteado.equals(listaPessoas.get(r).getCpf())){
				return false;
			}
			i++;
		}
		return true;
	}

	private Usuarios buscaSorteado(Pessoas pessoa){
		UsuariosDao usuarioDao = RepositoryDao.getUsuariosDao();
		sorteado = usuarioDao.getUsuarioSorteado(pessoa);
		return sorteado;
	}
	
}
