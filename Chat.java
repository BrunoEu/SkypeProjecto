public class Chat {
	
	/*** CONSTANTES ***/
	
	private static final int MAX_FACTOR = 26;
	private static final int MIN_FACTOR = -60;
	
	
	/*** VARIÁVEIS DE INSTÂNCIA ***/
	
	private int factor;			//Factor de translação
	private User user1, user2;	//Utilizadores 1 e 2
	private String log;			//Histórico de conversas
	private Conversation currentConversation;	//Conversa atual

	
	/*** CONSTRUTOR ***/
	// @pre validFactor(newFactor)
	
	public Chat(String name1, String name2, int newFactor){
		user1 = new User(name1, 1);
		user2 = new User(name2, 2);
		factor = newFactor;
		log = initLog();
		currentConversation = new Conversation(user1, user2);
	}
	
	
	/*** DEVOLVE A CONVERSA EM PROGRESSO ***/
	
	public String showChat(){
		return currentConversation.showConversation();
	}
	
	
	/*** DEVOLVE AS CONVERSAS ANTERIORES ***/
	
	public String showLog(){
		if (log.equals(initLog()))
			return "Nao ha conversas anteriores\n";
		else
			return log;
	}
	
	
	/*** ADICIONA MENSAGEM ***/
	//@pre validUser(user)
	
	public void addMsg(int user,String msg){
		currentConversation.addMsg(intToUser(user), msg);
	}
	
	
	/*** ADICIONA MENSAGEM ENCRIPTADA ***/
	//@pre validUser(user)
	
	public void addEncMsg(int user, String msg){
		currentConversation.addEncryptedMsg(intToUser(user), msg, factor);
	}
	
	
	/*** TERMINA A CONVERSA ***/
	
	public void closeConversation(){
		String conversation = currentConversation.showConversation();
		log = formatToLog(conversation);
		currentConversation.reset();
	}
	
	
	/*** VERIFICA SE PODE EDITAR A ULTIMA MENSAGEM ***/
	//@pre validUser(user)
	
	public boolean canEditLastMessage(int user){
		return currentConversation.canEditLastMessage(intToUser(user));
	}
	
	
	/*** EDITA ULTIMA MENSAGEM ***/
	//@pre validUser(user)
	
	public void editLastMessage(int user, String msg){
		currentConversation.editLastMessage(intToUser(user), msg, factor);
	}
	
	
	/*** DEVOLVE A ULTIMA MENSAGEM ***/
	
	public String getLastMsg(){
		return currentConversation.getLastMsg();
	}
	
	
	/*** DEVOLVE O NUMERO DA MENSAGEM A PUBLICAR ***/
	
	public int getMsgNumber(){
		return currentConversation.getMsgNumber();
	}
	
	
	/*** VERIFICA SE O NUMERO DE UTILIZADOR É VÁLIDO ***/
	
	public boolean validUserNumber(int user){
		return (user == user1.getNumber() || user == user2.getNumber());
	}
	
	
	/*** VERIFICA SE O FACTOR É VÁLIDO ***/
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	
	
	/*** DEVOLVE O OBJECTO UTILIZADOR CORRESPONDENTE AO NUMERO DE UTILIZADOR ***/
	//@pre validUser(user)
	
	public User intToUser(int user){
		if (user == user1.getNumber())
			return user1;
		else
			return user2;
	}
	
	
	/*** DEVOLVE A STRING INICIAL DO LOG ***/
	
	public String initLog(){
		return "Utilizador 1: "+user1.getName()+"\nUtilizador 2: "+user2.getName();
	}
	
	
	/*** DEVOLVE A STRING FORMATADA DE ACORDO COM O LOG ***/
	
	public String formatToLog(String msg){
		return log.concat("\n\n**** NOVA CONVERSA ****\n").concat(msg);
	}
	
}
