package com.bobbyakyong.InstructorApp.dao;

import com.bobbyakyong.InstructorApp.entity.Instructor;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */
@Repository
public class AppDaoImpl implements AppDao{

    // define field for entitymanager
    private EntityManager entityManager;

    // inject entitymanager using constructor injection
    /**
     * Constructor injection for the EntityManager.
     * @Autowired is optional here if there is only one constructor.
     */
    @Autowired
    public AppDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void saveInstructor(Instructor instructor) {
        // Implementation code to save instructor to the database
        entityManager.persist(instructor);
    }

    @Override
    public Instructor findInstructorById(int id) {
        // Implementation code to find instructor by ID from the database
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        // Implementation code to delete instructor by ID from the database
        Instructor instructor = entityManager.find(Instructor.class, id);
        if (instructor != null) {
            entityManager.remove(instructor);
        }
    }
}
