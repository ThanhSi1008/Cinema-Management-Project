package gui.application.form.other;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class AddMovieDialog extends JDialog {

	private JLabel movieNameLabel;
	private JTextField movieNameTextField;
	private JLabel directorLabel;
	private JTextField directorTextField;
	private JLabel durationLabel;
	private JTextField durationTextField;
	private JLabel importPriceLabel;
	private JTextField importPriceTextField;
	private JLabel countryLabel;
	private JTextField countryTextField;
	private JLabel languageLabel;
	private JTextField languageTextField;
	private JLabel imageSourceLabel;
	private JTextField imageSourceTextField;
	private JLabel releasedDateLabel;
	private JTextField releasedDateTextField;
	private JLabel statusLabel;
	private JTextField statusTextField;
	private JLabel trailerLabel;
	private JTextField trailerTextField;
	private JLabel genreLabel;
	private JTextField genreTextField;
	private JLabel descriptionLabel;
	private JTextField descriptionTextField;
	protected File selectedFile;
	protected JFileChooser fileChooser;

	public AddMovieDialog() {
		setLayout(new BorderLayout());

		movieNameLabel = new JLabel("Name: ");
		movieNameTextField = new JTextField(20);
		directorLabel = new JLabel("Director: ");
		directorTextField = new JTextField(20);
		durationLabel = new JLabel("Duration: ");
		durationTextField = new JTextField(20);
		importPriceLabel = new JLabel("Price: ");
		importPriceTextField = new JTextField(20);
		countryLabel = new JLabel("Country: ");
		countryTextField = new JTextField(20);
		languageLabel = new JLabel("Language: ");
		languageTextField = new JTextField(20);
		imageSourceLabel = new JLabel("Image: ");
		JButton imageSourceButton = new JButton("Choose Image");
		releasedDateLabel = new JLabel("ReleasedDate: ");
		releasedDateTextField = new JTextField(20);
		statusLabel = new JLabel("Status: ");
		statusTextField = new JTextField(20);
		trailerLabel = new JLabel("Trailer: ");
		trailerTextField = new JTextField(20);
		genreLabel = new JLabel("Genre: ");
		genreTextField = new JTextField(20);
		descriptionLabel = new JLabel("Description: ");
		descriptionTextField = new JTextField(20);

		Box box1 = Box.createHorizontalBox();
		// comp1
		Box movieNameBox = Box.createVerticalBox();
		movieNameBox.add(movieNameLabel);
		movieNameBox.add(movieNameTextField);
		// comp2
		Box directorBox = Box.createVerticalBox();
		directorBox.add(directorLabel);
		directorBox.add(directorTextField);
		// comp3
		Box durationBox = Box.createVerticalBox();
		durationBox.add(durationLabel);
		durationBox.add(directorTextField);
		// comp4
		Box priceBox = Box.createVerticalBox();
		priceBox.add(importPriceLabel);
		priceBox.add(importPriceTextField);
		// add to box1
		box1.add(movieNameBox);
		box1.add(directorBox);
		box1.add(priceBox);

		Box box2 = Box.createHorizontalBox();
		// comp1
		Box languageBox = Box.createVerticalBox();
		languageBox.add(languageLabel);
		languageBox.add(languageTextField);
		// comp2
		Box conuntryBox = Box.createVerticalBox();
		conuntryBox.add(countryLabel);
		conuntryBox.add(countryTextField);
		// comp3
		Box imageSourceBox = Box.createVerticalBox();
		imageSourceBox.add(imageSourceLabel);
		imageSourceBox.add(imageSourceButton);
		// add to box2
		box2.add(languageBox);
		box2.add(conuntryBox);
		box2.add(imageSourceBox);

		Box container = Box.createVerticalBox();
		container.add(box1);
		container.add(box2);

		add(container);

		pack();
		setLocationRelativeTo(null);

		imageSourceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Choose Image File");

				fileChooser.setFileFilter(new FileNameExtensionFilter("Image files", "jpg", "jpeg", "png", "gif"));

				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					// Here, you can use the selected file (e.g., display it in an image component)
					System.out.println("Selected file: " + selectedFile.getAbsolutePath());
				}
			}
		});

	}

}
