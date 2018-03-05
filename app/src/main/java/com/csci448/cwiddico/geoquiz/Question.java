package com.csci448.cwiddico.geoquiz;

/**
 * Created by cwiddico on 1/22/2018.
 */

public class Question {

    public Question(){

    }
    private int mTextResId;
    private boolean mAnswerTrue;
    private int qType;
    public String a1;
    public String a2;
    public String a3;
    public String a4;

    public int getTextResId() {
        return mTextResId;
    }

    public int getqType(){return qType;}

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public int getAnswerNumber() {
        return 0;
    }

    public String getAnswerText() {
        return "";
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public String geta1(){
        return a1;
    }
    public String geta2(){
        return a2;
    }
    public String geta3(){
        return a3;
    }
    public String geta4(){
        return a4;
    }


    public Question(int textResId, boolean answerTrue) {
        qType = 1;
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }
}
