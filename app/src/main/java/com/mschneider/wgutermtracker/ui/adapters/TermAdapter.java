package com.mschneider.wgutermtracker.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mschneider.wgutermtracker.R;
import com.mschneider.wgutermtracker.models.Term;

import java.util.List;


public class TermAdapter extends RecyclerView.Adapter<TermAdapter.ViewHolder> {
    private int pos;
    private List<Term> termsList;

    private ViewHolder.OnTermClickListener onTermClickListener;

    public TermAdapter(List<Term> terms, ViewHolder.OnTermClickListener onTermClickListener){
        this.termsList = terms;
        this.onTermClickListener = onTermClickListener;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView termTitleTextView;
        private TextView termStartDateTextView;
        private TextView termEndDateTextView;
        OnTermClickListener onTermClickListener;
        

        public ViewHolder(View view, OnTermClickListener onTermClickListener) {
            super(view);
            // Define click listener for the ViewHolder's View
            termTitleTextView = view.findViewById(R.id.termTitleTextView);
            termStartDateTextView = view.findViewById(R.id.termStartDateTextView);
            termEndDateTextView = view.findViewById(R.id.termEndDateTextView);
            this.onTermClickListener = onTermClickListener;
            view.setOnClickListener(this);

        }

        public TextView getTermTitleTextView() {
            return termTitleTextView;
        }
        public TextView getTermStartDateTextView() {
            return termStartDateTextView;
        }
        public TextView getTermEndDateTextView() {
            return termEndDateTextView;
        }

        @Override
        public void onClick(View v) {
            onTermClickListener.onTermClick(getAdapterPosition());
        }

        // Click listener for row
        public interface OnTermClickListener {
            void onTermClick(int position);
        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *
     *
     * by RecyclerView.
     * @param terms
     */
    public TermAdapter(List<Term> terms) {
        termsList = terms;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.term_row_item, viewGroup, false);
        

        return new ViewHolder(view, onTermClickListener);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTermTitleTextView().setText(termsList.get(position).getTitle());
        viewHolder.getTermStartDateTextView().setText(termsList.get(position).getStartDate());
        viewHolder.getTermEndDateTextView().setText(termsList.get(position).getEndDate());



    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return termsList.size();
    }
    
    
}
