
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import modelo.Contato;



public class Banco {
    private Connection conexao;
    
    public Banco () throws Exception{
        this.conexao = CriaConexao.getConexao();
        
    }
    
    public void adicionaContato(Contato c) throws SQLException{
        
             String sql = "insert into contato(nome,endereco,telefone,email,sexo)" +
                "values(?,?,?,?,?)";
        
        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        stmt.setString(1, c.getNome());
        stmt.setString(2, c.getEndereco());
        stmt.setString(3, c.getTelefone());
        stmt.setString(4, c.getEmail());
        stmt.setString(5, c.getSexo());
        //executa o codigo SQL
        stmt.execute();
        stmt.close();
    }
        
        
    }
    

