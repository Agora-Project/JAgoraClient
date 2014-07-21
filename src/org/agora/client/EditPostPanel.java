package org.agora.client;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import org.agora.graph.JAgoraArgumentID;
import org.bson.BasicBSONObject;

/**
 *
 * @author greg
 */
public class EditPostPanel extends JPanel {
    protected JTextField titleField;
    protected JTextPane textField;
    protected JAgoraClient client;
    protected JButton button;
    protected JAgoraArgumentID post;
    
    public EditPostPanel(JAgoraClient client, JAgoraArgumentID post) {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.client = client;
        this.post = post;
        textField = new JTextPane();
        titleField = new JTextField();
        titleField.setAlignmentX(CENTER_ALIGNMENT);
        titleField.setMinimumSize(new Dimension(800, 20));
        titleField.setPreferredSize(new Dimension(1600, 20));
        titleField.setMaximumSize(new Dimension(1600, 20));
        add(titleField);
        textField.setAlignmentX(CENTER_ALIGNMENT);
        add(textField);
        button = new JButton("Save Edit");
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(new ButtonListener());
        add(button);
    }
    
    public class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            BasicBSONObject content = new BasicBSONObject();
            content.put("Title", titleField.getText());
            content.put("Text", textField.getText());
            
            client.lib.editArgument(content, post);
            textField.setText("");
            titleField.setText("");
        }
        
    }
}
