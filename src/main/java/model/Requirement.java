package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "requirement")

public class Requirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "requirement", cascade = CascadeType.ALL)
    private List<TestCase> testCases;

    public Requirement() {
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
}

    public LocalDateTime getCreatedAt() {
    return createdAt;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }
}

