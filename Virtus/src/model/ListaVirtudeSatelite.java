package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class ListaVirtudeSatelite
{
	static List<VirtudeSatelite> listaVirtudesSatelites = new ArrayList<>();
	
	//Conectar através da Classe ConectionFactory;
	static Connection conexao = new ConnectionFactory().getConection();
			
	static //Função: Levar os dados do formul�rio at� o Banco de Dados;
	PreparedStatement st;
			
	//Faz o caminho inverso do anterior;
	static ResultSet rs;
	
	public static List<VirtudeSatelite> listarVirtudesSatelites(){
		
			String sql = "SELECT cod_virtude_satelite, cod_virtude_cardeal, nome FROM virtude_satelite";	
			
			try {
			

				st = conexao.prepareStatement(sql);
				rs = st.executeQuery();
		
				while(rs.next()) {
			
					VirtudeSatelite virtudeSatelite = new VirtudeSatelite();
					virtudeSatelite.setNome(rs.getString("nome"));
					virtudeSatelite.setCodVirtudeCardeal(rs.getInt("cod_virtude_cardeal"));
					virtudeSatelite.setCodVirtudeSatelite(rs.getInt("cod_virtude_satelite"));
					
					listaVirtudesSatelites.add(virtudeSatelite);
					
				}	
		
				st.close();
			}
			catch(Exception e){
			
				JOptionPane.showMessageDialog(null, "Erro de coluna: " + e);
				
				}
			
			return listaVirtudesSatelites;
			
				
		}
	
	public static VirtudeSatelite BuscaVirtudeSatelite(int cod)
	{
		VirtudeSatelite virtudeSatelite = new VirtudeSatelite();
		
		String sql = "SELECT cod_virtude_satelite, nome FROM virtude_satelite WHERE cod_virtude_satelite = ?";	
		
		try {
		

			st = conexao.prepareStatement(sql);
			st.setInt(1, cod);
			rs = st.executeQuery();
	
			while(rs.next()) {
		
				virtudeSatelite.setNome(rs.getString("nome"));
				virtudeSatelite.setCodVirtudeSatelite(rs.getInt("cod_virtude_satelite"));
				
			}	
	
			st.close();
		}
		catch(Exception e){
		
			JOptionPane.showMessageDialog(null, "Erro de coluna: " + e);
			
			}
		
		return virtudeSatelite;
	}
}
