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
import Inventario.Proveedores;
import Inventrio.Bd;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JMenuBar;

public class View extends JFrame {

	private JPanel contentPane;
	//DEFINICIÓN DE LAS ETIQUETAS
    private JLabel lblNombre;
    private JLabel lblExistencia;
    private JLabel lblPrecio;
 
    //DEFINICIÓN DE LOS CUADROS DE TEXTO
    public JTextField txtNombre;
    public JTextField txtExistencia;
    public JTextField txtPrecio;
    public JComboBox<Proveedores> cmbProveedores;
 
    //DEFINICIÓN DE LOS BOTONES
    public JButton btnAdd;
    public JButton btnDel;
    public JButton btnUpd;
    
    private JLabel lblNombreProveedor;
    private JLabel lblDireccion;
 
    //DEFINICIÓN DE LOS CUADROS DE TEXTO
    public JTextField txtNombreProveedor;
    public JTextField txtDireccion;
 
    //DEFINICIÓN DE LOS BOTONESs
    
    public JButton btnAddProveedor;
    public JButton btnDelProveedor;
    public JButton btnUpdProveedor;
 
    //DEFINICIÓN DE LOS OBJETOS PARA LA TABLA
    public JScrollPane scroll; //Panel de scroll que contiene la tabla
    public Object[][] datos; //Cuerpo de la tabla
    public String[] cabecera;    //Cabecera de la tabla
    public DefaultTableModel dtm;//Unión de la cabecera y la tabla
    public JTable tabla; //Tabla propiamente dicha
    
    public JScrollPane scrollProveedor; //Panel de scroll que contiene la tabla
    public Object[][] datosProveedor; //Cuerpo de la tabla
    public String[] cabeceraProveedor;    //Cabecera de la tabla
    public DefaultTableModel dtmProveedor;//Unión de la cabecera y la tabla
    public JTable tablaProveedor; //Tabla propiamente dicha
    
    public JPanel panel1 = new JPanel();
    public JPanel panel2 = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 try {
		     // creando el frame y lo muestra
		    View frame = new View();
		 	new Bd("Inventario");
			
			Controller controlador = new Controller(frame);
			
			frame.conectaControlador(controlador);
			controlador.llenarComboBox();
		    frame.setVisible(true);
		    } catch (Exception e) {
		     e.printStackTrace();
		    }
	}

	
	public View() {
		
		  setTitle("Inventario");
		 
		  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		  setBounds(100, 100, 1045, 595);
		  
		 
		  contentPane = new JPanel();
		  contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		  setContentPane(contentPane);
		 
		  contentPane.setLayout(null);
		  
		
		  JTabbedPane panelDePestanas = new JTabbedPane(JTabbedPane.TOP);
		  
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
	        cabecera    = new String[] {"ID","NOMBRE","EXISTENCIA", "PRECIO", "Fecha"};
	        dtm         = new DefaultTableModel(datos,cabecera);
	        tabla       = new JTable(dtm);
	        scroll.setViewportView(tabla);
	        //y ahora se coloca el scrollpane...
	        panel1.add(scroll); //añadir al panel1
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
	        
	        JLabel lblNewLabel = new JLabel("New label");
	        sp1.putConstraint(SpringLayout.NORTH, lblNewLabel, 0, SpringLayout.NORTH, lblExistencia);
	        sp1.putConstraint(SpringLayout.EAST, lblNewLabel, -18, SpringLayout.WEST, cmbProveedores);
	        panel1.add(lblNewLabel);
		  
		 ///Panel 2
		  panel2 = new JPanel();
		  panelDePestanas.addTab("Panel 2", null, panel2, null);
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
	        panel2.add(txtDireccion);    //añadir al panel1
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
	        //y ahora se coloca el scrollpane...
	        panel2.add(scrollProveedor); //añadir al panel1
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
 
        tabla.addMouseListener(c);
        
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tablaProveedor.addMouseListener(c);
        
        tablaProveedor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
}
