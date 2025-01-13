package ru.sChernoivanov.taskManagementSystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String header;
    private String description;
    private Status status;
    private Priority priority;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "performer_id")
    private User performer;

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();
}
