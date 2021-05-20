package com.mschneider.wgutermtracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Assessment;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.ViewHolder> {
    private int pos;
    private List<Assessment> assessmentsList;
    private ViewHolder.OnAssessmentClickListener onAssessmentClickListener;

    public AssessmentAdapter(List<Assessment> assessments, ViewHolder.OnAssessmentClickListener onAssessmentClickListener){
        this.assessmentsList = assessments;
        this.onAssessmentClickListener = onAssessmentClickListener;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView assessmentTypeTextView;
        private TextView assessmentDueDateTextView;
        private TextView assessmentNotesTextView;

        OnAssessmentClickListener onAssessmentClickListener;


        public TextView getAssessmentTypeTextView() {
            return assessmentTypeTextView;
        }

        public TextView getAssessmentDueDateTextView() {
            return assessmentDueDateTextView;
        }

        public TextView getAssessmentNotesTextView() {
            return assessmentNotesTextView;
        }

        public ViewHolder(View view, OnAssessmentClickListener onAssessmentClickListener) {
            super(view);
            // Define click listener for the ViewHolder's View
            assessmentTypeTextView = view.findViewById(R.id.assessmentTypeTextView);
            assessmentDueDateTextView = view.findViewById(R.id.assessmentDueDateTextView);
            assessmentNotesTextView = view.findViewById(R.id.assessmentNotesTextView);
            this.onAssessmentClickListener = onAssessmentClickListener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onAssessmentClickListener.onAssessmentClick(getAdapterPosition());
        }

        // Click listener for row
        public interface OnAssessmentClickListener {
            void onAssessmentClick(int position);
        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *
     *
     * by RecyclerView.
     * @param
     */
    public AssessmentAdapter(List<Assessment> assessments) {
        assessmentsList = assessments;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public AssessmentAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.assessment_row_item, viewGroup, false);


        return new AssessmentAdapter.ViewHolder(view, onAssessmentClickListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(AssessmentAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        viewHolder.getAssessmentTypeTextView().setText(assessmentsList.get(position).getAssessmentType());
        viewHolder.getAssessmentNotesTextView().setText(assessmentsList.get(position).getDueDate());
        viewHolder.getAssessmentDueDateTextView().setText(assessmentsList.get(position).getNotes());






    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return assessmentsList.size();
    }
}
