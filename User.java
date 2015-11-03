
public class User {

	public String userName;
	public int userNumber;
	
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
