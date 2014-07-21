package org.agora.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import org.agora.graph.JAgoraArgument;
import org.agora.graph.JAgoraArgumentID;
import org.bson.BasicBSONObject;

/**
 *
 * @author greg
 */
public class NewPostPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    protected JTextField titleField;
    protected JTextPane textField;
    protected JAgoraClient client;
    protected ArrayList<PostReference> posts;
    protected JPanel postPanel;
    protected JButton button;
    protected GraphPanel graphPanel;
    
    public NewPostPanel(JAgoraClient client, GraphPanel panel) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.client = client;
        graphPanel = panel;
        textField = new JTextPane();
        titleField = new JTextField();
        posts = new ArrayList<>();
        postPanel = new JPanel();
        postPanel.setLayout(new BoxLayout (postPanel, BoxLayout.X_AXIS));
        postPanel.setMinimumSize(new Dimension(800, 20));
        postPanel.setPreferredSize(new Dimension(1600, 20));
        postPanel.setMaximumSize(new Dimension(1600, 20));
        postPanel.setAlignmentX(CENTER_ALIGNMENT);
        add(postPanel);
        //titleField.setBounds(0, 0, client.getWidth(), 20);
        titleField.setAlignmentX(CENTER_ALIGNMENT);
        titleField.setMinimumSize(new Dimension(800, 20));
        titleField.setPreferredSize(new Dimension(1600, 20));
        titleField.setMaximumSize(new Dimension(1600, 20));
        add(titleField);
        //textField.setBounds(0, 21, client.getWidth(), client.getHeight() -21);
        textField.setAlignmentX(CENTER_ALIGNMENT);
        add(textField);
        button = new JButton("Post");
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(new ButtonListener());
        add(button);
    }
    
    public void addPost(JAgoraArgument node) {
        for (PostReference p :posts) {
            if (p.getNode() == node)
                return;
        }
        PostReference pr = new PostReference(node);
        posts.add(pr);
        postPanel.add(pr);
        repaint();
    }
    
    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BasicBSONObject content = new BasicBSONObject();
            content.put("Title", titleField.getText());
            content.put("Text", textField.getText());
            ArrayList<JAgoraArgumentID> targets = new ArrayList<>();
            for (PostReference p :posts) {
                targets.add(p.node.getID());
            }
            client.lib.addArgumentWithAttacks(content, graphPanel.thread_id, targets);
            textField.setText("");
            titleField.setText("");
            posts.clear();
        }
        
    }
    
    public class PostReference extends JPanel {
        private static final long serialVersionUID = 1L;
        private JAgoraArgument node;
        private PostReference pr;
        
        public PostReference(JAgoraArgument node) {
            this.node = node;
            pr = this;
            this.setMinimumSize(new Dimension(75, 20));
            this.setPreferredSize(new Dimension(75, 20));
            this.setMaximumSize(new Dimension(75, 20));
            this.setAlignmentY(CENTER_ALIGNMENT);
            addMouseListener(new PanelListener());
        }
        
        public void paintComponent(Graphics g) {
            g.setColor(Color.red);
            g.drawRect(0, 0, 74, 19);
        }

        /**
         * @return the node
         */
        public JAgoraArgument getNode() {
            return node;
        }
        
        public class PanelListener implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                posts.remove(pr);
                postPanel.remove(pr);
                pr.repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
            
        }
        
        
    }
}
