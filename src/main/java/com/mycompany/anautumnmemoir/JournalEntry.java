/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.anautumnmemoir;

/**
 *
 * @author tarun
 */

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class JournalEntry extends JPanel {
    JPanel p;
    
    GridBagLayout grid;
    GridBagConstraints gbc;
    
    JLabel lblTitle, lblDate, lblTopic;
    
    JButton btnEdit, btnDelete, btnView;
    
    Font bFont, mFont, sFont;
    
    int id;
    String title, topic, fontname, fontsize;
    boolean bold, italic;
    StringBuffer content;
    Date date;
    
    JournalEntry(int id, String title, String topic, Date date, String content, String fontname, String fontsize, boolean bold, boolean italic){
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.date = date;
        this.content = new StringBuffer(content);
        this.fontname = fontname;
        this.fontsize = fontsize;
        this.bold = bold;
        this.italic = italic;
        
        p = this;
        
        grid = new GridBagLayout();
        gbc = new GridBagConstraints();
        
        p.setLayout(grid);
        
        bFont = new Font("Lucida Handwriting", Font.PLAIN, 28);
        mFont = new Font("Lucida Handwriting", Font.PLAIN, 22);
        mFont = new Font("Lucida Handwriting", Font.PLAIN, 18);
        
        lblTitle = new JLabel( title.length()>10 ? title.substring(0,10) + "..." : title);
        lblTitle.setToolTipText(title);
        lblTitle.setFont(bFont);
        
        lblDate = new JLabel(date.toString());
        lblDate.setFont(mFont);
        
        lblTopic = new JLabel(", "+topic);
        lblTopic.setFont(mFont);
        
        btnView = new JButton("View");
        btnView.setFont(mFont);
        
        btnEdit = new JButton("Edit");
        btnEdit.setFont(sFont);
        
        btnDelete = new JButton("Delete");
        btnDelete.setFont(sFont);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = .8;
        //gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        p.add(lblTitle, gbc);
        
        //gbc.gridwidth = 1;
        
        JPanel temp = new JPanel();
//        temp.setLayout(new FlowLayout(FlowLayout.RIGHT));
        temp.setLayout(grid);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        temp.add(lblDate, gbc);
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1;
        temp.add(lblTopic, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        p.add(temp, gbc);
        
//        gbc.gridx = 1; gbc.gridy = 1;
//        gbc.weightx = 0;
//        gbc.anchor = GridBagConstraints.WEST;
//        p.add(lblTopic, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 0;
//        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        p.add(btnView, gbc);
        
//        gbc.gridwidth = 1;

        temp = new JPanel();
//        temp.setLayout(new FlowLayout(FlowLayout.RIGHT));
        temp.setLayout(grid);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        temp.add(btnEdit);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        temp.add(btnDelete);
        
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        p.add(temp, gbc);
        
//        gbc.gridx = 3; gbc.gridy = 1;
//        gbc.weightx = 0;
//        gbc.anchor = GridBagConstraints.EAST;
//        p.add(btnDelete, gbc);
    }
    
//    public JPanel getPanel(){
//        return this;
//    }
}
