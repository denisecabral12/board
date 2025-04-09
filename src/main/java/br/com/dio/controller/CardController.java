package br.com.dio.controller;

import br.com.dio.dto.CardDTO;
import br.com.dio.service.CardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
@Tag(name = "Card", description = "Card management APIs")
public class CardController {
    private final CardService cardService;

    @GetMapping("/column/{columnId}")
    @Operation(summary = "Get all cards in a column")
    public ResponseEntity<List<CardDTO>> getCardsByColumn(@PathVariable Long columnId) {
        return ResponseEntity.ok(cardService.getCardsByColumn(columnId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get card by ID")
    public ResponseEntity<CardDTO> getCardById(@PathVariable Long id) {
        return ResponseEntity.ok(cardService.getCardById(id));
    }

    @PostMapping("/column/{columnId}")
    @Operation(summary = "Create a new card in a column")
    public ResponseEntity<CardDTO> createCard(
            @PathVariable Long columnId,
            @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.createCard(columnId, cardDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing card")
    public ResponseEntity<CardDTO> updateCard(
            @PathVariable Long id,
            @RequestBody CardDTO cardDTO) {
        return ResponseEntity.ok(cardService.updateCard(id, cardDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a card")
    public ResponseEntity<Void> deleteCard(@PathVariable Long id) {
        cardService.deleteCard(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cardId}/move")
    @Operation(summary = "Move a card to a different column")
    public ResponseEntity<CardDTO> moveCard(
            @PathVariable Long cardId,
            @RequestParam Long targetColumnId,
            @RequestParam Integer newPosition) {
        return ResponseEntity.ok(cardService.moveCard(cardId, targetColumnId, newPosition));
    }
} 