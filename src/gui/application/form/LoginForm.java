package gui.application.form;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.util.UIScale;

import controller.Ctrl_LoginForm;
import gui.application.Application;

public class LoginForm extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton cmdLogin;
	private JLabel lbPass;
	private JLabel lbTitle;
	private JLabel lbUser;
	private JPanel login;
	private JPasswordField txtPass;
	private JTextField txtUser;
	private Ctrl_LoginForm ctrl_LoginForm;

	public LoginForm() {
		ctrl_LoginForm = new Ctrl_LoginForm();
		initComponents();
		init();
		addEvents();

		// test
//		txtUser.setText("admin");
//		txtPass.setText("123");
	}

	public void resetLogin() {
		txtUser.setText("");
		txtPass.setText("");
		txtUser.requestFocus();
	}

	private void init() {

		setLayout(new LoginFormLayout());
		login.setLayout(new LoginLayout());
		lbTitle.putClientProperty(FlatClientProperties.STYLE, "" + "font:$h1.font");
		login.putClientProperty(FlatClientProperties.STYLE,
				"" + "background:$Login.background;" + "arc:20;" + "border:30,40,50,30");

		txtPass.putClientProperty(FlatClientProperties.STYLE, "" + "showRevealButton:true;" + "showCapsLock:true");
		cmdLogin.putClientProperty(FlatClientProperties.STYLE, "" + "borderWidth:0;" + "focusWidth:0");
		txtUser.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "User Name");
		txtPass.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
	}

	private void addEvents() {
		cmdLogin.addActionListener(e -> {
			String username = txtUser.getText().trim();
			String password = new String(txtPass.getPassword());

			boolean isValid = ctrl_LoginForm.checkCredentials(username, password);
			String role = ctrl_LoginForm.getRole(username);

			if (!isValid) {
				JOptionPane.showMessageDialog(this, "User name or password is incorrect!", "Error", 0);
				Application.getInstance().getLoginForm().resetLogin();
			} else {
				Application.getInstance().setRole(role);
				Application.getInstance().createMainForm();
				FlatAnimatedLafChange.showSnapshot();
				Application.getInstance().setContentPane(Application.getInstance().getMainForm());
				Application.getInstance().getMainForm()
						.applyComponentOrientation(Application.getInstance().getComponentOrientation());
				Application.setSelectedMenu(0, 0);
				Application.getInstance().getMainForm().hideMenu();
				SwingUtilities.updateComponentTreeUI(Application.getInstance().getMainForm());
				FlatAnimatedLafChange.hideSnapshotWithAnimation();
			}
		});

		KeyListener keyEnterListener = new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					cmdLogin.doClick();
				}
			}
		};

		txtUser.addKeyListener(keyEnterListener);
		txtPass.addKeyListener(keyEnterListener);
	}

	private void initComponents() {

		login = new JPanel();
		cmdLogin = new JButton();
		lbTitle = new JLabel();
		lbUser = new JLabel();
		txtUser = new JTextField();
		lbPass = new JLabel();
		txtPass = new JPasswordField();

		cmdLogin.setText("Login");

		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setText("Login");

		lbUser.setText("User Name");

		lbPass.setText("Password");

		GroupLayout loginLayout = new GroupLayout(login);
		login.setLayout(loginLayout);
		loginLayout.setHorizontalGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(loginLayout.createSequentialGroup().addGroup(loginLayout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(loginLayout.createSequentialGroup().addGroup(loginLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(loginLayout.createSequentialGroup().addGap(15, 15, 15).addComponent(lbUser))
								.addGroup(loginLayout.createSequentialGroup().addContainerGap().addComponent(txtUser,
										GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)))
								.addGap(0, 36, Short.MAX_VALUE))
						.addGroup(loginLayout.createSequentialGroup().addContainerGap().addGroup(loginLayout
								.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addComponent(lbTitle, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(loginLayout.createSequentialGroup()
										.addGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addComponent(lbPass).addComponent(txtPass, GroupLayout.PREFERRED_SIZE,
														100, GroupLayout.PREFERRED_SIZE))
										.addGap(0, 0, Short.MAX_VALUE)))))
						.addContainerGap())
				.addGroup(loginLayout.createSequentialGroup().addContainerGap().addComponent(cmdLogin)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		loginLayout.setVerticalGroup(
				loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING,
						loginLayout.createSequentialGroup().addContainerGap().addComponent(lbTitle).addGap(10, 10, 10)
								.addComponent(lbUser).addGap(5, 5, 5)
								.addComponent(txtUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10, 10, 10).addComponent(lbPass).addGap(5, 5, 5)
								.addComponent(txtPass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addGap(50, 50, 50).addComponent(cmdLogin).addContainerGap()));

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addGap(204, 204, 204).addComponent(login, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(319, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout
						.createSequentialGroup().addGap(68, 68, 68).addComponent(login, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(179, Short.MAX_VALUE)));
	}

	private class LoginFormLayout implements LayoutManager {

		@Override
		public void addLayoutComponent(String name, Component comp) {
		}

		@Override
		public void removeLayoutComponent(Component comp) {
		}

		@Override
		public Dimension preferredLayoutSize(Container parent) {
			synchronized (parent.getTreeLock()) {
				return new Dimension(0, 0);
			}
		}

		@Override
		public Dimension minimumLayoutSize(Container parent) {
			synchronized (parent.getTreeLock()) {
				return new Dimension(0, 0);
			}
		}

		@Override
		public void layoutContainer(Container parent) {
			synchronized (parent.getTreeLock()) {
				int width = parent.getWidth();
				int height = parent.getHeight();
				int loginWidth = UIScale.scale(320);
				int loginHeight = login.getPreferredSize().height;
				int x = (width - loginWidth) / 2;
				int y = (height - loginHeight) / 2;
				login.setBounds(x, y, loginWidth, loginHeight);
			}
		}
	}

	private class LoginLayout implements LayoutManager {

		private final int titleGap = 10;
		private final int textGap = 10;
		private final int labelGap = 5;
		private final int buttonGap = 50;

		@Override
		public void addLayoutComponent(String name, Component comp) {
		}

		@Override
		public void removeLayoutComponent(Component comp) {
		}

		@Override
		public Dimension preferredLayoutSize(Container parent) {
			synchronized (parent.getTreeLock()) {
				Insets insets = parent.getInsets();
				int height = insets.top + insets.bottom;

				height += lbTitle.getPreferredSize().height;
				height += UIScale.scale(titleGap);
				height += lbUser.getPreferredSize().height;
				height += UIScale.scale(labelGap);
				height += txtUser.getPreferredSize().height;
				height += UIScale.scale(textGap);

				height += lbPass.getPreferredSize().height;
				height += UIScale.scale(labelGap);
				height += txtPass.getPreferredSize().height;
				height += UIScale.scale(buttonGap);
				height += cmdLogin.getPreferredSize().height;
				return new Dimension(0, height);
			}
		}

		@Override
		public Dimension minimumLayoutSize(Container parent) {
			synchronized (parent.getTreeLock()) {
				return new Dimension(0, 0);
			}
		}

		@Override
		public void layoutContainer(Container parent) {
			synchronized (parent.getTreeLock()) {
				Insets insets = parent.getInsets();
				int x = insets.left;
				int y = insets.top;
				int width = parent.getWidth() - (insets.left + insets.right);

				lbTitle.setBounds(x, y, width, lbTitle.getPreferredSize().height);
				y += lbTitle.getPreferredSize().height + UIScale.scale(titleGap);

				lbUser.setBounds(x, y, width, lbUser.getPreferredSize().height);
				y += lbUser.getPreferredSize().height + UIScale.scale(labelGap);
				txtUser.setBounds(x, y, width, txtUser.getPreferredSize().height);
				y += txtUser.getPreferredSize().height + UIScale.scale(textGap);

				lbPass.setBounds(x, y, width, lbPass.getPreferredSize().height);
				y += lbPass.getPreferredSize().height + UIScale.scale(labelGap);
				txtPass.setBounds(x, y, width, txtPass.getPreferredSize().height);
				y += txtPass.getPreferredSize().height + UIScale.scale(buttonGap);

				cmdLogin.setBounds(x, y, width, cmdLogin.getPreferredSize().height);
			}
		}
	}

}
