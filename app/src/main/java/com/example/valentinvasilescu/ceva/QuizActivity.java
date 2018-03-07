package com.example.valentinvasilescu.ceva;

import android.os.CountDownTimer;
import android.provider.DocumentsContract;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.awareness.snapshot.TimeIntervalsResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;

public class QuizActivity extends AppCompatActivity {
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mQuestionRef = mRootRef.child("Questions");
    DatabaseReference mQuestionRef1 = mQuestionRef.child("1");
    DatabaseReference mQuestionRef1text = mQuestionRef1.child("text");
    DatabaseReference mQuestionRef1A = mQuestionRef1.child("A");
    DatabaseReference mQuestionRef1B = mQuestionRef1.child("B");
    DatabaseReference mQuestionRef1C = mQuestionRef1.child("C");
    DatabaseReference mQuestionRef2 = mQuestionRef.child("2");
    DatabaseReference mQuestionRef2text = mQuestionRef2.child("text");
    DatabaseReference mQuestionRef2A = mQuestionRef2.child("A");
    DatabaseReference mQuestionRef2B = mQuestionRef2.child("B");
    DatabaseReference mQuestionRef2C = mQuestionRef2.child("C");
    DatabaseReference mQuestionRef3 = mQuestionRef.child("3");
    DatabaseReference mQuestionRef3text = mQuestionRef3.child("text");
    DatabaseReference mQuestionRef3A = mQuestionRef3.child("A");
    DatabaseReference mQuestionRef3B = mQuestionRef3.child("B");
    DatabaseReference mQuestionRef3C = mQuestionRef3.child("C");
    DatabaseReference mQuestionRef4 = mQuestionRef.child("4");
    DatabaseReference mQuestionRef4text = mQuestionRef4.child("text");
    DatabaseReference mQuestionRef4A = mQuestionRef4.child("A");
    DatabaseReference mQuestionRef4B = mQuestionRef4.child("B");
    DatabaseReference mQuestionRef4C = mQuestionRef4.child("C");


    TextView textquestion;
    CheckBox a1;
    CheckBox a2;
    CheckBox a3;
    Button nextbutton;
    Button yes;
    Button no;
    int q = 1;
    int the_answer;
    boolean database_changed = false;
    TextInputLayout addq;
    TextInputLayout adda1;
    TextInputLayout adda2;
    TextInputLayout adda3;
    RadioGroup corect_answer;
    Button submit_question;
    TextView getanswer;
    RadioButton valida1;
    RadioButton valida2;
    RadioButton valida3;
    TextView question_addq;
    TextView answer_adda1;
    TextView answer_adda2;
    TextView answer_adda3;



    TextView congrats;
    private final String TAG = "QUIZ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        nextbutton = (Button) findViewById(R.id.submit);
        yes = (Button)findViewById(R.id.yesbutton);
        no = (Button)findViewById(R.id.nobutton);
        textquestion = (TextView) findViewById(R.id.question);
        a1 = (CheckBox) findViewById(R.id.answer1);
        a2 = (CheckBox) findViewById(R.id.answer2);
        a3 = (CheckBox) findViewById(R.id.answer3);
        congrats = (TextView) findViewById(R.id.finaltext);
        addq = (TextInputLayout) findViewById(R.id.addquestion);
        adda1 = (TextInputLayout) findViewById(R.id.addanswer1);
        adda2 = (TextInputLayout) findViewById(R.id.addanswer2);
        adda3 = (TextInputLayout) findViewById(R.id.addanswer3);
        corect_answer = (RadioGroup) findViewById(R.id.radioGroup);
        submit_question = (Button) findViewById(R.id.submit_question);
        getanswer = (TextView) findViewById(R.id.answerquestion) ;
        valida1 = (RadioButton) findViewById(R.id.valid1);
        valida2 = (RadioButton) findViewById(R.id.valid2);
        valida3 = (RadioButton) findViewById(R.id.valid3);
        question_addq = (TextView) findViewById(R.id.ques);
        answer_adda1 = (TextView) findViewById(R.id.ans1);
        answer_adda2 = (TextView) findViewById(R.id.ans2);
        answer_adda3 = (TextView) findViewById(R.id.ans3);



        //fb = new Firebase("https://multipurpouseapp.firebaseio.com/");
        //DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();


        nextbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify_and_update(v);
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addquestion(v);

            }

            private void addquestion(View v) {
                yes.setVisibility(View.GONE);
                no.setVisibility(View.GONE);
                congrats.setVisibility(View.INVISIBLE);
                addq.setVisibility(View.VISIBLE);
                adda1.setVisibility(View.VISIBLE);
                adda2.setVisibility(View.VISIBLE);
                adda3.setVisibility(View.VISIBLE);
                corect_answer.setVisibility(View.VISIBLE);
                submit_question.setVisibility(View.VISIBLE);
                getanswer.setVisibility(View.VISIBLE);


            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quiz_resume(v);
            }
        });
        submit_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_question_to_database(v);
                quiz_resume(v);
            }
        });












    }

    @Override
    protected void onStart() {
        super.onStart();



        mQuestionRef1text.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                textquestion.setText(text);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mQuestionRef1A.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                a1.setText(text);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mQuestionRef1B.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                a2.setText(text);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mQuestionRef1C.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text = dataSnapshot.getValue(String.class);
                a3.setText(text);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    private void move_question_to_database(View v) {
        mQuestionRef4text.setValue(question_addq.getText().toString());
        mQuestionRef4A.setValue(answer_adda1.getText().toString());
        mQuestionRef4B.setValue(answer_adda2.getText().toString());
        mQuestionRef4C.setValue(answer_adda3.getText().toString());
        if(valida1.isChecked())
            the_answer = 1;
        if(valida2.isChecked())
            the_answer = 2;
        if(valida3.isChecked())
            the_answer = 3;

        database_changed = true;
    }

    private void quiz_resume(View v) {
        yes.setVisibility(View.GONE);
        no.setVisibility(View.GONE);
        addq.setVisibility(View.GONE);
        adda1.setVisibility(View.GONE);
        adda2.setVisibility(View.GONE);
        adda3.setVisibility(View.GONE);
        corect_answer.setVisibility(View.GONE);
        submit_question.setVisibility(View.GONE);
        getanswer.setVisibility(View.GONE);
        //
        congrats.setVisibility(View.INVISIBLE);
        nextbutton.setVisibility(View.VISIBLE);
        textquestion.setVisibility(View.VISIBLE);
        a1.setVisibility(View.VISIBLE);
        a2.setVisibility(View.VISIBLE);
        a3.setVisibility(View.VISIBLE);


    }

    public void verify_and_update(View v) {
        Log.d(TAG, String.valueOf(the_answer));

        if (q == 1) {
            if (a1.isChecked() == false) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }

            if (a2.isChecked()) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (a3.isChecked()) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getApplicationContext(), "Corect!", Toast.LENGTH_SHORT).show();
            a1.setChecked(false);
            q++;



        }
        if (q == 2) {
            mQuestionRef2text.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    textquestion.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mQuestionRef2A.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a1.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mQuestionRef2B.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a2.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mQuestionRef2C.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a3.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            if (a1.isChecked()) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }

            if (a2.isChecked()) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (a3.isChecked() == false) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getApplicationContext(), "Corect!", Toast.LENGTH_SHORT).show();
            a3.setChecked(false);
            q++;

        }
        if (q == 3) {
            textquestion.setTextSize(25);
            a3.setTextSize(15);
            mQuestionRef3text.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    textquestion.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mQuestionRef3A.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a1.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mQuestionRef3B.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a2.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mQuestionRef3C.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a3.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            if (a1.isChecked()) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }

            if (a2.isChecked()) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }
            if (a3.isChecked() == false) {
                //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(getApplicationContext(), "Corect!", Toast.LENGTH_SHORT).show();
            a3.setChecked(false);
            q++;
        }
        //Log.d(TAG, String.valueOf(q));
        if(q == 4) {

            mQuestionRef4text.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    textquestion.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            mQuestionRef4A.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a1.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mQuestionRef4B.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a2.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
            mQuestionRef4C.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String text = dataSnapshot.getValue(String.class);
                    a3.setTextSize(20);
                    a3.setText(text);
                }


                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            if (the_answer == 1 && a1.isChecked() ) {
                Toast.makeText(getApplicationContext(), "Corect!", Toast.LENGTH_SHORT).show();
                q++;
            }
            if (the_answer == 2 && a2.isChecked()) {
                Toast.makeText(getApplicationContext(), "Corect!", Toast.LENGTH_SHORT).show();
                q++;
            }
            if (the_answer == 3 && a3.isChecked()) {
                Toast.makeText(getApplicationContext(), "Corect!", Toast.LENGTH_SHORT).show();
                q++;
            }

            //Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
        }
        if(q >= 5) {
            a1.setVisibility(View.GONE);
            a2.setVisibility(View.GONE);
            a3.setVisibility(View.GONE);
            nextbutton.setVisibility(View.GONE);
            textquestion.setVisibility(View.GONE);
            congrats.setText("WOW YOU DID IT");
            congrats.setVisibility(View.VISIBLE);
        }

    }
}



