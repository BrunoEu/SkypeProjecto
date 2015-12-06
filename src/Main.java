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
					case SHOW_CHAT: processShowChat(facade); break;
					case PUBLISH_MSG: processPlainMsg(facade, in); break;
					case PUBLISH_ENC: processEncryptedMsg(facade, in); break;
					case CORRECT_MSG: processCorrectMsg(facade, in); break;
					case CLOSE_CHAT: processCloseConversation(facade); break;
					case SHOW_LOG: processShowLog(facade); break;
					case HELP: processHelp(); break;
					case EXIT: break;
					default: System.out.println("Opcao inexistente.");
				}
			} catch(InputMismatchException exception){
				System.out.println("ERRO: Input Invalido.");
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
		
		else
			facade.createChat(getUsersIds(facade, in), getFactor(in));
	}
	
	private static void processShowChat(Facade facade, Scanner in)throws InputMismatchException{
		if(facade.showChat().isEmpty())
			System.out.println("Conversa Vazia.");
		else
			System.out.print(facade.showChat());
	}	
	
	private static void processPlainMsg(Chat facade, Scanner in)throws InputMismatchException{
		publishMsg(facade, in, false);
	}	
	
	private static void processEncryptedMsg(Chat facade, Scanner in)throws InputMismatchException{
		publishMsg(facade, in, true);
	}	

	/*private static void publishMsg(Chat facade, Scanner in, boolean encrypted)throws InputMismatchException{
		if (facade.showChat().isEmpty())
			System.out.println("Nova conversa iniciada");
		
		int userNumber = getUserNumber(facade, in);
		if(encrypted)
			facade.addEncryptedMsg(userNumber, getMsg(in));
		else
			facade.addMsg(userNumber, getMsg(in));
		System.out.print(facade.formatMessage(userNumber, "Publicada"));
	}*/	
	
	private static void processCorrectMsg(Chat facade, Scanner in)throws InputMismatchException{
		if(facade.showChat().isEmpty())
			System.out.println("Conversa Vazia.");
		else{
			int userNumber = getUserNumber(facade, in);
			if (facade.canEditLastMessage(userNumber)){
				facade.editLastMessage(userNumber, getMsg(in));
				System.out.print("Mensagem Corrigida:\n"+facade.getLastMsg());
			}
			else{
				System.out.println("Utilizador " + userNumber + " nao e autor da mensagem mais recente.");
			}
		}
	}	
	
	private static void processCloseConversation(Facade facade, Scanner in)throws InputMismatchException{
		facade.closeConversation(getUsersIds(facade, in));
		System.out.println("Conversa terminada.");
	}

	
	private static void processShowLog(Chat facade)throws InputMismatchException{
		if (facade.showLog().equals(facade.initializeLog()))
			System.out.println("Nao ha conversas anteriores");
		else
			System.out.print(facade.showLog());
	}

	
	private static void processHelp(){
		System.out.println("\nVCP - Ver a conversa em progresso\n"+
						   "PNM - Publicar nova mensagem\n"+
						   "PME - Publicar mensagem encriptada\n"+
						   "CMA - Corrigir mensagem anterior\n"+
						   "ECP - Encerrar conversa em progresso\n"+
						   "MCA - Mostrar conversas anteriores\n"+
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
				System.out.println("O utilizador "+userNumber+" não existe. Dê por favor um identificador válido.");
		}while(!facade.validUserNumber(userNumber));
		
		return userNumber;
	}
	
	
	private static String getUsername(Facade facade, Scanner in){
		String name;
		
		do{
		System.out.print("Nome do Utilizador: ");
		name = in.nextLine();
		if(facade.nameTaken(name))
			System.out.println("Nome em utilização. Escolha um novo nome.");
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
