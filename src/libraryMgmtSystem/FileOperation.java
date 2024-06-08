package libraryMgmtSystem;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.util.*;


public class FileOperation{
	
	static File file = new File("studentsData.txt");

	//writing to a file
	public static void writeFile(User user){
		try{
			
		//uncomment below lines to write object to a file and add other code
		//ObjectOutput objOutput = new ObjectOutputStream(new FileOutputStream(file,true))
			//objOutput.writeObject(user);
		
		//write string of student data to a file
		BufferedWriter bw=new BufferedWriter(new FileWriter(file,true));
		
		String tempFirstName= user.getFirstName();
		String tempLastName= user.getSecondName();
		String tempGender= user.getGender();
		//String tempAddress= user.getAddress();
		//String tempContact= user.getContact();
		String tempSection= user.getSection();
		String tempProgram= user.getProgram();
		
		
		//adding all the data to a single string
		String st=tempFirstName+" "+tempLastName+" "+tempGender+" "+tempProgram+" "+tempSection+" "+user.getBookTaken()+"\n";
		bw.write(st);
		bw.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	}
	
	//reading a file
	public static ArrayList<User> readFile(){
		
		User user=new User();
		ArrayList<User> list=new ArrayList<User>();
		try{
			BufferedReader br=new BufferedReader(new FileReader(file));

			String line="";
			int i=0;
			int count=0;
			// now read all the record from the file 
			while((line=br.readLine())!=null){
				
				//split lines of record to array 
				String[] arr=line.split(" ");
				
				//initialize users object with user information
				User usr=new User();							
				usr.setFirstName(arr[0]);
				usr.setSecondName(arr[1]);
				usr.setGender(arr[2]);
				usr.setSection(arr[3]);
				usr.setProgram(arr[4]);
				usr.setBookTaken(arr[5]);
				i++;	
				
				//add each user object to a array list
				list.add(usr);
			}
			br.close();
			//br1.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(list);
		//return array list object
		return list;
	}
}