package com.fitucab.ds1617b.fitucab.Model;

/**
 * Created by andres on 19/05/17.
 */
/**
 * Created by andres on 13/05/17.
 */
public class Person {
    int personid;
    String personusername;
    String personpassword;
    String personemail;
    String personsex;
    String personphone;
    int personingreso;

    public Person() {
    }

    public Person(int personid, String personusername, String personpassword, String personemail, String personsex, String personphone, int personingreso) {
        this.personid = personid;
        this.personusername = personusername;
        this.personpassword = personpassword;
        this.personemail = personemail;
        this.personsex = personsex;
        this.personphone = personphone;
        this.personingreso = personingreso;
    }

    public int getPersonid() {
        return personid;
    }

    public void setPersonid(int personid) {
        this.personid = personid;
    }

    public String getPersonusername() {
        return personusername;
    }

    public void setPersonusername(String personusername) {
        this.personusername = personusername;
    }

    public String getPersonpassword() {
        return personpassword;
    }

    public void setPersonpassword(String personpassword) {
        this.personpassword = personpassword;
    }

    public String getPersonemail() {
        return personemail;
    }

    public void setPersonemail(String personemail) {
        this.personemail = personemail;
    }

    public String getPersonsex() {
        return personsex;
    }

    public void setPersonsex(String personsex) {
        this.personsex = personsex;
    }

    public String getPersonphone() {
        return personphone;
    }

    public void setPersonphone(String personphone) {
        this.personphone = personphone;
    }

    public int getPersoningreso() {
        return personingreso;
    }

    public void setPersoningreso(int personingreso) {
        this.personingreso = personingreso;
    }
}


