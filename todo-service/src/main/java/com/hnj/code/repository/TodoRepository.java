package com.hnj.code.repository;

import com.hnj.code.model.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByIsDoneFalseOrderByModifiedAtDesc();
    List<Todo> findAllByIsDoneTrueOrderByModifiedAtDesc();
}