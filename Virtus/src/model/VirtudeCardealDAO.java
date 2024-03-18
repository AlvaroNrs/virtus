package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JOptionPane;

import util.ConnectionFactory;

public class VirtudeCardealDAO
{	
	PreparedStatement pS;
	
	ResultSet rS;
	
	Connection conexao = new ConnectionFactory().getConection();
	
	public List<VirtudeCardeal> buscarVirtudesCardeais()
	{
		List<VirtudeCardeal> virtudesC = new java.util.ArrayList<VirtudeCardeal>();
		
		try {
			
				pS = conexao.prepareStatement("SELECT * FROM virtude_cardeal");
					
				rS = pS.executeQuery();

			
				while (rS.next()) {
				
				VirtudeCardeal virtudeC = new VirtudeCardeal();
				
				//nome da coluna
				virtudeC.setCodVirtudeCardeal(rS.getInt("cod_virtude_cardeal"));
				virtudeC.setNome(rS.getString("nome"));				
				
				virtudesC.add(virtudeC);		
			
			}
			pS.close();
			rS.close();
			// conexao.close();
		} catch (SQLException e) {
			
			JOptionPane.showMessageDialog(null, "Erro: " + e);
			e.printStackTrace();
		}
		
		return virtudesC;
		
	}

}
