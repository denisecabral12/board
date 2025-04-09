package br.com.dio.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class BoardDTO {
    private Long id;
    private String title;
    private String description;
    private List<BoardColumnDTO> columns = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean archived;
} 