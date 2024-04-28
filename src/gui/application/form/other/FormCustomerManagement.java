package gui.application.form.other;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FormCustomerManagement extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtSearch;
	private JButton btnSearch;
	private JComboBox<String> comboBoxFilter;
	private JTable tableMovies;

	public FormCustomerManagement() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new BorderLayout());

		// Create a panel for top section
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

		// Add title label
		JLabel lblTitle = new JLabel("CUSTOMER MANAGEMENT");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setForeground(Color.LIGHT_GRAY);
		topPanel.add(lblTitle);
		topPanel.add(Box.createVerticalStrut(20)); // Add vertical space

		// Add search components
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
		JLabel lblSearch = new JLabel("Search:");
		txtSearch = new JTextField(10);
		btnSearch = new JButton("Search");
		comboBoxFilter = new JComboBox<>();
		comboBoxFilter.addItem("Filter 1");
		comboBoxFilter.addItem("Filter 2");
		comboBoxFilter.addItem("Filter 3");
		searchPanel.add(lblSearch);
		searchPanel.add(txtSearch);
		searchPanel.add(btnSearch);
		searchPanel.add(comboBoxFilter);
		topPanel.add(searchPanel);
		topPanel.add(Box.createVerticalStrut(10)); // Add vertical space

		// Add top panel to the main panel
		add(topPanel, BorderLayout.NORTH);

		// Create table for movie display
		String[] columnHeaders = { "ID", "Name", "Phone Number", "Email", "Registration Date" };
		DefaultTableModel tableModel = new DefaultTableModel(columnHeaders, 0);
		tableMovies = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(tableMovies);
		add(scrollPane, BorderLayout.CENTER);
	}
}
