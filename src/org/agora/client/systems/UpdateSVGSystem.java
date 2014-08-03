package org.agora.client.systems;

import javax.swing.JPanel;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.EntitySystem;
import com.artemis.utils.ImmutableBag;
import com.kitfox.svg.SVGDiagram;
import com.kitfox.svg.SVGException;

public class UpdateSVGSystem extends EntitySystem {

  protected SVGDiagram diag;
  protected JPanel panel;

  public UpdateSVGSystem(SVGDiagram diag, JPanel panel) {
    super(Aspect.getEmpty());
    this.diag = diag;
    this.panel = panel;
  }
  
  @Override
  protected boolean checkProcessing() {
    return true;
  }

  @Override
  protected void processEntities(ImmutableBag<Entity> bag) {
    System.out.println("YEAAHH");
    try {
      diag.updateTime(0.0f);
      panel.repaint();
    } catch (SVGException e) {
      e.printStackTrace();
    }
  }


}
