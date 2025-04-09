package br.com.dio.persistence.repository;

import br.com.dio.persistence.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByColumnIdAndArchivedFalseOrderByPositionAsc(Long columnId);
    
    @Query("SELECT MAX(c.position) FROM Card c WHERE c.column.id = :columnId")
    Integer findMaxPositionByColumnId(Long columnId);
    
    List<Card> findByAssigneeAndArchivedFalseOrderByDueDateAsc(String assignee);
    
    List<Card> findByColumnBoardIdAndArchivedFalseOrderByDueDateAsc(Long boardId);
} 