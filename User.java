import java.util.Scanner;

public class User {

	public String NAME;
	
	public User(Scanner in){
		insertName(in);
	}
	
	public void insertName(Scanner in){
		System.out.print("NOME > ");
		NAME = in.nextLine();
	}
	
}
