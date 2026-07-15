package vista;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import model.AlumnoModel;
import util.Exportador;
import util.ValidateUtil;

public class FrmCrudAlumno extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtFechaNacimiento;
	private JTextField txtEmail;
	private JTextField txtDNI;
	private JTextField txtNombre;
	private JTextField txtCodigo;
	private JTable table;
	private JButton btnListar;
	private JButton btnBuscar;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminarLogico;
	private JButton btnEliminarFisico;
	private JButton btnLimpiar;
	private JCheckBox chkEstado;
	private JButton brnReporte;
	private JButton btnPDF;
	private JButton btnJSON;
	private JButton btnXML;
	private JButton btnExcel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					UIManager.setLookAndFeel("com.jtattoo.plaf.luna.LunaLookAndFeel");
					FrmCrudAlumno frame = new FrmCrudAlumno();
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
	public FrmCrudAlumno() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1337, 504);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(31, 153, 101, 14);
		contentPane.add(lblNombre);

		JLabel lblDNI = new JLabel("DNI");
		lblDNI.setBounds(31, 194, 73, 14);
		contentPane.add(lblDNI);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(31, 237, 78, 14);
		contentPane.add(lblEmail);

		JLabel lblFechanacimiento = new JLabel("FechaNacimiento");
		lblFechanacimiento.setBounds(31, 278, 108, 14);
		contentPane.add(lblFechanacimiento);

		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(163, 275, 143, 20);
		contentPane.add(txtFechaNacimiento);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(163, 234, 222, 20);
		contentPane.add(txtEmail);

		txtDNI = new JTextField();
		txtDNI.setColumns(10);
		txtDNI.setBounds(163, 191, 143, 20);
		contentPane.add(txtDNI);

		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(163, 150, 222, 20);
		contentPane.add(txtNombre);

		JLabel lblMantenimientoAlumno = new JLabel("Mantenimiento Alumno");
		lblMantenimientoAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoAlumno.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMantenimientoAlumno.setBounds(31, 26, 1219, 34);
		contentPane.add(lblMantenimientoAlumno);

		btnListar = new JButton("Listar Todos");
		btnListar.addActionListener(this);
		btnListar.setBounds(433, 93, 160, 30);
		contentPane.add(btnListar);

		btnBuscar = new JButton("Busca");
		btnBuscar.setIcon(new ImageIcon("icons/004-buscar.png"));
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(433, 125, 160, 30);
		contentPane.add(btnBuscar);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setIcon(new ImageIcon("icons/001-agregar.png"));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(433, 157, 160, 30);
		contentPane.add(btnRegistrar);

		btnActualizar = new JButton("Actualizar");
		btnActualizar.setIcon(new ImageIcon("icons/editar.png"));
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(433, 189, 160, 30);
		contentPane.add(btnActualizar);

		btnEliminarLogico = new JButton("Eliminar lógico");
		btnEliminarLogico.setIcon(new ImageIcon("icons/eliminar.png"));
		btnEliminarLogico.addActionListener(this);
		btnEliminarLogico.setBounds(433, 221, 160, 30);
		contentPane.add(btnEliminarLogico);

		btnEliminarFisico = new JButton("Eliminar físico");
		btnEliminarFisico.setIcon(new ImageIcon("icons/remove.gif"));
		btnEliminarFisico.addActionListener(this);
		btnEliminarFisico.setBounds(433, 253, 160, 30);
		contentPane.add(btnEliminarFisico);

		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setIcon(new ImageIcon("icons/005-limpiar.png"));
		btnLimpiar.addActionListener(this);
		btnLimpiar.setBounds(433, 285, 160, 30);
		contentPane.add(btnLimpiar);
		JLabel lblCodigo = new JLabel("Código");
		lblCodigo.setBounds(31, 118, 101, 14);
		contentPane.add(lblCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);
		txtCodigo.setBounds(163, 115, 143, 20);
		contentPane.add(txtCodigo);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(606, 71, 691, 293);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "C\u00F3digo", "Nombres", "DNI", "Correo", "Fecha Nacimiento", "Estado" }));
		
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setDefaultEditor(Object.class, null);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		
		UIDefaults defaults = UIManager.getLookAndFeelDefaults();
		defaults.putIfAbsent("Table.alternateRowColor", new Color(176, 245, 215));
		
		scrollPane.setViewportView(table);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setBounds(159, 317, 97, 23);
		contentPane.add(chkEstado);
		
		brnReporte = new JButton("Reporte");
		brnReporte.setIcon(new ImageIcon("icons/005-reporte-de-negocios.png"));
		brnReporte.addActionListener(this);
		brnReporte.setFocusPainted(false);
		brnReporte.setIconTextGap(8);
		brnReporte.setBounds(1133, 385, 153, 30);
		contentPane.add(brnReporte);

		btnPDF = new JButton("PDF");
		btnPDF.addActionListener(this);
		btnPDF.setBounds(606, 385, 120, 30);
		contentPane.add(btnPDF);

		btnJSON = new JButton("JSON");
		btnJSON.addActionListener(this);
		btnJSON.setBounds(736, 385, 120, 30);
		contentPane.add(btnJSON);

		btnXML = new JButton("XML");
		btnXML.addActionListener(this);
		btnXML.setBounds(866, 385, 120, 30);
		contentPane.add(btnXML);

		btnExcel = new JButton("Excel");
		btnExcel.addActionListener(this);
		btnExcel.setBounds(996, 385, 120, 30);
		contentPane.add(btnExcel);
		
		listarTodos();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == brnReporte) {
			do_brnReporte_actionPerformed(e);
		}
		if (e.getSource() == btnLimpiar) {
			do_btnLimpiar_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarFisico) {
			do_btnEliminarFisico_actionPerformed(e);
		}
		if (e.getSource() == btnEliminarLogico) {
			do_btnEliminarLogico_actionPerformed(e);
		}
		if (e.getSource() == btnActualizar) {
			do_btnActualizar_actionPerformed(e);
		}
		if (e.getSource() == btnRegistrar) {
			do_btnRegistrar_actionPerformed(e);
		}
		if (e.getSource() == btnBuscar) {
			do_btnBuscar_actionPerformed(e);
		}
		if (e.getSource() == btnListar) {
			do_btnListar_actionPerformed(e);
		}
		if (e.getSource() == btnPDF) {
			exportarPDF(); 
		}
		if (e.getSource() == btnJSON) {
		    exportarJSON();
		}
		if (e.getSource() == btnXML) {
		    exportarXML();
		}
		if (e.getSource() == btnExcel) {
		    exportarExcel();
		}
	}

	protected void do_btnListar_actionPerformed(ActionEvent e) {
		listarTodos();
	}

	protected void do_btnBuscar_actionPerformed(ActionEvent e) {
		buscar();
	}

	protected void do_btnRegistrar_actionPerformed(ActionEvent e) {
		registrar();
		listarTodos();
		limpiar();
	}

	protected void do_btnActualizar_actionPerformed(ActionEvent e) {
		actualizar();
		listarTodos();
		limpiar();
	}

	protected void do_btnEliminarLogico_actionPerformed(ActionEvent e) {
		eliminarLogico();
		listarTodos();
		limpiar();
	}

	protected void do_btnEliminarFisico_actionPerformed(ActionEvent e) {
		eliminarFisico();
		listarTodos();
		limpiar();
	}

	protected void do_btnLimpiar_actionPerformed(ActionEvent e) {
		limpiar();
	}

	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			do_table_mouseClicked(e);
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	protected void do_table_mouseClicked(MouseEvent e) {
		seleccionarFila();
	}
	
	void seleccionarFila() {
		int fila = table.getSelectedRow();
		txtCodigo.setText(table.getValueAt(fila, 0).toString());
		txtNombre.setText(table.getValueAt(fila, 1).toString());
		txtDNI.setText(table.getValueAt(fila, 2).toString());
		txtEmail.setText(table.getValueAt(fila, 3).toString());
		txtFechaNacimiento.setText(table.getValueAt(fila, 4).toString());
		String estado = table.getValueAt(fila, 5).toString();
		if ("Activo".equalsIgnoreCase(estado)) {
			chkEstado.setSelected(true);
		}else {
			chkEstado.setSelected(false);
		}
	}
	
	void listarTodos() {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos

		AlumnoModel model = new AlumnoModel();
		List<Alumno> lista = model.listaTodos();
		for (Alumno a : lista) {
			Object[] rowData = { a.getIdAlumno(), 
								 a.getNombre(), 
								 a.getDni(), 
								 a.getCorreo(), 
								 a.getFechaNacimiento(),
								 a.getEstado()==1?"Activo":"Inactivo" };
			dtm.addRow(rowData);
		}
	}

	void buscar() {
		String codigo = txtCodigo.getText().trim();
		if (codigo.isEmpty()) {
		    JOptionPane.showMessageDialog(this, "Seleccione un alumno o ingrese un código válido");
		    return;
		}
		AlumnoModel model = new AlumnoModel();
		Alumno objAlumno  = model.buscaAlumno(Integer.parseInt(codigo));
		if (objAlumno == null ) {
			JOptionPane.showMessageDialog(this, "No existe el alumnos de código " + codigo);
			limpiar();
		    return;
		}
		txtCodigo.setText(String.valueOf(objAlumno.getIdAlumno()));
		txtNombre.setText(objAlumno.getNombre());
		txtDNI.setText(objAlumno.getDni());
		txtEmail.setText(objAlumno.getCorreo());
		txtFechaNacimiento.setText(objAlumno.getFechaNacimiento().format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE));
		chkEstado.setSelected(objAlumno.getEstado() == 1? true: false);
		
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0); // Limpiar la tabla antes de agregar nuevos datos
		
		Object[] rowData = { objAlumno.getIdAlumno(), 
				objAlumno.getNombre(), 
				objAlumno.getDni(), 
				objAlumno.getCorreo(), 
				objAlumno.getFechaNacimiento(),
				objAlumno.getEstado()==1?"Activo":"Inactivo" };
		dtm.addRow(rowData);
	}

	void registrar() {
		// 1 Recibir los datos del formulario en String
		String nombre = txtNombre.getText().trim();
		String dni = txtDNI.getText().trim();
		String email = txtEmail.getText().trim();
		String fechaNacimiento = txtFechaNacimiento.getText().trim();

		// 2 Validar los datos (opcional)
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
			JOptionPane.showMessageDialog(this,
					"La fecha de nacimiento no es válida. Tiene que tener el formato YYYY-MM-DD");
			return;
		}

		// 3 Crear el objeto Alumno
		Alumno obj = new Alumno();
		obj.setNombre(nombre);
		obj.setDni(dni);
		obj.setCorreo(email);
		obj.setFechaNacimiento(java.time.LocalDate.parse(fechaNacimiento));

		// 4 Crear el objeto AlumnoModel
		AlumnoModel model = new AlumnoModel();
		int salida = model.insertaAlumno(obj);

		// 5 Mostrar el resultado
		if (salida > 0) {
			JOptionPane.showMessageDialog(this, "Alumno registrado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al registrar el alumno");
		}
	}

	void actualizar() {
		// 1 Recibir los datos del formulario en String
		String nombre = txtNombre.getText().trim();
		String dni = txtDNI.getText().trim();
		String email = txtEmail.getText().trim();
		String fechaNacimiento = txtFechaNacimiento.getText().trim();
		String codigo = txtCodigo.getText().trim();
		
		// 2 Validar los datos (opcional)
		if (codigo.isEmpty()) {
		    JOptionPane.showMessageDialog(this, "Seleccione un alumno o ingrese un código válido");
		    return;
		}
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
			JOptionPane.showMessageDialog(this,
					"La fecha de nacimiento no es válida. Tiene que tener el formato YYYY-MM-DD");
			return;
		}

		// 3 Crear el objeto Alumno
		Alumno obj = new Alumno();
		obj.setIdAlumno(Integer.parseInt(codigo));
		obj.setNombre(nombre);
		obj.setDni(dni);
		obj.setCorreo(email);
		obj.setFechaNacimiento(java.time.LocalDate.parse(fechaNacimiento));

		// 4 Crear el objeto AlumnoModel
		AlumnoModel model = new AlumnoModel();
		int salida = model.actualizaAlumno(obj);

		// 5 Mostrar el resultado
		if (salida > 0) {
			JOptionPane.showMessageDialog(this, "Alumno actualizado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al actualizar el alumno");
		}
	}

	void eliminarLogico() {
		String codigo = txtCodigo.getText().trim();
		if (codigo.isEmpty()) {
		    JOptionPane.showMessageDialog(this, "Seleccione un alumno o ingrese un código válido");
		     return;
		}
		AlumnoModel model = new AlumnoModel();
		Alumno objAlumno  = model.buscaAlumno(Integer.parseInt(codigo));
		int newEstado = objAlumno.getEstado() == 0 ? 1 : 0;
		objAlumno.setEstado(newEstado);
		model.actualizaAlumno(objAlumno);
	}

	void eliminarFisico() {
		 String codigo = txtCodigo.getText().trim();
		 if (codigo.isEmpty()) {
		     JOptionPane.showMessageDialog(this, "Seleccione un alumno o ingrese un código válido");
		     return;
		 }
		 
		 int confirm = JOptionPane.showConfirmDialog(
			        this,
			        "¿Confirma eliminar el alumno con código " + codigo + "?",
			        "Confirmar eliminación",
			        JOptionPane.YES_NO_OPTION,
			        JOptionPane.WARNING_MESSAGE
				 );
		 if (confirm != JOptionPane.YES_OPTION) {
		        return;
		 }
		
		AlumnoModel model = new AlumnoModel();
		int salida = model.eliminaAlumno(Integer.parseInt(codigo));
		if (salida > 0) {
			JOptionPane.showMessageDialog(this, "Alumno eliminado correctamente");
		} else {
			JOptionPane.showMessageDialog(this, "Error al eliminar el alumno");
		}
	}

	void limpiar() {
		txtCodigo.setText("");
		txtNombre.setText("");
		txtDNI.setText("");
		txtEmail.setText("");
		txtFechaNacimiento.setText("");
	}
	protected void do_brnReporte_actionPerformed(ActionEvent e) {
		FrmDialogReporteAlumno dialog = new FrmDialogReporteAlumno(this, true);
		dialog.setLocationRelativeTo(this);
		dialog.setVisible(true);
		dialog.setModal(true);
	}
	void exportarPDF() {

	    AlumnoModel model = new AlumnoModel();
	    List<Alumno> lista = model.listaTodos();

	    Exportador exportador = new Exportador();
	    exportador.exportarPDF(lista);

	    JOptionPane.showMessageDialog(this, "JSON generado correctamente");
	}
	
	
	void exportarJSON() {

	    AlumnoModel model = new AlumnoModel();
	    List<Alumno> lista = model.listaTodos();

	    Exportador exportador = new Exportador();
	    exportador.exportarJSON(lista);

	    JOptionPane.showMessageDialog(this, "JSON generado correctamente");
	}


	void exportarXML() {

	    AlumnoModel model = new AlumnoModel();
	    List<Alumno> lista = model.listaTodos();

	    Exportador exportador = new Exportador();
	    exportador.exportarXML(lista);

	    JOptionPane.showMessageDialog(this, "XML generado correctamente");
	}


	void exportarExcel() {

	    AlumnoModel model = new AlumnoModel();
	    List<Alumno> lista = model.listaTodos();

	    Exportador exportador = new Exportador();
	    exportador.exportarExcel(lista);

	    JOptionPane.showMessageDialog(this, "Excel generado correctamente");
	}

}
