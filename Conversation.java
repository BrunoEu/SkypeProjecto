
public class Conversation {
	
	
	/*** constantes ***/
	
	private static final char FIRST_UPPER_CASE_CHAR = 'A';
	private static final char FIRST_LOWER_CASE_CHAR = 'a';
	private static final int ALPHABET_LENGTH = 26;
	private static final User NOBODY = new User ("", 0);
	
	
	/*** variaveis de instancia ***/
	
	private int msgNumber;
	private String conversation;
	private String lastMsg;
	private boolean lastMsgEncrypted;
	private User lastUser;
	
	
	/*** construtor ***/
	
	public Conversation(){
		reset();
	}
	
	
	public String showConversation (){
		return conversation + lastMsg;
	}
	
	
	public void addMsg(User user, String msg){
		msgNumber++;
		lastUser = user;
		conversation = conversation.concat(lastMsg);
		lastMsg = formatMessage(user, msg);
		lastMsgEncrypted = false;
	}
	
	
	//@pre factor > 0
	
	public void addEncryptedMsg(User user, String msg, int factor){
		addMsg(user, encryptMsg(msg, factor));
		lastMsgEncrypted = true;
	}
	
	
	//@pre factor > 0
	
	private String encryptMsg(String msg, int factor){
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
	
	private char encryptChar(char charToEncrypt, int factor){
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
	

	public String formatMessage(User user,String message){
		return "USER[" + user.getNumber() + "]MSG[" + getMsgNumber() +"]: " + message +"\n";
	}
	
	
	//@pre canEditLastMessage(user)
	
	public void editLastMessage(User user, String message, int factor){
		if (lastMsgEncrypted)
			lastMsg = formatMessage(user, encryptMsg(message, factor));
		else
			lastMsg = formatMessage(user, message);	
	}
	

	public boolean canEditLastMessage(User user){
		return lastUser.getNumber() == user.getNumber();
	}
	
	
	public int getMsgNumber(){
		return msgNumber;
	}
	
	
	public String getLastMsg(){
		return lastMsg;
	}
	
	
	public void reset (){
		lastMsg = "";
		lastMsgEncrypted = false;
		lastUser = NOBODY;
		conversation = "";
		msgNumber = 0;
	}
	
}
