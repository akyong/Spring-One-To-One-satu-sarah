package com.bobbyakyong.InstructorApp.entity;

import jakarta.persistence.*;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */


/**
 * Entity Instructor
 *
 * Memiliki relasi OneToOne dengan {@link InstructorDetail}
 * */
@Entity
@Table(name="instructor")
public class Instructor {

    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db column names

    // create constructors

    // gerate getter/setter methods

    // generate toString() method

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    /**
     * InstuctorDetail milik Instructor
     * Jika di InstructorDetail tidak ada deklarasi relasi balik ke Instructor,
     * maka ini adalah unidirectional one-to-one relationship. satu arah.
     * namun jika di InstructorDetail ada deklarasi relasi balik ke Instructor,
     * maka ini adalah bidirectional one-to-one relationship. dua arah.
     * Contoh deklrasi bidirectional one-to-one relationship pada {@link InstructorDetail} harus ada
     *
     * @OneToOne
     * private Instructor instructor;
     *
     * kita akan lihat Foreign Key nya ada dimana? Jika di table instructor ada kolom instructor_detail_id yang sebagai
     * Foreign Key ke table instructor_detail,
     * maka di Entity Instructor harus menambahkan @JoinColumn seperti di bawah.
     *
     * @see InstructorDetail
     *
     * kalau Entity yang tidak ada Foreign Key, maka harus pakay mapppedBy, contoh
     * @Entity
     * public class User {
     *
     *     @Id
     *     @GeneratedValue
     *     private Long id;
     *
     *     @OneToOne(mappedBy = "user")
     *     private UserProfile profile;
     * }
     *
     * jadi di atas, Table user_profile yang punya foreign key ke user_id.
     * makanya di private UserProfile profile; harus pakai mappedBy = "user" di entity User.
     * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;

    public Instructor() {}

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }
}
