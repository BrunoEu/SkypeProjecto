
public class Chat {
	
	/*** constantes ***/
	
	private static final int MAX_FACTOR = 26;
	private static final int MIN_FACTOR = 1;
	
	
	/*** variaveis de instancia ***/
	
	private int factor;
	private UserGroup users;
	
	private String log;
	private Conversation currentConversation;

	
	/*** construtor ***/
	
	public Chat(UserGroup users, int newFactor, int msgNumber,
			String conversation, String lastMsg, boolean lastMsgEncrypted,
			User lastUser, String log){
		this.users = users;
		factor = newFactor;
		currentConversation = new Conversation(msgNumber, conversation, lastMsg, lastMsgEncrypted, lastUser);
		this.log = log;
	}
	
	// @pre validFactor(newFactor)
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
	
	public void addMsg(User user,String msg){
		currentConversation.addMsg(user, msg);
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public void addEncryptedMsg(User user, String msg){
		currentConversation.addEncryptedMsg(user, msg, factor);
	}
	
	
	public void closeConversation(){
		String conversation = currentConversation.showConversation();
		log = formatToLog(conversation);
		currentConversation.reset();
	}
	

	//@pre validUserNumber(userNumber)
	
	public boolean canEditLastMessage(User user){
		return currentConversation.canEditLastMessage(user);
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public void editLastMessage(User user, String msg){
		currentConversation.editLastMessage(user, msg, factor);
	}
	
	public UserGroup getUsers(){
		return users;
	}
	
	public String getLastMsg(){
		return currentConversation.getLastMsg();
	}
	
	public int getFactor(){
		return factor;
	}
	
	public String getLog(){
		return log;
	}
	
	public int getMsgNumber(){
		return currentConversation.getMsgNumber();
	}
	
	public String getConversation(){
		return currentConversation.getConversation();
	}
	
	public boolean getLastMsgEncrypted(){
		return currentConversation.getLastMsgEnc();
	}
	
	public User getLastUser(){
		return currentConversation.getLastUser();
	}
	
	public boolean hasUser(User user){
		return users.hasUser(user.getNumber());
	}
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}

	public boolean userGroupEquals(UserGroup userGroup){
		return(users.equals(userGroup));
	}
	
	public String initializeLog(){
		String result = "";
		User user;
		users.initializeIterator();
		
		while(users.hasNext()){
			user = users.next();
			result += "Utilizador "+ user.getNumber() +": "+ user.getName() +"\n";
		}
		
		return result;
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public String formatMessage(User user, String msg){
		return currentConversation.formatMessage(user, msg);
	}


	public String formatToLog(String msgs){
		return log.concat("\n**** NOVA CONVERSA ****\n").concat(msgs);
	}
	
}
