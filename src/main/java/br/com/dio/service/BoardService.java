package br.com.dio.service;

import br.com.dio.dto.BoardDTO;
import br.com.dio.dto.BoardColumnDTO;
import br.com.dio.persistence.entity.Board;
import br.com.dio.persistence.entity.BoardColumn;
import br.com.dio.persistence.repository.BoardRepository;
import br.com.dio.persistence.repository.BoardColumnRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardColumnRepository columnRepository;

    @Transactional(readOnly = true)
    public List<BoardDTO> getAllBoards() {
        return boardRepository.findByArchivedFalseOrderByCreatedAtDesc()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BoardDTO getBoardById(Long id) {
        Board board = boardRepository.findByIdWithColumns(id);
        if (board == null) {
            throw new EntityNotFoundException("Board not found with id: " + id);
        }
        return convertToDTO(board);
    }

    @Transactional
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setDescription(boardDTO.getDescription());
        
        Board savedBoard = boardRepository.save(board);
        return convertToDTO(savedBoard);
    }

    @Transactional
    public BoardDTO updateBoard(Long id, BoardDTO boardDTO) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + id));
        
        board.setTitle(boardDTO.getTitle());
        board.setDescription(boardDTO.getDescription());
        
        Board updatedBoard = boardRepository.save(board);
        return convertToDTO(updatedBoard);
    }

    @Transactional
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + id));
        board.setArchived(true);
        boardRepository.save(board);
    }

    @Transactional
    public BoardColumnDTO addColumn(Long boardId, BoardColumnDTO columnDTO) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));
        
        BoardColumn column = new BoardColumn();
        column.setTitle(columnDTO.getTitle());
        column.setPosition(columnRepository.findMaxPositionByBoardId(boardId) + 1);
        column.setBoard(board);
        
        board.addColumn(column);
        BoardColumn savedColumn = columnRepository.save(column);
        return convertToDTO(savedColumn);
    }

    private BoardDTO convertToDTO(Board board) {
        BoardDTO dto = new BoardDTO();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setDescription(board.getDescription());
        dto.setCreatedAt(board.getCreatedAt());
        dto.setUpdatedAt(board.getUpdatedAt());
        dto.setArchived(board.isArchived());
        
        dto.setColumns(board.getColumns().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList()));
        
        return dto;
    }

    private BoardColumnDTO convertToDTO(BoardColumn column) {
        BoardColumnDTO dto = new BoardColumnDTO();
        dto.setId(column.getId());
        dto.setTitle(column.getTitle());
        dto.setPosition(column.getPosition());
        dto.setBoardId(column.getBoard().getId());
        dto.setCreatedAt(column.getCreatedAt());
        dto.setUpdatedAt(column.getUpdatedAt());
        dto.setArchived(column.isArchived());
        return dto;
    }
}
