package org.agora.client;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import org.agora.graph.JAgoraNode;

/**
 *
 * @author greg
 */
public class NewPostPanel extends JPanel{
    protected JTextField titleField;
    protected JTextPane textField;
    protected JAgoraClient client;
    protected ArrayList<PostReference> posts;
    protected JPanel postPanel;
    
    public NewPostPanel(JAgoraClient client) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.client = client;
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
    }
    
    public class PostReference extends JPanel {
        protected JAgoraNode node;
        
        public PostReference(JAgoraNode node) {
            this.node = node;
        }
    }
}
