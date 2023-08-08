/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.anautumnmemoir;

/**
 *
 * @author tarun
 */

import javax.swing.*;
import java.awt.*;
import com.mycompany.anautumnmemoir.*;



public class AnAutumnMemoir {
    
    public static Color blue = new Color(140, 26, 255);
    public static Color lblue = new Color(77, 77, 255);
    public static Color llblue = new Color(26, 198, 255);
    public static Color dblue = new Color(31, 31, 122);
    public static Color green = new Color(141, 135, 65);
    public static Color mBrown = new Color(218, 173, 134);
    public static Color dBrown = new Color(188, 152, 106);
    public static Color lBrown = new Color(251, 238, 193);
    
    public static Color purple = new Color(255, 128, 255);
    public static Color white = new Color(255, 255, 255, 130);
    
    public static int width = 0;
    public static int height = 0;

    public static void main(String[] args) {
        GridBagLayout grid = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        MakeConnection con = new MakeConnection();
        
//        JFrame f = new JFrame("An Autumn Memoir");
//        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        LoginPage lp = new LoginPage(grid, gbc, con);
//        new SignUp(grid, gbc, con, 0);
//        SignUp su = new SignUp(f, grid, gbc);
//        HomePage hp =  new HomePage(f);
        
//        f.revalidate();
        
//        JFrame f1 = new JFrame("An Autumn Memoir");
//        f1.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        f1.setVisible(true);
//        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        LoginPage lp = new LoginPage(f, grid, gbc);
//        SignUp su = new SignUp(grid, gbc, con);
//        HomePage hp =  new HomePage(f);
        
//        f1.revalidate();
        
//        JFrame f2 = new JFrame("An Autumn Memoir");
//        f2.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        f2.setVisible(true);
//        f2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
//        LoginPage lp = new LoginPage(f, grid, gbc);
//        SignUp su = new SignUp(f, grid, gbc);
//        HomePage hp =  new HomePage(grid, gbc);
        
//        f2.revalidate();




//        LoginPage login = new LoginPage();
        
//        JFrame f = new JFrame("app");
        
//        SignUp s = new SignUp();
//        HomePage h = new HomePage(f);
        
//        f.getContentPane().setLayout(new FlowLayout());
//        Container cp = f.getContentPane();
//        cp.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.weighty = 1;
//        gbc.weightx = 1;
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.fill = GridBagConstraints.NORTH;
//        gbc.anchor = GridBagConstraints.NORTH;
//        cp.add(h.getPanel(), gbc);
//        
//        gbc.fill = GridBagConstraints.NORTH;
//        gbc.anchor = GridBagConstraints.WEST;
//        cp.add(new JLabel("hello"), gbc);
        //f.getContentPane().setLayout(new FlowLayout());
        //f.getContentPane().add(h.getPanel());
        
//        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        f.setVisible(true);
//        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
