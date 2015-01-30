package presentacion;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JRadioButton;

import utilidades.conException;
import negocio.Television;

public class FormAMTelevision extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JTextField txtPrecioBase;
	public JTextField txtPeso;
	public JComboBox<String> cboColor;
	public JComboBox<String> cboConsumo;
	public JButton btnGuardar;
	protected JLabel lblNuevoElectrodomstico;
	public JButton btnCancelar;
	private JPanel contentPane;
	public JTextField txtResolucion;
	private JLabel lblResolucion;
	public JRadioButton rdbtnSintonizador;
	private JButton btnModificar;
	private JTextField txtId;
	private JLabel lblTelevisionNmero;

	public FormAMTelevision() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 391, 323);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		txtPrecioBase = new JTextField();
		txtPrecioBase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPrecioBase.setColumns(10);
		
		JLabel lblPrecioBase = new JLabel("Precio base");
		lblPrecioBase.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblConsumo = new JLabel("Consumo");
		lblConsumo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		JLabel lblPeso = new JLabel("Peso");
		lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtPeso = new JTextField();
		txtPeso.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtPeso.setColumns(10);
		
		lblNuevoElectrodomstico = new JLabel("Nuevo televisor");
		lblNuevoElectrodomstico.setHorizontalAlignment(SwingConstants.CENTER);
		lblNuevoElectrodomstico.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		cboColor = new JComboBox<String>();
		cboColor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		completarComboColor();
		
		cboConsumo = new JComboBox<String>();
		cboConsumo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		completarComboConsumo();
		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				guardar();
			}
		});
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cancelar();
			}
		});
		
		txtResolucion = new JTextField();
		txtResolucion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtResolucion.setColumns(10);
		
		lblResolucion = new JLabel("Resolucion");
		lblResolucion.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		rdbtnSintonizador = new JRadioButton("Sintonizador");
		
		btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				modificar();
			}
		});
		btnModificar.setVisible(false);
		
		txtId = new JTextField();
		txtId.setEditable(false);
		txtId.setColumns(10);
		txtId.setVisible(false);
		
		lblTelevisionNmero = new JLabel("Television n\u00FAmero: ");
		lblTelevisionNmero.setVisible(false);


		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(lblColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblPeso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblPrecioBase))
								.addComponent(lblResolucion))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtPrecioBase, 0, 0, Short.MAX_VALUE)
								.addComponent(txtPeso, 0, 0, Short.MAX_VALUE)
								.addComponent(cboColor, 0, 85, Short.MAX_VALUE)
								.addComponent(txtResolucion, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
							.addGap(53)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnSintonizador)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblConsumo, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cboConsumo, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblTelevisionNmero)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))))
						.addComponent(lblNuevoElectrodomstico, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btnModificar)
							.addGap(2)
							.addComponent(btnGuardar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNuevoElectrodomstico)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPrecioBase)
								.addComponent(txtPrecioBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPeso)
								.addComponent(txtPeso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblColor)
								.addComponent(cboColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(txtResolucion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblResolucion)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblTelevisionNmero)
								.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(59)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblConsumo)
								.addComponent(cboConsumo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(rdbtnSintonizador)))
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnModificar)
						.addComponent(btnGuardar)
						.addComponent(btnCancelar))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void completarComboColor()
	{
		cboColor.addItem("Defecto");
		cboColor.addItem("GRIS");
		cboColor.addItem("NEGRO");
		cboColor.addItem("BLANCO");
		cboColor.addItem("ROJO");
		cboColor.addItem("AZUL");
	}
	
	private void completarComboConsumo()
	{
		cboConsumo.addItem("Defecto");		
		cboConsumo.addItem("A");
		cboConsumo.addItem("B");
		cboConsumo.addItem("C");
		cboConsumo.addItem("D");
		cboConsumo.addItem("E");
		cboConsumo.addItem("F");

	}
	
	private boolean validarcampos()
	{
		String mensaje = "";
		if(!(txtResolucion.getText().trim().equals("") && txtPrecioBase.getText().trim().equals("") && 
						 txtPeso.getText().trim().equals("") && cboColor.getSelectedItem().equals("Defecto") && 
						 cboConsumo.getSelectedItem().equals("Defecto")))
		{
			if(!(!txtResolucion.getText().trim().equals("") && !txtPrecioBase.getText().trim().equals("") && 
					 !txtPeso.getText().trim().equals("") && !cboColor.getSelectedItem().toString().equals("Defecto") && 
					 !cboConsumo.getSelectedItem().toString().equals("Defecto")))
			{
				if ((!(!txtPrecioBase.getText().equals("") && !txtPeso.getText().equals(""))) ||
					cboConsumo.getSelectedItem().equals("Defecto")&& !cboColor.getSelectedItem().equals("Defecto") ||
					!cboConsumo.getSelectedItem().equals("Defecto") && cboColor.getSelectedItem().equals("Defecto"))
				{
					mensaje = mensaje + "La combinacion de datos ingresados no es correcta\n"
									  + "debe completar todos los campos, ninguno de ellos\n"
									  + "o solo el precio y el peso\n\n";
				}
			}
		}
		
		
		
		
		if(!txtPrecioBase.getText().trim().equals("") )
		{
			try 
			{Double.parseDouble(txtPrecioBase.getText());}
			catch (NumberFormatException e) 
			{mensaje = mensaje + "El formato del precio no es correcto\n";}
		}
		
		if(!txtPeso.getText().trim().equals("") )
		{
			try
			{Double.parseDouble(txtPeso.getText());} 
			catch (NumberFormatException e)
			{mensaje = mensaje + "El formato del peso no es correcto\n";}
		}
		if(!txtResolucion.getText().trim().equals("") )
		{
			try
			{Double.parseDouble(txtResolucion.getText());} 
			catch (NumberFormatException e)
			{mensaje = mensaje + "El formato de la resolución no es correcto\n";}
		}
		if(mensaje!="")
		{JOptionPane.showMessageDialog(null, mensaje);
		return false;}
		else
		{
			return true;
		}
		
	}
	
	private void guardar()
	{			
		if(validarcampos())
		{
			try {
				Television tev = crearTelevision();
				Television.agregarTelevision(tev);
				this.dispose();
			} catch (conException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}	
	}
	
	public Television crearTelevision()
	{
		Television tev = null;
		
		try {
			if(!txtResolucion.getText().trim().equals("") && !txtPrecioBase.getText().trim().equals("") && 
			 !txtPeso.getText().trim().equals("") && !cboColor.getSelectedItem().toString().equals("Defecto") && 
			 !cboConsumo.getSelectedItem().toString().equals("Defecto"))
			{
				tev= new Television(	Integer.parseInt(txtResolucion.getText()),  rdbtnSintonizador.isSelected(),
										Double.parseDouble(txtPrecioBase.getText()), cboColor.getSelectedItem().toString(),
										cboConsumo.getSelectedItem().toString(),Double.parseDouble(txtPeso.getText()));
			}
			else
			{
				if(txtResolucion.getText().trim().equals("") && txtPrecioBase.getText().trim().equals("") && 
						 txtPeso.getText().trim().equals("") && cboColor.getSelectedItem().equals("Defecto") && 
						 cboConsumo.getSelectedItem().equals("Defecto") )
				{
					tev = new Television();
				}
				else
				{
					if(!txtPrecioBase.getText().equals("") && !txtPeso.getText().equals(""))
					{
						tev= new Television(Double.parseDouble(txtPrecioBase.getText()),Double.parseDouble(txtPeso.getText()));
					}
					else
					{
						tev = new Television();						
					}
				}
			}
		} catch (conException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		catch (NumberFormatException w)
		{
			JOptionPane.showMessageDialog(null, "Error al insertar nuevo televisor");

		}
		return tev;
	}
	
	public void modificar()
	{
		if(validarcampos())
		{
			try {
				Television tev = crearTelevision();
				Television.modificarTelevision(tev, Integer.parseInt(txtId.getText()) );
				JOptionPane.showMessageDialog(null, "El televisor ha sido modificado");
				this.dispose();
			} catch (conException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al encontrar el televisor a modificar");
			}
		}	
	}
	
	public void cancelar()
	{		this.dispose();	}
	
	public void modoModificar(int id, String color, String consumo, String precio, String resolucion, String peso,boolean sintonizador)
	{
			lblNuevoElectrodomstico.setText("Modificar Television");
			txtId.setText(Integer.toString(id));
			txtId.setVisible(true);
			lblTelevisionNmero.setVisible(true);
			btnModificar.setVisible(true);
			btnGuardar.setVisible(false);
			txtPrecioBase.setText(precio);
			txtPeso.setText(peso);
			txtResolucion.setText(resolucion);
			rdbtnSintonizador.setSelected(sintonizador);
			cboColor.setSelectedItem(color);
			cboConsumo.setSelectedItem(consumo);			
			
	}
}