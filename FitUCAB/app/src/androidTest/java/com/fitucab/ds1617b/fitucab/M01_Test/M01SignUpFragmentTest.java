package com.fitucab.ds1617b.fitucab.M01_Test;

import com.fitucab.ds1617b.fitucab.UI.Fragments.M01.M01SignUpFragment;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Daniel on 28-05-2017.
 */
public class M01SignUpFragmentTest {
    @Test
    public void validateComponents() throws Exception {
        M01SignUpFragment prueba = new M01SignUpFragment();
        prueba.getRetrofit("daniel","sdfsdfsdf","daniel@gmail.com","04122584809","12345687sdfsd","11/06/1993","78","1");

    }

}