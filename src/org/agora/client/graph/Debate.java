package org.agora.client.graph;

import java.util.Random;

import org.agora.graph.JAgoraArgument;

import com.kitfox.svg.Group;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGElementException;
import com.kitfox.svg.SVGException;

public class Debate {

  protected SVGDiagram diag;
  protected Group arguments;
  protected Group attacks;
  
  public Debate(SVGDiagram diag) {
    this.diag = diag;
    
    arguments = new Group();
    attacks = new Group();
    
    try {
      diag.getRoot().loaderAddChild(null, arguments);
      diag.getRoot().loaderAddChild(null, attacks);
    } catch (SVGElementException e) {
      e.printStackTrace();
    }
  }
  
  public Argument addArgument(JAgoraArgument argument) {
    Argument arg = new Argument(argument);
    Random r = new Random();
    arg.setPosition((int)(r.nextFloat()*400.0), (int)(r.nextFloat()*400));
    try {
      arguments.loaderAddChild(null, arg);
      arg.updateTime(0.0f);
    } catch (SVGElementException e) {
      e.printStackTrace();
    } catch (SVGException e) {
      e.printStackTrace();
    }
    return arg;
  }
  
  public void step() {
    for (int i = 0; i < arguments.getNumChildren(); i++) {
      ((Argument)arguments.getChild(i)).step();
    }
    try {
      arguments.updateTime(0.0f);
      diag.getRoot().updateTime(0.0f);
    } catch (SVGException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
