
package top_up_game;
import java.util.UUID;

class Game {
    private final UUID uuid = UUID.randomUUID();
    private final String idGame = "game-" + uuid.toString();

    private String namaGame, tanggalRilis;
    private int hargaGame, ratingGame, stokGame;

    public Game(String namaGame, String tanggalRilis, int hargaGame, int ratingGame, int stokGame) {
        this.namaGame = namaGame;
        this.tanggalRilis = tanggalRilis;
        this.hargaGame = hargaGame;
        this.ratingGame = ratingGame;
        this.stokGame = stokGame;
    }

    public String getIdGame() {
        return idGame;
    }

    public String getNamaGame() {
        return namaGame;
    }

    public String getTanggalRilis() {
        return tanggalRilis;
    }

    public int getHargaGame() {
        return hargaGame;
    }

    public int getRatingGame() {
        return ratingGame;
    }

    public int getStokGame() {
        return stokGame;
    }

    public void setNamaGame(String namaGame) {
        this.namaGame = namaGame;
    }

    public void setTanggalRilis(String tanggalRilis) {
        this.tanggalRilis = tanggalRilis;
    }

    public void setHargaGame(int hargaGame) {
        this.hargaGame = hargaGame;
    }

    public void setRatingGame(int ratingGame) {
        this.ratingGame = ratingGame;
    }

    public void setStokGame(int stokGame) {
        this.stokGame = stokGame;
    }
    
}