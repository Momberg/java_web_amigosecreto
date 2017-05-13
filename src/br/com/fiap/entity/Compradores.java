package br.com.fiap.entity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="compradores")
@ManagedBean(name="beanComp")
@RequestScoped
public class Compradores {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="CODIGO")
	private int codigo;
	
	@Column(name="NOME")
	private String nome;
	
	@Column(name="EMAIL")
	private String email;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CODLIVRO")
	private Livros livro;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

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

	public Livros getLivro() {
		return livro;
	}

	public void setLivro(Livros livro) {
		this.livro = livro;
	}
}
