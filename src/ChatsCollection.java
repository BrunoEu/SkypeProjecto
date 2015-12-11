
public class ChatsCollection {
	
	private static final int DEFAULT_SIZE = 5;
	
	private Chat[] chats;
	private int chatCounter;
	private int currentChat;
	
	
	public ChatsCollection(){
		chats = new Chat[DEFAULT_SIZE];
		chatCounter = 0;
	}
	
	public void addChat(Chat chat){
		chats[chatCounter++] = chat;
	}
	
	public void publishMsg(UserGroup userGroup, User user, String msg, boolean encrypted){
		Chat chat = getChat(userGroup);
		
		if(!encrypted)
			chat.addMsg(user, msg);
		else
			chat.addEncryptedMsg(user, msg);
	}
	
	public boolean hasChat(UserGroup userGroup){
		
		for(int i = 0; i < chatCounter; i++){
			if(chats[i].getUsers().equals(userGroup))
				return true;
		}
		
		return false;
	}
	
	//@pre hasChat(users)
	public Chat getChat(UserGroup users){
		Chat chat = null;
		
		for(int i = 0; i < chatCounter; i++){
			if(chats[i].getUsers().equals(users))
				chat = chats[i];
		}
		return chat;
	}
	
	public void closeConversation(UserGroup userGroup){
		getChat(userGroup).closeConversation();
	}
	
	public void initializeIterator(){
		currentChat = 0;
	}
	
	public boolean hasNext(){
		return (currentChat >= 0) && (currentChat < chatCounter);
	}
	
	//@pre: hasNext()	
	public Chat next(){
		return chats[currentChat++];
	}
	
	
}