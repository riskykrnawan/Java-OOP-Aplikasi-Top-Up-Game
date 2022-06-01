/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package top_up_game;

import java.util.UUID;

/**
 *
 * @author Lenovo-PC
 */
public class Admin extends User {
    private final UUID uuid = UUID.randomUUID();
    private final String id = "admin-" + uuid.toString();

    // constructor
    public Admin(
            String nama,
            String alamat,
            String noTelp
    ) {
        super(nama, alamat, noTelp);
    }

    public String getId(){
        return id;
    }

    @Override
    public String statusLogin() {
        return "Berhasil login sebagai admin";
    }
}
