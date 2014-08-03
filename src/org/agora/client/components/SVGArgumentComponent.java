package org.agora.client.components;

import org.agora.client.Options;
import org.agora.client.Style;

import com.artemis.Component;
import com.kitfox.svg.Circle;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.animation.AnimationElement;

public class SVGArgumentComponent extends Component {  
  protected Circle c;
  
  public SVGArgumentComponent(Circle c) {
    this.c = c;
    init(Options.style);
  }
  
  public void init(Style style) {
    try {
      c.addAttribute("cx", AnimationElement.AT_XML, "0");
      c.addAttribute("cy", AnimationElement.AT_XML, "0");
      c.addAttribute("r", AnimationElement.AT_XML, "" + style.ARG_RADIUS);
      c.addAttribute("stroke", AnimationElement.AT_CSS, "" + style.ARG_STROKE_COLOUR);
      c.addAttribute("stroke-width", AnimationElement.AT_CSS, "" + style.ARG_WIDTH);
      c.addAttribute("fill", AnimationElement.AT_CSS, "" + style.ARG_FILL_COLOUR);
    } catch (SVGElementException e) {
      e.printStackTrace();
    }
  }
  
  public void setStyle() {
    setStyle(Options.style);
  }
  
  public void setStyle(Style style) {
    try {
      c.setAttribute("r", AnimationElement.AT_XML, "" + style.ARG_RADIUS);
      c.setAttribute("stroke", AnimationElement.AT_CSS, "" + style.ARG_STROKE_COLOUR);
      c.setAttribute("stroke-width", AnimationElement.AT_CSS, "" + style.ARG_WIDTH);
      c.setAttribute("fill", AnimationElement.AT_CSS, "" + style.ARG_FILL_COLOUR);
    } catch (SVGElementException e) {
      e.printStackTrace();
    }
  }
  
  public void setPosition(float x, float y) {
    try {
      c.setAttribute("cx", AnimationElement.AT_XML, ""+ Math.round(x));
      c.setAttribute("cy", AnimationElement.AT_XML, ""+ Math.round(y));
    } catch (SVGElementException e) {
      e.printStackTrace();
    }
  }
}
