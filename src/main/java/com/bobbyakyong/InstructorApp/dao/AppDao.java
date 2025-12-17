package com.bobbyakyong.InstructorApp.dao;

import com.bobbyakyong.InstructorApp.entity.Instructor;

/*
 * Copyright (c) 2025 Bobby Akyong
 * Author: Bobby Akyong
 *
 * This file is part of udemy.
 * Licensed under the MIT License.
 */
public interface AppDao {
    void saveInstructor(Instructor instructor);

    Instructor findInstructorById(int id);

    void deleteInstructorById(int id);
}
