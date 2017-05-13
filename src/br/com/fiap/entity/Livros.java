package br.com.fiap.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.fiap.dao.LivrosDao;

@Entity
@Table(name="livros", schema="servletjsp")
@ManagedBean(name="beanLivro")
@RequestScoped
public class Livros {

	@Id
	@Column(name="CODIGO")
	private Integer codigo;
	
	@Column(name="TITULO")
	private String titulo;
	
	@Column(name="AUTOR")
	private String autor;
	
	@Column(name="DATAPUB")
	private Date dataPublicacao;
	
	@Column(name="PRECO")
	private double preco;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="livro")
	private Set<Compradores> compradores = new LinkedHashSet<Compradores>();
	
	public Livros() {}
	
	public Livros(Integer codigo,String titulo,String autor,Date data,double preco){
		setCodigo(codigo);
		setTitulo(titulo);
		setAutor(autor);
		setDataPublicacao(data);
		setPreco(preco);
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public Date getDataPublicacao() {
		return dataPublicacao;
	}
	public void setDataPublicacao(Date dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}
	
	public Set<Compradores> getCompradores() {
		return compradores;
	}

	public void setCompradores(Set<Compradores> compradores) {
		this.compradores = compradores;
	}

	//Método para receber uma data no formato String
	public void setDataReal(String data) throws ParseException{
		//Ler a data passada como parâmetro, em um formato conhecido
		//A partir deste dado, convertê-la para um objeto Date
		//Atribuir este objeto para o método setDataPublicacao(Date)
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date dat = df.parse(data);
		setDataPublicacao(dat);
	}
	
	
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	//propriedades complementares
	public String getCadastro(){
		return new LivrosDao().salvar(this);
	}	
	
	public List<Livros> getListaLivros(){
		return new LivrosDao().listar();
	}
}

