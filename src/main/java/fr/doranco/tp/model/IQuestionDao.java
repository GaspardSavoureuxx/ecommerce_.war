package fr.doranco.tp.model;

import java.util.List;

import fr.doranco.tp.entity.Question;

public interface IQuestionDao {
	
	void addQuestion(Question question) throws Exception;
	 List<Question> getAllQuestions() throws Exception;


}
