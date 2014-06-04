package org.agora.client;

import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.BoxLayout;
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
    }
    
    public ThreadListPanel(JAgoraClient client, ArrayList<JAgoraThread> threads) {
        super();
        this.client = client;
        this.threads = threads;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (JAgoraThread thread : threads) {
            add(new ThreadPanel(thread));
        }
        
    }
    
//    public void paintComponent(Graphics g) {
//        int ypos = 0;
//        for (JAgoraThread thread : threads) {
//            g.setColor(Color.white);
//            g.fillRect(0, ypos, 400, 40);
//            g.setColor(Color.black);
//            g.drawRect(0, ypos, 400, 40);
//            g.drawString(thread.getTitle(), 2, ypos +15);
//            g.drawString(thread.getDescription(), 2, ypos +35);
//            ypos += 40;
//        }
//    }
    
    public class ThreadPanel extends JPanel {
        protected JAgoraThread thread;
        
        public ThreadPanel(JAgoraThread thread) {
            this.thread = thread;
            setMinimumSize(new Dimension(800, 40));
            setPreferredSize(new Dimension(1600, 40));
            setMaximumSize(new Dimension(1600, 40));
            setAlignmentX(CENTER_ALIGNMENT);
            addMouseListener(new PanelListener());
            
        }
        
        public void paintComponent(Graphics g) {
            g.setColor(Color.white);
            g.fillRect(0, 0, 400, 39);
            g.setColor(Color.black);
            g.drawRect(0, 0, 400, 39);
            g.drawString(thread.getTitle(), 2, 15);
            g.drawString(thread.getDescription(), 2, 35);
        }
        
        public class PanelListener implements MouseListener{

            @Override
            public void mouseClicked(MouseEvent e) {
                client.changePanel(new GraphPanel(client, client.lib.getThreadByID(thread.getId()), thread.getId()));
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