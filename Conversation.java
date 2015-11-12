
public class Conversation {
	
	
	/*** CONSTANTES ***/
	
	private static final char FIRST_UPPER_CASE_CHAR = 'A';
	private static final char FIRST_LOWER_CASE_CHAR = 'a';
	private static final int ALPHABET_LENGTH = 26;
	private static final User NOBODY = new User ("", 0);
	
	
	/*** VARIÁVEIS DE INSTÂNCIA ***/
	
	private int msgNumber;
	private String conversation;
	private String lastMsg;
	private boolean lastMsgEncrypted;
	private User lastUser;
	
	
	/*** CONSTRUTOR ***/
	
	public Conversation(User newUser1, User newUser2){
		reset();
	}
	
	
	/*** DEVOLVE A CONVERSA ATUAL ***/
	
	public String showConversation (){
		if (conversation.isEmpty())
			return "Conversa vazia\n";
		else
			return conversation + lastMsg;
	}
	
	
	/*** ADICIONA MENSAGEM À CONVERSA ATUAL ***/
	
	public void addMsg(User user, String msg){
		msgNumber++;
		lastUser = user;
		conversation = conversation.concat(lastMsg);
		lastMsg = formatMessage(user, msg);
		lastMsgEncrypted = false;
	}
	
	
	/*** ADICIONA MENSAGEM ENCRIPTADA ***/
	//@pre factor > 0
	
	public void addEncryptedMsg(User user, String msg, int factor){
		addMsg(user, encryptMsg(msg, factor));
		lastMsgEncrypted = true;
	}
	
	
	/*** ENCRIPTA UMA MENSAGEM ***/
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
	
	
	/*** ENCRIPTA UM CARACTER ***/
	//@pre factor > 0
	
	private char encryptChar(char charToEncrypt, int factor){
		char charEncrypted;
		char firstChar = 0;	//Caracter nulo
		
		if(validLowerCaseChar(charToEncrypt) || validUpperCaseChar(charToEncrypt)){
			if (validLowerCaseChar(charToEncrypt)){
				firstChar = FIRST_LOWER_CASE_CHAR;
			}
			else if(validUpperCaseChar(charToEncrypt)){
				firstChar = FIRST_UPPER_CASE_CHAR;
			}
			
			charEncrypted = (char)((charToEncrypt - firstChar + factor) % ALPHABET_LENGTH + firstChar);
		}
		
		else{
			charEncrypted = charToEncrypt;
		}
		
		return charEncrypted;
	}
	
	
	/*** VERIFICA SE UM CARACTER É UMA LETRA MAIÚSCULA ***/
	
	private static boolean validUpperCaseChar(char a){
		return a >= 'A' && a <= 'Z' ;
	}
	
	
	/*** VERIFICA SE UM CARACTER É UMA LETRA MINÚSCULA ***/

	private static boolean validLowerCaseChar(char a){
		return a >= 'a' && a <= 'z' ;
	}
	
	
	/*** DEVOLVE A MENSAGEM FORMATADA ***/

	private String formatMessage(User user,String message){
		return "USER[" + user.getNumber() + "]MSG[" + getMsgNumber() +"]: " + message +"\n";
	}
	
	
	/*** EDITA A ULTIMA MENSAGEM ***/
	
	public void editLastMessage(User user, String message, int factor){
		if (lastMsgEncrypted)
			lastMsg = formatMessage(user, encryptMsg(message, factor));
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
		conversation = "";
		lastUser = NOBODY;
		msgNumber = 0;
	}
	
}
