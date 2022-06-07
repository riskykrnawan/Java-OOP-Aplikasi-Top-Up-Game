
package top_up_game;

abstract class Person {
    protected String username, password, otorisasi, nama, alamat, noTelp;

    // constructor
    public Person (
            String username,
            String password
    ) {
        this.username = username;
        this.password = password;
    }
    
    public void setUsername(String username) {
        this.username = username;
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

    public String getPassword() {
        return password;
    }
    
    public String getOtorisasi() {
        return otorisasi;
    }

    public void setOtorisasi(String otorisasi) {
        this.otorisasi = otorisasi;
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
}
