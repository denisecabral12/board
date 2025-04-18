package br.com.dio.persistence.repository;

import br.com.dio.persistence.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByArchivedFalseOrderByCreatedAtDesc();
    
    @Query("SELECT b FROM Board b LEFT JOIN FETCH b.columns WHERE b.id = :id")
    Board findByIdWithColumns(Long id);
} 