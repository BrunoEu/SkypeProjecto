public class Chat {
	
	private static final int MAX_FACTOR = 26;
	private static final int MIN_FACTOR = 0;
	
	private int factor;
	public User user1, user2;
	private String log;
	
	public Conversation currentConversation;
	
	public Chat(String name1, String name2, int newFactor){
		user1 = new User(name1, 1);
		user2 = new User(name2, 2);
		factor = newFactor;
		initLog();
		currentConversation = new Conversation(user1, user2);
	}
	
	public String showChat(){
		return currentConversation.showConversation();
	}
	
	public String showLog(){
		return log;
	}
	
	public void addMsg(int user,String msg){
		currentConversation.addMsg(intToUser(user), msg);
	}
	
	public void addEncMsg(int user, String msg){
		currentConversation.addEncMsg(intToUser(user), msg, factor);
	}
	
	public void closeConversation(){
		String conversation = currentConversation.showConversation();
		addToLog(conversation);
		currentConversation.reset();
	}
	
	public boolean canEditLastMessage(int user){
		return currentConversation.canEditLastMessage(intToUser(user));
	}
	
	public void editLastMessage(int user, String msg){
		currentConversation.editLastMessage(intToUser(user), msg, factor);
	}
	
	public String getLastMsg(){
		return currentConversation.getLastMsg();
	}
	
	public int getMsgNumber(){
		return currentConversation.getMsgNumber();
	}
	
	public boolean validUser(int user){
		return (user == user1.getNumber() || user == user2.getNumber());
	}
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	
	public User intToUser(int user){
		if (user == user1.getNumber())
			return user1;
		else
			return user2;
	}
	
	public void initLog(){
		log = "Utilizador 1: "+user1.getName()+"\nUtilizador 2: "+user2.getName()+"\n\n";
	}
	
	public void addToLog(String msg){
		log = log.concat(msg);
	}
	
}
