package org.agora.client;

import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.agora.lib.JAgoraLib;

/**
 *
 * @author greg
 */
public class HostPanel extends JPanel{
    
    protected JTextField hostField;
    protected JAgoraClient client;
    protected JButton button;
    
    public HostPanel(JAgoraClient client) {
        this.client = client;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        hostField = new JTextField("Enter desired Host Address.");
        hostField.setMinimumSize(new Dimension(400, 20));
        hostField.setPreferredSize(new Dimension(400, 20));
        hostField.setMaximumSize(new Dimension(400, 20));
        hostField.setAlignmentX(CENTER_ALIGNMENT);
        button = new JButton("Login");
        button.addActionListener(new ButtonListener());
        button.setAlignmentX(CENTER_ALIGNMENT);
        add(hostField);
        add(button);
    }
    
    protected class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            
            client.lib = new JAgoraLib(hostField.getText(), org.agora.lib.Options.AGORA_PORT);
            client.changePanel(new LoginPanel(client));
        }
    
    }
}
