
public class ChatsCollection {
	
	private static final int DEFAULT_SIZE = 5;
	private static final int GROWTH_RATE = 2;
	
	private Chat[] chats;
	private int chatCounter;
	private int currentChat;
	
	
	public ChatsCollection(){
		chats = new Chat[DEFAULT_SIZE];
		chatCounter = 0;
	}
	
	public void addChat(Chat chat){
		if(isFull())
			resize();
		chats[chatCounter++] = chat;
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
	
	private boolean isFull(){
		return chatCounter == chats.length;
	}
	
	private void resize(){
		Chat[] newChatCollection= new Chat[chats.length *  GROWTH_RATE];
		
		for(int i = 0; i < chatCounter; i++){
			newChatCollection[i] = chats[i];
		}
		
		chats = newChatCollection;
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