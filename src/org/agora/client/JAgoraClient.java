package org.agora.client;


import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.agora.lib.*;
import org.agora.graph.JAgoraThread;

/**
 *
 * @author greg
 */
public class JAgoraClient extends JFrame {
    private static final long serialVersionUID = 1L;
    
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
        mi = new MenuItem("Add Argument");
        mi.addActionListener(listener);
	m.add(mi);
        mi = new MenuItem("Edit Argument");
        mi.addActionListener(listener);
	m.add(mi);
	mbar.add(m);
        this.setMenuBar(mbar);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        changePanel(new LoginPanel((JAgoraClient)frame));
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
    
    public void emptyPanel() {
        remove(this.panel);
        panel = null;
    }
    
    public void getThreadList() {
        ArrayList<JAgoraThread> threads = lib.getThreadList();
        changePanel(new ThreadListPanel((JAgoraClient) frame, threads));
        repaint();
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
                    changePanel(new HostPanel((JAgoraClient)frame));
                    break;
                case "Register": 
                    changePanel(new RegisterPanel((JAgoraClient)frame));
                    break;
                case "Login":
                    changePanel(new LoginPanel((JAgoraClient)frame));
                    break;
                case "Logout":
                    lib.logout();
                    break;
                case "Quit":
                    lib.logout();
                    System.exit(0);
                    break;
                case "Get Thread List":
                    getThreadList();
                    break;
                case "Add Argument":
                    if (panel == null)
                        break;
                    else {
                        JPanel newPanel = new JPanel();
                        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
                        panel.setMinimumSize(new Dimension(1600, 400));
                        panel.setPreferredSize(new Dimension(1600, 400));
                        panel.setMaximumSize(new Dimension(1600, 400));
                        panel.setAlignmentX(CENTER_ALIGNMENT);
                        GraphPanel gp = (GraphPanel) panel;
                        newPanel.add(panel);
                        NewPostPanel np = new NewPostPanel((JAgoraClient) frame, gp);
                        newPanel.add(np);
                        changePanel(newPanel);
                        gp.newPostPanel = np;
                    }
                    break;
                case "Edit Argument": 
                    if (panel instanceof GraphPanel) {
                        JPanel newPanel = new JPanel();
                        newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
                        panel.setMinimumSize(new Dimension(1600, 400));
                        panel.setPreferredSize(new Dimension(1600, 400));
                        panel.setMaximumSize(new Dimension(1600, 400));
                        panel.setAlignmentX(CENTER_ALIGNMENT);
                        GraphPanel gp = (GraphPanel) panel;
                        newPanel.add(panel);
                        EditPostPanel np = new EditPostPanel((JAgoraClient) frame, gp.centerPost.node.getID());
                        newPanel.add(np);
                        changePanel(newPanel);
                    }
                    break;
            }
        }
        
    }

}
