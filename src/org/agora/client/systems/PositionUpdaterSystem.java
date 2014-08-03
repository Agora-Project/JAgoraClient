package org.agora.client.systems;

import org.agora.client.Options;
import org.agora.client.components.PositionComponent;
import org.agora.client.components.SVGArgumentComponent;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;

public class PositionUpdaterSystem extends IntervalEntityProcessingSystem {

  @Mapper ComponentMapper<PositionComponent> pm;
  @Mapper ComponentMapper<SVGArgumentComponent> cm;
  
  @SuppressWarnings("unchecked")
  public PositionUpdaterSystem() {
    super(Aspect.getAspectForAll(PositionComponent.class, SVGArgumentComponent.class), Options.updateInterval);
  }
  
  @Override
  protected void process(Entity e) {
    System.out.println("PosUpdt");
    PositionComponent pc = pm.get(e);
    SVGArgumentComponent cc = cm.get(e);
    
    cc.setPosition(pc.x, pc.y);
    System.out.println(pc.x + " " + pc.y);
  }

}
