/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top_up_game;

import java.util.UUID;

/**
 *
 * @author Lenovo-PC
 */
public class Admin {
    private String nama, alamat, noTelp, otorisasi;
    private final UUID uuid = UUID.randomUUID();
    private final String id = "admin-" + uuid.toString();

    // constructor
    public Admin(
            String nama,
            String alamat,
            String noTelp
    ) {
        this.nama = nama;
        this.alamat = alamat;
        this.noTelp = noTelp;
    }

    public String getId(){
        return id;
    }
    public String getNama(){
        return nama;
    }
    public String getAlamat(){
        return alamat;
    }
    public String getOtorisasi(){
        return otorisasi;
    }
    public String getNoTelp(){
        return noTelp;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
}
