/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package negocio;

import dados.CadastroDAOException;
import dados.CadastroDAODB;
import java.util.ArrayList;
/**
 *
 * @author Lucas
 */
public class cadastroFachada{
    private CadastroDAO dao;

    public cadastroFachada() throws CadastroException {
        try {
            dao = CadastroDAODB.getInstance();
        } catch (CadastroDAOException exception) {
            throw new CadastroException("Falha na criação de fachada", exception);
        }
    }  
    
    public Pessoa novaPessoa(String nome, String telefone) throws CadastroException {
        if(!ValidarPessoa.validaNome(nome)) {
           throw new CadastroException("Nome inválido!");
        }
        if(!ValidarPessoa.validaTelefone(telefone)) {
            throw new CadastroException("Telefone inválido!");
        }
        Pessoa pessoa = new Pessoa(nome, telefone);
        try {
           boolean ok = dao.novaPessoa(pessoa);
           return ok ? pessoa : null;
        } catch (CadastroDAOException exception) {
           throw new CadastroException("Falha ao adicionar pessoa!", exception);
        }
    }
    
    public String recuperarTelefone(String nomePessoa) throws CadastroException {
        try {
            return dao.recuperarTelefone(nomePessoa);
        } catch (CadastroDAOException exception) {
            throw new CadastroException("Falha ao recuperar telefone!", exception);
        }
    }
    
    public ArrayList<String> nomesEmOrdemAlfabetica() throws CadastroException {
        try {
            return dao.nomesEmOrdemAlfabetica();
        } catch (CadastroDAOException exception) {
            throw new CadastroException("Falha ao recuperar nomes!", exception);
        }
    }    
}
