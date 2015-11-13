public class Chat {
	
	/*** constantes ***/
	
	private static final int MAX_FACTOR = 26;
	private static final int MIN_FACTOR = -60;
	
	
	/*** variáveis de instância ***/
	
	private int factor;
	private User user1, user2;
	private String log;
	private Conversation currentConversation;

	
	/*** construtor ***/
	// @pre validFactor(newFactor)
	
	public Chat(String name1, String name2, int newFactor){
		user1 = new User(name1, 1);
		user2 = new User(name2, 2);
		factor = newFactor;
		log = initLog();
		currentConversation = new Conversation(user1, user2);
	}
	
	
	public String showChat(){
		return currentConversation.showConversation();
	}
	
	
	public String showLog(){
		return log;
	}
	
	
	//@pre validUser(user)
	
	public void addMsg(int user,String msg){
		currentConversation.addMsg(intToUser(user), msg);
	}
	
	
	//@pre validUser(user)
	
	public void addEncMsg(int user, String msg){
		currentConversation.addEncryptedMsg(intToUser(user), msg, factor);
	}
	
	
	public void closeConversation(){
		String conversation = currentConversation.showConversation();
		log = formatToLog(conversation);
		currentConversation.reset();
	}
	

	//@pre validUser(user)
	
	public boolean canEditLastMessage(int user){
		return currentConversation.canEditLastMessage(intToUser(user));
	}
	
	
	//@pre validUser(user)
	
	public void editLastMessage(int user, String msg){
		currentConversation.editLastMessage(intToUser(user), msg, factor);
	}
	
	
	public String formatMessage(int user, String msg){
		return currentConversation.formatMessage(intToUser(user), msg);
	}
	
	
	public String getLastMsg(){
		return currentConversation.getLastMsg();
	}
	
	
	public int getMsgNumber(){
		return currentConversation.getMsgNumber();
	}
	
	
	public boolean validUserNumber(int user){
		return (user == user1.getNumber() || user == user2.getNumber());
	}
	
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	

	//@pre validUser(user)
	
	public User intToUser(int user){
		if (user == user1.getNumber())
			return user1;
		else
			return user2;
	}
	
	
	public String initLog(){
		return "Utilizador 1: "+ user1.getName() +"\nUtilizador 2: "+ user2.getName();
	}
	
	
	public String formatToLog(String msg){
		return log.concat("\n\n**** NOVA CONVERSA ****\n").concat(msg);
	}
	
}
