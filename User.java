public class User {

	public static User NOBODY = new User("",0);
	
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
	
	public boolean equals(User other){
		return getNumber() == other.getNumber();
	}
	
}
