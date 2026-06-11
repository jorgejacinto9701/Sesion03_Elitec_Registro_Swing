package vista;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import entidad.Concurso;
import model.ConcursoModel;
import util.ValidateUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

public class FrmRegistroConcurso extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField txtNombre;
    private JTextField txtFechaInicio;
    private JTextField txtFechaFin;

    private JButton btnRegistrar;
    private JCheckBox chkEstado;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    FrmRegistroConcurso frame = new FrmRegistroConcurso();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public FrmRegistroConcurso() {

        setTitle("Registro de Concurso");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 400);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitulo = new JLabel("REGISTRO CONCURSO");
        lblTitulo.setBounds(200, 20, 200, 30);
        contentPane.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 70, 100, 20);
        contentPane.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(180, 70, 200, 20);
        contentPane.add(txtNombre);

        JLabel lblFechaInicio = new JLabel("Fecha Inicio (YYYY-MM-DD):");
        lblFechaInicio.setBounds(50, 110, 200, 20);
        contentPane.add(lblFechaInicio);

        txtFechaInicio = new JTextField();
        txtFechaInicio.setBounds(250, 110, 130, 20);
        contentPane.add(txtFechaInicio);

        JLabel lblFechaFin = new JLabel("Fecha Fin (YYYY-MM-DD):");
        lblFechaFin.setBounds(50, 150, 200, 20);
        contentPane.add(lblFechaFin);

        txtFechaFin = new JTextField();
        txtFechaFin.setBounds(250, 150, 130, 20);
        contentPane.add(txtFechaFin);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(220, 250, 120, 30);
        btnRegistrar.addActionListener(this);
        contentPane.add(btnRegistrar);
        
        chkEstado = new JCheckBox("Activo");
        chkEstado.setBounds(243, 191, 97, 23);
        contentPane.add(chkEstado);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegistrar) {
            registrarConcurso();
        }
    }

    private void registrarConcurso() {


            // 1. Capturar datos
            String nombre = txtNombre.getText();
            String fechaInicio = txtFechaInicio.getText();
            String fechaFin = txtFechaFin.getText();
            int estado = chkEstado.isSelected() ? 1 :0; // 1 para activo, 0 para inactivo

            //validar datos
            if (nombre.matches(ValidateUtil.TEXTO_40) == false) {
    			JOptionPane.showMessageDialog(this, "El nombre no es válido. Tiene que tener de 1 a 40 caracteres");
    			return;
    		}
            if (fechaInicio.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
    			JOptionPane.showMessageDialog(this,"La fecha de inicio no es válida. Tiene que tener el formato YYYY-MM-DD");
    			return;
    		}
            if (fechaFin.matches(ValidateUtil.DATE_YYYY_MM_DD) == false) {
    			JOptionPane.showMessageDialog(this,"La fecha de fin no es válida. Tiene que tener el formato YYYY-MM-DD");
    			return;
    		}
            
            // 2. Crear objeto
            Concurso c = new Concurso();
            c.setNombre(nombre);
            c.setFechaInicio(LocalDate.parse(fechaInicio));
            c.setFechaFin(LocalDate.parse(fechaFin));
            c.setEstado(estado);

            // 3. Enviar al model
            ConcursoModel model = new ConcursoModel();
            int resultado = model.insertaConcurso(c);

            // 4. Mensaje
            if (resultado > 0) {
                JOptionPane.showMessageDialog(this, "Concurso registrado correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar concurso");
            }

     
    }
}