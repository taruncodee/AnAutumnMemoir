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
import javax.swing.event.*;
import javax.swing.border.*;
//import com.mycompany.anautumnmemoir.*;
import java.sql.*;
import java.util.Date;

public class HomePage extends JFrame{
    JFrame f;
    
    Container cp;
    
    JPanel mainPanel, titlePanel, accountPanel, leftPanel, rightPanel, entriesPanel, editorControlsPanel, editorControlsPanel1, editorEntryInfoPanel;
    
    Border border;
    
    GridBagLayout grid;
    GridBagConstraints gbc;
    
    JLabel lblTitle, lblAccount, lblLogout, lblFontSize, lblFontName, lblEntryTitle, lblEntryDate, lblEntryTopic;
    
    JButton btnEditProfile, btnNewEntry;
    
    JTextArea txtWritingArea;
    
    Font titleFont, headingFont, controlFont;
    
    JComboBox fontSizeCB, fontNameCB, entryTopic;
    
    JToggleButton boldToggle, italicToggle;
    JButton btnSave, btnClose;
    //"4", "6", "8", "10", "12", "14", "16", "18", 
    static String sizes[] = {"20", "22", "24", "26", "28", "30", "32", "34", "36", "38", "40", "42", "44", "46", "48", "50", "52", "54", "56", "58", "60"};
    static String names[] = {"Arial","Blackadder ITC","Bradley Hand ITC","Brush Script MT","Calibri Light", "Chiller","Comic Sans MS","Courier New","Curlz MT","Edwardian Script ITC","Footlight MT Light","Forte","Freestyle Script","French Script MT","Gabriola","Georgia","Gigi","Goudy Old Style","Harlow Solid Italic","Harrington","High Tower Text","Informal Roman","Ink Free","Jokerman","Juice ITC","Kristen ITC","Kunstler Script","Lucida Calligraphy","Lucida Handwriting","Magneto","Maiandra GD","Matura MT Script Capitals","Mistral","Monotype Corsiva","MV Boli","Old English Text MT", "Palace Script MT","Papyrus","Parchment","Perpetua","Poor Richard","Pristina","Rage Italic","Ravie","Rockwell","Script MT Bold","Segoe Print","Segoe Script","Showcard Gothic","Snap ITC","Stencil","Tahoma","Tempus Sans ITC","Times New Roman","Tw Cen MT","Verdana","Viner Hand ITC","Vivaldi","Vladimir Script"};
    static String topics[] = {"Event", "Thought", "Dream"};
    
    MakeConnection con;
    
    int id, entryid;
    
    ActionListener alView, alEdit, alDelete;
    ItemListener ilFontChange;
    ChangeListener clBoldItalic;
    
    boolean updating, viewing;
    
    java.util.Date dateCreate;
    
    String username;
    
    HomePage(GridBagLayout grid, GridBagConstraints gbc, MakeConnection con, int id, String username){
        this.f = this;
        this.grid = grid;
        this.gbc = gbc;
        this.id = id;
        this.con = con;
        
        this.username = username;
        
        updating = false;
        entryid = 0;
        
        setFrame();
        
        createFonts();
        
        createPanels();
        
        createControls();
        
        addControls();
        
        eventHandling();
        
        showEntries();
        
        disableEditor();
        
        setColors();
        
        f.revalidate();
    }
    
    public final void createFonts(){
        border = BorderFactory.createLineBorder(Color.DARK_GRAY, 1);
        
        titleFont = new Font("Vladimir Script", Font.BOLD, 75);
        headingFont = new Font("Lucida Handwriting", Font.PLAIN, 40);
        controlFont = headingFont.deriveFont(23f);
    }
    
