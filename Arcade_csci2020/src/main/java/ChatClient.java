package mainApp;
import java.io.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.System.out;
public class  ChatClient extends JFrame implements ActionListener {
    private String userName;
    private PrintWriter sendClient;
    private BufferedReader readIn;
    private JTextArea  messages;
    private JTextField input;
    private JButton btnSend,btnExit;
    private Socket client;

    
    public ChatClient(String userName,String servername) throws Exception {
        super(userName);  // set title for frame
        this.userName = userName;
        client  = new Socket(servername,2000);
        readIn = new BufferedReader( new InputStreamReader( client.getInputStream())) ; // used to write
        sendClient = new PrintWriter(client.getOutputStream(),true); // used to read in from server
        sendClient.println(userName);  // send name to server
        GUIInterface();
        new MessagesThread().start();  // create thread to listen for messages
    }
    
    public void GUIInterface() {
        btnSend = new JButton("Send");
        btnExit = new JButton("Exit");
        messages = new JTextArea();
        messages.setRows(10);
        messages.setColumns(50); 
        messages.setEditable(false);
        input  = new JTextField(25);
        JScrollPane sp = new JScrollPane(messages, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(sp,"Center");
        JPanel pane = new JPanel( new FlowLayout());
        pane.add(input);
        pane.add(btnSend);
        pane.add(btnExit);
        add(pane,"South");
        btnSend.addActionListener(this); // handler for sending the message
        btnExit.addActionListener(this);
        setSize(500,300);
        setVisible(true);
        pack();
    }
    
    public void actionPerformed(ActionEvent evt) {
        if ( evt.getSource() == btnExit ) {
            sendClient.println("end");  // send end to server so that server knows about the termination
            System.exit(0);
        } else {
            // send message to server
            sendClient.println(input.getText());
        }
    }
    
    public static void main(String[] args) {
        // made with the help of http://www.srikanthtechnologies.com/blog
    } 
    class  MessagesThread extends Thread {
        private Scene3 content = new Scene3();
        public void run() {
            String line;
            try {
                while(true) {
                    line = readIn.readLine();
                    if(line.equals("level") || line.equals("Level")){ // checks for current level state 
                        messages.append(content.levelsUnlocked + "\n");
                    }else{
                        messages.append(line + "\n");
                    }
                } // end of while
            } catch(Exception ex) {}
        }
    }
} 