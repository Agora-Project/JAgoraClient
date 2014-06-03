package org.agora.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.agora.graph.JAgoraThread;

/**
 *
 * @author greg
 */
public class ThreadListPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    public ArrayList<JAgoraThread> threads;
    public JAgoraClient client;
    
    public ThreadListPanel(JAgoraClient client) {
        super();
        this.client = client;
        threads = new ArrayList<>();
        addMouseListener(new PanelListener());
    }
    
    public ThreadListPanel(JAgoraClient client, ArrayList<JAgoraThread> threads) {
        super();
        this.client = client;
        this.threads = threads;
        addMouseListener(new PanelListener());
    }
    
    public void paintComponent(Graphics g) {
        int ypos = 0;
        for (JAgoraThread thread : threads) {
            g.setColor(Color.white);
            g.fillRect(0, ypos, 400, 40);
            g.setColor(Color.black);
            g.drawRect(0, ypos, 400, 40);
            g.drawString(thread.getTitle(), 2, ypos +15);
            g.drawString(thread.getDescription(), 2, ypos +35);
            ypos += 40;
        }
    }
    
    public class PanelListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getX() < 400 && e.getY() < threads.size() * 40) {
                int thread = e.getY()/40;
                client.changePanel(new GraphPanel(client, client.lib.getThreadByID(thread), thread));
            }
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