package fr.doranco.tp.model;

import java.util.List;

import javax.persistence.RollbackException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import fr.doranco.tp.entity.Adresse;
import fr.doranco.tp.entity.Question;

public class QuestionDao implements IQuestionDao {

	

	@Override
	public List<Question> getAllQuestions() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addQuestion(Question question) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
