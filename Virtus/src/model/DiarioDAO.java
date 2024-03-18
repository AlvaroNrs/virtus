package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class DiarioDAO 
{
	Diario diario = new Diario();
	
	PreparedStatement pS;
	
	ResultSet rS;
	
	Connection conexao = new ConnectionFactory().getConection();
	
	public int CadastrarNota(Diario diario)
	{
		int resultado = 0;
		
		try{
			
			pS = conexao.prepareStatement("INSERT INTO diario (cod_virtude_cardeal, cod_virtude_satelite, ultima_modificacao, descricao) VALUES (?, ?, ?, ?)");
			
			pS.setInt(1, diario.getCodVirtCardeal());
			pS.setInt(2, diario.getCodVirtudeSatelite());
			pS.setDate(3, Date.valueOf(diario.getUltimaModificacao()));
			pS.setString(4, diario.getDescricao());
			resultado = pS.executeUpdate();
			
		}
		catch(Exception ex) 
		{
			throw new RuntimeException("Erro ao cadastrar Nota: " + ex);
		}
		
		if(resultado == 1)
		{
			JOptionPane.showMessageDialog(null, "Nota cadastrada com sucesso");
		}
		
		return resultado;
	}
	
	public Diario Buscar(int cod)
	{
		Diario diario = new Diario();

		try {
			
			pS = conexao.prepareStatement("SELECT * FROM diario WHERE cod_diario = ?");
			pS.setInt(1, cod);
			rS = pS.executeQuery();
			
			if (rS.next()) {
				
				//nome da coluna
				diario.setCodigo(rS.getInt(1));
				diario.setCodVirtCardeal(rS.getInt(2));
				diario.setCodVirtudeSatelite(rS.getInt(3));
				
				diario.setUltimaModificacao(LocalDate.parse(rS.getDate(4).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				
				diario.setDescricao(rS.getString(5));
				
				//Adap. das Datas
				/*animais.setDataChegada(LocalDate.parse(rs.getDate(12).toString().replace("-", "/"),
				DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				
				animais.setDataNasc(LocalDate.parse(rs.getDate(13).toString().replace("-", "/"),
				DateTimeFormatter.ofPattern("yyyy/MM/dd")));;*/
				
				}
				else {
					
					JOptionPane.showMessageDialog(null,
					"Nota com código fornecido não encontrada");
				
				}
				
			pS.close();
			rS.close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erro na busca: " + e);
		}
		return diario;
	}
	
	public int ExcluirNota (int cod) {
		
		int resultado = 0;
		
		String sql = "DELETE FROM diario WHERE cod_diario = ?";
		
		try {
			
			pS = conexao.prepareStatement(sql);
			pS.setInt(1, cod);
			
			resultado = pS.executeUpdate();
			
			pS.close();
			if(resultado == 1){
				
				JOptionPane.showMessageDialog(null, "Nota excluída com sucesso");
			}
			
			return resultado;
		}
		
		catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
			e.printStackTrace();
		}
	
		return resultado;
	}
	
	public int AlterarNota(Diario nota)
	{
		int resultado = 0;
		
		try {
			
			pS = conexao.prepareStatement("UPDATE diario SET cod_virtude_cardeal = ?, cod_virtude_satelite = ?,"
					+ " ultima_modificacao = ?, descricao = ? WHERE cod_diario = ?");
			
			pS.setInt(1, nota.getCodVirtCardeal());
			pS.setInt(2, nota.getCodVirtudeSatelite());
			//Data
			pS.setDate(3, Date.valueOf(nota.getUltimaModificacao()));
			pS.setString(4, nota.getDescricao());
			pS.setInt(5, nota.getCodigo());
				
			resultado = pS.executeUpdate();
			
			pS.close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erro ao salvar as alterações: " + e);
		}
		if(resultado == 1)
		{
			JOptionPane.showMessageDialog(null, "Alterações na Nota salvas com sucesso");
		}
		return resultado;
	}
	
	public List<Diario> ListarTodasAsNotas() {

		List<Diario> notas = new java.util.ArrayList<Diario>();

		try {
			
			pS = conexao.prepareStatement("SELECT * FROM diario");
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				diario = new Diario();
				
				diario.setCodigo(rS.getInt(1));
				diario.setCodVirtCardeal(rS.getInt(2));
				diario.setCodVirtudeSatelite(rS.getInt(3));
				diario.setUltimaModificacao(LocalDate.parse(rS.getDate(4).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				diario.setDescricao(rS.getString(5));
				
				notas.add(diario);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return notas;

	}
	
	public List<Diario> ListarTodasAsNotasOrdemCrescente() {

		List<Diario> notas = new java.util.ArrayList<Diario>();

		try {
			
			pS = conexao.prepareStatement("SELECT diario.cod_diario, virtude_cardeal.nome, virtude_satelite.nome, diario.ultima_modificacao, diario.descricao"
					+ " FROM ((diario INNER JOIN virtude_cardeal ON diario.cod_virtude_cardeal = virtude_cardeal.cod_virtude_cardeal)"
					+ "INNER JOIN virtude_satelite ON diario.cod_virtude_satelite = virtude_satelite.cod_virtude_satelite) ORDER BY diario.ultima_modificacao");
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				diario = new Diario();
				
				diario.setCodigo(rS.getInt(1));
				//diario.setCodVirtCardeal(rS.getInt(2));
				//diario.setCodVirtudeSatelite(rS.getInt(3));
				diario.setVirtudeCardeal(rS.getString(2));
				diario.setVirtudeSatelite(rS.getString(3));
				diario.setUltimaModificacao(LocalDate.parse(rS.getDate(4).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				diario.setDescricao(rS.getString(5));
				
				notas.add(diario);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return notas;

	}
	
	public List<Diario> ListarTodasAsNotasInner() {

		List<Diario> notas = new java.util.ArrayList<Diario>();

		try {
			
			pS = conexao.prepareStatement("SELECT diario.cod_diario, virtude_cardeal.nome, virtude_satelite.nome, diario.ultima_modificacao, diario.descricao"
					+ " FROM ((diario INNER JOIN virtude_cardeal ON diario.cod_virtude_cardeal = virtude_cardeal.cod_virtude_cardeal)"
					+ "INNER JOIN virtude_satelite ON diario.cod_virtude_satelite = virtude_satelite.cod_virtude_satelite)");
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				diario = new Diario();
				
				diario.setCodigo(rS.getInt(1));
				//diario.setCodVirtCardeal(rS.getInt(2));
				//diario.setCodVirtudeSatelite(rS.getInt(3));
				diario.setVirtudeCardeal(rS.getString(2));
				diario.setVirtudeSatelite(rS.getString(3));
				diario.setUltimaModificacao(LocalDate.parse(rS.getDate(4).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				diario.setDescricao(rS.getString(5));
				
				notas.add(diario);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return notas;

	}
	
	public List<Diario> ListarTodasAsNotasPorVirtude(int virt) {

		List<Diario> notas = new java.util.ArrayList<Diario>();

		try {
			
			pS = conexao.prepareStatement("SELECT diario.cod_diario, virtude_cardeal.nome, virtude_satelite.nome, diario.ultima_modificacao, diario.descricao"
					+ " FROM ((diario INNER JOIN virtude_cardeal ON diario.cod_virtude_cardeal = virtude_cardeal.cod_virtude_cardeal)"
					+ "INNER JOIN virtude_satelite ON diario.cod_virtude_satelite = virtude_satelite.cod_virtude_satelite) WHERE diario.cod_virtude_cardeal = ?");
			pS.setInt(1, virt);
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				diario = new Diario();
				
				diario.setCodigo(rS.getInt(1));
				//diario.setCodVirtCardeal(rS.getInt(2));
				//diario.setCodVirtudeSatelite(rS.getInt(3));
				diario.setVirtudeCardeal(rS.getString(2));
				diario.setVirtudeSatelite(rS.getString(3));
				diario.setUltimaModificacao(LocalDate.parse(rS.getDate(4).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				diario.setDescricao(rS.getString(5));
				
				notas.add(diario);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return notas;

	}
	
	public List<Diario> ListarTodasAsNotasPorVirtudePorDescricao(int virt, String descricao) {

		List<Diario> notas = new java.util.ArrayList<Diario>();

		try {
			
			pS = conexao.prepareStatement("SELECT diario.cod_diario, virtude_cardeal.nome, virtude_satelite.nome, diario.ultima_modificacao, diario.descricao"
					+ " FROM ((diario INNER JOIN virtude_cardeal ON diario.cod_virtude_cardeal = virtude_cardeal.cod_virtude_cardeal)"
					+ "INNER JOIN virtude_satelite ON diario.cod_virtude_satelite = virtude_satelite.cod_virtude_satelite) WHERE diario.cod_virtude_cardeal = ?"
					+ " AND diario.descricao LIKE ?");
			pS.setInt(1, virt);
			pS.setString(2, descricao);
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				diario = new Diario();
				
				diario.setCodigo(rS.getInt(1));
				//diario.setCodVirtCardeal(rS.getInt(2));
				//diario.setCodVirtudeSatelite(rS.getInt(3));
				diario.setVirtudeCardeal(rS.getString(2));
				diario.setVirtudeSatelite(rS.getString(3));
				diario.setUltimaModificacao(LocalDate.parse(rS.getDate(4).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				diario.setDescricao(rS.getString(5));
				
				notas.add(diario);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return notas;

	}
	
}
