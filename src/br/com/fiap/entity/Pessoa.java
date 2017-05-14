package br.com.fiap.entity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="pessBean")
@RequestScoped
public class Pessoa {
	
	private String id;
	private String cpf;
	private String nome;
	private String email;
	private String senha;
	private int nivel;
	private String nomePessoaSorteada;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getNivel() {
		return nivel;
	}

	public void setNivel(int nivel) {
		this.nivel = nivel;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomePessoaSorteada() {
		return nomePessoaSorteada;
	}

	public void setNomePessoaSorteada(String nomePessoaSorteada) {
		this.nomePessoaSorteada = nomePessoaSorteada;
	}

}
