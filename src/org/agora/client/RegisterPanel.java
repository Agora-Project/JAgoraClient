package org.agora.client;

import static java.awt.Component.CENTER_ALIGNMENT;
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
public class RegisterPanel extends JPanel{
    public JAgoraClient client;
    protected JTextField username;
    protected JTextField email;
    protected JPasswordField password;
    protected JPasswordField passwordConfirm;
    protected JButton button;
    
    public RegisterPanel(JAgoraClient client) {
        this.client = client;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        username = new JTextField("Username");
        username.setMinimumSize(new Dimension(400, 20));
        username.setPreferredSize(new Dimension(400, 20));
        username.setMaximumSize(new Dimension(400, 20));
        username.setAlignmentX(CENTER_ALIGNMENT);
        email = new JTextField("Email");
        email.setMinimumSize(new Dimension(400, 20));
        email.setPreferredSize(new Dimension(400, 20));
        email.setMaximumSize(new Dimension(400, 20));
        email.setAlignmentX(CENTER_ALIGNMENT);
        password = new JPasswordField();
        password.setMinimumSize(new Dimension(400, 20));
        password.setPreferredSize(new Dimension(400, 20));
        password.setMaximumSize(new Dimension(400, 20));
        password.setAlignmentX(CENTER_ALIGNMENT);;
        passwordConfirm = new JPasswordField();
        passwordConfirm.setMinimumSize(new Dimension(400, 20));
        passwordConfirm.setPreferredSize(new Dimension(400, 20));
        passwordConfirm.setMaximumSize(new Dimension(400, 20));
        passwordConfirm.setAlignmentX(CENTER_ALIGNMENT);;
        add(username);
        add(email);
        add(password);
        add(passwordConfirm);
        button = new JButton("Login");
        button.addActionListener(new ButtonListener());
        button.setAlignmentX(CENTER_ALIGNMENT);
        add(button);
    }
    
    protected class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String pass = new String(password.getPassword());
            if (pass.equals(new String(passwordConfirm.getPassword())))
            client.lib.register(username.getText(), new String(password.getPassword()), email.getText());
            password.setText("");
            client.changePanel(new LoginPanel(client));
        }
    
    }
}
