package com.embu.gamedev.objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import com.embu.gamedev.framework.GameObject;
import com.embu.gamedev.framework.ObjectId;
import com.embu.gamedev.framework.Texture;
import com.embu.gamedev.window.Animation;
import com.embu.gamedev.window.Game;
import com.embu.gamedev.window.Handler;
public class Player extends GameObject {
private float width = 32, height = 64;
private float gravity = 0.5f;
private final float MAX_SPEED = 10; // limit the falling speed
private Handler handler;
Texture tex = Game.getInstance();
private Animation playerWalk,playerWalkleft;
private int facing=1; // 1=right,-1=left to the declaratio
public Player(float x, float y, Handler handler, ObjectId id) {
super(x, y, id);
this.handler = handler;
playerWalk = new Animation(6, tex.player[1], tex.player[2], tex.player[3],
tex.player[4], tex.player[5],tex.player[6]);// 6 represents speed
playerWalkleft = new Animation(6, tex.player[8], tex.player[9],tex.player[10], tex.player[11], tex.player[12],tex.player[12]);// 6 represents speed
}

public void tick(LinkedList<GameObject> object) {
x += VelX;
y += VelY;
if(VelX<0)facing=-1;
 else if(VelX>0) facing=1;
if (falling || jumping) {
 VelY += gravity;
 if (VelY > MAX_SPEED) // ensure gravity speed does not go past MAX_SPEED
VelY = MAX_SPEED;
 }
 collision(object);
 playerWalk.runAnimation();
 playerWalkleft.runAnimation();
}

private void collision(LinkedList<GameObject> object) {
    for (int i = 0; i < handler.object.size(); i++) {
        GameObject tempObject = handler.object.get(i);
        if (tempObject.getId() == ObjectId.Block) {
            // top collision
            if (getBoundsTop().intersects(tempObject.getBounds())) {
                y = tempObject.getY() + tempObject.getBounds().height;
                VelY = 0;
            }
            // bottom collision
            if (getBounds().intersects(tempObject.getBounds())) {
                y = tempObject.getY() - height;
                VelY = 0;
                falling = false;
                jumping = false;
            } else {
                falling = true;
            }
            // right
            if (getBoundsRight().intersects(tempObject.getBounds())) {
                x = tempObject.getX() - width;
            }
            // left
            if (getBoundsLeft().intersects(tempObject.getBounds())) {
                x = tempObject.getX() + tempObject.getBounds().width;
            }
        }
    }
}

public void render(Graphics g) {
    g.setColor(Color.green);
    if (facing == 1) {
        playerWalk.drawAnimation(g, (int) x, (int) y);
    } else {
        playerWalkleft.drawAnimation(g, (int) x, (int) y);
    }
    if (facing == 1) {
        g.drawImage(tex.player[0], (int) x, (int) y, null);
    } else {
        g.drawImage(tex.player[7], (int) x, (int) y, null);
    }
}
public Rectangle getBounds() {
return new Rectangle((int) ((int) x + (width / 2) - (width / 2) /
2), (int) ((int) y + (height / 2)),(int) width / 2, (int) height / 2);
}
public Rectangle getBoundsTop() {
return new Rectangle((int) ((int) x + (width / 2) - (width / 2) / 2),
(int) y, (int) width / 2,(int) height / 2);
}
public Rectangle getBoundsRight() {
    return new Rectangle((int) (x + width - 5), (int) (y + 5), 5, (int) (height - 10));
}

public Rectangle getBoundsLeft() {
    return new Rectangle((int) x, (int) (y + 5), 5, (int) (height - 10));
}

}


