package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.Controller;
import Inventrio.Bd;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;
import java.awt.Color;

public class View extends JFrame {

	private JPanel contentPane;
	
    private JLabel lblNombre;
    private JLabel lblExistencia;
    private JLabel lblPrecio;
    public JLabel lblError;
 
    
    public JTextField txtNombre;
    public JTextField txtExistencia;
    public JTextField txtPrecio;
    public JComboBox<String> cmbProveedores;
 
   
    public JButton btnAdd;
    public JButton btnDel;
    public JButton btnUpd;
    
    private JLabel lblNombreProveedor;
    private JLabel lblDireccion;
 
   
    public JTextField txtNombreProveedor;
    public JTextField txtDireccion;
 
    private JLabel lblNombreUsuario;
    private JLabel lblContrasena;
    private JLabel lblPermisos;
 
   
    public JTextField txtNombreUsuario;
    public JTextField txtContrasena;
    public JComboBox<String> cmbPermisos;
    
    public JButton btnAddProveedor;
    public JButton btnDelProveedor;
    public JButton btnUpdProveedor;
    
    public JButton btnAddUsuario;
    public JButton btnDelUsuario;
    public JButton btnUpdUsuario;
 
   
    public JScrollPane scroll; 
    public Object[][] datos; 
    public String[] cabecera;  
    public DefaultTableModel dtm;
    public JTable tabla;
    
    public JScrollPane scrollProveedor; 
    public Object[][] datosProveedor; 
    public String[] cabeceraProveedor;
    public DefaultTableModel dtmProveedor;
    public JTable tablaProveedor; 
    
    public JScrollPane scrollUsuario; 
    public Object[][] datosUsuario; 
    public String[] cabeceraUsuario;
    public DefaultTableModel dtmUsuario;
    public JTable tablaUsuario; 
    
