package org.agora.client.graphics;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Renders something of type T
 */
public abstract class Renderer<T> {
  protected T element;
  
  public Renderer(T element) {
    this.element = element;
  }
  
  public abstract void render(ShapeRenderer renderer);
}
