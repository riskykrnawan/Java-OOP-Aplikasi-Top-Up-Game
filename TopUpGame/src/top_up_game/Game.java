
package top_up_game;
import java.util.UUID;

class Game {
    private final UUID uuid = UUID.randomUUID();
    private final String idGame = "game-" + uuid.toString();

    private String namaGame, tanggalRilis;
    private int hargaGame, ratingGame;

    public Game(String namaGame, String tanggalRilis, int hargaGame, int ratingGame) {
        this.namaGame = namaGame;
        this.tanggalRilis = tanggalRilis;
        this.hargaGame = hargaGame;
        this.ratingGame = ratingGame;
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
    
}