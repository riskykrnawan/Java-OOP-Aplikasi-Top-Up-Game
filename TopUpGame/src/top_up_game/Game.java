
package top_up_game;
//import java.util.UUID;

import java.sql.ResultSet;
import java.util.ArrayList;

import static top_up_game.Main.query;
import static top_up_game.Main.update;


class Game {
//    private final UUID uuid = UUID.randomUUID();
//    private final String idGame = "game-" + uuid.toString();

    private String id, nama, deskripsi;

    public Game(String id, String nama, String deskripsi) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    
    static String addGame(
            String id,
            String nama,
            String deskripsi
        ) {
        try {
            String query = "INSERT INTO games "
                            + "(id, nama, deskripsi) "
                            + "VALUES ("
                            + "'" + id + "', "
                            + "'" + nama + "', "
                            + "'" + deskripsi + "'"
                            + ")";
            int result = update(query);
            if(result != 0) {
                return "BERHASIL MENAMBAHKAN GAME";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "GAME GAGAL DITAMBAHKAN, DATA YANG DIBERIKAN TIDAK BENAR";
    }
    
    static void getGames(ArrayList<Game> n) {
        try {           
            n.clear();
            String query = "SELECT * FROM games";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultNama = resultSet.getString(2);
                String resultDeskripsi = resultSet.getString(3);
                Game games = new Game(
                    resultId,
                    resultNama,
                    resultDeskripsi
                );                             
                n.add(games);
                resultId = null;
                resultNama = null;
                resultDeskripsi = null;
            }
            String leftAlignFormat = "| %-3s | %-25s | %-43s | %-43s |%n";
            System.out.format("+-----+---------------------------+---------------------------------------------+---------------------------------------------+%n");
            System.out.format("| No  | Nama Game                 | Deskripsi                                   |  ID                                         |%n");
            System.out.format("+-----+---------------------------+---------------------------------------------+---------------------------------------------+%n");
            for (int i = 0; i < n.size(); i++) {
                System.out.format(
                        leftAlignFormat, 
                        i+1,
                        n.get(i).getNama(), 
                        n.get(i).getDeskripsi(), 
                        n.get(i).getId()
                    );
            }
            System.out.format("+-----+---------------------------+---------------------------------------------+---------------------------------------------+%n");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    static String getNamaGame(ArrayList<Game> n, int nomor) {
        String namaGame = null;
        try {           
            n.clear();
            String query = "SELECT * FROM games";
            ResultSet resultSet = query(query);
            for(int i = 0; i < nomor; i++) {
                resultSet.next();
                namaGame = resultSet.getString(2);
            }
            return namaGame;
        } catch(Exception e) {
            System.out.println(e);
            return namaGame;
        }
    }
    
    static Game getGameById(String id) {
        Game result = null;
        try {
            String query = "SELECT * FROM games WHERE id='" + id + "'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultNama = resultSet.getString(2);
                String resultDeskripsi = resultSet.getString(3);
                result = new Game(
                    resultId,
                    resultNama,
                    resultDeskripsi
                );
                return result;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    static String updateGameById(String id, String nama, String deskripsi) {
        try {
            String query = "UPDATE games SET "
                    + "nama='" + nama + "', "
                    + "deskripsi='" + deskripsi + "', "
                    + "WHERE id='" + id + "'";
            int result = update(query);
                
            if(result != 0) {
                return "BERHASIL MEMPERBARUI GAME";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "GAGAL MEMPERBARUI GAME, TERJADI KEGAGALAN PADA SERVER";
    }
    
    static String deleteGameById(String id) {
        try {
            String query = "DELETE FROM games WHERE id='" + id + "'";
            int result = update(query);
                
            if(result != 0) {
                return "BERHASIL MENGHAPUS GAME";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "GAGAL MENGHAPUS GAME, TERJADI KEGAGALAN PADA SERVER";
    }    

    static void topUp(String game, int voucher, String idgame) {
        // informasi berhasil top up
    }

}