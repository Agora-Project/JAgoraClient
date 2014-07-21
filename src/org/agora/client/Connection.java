package org.agora.client;

import java.awt.Color;
import java.awt.Graphics;
import org.agora.graph.JAgoraAttack;

/**
 *
 * @author greg
 */
public class Connection {
    protected Post origin, target;
    protected Color color;
    private boolean visible;
    
    public Connection(Post a, Post b, JAgoraAttack edge) {
        origin = a;
        target  = b;
        color = Color.BLACK;
        visible = true;
    }
    
    public void draw(Graphics g) {
        if (!isVisible()) return;
        g.setColor(color);
        g.drawLine(origin.getPosition().x +20, origin.getPosition().y, target.getPosition().x +20, target.getPosition().y);
    }

    /**
     * @return the visible
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * @param visible the visible to set
     */
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
