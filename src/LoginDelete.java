/*
 * This class allow the user to log in see his tickets, delete his tickets. 
 * As well as it allows the admin to log in and view, delete, or udpate a ticket. 
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.xdevapi.Statement;

class LoginDelete extends JFrame {
	int userID;
	public  LoginDelete(int userID) throws SQLException {
		this.userID=userID;
	//super("Deleting A Ticket");

	Dao conn = new Dao();
	java.sql.Statement statement= conn.connect().createStatement();
	setSize(500, 300);
	setLayout(new GridLayout(3, 2));
	setLocationRelativeTo(null); // centers window
	
	// SET UP CONTROLS
	JLabel lblTicketID = new JLabel("Ticket ID", JLabel.LEFT);
	JLabel lblStatus = new JLabel(" ", JLabel.LEFT);
	// Creating text-file
	JTextField textTicketID = new JTextField(10);
	
	
	JButton btn = new JButton("Submit");
	JButton backBtn = new JButton("Back");
	JButton btnExit = new JButton("Exit");

	
	// constraints
	lblTicketID.setHorizontalAlignment(JLabel.CENTER);
	
	// ADD OBJECTS TO FRAME
	add(lblTicketID);// 1st row filler
	add(textTicketID); // 2nd row

	
	add(btn); // 3rd row
	add(btnExit);
	add(backBtn);
	add(lblStatus);
	
   btn.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    	try {
    		String
    		sql = "DELETE FROM msala_tickets WHERE ticket_id="+ textTicketID.getText();
    		statement.executeUpdate(sql);
    		lblStatus.setText("This ticket has been deleted." );
            //dispose(); // CLOSE OUT THE WINDOW
        }
        catch (SQLException ex) {
                ex.printStackTrace();
        }
    }
});
   
   backBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	new Tickets(userID);
			dispose();
	    }
	});

   btnExit.addActionListener(e -> System.exit(0));
   setVisible(true); // SHOW THE FRAME
}
}
