
public class Facade {

	public static final String END_LAST_MESSAGE_LABEL = "*end last message";
	public static final String USER_LABEL = "*user";
	public static final String CHAT_LABEL = "*chat";
	public static final String END_CONVERSATION_LABEL = "*end conversation";
	public static final String END_LOG_LABEL = "*end log";
	
	
	private ChatsCollection chats;
	private UserGroup usersList;
	private UserGroup contactedUsers;

	public Facade(){
		reset();
	}

	public void creatUser(String name){
		usersList.addUser(new User(name, getNextId()));
	}
	
	//@pre validUserNumber(userIds[i])
	//Verfi
	//@pre validFactor(factor)
	public void createChat(int[] userIds, int factor){
		chats.addChat(new Chat(usersList.getSubGroup(userIds), factor));
	}

	//@pre validUserNumbers(userIds)
	//@pre validFactor(factor)
	//garantir que o chat não existe
	
	//@pre garantir que o chat existe
	public String showChat(int[] userIds){
		return chats.getChat(usersList.getSubGroup(userIds)).showChat();
	}

	

	

	//@pre validUserNumbers(userIds)
	//@pre validFactor(factor)
	//garantir que o chat não existe

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

	public String formatMsg(int[] userIds, int senderId, String msg){
		return chats.getChat(usersList.getSubGroup(userIds)).formatMessage(usersList.getUser(senderId), msg);
	}

	//@pre validUserNumbers(userIds)
	public void closeConversation(int[] userIds){
		chats.getChat((usersList.getSubGroup(userIds))).closeConversation();
	}

	public String initializeLog(int[] userIds){
		return chats.getChat(usersList.getSubGroup(userIds)).initializeLog();
	}

	public String showLog(int[] userIds){
		return chats.getChat(usersList.getSubGroup(userIds)).showLog();
	}

	//@pre validUserNumbers(userIds)
	public boolean hasChat(int[] userIds){
		return chats.hasChat(usersList.getSubGroup(userIds));
	}

	public boolean chatHasUser(int[] userIds, int user){
		return chats.getChat(usersList.getSubGroup(userIds)).hasUser(usersList.getUser(user));
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

	/*public boolean validUserNumbers(int[] userIds){
		for(int i = 0; i < userIds.length; i++){
			if(!validUserNumber(userIds[i]))
				return false;
		}
		return true;
	}*/

	public boolean validUserNumber(int userNumber){
		return usersList.hasUser(usersList.getUser(userNumber));
	}

	public static boolean validFactor(int factor){
		return Chat.validFactor(factor);
	}

	public int getLastUserId(){
		return usersList.getNumberUsers();
	}

	//@pre validUserNumber(userId)
	public String getName(int userId){
		return usersList.getUser(userId).getName();
	}

	public String getLastMsg(int[] userIds){
		return chats.getChat(usersList.getSubGroup(userIds)).getLastMsg();
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
			contactedUsers.removeUser(usersList.getUser(userId));
	
		return contactedUsers;
	}

	private int getNextId(){
		return usersList.getNumberUsers() + 1;
	}

	public void importUser(String name, int id){
		usersList.addUser(new User(name, id));
	}

	public void importChat(int[] userIds, int newFactor, int msgNumber,
			String conversation, String lastMsg, boolean lastMsgEncrypted,
			int lastUserId, String log){
		UserGroup users = usersList.getSubGroup(userIds);
		User lastUser = usersList.getUser(lastUserId);
		
		chats.addChat(new Chat(users, newFactor, msgNumber, conversation, lastMsg, lastMsgEncrypted, lastUser, log));
		
	}

	public String exportUsers(){
		String usersState = "";
		User user = null;
	
		usersList.initializeIterator();
	
		while(usersList.hasNext()){
			user = usersList.next();
			usersState = usersState.concat(USER_LABEL+"\n"+user.getName()+"\n"+user.getNumber()+"\n");
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
			
			chatsState = chatsState.concat(CHAT_LABEL+"\n"+
										   users.next().getNumber()+"\n"+
										   users.next().getNumber()+"\n"+
										   chat.getFactor()+"\n"+
										   chat.getMsgNumber()+"\n"+
										   chat.getConversation()+
										   END_CONVERSATION_LABEL+"\n"+
										   chat.getLastMsg()+
										   END_LAST_MESSAGE_LABEL+"\n"+
										   chat.getLastMsgEncrypted()+"\n"+
										   chat.getLastUser().getNumber()+"\n"+
										   chat.getLog()+
										   END_LOG_LABEL+"\n");
		}
		
		return chatsState;
	}

	public void reset(){
		usersList = new UserGroup();
		chats = new ChatsCollection();
		contactedUsers = new UserGroup();
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
	
}
