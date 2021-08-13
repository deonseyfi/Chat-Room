
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
    public class Client {
        final static int Port = 5000;
        static JButton btnSubmit;

        public static void main(String args[]) throws UnknownHostException, IOException {

  
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
				
                    try {
                        createAndShowGUI();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });


        }


        private static void createAndShowGUI() throws IOException {
        
			
			ChatRoom chatRoom = new ChatRoom();          

			InetAddress ip = InetAddress.getByName("localhost"); 
			Socket socket = new Socket(ip, Port); 
          
        
			DataInputStream dataIn = new DataInputStream(socket.getInputStream()); 
			DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream()); 
  

        Thread sendMessage = new Thread(new Runnable()  
        { 
            @Override
            public void run() { 
                while (true) { 

                    try { 
                        // write on the output stream 
						System.out.println(chatRoom.passedString());
						if (chatRoom.passedString())
						{      
							System.out.println("Passed");
							dataOut.writeUTF(chatRoom.getString()); 
							dataOut.writeUTF(chatRoom.getString()); 
							chatRoom.setBool(false);
						}
                    } catch (IOException e) { 
                        e.printStackTrace(); 
                    } 
				
                
				}
            } 
        }); 
          
     
        Thread readMessage = new Thread(new Runnable()  
        { 
            @Override
            public void run() { 
  
                while (true) { 
					chatRoom.windowRepaint();
					
                    try { 

                        String message = dataIn.readUTF();
						System.out.println(message);
                        chatRoom.addString(dataIn.readUTF());
						chatRoom.windowRepaint();

                    } catch (IOException e) { 
  
                        e.printStackTrace(); 
                    } 
					
                } 
            } 
        }); 
  
        sendMessage.start(); 
        readMessage.start(); 
		JFrame f = new JFrame("Chat Room");
            

         f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         f.setSize(600,400);
         f.setVisible(true);


         f.add(chatRoom);
	
			f.addWindowListener(new WindowAdapter() {
					public void windowClosed(WindowEvent e) {
						 try { 

                       	dataOut.writeUTF("logout");
					dataOut.writeUTF("logout");

                    } catch (IOException ex) { 
  
                        ex.printStackTrace(); 
                    } 
				
				}

					public void windowClosing(WindowEvent e) {
        
				}
			});
			
        }

    }