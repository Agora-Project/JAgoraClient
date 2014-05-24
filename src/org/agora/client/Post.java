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
public class Post {
  protected JAgoraNode node;
  private Point position;
  protected int width;
  protected int height;
  
  public Post(JAgoraNode node, Point position) {
    this.node = node;
    this.position = position;
    width = 200;
    height = 100;
  }
  
  public Point getPosition() { return position; }
  public void setPosition(Point position) { this.position = position; }
  public JAgoraNode getNode() { return node; }
  
  public boolean containsPoint(Point p) {
      if (p.x > position.getX() -(width/2) && p.x < position.getX() +(width/2) 
       && p.y > position.getY() -(height/2) && p.y < position.getY() +(height/2))
          return true;
      else return false;
  }
  
  public void draw(Graphics g) {
      g.setColor(Color.white);
      g.fillRect( (int) position.getX() -(width/2), (int) position.getY() -(height/2), width, height);
      g.setColor(Color.black);
      g.drawRect( (int) position.getX() -(width/2), (int) position.getY() -(height/2), width, 18);
      if (node.getContent().containsField("Title")) 
          g.drawString((String) node.getContent().get("Title"), 
              (int) position.getX() -(width/2) +2, (int) position.getY() -(height/2) +15);
      g.drawRect( (int) position.getX() -(width/2), (int) position.getY() -(height/2), width, height);
      if (node.getContent().containsField("txt"))
          g.drawString((String) node.getContent().get("txt"), 
              (int) position.getX() -(width/2) +2, (int) position.getY() -(height/2) +35);
      else if (node.getContent().containsField("Text"))
          g.drawString((String) node.getContent().get("Text"), 
              (int) position.getX() -(width/2) +2, (int) position.getY() -(height/2) +35);
          
      Iterator<JAgoraEdge> edges = node.getIncomingEdges();
  }
  
}
