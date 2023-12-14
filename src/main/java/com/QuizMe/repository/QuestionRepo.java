package com.QuizMe.repository;


import com.QuizMe.model.Question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
	
    @Query("SELECT c FROM Question c WHERE c.id =:id")
    Question getQuestion(@Param("id") Long id);

    @Query("SELECT c FROM Question c WHERE c.quizId =:quizId")
    List<Question> getQuestionsByQuizId(@Param("quizId") Long quizId);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("DELETE FROM Question c WHERE c.id = :id")
	int deleteQuestion(@Param("id") Long id);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value="UPDATE Question c SET c.name = :name, c.correctAnswer = :correctAnswer, c.wrongAnswer1 = :wrongAnswer1, c.wrongAnswer2 = :wrongAnswer2, c.wrongAnswer3 = :wrongAnswer3, c.quizId = :quizId WHERE c.id = :id")
    int updateQuestion(
        @Param("id") Long id, 
        @Param("name") String name, 
        @Param("correctAnswer") String correctAnswer, 
        @Param("wrongAnswer1") String wrongAnswer1, 
        @Param("wrongAnswer2") String wrongAnswer2, 
        @Param("wrongAnswer3") String wrongAnswer3,
        @Param("quizId") Long quizId
        );
}

