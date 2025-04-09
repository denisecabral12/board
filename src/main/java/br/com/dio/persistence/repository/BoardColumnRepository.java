package br.com.dio.persistence.repository;

import br.com.dio.persistence.entity.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardColumnRepository extends JpaRepository<BoardColumn, Long> {
    List<BoardColumn> findByBoardIdAndArchivedFalseOrderByPositionAsc(Long boardId);
    
    @Query("SELECT c FROM BoardColumn c LEFT JOIN FETCH c.cards WHERE c.id = :id")
    BoardColumn findByIdWithCards(Long id);
    
    @Query("SELECT MAX(c.position) FROM BoardColumn c WHERE c.board.id = :boardId")
    Integer findMaxPositionByBoardId(Long boardId);
} 