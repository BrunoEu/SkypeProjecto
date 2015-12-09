import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
	
	
	/***************** constantes *****************/
	
	private static final String CREATE_USER = "CNU";
	private static final String NEW_CHAT = "INC";
	private static final String SHOW_CONTACTED_USERS = "LUC";
	private static final String SHOW_ALL_USERS = "LTU";
	private static final String SHOW_CHAT = "VCP";
	private static final String PUBLISH_MSG = "PNM";
	private static final String PUBLISH_ENC = "PME";
	private static final String CORRECT_MSG = "CMA";
	private static final String CLOSE_CHAT = "ECP";
	private static final String SHOW_LOG = "MCA";
	private static final String HELP = "A";
	private static final String EXIT = "S";
	
	/*
		Nota: o metodo trim() e chamado ao longo
		da classe para retirar os espacos do inicio
		e fim da String.
	*/
	
	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);

		Facade facade = new Facade();
		
		String command;
		
		
		processHelp();
		
		do{
			command = processCommand(in);
			try{
				switch(command){
					case CREATE_USER: processCreateUser(facade, in); break;
					case NEW_CHAT: processNewChat(facade, in); break;
					case SHOW_CONTACTED_USERS: processShowContactedUsers(facade, in); break;
					case SHOW_ALL_USERS: processShowAllUsers(facade); break;
					case SHOW_CHAT: processShowChat(facade, in); break;
					case PUBLISH_MSG: processPlainMsg(facade, in); break;
					case PUBLISH_ENC: processEncryptedMsg(facade, in); break;
					case CORRECT_MSG: processCorrectMsg(facade, in); break;
					case CLOSE_CHAT: processCloseConversation(facade, in); break;
					case SHOW_LOG: processShowLog(facade, in); break;
					case HELP: processHelp(); break;
					case EXIT: break;
					default: System.out.println("Opcao inexistente.");
				}
			} catch(InputMismatchException exception){
				System.out.println("ERRO: Input Invalido.");
				in.nextLine();
			}
		}while(!command.equals(EXIT));
		
		System.out.println("Aplicacao terminada. Ate a proxima.");
	
		in.close();
	}

	private static String processCommand(Scanner in){
		System.out.print("> ");
		return in.nextLine().toUpperCase().trim();
	}
	
	private static void processCreateUser(Facade facade, Scanner in){
		facade.creatUser(getUsername(facade, in));
	}
	
	private static void processNewChat(Facade facade, Scanner in)throws InputMismatchException{
		int[] userIds = getUsersIds(facade, in);
		
		if(facade.hasChat(userIds))
			System.out.println("Chat existente.");
		
		else if(userIds[0]==userIds[1])
			System.out.println("Utilizadores iguais.");
		
		else
			facade.createChat(userIds, getFactor(in));
	}
	
	private static void processShowContactedUsers(Facade facade, Scanner in){
		int userId = getId(facade, in);
		int[] contactedUsersIds = facade.showContactedIds(userId);
		String[] contactedUsersNames = facade.showContactedNames(userId);
		
		if(contactedUsersIds.length == 0)
			System.out.println("Nao ha utilizadores contactados.");
		
		else{
			System.out.println("Utilizadores contactados por "+facade.getName(userId)+" ("+userId+"): ");
			
			for (int i = 0; i < contactedUsersIds.length; i++)
				System.out.print(contactedUsersNames[i]+" "+contactedUsersIds[i]+", ");
			System.out.println();
		}
	}
	
	private static void processShowAllUsers(Facade facade){
		int[] usersIds = facade.showAllIds();
		String[] usersNames = facade.showAllNames();
		
		if(usersIds.length == 0)
			System.out.println("Nao ha utilizadores.");
		
		else{
		
			System.out.println("Utilizadores: ");
		
			for(int i = 0; i < usersIds.length; i++)
				System.out.print(usersNames[i]+" "+usersIds[i]+", ");
			System.out.println();
		}
	
	}
	
	private static void processShowChat(Facade facade, Scanner in)throws InputMismatchException{
		int[] userIds = getUsersIds(facade, in);
		
		if(facade.showChat(userIds).isEmpty())
			System.out.println("Conversa Vazia.");
		
		else if(!facade.hasChat(userIds))
			System.out.println("Conversa inexistente");
		
		else
			System.out.print(facade.showChat(userIds));
	}
	
	private static void processPlainMsg(Facade facade, Scanner in)throws InputMismatchException{
		publishMsg(facade, in, false);
	}	
	
	private static void processEncryptedMsg(Facade facade, Scanner in)throws InputMismatchException{
		publishMsg(facade, in, true);
	}	
	
	private static void publishMsg(Facade facade, Scanner in, boolean encrypted)throws InputMismatchException{
		int[] userIds = getUsersIds(facade, in);
		
		if(!facade.hasChat(userIds))
			System.out.println("Conversa inexistente.");
		
		else{
			int senderId = getId(facade, in);
			if(facade.chatHasUser(userIds, senderId)){
				facade.addMessage(userIds, senderId, getMsg(in), encrypted);
				System.out.print(facade.formatMsg(userIds, senderId, "Publicada"));
			}
			else
				System.out.println("Utilizador "+senderId+" nao pertence ao chat.");	
		}
		
	}
	
	private static void processCorrectMsg(Facade facade, Scanner in)throws InputMismatchException{
		int[] userIds = getUsersIds(facade, in);
		
		if(!facade.hasChat(userIds))
			System.out.println("Conversa inexistente.");
		
		else if(facade.showChat(userIds).isEmpty())
			System.out.println("Conversa Vazia.");
		
		else{
			int senderId = getId(facade, in);
			if (facade.canEditLastMessage(userIds, senderId)){
				facade.editLastMessage(userIds, senderId, getMsg(in));
				System.out.print("Mensagem Corrigida:\n"+facade.getLastMsg(userIds));
			}
			else{
				System.out.println("Utilizador " + senderId + " nao e autor da mensagem mais recente.");
			}
		}
	}	
	
	private static void processCloseConversation(Facade facade, Scanner in)throws InputMismatchException{
		int[] userIds = getUsersIds(facade, in);
		
		if(facade.hasChat(userIds)){
			facade.closeConversation(userIds);
			System.out.println("Conversa terminada.");
		}
		else
			System.out.println("Conversa inexistente.");
	}

	
	private static void processShowLog(Facade facade, Scanner in)throws InputMismatchException{
		int[] userIds = getUsersIds(facade, in);
		
		if(!facade.hasChat(userIds))
			System.out.println("Conversa inexistente.");
		else if (facade.showLog(userIds).equals(facade.initializeLog(userIds)))
			System.out.println("Nao ha conversas anteriores");
		else
			System.out.print(facade.showLog(userIds));
	}

	
	private static void processHelp(){
		System.out.println("\nCNU - Criar novo utilizador\n"+
						   "INC - Iniciar nova conversa\n"+
						   "VCP - Ver a conversa em progresso\n"+
						   "PNM - Publicar nova mensagem\n"+
						   "PME - Publicar mensagem encriptada\n"+
						   "CMA - Corrigir mensagem anterior\n"+
						   "LUC - Listar utilizadores contactados\n"+
						   "LTU - Listar todos os utilizadores\n"+
						   "ECP - Encerrar conversa em progresso\n"+
						   "MCA - Mostrar conversas anteriores\n"+
						   "GEA - Gravar estado da aplicaçao para ficheiro\n"+
						   "CAF - Carregar aplicacao de ficheiro"+
						   "A - Ajuda\n"+
						   "S - Sair");
	}
	
	
	private static String getMsg(Scanner in){
		System.out.print("Mensagem: ");
		String msg = in.nextLine();
		return msg;
	}
	
	private static int[] getUsersIds(Facade facade, Scanner in)throws InputMismatchException{
		int[] ids = new int[2];
		
		ids[0] = getId(facade, in, 1);
		ids[1] = getId(facade, in, 2);
		
		return ids;
	}
	
	private static int getId(Facade facade, Scanner in, int number)throws InputMismatchException{
		int userNumber;
		
		do{
			System.out.print("Id "+number+": ");
			userNumber = in.nextInt();
			in.nextLine();
			if (!facade.validUserNumber(userNumber))
				System.out.println("O utilizador "+userNumber+" nao existe. De por favor um identificador valido.");
		}while(!facade.validUserNumber(userNumber));
		
		return userNumber;
	}
	
	private static int getId(Facade facade, Scanner in)throws InputMismatchException{
		int userNumber;
		
		do{
			System.out.print("Id: ");
			userNumber = in.nextInt();
			in.nextLine();
			if (!facade.validUserNumber(userNumber))
				System.out.println("O utilizador "+userNumber+" nao existe. De por favor um identificador vÃ¡lido.");
		}while(!facade.validUserNumber(userNumber));
		
		return userNumber;
	}
	
	private static String getUsername(Facade facade, Scanner in){
		String name;
		
		do{
		System.out.print("Nome do Utilizador: ");
		name = in.nextLine();
		if(facade.nameTaken(name))
			System.out.println("Nome em utilizacao. Escolha um novo nome.");
		}while(facade.nameTaken(name));
			
		return name;
	}
	
	
	private static int getFactor(Scanner in)throws InputMismatchException{
		int factor;
		
		do{
			System.out.print("Insira um factor de translacao: ");
			factor = in.nextInt();
			in.nextLine();
			if (!Facade.validFactor(factor))
				System.out.println("Factor invalido. [1, 26]");
		}while(!Facade.validFactor(factor));
		
		return factor;
	}
	
}
