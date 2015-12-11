
public class Chat {
	
	/*** constantes ***/
	
	private static final int MAX_FACTOR = 26;
	private static final int MIN_FACTOR = 1;
	private static final char FIRST_UPPER_CASE_CHAR = 'A';
	private static final char FIRST_LOWER_CASE_CHAR = 'a';
	private static final int ALPHABET_LENGTH = 26;
	
	
	/*** variaveis de instancia ***/
	
	private int factor;
	private UserGroup users;
	private String log;
	private int msgNumber;
	private String conversation;
	private String lastMsg;
	private boolean lastMsgEncrypted;
	private User lastUser;

	
	/*** construtor ***/
	
	public Chat(UserGroup users, int factor, int msgNumber,
			String conversation, String lastMsg, boolean lastMsgEncrypted,
			User lastUser, String log){
		this.users = users;
		this.msgNumber = msgNumber;
		this.conversation = conversation;
		this.lastMsg = lastMsg;
		this.lastMsgEncrypted = lastMsgEncrypted;
		this.lastUser = lastUser;
		this.factor = factor;
		this.log = log;
	}
	
	// @pre validFactor(newFactor)
	public Chat(UserGroup users, int newFactor){
		this.users = users;
		factor = newFactor;
		log = initializeLog();
		reset();
	}
	
	public String showChat(){
		return conversation + lastMsg;
	}
	
	
	public String showLog(){
		return log;
	}
	
	
	//@pre validUserNumber(userNumber)
	
	public void addMsg(User user,String msg){
		msgNumber++;
		lastUser = user;
		conversation = conversation.concat(lastMsg);
		lastMsg = formatMessage(user, msg);
		lastMsgEncrypted = false;
	}
	
	
	//@pre validUserNumber(userNumber) && factor > 0
	
	public void addEncryptedMsg(User user, String msg){
		addMsg(user, encryptMsg(msg, factor));
		lastMsgEncrypted = true;
	}
	
	//@pre validUserNumber(userNumber)
	public boolean canEditLastMessage(User user){
		return lastUser.getNumber() == user.getNumber();
	}

	//@pre canEditLastMessage(user)
	public void editLastMessage(User user, String msg){
		if (lastMsgEncrypted)
			lastMsg = formatMessage(user, encryptMsg(msg, factor));
		else
			lastMsg = formatMessage(user, msg);	
	}

	public void closeConversation(){
		String conversation = showChat();
		log = formatToLog(conversation);
		reset();
	}

	public String formatMessage(User user,String message){
		return "USER[" + user.getNumber() + "]MSG[" + getMsgNumber() +"]: " + message +"\n";
	}
	
	public UserGroup getUsers(){
		return users;
	}
	
	public String getLastMsg(){
		return lastMsg;
	}
	
	public int getFactor(){
		return factor;
	}
	
	public String getLog(){
		return log;
	}
	
	public int getMsgNumber(){
		return msgNumber;
	}
	
	public String getConversation(){
		return conversation;
	}
	
	public boolean getLastMsgEncrypted(){
		return lastMsgEncrypted;
	}
	
	public User getLastUser(){
		return lastUser;
	}
	
	public boolean hasUser(User user){
		return users.hasUser(user.getNumber());
	}
	
	/*public boolean userGroupEquals(UserGroup userGroup){
		return(users.equals(userGroup));
	}*/
	
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

	public String formatToLog(String msgs){
		return log.concat("\n**** NOVA CONVERSA ****\n").concat(msgs);
	}
	
	public void reset (){
		lastMsg = "";
		lastMsgEncrypted = false;
		lastUser = User.NOBODY;
		conversation = "";
		msgNumber = 0;
	}

	//@pre factor > 0
	private static String encryptMsg(String msg, int factor){
		int index = 0;
		String msgEnc = "";
		char charToEncrypt, charEncrypted;
		
		while(index < msg.length()){
			charToEncrypt = msg.charAt(index);
			charEncrypted = encryptChar(charToEncrypt, factor);
			msgEnc += charEncrypted;
			index++;
		}
		
		return msgEnc;
	}

	//@pre factor > 0
	private static char encryptChar(char charToEncrypt, int factor){
		char charEncrypted;
		char firstChar;
		
		if(validLowerCaseChar(charToEncrypt) || validUpperCaseChar(charToEncrypt)){
			if (validLowerCaseChar(charToEncrypt)){
				firstChar = FIRST_LOWER_CASE_CHAR;
			}
			else {
				firstChar = FIRST_UPPER_CASE_CHAR;
			}
			
			charEncrypted = (char)((charToEncrypt - firstChar + factor) % ALPHABET_LENGTH + firstChar);
		}
		
		else{
			charEncrypted = charToEncrypt;
		}
		
		return charEncrypted;
	}

	private static boolean validUpperCaseChar(char a){
		return a >= 'A' && a <= 'Z' ;
	}

	private static boolean validLowerCaseChar(char a){
		return a >= 'a' && a <= 'z' ;
	}

	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	
}
