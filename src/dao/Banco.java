
package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import modelo.Contato;
import java.util.List;



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
    
    

     public Contato consultarContatoId(int id) throws Exception{
        String pesq = ("select * from contato where id = ?");
        PreparedStatement stmt = this.conexao.prepareStatement(pesq);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        Contato c = null; // Inicializa o objeto Contato como nulo
        if(rs.next()){
           c = new Contato(); // Cria um novo objeto Contato se houver resultado
           c.setId(Integer.parseInt(rs.getString("id")));
           c.setNome(rs.getString("nome"));
           c.setEndereco(rs.getString("endereco"));
           c.setTelefone(rs.getString("telefone"));
           c.setEmail(rs.getString("email"));
           c.setSexo(rs.getString("sexo"));
        }
        return c; // Retorna o objeto Contato encontrado ou null se nenhum contato for encontrado
    }
    
    public Contato consultarContatoNome(String nome) throws Exception{
        Contato c = new Contato();
        String pesq = ("select * from contato where nome = ?");
        PreparedStatement stmt = this.conexao.prepareStatement(pesq);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
           c.setId(Integer.parseInt(rs.getString("id")));
           c.setNome(rs.getString("nome"));
           c.setEndereco(rs.getString("endereco"));
           c.setTelefone(rs.getString("telefone"));
           c.setEmail(rs.getString("email"));
           c.setSexo(rs.getString("sexo"));  
        }
        return c;
    }
    
    public void alterarContato(Contato c)throws SQLException{
        String sql = "update contato set nome=?, endereco=?,telefone=?,email=?,sexo=? where id=?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        
        stmt.setString(1, c.getNome());
        stmt.setString(2, c.getEndereco());
        stmt.setString(3, c.getTelefone());
        stmt.setString(4, c.getEmail());
        stmt.setString(5, c.getSexo());
        stmt.setInt(6, c.getId());
        //executa o codigo SQL
        stmt.execute();
        stmt.close();
    }
    public void excluirContato(int id)throws Exception{
        String sql = ("delete from contato where id = '" +id + "'");
        PreparedStatement stmt = this.conexao.prepareStatement(sql);
        stmt.execute();
        stmt.close();
    }
    
 public List<Contato>getLista(String nome)throws SQLException{
        String sql = "select * from contato where nome ilike ?";
        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, nome);
        ResultSet rs = stmt.executeQuery();
        List<Contato> minhaLista = new ArrayList<Contato>();
        
        while(rs.next()){
            Contato c = new Contato();
           c.setId(Integer.parseInt(rs.getString("id")));
           c.setNome(rs.getString("nome"));
           c.setEndereco(rs.getString("endereco"));
           c.setTelefone(rs.getString("telefone"));
           c.setEmail(rs.getString("email"));
           c.setSexo(rs.getString("sexo"));  
           minhaLista.add(c);
        }
        rs.close();
        stmt.close();
        return minhaLista;
    }
}

