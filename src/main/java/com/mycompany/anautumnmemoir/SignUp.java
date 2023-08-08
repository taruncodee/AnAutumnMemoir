/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.anautumnmemoir;

/**
 *
 * @author tarun
 */
import com.toedter.calendar.JDateChooser;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.util.Date;
import java.util.Enumeration;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.image.*;
import java.io.*;
import javax.imageio.*;

public class SignUp extends JFrame{
    JFrame f;
    
    JPanel titlePanel, headingPanel, formPanel, genderPanel;
    
    JLabel  mainPanel;
    
    JLabel lblTitle, lblHeading, lblName, lblEmail, lblUsername, lblPassword, lblConfirmPassword, lblGender, lblDob, lblSecurityQues, lblSecurityAns;
    
    JTextField txtName, txtEmail, txtUsername;
    JPasswordField txtPassword, txtConfirmPassword;
    
    JRadioButton rdbMale, rdbFemale, rdbOther;
    ButtonGroup btnGrpGender;
    
    JDateChooser dtChDob;
    
    JTextArea txtSecurityQues, txtSecurityAns;
    
    JButton btnSubmit, btnClear, btnLogin;
    
    GridBagConstraints gbc;
    GridBagLayout grid;
    
    Font titleFont, headingFont, controlFont, controlFontSmall;
    
    Border border;
    
    MakeConnection con;
    
    int id;
    String usernameUpdate;
    
    SignUp(GridBagLayout grid, GridBagConstraints gbc, MakeConnection con, int id){
        System.out.println("sign up " + id);
        
        this.f = this;
        this.grid = grid;
        this.gbc = gbc;
        this.id = id;
        this.con = con;
        
        setFrame();
        
        createFonts();
        
        createPanels();
        
        f.setContentPane(mainPanel);
        
        createControls();
        
        addControls();
        
        eventHandling();
        
        if(this.id != 0){
            setControlsForUpdate();
        }
        
        setColors();
        
        f.revalidate();
    }
    
    public final void setFrame(){
        f.setTitle("An Autumn Memoir : Sign Up");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }
    
