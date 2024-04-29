package gui.application;

import java.awt.Component;
import java.awt.Font;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import gui.application.form.EmployeeForm;
import gui.application.form.LoginForm;
import gui.application.form.ManagerForm;

public class Application extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Application app;
	private ManagerForm managerForm;
	private EmployeeForm employeeForm;
	private LoginForm loginForm;

	private Application() {
		initComponents();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		loginForm = new LoginForm();
//		setContentPane(loginForm);
		app = this;
		app.createManagerForm();
		FlatAnimatedLafChange.showSnapshot();
		app.setContentPane(app.getManagerForm());
		app.getManagerForm().applyComponentOrientation(app.getComponentOrientation());
		Application.setSelectedMenuForManager(0, 0);
		app.getManagerForm().hideMenu();
		SwingUtilities.updateComponentTreeUI(app.getManagerForm());
		FlatAnimatedLafChange.hideSnapshotWithAnimation();
	}

	public static Application getInstance() {
		return app;
	}

	public ManagerForm getManagerForm() {
		return managerForm;
	}

	public EmployeeForm getEmployeeForm() {
		return employeeForm;
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void createManagerForm() {
		managerForm = new ManagerForm();
	}

	public void createEmployeeForm() {
		employeeForm = new EmployeeForm();
	}

	public static void showManagerForm(Component component) {
		component.applyComponentOrientation(app.getComponentOrientation());
		app.managerForm.showForm(component);
	}

	public static void showEmployeeForm(Component component) {
		component.applyComponentOrientation(app.getComponentOrientation());
		app.employeeForm.showForm(component);
	}

	public static void setSelectedMenuForManager(int index, int subIndex) {
		app.managerForm.setSelectedMenu(index, subIndex);
	}

	public static void setSelectedMenuForEmployee(int index, int subIndex) {
		app.employeeForm.setSelectedMenu(index, subIndex);
	}

	public static void logout() {
		app.loginForm.resetLogin();
		FlatAnimatedLafChange.showSnapshot();
		app.setContentPane(app.loginForm);
		app.loginForm.applyComponentOrientation(app.getComponentOrientation());
		SwingUtilities.updateComponentTreeUI(app.loginForm);
		FlatAnimatedLafChange.hideSnapshotWithAnimation();
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(true);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 719, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 521, Short.MAX_VALUE));

		pack();
	}

	public static void main(String args[]) {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("gui.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 16));
		int now = LocalTime.now().getHour();
		if (now >= 6 && now <= 18) {
			FlatMacLightLaf.setup();
		} else {
			FlatMacDarkLaf.setup();
		}
		SwingUtilities.invokeLater(() -> {
			new Application().setVisible(true);
		});
	}

}
