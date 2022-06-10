
package top_up_game;

import java.sql.ResultSet;
import java.util.ArrayList;
import static top_up_game.Main.query; // L81 error klo gk pke ini
import static top_up_game.Main.update;


class Voucher {
    private String id,idGame, nominalVoucher; // mungkin idGame bisa final?
    private int hargaVoucher;  
    
    public Voucher (String id, String idGame,String nominalVoucher, int hargaVoucher ){
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

    public String getNominalVoucher() {
        return nominalVoucher;
    }

    public void setNominalVoucher(String nominalVoucher) {
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
            String nominalVoucher,
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
                return "BERHASIL MENAMBAHKAN VOUCHER\n";
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "VOUCHER GAGAL DITAMBAHKAN, DATA YANG DIBERIKAN TIDAK BENAR";
    }
    
    
    static void getVouchers(ArrayList<Voucher> n) {
        try {           
            n.clear();
            String query = "SELECT * FROM vouchers";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultIdGame = resultSet.getString(2);
                String resultNominalVoucher = resultSet.getString(3);
                int resultHargaVoucher = resultSet.getInt(4);
                Voucher vouchers = new Voucher(
                    resultId,
                    resultIdGame,
                    resultNominalVoucher,
                    resultHargaVoucher
                );                             
                n.add(vouchers);
            }
            String leftAlignFormat = "| %-3s | %-50s | %-35s | %-42s | %-50s |%n";
            System.out.format("+-----+----------------------------------------------------+-------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
            System.out.format("| No  | Nama Game                                          | Nominal                             | Harga                                      |  ID                                                |%n");
            System.out.format("+-----+----------------------------------------------------+-------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
            for (int i = 0; i < n.size(); i++) {
                System.out.format(
                        leftAlignFormat, 
                        i+1,
                        Game.getNamaGameById(n.get(i).getIdGame()),
                        n.get(i).getNominalVoucher(),
                        n.get(i).getHargaVoucher(),
                        n.get(i).getId()
                    );
            }
            System.out.format("+-----+----------------------------------------------------+-------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
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
                String resultNominalVoucher = resultSet.getString(3);
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
    
    static String getNominalVoucherById(String id) {
        try {
            String query = "SELECT nominalVoucher FROM vouchers WHERE id='" + id + "'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                return resultId;
            }
        } catch(Exception e) {
            System.out.println(e);
        }
        return "";
    }
    
    static void getVouchersByIdGame(ArrayList<Voucher> n, String idGame) {
        n.clear();
        Voucher result = null;
        try {
            String query = "SELECT * FROM vouchers WHERE idGame='" + idGame + "'";
            ResultSet resultSet = query(query);
            while (resultSet.next()) {
                String resultId = resultSet.getString(1);
                String resultIdGame = resultSet.getString(2);
                String resultNominalVoucher = resultSet.getString(3);
                int resultHargaVoucher = resultSet.getInt(4);
                result = new Voucher(
                    resultId,
                    resultIdGame,
                    resultNominalVoucher,
                    resultHargaVoucher
                );
                n.add(result);
            }
            String leftAlignFormat = "| %-3s | %-50s | %-35s | %-42s | %-50s |%n";
            System.out.format("+-----+----------------------------------------------------+-------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
            System.out.format("| No  | Nama Game                                          | Nominal                             | Harga                                      |  ID                                                |%n");
            System.out.format("+-----+----------------------------------------------------+-------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
            for (int i = 0; i < n.size(); i++) {
                System.out.format(
                        leftAlignFormat, 
                        i+1,
                        Game.getNamaGameById(n.get(i).getIdGame()),
                        n.get(i).getNominalVoucher(),
                        n.get(i).getHargaVoucher(),
                        n.get(i).getId()
                    );
            }
            System.out.format("+-----+----------------------------------------------------+-------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    static String updateVoucherById(String id, String nominalVoucher, int hargaVoucher) {
        try {
            String query = "UPDATE vouchers SET "
                    + "nominalVoucher='" + nominalVoucher + "', "
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
    
    static String deleteVoucherById(String id) {
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
}
