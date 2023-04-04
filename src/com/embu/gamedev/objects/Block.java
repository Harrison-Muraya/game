/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.embu.gamedev.objects;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import com.embu.gamedev.framework.GameObject;
import com.embu.gamedev.framework.ObjectId;
import com.embu.gamedev.framework.Texture;
import com.embu.gamedev.window.Game;
public class Block extends GameObject{
 Texture tex = Game.getInstance();
    private int type;
public Block(float x, float y,int type, ObjectId id) {
super(x, y, id);
 this.type = type;
}

public void tick(LinkedList<GameObject> object) {
}
public void render(Graphics g) {
if(type==0)//dirt block
g.drawImage(tex.block[0],(int)x,(int)y,null);
if(type==0)//grass block
g.drawImage(tex.block[1],(int)x,(int)y,null);
}
public Rectangle getBounds() {
return new Rectangle((int)x,(int)y,32,32);
}
}

