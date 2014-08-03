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
    Options.init();
    
    buildLayout();
  }
  
  protected void buildLayout() throws SVGElementException  {
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
    for (int i = 0; i < 1; i++) {
      panel.addArgument(new JAgoraArgument());
    }
    
    panel.run();
  }
  
  public static void main(String[] args) throws SVGElementException {
    new JAgoraClient().test();
  }
}
