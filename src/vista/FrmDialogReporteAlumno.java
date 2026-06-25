package vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import entidad.Alumno;
import model.AlumnoModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.GeneradorReporte;

public class FrmDialogReporteAlumno extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton cancelButton;

	public FrmDialogReporteAlumno(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		setBounds(100, 100, 1166, 623);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		JPanel panelReporte = new JPanel();
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setLayout(new BorderLayout(0, 0));
		contentPanel.add(panelReporte, BorderLayout.CENTER);
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(this);
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
		
		
		AlumnoModel model = new AlumnoModel();
		List<Alumno> listaTodos = model.listaTodos();
		
		//1 Coleccion para el Reporte le pasamos la data 
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(listaTodos);
		
		//2 Le pasamos Diseño del Reporte
		String jasper = "reporteAlumno.jasper";
		
		//3 generamos el PDF del reporte
		JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);
		JRViewer jRViewer = new JRViewer(print);
		
		//4 Le agrega al Panel
		panelReporte.removeAll();
		panelReporte.add(jRViewer);
		panelReporte.repaint();
		panelReporte.revalidate();

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == cancelButton) {
			do_cancelButton_actionPerformed(e);
		}
	}
	protected void do_cancelButton_actionPerformed(ActionEvent e) {
		this.dispose();
	}
}
