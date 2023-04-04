/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.embu.gamedev.window;
import com.embu.gamedev.framework.GameObject;

/**
 *
 * @author User
 */
public class Camera {
public Camera(float x, float y) {
this.x = x;
this.y = y;
}
public void tick(GameObject player){
x=-player.getX()+Game.WIDTH/2;
}
private float x, y;
public float getX() {
return x;
}
public void setX(float x) {
this.x = x;
}
public float getY() {
return y;
}
public void setY(float y) {
this.y = y;
}
}