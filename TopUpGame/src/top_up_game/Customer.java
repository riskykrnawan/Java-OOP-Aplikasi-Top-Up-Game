/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top_up_game;
/**
 *
 * @author Lenovo-PC
 */
public class Customer extends User {

    // constructor
    public Customer (
            String nama,
            String alamat,
            String noTelp
    ) {
        super(nama, alamat, noTelp);
    }

    @Override
    public String statusLogin() {
        return "Berhasil login sebagai customer";
    }
}
