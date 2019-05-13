/**
 *This page is where Admin enters a new ticket. 
 * @author mohammadsalehi
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class NewTicketAdmin extends JFrame {
	int userID;
	public  NewTicketAdmin(int userID) throws SQLException {
		this.userID=userID;
		//super("Enter New Ticket");
		Dao dao = new Dao();
		java.sql.Statement statement= dao.connect().createStatement();
		setSize(500, 300);
		setLayout(new GridLayout(3, 2));
		setLocationRelativeTo(null); // centers window
		
		JLabel DescriptionLabel = new JLabel("Enter Description ", JLabel.CENTER);
		JTextField textDescription = new JTextField();
		  
		JLabel lblStatus = new JLabel("", JLabel.LEFT);
		
		
		JButton exitBTN  = new JButton("EXIT");
		JButton submitBTN  = new JButton("Submit");
		JButton back  = new JButton("Back");
		
		//Adding the labels and buttons to the frame.
		add(DescriptionLabel);
		add(textDescription);
		add(submitBTN);
		add(back);
		add(exitBTN);
		add(lblStatus);
		
		// Action Listerners for Buttons.
		back.addActionListener(new ActionListener() {
			    @Override
			    public void actionPerformed(ActionEvent e) {
			    	new Tickets(userID);
					//dispose();
			    }
			});
		submitBTN.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	try {
    	    		String sql = "INSERT INTO msala_tickets (tdescription, Date_Entered, User_ID, Note) VALUES "
    	    				+"('"+textDescription.getText()+"', NOW(), "+userID+", NULL)";
    	    		statement.executeUpdate(sql);
    	    		sql = "SELECT MAX(ticket_id) AS ID FROM msala_tickets ";
    	    		ResultSet rs = statement.executeQuery(sql);
    				rs.next();
    				int newID = rs.getInt("ID");
    				lblStatus.setText("Thanks, your ticket Number is"
    						+ "  "+newID);	
    	        }
    	        catch (SQLException ex) {
    	                ex.printStackTrace();
    	        }
    	   }
		});
		exitBTN.addActionListener(e -> System.exit(0));
		setVisible(true); // SHOW THE FRAME
	}
}
