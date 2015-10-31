public class Chat {
	
	private static final int USER_NUMBER_POSITION = 5;
	
	public int factor;
	public int msgNumber;
	public String AllMsgs; //BRUNO: Isto precisa de um nome melhor
	public String user1Name;
	public String user2Name;
	public String lastMsg = "";
	public int lastUser = 0;
	
	
	public Chat(String name1, String name2, int newFactor){
		user1Name = name1;
		user2Name = name2;
		factor = newFactor;
		msgNumber = 0;//EDUARDO: será necessário criar uma constante para o 0 ?
					//BRUNO: Não. Não faz sentido. Assim tas a fazer reset da variavél.
					//Está correcto assim.
	}
	
	public String showChat(){
		return AllMsgs + lastMsg;
	}
	
	public void addMsg(int user,String msg){
		//incrementar antes pois está inicializada a 0 mas começa na mensagem 1 e para facilitar o edditLastMessage()
		msgNumber++;
		lastUser = user;
		//AllMsgs.concat(lastMsg); // EDUARDO: esta forma de escrita está me a dar erros de complicação
		AllMsgs += lastMsg;
		lastMsg = "USER[" + user + "]MSG[" + msgNumber +"]: " + msg +"\n";
	}
	
	public void addEncMsg(int user, String msg){
		msgNumber++;
		lastUser = user;
		AllMsgs.concat(lastMsg);
		int i = 0;
		String msgEnc = "";
		char charEnc;
		while(i < msg.length()){
			charEnc = msg.charAt(i);
			charEnc += factor;
			msgEnc += charEnc;
			i++;
		}
		lastMsg = "USER[" + user + "]MSG[" + msgNumber +"]: " + msgEnc +"\n";
	}
	
	public void encMsg(String msg){
		
	}
	
	public void editLastMessage(int user, String message){
		lastMsg = "USER[" + user + "]MSG[" + msgNumber +"]: " + message;		
	}
	
	
	
	public boolean checkUser(String userTest){
		return (userTest.equals(user1Name) || userTest.equals(user2Name));
	}
	
	/*
	O metodo que se segue devolve o numero do utilizador que envia a ultima mensagem
	será util para confirmar se um dado utilizador pode alterar a ultima mensagem
	NOT WORKING 
	*/
	public boolean checkUserLastMessage()	{
		return (lastUser == lastMsg.charAt(USER_NUMBER_POSITION));
	}	
}
