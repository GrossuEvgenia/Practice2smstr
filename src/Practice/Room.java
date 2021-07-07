/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practice;

import java.awt.Graphics;
import java.awt.Color;
/**
 *
 * @author User
 */
public class Room {
    
    int floor;          //этаж
    int roomX;          //координата отрисовки омещения по Х
    int roomY;          //координата отрисовки помещения по Y
    int weigh;          //ширина
    int heigh;          //высота
    int Grid[][];
//конструктор класса Комната    
    public Room()
    {
        floor=1+(int)(Math.random()*5);
        weigh=601;
        heigh=501;
        roomX=100;
        roomY=50;
        Grid= new int[weigh][heigh];
        for(int i=0; i<weigh;i++)
        {
            for(int j=0; j<heigh; j++)
            {
                Grid[i][j]=0;
            }
        }
    }

    //геттеры класса Комната
    public int getFloor()
    {
        return floor;
    }
    
    public int getroomX()
    {
        return roomX;
    }
    
    public int getroomY()
    {
        return roomY;
    }
       
    public int getWeigh()
    {
        return weigh;
    }
    
    public int getHeigh()
    {
        return heigh;
    }
    
    public int getGrid(int x, int y)
    {
        return Grid[x][y];
    }
    
    public void setGrid(int x, int y, int v)
    {
        Grid[x][y]=v;
    }
   //Отрисовка помещения 
    public void drawRoom(Graphics g)
    {
        Integer s=floor;
        g.drawString("Этаж:"+s.toString(), roomX-50, roomY-10);
        g.setColor(Color.DARK_GRAY);
        g.drawRect(roomX-1,roomY-1,weigh+1,heigh+1);
        g.setColor(Color.WHITE);
        g.fillRect(roomX,roomY,weigh,heigh);
    }
    
    
}
