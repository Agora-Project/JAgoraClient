package org.agora.client;

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
    public ArrayList<JAgoraThread> threads;
    
    public ThreadListPanel() {
        super();
        threads = new ArrayList<>();
        addMouseListener(new PanelListener());
    }
    
    public ThreadListPanel(ArrayList<JAgoraThread> threads) {
        super();
        this.threads = threads;
        addMouseListener(new PanelListener());
    }
    
    public void paintComponent(Graphics g) {
        int ypos = 0;
        for (JAgoraThread thread : threads) {
            g.drawRect(0, ypos, 400, 40);
            g.drawString(thread.getTitle(), 2, ypos +15);
            g.drawString(thread.getDescription(), 2, ypos +35);
            ypos += 40;
        }
    }
    
    public class PanelListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            
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