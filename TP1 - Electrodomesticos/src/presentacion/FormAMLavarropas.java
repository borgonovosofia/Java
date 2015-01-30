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

import utilidades.conException;
import negocio.Lavarropas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormAMLavarropas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtPrecioBase;
	private JTextField txtPeso;
	private JComboBox<String> cboColor;
	private JComboBox<String> cboConsumo;
	private JButton btnGuardar;
	protected JLabel lblNuevoElectrodomstico;
	private JButton btnCancelar;
	private JPanel contentPane;
	private JTextField txtCarga;
	private JLabel lblLavarropasNmero;
	private JTextField txtId;
	private JButton btnModificar;

	public FormAMLavarropas() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 379, 323);
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
		
		lblNuevoElectrodomstico = new JLabel("Nuevo lavarropas");
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
		
		JLabel lblCarga = new JLabel("Carga");
		lblCarga.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		txtCarga = new JTextField();
		txtCarga.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtCarga.setColumns(10);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				modificar();
			}
		});
		btnModificar.setVisible(false);
		
		lblLavarropasNmero = new JLabel("Lavarropas n\u00FAmero:");
		lblLavarropasNmero.setVisible(false);
		txtId = new JTextField();
		txtId.setVisible(false);
		txtId.setEditable(false);
		txtId.setEnabled(false);
		txtId.setColumns(10);

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnModificar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnGuardar)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnCancelar))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(28)
							.addComponent(lblNuevoElectrodomstico, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(57, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(lblColor, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblPeso, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblPrecioBase))
						.addComponent(lblCarga))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(txtPrecioBase, 0, 0, Short.MAX_VALUE)
								.addComponent(txtPeso, 0, 0, Short.MAX_VALUE)
								.addComponent(cboColor, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lblConsumo, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cboConsumo, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addGap(20))
								.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
									.addComponent(lblLavarropasNmero)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
									.addGap(33))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(txtCarga, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE)
							.addContainerGap(212, Short.MAX_VALUE))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNuevoElectrodomstico, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrecioBase)
						.addComponent(txtPrecioBase, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblLavarropasNmero)
						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblPeso)
								.addComponent(txtPeso, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblColor)
								.addComponent(cboColor, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblCarga)
								.addComponent(txtCarga, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(38)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblConsumo)
								.addComponent(cboConsumo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
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
		
		if(!(!txtCarga.getText().trim().equals("") && !txtPrecioBase.getText().trim().equals("") && 
				 !txtPeso.getText().trim().equals("") && !cboColor.getSelectedItem().toString().equals("Defecto") && 
				 !cboConsumo.getSelectedItem().toString().equals("Defecto")))
		{
			if(!(txtCarga.getText().trim().equals("") && txtPrecioBase.getText().trim().equals("") && 
					 txtPeso.getText().trim().equals("") && cboColor.getSelectedItem().equals("Defecto") && 
					 cboConsumo.getSelectedItem().equals("Defecto") ))
			{
				if((!(!txtPrecioBase.getText().equals("") && !txtPeso.getText().equals("")))||
				cboConsumo.getSelectedItem().equals("Defecto")&& !cboColor.getSelectedItem().equals("Defecto") ||
				!cboConsumo.getSelectedItem().equals("Defecto") && cboColor.getSelectedItem().equals("Defecto"))
				{
					mensaje = mensaje + "La combinacion de datos ingresados no es correcta\n"
							  + "debe completar todos los campos, ninguno de ellos\n"
							  + "o solo el precio y el peso\n\n";				}
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
		if(!txtCarga.getText().trim().equals("") )
		{
			try 
			{Double.parseDouble(txtCarga.getText());}
			catch (NumberFormatException e) 
			{mensaje = mensaje + "El formato de la carga no es correcto\n";}
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
		try {
			if(validarcampos())
			{
				Lavarropas lav = crearLavarropas();
				Lavarropas.agregarLavarropas(lav);
				this.dispose();
			}
		} catch (conException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	public void cancelar()
	{		this.dispose();
	}
	
	public void modificar()
	{
		try {
				if(validarcampos())
				{
					Lavarropas lav = crearLavarropas();
					Lavarropas.modificarLavarropas(lav,Integer.parseInt(txtId.getText()));
					JOptionPane.showMessageDialog(null, "El lavarropas ha sido modificado");
					this.dispose();
				}
		}
		catch (conException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}		
		catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al encontrar el lavarropas a modificar");
		} 
		
	}
	public void modoModificar(int id, String color, String consumo, String precio, String carga, String peso)
	{
			lblNuevoElectrodomstico.setText("Modificar Lavarropas");
			txtId.setText(Integer.toString(id));
			txtId.setVisible(true);
			lblLavarropasNmero.setVisible(true);
			btnModificar.setVisible(true);
			btnGuardar.setVisible(false);
			txtPrecioBase.setText(precio);
			txtPeso.setText(peso);
			txtCarga.setText(carga);
			cboColor.setSelectedItem(color);
			cboConsumo.setSelectedItem(consumo);			
			
	}

	public Lavarropas crearLavarropas()
	{
		Lavarropas lav = null;
		if(!txtCarga.getText().trim().equals("") && !txtPrecioBase.getText().trim().equals("") && 
				 !txtPeso.getText().trim().equals("") && !cboColor.getSelectedItem().toString().equals("Defecto") && 
				 !cboConsumo.getSelectedItem().toString().equals("Defecto"))
		{
			try 
			{
				lav= new Lavarropas(Double.parseDouble(txtCarga.getText()),Double.parseDouble(txtPrecioBase.getText()),cboColor.getSelectedItem().toString(),
						cboConsumo.getSelectedItem().toString(),Double.parseDouble(txtPeso.getText()));
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Error al agregar nuevo lavarropas");

			} catch (conException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		else
		{
			try {
				if(txtCarga.getText().trim().equals("") && txtPrecioBase.getText().trim().equals("") && 
				 txtPeso.getText().trim().equals("") && cboColor.getSelectedItem().equals("Defecto") && 
				 cboConsumo.getSelectedItem().equals("Defecto") )
				{
					lav = new Lavarropas();
				}
				else
				{
					if(!txtPrecioBase.getText().equals("") && !txtPeso.getText().equals(""))
					{
						lav= new Lavarropas(Double.parseDouble(txtPrecioBase.getText()),Double.parseDouble(txtPeso.getText()));
					}
					else
					{
						lav = new Lavarropas();						
					}
				}
			} catch (NumberFormatException | conException e) {
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
		}
		return lav;
	}
}

