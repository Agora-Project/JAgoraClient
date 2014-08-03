package org.agora.client;

public class Options {
  public static int fps = 30;
  public static float updateInterval = 1/30f;
  
  public static Style style;
  
  public static void init() {
    Options.style = new Style();
    style.ARG_FILL_COLOUR = "#338855";
    style.ARG_RADIUS = 30;
    style.ARG_STROKE_COLOUR = "black";
    style.ARG_WIDTH = 3;
  }
}
