package org.agora.client;


import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.agora.graph.JAgoraArgument;

import com.kitfox.svg.SVGElementException;


public class JAgoraClient extends JFrame {
  private static final long serialVersionUID = 1L;

  
  JAgoraPanel panel;
  
  public JAgoraClient() throws SVGElementException {
    JPanel p = new JPanel();
    p.setLayout(new BorderLayout());
    getContentPane().add(p, BorderLayout.CENTER);
    
    
    p.setVisible(true);
    panel = new JAgoraPanel(new Dimension(400, 400));
    
    p.add(panel, BorderLayout.CENTER);
    panel.setVisible(true);
    JLabel label = new JLabel("Agora - debate now!");
    label.setVisible(true);
    p.add(label, BorderLayout.AFTER_LAST_LINE);
    this.pack();
    this.setVisible(true);
  }
  
  public void test() {
    Style s = new Style();
    Options.style = s;
    s.ARG_FILL_COLOUR = "#338855";
    s.ARG_RADIUS = 30;
    s.ARG_STROKE_COLOUR = "black";
    s.ARG_WIDTH = 3;
    
    for (int i = 0; i < 20; i++) {
      panel.getDebate().addArgument(new JAgoraArgument());
    }
    
    while(true) {
      try {
        Thread.sleep(30);
        panel.getDebate().step();
        panel.repaint();
        System.out.println("Step");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public static void main(String[] args) throws SVGElementException {
    new JAgoraClient().test();
  }
}
