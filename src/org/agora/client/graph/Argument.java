package org.agora.client.graph;

import java.util.Random;

import org.agora.client.Options;
import org.agora.client.Style;
import org.agora.graph.JAgoraArgument;

import com.kitfox.svg.Circle;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;
import com.kitfox.svg.animation.AnimationElement;

public class Argument extends Circle {
  private static final long serialVersionUID = 1L;
  
  protected int posX = 0, posY = 0;
  protected int velX = 0;
  protected int velY = 0;

  protected JAgoraArgument arg;
  
  public Argument(JAgoraArgument arg) {
    this.arg = arg;
    
    initStyle(Options.style);
  }
  
  public void initStyle(Style style) {
    try {
      addAttribute("cx", AnimationElement.AT_XML, "0");
      addAttribute("cy", AnimationElement.AT_XML, "0");
      addAttribute("r", AnimationElement.AT_XML, "" + style.ARG_RADIUS);
      addAttribute("stroke", AnimationElement.AT_CSS, "" + style.ARG_STROKE_COLOUR);
      addAttribute("stroke-width", AnimationElement.AT_CSS, "" + style.ARG_WIDTH);
      addAttribute("fill", AnimationElement.AT_CSS, "" + style.ARG_FILL_COLOUR);
    } catch (SVGElementException e) {
      e.printStackTrace();
    }
  }
  
  public void setStyle() {
    setStyle(Options.style);
  }
  
  public void setStyle(Style style) {
    try {
      setAttribute("r", AnimationElement.AT_XML, "" + style.ARG_RADIUS);
      setAttribute("stroke", AnimationElement.AT_CSS, "" + style.ARG_STROKE_COLOUR);
      setAttribute("stroke-width", AnimationElement.AT_CSS, "" + style.ARG_WIDTH);
      setAttribute("fill", AnimationElement.AT_CSS, "" + style.ARG_FILL_COLOUR);
      updateTime(0f);
    } catch (SVGElementException e) {
      e.printStackTrace();
    } catch (SVGException e) {
      e.printStackTrace();
    }
  }
  
  public void setPosition(int x, int y) {
    try {
      posX = x; posY = y;
      setAttribute("cx", AnimationElement.AT_XML, ""+ x);
      setAttribute("cy", AnimationElement.AT_XML, ""+ y);
      updateTime(0.0f);
    } catch (SVGElementException e) {
      e.printStackTrace();
    } catch (SVGException e) {
      e.printStackTrace();
    }
  }
  
  public void step() {
    Random r = new Random();
    velX = velX + (int)(4*(r.nextFloat() - 0.5));
    velY = velY + (int)(4*(r.nextFloat() - 0.5));
    
    setPosition(posX + velX, posY + velY);
    System.out.println(posX + " - " + posY);
  }
}
  
