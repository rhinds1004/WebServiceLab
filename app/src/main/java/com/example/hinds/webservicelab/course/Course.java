package com.example.hinds.webservicelab.course;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hinds on 5/5/2017.
 */

public class Course implements Serializable{
    public String mCourseId;
    public String mShortDescription;
    public String mLongDescription;
    public String mPrereqs ;

    public static final String ID = "id";
     public static final String SHORT_DESC = "shortDesc";
     public static final String LONG_DESC = "longDesc";
     public static final String PRE_REQS = "prereqs";

    public Course(String mCourseId, String mShortDescription, String mLongDescription, String mPrereqs) {
        this.mCourseId = mCourseId;
        this.mShortDescription = mShortDescription;
        this.mLongDescription = mLongDescription;
        this.mPrereqs = mPrereqs;
    }

    public String getmCourseId() {
        return mCourseId;
    }

    public void setmCourseId(String mCourseId) {
        this.mCourseId = mCourseId;
    }

    public String getmShortDescription() {
        return mShortDescription;
    }

    public void setmShortDescription(String mShortDescription) {
        this.mShortDescription = mShortDescription;
    }

    public String getmLongDescription() {
        return mLongDescription;
    }

    public void setmLongDescription(String mLongDescription) {
        this.mLongDescription = mLongDescription;
    }

    public String getmPrereqs() {
        return mPrereqs;
    }

    public void setmPrereqs(String mPrereqs) {
        this.mPrereqs = mPrereqs;
    }

    /**
     * Parses the json string, returns an error message if unsuccessful.
     * Returns course list if successful.
     * @param courseJSON
     * @return reason or null if unsuccessful
     */

    public static String parseCourseJSON(String courseJSON, List<Course>courseList){
        String reason = null;
        if(courseJSON != null){
            try{
                JSONArray arr = new JSONArray(courseJSON);

                for(int i = 0; i < arr.length(); i++){
                    JSONObject obj = arr.getJSONObject(i);
                    Course course = new Course(obj.getString(Course.ID), obj.getString(Course.SHORT_DESC),
                    obj.getString(Course.LONG_DESC), obj.getString(Course.PRE_REQS));
                    courseList.add(course);
                }
            }catch(JSONException e){
                reason = "Unable to parse data, Reason: " + e.getMessage();
            }
        }
        return reason;
    }

}
