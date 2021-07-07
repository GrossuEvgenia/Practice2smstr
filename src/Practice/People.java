/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author User
 */
public class People {
   //статус человека
 private    int calm;
 private   int hero;
 private   int panic;
 private   int die;
 private   int hurt;
 private   int save;
   //коорлинаты
private    double x;
private    double y;
    //координаты для задания направления
private    int saveX;
private    int saveY;
private    int xExit;
private    int yExit;
    //скорость
private    int vx,vy;




//конструктор класса Человек
    public People(Door door)
    {
        //рандомным образом определям свойства
        x=100+(Math.random()*200);
        y=50+(Math.random()*150);
        vx=(int)(Math.random()*(10+1)+-5);
        vy=(int)(Math.random()*(10+1)+-5);
        calm=0+(int)(Math.random()*100);
        panic=0+(int)(Math.random()*100);
        if(calm>=panic)
        { hero=0+(int)(Math.random()*100);}
        else hero=0;
        die=0;
        hurt=0;
        save=0;
        saveX=door.getPositionX()-(int)x;
        saveY=door.getPositionY()-(int)y;
    }

    //геттеры класса Человек
    public double getPositionPeopleX()
    {
        return x;
    }
    public double getPositionPeopleY()
    {
        return y;
    }
    public int getDie()
    {
        return die;
    }
    public int getHurt()
    {
        return hurt;
    }
    public int getSave()
    {
        return save;
    }

    //Функция определяющая близость к огню
    public void ClosetoFlame(Fire fire, Room room)
    {
        for(int xFlame=0; xFlame<room.getWeigh()-1; xFlame++)
        {
            for(int yFlame=0;yFlame<room.getHeigh()-1;yFlame++){

       if(fire.getstatusgrid((int)(x-100),(int)(y-50))==-1 && fire.getstatusdraw((int)(x-100), (int)(y-50))==1)
           {

                 die=1;
                 break;
             }

   if(Math.sqrt(Math.pow(x-(xFlame+100), 2)+Math.pow(y-(yFlame+50), 2))<15&&fire.getstatusgrid(xFlame, yFlame)==-1 &&fire.getstatusdraw(xFlame,yFlame)==1)  
   {
             hurt=1;
                break;
            }
       
            }
        }
    }

   //Функция выбирающая путь для человека
   //если человек паникует, то выбирает лиюо перое попавшееся окно, либо первую попавшуюся дверь в зависимоти от уровня паники
  //если человек спокоен, то он выьирает ближайшую к нему дверь
    private void findWay(ArrayList<Door> door, ArrayList<Window> window,Fire fire)
    {
       
      if(panic>calm)
      {

          if(panic<50)
          {

                saveX=door.get(0).getPositionX()-(int)x;
                saveY=door.get(0).getPositionY()-(int)y;
                xExit=door.get(0).getPositionX();
                yExit=door.get(0).getPositionY();

           }

        else{
              Random random = new Random();
              int index=random.nextInt(window.size());
              saveX=window.get(0).getwindowX()-(int)x;
              saveY=window.get(0).getwindowY()-(int)y;
              xExit=window.get(0).getwindowX();
              yExit=window.get(0).getwindowY();
             
                 
          }

      }
   else{
          
       
        
          
        
       for(int i=0; i<door.size();i++)
    {   
        
        if(Math.abs((int)x-door.get(i).getPositionX())<Math.abs(saveX) && Math.abs(fire.getLaststY()-door.get(i).getPositionX())>15
                ||Math.abs((int)y-door.get(i).getPositionY())<Math.abs(saveY)&& Math.abs(fire.getLaststY()-door.get(i).getPositionY())>15)
        {      
                 saveX=door.get(i).getPositionX()-(int)x;
                 saveY=door.get(i).getPositionY()-(int)y;
                 xExit=door.get(i).getPositionX();
                 yExit=door.get(i).getPositionY();
                 
              }
          
            }
       
        for(int i=0; i<window.size();i++)
       {   if(window.get(i).getstatusWindow()==true &&window.get(i).getStrength()>0)
             {
                if(Math.abs((int)x-window.get(0).getwindowX())<Math.abs(saveX) && Math.abs(fire.getLaststY()-window.get(0).getwindowX())>15
                ||Math.abs((int)y-window.get(0).getwindowY())<Math.abs(saveY)&& Math.abs(fire.getLaststY()-window.get(0).getwindowY())>15)
           
           {
                 
                 saveX=window.get(i).getwindowX()-(int)x;
                 saveY=window.get(i).getwindowY()-(int)y;
                 xExit=window.get(i).getwindowX();
                 yExit=window.get(i).getwindowY();
            }

       }
       
       
       
       }
        
        
      }
       
    }

    //для понимания близости к выходу
    private int CloseToExitOrButton(double x1, double y1, int x2, int y2)
    {
        if(Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2))<8)
             return 1;
       
         return 0;
    }
    //отрисовка человека и его поведения
    public void drawPeople(Graphics g, Fire fire,ArrayList<Door> door, ArrayList<Window> window,Room room , SaveSystem button)
    {


       
    //в зависимости от статуса устанавливаем цвет
     if(panic>calm&& fire.getstatusFire()==1)
     {
         g.setColor((Color.ORANGE));
     }
     else if(calm>=panic || button.getStatus()==true||fire.getstatusFire()!=1)
     {g.setColor(Color.GREEN);}
     if(hurt==1)
     {
         g.setColor(Color.YELLOW);
     }



    int close=CloseToExitOrButton((int)x,(int)y,xExit,yExit);
    if(close==1)
    {
        if(window.get(0).getwindowX()==xExit &&window.get(0).getwindowY()==yExit &&window.get(0).getstatusWindow()==false && room.getFloor()>2)
       {
           die=1;
       }
       else   save=1;
    }
    
     g.fillOval((int)x, (int)y, 6,6 );
     //поведение если начался пожар
     if(fire.getstatusFire()==1)
     {
         //поведение человека с большим уровнем героизма
         if(hero>=97)
   {

       if((int)x!=(button.getX()-x )&&x>=100&&x<700)
     {
         if((button.getX()-x)>0)
            x+=1;
         else x-=1;
     }
       else{
           x=button.getX();}
     if((int)y!=(button.getY()-y)&&y>=50&&y<545)
     {
        if((button.getY()-y)>0)
            y+=1;
         else y-=1;
     }
     else{y=button.getY();}


 close=CloseToExitOrButton((int)x,(int)y,button.getX(),button.getY());
    if(close==1)

     {
         button.setStatus(true);
     }
         }
     //поведение спокойного человека
      else if(calm>=panic||panic<70 )
         {
             findWay(door, window, fire); 
         if((int)x!=xExit &&x>99&&x<700)
     {
         
         if(saveX>0)
            x+=1;
         else x-=1;
     }
         else if(y!=0){
             x=xExit;}
     if((int)y!=yExit&&y>45&&y<545)
     {
        if(saveY>0)
            y+=1;
         else y-=1;
     }
     else if(x!=0){
         y=yExit;}

      if(x<100||x>=690)
     {

         x-=1;
     }
     if(y<50||y>555)
     {
         y-=1;
     }
         }
      
      //паника человека
     else{
        
             x+=vx*2;
             y+=vy*2;

     if(x<100||x>=690)
     {
         vx*=-1;
     }
     if(y<50||y>=540)
     {
         vy*=-1;
     }

        }


        

     }
     //поведение если пожара нет
     else{
     x+=vx;
     y+=vy;

     if(x<100||x>=690)
     {
         vx*=-1;
     }
     if(y<50||y>=540)
     {
         vy*=-1;
     }
     }
    }


}
