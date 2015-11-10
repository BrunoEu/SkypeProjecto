
public class Conversation {

	private static final char ALPHABET_LENGTH = 26;
	private static final User NOBODY = new User ("", 0);
	
	private int msgNumber;
//	private User user1;
//	private User user2;
	private String allMsgs;
	private String lastMsg;
	private boolean lastMsgEnc;
	private User lastUser;
	
	public Conversation(User newUser1, User newUser2){
//		user1 = newUser1;
//		user2 = newUser2;
		reset();
	}
	
	public String showConversation (){
		return allMsgs + lastMsg;
	}
	
	public void addMsg(User user, String msg){
		msgNumber++;
		lastUser = user;
		allMsgs = allMsgs.concat(lastMsg);
		lastMsg = formatMessage(user, msg);
		lastMsgEnc = false;
	}
	
	public void addEncMsg(User user, String msg, int factor){
		addMsg(user, encMsg(msg, factor));
		lastMsgEnc = true;
	}
	
	public String encMsg(String msg, int factor){
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
	
	public char encryptChar(char charToEncrypt, int factor){
		/*if (validLowerCaseChar(charToEncrypt)){
			charToEncrypt += factor;
			if(lowerCaseOverflow(charToEncrypt)){
				charToEncrypt -= ALPHABET_LENGTH;
			}
		}else{ 
			if (validUpperCaseChar(charToEncrypt)){
			charToEncrypt += factor;
				if(upperCaseOverflow(charToEncrypt)){
					charToEncrypt -= ALPHABET_LENGTH;
				}
			}
		}
		return charToEncrypt;
		*/
		char charFactor = (char)factor;
		char charEncrypted;
		
		if (validLowerCaseChar(charToEncrypt)){
			charEncrypted = ((((charToEncrypt - 'a') + charFactor ) + ALPHABET_LENGTH) + 'a');
		
		}else if (validUpperCaseChar(charToEncrypt)){
			charEncrypted = ((((charToEncrypt - 'A') + charFactor ) + ALPHABET_LENGTH) + 'A');
		
			
		}else{
			charEncrypted = charToEncrypt;
		}
		
		return charEncrypted;
	}
	
	public String formatMessage(User user,String message){
		return "USER[" + user.getNumber() + "]MSG[" + getMsgNumber() +"]: " + message +"\n";
	}
	
	public boolean canEditLastMessage(User user){
		return lastUser.getNumber() == user.getNumber();
	}
	
	public void editLastMessage(User user, String message, int factor){
		if (lastMsgEnc)
			lastMsg = formatMessage(user, encMsg(message, factor));
		else
			lastMsg = formatMessage(user, message);	
	}
	
	private boolean validLowerCaseChar(char a){
		return a >= 'a' && a <= 'z' ;
	}
	
	private boolean validUpperCaseChar(char a){
		return a >= 'A' && a <= 'Z' ;
	}
	
	public int getMsgNumber(){
		return msgNumber;
	}
	
	public String getLastMsg(){
		return lastMsg;
	}
	
	public void reset (){
		lastMsg = "";
		lastMsgEnc = false;
		allMsgs = "";
		lastUser = NOBODY;
		msgNumber = 0;
	}
	
}
