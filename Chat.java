public class Chat {
	
	private static final int MAX_FACTOR = 25;
	private static final int MIN_FACTOR = 1;
	private static final int USER_NOBODY = 0;
	private static final int USER1 = 1;
	private static final int USER2 = 2;
	private static final int ALPHABET_LENGTH = 26;
	
	public int factor;
	public int msgNumber;
	public String allMsgs;
	public String user1Name;
	public String user2Name;
	public String lastMsg;
	public int lastUser;
	
	
	public Chat(String name1, String name2, int newFactor){
		//user1Name = name1;
		//user2Name = name2;
		factor = newFactor;
		reset();
	}
	
	public String showChat(){
		return allMsgs + lastMsg;
	}
	
	public void addMsg(int user,String msg){
		//incrementar antes pois estÃ¡ inicializada a 0 mas comeÃ§a na mensagem 1 e para facilitar o edditLastMessage()
		msgNumber++;
		lastUser = user;
		//allMsgs.concat(lastMsg); // EDUARDO: esta forma de escrita estÃ¡ me a dar erros de complicaÃ§Ã£o
		allMsgs += lastMsg;
		lastMsg = formatMessage(user, msg);	
	}
	
	public void addEncMsg(int user, String msg){
		addMsg(user, encMsg(msg));
	}
	
	public void closeChat(){
		String log = showChat();
		//addToLog(log);
		reset();
	}
	
	public String encMsg(String msg){
		int index = 0;
		String msgEnc = "";
		char charEnc;
		while(index < msg.length()){
			charEnc = msg.charAt(index);
			/*if (validChar(charEnc)){
				charEnc += factor;
				if(!(validChar(charEnc)))
					charEnc -= ALPHABET_LENGTH;
			}*/
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
	
	public void editLastMessage(int user, String message){
		lastMsg = formatMessage(user, message);	
	}
	
	//EDUARDO: este metodo tá a ser usado ??
	public boolean checkUser(String userTest){
		return (userTest.equals(user1Name) || userTest.equals(user2Name));
	}
	
	public String formatMessage(int user,String message){
		return "USER[" + user + "]MSG[" + msgNumber +"]: " + message +"\n";
	}
	
	public boolean canEdditLastMessage(int user)	{
		return (user == lastUser);
	}
	
	public static boolean validUser(int user){
		return user == USER1 || user == USER2;
	}
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	
	//EDUARDO: pensso que se torna um metodo irrelevante
	private boolean validChar(char a){
		return (a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z');

	}
	
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
	
	public void reset (){
		lastMsg = "";
		allMsgs = "";
		lastUser= USER_NOBODY;
		msgNumber = 0;
	}
	
}
