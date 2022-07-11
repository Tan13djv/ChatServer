import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client implements Runnable {
   Socket socket;
   Scanner in;
   PrintStream out;
   ChatServer server;

   public Client(Socket socket, ChatServer server){

       this.socket = socket;
       this.server = server;
       new Thread(this).start();

   }
   void receive(String message){
	   out.println(message);
   }
   
   public void run() {
       try {
    	   InputStream is = socket.getInputStream();
    	   OutputStream os = socket.getOutputStream();
    	   String name = null; 
    	   
           in = new Scanner(is);
           out = new PrintStream(os);

           out.println("Welcome to chat! Enter your name: ");
                  
           String input = in.nextLine();
           
           name = input;
           out.println("Hello "+name+" !");
           
           
           while (!input.equals("bye")) {
               server.sendAll(name+" said: "+input);
               input = in.nextLine();
           }
           socket.close();
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}