
public class ChatsCollection {
	
	private static final int DEFAULT_SIZE = 5;
	
	private Chat[] chats;
	private int chatCounter;
	private int currentChat;
	
	
	public ChatsCollection(){
		chats = new Chat[DEFAULT_SIZE];
		chatCounter = 0;
	}
	
	public void addChat(UserGroup users, int newFactor){
		chats[chatCounter++] = new Chat(users, newFactor);
	}
	
	//@pre hasChat(users)
	public Chat getChat(UserGroup users){
		Chat chat = null;
		
		for(int i = 0; i < chatCounter; i++){
			if(chats[i].userGroupEquals(users))
				chat = chats[i];
		}
		
		return chat;
	}
	
	public void publishMsg(UserGroup userGroup, User user, String msg, boolean encrypted){
		Chat chat = userGroupToChat(userGroup);
		
		if(!encrypted)
			chat.addMsg(user, msg);
		else
			chat.addEncryptedMsg(user, msg);
	}
	
	public boolean hasChat(UserGroup userGroup){
		
		for(int i = 0; i < chatCounter; i++){
			if(chats[i].userGroupEquals(userGroup))
				return true;
		}
		
		return false;
	}
	
	//@pre hasChat(userGroup)
	public Chat userGroupToChat(UserGroup userGroup){
		Chat chat = null;	//A @pre garante que o metodo retornara sempre um chat valido
		
		for(int i = 0; i < chatCounter; i++){
			if(chats[i].userGroupEquals(userGroup))
				chat = chats[i];
		}
		
		return chat;
		
	}
	
	public void closeConversation(UserGroup userGroup){
		userGroupToChat(userGroup).closeConversation();
	}
	
	public void initializeIterator(){
		currentChat = 0;
	}
	
	public boolean hasNext(){
		return ( currentChat >= 0) && (currentChat < chatCounter);
	}
	
	//@pre: hasNext()	
	public Chat next(){
		return chats[currentChat++];
	}
	
	
}