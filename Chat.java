public class Chat {
	
	/*** constantes ***/
	
	private static final int MAX_FACTOR = 26;
	private static final int MIN_FACTOR = 0;
	
	
	/*** variaveis de instancia ***/
	
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
		log = initializeLog();
		currentConversation = new Conversation();
	}
	
	
	public String showChat(){
		return currentConversation.showConversation();
	}
	
	
	public String showLog(){
		return log;
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public void addMsg(int userNumber,String msg){
		currentConversation.addMsg(intToUser(userNumber), msg);
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public void addEncryptedMsg(int userNumber, String msg){
		currentConversation.addEncryptedMsg(intToUser(userNumber), msg, factor);
	}
	
	
	public void closeConversation(){
		String conversation = currentConversation.showConversation();
		log = formatToLog(conversation);
		currentConversation.reset();
	}
	

	//@pre validUserNumber(userNumber)
	
	public boolean canEditLastMessage(int userNumber){
		return currentConversation.canEditLastMessage(intToUser(userNumber));
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public void editLastMessage(int useNumber, String msg){
		currentConversation.editLastMessage(intToUser(useNumber), msg, factor);
	}
	
	
	public String getLastMsg(){
		return currentConversation.getLastMsg();
	}
	
	
	public boolean validUserNumber(int userNumber){
		return (userNumber == user1.getNumber() || userNumber == user2.getNumber());
	}
	
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	

	//@pre validUserNumber(userNumber)
	
	public User intToUser(int userNumber){
		if (userNumber == user1.getNumber())
			return user1;
		else
			return user2;
	}
	
	
	public String initializeLog(){
		return "Utilizador 1: "+ user1.getName() +"\nUtilizador 2: "+ user2.getName();
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public String formatMessage(int userNumber, String msg){
		return currentConversation.formatMessage(intToUser(userNumber), msg);
	}


	public String formatToLog(String msgs){
		return log.concat("\n\n**** NOVA CONVERSA ****\n").concat(msgs);
	}
	
	
}
