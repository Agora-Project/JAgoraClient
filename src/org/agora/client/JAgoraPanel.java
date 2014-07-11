package org.agora.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;

import javax.swing.JPanel;

import org.agora.client.graph.Debate;

import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.app.beans.SVGIcon;

class JAgoraPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  
  protected Debate debate;
  
  protected SVGIcon icon;
  protected SVGDiagram diag;
  
  JAgoraPanel(Dimension d) throws SVGElementException {
    
    
    icon = new SVGIcon();
    icon.setAntiAlias(true);
    String svgStr = makeDynamicSVG();
    URI uri = SVGCache.getSVGUniverse().loadSVG(new StringReader(svgStr), "agoraGraph");
    icon.setSvgURI(uri);
    
    diag = icon.getSvgUniverse().getDiagram(uri);
   
    debate = new Debate(diag);
    
    setPreferredSize(d);
    setMinimumSize(d);
  }
  
  private String makeDynamicSVG() {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    pw.println("<svg width=\"400\" height=\"400\" style=\"fill:none;stroke-width:16\">");
    pw.println("</svg>"); 

    pw.close();
    return sw.toString();
  }
  
  public void paintComponent(Graphics g) {
    final int width = getWidth();
    final int height = getHeight();
    
    g.setColor(getBackground());
    g.fillRect(0, 0, width, height);
    icon.paintIcon(this, g, 0, 0);
  }
  
  public Debate getDebate() { return debate; }
}