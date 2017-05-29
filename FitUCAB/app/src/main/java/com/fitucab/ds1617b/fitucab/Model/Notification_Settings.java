package com.fitucab.ds1617b.fitucab.Model;

/**
 * Created by Daniel Da Silva on 27/5/2017.
 */

public class Notification_Settings {

    private int _idNotification;
    private boolean _preferenceFriends;
    private boolean _preferenceActivity;
    private boolean _preferenceTraining;
    private boolean _preferenceChallenges;
    private boolean _preferenceHydration;
    private boolean _preferenceCalories;
    private boolean _preferenceGamification;
    private String _preferenceLanguage;
    private String _preferenceUnit;
    private int _preferenceRadius;

    /**
     * Constructor vacio
     */

    public Notification_Settings(){}

    /**
     * Constructor con todos los atributos
     * @param idNotification
     * @param preferenceFriends
     * @param preferenceActivity
     * @param preferenceTraining
     * @param preferenceChallenges
     * @param preferenceHydration
     * @param preferenceCalories
     * @param preferenceGamification
     * @param preferenceLanguage
     * @param preferenceUnit
     * @param preferenceRadius
     */

    public Notification_Settings(int idNotification, boolean preferenceFriends, boolean preferenceActivity, boolean preferenceTraining,
                                 boolean preferenceChallenges, boolean preferenceHydration, boolean preferenceCalories, boolean preferenceGamification,
                                 String preferenceLanguage, String preferenceUnit, int preferenceRadius)
    {
        this._idNotification = idNotification;
        this._preferenceFriends = preferenceFriends;
        this._preferenceActivity = preferenceActivity;
        this._preferenceTraining = preferenceTraining;
        this._preferenceChallenges = preferenceChallenges;
        this._preferenceHydration = preferenceHydration;
        this._preferenceCalories = preferenceCalories;
        this._preferenceGamification = preferenceGamification;
        this._preferenceLanguage = preferenceLanguage;
        this._preferenceUnit = preferenceUnit;
        this._preferenceRadius = preferenceRadius;
    }

    /**
     * Constructor sin el id de la configuración
     * @param preferenceFriends
     * @param preferenceActivity
     * @param preferenceTraining
     * @param preferenceChallenges
     * @param preferenceHydration
     * @param preferenceCalories
     * @param preferenceGamification
     * @param preferenceLanguage
     */

    public Notification_Settings(boolean preferenceFriends, boolean preferenceActivity, boolean preferenceTraining,
                                 boolean preferenceChallenges, boolean preferenceHydration, boolean preferenceCalories,
                                 boolean preferenceGamification, String preferenceLanguage, String preferenceUnit, int preferenceRadius)
    {

        this._preferenceFriends = preferenceFriends;
        this._preferenceActivity = preferenceActivity;
        this._preferenceTraining = preferenceTraining;
        this._preferenceChallenges = preferenceChallenges;
        this._preferenceHydration = preferenceHydration;
        this._preferenceCalories = preferenceCalories;
        this._preferenceGamification = preferenceGamification;
        this._preferenceLanguage = preferenceLanguage;
        this._preferenceUnit = preferenceUnit;
        this._preferenceRadius = preferenceRadius;
    }

    /**
     * Getters y Setters
     */

    public int get_idNotification() {
        return _idNotification;
    }

    public void set_idNotification(int idNotification) {
        this._idNotification = idNotification;
    }

    public boolean is_preferenceFriends() {
        return _preferenceFriends;
    }

    public void set_preferenceFriends(boolean preferenceFriends) {
        this._preferenceFriends = preferenceFriends;
    }

    public boolean is_preferenceActivity() {
        return _preferenceActivity;
    }

    public void set_preferenceActivity(boolean preferenceActivity) {
        this._preferenceActivity = preferenceActivity;
    }

    public boolean is_preferenceTraining() {
        return _preferenceTraining;
    }

    public void set_preferenceTraining(boolean preferenceTraining) {
        this._preferenceTraining = preferenceTraining;
    }

    public boolean is_preferenceChallenges() {
        return _preferenceChallenges;
    }

    public void set_preferenceChallenges(boolean preferenceChallenges) {
        this._preferenceChallenges = preferenceChallenges;
    }

    public boolean is_preferenceHydration() {
        return _preferenceHydration;
    }

    public void set_preferenceHydration(boolean preferenceHydration) {
        this._preferenceHydration = preferenceHydration;
    }

    public boolean is_preferenceCalories() {
        return _preferenceCalories;
    }

    public void set_preferenceCalories(boolean preferenceCalories) {
        this._preferenceCalories = preferenceCalories;
    }

    public boolean is_preferenceGamification() {
        return _preferenceGamification;
    }

    public void set_preferenceGamification(boolean preferenceGamification) {
        this._preferenceGamification = preferenceGamification;
    }

    public String get_preferenceLanguage() {
        return _preferenceLanguage;
    }

    public String get_preferenceUnit() {
        return _preferenceUnit;
    }

    public void set_preferenceUnit(String _preferenceUnit) {
        this._preferenceUnit = _preferenceUnit;
    }

    public int get_preferenceRadius() {
        return _preferenceRadius;
    }

    public void set_preferenceRadius(int _preferenceRadius) {
        this._preferenceRadius = _preferenceRadius;
    }


}
