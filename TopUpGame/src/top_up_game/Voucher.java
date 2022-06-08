
package top_up_game;


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
    
     
    
}























