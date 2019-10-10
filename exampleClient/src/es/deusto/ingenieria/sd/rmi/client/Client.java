package es.deusto.ingenieria.sd.rmi.client;

import java.util.Scanner;

import es.deusto.ingenieria.sd.rmi.server.IServer;

public class Client {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("uso: java [policy] [codebase] cliente.Cliente [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
			IServer stubServer = (IServer) java.rmi.Naming.lookup(name);
			
		//here i start editing
			//variables
			String nombre, pass, message;
			int selection;
			Scanner scan = new Scanner(System.in);
			
			System.out.println("* Message coming from the server: '" + stubServer.sayHello() + "'");
			
			do {
				//starting menu
				System.out.println("Please select your option:");
				System.out.println("1--> register");
				System.out.println("2--> send message");
				System.out.println("3--> exit");

				selection = scan.nextInt();
				if (selection == 1) {//register user
					System.out.println("Please enter your username");
					nombre = scan.next();
					System.out.println("Please enter your password");
					pass= scan.next();
					System.out.println("registering user please wait...");
					stubServer.registerUser(nombre,pass);
				}else if(selection ==2){//send message
					System.out.println("Please enter your username");
					nombre = scan.next();
					System.out.println("Please enter your password");
					pass= scan.next();
					System.out.println("please, write a message");
					message = scan.next();
					System.out.println("* Message coming from the server: '" + stubServer.sayMessage( nombre, pass, message) + "'");
						
				}
				
			} while (selection != 3);
		//stop editing
		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
	}
}