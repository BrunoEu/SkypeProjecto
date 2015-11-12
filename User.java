
public class User {

	/*** VARIÁVEIS DE INSTÂNCIA ***/
	
	private String userName;	//Nome do utilizador
	private int userNumber;		//Numero do utilizador
	
	
	/*** CONSTRUTOR ***/
	
	public User(String name, int number){
		userName = name;
		userNumber = number;
	}
	
	
	/*** DEVOLVE O NOME DO UTILIZADOR ***/
	
	public String getName(){
		return userName;
	}
	
	
	/*** DEVOLVE O NUMERO DO UTILIZADOR ***/
	
	public int getNumber(){
		return userNumber;
	}
	
}
