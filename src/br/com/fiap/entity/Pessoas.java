package br.com.fiap.entity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pessoas", schema="amigosecreto")
@ManagedBean(name="pessBean")
@RequestScoped
public class Pessoas {
	
	@Id
	@GeneratedValue
	@Column(name="ID")
	private String id;
	
	@Column(name="CPF")
	private String cpf;
	
	@Column(name="COD_GRUPO")
	private String cod_grupo;
	
	@Column(name="CPF_SORTEADO")
	private String cpf_sorteado;
	
	@Column(name="PARTICIPANDO")
	private int participando;
	
	public Pessoas() {}

	public Pessoas(String cpf, String cod_grupo, String cpf_sorteado, int participando){
		setCpf(cpf);
		setCod_grupo(cod_grupo);
		setCpf_sorteado(cpf_sorteado);
		setParticipando(participando);
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

	public String getCod_grupo() {
		return cod_grupo;
	}

	public void setCod_grupo(String cod_grupo) {
		this.cod_grupo = cod_grupo;
	}

	public String getCpf_sorteado() {
		return cpf_sorteado;
	}

	public void setCpf_sorteado(String cpf_sorteado) {
		this.cpf_sorteado = cpf_sorteado;
	}

	public int getParticipando() {
		return participando;
	}

	public void setParticipando(int participando) {
		this.participando = participando;
	}
	
}
