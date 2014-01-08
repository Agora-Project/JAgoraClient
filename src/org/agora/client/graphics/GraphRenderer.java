package org.agora.client.graphics;

import java.util.HashMap;
import java.util.Map;

import org.agora.client.Argument;
import org.agora.graph.JAgoraGraph;
import org.agora.graph.JAgoraNode;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class GraphRenderer extends Renderer<JAgoraGraph>{

  protected Map<JAgoraNode, ArgumentRenderer> nodeRenderers;
  
  public GraphRenderer(JAgoraGraph element) {
    super(element);
    nodeRenderers = new HashMap<JAgoraNode, ArgumentRenderer>();
    updateRenderers();
  }

  @Override
  public void render(ShapeRenderer renderer) {
    // Attack renderers should go first?

    for (ArgumentRenderer ar : nodeRenderers.values())
      ar.render(renderer);
  }
  
  protected void updateRenderers() {
    Map<JAgoraNode, ArgumentRenderer> newRenderers = new HashMap<JAgoraNode, ArgumentRenderer>(element.getNodes().length);
    
    for (JAgoraNode node : element.getNodes()) {
      if (nodeRenderers.containsKey(node))  // If there was a renderer before, keep it. 
        newRenderers.put(node, nodeRenderers.get(node));
      else // TODO: change default argument position.
        newRenderers.put(node, new ArgumentRenderer(new Argument(node, new Vector2(0,0))));
    }
    
    nodeRenderers = newRenderers;
  }

}
