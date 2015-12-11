
public class Facade {

	private ChatsCollection chats;
	private UserGroup usersList;
	private UserGroup contactedUsers;

	public Facade(){
		chats = new ChatsCollection();
		usersList = new UserGroup();
	}

	public void creatUser(String name){
		usersList.addUser(new User(name, getNextId()));
	}
	
	public void importUser(String name, int id){
		usersList.addUser(new User(name, id));
	}

	//@pre validUserNumbers(userIds)
	//@pre validFactor(factor)
	//garantir que o chat não existe

	public void createChat(int[] userIds, int factor){
		chats.addChat(usersList.getSubGroup(userIds), factor);
	}

	//@pre garantir que o chat existe
	public String showChat(int[] userIds){
		return chats.getChat(usersList.getSubGroup(userIds)).showChat();
	}
	 

	//@pre validUserNumber(userId)
	/*public int[] getContactedIds(int userId){
		UserGroup contactedUsers = getContactedUserGroup(userId);

		return contactedUsers.userGroupToArrayInt();
	}*/

	//@pre validUserNumber(userId)
	/*public String[] getContactedNames(int userId){
		UserGroup contactedUsers = getContactedUserGroup(userId);

		return contactedUsers.userGroupToArrayName();
	}*/

	public String[] showAllNames(){
		return usersList.userGroupToArrayName();
	}

	public int[] showAllIds(){
		return usersList.userGroupToArrayInt();
	}

	//@pre validUserNumbers(userIds)
	//@pre validUserNumber(senderId)
	public void addMessage(int[] userIds, int senderId, String msg, boolean encrypted){
		Chat chat = chats.getChat(usersList.getSubGroup(userIds));

		if(!encrypted)
			chat.addMsg(usersList.getUser(senderId), msg);
		else
			chat.addEncryptedMsg(usersList.getUser(senderId), msg);
	}

	public void editLastMessage(int[] userIds, int userCorrectorId, String msg){
		chats.getChat(usersList.getSubGroup(userIds)).editLastMessage(usersList.getUser(userCorrectorId), msg);
	}

	//@pre validUserNumber(userId)
	public String getName(int userId){
		return usersList.getUser(userId).getName();
	}

	public String getLastMsg(int[] userIds){
		return chats.getChat(usersList.getSubGroup(userIds)).getLastMsg();
	}

	public String exportUsers(){
		String usersState = "";
		User user = null;

		usersList.initializeIterator();

		while(usersList.hasNext()){
			user = usersList.next();
			usersState = usersState.concat("*user\n"+user.getName()+"\n"+user.getNumber()+"\n");
		}

		return usersState;

	}

	public String exportChats(){
		String chatsState = "";
		Chat chat = null;

		chats.initializeIterator();
	
		while(chats.hasNext()){
			chat = chats.next();
			UserGroup users= chat.getUsers();

			users.initializeIterator();
			
			chatsState = chatsState.concat("*chat\n"+
										   users.next().getNumber()+"\n"+
										   users.next().getNumber()+"\n"+
										   chat.getFactor()+"\n"+
										   chat.getMsgNumber()+"\n"+
										   chat.getConversation()+
										   "*end conversation\n"+
										   chat.getLastMsg()+
										   chat.getLastMsgEncrypted()+"\n"+
										   chat.getLastUser().getNumber()+"\n"+
										   chat.getLog()+
										   "*end log\n");
		}
		
		return chatsState;
	}

	//@pre validUserNumber(userId)
	private UserGroup getContactedUserGroup(int userId){
		UserGroup contactedUsers = new UserGroup();
		Chat chat = null;

		chats.initializeIterator();

		while(chats.hasNext()){
			chat = chats.next();
			if(chat.hasUser(usersList.getUser(userId)))
				contactedUsers = UserGroup.mergeGroups(contactedUsers, chat.getUsers());
		}

		if(contactedUsers.getNumberUsers()>0)
			contactedUsers.removeUser(userId);

		return contactedUsers;
	}

	public String showLog(int[] userIds){
		return chats.getChat(usersList.getSubGroup(userIds)).showLog();
	}

	public String initializeLog(int[] userIds){
		return chats.getChat(usersList.getSubGroup(userIds)).initializeLog();
	}

	public String formatMsg(int[] userIds, int senderId, String msg){
		return chats.getChat(usersList.getSubGroup(userIds)).formatMessage(usersList.getUser(senderId), msg);
	}

	//@pre validUserNumbers(userIds)
	public void closeConversation(int[] userIds){
		chats.closeConversation(usersList.getSubGroup(userIds));
	}

	//@pre validUserNumbers(userIds)
	public boolean hasChat(int[] userIds){
		return chats.hasChat(usersList.getSubGroup(userIds));
	}

	public boolean chatHasUser(int[] userIds, int user){
		return chats.getChat(usersList.getSubGroup(userIds)).hasUser(usersList.getUser(user));
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

	//@pre hasChat(userIds)
	public boolean canEditLastMessage(int[] userIds, int userCorrectorId){
		return chats.getChat(usersList.getSubGroup(userIds)).canEditLastMessage(usersList.getUser(userCorrectorId));
	}

	public void userListInitializeIterator(){
		usersList.initializeIterator();
	}
	
	public boolean userListHasNext(){
		return usersList.hasNext();
	}
	
	public User userListNext(){
		return usersList.next();
	}
	
	private int getNextId(){
		return usersList.getNumberUsers() + 1;
	}

	public void initializeContactedIterator(int userId){
		contactedUsers = getContactedUserGroup(userId);
		
		contactedUsers.initializeIterator();
	}
	
	public boolean contactedUsersHasNext(){
		return contactedUsers.hasNext();
	}
	
	public User contactedUsersNext(){
		return contactedUsers.next();
	}
	
	public void reset(){
		
	}
	
}
