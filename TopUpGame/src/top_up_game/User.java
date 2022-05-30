package top_up_game;

import java.util.UUID;

abstract class User {
    private String nama, alamat, noTelp, otorisasi;
    private final UUID uuid = UUID.randomUUID();
    private final String id = "admin-" + uuid.toString();

    // constructor
    public User (
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
