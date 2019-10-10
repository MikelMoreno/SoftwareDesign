package es.deusto.ingenieria.sd.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServer extends Remote {
	String sayHello() throws RemoteException;
	String sayMessage(String login, String password, String message) throws RemoteException, UserException; 
	void registerUser(String login, String password) throws	RemoteException, UserException; 

}