/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package drawapplication;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.JColorChooser;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
/**
 *
 * @author Anchit Grover
 */
public class DrawApplication
{
    public static class firstFrame extends JFrame  //frame that contains all buttons panel and draw panel
    {
        private JPanel buttons,buttons2,together; //panels used for the buttons
        private DrawPanel dp; //panel for drawing
        
        //buttons and labels
        private JButton undoButton; 
        private JButton clearButton;
        private JCheckBox filled,gradient,dashed;
        private JComboBox shape;
        private JLabel lbltxt;
        private JLabel label,width,length;
        private JButton changeColorButton1, changeColorButton2;
        private Color color = Color.LIGHT_GRAY;
        private JTextField tf1,tf2; 
        
        
        public firstFrame()
        {
            super("2d application");
            setLayout(new BorderLayout());
            
            //panels
            buttons = new JPanel(new FlowLayout());
            buttons2 = new JPanel(new FlowLayout());
            
            
            //buttons
            undoButton = new JButton("Undo");  
            buttons.add(undoButton);
            
            clearButton = new JButton("Clear");
            buttons.add(clearButton);
            
            ButtonHandler undoHandler = new ButtonHandler();
            ButtonHandler clearHandler = new ButtonHandler();
            undoButton.addActionListener(undoHandler);
            clearButton.addActionListener(clearHandler);
                                  
            label = new JLabel("Shape:");
            buttons.add(label);
            
            String[] dropDown = {"Oval", "Line", "Rectangle"};          
            lbltxt = new JLabel();
            shape = new JComboBox(dropDown);
            dropMenuHandler dh = new dropMenuHandler();
            shape.addActionListener(dh);
            buttons.add(shape);
            buttons.add(lbltxt);          
            
            filled = new JCheckBox("Filled");
            buttons.add(filled);
            checkBoxHandler cbh = new checkBoxHandler();
            filled.addItemListener(cbh);
            
            gradient = new JCheckBox("Gradient");
            gradient.addItemListener(new checkBoxHandler());
            buttons2.add(gradient);
            
            
            changeColorButton1 = new JButton("1st Color");
            changeColorButton1.addActionListener(
            new ActionListener() //handler for colors
            {
                public void actionPerformed(ActionEvent e)
                {
                    color = JColorChooser.showDialog(
                    firstFrame.this,"Choose a color", color);
                    if(color == null)
                        dp.setCurrentColor1(Color.BLACK);
                    else
                        dp.setCurrentColor1(color);
                }
            }
            );
            
            buttons2.add(changeColorButton1);         
            
            changeColorButton2 = new JButton("2nd Color");
            changeColorButton2.addActionListener(
            new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    color = JColorChooser.showDialog(
                    firstFrame.this,"Choose a color", color);
                    
                        //dp.setCurrentColor2(Color.BLACK);
                        dp.setCurrentColor2(color);
                }
            }
            );
            
            buttons2.add(changeColorButton2); 
            
            //textbox field 
            width = new JLabel("Line Width:");
            buttons2.add(width);
            tf1 = new JTextField(2);
            buttons2.add(tf1);
            tf1.addActionListener(new ActionListener() //action listener for text field
            {
                public void actionPerformed(ActionEvent e)
                {
                    dp.setDashedLength(Float.parseFloat(tf1.getText()));               
                }
            });
            
            
            length = new JLabel("Dash Length:");
            buttons2.add(length);
            
            tf2 = new JTextField(2);
            buttons2.add(tf2);
            
            
            tf2.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    dp.setLineWidth(Integer.parseInt(tf2.getText()));
                }
            });
            
            
            dashed = new JCheckBox("Dashed"); //check box
            dashed.addItemListener(new checkBoxHandler());
            buttons2.add(dashed);
            
            together = new JPanel(new GridLayout(2,1));
            together.add(buttons);
            together.add(buttons2);
           
            add(together,BorderLayout.NORTH);
            
            dp = new DrawPanel();
            add(dp,BorderLayout.CENTER);        
        }
        
        
        private class checkBoxHandler implements ItemListener  //handlers for checkbox
        {
            @Override
            public void itemStateChanged(ItemEvent event)
            {
                if(event.getSource()==filled)
                {
                    if(filled.isSelected())
                    {
                        dp.setFilledShape(true);
                    }
                    
                    else
                    {
                        dp.setFilledShape(false);
                    }
                }
                
               if(event.getSource()==gradient)
                {
                    if(gradient.isSelected())
                    {
                    dp.setGradientShape(true);
                    }
                    else
                    {
                        dp.setGradientShape(false);
                    }                    
                }
               
               if(event.getSource()==dashed)
               {
                   if(dashed.isSelected())
                   {
                       dp.setDashedShape(true);
                   }
                   else
                   {
                       dp.setDashedShape(false);
                   }
               }  
            }
        }
    
        private class dropMenuHandler implements ActionListener  //drop menu handler for the different shapes
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==shape)
                {
                    dp.setShapeIndex(shape.getSelectedIndex());
                }
        }
    }

    private class ButtonHandler implements ActionListener //button handler for clear and undo
    {
        public void actionPerformed(ActionEvent event)
        {
            if(event.getSource()==clearButton)
                dp.clearDrawing();
            if(event.getSource()==undoButton)
                dp.clearLastShape();
        }            
    } 
    
}            
    
        
    public static void main(String[] args) 
    {
        firstFrame topFrame = new firstFrame();
        topFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        topFrame.setSize(1000,500);
        topFrame.setVisible(true);
    }
    
}
