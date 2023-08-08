/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.anautumnmemoir;

/**
 *
 * @author tarun
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.mycompany.anautumnmemoir.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class LoginPage extends JFrame{
    JFrame f;
    
    JLabel mainPanel;
    JPanel titlePanel, bottomPanel;
    
    GridBagLayout grid;
    GridBagConstraints gbc;
    
    JLabel lblTitle, lblUsername, lblPassword, lblForgetPassword, lblNoAccount, lblCreateAccount;
    
    JTextField txtUsername;
    JPasswordField txtPassword;
    
    JButton btnSubmit, btnClear;
    
    Font titleFont, controlFont, smallFont, mediumFont;
    
    MakeConnection con;
    
    int id;
    
    LoginPage(GridBagLayout grid, GridBagConstraints gbc, MakeConnection con){
        this.f = this;
        this.grid = grid;
        this.gbc = gbc;
        this.con = con;
        
        id = -1;
        
        setFrame();
        
        createPanels();
        
        this.f.setContentPane(mainPanel);
        
        createFonts();
        
        createControls();
        
        addControls();
        
        eventHandling();
        
        setColors();
        
        f.revalidate();
    }
    
    public final void setFrame(){
        f.setTitle("An Autumn Memoir : Login");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }
    
    public final void  createFonts(){
        controlFont = new Font("Lucida Handwriting", Font.BOLD, 25);
        titleFont = new Font("Vladimir Script", Font.BOLD, 150);
        smallFont = controlFont.deriveFont(16f);
        mediumFont = controlFont.deriveFont(20f);
    }
    
    public final void createPanels(){
        mainPanel = new JLabel();
        mainPanel.setLayout(grid);
        titlePanel = new JPanel();
        titlePanel.setLayout(grid);
        bottomPanel = new JPanel();
        bottomPanel.setLayout(grid);
        
        titlePanel.setOpaque(false);
        bottomPanel.setOpaque(false);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(titlePanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.weighty = 5;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTH;
        mainPanel.add(bottomPanel, gbc);
        
    }
    
    public final void createControls(){
        lblTitle = new JLabel("An Autumn Memoir");
        lblTitle.setFont(titleFont);
        
        lblUsername = new JLabel("Username : ");
        lblUsername.setFont(controlFont);
        
        lblPassword = new JLabel("Password : ");
        lblPassword.setFont(controlFont);
        
        txtUsername = new JTextField();
        txtUsername.setFont(controlFont);
        txtUsername.setColumns(7);
        
        txtPassword = new JPasswordField();
        txtPassword.setFont(controlFont);
        txtPassword.setColumns(7);
        
        btnSubmit = new JButton("Login");
        btnSubmit.setFont(controlFont);
        
        btnClear = new JButton("Clear");
        btnClear.setFont(controlFont);
        
        lblForgetPassword = new JLabel("ForgetPassword");
        lblForgetPassword.setFont(smallFont);
        lblForgetPassword.setForeground(Color.BLUE);
        lblForgetPassword.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
        lblNoAccount = new JLabel("Don't have an account?");
        lblNoAccount.setFont(mediumFont);
        
        lblCreateAccount = new JLabel("Create new!!!");
        lblCreateAccount.setFont(mediumFont);
        lblCreateAccount.setForeground(Color.BLUE);
        lblCreateAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    public final void addControls(){
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0; gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.NONE;
        titlePanel.add(lblTitle, gbc);
        
        gbc.insets = new Insets(5, 5, 5, 5);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.weighty = 0;
        
        gbc.gridx = 0; gbc.gridy = 0;
        bottomPanel.add(lblUsername, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        bottomPanel.add(txtUsername, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        bottomPanel.add(lblPassword, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        bottomPanel.add(txtPassword, gbc);
        
        gbc.insets = new Insets(25, 5, 25, 5);
        
        
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridx = 0; gbc.gridy = 2;
        bottomPanel.add(btnSubmit, gbc);
        
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 1; gbc.gridy = 2;
        bottomPanel.add(btnClear, gbc);
        
        gbc.insets = new Insets(0, 0, 10, 0);
        
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        bottomPanel.add(lblForgetPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        
        gbc.insets = new Insets(80, 0, 0, 0);
        bottomPanel.add(lblNoAccount, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        gbc.gridwidth = 2;
        
        gbc.insets = new Insets(4, 0, 0, 0);
        bottomPanel.add(lblCreateAccount, gbc);
        
    }

    public final void eventHandling(){
        
        btnSubmit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(validation()){
                    System.gc();
                    new HomePage(grid, gbc, con, id, txtUsername.getText());
                    f.dispose();
                }
            }
        });
        
        lblCreateAccount.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                System.gc();
                new SignUp(grid, gbc, con, 0);
                f.dispose();
            }
        });
        
        btnClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                txtUsername.setText("");
                txtPassword.setText("");
                
//                btnClear.setFocusPainted(false);
//                btnClear.setMargin(new Insets(0,0,0,0));
//                btnClear.setContentAreaFilled(false);
//                btnClear.setBorderPainted(false);
//                btnClear.setOpaque(false);
            }
        });
        
        lblForgetPassword.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                System.gc();
                if(txtUsername.getText().compareTo("") == 0){
                    JOptionPane.showMessageDialog(f, "Enter your username\nThen click Forget");
                }
                else{
                    int tempid;
                    tempid = con.getIdFromUsername(txtUsername.getText());
                    if(tempid == -1){
                        JOptionPane.showMessageDialog(f, "No user with username \""+ txtUsername.getText() + "\"exists.");
                    }
                    else{
                        String ques, ans;
                        ques = con.getQuesFromId(tempid);
                        ans = con.getAnsFromId(tempid);
                        
                        String userAns = JOptionPane.showInputDialog("Your security question:\n" + ques + "\nEnter your ans:\n");
                        if(ans.compareToIgnoreCase(userAns)==0){
                            JOptionPane.showMessageDialog(f, "User Confirmed\nYou will be Redirected to Edit profile page\nEnter a new password there");
                            
                            System.gc();
                            (new HomePage(grid, gbc, con, tempid, txtUsername.getText())).btnEditProfile.doClick();
                            
                            f.dispose();
                            
//                            MouseListener [] m = h.lblEditProfile.getMouseListeners();
                            
//                            JButton b= new JButton();
//                            m[0].mouseClicked(new MouseEvent(h.lblEditProfile));
//                            h.lblEditProfile.getMouse
                        }
                        else{
                            JOptionPane.showMessageDialog(f, "Wrong answer");
                        }
                    }
                }
            }
        });
    }
    
    public boolean validation(){
        boolean result = false;
        StringBuilder error = new StringBuilder("");
        
        if(txtUsername.getText().compareTo("") == 0){
            error.append("Please enter username\n");
        }
        if(txtPassword.getText().compareTo("") == 0){
            error.append("Please enter password\n");
        }
        
        id = con.validUser(txtUsername.getText(), txtPassword.getText());
        
        if(error.toString().compareTo("") == 0){
            if(id != -1){
                JOptionPane.showMessageDialog(f, "Login Successful\nWelcome"+txtUsername.getText());
                return true;
            }
            else{
                error.append("Incorrect username and password");
                JOptionPane.showMessageDialog(f,error);
                return false;
            }
        }
        else{
            JOptionPane.showMessageDialog(f, error);
            return false;
        }
    }
    
    public void setColors(){
//        f.setBackground(AnAutumnMemoir.blue);
//        mainPanel.setBackground(AnAutumnMemoir.green);
//        titlePanel.setBackground(AnAutumnMemoir.mBrown);
//        bottomPanel.setBackground(AnAutumnMemoir.mBrown);
//        mainPanel.setOpaque(true);
//        titlePanel.setOpaque(true);
//        bottomPanel.setOpaque(true);
        
        BufferedImage img = null; // new BufferedImage();
        
        try{
            img = ImageIO.read(new File("D:\\Coding\\advanced java\\porject images\\login1.jpg"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        if(AnAutumnMemoir.width == 0){
            AnAutumnMemoir.width = mainPanel.getWidth();
            AnAutumnMemoir.height = mainPanel.getHeight();
        }
        
        Image img1 = img.getScaledInstance(AnAutumnMemoir.width, AnAutumnMemoir.height, Image.SCALE_SMOOTH);
        
        mainPanel.setIcon(new ImageIcon(img1));
        mainPanel.repaint();
        bottomPanel.setOpaque(true);
        bottomPanel.setBackground(AnAutumnMemoir.white);
        bottomPanel.repaint();
    }
}
