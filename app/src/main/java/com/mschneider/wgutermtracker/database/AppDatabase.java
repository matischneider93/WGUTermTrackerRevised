package com.mschneider.wgutermtracker.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.mschneider.wgutermtracker.database.dao.AssessmentDao;
import com.mschneider.wgutermtracker.database.dao.CourseDao;
import com.mschneider.wgutermtracker.database.dao.TermDao;
import com.mschneider.wgutermtracker.models.Assessment;
import com.mschneider.wgutermtracker.models.Course;
import com.mschneider.wgutermtracker.models.Term;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities={Course.class, Term.class, Assessment.class}, version=5 , exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String DATABASE_NAME = "AppDatabase.db";
    private static volatile AppDatabase databaseInstance;
    private static final Object LOCK = new Object();

    public abstract TermDao termDao();
    public abstract CourseDao courseDao();
    public abstract AssessmentDao assessmentDao();
    private static final int NO_OF_THREADS = 4;

    static final ExecutorService dbWriteExecutor = Executors.newFixedThreadPool(NO_OF_THREADS);




    public static AppDatabase getDatabaseInstance(Context context) {
        if (databaseInstance == null) {
            synchronized (AppDatabase.class) {
                if (databaseInstance == null) {
                    databaseInstance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().
                            fallbackToDestructiveMigration()
                            .addCallback(roomCallBack)
                            .build();
                }
            }
        }
        return databaseInstance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(databaseInstance).execute();

        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {

        public TermDao termDao;
        public CourseDao courseDao;
        public AssessmentDao assessmentDao;


        public PopulateDbAsyncTask(AppDatabase db) {

            termDao = db.termDao();
            courseDao = db.courseDao();
            assessmentDao = db.assessmentDao();


        }

        @Override
        protected Void doInBackground(Void... voids) {


            return null;
        }
    }





}