    public JPanel panel1 = new JPanel();
    public JPanel panel2 = new JPanel();
    public JPanel panel3 = new JPanel();
    private JLabel lblApellido;
    public JTextField txtApellido;
    boolean isVisble = false;
    public JTabbedPane panelDePestanas;
	/**
	 * Launch the application.
	 */
	

	
	public View() {
		
		  setTitle("Inventario");
		 
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		  setBounds(100, 100, 1045, 595);
		  
		 
		  contentPane = new JPanel();
		  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		  setContentPane(contentPane);
		 
		  contentPane.setLayout(null);
		  
		
		  panelDePestanas = new JTabbedPane(JTabbedPane.TOP);
		  
		  panelDePestanas.setBounds(10, 11, 1035, 546);
		  contentPane.add(panelDePestanas);
		  
		  panel1 = new JPanel();
		  panelDePestanas.addTab("Productos", null, panel1, null);
		
		  SpringLayout sp1 = new SpringLayout();
		  panel1.setLayout(sp1);
		  
		
		  lblNombre = new JLabel("Nombre:"); 
		  panel1.add(lblNombre);      
		  panel1.add(lblNombre);
		  
		  lblExistencia = new JLabel("Existencia:");
		  sp1.putConstraint(SpringLayout.WEST, lblNombre, 0, SpringLayout.WEST, lblExistencia);
	        panel1.add(lblExistencia);
	        sp1.putConstraint(SpringLayout.NORTH, lblExistencia, 50,
	                        SpringLayout.NORTH, panel1);
	        sp1.putConstraint(SpringLayout.WEST, lblExistencia,  10,
	                        SpringLayout.WEST, panel1);
	       
	        lblPrecio = new JLabel("Precio:");
	        panel1.add(lblPrecio);
	        sp1.putConstraint(SpringLayout.NORTH, lblPrecio, 90,
	                        SpringLayout.NORTH, panel1);
	        sp1.putConstraint(SpringLayout.WEST, lblPrecio,  10,
	                        SpringLayout.WEST, panel1);
	      
	        txtNombre       = new JTextField();
	        sp1.putConstraint(SpringLayout.NORTH, lblNombre, 2,
	                        SpringLayout.NORTH, txtNombre);
	        panel1.add(txtNombre);
	        sp1.putConstraint(SpringLayout.NORTH, txtNombre, 10,
	                        SpringLayout.NORTH, panel1);
	        sp1.putConstraint(SpringLayout.WEST, txtNombre, 100,
	                        SpringLayout.WEST, panel1);
	        sp1.putConstraint(SpringLayout.EAST, txtNombre, 300,
	                        SpringLayout.WEST, panel1);
	     
	        txtExistencia = new JTextField();
	        panel1.add(txtExistencia);    //añadir al panel1
	        sp1.putConstraint(SpringLayout.NORTH, txtExistencia, 50,
	                        SpringLayout.NORTH, panel1);
	        sp1.putConstraint(SpringLayout.WEST, txtExistencia, 100,
	                        SpringLayout.WEST, panel1);
	        sp1.putConstraint(SpringLayout.EAST, txtExistencia, 300,
	                        SpringLayout.WEST, panel1);
	       
	        txtPrecio      = new JTextField();
	        panel1.add(txtPrecio);
	        sp1.putConstraint(SpringLayout.NORTH, txtPrecio, 90, SpringLayout.NORTH, panel1);
	        sp1.putConstraint(SpringLayout.WEST, txtPrecio, 100, SpringLayout.WEST, panel1);
	        sp1.putConstraint(SpringLayout.EAST, txtPrecio, 300, SpringLayout.WEST, panel1);
	        
	        cmbProveedores = new JComboBox<>();
	        sp1.putConstraint(SpringLayout.NORTH, cmbProveedores, -5, SpringLayout.NORTH, lblExistencia);
	        sp1.putConstraint(SpringLayout.WEST, cmbProveedores, 111, SpringLayout.EAST, txtExistencia);
	        sp1.putConstraint(SpringLayout.EAST, cmbProveedores, 611, SpringLayout.WEST, panel1);
	        panel1.add(cmbProveedores);
	        
	        scroll      = new JScrollPane();
	        cabecera    = new String[] {"ID","NOMBRE","EXISTENCIA", "PRECIO", "FECHA", "PROVEEDOR", "USUARIO"};
	        dtm         = new DefaultTableModel(datos,cabecera);
	        tabla       = new JTable(dtm);
	        scroll.setViewportView(tabla);
	       
	        panel1.add(scroll); 
	        sp1.putConstraint(SpringLayout.NORTH, scroll, 120,
	                        SpringLayout.NORTH, panel1);
	        sp1.putConstraint(SpringLayout.WEST, scroll,   10,
	                        SpringLayout.WEST, panel1);
	        sp1.putConstraint(SpringLayout.EAST, scroll,  -10,
	                        SpringLayout.EAST, panel1);
	        sp1.putConstraint(SpringLayout.SOUTH, scroll, -50,
	                        SpringLayout.SOUTH, panel1);
	      
	        btnAdd          = new JButton("Añadir");
	        panel1.add(btnAdd);
	        sp1.putConstraint(SpringLayout.SOUTH, btnAdd, -10,
	                        SpringLayout.SOUTH, panel1);//colocarlo
	        sp1.putConstraint(SpringLayout.WEST, btnAdd,   60,
	                        SpringLayout.WEST, panel1);
	       
	        btnDel          = new JButton("Borrar");
	        panel1.add(btnDel);
	        sp1.putConstraint(SpringLayout.SOUTH, btnDel, -10,
	                        SpringLayout.SOUTH, panel1);
	        sp1.putConstraint(SpringLayout.WEST, btnDel,  190,
	                        SpringLayout.WEST, panel1);
	       
	        btnUpd          = new JButton("Editar");
	        panel1.add(btnUpd);
	        sp1.putConstraint(SpringLayout.SOUTH, btnUpd, -10,
	                        SpringLayout.SOUTH, panel1);
	        sp1.putConstraint(SpringLayout.WEST, btnUpd,  310,
	                        SpringLayout.WEST, panel1);
	        
	        JLabel lblNewLabel = new JLabel("Proveedor");
	        sp1.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, lblExistencia);
	        sp1.putConstraint(SpringLayout.EAST, lblNewLabel, -18, SpringLayout.WEST, cmbProveedores);
	        panel1.add(lblNewLabel);
	        