    public final void createPanels(){
        mainPanel = new JLabel();
        titlePanel = new JPanel();
        headingPanel = new JPanel();
        formPanel = new JPanel();
        
        mainPanel.setLayout(grid);
        titlePanel.setLayout(grid);
        headingPanel.setLayout(grid);
        formPanel.setLayout(grid);
        
//        mainPanel.setBorder(border);
//        titlePanel.setBorder(border);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titlePanel, gbc);
        gbc.fill = GridBagConstraints.NONE;
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0; gbc.weighty = 0;
        mainPanel.add(headingPanel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 0; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(formPanel, gbc);
    }
    
    public final void createFonts(){
        titleFont = new Font("Vladimir Script", Font.BOLD, 75);
        headingFont = new Font("Lucida Handwriting", Font.PLAIN, 40);
        controlFont = headingFont.deriveFont(23f);
        controlFontSmall = controlFont.deriveFont(19f);
        
        border = BorderFactory.createLineBorder(Color.yellow, 2);
    }
    
    public final void createControls(){
        lblTitle = new JLabel("An Autumn Memoir");
        lblTitle.setFont(titleFont);
        
        btnLogin = new JButton("Login");
        btnLogin.setFont(controlFont);
        
        lblHeading = new JLabel("Sign Up");
        lblHeading.setFont(headingFont);
        
        lblName = new JLabel("Name");
        lblName.setFont(controlFont);
        
        lblEmail = new JLabel("Email");
        lblEmail.setFont(controlFont);
        
        lblUsername = new JLabel("Username");
        lblUsername.setFont(controlFont);
        
        lblPassword = new JLabel("Password");
        lblPassword.setFont(controlFont);
        
        lblConfirmPassword = new JLabel("Confirm Password");
        lblConfirmPassword.setFont(controlFont);
        
        lblGender = new JLabel("Gender");
        lblGender.setFont(controlFont);
        
        lblDob = new JLabel("Date of Birth");
        lblDob.setFont(controlFont);
        
        lblSecurityQues = new JLabel("Security Question");
        lblSecurityQues.setFont(controlFont);
        
        lblSecurityAns = new JLabel("Security Answer");
        lblSecurityAns.setFont(controlFont);
        
        txtName = new JTextField();
        txtName.setColumns(8);
        txtName.setFont(controlFontSmall);
        
        txtEmail = new JTextField();
        txtEmail.setColumns(8);
        txtEmail.setFont(controlFontSmall);
        
        txtUsername = new JTextField();
        txtUsername.setColumns(8);
        txtUsername.setFont(controlFontSmall);
        
        txtPassword = new JPasswordField();
        txtPassword.setColumns(8);
        txtPassword.setFont(controlFontSmall);
        
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setColumns(8);
        txtConfirmPassword.setFont(controlFontSmall);
        
        genderPanel = new JPanel();
//        genderPanel.setBorder(border);
        
        rdbMale = new JRadioButton("Male");
        rdbMale.setFont(controlFontSmall);
        
        rdbFemale = new JRadioButton("Female");
        rdbFemale.setFont(controlFontSmall);
        
        rdbOther = new JRadioButton("Other");
        rdbOther.setFont(controlFontSmall);
        
        btnGrpGender = new ButtonGroup();
        btnGrpGender.add(rdbMale);
        btnGrpGender.add(rdbFemale);
        btnGrpGender.add(rdbOther);
        
        dtChDob = new JDateChooser();
        dtChDob.setDate(new Date());
        dtChDob.setFont(controlFontSmall);
        
        txtSecurityQues = new JTextArea();
        //no need to set columns as we are stretching the control in both directions
        //but still need to mention rows, otherwise we get only one row visible at a time.
        //txtSecurityQues.setColumns(8);
        txtSecurityQues.setRows(4);
        txtSecurityQues.setFont(controlFontSmall);
        
        
        txtSecurityAns = new JTextArea();
        txtSecurityAns.setRows(4);
        txtSecurityAns.setFont(controlFontSmall);
        
        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(controlFont);
        
        btnClear = new JButton("Clear");
        btnClear.setFont(controlFont);
    }
    
    public final void addControls(){
        gbc.insets = new Insets(10, 0, 20, 0);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        titlePanel.add(lblTitle, gbc);
        
        gbc.insets = new Insets(10, 20, 20, 0);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1; gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        titlePanel.add(btnLogin, gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        
        gbc.insets = new Insets(10, 0, 70, 0);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        headingPanel.add(lblHeading, gbc);
        
        gbc.insets = new Insets(3, 3, 3, 3);
        
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(lblName, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0; gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(lblEmail, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(lblUsername, gbc);
        
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(lblPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(lblConfirmPassword, gbc);
        
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(lblGender, gbc);
        
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(lblDob, gbc);
        
        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(lblSecurityQues, gbc);
        
        gbc.gridx = 0; gbc.gridy = 8;
        formPanel.add(lblSecurityAns, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(txtName, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(txtEmail, gbc);
        
        gbc.gridx = 1; gbc.gridy = 2;
        formPanel.add(txtUsername, gbc);
        
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(txtPassword, gbc);
        
        gbc.gridx = 1; gbc.gridy = 4;
        formPanel.add(txtConfirmPassword, gbc);
        
        genderPanel.add(rdbMale);
        genderPanel.add(rdbFemale);
        genderPanel.add(rdbOther);
        
        gbc.gridwidth = 2;
        
        gbc.gridx = 1; gbc.gridy = 5;
//        gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(genderPanel, gbc);
        
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        gbc.gridx = 1; gbc.gridy = 6;
        formPanel.add(dtChDob, gbc);
        
        gbc.gridwidth = 2;
        
        JScrollPane scrollSQues = new JScrollPane(txtSecurityQues, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        gbc.gridx = 1; gbc.gridy = 7;
        formPanel.add(scrollSQues, gbc);
        
        JScrollPane scrollSAns = new JScrollPane(txtSecurityAns, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        gbc.gridx = 1; gbc.gridy = 8;
        formPanel.add(scrollSAns, gbc);
        
        gbc.fill = GridBagConstraints.NONE;
        
        gbc.gridwidth = 1;
        
        gbc.gridx = 1; gbc.gridy = 9;
        formPanel.add(btnClear, gbc);
        
        gbc.anchor = GridBagConstraints.EAST;
        
        gbc.gridx = 0; gbc.gridy = 9;
        formPanel.add(btnSubmit, gbc);
        
//        gbc.anchor = GridBagConstraints.NONE;
        
        gbc.gridx = 0; gbc.gridy = 20;
        gbc.weightx = 0; gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        formPanel.add(new JPanel(), gbc);
    }

    public final void eventHandling(){
        btnSubmit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.out.println("button pressed");
                
                if(validation()){
                    int result = con.createUser(id, txtName.getText(), txtEmail.getText(), txtUsername.getText(), txtPassword.getText(), getGender(btnGrpGender), dtChDob.getDate(), txtSecurityQues.getText(), txtSecurityAns.getText()  );
                    
                    if(result == 0){
                        btnSubmit.setBackground(Color.red);
                    }
                    else{
                        if(id==0){
                            JOptionPane.showMessageDialog(f, "User created Successfully!");
                            btnLogin.doClick();
                        }
                        else{
                            usernameUpdate = txtUsername.getText();
                            JOptionPane.showMessageDialog(f, "User updated Successfully!");
                            btnLogin.doClick();
                        }
                    }
                }
            }
        });
        
        btnLogin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.gc();
                new LoginPage(grid, gbc, con);
                f.dispose();
            }
        });
        
        btnClear.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
//                System.gc();
                txtName.setText("");
                txtEmail.setText("");
                txtUsername.setText("");
                txtPassword.setText("");
                txtConfirmPassword.setText("");
                txtSecurityQues.setText("");
                txtSecurityAns.setText("");
                
                f.repaint();
            }
        });
    }
    
