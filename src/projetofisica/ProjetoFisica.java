/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package projetofisica;

import controller.Controller;
import view.Janela;
import view.MenuExercicio1;



/**
 *
 * @author Givas
 */
public class ProjetoFisica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Janela janela = new Janela();
        MenuExercicio1 menuEx1 = new MenuExercicio1();
        Controller c = new Controller(janela,menuEx1);
        janela.setC(c);
        menuEx1.setC(c);      
        janela.setVisible(true);
        //menuTeste.setVisible(true);
        menuEx1.setVisible(true);
        //menuEx2.setVisible(true);
        
//           public Controller(Janela janela, MenuExercicio1 menuEx1, MenuExercicio2 menuEx2) {
//        this.janela = janela;
//        this.menuEx1 = menuEx1;
//        this.menuEx2 = menuEx2;
    //}
    }
    
}
