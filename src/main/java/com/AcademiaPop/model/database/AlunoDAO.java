package com.AcademiaPop.model.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.AcademiaPop.model.entities.Aluno;

public class AlunoDAO {
	public static boolean insertAluno(Aluno a) throws SQLException {
		Connection conexao = Factory.getConexao();	
		
		boolean auth = false;
		
		if(UserDAO.insertUser(a)) {	
						
			String sql = "INSERT into aluno(id_user) SELECT id FROM user WHERE login = '"+a.login+"'";
					
			PreparedStatement stmt = conexao.prepareStatement(sql);				
			
			stmt.execute();
			
			System.out.println("aluno inserido com sucesso");
			auth = true;
		}
		conexao.close();
		
		return auth;
		
	}
	
	public static Aluno getAluno(int id_q) throws SQLException {		
		Connection conexao = Factory.getConexao();		
		
		String sql = "SELECT a.id, a.id_user, u.nome, u.login, u.senha, u.email, u.telefone, u.cpf, u.img, u.status FROM user u INNER JOIN aluno a ON u.id = a.id_user WHERE u.id =" + id_q;
		
		Statement stmt = conexao.createStatement();	
		
		
		ResultSet r = stmt.executeQuery(sql);		
		
		int id = 0,id_user = 0,status = 0;
		String login = null, senha = null, email = null, telefone = null, nome = null, cpf = null, img = null;
		
		if(r != null && r.next()){
            id = r.getInt("id");
            id_user = r.getInt("id_user");
			status = r.getInt("status");
			login = r.getString("login");
			senha = r.getString("senha");
			email = r.getString("email");
			telefone = r.getString("telefone");
			nome = r.getString("nome");
			cpf = r.getString("cpf");
			img = r.getString("img");
        }		
		System.out.println(id_user);
		conexao.close();
		
		Aluno aluno = new Aluno(id, id_user, status, login, senha, email, telefone, nome, cpf, img);
		
		return aluno;
	}
}
