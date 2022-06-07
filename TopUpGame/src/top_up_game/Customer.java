/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top_up_game;

import java.sql.ResultSet;
import static top_up_game.Main.query;

/**
 *
 * @author Lenovo-PC
 */
public class Customer extends Person implements User{
    private String id;
    // constructor
    public Customer (
            String username,
            String password
        ) {
        super(username, password);
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String statusLogin(String otorisasi) {
        return "Berhasil login sebagai " + otorisasi;
    }
    
    @Override
    public String login(
        String username,
        String password
    ) {
        try {
            
            String query = "SELECT otorisasi "
                            + "FROM users WHERE "
                            + "username='" + username 
                            + "' AND " 
                            + "password='" + password
                            + "'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String result = resultSet.getString(1);
                return result;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "";
    }
}
