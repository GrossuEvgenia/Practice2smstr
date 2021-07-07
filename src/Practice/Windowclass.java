/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

/**
 *
 * @author User
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Timer;

public class Windowclass extends JPanel implements ActionListener {
    
  Room room = new Room();
  Window w= new Window();
  Door d= new Door();
  Fire fire=new Fire(room);
  SaveSystem button= new SaveSystem();
  int startnumberofPeople;
  int diePeople=0;
  int savepeople;
  int hurtPeople=0;
  int numberofWindow;
  int numberofDoor;
  int openWindow=0;
  ArrayList<People> people= new ArrayList();
  ArrayList<Door> door = new ArrayList();
  ArrayList<Window> window = new ArrayList();
  int status=0;
  public static void main(String[] arg)
  {
      Windowclass frame = new Windowclass();
  }
  public void paint(Graphics g)
  {
      Integer tmp, tmp1,tmp2;
      ArrayList<Integer> X= new ArrayList();
      ArrayList<Integer> Y= new ArrayList();
      int[] index=new int[people.size()];
      
      int i=0;
      
      
      super.paintComponent(g);
      room.drawRoom(g);
      button.drawSystem(g);
      for(Window w: window)
      {
          w.drawWindow(g);
      }
      for(Door d: door)
      {
          d.drawDoor(g);
      }
      
     
      
      tmp=startnumberofPeople;
      g.setColor(Color.black);
      g.drawString("Начальное количество людей: "+tmp.toString(), 750,50);
      tmp=diePeople;
      tmp1=0;
      tmp2=savepeople;
      for(People p: people)
      {   
          
          p.drawPeople(g, fire,door,window,room,button);
         if(status==1)
         {
          
         
           p.ClosetoFlame(fire,room);
          if(p.getDie()==1)
          {
              index[i]=1;
              
          }
         else if(p.getHurt()==1)
          {
              index[i]=2;
              tmp1++;
          }
          if(p.getSave()==1)
          {
          X.add((int) p.getPositionPeopleX());
          index[i]=3;
          }
          i++;}
      }
      for(int j=0; j<people.size();j++)
      {
          if(index[j]==1)
          {
              people.remove(j);
              tmp++;
          }
          else if(index[j]==3)
          {
              if(people.get(j).getHurt()==1)
              {
                hurtPeople++;  
              }
              people.remove(j);
              tmp2++;
          }
      }
      
      if(status==1)
      { fire.drawFire(openWindow, g, button,room);}
      if(Math.random()<0.01)
      {
          status=1;
      }
      g.setColor(Color.black);
      if(people.size()!=0)
      {g.drawString("Количество пострадавших: "+tmp1, 750, 70);}
      else{
      g.drawString("Количество пострадавших: "+hurtPeople, 750, 70);
      }
      g.drawString("Количество погибших: "+tmp, 750, 90);
      g.drawString("Количество спасенных: "+tmp2, 750, 110);
      drawSubt(g);
      diePeople=tmp;
      
      savepeople=tmp2;
      numberofSavePeople(X);
      
      
  }
  private void  numberofSavePeople(ArrayList<Integer> X)
  {
   ArrayList<Integer> numberOf= new ArrayList<>();
   numberOf.addAll(X);
   //int i=0; 
   for(Window w: window)
   {
       for(int i=0; i<numberOf.size();i++)
       {  if(w.getwindowX()==numberOf.get(i))
       {
           w.setStrength(w.getStrength()-1);
       }}
      // i++;
   }
   
  }
  private void drawSubt(Graphics g)
  {
      g.drawString("Пояснения:", 750, 150);
      g.drawString("Зеленый-спокойствие", 750, 170);
      g.drawString("Оранжевый-паника", 750, 190);
      g.drawString("Фиолетовая дверь-открытая", 750, 210);
      g.drawString("Фиолетовое окно-окно с пожарной лестницей", 750, 230);
      g.drawString("Синие окно-окно без пожарной лестници", 750, 250);
  }
  public Windowclass()
  {
      JFrame frame = new JFrame("Simulation");
      frame.setSize(1050,600);
      
       
       
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startnumberofPeople=10+(int)(Math.random()*70);
        numberofWindow=1+(int)(Math.random()*9);
        numberofDoor=2+(int)(Math.random()*4);
        for(int i=0; i<numberofWindow; i++)
        {
            window.add(new Window());
                    if(window.get(i).getstatusWindow()==true)
                openWindow++;
        }
             for(int i=0; i<numberofDoor; i++)
        {
            door.add(new Door());
            
        }
        for(int i=0; i<startnumberofPeople; i++)
        {
            people.add(new People(door.get(0)));
        }
        
       
        
        
        Timer t= new Timer(16, this);
        
          t.restart();
       
        
        frame.add(this);
        frame.setVisible(true);
  }

    @Override
    public void actionPerformed(ActionEvent e) {
       repaint();
    }
    
}
