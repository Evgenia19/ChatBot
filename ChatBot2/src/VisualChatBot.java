import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;


public class VisualChatBot extends JFrame implements ActionListener {
	
	MultiUser multiUser = new MultiUser();

	final String TITLE_OF_PROGRAM = "Шахттер";
	final int START_LOCATION = 200;
	final int WINDOW_WIDTH = 350;
	final int WINDOW_HEIGHT = 450;
	final String CHB_AI = "AI";
	final String BTN_ENTER = "Enter";

	JTextPane dialogue;
	JCheckBox ai;
	JTextField message;
	ChatBot bot;
	SimpleAttributeSet botStyle;

	public static void main(String[] args) {
		new VisualChatBot();
	}

	VisualChatBot() {
		setTitle(TITLE_OF_PROGRAM);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_HEIGHT);
		dialogue = new JTextPane();
		dialogue.setEditable(false);
		dialogue.setContentType("text/html");
		JScrollPane scrollBar = new JScrollPane(dialogue);
		botStyle = new SimpleAttributeSet();
		StyleConstants.setItalic(botStyle, true);
		StyleConstants.setForeground(botStyle, Color.magenta);
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
		bot = new ChatBot();
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		//MultiUser multiUser = new MultiUser();
		if (message.getText().trim().length() > 0) {
			try {
				//Message text = multiUser.respondTo(new Message())
				StyledDocument doc = dialogue.getStyledDocument();
				doc.insertString(doc.getLength(), message.getText() + "\n", new SimpleAttributeSet());
				doc.insertString(doc.getLength(),
						"Шахттер: " + ChatBot.sayInReturn(message.getText()) + "\n", botStyle);
			} catch (Exception e) {
			}
		}
		message.setText("");
		message.requestFocusInWindow();
	}
}
