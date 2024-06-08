package libraryMgmtSystem;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DisplayTable {
	
	public DisplayTable() {
		
		//table model represents data in row and column
		DefaultTableModel dtm = new DefaultTableModel();

		JFrame frame = new JFrame("Display records");
		
		//create table and attach table model object
		JTable table = new JTable(dtm);
		
		//prepare column for the table using table model
		dtm.addColumn("First Name");
		dtm.addColumn("Last Name");
		dtm.addColumn("Gender");
		dtm.addColumn("Program");
		dtm.addColumn("Section");
		dtm.addColumn("Book Taken");
		
		//call the readFile() method that returns array list object that
		//contains users object 
		ArrayList<User> userList=FileOperation.readFile();
		
		//iterate through arraylist object to get user object and add data of
		//user object to table as new row to previous instance
		for(int i=0;i<userList.size();i++)
		{
			 //to atttach new row leta get previous state of table model
			 dtm = (DefaultTableModel) table.getModel();
			//inserting row of user record
			dtm.insertRow(0,new Object[] {
					userList.get(i).getFirstName(),
					userList.get(i).getSecondName(),
					userList.get(i).getGender(),
					userList.get(i).getSection(),
					userList.get(i).getProgram(),
					userList.get(i).getBookTaken()
				});
			
		}
		//size of table
		table.setPreferredScrollableViewportSize(new Dimension(550, 350));
		table.setFillsViewportHeight(true);
		
		//use scroll pane to add table so that it provides scrolling of large data
		JScrollPane sp = new JScrollPane(table);
		frame.add(sp);
		frame.setSize(400,200);
		frame.setVisible(true);
	}

}
