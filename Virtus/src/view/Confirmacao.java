package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Confirmacao extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblTitulo;
	public JLabel lblTexto;
	private JLabel lblConfirmacao;
	private JLabel lblNegar;

	public int opcaoClass = -1;
	public MenuPrincipal telaAnterior;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirmacao frame = new Confirmacao();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Confirmacao() {
		setPreferredSize(new Dimension(650, 100));
		setMinimumSize(new Dimension(650, 100));
		setSize(new Dimension(650, 100));
		setMaximumSize(new Dimension(650, 100));
		setBounds(new Rectangle(0, 0, 650, 100));
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(650, 100));
		contentPane.setMinimumSize(new Dimension(650, 100));
		contentPane.setMaximumSize(new Dimension(1000, 100));
		contentPane.setPreferredSize(new Dimension(650, 100));
		contentPane.setBackground(new Color(217, 205, 172));
		contentPane.setBounds(new Rectangle(0, 0, 650, 100));
		contentPane.setBorder(new LineBorder(new Color(255, 252, 243), 5));

		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);
		
		lblTitulo = new JLabel("Confirmação");
		lblTitulo.setBounds(170, 27, 297, 66);
		lblTitulo.setFont(new Font("Yeseva One", Font.PLAIN, 42));
		lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTitulo);
		
		lblTexto = new JLabel("Tem certeza de que deseja excluir a nota?");
		lblTexto.setBounds(10, 96, 630, 33);
		lblTexto.setHorizontalTextPosition(SwingConstants.CENTER);
		lblTexto.setHorizontalAlignment(SwingConstants.CENTER);
		lblTexto.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		contentPane.add(lblTexto);
		
		lblConfirmacao = new JLabel("Sim");
		lblConfirmacao.setBounds(109, 140, 168, 44);
		lblConfirmacao.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				telaAnterior.setFocusable(true);
				switch(opcaoClass)
				{
					case 1:
						telaAnterior.returnExcludeOptionDiario(true);
					break;
					case 2:
						telaAnterior.returnExcludeOptionMarco(true);		
					break;
				}
				dispose();
			}
		});
		lblConfirmacao.setForeground(new Color(255, 252, 243));
		lblConfirmacao.setIcon(new ImageIcon(Confirmacao.class.getResource("/icone/Botao3.png")));
		lblConfirmacao.setHorizontalTextPosition(SwingConstants.CENTER);
		lblConfirmacao.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmacao.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		contentPane.add(lblConfirmacao);
		
		lblNegar = new JLabel("Não");
		lblNegar.setBounds(341, 140, 168, 44);
		lblNegar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				dispose();
				
			}
		});
		lblNegar.setForeground(new Color(255, 252, 243));
		lblNegar.setIcon(new ImageIcon(Confirmacao.class.getResource("/icone/Botao3.png")));
		lblNegar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNegar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNegar.setFont(new Font("Yeseva One", Font.PLAIN, 24));
		contentPane.add(lblNegar);
	}
}
