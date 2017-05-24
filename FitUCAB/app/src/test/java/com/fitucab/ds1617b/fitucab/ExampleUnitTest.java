package com.fitucab.ds1617b.fitucab;

import com.fitucab.ds1617b.fitucab.UI.Activities.M04NotificationActivity;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testconverterMiles()
    {
        M04NotificationActivity noti = new M04NotificationActivity();
        int x = noti.converterMiles(50);
        assertEquals(31,x);
    }
    @Test
    public void testconverterKm()
    {
        M04NotificationActivity noti = new M04NotificationActivity();
        int y = noti.converterKm(5);
        assertEquals(8,y);
    }
}