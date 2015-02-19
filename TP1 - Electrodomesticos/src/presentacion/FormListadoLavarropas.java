package presentacion;



import negocio.Lavarropas;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.ListSelectionModel;

import utilidades.conException;
import data.LavarropasAdapter;

public class FormListadoLavarropas extends FormListadoBase 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public FormListadoLavarropas() 
	{
		tblListado.setSurrendersFocusOnKeystroke(true);
		tblListado.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblListado.setForeground(Color.BLACK);
		tblListado.setBackground(UIManager.getColor("activeCaption"));
		setBounds(100, 100, 772, 441);
		
		lblNuevoElectrodomstico.setText("Listado de lavarropas");		
		cargar_grilla();
	}
	protected void cargar_grilla()
	{
		TableModelNuevo dtm;
		try {
			dtm = Lavarropas.getLavarropas();
			tblListado.setModel(dtm);
		} catch (conException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		tblListado.getColumnModel().getColumn(0).setMaxWidth(17);
		tblListado.getColumnModel().getColumn(0).setMinWidth(17);
		tblListado.getColumnModel().getColumn(0).setPreferredWidth(17);		
	}
	protected void nuevo()
	{
		try
		{
			FormAMLavarropas frame = new FormAMLavarropas();
			frame.setVisible(true);
		} 
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}

	protected void borrar()
	{
		int i=tblListado.getSelectedRowCount();
		if(i==0)
		{
			JOptionPane.showMessageDialog(null, "Debe seleccionar el lavarropas a borrar");
		}
		else
		{
			int respuesta =JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el lavarropas?", "Confirmar borrado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(respuesta == JOptionPane.YES_OPTION)
			{
				i = tblListado.getSelectedRow();
				LavarropasAdapter adapter = new LavarropasAdapter();
				try {
					adapter.borrarLavarropas((Integer)tblListado.getValueAt(i, 0));
					JOptionPane.showMessageDialog(null, "El lavarropas ha sido borrado");
					cargar_grilla();
				}
				catch (conException e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				catch (Exception e){
					
				}
			}
		}
		
	}

	protected void modificar()
	{
		int i=tblListado.getSelectedRowCount();
		if(i==0)
		{
			JOptionPane.showMessageDialog(null, "Debe seleccionar el lavarropas a modificar");
		}
		else
		{
			i = tblListado.getSelectedRow();
			FormAMLavarropas form = new FormAMLavarropas();
			form.modoModificar((Integer)tblListado.getValueAt(i, 0),tblListado.getValueAt(i, 1).toString(),tblListado.getValueAt(i, 2).toString(),
								tblListado.getValueAt(i, 5).toString(), tblListado.getValueAt(i, 4).toString(), tblListado.getValueAt(i, 3).toString());
			form.setVisible(true);
			cargar_grilla();
		}
	}
}