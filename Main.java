
import java.util.Scanner;

public class Main {

	private static final String EXIT = "S";
	private static final String HELP = "A";
	private static final String SHOW_LOG = "MCA";
	private static final String CLOSE_CHAT = "ECP";
	private static final String CORRECT_MSG = "CMA";
	private static final String PUBLISH_ENC = "PME";
	private static final String PUBLISH_MSG = "PNM";
	private static final String SHOW_CHAT = "VCP";

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		String name1;//string que ira conter o nome do utilisador 1
		String name2;//string que ira conter o nome do utilixador 2
		String cmd;//string que ira conter o comando introduzido pelo utilizador
		
		System.out.print("Nome do Utilizador 1: ");
		name1 = in.nextLine();
		
		name2 = getUsername(in, name1);
		
		int factor = getFactor(in);
		
		Chat novaConversa = new Chat(name1, name2, factor);
		System.out.println("\nChat criado com sucesso.");
		
		//Menu principal que interpreta comandos do utilizador
		do{
			cmd = processCmd(in);
			switch(cmd){
				case SHOW_CHAT: processShowChat(novaConversa); break;
				case PUBLISH_MSG: processPubMsg(novaConversa, in); break;
				case PUBLISH_ENC: processPubEnc(novaConversa, in); break;
				case CORRECT_MSG: processCorMsg(novaConversa, in); break;
				case CLOSE_CHAT: processCloseChat(novaConversa); break;
				//case SHOW_LOG: processShowLog(); break;
				case HELP: processHelp(); break;
				case EXIT: System.out.println("Aplicacao terminada. Ate a proxima.");break;
				default: System.out.println("Opcao inexistente.");
			}
		}while(cmd != EXIT);
		
	}
	
	private static String processCmd(Scanner in){
		System.out.print("> ");
		//ler o que foi introduzido passar a letra maiuscula e tirar espacos no inicio e fim da string
		return in.nextLine().toUpperCase().trim();
	}
	
	private static void processShowChat(Chat conversa){
		if (conversa.showChat().isEmpty())
			System.out.println("Conversa vazia.");
		else
			System.out.println(conversa.showChat());
	}
	
	private static void processPubMsg(Chat conversa, Scanner in){
		int user = getUser(in);
		conversa.addMsg(user , getMsg(in));
		System.out.println("USER[" + user + "]MSG[" + conversa.getMsgNumber()+"]: Publicada");
	}
	
	
	private static void processPubEnc(Chat conversa, Scanner in){
		conversa.addEncMsg(getUser(in), getMsg(in));
	}
	
	private static void processCorMsg(Chat conversa, Scanner in){
		int user = getUser(in);
		if (conversa.canEditLastMessage(user)){
			conversa.editLastMessage(user, getMsg(in));
		}
		else{
			System.out.println("Utilizador " + user + " nao pode editar.");
		}
			
	}
	
	private static void processCloseChat(Chat conversa){
		conversa.closeConversation();
		System.out.println("Conversa terminada.");
	}
	
	private static String getMsg(Scanner in){
		System.out.print("Mensagem: ");
		String msg = in.nextLine();
		return msg;
	}
	
	private static int getUser(Scanner in){
		int user;
		do{
			System.out.print("Utilizador: ");
			user = in.nextInt();
			in.nextLine();
			if (!Chat.validUser(user))
				System.out.println("Utilizador introduzido invalido.");
		}while(!Chat.validUser(user));
		return user;
	}
	
	private static void processHelp() {
		System.out.println("VCP - Ver a conversa em progresso\n"+
						   "PNM - Publicar nova mensagem\n"+
						   "PME - Publicar mensagem encriptada\n"+
						   "CMA - Corrigir mensagem anterior\n"+
						   "ECP - Encerrar converssa em progresso\n"+
						   "A - Ajuda\n"+
						   "S - Sair");
	}
	
	private static String getUsername(Scanner in, String name1){
		String name2;
		do{	
			System.out.print("Nome do Utilizador 2: ");
			name2 = in.nextLine();
			if(name1.equalsIgnoreCase(name2)){
				System.out.println("Nome já em utilização. "
				+ "Por favor introduza um nome diferente.");
			}
		}while (name1.equalsIgnoreCase(name2));
		return name2;
	}
	
	private static int getFactor(Scanner in){
		int factor;
		
		do{
			System.out.print("Insira um factor de translaçao: ");
			factor = in.nextInt();
			in.nextLine();
			if (factor <0 || factor > 25)
				System.out.println("Factor invalido. [0, 25]");
		}while(factor < 0 || factor > 25);
		return factor;
	}
	
}
