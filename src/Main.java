import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


	/***************** constantes *****************/

	private static final String SAVE_FILE = "savefile.txt";

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
	private static final String SAVE_FROM_FILE = "GEA";
	private static final String LOAD_FROM_FILE = "CAF";
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
					case SAVE_FROM_FILE: processSaveToFile(facade); break;
					case LOAD_FROM_FILE: processLoadFromFile(facade); break;
					case HELP: processHelp(); break;
					case EXIT: processSaveToFile(facade); break;
					default: System.out.println("Opcao inexistente.");
				}
			} catch(InputMismatchException input){
				System.out.println("ERRO: Input Invalido.");
				in.nextLine();
			} catch (FileNotFoundException file){
				System.out.println("ERRO: Nome de Ficheiro Invalido. Por favor contacte o admnistrador.");
			}
		}while(!command.equals(EXIT));
		
		System.out.println("Aplicacao guardada e terminada. Ate a proxima.");

		in.close();
	}

	private static String processCommand(Scanner in){
		System.out.print("> ");
		return in.nextLine().toUpperCase().trim();
	}

	private static void processCreateUser(Facade facade, Scanner in){
		String name = getUsername(facade, in);
		facade.creatUser(name);
		System.out.println("Utilizador "+name+" com id "+facade.getLastUserId()+" criado com sucesso.");
	}

	private static void processNewChat(Facade facade, Scanner in){
		int[] userIds = getUsersIds(facade, in);

		if(facade.hasChat(userIds))
			System.out.println("Chat existente.");

		else
			facade.createChat(userIds, getFactor(in));
	}

//	MUDAR PARA QUE A MAIN SO CHAME UM METODO DA FACHADA
	private static void processShowContactedUsers(Facade facade, Scanner in){
		int userId = getId(facade, in);
		String contacted = "";
		
		facade.initializeContactedIterator(userId);

		while(facade.contactedUsersHasNext()){
			User user = facade.contactedUsersNext();
			
			contacted = contacted.concat(user.getName()+" ");
			contacted = contacted.concat(user.getNumber()+", ");
		}
		
		if(contacted.isEmpty())
			System.out.println("Nao ha utilizadores contactados.");

		else{
			System.out.println("Utilizadores contactados por "+facade.getName(userId)+" ("+userId+"): ");
			System.out.println(contacted);
		}
		
	}

