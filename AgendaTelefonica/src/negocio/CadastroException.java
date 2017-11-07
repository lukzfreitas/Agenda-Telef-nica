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
public class CadastroException extends Exception{
    
    public CadastroException() {
    }
    
    public CadastroException(String msg) {
        super(msg);
    }

    public CadastroException(String message, Throwable cause) {
        super(message, cause);
    }
}
