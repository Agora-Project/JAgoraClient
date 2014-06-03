package org.agora.client;

import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.agora.graph.JAgoraEdge;
import org.agora.graph.JAgoraGraph;
import org.agora.graph.JAgoraNode;

/**
 *
 * @author greg
 */
public class GraphPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    
    public ArrayList<Post> posts;
    public ArrayList<Connection> connections;
    public JAgoraGraph graph;
    public JAgoraClient client;
    public NewPostPanel newPostPanel;
    public Post centerPost;
    public int thread_id;
    
    public GraphPanel(JAgoraClient client, int thread) {
        this(client, new JAgoraGraph(), thread);
    }
    
    public GraphPanel(JAgoraClient client, JAgoraGraph graph, int thread) {
        super();
        this.client = client;
        this.graph = graph;
        posts = new ArrayList<>();
        connections = new ArrayList<>();
        updateArguments();
        addMouseListener(new PanelListener());
        newPostPanel = null;
        thread_id = thread;
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Connection c : connections) {
            c.draw(g);
        }
        for (Post a : posts) {
            a.draw(g);
        }
    }
    
    public void updateArguments() {
        posts.clear();
        connections.clear();
        for (JAgoraNode node : graph.getNodes())
        {
            posts.add(new Post(node, new Point(0, 0)));
        }
        for (JAgoraEdge edge : graph.edgeMap.values()) {
            Post origin = getPost(edge.getOrigin());
            Post target = getPost(edge.getTarget());
            connections.add(new Connection(origin, target, edge));
        }
        if (posts.size() > 0) centerViewpoint(posts.get(0));
        
    }
    
    public Post getPost(JAgoraNode node) {
        for (Post a : posts) {
                if (a.getNode() == node)
                    return a;
            }
        return null;
    }
    
    public void centerViewpoint(Post a) {
        for (Post p : posts) {
            p.setPosition(new Point(50,50));
            p.setVisible(false);
        }
        for (Connection c : connections) {
            if (c.origin == a || c.target == a)
                c.setVisible(true);
            else c.setVisible(false);
        }
        centerPost = a;
        a.setPosition(new Point(getWidth()/2, getHeight()/2));
        a.setVisible(true);
        ArrayList<JAgoraEdge> list = a.node.getOutgoingEdgeList();
        int xoff = -110*(list.size()-1);
        for (JAgoraEdge edge : list) {
            Post p = getPost(edge.getTarget());
            p.setPosition(new Point(getWidth()/2 + xoff, getHeight()/2 -110));
            p.setVisible(true);
            xoff += 220;
        }
        list = a.node.getIncomingEdgeList();
        xoff = -110*(list.size()-1);
        for (JAgoraEdge edge : list) {
            Post p = getPost(edge.getOrigin());
            p.setPosition(new Point(getWidth()/2 + xoff, getHeight()/2 +110));
            p.setVisible(true);
            xoff += 220;
        }
        repaint();
    }
    
    public class PanelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                for (Post p : posts) {
                    if (p.containsPoint(e.getPoint())) { 
                        centerViewpoint(p);
                        return;
                    }
                }
            } else if (newPostPanel != null) {
                for (Post p : posts) {
                    if (p.containsPoint(e.getPoint())) 
                        newPostPanel.addPost(p.getNode());
                }
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
