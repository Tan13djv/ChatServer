import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.OutputStream;

public class ChatServer {
	ArrayList<Client> clients = new ArrayList<>();
	ServerSocket server;
	ChatServer() throws IOException{
		server = new ServerSocket(1234);
	}
	void sendAll(String message){
		for (Client client: clients){
			client.receive(message);
		}
	}
	public void run(){
		while(true) {
		       System.out.println("Waiting...");
		       Socket socket;
			try {
		       // ждем клиента из сети
				socket = server.accept();
		       System.out.println("Client connected!");
		       // создаем клиента на своей стороне
		       clients.add(new Client(socket, this));
			} catch (IOException e) {
				e.printStackTrace();
			}
	   }
	}
	
	public static void main(String[] args) throws IOException {
		
		 new ChatServer().run();  
		}
	}
