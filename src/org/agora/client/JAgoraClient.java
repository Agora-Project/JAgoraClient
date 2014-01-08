package org.agora.client;

import org.agora.client.graphics.GraphRenderer;
import org.agora.graph.JAgoraGraph;
import org.agora.graph.JAgoraNode;
import org.agora.graph.JAgoraNodeID;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class JAgoraClient implements ApplicationListener {
  
  protected JAgoraGraph graph;
  protected GraphRenderer graphRenderer;
  
  protected OrthographicCamera camera;
  
  protected ShapeRenderer shapeRenderer;
  
  public void create () {
    camera = new OrthographicCamera();
    camera.setToOrtho(true, 100, 100);
    shapeRenderer = new ShapeRenderer();
    
    graph = new JAgoraGraph();
    JAgoraNode testNode = new JAgoraNode(new JAgoraNodeID("bigornas.bounceme.net", 1));
    graph.addNode(testNode);
    
    graphRenderer = new GraphRenderer(graph);
  }

  public void render () {
    GL10 gl = Gdx.graphics.getGL10();
    gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    gl.glViewport(0, 0, 600, 600);
    
    camera.update();
    camera.apply(gl);
    shapeRenderer.setProjectionMatrix(camera.combined);
    
    graphRenderer.render(shapeRenderer);
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