package org.agora.client;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author greg
 */
public class LoginPanel extends JPanel {
private static final long serialVersionUID = 1L;
    
    public JAgoraClient client;
    protected JTextField username;
    protected JPasswordField password;
    protected JButton button;
    
    public LoginPanel(JAgoraClient client) {
        this.client = client;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        username = new JTextField();
        username.setMinimumSize(new Dimension(400, 20));
        username.setPreferredSize(new Dimension(400, 20));
        username.setMaximumSize(new Dimension(400, 20));
        username.setAlignmentX(CENTER_ALIGNMENT);
        password = new JPasswordField();
        password.setMinimumSize(new Dimension(400, 20));
        password.setPreferredSize(new Dimension(400, 20));
        password.setMaximumSize(new Dimension(400, 20));
        password.setAlignmentX(CENTER_ALIGNMENT);;
        add(username);
        add(password);
        button = new JButton("Login");
        button.addActionListener(new ButtonListener());
        button.setAlignmentX(CENTER_ALIGNMENT);
        add(button);
    }
    
    protected class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            client.lib.login(username.getText(), new String(password.getPassword()));
            password.setText("");
            client.getThreadList();
        }
    
    }
}
