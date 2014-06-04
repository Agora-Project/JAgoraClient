package org.agora.client;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import org.agora.graph.JAgoraNode;

/**
 * An argument is the view of a JAgoraNode.
 */
public class Post {
  protected JAgoraNode node;
  protected Point position;
  protected int width;
  protected int height;
  protected boolean visible;
  protected ArrayList<String> content;
  
  public Post(JAgoraNode node, Point position) {
    this.node = node;
    this.position = position;
    adjustSize(200);
    visible = true;
    
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
  
  public void adjustSize(int width) {
//      int characters = 0;
      String[] tokens = new String[0];
      if (node.getContent().containsField("txt")) {
//          characters = ((String) node.getContent().get("txt")).length();
          tokens = ((String) node.getContent().get("txt")).split(" ");
      }
      else if (node.getContent().containsField("Text")) {
//          characters = ((String) node.getContent().get("Text")).length();
          tokens = ((String) node.getContent().get("Text")).split(" ");
      }
      
      int lines = 1;
      content = new ArrayList<>();
      String line = new String();
      for (String s : tokens) {
          if ((line.length() + s.length()) * 8 < width) {
              line += s + " ";
          }
          else {
              lines += 1;
              content.add(line);
              line = s + " ";
          }
      }
      content.add(line);
      this.width = width;
      height = 20 + lines * 20;
  }
  
  public void draw(Graphics g) {
      if (!isVisible()) return;
      g.setColor(Color.white);
      int startx = (int) position.getX() -(width/2);
      int starty = (int) position.getY() -(height/2);
      g.fillRect( startx, starty, width, height);
      g.setColor(Color.black);
      g.drawRect( startx, starty, width, 18);
      if (node.getContent().containsField("Title")) 
          g.drawString((String) node.getContent().get("Title"), 
              startx +2, starty +15);
      g.drawRect(startx, starty, width, height);
      
      int line = 0;
      for (String s : content) {
          g.drawString(s, startx +2, starty +35 +(line*20));
          line++;
      }
          
//      Iterator<JAgoraEdge> edges = node.getIncomingEdges();
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
