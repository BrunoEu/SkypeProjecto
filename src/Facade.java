
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
		return chats.getChat(usersList.getSubGroup(userIds)).showChat();
	}
	
	public int[] showContactedIds(int userId){
		UserGroup contactedUsers = getContactedUserGroup(userId);
		
		return contactedUsers.userGroupToArrayInt();
	}
	
	public String[] showContactedNames(int userId){
		UserGroup contactedUsers = getContactedUserGroup(userId);
		
		return contactedUsers.userGroupToArrayName();
	}
	
	public String[] showAllNames(){
		return usersList.userGroupToArrayName();
	}
	
	public int[] showAllIds(){
		return usersList.userGroupToArrayInt();
	}
	
	public void addMsg(int[] userIds, int senderId, String msg, boolean encrypted){
		Chat chat = chats.getChat(usersList.getSubGroup(userIds));
		
		if(!encrypted)
			chat.addMsg(senderId, msg);
		else
			chat.addEncryptedMsg(senderId, msg);
	}
	
	public String getName(int userId){
		return usersList.getUser(userId).getName();
	}
	
	private UserGroup getContactedUserGroup(int userId){
		UserGroup contactedUsers = new UserGroup();
		Chat chat = null;
		
		chats.initializeIterator();
		
		while(chats.hasNext()){
			chat = chats.next();
			if(chat.validUserNumber(userId))
				contactedUsers = UserGroup.mergeGroups(contactedUsers, chat.getUsers());
		}
		
		contactedUsers.removeUser(userId);
		
		return contactedUsers;
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
		usersList.initializeIterator();
		
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
