package ru.sChernoivanov.taskManagementSystem.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Task> createdTasks = new ArrayList<>();

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Message> messages = new ArrayList<>();

    @Transient
    @EqualsAndHashCode.Exclude
    @Builder.Default
    private List<RoleType> roleTypes = new ArrayList<>();


    @ElementCollection(targetClass = RoleType.class, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "roles", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<RoleType> roles = new HashSet<>();
}
