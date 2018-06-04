package com.gscape.sdp.galacticescape;

import android.test.suitebuilder.TestSuiteBuilder;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserDataTest {

    /*
        Written by Aseni, checking to see if all user data are of Type String, so that it can
        be used in a JSON file.
     */
    @Test
    public void UserInstancesAllStrings(){
        User u = new User();
        u.setScore("2");
        u.setLives("1");
        u.setUsername("name");
        u.setAchievements("zero");
        u.setCurrentPos("<2,3,0>");
        assertEquals("2", u.getScore());
        assertEquals("1", u.getLives());
        assertEquals("name",u.getUsername());
        assertEquals("zero", u.getAchievements());
        assertEquals("<2,3,0>", u.getCurrentPos());
    }



}
