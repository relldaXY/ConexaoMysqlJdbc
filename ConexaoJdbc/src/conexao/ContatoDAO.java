package conexao;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class ContatoDAO {
	
	private Connection conn;
	
	
	public void adiciona(Contato contato) {
		conn = new ConexaoComBD().conectarComBD();
		
		String sql = "insert into contatos " + "(nome,email,endereco,dataNascimento)" + " values (?,?,?,?)";
		
		try {
		// prepared statement para inserção
		java.sql.PreparedStatement stmt = conn.prepareStatement(sql);
		
		// seta os valores
		stmt.setString(1,contato.getNome());
		stmt.setString(2,contato.getEmail());
		stmt.setString(3,contato.getEndereco());
		stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
		
		// executa
		stmt.execute();
		stmt.close();
		conn.close();
		
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}	
	
	public List<Contato> getLista() {
		conn = new ConexaoComBD().conectarComBD();
		try {
			List<Contato> contatos = new ArrayList<Contato>();
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("select * from contatos");
			ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
		// criando o objeto Contato
			Contato contato = new Contato();
			contato.setId(rs.getInt("id"));
			contato.setNome(rs.getString("nome"));
			contato.setEmail(rs.getString("email"));
			contato.setEndereco(rs.getString("endereco"));
	
		// montando a data através do Calendar
		
			Calendar data = Calendar.getInstance();
			data.setTime(rs.getDate("dataNascimento"));
			contato.setDataNascimento(data);
			
		// adicionando o objeto à lista
			System.out.println(contato.getId() + contato.getNome() + contato.getEndereco() + contato.getEmail());
		
			
			contatos.add(contato);
		}		
			rs.close();
			stmt.close();
			conn.close();
			
		return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	
	}
	
	public void altera(Contato contato) {
		conn = new ConexaoComBD().conectarComBD();
		
		String sql = "update contatos set nome=?, email=?, endereco=?," + "dataNascimento=? where id=2";
		
		try {
			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);
			stmt.setString(1, contato.getNome());
			stmt.setString(2, contato.getEmail());
			stmt.setString(3, contato.getEndereco());
			stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
			//stmt.setInt(5, contato.getId());
			stmt.execute();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void remove(Contato contato) {
		conn = new ConexaoComBD().conectarComBD();
		
		try {
		PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("delete" + " from contatos where id=10");
		//stmt.setInt(1, contato.getId());
		stmt.execute();
		stmt.close();
		conn.close();
		
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}
	}
}
