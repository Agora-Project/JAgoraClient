package org.agora.client.components;

import org.agora.graph.JAgoraArgument;

import com.artemis.Component;

public class AgoraComponent extends Component {
  public JAgoraArgument arg;
  
  public AgoraComponent(JAgoraArgument arg) {
    this.arg = arg;
  }
}
