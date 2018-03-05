package com.csci448.cwiddico.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mButton1;
    private Button mButton2;
    private Button mButton3;
    private Button mButton4;
    private Button mSubmitButton;
    private EditText mEdit;
    private Button mNextButton;
    private Button mCheatButton;
    private boolean mIsCheater;
    private TextView mQuestionTextView;
    private TextView mPointsView;
    private int mCurrentIndex = 0;
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";
    private static final int REQUEST_CODE_CHEAT = 0;
    private int points = 0;
    private boolean done = false;


    private Question[] mQuestionBank = new Question[]{
            new BlankQuestion(R.string.question_blank,"yes"),
            new BlankQuestion(R.string.question_blank2,"denver"),
            new MultipleChoiceQuestion(R.string.question_mult,"No", "Yes", "Dunno", "Maybe", 2),
            new MultipleChoiceQuestion(R.string.question_mult2,"Montana", "Texas", "Florida", "Alaska", 4),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, true),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, false),

    };

    private void updateQuestion() {
        done = false;
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        if (mQuestionBank[mCurrentIndex].getqType() == 2) {
            setContentView(R.layout.multiplechoicelayout);
            updateMult();
            mQuestionTextView.setText(question);
        } else if(mQuestionBank[mCurrentIndex].getqType() == 1) {
            setContentView(R.layout.activity_quiz);
            updateQuiz();
            mQuestionTextView.setText(question);
        }
        else if(mQuestionBank[mCurrentIndex].getqType() == 3){
            setContentView(R.layout.fillblanklayout);
            updateBlank();
            mQuestionTextView.setText(question);
        }


    }

    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
        int messageResId = 0;
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {

            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                if(!done) {
                    points++;
                    done = true;
                    mPointsView.setText(String.valueOf(String.format("%d Points", points)));
                }
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast ansToast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        ansToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        ansToast.show();
    }

    private void checkAnswerMult(int userInput) {
        int answerIsTrue = mQuestionBank[mCurrentIndex].getAnswerNumber();
        int messageResId = 0;
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {

            if (userInput == answerIsTrue) {
                messageResId = R.string.correct_toast;
                if(!done) {
                    points++;
                    done = true;
                    mPointsView.setText(String.valueOf(String.format("%d Points", points)));
                }
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast ansToast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        ansToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        ansToast.show();
    }

    private void checkAnswerBlank(String userInput) {
        String answerIsTrue = mQuestionBank[mCurrentIndex].getAnswerText();
        int messageResId = 0;
        if (mIsCheater) {
            messageResId = R.string.judgment_toast;
        } else {

            if (userInput.equals(answerIsTrue)) {
                messageResId = R.string.correct_toast;
                if(!done) {
                    points++;
                    done = true;
                    mPointsView.setText(String.valueOf(String.format("%d Points", points)));
                }
            } else {
                messageResId = R.string.incorrect_toast;
            }
        }

        Toast ansToast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT);
        ansToast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);
        ansToast.show();
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate(bundle) called");
        setContentView(R.layout.activity_quiz);

        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }


        updateQuestion();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data == null) {
                return;
            }
            mIsCheater = CheatActivity.wasAnswerShown(data);
        }
    }

    protected void updateQuiz() {

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mPointsView = (TextView) findViewById(R.id.point_view);
        mPointsView.setText(String.valueOf(String.format("%d Points", points)));

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(true);
            }
        });
        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswer(false);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
    }

    protected void updateMult() {

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mPointsView = (TextView) findViewById(R.id.point_view);
        mPointsView.setText(String.valueOf(String.format("%d", points, " Points")));

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        mButton1 = (Button) findViewById(R.id.button1);
        mButton1.setText(mQuestionBank[mCurrentIndex].geta1());
        mButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerMult(1);
            }
        });
        mButton2 = (Button) findViewById(R.id.button2);
        mButton2.setText(mQuestionBank[mCurrentIndex].geta2());
        mButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerMult(2);
            }
        });

        mButton3 = (Button) findViewById(R.id.button3);
        mButton3.setText(mQuestionBank[mCurrentIndex].geta3());
        mButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerMult(3);
            }
        });

        mButton4 = (Button) findViewById(R.id.button4);
        mButton4.setText(mQuestionBank[mCurrentIndex].geta4());
        mButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerMult(4);
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
    }
    protected void updateBlank() {

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);

        mPointsView = (TextView) findViewById(R.id.point_view);
        mPointsView.setText(String.valueOf(String.format("%d Points", points)));

        mQuestionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mEdit = (EditText)findViewById(R.id.text_input);

        mSubmitButton = (Button) findViewById(R.id.submit_button);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkAnswerBlank(mEdit.getText().toString().toLowerCase());
            }
        });

        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mIsCheater = false;
                updateQuestion();
            }
        });

        mCheatButton = (Button) findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivityForResult(i, REQUEST_CODE_CHEAT);
            }
        });
    }
}
