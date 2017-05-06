package com.example.hinds.webservicelab;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hinds.webservicelab.CourseFragment.OnListFragmentInteractionListener;
import com.example.hinds.webservicelab.course.Course;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Course} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyCourseRecyclerViewAdapter extends RecyclerView.Adapter<MyCourseRecyclerViewAdapter.ViewHolder> {

    private final List<Course> mValues;
    private final OnListFragmentInteractionListener mListener;


    public MyCourseRecyclerViewAdapter(List<Course> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_course, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mCourse = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getmCourseId());
        System.out.println(mValues.get(position).getmCourseId().toString());
        holder.mContentView.setText(mValues.get(position).getmShortDescription());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //System.out.println(holder.mCourse);
                    mListener.onListFragmentInteraction(holder.mCourse);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Course mCourse;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }



}
