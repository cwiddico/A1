package com.csci448.cwiddico.geoquiz;

public class MultipleChoiceQuestion extends Question{

    private int mTextResId;
    private int mAnswerNumber;
    public int qType;
    public String a1;
    public String a2;
    public String a3;
    public String a4;

    @Override
    public int getTextResId() {
        return mTextResId;
    }

    @Override
    public int getqType(){return qType;}

    @Override
    public String geta1(){
        return a1;
    }
    @Override
    public String geta2(){
        return a2;
    }
    @Override
    public String geta3(){
        return a3;
    }
    @Override
    public String geta4(){
        return a4;
    }

    @Override
    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    @Override
    public int getAnswerNumber() {
        return mAnswerNumber;
    }

    public void setAnswerTrue(int answerTrue) {
        mAnswerNumber = answerTrue;
    }

    public MultipleChoiceQuestion(int textResId,String a1ResId,String a2ResId,String a3ResId,String a4ResId, int answerTrue){
        qType = 2;
        mTextResId = textResId;
        mAnswerNumber = answerTrue;
        a1 = a1ResId;
        a2 = a2ResId;
        a3 = a3ResId;
        a4 = a4ResId;
    }
}
