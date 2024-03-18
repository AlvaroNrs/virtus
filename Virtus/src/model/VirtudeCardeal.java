package model;

public class VirtudeCardeal
{
	int codVirtudeCardeal;
	String nome;
	
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
		return codVirtudeCardeal;
	}
	
}
