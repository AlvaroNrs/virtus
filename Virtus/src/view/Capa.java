package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import javax.swing.border.LineBorder;

import tabela.DiarioTable;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Capa extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cPInicio;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Capa frame = new Capa();
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
	public Capa() {
		setUndecorated(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		cPInicio = new JPanel();
		cPInicio.setBackground(new Color(154, 109, 78));
		cPInicio.setMinimumSize(new Dimension(600, 400));
		cPInicio.setMaximumSize(new Dimension(600, 400));
		cPInicio.setBorder(new LineBorder(new Color(255, 252, 243), 8));

		setContentPane(cPInicio);
		
		JLabel lblTitle = new JLabel("Virtus");
		lblTitle.setBounds(10, 55, 430, 62);
		lblTitle.setForeground(new Color(245, 195, 43));
		lblTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Yeseva One", lblTitle.getFont().getStyle(), lblTitle.getFont().getSize() + 42));
		cPInicio.setLayout(null);
		
		JLabel lblSetaEntrar = new JLabel("");
		lblSetaEntrar.setIcon(new ImageIcon(Capa.class.getResource("/icone/Seta.png")));
		lblSetaEntrar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSetaEntrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblSetaEntrar.setForeground(new Color(245, 195, 43));
		lblSetaEntrar.setFont(new Font("Yeseva One", Font.PLAIN, 20));
		lblSetaEntrar.setBounds(290, 191, 32, 36);
		cPInicio.add(lblSetaEntrar);
		
		JLabel lblSeparador = new JLabel("");
		lblSeparador.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSeparador.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeparador.setBounds(44, 114, 353, 6);
		lblSeparador.setIcon(new ImageIcon(Capa.class.getResource("/icone/Separador.png")));
		cPInicio.add(lblSeparador);
		cPInicio.add(lblTitle);
		
		JLabel lblSubTitle = new JLabel("Caderno de Virtudes");
		lblSubTitle.setFont(new Font("LUMINA", lblSubTitle.getFont().getStyle(), lblSubTitle.getFont().getSize() + 15));
		lblSubTitle.setHorizontalTextPosition(SwingConstants.CENTER);
		lblSubTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubTitle.setForeground(new Color(255, 252, 243));
		lblSubTitle.setBounds(10, 128, 430, 26);
		cPInicio.add(lblSubTitle);
		
		JLabel lblBotaoEntrar = new JLabel("Abrir Caderno   ");
		lblBotaoEntrar.setBackground(new Color(93, 93, 93));
		lblBotaoEntrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuPrincipal menuP = new MenuPrincipal();
				menuP.setLocationRelativeTo(null);
				menuP.setVisible(true);
				//menuP.tblSubMenu.setModel(new DiarioTable(null));
				dispose();
			}
		});
		lblBotaoEntrar.setHorizontalAlignment(SwingConstants.LEFT);
		lblBotaoEntrar.setForeground(new Color(245, 195, 43));
		lblBotaoEntrar.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		lblBotaoEntrar.setIcon(new ImageIcon(Capa.class.getResource("/icone/Botao5.png")));
		lblBotaoEntrar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblBotaoEntrar.setBounds(110, 170, 227, 77);
		cPInicio.add(lblBotaoEntrar);
		
		JLabel lblFechar = new JLabel("X");
		lblFechar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		lblFechar.setIcon(new ImageIcon(Capa.class.getResource("/icone/Botao_Pesquisar.png")));
		lblFechar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblFechar.setHorizontalAlignment(SwingConstants.LEFT);
		lblFechar.setForeground(new Color(245, 195, 43));
		lblFechar.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		lblFechar.setBackground(new Color(93, 93, 93));
		lblFechar.setBounds(391, 8, 49, 36);
		cPInicio.add(lblFechar);
	}
}
