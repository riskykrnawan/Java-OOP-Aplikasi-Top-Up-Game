package toko_game;


import java.util.Scanner;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Lenovo-PC
 */
public class Main {
    // db connect
    static final String URL = "jdbc:mysql://localhost:3306/toko_game";
    static final String USERNAME = "root";
    static final String PASSWORD = "";

    static void templateAdmin(String str) {
        System.out.println("1. LIHAT DATA " + str);
        System.out.println("2. TAMBAH DATA " + str);
        System.out.println("3. UBAH DATA " + str);
        System.out.println("4. HAPUS DATA " + str);
    }
    
    static ResultSet query(String query) {
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            Connection connection = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
            
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            
            return resultSet;
            
        } catch(Exception e) {
            System.out.println(e);
        }
        return resultSet;
    }

    static String login(
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
                if (result.equals("user")) {
                    return "SUKSES LOGIN SEBAGAI USER";   
                }
                if (result.equals("admin")) {
                    return "SUKSES LOGIN SEBAGAI USER";   
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "LOGIN GAGAL, KREDENSIAL YANG DIBERIKAN TIDAK BENAR";
    }
    static String register(
        String username,
        String password,
        String nama,
        String alamat,
        String noTelp
    ) {
        return "PESAN REGISTER SUKSES";
    }
    
    public static void main(String[] args) {
        // declare variable
        Scanner myObj = new Scanner(System.in);
        
        boolean repeat = true;
        String pil, pil2, pil3, pil4;
        String username, password, nama, alamat, noTelp;
        
        while(repeat) {
            System.out.println("===== TOKO GAME ETAM =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("==========================");
            System.out.print("Masukkan Pilihan: ");
            pil = myObj.nextLine();
            
            switch(pil) {
                case "1" -> {
                    System.out.println("===========LOGIN===========");
                    System.out.print("Username: ");
                    username = myObj.nextLine();
                    System.out.print("Password: ");
                    password = myObj.nextLine();
                    //setelah user nginputkan data login, jalankan fungsi login
                    System.out.println(login(username, password));
                }
                case "2" -> {
                    System.out.println("==========REGISTER==========");
                    System.out.print("Username: ");
                    username = myObj.nextLine();
                    System.out.print("Password: ");
                    password = myObj.nextLine();
                    System.out.print("Nama: ");
                    nama = myObj.nextLine();
                    System.out.print("Alamat: ");
                    alamat = myObj.nextLine();
                    System.out.print("No Telp: ");
                    noTelp = myObj.nextLine();
                    //setelah user nginputkan data register, jalankan fungsi register
                    register(username, password, nama, alamat, noTelp);
                }
            }
        }
    }
    
}
