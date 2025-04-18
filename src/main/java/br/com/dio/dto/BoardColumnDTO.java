package br.com.dio.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardColumnDTO {
    private Long id;
    private String title;
    private Integer position;
    private Long boardId;
    private List<CardDTO> cards = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean archived;
}
