package top_up_game;


import java.util.Scanner;
import java.sql.Connection;
// import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


/**
 *
 * @author Lenovo-PC
 */
public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/toko_game";
    static final String USERNAME = "root";
    static final String PASSWORD = "";

    static void templateAdmin1(String str) {
        System.out.println("1. Lihat Data " + str);
        System.out.println("2. Ubah Data " + str);
        System.out.println("3. Hapus Data " + str);
    }
    static void templateAdmin2(String str) {
        System.out.println("1. Lihat Data " + str);
        System.out.println("2. Tambah Data " + str);
        System.out.println("3. Ubah Data " + str);
        System.out.println("4. Hapus Data " + str);
    }
    static void menuAdmin() {
        System.out.println("1. Pengelolaan Data Game");
        System.out.println("2. Pengelolaan Data User");
        System.out.println("3. Pengelolaan Data Riwayat Pembelian");
    }
    
    
    static ResultSet query(String query) {
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // untuk connect ke dbmysql
            Connection connection = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
            
            Statement statement = connection.createStatement();
            
            // mengeksekusi query
            resultSet = statement.executeQuery(query);
            
            // mengembalikan hasil query
            return resultSet;
            
        } catch(Exception e) {
            System.out.println(e);
        }
        return resultSet;
    }
    
    static int update(String query) {        
        int result = 0;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // untuk connect ke dbmysql
            Connection connection = DriverManager.getConnection(
                    URL, USERNAME, PASSWORD);
            
            Statement statement = connection.createStatement();
            
            // mengeksekusi query
            result = statement.executeUpdate(query);
            
            // mengembalikan hasil query
            return result;
            
        } catch(Exception e) {
            System.out.println(e);
        }
        return result;
    }

    static Person login(
        String username,
        String password
    ) {
        Person result = null;
        try {
            
            String query = "SELECT * "
                            + "FROM users WHERE "
                            + "username='" + username 
                            + "' AND " 
                            + "password='" + password
                            + "'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultUsername = resultSet.getString(2);
                String resultPassword = resultSet.getString(3);
                String resultNama = resultSet.getString(4);
                String resultAlamat = resultSet.getString(5);
                String resultNoTelp = resultSet.getString(6);
                String resultOtorisasi = resultSet.getString(7);
                result = new Person(
                    resultId,
                    resultUsername,
                    resultPassword,
                    resultOtorisasi,
                    resultNama,
                    resultAlamat,
                    resultNoTelp
                );
                result.statusLogin(resultOtorisasi);
                return result;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    static String addUser(
        String username,
        String password,
        String nama,
        String alamat,
        String noTelp
    ) {
       try {
            final UUID uuid = UUID.randomUUID();
            final String id = "user-" + uuid.toString();
            String query = "INSERT INTO users "
                            + "(id, username, password, nama, alamat, noTelp, otorisasi) "
                            + "VALUES ("
                            + "'" + id + "', "
                            + "'" + username + "', "
                            + "'" + password + "', "
                            + "'" + nama + "', "
                            + "'" + alamat + "', "
                            + "'" + noTelp + "', "
                            + "'user'"
                            + ")";
            int result = update(query);
            if(result != 0) {
                return "BERHASIL MENDAFTARKAN AKUN";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "AKUN GAGAL DIDAFTARKAN, DATA YANG DIBERIKAN TIDAK BENAR";
    }
    
    static void getUsers(ArrayList<Person> n) {
        try {           
            String leftAlignFormat = "| %-3s | %-15s | %-15s | %-15s | %-15s | %-32s | %-43s |%n";
            System.out.format("+-----+-----------------+-----------------+-----------------+-----------------+----------------------------------+---------------------------------------------+%n");
            System.out.format("| No  | Username        | Password        | Nama            | No Telepon      |  Alamat                          | ID                                          |%n");
            System.out.format("+-----+-----------------+-----------------+-----------------+-----------------+----------------------------------+---------------------------------------------+%n");
            for (int i = 0; i < n.size(); i++) {
                System.out.format(
                        leftAlignFormat, 
                        i+1,
                        n.get(i).getUsername(), 
                        n.get(i).getPassword(), 
                        n.get(i).getNama(), 
                        n.get(i).getNoTelp(), 
                        n.get(i).getAlamat(), 
                        n.get(i).getId()
                    );
            }
            System.out.format("+-----+-----------------+-----------------+-----------------+-----------------+----------------------------------+---------------------------------------------+%n");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    static Person getUserById(String id) {
        Person result = null;
        try {
            String query = "SELECT * FROM users WHERE id='" + id + "'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultUsername = resultSet.getString(2);
                String resultPassword = resultSet.getString(3);
                String resultNama = resultSet.getString(4);
                String resultAlamat = resultSet.getString(5);
                String resultNoTelp = resultSet.getString(6);
                String resultOtorisasi = resultSet.getString(7);
                result = new Person(
                    resultId,
                    resultUsername,
                    resultPassword,
                    resultOtorisasi,
                    resultNama,
                    resultAlamat,
                    resultNoTelp
                );
                return result;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    static String updateUserById(String id, String username, String password, String nama, String alamat, String noTelp) {
        try {
            String query = "UPDATE users SET "
                    + "username='" + username + "', "
                    + "password='" + password + "', "
                    + "nama='" + nama + "', "
                    + "alamat='" + alamat + "', "
                    + "noTelp='" + noTelp + "' "
                    + "WHERE id='" + id + "'";
            int result = update(query);
                
            if(result != 0) {
                return "BERHASIL MEMPERBARUI AKUN";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "GAGAL MEMPERBARUI AKUN, TERJADI KEGAGALAN PADA SERVER";
    }
    
    static String deleteUserById(String id) {
        try {
            String query = "DELETE FROM users WHERE id='" + id + "'";
            int result = update(query);
                
            if(result != 0) {
                return "BERHASIL MENGHAPUS AKUN";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "GAGAL MENGHAPUS AKUN, TERJADI KEGAGALAN PADA SERVER";
    }
    
    public static void main(String[] args) {              
        // declare variable
        Scanner myObj = new Scanner(System.in);
        ArrayList<Person> dataPerson = new ArrayList<>();
        boolean repeat = true, repeat2 = true;
        String pil, pil2, pil3, pil4, pil5;
        String username, password, nama, alamat, noTelp;
        
        // sebelum jalankan aplikasinya, masukkan dulu data dari db ke arraylist
        try {
            String query = "SELECT * FROM users WHERE otorisasi='user'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultUsername = resultSet.getString(2);
                String resultPassword = resultSet.getString(3);
                String resultNama = resultSet.getString(4);
                String resultAlamat = resultSet.getString(5);
                String resultNoTelp = resultSet.getString(6);
                String resultOtorisasi = resultSet.getString(7);
                Person users = new Person(
                    resultId,
                    resultUsername,
                    resultPassword,
                    resultOtorisasi,
                    resultNama,
                    resultAlamat,
                    resultNoTelp
                );                             
                dataPerson.add(users);
                resultId = null;
                resultUsername = null;
                resultPassword = null;
                resultNama = null;
                resultAlamat = null;
                resultNoTelp = null;
                resultOtorisasi = null;
            }

        } catch(Exception e) {
            System.out.println(e);
        }
        
        while(repeat) {
            System.out.println("===== TOKO GAME ETAM =====");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Keluar");
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
                    Person result = login(username, password);
                    username = null;
                    password = null;
                    
                    if (result == null) {
                        System.out.print("KREDENSIAL YANG DIBERIKAN TIDAK BENAR");
                        continue;
                    } else {
                        if (result.getOtorisasi().equals("admin")) {
                            //menu admin
                            System.out.println("=== SELAMAT DATANG ADMIN === ");
                            while(repeat2) {
                                menuAdmin();
                                System.out.print("Masukkan Pilihan: ");
                                pil2 = myObj.nextLine();
                                switch(pil2) {
                                    case "1" -> {
                                        templateAdmin2("Game");
                                        System.out.print("Masukkan Pilihan: ");
                                        pil3 = myObj.nextLine();
                                        break;
                                    }
                                    case "2" -> {
                                        templateAdmin1("User");
                                        System.out.print("Masukkan Pilihan: ");
                                        pil3 = myObj.nextLine();
                                        switch(pil3) {
                                            case "1" -> {
                                                getUsers(dataPerson);
                                                System.out.print("\nTekan Untuk Melanjutkan...");
                                                myObj.nextLine();
                                            }
                                            case "2" -> {
                                                getUsers(dataPerson);
                                                System.out.println("Pilih No. User yang mau diubah.");
                                                System.out.print("Masukkan Pilihan: ");
                                                pil4 = myObj.nextLine();
                                                
                                                Person selectedUser = getUserById(dataPerson.get(Integer.parseInt(pil4)-1).getId());
                                                System.out.println("\n\n=== Data User yang anda pilih ===");
                                                System.out.println("ID          : " + selectedUser.getId());
                                                System.out.println("Username    : " + selectedUser.getUsername());
                                                System.out.println("Password    : " + selectedUser.getPassword());
                                                System.out.println("Nama        : " + selectedUser.getNama());
                                                System.out.println("Alamat      : " + selectedUser.getAlamat());
                                                System.out.println("NoTelp      : " + selectedUser.getNoTelp());
                                                
                                                System.out.println("\n\n=== Silahkan Masukkan Data Baru User ===");
                                                
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
                                                
                                                System.out.println(updateUserById(selectedUser.getId(), username, password, nama, alamat, noTelp));
                                                
                                                pil4 = null;
                                                username = null;
                                                password = null;
                                                nama = null;
                                                alamat = null;
                                                noTelp = null;
                                                break;
                                            }
                                            case "3" -> {
                                                getUsers(dataPerson);
                                                System.out.println("Pilih No. User yang mau dihapus.");
                                                System.out.print("Masukkan Pilihan: ");
                                                pil4 = myObj.nextLine();
                                                
                                                Person selectedUser = getUserById(dataPerson.get(Integer.parseInt(pil4)-1).getId());
                                                System.out.println("\n\n=== Data User yang anda pilih ===");
                                                System.out.println("ID          : " + selectedUser.getId());
                                                System.out.println("Username    : " + selectedUser.getUsername());
                                                System.out.println("Password    : " + selectedUser.getPassword());
                                                System.out.println("Nama        : " + selectedUser.getNama());
                                                System.out.println("Alamat      : " + selectedUser.getAlamat());
                                                System.out.println("NoTelp      : " + selectedUser.getNoTelp());
                                                
                                                System.out.println("Apakah anda yakin ingin menghapus user ini ? (Y/N)");
                                                System.out.print("Masukkan Pilihan: ");
                                                pil5 = myObj.nextLine();
                                                if (pil5.toLowerCase().equals("y")){
                                                    System.out.println(deleteUserById(selectedUser.getId()));
                                                } else {
                                                    continue;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    case "3" -> {
                                        templateAdmin2("Riwayat Pembelian");
                                        System.out.print("Masukkan Pilihan: ");
                                        pil3 = myObj.nextLine();
                                        break;
                                    }
                                }
                                
                            }
                        }
                    }
                    break;
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
                    System.out.println(addUser(username, password, nama, alamat, noTelp));
                    username = null;
                    password = null;
                    nama = null;
                    alamat = null;
                    noTelp = null;
                    break;
                }
                case "3" -> {
                    System.out.println("===========KELUAR===========");
                    System.out.println("Terima Kasih Telah Menggunakan Aplikasi");
                    // close scanner
                    myObj.close();
                    // end program
                    repeat = false;
                    break;
                }
            }
        }
    }
    
}
