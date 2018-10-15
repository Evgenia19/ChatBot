import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;

public class VisualBot extends JFrame implements ActionListener {

	final String TITLE_OF_PROGRAM = "Шахттер";
	final int START_LOCATION = 200;
	final int WINDOW_WIDTH = 350;
	final int WINDOW_HEIGHT = 450;
	final String BTN_ENTER = "Enter";

	JTextPane dialogue;
	JTextField message;
	ChatBot sbot;
	SimpleAttributeSet botStyle;
	SimpleAttributeSet userStyle;
	String id = null;
	MultiUsersBot multiusers = new MultiUsersBot();

	public static void main(String[] args) {
		new VisualBot();
	}

	VisualBot() {
		setTitle(TITLE_OF_PROGRAM);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
		dialogue = new JTextPane();
		dialogue.setEditable(false);
		dialogue.setContentType("text/html");
		dialogue.setBackground(Color.white);
		JScrollPane scrollBar = new JScrollPane(dialogue);
		botStyle = new SimpleAttributeSet();
		StyleConstants.setItalic(botStyle, true);
		StyleConstants.setForeground(botStyle, Color.blue);
		userStyle = new SimpleAttributeSet();
		StyleConstants.setItalic(userStyle, true);
		StyleConstants.setForeground(userStyle, Color.magenta);
		JPanel bp = new JPanel();
		bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));
		message = new JTextField();
		message.addActionListener(this);
		JButton enter = new JButton(BTN_ENTER);
		enter.addActionListener(this);
		bp.add(message);
		bp.add(enter);
		add(BorderLayout.CENTER, scrollBar);
		add(BorderLayout.SOUTH, bp);
		setVisible(true);
		StyledDocument doc = dialogue.getStyledDocument();
		try {
			doc.insertString(doc.getLength(),"Шаахтер: Ведите ваш ник" + "\n", botStyle);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		if (message.getText().trim().length() > 0) {
			try {
				StyledDocument doc = dialogue.getStyledDocument();
				if(id == null) {
					if(multiusers.addUser(message.getText())) {
						id = message.getText();
						doc.insertString(doc.getLength(), id + ": " + message.getText() + "\n", userStyle);
						doc.insertString(doc.getLength(), "Шаахтер: Рад с вами познокомиться!" + "\n", botStyle);
					}
					else
						doc.insertString(doc.getLength(),"Шаахттер: Ведите другой ник" + "\n", botStyle);
				}
				else {
					doc.insertString(doc.getLength(), id + ": " + message.getText() + "\n", userStyle);
					User msg = multiusers.Users(new User(id ,message.getText()));
					doc.insertString(doc.getLength(),
						"Шаaхтер: " + msg.content + "\n", botStyle);
				}
			} catch (Exception e) {
			}
		}
		message.setText("");
		message.requestFocusInWindow();
	}
}
