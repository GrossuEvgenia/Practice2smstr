/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

import java.awt.Graphics;
import java.awt.Color;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author User
 */
public class Window extends Exit {

   
 private   int strength;                //прочночть пожарной лестницы
    //функция определяющая положнеие двери по Х и Y
    //ранндомно выбирает координату в зависимоти от передаваемого значения
    private void PositionWindow(int ran)
    {
        ArrayList<Integer> list_top = new ArrayList();
        list_top.addAll(Arrays.asList(100,132,164,196,228,260,292,324,356));
        ArrayList<Integer> list_bot= new ArrayList();
        list_bot.addAll(Arrays.asList(304,336,368,400,432,464,496,528,560));
        ArrayList<Integer> list_left = new ArrayList<>();
        list_left.addAll(Arrays.asList(50,82,114,146,178,210,242,274,306));
        ArrayList<Integer> list_right = new ArrayList<>();
        list_right.addAll(Arrays.asList(252,284,316,348,380,412,44,47,508));
        
        int index;
        Random random = new Random();
        switch(ran)
                {
            case 0:
                index= random.nextInt(list_top.size());
                x=list_top.get(index);
                y=50;
                break;
                
            case 1: 
                index = random.nextInt(list_bot.size());
                x=list_bot.get(index);
                y=548;
                break;
            case 2:
                x=100;
                 index = random.nextInt(list_left.size());
                 y=list_left.get(index);  
                break;
            case  3:
                x=700;
                index = random.nextInt(list_right.size());
                y=list_right.get(index);
                break;
                }
       
        
    }
    
  //конструктор класса Окно
   public Window()
   {
       //Рандомно устанавливается статус окна 
       //true-окно с пожарной лестницей
       //false-окно без пожарной лестицы
       if(Math.random()>0.5)
       {
           status=true;
           strength=10;
       }
       else
       {status= false; strength=0;}
       
       
       weigh = 20;
       heigh=2;
       
       //рандомно устанавливаем координаты двери
       int ran =0+(int)(Math.random()*3);
       PositionWindow(ran);
       
   }
   
   //геттеры класса Окно
   public int getwindowX()
   {
       return x;
   }
      public int getwindowY()
   {
       return y;
   }
     
    public boolean getstatusWindow()
    {
        return status;
    }
    public int getStrength()
    {
        return strength;
    }
    //setters
    public void setStrength(int i)
    {
      strength=i;
    } 
    //Функция отрисовки окна
   public void drawWindow(Graphics g)
   {
       if(strength<=0)
       {
           status=false;
       }
       if(status==false)
       {
           g.setColor(Color.blue);
       }
       else {
       g.setColor(Color.MAGENTA);
       }
       if(x==700||x==100 &&(y>50&&y<545) )
       {
            g.fillRect(x,y,heigh,weigh);
            if(x==100)
             g.drawString("Window",x-10,y);
            else
                g.drawString("Window",x+10,y);
       }
       else{ g.fillRect(x,y,weigh,heigh);
       
       if(y==50)
           g.drawString("Window",x,y-10);
       else g.drawString("Window",x,y+10);}
       
   }
}
