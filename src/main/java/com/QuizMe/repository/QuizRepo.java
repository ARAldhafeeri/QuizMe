package com.QuizMe.repository;


import com.QuizMe.model.Quiz;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface QuizRepo extends JpaRepository<Quiz, Long> {
	
    @Query("SELECT c FROM Quiz c WHERE c.id =:id")
    Quiz getQuiz(@Param("id") long id);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("DELETE FROM Quiz c WHERE c.id = :id")
	void deleteQuiz(@Param("id") long id);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value="UPDATE Quiz c SET c.quizName = :quizName, c.quizDescription = :quizDescription, c.questions = :questions WHERE c.id = :id")
    void updateQuiz(
        @Param("id") long id,
        @Param("quizName") String quizName,
        @Param("quizDescription") String quizDescription,
        @Param("questions") Set<Quiz> questions
    );

}

