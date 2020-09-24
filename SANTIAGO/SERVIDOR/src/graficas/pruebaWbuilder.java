package graficas;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import org.w3c.dom.events.EventTarget;
import org.w3c.dom.events.MouseEvent;
import org.w3c.dom.views.AbstractView;

import javax.swing.JTextField;
import javax.swing.JButton;

public class pruebaWbuilder {

	private JFrame frame;
	private JTextField TBd;
	private JTextField Tusu;
	private JTextField Tpass;
	private JButton btnConecta;
	private JTextField Testado;
	private JLabel lblEstado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pruebaWbuilder window = new pruebaWbuilder();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pruebaWbuilder() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblHolaMaqina = new JLabel("baseDD");
		lblHolaMaqina.setHorizontalAlignment(SwingConstants.CENTER);
		lblHolaMaqina.setBounds(26, 22, 92, 14);
		frame.getContentPane().add(lblHolaMaqina);
		
		TBd = new JTextField();
		TBd.setBounds(172, 19, 86, 20);
		frame.getContentPane().add(TBd);
		TBd.setColumns(10);
		
		JLabel lblNombreUsuario = new JLabel("NOMBRE USUARIO");
		lblNombreUsuario.setBounds(42, 75, 112, 14);
		frame.getContentPane().add(lblNombreUsuario);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(42, 127, 76, 14);
		frame.getContentPane().add(lblPassword);
		
		Tusu = new JTextField();
		Tusu.setBounds(172, 72, 86, 20);
		frame.getContentPane().add(Tusu);
		Tusu.setColumns(10);
		
		Tpass = new JTextField();
		Tpass.setBounds(172, 124, 86, 20);
		frame.getContentPane().add(Tpass);
		Tpass.setColumns(10);
		
		JButton bsalir = new JButton("S A L I R");
		bsalir.addActionListener(new eventoRatonSalir());
		bsalir.setBounds(335, 123, 89, 23);
		frame.getContentPane().add(bsalir);
		
		btnConecta = new JButton("CONECTA");
		btnConecta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String us = Tusu.getText();
				String contra = Tpass.getText();
				String bd = TBd.getText();
				Conectar conecta = new Conectar(us,contra, bd);
				con =conecta.getConnection();
				System.out.println("has pulsado");
				if(con != null){
				Testado.setText("CONEXION CORRECTA");
				}else{
					Testado.setText("CONEXION incorrecta");
				}
			}
		});
		btnConecta.setBounds(335, 18, 89, 23);
		frame.getContentPane().add(btnConecta);
		
		Testado = new JTextField();
		Testado.setBounds(87, 231, 222, 20);
		frame.getContentPane().add(Testado);
		Testado.setColumns(10);
		
		lblEstado = new JLabel("ESTADO");
		lblEstado.setBounds(26, 234, 64, 14);
		frame.getContentPane().add(lblEstado);
		
		Bdesconecta = new JButton("DESCONECTAR");
		Bdesconecta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println("error al desconectar ");
				}
			}
		});
		Bdesconecta.setBounds(312, 71, 112, 23);
		frame.getContentPane().add(Bdesconecta);
	}
	class eventoRatonSalir implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			System.out.println("HAS HECHO CLICK");
			System.exit(0);
		}
		
	}
	private Connection con;
	private JButton Bdesconecta;
}
