
public class UserGroup {
	
	private static final int INDEX_ERROR = -1;
	private static final int GROWTH_RATE = 2;
	private static final int DEFAULT_SIZE = 2;
	
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
	public void removeUser(User user){
		group[getIndex(user)] = group[--groupCounter];
	}

	private void resize(){
		User[] newGroup = new User[group.length *  GROWTH_RATE];
		
		for(int i = 0; i < groupCounter; i++){
			newGroup[i] = group[i];
		}
		
		group = newGroup;
	}

	public boolean hasUser(User user){
		return getIndex(user) != INDEX_ERROR;
	}
	
 	private boolean isFull(){
		return groupCounter == group.length;
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
	
	public int getNumberUsers(){
		return groupCounter;
	}

	public User getUser(int userId){
		User user = User.NOBODY;//para que nao de erro ao dizer que pode nao ser inicializada
		boolean found = false;
		
		for (int i = 0;(i< groupCounter) && (!found); i++ ){
			if (userId == group[i].getNumber()){
				found = true;
				user = group[i];
			}
		}
		
		return user;
	}
	
	//@pre hasUser(userIds[i]) 
	//Verifica que todos os utilizadores
	//do vector existem
	public UserGroup getSubGroup(int[] userIds){
		
		UserGroup subUserGroup = new UserGroup();
		
		for(int i = 0; i < userIds.length; i++ ){
			subUserGroup.addUser(getUser(userIds[i]));
		}
			
		return subUserGroup;
	}
	
	//Iterator
	
	public void initializeIterator(){
		currentUser = 0;
	}
	
	public boolean hasNext(){
		return ( currentUser >= 0) && (currentUser < groupCounter);
	}
	
	//@pre hasNext()
	public User next(){
		return group[currentUser++];
	}

	public boolean equals(UserGroup groupToCompare){
		
		if(this.getNumberUsers() == groupToCompare.getNumberUsers()){
			for(int i = 0; i < groupCounter; i++)
				if(!groupToCompare.hasUser(group[i]))
					return false;
			return true;
		}
		
		return false;
	}

	public static UserGroup mergeGroups(UserGroup userGroup1, UserGroup userGroup2){
		UserGroup mergedUserGroup = new UserGroup();
		
		userGroup1.initializeIterator();
		
		while(userGroup1.hasNext())
			mergedUserGroup.addUser(userGroup1.next());
		
		userGroup2.initializeIterator();
		
		while(userGroup2.hasNext()){
			User user = userGroup2.next();
			if(!mergedUserGroup.hasUser(user))
				mergedUserGroup.addUser(user);
		}
		
		return mergedUserGroup;
		
	}
	
	
}
