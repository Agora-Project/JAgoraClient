package org.agora.client;

import java.awt.Color;
import java.awt.Graphics;
import org.agora.graph.JAgoraEdge;

/**
 *
 * @author greg
 */
public class Connection {
    protected Post origin, target;
    protected Color color;
    
    public Connection(Post a, Post b, JAgoraEdge edge) {
        origin = a;
        target  = b;
        color = Color.BLACK;
    }
    
    public void draw(Graphics g) {
        g.setColor(color);
        g.drawLine(origin.getPosition().x +20, origin.getPosition().y, target.getPosition().x +20, target.getPosition().y);
    }
}
