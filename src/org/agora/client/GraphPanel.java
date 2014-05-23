package org.agora.client;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import org.agora.graph.JAgoraEdge;
import org.agora.graph.JAgoraGraph;
import org.agora.graph.JAgoraNode;

/**
 *
 * @author greg
 */
public class GraphPanel extends JPanel {
    
    public ArrayList<Post> posts;
    public ArrayList<Connection> connections;
    public JAgoraGraph graph;
    public JAgoraClient client;
    
    public GraphPanel(JAgoraClient client) {
        this(client, new JAgoraGraph());
    }
    
    public GraphPanel(JAgoraClient client, JAgoraGraph graph) {
        super();
        this.client = client;
        this.graph = graph;
        posts = new ArrayList<>();
        connections = new ArrayList<>();
        updateArguments();
        addMouseListener(new PanelListener());
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
        int ypos = 200;
        for (JAgoraNode node : graph.getNodes())
        {
            posts.add(new Post(node, new Point(200,ypos)));
            ypos += 200;
        }
        for (JAgoraEdge edge : graph.edgeMap.values()) {
            Post origin = getPost(edge.getOrigin());
            Post target = getPost(edge.getTarget());
            connections.add(new Connection(origin, target, edge));
        }
        
    }
    
    public Post getPost(JAgoraNode node) {
        for (Post a : posts) {
                if (a.getNode() == node)
                    return a;
            }
        return null;
    }
    
    public void centerViewpoint(Post a) {
        a.setPosition(new Point(getWidth()/2, getHeight()/2));
        ArrayList<JAgoraEdge> list = a.node.getOutgoingEdgeList();
        int xoff = 50*(list.size()-1);
        for (JAgoraEdge edge : list) {
            getPost(edge.getTarget()).setPosition(new Point(getWidth()/2 + xoff, getHeight()/2 -110));
            xoff += 100;
        }
        list = a.node.getIncomingEdgeList();
        xoff = 50*(list.size()-1);
        for (JAgoraEdge edge : list) {
            getPost(edge.getOrigin()).setPosition(new Point(getWidth()/2 + xoff, getHeight()/2 +110));
            xoff += 100;
        }
        repaint();
    }
    
    public class PanelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            for (Post p : posts) {
                if (p.containsPoint(e.getPoint())) 
                    centerViewpoint(p);
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
