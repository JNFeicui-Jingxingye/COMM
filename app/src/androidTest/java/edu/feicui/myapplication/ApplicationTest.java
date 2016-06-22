package edu.feicui.myapplication;

import android.app.Application;
import android.test.ApplicationTestCase;

import edu.feicui.myapplication.db.DBReader;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    DBReader db = new DBReader();
    public ApplicationTest() {
        super(Application.class);
    }

    public void DBReader(){};
}