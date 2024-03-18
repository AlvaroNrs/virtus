package model;

import java.time.LocalDate;

public class Diario {

	int codigo;
	int codVirtCardeal;
	int codVirtudeSatelite;
	LocalDate ultimaModificacao;
	String descricao;
	
	String virtudeCardeal;
	String virtudeSatelite;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getCodVirtCardeal() {
		return codVirtCardeal;
	}
	public void setCodVirtCardeal(int codVirtCardeal) {
		this.codVirtCardeal = codVirtCardeal;
	}
	public int getCodVirtudeSatelite() {
		return codVirtudeSatelite;
	}
	public void setCodVirtudeSatelite(int codVirtudeSatelite) {
		this.codVirtudeSatelite = codVirtudeSatelite;
	}
	public LocalDate getUltimaModificacao() {
		return ultimaModificacao;
	}
	public void setUltimaModificacao(LocalDate ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
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
