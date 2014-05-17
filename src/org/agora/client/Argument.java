package org.agora.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Iterator;
import org.agora.graph.JAgoraEdge;
import org.agora.graph.JAgoraNode;

/**
 * An argument is the view of a JAgoraNode.
 */
public class Argument {
  protected JAgoraNode node;
  private Point position;
  protected int width;
  protected int height;
  
  public Argument(JAgoraNode node, Point position) {
    this.node = node;
    this.position = position;
    width = 200;
    height = 100;
  }
  
  public Point getPosition() { return position; }
  public void setPosition(Point position) { this.position = position; }
  public JAgoraNode getNode() { return node; }
  
  public void draw(Graphics g) {
      g.setColor(Color.black);
      g.drawRect( (int) position.getX() -(width/2), (int) position.getY() -(height/2), width, height);
      g.drawString((String) node.getContent().get("txt"), (int) position.getX() +2, (int) position.getY()+15);
      Iterator<JAgoraEdge> edges = node.getIncomingEdges();
  }
  
}