    public boolean validation(){
        StringBuffer error = new StringBuffer("");
        boolean result = false;
        
        String regexEmail = "^[A-Za-z0-9+_.-]+@(.+)$";
        
        Pattern pattern = Pattern.compile(regexEmail);
        
        if(txtName.getText().compareTo("") == 0){
            error.append("Name can't be empty\n");
        }
        
        if(txtEmail.getText().compareTo("") == 0){
            error.append("Email can't be empty\n");
        }
        else{
            Matcher matcher = pattern.matcher(txtEmail.getText());
            if(! matcher.matches()){
                error.append("Email format wrong\nOnly alphabets, numbers, underscores, dots and dashes allowed\n");
            }
        }
        
        if(txtUsername.getText().compareTo("") == 0){
            error.append("Username can't be empty\n");
        }
        else{
            boolean cond1, cond2;
            //cond1 = con.usernameTaken(txtUsername.getText()) && id == 0;
            
            
            if(id == 0 && con.usernameTaken(txtUsername.getText())){
                error.append("Username already taken\n");
            }
            else if(id != 0){
                cond2 = usernameUpdate.compareTo(txtUsername.getText()) != 0 && con.usernameTaken(txtUsername.getText());
                if(cond2){
                    error.append("Username already taken\n");
                }
            }
        }
        
        if(txtPassword.getText().length() <8){
            error.append("Password can't be shorter than 8 characters\n");
        }
        
        if(txtConfirmPassword.getText().compareTo(txtPassword.getText()) != 0){
            error.append("Confirm Password doesn't match\n");
        }
        
        if(btnGrpGender.getSelection() == null){
            error.append("Select a Gender\n");
        }
        
        if(txtSecurityQues.getText().compareTo("") == 0){
            error.append("Please provide a security question\n");
        }
        
        if(txtSecurityAns.getText().compareTo("") == 0){
            error.append("Please provide an answer to the security question\n");
        }
        
        if(error.toString().compareTo("") != 0){
            JOptionPane.showMessageDialog(this, error);
            return false;
        }
        else{
            return true;
        }
        
//        return false;
    }
    
    public final String getGender(ButtonGroup bg){
        for(Enumeration<AbstractButton> buttons = bg.getElements(); buttons.hasMoreElements();){
            AbstractButton btn = buttons.nextElement();
            
            if(btn.isSelected()){
                return btn.getText();
            }
        }
        return null;
    }

    public final void setControlsForUpdate(){
        ResultSet rs = con.getDetails(id);
        if(rs != null){
            try{
                rs.next();
                
                txtName.setText(rs.getString(2));
                txtEmail.setText(rs.getString(3));
                txtUsername.setText(rs.getString(4));
                usernameUpdate = txtUsername.getText();
                txtPassword.setText(rs.getString(5));
                txtConfirmPassword.setText(txtPassword.getText());
                
                if(rs.getString(6).compareTo("Male") == 0){
                    rdbMale.setSelected(true);
                }
                else if(rs.getString(6).compareTo("Female") == 0){
                    rdbFemale.setSelected(true);
                }
                else if(rs.getString(6).compareTo("Other") == 0){
                    rdbOther.setSelected(true);
                }
                
                txtSecurityQues.setText(rs.getString(8));
                txtSecurityAns.setText(rs.getString(9));
                
                Date date = rs.getDate(7);
                
                dtChDob.setDate(date);
            }
            catch(SQLException e){
                System.out.println(e);
            }
        }
    }

    public void setColors(){
        BufferedImage img = null;
        try{
            img = ImageIO.read(new File("D:\\Coding\\advanced java\\porject images\\signup1.jpeg"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
        Image img1 = img.getScaledInstance(AnAutumnMemoir.width, AnAutumnMemoir.height, Image.SCALE_SMOOTH);
        
        mainPanel.setIcon(new ImageIcon(img1));
        mainPanel.repaint();
        titlePanel.setOpaque(false);
//        titlePanel.setBackground(AnAutumnMemoir.white);
        headingPanel.setOpaque(false);
//        headingPanel.setBackground(AnAutumnMemoir.white);
        formPanel.setOpaque(true);
        formPanel.setBackground(AnAutumnMemoir.white);
        
        lblHeading.setOpaque(true);
        lblHeading.setBackground(AnAutumnMemoir.white);
        lblTitle.setOpaque(true);
        lblTitle.setBackground(AnAutumnMemoir.white);
        
//        dtChDob.setOpaque(false);
//        dtChDob.setBackground(AnAutumnMemoir.white);
//        genderPanel.setOpaque(false);
        
//        rdbMale.setOpaque(false);
//        rdbMale.setBackground(AnAutumnMemoir.white);
//        rdbFemale.setOpaque(false);
//        rdbFemale.setBackground(AnAutumnMemoir.white);
//        rdbOther.setOpaque(false);
//        rdbOther.setBackground(AnAutumnMemoir.white);
    }
}