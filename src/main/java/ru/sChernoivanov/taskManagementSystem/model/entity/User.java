package ru.sChernoivanov.taskManagementSystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Task> createdTasks = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private List<Message> messages = new ArrayList<>();

    @Transient
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<RoleType> roleTypes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<Role> roles = new ArrayList<>();
}
