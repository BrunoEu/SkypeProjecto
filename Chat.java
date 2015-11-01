public class Chat {
	
	private static final int USER_NUMBER_POSITION = 5;
	private static final int ALPHABET_LENGTH = 26;
	
	public int factor;
	public int msgNumber;
	public String allMsgs;
	public String user1Name;
	public String user2Name;
	public String lastMsg;
	public int lastUser;
	
	
	public Chat(String name1, String name2, int newFactor){
		user1Name = name1;
		user2Name = name2;
		factor = newFactor;
		reset();
	}
	
	public String showChat(){
		return allMsgs + lastMsg;
	}
	
	public void addMsg(int user,String msg){
		//incrementar antes pois está inicializada a 0 mas começa na mensagem 1 e para facilitar o edditLastMessage()
		msgNumber++;
		lastUser = user;
		//AllMsgs.concat(lastMsg); // EDUARDO: esta forma de escrita está me a dar erros de complicação
		allMsgs += lastMsg;
		lastMsg = "USER[" + user + "]MSG[" + msgNumber +"]: " + msg+"\n";
	}
	
	public void addEncMsg(int user, String msg){
		addMsg(user, encMsg(msg));
	}
	
	public void closeChat(){
		String log = allMsgs.concat(lastMsg);
		//addToLog(log);
		reset();
	}
	
	public String encMsg(String msg){
		int i = 0;
		String msgEnc = "";
		char charEnc;
		while(i < msg.length()){
			charEnc = msg.charAt(i);
			if (validChar(charEnc)){
				charEnc += factor;
				if(!(validChar(charEnc)))
					charEnc -= ALPHABET_LENGTH;
			}
			msgEnc += charEnc;
			i++;
		}
		return msgEnc;
	}
	
	public void editLastMessage(int user, String message){
		lastMsg = "USER[" + user + "]MSG[" + msgNumber +"]: " + message;		
	}
	
	public boolean checkUser(String userTest){
		return (userTest.equals(user1Name) || userTest.equals(user2Name));
	}
	
	public boolean checkUserLastMessage(int user)	{
		return (user == lastUser);
	}
	
	public boolean validChar(char a){
		if (a >= 'a' && a <= 'z' || a >= 'A' && a <= 'Z')
			return true;
		else
			return false;
	}
	
	public void reset (){
		lastMsg = "";
		allMsgs = "";
		lastUser= 0;
		msgNumber = 0;
	}
	
}
