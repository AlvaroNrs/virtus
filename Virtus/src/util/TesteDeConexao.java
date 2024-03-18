package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class TesteDeConexao {

	public static void main(String[] args) {	
		try {
			Connection conexao = new ConnectionFactory().getConection();
			
			JOptionPane.showMessageDialog(null,
					"Conexão realizada com sucesso!");
			conexao.close();
		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null,
					"Problemas com a fonte de dados!", 
					"Falha", 0);
		}

	}

}
