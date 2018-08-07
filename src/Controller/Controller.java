package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import Inventario.Proveedores;
import Inventrio.Bd;
import View.View;

public class Controller implements ActionListener, MouseListener{
	
	private View view;
	
	public Controller(View view) {
		this.view = view;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		PreparedStatement ps;
		
		String comando = arg0.getActionCommand();
		
		switch(comando) {
		case "INSERTAR" :
			try {
				
				if(!this.view.txtNombre.getText().equals("") || !this.view.txtNombre.getText().isEmpty()) {
					String sql = "INSERT INTO Productos (Nombre, Existencia, Precio, Fecha)"
							 + " VALUES (?, ?, ?, now())";
					ps = Bd.getConexion().prepareStatement(sql);
					ps.setString(1,this.view.txtNombre.getText());
					ps.setInt(2, Integer.parseInt(this.view.txtExistencia.getText()));
					ps.setFloat(3, Float.parseFloat(this.view.txtPrecio.getText()));
					ps.execute();
				}
				
			} catch (SQLException e) {
				System.err.println("Error en la INSERCIÓN");
				System.err.println(e.getMessage());
			}
			cargarTabla();
			break;
			
		case "BORRAR" :
			
			
				int filaPulsada = this.view.tabla.getSelectedRow();
				
				if(filaPulsada >= 0) {
					int identificador = (int)this.view.dtm.getValueAt(filaPulsada, 0);
					try {
						String sql = "DELETE FROM Productos WHERE id = ?";
						ps = Bd.getConexion().prepareStatement(sql);
						ps.setInt(1, identificador);
						ps.execute();
					} catch (SQLException e) {
						System.err.println("Error al ELIMINAR");
						System.err.println(e.getMessage());
						
					}
				
			}
			
				cargarTabla();
			
			break;
			
		case "MODIFICAR" :
			
			
				int filaPulsadaUpdate = this.view.tabla.getSelectedRow();
				System.out.println(filaPulsadaUpdate);
				if(filaPulsadaUpdate >= 0) {
					int identificador = (int)this.view.dtm.getValueAt(filaPulsadaUpdate, 0);
					try {
						String sql = " UPDATE Productos  SET Nombre  = ?, Existencia= ?,  Precio = ?, Fecha = now() WHERE id= ?;";
						ps = Bd.getConexion().prepareStatement(sql);
						ps.setString(1,this.view.txtNombre.getText());
						ps.setInt(2, Integer.parseInt(this.view.txtExistencia.getText()));
						ps.setFloat(3, Float.parseFloat(this.view.txtPrecio.getText()));
						ps.setInt(4, identificador);
						ps.execute();
					} catch (SQLException e) {
						System.err.println("Error en la MODICACION");
						System.err.println(e.getMessage());
					}
				
			}
				cargarTabla();
			break;
			
		case "INSERTAR PROVEEDOR" :
			cargarTablaProveedor();
			if(!this.view.txtNombreProveedor.getText().isEmpty() || !this.view.txtNombreProveedor.getText().equals("")) {
				try {
					String sql = " INSERT INTO Proveedores (Nombre, Direccion, Fecha) VALUES (?, ?, now());";
					ps = Bd.getConexion().prepareStatement(sql);
					ps.setString(1,this.view.txtNombreProveedor.getText());
					ps.setString(2,this.view.txtDireccion.getText());
					ps.execute();
				} catch (SQLException e) {
					System.err.println("Error en la INSERCIÓN PROVEEDOR");
					System.err.println(e.getMessage());
				}
			}
			break;
			
		case "BORRAR PROVEEDOR" :
			
			
				System.out.println("Borrar");
				int filaPulsadaProoverdor = this.view.tablaProveedor.getSelectedRow();
				
				if(filaPulsadaProoverdor >= 0) {
					int identificador = (int)this.view.dtmProveedor.getValueAt(filaPulsadaProoverdor, 0);
					try {
						String sql = "DELETE FROM Proveedores WHERE id = ?";
						ps = Bd.getConexion().prepareStatement(sql);
						ps.setInt(1, identificador);
						ps.execute();
					} catch (SQLException e) {
						System.err.println("Error al ELIMINAR PROVEEDOR");
						System.err.println("Error al ELIMINAR PROVEEDOR");
						
					}
				
			}
				cargarTablaProveedor();
			break;	
		case "MODIFICAR PROVEEDOR" :
			
			
				int filaPulsadaProveedor = this.view.tablaProveedor.getSelectedRow();
				
				if(filaPulsadaProveedor >= 0) {
					System.out.println("Modificar");
					int identificador = (int)this.view.dtmProveedor.getValueAt(filaPulsadaProveedor, 0);
					try {
						String sql = " UPDATE Proveedores  SET Nombre  = ?, Direccion= ?, Fecha = now() WHERE id= ?;";
						ps = Bd.getConexion().prepareStatement(sql);
						ps.setString(1,this.view.txtNombreProveedor.getText());
						ps.setString(2, this.view.txtDireccion.getText());
						ps.setInt(3, identificador);
						ps.execute();
					} catch (SQLException e) {
						System.err.println("Error en la MODICACION PROVEEDOR");
						System.err.println(e.getMessage());
					}
				
			}
				cargarTablaProveedor();
			break;		
		
	 default:
             System.err.println("Comando no definido");
         break;
		}
		
		limpia();
		llenarComboBox();
		
		
	}
	
