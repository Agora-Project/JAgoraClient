package org.agora.client;

import java.awt.Graphics;
import java.awt.Point;
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
    
    public ArrayList<Argument> arguments;
    public ArrayList<Connection> connections;
    public JAgoraGraph graph;
    public JAgoraClient client;
    
    public GraphPanel(JAgoraClient client) {
        super();
        this.client = client;
        graph = new JAgoraGraph();
        arguments = new ArrayList<>();
        connections = new ArrayList<>();
    }
    
    public GraphPanel(JAgoraClient client, JAgoraGraph graph) {
        super();
        this.client = client;
        this.graph = graph;
        arguments = new ArrayList<>();
        connections = new ArrayList<>();
        updateArguments();
    }
    
    public void paintComponent(Graphics g) {
        for (Connection c : connections) {
            c.draw(g);
        }
        for (Argument a : arguments) {
            a.draw(g);
        }
    }
    
    public void updateArguments() {
        arguments.clear();
        connections.clear();
        int ypos = 200;
        for (JAgoraNode node : graph.getNodes())
        {
            arguments.add(new Argument(node, new Point(200,ypos)));
            ypos += 200;
        }
        for (JAgoraEdge edge : graph.edgeMap.values()) {
            Argument origin = null;
            for (Argument a : arguments) {
                if (a.getNode() == edge.getOrigin())
                    origin = a;
            }
            Argument target = null;
            for (Argument a : arguments) {
                if (a.getNode() == edge.getTarget())
                    target = a;
            }
            connections.add(new Connection(origin, target, edge));
        }
        
    }
    
    public void centerViewpoint(Argument a) {
        a.setPosition(new Point(getWidth()/2, getHeight()/2));
    }
    
}
