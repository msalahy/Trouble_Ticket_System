/*
 * This is where the Admin page looks. 
 * Admin after loging in get access to this page. 
 */

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class Tickets extends JFrame implements ActionListener {
int userID;
	// class level member objects
	Dao dao = new Dao(); // for CRUD operations
	Boolean chkIfAdmin = null;
	
	public Tickets(int userID) {
		this.userID=userID;
		createMenu();
		prepareGUI();
		getTickets();
	}
	public Tickets(Boolean isAdmin) {
		chkIfAdmin = isAdmin;
		createMenu();
		prepareGUI();
		getTickets();
	}
	
	// Main menu object items
	private JMenu mnuFile = new JMenu("File");
	private JMenu mnuAdmin = new JMenu("Admin");
	private JMenu mnuTickets = new JMenu("Tickets");

	// Sub menu item objects for all Main menu item objects
	JMenuItem mnuItemExit;
	JMenuItem mnuItemUpdate;
	JMenuItem mnuItemDelete;
	JMenuItem menuBack;
	JMenuItem NewTicketBTN;
	
	JLabel lblStatus = new JLabel(" ", JLabel.CENTER);
	JScrollPane scroll = new JScrollPane(lblStatus, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	
	private void createMenu() {

		/* Initialize sub menu items **************************************/
		// initialize sub menu item for File main menu
		mnuItemExit = new JMenuItem("Exit");
		// add to File main menu item
		mnuFile.add(mnuItemExit);
		
		// initialize first sub menu items for Admin main menu
		mnuItemUpdate = new JMenuItem("Update Ticket");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemUpdate);

		// initialize second sub menu items for Admin main menu
		mnuItemDelete = new JMenuItem("Delete Ticket");
		// add to Admin main menu item
		mnuAdmin.add(mnuItemDelete);
		
		NewTicketBTN = new JMenuItem("A New Ticket");
		mnuTickets.add(NewTicketBTN);

		menuBack = new JMenuItem("Back to Log In");
		// initialize any more desired sub menu items below


		/* Add action listeners for each desired menu item *************/
		mnuItemExit.addActionListener(e -> System.exit(0));

		mnuItemUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              try {
				new UpdateTicket();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
              dispose();
              }});
		
		
		mnuItemDelete.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	              try {
					new LoginDelete(userID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                  dispose();
                  }});
		
		NewTicketBTN.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	try {
					new NewTicketAdmin(userID);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				dispose();
    	    }
    	});
    	menuBack.addActionListener(new ActionListener() {
    	    @Override
    	    public void actionPerformed(ActionEvent e) {
    	    	new Login();
				dispose();
    	    }
    	});
	}
	
	
	private void getTickets() {
		try {
			String sql = "SELECT * FROM msala_tickets";
			Connection conn = dao.connect();
			ResultSet rs =	conn.createStatement().executeQuery(sql);
			String html="<html> Welcome Admin! <br /> <br /> Here are the current tickets: <br /> <br /> ";
			while(rs.next()) {
				html+="Ticket ID: "+rs.getInt("ticket_id")+"<br /> Description: "+rs.getString("tdescription")+
			"<br />Date Entered: "+rs.getString("Date_Entered")+
			"<br /> Date Closed: "+rs.getString("Date_closed")+"<br />"+" User ID: "+rs.getInt("User_ID")+"<br />" +"Note: "+rs.getString("Note")+"<br /> <br />";
			}
			html+="</html>";
			lblStatus.setText(html);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void prepareGUI() {

		// create jmenu bar
		JMenuBar bar = new JMenuBar();
		bar.add(mnuFile); // add main menu items in order, to JMenuBar
		bar.add(mnuAdmin);
		bar.add(mnuTickets);
		bar.add(menuBack);
		// add menu bar components to frame
		setJMenuBar(bar);

		addWindowListener(new WindowAdapter() {
		// define a window close operation
		public void windowClosing(WindowEvent wE) {
		    System.exit(0);
		}
		});
		
		// set frame options
		setSize(500, 300);
		setLayout(new GridLayout(1, 1));
		
		//add(welcome);
		add(scroll);
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}
	class UpdateTicket extends JFrame {
		public  UpdateTicket() throws SQLException {

		super("Updating A Ticket");
		Dao conn = new Dao();
		java.sql.Statement statement= conn.connect().createStatement();
		setSize(500, 300);
		setLayout(new GridLayout(6, 2));
		setLocationRelativeTo(null); // centers window
		
		// SET UP CONTROLS
		JLabel CenterNote = new JLabel("Please Enter The Following Information: ", JLabel.CENTER);
		JLabel lblTicketID = new JLabel("Ticket ID", JLabel.LEFT);
		JLabel ENDDateLabel = new JLabel("CLosed Date", JLabel.LEFT);
		JLabel NoteLabel = new JLabel("Note You Want to Add", JLabel.LEFT);
		JLabel lblStatus = new JLabel(" ", JLabel.LEFT);
		// Creating text-file
		JTextField textTicketID = new JTextField(10);
		JTextField textEndDate = new JTextField(10);
		JTextField textNoteLabel = new JTextField(10);
		
		
		JButton SubmitBtn = new JButton("Submit");
		JButton backBtn = new JButton("Back");
		JButton btnExit = new JButton("Exit");

		
		// ADD OBJECTS TO FRAME
		add(lblTicketID);// 1st row filler
		add(textTicketID); // 2nd row
		add(ENDDateLabel);//3rd
		add(textEndDate);//3rd
		
		add(NoteLabel);//4rd
		add(textNoteLabel);//4rd
		
		add(SubmitBtn); // 5th row
		add(btnExit);//5th row
		add(backBtn);//6th
		add(lblStatus);//6th
		
		SubmitBtn.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	    	try {
	    		String sql = "UPDATE msala_tickets SET Note"
	    				+ " = '"+textNoteLabel.getText()+"', Date_Closed = '"+ textEndDate.getText()+"' WHERE ticket_id = "+textTicketID.getText();
	    		int rs = statement.executeUpdate(sql);
				lblStatus.setText("Thanks, the information has been udpated.");
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
}
