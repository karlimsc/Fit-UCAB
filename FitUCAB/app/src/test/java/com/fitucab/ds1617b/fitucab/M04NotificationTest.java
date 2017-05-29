package com.fitucab.ds1617b.fitucab;

import com.fitucab.ds1617b.fitucab.UI.Activities.M04NotificationActivity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 *  Declaracion de la Clase M04NotificationTest
 *  @author Anderson Gomez
 *  @author Daniel Da Silva
 *  @author Luis Matinez
 *
 */

public class M04NotificationTest {

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

    @Test
    public void testSendMail()
    {
        int _cM;
        M04NotificationActivity noti = new M04NotificationActivity();
        _cM = noti.checkMail("luismzk@gmail.com","Asunto","Mensaje");
        
        assertEquals(1,_cM);
    }
}
