package model;

public class VirtudeSatelite
{
	int codVirtudeSatelite;
	int codVirtudeCardeal;
	String nome;
	
	public int getCodVirtudeSatelite() {
		return codVirtudeSatelite;
	}
	public void setCodVirtudeSatelite(int codVirtudeSatelite) {
		this.codVirtudeSatelite = codVirtudeSatelite;
	}
	public int getCodVirtudeCardeal() {
		return codVirtudeCardeal;
	}
	public void setCodVirtudeCardeal(int codVirtudeCardeal) {
		this.codVirtudeCardeal = codVirtudeCardeal;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return nome;
	}
	
	@Override
	public int hashCode() {
		return codVirtudeSatelite;
	}
	
}
