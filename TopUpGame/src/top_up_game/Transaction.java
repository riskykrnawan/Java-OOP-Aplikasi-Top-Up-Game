
package top_up_game;

import java.sql.ResultSet;
import java.util.ArrayList;
import static top_up_game.Main.query;
import static top_up_game.Main.update;


public class Transaction {
    private String id, item, idCustomer, tanggalTransaksi, metodePembayaran, namaGame, userIdGame;
    private int total;
    public Transaction (String id, String item, String idCustomer, String namaGame, String userIdGame, String tanggalTransaksi, String metodePembayaran, int total){
        this.id = id;
        this.item = item;
        this.idCustomer = idCustomer;
        this.namaGame = namaGame;
        this.userIdGame = userIdGame;
        this.tanggalTransaksi = tanggalTransaksi;
        this.metodePembayaran = metodePembayaran;
        this.total = total;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public String getNamaGame() {
        return namaGame;
    }

    public void setNamaGame(String namaGame) {
        this.namaGame = namaGame;
    }

    public String getUserIdGame() {
        return userIdGame;
    }

    public void setUserIdGame(String userIdGame) {
        this.userIdGame = userIdGame;
    }
    
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String metodePembayaran() {
        return metodePembayaran;
    }

    public void metodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    static void addTransaction(
            String id, 
            String idVoucher, 
            String idUser, 
            String game, 
            String userIdGame, 
            String metodePembayaran, 
            String tanggalTransaksi, 
            String nominal,
            int total
    ) {
        try {
            String query = "INSERT INTO transactions "
                            + "(id, idVoucher, idUser, game, userIdGame, metodePembayaran, tanggalTransaksi, total) "
                            + "VALUES ("
                            + "'" + id + "', "
                            + "'" + idVoucher + "', "
                            + "'" + idUser + "', "
                            + "'" + game + "', "
                            + "'" + userIdGame + "', "
                            + "'" + metodePembayaran + "', "
                            + "'" + tanggalTransaksi + "', "
                            + "'" + total + "'"
                            + ")";
            int result = update(query);
            if(result != 0) {
                System.out.println("=== Berhasil TopUp ===");
                System.out.println("Game: " + game);
                System.out.println("Nominal: " + nominal);
                System.out.println("Harga: " + nominal);
                System.out.println("Metode Pembayaran: " + metodePembayaran);
                System.out.println("ID Game: " + userIdGame);
            }
        } catch(Exception e) {
            System.out.println(e);
        }
    }
    
    static void getTransactions(ArrayList<Transaction> n) {
        
        try {
            n.clear();
            String query = "SELECT * FROM transactions";
            ResultSet resultSet = query(query);

            while (resultSet.next()) {
                    String resultId = resultSet.getString(1);
                    String resultIdVoucher = resultSet.getString(2);
                    String resultIdUser = resultSet.getString(3);
                    String resultNamaGame = resultSet.getString(4);
                    String resultUserIdGame = resultSet.getString(5);
                    String resultMetodePembayaran = resultSet.getString(6);
                    String resultTanggalTransaksi = resultSet.getString(7);
                    int resultTotal = resultSet.getInt(8);
                    Transaction transaction = new Transaction(
                        resultId,
                        resultIdVoucher,
                        resultIdUser,
                        resultNamaGame,
                        resultUserIdGame,
                        resultTanggalTransaksi,
                        resultMetodePembayaran,
                        resultTotal
                    );                             
                    n.add(transaction);
            }
            String leftAlignFormat = "| %-3s | %-50s | %-50s | %-50s | %-50s | %-42s | %-42s | %-42s | %-50s |%n";
            System.out.format("+-----+----------------------------------------------------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------------+--------------------------------------------+--------------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
            System.out.format("| No  | Nominal Voucher                                    | Customer                                           | Nama Game                                          | ID Game                                            | Metode Pembayaran                          | Tanggal Transaksi                          | Total                                      |  ID                                                |%n");
            System.out.format("+-----+----------------------------------------------------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------------+--------------------------------------------+--------------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
            for (int i = 0; i < n.size(); i++) {
                System.out.format(
                        leftAlignFormat, 
                        i+1,
                        Voucher.getNominalVoucherById(n.get(i).getItem()),
                        Main.getOneUser(n.get(i).getIdCustomer()),
                        n.get(i).getNamaGame(),
                        n.get(i).getUserIdGame(),
                        n.get(i).getMetodePembayaran(),
                        n.get(i).getTanggalTransaksi(),
                        n.get(i).getTotal(),
                        n.get(i).getId()
                    );
            }
            System.out.format("+-----+----------------------------------------------------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------------+--------------------------------------------+--------------------------------------------+--------------------------------------------+----------------------------------------------------+%n");
        } catch (Exception e) {
            
        }
    }
}