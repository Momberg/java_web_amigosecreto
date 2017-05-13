package br.com.fiap.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import br.com.fiap.entity.Pessoa;

@ManagedBean
@RequestScoped
public class PessoaBean {
	
	@ManagedProperty(value = "#{pessBean}")
	private Pessoa pessoa;

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public void cadastrar() {
		
		System.out.println(pessoa.getNome());
	}
	
}
