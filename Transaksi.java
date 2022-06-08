
package top_up_game;


public class Transaksi {
    private String id,idCustomer, tanggalTransaksi, metodePembayarn;
    private int total;  
    
    public Transaksi (String id, String idCustomer,String tanggalTransaksi, String metodePembayarn,int total ){
        this.id = id;
        this.idCustomer = idCustomer;
        this.tanggalTransaksi = tanggalTransaksi;
        this.metodePembayarn = metodePembayarn;
        this.total = total;
    } 

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getMetodePembayarn() {
        return metodePembayarn;
    }

    public void setMetodePembayarn(String metodePembayarn) {
        this.metodePembayarn = metodePembayarn;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
    
    
    
    

}





















