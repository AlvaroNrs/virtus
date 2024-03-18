package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Diario;
import model.DiarioDAO;
import model.VirtudeCardealDAO;
import tabela.DiarioTable;
import view.Nota;

public class DiarioControl {
	
	
	public static List<Diario> listaNotas;

	static DefaultTableModel model = new DefaultTableModel();
	
	private static TableModel tabelaVazia = null;
	
	
	public static void LimparCampos() 
	{
		view.Nota.txtDescricao.setText("");
		view.Nota.virtudeCardeal = 1;
		view.Nota.cmbVirtudeSatelite.setSelectedIndex(0);
		view.Nota.virtudeSatelite = 1;
		view.Nota.retornoErro = "";
		view.Nota.dataAjustada = null;
	}
	
	public static void CadastrarNota(int codVirtudeCardeal, int codVirtudeSatelite, LocalDate ultimaModificacao, String descricao) {
		
		DiarioDAO diarioDAO = new DiarioDAO();
		Diario diario = new Diario();
		diario.setCodVirtCardeal(codVirtudeCardeal);
		diario.setCodVirtudeSatelite(codVirtudeSatelite);
		diario.setUltimaModificacao(ultimaModificacao);
		diario.setDescricao(descricao);
		int diarioResult = diarioDAO.CadastrarNota(diario);
		LimparCampos();
	}
	
	public static void ExcluirNota(int cod)
	{
		DiarioDAO diarioDAO = new DiarioDAO();
		
		try {
			
			diarioDAO.ExcluirNota(cod);
			
			}
			catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Erro ao tentar excluir Nota: " + e);
				
			}
	}
		
	public static void BuscarNota(int cod)
	{
		DiarioDAO diarioDAO = new DiarioDAO();
		Diario diario = diarioDAO.Buscar(cod);
		
		view.Nota.codDiario = diario.getCodigo();
			 
		view.Nota.virtudeCardeal = diario.getCodVirtCardeal();
		
		//ComboBox de Virtude Satelite
		view.Nota.cmbVirtudeSatelite.setSelectedIndex(diario.getCodVirtudeSatelite()-1);
		model.VirtudeSatelite virtudeS = (model.VirtudeSatelite)Nota.cmbVirtudeSatelite.getSelectedItem();
		
		view.Nota.virtudeSatelite = virtudeS.getCodVirtudeSatelite();
		view.Nota.virtudeCardeal = virtudeS.getCodVirtudeCardeal();
		
		view.Nota.txtDescricao.setText(diario.getDescricao());
		
		view.Nota.txtUltimaAtualizacao.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy")
				.format(diario.getUltimaModificacao()).toString().replace("-", "/"));
		view.Nota.txtUltimaAtualizacao.setVisible(true);
		
		view.Nota.lblltimaAtualizao.setVisible(true);
		
	}
	
	public static void AlterarNota(int cod, int codVirtudeCardeal, int codVirtudeSatelite, LocalDate ultimaModificao, String descricao)
	{
		DiarioDAO diarioDAO = new DiarioDAO();
		Diario diario = new Diario();
		diario.setCodigo(cod);
		diario.setCodVirtCardeal(codVirtudeCardeal);
		diario.setCodVirtudeSatelite(codVirtudeSatelite);
		diario.setUltimaModificacao(ultimaModificao);
		diario.setDescricao(descricao);
		
		int diarioResult = diarioDAO.AlterarNota(diario);
		LimparCampos();
	}
	
	public static void ListarNotas(int codVirtudeCardeal, String desc)
	{
			if(codVirtudeCardeal > 0)
			{
				if(desc.length() <= 0)
				{
					listaNotas = new DiarioDAO().ListarTodasAsNotasPorVirtude(codVirtudeCardeal);
				}
				else
				{
					listaNotas = new DiarioDAO().ListarTodasAsNotasPorVirtudePorDescricao(codVirtudeCardeal, desc);
				}
			}
			else
			{
				if(codVirtudeCardeal == 0)
				{
					//Ordem de última modificacao
					listaNotas = new DiarioDAO().ListarTodasAsNotasOrdemCrescente();
				}
				else
				{
					//BUSCA GERAL
					listaNotas = new DiarioDAO().ListarTodasAsNotasInner();
				}
			}
				
				
		if (listaNotas.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Sem registros de acordo com a busca");
			//Deixando a tabela vazia, caso nenhum registro seja retornado
			view.MenuPrincipal.tblSubMenu.setModel(model);
			
		}	
		else
		{
			view.MenuPrincipal.tblSubMenu.setModel(new DiarioTable(listaNotas));
			
			// DEFINE A LARGURA DA COLUNA NA TABELA.
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(0)
			.setMinWidth(0);
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(0)
			.setMaxWidth(0);
			
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(1)
			.setMinWidth(166);
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(1)
			.setMaxWidth(166);
			
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(2)
			.setMinWidth(166);
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(2)
			.setMaxWidth(166);
			
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(3)
			.setMinWidth(240);
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(3)
			.setMaxWidth(240);
			
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(4)
			.setMinWidth(480);
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(4)
			.setMaxWidth(480);
			
			
		}
	}
	
	
}