    public final void createPanels(){
        mainPanel = new JPanel();
        titlePanel = new JPanel();
        accountPanel = new JPanel();
        leftPanel = new JPanel();
        rightPanel = new JPanel();
        entriesPanel = new JPanel();
        editorEntryInfoPanel = new JPanel();
        editorControlsPanel1 = new JPanel();
        editorControlsPanel = new JPanel();
        
        mainPanel.setLayout(grid);
        titlePanel.setLayout(grid);
        accountPanel.setLayout(grid);
        leftPanel.setLayout(grid);
        rightPanel.setLayout(grid);
        entriesPanel.setLayout(grid);
        editorEntryInfoPanel.setLayout(grid);
        editorControlsPanel1.setLayout(grid);
        editorControlsPanel.setLayout(grid);
        
//        mainPanel.setBorder(border);
//        titlePanel.setBorder(border);
//        accountPanel.setBorder(border);
//        leftPanel.setBorder(border);
//        rightPanel.setBorder(border);
        entriesPanel.setBorder(border);
        
        gbc.insets = new Insets(3,3,3,3);
        f.setContentPane(mainPanel);
        
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = 2;
        mainPanel.add(titlePanel, gbc);
        
        gbc.gridwidth = 1;
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(leftPanel, gbc);
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 8;
        gbc.weighty = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(rightPanel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;
        leftPanel.add(accountPanel, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.NORTH;
        
        JScrollPane s1 = new JScrollPane(entriesPanel);
        s1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        s1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        s1.setBorder(BorderFactory.createEmptyBorder());
        
        leftPanel.add(s1, gbc);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        rightPanel.add(editorEntryInfoPanel, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        rightPanel.add(editorControlsPanel1, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        rightPanel.add(editorControlsPanel, gbc);
        
        
    }
    
    public final void createControls(){
        lblTitle = new JLabel("An Autumn Memoir");
        lblTitle.setFont(titleFont);
//        lblTitle.setBackground(Color.PINK);
//        lblTitle.setOpaque(true);
        
        lblAccount = new JLabel(this.username);
        lblAccount.setFont(headingFont);
        
        lblLogout = new JLabel("Logout");
        lblLogout.setFont(controlFont);
        
        btnEditProfile = new JButton("Edit Profile");
        btnEditProfile.setFont(controlFont);
        btnEditProfile.setFocusPainted(false);
        btnEditProfile.setMargin(new Insets(0,0,0,0));
        btnEditProfile.setContentAreaFilled(false);
        btnEditProfile.setBorderPainted(false);
        btnEditProfile.setOpaque(false);
        
        btnNewEntry = new JButton("New Journal Entry");
        btnNewEntry.setFont(controlFont);
        
        lblEntryTitle = new JLabel("Entry Title");
        lblEntryTitle.setFont(controlFont.deriveFont(Font.BOLD));
        
        lblEntryDate = new JLabel("dd-mm-yyyy");
        lblEntryDate.setFont(controlFont);
        
        lblEntryTopic = new JLabel("Topic : ");
        lblEntryTopic.setFont(controlFont);
        
        
        Font tempFont = new Font("Times New Roman", Font.PLAIN, 19);
        Font tempFont1 = controlFont.deriveFont(20f);
        
        
        entryTopic = new JComboBox(topics);
        entryTopic.setFont(tempFont);
        
        btnSave = new JButton("Save");
        btnSave.setFont(tempFont1);
        
        btnClose = new JButton("Close");
        btnClose.setFont(tempFont1);
        
        lblFontSize = new JLabel("Size : ");
        lblFontName = new JLabel("Name : ");
        lblFontSize.setFont(controlFont);
        lblFontName.setFont(controlFont);
        
        fontSizeCB = new JComboBox(sizes);
//        fontSizeCB.setFont(fontSizeCB.getFont().deriveFont(23F));
        fontSizeCB.setFont(tempFont);
        fontSizeCB.setEditable(false);
        
        fontNameCB = new JComboBox(names);
//        fontNameCB.setFont(fontNameCB.getFont().deriveFont(23F));
        fontNameCB.setFont(tempFont);
        
        boldToggle = new JToggleButton("Bold");
        boldToggle.setFont(tempFont1);
        italicToggle = new JToggleButton("Italic");
        italicToggle.setFont(tempFont1);
        
        txtWritingArea = new JTextArea();
        txtWritingArea.setFont(controlFont);
        txtWritingArea.setLineWrap(true);
        txtWritingArea.setWrapStyleWord(true);
        
    }
    
    public final void addControls(){
        gbc.insets = new Insets(5, 3, 5, 3);
        
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        titlePanel.add(lblTitle, gbc);
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridheight = 2;
        accountPanel.add(lblAccount, gbc);
        gbc.gridheight = 1;
        
        gbc.gridx = 1; gbc.gridy = 1;
        gbc.weightx = 1; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        accountPanel.add(lblLogout, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        accountPanel.add(btnEditProfile, gbc);
        
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.weightx = 1; gbc.weighty = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 2;
        accountPanel.add(btnNewEntry, gbc);
        gbc.gridwidth = 1;
        
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        editorEntryInfoPanel.add(lblEntryTitle, gbc);
        
        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        editorEntryInfoPanel.add(lblEntryDate, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        editorControlsPanel1.add(lblEntryTopic, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        editorControlsPanel1.add(entryTopic, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        editorControlsPanel1.add(btnSave, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.WEST;
        editorControlsPanel1.add(btnClose, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        editorControlsPanel.add(lblFontName, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        editorControlsPanel.add(fontNameCB, gbc);
        
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        editorControlsPanel.add(lblFontSize, gbc);
        
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        editorControlsPanel.add(fontSizeCB, gbc);
        
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        editorControlsPanel.add(boldToggle, gbc);
        
        gbc.gridx = 5;
        gbc.gridy = 0;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        editorControlsPanel.add(italicToggle, gbc);
        
        gbc.insets = new Insets(3, 3, 3,3);
        
        JScrollPane s2 = new JScrollPane(txtWritingArea);
        s2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        s2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        s2.setBorder(BorderFactory.createEmptyBorder());
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        rightPanel.add(s2, gbc);
    }

    public final void setFrame(){
        f.setTitle("An Autumn Memoir : Home");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setExtendedState(JFrame.MAXIMIZED_BOTH);
        f.setVisible(true);
    }

    public final void eventHandling(){
        alView = new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JournalEntry sourceEntry = (JournalEntry) ((JButton)ae.getSource()).getParent();
                
                viewing = true;
                disableEditor();
                lblEntryTitle.setText(sourceEntry.title.length() > 30 ? sourceEntry.title.substring(0, 30) + "..." : sourceEntry.title);
                lblEntryTitle.setToolTipText(sourceEntry.title);
                entryTopic.setSelectedItem(sourceEntry.topic);
                lblEntryDate.setText(sourceEntry.date.toString());
                fontNameCB.setSelectedItem(sourceEntry.fontname);
                fontSizeCB.setSelectedItem(sourceEntry.fontsize);
                if(sourceEntry.bold){
                    boldToggle.setSelected(true);
                }
                if(sourceEntry.italic){
                    italicToggle.setSelected(true);
                }
                
                txtWritingArea.setText(sourceEntry.content.toString());
                
                btnClose.setEnabled(true);
                
                btnNewEntry.setEnabled(false);
                setEnablePanel(entriesPanel, false);
            }
        };
        
        alEdit = new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JournalEntry sourceEntry = (JournalEntry)  ((JPanel)((JButton)ae.getSource()).getParent()).getParent();
                
                updating = true;
                setEnablePanel(entriesPanel, false);
                btnNewEntry.setEnabled(false);
                disableEditor();
                
                entryid = sourceEntry.id;
                dateCreate = sourceEntry.date;
                
                lblEntryTitle.setText(sourceEntry.title.length() > 30 ? sourceEntry.title.substring(0, 30) + "..." : sourceEntry.title);
                lblEntryTitle.setToolTipText(sourceEntry.title);
                
                entryTopic.setEnabled(true);
                entryTopic.setSelectedItem(sourceEntry.topic);
                
                lblEntryDate.setText(sourceEntry.date.toString());
                
                fontNameCB.setSelectedItem(sourceEntry.fontname);
                fontNameCB.setEnabled(true);
                
                fontSizeCB.setSelectedItem(sourceEntry.fontsize);
                fontSizeCB.setEnabled(true);
                
                if(sourceEntry.bold){
                    boldToggle.setSelected(true);
                }
                if(sourceEntry.italic){
                    italicToggle.setSelected(true);
                }
                boldToggle.setEnabled(true);
                italicToggle.setEnabled(true);
                
                txtWritingArea.setText(sourceEntry.content.toString());
                txtWritingArea.setEnabled(true);
                txtWritingArea.setEditable(true);
                
                btnClose.setEnabled(true);
                btnSave.setEnabled(true);
                
                f.revalidate();
            }
        };
        
        alDelete = new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JournalEntry sourceEntry = (JournalEntry) (((JButton)ae.getSource()).getParent()).getParent();
                int choice = JOptionPane.showConfirmDialog(f, "Are you sure, you want to delete this entry?", "Delete", JOptionPane.YES_NO_OPTION);
                
                if(choice == 0){
                    if(con.deleteById(sourceEntry.id) == 1){
                        JOptionPane.showMessageDialog(f, "Deleted Successfully");
                    }
                    else{
                        JOptionPane.showMessageDialog(f, "Deletion Failed");
                    }
                }
                
                showEntries();
                entriesPanel.repaint();
                f.revalidate();
            }
        };
        
        ilFontChange = new ItemListener(){
            public void itemStateChanged(ItemEvent ie){
//                Font currentFont = txtWritingArea.getFont();
                String newFontName = (String)fontNameCB.getSelectedItem();
                float fontsize = Float.parseFloat((String)fontSizeCB.getSelectedItem());
                
                int bold, italic;
                bold = italic = 0;
                
                if(boldToggle.isSelected()){
                    bold = Font.BOLD;
                }
                if(italicToggle.isSelected()){
                    italic = Font.ITALIC;
                }
                
                txtWritingArea.setFont(new Font(newFontName, bold | italic , (int)fontsize));
                
//                System.out.println("super duper");
//                String newFontName = (String) ((JComboBox)ie.getSource()).getSelectedItem();
//                float currentSize = currentFont.getFont();
//                Font newFont = currentFont.deriveFont(newFontName);
            }
        };
        
        clBoldItalic = new ChangeListener(){
            public void stateChanged(ChangeEvent ce){
                String newFontName = (String)fontNameCB.getSelectedItem();
                float fontsize = Float.parseFloat((String)fontSizeCB.getSelectedItem());
                
                int bold, italic;
                bold = italic = 0;
                
                if(boldToggle.isSelected()){
                    bold = Font.BOLD;
                }
                if(italicToggle.isSelected()){
                    italic = Font.ITALIC;
                }
                
                txtWritingArea.setFont(new Font(newFontName, bold | italic , (int)fontsize));
            }
        };
        
        btnEditProfile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.gc();
                new SignUp(grid, gbc, con, id);
                f.dispose();
            }
        });
        
        lblLogout.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent me){
                System.gc();
                new LoginPage(grid, gbc, con);
                f.dispose();
            }
        });
        
