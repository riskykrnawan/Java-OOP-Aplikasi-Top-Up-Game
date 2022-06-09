package top_up_game;


import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;
import static top_up_game.Voucher.getVoucher;
//import static top_up_game.Voucher.deleteVoucherById;
//import static top_up_game.Voucher.getVoucherById;
//import static top_up_game.Voucher.updateVoucherById;


public class Main {
    static final String URL = "jdbc:mysql://localhost:3306/toko_game";
    static final String USERNAME = "root";
    static final String PASSWORD = "";

    static void templateAdmin1(String str) {
        System.out.println("1. Lihat Data " + str);
        System.out.println("2. Ubah Data " + str);
        System.out.println("3. Hapus Data " + str);
        System.out.println("0. Kembali");
    }
    static void templateAdmin2(String str) {
        System.out.println("1. Lihat Data " + str);
        System.out.println("2. Tambah Data " + str);
        System.out.println("3. Ubah Data " + str);
        System.out.println("4. Hapus Data " + str);
        System.out.println("0. Kembali");
    }
    static void menuAdmin() {
        System.out.println("1. Pengelolaan Data Game");
        System.out.println("2. Pengelolaan Data User");
        System.out.println("3. Pengelolaan Data Voucher");
        System.out.println("4. Pengelolaan Data Riwayat Pembelian");
        System.out.println("0. Logout");
    }

