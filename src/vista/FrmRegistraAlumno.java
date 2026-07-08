package vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import entidad.Alumno;
import model.AlumnoModel;
import util.ValidateUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistraAlumno extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtDni;
	private JTextField txtEmail;
	private JTextField txtFechaNacimiento;
	private JButton btnRegistrar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					//UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
					//UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
					//UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");
					
					FrmRegistraAlumno frame = new FrmRegistraAlumno();
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
	public FrmRegistraAlumno() {
		setTitle("Jorge Jacinto");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 698, 458);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registra Alumno");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTitulo.setBounds(10, 30, 680, 34);
		contentPane.add(lblTitulo);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(62, 107, 152, 14);
		contentPane.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(247, 104, 282, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(62, 148, 152, 14);
		contentPane.add(lblDNI);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(247, 145, 282, 20);
		contentPane.add(txtDni);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(62, 191, 152, 14);
		contentPane.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(247, 188, 282, 20);
		contentPane.add(txtEmail);
		
		JLabel lblFechanacimiento = new JLabel("FechaNacimiento");
		lblFechanacimiento.setBounds(62, 232, 152, 14);
		contentPane.add(lblFechanacimiento);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(247, 229, 282, 20);
		contentPane.add(txtFechaNacimiento);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(291, 307, 89, 23);
		contentPane.add(btnRegistrar);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
	}
	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		
		//1 Recibir los datos del formulario en String
		String nombre = txtNombre.getText().trim();
		String dni = txtDni.getText().trim();
		String email = txtEmail.getText().trim();
		String fechaNacimiento = txtFechaNacimiento.getText().trim();
		
		//2 Validar los datos (opcional)
		if (nombre.matches(ValidateUtil.TEXTO_40) == false) {
			JOptionPane.showMessageDialog(this, "El nombre no es válido. Tiene que tener de 1 a 40 caracteres");
			return;
		}
		if (dni.matches(ValidateUtil.DNI) == false) {
			JOptionPane.showMessageDialog(this, "El DNI no es válido. Tiene que tener 8 dígitos");
			return;
		}
		if (email.matches(ValidateUtil.EMAIL) == false) {
			JOptionPane.showMessageDialog(this, "El email no es válido");
			return;
		}
		if (fechaNacimiento.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
			JOptionPane.showMessageDialog(this,"La fecha de nacimiento no es válida. Tiene que tener el formato YYYY-MM-DD");
			return;
		}
		
		//3 Crear el objeto Alumno
		Alumno obj = new Alumno();
		obj.setNombre(nombre);
		obj.setDni(dni);
		obj.setCorreo(email);
		obj.setFechaNacimiento(java.time.LocalDate.parse(fechaNacimiento));
		
		//4 Crear el objeto AlumnoModel
		AlumnoModel model = new AlumnoModel();
		int salida = model.insertaAlumno(obj);
		
		//5 Mostrar el resultado
		if (salida > 0) {
			JOptionPane.showMessageDialog(this, "Alumno registrado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al registrar el alumno");
		}
		
	}
}








