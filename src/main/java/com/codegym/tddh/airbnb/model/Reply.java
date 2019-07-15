package com.codegym.tddh.airbnb.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String content;

    @ManyToOne
    @JoinColumn(name = "Evaluation_id")
    private  Evaluation evaluation;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Reply() {
    }

    public Reply(String contern) {
        this.content = contern;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContern() {
        return content;
    }

    public void setContern(String contern) {
        this.content = contern;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Evaluation getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
