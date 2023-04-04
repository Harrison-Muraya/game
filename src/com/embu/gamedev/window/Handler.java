
package com.embu.gamedev.window;
import java.awt.Graphics;
import java.util.LinkedList;
import com.embu.gamedev.framework.GameObject;
import com.embu.gamedev.framework.ObjectId;
import com.embu.gamedev.objects.Block;


public class Handler {
 public LinkedList<GameObject> object=new LinkedList<GameObject>();
private GameObject tempObject;
public void tick(){
for(int i=0;i<object.size();i++){
tempObject=object.get(i);
tempObject.tick(object);
}
}
public void render(Graphics g){    
for(int i=0;i<object.size();i++){
tempObject=object.get(i);
tempObject.render(g);
 }
}
public void addObject(GameObject object){
this.object.add(object);
}
public void removeObject(GameObject object){
this.object.remove(object);
}


}

