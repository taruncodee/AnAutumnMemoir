/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.anautumnmemoir;

/**
 *
 * @author tarun
 */

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class MakeConnection {
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    
    String driverClass = "com.mysql.cj.jdbc.Driver";
    String dburl = "jdbc:mysql://localhost:3306/anautumnmemoir";
    String dbuser = "root";
    String dbpass = "1234";
    
    String queries[] = {
        "select * from users where username = ? and password = ?",//0
        "select * from users where username = ?",//1
        "insert into users values(?,?,?,?,?,?,?,?,?)",//2
        "select * from users",//3
        "select * from users where id = ?",//4
        "update users set name = ?, email = ?, username = ?, password = ?, gender = ?, dateofbirth = ?, securityques = ?, securityans = ? where id = ?",
        "select * from entries where userid = ?",//6
        "insert into entries values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", //7
        "update entries set topic = ?, content = ?, fontname = ?, fontsize = ?, bold = ?, italic = ? where entryid = ?",
        "select max(entryid) from entries", //9
        "delete from entries where entryid = ?" //10
    };
    
//    String stmtString;
    
    MakeConnection(){
        try{
            Class.forName(driverClass);
        }
        catch(ClassNotFoundException e){
            System.out.println(e);
        }
        
        try{
            con = DriverManager.getConnection(dburl, dbuser, dbpass);
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }
    
    int getIdFromUsername(String username){
        int id = -1;
        try{
            stmt = con.prepareStatement(queries[1], ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            if(count == 1){
                rs.first();
                id = rs.getInt(1);
            }
            System.out.println("count is " + count +"\nid is "+id);
            rs.close();
            stmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return id;
    }
    
    String getQuesFromId(int id){
        String ques = "";
        try{
            stmt = con.prepareStatement(queries[4], ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            if(count == 1){
                rs.first();
                ques = rs.getString("securityques");
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return ques;
    }
    
    String getAnsFromId(int id){
        String ans = "";
        try{
            stmt = con.prepareStatement(queries[4], ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            if(count == 1){
                rs.first();
                ans = rs.getString("securityans");
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return ans;
    }
    
    boolean usernameTaken(String username){
        int count = 0;
        try{
            stmt = con.prepareStatement(queries[1], ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setString(1,username);
            rs = stmt.executeQuery();
            while(rs.next()){
                count++;
            }
            rs.close();
            stmt.close();
            if(count == 0){
                return false;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return true;
    }
    
    int validUser(String username, String password){
        int count = 0;
        int id = -1;
        
        try{
            stmt = con.prepareStatement(queries[0]);
            stmt.setString(1,username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
                count++;
            }
            
            rs.close();
            stmt.close();
            if(count == 0){
                return -1;
            }
            else if(count == 1){
                return id;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return -1;
    }
    
    int getNextId(){
        int id = 1;
        try{
            stmt = con.prepareStatement(queries[3], ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery();
            if(rs.last()){
                id += rs.getInt("id");
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return id;
    }
    
    public ResultSet getDetails(int id){
        try{
            stmt = con.prepareStatement(queries[4]);
            stmt.setInt(1, id);
            return stmt.executeQuery();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return null;
    }
    
    int createUser(int id, String name, String email, String username, String password, String gender, java.util.Date date, String securityques, String securityans ){
        int result = 0;
        
        try{
            java.sql.Date sqldate = new java.sql.Date(date.getTime());
            if(id == 0){
                id = getNextId();
                
                stmt = con.prepareStatement(queries[2]);
                stmt.setInt(1, id);
                stmt.setString(2, name);
                stmt.setString(3, email);
                stmt.setString(4, username);
                stmt.setString(5, password); 
                stmt.setString(6, gender); 
                stmt.setDate(7, sqldate); 
                stmt.setString(8, securityques);
                stmt.setString(9, securityans);

                result = stmt.executeUpdate();

                stmt.close();
                if(result == 0){
                    System.out.println("update failed");
                }
                else if(result==1){
                    System.out.println("user created");
                }
            }
            else{
                stmt = con.prepareStatement(queries[5]);
                stmt.setInt(9, id);
                stmt.setString(1, name);
                stmt.setString(2, email);
                stmt.setString(3, username);
                stmt.setString(4, password); 
                stmt.setString(5, gender); 
                stmt.setDate(6, sqldate); 
                stmt.setString(7, securityques);
                stmt.setString(8, securityans);
                
                result = stmt.executeUpdate();
                
                stmt.close();
                if(result == 0){
                    System.out.println("update failed");
                }
                else if(result==1){
                    System.out.println("user created");
                }
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return result;
    }
    
    public ResultSet getUserEntries(int id){
        try{
            stmt = con.prepareStatement(queries[6], ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
            }
            if(count == 0){
                return null;
            }
            else{
                return rs;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return null;
    }
    
    public int createEntry(int entryId, String title, String topic, java.util.Date date, String content, String fontName, int fontSize, int bold, int italic, int userid){
        int result = 0;
        try{
            if(entryId == 0){
                // remember why you called here, instead of directly calling in setInt?
                entryId = getNextEntryId();
                
                stmt = con.prepareStatement(queries[7]);
                stmt.setInt(1, entryId);
                stmt.setString(2, title);
                stmt.setString(3, topic);
                stmt.setDate(4, new java.sql.Date(date.getTime()));
                stmt.setString(5, content);
                stmt.setString(6, fontName);
                stmt.setInt(7, fontSize);
                stmt.setInt(8, bold);
                stmt.setInt(9, italic);
                stmt.setInt(10, userid);
                
                result = stmt.executeUpdate();
                
                stmt.close();
                
                if(result == 0){
                    System.out.println("insertion failed");
                }
                else{
                    System.out.println("entry created successfully");
                }
            }
            else{
                stmt = con.prepareStatement(queries[8]);
                stmt.setInt(7, entryId);
                stmt.setString(1, topic);
                stmt.setString(2, content);
//                stmt.setDate(3, new java.sql.Date(date.getTime()));
//                stmt.setString(4, content);
                stmt.setString(3, fontName);
                stmt.setInt(4, fontSize);
                stmt.setInt(5, bold);
                stmt.setInt(6, italic);
//                stmt.setInt(9, userid);
                
                result = stmt.executeUpdate();
                
                stmt.close();
                
                if(result == 0){
                    System.out.println("updation failed");
                }
                else{
                    System.out.println("entry updated successfully");
                }
            }
            
        }
        catch(SQLException e){
            System.out.println(e);
        }
        
        return result;
    }
    
    public int getNextEntryId(){
        int id = 1;
        try{
            stmt = con.prepareStatement(queries[9], ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stmt.executeQuery();
            if(rs.last()){
                id += rs.getInt(1);
            }
            rs.close();
            stmt.close();
        }
        catch(SQLException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        return id;
    }
    
    public int deleteById(int id){
        int result = 0;
        try{
            stmt = con.prepareStatement(queries[10]);
            stmt.setInt(1, id);
            result = stmt.executeUpdate();
            
            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return result;
    }
    
//    void setStatement(String querystring){
//        try{
//            stmt = con.prepareStatement(querystring);
//            
//        }
//        catch(SQLException e){
//            System.out.println(e);
//        }
//    }
    
}
