package org.agora.client;

public class JAgoraClient extends javax.swing.JFrame {
  private static final long serialVersionUID = 9150667046879612245L;
  
  protected GraphPanel gPanel; 
  
  public JAgoraClient() {
    initComponents();
  }
  
  
  private void initComponents() {
    gPanel = new GraphPanel();
    this.add(gPanel);
    pack();
}


public static void main(String args[]) {
    new JAgoraClient().setVisible(true);
}
 
 
}
