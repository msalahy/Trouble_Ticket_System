/**
 * 
 * We will make a ticket system for saving a ticket system.
 * Opening the ticket, reviewing, and closing. 
 * Here we the Login Class for both Admin and Regular USer
 *If Admin sign in we will show a different page, and if user log ins, 
 *it is going to be a different view. 
 * @author mohammadsalehi
 */

import java.awt.GridLayout; //useful for layouts
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//controls-label text fields, button
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

// Main Class at the Log in. 
public class Login extends JFrame {
    Dao conn = new Dao();
    
    public Login() {
        super("IIT HELP DESK LOGIN");
        conn = new Dao();
        setSize(500, 300);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null); // centers window

        // SET UP CONTROLS
        JLabel lblUsername = new JLabel("Username", JLabel.LEFT);
        JLabel lblPassword = new JLabel("Password", JLabel.LEFT);
        JLabel lblStatus = new JLabel(" ", JLabel.LEFT);
        

        // Creating text-file
        JTextField txtUname = new JTextField(10);
        JPasswordField txtPassword = new JPasswordField();
        JButton btn = new JButton("Submit");
        JButton btnExit = new JButton("Exit");

        
        // constraints
        lblStatus.setToolTipText("Contact help desk to unlock password");
        lblUsername.setHorizontalAlignment(JLabel.CENTER);
        lblPassword.setHorizontalAlignment(JLabel.CENTER);

        // ADD OBJECTS TO FRAME
        add(lblUsername);// 1st row filler
        add(txtUname);
        add(lblPassword); // 2nd row
        add(txtPassword);
        add(btn); // 3rd row
        add(btnExit);
        add(lblStatus); // 4th row
        
        // Action Buttons  
        btn.addActionListener(new ActionListener() {
            int count = 0; // count agent
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean admin = false;
                count = count + 1;
                
                // verify credentials of user (and Loging in. )
                String query = "SELECT * FROM msalah_users WHERE uname = ? and upass = ?;";
                try (PreparedStatement stmt = conn.connect().prepareStatement(query)) {
                    stmt.setString(1, txtUname.getText());
                    stmt.setString(2, txtPassword.getText());
                    ResultSet rs = stmt.executeQuery();
                    
                    if (rs.next()) {
                        admin = rs.getBoolean("admin"); // get table column value
                        if (admin) {
                        	lblStatus.setText("You are logged in as Admin. ");
                        	dispose();
                        	new Tickets(admin);
                        }
                        else {
                        	new Tickets1(rs.getInt("uid"));
                        	//new LoginUser(rs.getInt("uid"));
                        }
                        setVisible(false);
                       dispose(); // CLOSE OUT THE WINDOW
                    } else {
                        lblStatus.setText("Try again! " + (3 - count) + " / 3 attempts left");
                    }
                    if (count > 3 ) {
                        btn.setVisible(false); 
                        lblStatus.setText("Attempts suspended for 1 hour");
                    }
                }
                catch (SQLException ex) {
                        ex.printStackTrace();
                }
            }
            });
        btnExit.addActionListener(e -> System.exit(0));
        setVisible(true);   
    }  
    public static void main(String[] args) throws SQLException {
    	new Login();
    }
}