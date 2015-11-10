public class Chat {
	
	/*** CONSTANTES ***/
	
	private static final int MAX_FACTOR = 26;
	private static final int MIN_FACTOR = 0;
	
	/*****************/
	
	
	/*** VARIÁVEIS DE INSTÂNCIA ***/
	
	private int factor;
	private User user1, user2;
	private String log;
	private Conversation currentConversation;

	/*****************************/

	
	/*** CONSTRUTOR ***
	 * 
	 * @param name1 Nome do utilizador 1
	 * @param name2 Nome do utilizador 2
	 * @param newFactor Factor de Transaçao
	 */
	 
	public Chat(String name1, String name2, int newFactor){
		user1 = new User(name1, 1);
		user2 = new User(name2, 2);
		factor = newFactor;
		log = initLog();
		currentConversation = new Conversation(user1, user2);
	}
	
	
	/*** 
	 * 
	 * @return Devolve a string com a conversa atual
	 */
	
	public String showChat(){
		return currentConversation.showConversation();
	}
	
	
	/***
	 * 
	 * @return 	"Nao ha conversas anteriores" Se o log nao tiver conversas
	 * 			log Se tiver conversas
	 */
	
	public String showLog(){
		if (log.equals(initLog()))
			return "Nao ha conversas anteriores\n";
		else
			return log;
	}
	
	
	/***
	 * 
	 * @param user 	Numero do utilizador
	 * @param msg 	Mensagem
	 * 
	 * @pre validUser(user)
	 */
	
	public void addMsg(int user,String msg){
		currentConversation.addMsg(intToUser(user), msg);
	}
	
	
	/***
	 * 
	 * @param user 	Numero do utilizador
	 * @param msg 	Mensagem
	 * 
	 * @pre validUser(user)
	 */
	
	public void addEncMsg(int user, String msg){
		currentConversation.addEncMsg(intToUser(user), msg, factor);
	}
	
	
	/*** TERMINA A CONVERSA ***/
	
	public void closeConversation(){
		String conversation = currentConversation.showConversation();
		log = formatToLog(conversation);
		currentConversation.reset();
	}
	
	
	/**
	 * 
	 * @param user Numero do utilizador
	 * @pre validUser(user)
	 * 
	 * @return 	true Se pode editar mensagem
	 *			false Se nao pode editar mensagem
	 */
	
	public boolean canEditLastMessage(int user){
		return currentConversation.canEditLastMessage(intToUser(user));
	}
	
	
	/***
	 * 
	 * @param user 	Numero do utilizador
	 * @param msg 	Mensagem
	 * 
	 * @pre validUser(user)
	 */
	
	public void editLastMessage(int user, String msg){
		currentConversation.editLastMessage(intToUser(user), msg, factor);
	}
	
	
	/**
	 * 
	 * @return String da ultima mensagem da conversa atual
	 */
	
	public String getLastMsg(){
		return currentConversation.getLastMsg();
	}
	
	
	/**
	 * 
	 * @return Int numero da mensagem
	 */
	
	public int getMsgNumber(){
		return currentConversation.getMsgNumber();
	}
	
	
	/**
	 * 
	 * @param user Numero do utilizador
	 * @pre validUser(user)
	 * 
	 * @return 
	 */
	
	public boolean validUser(int user){
		return (user == user1.getNumber() || user == user2.getNumber());
	}
	
	public static boolean validFactor(int factor){
		return factor >= MIN_FACTOR && factor <= MAX_FACTOR;
	}
	
	public User intToUser(int user){
		if (user == user1.getNumber())
			return user1;
		else
			return user2;
	}
	
	public String initLog(){
		return "Utilizador 1: "+user1.getName()+"\nUtilizador 2: "+user2.getName();
	}
	
	public String formatToLog(String msg){
		return log.concat("\n\n**** NOVA CONVERSA ****\n").concat(msg);
	}
	
}
