package vista;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import entidad.Categoria;
import entidad.Cliente;
import model.CategoriaModel;
import model.ClienteModel;
import util.ValidateUtil;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FrmRegistraCliente extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JComboBox<String> cboCategoria;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmRegistraCliente frame = new FrmRegistraCliente();
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
	public FrmRegistraCliente() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 606, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registra Cliente");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 40, 570, 35);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombres");
		lblNombre.setBounds(104, 125, 93, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(294, 122, 220, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(104, 169, 93, 14);
		contentPane.add(lblDNI);
		
		txtDni = new JTextField();
		txtDni.addKeyListener(this);
		txtDni.setColumns(10);
		txtDni.setBounds(294, 166, 220, 20);
		contentPane.add(txtDni);
		
		JLabel lblcategoria = new JLabel("Categoría");
		lblcategoria.setBounds(104, 219, 93, 14);
		contentPane.add(lblcategoria);
		
		cboCategoria = new JComboBox<String>();
		cboCategoria.setBounds(294, 215, 220, 22);
		contentPane.add(cboCategoria);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(199, 304, 226, 23);
		contentPane.add(btnRegistrar);
		
		cargaCategoria(); 

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		//1 Capturar datos
		String nombre = txtNombre.getText();
		String dni = txtDni.getText();
		int index = cboCategoria.getSelectedIndex();
		
		//2 validar datos
		if (nombre.matches(ValidateUtil.TEXTO_40) == false) {
			 JOptionPane.showMessageDialog(this, "El nombre no es válido. Tiene que tener de 1 a 40 caracteres");
 			return;
		}
		if (dni.matches(ValidateUtil.DNI) == false) {
			JOptionPane.showMessageDialog(this, "El DNI no es válido. Tiene que tener 8 dígitos");
			return;
		}
		if (index == 0) {
			JOptionPane.showMessageDialog(this, "Debe seleccionar una categoría");
			return;
		}
		
		//2.1 Validar que el DNI no exista en la base de datos
		ClienteModel model = new ClienteModel();
		boolean existeCliente = model.existeClientePorDNI(dni);
		if (existeCliente) {
			JOptionPane.showMessageDialog(this, "El DNI : " + dni + " ya existe. No se puede registrar el cliente");
			return;
		}
		
		//3 Crear objeto Cliente
		Categoria objCategoria = new Categoria();
		objCategoria.setIdCategoria(Integer.parseInt(cboCategoria.getSelectedItem().toString().split(" - ")[0]));
		
		Cliente cliente = new Cliente();
		cliente.setNombre(nombre);
		cliente.setDni(dni);
		cliente.setFechaRegistro(LocalDateTime.now());
		cliente.setEstado(1);
		cliente.setCategoria(objCategoria);
		
		//4 Llamar a ClienteModel para registrar el cliente
	
		int resultado = model.insertaCliente(cliente);

        // 4. Mensaje
        if (resultado > 0) {
            JOptionPane.showMessageDialog(this, "Cliente registrado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar Cliente");
        }

	}
	
	
	void cargaCategoria() {
		//1 llamar a CategoriaModel para obtener la lista de categorias
		CategoriaModel model = new CategoriaModel();
		List<Categoria> lista = model.ListaTodos();
		
		//2 cargar la lista de categorias en el combo
		DefaultComboBoxModel<String> cboModel = new DefaultComboBoxModel<String>();
		cboModel.addElement(" [ Seleccione ]");
		
		for (Categoria c : lista) {
			cboModel.addElement(c.getIdCategoria() + " - " + c.getNombre());
		}
		
		cboCategoria.setModel(cboModel);
		
	}
	
	
	
	public void keyPressed(KeyEvent e) {
	}
	public void keyReleased(KeyEvent e) {
	}
	public void keyTyped(KeyEvent e) {
		if (e.getSource() == txtDni) {
			do_textField_keyTyped(e);
		}
	}
	protected void do_textField_keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		System.out.println("Tecla presionada: keyPressed " + c);
		//Si es letra no ingresa
		if (Character.isLetter(c)) {
			e.consume(); // Ignorar la letra
		}
		//Si se ingresa más 8 dígitos digitos
		if (Character.isDigit(c) && txtDni.getText().length() >= 8) {
			e.consume(); // Ignorar el dígito si ya hay 8
		}
	}
}
