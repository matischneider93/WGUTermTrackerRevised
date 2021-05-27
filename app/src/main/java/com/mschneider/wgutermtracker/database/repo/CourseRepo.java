package com.mschneider.wgutermtracker.database.repo;

import android.app.Application;
import android.os.AsyncTask;

import com.mschneider.wgutermtracker.database.AppDatabase;
import com.mschneider.wgutermtracker.database.dao.CourseDao;
import com.mschneider.wgutermtracker.models.Course;

import java.util.List;

public class CourseRepo {
    private CourseDao courseDao;
    private List<Course> allCourses;


    public CourseRepo(Application application){
        AppDatabase appDatabase = (AppDatabase) AppDatabase.getDatabaseInstance(application.getApplicationContext());
        courseDao = appDatabase.courseDao();
        allCourses = courseDao.getAllCourses();

    }

    //Insert methods
    public void insertCourse(Course course){
        new InsertCourseAsyncTask(courseDao).execute(course);

    }

    //Delete methods
    public void deleteCourse(Course course){
        new DeleteCourseAsyncTask(courseDao).execute(course);

    }

    public void deleteAllCourses(){
        new DeleteAllCoursesAsyncTask(courseDao).execute();

    }


    //Get methods
    public List<Course> getAllCourses(){
        return allCourses;
    }

    public Course getCourseById(int courseId){
        return courseDao.getCourseById(courseId);
    }

    private static class InsertCourseAsyncTask extends AsyncTask<Course, Void, Void> {
        private CourseDao courseDao;

        private InsertCourseAsyncTask(CourseDao courseDao){
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {

            return null;
        }
    }

    private static class DeleteCourseAsyncTask extends AsyncTask<Course, Void, Void>{
        private CourseDao courseDao;

        private DeleteCourseAsyncTask(CourseDao courseDao){
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Course... courses) {
            courseDao.deleteCourse(courses[0]);
            return null;
        }
    }

    private static class DeleteAllCoursesAsyncTask extends AsyncTask<Void, Void, Void>{
        private CourseDao courseDao;

        private DeleteAllCoursesAsyncTask(CourseDao courseDao){
            this.courseDao = courseDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            courseDao.deleteAllCourses();
            return null;
        }
    }
}
