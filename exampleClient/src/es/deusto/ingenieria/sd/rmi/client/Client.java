package es.deusto.ingenieria.sd.rmi.client;

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
			System.out.println("* Message coming from the server: '" + stubServer.sayHello() + "'");
			System.out.println("registering user");
			stubServer.registerUser( "mikel",  "moreno" );
			System.out.println("* Message coming from the server: '" + stubServer.sayMessage( "mikel", "moreno", "buenos dias mundo") + "'");
			
		} catch (Exception e) {
			System.err.println("- Exception running the client: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
