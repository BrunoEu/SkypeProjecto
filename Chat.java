public class Chat {
	
	private static final int MAX_FACTOR = 25;
	private static final int MIN_FACTOR = 1;
	
	public int factor;
	
	public Conversation currentConversation;
	
	public Chat(String name1, String name2, int factor){
		User user1 = new User(name1, 1);
		User user2 = new User(name2, 2);
		currentConversation = new Conversation(user1, user2, factor);
	}
	
	public String showChat(){
		return currentConversation.showConversation();
	}
	
	public void addMsg(int user,String msg){
		currentConversation.addMsg(currentConversation.intToUser(user), msg);
	}
	
	public void addEncMsg(int user, String msg){
		currentConversation.addEncMsg(currentConversation.intToUser(user), msg, factor);
	}
	
	public void closeConversation(){
		String log = currentConversation.showConversation();
		//addToLog(log);
		currentConversation.reset();
	}
	
	public boolean canEditLastMessage(int user){
		return currentConversation.canEditLastMessage(currentConversation.intToUser(user));
	}
	
	public void editLastMessage(int user, String msg){
		currentConversation.editLastMessage(currentConversation.intToUser(user), msg);
	}
	
	public int getMsgNumber(){
		return currentConversation.getMsgNumber();
	}
	
	//EDUARDO: este metodo tá a ser usado ??
	/*public boolean checkUser(String userTest){
		return (userTest.equals(user1Name) || userTest.equals(user2Name));
	}*/
	
	public static  boolean validUser(int user){
		return currentConversation.validUser(currentConversation.intToUser(user));
	}
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	
}
