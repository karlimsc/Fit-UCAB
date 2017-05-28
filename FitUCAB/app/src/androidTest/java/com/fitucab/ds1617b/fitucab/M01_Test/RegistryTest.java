package com.fitucab.ds1617b.fitucab.M01_Test;

import com.fitucab.ds1617b.fitucab.Model.Registry;

import org.junit.Test;

import static org.junit.Assert.*;

public class RegistryTest {
    Registry regis = new  Registry(1,1);

    @Test
    public void get_weight() throws Exception {

        assertEquals(1,regis.get_weight(),1);
    }

    @Test
    public void set_weight() throws Exception {
        regis.set_weight(2);
        assertEquals(2,regis.get_weight(),2);
    }

    @Test
    public void get_height() throws Exception {
        assertEquals(1,regis.get_weight(),1);
    }

    @Test
    public void set_height() throws Exception {
        regis.set_height(2);
        assertEquals(2,regis.get_height(),2);

    }

}