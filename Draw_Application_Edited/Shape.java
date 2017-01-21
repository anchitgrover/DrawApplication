/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawapplication;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Anchit Grover
 */
public abstract class Shape  //abstract class shape with sub classes for line, rectangle and oval
{
    private int x1; //coordinates on the panel of the mouse
    private int x2;
    private int y1;
    private int y2;
    private Color myColor1,myColor2; //2 different colors
    private boolean isFilled;  //to see if check box is selected or not
    private boolean isGradient; 
    private boolean isDashed;
    private int width; //value of textfield width
    private float length[]; //value of length textfield
    
        
    public Shape() //default constructor
    {
       x1 = 0;
       x2 = 0;
       y1 = 0;
       y2 = 0;
       myColor1 = Color.BLACK;
       myColor2 = Color.BLACK;
    }
        
    public Shape(int x1, int x2, int y1, int y2, Color color1, Color color2,boolean filled, boolean gradient,float len,boolean dash,int w) //overloaded constructor
    {
        setX1(x1);
        setY1(y1);
        setX2(x2);
        setY2(y2);
        setColor1(color1);
        setColor2(color2);
        setFilled(filled);
        setGradient(gradient);
        setDashLen(len);
        setDashed(dash);
        setLineWidth(w);
    }
    //setters and getters for constructors
    public void setX1(int x1)
    {
        this.x1 = x1;
    }

    public int getX1() 
    {
        return x1;
    }

    public void setX2(int x2)
    {
        this.x2 = x2;
    }

    public int getX2()
    {
        return x2;
    }
    public void setY1(int y1)
    {
        this.y1 = y1;
    }
    public int getY1()
    {
        return y1;
    }

    public void setY2(int y2)
    {
        this.y2 = y2;
    }

    public int getY2()
    {
        return y2;
    }

    public void setColor1(Color color)
    {
        myColor1 = color;
    }

    public Color getColor1()
    {
        return myColor1;
    }
    
    public void setColor2(Color color)
    {
        myColor2 = color;
    }
    
    public Color getColor2()
    {
        return myColor2;
    }
    
    public void setFilled(boolean filled)
    {
        isFilled = filled;
    }
    
    public boolean getFilled()
    {
        return isFilled;
    }
    
    
    public void setGradient(boolean x)
    {
        isGradient = x;
    }
    
    public boolean getGradient()
    {
        return isGradient;
    }
    
    public void setDashed(boolean x)
    {
        isDashed = x;
    }
    public void setDashLen(float len)
    {
        float dashed[]= {len};
        this.length = dashed;
    }
    
    public float[] getDashedLen()
    {
        return this.length;
    }
    
    public boolean getDashed()
    {
        return isDashed;
    }
    
    public void setLineWidth(int x)
    {
        width = x;
    }
    
    public int getLineWidth()
    {
        return width;
    }
        
    public int getLength()
    {
        return width;
    }
            

    public abstract void draw(Graphics2D g); //abstract function later implemented in subclasses
}

class Line extends Shape  //subclass of shape
{
    public Line() //constructor
    {
        super();
    }

    public Line(int x1, int x2, int y1, int y2, Color color1, Color color2, boolean x,boolean y,float f,boolean d,int w) //overloaded constructor
    {
        super(x1,x2,y1,y2,color1,color2,x,y,f,d,w);
    }

    public void draw(Graphics2D g) //draw method to draw the line
    {
        if(getGradient()==true) //checks to see if gradiend check box is selected
        {
            g.setPaint(new GradientPaint(0,0,getColor1(),50,50,getColor2(),true));
        }
        
        else 
        {
             g.setColor(getColor1());
        }
        
        if(getDashed()==true) //checks to see if dashed box is selected
        {
            g.setStroke(new BasicStroke(getLineWidth(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,getDashedLen(),0.0f));
        }
        
        else 
        {
            g.setStroke(new BasicStroke(getLineWidth()));
        }
       

        g.drawLine(getX1(), getY1(), getX2(), getY2());  
    }
}
class Oval extends Shape  //sub class
{
    public Oval() //constructor
    {
        super();
    }
    
    public Oval(int x1,int x2, int y1, int y2, Color firstColor,Color secondColor,  boolean x,boolean y,float f,boolean d,int w) //overloaded constructor
    {
        super(x1,x2,y1,y2,firstColor,secondColor,x,y,f,d,w);
    }
    public void draw(Graphics2D g) //method to draw oval
    {    
        if(getDashed()==true) //checks to see if dashed is selected
        {
            g.setStroke(new BasicStroke(getLineWidth(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,getDashedLen(),0.0f));
        }
        
        else
        {
            g.setStroke(new BasicStroke(getLineWidth()));
        }
         
        if(getGradient()==true) //checks to see if gradient is selected
        {
            g.setPaint(new GradientPaint(0,0,getColor1(),50,50,getColor2(),true));
        }
        
        else 
        {
             g.setColor(getColor1());
        }
        
        if(getFilled()==false) //checks to see if filled is slected
        {    
            g.drawOval(Integer.min(getX1(), getX2()), Integer.min(getY1(), getY2()), Integer.max(getX1(), getX2())-Integer.min(getX1(), getX2()),Integer.max(getY1(),getY2()-Integer.min(getY1(), getY2())));
        }
        else
        {
            g.fillOval(Integer.min(getX1(), getX2()), Integer.min(getY1(), getY2()), Integer.max(getX1(), getX2())-Integer.min(getX1(), getX2()),Integer.max(getY1(),getY2()-Integer.min(getY1(), getY2())));
        }
    }
}

class Rectangle extends Shape //subclass of shape
{
    public Rectangle() //constructor
    {
        super();
    }
    
    public Rectangle(int x1,int x2, int y1, int y2, Color color1, Color color2, boolean filled,boolean gradient,float f,boolean d,int w) //overloaded constructor
    {
        super(x1,x2,y1,y2,color1,color2,filled,gradient,f,d,w);
    }
    
    public void draw(Graphics2D g) //method to draw rectangle
    {
        
        if(getDashed()==true) //checks to see if dashed is selected
        {
            g.setStroke(new BasicStroke(getLineWidth(),BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,getDashedLen(),0.0f));
        }
        
        else
        {
            g.setStroke(new BasicStroke(getLineWidth()));
        }

        if(getGradient()==true) //checks to see if gradient is selected
        {
            g.setPaint(new GradientPaint(0,0,getColor1(),50,50,getColor2(),true));
        }
        
        else 
        {
             g.setColor(getColor1());
        }
        
        if(getFilled()==true) //checks to see if filled is selected
        {
            g.fillRect(Integer.min(getX1(), getX2()), Integer.min(getY1(), getY2()), Integer.max(getX1(), getX2())-Integer.min(getX1(), getX2()),Integer.max(getY1(),getY2()-Integer.min(getY1(), getY2())));
        }
        
        else 
        {
            g.drawRect(Integer.min(getX1(), getX2()), Integer.min(getY1(), getY2()), Integer.max(getX1(), getX2())-Integer.min(getX1(), getX2()),Integer.max(getY1(),getY2()-Integer.min(getY1(), getY2())));
        }        
    }
    
    
}
