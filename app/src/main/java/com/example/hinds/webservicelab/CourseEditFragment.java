package com.example.hinds.webservicelab;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link CourseEditFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link CourseEditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CourseEditFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_SHORTDESC = "shortDesc";
    private static final String ARG_LONGDESC = "longDesc";
    private static final String ARG_COURSEID = "courseID";
    private static final String ARG_PREREQS  = "prereqs";
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private TextView mCourseIdTextView;
    private EditText mCourseShortDescEditText;
    private EditText mCourseLongDescEditText;
    private EditText mCoursePrereqsEditText;

   // private OnFragmentInteractionListener mListener;
    private CourseAddFragment.CourseAddListener mListener;

    public CourseEditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CourseEditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CourseEditFragment newInstance(String param1, String param2) {
        CourseEditFragment fragment = new CourseEditFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
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
        View view =  inflater.inflate(R.layout.fragment_course_edit, container, false);

         mCourseIdTextView       =  (TextView) view.findViewById(R.id.editText_edit_course_id) ;
         mCourseShortDescEditText= (EditText) view.findViewById(R.id.editText_edit_course_short_desc)  ;
         mCourseLongDescEditText =  (EditText) view.findViewById(R.id.editText_edit_course_long_desc) ;
         mCoursePrereqsEditText  =  (EditText) view.findViewById(R.id.editText_edit_course_prereqs) ;
        mCourseIdTextView.setText(getArguments().getString(ARG_COURSEID));
        mCourseShortDescEditText.setText(getArguments().getString(ARG_SHORTDESC));
        mCourseLongDescEditText.setText(getArguments().getString(ARG_LONGDESC));
        mCoursePrereqsEditText.setText(getArguments().getString(ARG_PREREQS));

       Button editCourseButton = (Button) view.findViewById(R.id.button_edit_course);
        editCourseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = buildCourseURL(v);
                mListener.addCourse(url);
            }
        });


        return view;
    }

    private final static String COURSE_EDIT_URL
            = "http://cssgate.insttech.washington.edu/~hindsr/Android/editCourse.php?";
    private String buildCourseURL (View v){

        StringBuilder sb = new StringBuilder(COURSE_EDIT_URL);

        try {

            String courseId = mCourseIdTextView.getText().toString();
            sb.append("id=");
            sb.append(courseId);


            String courseShortDesc = mCourseShortDescEditText.getText().toString();
            sb.append("&shortDesc=");
            sb.append(URLEncoder.encode(courseShortDesc, "UTF-8"));


            String courseLongDesc = mCourseLongDescEditText.getText().toString();
            sb.append("&longDesc=");
            sb.append(URLEncoder.encode(courseLongDesc, "UTF-8"));
            String coursePrereqs = mCoursePrereqsEditText.getText().toString();
            sb.append("&prereqs=");
            sb.append(URLEncoder.encode(coursePrereqs, "UTF-8"));

            Log.i("CourseAddFragment", sb.toString());

        } catch (Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CourseAddFragment.CourseAddListener) {
            mListener = (CourseAddFragment.CourseAddListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement CourseAddListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
