package com.gsv28rus.android.addictfoodnumber;



public class Addict {

    private int mId;                // id addict
    private String mNumber;         // e number addict
    private String mName;           // name addict
    private String mGeneral;        // general description
    private String mBenefit;        // benefit addict
    private String mHarm;           // harm addict
    private int mDanger;            // danger addict
    private int mType;              // type addict

    public Addict(int id, String number, String name, String general, String benefit, String harm, int danger, int type) {
        mId = id;
        mNumber = number;
        mName = name;
        mGeneral = general;
        mBenefit = benefit;
        mHarm = harm;
        mDanger = danger;
        mType = type;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getNumber() {
        return mNumber;
    }

    public void setNumber(String number) {
        mNumber = number;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getGeneral() {
        return mGeneral;
    }

    public void setGeneral(String general) {
        mGeneral = general;
    }

    public String getBenefit() {
        return mBenefit;
    }

    public void setBenefit(String benefit) {
        mBenefit = benefit;
    }

    public String getHarm() {
        return mHarm;
    }

    public void setHarm(String harm) {
        mHarm = harm;
    }

    public int getDanger() {
        return mDanger;
    }

    public void setDanger(int danger) {
        mDanger = danger;
    }

    public int getType() {
        return mType;
    }

    public void setType(int type) {
        mType = type;
    }
}
