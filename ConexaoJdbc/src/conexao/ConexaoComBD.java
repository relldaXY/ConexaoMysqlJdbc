package conexao;

import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

public class ConexaoComBD {
	public java.sql.Connection minhaConexao;
	
	String host = "127.0.0.1";
	String usuario = "root";
	String senha = "rellda";
	String bd = "testeConexao";
	String driver = "com.mysql.jdbc.Driver";
	
	public Connection conectarComBD(){
		try{
			Class.forName(driver);
			String newConnectionURL = "jdbc:mysql://" + host + "/" + bd + "?" + "user=" + usuario + "&password=" + senha;
			minhaConexao = DriverManager.getConnection(newConnectionURL);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return (Connection) minhaConexao;
	}
	
	
}
