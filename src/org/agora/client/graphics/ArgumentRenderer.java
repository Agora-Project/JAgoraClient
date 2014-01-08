package org.agora.client.graphics;

import org.agora.client.Argument;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class ArgumentRenderer extends Renderer<Argument> {

  public int NUM_SEGMENTS = 30;
  
  public float ARGUMENT_MAX_SIZE = 10;
  public float ARGUMENT_MIN_SIZE = 1;
  
  
  public ArgumentRenderer(Argument element) { super(element); }

  @Override
  public void render(ShapeRenderer renderer) {
    Vector2 pos = element.getPosition();
    
    renderer.begin(ShapeType.Filled);
    renderer.setColor(0.6f, 0.3f, 0.7f, 1.0f);
    renderer.circle(pos.x, pos.y, ARGUMENT_MAX_SIZE, NUM_SEGMENTS);
    renderer.end();
    
    Gdx.gl.glLineWidth(3);
    renderer.begin(ShapeType.Line);
    renderer.setColor(1,1,1,1);
    renderer.circle(pos.x, pos.y, ARGUMENT_MAX_SIZE, NUM_SEGMENTS);
    renderer.end();
  }
}
