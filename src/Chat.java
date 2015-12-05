
public class Chat {
	
	/*** constantes ***/
	
	private static final int MAX_FACTOR = 26;
	private static final int MIN_FACTOR = 0;
	
	
	/*** variaveis de instancia ***/
	
	private int factor;
	private UserGroup users;
	
	private String log;
	private Conversation currentConversation;

	
	/*** construtor ***/
	// @pre validFactor(newFactor)
	
	public Chat(String name1, String name2, int id1, int id2, int newFactor){
		users = new UserGroup();
		users.addUser(new User(name1, id1));
		users.addUser(new User(name2, id2));
		factor = newFactor;
		log = initializeLog();
		currentConversation = new Conversation();
	}
	
	public Chat(UserGroup users, int newFactor){
		this.users = users;
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
		return users.hasUser(userNumber);
	}
	
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	

	//@pre validUserNumber(userNumber)
	
	public User intToUser(int userNumber){
		return users.getUser(userNumber);
	}
	
	public boolean userGroupEquals(UserGroup userGroup){
		return(users.equals(userGroup));
	}
	
	public String initializeLog(){
		String result = "";
		User user;
		users.initializIterator();
		
		while(users.hasNext()){
			user = users.next();
			result += "Utilizador "+ user.getNumber() +": "+ user.getName() +"\n";
		}
		
		return result;
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public String formatMessage(int userNumber, String msg){
		return currentConversation.formatMessage(intToUser(userNumber), msg);
	}


	public String formatToLog(String msgs){
		return log.concat("\n**** NOVA CONVERSA ****\n").concat(msgs);
	}
	
	
}
