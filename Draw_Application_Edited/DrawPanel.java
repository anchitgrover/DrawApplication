/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawapplication;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anchit Grover
 */ 
public class DrawPanel extends JPanel  //draw panel used in the j frame to draw shapes
{    
    private ArrayList<Shape> shapes; //shapes in an array for easy erase and delete drawing
    private int shapeIndex;
    public Shape currentShape;
    public Color currentColor1= Color.BLACK;
    public Color currentColor2= Color.BLACK;
    private boolean filledShape=false;
    private boolean gradientShape=false;
    private boolean dashedShape = false;
    private JLabel statusLabel;
    private float dashed=10.f;
    private int width;
    
   
    public DrawPanel() //constructor
    {
        shapes = new ArrayList<Shape>(5); 
        
        //default "settings"
        setFilledShape(false);
        setGradientShape(false);
        currentShape = null;

        setBackground(Color.WHITE); //change the background color of the panel
        setSize(500,500); //set the size of the panel

        mouseHandler mouseHandler1 = new mouseHandler(); //mouse handlers used for drawing on the panel
        addMouseListener(mouseHandler1);
        addMouseMotionListener(mouseHandler1);   
        
        statusLabel = new JLabel("(0,0)"); //shows the curser value
        statusLabel.setOpaque(true);
        add(statusLabel,BorderLayout.SOUTH);
        
        
    }

    public void paintComponent(Graphics g) //method to draw
    {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;

        for(int i = 0; i<shapes.size();i++)
        {
            shapes.get(i).draw(g2d); 
        }
        if(currentShape != null)
        {
            currentShape.draw(g2d);
        }
    }
    
    //setters and getters for shapes
    public void setDashedShape(boolean x)
    {
        dashedShape = x;
    }
    
    public boolean getDashedShape()
    {
        return dashedShape;
    }
    public void setDashedLength(float x)
    {
        dashed = x;
    }
    
    public float getDashedLength()
    {
        return dashed;
    }
    
    public void setLineWidth(int x)
    {
        width = x;
    }
    
    public int getLineWidth()
    {
        return width;
    }

    public void setShapeIndex(int x)
    {
        this.shapeIndex = x;
    }

    public void setFilledShape(boolean filled)
    {
        this.filledShape = filled;
    }
    
    public boolean getFilledShape()
    {
        return this.filledShape;
    }
    
    public void setGradientShape(boolean gradient)
    {
        this.gradientShape = gradient;
    }
    
    public boolean getGradient()
    {
        return this.gradientShape;
    }

    public void setCurrentColor1(Color color)
    {
        this.currentColor1 = color; 
    }
        
    public Color getCurrentColor1()
    {
        return currentColor1;
    }
    
    public void setCurrentColor2(Color color)
    {
        this.currentColor2 = color;
    }
    
    public Color getCurrentColor2()
    {
        return this.currentColor2;
    }

    public void clearLastShape() //removes the last item from the painting
    {
        if(shapes.size()!=0)
        {
            shapes.remove(shapes.size()-1);
        }
        
        else
        {
            shapes.clear();
        }
        repaint();
    }

    public void clearDrawing() //removes everything from the painting
    {
        shapes.removeAll(shapes);
        repaint();
    }

    private class mouseHandler extends MouseAdapter implements MouseMotionListener //handler for what the mouse does on the panel
    {
        @Override
        public void mousePressed(MouseEvent event)
        {
            //System.out.println("asdlj");
            switch(shapeIndex)
            {
               //what shape is being used
                case 0:
                            currentShape = new Oval(event.getX(),event.getX(),event.getY(),event.getY(),getCurrentColor1(),getCurrentColor2(),filledShape,getGradient(),getDashedLength(),getDashedShape(),getLineWidth());
                            break;   
                case 1:
                            currentShape = new Line(event.getX(),event.getX(),event.getY(),event.getY(),getCurrentColor1(),getCurrentColor2(),filledShape,getGradient(),getDashedLength(),getDashedShape(),getLineWidth());
                            break;
                case 2:
                            currentShape = new Rectangle(event.getX(),event.getX(),event.getY(),event.getY(),getCurrentColor1(),getCurrentColor2(),filledShape,getGradient(),getDashedLength(),getDashedShape(),getLineWidth());
                            break;
                default:
                            currentShape = new Oval(event.getX(),event.getX(),event.getY(),event.getY(),getCurrentColor1(),getCurrentColor2(),filledShape,gradientShape,getDashedLength(),getDashedShape(),getLineWidth());
            }
                        
        }
        @Override
        public void mouseReleased(MouseEvent event)
        {
            if(currentShape!=null)
            {
                currentShape.setX2(event.getX());
                currentShape.setY2(event.getY());

                shapes.add(currentShape);
                
                currentShape = null;
                repaint();
            }
        }

        @Override
        public void mouseDragged(MouseEvent event)
        {
            currentShape.setX2(event.getX());
            currentShape.setY2(event.getY());
            statusLabel.setText("("+event.getX()+","+event.getY()+")");
            repaint();
            
        }
        
        @Override
        public void mouseMoved(MouseEvent event)
        {
            statusLabel.setText("("+event.getX()+","+event.getY()+")");
        }
    }

}
