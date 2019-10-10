package es.deusto.ingenieria.sd.rmi.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class Server extends UnicastRemoteObject implements IServer {

	private static final long serialVersionUID = 1L;
	private int cont = 0;
	private HashMap<String, String> clientPassword = new HashMap<String, String>();
	protected Server() throws RemoteException {
		super();
	}

	public String sayHello() {
		cont++;
		System.out.println(" * Client number: " + cont);
		return "Hello World!";
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}

		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];

		try {		
			IServer objServer = new Server();
			Naming.rebind(name, objServer);
			System.out.println("* Server '" + name + "' active and waiting...");
			
		}catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}

	//here i start editing
	@Override
	public String sayMessage(String login, String password, String message) throws RemoteException, UserException {
		
		if (this.clientPassword.containsKey(login) && this.clientPassword.get(login).equals(password)) {//if the username and password are correct, then returns the message
			return message;
		}else {//if the password is not correct or the username is incorrect
			throw new UserException("the password is incorrect or the username does not exist");
		}
	
	}

	@Override
	public void registerUser(String login, String password) throws RemoteException, UserException {
		
		if (!clientPassword.containsKey(login)) {//if the username does not exist in the hashmap, it is included
			clientPassword.put(login, password);
			System.out.println("registration completed Username: " + login + " Password: " + password );
		}else {//if it already exists prints a message
			throw new UserException("the username "+ login +" is not available ");	
		}
		
	}
	//here i stop editing
	
}