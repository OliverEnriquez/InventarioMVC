package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import Controller.Controller;
import Controller.ControllerLogin;
import Inventrio.Bd;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class Login extends JFrame {

	public JPanel contentPane;
	public JTextField textUsuario;
	public JPasswordField textPassword;
	JButton btnEntrar;
	public JFrame frame;
	public JLabel labelError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.out.println("Inicia");
					Login frame = new Login();
					new Bd("Inventario");
					ControllerLogin login = new ControllerLogin(frame);
					frame.conectaControlador(login);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setTitle("Inventario");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 509, 243);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Usuario");
		lblNewLabel.setBounds(156, 91, 70, 15);
		contentPane.add(lblNewLabel);

		textUsuario = new JTextField();
		textUsuario.setBounds(230, 89, 114, 19);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(156, 120, 70, 15);
		contentPane.add(lblPassword);

		textPassword = new JPasswordField();
		textPassword.setColumns(10);
		textPassword.setBounds(230, 118, 114, 19);
		contentPane.add(textPassword);

		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(230, 149, 117, 25);
		contentPane.add(btnEntrar);

		JLabel lblLoginInventario = new JLabel("Login Inventario");
		lblLoginInventario.setFont(new Font("Dialog", Font.BOLD, 16));
		lblLoginInventario.setBounds(178, 49, 166, 15);
		contentPane.add(lblLoginInventario);

		labelError = new JLabel("");
		labelError.setForeground(Color.RED);
		labelError.setBounds(156, 219, 201, 15);
		contentPane.add(labelError);
	}

	public void conectaControlador(ControllerLogin c) {

		btnEntrar.addActionListener(c);
		btnEntrar.setActionCommand("ENTRAR");

	}
}
