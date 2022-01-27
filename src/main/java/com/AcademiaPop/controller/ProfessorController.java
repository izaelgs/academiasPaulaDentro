package com.AcademiaPop.controller;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AcademiaPop.model.database.ProfessorDAO;
import com.AcademiaPop.model.entities.Professor;

@RestController
@RequestMapping("/professor")
public class ProfessorController {	
	
	@GetMapping(path="/qualquer")
	public Professor obterProfessor() {
		return new Professor("login.professorDAO", "senha_profedao", "email@professorbrabo", "27654231453", "pRofesorAnofabeto", "7556453419");
	}
	
	@GetMapping("/aluno/{id}")
	public Professor obterProfessorAluno(@PathVariable int id) throws SQLException {
		Professor professor = ProfessorDAO.getProfessorAluno(id);
		System.out.println("id: "+ id +"; professor: "+ professor.nome);
		return professor;
	}
	
	@PostMapping("/insert")
	public String insertProfessor(@RequestBody Professor professor) throws SQLException{
		ProfessorDAO.insertProfessor(professor);		
		return "Professor inserido com sucesso";
	}
	
	@PutMapping("/update")
	public Professor updatetProfessor(@RequestBody Professor professor) throws SQLException{
		ProfessorDAO.insertProfessor(professor);		
		return professor;
	}
	
}
