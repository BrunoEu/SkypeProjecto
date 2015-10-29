import java.util.Scanner;
//inserting random comment : Eduardo
public class Chat {
	
	public int Factor;
	
	public Chat(Scanner in){
		startChat(in);
	}
	
	public void startChat(Scanner in){
		User user1 = new User(in);
		User user2 = new User(in);
		while (user1.NAME == user2.NAME){
			System.out.println("Nome já em utilização. "
			+ "Por favor introduza um nome diferente.");
			user2.insertName(in);
		}
	}

	public void getChatFactor(Scanner in){
		boolean erro = false;
		
		do {
			if (erro == true)
				System.out.println("Factor introduzido incorrecto ([0;26]).");
			System.out.print("Factor de Encriptação > ");
			Factor = in.nextInt();
			in.nextLine();
			erro = true;
		}while(Factor > 26 || Factor < 0)
	}
	
}

