package com.sumsum.ai.repository;

import com.sumsum.ai.entity.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByAuthor(String author, Sort sort);

//    @Query("SELECT * FROM question WHERE author={author} character={character}",nativeQuery = true)
//    List<Question> findByAuthorAndCharacter(String author, String character);
}
