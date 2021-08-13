import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ChatRoom extends JPanel {

   public JButton btnSubmit;
    String messages[] = {"","","","","","","","","",""};
    String messagePassed;
    public Boolean bool = false;
	String username;
    ChatRoom()
    {
		username = JOptionPane.showInputDialog("Enter your username");
		
		
		
		
        setSize(600,400);
        setLayout(null);

			

        JTextArea jt = new JTextArea(1,10);
        jt.setBounds(10,300,390,40);
        add(jt);
        btnSubmit = new JButton("Submit");
        btnSubmit.setBounds(450,300,100,40);

        btnSubmit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
				String sendMessage = username +" : "+jt.getText();
				setString(sendMessage);
                jt.setText("");
				repaint();
				bool = true;	
            }
        });

        add(btnSubmit);
    }
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        Font font = new Font("Times New Roman",Font.PLAIN,16);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);


        g.setColor(Color.WHITE);
        g.fillRect(10, 30, 560, 260);
        g2.setColor(Color.BLACK);
        g2.drawString(messages[9], 15, 44);
        g2.drawString(messages[8], 15, 68);
        g2.drawString(messages[7], 15, 92);
        g2.drawString(messages[6], 15, 116);
        g2.drawString(messages[5], 15, 140);
        g2.drawString(messages[4], 15, 164);
        g2.drawString(messages[3], 15, 188);
        g2.drawString(messages[2], 15, 212);
        g2.drawString(messages[1], 15, 236);
        g2.drawString(messages[0], 15, 260);
		g2.setColor(Color.RED);
		g2.drawString("Username: "+username,270,15);


    }
    public void addString(String input)
    {
            for (int i = 9;i >= 1;i-- )
            {
                messages[i] = messages[i-1];
            }
            messages[0] = input;

    }
    private void setString(String input)
    { messagePassed = input; }
    public String getString()
    {return messagePassed;}


    public boolean passedString()
    {return bool;}
	public void windowRepaint()
	{repaint();}
	public void setBool(Boolean value)
	{bool= value;}
		

}
