package org.agora.client;

import org.agora.graph.JAgoraNode;

import com.badlogic.gdx.math.Vector2;

/**
 * An argument is the view of a JAgoraNode.
 */
public class Argument {
  protected JAgoraNode node;
  protected Vector2 position;
  
  public Argument(JAgoraNode node, Vector2 position) {
    this.node = node;
    this.position = position;
  }
  
  
}
