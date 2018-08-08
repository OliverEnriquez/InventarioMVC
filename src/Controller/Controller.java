package Controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import Inventrio.Bd;
import Inventrio.Usuario;
import View.Login;
import View.View;

public class Controller implements ActionListener, MouseListener{
	
	private View view;
	public Integer id;
	public String nombre;
	public String apellido;
	public String password;
	public String usuario;
	public Integer permisos;
	public Date fecha;
	
	
	
	public Controller(View view, Integer id, String nombre, String apellido, String password, String usuario, Integer permisos,
			Date fecha) {
		super();
		this.view = view;
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.password = password;
		this.usuario = usuario;
		this.permisos = permisos;
		this.fecha = fecha;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		PreparedStatement ps;
		
		String comando = arg0.getActionCommand();
		
		switch(comando) {
		case "INSERTAR" :
			try {
				int antesExistencia = getExitencia();
				if(this.view.cmbProveedores.getSelectedIndex() != 0 && (!this.view.txtNombre.getText().equals("") || !this.view.txtNombre.getText().isEmpty())) {
					
					String sql = "INSERT INTO Productos (Nombre, Existencia, Precio, Fecha, idProveedor, idUsuario)"
							 + " VALUES (?, ?, ?, now(), ?, ?)";
					ps = Bd.getConexion().prepareStatement(sql);
					ps.setString(1,this.view.txtNombre.getText());
					ps.setInt(2, Integer.parseInt(this.view.txtExistencia.getText()));					
					ps.setFloat(3, Float.parseFloat(this.view.txtPrecio.getText()));
					ps.setInt(4, getProveedor(this.view.cmbProveedores.getSelectedItem().toString()));
					ps.setInt(5, id);
					ps.execute();
					this.view.lblError.setForeground(Color.green);
					this.view.lblError.setText("Producto Agregado");
					cargarTabla();
					int despuesExistencia = getExitencia()>=antesExistencia?getExitencia()-antesExistencia:antesExistencia-getExitencia();
					
					if(despuesExistencia != 0) {
						crearReporte("ENTRADA", despuesExistencia);
					}
				} else {
					System.err.println("Llenar todos los campos");
					this.view.lblError.setText("Llenar todos los campos");
				}
				
			} catch (SQLException e) {
				System.err.println("Error en la INSERCIÓN");

				this.view.lblErrorProveedor.setForeground(Color.red);
				this.view.lblError.setText("ERROR EN LA INSERCIÓN");
				System.err.println(e.getMessage());
			}
			
			break;
			
		case "BORRAR" :
			
			
				int filaPulsada = this.view.tabla.getSelectedRow();
				
				if(filaPulsada >= 0) {
					int identificador = (int)this.view.dtm.getValueAt(filaPulsada, 0);
					try {
						int antesExistencia = getExitencia();
						String sql = "DELETE FROM Productos WHERE id = ?";
						ps = Bd.getConexion().prepareStatement(sql);
						ps.setInt(1, identificador);
						ps.execute();
						this.view.lblError.setForeground(Color.green);
						this.view.lblError.setText("Producto Borrado");
						int despuesExistencia = getExitencia()>=antesExistencia?getExitencia()-antesExistencia:antesExistencia-getExitencia();
						
						if(despuesExistencia != 0) {
							crearReporte("SALIDA", despuesExistencia);
						}
					} catch (SQLException e) {
						System.err.println("Error al ELIMINAR");

						this.view.lblErrorProveedor.setForeground(Color.red);
						this.view.lblError.setText("ERROR AL ELIMINAR");
						System.err.println(e.getMessage());
						
					}
				
			}
			
				cargarTabla();
			
			break;
			
		case "MODIFICAR" :
				
				int filaPulsadaUpdate = this.view.tabla.getSelectedRow();
				
				if(filaPulsadaUpdate >= 0) {
					int identificador = (int)this.view.dtm.getValueAt(filaPulsadaUpdate, 0);
					try {
						int antesExistencia = getExitencia();
						String sql = " UPDATE Productos  SET Nombre  = ?, Existencia= ?,  Precio = ?, Fecha = now(), idProveedor = ?, idUsuario = ? WHERE id= ?;";
						ps = Bd.getConexion().prepareStatement(sql);
						ps.setString(1,this.view.txtNombre.getText());
						ps.setInt(2, Integer.parseInt(this.view.txtExistencia.getText()));
						ps.setFloat(3, Float.parseFloat(this.view.txtPrecio.getText()));
						ps.setInt(4, getProveedor(this.view.cmbProveedores.getSelectedItem().toString()));
						ps.setInt(5, id);
						ps.setInt(6, identificador);
						
						ps.execute();
						this.view.lblError.setForeground(Color.green);
						this.view.lblError.setText("Producto Modificado");
						int despuesExistencia = getExitencia()>=antesExistencia?getExitencia()-antesExistencia:antesExistencia-getExitencia();
						String concepto = getExitencia()>=antesExistencia?"ENTRADA":"SALIDA";
						if(despuesExistencia != 0) {
							crearReporte(concepto, despuesExistencia);
						}
						
					} catch (SQLException e) {
						System.err.println("Error en la MODICACION");

						this.view.lblErrorProveedor.setForeground(Color.red);
						this.view.lblError.setText("ERROR EN LA MODIFICACIÓN");
						
						System.err.println(e.getMessage());
					}
				
			}
				cargarTabla();
			break;
			
		case "INSERTAR PROVEEDOR" :
			
			if(!this.view.txtNombreProveedor.getText().isEmpty() || !this.view.txtNombreProveedor.getText().equals("")) {
				try {
					String sql = " INSERT INTO Proveedores (Nombre, Direccion, Fecha) VALUES (?, ?, now());";
					ps = Bd.getConexion().prepareStatement(sql);
					ps.setString(1,this.view.txtNombreProveedor.getText());
					ps.setString(2,this.view.txtDireccion.getText());
					ps.execute();
					this.view.lblErrorProveedor.setForeground(Color.green);
					this.view.lblErrorProveedor.setText("Proveedor Agregado");
				} catch (SQLException e) {
					System.err.println(e.getMessage());

					this.view.lblErrorProveedor.setForeground(Color.red);
					this.view.lblErrorProveedor.setText("ERROR AL AGREGAR PROVEEDOR");
				}
			}
			cargarTablaProveedor();
			llenarComboBox();
			break;
			
		case "BORRAR PROVEEDOR" :
			
			
				
				int filaPulsadaProoverdor = this.view.tablaProveedor.getSelectedRow();
				
				if(filaPulsadaProoverdor >= 0) {
					int identificador = (int)this.view.dtmProveedor.getValueAt(filaPulsadaProoverdor, 0);
					try {
						String sql = "DELETE FROM Proveedores WHERE id = ?";
						ps = Bd.getConexion().prepareStatement(sql);
						ps.setInt(1, identificador);
						ps.execute();
						this.view.lblErrorProveedor.setForeground(Color.green);
						this.view.lblErrorProveedor.setText("Proveedor Borrado");
					} catch (SQLException e) {
						System.err.println("Error al ELIMINAR PROVEEDOR");
						this.view.lblErrorProveedor.setForeground(Color.red);
						this.view.lblErrorProveedor.setText("ERROR AL ELIMINAR PROVEEDOR");
						
					}
				
			}
				cargarTablaProveedor();
				llenarComboBox();
			break;	
		case "MODIFICAR PROVEEDOR" :
			
			
				int filaPulsadaProveedor = this.view.tablaProveedor.getSelectedRow();
				
				if(filaPulsadaProveedor >= 0) {
					
					int identificador = (int)this.view.dtmProveedor.getValueAt(filaPulsadaProveedor, 0);
					try {
						String sql = " UPDATE Proveedores  SET Nombre  = ?, Direccion= ?, Fecha = now() WHERE id= ?;";
						ps = Bd.getConexion().prepareStatement(sql);
						ps.setString(1,this.view.txtNombreProveedor.getText());
						ps.setString(2, this.view.txtDireccion.getText());
						ps.setInt(3, identificador);
						ps.execute();
						this.view.lblErrorProveedor.setForeground(Color.green);
						this.view.lblErrorProveedor.setText("Proveedor Modificado");
					} catch (SQLException e) {
						System.err.println("Error en la MODICACION PROVEEDOR");
						this.view.lblErrorProveedor.setForeground(Color.red);
						this.view.lblErrorProveedor.setText("ERROR AL MODIFICAR PROVEEDOR");
						System.err.println(e.getMessage());
					}
				
			}
				cargarTablaProveedor();
				llenarComboBox();
			break;	
		case "INSERTAR USUARIOS" :
			
			if(this.view.cmbPermisos.getSelectedIndex() != 0 && (!this.view.txtNombreUsuario.getText().isEmpty() || !this.view.txtNombreUsuario.getText().equals(""))) {
				try {
					String sql = " INSERT INTO Usuarios (Nombre, Apellido, Password, Permisos, Fecha, Usuario) VALUES (?, ?, ?, ?, now(), ?);";
					ps = Bd.getConexion().prepareStatement(sql);
					ps.setString(1,this.view.txtNombreUsuario.getText());
					ps.setString(2,this.view.txtApellido.getText());
					ps.setString(3,this.view.txtContrasena.getText());
					ps.setInt(4, this.view.cmbPermisos.getSelectedItem().equals("Administrador")?1:0);
					ps.setString(5, this.view.txtNombreUsuario.getText().toUpperCase().charAt(0)+this.view.txtApellido.getText().toLowerCase());
					ps.execute();
					this.view.lblErrorUsuario.setForeground(Color.green);
					this.view.lblErrorUsuario.setText("Usuario Agregado");
					cargarTablaUsuarios();
				} catch (SQLException e) {
					this.view.lblErrorUsuario.setForeground(Color.red);
					this.view.lblErrorUsuario.setText("ERROR AL AGREGAR USUARIO");
					System.err.println(e.getMessage());
				}
			}
			
			break;		
		
	 default:
             System.err.println("Comando no definido");
         break;
	 case "BORRAR USUARIOS" :
			
			
			int filaPulsadaUsuario = this.view.tablaUsuario.getSelectedRow();
			
			if(filaPulsadaUsuario >= 0) {
				int identificador = (int)this.view.dtmUsuario.getValueAt(filaPulsadaUsuario, 0);
				try {
					String sql = "DELETE FROM Usuarios WHERE id = ?";
					ps = Bd.getConexion().prepareStatement(sql);
					ps.setInt(1, identificador);
					ps.execute();
					this.view.lblErrorUsuario.setForeground(Color.green);
					this.view.lblErrorUsuario.setText("Usuario Borrado");
				} catch (SQLException e) {
					this.view.lblErrorUsuario.setForeground(Color.red);
					this.view.lblErrorUsuario.setText("ERROR A BORRAR USUARIO");
					System.err.println(e.getMessage());
					
				}
			
		}
			cargarTablaUsuarios();
		break;	
		
	case "MODIFICAR USUARIOS" :
		
		
			int filaPulsadaUpdUsuario= this.view.tablaUsuario.getSelectedRow();
			
			if(filaPulsadaUpdUsuario >= 0) {
				int identificador = (int)this.view.dtmUsuario.getValueAt(filaPulsadaUpdUsuario, 0);
				try {
					String sql = " UPDATE Usuarios  SET Nombre  = ?, Apellido  = ?, Password  = ?, Permisos= ?, Fecha = now(), Usuario = ? WHERE id= ?;";
					ps = Bd.getConexion().prepareStatement(sql);
					ps.setString(1,this.view.txtNombreUsuario.getText());
					ps.setString(2,this.view.txtApellido.getText());
					ps.setString(3,this.view.txtContrasena.getText());
					ps.setInt(4, this.view.cmbPermisos.getSelectedItem().equals("Administrador")?1:0);
					ps.setString(5, this.view.txtNombreUsuario.getText().toUpperCase().charAt(0)+this.view.txtApellido.getText().toLowerCase());
					ps.setInt(6, identificador);
					ps.execute();
					this.view.lblErrorUsuario.setForeground(Color.green);
					this.view.lblErrorUsuario.setText("Usuario Modicado");
				} catch (SQLException e) {
					this.view.lblErrorUsuario.setForeground(Color.red);
					this.view.lblErrorUsuario.setText("ERROR AL MODIFICAR USUARIO");
					System.err.println(e.getMessage());
				}
			
		}
			cargarTablaUsuarios();
		break;	
	case "SALIR" :
		Login login = new Login();
		Login.main(null);
		login.setVisible(true);
		this.view.dispose();
		
			
		break;
		
		}
		
		cargarTablaReporte();
		llenarComboBox();
		limpia();
		
	}
	
