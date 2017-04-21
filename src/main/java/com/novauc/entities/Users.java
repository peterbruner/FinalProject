package com.novauc.entities;

import javax.persistence.*;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue
    private int id;

    @Column
    String firstName;

    @Column
    String password;
}
