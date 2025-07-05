package com.nn.nerdnest.member;

import com.nn.nerdnest.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name ="member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50, nullable = false)
    private String name;

    @Column(unique = true, length = 100, nullable = false)
    private String email;

    @Column(unique = true, length = 30, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    private int level;
    private boolean agree;

    public Member(String name, String email, String username, String password, Job job, int level, boolean agree) {
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;  // 암호화된 비밀번호만 저장됨
        this.job = job;
        this.level = level;
        this.agree = agree;
    }



}
