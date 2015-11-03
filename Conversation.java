
public class Conversation {

	private static final int ALPHABET_LENGTH = 26;
	private static final User NOBODY = new User ("", 0);
	
	public int msgNumber;
	public User user1;
	public User user2;
	public String allMsgs;
	public String lastMsg;
	public User lastUser;
	
	public Conversation(User newUser1, User newUser2, int newFactor){
		user1 = newUser1;
		user2 = newUser2;
		reset();
	}
	
	public String showConversation (){
		return allMsgs + lastMsg;
	}
	
	public void addMsg(User user, String msg){
		msgNumber++;
		lastUser = user;
		allMsgs.concat(lastMsg);
		lastMsg = formatMessage(user, msg);
	}
	
	public void addEncMsg(User user, String msg, int factor){
		addMsg(user, encMsg(msg, factor));
	}
	
	public String encMsg(String msg, int factor){
		int index = 0;
		String msgEnc = "";
		char charEnc;
		while(index < msg.length()){
			charEnc = msg.charAt(index);
			if (validLowerCaseChar(charEnc)){
				charEnc += factor;
				if(lowerCaseOverflow(charEnc)){
					charEnc -= ALPHABET_LENGTH;
				}
			}else{ 
				if (validUpperCaseChar(charEnc)){
				charEnc += factor;
					if(upperCaseOverflow(charEnc)){
						charEnc -= ALPHABET_LENGTH;
					}
				}
			}
			msgEnc += charEnc;
			index++;
		}
		return msgEnc;
	}
	
	public String formatMessage(User user,String message){
		return "USER[" + user.getNumber() + "]MSG[" + msgNumber +"]: " + message +"\n";
	}
	
	public boolean canEditLastMessage(User user){
		return lastUser.getNumber() == user.getNumber();
	}
	
	public void editLastMessage(User user, String message){
		lastMsg = formatMessage(user, message);	
	}
	
	public static boolean validUser(User user){
		return (user.getNumber() == user1.getNumber() || user.getNumber() == user2.getNumber());
	}
	
	/*public User getLastUser(){
		return lastUser;
	}*/
	
	private boolean validLowerCaseChar(char a){
		return a >= 'a' && a <= 'z' ;
	}
	
	private boolean validUpperCaseChar(char a){
		return a >= 'A' && a <= 'Z' ;
	}
	
	private boolean lowerCaseOverflow(char a){
		return a > 'z';
	}
	
	private boolean upperCaseOverflow(char a){
		return a > 'Z';
	}
	
	public int getMsgNumber(){
		return msgNumber;
	}
	
	public static User intToUser(int user){
		if (user == user1.getNumber())
			return user1;
		else
			return user2;
	}
	
	public void reset (){
		lastMsg = "";
		allMsgs = "";
		lastUser = NOBODY;
		msgNumber = 0;
	}
	
}
