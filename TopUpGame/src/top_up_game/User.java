package top_up_game;

abstract class User implements UserInterface {
    private String nama, alamat, noTelp, otorisasi;

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
