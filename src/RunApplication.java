import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.hibernate.example.CRUDoperations;

public class RunApplication {
	public static void main(String[] args) {
		BufferedReader readerOne;
		System.out.println("---Welcome to our data base mister King---");
		CRUDoperations.start();
		while(true) {
			readerOne = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Choose one option, please..."
					+ "\n1. Create user\n"
					+ "\n2. Show Table\n"
					+ "\n3. Update user data\n"
					+ "\n4. Delete user\n"
					+ "\n5. Exit.");
			try {
				byte num = Byte.parseByte(readerOne.readLine());
				
				switch(num) {
				case 1:
					CRUDoperations.create();
					break;
				case 2:
					CRUDoperations.getData();
					break;
				case 3:
					CRUDoperations.updateData();
					break;
				case 4:
					CRUDoperations.deleteData();
					break;
				case 5:
					System.out.println("Goodbye King.");
					break;
				default:
					System.out.println("Please choose one option.(from 1-5)");
					break;
				} 
				if(num==5)break;
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException nfx) {
				System.out.println("Invalid number. Try again!");
			} 
			
		}
		CRUDoperations.closeAll();
	}
}
