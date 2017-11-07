/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

/**
 *
 * @author Lucas
 */
public class ValidarPessoa {
    public static boolean validaNome(String nome) {
        return nome.contains(" ");
    }
    public static boolean validaTelefone(String telefone) {
        return (telefone.length() == 8);
    }
}
