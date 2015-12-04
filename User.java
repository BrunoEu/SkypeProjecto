
public class User {

	/*** varaveis de instancia ***/
	
	private String userName;
	private int userNumber;
	
	
	/*** construtor ***/
	
	public User(String name, int number){
		userName = name;
		userNumber = number;
	}
	
	
	public String getName(){
		return userName;
	}
	
	
	public int getNumber(){
		return userNumber;
	}
	
}
