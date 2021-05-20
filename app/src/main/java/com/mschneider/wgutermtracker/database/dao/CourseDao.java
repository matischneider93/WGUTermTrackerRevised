package com.mschneider.wgutermtracker.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mschneider.wgutermtracker.models.Course;

import java.util.List;

@Dao
public interface CourseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourse(Course Course);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCourses(List<Course> courses);


    @Update
    void updateCourse(Course course);

    @Delete
    void deleteCourse(Course Course);

    @Query("SELECT * FROM courses WHERE courseId = :courseId")
    Course getCourseById(int courseId);

    @Query("SELECT * FROM courses ORDER BY courseId ASC")
    List<Course> getAllCourses();

    @Query("DELETE FROM courses")
    int deleteAllCourses();

    @Query("SELECT COUNT(*) FROM courses")
    int getCoursesCount();

    @Query("DELETE FROM courses WHERE courseId = :selectedPosition")
    void deleteById(int selectedPosition);
}
