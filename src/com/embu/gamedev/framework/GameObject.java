
package com.embu.gamedev.framework;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;


 public abstract class GameObject {
protected boolean falling=true;
protected boolean jumping=false;
protected float x,y;
protected ObjectId id;
protected float VelX=0, VelY=0;
public GameObject(float x,float y, ObjectId id){
this.x=x;
this.y=y;
this.id=id;
}
public abstract void tick(LinkedList<GameObject> object);
public abstract void render(Graphics g);
public abstract Rectangle getBounds();
public float getX() {
return x;
}
public float getY() {
return y;
}
public void setX(float x) {
this.x=x;
}
public void setY(float y) {
this.y=y;
}
public float getVelX() {
return VelX;
}
public float getVelY() {
return VelY;
}
public void setVelX(float VelX) {
this.VelX=VelX;
}
public void setVelY(float VelY) {
this.VelY=VelY;
}
public ObjectId getId() {
return id;
}
public boolean isFalling() {
return falling;
}
public void setFalling(boolean falling) {
this.falling = falling;
}
public boolean isJumping() {
return jumping;
}
public void setJumping(boolean jumping) {
this.jumping = jumping;
}
}
