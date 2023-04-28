package com.sumsum.ai.repository;

import com.sumsum.ai.entity.Question;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByAuthor(String author, Sort sort);

    @Query(value = "SELECT * FROM question WHERE author = :author and char_mo = :character ORDER BY id DESC", nativeQuery = true)
    List<Question> findByAuthorAndCharacter(@Param("author") String author, @Param("character") String character);
}
