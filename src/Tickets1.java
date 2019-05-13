
/*
 * This is where the User page looks. 
 * User after loging in get access to this page. 
 */

import java.sql.SQLException;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public  class Tickets1 extends JFrame{
	int userID;
	Dao dao = new Dao();
	public Tickets1(int userID){
	this.userID=userID;
	createMenu();
	prepareGUI();
	getTickets();
	}
		// Main menu object items
		private JMenu mnuFile = new JMenu("File");
		private JMenu mnuAdmin = new JMenu("USER");
		private JMenu mnuTickets = new JMenu("Tickets");

		// Sub menu item objects for all Main menu item objects
		JMenuItem mnuItemExit;
		JMenuItem mnuItemUpdate;
		JMenuItem mnuItemDelete;
		JMenuItem menuBack;
		JMenuItem NewTicketBTN;
		
		//JLabel welcome = new JLabel("Welcome Admin! Here are the current tickets:", JLabel.CENTER);
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
			
			menuBack = new JMenuItem("Back to Log In");
			// initialize any more desired sub menu items below
			
			NewTicketBTN = new JMenuItem("Add A Ticket");
			mnuTickets.add(NewTicketBTN);
			
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
						new LoginDelete();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
	                  dispose();
	                  }});
	    	menuBack.addActionListener(new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	    	new Login();
					dispose();
	    	    }
	    	});
	    	NewTicketBTN.addActionListener(new ActionListener() {
	    	    @Override
	    	    public void actionPerformed(ActionEvent e) {
	    	    	try {
						new NewTicket();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					dispose();
	    	    }
	    	});
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
		    				+ " = '"+textNoteLabel.getText()+"', Date_Closed ='"+ textEndDate.getText()+"' WHERE ticket_id = "+textTicketID.getText()+" AND User_ID = "+userID;
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
			    	new Tickets1(userID);
					dispose();
			    }
			});

		   btnExit.addActionListener(e -> System.exit(0));
		   setVisible(true); // SHOW THE FRAME
		}
		}
		class LoginDelete extends JFrame {
			public  LoginDelete() throws SQLException {

			super("Deleting A Ticket");
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
		    		String sql = "DELETE FROM msala_tickets WHERE ticket_id = "+ textTicketID.getText()+" AND User_ID ="+userID;
		    		int rs=statement.executeUpdate(sql);
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
			    	new Tickets1(userID);
					dispose();
			    }
			});

		   btnExit.addActionListener(e -> System.exit(0));
		   setVisible(true); // SHOW THE FRAME
		}
		}
		
		private void getTickets() {			
			try {
				String sql="SELECT * FROM msala_tickets WHERE User_ID ="+userID;
				Connection conn = dao.connect();
				ResultSet rs =	conn.createStatement().executeQuery(sql);
				String html="<html> Welcome User! <br /> <br /> Here are your current tickets: <br /> <br /> ";
				while(rs.next()) {
					html+="Ticket ID: "+rs.getInt("ticket_id")+"<br /> Description: "+rs.getString("tdescription")+
				"<br />Date Entered: "+rs.getString("Date_Entered")+
				"<br /> Date Closed: "+rs.getString("Date_closed")+"<br />"+" User ID: "+rs.getInt("User_ID")+"<br />"+"Note: " +rs.getString("Note")+"<br /> <br />";
				}
				html+="</html>";
				lblStatus.setText(html);
			}
			catch (SQLException se){
	           se.printStackTrace();
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
	
		public class NewTicket extends JFrame {

		    		public  NewTicket() throws SQLException {
		    			
		    			super("Enter New Ticket");
		    			Dao dao = new Dao();
		    			java.sql.Statement statement= dao.connect().createStatement();
		    			setSize(500, 300);
		    			setLayout(new GridLayout(3, 1));
		    			setLocationRelativeTo(null); // centers window
		    			
		    			JLabel DescriptionLabel = new JLabel("Enter Description ", JLabel.CENTER);
		    			JTextField textDescription = new JTextField();
		    			  
		    			JLabel lblStatus = new JLabel("", JLabel.LEFT);
		    			
		    			
		    			JButton exitBTN  = new JButton("EXIT");
		    			JButton submitBTN  = new JButton("Submit");
		    			JButton back  = new JButton("Back");
		    			
		    			add(DescriptionLabel);
		    			add(textDescription);
		    		
		    			add(exitBTN);
		    			add(submitBTN);
		    			add(back);
		    			add(lblStatus);
		    			
		    			 
		    			   back.addActionListener(new ActionListener() {
		    				    @Override
		    				    public void actionPerformed(ActionEvent e) {
		    				    	new Tickets1(userID);
		    						dispose();
		    				    }
		    				});
		    			 submitBTN.addActionListener(new ActionListener() {
		    	    	    @Override
		    	    	    public void actionPerformed(ActionEvent e) {
		    	    	    	try {
		    	    	    		String sql = "INSERT INTO msala_tickets (tdescription, Date_Entered, User_ID) VALUES "
		    	    	    				+"('"+textDescription.getText()+"', NOW(), "+userID+")";
		    	    	    		statement.executeUpdate(sql);

		    	    	    		sql = "SELECT MAX(ticket_id) AS ID FROM msala_tickets ";
		    	    	    				ResultSet rs = statement.executeQuery(sql);
		    	    	    				rs.next();
		    	    	    				int newID = rs.getInt("ID");
		    	    	    				lblStatus.setText("Thanks, your ticket Number is"
		    	    	    						+ "  "+newID);	
		    	    	    				//dispose(); 
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

}
	