	private void limpia() {
		this.view.txtNombre.setText("");
		this.view.txtExistencia.setText("");
		this.view.txtPrecio.setText("");
		this.view.txtNombreProveedor.setText("");
		this.view.txtDireccion.setText("");
		this.view.txtNombreUsuario.setText("");
		this.view.txtApellido.setText("");
		this.view.txtContrasena.setText("");
		this.view.cmbPermisos.setSelectedIndex(0);
		
		
	}
	
	public void cargarTabla(){
		
		PreparedStatement ps;
		
		ResultSet rs;
		
		Vector<Object> fila;
		
		for(int i = this.view.dtm.getRowCount(); i > 0; i--) {
			this.view.dtm.removeRow(i-1);
		}
		
		try {
			String sql = "SELECT pr.id, pr.Nombre, pv.Nombre AS nombreProveedor, Existencia, Precio, pr.Fecha, us.Nombre AS nombreUsuario, us.Apellido FROM Productos pr LEFT JOIN Proveedores pv ON pr.idProveedor = pv.id LEFT JOIN Usuarios us ON pr.idUsuario=us.id ORDER BY pr.Fecha";
			ps = Bd.getConexion().prepareStatement(sql);
			rs  = ps.executeQuery();
			while(rs.next()) {
				fila = new Vector<Object>();
				fila.add(rs.getInt("id"));
				fila.add(rs.getString("Nombre"));
				fila.add(rs.getString("Existencia"));
				fila.add(rs.getString("Precio"));
				fila.add(rs.getDate("Fecha"));
				fila.add(rs.getString("nombreProveedor"));
				fila.add(rs.getString("nombreUsuario")!=null?rs.getString("nombreUsuario")+ " " + rs.getString("Apellido"):"");
				
				this.view.dtm.addRow(fila);
			}
		} catch (SQLException e) {
			 System.err.println("Error al CARGAR DATOS");
			 this.view.lblError.setText("ERROR AL CARGAR TABLA");
		}
	}
	
