public class Chat {
	
	private static final int USER_NUMBER_POSITION = 5;
	
	public int factor;
	public int msgNumber;
	public String AllMsgs; //BRUNO: Isto precisa de um nome melhor
	public String user1Name;
	public String user2Name;
	public String lastMsg = "";
	public String lastUser;
	
	
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
		lastUser = 
		//AllMsgs.concat(lastMsg); // EDUARDO: esta forma de escrita está me a dar erros de complicação
		AllMsgs += lastMsg;
		lastMsg = "USER[" + user + "]MSG[" + msgNumber +"]: " + msg +"\n";
	}
	
	public void addEncMsg(int user, String msg){
		
	}
	
	public boolean checkUser(String userTest){
		return (userTest.equals(user1Name) || userTest.equals(user2Name));
	}
	
		public void editLastMessage(int user, String message){
		lastMsg = "USER[" + user + "]MSG[" + msgNumber +"]: " + message;		
	}
	
	/*
	O metodo que se segue devolve o numero do utilizador que envia a ultima mensagem
	será util para confirmar se um dado utilizador porde alterar a ultima mensagem
	NOT WORKING 
	*/
	public int getUserLastMessage()	{
		//return (int)lastMsg.charAt(USER_NUMBER_POSITION);
	}	
}
