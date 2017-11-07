/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import negocio.CadastroDAO;
import negocio.Pessoa;

/**
 *
 * @author Lucas
 */
public class CadastroDAODB implements CadastroDAO {

    private static CadastroDAODB cadastroDAODB;

    public static CadastroDAODB getInstance() throws CadastroDAOException {
        if (cadastroDAODB == null) {
            cadastroDAODB = new CadastroDAODB();
        }
        return cadastroDAODB;
    }

    private CadastroDAODB() throws CadastroDAOException {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
        } catch (ClassNotFoundException ex) {
            throw new CadastroDAOException("JdbcOdbDriver not found!!");
        }
    }

    private static void createDB() throws CadastroDAOException {
        try {
            Connection con = DriverManager.getConnection("jdbc:derby:derbyDB;create=true");
            Statement sta = con.createStatement();
            String sql = "CREATE TABLE Pessoas ("
                    + "ID INTEGER NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + "NOME VARCHAR(100) NOT NULL,"
                    + "TELEFONE CHAR(8) NOT NULL"
                    + ")";
            sta.executeUpdate(sql);
            sta.close();
            con.close();
        } catch (SQLException exception) {
            throw new CadastroDAOException(exception.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        //derbyDB sera o nome do diretorio criado localmente
        return DriverManager.getConnection("jdbc:derby:derbyDB");
    }

    @Override
    public boolean novaPessoa(Pessoa pessoa) throws CadastroDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "INSERT INTO PESSOAS (NOME, TELEFONE) VALUES (?,?)"
            );
            statement.setString(1, pessoa.getNome());
            statement.setString(2, pessoa.getTelefone());
            int ret = statement.executeUpdate();
            con.close();
            return (ret > 0);
        } catch (SQLException ex) {
            throw new CadastroDAOException("Falha ao adicionar.", ex);
        }
    }

    @Override
    public String recuperarTelefone(String nomePessoa) throws CadastroDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM PESSOAS WHERE NOME=?"
            );
            statement.setString(1, nomePessoa);
            ResultSet resultado = statement.executeQuery();
            String telefone = null;
            if (resultado.next()) {
                telefone = resultado.getString("TELEFONE");
            }
            return telefone;
        } catch (SQLException exception) {
            throw new CadastroDAOException("Falha ao buscar.", exception);
        }
    }

    @Override
    public ArrayList<String> nomesEmOrdemAlfabetica() throws CadastroDAOException {
        try {
            Connection con = getConnection();
            PreparedStatement statement = con.prepareStatement(
                    "SELECT * FROM PESSOAS"
            );            
            ResultSet resultado = statement.executeQuery();
            ArrayList<String> lista = new ArrayList<>();            
            while(resultado.next()) {
                String nome = resultado.getString("NOME");                
                lista.add(nome);
            }
            Collections.sort(lista);
            return lista;
        } catch (SQLException exception) {
            throw new CadastroDAOException("Falha ao buscar.", exception);
        }
    }
}
