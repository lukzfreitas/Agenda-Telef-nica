/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.CadastroDAOException;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public interface CadastroDAO {
    boolean novaPessoa(Pessoa pessoa) throws CadastroDAOException;    
    String recuperarTelefone(String nomePessoa) throws CadastroDAOException; 
    ArrayList<String> nomesEmOrdemAlfabetica() throws CadastroDAOException;    
}
