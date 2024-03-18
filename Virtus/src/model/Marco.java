package model;

import java.time.LocalDate;

public class Marco
{
	int codigo;
	int codVirtudeCardeal;
	int codVirtudeSatelite;
	String nome;
	LocalDate data;
	String descricao;
	
	String virtudeCardeal;
	String virtudeSatelite;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodVirtudeCardeal() {
		return codVirtudeCardeal;
	}
	public void setCodVirtudeCardeal(int codVirtudeCardeal) {
		this.codVirtudeCardeal = codVirtudeCardeal;
	}
	public int getCodVirtudeSatelite() {
		return codVirtudeSatelite;
	}
	public void setCodVirtudeSatelite(int codVirtudeSatelite) {
		this.codVirtudeSatelite = codVirtudeSatelite;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getVirtudeCardeal() {
		return virtudeCardeal;
	}
	public void setVirtudeCardeal(String virtudeCardeal) {
		this.virtudeCardeal = virtudeCardeal;
	}
	public String getVirtudeSatelite() {
		return virtudeSatelite;
	}
	public void setVirtudeSatelite(String virtudeSatelite) {
		this.virtudeSatelite = virtudeSatelite;
	}
	
	@Override
	public String toString() {
		return virtudeCardeal;
	
	}	
}
