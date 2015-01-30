package presentacion;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowEvent;
public class FormListadoBase extends JFrame {

	
	protected static final long serialVersionUID = 1L;
	protected JPanel contentPane;
	protected JLabel lblNuevoElectrodomstico;
	protected JTable tblListado;
	protected ResultSet rs;
	protected JScrollPane scrollPane;

	public FormListadoBase() {
		addWindowFocusListener(new WindowFocusListener() {
			public void windowGainedFocus(WindowEvent arg0) {
				cargar_grilla();
			}
			public void windowLostFocus(WindowEvent arg0) {
			}
		});

		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 772, 441);
		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblNuevoElectrodomstico = new JLabel("Listado Electrodom\u00E9stico");
		lblNuevoElectrodomstico.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoElectrodomstico.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		scrollPane = new JScrollPane();
		
		JButton btnNuevo = new JButton("Nuevo");

		btnNuevo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				nuevo();
			}
			
		});
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			modificar();}
	
		});

		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			borrar();}
			
		});
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				borrar();
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
						.addComponent(lblNuevoElectrodomstico, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnNuevo)
							.addGap(18)
							.addComponent(btnModificar)
							.addGap(18)
							.addComponent(btnBorrar)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNuevoElectrodomstico)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBorrar)
						.addComponent(btnModificar)
						.addComponent(btnNuevo))
					.addGap(23))
		);
		
		
		tblListado = new JTable();
		tblListado.setSelectionBackground(new Color(0,200,200));
		tblListado.setSelectionForeground(new Color(1,1,1));
		tblListado.setRowSelectionAllowed(true);
		tblListado.setAutoscrolls(true);
		scrollPane.setViewportView(tblListado);
		contentPane.setLayout(gl_contentPane);
	}
	protected void nuevo()
	{
		
	}
	protected void modificar()
	{
		
	}
	protected void borrar()
	{
		
	}
	protected void cargar_grilla()
	{
		
	}
}
