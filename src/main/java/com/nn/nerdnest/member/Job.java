package com.nn.nerdnest.member;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "job")
@Getter
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

}
