package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class ListaVirtudeCardeal
{
	//Conectar através da Classe ConectionFactory;
	static Connection conexao = new ConnectionFactory().getConection();
					
	//Função: Levar os dados do formul�rio at� o Banco de Dados;
	static PreparedStatement st;
					
	//Faz o caminho inverso do anterior;
	static ResultSet rs;
	
	public static List<VirtudeCardeal> listarVirtudesCardeais(){
		List<VirtudeCardeal> listaVirtudesCardeais = new ArrayList<>();
		
			String sql = "SELECT cod_virtude_cardeal, nome FROM virtude_cardeal";	
			
			try {
			
				st = conexao.prepareStatement(sql);
			
				rs = st.executeQuery();
		
				while(rs.next()) {
			
					VirtudeCardeal virtudeCardeal = new VirtudeCardeal();
					virtudeCardeal.setNome(rs.getString("nome"));
					virtudeCardeal.setCodVirtudeCardeal(rs.getInt("cod_virtude_cardeal"));
					
					listaVirtudesCardeais.add(virtudeCardeal);
					
			
				}	
		
				st.close();
			}
			catch(Exception e){
			
				JOptionPane.showMessageDialog(null, "Erro de coluna: " + e);
				
				}
			
			return listaVirtudesCardeais;
			
				
		}
	
	
	public static VirtudeCardeal BuscaVirtudeCardeal(int cod)
	{
		VirtudeCardeal virtudeCardeal = new VirtudeCardeal();
		
		String sql = "SELECT cod_virtude_cardeal, nome FROM virtude_cardeal WHERE cod_virtude_cardeal = ?";	
		
		try {
		

			st = conexao.prepareStatement(sql);
			st.setInt(1, cod);
			rs = st.executeQuery();
	
			while(rs.next()) {
		
				virtudeCardeal.setNome(rs.getString("nome"));
				virtudeCardeal.setCodVirtudeCardeal(rs.getInt("cod_virtude_cardeal"));
				
			}	
	
			st.close();
		}
		catch(Exception e){
		
			JOptionPane.showMessageDialog(null, "Erro de coluna: " + e);
			
			}
		
		return virtudeCardeal;
	}
	
}
