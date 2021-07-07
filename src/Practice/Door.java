/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author User
 */
public class Door extends Exit {

    
    //функция определяющая положнеие двери по Х и Y
    //ранндомно выбирает координату в зависимоти от передаваемого значения
    private void PositionDoor(int ran)
    {
        int index;
       
        ArrayList<Integer> list_top = new ArrayList();
        list_top.addAll(Arrays.asList(400,452,504,556));
        ArrayList<Integer> list_bot= new ArrayList();
        list_bot.addAll(Arrays.asList(100,152,204,256));
        ArrayList<Integer> list_left = new ArrayList();
        list_left.addAll(Arrays.asList(340,392,454,506));
        ArrayList<Integer> list_right = new ArrayList();
        list_right.addAll(Arrays.asList(50,102,154,206));
        Random random = new Random();
        switch(ran)
        {
            case 0:
                index=random.nextInt(list_top.size());
                x=list_top.get(index);
                y=50;
                break;
                
            case 1:
                index=random.nextInt(list_bot.size());
                x=list_bot.get(index);
                y=548;
                break;
                
            case 2:
                index=random.nextInt(list_left.size());
                y=list_left.get(index);
                x=100;
                
                break;
                
            case 3:
                index=random.nextInt(list_right.size());
                y=list_right.get(index);
                x=700;
                break;
        }
        
    }
    
   //конструктор класса Дверь   
    public Door()
    {
        
       status =true;             //дверь всегда открыта
       weigh = 45;
       heigh=3;
       //рандомно получаем координату двери на окне
       int ran =0+(int)(Math.random()*3);
       PositionDoor(ran);
    }
    
    //геттеры полей класса Дверь
    public int getPositionX()
    {
        return x;
    }
    public int getPositionY()
    {
        return y;
    }  
    public boolean getstatusDoor()
    {
      return status; 
    }
    
  
    //Функция отрисовки двери на экране
    public void drawDoor(Graphics g)
    {
      
       g.setColor(Color.MAGENTA);
       //определям горизонтальная или вертикальная дверь
       if( (y>50&&y<545)&& x==100|| x==700 )
       {
            g.fillRect(x,y,heigh,weigh);
            if(x==10)
             g.drawString("Door",x-10,y-10);
            else
                g.drawString("Door",x+10,y+10);
       }
       else{ 
           g.fillRect(x,y,weigh,heigh);
            if(y==50)
                     g.drawString("Door",x-10,y-10);
             else g.drawString("Door",x+10,y+10);
       }
    }
}
