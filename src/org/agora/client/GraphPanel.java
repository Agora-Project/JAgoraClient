package org.agora.client;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GraphPanel extends JPanel {
  private static final long serialVersionUID = -1687615694033778577L;

  int x;
  int y;
  
  GraphPanel() {
    this.setSize(500, 500);
    this.addMouseMotionListener(new MouseMotionListener() {

      @Override
      public void mouseDragged(MouseEvent arg0) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void mouseMoved(MouseEvent arg0) {
        System.out.println(arg0);
        x = arg0.getX();
        y = arg0.getY();
        GraphPanel.this.repaint();
      }});
  }
  
  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2 = (Graphics2D) g;
    super.paintComponent(g);
    
    
    g2.fillOval(x, y, 10, 10);
    System.out.println("AW YEAH");
  }

}