	        lblError = new JLabel("");
	        lblError.setForeground(Color.RED);
	        sp1.putConstraint(SpringLayout.NORTH, lblError, 0, SpringLayout.NORTH, lblPrecio);
	        sp1.putConstraint(SpringLayout.WEST, lblError, 0, SpringLayout.WEST, lblNewLabel);
	        panel1.add(lblError);
	        
	     
		  
		 ///Panel 2
		  panel2 = new JPanel();
//		  panelDePestanas.addTab("Proveedores", null, panel2, null);
		  SpringLayout sp2 = new SpringLayout();
		  panel2.setLayout(sp2);
		  
		  lblNombreProveedor = new JLabel("Nombre:");
		  panel2.add(lblNombreProveedor);
		    sp2.putConstraint(SpringLayout.WEST, lblNombreProveedor, 10, SpringLayout.WEST, panel2);
	      
	        sp2.putConstraint(SpringLayout.NORTH, lblNombreProveedor, 10,
	                        SpringLayout.NORTH, panel2);
	       
	        lblDireccion = new JLabel("Dirección:");
	        panel2.add(lblDireccion);
	        sp2.putConstraint(SpringLayout.NORTH, lblDireccion, 50,
	                        SpringLayout.NORTH, panel2);
	        sp2.putConstraint(SpringLayout.WEST, lblDireccion,  10,
	                        SpringLayout.WEST, panel2);
	      
	        txtNombreProveedor = new JTextField();
	        sp2.putConstraint(SpringLayout.NORTH, panel2, 2,
	                        SpringLayout.NORTH, txtNombreProveedor);
	        panel2.add(txtNombreProveedor);
	        sp2.putConstraint(SpringLayout.NORTH, txtNombreProveedor, 10,
	                        SpringLayout.NORTH, panel2);
	        sp2.putConstraint(SpringLayout.WEST, txtNombreProveedor, 100,
	                        SpringLayout.WEST, panel2);
	        sp2.putConstraint(SpringLayout.EAST, txtNombreProveedor, 300,
	                        SpringLayout.WEST, panel2);
	     
	        txtDireccion = new JTextField();
	        panel2.add(txtDireccion);   
	        sp2.putConstraint(SpringLayout.NORTH, txtDireccion, 50,
	                        SpringLayout.NORTH, panel2);
	        sp2.putConstraint(SpringLayout.WEST, txtDireccion, 100,
	                        SpringLayout.WEST, panel2);
	        sp2.putConstraint(SpringLayout.EAST, txtDireccion, 300,
	                        SpringLayout.WEST, panel2);
	       
	       
	      
	        scrollProveedor      = new JScrollPane();
	        cabeceraProveedor    = new String[] {"ID","NOMBRE","DIRECCION"};
	        dtmProveedor         = new DefaultTableModel(datosProveedor,cabeceraProveedor);
	        tablaProveedor      = new JTable(dtmProveedor);
	        scrollProveedor.setViewportView(tablaProveedor);
	       
	        panel2.add(scrollProveedor); 
	        sp2.putConstraint(SpringLayout.NORTH, scrollProveedor, 120,
	                        SpringLayout.NORTH, panel2);
	        sp2.putConstraint(SpringLayout.WEST, scrollProveedor,   10,
	                        SpringLayout.WEST, panel2);
	        sp2.putConstraint(SpringLayout.EAST, scrollProveedor,  -10,
	                        SpringLayout.EAST, panel2);
	        sp2.putConstraint(SpringLayout.SOUTH, scrollProveedor, -50,
	                        SpringLayout.SOUTH, panel2);
	      
	        btnAddProveedor          = new JButton("Añadir");
	        panel2.add(btnAddProveedor);
	        sp2.putConstraint(SpringLayout.SOUTH, btnAddProveedor, -10,
	                        SpringLayout.SOUTH, panel2);//colocarlo
	        sp2.putConstraint(SpringLayout.WEST, btnAddProveedor,   60,
	                        SpringLayout.WEST, panel2);
	       
