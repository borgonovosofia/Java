package presentacion;


import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import utilidades.conException;
import data.TelevisorAdapter;
import negocio.Television;

public class FormListadoTelevisores extends FormListadoBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FormListadoTelevisores() {
		tblListado.setForeground(UIManager.getColor("inactiveCaptionText"));
		tblListado.setBackground(UIManager.getColor("inactiveCaptionBorder"));
		setBounds(100, 100, 772, 441);
		
		lblNuevoElectrodomstico.setText("Listado de televisores");		
		cargar_grilla();
	}
	protected void cargar_grilla()
	{
		DefaultTableModel dtm;
		try {
			dtm = Television.getTelevisores();
			tblListado.setModel(dtm);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		tblListado.getColumnModel().getColumn(0).setMaxWidth(17);
		tblListado.getColumnModel().getColumn(0).setMinWidth(17);
		tblListado.getColumnModel().getColumn(0).setPreferredWidth(17);		
	}
	protected void nuevo()
	{
		try {
			FormAMTelevision frame = new FormAMTelevision();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void borrar()
	{
		int i=tblListado.getSelectedRowCount();
		if(i==0)
		{
			JOptionPane.showMessageDialog(null, "Debe seleccionar el televisor a borrar");
		}
		else
		{
			int respuesta =JOptionPane.showConfirmDialog(null, "Está seguro que desea eliminar el televisor?", "Confirmar borrado", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if(respuesta == JOptionPane.YES_OPTION)
			{
				i = tblListado.getSelectedRow();
				TelevisorAdapter adapter = new TelevisorAdapter();
				try {
					adapter.borrarTelevision((int)tblListado.getValueAt(i, 0));
				} catch (conException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
				JOptionPane.showMessageDialog(null, "El televisor ha sido borrado");
				cargar_grilla();
			}

			
		}
		
	}
	protected void modificar()
	{
		int i=tblListado.getSelectedRowCount();
		if(i==0)
		{
			JOptionPane.showMessageDialog(null, "Debe seleccionar el televisor a modificar");
		}
		else
		{
			i = tblListado.getSelectedRow();
			boolean sint;
			if(tblListado.getValueAt(i, 5).toString().equals("Si"))
			{	sint=true;	}
			else
			{	sint = false;	}
				
			FormAMTelevision form = new FormAMTelevision();
			form.modoModificar((int)tblListado.getValueAt(i, 0), tblListado.getValueAt(i, 1).toString(), tblListado.getValueAt(i, 2).toString(), 
					tblListado.getValueAt(i, 6).toString(), tblListado.getValueAt(i, 4).toString(), tblListado.getValueAt(i, 3).toString(), sint);

			form.setVisible(true);
			cargar_grilla();
		}
	}
	
}
