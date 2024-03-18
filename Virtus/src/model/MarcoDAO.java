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

public class MarcoDAO
{
	Marco marco = new Marco();
	
	PreparedStatement pS;
	
	ResultSet rS;
	
	Connection conexao = new ConnectionFactory().getConection();
	
	public int CadastrarMarco(Marco marco)
	{
		int resultado = 0;
		
		try{
			
			pS = conexao.prepareStatement("INSERT INTO marco (cod_virtude_cardeal, cod_virtude_satelite, nome, data_marco, descricao) VALUES (?, ?, ?, ?, ?)");
			
			pS.setInt(1, marco.getCodVirtudeCardeal());
			pS.setInt(2, marco.getCodVirtudeSatelite());
			pS.setString(3, marco.getNome());
			pS.setDate(4, Date.valueOf(marco.getData()));
			pS.setString(5, marco.getDescricao());
			resultado = pS.executeUpdate();
			
		}
		catch(Exception ex) 
		{
			throw new RuntimeException("Erro ao cadastrar Marco: " + ex);
		}
		
		if(resultado == 1){
			
			JOptionPane.showMessageDialog(null, "Marco cadastrado com sucesso");
		}
		
		return resultado;
	}
	
	public Marco Buscar(int cod)
	{
		Marco marco = new Marco();

		try {
			
			pS = conexao.prepareStatement("SELECT * FROM marco WHERE cod_marco = ?");
			pS.setInt(1, cod);
			rS = pS.executeQuery();
			
			if (rS.next()) {
				
				//nome da coluna
				marco.setCodigo(rS.getInt(1));
				marco.setCodVirtudeCardeal(rS.getInt(2));
				marco.setCodVirtudeSatelite(rS.getInt(3));
				marco.setNome(rS.getString(4));
				marco.setData(LocalDate.parse(rS.getDate(5).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				marco.setDescricao(rS.getString(6));
				
				}
				else {
					
					JOptionPane.showMessageDialog(null,
					"Marco com código fornecido não encontrado");
				
				}
				
			pS.close();
			rS.close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erro na busca: " + e);
		}
		return marco;
	}
	
	public int ExcluirMarco (int cod) {
		
		int resultado = 0;
		
		String sql = "DELETE FROM marco WHERE cod_marco = ?";
		
		try {
			
			pS = conexao.prepareStatement(sql);
			pS.setInt(1, cod);
			
			resultado = pS.executeUpdate();
			
			pS.close();
			
			if(resultado == 1){
				
				JOptionPane.showMessageDialog(null, "Marco excluído com sucesso");
			}
			return resultado;
		}
		
		catch(Exception e) {
			
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
			e.printStackTrace();
		}
	
		return resultado;
	}
	
	
	public int AlterarMarco(Marco marco)
	{
		int resultado = 0;
		
try {
			
			pS = conexao.prepareStatement("UPDATE marco SET cod_virtude_cardeal = ?, cod_virtude_satelite = ?,"
					+ "nome = ?, data_marco = ?, descricao = ? WHERE cod_marco = ?");
			
			pS.setInt(1, marco.getCodVirtudeCardeal());
			pS.setInt(2, marco.getCodVirtudeSatelite());
			pS.setString(3, marco.getNome());
			//Data
			pS.setDate(4, Date.valueOf(marco.getData()));
			pS.setString(5, marco.getDescricao());
			pS.setInt(6, marco.getCodigo());
				
			resultado = pS.executeUpdate();
			
			pS.close();
			
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erro ao salvar as alterações: " + e);
		}
		if(resultado == 1)	
		{
			JOptionPane.showMessageDialog(null, "Alterações no Marco salvas com sucesso");
		}
		
		return resultado;
	}
	
	public List<Marco> ListarTodosOsMarcos() {

		List<Marco> marcos = new java.util.ArrayList<Marco>();

		try {
			
			pS = conexao.prepareStatement("SELECT * FROM marco");
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				marco = new Marco();
				
				marco.setCodigo(rS.getInt(1));
				marco.setCodVirtudeCardeal(rS.getInt(2));
				marco.setCodVirtudeSatelite(rS.getInt(3));
				marco.setNome(rS.getString(4));
				marco.setData(LocalDate.parse(rS.getDate(5).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				marco.setDescricao(rS.getString(6));
				
				marcos.add(marco);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return marcos;

	}
	
	public List<Marco> ListarTodosOsMarcosInner() {

		List<Marco> marcos = new java.util.ArrayList<Marco>();

		try {
			
			pS = conexao.prepareStatement("SELECT marco.cod_marco, virtude_cardeal.nome, virtude_satelite.nome, marco.nome, marco.data_marco, marco.descricao"
					+ " FROM ((marco INNER JOIN virtude_cardeal ON marco.cod_virtude_cardeal = virtude_cardeal.cod_virtude_cardeal)"
					+ " INNER JOIN virtude_satelite ON marco.cod_virtude_satelite = virtude_satelite.cod_virtude_satelite)");
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				marco = new Marco();
				
				marco.setCodigo(rS.getInt(1));
				//marco.setCodVirtudeCardeal(rS.getInt(2));
				//marco.setCodVirtudeSatelite(rS.getInt(3));
				marco.setVirtudeCardeal(rS.getString(2));
				marco.setVirtudeSatelite(rS.getString(3));
				marco.setNome(rS.getString(4));
				marco.setData(LocalDate.parse(rS.getDate(5).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				marco.setDescricao(rS.getString(6));
				
				marcos.add(marco);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return marcos;

	}
	
	public List<Marco> ListarMarcosPorVirtude(int virt) {

		List<Marco> marcos = new java.util.ArrayList<Marco>();

		try {
			
			pS = conexao.prepareStatement("SELECT marco.cod_marco, virtude_cardeal.nome, virtude_satelite.nome, marco.nome, marco.data_marco, marco.descricao"
					+ " FROM ((marco INNER JOIN virtude_cardeal ON marco.cod_virtude_cardeal = virtude_cardeal.cod_virtude_cardeal)"
					+ " INNER JOIN virtude_satelite ON marco.cod_virtude_satelite = virtude_satelite.cod_virtude_satelite) WHERE marco.cod_virtude_cardeal = ?");
			pS.setInt(1, virt);
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				marco = new Marco();
				
				marco.setCodigo(rS.getInt(1));
				//marco.setCodVirtudeCardeal(rS.getInt(2));
				//marco.setCodVirtudeSatelite(rS.getInt(3));
				marco.setVirtudeCardeal(rS.getString(2));
				marco.setVirtudeSatelite(rS.getString(3));
				marco.setNome(rS.getString(4));
				marco.setData(LocalDate.parse(rS.getDate(5).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				marco.setDescricao(rS.getString(6));
				
				marcos.add(marco);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return marcos;

	}
	
	public List<Marco> ListarTodosOsMarcosOrdemCrescente() {

		List<Marco> marcos = new java.util.ArrayList<Marco>();

		try {
			
			pS = conexao.prepareStatement("SELECT marco.cod_marco, virtude_cardeal.nome, virtude_satelite.nome, marco.nome, marco.data_marco, marco.descricao"
					+ " FROM ((marco INNER JOIN virtude_cardeal ON marco.cod_virtude_cardeal = virtude_cardeal.cod_virtude_cardeal)"
					+ " INNER JOIN virtude_satelite ON marco.cod_virtude_satelite = virtude_satelite.cod_virtude_satelite) ORDER BY marco.data_marco");
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				marco = new Marco();
				
				marco.setCodigo(rS.getInt(1));
				//marco.setCodVirtudeCardeal(rS.getInt(2));
				//marco.setCodVirtudeSatelite(rS.getInt(3));
				marco.setVirtudeCardeal(rS.getString(2));
				marco.setVirtudeSatelite(rS.getString(3));
				marco.setNome(rS.getString(4));
				marco.setData(LocalDate.parse(rS.getDate(5).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				marco.setDescricao(rS.getString(6));
				
				marcos.add(marco);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return marcos;

	}
	
	public List<Marco> ListarMarcosPorNomeEDescricao(String texto) {

		List<Marco> marcos = new java.util.ArrayList<Marco>();

		try {
		
			pS = conexao.prepareStatement("SELECT * FROM marco WHERE marco.nome LIKE ? OR marco.descricao LIKE ?");
			pS.setString(1, texto);
			pS.setString(2, texto);
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				marco = new Marco();
				
				marco.setCodigo(rS.getInt(1));
				marco.setCodVirtudeCardeal(rS.getInt(2));
				marco.setCodVirtudeSatelite(rS.getInt(3));
				marco.setNome(rS.getString(4));
				marco.setData(LocalDate.parse(rS.getDate(5).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				marco.setDescricao(rS.getString(6));
				
				marcos.add(marco);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return marcos;

	}
	
	public List<Marco> ListarMarcosPorVirtudeNomeEDescricao(int virt, String texto) {

		List<Marco> marcos = new java.util.ArrayList<Marco>();

		try {
		
			pS = conexao.prepareStatement("SELECT marco.cod_marco, virtude_cardeal.nome, virtude_satelite.nome, marco.nome, marco.data_marco, marco.descricao"
					+ " FROM ((marco INNER JOIN virtude_cardeal ON marco.cod_virtude_cardeal = virtude_cardeal.cod_virtude_cardeal)"
					+ " INNER JOIN virtude_satelite ON marco.cod_virtude_satelite = virtude_satelite.cod_virtude_satelite) WHERE marco.cod_virtude_cardeal = ?"
					+ " AND marco.nome LIKE %?% OR marco.cod_virtude_cardeal = ? AND  marco.descricao %LIKE% ?");
			pS.setInt(1, virt);
			pS.setString(2, texto);
			pS.setString(3, texto);
			rS = pS.executeQuery();

			
			while (rS.next()) {
				
				marco = new Marco();
				
				marco.setCodigo(rS.getInt(1));
				marco.setCodVirtudeCardeal(rS.getInt(2));
				marco.setCodVirtudeSatelite(rS.getInt(3));
				marco.setNome(rS.getString(4));
				marco.setData(LocalDate.parse(rS.getDate(5).toString().replace("-", "/"), DateTimeFormatter.ofPattern("yyyy/MM/dd")));
				marco.setDescricao(rS.getString(6));
				
				marcos.add(marco);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}

		return marcos;

	}
}
