package org.agora.client;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.kitfox.svg.Circle;
import com.kitfox.svg.Group;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElement;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.animation.AnimationElement;
import com.kitfox.svg.app.beans.SVGIcon;


//class JAgoraPanel extends JPanel {
//  private static final long serialVersionUID = 1L;
//  
//  protected SVGIcon icon;
//  
//  JAgoraPanel(Dimension d) throws SVGElementException {
//    setPreferredSize(d);
//    setMinimumSize(d);
//    
//    icon = new SVGIcon();
//    icon.setAntiAlias(true);
//    String svgStr = makeDynamicSVG();
//    URI uri = icon.getSvgUniverse().loadSVG(new StringReader(svgStr), "agoraGraph");
//    SVGDiagram diag = icon.getSvgUniverse().getDiagram(uri);
//    SVGElement g = new Group();
//    diag.getRoot().loaderAddChild(null, g);
//    Circle c = new Circle();
//    c.addAttribute("cx", AnimationElement.AT_XML, "10");
//    c.addAttribute("cy", AnimationElement.AT_XML, "10");
//    c.addAttribute("r", AnimationElement.AT_XML, "10");
//    c.addAttribute("stroke", AnimationElement.AT_CSS, "#000000");
//    c.addAttribute("fill", AnimationElement.AT_CSS, "#000000");
//    g.loaderAddChild(null, c);
//    try {
//      diag.updateTime(0.0f);
//    } catch (SVGException e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//    }
//    repaint();
//  }
//  
//  private String makeDynamicSVG() {
//    StringWriter sw = new StringWriter();
//    PrintWriter pw = new PrintWriter(sw);
//
//    pw.println("<svg width=\"400\" height=\"400\" style=\"fill:#000000;stroke-width:16\"></svg>");   
//
//    pw.close();
//    System.out.println("Tralala2");
//    return sw.toString();
//  }
//  
//  public void paintComponent(Graphics g) {
//    System.out.println("Tralala");
//    icon.paintIcon(this, g, 0, 0);
//  }
//}

public class JAgoraClientSVG extends JFrame {
  private static final long serialVersionUID = 1L;

  JAgoraPanel panel;
  
  public JAgoraClientSVG() throws SVGElementException {
    panel = new JAgoraPanel(new Dimension(400, 400));
    this.getContentPane().add(panel, BorderLayout.CENTER);
    panel.setVisible(true);
    JLabel label = new JLabel("lol");
    label.setVisible(true);;
    this.getContentPane().add(label);
    this.pack();
    this.setVisible(true);
  }
  
  public static void main(String[] args) throws SVGElementException {
    JAgoraClientSVG svg = new JAgoraClientSVG();
  }
}
