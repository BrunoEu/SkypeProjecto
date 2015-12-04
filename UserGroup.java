
public class UserGroup {
	
	private static final int INDEX_ERROR = -1;
	private static final int GROWTH_RATE = 2;
	private static final int DEFAULT_SIZE = 32;
	
	private User[] group;
	private int groupCounter;
	
	private int currentUser;
	
	public UserGroup() {
		group = new User[DEFAULT_SIZE];
		groupCounter = 0;
		currentUser = -1;
	}
	
	public void addUser(User user){
		if( isFull() ){
			resize();
		}
		group[groupCounter++]= user;
	}
	
	//@pre hasUser(user)
	//não mantem ordem
	public void removeUser(User user){
		group[getIndex(user)] = group[groupCounter--];
	}

	public int getNumberUsers(){
		return groupCounter;
	}
	
	private boolean isFull(){
		return groupCounter == group.length;
	}
	
	private void resize(){
		User[] newGroup = new User[group.length *  GROWTH_RATE];
		
		for(int i = 0; i < groupCounter; i++){
			newGroup[i] = group[i];
		}
		
		group = newGroup;
	}

	private int getIndex(User user){

		int index = INDEX_ERROR;
		boolean found = false;
		
		for (int i = 0; (i< groupCounter) && (!found); i++ ){
			if (user.equals(group[i])){
				found = true;
				index = i;
			}
		}
		
		return index;
	}

	private int getIndex(int userNumber){
		int index = INDEX_ERROR;
		boolean found = false;
		
		for (int i = 0; (i< groupCounter) && (!found); i++ ){
			if (userNumber == group[i].getNumber()){
				found = true;
				index = i;
			}
		}
		
		return index;
	}
	
	public boolean hasUser(User user){
		return getIndex(user) != INDEX_ERROR;
	}

	public boolean hasUser(int userNumber) {
		return getIndex(userNumber) != INDEX_ERROR;
	}
	
	public boolean hasUsers(int[] userNumbers){
		for(int i = 0; i < userNumbers.length; i++){
			//sera melhor fazer assim a preguiçoso ou usar uma flag ?
			if(!hasUser(userNumbers[i])){
				return false;
			}
		}
		return true;
	}
	
	//@pre: hasUser(userNumber) 
	
 	public User getUser(int userNumber){
		int i = 0;
		User user = User.NOBODY;//para que não de erro ao dizer que pode não ser inicializada
		boolean found = false;
		
		for (;(i< groupCounter) && (!found); i++ ){
			if (userNumber == group[i].getNumber()){
				found = true;
				user = group[i];
			}
		}
		
		return user;
	}
	
 	//@pre: hasUsers(userNumbers)
	/*
	 * Recebe um array de numeros de utilizador e devolve um UserGroup com os users definidos
	 * Usa isto para dar aos Chats
	 */
	
	public UserGroup getSubGroup(int[] userNumbers){
		
		UserGroup subUserGroup = new UserGroup();
		
		for(int i = 0; i < userNumbers.length; i++ ){
			subUserGroup.addUser(getUser(userNumbers[i]));
		}
			
		return subUserGroup;
	}
	
	//Iterator
	
	public void initializIterator(){
		currentUser = 0;
	}
	
	public boolean hasNext(){
		return ( currentUser >= 0) && (currentUser < groupCounter);
	}
	
	//@pre: hasNext()
	
	public User next(){
		return group[currentUser++];
	}

	
}
