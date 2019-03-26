package mainApp;
import java.io.*;
import java.util.*;
import java.net.*;
import static java.lang.System.out;
public class  ChatServer {
  Vector<String> users = new Vector<String>();
  Vector<HandleClient> clients = new Vector<HandleClient>();
  public void process() throws Exception  {
      ServerSocket server = new ServerSocket(2000,10); // socket server that is used to connect 
      out.println("Server Started. Please invite your friend(s)");
      while( true) {
          Socket client = server.accept(); // accpt connections from clients
          HandleClient c = new HandleClient(client);
          clients.add(c); // adding the clients to the vector 
      } 
  }
  public static void main(String[] args) {
    // made with the help of http://www.srikanthtechnologies.com/blog
  } 
  public void broadcast(String user, String message)  {
      // send message to all connected users
      for ( HandleClient c : clients )
        if ( ! c.getUserName().equals(user) )
          c.sendMessage(user,message);
  }
  class  HandleClient extends Thread {
        String name = "";
        BufferedReader input; // used to read from user 
        PrintWriter output; // used to write to user 
        public HandleClient(Socket  client) throws Exception {
         // get input and output streams
         input = new BufferedReader(new InputStreamReader( client.getInputStream())) ;
         output = new PrintWriter (client.getOutputStream(),true);
         // read name
         name  = input.readLine();
         users.add(name); // add to vector
          start();
        }
        public void sendMessage(String uname,String  msg)  {
          output.println(uname + " : " + msg); // sending the username and the message 
        }
    
        public String getUserName() {  
            return name; 
        }
        public void run()  {
          String line;
            try    {
                while(true)   {
                     line = input.readLine();
                     if (line.equals("end") ) { // if a client write ends, he will be disconnnected from the user
                           clients.remove(this);
                           users.remove(name);
                           break;
                      }
            broadcast(name,line); // method  of outer class - send messages to all
         } // end of while
       }  // try
       catch(Exception ex) {
         System.out.println(ex.getMessage());
       }
      } 
   } 
} 