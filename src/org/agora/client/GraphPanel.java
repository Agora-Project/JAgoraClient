package org.agora.client;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.agora.graph.JAgoraGraph;
import org.agora.graph.JAgoraNode;

/**
 *
 * @author greg
 */
public class GraphPanel extends JPanel {
    
    public ArrayList<Argument> arguments;
    public JAgoraGraph graph;
    public JAgoraClient client;
    
    public GraphPanel(JAgoraClient client) {
        super();
        this.client = client;
        graph = new JAgoraGraph();
        arguments = new ArrayList<>();
    }
    
    public GraphPanel(JAgoraClient client, JAgoraGraph graph) {
        super();
        this.client = client;
        this.graph = graph;
        arguments = new ArrayList<>();
        updateArguments();
    }
    
    public void paintComponent(Graphics g) {
        for (Argument a : arguments) {
            a.draw(g);
        }
    }
    
    public void updateArguments() {
        arguments.clear();
        int ypos = 0;
        for (JAgoraNode node : graph.getNodes())
        {
            arguments.add(new Argument(node, new Point(0,ypos)));
            ypos += 100;
        }
    }
    
}
