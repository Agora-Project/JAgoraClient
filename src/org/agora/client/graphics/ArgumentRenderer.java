package org.agora.client.graphics;

import org.agora.client.Argument;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap.Filter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer;
import com.badlogic.gdx.graphics.glutils.ImmediateModeRenderer10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

public class ArgumentRenderer extends Renderer<Argument> {

  public int NUM_QUARTER_SEGMENTS = 5;
  
  // Deprecated? For circle rendering only.
  public int NUM_SEGMENTS = 60;
  
  public float ARGUMENT_MAX_SIZE = 10;
  public float ARGUMENT_MIN_SIZE = 1;
  
  
  public ArgumentRenderer(Argument element) { super(element); }

  @Override
  public void render(ShapeRenderer renderer) {
    Vector2 pos = element.getPosition();
    
    Texture tex = new Texture(Gdx.files.internal("Assets/ArgumentTexture.png"));
    tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
    
    
    float radius = ARGUMENT_MAX_SIZE;
    
    ImmediateModeRenderer imr = new ImmediateModeRenderer10();
    Gdx.graphics.getGL10().glEnable(GL10.GL_TEXTURE_2D);
    Gdx.graphics.getGL10().glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    tex.bind();
    
    imr.begin(renderer.getProjectionMatrix(), GL10.GL_TRIANGLE_FAN);
    imr.vertex(pos.x, pos.y, 0.0f);
    imr.texCoord(1.0f, 0.0f);
    
    float arc = (float) (2*Math.PI / NUM_SEGMENTS);
    float arcPoint = 0;
    Vector2 p = new Vector2();
    for (int i = 0; i < NUM_SEGMENTS; i++) {
      arcPoint = i*arc;
      p.x = pos.x + (float)Math.cos(arcPoint)*radius;
      p.y = pos.y + (float)Math.sin(arcPoint)*radius;
      imr.vertex(p.x, p.y, 0.0f);
      imr.texCoord(0.0f, 0.0f);
    }
    imr.end();
    
    tex.dispose();
  }
  
  
  public void render2(ShapeRenderer renderer) {
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
