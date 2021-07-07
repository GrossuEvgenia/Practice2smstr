/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author User
 */
public class Fire {
 private int [][] FireGrid;            //сетка для распространения огня и воды
 private int sx,sy;                    //начальные координаты огня 
 private int statusFire;               //статус пожара
 private int sizeFlame;                //размер пламени
 private int statusdraw[][];           //статус отрисовки огня на экране
 private int lx,ly; 
  //конструктор класса Огонь
  public Fire( Room room)
  {
      statusFire=0;
      //в зависимости от количества открытых окон устанавливаем размер пламени
         sizeFlame=0;
      FireGrid=new int[room.getWeigh()][room.getHeigh()+5];
      for(int i=0; i<room.getWeigh()-1;i++)
      {
          for(int j=0; j<room.getHeigh()-1;j++)
          {
              FireGrid[i][j]=0;
          }
      }
      statusdraw=new int[room.getWeigh()][room.getHeigh()+5];
      for(int i=0; i<room.getWeigh()-1;i++)
      {
          for(int j=0; j<room.getHeigh()-1;j++)
          {
              statusdraw[i][j]=0;
          }
      }
      sx=400+(int)(Math.random()*(room.getWeigh()-400));
      sy=0+(int)(Math.random()*room.getHeigh());
      lx=sx;
      ly=sy;
      FireGrid[sx][sy]=-1;
     room.setGrid(sx, sy, -1);
              
  }
  //геттеры класса Огонь
  public int getstartX()
  {
      return sx;
  }
  public int getstartY()
  {
      return sy;
  }
  public int getstatusgrid(int x, int y)
  {
      if(x>=0&&x<=600 &&y>=0&&y<=500)
        return FireGrid[x][y];
      return 0;
  }
   public int getstatusdraw(int x, int y)
  {
      if(x>=0&&x<=600 &&y>=0&&y<=500)
         return statusdraw[x][y];
      return 0;
  }
   public int getstatusFire()
   {
       return statusFire;
   }
   public int getLaststX()
   {
     return lx;
   }
    public int getLaststY()
   {
     return ly;
   }
   //функция распространения огня
  private void moveFire(int x, int y, int numberOfopenWindow, Room room)
  {
       if(room.getGrid(x, y)==0)
          return;
      double proc;
      if(numberOfopenWindow>5)
      {
          proc=0.8;
      }
      else proc=0.1;
      //left
      if(y-1>=0&&room.getGrid(x, y-1)==0&&Math.random()<proc)
      {
          room.setGrid(x, y-1, -1);
          FireGrid[x][y-1]=-1;
      }
      //right
      if(y+1<500&&room.getGrid(x, y+1)==0&&Math.random()<proc)
      {
          room.setGrid(x, y+1, -1);
          FireGrid[x][y+1]=-1;
      }
      //top
      if(x-1>=0&&room.getGrid(x-1, y)==0&&Math.random()<proc)
      {
         room.setGrid(x-1, y,-1);
         FireGrid[x-1][y]=-1;
      }
      //high
      if(x+1>=0&&room.getGrid(x+1, y)==0&&Math.random()<proc)
      {
          room.setGrid(x+1, y, -1);
          FireGrid[x+1][y]=-1;
      }

  }
  //функция распростарнения воды
  private void moveWater(int x, int y, int numberOfopenWindow,Room room)
  {
      if(room.getGrid(x, y)==0)
          return;
      double proc;
      if(numberOfopenWindow>5)
      {
          proc=0.6;
      }
      else proc=0.1;
      //left
      if(y-1>=0&&room.getGrid(x, y-1)==-1&&Math.random()<proc)
      {
          room.setGrid(x, y-1, 1);
          FireGrid[x][y-1]=1;
      }
      //right
      if(y+1<500&&room.getGrid(x, y+1)==-1&&Math.random()<proc)
      {
          room.setGrid(x, y+1, 1);
          FireGrid[x][y+1]=1;
      }
      //top
      if(x-1>=0&&room.getGrid(x-1, y)==-1&&Math.random()<proc)
      {
         room.setGrid(x-1, y,1);
         FireGrid[x-1][y]=1;
      }
      //high
      if(x+1>=0&&room.getGrid(x+1, y)==-1&&Math.random()<proc)
      {
          room.setGrid(x+1, y, 1);
          FireGrid[x+1][y]=1;
      }

  }

  
//отрисовка распространения огня или воды
  public void drawFire(int numberofopenWindow, Graphics g,  SaveSystem button, Room room)
  {
      if(numberofopenWindow >4)
      {
          sizeFlame =4;
      }
      else sizeFlame =2;
      for(int x=0; x<room.getWeigh()-1;x++)
       {
           for(int y=0; y<room.getHeigh()-1;y++)
           {
               if(button.getStatus()==false)
               {moveFire(x,y, numberofopenWindow,room);}
               if(button.getStatus()==true)
               {
                   moveWater(x,y, numberofopenWindow,room);
               }
                      
               if(room.getGrid(x, y)==-1)
              
               {   statusFire=1;
              
                    lx=x;
                    ly=y;
                   
                   g.setColor(Color.red);
                   g.fillRect(x+100, y+50, sizeFlame, sizeFlame);
                   statusdraw[x][y]=1;
                  
               }
               else if(room.getGrid(x, y)==1)
            
               {
                   statusFire=0;
                   g.setColor(Color.CYAN);
                   g.fillRect(x+100, y+50,1 ,1);
                   
               }
             
           }
       }
      
  }
}
