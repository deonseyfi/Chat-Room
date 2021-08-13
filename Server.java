

import java.io.*; 
import java.util.*; 
import java.net.*; 
  

public class Server  
{ 
  
 
    static List<ClientHandler> arr = new ArrayList<ClientHandler>();
      
 
    static int i = 0; 
  
    public static void main(String[] args) throws IOException  
    { 

        ServerSocket serverSocket = new ServerSocket(5000); 
          
        Socket socket; 
          

        while (true)  
        { 
 
            socket = serverSocket.accept(); 
            DataInputStream dataInput = new DataInputStream(socket.getInputStream()); 
            DataOutputStream dataOutput = new DataOutputStream(socket.getOutputStream()); 
                

            ClientHandler mtch = new ClientHandler(socket,"client " + i, dataInput, dataOutput);  

            Thread t = new Thread(mtch); 
              
            System.out.println("Client Added"); 
  

            arr.add(mtch); 
  
  
            t.start(); 
  

            i++; 
  
        } 
    } 
} 
  
// ClientHandler class 
class ClientHandler implements Runnable  
{ 

    private String name; 
    final DataInputStream dataInput; 
    final DataOutputStream dataOutput; 
    Socket socket; 
    boolean isloggedin; 
      
   // constructor 
    public ClientHandler(Socket socket, String name, 
                            DataInputStream dataInput, DataOutputStream dataOutput) { 
        this.dataInput = dataInput; 
        this.dataOutput = dataOutput; 
        this.name = name; 
        this.socket = socket; 
        this.isloggedin=true; 
    } 
  
    @Override
    public void run() { 
  
        String received; 
        while (true)  
        { 
            try
            { 
                // receive the string 
                received = dataInput.readUTF(); 
                  
                System.out.println(received); 
                  
                if(received.equals("logout")){ 
                    this.isloggedin=false; 
					System.out.println("Client Logged Out");
                    this.socket.close(); 
                    break; 
                } 
                  

  

				System.out.println("List size: "+Server.arr.size());
                for (int i = 0; i < Server.arr.size();i++)  
                { 
                    
                    if (Server.arr.get(i).isloggedin==true)  
                    { 
						System.out.println(Server.arr.get(i).name);
                        Server.arr.get(i).dataOutput.writeUTF(received); 
                  
                    } 
                } 
			
            } catch (IOException e) { 
                  
                e.printStackTrace(); 
            } 
              
        } 
        try
        { 
  
            this.dataInput.close(); 
            this.dataOutput.close(); 
              
        }catch(IOException e){ 
            e.printStackTrace(); 
        } 
    } 
}