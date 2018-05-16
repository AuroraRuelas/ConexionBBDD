package codigoBDaSwing;

import java.awt.EventQueue;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Controller.controller;
import conn.TestConexionW;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class form extends controller{

	private JFrame frame;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	

	public static void main(String[] args) {

		form window = new form();
		window.frame.setVisible(true);

	}

	/**
	 * Create the application.
	 */
	public form() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane();

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {  },
				new String[] {}));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.PREFERRED_SIZE, 434, GroupLayout.PREFERRED_SIZE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(scrollPane,
				GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE));
		frame.getContentPane().setLayout(groupLayout);
	}

}
