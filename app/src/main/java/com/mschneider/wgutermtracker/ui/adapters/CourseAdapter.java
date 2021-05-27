package com.mschneider.wgutermtracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Ignore;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Course;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.ViewHolder> {
    private int pos;
    private List<Course> courseList;
    private ViewHolder.OnCourseClickListener onCourseClickListener;

    public CourseAdapter(List<Course> courses, ViewHolder.OnCourseClickListener onCourseClickListener){
        this.courseList = courses;
        this.onCourseClickListener = onCourseClickListener;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView courseTitleTextView;
        private TextView courseStatusTextView;
        private TextView courseStartDateTextView;
        private TextView courseEndDateTextView;

        OnCourseClickListener onCourseClickListener;

        public TextView getCourseTitleTextView() {
            return courseTitleTextView;
        }

        public TextView getCourseStatusTextView() {
            return courseStatusTextView;
        }

        public TextView getCourseStartDateTextView() {
            return courseStartDateTextView;
        }

        public TextView getCourseEndDateTextView() {
            return courseEndDateTextView;
        }

        public ViewHolder(View view, OnCourseClickListener onCourseClickListener) {
            super(view);
            // Define click listener for the ViewHolder's View
            courseTitleTextView = view.findViewById(R.id.courseTitleTextView);
            courseStatusTextView = view.findViewById(R.id.courseStatusTextView);
            courseStartDateTextView = view.findViewById(R.id.courseStartDateTextView);
            courseEndDateTextView = view.findViewById(R.id.courseEndDateTextView);
            this.onCourseClickListener = onCourseClickListener;
            view.setOnClickListener(this);

        }


        @Ignore
        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View v) {
            onCourseClickListener.onCourseClick(getAdapterPosition());
        }

        // Click listener for row
        public interface OnCourseClickListener {
            void onCourseClick(int position);
        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *
     *
     * by RecyclerView.
     * @param
     */
    public CourseAdapter(List<Course> courses) {
        courseList = courses;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.course_row_item, viewGroup, false);


        return new ViewHolder(view, onCourseClickListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getCourseTitleTextView().setText(courseList.get(position).getTitle());
        viewHolder.getCourseStatusTextView().setText(courseList.get(position).getStatus());
        viewHolder.getCourseStartDateTextView().setText(courseList.get(position).getStartDate());
        viewHolder.getCourseEndDateTextView().setText(courseList.get(position).getEndDate());





    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return courseList.size();
    }


}
