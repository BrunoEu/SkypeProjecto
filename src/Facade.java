
public class Facade {

	ChatsCollection chats;
	UserGroup usersList;
	
	public Facade(){
		chats = new ChatsCollection();
		usersList = new UserGroup();
	}
	
	public void creatUser(String name){
		usersList.addUser(new User(name, getNextId()));
	}
	
	//@pre validUserNumbers(userIds)
	public void createChat(int[] userIds, int factor){
		chats.addChat(usersList.getSubGroup(userIds), factor);
	}
	
	public String showChat(int[] userIds){
		chats.
	}
	
	//@pre validUserNumbers(userIds)
	public void closeConversation(int[] userIds){
		chats.closeConversation(usersList.getSubGroup(userIds));
	}
	
	//@pre validUserNumbers(userIds)
	public boolean hasChat(int[] userIds){
		return chats.hasChat(usersList.getSubGroup(userIds));
	}

	public boolean validUserNumbers(int[] userIds){
		for(int i = 0; i < userIds.length; i++){
			if(!validUserNumber(userIds[i]))
				return false;
		}
		return true;
	}
	
	public boolean validUserNumber(int userNumber){
		return usersList.hasUser(userNumber);
	}
	
	public static boolean validFactor(int factor){
		return Chat.validFactor(factor);
	}
	
	public boolean nameTaken(String name){
		usersList.initializIterator();
		
		while(usersList.hasNext()){
			if(usersList.next().getName().equals(name))
				return true;
		}
		
		return false;
		
	}
	
	private int getNextId(){
		return usersList.getNumberUsers() + 1;
	}
	
}
