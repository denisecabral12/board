package br.com.dio.controller;

import br.com.dio.dto.BoardDTO;
import br.com.dio.dto.BoardColumnDTO;
import br.com.dio.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
@Tag(name = "Board", description = "Board management APIs")
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    @Operation(summary = "Get all boards")
    public ResponseEntity<List<BoardDTO>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get board by ID")
    public ResponseEntity<BoardDTO> getBoardById(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoardById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new board")
    public ResponseEntity<BoardDTO> createBoard(@RequestBody BoardDTO boardDTO) {
        return ResponseEntity.ok(boardService.createBoard(boardDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing board")
    public ResponseEntity<BoardDTO> updateBoard(@PathVariable Long id, @RequestBody BoardDTO boardDTO) {
        return ResponseEntity.ok(boardService.updateBoard(id, boardDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a board")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{boardId}/columns")
    @Operation(summary = "Add a new column to a board")
    public ResponseEntity<BoardColumnDTO> addColumn(
            @PathVariable Long boardId,
            @RequestBody BoardColumnDTO columnDTO) {
        return ResponseEntity.ok(boardService.addColumn(boardId, columnDTO));
    }
} 