package top_up_game;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;
import static top_up_game.Main.query;
import static top_up_game.Main.update;

abstract class User {
    protected String id, username, password, otorisasi, nama, alamat, noTelp;

    // constructor
    public User (
            String id,
            String username,
            String password,
            String otorisasi,
            String nama,
            String alamat,
            String noTelp
    ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.otorisasi = otorisasi;
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setOtorisasi(String otorisasi) {
        this.otorisasi = otorisasi;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getId(String id) {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getOtorisasi() {
        return otorisasi;
    }

    public String getPassword() {
        return password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    
    public String statusLogin(String otorisasi) {
        return "Berhasil login sebagai " + otorisasi;
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
    
    static void getUsers(ArrayList<Person> n) {
        try {           
            n.clear();
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
                n.add(users);
                resultId = null;
                resultUsername = null;
                resultPassword = null;
                resultNama = null;
                resultAlamat = null;
                resultNoTelp = null;
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
    

}