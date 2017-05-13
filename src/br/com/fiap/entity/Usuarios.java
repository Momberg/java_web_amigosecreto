package br.com.fiap.entity;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import javax.persistence.Id;

import br.com.fiap.dao.UsuariosDao;

@Entity
@Table(name="usuarios", schema="servletjsp")
@ManagedBean(name="beanUsuario")
@RequestScoped
public class Usuarios implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	@Column(name="ID")
	private Integer Id;
	
	@Column(name="USUARIO")
	private String nome;
	
	@Column(name="SENHA")
	private String senha;
	
	@Column(name="NIVEL")
	private int nivel;

	
	public Usuarios() {}
	
	public Usuarios(String nome, String senha, int nivel){
		setNome(nome);
		setSenha(senha);
		setNivel(nivel);
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
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
	
	//propriedades de conveniencia
	public String getCadastro(){
		try {
			return new UsuariosDao().salvar(this);
		} catch (Exception e) {
			return "ERRO: " + e.getMessage();
		}
	}
	
}
