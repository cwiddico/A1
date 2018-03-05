package com.csci448.cwiddico.geoquiz;

/**
 * Created by cwiddico on 2/2/2018.
 */

public class BlankQuestion extends Question {
    private int mTextResId;
    private String textAnswer;
    public int qType;

    @Override
    public int getTextResId() {
        return mTextResId;
    }

    @Override
    public int getqType(){return qType;}

    @Override
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    @Override
    public String getAnswerText() {
        return textAnswer;
    }

    public BlankQuestion(int textResId, String answer){

        qType = 3;
        mTextResId = textResId;
        textAnswer = answer;
    }
}
