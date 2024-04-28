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

import gui.application.form.LoginForm;
import gui.application.form.MainForm;
import raven.toast.Notifications;

public class Application extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Application app;
	private final MainForm mainForm;
	private final LoginForm loginForm;

	private Application() {
		initComponents();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setLocationRelativeTo(null);
		mainForm = new MainForm();
		loginForm = new LoginForm();

//		setContentPane(loginForm);
		app = this;

		FlatAnimatedLafChange.showSnapshot();
		app.setContentPane(app.mainForm);
		app.mainForm.applyComponentOrientation(app.getComponentOrientation());
		Application.setSelectedMenu(0, 0);
		app.mainForm.hideMenu();
		SwingUtilities.updateComponentTreeUI(app.getMainForm());
		FlatAnimatedLafChange.hideSnapshotWithAnimation();
		Notifications.getInstance().setJFrame(this);

	}

	public static Application getInstance() {
		return app;
	}

	public MainForm getMainForm() {
		return mainForm;
	}

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public static void showForm(Component component) {
		component.applyComponentOrientation(app.getComponentOrientation());
		app.mainForm.showForm(component);
	}

	public static void logout() {
		app.loginForm.resetLogin();
		FlatAnimatedLafChange.showSnapshot();
		app.setContentPane(app.loginForm);
		app.loginForm.applyComponentOrientation(app.getComponentOrientation());
		SwingUtilities.updateComponentTreeUI(app.loginForm);
		FlatAnimatedLafChange.hideSnapshotWithAnimation();
	}

	public static void setSelectedMenu(int index, int subIndex) {
		app.mainForm.setSelectedMenu(index, subIndex);
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