	        btnDelProveedor = new JButton("Borrar");
	        panel2.add(btnDelProveedor);
	        sp2.putConstraint(SpringLayout.SOUTH, btnDelProveedor, -10,
	                        SpringLayout.SOUTH, panel2);
	        sp2.putConstraint(SpringLayout.WEST, btnDelProveedor,  190,
	                        SpringLayout.WEST, panel2);
	       
	        btnUpdProveedor = new JButton("Editar");
	        panel2.add(btnUpdProveedor);
	        sp2.putConstraint(SpringLayout.SOUTH, btnUpdProveedor, -10,
	                        SpringLayout.SOUTH, panel2);
	        sp2.putConstraint(SpringLayout.WEST, btnUpdProveedor,  310,
	                        SpringLayout.WEST, panel2);
	        
	        
	      ///Panel 3
			  panel3 = new JPanel();
//			  panelDePestanas.addTab("Usuarios", null, panel3, null);
			  SpringLayout sp3 = new SpringLayout();
			  panel3.setLayout(sp3);
			  
			  lblNombreUsuario = new JLabel("Nombre:");
			  panel3.add(lblNombreUsuario);
			    sp3.putConstraint(SpringLayout.WEST, lblNombreUsuario, 10, SpringLayout.WEST, panel3);
		      
		        sp3.putConstraint(SpringLayout.NORTH, lblNombreUsuario, 10,
		                        SpringLayout.NORTH, panel3);
		       
		        lblContrasena = new JLabel("Contraseña:");
		        panel3.add(lblContrasena);
		        sp3.putConstraint(SpringLayout.NORTH, lblContrasena, 50,
		                        SpringLayout.NORTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, lblContrasena,  10,
		                        SpringLayout.WEST, panel3);
		        
		        lblPermisos = new JLabel("Permisos:");
		        panel3.add(lblPermisos);
		        sp3.putConstraint(SpringLayout.NORTH, lblPermisos, 90,
		                        SpringLayout.NORTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, lblPermisos,  10,
		                        SpringLayout.WEST, panel3);
		      
		        txtNombreUsuario = new JTextField();
		        sp3.putConstraint(SpringLayout.NORTH, panel3, 2,
		                        SpringLayout.NORTH, txtNombreUsuario);
		        panel3.add(txtNombreUsuario);
		        sp3.putConstraint(SpringLayout.NORTH, txtNombreUsuario, 10,
		                        SpringLayout.NORTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, txtNombreUsuario, 100,
		                        SpringLayout.WEST, panel3);
		        sp3.putConstraint(SpringLayout.EAST, txtNombreUsuario, 300,
		                        SpringLayout.WEST, panel3);
		     
		        txtContrasena = new JTextField();
		        panel3.add(txtContrasena);    //añadir al panel1
		        sp3.putConstraint(SpringLayout.NORTH, txtContrasena, 50,
		                        SpringLayout.NORTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, txtContrasena, 100,
		                        SpringLayout.WEST, panel3);
		        sp3.putConstraint(SpringLayout.EAST, txtContrasena, 300,
		                        SpringLayout.WEST, panel3);
		        
		        cmbPermisos = new JComboBox<>();
		        cmbPermisos.addItem("Selecione permisos");
		        cmbPermisos.addItem("Administrador");
		        cmbPermisos.addItem("Capturista");
		        
		        panel3.add(cmbPermisos);
		        sp3.putConstraint(SpringLayout.NORTH, cmbPermisos, 90, SpringLayout.NORTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, cmbPermisos, 100, SpringLayout.WEST, panel3);
		        sp3.putConstraint(SpringLayout.EAST, cmbPermisos, 300, SpringLayout.WEST, panel3);
		       
		       
		      