	public void cargarTablaProveedor(){
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
	
	public void cargarTablaUsuarios(){
		PreparedStatement ps;
		
		ResultSet rs;
		
		Vector<Object> fila;
		
		for(int i = this.view.dtmUsuario.getRowCount(); i > 0; i--) {
			this.view.dtmUsuario.removeRow(i-1);
		}
		
		try {
			String sql = "SELECT id, Nombre, Apellido, Usuario, Permisos, Fecha FROM Usuarios";
			ps = Bd.getConexion().prepareStatement(sql);
			rs  = ps.executeQuery();
			while(rs.next()) {
				fila = new Vector<Object>();
				fila.add(rs.getInt("id"));
				fila.add(rs.getString("Nombre"));
				fila.add(rs.getString("Apellido"));
				fila.add(rs.getString("Usuario"));
				fila.add(rs.getDate("Fecha"));
				fila.add(rs.getInt("Permisos")==1?"Administrador":"Capturista");
				
				this.view.dtmUsuario.addRow(fila);
			}
		} catch (SQLException e) {
			 System.err.println("Error al CARGAR DATOS");
		}
	}
	
	public void cargarTablaReporte(){
		PreparedStatement ps;
		
		ResultSet rs;
		
		Vector<Object> fila;
		
		for(int i = this.view.dtmReporte.getRowCount(); i > 0; i--) {
			this.view.dtmReporte.removeRow(i-1);
		}
		
		try {
			String sql = "SELECT *  FROM Reporte";
			ps = Bd.getConexion().prepareStatement(sql);
			rs  = ps.executeQuery();
			while(rs.next()) {
				fila = new Vector<Object>();
				fila.add(rs.getInt("id"));
				fila.add(rs.getString("Fecha"));
				fila.add(rs.getString("Concepto"));
				fila.add(rs.getInt("Entradas"));
				fila.add(rs.getInt("Salidas"));
				fila.add(rs.getInt("Existencia"));
				fila.add(rs.getFloat("Precio"));
				fila.add(rs.getFloat("EntradasPrecio"));
				fila.add(rs.getFloat("SalidaPrecio"));
				fila.add(rs.getFloat("precioTotal"));
				
				
				this.view.dtmReporte.addRow(fila);
			}
		} catch (SQLException e) {
			 System.err.println("Error al CARGAR DATOS");
		}
	}
	
	public void llenarComboBox() {
		this.view.cmbProveedores.removeAllItems();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			String sql = "SELECT id, Nombre, Direccion, Fecha FROM Proveedores";
			ps = Bd.getConexion().prepareStatement(sql);
			rs  = ps.executeQuery();

			this.view.cmbProveedores.addItem("Sleccione un proveedor");
			while(rs.next()) {
				this.view.cmbProveedores.addItem(rs.getString("Nombre"));
			}
		} catch (SQLException e) {
			 System.err.println("Error al CARGAR DATOS");
			 this.view.lblError.setText("ERROR AL CARGAR DATOS");
		}
	}
	
