
public class CurrentChats {
	
	private static final int DEFAULT_SIZE = 5;
	
	private Chat[] chats;
	private counter;
	
	
	public CurrentChats(){
		chats = new Chat[DEFAULT_SIZE];
		counter = 0;
	}
	
	public void addChat(String name1, int id1, String name2, int id2){
		Chat newChat = new Chat(name1, name2, id1, id2);
		chats[counter++];
	}
	
}