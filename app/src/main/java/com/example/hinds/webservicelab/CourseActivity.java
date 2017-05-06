package com.example.hinds.webservicelab;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.hinds.webservicelab.course.Course;

public class CourseActivity extends AppCompatActivity implements
        CourseFragment.OnListFragmentInteractionListener{

    private static final String TAG = "MyFragment" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
/*        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        if(savedInstanceState == null || getSupportFragmentManager().findFragmentById(R.id.list) == null){
           CourseFragment courseFragment = new CourseFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, courseFragment)
                    .commit();
        }
    }

    @Override
    public void onListFragmentInteraction(Course course) {
        System.out.println("onListFragmentInteraction");
        CourseDetailFragment courseDetailFragment = new CourseDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(CourseDetailFragment.COURSE_ITEM_SELECTED,  course);
        courseDetailFragment.setArguments(args);
        System.out.println("CLicked");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, courseDetailFragment)
                .addToBackStack(null)
                .commit();

    }
}
