package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Mom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=4)
    @Column(unique = true)
    private String name;

    @OneToMany(mappedBy = "Mom")
    public Set<Children> children;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Children> getChildren() {
        return children;
    }

    public void setChildren(Set<Children> children) {
        this.children = children;
    }
}
