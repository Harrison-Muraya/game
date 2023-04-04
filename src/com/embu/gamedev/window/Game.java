package com.embu.gamedev.window;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;
import com.embu.gamedev.framework.GameObject;
import com.embu.gamedev.framework.KeyInput;
import com.embu.gamedev.framework.ObjectId;
import com.embu.gamedev.framework.Texture;
import com.embu.gamedev.objects.Block;
import com.embu.gamedev.objects.Player;
public class Game extends Canvas implements Runnable {
private static final long serialVersionUID = -8360998378314880919L;
private boolean running = false;
private Thread thread;
static int WIDTH, HEIGHT;
private BufferedImage level = null;
private BufferedImage cloud = null;
Handler handler;
Camera cam;
static Texture tex;
Random rand = new Random();


public void init() {
WIDTH = getWidth();
HEIGHT = getHeight();
tex=new Texture();
BufferedImageLoader loader = new BufferedImageLoader();
level = loader.loadImage("/level.png");
cloud=loader.loadImage("/background_cloud2.png");//load cloud image
handler = new Handler();
cam = new Camera(0, 0);
loadImageLevel(level);
this.addKeyListener(new KeyInput(handler));
}


public synchronized void start() {
if (running)
return;
running = true;
Thread thread = new Thread(this);
thread.start();
}
public void run() {
init();
this.requestFocus();
long lastTime = System.nanoTime();
double amountOfTicks = 60.0;
double ns = 1000000000 / amountOfTicks;
double delta = 0;
long timer = System.currentTimeMillis();
int updates = 0;
int frames = 0;
while (running) {
long now = System.nanoTime();
delta += (now - lastTime) / ns;
lastTime = now;
while (delta >= 1) {
tick();
updates++;
delta--;
}
render();
frames++;
if (System.currentTimeMillis() - timer > 1000) {
timer += 1000;
System.out.println("FPS: " + frames + " TICS: " + 
updates);
frames = 0;
updates = 0;
}
}
}
private void tick() {
handler.tick();
for (int i = 0; i < handler.object.size(); i++) {
if (handler.object.get(i).getId() == ObjectId.Player) {
cam.tick(handler.object.get(i));
}
}
}
private void render() {
BufferStrategy bs = this.getBufferStrategy();
if (bs == null) {
this.createBufferStrategy(3);
return;
}
Graphics g = bs.getDrawGraphics();
Graphics2D g2d = (Graphics2D) g;
g.setColor(new Color(70, 133,200)); //light blue color
g.fillRect(0, 0, getWidth(), getHeight());
g.drawImage(cloud, 0, 0, getWidth(), getHeight(), null);
g2d.translate(cam.getX(), cam.getY()); // Start
handler.render(g);
g2d.translate(-cam.getX(), cam.getY()); // end
g.dispose();
bs.show();
}

public void loadImageLevel(BufferedImage image) {
int w = image.getWidth();
int h = image.getHeight();
System.out.println("Width, height" + w + " " + h);
for (int xx = 0; xx < w; xx++) {
for (int yy = 0; yy < h; yy++) {
 //get pixels by looping
int pixel = image.getRGB(xx, yy);
int red=(pixel >> 16) & 0xff;
int green=(pixel >> 8) & 0xff;
int blue=(pixel) & 0xff;
if(red==255 && green==255 && blue==255) 
handler.addObject(new Block(xx*32.0f,yy*32,0,ObjectId.Block)); //modified to 0 for dirty block
if(red==0 && green==255 && blue==0) handler.addObject(new
Player(xx*32,yy*32,handler,ObjectId.Player));
}
}
}
public static Texture getInstance(){
return tex;
}
public static void main(String args[]) {
new Window(900, 600, "Embu Games Prototype", new Game());
}
}