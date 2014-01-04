package org.agora.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class JAgoraClient implements ApplicationListener {
  
  protected OrthographicCamera camera; 
  
  public void create () {
    camera = new OrthographicCamera();
    camera.setToOrtho(true, 10, 10);
  }

  public void render () {
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
  }

  public void resize (int width, int height) {
  }

  public void pause () {
  }

  public void resume () {
  }

  public void dispose () {
  }
  
  public static void main(String[] args) {
    new LwjglApplication(new JAgoraClient(), "JAgoraClient", 600, 600, false);
  }
}