
public class User {

	private String userName;
	private int userNumber;
	
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