	private Integer getProveedor(String nombre) {
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			String sql = "SELECT id FROM Proveedores where Nombre = ?";
			ps = Bd.getConexion().prepareStatement(sql);
			ps.setString(1, nombre);
			rs  = ps.executeQuery();
			
			while(rs.next()) {
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			 System.err.println("Error al CARGAR DATOS");
			 this.view.lblError.setText("ERROR AL CARGAR PROVEEDORES");
		}
		
		return 0;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		PreparedStatement ps;
		ResultSet rs;
		
		int filaPulsada = this.view.tabla.getSelectedRow();
		int filaPulsadaProveedor = this.view.tablaProveedor.getSelectedRow();
		int filaPulsadaUsuario = this.view.tablaUsuario.getSelectedRow();
		if(filaPulsada >= 0) {
			int identificador = (int)this.view.dtm.getValueAt(filaPulsada, 0);
		
			try {
				String sql = "SELECT pr.id, pr.Nombre, pv.Nombre AS nombreProveedor, Existencia, Precio, pr.Fecha FROM Productos pr LEFT JOIN Proveedores pv ON pr.idProveedor = pv.id WHERE pr.id = ?";
				ps = Bd.getConexion().prepareStatement(sql);
				ps.setInt(1, identificador);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					this.view.txtNombre.setText(rs.getString("Nombre"));
					this.view.cmbProveedores.setSelectedItem(rs.getString("nombreProveedor"));
					this.view.txtExistencia.setText(rs.getString("Existencia"));
					this.view.txtPrecio.setText(rs.getString("Precio"));
					
				}
			} catch (SQLException e) {
				System.err.println("Error al CARGAR UN PRODUCTO");
				System.out.println(e.getMessage());
			}
		} else if(filaPulsadaProveedor >= 0) {
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
		} else if(filaPulsadaUsuario >= 0) {
			int identificador = (int)this.view.dtmUsuario.getValueAt(filaPulsadaUsuario, 0);
			try {
				String sql = "SELECT  * FROM Usuarios WHERE id = ?";
				ps = Bd.getConexion().prepareStatement(sql);
				ps.setInt(1, identificador);
				rs = ps.executeQuery();
				
				if(rs.next()) {
					this.view.txtNombreUsuario.setText(rs.getString("Nombre"));
					this.view.txtApellido.setText(rs.getString("Apellido"));
					this.view.txtContrasena.setText(rs.getString("Password"));
					this.view.cmbPermisos.setSelectedIndex(rs.getInt("Permisos")==0?2:(rs.getInt("Permisos")==1?1:0));
				}
			} catch (SQLException e) {
				System.err.println("Error al CARGAR UN PROVEEDOR");
				System.out.println(e.getMessage());
			}
		}
		
	}
	
	public void isAdministrador() {
		this.view.lblUser.setText(nombre+" "+apellido);
		if(permisos==1) {
			this.view.panelDePestanas.addTab("Proveedores", null, this.view.panel2, null);
			this.view.panelDePestanas.addTab("Usuarios", null, this.view.panel3, null);
			this.view.panelDePestanas.addTab("Reporte", null, this.view.panel4, null);
			
		} else if(permisos==0) {
			this.view.btnDel.setVisible(false);
			this.view.btnUpd.setVisible(false);
		}
		
	}
	
	public Integer getExitencia() throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT SUM(Existencia) AS existenciaSuma FROM Productos";
		ps = Bd.getConexion().prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getInt("existenciaSuma");
		}
		return 0;
	}
	
	public Float getPrecioPorUnidad() throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT precio FROM Productos ORDER BY id DESC LIMIT 1";
		ps = Bd.getConexion().prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getFloat("precio");
		}
		return (float) 0.0;
	}
	
	public Float getPrecioTotal() throws SQLException {
		PreparedStatement ps;
		ResultSet rs;
		String sql = "SELECT precioTotal FROM Reporte ORDER BY id DESC LIMIT 1";
		ps = Bd.getConexion().prepareStatement(sql);
		rs = ps.executeQuery();
		if(rs.next()) {
			return rs.getFloat("precioTotal");
		}
		return (float) 0.0;
	}
	
	public void crearReporte(String concepto, Integer cantidad) {
		PreparedStatement ps;
		ResultSet rs;
		String sql = "INSERT INTO Reporte (Fecha, Concepto, Entradas, Salidas, Existencia, Precio, EntradasPrecio, SalidaPrecio, precioTotal) VALUES (now(), ?, ?, ?, ?, ?, ?, ?,?)";
		
		try {
			ps = Bd.getConexion().prepareStatement(sql);
			ps.setString(1, concepto);
			ps.setInt(2, concepto == "ENTRADA" ? cantidad : 0);
			ps.setInt(3, concepto == "SALIDA" ? cantidad : 0);
			ps.setInt(4, getExitencia());
			ps.setFloat(5, getPrecioPorUnidad());
			ps.setFloat(6, concepto == "ENTRADA" ? cantidad * getPrecioPorUnidad() : 0);
			ps.setFloat(7, concepto == "SALIDA" ? cantidad * getPrecioPorUnidad() : 0);
			ps.setFloat(8, concepto == "ENTRADA" ? getPrecioTotal() + (cantidad * getPrecioPorUnidad()) : getPrecioTotal() - (cantidad * getPrecioPorUnidad()));
			ps.execute();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
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
