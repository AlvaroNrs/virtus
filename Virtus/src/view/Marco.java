package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import model.ListaVirtudeCardeal;
import model.ListaVirtudeSatelite;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.ComponentOrientation;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.time.LocalDate;

import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

public class Marco extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MaskFormatter Data;
	public static JComboBox cmbVirtudeSatelite;
	public static JTextArea txtDescricao;
	public static JFormattedTextField txtUltimaAtualizacao;
	public static JTextField txtNome;

	public static int codMarco = -1;
	public static int virtudeCardeal = 1;
	public static int virtudeSatelite = 1;
	public static String retornoErro = "";
	public static LocalDate dataAjustada = null;
	public static JLabel lblltimaAtualizao;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Nota frame = new Nota();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});		
	}

	/**
	 * Create the frame.
	 */
	public Marco() {
		setMinimumSize(new Dimension(1190, 685));
		setPreferredSize(new Dimension(1190, 685));
		setMaximumSize(new Dimension(1190, 658));
		setResizable(false);
		setBounds(new Rectangle(0, 0, 1190, 685));
		setSize(new Dimension(1234, 300));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(1190, 685));
		contentPane.setBackground(new Color(246, 234, 201));
		contentPane.setBounds(new Rectangle(0, 0, 1190, 685));
		contentPane.setMinimumSize(new Dimension(1190, 685));
		contentPane.setMaximumSize(new Dimension(1190, 685));
		contentPane.setBorder(new LineBorder(new Color(255, 252, 243), 5));
		
		try {
			 Data = new MaskFormatter("##/##/####");
		}
		catch (ParseException e1) {
			//TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(193, 162, 129));
		panel.setBorder(new LineBorder(new Color(255, 252, 243), 5));
		panel.setBounds(40, 35, 1115, 615);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Marco");
		lblTitulo.setForeground(new Color(10, 10, 10));
		lblTitulo.setBounds(470, 42, 150, 35);
		lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Yeseva One", Font.PLAIN, 36));
		panel.add(lblTitulo);
		
		JLabel lblVirtudeEspecfica = new JLabel("Virtude Específica:");
		lblVirtudeEspecfica.setForeground(new Color(10, 10, 10));
		lblVirtudeEspecfica.setBounds(44, 120, 230, 31);
		lblVirtudeEspecfica.setHorizontalTextPosition(SwingConstants.CENTER);
		lblVirtudeEspecfica.setHorizontalAlignment(SwingConstants.CENTER);
		lblVirtudeEspecfica.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		panel.add(lblVirtudeEspecfica);
		
		cmbVirtudeSatelite = new JComboBox();
		cmbVirtudeSatelite.setBounds(280, 123, 230, 26);
		cmbVirtudeSatelite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox cmbVirtudeEspecifica = (JComboBox)e.getSource();
				
				model.VirtudeSatelite virtude_Satelite = (model.VirtudeSatelite)cmbVirtudeEspecifica.getSelectedItem();
				
				virtudeSatelite = virtude_Satelite.getCodVirtudeSatelite();
				virtudeCardeal = virtude_Satelite.getCodVirtudeCardeal();
			}
		});
		cmbVirtudeSatelite.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		panel.add(cmbVirtudeSatelite);
		
		DefaultComboBoxModel virtudesSatelite = new DefaultComboBoxModel(ListaVirtudeSatelite.listarVirtudesSatelites().toArray());
		cmbVirtudeSatelite.setModel(virtudesSatelite);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setForeground(new Color(10, 10, 10));
		lblNome.setBounds(44, 192, 210, 31);
		lblNome.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNome.setHorizontalAlignment(SwingConstants.LEFT);
		lblNome.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		panel.add(lblNome);
		
		txtDescricao = new JTextArea();
		txtDescricao.setBounds(44, 322, 1028, 200);
		txtDescricao.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescricao.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		panel.add(txtDescricao);
		
		lblltimaAtualizao = new JLabel("Última Atualização:");
		lblltimaAtualizao.setForeground(new Color(10, 10, 10));
		lblltimaAtualizao.setBounds(718, 290, 240, 31);
		lblltimaAtualizao.setVisible(false);
		lblltimaAtualizao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblltimaAtualizao.setHorizontalAlignment(SwingConstants.LEFT);
		lblltimaAtualizao.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		panel.add(lblltimaAtualizao);
		
		JLabel lblVoltar = new JLabel("Voltar");
		lblVoltar.setBounds(44, 543, 180, 38);
		lblVoltar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setLocationRelativeTo(null);
				menuP.setVisible(true);
				dispose();
			}
		});
		lblVoltar.setIcon(new ImageIcon(Nota.class.getResource("/icone/B0_.png")));
		lblVoltar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblVoltar.setHorizontalAlignment(SwingConstants.CENTER);
		lblVoltar.setForeground(new Color(255, 252, 243));
		lblVoltar.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblVoltar.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblVoltar.setBackground(new Color(93, 93, 93));
		panel.add(lblVoltar);
		
		JLabel lblSalvarMarco = new JLabel("Salvar Marco");
		lblSalvarMarco.setBounds(892, 543, 180, 38);
		lblSalvarMarco.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(codMarco == -1)
				{
					//Insert
					if(txtNome.getText().length() <= 0 || txtNome.getText().length() <= 6)
					{
						retornoErro = "O campo de nome não pode ser vazio e deve ter mais de 6 caracteres \n";
					}
					if(txtNome.getText().length() > 50)
					{
						retornoErro = retornoErro + "Limite de caracteres do campo nome atingido \n";
					}
					if(txtDescricao.getText().length() <= 0 || txtDescricao.getText().length() <= 10)
					{
						retornoErro = retornoErro + "O campo de descrição não pode ser vazio e deve ter mais de 10 caracteres \n";
					}
					if(txtDescricao.getText().length() >= 600)
					{
						retornoErro = retornoErro + "Limite de caracteres do campo descrição atingido";
					}
					if(retornoErro.length() > 0)
					{
						JOptionPane.showMessageDialog(null, retornoErro);
						retornoErro = "";
						return;
					}
					else
					{
						//Chamando a função de inserção no Banco
						dataAjustada = LocalDate.now();
						control.MarcoControl.CadastrarMarco(virtudeCardeal, virtudeSatelite, txtNome.getText(), dataAjustada, txtDescricao.getText());
					}
				}
				else
				{
					//Update
					if(txtNome.getText().length() <= 0 || txtNome.getText().length() <= 6)
					{
						retornoErro = "O campo de nome não pode ser vazio e deve ter mais de 6 caracteres \n";
					}
					if(txtNome.getText().length() > 50)
					{
						retornoErro = retornoErro + "Limite de caracteres do campo nome atingido \n";
					}
					if(txtDescricao.getText().length() <= 0 || txtDescricao.getText().length() <= 10)
					{
						retornoErro = retornoErro + "O campo de descrição não pode ser vazio e deve ter mais de 10 caracteres \n";
					}
					if(txtDescricao.getText().length() >= 600)
					{
						retornoErro = retornoErro + "Limite de caracteres do campo descrição atingido";
					}
					if(retornoErro.length() > 0)
					{
						JOptionPane.showMessageDialog(null, retornoErro);
						retornoErro = "";
						return;
					}
					else
					{
						dataAjustada = LocalDate.now();
						//Chamando a função de update no Banco
						control.MarcoControl.AlterarMarco(codMarco, virtudeCardeal, virtudeSatelite, txtNome.getText(), dataAjustada, txtDescricao.getText());
					}
				}
				codMarco = -1;
			}
		});
		lblSalvarMarco.setIcon(new ImageIcon(Nota.class.getResource("/icone/B0_.png")));
		lblSalvarMarco.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSalvarMarco.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalvarMarco.setForeground(new Color(255, 252, 243));
		lblSalvarMarco.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblSalvarMarco.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblSalvarMarco.setBackground(new Color(93, 93, 93));
		panel.add(lblSalvarMarco);
		
		txtUltimaAtualizacao = new JFormattedTextField(Data);
		txtUltimaAtualizacao.setBounds(965, 293, 105, 26);
		txtUltimaAtualizacao.setVisible(false);
		txtUltimaAtualizacao.setForeground(new Color(54, 54, 54));
		txtUltimaAtualizacao.setFocusable(false);
		txtUltimaAtualizacao.setText("00/00/0000    ");
		txtUltimaAtualizacao.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		panel.add(txtUltimaAtualizacao);
		
		JLabel lblDescritivo_1 = new JLabel("Descritivo:");
		lblDescritivo_1.setForeground(new Color(10, 10, 10));
		lblDescritivo_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDescritivo_1.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescritivo_1.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		lblDescritivo_1.setBounds(44, 290, 210, 31);
		panel.add(lblDescritivo_1);
		
		txtNome = new JTextField();
		txtNome.setFont(new Font("Lucida Sans", Font.PLAIN, 18));
		txtNome.setBounds(44, 219, 700, 26);
		panel.add(txtNome);
		txtNome.setColumns(10);
		
	}
}
