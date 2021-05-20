package com.mschneider.wgutermtracker.database.view_models;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.mschneider.wgutermtracker.database.repo.CourseRepo;
import com.mschneider.wgutermtracker.models.Course;

import java.util.List;

public class CourseViewModel extends AndroidViewModel {

    private CourseRepo mRepository;

    private List<Course> mAllCourses;

    public CourseViewModel(Application application) {
        super(application);
        mRepository = new CourseRepo(application);
        mAllCourses = mRepository.getAllCourses();
    }

    List<Course> getAllPatients() { return mAllCourses; }

    public void insert(Course course) { mRepository.insertCourse(course); }
}