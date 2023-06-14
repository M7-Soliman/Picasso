package picasso.view.commands;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;

import javax.swing.JTextField;

import picasso.model.Pixmap;
import picasso.util.Command;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class History implements Command<Pixmap>, ActionListener {
	
	public static String input;
	public static String output_str;
	JTextField textfield;
	JFrame frame;

	public History(JTextField tf) {
		this.textfield = tf;
	}
	
	public void openWindow()
    {
        // creating instance of JFrame with name "first way"
        frame = new JFrame("Expression History");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        // sets 500 width and 600 height
        frame.setSize(500, 600);
         
        // uses no layout managers
        frame.setLayout(null);
         
        // makes the frame visible
        frame.setVisible(true);
        JLabel HistoryLabel = new JLabel("History shows up to your last 10 inputted expressions:");
        HistoryLabel.setBounds(35, 0, 450, 30);
        HistoryLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(HistoryLabel);
        
        JLabel helpLabel = new JLabel("Most recent to least recent expression:");
        helpLabel.setBounds(5, 35, 400, 30);
        helpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        frame.add(helpLabel);
        
        int Ybound = 30;
        int x = 0;
        
        if (HistoryFolder.HistoryList != null) {
        	for (int i = 0; i < HistoryFolder.HistoryList.size(); i++) {
        		String newButton = HistoryFolder.HistoryList.get(i);
        		//System.out.println(newButton);
        		JButton button = new JButton(newButton);
        		button.setName(newButton);
        		button.setBounds(250, Ybound, 90, 42);
        		Ybound += 52;
        		button.addActionListener(this);
        		frame.add(button);
        	}
        }	
        		
        		//frame.setContentPane(button);
        else {
        	JButton button = new JButton("No Expressions in History (Creates all white image)");
        	button.setName("No Expressions in History (Creates all white image)");
        	button.addActionListener(this);
        	frame.add(button);
        	frame.setContentPane(button);
        }

    }
	
	@Override
    public void actionPerformed(ActionEvent e) {
		
        if (((Component) e.getSource()).getName() == "No Expressions in History (Creates all white image)")
        {
        	input = "[1,1,1]";
        	textfield.setText(input);
        }
        else 
        {
        	input = ((Component) e.getSource()).getName();
        	textfield.setText(input);
        	//System.out.println(input);
        }
        frame.dispose();
    }
	
	@Override
	public void execute(Pixmap target) {
		this.openWindow();
	}
			
}
