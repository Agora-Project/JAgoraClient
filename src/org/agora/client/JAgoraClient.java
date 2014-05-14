package org.agora.client;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.agora.lib.*;
import org.agora.graph.JAgoraGraph;
import org.agora.graph.JAgoraNode;
import org.agora.graph.JAgoraThread;

/**
 *
 * @author greg
 */
public class JAgoraClient extends JFrame {
    
    public JAgoraLib lib;
    protected JPanel panel;
    protected JFrame frame;
    
    public JAgoraClient(String title, Color bgColor) {
        super(title);
        lib = new JAgoraLib("127.0.0.1", org.agora.lib.Options.AGORA_PORT);
//        JAgoraNode testNode = new JAgoraNode(new JAgoraNodeID("bigornas.bounceme.net", 1));
//        graph.addNode(testNode);
        frame = this;
        panel = null;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 800);
        MenuListener listener = new MenuListener();
        MenuBar mbar = new MenuBar();
        Menu m = new Menu("Connect");
        MenuItem mi = new MenuItem("Set Host");
        mi.addActionListener(listener);
	m.add(mi);
        mi = new MenuItem("Register");
        mi.addActionListener(listener);
	m.add(mi);
        mi = new MenuItem("Login");
        mi.addActionListener(listener);
	m.add(mi);
        mi = new MenuItem("Logout");
        mi.addActionListener(listener);
	m.add(mi);
	m.addSeparator();
        mi = new MenuItem("Quit");
        mi.addActionListener(listener);
	m.add(mi);
	mbar.add(m);
		
	m = new Menu("Agora");
        mi = new MenuItem("Get Thread List");
        mi.addActionListener(listener);
        m.add(mi);
        mi = new MenuItem("Get Thread");
        mi.addActionListener(listener);
	m.add(mi);
        mi = new MenuItem("Add Argument");
        mi.addActionListener(listener);
	m.add(mi);
	mbar.add(m);
        this.setMenuBar(mbar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
    }
    
    public void changePanel(JPanel panel) {
        if (this.panel != null)
            remove(this.panel);
        this.panel = panel;
        add(panel);
        pack();
        setSize(1600, 800);
        setVisible(true);
    }
    
    public static void main(String[] args) {
        new JAgoraClient("Agora", Color.black);
    }
    
    public class MenuListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String string;
            String tokens[];
            switch (((MenuItem) e.getSource()).getLabel())
            {
                case "Set Host": 
                    lib = new JAgoraLib(
                        (String) JOptionPane.showInputDialog(frame,
                            "Enter Host Address",
                            "Set Host",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            null,
                            "127.0.0.1"), org.agora.lib.Options.AGORA_PORT);
                    break;
                case "Register": 
                    string = (String) JOptionPane.showInputDialog(frame, 
                        "Enter username, password, and Email",
                        "Register",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null);
                    if (string != null) {
                        tokens = string.split(" ");
                        if (tokens.length == 3) 
                            lib.register(tokens[0], tokens[1], tokens[2]);
                    }
                    break;
                case "Login":
                    string = (String) JOptionPane.showInputDialog(frame, 
                        "Enter username and password.",
                        "Login",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null);
                    if (string != null) {
                        tokens = string.split(" ");
                        if (tokens.length == 2) 
                            lib.login(tokens[0], tokens[1]);
                    }
                    break;
                case "Logout":
                    lib.logout();
                    break;
                case "Quit":
                    lib.logout();
                    System.exit(0);
                    break;
                case "Get Thread":
                    string = (String) JOptionPane.showInputDialog(frame, 
                        "Enter thread ID",
                        "Get Thread",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        null,
                        null);
                    break;
                case "Get Thread List":
                    ArrayList<JAgoraThread> threads = lib.getThreadList();
                    changePanel(new ThreadListPanel((JAgoraClient) frame, threads));
                    break;
            }
        }
        
    }

}