        btnNewEntry.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(!enableEditorNewEntry()){
                    JOptionPane.showMessageDialog(f, "New entry can't be written without title.\nNew entry creation cancelled.");
                    disableEditor();
                    return;
                }
                
                btnNewEntry.setEnabled(false);
//                disableEntriesPanel();
                setEnablePanel(entriesPanel, false);
                entryid = 0;
                updating = false;
            }
        });
        
        fontNameCB.addItemListener(ilFontChange);
        fontSizeCB.addItemListener(ilFontChange);
        
        boldToggle.addChangeListener(clBoldItalic);
        italicToggle.addChangeListener(clBoldItalic);
        
        btnClose.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                if(viewing){
                    disableEditor();
                    viewing = false;
                    
                    btnNewEntry.setEnabled(true);
                    setEnablePanel(entriesPanel, true);
                    
                    return;
                }
                
                int choice = JOptionPane.showConfirmDialog(f, "Do you want to save before closing?");
//                System.out.println(choice);
                if(choice == 2){
                    return;
                }
                else if(choice == 0){
                    btnSave.doClick();
                }
                disableEditor();

                btnNewEntry.setEnabled(true);
//                enableEntriesPanel();
                setEnablePanel(entriesPanel, true);
                entryid = 0;
                updating = false;
            }
        });
        
        btnSave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                int result = 0;
                
                int bold, italic;
                
                if(boldToggle.isSelected()){
                    bold = 1;
                }
                else{
                    bold = 0;
                }

                if(italicToggle.isSelected()){
                    italic = 1;
                }
                else{
                    italic = 0;
                }
                
                if( !updating){
                    
                    result = con.createEntry(entryid, lblEntryTitle.getToolTipText(), (String)entryTopic.getSelectedItem(), dateCreate, txtWritingArea.getText(), (String)fontNameCB.getSelectedItem(), Integer.parseInt((String)fontSizeCB.getSelectedItem()), bold, italic, id);
                    
                    if(result == 0){
                        JOptionPane.showMessageDialog(f, "Save Failed!\nEntry not saved.");
                    }
                    else{
                        JOptionPane.showMessageDialog(f, "Saved Successfully.");
                        updating = true;
                        entryid = con.getNextEntryId() - 1;
                        showEntries();
                        setEnablePanel(entriesPanel, false);
                    }
                }
                else{
                    result = con.createEntry(entryid, lblEntryTitle.getToolTipText(), (String)entryTopic.getSelectedItem(), dateCreate, txtWritingArea.getText(), (String)fontNameCB.getSelectedItem(), Integer.parseInt((String)fontSizeCB.getSelectedItem()), bold, italic, id);
                    
                    if(result == 0){
                        JOptionPane.showMessageDialog(f, "Update Failed!\nEntry not Updated.");
                    }
                    else{
                        JOptionPane.showMessageDialog(f, "Updated Successfully.");
                        showEntries();
                        setEnablePanel(entriesPanel, false);
                    }
                }
            }
        });
    }
    
    public final void showEntries(){
        try{
            entriesPanel.removeAll();
            
            System.gc();
            
            JPanel t1 = new JPanel();
            t1.setOpaque(false);
            
            ResultSet rs = con.getUserEntries(id);
            if(rs == null){
                JLabel lblNoEntries = new JLabel("You haven't wriiten anything yet.");
                lblNoEntries.setFont(controlFont.deriveFont(16f));
                
                gbc.gridx = 0; gbc.gridy = 0;
                gbc.weightx = 1; gbc.weighty = 0;
                gbc.fill = GridBagConstraints.NONE;
                gbc.anchor = GridBagConstraints.CENTER;
                entriesPanel.add(lblNoEntries, gbc);
                
                
                
                gbc.gridx = 0; gbc.gridy = 1;
                gbc.weightx = 1; gbc.weighty = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.NORTH;
                entriesPanel.add(t1, gbc);
            }
            else{
                rs.beforeFirst();
                int count = 0;
                while(rs.next()){
                    System.out.println("Result set navigating\n" + rs.getDate(4).getClass());
                    
                    JournalEntry temp = new JournalEntry(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getBoolean(8), rs.getBoolean(9));
                    
                    temp.btnView.addActionListener(alView);
                    temp.btnEdit.addActionListener(alEdit);
                    temp.btnDelete.addActionListener(alDelete);
                    
                    temp.setBackground(AnAutumnMemoir.purple);
                    ((JPanel)temp.lblTopic.getParent()).setOpaque(false);
                    
                    gbc.gridx = 0; gbc.gridy = count;
                    gbc.weightx = 1; gbc.weighty = 0;
                    gbc.fill = GridBagConstraints.HORIZONTAL;
                    gbc.anchor = GridBagConstraints.NORTH;
                    entriesPanel.add(temp, gbc);
                    
                    count++;
                }
                
                
                
                gbc.gridx = 0; gbc.gridy = count;
                gbc.weightx = 1; gbc.weighty = 1;
                gbc.fill = GridBagConstraints.HORIZONTAL;
                gbc.anchor = GridBagConstraints.NORTH;
                entriesPanel.add(t1, gbc);
                
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        f.revalidate();
    }
    
    public final void disableEditor(){
        lblEntryTitle.setText("No title set");
        lblEntryDate.setText("N/A");
        entryTopic.setEnabled(false);
        fontNameCB.setEnabled(false);
        fontSizeCB.setEnabled(false);
        boldToggle.setEnabled(false);
        italicToggle.setEnabled(false);
        
        txtWritingArea.setText("");
        txtWritingArea.setEditable(false);
        
        btnSave.setEnabled(false);
        btnClose.setEnabled(false);
    }
    
    public final boolean enableEditorNewEntry(){
        String title = JOptionPane.showInputDialog("Enter title for new entry : ");
        if(title == null || title.length() == 0){
            return false;
        }
//        System.out.println(title + " "+title.length());
        lblEntryTitle.setText( title.length()>30 ? title.substring(0, 30) + "..." : title);
        lblEntryTitle.setToolTipText(title);
        dateCreate = new Date();
        lblEntryDate.setText(dateCreate.toString());
        entryTopic.setEnabled(true);
        fontNameCB.setEnabled(true);
        fontSizeCB.setEnabled(true);
        boldToggle.setEnabled(true);
        italicToggle.setEnabled(true);
        
        txtWritingArea.setEditable(true);
        
        btnSave.setEnabled(true);
        btnClose.setEnabled(true);
        
        return true;
    }
    
    public final void setEnablePanel(JPanel panel, boolean isEnabled){
        panel.setEnabled(isEnabled);
        
        Component [] components = panel.getComponents();
        
        for(Component component : components){
            if(component instanceof JPanel){
                setEnablePanel((JPanel)component, isEnabled);
            }
            component.setEnabled(isEnabled);
        }
        
//        void setPanelEnabled(JPanel panel, Boolean isEnabled) {
//            panel.setEnabled(isEnabled);
//
//            Component[] components = panel.getComponents();
//
//            for (Component component : components) {
//                if (component instanceof JPanel) {
//                    setPanelEnabled((JPanel) component, isEnabled);
//                }
//                component.setEnabled(isEnabled);
//            }
//        }
    }
    
    public final void setColors(){
        mainPanel.setBackground(AnAutumnMemoir.dblue);
        mainPanel.setOpaque(true);
        titlePanel.setBackground(AnAutumnMemoir.dblue);
        titlePanel.setOpaque(true);
        lblTitle.setForeground(Color.LIGHT_GRAY);
        entriesPanel.setBackground(AnAutumnMemoir.lblue);
        entriesPanel.setOpaque(true);
        leftPanel.setBackground(AnAutumnMemoir.lblue);
        leftPanel.setOpaque(true);
        accountPanel.setBackground(AnAutumnMemoir.blue);
        accountPanel.setOpaque(true);
        rightPanel.setBackground(AnAutumnMemoir.blue);
        rightPanel.setOpaque(true);
        
        editorControlsPanel.setOpaque(false);
        editorControlsPanel1.setOpaque(false);
        editorEntryInfoPanel.setOpaque(false);
        
        txtWritingArea.setBackground(AnAutumnMemoir.lBrown);
        
//        btnEditProfile.setOpaque(false);
    }
    
}