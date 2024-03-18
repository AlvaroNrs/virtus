package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import control.DiarioControl;
import tabela.DiarioTable;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Point;
import java.util.Set;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import java.awt.ComponentOrientation;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import java.awt.Button;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static JTextField txtBusca;
	
	public static JTable tblSubMenu;
	DefaultTableModel modelConquistas = null;
	DefaultTableModel modelDiario = null;
	DefaultTableModel modelVirtudeCardeal = null;
	private static int idVirtudeSelec = -1;
	public int opcaoBusca = -1;
	
	private int selectedItem;
	private JLabel lblExcluirNota;
	private JLabel lblEditarNota;
	private JLabel lblExcluirMarco;
	private JLabel lblEditarMarco;
	private JLabel lblNovoMarco;
	private JLabel lblNovaNota;
	private JLabel lblMinhasNotas;
	private JLabel lblMeusMarcos;
	private JLabel lblFortaleza;
	private JLabel lblJustica;
	private JLabel lblPrudencia;
	private JLabel lblTemperanca;
	private JLabel lblTitulo;
	private JLabel lblIconePesquisar;
	
	public void changeIdVirt(int id)
	{
		idVirtudeSelec = id;
	}
	
	public void search(String text)
	{
		switch(opcaoBusca)
		{
			case 1:
				control.DiarioControl.ListarNotas(idVirtudeSelec, txtBusca.getText());
			break;
			case 2:
				control.MarcoControl.ListarMarcos(idVirtudeSelec, txtBusca.getText());
			break;
		}
	}
	
	
	public Boolean verifyNoteOrGoalInMenu()
	{
		if(opcaoBusca == -1)
		{
			JOptionPane.showMessageDialog(null, "Selecione primeiro Minhas Notas ou Meus Marcos");
			return false;
		}
		return true;
	}
	
	public void setSubtextText(String newText)
	{
		if(idVirtudeSelec != -1)
		{
			switch(opcaoBusca)
			{
				case 1:
					lblTitulo.setText("Minhas Notas / " + newText);
				break;
				case 2:
					lblTitulo.setText("Meus Marcos / " + newText);
				break;
			}
		}
		else
		{
			lblTitulo.setText(newText);
		}
	}

	public void changeSearchOption(int op)
	{
		// 1 -> Notas
		//2 -> Marcos
		opcaoBusca = op;
	}
	
	
	public void changeNotesOptionVisibility(Boolean visible)
	{
		lblEditarNota.setVisible(visible);
		lblExcluirNota.setVisible(visible);
	}
	
	public void changeGoalsOptionVisibility(Boolean visible)
	{
		lblEditarMarco.setVisible(visible);
		lblExcluirMarco.setVisible(visible);
	}
	
	public void callEditNote()
	{
		Nota nota = new Nota();
		nota.setLocationRelativeTo(null);
		control.DiarioControl.BuscarNota(Integer.parseInt(tblSubMenu.getValueAt(tblSubMenu.getSelectedRow(), 0).toString()));
		nota.codDiario = Integer.parseInt(tblSubMenu.getValueAt(tblSubMenu.getSelectedRow(), 0).toString());
		
		dispose();
		
		nota.setVisible(true);
	}
	
	public void callEditGoal()
	{
		Marco marco = new Marco();
		marco.setLocationRelativeTo(null);
		dispose();	
		marco.setVisible(true);
		control.MarcoControl.BuscarMarco(Integer.parseInt(tblSubMenu.getValueAt(tblSubMenu.getSelectedRow(), 0).toString()));
		marco.codMarco = Integer.parseInt(tblSubMenu.getValueAt(tblSubMenu.getSelectedRow(), 0).toString());
		
	}
	
	public void callExcludeNote()
	{
		Confirmacao confirmacao = new Confirmacao();
		confirmacao.lblTexto.setText("Tem certeza de que deseja excluir a nota?");
		confirmacao.telaAnterior = this;
		confirmacao.opcaoClass = 1;
		confirmacao.setLocationRelativeTo(null);
		confirmacao.setVisible(true);
	}
	
	public void callExcludeGoal()
	{
		Confirmacao confirmacao = new Confirmacao();
		confirmacao.lblTexto.setText("Tem certeza de que deseja excluir o marco?");
		confirmacao.telaAnterior = this;
		confirmacao.opcaoClass = 2;
		confirmacao.setLocationRelativeTo(null);
		confirmacao.setVisible(true);
	}
	
	public void returnExcludeOptionDiario(Boolean o)
	{
		if(o)
		{
			control.DiarioControl.ExcluirNota(Integer.parseInt(tblSubMenu.getValueAt(tblSubMenu.getSelectedRow(), 0).toString()));
			control.DiarioControl.ListarNotas(-1, txtBusca.getText());
			contentPane.setRequestFocusEnabled(true);
		}
	}
	
	public void returnExcludeOptionMarco(Boolean o)
	{
		if(o)
		{
			control.MarcoControl.ExcluirMarco(Integer.parseInt(tblSubMenu.getValueAt(tblSubMenu.getSelectedRow(), 0).toString()));
			control.MarcoControl.ListarMarcos(idVirtudeSelec, txtBusca.getText());
			contentPane.setRequestFocusEnabled(true);
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
					frame.setResizable(false);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setMinimumSize(new Dimension(1280, 720));
		setMaximumSize(new Dimension(1280, 720));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 234, 201));
		contentPane.setPreferredSize(new Dimension(1280, 720));
		contentPane.setMinimumSize(new Dimension(1280, 720));
		contentPane.setMaximumSize(new Dimension(1280, 720));
		contentPane.setSize(new Dimension(1280, 720));
		contentPane.setBorder(new LineBorder(new Color(255, 252, 243), 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel pSubMenu = new JPanel();
		pSubMenu.setVisible(false);
		pSubMenu.setBackground(new Color(245, 224, 196));
		pSubMenu.setBorder(new LineBorder(new Color(255, 252, 243), 5));
		pSubMenu.setBounds(194, 85, 1085, 635);
		contentPane.add(pSubMenu);
		pSubMenu.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		scrollPane.setBounds(16, 102, 1055, 478);
		pSubMenu.add(scrollPane);
		
		tblSubMenu = new JTable();
		tblSubMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(tblSubMenu.getModel() != null)
				{
					switch (opcaoBusca)
					{
						case 1:
							changeNotesOptionVisibility(true);
							changeGoalsOptionVisibility(false);
							break;

						case 2:
							changeNotesOptionVisibility(false);
							changeGoalsOptionVisibility(true);
							break;
					}
					
				}
			}
		});
		tblSubMenu.setForeground(new Color(10, 10, 10));
		tblSubMenu.setGridColor(new Color(197, 197, 197));
		tblSubMenu.setShowVerticalLines(false);
		tblSubMenu.setSelectionBackground(new Color(168, 123, 83));
		tblSubMenu.setBounds(421, 298, 647, 280);
		tblSubMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblSubMenu.setRowMargin(2);
		tblSubMenu.setFont(new Font("SansSerif", Font.PLAIN, 14));
		tblSubMenu.setRowHeight(30);
		tblSubMenu.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tblSubMenu.getTableHeader().setFont(new Font("LUMINA", Font.BOLD, 18));
		tblSubMenu.getTableHeader().setBackground(new Color(175,143,110));
		tblSubMenu.getTableHeader().setForeground(new Color(10, 10,10));
		tblSubMenu.getTableHeader().setPreferredSize(new Dimension(scrollPane.getWidth(), 35));
		tblSubMenu.setShowHorizontalLines(false);
		tblSubMenu.setColumnSelectionAllowed(false);		
		scrollPane.setViewportView(tblSubMenu);
		
		lblNovaNota = new JLabel("Nova Nota");
		lblNovaNota.setBounds(880, 40, 180, 38);
		lblNovaNota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				Nota nota = new Nota();
				control.DiarioControl.LimparCampos();
				nota.setLocationRelativeTo(null);
				nota.setVisible(true);
				dispose();
			}
		});
		lblNovaNota.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblNovaNota.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNovaNota.setHorizontalAlignment(SwingConstants.LEFT);
		lblNovaNota.setForeground(new Color(255, 252, 243));
		lblNovaNota.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblNovaNota.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNovaNota.setBackground(new Color(93, 93, 93));
		pSubMenu.add(lblNovaNota);
		
		lblTitulo = new JLabel("Notas Recentes");
		lblTitulo.setForeground(new Color(10, 10, 10));
		lblTitulo.setBounds(16, 32, 563, 32);
		lblTitulo.setPreferredSize(new Dimension(100, 50));
		lblTitulo.setMaximumSize(new Dimension(29, 30));
		lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitulo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTitulo.setFont(new Font("LUMINA", Font.PLAIN, 26));
		lblTitulo.setBackground(new Color(4, 3, 2));
		lblTitulo.setAlignmentX(0.5f);
		pSubMenu.add(lblTitulo);
		
		txtBusca = new JTextField();
		txtBusca.setForeground(new Color(10, 10, 10));
		txtBusca.setBounds(16, 63, 600, 28);
		txtBusca.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		pSubMenu.add(txtBusca);
		txtBusca.setColumns(10);
		
		lblExcluirNota = new JLabel("Excluir Nota");
		lblExcluirNota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				contentPane.setRequestFocusEnabled(false);
				callExcludeNote();
			}
		});
		lblExcluirNota.setBounds(710, 586, 145, 38);
		lblExcluirNota.setVisible(false);
		lblExcluirNota.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/Botao3.png")));
		lblExcluirNota.setHorizontalTextPosition(SwingConstants.CENTER);
		lblExcluirNota.setHorizontalAlignment(SwingConstants.LEFT);
		lblExcluirNota.setForeground(new Color(255, 252, 243));
		lblExcluirNota.setFont(new Font("LUMINA", Font.PLAIN, 16));
		lblExcluirNota.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblExcluirNota.setBackground(new Color(93, 93, 93));
		pSubMenu.add(lblExcluirNota);
		
		lblEditarNota = new JLabel("Editar Nota");
		lblEditarNota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				callEditNote();
			}
		});
		lblEditarNota.setBounds(915, 586, 145, 38);
		lblEditarNota.setVisible(false);
		lblEditarNota.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/Botao3.png")));
		lblEditarNota.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEditarNota.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditarNota.setForeground(new Color(255, 252, 243));
		lblEditarNota.setFont(new Font("LUMINA", Font.PLAIN, 16));
		lblEditarNota.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblEditarNota.setBackground(new Color(93, 93, 93));
		pSubMenu.add(lblEditarNota);
		
		lblNovoMarco = new JLabel("Novo Marco");
		lblNovoMarco.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblNovoMarco.setBounds(880, 40, 180, 38);
		lblNovoMarco.setVisible(false);
		lblNovoMarco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Marco marco = new Marco();
				control.MarcoControl.LimparCampos();
				marco.setLocationRelativeTo(null);
				marco.setVisible(true);
				dispose();
			}
		});
		lblNovoMarco.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNovoMarco.setHorizontalAlignment(SwingConstants.LEFT);
		lblNovoMarco.setForeground(new Color(255, 252, 243));
		lblNovoMarco.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblNovoMarco.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNovoMarco.setBackground(new Color(93, 93, 93));
		pSubMenu.add(lblNovoMarco);
		
		lblExcluirMarco = new JLabel("Excluir Marco");
		lblExcluirMarco.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/Botao3.png")));
		lblExcluirMarco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				contentPane.setRequestFocusEnabled(false);
				callExcludeGoal();
			}
		});
		lblExcluirMarco.setBounds(710, 586, 145, 38);
		lblExcluirMarco.setVisible(false);
		lblExcluirMarco.setHorizontalTextPosition(SwingConstants.CENTER);
		lblExcluirMarco.setHorizontalAlignment(SwingConstants.LEFT);
		lblExcluirMarco.setForeground(new Color(255, 252, 243));
		lblExcluirMarco.setFont(new Font("LUMINA", Font.PLAIN, 16));
		lblExcluirMarco.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblExcluirMarco.setBackground(new Color(93, 93, 93));
		pSubMenu.add(lblExcluirMarco);
		
		lblEditarMarco = new JLabel("Editar Marco");
		lblEditarMarco.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/Botao3.png")));
		lblEditarMarco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				callEditGoal();
			}
		});
		lblEditarMarco.setBounds(915, 586, 145, 38);
		lblEditarMarco.setVisible(false);
		lblEditarMarco.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEditarMarco.setHorizontalAlignment(SwingConstants.LEFT);
		lblEditarMarco.setForeground(new Color(255, 252, 243));
		lblEditarMarco.setFont(new Font("LUMINA", Font.PLAIN, 16));
		lblEditarMarco.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblEditarMarco.setBackground(new Color(93, 93, 93));
		pSubMenu.add(lblEditarMarco);
		
		JLabel lblPesquisar = new JLabel("");
		lblIconePesquisar = new JLabel("");
		lblIconePesquisar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				switch(opcaoBusca)
				{
					case 1:
						control.DiarioControl.ListarNotas(-1, txtBusca.getText());
						break;
					
					case 2:
						control.MarcoControl.ListarMarcos(-1, txtBusca.getText());
						break;
				}			
			}
		});
		lblIconePesquisar.setHorizontalAlignment(SwingConstants.CENTER);
		lblIconePesquisar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/Lupa.png")));
		lblIconePesquisar.setBounds(626, 63, 49, 28);
		pSubMenu.add(lblIconePesquisar);
		lblPesquisar.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/Botao_Pesquisar.png")));
		lblPesquisar.setBounds(625, 58, 50, 38);
		pSubMenu.add(lblPesquisar);
		
		JLabel lblIndicacao = new JLabel("New label");
		lblIndicacao.setForeground(new Color(10, 10, 10));
		lblIndicacao.setFont(new Font("Yeseva One", Font.PLAIN, 28));
		lblIndicacao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblIndicacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblIndicacao.setBounds(406, 200, 650, 300);
		lblIndicacao.setText("Selecione uma opção no Índice para começar");
		contentPane.add(lblIndicacao);
		
		JPanel pHeader = new JPanel();
		pHeader.setBorder(new LineBorder(new Color(255, 252, 243), 5));
		pHeader.setBackground(new Color(143, 93, 59));
		pHeader.setForeground(new Color(0, 0, 0));
		pHeader.setMinimumSize(new Dimension(1280, 100));
		pHeader.setPreferredSize(new Dimension(1280, 90));
		pHeader.setBounds(0, 0, 1280, 90);
		contentPane.add(pHeader);
		pHeader.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Virtus");
		lblNewLabel.setPreferredSize(new Dimension(1280, 90));
		lblNewLabel.setMaximumSize(new Dimension(2000, 200));
		lblNewLabel.setForeground(new Color(245, 195, 43));
		lblNewLabel.setFont(new Font("Yeseva One", Font.PLAIN, 38));
		lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		pHeader.add(lblNewLabel);
		
		JPanel pMenuPrincipal = new JPanel();
		pMenuPrincipal.setPreferredSize(new Dimension(300, 600));
		pMenuPrincipal.setMaximumSize(new Dimension(2000, 2000));
		pMenuPrincipal.setMinimumSize(new Dimension(27, 14));
		pMenuPrincipal.setSize(new Dimension(500, 2000));
		pMenuPrincipal.setBackground(new Color(193, 162, 129));
		pMenuPrincipal.setBorder(new LineBorder(new Color(255, 252, 243), 5));
		pMenuPrincipal.setBounds(0, 85, 200, 635);
		contentPane.add(pMenuPrincipal);
		pMenuPrincipal.setLayout(null);
		
		lblFortaleza = new JLabel("Fortaleza");
		lblFortaleza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(verifyNoteOrGoalInMenu())
				{
					pSubMenu.setVisible(true);
					changeIdVirt(1);
					setSubtextText("Fortaleza");
					search(txtBusca.getText());
				}
			}
		});
		lblFortaleza.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblFortaleza.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFortaleza.setHorizontalAlignment(SwingConstants.LEFT);
		lblFortaleza.setForeground(new Color(255, 252, 243));
		lblFortaleza.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblFortaleza.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblFortaleza.setBackground(new Color(93, 93, 93));
		lblFortaleza.setBounds(10, 288, 180, 38);
		pMenuPrincipal.add(lblFortaleza);
		
		JLabel lblEstrela = new JLabel("");
		lblEstrela.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEstrela.setHorizontalAlignment(SwingConstants.CENTER);
		lblEstrela.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/Estrela.png")));
		lblEstrela.setBounds(159, 90, 26, 26);
		pMenuPrincipal.add(lblEstrela);
		
		JLabel lblNotaRapida = new JLabel("Criar Nota Rápida    ");
		lblNotaRapida.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//TO-DO: Abrir a tela de edição de notas, cada campo vazio ou zerado
				Nota nota = new Nota();
				nota.setLocationRelativeTo(null);
				nota.setVisible(true);
				nota.codDiario = -1;
				nota.virtudeCardeal = 1;
				nota.virtudeSatelite = 1;
				nota.retornoErro = "";
				dispose();
			}
		});
		lblNotaRapida.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblNotaRapida.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNotaRapida.setHorizontalAlignment(SwingConstants.LEFT);
		lblNotaRapida.setForeground(new Color(255, 252, 243));
		lblNotaRapida.setFont(new Font("LUMINA", Font.PLAIN, 18));
		lblNotaRapida.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblNotaRapida.setBackground(new Color(93, 93, 93));
		lblNotaRapida.setBounds(10, 80, 180, 49);
		pMenuPrincipal.add(lblNotaRapida);
		
		JLabel lblSubtitulo = new JLabel("Índice");
		lblSubtitulo.setForeground(new Color(10, 10, 10));
		lblSubtitulo.setBackground(new Color(4, 3, 2));
		lblSubtitulo.setBounds(10, 24, 180, 30);
		lblSubtitulo.setMaximumSize(new Dimension(29, 30));
		lblSubtitulo.setFont(new Font("Yeseva One", Font.PLAIN, 30));
		lblSubtitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblSubtitulo.setPreferredSize(new Dimension(100, 50));
		pMenuPrincipal.add(lblSubtitulo);
		
		lblMinhasNotas = new JLabel("Minhas Notas");
		lblMinhasNotas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pSubMenu.setVisible(true);
				lblNovaNota.setVisible(true);
				lblNovoMarco.setVisible(false);
				changeNotesOptionVisibility(false);
				changeGoalsOptionVisibility(false);
				changeIdVirt(-1);
				changeSearchOption(1);
				setSubtextText("Minhas Notas");
				control.DiarioControl.ListarNotas(0,"");
				
			}
		});
		lblMinhasNotas.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblMinhasNotas.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblMinhasNotas.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMinhasNotas.setHorizontalAlignment(SwingConstants.LEFT);
		lblMinhasNotas.setForeground(new Color(255, 252, 243));
		lblMinhasNotas.setFont(new Font("LUMINA", Font.PLAIN, 19));
		lblMinhasNotas.setBackground(new Color(93, 93, 93));
		lblMinhasNotas.setBounds(10, 132, 180, 38);
		pMenuPrincipal.add(lblMinhasNotas);
		
		JLabel lblSubtitlo2 = new JLabel("Virtudes Cardeais");
		lblSubtitlo2.setForeground(new Color(10, 10, 10));
		lblSubtitlo2.setPreferredSize(new Dimension(100, 50));
		lblSubtitlo2.setMaximumSize(new Dimension(29, 30));
		lblSubtitlo2.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSubtitlo2.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitlo2.setFont(new Font("Yeseva One", Font.PLAIN, 19));
		lblSubtitlo2.setBackground(new Color(4, 3, 2));
		lblSubtitlo2.setAlignmentX(0.5f);
		lblSubtitlo2.setBounds(10, 247, 180, 30);
		pMenuPrincipal.add(lblSubtitlo2);
		
		lblJustica = new JLabel("Justiça");
		lblJustica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(verifyNoteOrGoalInMenu())
				{
					pSubMenu.setVisible(true);
					changeIdVirt(2);
					setSubtextText("Justiça");
					search(txtBusca.getText());
				}
			}
		});
		lblJustica.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblJustica.setHorizontalTextPosition(SwingConstants.CENTER);
		lblJustica.setHorizontalAlignment(SwingConstants.LEFT);
		lblJustica.setForeground(new Color(255, 252, 243));
		lblJustica.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblJustica.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblJustica.setBackground(new Color(93, 93, 93));
		lblJustica.setBounds(10, 337, 180, 38);
		pMenuPrincipal.add(lblJustica);
		
		lblPrudencia = new JLabel("Prudência");
		lblPrudencia.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(verifyNoteOrGoalInMenu())
				{
					pSubMenu.setVisible(true);
					changeIdVirt(3);
					setSubtextText("Prudência");
					control.DiarioControl.ListarNotas(idVirtudeSelec, txtBusca.getText());
				}
			}
		});
		lblPrudencia.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblPrudencia.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPrudencia.setHorizontalAlignment(SwingConstants.LEFT);
		lblPrudencia.setForeground(new Color(255, 252, 243));
		lblPrudencia.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblPrudencia.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblPrudencia.setBackground(new Color(93, 93, 93));
		lblPrudencia.setBounds(10, 386, 180, 38);
		pMenuPrincipal.add(lblPrudencia);
		
		lblTemperanca = new JLabel("Temperança");
		lblTemperanca.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(verifyNoteOrGoalInMenu())
				{
					pSubMenu.setVisible(true);
					changeIdVirt(4);
					setSubtextText("Temperança");
					search(txtBusca.getText());
				}
			}
		});
		lblTemperanca.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblTemperanca.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTemperanca.setHorizontalAlignment(SwingConstants.LEFT);
		lblTemperanca.setForeground(new Color(255, 252, 243));
		lblTemperanca.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblTemperanca.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblTemperanca.setBackground(new Color(93, 93, 93));
		lblTemperanca.setBounds(10, 435, 180, 38);
		pMenuPrincipal.add(lblTemperanca);
		
		JLabel lblSair = new JLabel("Sair");
		lblSair.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblSair.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/Botao2.png")));
		lblSair.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSair.setHorizontalAlignment(SwingConstants.LEFT);
		lblSair.setForeground(new Color(255, 252, 243));
		lblSair.setFont(new Font("LUMINA", Font.PLAIN, 17));
		lblSair.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblSair.setBackground(new Color(93, 93, 93));
		lblSair.setBounds(24, 573, 145, 38);
		pMenuPrincipal.add(lblSair);
		
		lblMeusMarcos = new JLabel("Meus Marcos");
		lblMeusMarcos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changeNotesOptionVisibility(false);
				changeGoalsOptionVisibility(false);
				lblNovaNota.setVisible(false);
				lblNovoMarco.setVisible(true);
				pSubMenu.setVisible(true);
				changeIdVirt(-1);
				changeSearchOption(2);
				setSubtextText("Meus Marcos");
				control.MarcoControl.ListarMarcos(idVirtudeSelec, "");
				
			}
		});
		lblMeusMarcos.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/B0_.png")));
		lblMeusMarcos.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMeusMarcos.setHorizontalAlignment(SwingConstants.LEFT);
		lblMeusMarcos.setForeground(new Color(255, 252, 243));
		lblMeusMarcos.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblMeusMarcos.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblMeusMarcos.setBackground(new Color(93, 93, 93));
		lblMeusMarcos.setBounds(10, 176, 180, 49);
		pMenuPrincipal.add(lblMeusMarcos);
		
		JLabel lblVFundo = new JLabel("");
		lblVFundo.setSize(new Dimension(2000, 2000));
		lblVFundo.setMinimumSize(new Dimension(2000, 2000));
		lblVFundo.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/icone/V_Fundo.png")));
		lblVFundo.setPreferredSize(new Dimension(2500, 2500));
		lblVFundo.setMaximumSize(new Dimension(3000, 3000));
		lblVFundo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblVFundo.setHorizontalAlignment(SwingConstants.CENTER);
		lblVFundo.setForeground(new Color(243, 216, 140));
		lblVFundo.setFont(new Font("Yeseva One", Font.PLAIN, 38));
		lblVFundo.setBounds(0, 14, 1280, 700);
		contentPane.add(lblVFundo);
	}
}
