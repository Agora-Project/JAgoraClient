package org.agora.client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.URI;
import java.util.Random;

import javax.swing.JPanel;

import org.agora.client.components.AgoraComponent;
import org.agora.client.components.PositionComponent;
import org.agora.client.components.SVGArgumentComponent;
import org.agora.client.systems.PositionUpdaterSystem;
import org.agora.client.systems.RandomPositionMovingSystem;
import org.agora.client.systems.UpdateSVGSystem;
import org.agora.graph.JAgoraArgument;

import com.artemis.Entity;
import com.artemis.World;
import com.kitfox.svg.Circle;
import com.kitfox.svg.Group;
import com.kitfox.svg.SVGCache;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.app.beans.SVGIcon;

class JAgoraPanel extends JPanel {
  private static final long serialVersionUID = 1L;
  
  protected Random r;
  
  protected SVGIcon icon;
  protected SVGDiagram diag;
  
  protected Group view;
  protected Group arguments;
  protected Group attacks;
 
  protected World world;
  
  JAgoraPanel(Dimension d) throws SVGElementException {
    r = new Random();
    
    // Set up SVG
    icon = new SVGIcon();
    icon.setAntiAlias(true);
    String svgStr = makeDynamicSVG();
    URI uri = SVGCache.getSVGUniverse().loadSVG(new StringReader(svgStr), "agoraGraph");
    icon.setSvgURI(uri);
    
    diag = icon.getSvgUniverse().getDiagram(uri);
   
    view = new Group(); arguments = new Group(); attacks = new Group();
    
    try {
      diag.getRoot().loaderAddChild(null, view);
      view.loaderAddChild(null, arguments);
      view.loaderAddChild(null, attacks);
    } catch (SVGElementException e) {
      e.printStackTrace();
    }
    
        // Set up Artemis
    world = new World();
    world.setSystem(new RandomPositionMovingSystem());
    world.setSystem(new PositionUpdaterSystem());
    world.setSystem(new UpdateSVGSystem(diag, this));
    world.initialize();
    
    // Set up Swing
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
  
  
  
  public void addArgument(JAgoraArgument agarg) {
    Entity e = world.createEntity();
    PositionComponent pc = new PositionComponent();
    pc.x = r.nextFloat()*400; pc.y = r.nextFloat()*400;
    e.addComponent(new PositionComponent());
    e.addComponent(new SVGArgumentComponent(newSVGArgument()));
    e.addComponent(new AgoraComponent(agarg));
    e.changedInWorld();
  }
  
  protected Circle newSVGArgument() {
    Circle c = new Circle();
    try {
      arguments.loaderAddChild(null, c);
    } catch (SVGElementException e) { e.printStackTrace(); }
    return c;
  }
  
  public void run() {
  
    long oldT = System.currentTimeMillis();
    while(true) {
      try {
        long newT = System.currentTimeMillis();
        long diffT = newT - oldT;
        world.setDelta(diffT/1000f);
        world.process();
        oldT = newT;
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
  
  public void paintComponent(Graphics g) {
    final int width = getWidth();
    final int height = getHeight();
    
    g.setColor(getBackground());
    g.fillRect(0, 0, width, height);
    icon.paintIcon(this, g, 0, 0);
  }
}