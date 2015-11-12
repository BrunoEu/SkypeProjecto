
public class Conversation {

	/*** CONSTANTES ***/
	
	private static final int ALPHABET_LENGTH = 26;
	private static final User NOBODY = new User ("", 0);
	
	
	/*** VARIÁVEIS DE INSTÂNCIA ***/
	
	private int msgNumber;
	private String allMsgs;
	private String lastMsg;
	private boolean lastMsgEncrypted;
	private User lastUser;
	
	
	/*** CONSTRUTOR ***/
	
	public Conversation(User newUser1, User newUser2){
		reset();
	}
	
	
	/*** DEVOLVE A CONVERSA ATUAL ***/
	
	public String showConversation (){
		return allMsgs + lastMsg;
	}
	
	
	/*** ADICIONA MENSAGEM À CONVERSA ATUAL ***/
	
	public void addMsg(User user, String msg){
		msgNumber++;
		lastUser = user;
		allMsgs = allMsgs.concat(lastMsg);
		lastMsg = formatMessage(user, msg);
		lastMsgEncrypted = false;
	}
	
	
	/*** ADICIONA MENSAGEM ENCRIPTADA ***/
	
	public void addEncMsg(User user, String msg, int factor){
		addMsg(user, encMsg(msg, factor));
		lastMsgEncrypted = true;
	}
	
	
	/*** ENCRIPTA UMA MENSAGEM ***/
	
	private String encMsg(String msg, int factor){
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
	
	
	/*** ENCRIPTA UM CARACTER ***/
	
	private char encryptChar(char charToEncrypt, int factor){
		char charEncrypted;
		
		if (validLowerCaseChar(charToEncrypt)){
			charEncrypted = (char)((charToEncrypt - 'a' + factor) % ALPHABET_LENGTH + 'a');
			
		}else if (validUpperCaseChar(charToEncrypt)){
			charEncrypted = (char)((charToEncrypt - 'A' + factor ) % ALPHABET_LENGTH + 'A');
			
		}else{
			charEncrypted = charToEncrypt;
		}
		
		return charEncrypted;
	}
	
	
	/*** VERIFICA SE UM CARACTER É UMA LETRA MAIÚSCULA ***/
	
	private boolean validUpperCaseChar(char a){
		return a >= 'A' && a <= 'Z' ;
	}
	
	
	/*** VERIFICA SE UM CARACTER É UMA LETRA MINÚSCULA ***/

	private boolean validLowerCaseChar(char a){
		return a >= 'a' && a <= 'z' ;
	}
	
	
	/*** DEVOLVE A MENSAGEM FORMATADA ***/

	private String formatMessage(User user,String message){
		return "USER[" + user.getNumber() + "]MSG[" + getMsgNumber() +"]: " + message +"\n";
	}
	
	
	/*** EDITA A ULTIMA MENSAGEM ***/
	
	public void editLastMessage(User user, String message, int factor){
		if (lastMsgEncrypted)
			lastMsg = formatMessage(user, encMsg(message, factor));
		else
			lastMsg = formatMessage(user, message);	
	}
	
	
	/*** VERIFICA SE O UTILIZADOR PODE EDITAR A ULTIMA MENSAGEM ***/

	public boolean canEditLastMessage(User user){
		return lastUser.getNumber() == user.getNumber();
	}
	
	
	/*** DEVOLVE O NUMERO DA MENSAGEM ***/
	
	public int getMsgNumber(){
		return msgNumber;
	}
	
	
	/*** DEVOLVE A ULTIMA MENSAGEM ***/
	
	public String getLastMsg(){
		return lastMsg;
	}
	
	
	/*** REINICIA AS VARIÁVEIS ***/
	
	public void reset (){
		lastMsg = "";
		lastMsgEncrypted = false;
		allMsgs = "";
		lastUser = NOBODY;
		msgNumber = 0;
	}
	
}
