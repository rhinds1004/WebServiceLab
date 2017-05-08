package com.example.hinds.webservicelab;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.hinds.webservicelab.course.Course;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseFragment.OnListFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "test";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_SHORTDESC = "shortDesc";
    private static final String ARG_LONGDESC = "longDesc";
    private static final String ARG_COURSEID = "courseID";
    private static final String ARG_PREREQS  = "prereqs";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView mCourseIdTextView;
    private TextView mCourseShortDescTextView;
    private TextView mCourseLongDescTextView;
    private TextView mCoursePrereqsTextView;

    private OnEditFragmentInteractionListener mListener;

    private Course mCourse;

    public CourseDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseDetailFragment newInstance(String param1, String param2) {
        CourseDetailFragment fragment = new CourseDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_course_detail, container, false);
        mCourseIdTextView = (TextView) view.findViewById(R.id.course_item_id);
        mCourseShortDescTextView = (TextView) view.findViewById(R.id.course_short_desc);
        mCourseLongDescTextView = (TextView) view.findViewById(R.id.course_long_desc);
        mCoursePrereqsTextView = (TextView) view.findViewById(R.id.course_prereqs);
        ImageButton editCourseButton = (ImageButton) view.findViewById(R.id.button_edit_course);
        editCourseButton.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    myClicker();
                                                }

                                            });
        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab);
        floatingActionButton.show();


        return view;

    }
public void myClicker(){

    Bundle args = new Bundle();
    args.putString(ARG_COURSEID, mCourseIdTextView.getText().toString());
    args.putString(ARG_SHORTDESC, mCourseShortDescTextView.getText().toString());
    args.putString(ARG_LONGDESC, mCourseLongDescTextView.getText().toString());
    args.putString(ARG_PREREQS, mCoursePrereqsTextView.getText().toString());
    //bundle.putSerializable("SerialCourse", mCourse);
    CourseEditFragment cf = new CourseEditFragment();

    cf.setArguments(args);
    FragmentManager fm = getFragmentManager();
    fm.beginTransaction()
            .replace(R.id.fragment_container, cf)
            .addToBackStack(null)
            .commit();
}


    public void updateView(Course course){
        if(course != null){
            mCourseIdTextView.setText(course.getmCourseId());
            mCourseShortDescTextView.setText(course.getmShortDescription());
            mCourseLongDescTextView.setText(course.getmLongDescription());
            mCoursePrereqsTextView.setText(course.getmPrereqs());
        }
    }

    public final static String COURSE_ITEM_SELECTED = "course_selected";
    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateView((Course) args.getSerializable(COURSE_ITEM_SELECTED));
        }

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditFragmentInteractionListener) {
          mListener = (OnEditFragmentInteractionListener) context;
           // mListener.OnEditFragmentInteraction(mCourse);
        }  else{
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnEditFragmentInteractionListener {
        // TODO: Update argument type and name
        void OnEditFragmentInteraction(Course course);
    }

}
