/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.embu.gamedev.framework;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.embu.gamedev.window.Handler;
/**
 *
 * @author User
 */
public class KeyInput extends KeyAdapter{
Handler handler;
public KeyInput(Handler handler){
this.handler=handler;
}
public void keyPressed(KeyEvent e){
int key=e.getKeyCode();
for(int i=0;i<handler.object.size();i++){
GameObject tempObject=handler.object.get(i);
if(tempObject.getId()==ObjectId.Player)
 {
 if(key==KeyEvent.VK_D) tempObject.setVelX(5);
 if(key==KeyEvent.VK_A) tempObject.setVelX(-5);
 if(key==KeyEvent.VK_SPACE) tempObject.setVelY(-
10);//jumping
 // to ensure we dont jump forever
if(key==KeyEvent.VK_SPACE && !tempObject.isJumping())
 {
tempObject.setJumping(true);
tempObject.setVelY(-10);
}
 }
}
if(key==KeyEvent.VK_ESCAPE){
System.exit(1);
}
}
public void keyReleased(KeyEvent e){
int key=e.getKeyCode();
for(int i=0;i<handler.object.size();i++){
GameObject tempObject=handler.object.get(i);
if(tempObject.getId()==ObjectId.Player)
 {
 if(key==KeyEvent.VK_D) tempObject.setVelX(0);
 if(key==KeyEvent.VK_A) tempObject.setVelX(0);
 
 }
}
}
}

