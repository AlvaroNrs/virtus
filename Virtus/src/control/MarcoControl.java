package control;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.Diario;
import model.DiarioDAO;
import model.ListaVirtudeCardeal;
import model.ListaVirtudeSatelite;
import model.Marco;
import model.MarcoDAO;
import model.VirtudeCardeal;
import model.VirtudeSatelite;
import tabela.MarcoTable;
public class MarcoControl
{
	public static List<Marco> listaMarcos;

	static DefaultTableModel model = new DefaultTableModel();
	
	private static TableModel tabelaVazia = null;
	
	public static void LimparCampos() 
	{
		view.Marco.txtNome.setText("");
		view.Marco.txtDescricao.setText("");
		view.Marco.virtudeCardeal = 1;
		view.Marco.cmbVirtudeSatelite.setSelectedIndex(0);
		view.Marco.virtudeSatelite = 1;
		view.Marco.retornoErro = "";
		view.Marco.dataAjustada = null;
	}
	
public static void CadastrarMarco(int codVirtudeCardeal, int codVirtudeSatelite, String nome , LocalDate data, String descricao) {
		
		MarcoDAO marcoDAO = new MarcoDAO();
		Marco marco = new Marco();
		marco.setCodVirtudeCardeal(codVirtudeCardeal);
		marco.setCodVirtudeSatelite(codVirtudeSatelite);
		marco.setNome(nome);
		marco.setData(data);
		marco.setDescricao(descricao);
		int marcoResult = marcoDAO.CadastrarMarco(marco);
		LimparCampos();
	}

	public static void ExcluirMarco(int cod)
	{
		MarcoDAO marcoDAO = new MarcoDAO();
		
		try {
			
			marcoDAO.ExcluirMarco(cod);
			
			}
			catch(Exception e) {
				
				JOptionPane.showMessageDialog(null, "Erro ao tentar excluir Marco: " + e);
				
			}
	}
	
	public static void BuscarMarco(int cod)
	{
		MarcoDAO marcoDAO = new MarcoDAO();
		Marco marco = marcoDAO.Buscar(cod);
		
		view.Marco.codMarco = marco.getCodigo();
		
		//ComboBox de Virtude
		view.Marco.cmbVirtudeSatelite.setSelectedIndex(marco.getCodVirtudeSatelite()-1);
		model.VirtudeSatelite virtudeS = (model.VirtudeSatelite)view.Marco.cmbVirtudeSatelite.getSelectedItem();
		
		view.Marco.virtudeSatelite = virtudeS.getCodVirtudeSatelite();
		view.Marco.virtudeCardeal = virtudeS.getCodVirtudeCardeal();
		
		view.Marco.txtNome.setText(marco.getNome());
		view.Marco.txtDescricao.setText(marco.getDescricao());
		
		view.Marco.lblltimaAtualizao.setVisible(true);
		view.Marco.txtUltimaAtualizacao.setVisible(true);
		
		view.Marco.txtUltimaAtualizacao.setText(DateTimeFormatter.ofPattern("dd/MM/yyyy")
				.format(marco.getData()).toString().replace("-", "/"));
		
	}
	
	public static void AlterarMarco(int cod, int codVirtudeCardeal, int codVirtudeSatelite, String nome, LocalDate data, String descricao)
	{
		MarcoDAO marcoDAO = new MarcoDAO();
		Marco marco = new Marco();
		marco.setCodigo(cod);
		marco.setCodVirtudeCardeal(codVirtudeCardeal);
		marco.setCodVirtudeSatelite(codVirtudeSatelite);
		marco.setNome(nome);
		marco.setData(data);
		marco.setDescricao(descricao);
		
		int diarioResult = marcoDAO.AlterarMarco(marco);
		LimparCampos();
	}
	
	public static void ListarMarcos(int virt,String desc)
	{
		if(virt > 0)
		{
			if(desc.length() > 0)
			{
				listaMarcos = new MarcoDAO().ListarMarcosPorVirtudeNomeEDescricao(virt, desc);
			}
			else
			{
				listaMarcos = new MarcoDAO().ListarMarcosPorVirtude(virt);
			}
		}
		else 
		{
			if(desc.length() > 0)
			{
				listaMarcos = new MarcoDAO().ListarMarcosPorNomeEDescricao(desc);
			}
			else
			{
				listaMarcos = new MarcoDAO().ListarTodosOsMarcosInner();
			}
		}
			
		if (listaMarcos.isEmpty()) {
			
			JOptionPane.showMessageDialog(null, "Sem registros de acordo com a busca");
			//Deixando a tabela vazia, caso nenhum registro seja retornado
			view.MenuPrincipal.tblSubMenu.setModel(model);
			
		}	
		else
		{
			
			view.MenuPrincipal.tblSubMenu.setModel(new MarcoTable(listaMarcos));
			
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
			.setMinWidth(240);
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(4)
			.setMaxWidth(240);
			
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(5)
			.setMinWidth(240);
			view.MenuPrincipal.tblSubMenu.getColumnModel().getColumn(5)
			.setMaxWidth(240);
			
			
		}
	}
	
}