	private void limpia() {
		this.view.txtNombre.setText("");
		this.view.txtExistencia.setText("");
		this.view.txtPrecio.setText("");
		
	}
	
	protected void cargarTabla(){
		PreparedStatement ps;
		
		ResultSet rs;
		
		Vector<Object> fila;
		
		for(int i = this.view.dtm.getRowCount(); i > 0; i--) {
			this.view.dtm.removeRow(i-1);
		}
		
		try {
			String sql = "SELECT id, Nombre, Existencia, Precio, Fecha FROM Productos";
			ps = Bd.getConexion().prepareStatement(sql);
			rs  = ps.executeQuery();
			while(rs.next()) {
				fila = new Vector<Object>();
				fila.add(rs.getInt("id"));
				fila.add(rs.getString("Nombre"));
				fila.add(rs.getString("Existencia"));
				fila.add(rs.getString("Precio"));
				fila.add(rs.getDate("Fecha"));
				
				this.view.dtm.addRow(fila);
			}
		} catch (SQLException e) {
			 System.err.println("Error al CARGAR DATOS");
		}
	}
	
	protected void cargarTablaProveedor(){
		PreparedStatement ps;
		
		ResultSet rs;
		
		Vector<Object> fila;
		
		for(int i = this.view.dtmProveedor.getRowCount(); i > 0; i--) {
			this.view.dtmProveedor.removeRow(i-1);
		}
		
		try {
			String sql = "SELECT id, Nombre, Direccion, Fecha FROM Proveedores";
			ps = Bd.getConexion().prepareStatement(sql);
			rs  = ps.executeQuery();
			while(rs.next()) {
				fila = new Vector<Object>();
				fila.add(rs.getInt("id"));
				fila.add(rs.getString("Nombre"));
				fila.add(rs.getString("Direccion"));
				fila.add(rs.getDate("Fecha"));
				
				this.view.dtmProveedor.addRow(fila);
			}
		} catch (SQLException e) {
			 System.err.println("Error al CARGAR DATOS");
		}
	}
	
	public void llenarComboBox() {
		PreparedStatement ps;
		System.out.println("Hello");
		ResultSet rs;
		Vector<Proveedores> model = new Vector<Proveedores>();
		DefaultComboBoxModel<Proveedores> value;
		try {
			String sql = "SELECT id, Nombre, Direccion, Fecha FROM Proveedores";
			ps = Bd.getConexion().prepareStatement(sql);
			rs  = ps.executeQuery();
			
			while(rs.next()) {
				Proveedores proveedor= new Proveedores();
				proveedor.setNombre(rs.getString("Nombre"));
				proveedor.setId(rs.getInt("id"));
				model.add(proveedor);
				
			}
			value = new DefaultComboBoxModel<>(model);
			this.view.cmbProveedores.setModel(value);
		} catch (SQLException e) {
			 System.err.println("Error al CARGAR DATOS");
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		PreparedStatement ps;
		ResultSet rs;
		
		int filaPulsada = this.view.tabla.getSelectedRow();
		int filaPulsadaProveedor = this.view.tablaProveedor.getSelectedRow();
		System.out.println(filaPulsadaProveedor);
		System.out.println(filaPulsada);
		if(filaPulsada >= 0) {
			int identificador = (int)this.view.dtm.getValueAt(filaPulsada, 0);
			try {
				String sql = "SELECT  Nombre, Existencia, Precio FROM Productos WHERE id = ?";
				ps = Bd.getConexion().prepareStatement(sql);
				ps.setInt(1, identificador);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					this.view.txtNombre.setText(rs.getString(1));
					this.view.txtExistencia.setText(rs.getString(2));
					this.view.txtPrecio.setText(rs.getString(3));
				}
			} catch (SQLException e) {
				System.err.println("Error al CARGAR UN PRODUCTO");
				System.out.println(e.getMessage());
			}
		} else if(filaPulsadaProveedor >= 0) {
			System.out.println("Entro");
			int identificador = (int)this.view.dtmProveedor.getValueAt(filaPulsadaProveedor, 0);
			try {
				String sql = "SELECT  Nombre, Direccion FROM Proveedores WHERE id = ?";
				ps = Bd.getConexion().prepareStatement(sql);
				ps.setInt(1, identificador);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					this.view.txtNombreProveedor.setText(rs.getString(1));
					this.view.txtDireccion.setText(rs.getString(2));
				}
			} catch (SQLException e) {
				System.err.println("Error al CARGAR UN PROVEEDOR");
				System.out.println(e.getMessage());
			}
		}
		
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