    static void menuUser() {
        System.out.println("1. Lihat Data Game");
        System.out.println("2. Edit Akun");
        System.out.println("0. Logout");
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
    
    // Users
    // ini dipakai pas daftar akun user di menu 2
    static String addUser(
            String id,
            String username,
            String password,
            String nama,
            String alamat,
            String noTelp
        ) {
        try {
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
    
    static void getUsers(ArrayList<Customer> n) {
        try {           
            n.clear();
            String query = "SELECT * FROM users";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultUsername = resultSet.getString(2);
                String resultPassword = resultSet.getString(3);
                String resultNama = resultSet.getString(4);
                String resultAlamat = resultSet.getString(5);
                String resultNoTelp = resultSet.getString(6);
                String resultOtorisasi = resultSet.getString(7);
                Customer user = new Customer(
                    resultUsername,
                    resultPassword
                );
                user.setId(resultId);
                user.setNama(resultNama);
                user.setNoTelp(resultNoTelp);
                user.setAlamat(resultAlamat);
                user.setOtorisasi(resultOtorisasi);
                n.add(user);
                resultId = null; resultUsername = null; resultPassword = null;
                resultNama = null; resultAlamat = null; resultNoTelp = null;
                resultOtorisasi = null;
            }
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
    
    static Customer getUserById(String id) {
        Customer user = null;
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
                user = new Customer(
                    resultUsername,
                    resultPassword
                );
                user.setId(resultId);
                user.setNama(resultNama);
                user.setNoTelp(resultNoTelp);
                user.setAlamat(resultAlamat);
                user.setOtorisasi(resultOtorisasi);
                return user;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return user;
    }
    
    static String getUserIdByUsername(String username) {
        try {
            String query = "SELECT id FROM users WHERE username='" + username + "'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                return resultId;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "";
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
        return "GAGAL MEMPERBARUI AKUN, TERJADI KESALAHAN";
    }
    
    static String deleteUserById(String id) {
        try {
            String query = "DELETE FROM users WHERE id='" + id + "'";
            int result = update(query);
                
            if(result != 0) {
                return "BERHASIL MENGHAPUS AKUN";
            }
          //ErHand
        } catch(Exception e) {
            System.out.println(e);
        }
        return "GAGAL MENGHAPUS AKUN, TERJADI KEGAGALAN PADA SERVER";
    }
    
    
//  MENU
    public static void main(String[] args) {              
        // declare variable
        Scanner myObj = new Scanner(System.in);
        
        ArrayList<Customer> dataCustomer = new ArrayList<>();
        ArrayList<Admin> dataAdmin = new ArrayList<>();
        ArrayList<Game> dataGames = new ArrayList<>();
        ArrayList<Voucher> dataVouchers = new ArrayList<>();
        
        boolean repeat = true;
        String pil, pil2, pil3, pil4, pil5;
        String username, password, nama, alamat, noTelp;
        String deskripsi;
        String result;
        String credentialId;
        String idGame;
        String nominalVoucher;
        int hargaVoucher;
        
        String[] metodePembayaran = {"gopay","OVO","DANA","ATM","Wallet", "Link Aja","Alfamart","Indomaret"};
        // ArrayList<String> metodePembayaran = new ArrayList<String>(Arrays.asList(pembayaran));
        // metodePembayaran.addAll(Arrays.asList(pembayaran));
        
//      MENU
        while(repeat) {
            System.out.println("\n===== TOKO GAME ETAM =====");
            System.out.println("1. Login"); // ini kalo login admin sama user campur
            System.out.println("2. Register");
            System.out.println("0. Keluar");
            System.out.println("==========================");
            System.out.print("Masukkan Pilihan: ");
            pil = myObj.nextLine();
            
            switch(pil) {
                case "1" -> {
                    System.out.println("\n===========LOGIN===========");
                    System.out.print("Username: ");
                    username = myObj.nextLine();
                    System.out.print("Password: ");
                    password = myObj.nextLine();
                    //setelah user nginputkan data login, jalankan fungsi login
                    // disini tenpat login sebagai admin atau user
                    if (username.equals("admin")) {
                        Admin user = new Admin(username, password);
                        result = user.login(username, password);
                    } else {
                        Customer user = new Customer(username, password);
                        result = user.login(username, password);
                    }
                    credentialId = getUserIdByUsername(username);
                    username = null; password = null;
                    
                    // ErHand
                    if (result == null) {
                        System.out.println("KREDENSIAL YANG DIBERIKAN TIDAK BENAR");
                        continue;
                    } else {
                        if (result.equals("admin") && credentialId != "") {
                           
                            //MENU ADMIN
                            System.out.println("=== SELAMAT DATANG ADMIN === ");
                            boolean repeat2 = true;
                            while(repeat2) {
                                menuAdmin();
                                System.out.print("Masukkan Pilihan: ");
                                pil2 = myObj.nextLine();
                                switch(pil2) {
                                    case "1" -> {
                                        boolean repeat3 = true;
                                        while(repeat3) {
                                            templateAdmin2("Game");
                                            System.out.print("Masukkan Pilihan: ");
                                            pil3 = myObj.nextLine();
                                            switch(pil3) {
                                                case "1" -> {
                                                    // read game
                                                    Game.getGames(dataGames);
                                                    System.out.print("\nTekan Untuk Melanjutkan...");
                                                    myObj.nextLine();
                                                }
                                                case "2" -> {
                                                    // add game
                                                    System.out.print("Nama: ");
                                                    nama = myObj.nextLine();
                                                    System.out.print("Deskripsi: ");
                                                    deskripsi = myObj.nextLine();
    
                                                    final UUID uuid = UUID.randomUUID();
                                                    final String id = "game-" + uuid.toString();
                                                    
                                                    System.out.println(Game.addGame(id, nama, deskripsi));
                                                    nama = null;
                                                    deskripsi = null;
                                                    break;
                                                }
                                                case "3" -> {
                                                    // update game
                                                    Game.getGames(dataGames);
                                                    System.out.println("Pilih No. game yang ingin diubah.");
                                                    System.out.print("Masukkan Pilihan: ");
                                                    pil4 = myObj.nextLine();

                                                    Game selectedGame = Game.getGameById(dataGames.get(Integer.parseInt(pil4)-1).getId());
                                                    System.out.println("\n\n=== Data Game yang anda pilih ===");
                                                    System.out.println("ID        : " + selectedGame.getId());
                                                    System.out.println("Nama      : " + selectedGame.getNama());
                                                    System.out.println("Deskripsi : " + selectedGame.getDeskripsi());

                                                    System.out.println("\n\n=== Masukkan Data Baru Game ===");
                                                    System.out.print("Nama: ");
                                                    nama = myObj.nextLine();
                                                    System.out.print("Deskripsi: ");
                                                    deskripsi = myObj.nextLine();

                                                    System.out.println(Game.updateGameById(selectedGame.getId(), nama, deskripsi));
                                                    nama = null;
                                                    deskripsi = null;
                                                    break;
                                                }
                                                case "4" -> {
                                                    // delete game
                                                    Game.getGames(dataGames);
                                                    System.out.println("Pilih No. game yang ingin dihapus.");
                                                    System.out.print("Masukkan Pilihan: ");
                                                    pil4 = myObj.nextLine();

                                                    Game selectedGame = Game.getGameById(dataGames.get(Integer.parseInt(pil4)-1).getId());
                                                    System.out.println("\n\n=== Data Game yang anda pilih ===");
                                                    System.out.println("ID        : " + selectedGame.getId());
                                                    System.out.println("Nama      : " + selectedGame.getNama());
                                                    System.out.println("Deskripsi : " + selectedGame.getDeskripsi());

                                                    System.out.println("Apakah anda yakin ingin menghapus user ini ? (Y/N)");
                                                    System.out.print("Masukkan Pilihan: ");
                                                    pil5 = myObj.nextLine();
                                                    if (pil5.equals("Y") || pil5.equals("y")) {
                                                        System.out.println(Game.deleteGameById(selectedGame.getId()));
                                                    } else {
                                                        continue;
                                                    }
                                                }
                                                case "0" -> {
                                                    repeat3 = false;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    case "2" -> { // Pengelolaan Data User
                                        boolean repeat3 = true;
                                        while(repeat3) {
                                            templateAdmin1("User");
                                            System.out.print("Masukkan Pilihan: ");
                                            pil3 = myObj.nextLine();
                                            switch(pil3) {
                                                case "1" -> {
                                                    // read user
                                                    getUsers(dataCustomer);
                                                    System.out.print("\nTekan Untuk Melanjutkan...");
                                                    myObj.nextLine();
                                                }
                                                case "2" -> {
                                                    // update user
                                                    getUsers(dataCustomer);
                                                    System.out.println("Pilih No. User yang mau diubah.");
                                                    System.out.print("Masukkan Pilihan: ");
                                                    pil4 = myObj.nextLine();
                                                    
                                                    Customer selectedUser = getUserById(dataCustomer.get(Integer.parseInt(pil4)-1).getId());
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
                                                    
                                                    pil4 = null; username = null; password = null;
                                                    nama = null; alamat = null; noTelp = null;
                                                    break;
                                                }
                                                case "3" -> {
                                                    // delete user
                                                    getUsers(dataCustomer);
                                                    System.out.println("Pilih No. User yang mau dihapus.");
                                                    System.out.print("Masukkan Pilihan: ");
                                                    pil4 = myObj.nextLine();
                                                    
                                                    Customer selectedUser = getUserById(dataCustomer.get(Integer.parseInt(pil4)-1).getId());
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
                                                case "0" -> {
                                                    repeat3 = false;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    case "3" -> { // Pengelolaan Data Voucher
     
                                        boolean repeat3 = true;
                                        while(repeat3) {
                                            templateAdmin2("Riwayat Pembelian");
                                            System.out.print("Masukkan Pilihan: ");
                                            pil3 = myObj.nextLine();
                                            switch(pil3) {
                                                case "1" -> {
                                                    // read vouchers
                                                    getVoucher(dataVouchers);
                                                    System.out.print("\nTekan Untuk Melanjutkan...");
                                                    myObj.nextLine();
                                                    break;
                                                }
                                                case "2" -> {
                                                    // add vouchers
                                                    System.out.print("ID Game : ");
                                                    idGame = myObj.nextLine();
                                                    System.out.print("Nominal : ");
                                                    nominalVoucher = myObj.nextLine();
                                                    System.out.print("Harga   : ");
                                                    hargaVoucher = myObj.nextInt();
                                                    myObj.nextLine();
    
                                                    final UUID uuid = UUID.randomUUID();
                                                    final String id = "game-" + uuid.toString();
                                                    
                                                    System.out.println(Voucher.addVoucher(id, idGame, nominalVoucher, hargaVoucher)); 
                                                    idGame = null;
                                                    nominalVoucher = null;
                                                    hargaVoucher = 0;
                                                    break;
                                                }
                                                case "3" -> {
                                                    // update voucher
                                                    getVoucher(dataVouchers);
                                                    System.out.println("Pilih No. Voucher yang mau diubah.");
                                                    System.out.print("Masukkan Pilihan: ");
                                                    pil4 = myObj.nextLine();
                                                    
                                                    Voucher selectedVoucher = Voucher.getVoucherById(dataVouchers.get(Integer.parseInt(pil4)-1).getId());
                                                    System.out.println("\n\n=== Data Voucher yang anda pilih ===");
                                                    System.out.println("ID          : " + selectedVoucher.getId());
                                                    System.out.println("ID Game     : " + selectedVoucher.getIdGame());
                                                    System.out.println("Nominal     : " + selectedVoucher.getNominalVoucher());
                                                    System.out.println("Harga       : " + selectedVoucher.getHargaVoucher());
                                                    
                                                    System.out.println("\n\n=== Silahkan Masukkan Data Baru Voucher ===");
                                                    System.out.print("ID Game: ");
                                                    idGame = myObj.nextLine();
                                                    System.out.print("Nominal: ");
                                                    nominalVoucher = myObj.nextLine();
                                                    System.out.print("Harga  : ");
                                                    hargaVoucher = myObj.nextInt();
                                                    myObj.nextLine();
                                                    
                                                    System.out.println(Voucher.updateVoucherById(selectedVoucher.getId(), idGame, nominalVoucher, hargaVoucher)); // ini klo gk pke Voucher. jg bsa tapi import updateVoucherById nya
                                                    
                                                    pil4 = null; idGame = null; nominalVoucher = null; hargaVoucher = 0;
                                                    break;
                                                }
                                                case "4" -> {
                                                    // delete voucher
                                                    getVoucher(dataVouchers);
                                                    System.out.println("Pilih No. Voucher yang mau dihapus.");
                                                    System.out.print("Masukkan Pilihan: ");
                                                    pil4 = myObj.nextLine();
                                                    
                                                    Voucher selectedVoucher = Voucher.getVoucherById(dataVouchers.get(Integer.parseInt(pil4)-1).getId()); // ini klo gk pke Voucher. jg bsa tapi import getVoucherById nya
                                                    System.out.println("\n\n=== Data Voucher yang anda pilih ===");
                                                    System.out.println("ID          : " + selectedVoucher.getId());
                                                    System.out.println("ID Game     : " + selectedVoucher.getIdGame());
                                                    System.out.println("Nominal     : " + selectedVoucher.getNominalVoucher());
                                                    System.out.println("Harga       : " + selectedVoucher.getHargaVoucher());
                                                    
                                                    System.out.println("Apakah anda yakin ingin menghapus voucher ini ? (Y/N)");
                                                    System.out.print("Masukkan Pilihan: ");
                                                    pil5 = myObj.nextLine();
                                                    if (pil5.toLowerCase().equals("y")){
                                                        System.out.println(Voucher.deleteVoucherById(selectedVoucher.getId())); // ini klo gk pke Voucher. jg bsa tapi import deleteVoucherById nya
                                                    } else {
                                                        continue;
                                                    }
                                                }
                                                case "0" -> {
                                                    repeat3 = false;
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                    case "0" -> {
                                        System.out.println("Keluar akun...");
                                        repeat2 = false;
                                        break;
                                    }
                                }
                                
                            }
                        } else if (result.equals("user") && credentialId != "") {
                            
                            // MENU USER
                            System.out.println("=== SELAMAT DATANG USER === ");
                            boolean repeat2 = true;
                            while(repeat2) {
                                menuUser();
                                System.out.print("Masukkan Pilihan: ");
                                pil2 = myObj.nextLine();
                                switch(pil2) {
                                    
                                    // Lihat game dan juga TopUp
                                    case "1" -> {
                                        System.out.println("\n\n=== Silahkan Lakukan TopUp ===");
                                        Game.getGames(dataGames);

                                        // milih game
                                        System.out.print("Pilih nomor game : ");
                                        String numb = myObj.nextLine();
                                        String game = Game.getNamaGame(dataGames, Integer.parseInt(numb));

                                        // milih jumlah topup
                                        // Voucher.getVoucher(dataVoucher);
                                        System.out.print("Pilih nominal TopUp : ");
                                        String voucher = myObj.nextLine();

                                        // milih metode topUp
                                        for(int i = 0; i < metodePembayaran.length; i++) {
                                            System.out.println((i+1) + ". " + metodePembayaran[i]);
                                        }
                                        System.out.print("Pilih metode pembayaran anda : ");
                                        String nomor = myObj.nextLine();
                                        String metode = metodePembayaran[Integer.parseInt(nomor)-1];

                                        // input id game user
                                        System.out.print("Masukkan id game anda : ");
                                        String idgame = myObj.nextLine();

                                        Game.topUp(game, voucher, metode, idgame);
                                        break;
                                    }
                                    
                                    // Edit akun (username, password, nama, alamat, notelp)
                                    case "2" -> {
                                        Customer selectedUser = getUserById(credentialId);
                                        System.out.println("\n\n=== Data Anda Sebelumnya ===");
                                        System.out.println("ID          : " + selectedUser.getId());
                                        System.out.println("Username    : " + selectedUser.getUsername());
                                        System.out.println("Password    : " + selectedUser.getPassword());
                                        System.out.println("Nama        : " + selectedUser.getNama());
                                        System.out.println("Alamat      : " + selectedUser.getAlamat());
                                        System.out.println("NoTelp      : " + selectedUser.getNoTelp());
                                        
                                        
                                        System.out.println("\n\n=== Silahkan Masukkan Data Baru Akun Anda ===");
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

                                        System.out.println(updateUserById(credentialId, username, password, nama, alamat, noTelp));
                                        break;                                        
                                    }
                                    case "0" -> {
                                        System.out.println("Keluar akun...");
                                        repeat2 = false;
                                        break;
                                    } 
                                }
                            }
                        }
                    }
                    break;
                }
                
                // Buat akun
                case "2" -> {
                    System.out.println("\n==========REGISTER==========");
                    
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
                    
                    final UUID uuid = UUID.randomUUID();
                    final String id = "user-" + uuid.toString();
                    
                    //setelah user nginputkan data register, jalankan fungsi register
                    System.out.println(addUser(id, username, password, nama, alamat, noTelp));
                    username = null;
                    password = null;
                    nama = null;
                    alamat = null;
                    noTelp = null;
                    break;
                }
                case "0" -> {
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
