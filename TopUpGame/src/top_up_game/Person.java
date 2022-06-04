/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top_up_game;
/**
 *
 * @author Lenovo-PC
 */
public class Person extends User {
    // constructor
    public Person (
            String id,
            String username,
            String password,
            String otorisasi,
            String nama,
            String alamat,
            String noTelp
    ) {
        super(id, username, password, otorisasi, nama, alamat, noTelp);
    }
    
    public String getId() {
        return id;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
    
    public void setOtorisasi(String otorisasi) {
        this.otorisasi = otorisasi;
    }    
    
    public String getOtorisasi() {
        return otorisasi;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getPassword() {
        return password;
    }
   
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getNama() {
        return nama;
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
    
    @Override
    public String statusLogin(String otorisasi) {
        return "Berhasil login sebagai " + otorisasi;
    }
}