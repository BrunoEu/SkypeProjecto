import java.util.Scanner;


public class Main {
	
	/***************** constantes *****************/

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
	
	private static String processCommand(Scanner in){
		System.out.print("> ");
		return in.nextLine().toUpperCase().trim();
	}
	
	
	private static void processShowChat(Chat newChat){
		if(newChat.showChat().isEmpty())
			System.out.println("Conversa Vazia.");
		else
			System.out.print(newChat.showChat());
	}
	
	
	private static void processPlainMsg(Chat newChat, Scanner in){
		publishMsg(newChat, in, false);
	}
	
	
	private static void processEncryptedMsg(Chat newChat, Scanner in){
		publishMsg(newChat, in, true);
	}
	

	private static void publishMsg(Chat newChat, Scanner in, boolean encrypted){
		if (newChat.showChat().isEmpty())
			System.out.println("Nova conversa iniciada");
		
		int userNumber = getUserNumber(newChat, in);
		if(encrypted)
			newChat.addEncMsg(userNumber, getMsg(in));
		else
			newChat.addMsg(userNumber, getMsg(in));
		System.out.print(newChat.formatMessage(userNumber, "Publicada"));
	}
	
	
	private static void processCorrectMsg(Chat newChat, Scanner in){
		if(newChat.showChat().isEmpty())
			System.out.println("Conversa Vazia.");
		else{
			int userNumber = getUserNumber(newChat, in);
			if (newChat.canEditLastMessage(userNumber)){
				newChat.editLastMessage(userNumber, getMsg(in));
				System.out.print("Mensagem Corrigida:\n"+newChat.getLastMsg());
			}
			else{
				System.out.println("Utilizador " + userNumber + " nao e autor da mensagem mais recente.");
			}
		}
	}
	
	
	private static void processCloseChat(Chat newChat){
		newChat.closeConversation();
		System.out.println("Conversa terminada.");
	}

	
	private static void processShowLog(Chat newChat) {
		if (newChat.showLog().equals(newChat.initLog()))
			System.out.println("Nao ha conversas anteriores");
		else
			System.out.print(newChat.showLog());
	}

	
	private static void processHelp() {
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
	
	
	private static int getUserNumber(Chat newChat, Scanner in){
		int userNumber;
		do{
			System.out.print("Utilizador: ");
			userNumber = in.nextInt();
			in.nextLine();
			if (!newChat.validUserNumber(userNumber))
				System.out.println("Utilizador desconhecido.");
		}while(!newChat.validUserNumber(userNumber));
		return userNumber;
	}

	
	private static String getUsername(int userNumber, Scanner in){
		System.out.print("Nome do Utilizador "+userNumber+": ");
		return in.nextLine();
	}
	
	
	private static int getFactor(Scanner in){
		int factor;
		
		do{
			System.out.print("Insira um factor de translacao: ");
			factor = in.nextInt();
			in.nextLine();
			if (!Chat.validFactor(factor))
				System.out.println("Factor invalido. [0, 26]");
		}while(!Chat.validFactor(factor));
		
		return factor;
	}


	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		int factor;
		
		String name1;
		String name2;
		String command;
	
		name1 = getUsername(1, in);
		
		do{
			name2 = getUsername(2, in);
			if (name2.trim().equalsIgnoreCase(name1.trim()))
				System.out.println("Nome ja em utilizacao. "
				+ "Por favor introduza um nome diferente.");
		}while(name2.trim().equalsIgnoreCase(name1.trim()));
		
		factor = getFactor(in);
		
		Chat newChat = new Chat(name1, name2, factor);
		
		processHelp();
		
		do{
			command = processCommand(in);
			switch(command){
				case SHOW_CHAT: processShowChat(newChat); break;
				case PUBLISH_MSG: processPlainMsg(newChat, in); break;
				case PUBLISH_ENC: processEncryptedMsg(newChat, in); break;
				case CORRECT_MSG: processCorrectMsg(newChat, in); break;
				case CLOSE_CHAT: processCloseChat(newChat); break;
				case SHOW_LOG: processShowLog(newChat); break;
				case HELP: processHelp(); break;
				default: System.out.println("Opcao inexistente.");
			}
		}while(command != EXIT);
		
		System.out.println("Aplicacao terminada. Ate a proxima.");
	
		in.close();
	}
	
	
}
