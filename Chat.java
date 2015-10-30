public class Chat {
	
	public int Factor;
	public String AllMsgs; //BRUNO: Isto precisa de um nome melhor
	public String user1Name;
	public String user2Name;
	public String lastMsg = "";
	
	public Chat(String name1, String name2, int newFactor){
		user1Name = name1;
		user2Name = name2;
		Factor = newFactor;
	}
	
	public String showChat(){
		return AllMsgs;
	}
	
	public void addMsg(String msg){
		AllMsgs.concat(lastMsg);
		lastMsg = msg;
	}
	
	public void addEncMsg(String msg){
		
	}
	
	public boolean checkUser(String userTest){
		return (userTest.equals(user1Name) || userTest.equals(user2Name));
	}
		
}

