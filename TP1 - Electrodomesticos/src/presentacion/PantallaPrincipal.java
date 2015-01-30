package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.JDesktopPane;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class PantallaPrincipal {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal window = new PantallaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PantallaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 392, 427);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblElectrodomsticos = new JLabel("Electrodom\u00E9sticos");
		lblElectrodomsticos.setHorizontalAlignment(SwingConstants.CENTER);
		lblElectrodomsticos.setFont(new Font("Segoe UI Semibold", Font.BOLD, 26));
		JPanel panelLavarropas = new JPanel();
		panelLavarropas.setBorder(new LineBorder(Color.DARK_GRAY));
		
		JPanel panelTelevisores = new JPanel();
		panelTelevisores.setBorder(new LineBorder(Color.DARK_GRAY));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblElectrodomsticos, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelLavarropas, GroupLayout.PREFERRED_SIZE, 348, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panelTelevisores, GroupLayout.PREFERRED_SIZE, 348, Short.MAX_VALUE)
					.addGap(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblElectrodomsticos)
					.addGap(18)
					.addComponent(panelLavarropas, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(panelTelevisores, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(13, Short.MAX_VALUE))
		);
		JButton btnNuevoTelevisor = new JButton("Nuevo televisor");
		btnNuevoTelevisor.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNuevoTelevisor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				nuevoTelevisor();
			}
		});
		
		JButton btnListadoDeTelevisores = new JButton("Listado de televisores");
		btnListadoDeTelevisores.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnListadoDeTelevisores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				listadoTelevisores();
			}
		});
		
		JDesktopPane desktopPane = new JDesktopPane();
		
		JLabel lblTelevisores = new JLabel("Televisores");
		lblTelevisores.setHorizontalAlignment(SwingConstants.CENTER);
		lblTelevisores.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		GroupLayout gl_panelTelevisores = new GroupLayout(panelTelevisores);
		gl_panelTelevisores.setHorizontalGroup(
			gl_panelTelevisores.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelTelevisores.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panelTelevisores.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTelevisores.createSequentialGroup()
							.addComponent(lblTelevisores, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelTelevisores.createSequentialGroup()
							.addGap(109)
							.addComponent(btnNuevoTelevisor)
							.addPreferredGap(ComponentPlacement.RELATED, 106, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelTelevisores.createSequentialGroup()
							.addGap(93)
							.addComponent(btnListadoDeTelevisores)
							.addPreferredGap(ComponentPlacement.RELATED, 92, GroupLayout.PREFERRED_SIZE)))
					.addGap(145))
		);
		gl_panelTelevisores.setVerticalGroup(
			gl_panelTelevisores.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelTelevisores.createSequentialGroup()
					.addGroup(gl_panelTelevisores.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelTelevisores.createSequentialGroup()
							.addGap(73)
							.addComponent(desktopPane, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelTelevisores.createSequentialGroup()
							.addGap(25)
							.addComponent(lblTelevisores, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNuevoTelevisor)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnListadoDeTelevisores)))
					.addContainerGap(26, Short.MAX_VALUE))
		);
		panelTelevisores.setLayout(gl_panelTelevisores);
		
		JButton btnListadoDeLavarropas = new JButton("Listado de Lavarropas");
		btnListadoDeLavarropas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnListadoDeLavarropas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				listadoLavarropas();
			}
		});
		
		JButton btnNuevoLavarropas = new JButton("Nuevo Lavarropas");
		btnNuevoLavarropas.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnNuevoLavarropas.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				nuevoLavarropas();
			}
		});
		
		JLabel lblLavarropas = new JLabel("Lavarropas");
		lblLavarropas.setHorizontalAlignment(SwingConstants.CENTER);
		lblLavarropas.setFont(new Font("Segoe UI Semibold", Font.PLAIN, 23));
		GroupLayout gl_panelLavarropas = new GroupLayout(panelLavarropas);
		gl_panelLavarropas.setHorizontalGroup(
			gl_panelLavarropas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLavarropas.createSequentialGroup()
					.addGroup(gl_panelLavarropas.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelLavarropas.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_panelLavarropas.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblLavarropas, GroupLayout.PREFERRED_SIZE, 322, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panelLavarropas.createSequentialGroup()
									.addComponent(btnNuevoLavarropas)
									.addGap(99))))
						.addGroup(gl_panelLavarropas.createSequentialGroup()
							.addGap(103)
							.addComponent(btnListadoDeLavarropas)))
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_panelLavarropas.setVerticalGroup(
			gl_panelLavarropas.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLavarropas.createSequentialGroup()
					.addGap(20)
					.addComponent(lblLavarropas, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnNuevoLavarropas)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnListadoDeLavarropas)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		panelLavarropas.setLayout(gl_panelLavarropas);
		frame.getContentPane().setLayout(groupLayout);
	}

	public void listadoTelevisores()
	{
		try {
			FormListadoTelevisores frame = new FormListadoTelevisores();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void nuevoTelevisor()
	{
		try {
			FormAMTelevision frame = new FormAMTelevision();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void listadoLavarropas()
	{
		try {
			FormListadoLavarropas frame = new FormListadoLavarropas();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void nuevoLavarropas()
	{
		try {
			FormAMLavarropas frame = new FormAMLavarropas();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}