		        scrollUsuario     = new JScrollPane();
		        cabeceraUsuario    = new String[] {"ID","NOMBRE","APELLIDO", "USUARIO",  "FECHA MODIFICACION", "PERMISOS" };
		        dtmUsuario        = new DefaultTableModel(datosUsuario,cabeceraUsuario);
		        tablaUsuario   = new JTable(dtmUsuario);
		        scrollUsuario.setViewportView(tablaUsuario);
		        //y ahora se coloca el scrollpane...
		        panel3.add(scrollUsuario); //añadir al panel1
		        sp3.putConstraint(SpringLayout.NORTH, scrollUsuario, 120,
		                        SpringLayout.NORTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, scrollUsuario,   10,
		                        SpringLayout.WEST, panel3);
		        sp3.putConstraint(SpringLayout.EAST, scrollUsuario,  -10,
		                        SpringLayout.EAST, panel3);
		        sp3.putConstraint(SpringLayout.SOUTH, scrollUsuario, -50,
		                        SpringLayout.SOUTH, panel3);
		      
		        btnAddUsuario          = new JButton("Añadir");
		        panel3.add(btnAddUsuario);
		        sp3.putConstraint(SpringLayout.SOUTH, btnAddUsuario, -10,
		                        SpringLayout.SOUTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, btnAddUsuario,   60,
		                        SpringLayout.WEST, panel3);
		       
		        btnDelUsuario = new JButton("Borrar");
		        panel3.add(btnDelUsuario);
		        sp3.putConstraint(SpringLayout.SOUTH, btnDelUsuario, -10,
		                        SpringLayout.SOUTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, btnDelUsuario,  190,
		                        SpringLayout.WEST, panel3);
		       
		        btnUpdUsuario = new JButton("Editar");
		        panel3.add(btnUpdUsuario);
		        sp3.putConstraint(SpringLayout.SOUTH, btnUpdUsuario, -10,
		                        SpringLayout.SOUTH, panel3);
		        sp3.putConstraint(SpringLayout.WEST, btnUpdUsuario,  310,
		                        SpringLayout.WEST, panel3);
		        
		        lblApellido = new JLabel("Apellido:");
		        sp3.putConstraint(SpringLayout.WEST, lblApellido, 139, SpringLayout.EAST, txtNombreUsuario);
		        sp3.putConstraint(SpringLayout.SOUTH, lblApellido, 0, SpringLayout.SOUTH, lblNombreUsuario);
		        panel3.add(lblApellido);
		        
		        txtApellido = new JTextField();
		        sp3.putConstraint(SpringLayout.NORTH, txtApellido, 0, SpringLayout.NORTH, txtNombreUsuario);
		        sp3.putConstraint(SpringLayout.WEST, txtApellido, 12, SpringLayout.EAST, lblApellido);
		        sp3.putConstraint(SpringLayout.EAST, txtApellido, 222, SpringLayout.EAST, lblApellido);
		        panel3.add(txtApellido);
		  
		
		
		 
	}
	
	public void conectaControlador(Controller c){
		 
        btnAdd.addActionListener(c);
        btnAdd.setActionCommand("INSERTAR");
 
        btnDel.addActionListener(c);
        btnDel.setActionCommand("BORRAR");
 
        btnUpd.addActionListener(c);
        btnUpd.setActionCommand("MODIFICAR");
        
        btnAddProveedor.addActionListener(c);
        btnAddProveedor.setActionCommand("INSERTAR PROVEEDOR");
 
        btnDelProveedor.addActionListener(c);
        btnDelProveedor.setActionCommand("BORRAR PROVEEDOR");
 
        btnUpdProveedor.addActionListener(c);
        btnUpdProveedor.setActionCommand("MODIFICAR PROVEEDOR");
        
        btnAddUsuario.addActionListener(c);
        btnAddUsuario.setActionCommand("INSERTAR USUARIOS");
 
        btnDelUsuario.addActionListener(c);
        btnDelUsuario.setActionCommand("BORRAR USUARIOS");
 
        btnUpdUsuario.addActionListener(c);
        btnUpdUsuario.setActionCommand("MODIFICAR USUARIOS");
 
        tabla.addMouseListener(c);
        
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tablaProveedor.addMouseListener(c);
        
        tablaProveedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tablaUsuario.addMouseListener(c);
        
        tablaUsuario.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
