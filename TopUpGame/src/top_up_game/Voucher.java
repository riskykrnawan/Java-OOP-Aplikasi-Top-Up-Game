
package top_up_game;

import java.sql.ResultSet;
import java.util.ArrayList;
import static top_up_game.Main.query; // L81 error klo gk pke ini
import static top_up_game.Main.update;


class Voucher {
    private String id,idGame;
    private int nominalVoucher, hargaVoucher;  
    
    public Voucher (String id, String idGame,int nominalVoucher, int hargaVoucher ){
        this.id = id;
        this.idGame = idGame;
        this.nominalVoucher = nominalVoucher;
        this.hargaVoucher = hargaVoucher;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdGame() {
        return idGame;
    }

    public int getNominalVoucher() {
        return nominalVoucher;
    }

    public void setNominalVoucher(int nominalVoucher) {
        this.nominalVoucher = nominalVoucher;
    }

    public int getHargaVoucher() {
        return hargaVoucher;
    }

    public void setHargaVoucher(int hargaVoucher) {
        this.hargaVoucher = hargaVoucher;
    }
    
    
    
    static String addVoucher(
            String id,     
            String idGame,
            int nominalVoucher,
            int hargaVoucher
        ) {
        try {
            String query = "INSERT INTO vouchers "
                            + "(id, idGame, nominalVoucher, hargaVoucher) "
                            + "VALUES ("
                            + "'" + id + "', "
                            + "'" + idGame + "', "
                            + "'" + nominalVoucher + "', "
                            + "'" + hargaVoucher + "'"
                            + ")";
            int result = update(query);
            if(result != 0) {
                return "BERHASIL MENAMBAHKAN VOUCHER";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "VOUCHER GAGAL DITAMBAHKAN, DATA YANG DIBERIKAN TIDAK BENAR";
    }
    
    
    static void getVoucher(ArrayList<Voucher> n) {
        try {           
            n.clear();
            String query = "SELECT * FROM vouchers";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultIdGame = resultSet.getString(2);
                int resultNominalVoucher = resultSet.getInt(3);
                int resultHargaVoucher = resultSet.getInt(4);
                Voucher vouchers = new Voucher(
                    resultId,
                    resultIdGame,
                    resultNominalVoucher,
                    resultHargaVoucher
                );                             
                n.add(vouchers);
                resultId = null;
                resultIdGame = null;
//                resultNominalVoucher = null;
//                resultHargaVoucher = null;
            }
            String leftAlignFormat = "| %-3s | %-25s | %-80s | %-43s |%n";
            System.out.format("+-----+---------------------------+-------------------------------------+--------------------------------------------+---------------------------------------------+%n");
            System.out.format("| No  | ID Game                   | Nominal                             | Harga                                      |  ID                                         |%n");
            System.out.format("+-----+---------------------------+-------------------------------------+--------------------------------------------+---------------------------------------------+%n");
            for (int i = 0; i < n.size(); i++) {
                System.out.format(
                        leftAlignFormat, 
                        i+1,
                        n.get(i).getIdGame(), 
                        n.get(i).getNominalVoucher(),
                        n.get(i).getHargaVoucher(),
                        n.get(i).getId()
                    );
            }
            System.out.format("+-----+---------------------------+-------------------------------------+--------------------------------------------+---------------------------------------------+%n");
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    
    static Voucher getVoucherById(String id) {
        Voucher result = null;
        try {
            String query = "SELECT * FROM vouchers WHERE id='" + id + "'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultIdGame = resultSet.getString(2);
                int resultNominalVoucher = resultSet.getInt(3);
                int resultHargaVoucher = resultSet.getInt(4);
                result = new Voucher(
                    resultId,
                    resultIdGame,
                    resultNominalVoucher,
                    resultHargaVoucher
                );
                return result;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    static String updateVoucherById(String id, String idGame, int nominalVoucher, int hargaVoucher) {
        try {
            String query = "UPDATE vouchers SET "
                    + "idGame='" + idGame + "', "
                    + "nominalVoucher='" + nominalVoucher + "' "
                    + "hargaVoucher='" + hargaVoucher + "' "
                    + "WHERE id='" + id + "'";
            int result = update(query);
                
            if(result != 0) {
                return "BERHASIL MEMPERBARUI VOUCHER";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "GAGAL MEMPERBARUI VOUCHER, TERJADI KEGAGALAN PADA SERVER";
    }
    
    static String deleteGameById(String id) {
        try {
            String query = "DELETE FROM vouchers WHERE id='" + id + "'";
            int result = update(query);
                
            if(result != 0) {
                return "BERHASIL MENGHAPUS VOUCHER";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "GAGAL MENGHAPUS VOUCHER, TERJADI KEGAGALAN PADA SERVER";
    }    

    static void topUp(String game, String voucher, String pembayaran, String idgame) {
        // (keterangan sementara)
        System.out.println("Berhasil TopUp Game: " + game);
        System.out.println("Dengan nominal: " + voucher);
        System.out.println("Metode Pembayaran: " + pembayaran);
        System.out.println("Dengan ID Game: " + idgame);
    }
    
     
    
}























