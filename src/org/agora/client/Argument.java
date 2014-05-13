package org.agora.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.agora.graph.JAgoraNode;

/**
 * An argument is the view of a JAgoraNode.
 */
public class Argument {
  protected JAgoraNode node;
  protected Point position;
  protected int width;
  protected int height;
  
  public Argument(JAgoraNode node, Point position) {
    this.node = node;
    this.position = position;
    width = 200;
    height = 100;
  }
  
  public Point getPosition() { return position; }
  public JAgoraNode getNode() { return node; }
  
  public void draw(Graphics g) {
      g.setColor(Color.black);
      g.drawRect( (int) position.getX(), (int) position.getY(), width, height);
      g.drawString(node.toString(), (int) position.getX() +20, (int) position.getY()+30);
      
  }
}
