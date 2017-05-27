package Domain;

import java.sql.Date;
import java.util.Calendar;

/**
 * Created by jorgelinux on 27/05/17.
 */


public class DetailChallenge {


    private int _detailId;
    private Date _detailDateStart;
    private Date _detailDateEnd;
    private boolean _detailActive;
    private int _detailIdChallenge;
    private int _detailIdUser;

    public int get_detailId() {
        return _detailId;
    }

    public void set_detailId(int _detailId) {
        this._detailId = _detailId;
    }

    public Date get_detailDateStart() {
        return _detailDateStart;
    }

    public void set_detailDateStart(Date _detailDateStart) {
        this._detailDateStart = _detailDateStart;
    }

    public Date get_detailDateEnd() {
        return _detailDateEnd;
    }

    public void set_detailDateEnd(Date _detailDateEnd) {
        this._detailDateEnd = _detailDateEnd;
    }

    public boolean is_detailActive() {
        return _detailActive;
    }

    public void set_detailActive(boolean _detailActive) {
        this._detailActive = _detailActive;
    }

    public int get_detailIdChallenge() {
        return _detailIdChallenge;
    }

    public void set_detailIdChallenge(int _detailIdChallenge) {
        this._detailIdChallenge = _detailIdChallenge;
    }

    public int get_detailIdUser() {
        return _detailIdUser;
    }

    public void set_detailIdUser(int _detailIdUser) {
        this._detailIdUser = _detailIdUser;
    }
}
