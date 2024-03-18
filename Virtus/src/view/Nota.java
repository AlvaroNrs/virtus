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
import java.util.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFormattedTextField;
import javax.swing.border.BevelBorder;

public class Nota extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MaskFormatter Data;
	public static JComboBox cmbVirtudeSatelite;
	public static JTextArea txtDescricao;
	public static JFormattedTextField txtUltimaAtualizacao;
	public static JLabel lblltimaAtualizao;

	public static int codDiario = -1;
	public static int virtudeCardeal = 1;
	public static int virtudeSatelite = 1;
	public static String retornoErro = "";
	public static LocalDate dataAjustada = null;
	
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
	public Nota() {
		setMinimumSize(new Dimension(1280, 720));
		setPreferredSize(new Dimension(1280, 720));
		setMaximumSize(new Dimension(1280, 720));
		setResizable(false);
		setBounds(new Rectangle(0, 0, 1280, 720));
		setSize(new Dimension(1234, 300));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(246, 234, 201));
		contentPane.setBounds(new Rectangle(0, 0, 1280, 720));
		contentPane.setMinimumSize(new Dimension(1280, 720));
		contentPane.setMaximumSize(new Dimension(1280, 720));
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
		panel.setBounds(50, 60, 1180, 600);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Nota");
		lblTitulo.setForeground(new Color(10, 10, 10));
		lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Yeseva One", Font.PLAIN, 36));
		lblTitulo.setBounds(515, 42, 150, 35);
		panel.add(lblTitulo);
		
		JLabel lblVirtudeEspecfica = new JLabel("Virtude Específica:");
		lblVirtudeEspecfica.setForeground(new Color(10, 10, 10));
		lblVirtudeEspecfica.setHorizontalTextPosition(SwingConstants.CENTER);
		lblVirtudeEspecfica.setHorizontalAlignment(SwingConstants.CENTER);
		lblVirtudeEspecfica.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		lblVirtudeEspecfica.setBounds(44, 120, 230, 31);
		panel.add(lblVirtudeEspecfica);
		
		cmbVirtudeSatelite = new JComboBox();
		cmbVirtudeSatelite.setForeground(new Color(10, 10, 10));
		cmbVirtudeSatelite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JComboBox cmbVirtudeEspecifica = (JComboBox)e.getSource();
				
				model.VirtudeSatelite virtude_Satelite = (model.VirtudeSatelite)cmbVirtudeEspecifica.getSelectedItem();
				
				virtudeSatelite = virtude_Satelite.getCodVirtudeSatelite();
				virtudeCardeal = virtude_Satelite.getCodVirtudeCardeal();
				
			}
		});
		cmbVirtudeSatelite.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		cmbVirtudeSatelite.setBounds(280, 123, 230, 26);
		panel.add(cmbVirtudeSatelite);
		
		DefaultComboBoxModel virtudesSatelite = new DefaultComboBoxModel(ListaVirtudeSatelite.listarVirtudesSatelites().toArray());
		cmbVirtudeSatelite.setModel(virtudesSatelite);
		
		JLabel lblDescritivo = new JLabel("Descritivo:");
		lblDescritivo.setForeground(new Color(10, 10, 10));
		lblDescritivo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblDescritivo.setHorizontalAlignment(SwingConstants.LEFT);
		lblDescritivo.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		lblDescritivo.setBounds(44, 180, 210, 31);
		panel.add(lblDescritivo);
		
		txtDescricao = new JTextArea();
		txtDescricao.setForeground(new Color(10, 10, 10));
		txtDescricao.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDescricao.setFont(new Font("Lucida Sans", Font.PLAIN, 15));
		txtDescricao.setBounds(44, 212, 1088, 320);
		panel.add(txtDescricao);
		
		lblltimaAtualizao = new JLabel("Última Atualização:");
		lblltimaAtualizao.setVisible(false);
		lblltimaAtualizao.setForeground(new Color(10, 10, 10));
		lblltimaAtualizao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblltimaAtualizao.setHorizontalAlignment(SwingConstants.LEFT);
		lblltimaAtualizao.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		lblltimaAtualizao.setBounds(780, 180, 240, 31);
		panel.add(lblltimaAtualizao);
		
		JLabel lblVoltar = new JLabel("Voltar");
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
		lblVoltar.setBounds(44, 543, 180, 38);
		panel.add(lblVoltar);
		
		JLabel lblSalvarNota = new JLabel("Salvar Nota");
		lblSalvarNota.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if(codDiario == -1)
				{
					//Insert
					if(txtDescricao.getText().length() <= 0 || txtDescricao.getText().length() <= 10)
					{
						retornoErro = "O campo de descrição não pode ser vazio e deve ter mais de 10 caracteres";
					}
					if(txtDescricao.getText().length() >= 600)
					{
						retornoErro = "Limite de caracteres do campo descrição atingido";
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
						control.DiarioControl.CadastrarNota(virtudeCardeal, virtudeSatelite, dataAjustada, txtDescricao.getText());
					}
				}
				else
				{
					//Update
					if(txtDescricao.getText().length() <= 0 || txtDescricao.getText().length() <= 10)
					{
						retornoErro = "O Campo de descrição não pode ser vazio e deve ter mais de 10 caracteres";
					}
					if(txtDescricao.getText().length() >= 600)
					{
						retornoErro = "Limite de caracteres do campo descrição atingido";
					}
					if(retornoErro.length() > 0)
					{
						JOptionPane.showMessageDialog(null, retornoErro);
						retornoErro = "";
						return;
					}
					else
					{
						//Chamando a função de update no Banco
						dataAjustada = LocalDate.now();
						control.DiarioControl.AlterarNota(codDiario, virtudeCardeal, virtudeSatelite, dataAjustada, txtDescricao.getText());
					}
				}
				
				codDiario = -1;
			}
		});
		lblSalvarNota.setIcon(new ImageIcon(Nota.class.getResource("/icone/B0_.png")));
		lblSalvarNota.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSalvarNota.setHorizontalAlignment(SwingConstants.CENTER);
		lblSalvarNota.setForeground(new Color(255, 252, 243));
		lblSalvarNota.setFont(new Font("LUMINA", Font.PLAIN, 20));
		lblSalvarNota.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		lblSalvarNota.setBackground(new Color(93, 93, 93));
		lblSalvarNota.setBounds(952, 543, 180, 38);
		panel.add(lblSalvarNota);
		
		txtUltimaAtualizacao = new JFormattedTextField(Data);
		txtUltimaAtualizacao.setVisible(false);
		txtUltimaAtualizacao.setForeground(new Color(54, 54, 54));
		txtUltimaAtualizacao.setFocusable(false);
		txtUltimaAtualizacao.setText("00/00/0000    ");
		txtUltimaAtualizacao.setFont(new Font("Lucida Sans", Font.PLAIN, 16));
		txtUltimaAtualizacao.setBounds(1027, 184, 105, 26);
		panel.add(txtUltimaAtualizacao);
		
	}
}
