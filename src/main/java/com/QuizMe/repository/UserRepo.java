package com.QuizMe.repository;


import com.QuizMe.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	
    @Query("SELECT c FROM User c WHERE c.id =:id")
    User getUser(@Param("id") Long id);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("DELETE FROM User c WHERE c.id = :id")
	int deleteUser(@Param("id") Long id);
    
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query(value="UPDATE User c SET c.username = :username WHERE c.id = :id", nativeQuery = true)
    int updateUser(@Param("id") Long id, @Param("username") String username);
}

