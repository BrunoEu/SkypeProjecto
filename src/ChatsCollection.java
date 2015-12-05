
public class ChatsCollection {
	
	private static final int DEFAULT_SIZE = 5;
	
	private Chat[] chats;
	private int chatCounter;
	
	
	public ChatsCollection(){
		chats = new Chat[DEFAULT_SIZE];
		chatCounter = 0;
	}
	
	public void addChat(UserGroup users, int newFactor){
		chats[chatCounter++] = new Chat(users, newFactor);
	}
	
	public void publishMsg(UserGroup userGroup, User user, String msg, boolean encrypted){
		Chat chat = userGroupToChat(userGroup);
		
		if(!encrypted)
			chat.addMsg(user.getNumber(), msg);
		else
			chat.addEncryptedMsg(user.getNumber(), msg);
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
	
}