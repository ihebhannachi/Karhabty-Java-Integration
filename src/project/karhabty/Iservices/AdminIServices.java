/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.karhabty.Iservices;

/**
 *
 * @author PC NET
 */
public interface AdminIServices {
    boolean disable_user(int id);
    boolean enable_user(int id);
    void  notif_newusers();
    String account_created();
    String account_created_yearly();
    String account_disabled();
    String requested_password_number();
    boolean generate_backup();

}