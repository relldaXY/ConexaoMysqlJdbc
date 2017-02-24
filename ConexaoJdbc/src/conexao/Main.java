package conexao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class Main {

	public static void main(String[] args) {
		
		Contato contato = new Contato();
		contato.setNome("Adller");
		contato.setEmail("adller@gmail.com");
		contato.setEndereco("R. Verge 3185 cj57");
		contato.setDataNascimento(Calendar.getInstance());
		
		// grave nessa conexão!!!
		ContatoDAO dao = new ContatoDAO();
		
		
		// método elegante
		
		//dao.adiciona(contato);
		
		dao.altera(contato);
		dao.remove(contato);
		dao.getLista();
		
		System.out.println("Gravado!");
		
	}

}
