package br.com.dio.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CardDTO {
    private Long id;
    private String title;
    private String description;
    private Integer position;
    private Long columnId;
    private LocalDateTime dueDate;
    private String assignee;
    private String labels;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean archived;
} 