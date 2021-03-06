package br.com.fiap.entity;

import java.text.ParseException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.fiap.dao.GruposDao;

@Entity
@Table(name="grupos", schema="amigosecreto")
@ManagedBean(name="beanGrupo")
@RequestScoped
public class Grupos {

	@Id
	@GeneratedValue
	@Column(name="IDGRUPO")
	private Integer id;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="LOCAL")
	private String local;
	
	@Column(name="DATA")
	private String dataSorteio;
	
	@Column(name="COD_GRUPO")
	private String cod_grupo;
	
	@Column(name="CPF_USUARIO")
	private String cpf_usuario;
	
	@Column(name="SORTEADO")
	private int sorteado;
	
	public Grupos() {}
	
	public Grupos(Integer id, String nome, String local, String data, String cod_grupo, String cpf_usuario, int sortado) throws ParseException{
		setId(id);
		setNome(nome);
		setLocal(local);
		setDataSorteio(data);
		setCod_grupo(cod_grupo);
		setCpf_usuario(cpf_usuario);
		setSorteado(sortado);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(String dataSorteio) {
		this.dataSorteio = dataSorteio;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCod_grupo() {
		return cod_grupo;
	}

	public void setCod_grupo(String cod_grupo) {
		this.cod_grupo = cod_grupo;
	}

	public String getCpf_usuario() {
		return cpf_usuario;
	}

	public void setCpf_usuario(String cpf_usuario) {
		this.cpf_usuario = cpf_usuario;
	}
	
	public int getSorteado() {
		return sorteado;
	}

	public void setSorteado(int sorteado) {
		this.sorteado = sorteado;
	}

	public String getCadastro(){
		return new GruposDao().salvar(this);
	}	
	
	public List<Grupos> getListaLivros(){
		return new GruposDao().listar();
	}
}

