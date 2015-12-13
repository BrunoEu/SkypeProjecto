public class User {

	public static User NOBODY = new User("",0);
	
	/*** varaveis de instancia ***/
	private String userName;
	private int userId;
	
	
	/*** construtor ***/
	
	public User(String name, int number){
		userName = name;
		userId = number;
	}
	
	
	public String getName(){
		return userName;
	}
	
	
	public int getNumber(){
		return userId;
	}
	
	public boolean equals(User other){
		return getNumber() == other.getNumber();
	}
	
}
