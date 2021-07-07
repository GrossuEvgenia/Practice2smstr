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
public class SaveSystem {
    
    int x;                      //координата кнопки по оси Х
    int y;                      //координата кнопки по оси Y
    int size;                   //размер кнопки
    boolean status;             //статус
    
    //конструктор для класса Спасательная система
   public SaveSystem()
   {
       x=110+(int)(Math.random()*400);
       y=55+(int)(Math.random()*425);
       size=5;
       status=false;
   }
   
   //сеттер статуса
   public void setStatus(boolean st)
   {
       status=st;
   }
    //геттеры класса Спасательная система
   public boolean getStatus()
   {
    return status;
   }
   public int getX()
   {
    return x;
   }
   public int getY()
   {
     return y;
   }
   
   //Отрисовка кнопки спасательной системы
   public void drawSystem(Graphics g)
   {
       if(status ==false)
          g.setColor(Color.pink);
       else g.setColor(Color.CYAN);
       g.fillRect(x, y, size, size);
       g.drawString("Button",(int)( x-1),(int)(y-1));
   }
   
}
