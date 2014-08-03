package org.agora.client.systems;

import java.util.Random;

import org.agora.client.Options;
import org.agora.client.components.PositionComponent;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.IntervalEntityProcessingSystem;

public class RandomPositionMovingSystem extends IntervalEntityProcessingSystem {
  protected Random r;

  @Mapper ComponentMapper<PositionComponent> pm;
  
  @SuppressWarnings("unchecked")
  public RandomPositionMovingSystem() {
    super(Aspect.getAspectForAll(PositionComponent.class), Options.updateInterval);
    r = new Random();
  }
  
  @Override
  protected void process(Entity e) {
    System.out.println("RandPos");
    PositionComponent pc = pm.get(e);
    
    pc.x = pc.x + r.nextFloat()*10;
    pc.y = pc.y + r.nextFloat()*10;
  }

}