//	MUDAR PARA QUE A MAIN SO CHAME UM METODO DA FACHADA
	private static void processShowAllUsers(Facade facade){
		String allUsers = "";
		
		facade.userListInitializeIterator();

		while(facade.userListHasNext()){
			User user = facade.userListNext();

			allUsers = allUsers.concat(user.getName()+" ");
			allUsers = allUsers.concat(user.getNumber()+", ");
		}

		if(allUsers.isEmpty())
			System.out.println("Nao ha utilizadores.");

		else{
			System.out.println("Utilizadores:\n"+allUsers);
		}

	}

	private static void processShowChat(Facade facade, Scanner in){
		int[] userIds = getUsersIds(facade, in);

		if(facade.showChat(userIds).isEmpty())
			System.out.println("Conversa Vazia.");

		else if(!facade.hasChat(userIds))
			System.out.println("Conversa inexistente");

		else
			System.out.print(facade.showChat(userIds));
	}

	private static void processPlainMsg(Facade facade, Scanner in){
		publishMsg(facade, in, false);
	}

	private static void processEncryptedMsg(Facade facade, Scanner in){
		publishMsg(facade, in, true);
	}

	private static void publishMsg(Facade facade, Scanner in, boolean encrypted){
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

	private static void processCorrectMsg(Facade facade, Scanner in){
		int[] userIds = getUsersIds(facade, in);

		if(!facade.hasChat(userIds))
			System.out.println("Conversa inexistente.");

		else if(facade.showChat(userIds).isEmpty())
			System.out.println("Conversa Vazia.");

		else{
			int senderId;
			try{
				senderId = getId(facade, in);
			}catch(InputMismatchException e){
				System.out.println("");
			}
			if (facade.canEditLastMessage(userIds, senderId)){
				facade.editLastMessage(userIds, senderId, getMsg(in));
				System.out.print("Mensagem Corrigida:\n"+facade.getLastMsg(userIds));
			}
			else{
				System.out.println("Utilizador " + senderId + " nao e autor da mensagem mais recente.");
			}
		}
	}

	private static void processCloseConversation(Facade facade, Scanner in){
		int[] userIds = getUsersIds(facade, in);

		if(facade.hasChat(userIds)){
			facade.closeConversation(userIds);
			System.out.println("Conversa terminada.");
		}
		else
			System.out.println("Conversa inexistente.");
	}

	private static void processShowLog(Facade facade, Scanner in){
		int[] userIds = getUsersIds(facade, in);

		if(!facade.hasChat(userIds))
			System.out.println("Conversa inexistente.");
		else if (facade.showLog(userIds).equals(facade.initializeLog(userIds)))
			System.out.println("Nao ha conversas anteriores");
		else
			System.out.print(facade.showLog(userIds));
	}

	private static void processSaveToFile(Facade facade)throws FileNotFoundException{
		PrintWriter saveFilePrinter = new PrintWriter(SAVE_FILE);

		saveFilePrinter.print(facade.exportUsers());
		saveFilePrinter.print(facade.exportChats());

		saveFilePrinter.close();
	}

	private static void processLoadFromFile(Facade facade)throws FileNotFoundException{
		FileReader reader = new FileReader(SAVE_FILE);
		Scanner fileReader = new Scanner(reader);
		String inp;
		
		facade.reset();
		
		while(fileReader.hasNextLine()){
			inp = fileReader.nextLine();
			switch(inp){
				case Facade.USER_LABEL: createUserFromFile(facade, fileReader); break;
				case Facade.CHAT_LABEL: createChatFromFile(facade, fileReader); break;
				default: System.out.println("ERRO. Contacte a administracao.");
			}
		}
		
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
						   "GEA - Gravar estado da aplicacao para ficheiro\n"+
						   "CAF - Carregar aplicacao de ficheiro\n"+
						   "A - Ajuda\n"+
						   "S - Sair");
	}

	private static String readUntil(String untilMsg, Scanner fileReader){
		String result = "";
		String line = "";
				
		do{
			line = fileReader.nextLine();
			if(!line.equals(untilMsg))
				result += line + "\n";
		}while(!line.equals(untilMsg));
		
		return result;	
		
	}
	
	private static void createUserFromFile(Facade facade, Scanner fileReader){
		facade.importUser(fileReader.nextLine(), fileReader.nextInt());
		fileReader.nextLine();
	}
	
	private static void createChatFromFile(Facade facade, Scanner fileReader){
		int[] users = new int[2];
		int newFactor;
		int msgNumber;
		String conversation = "";
		String lastMsg = "";
		boolean lastMsgEncrypted;
		int lastUserId;
		String log = "";
		
		users[0] = fileReader.nextInt();
		fileReader.nextLine();
		users[1] = fileReader.nextInt();
		fileReader.nextLine();
		newFactor = fileReader.nextInt();
		fileReader.nextLine();
		msgNumber = fileReader.nextInt();
		fileReader.nextLine();
		conversation = readUntil(Facade.END_CONVERSATION_LABEL, fileReader);
		lastMsg = readUntil(Facade.END_LAST_MESSAGE_LABEL, fileReader);
		lastMsgEncrypted = fileReader.nextBoolean();
		lastUserId = fileReader.nextInt();
		fileReader.nextLine();
		log = readUntil(Facade.END_LOG_LABEL, fileReader);
		
		facade.importChat(users, newFactor, msgNumber, conversation, lastMsg, lastMsgEncrypted, lastUserId, log);
	}

	private static String getMsg(Scanner in){
		System.out.print("Mensagem: ");
		String msg = in.nextLine();
		return msg;
	}

	private static int[] getUsersIds(Facade facade, Scanner in)throws InputMismatchException{
		int[] ids = new int[2];

		ids[0] = getId(facade, in, 1);

		do{
			ids[1] = getId(facade, in, 2);
			if (ids[0]==ids[1])
				System.out.println("Utilizadores iguais.");
		}while(ids[0]==ids[1]);

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
				System.out.println("O utilizador "+userNumber+" nao existe. De por favor um identificador válido.");
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
