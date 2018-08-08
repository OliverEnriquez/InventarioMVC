package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Inventrio.Bd;
import Inventrio.Usuario;
import View.Login;
import View.View;

public class ControllerLogin implements ActionListener, MouseListener{
	
	private Login login;
	public Usuario usuarioLogin;
	
	public ControllerLogin(Login login) {
		this.login = login;
	} 
	public ControllerLogin() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		PreparedStatement ps;
		System.out.println("Entro");
		String comando = arg0.getActionCommand();
		
		
			try {
				ResultSet rs;
				String sql = "SELECT * FROM Usuarios WHERE Usuario =?";
				ps = Bd.getConexion().prepareStatement(sql);
				ps.setString(1, this.login.textUsuario.getText());
				rs  = ps.executeQuery();
				
				if(rs.next()) {
					if(rs.getString("Password").equals(this.login.textPassword.getText())) {
						
						
						View frame = new View();
					 	new Bd("Inventario");
					 	this.login.dispose();
					 	this.login.hide();
					 	this.login.setVisible(false);
						Controller controlador = new Controller(frame,rs.getInt("id"), rs.getString("Nombre"), rs.getString("Apellido"), rs.getString("Password"), rs.getString("Usuario"), rs.getInt("Permisos"), rs.getDate("Fecha"));
						
						frame.conectaControlador(controlador);
						controlador.isAdministrador();
						controlador.llenarComboBox();
						controlador.cargarTabla();
						controlador.cargarTablaProveedor();
						controlador.cargarTablaUsuarios();
						controlador.cargarTablaReporte();
						
						
					    frame.setVisible(true);
					   
					   
						
					} else {
						System.out.println("Password Incorrecto");
						this.login.labelError.setText("Password Incorrecto");
					}
					 
				} else {
					System.out.println("Usuario Incorrecto");
					this.login.labelError.setText("Usuario Incorrecto");
				}
				
			} catch (SQLException e) {
				System.err.println("Error en login");
				
			}
			
			
	}
	
	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	

}
