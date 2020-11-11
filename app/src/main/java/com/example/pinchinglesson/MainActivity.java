package com.example.pinchinglesson;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ScaleGestureDetector;
import android.widget.TextView;
import android.view.MotionEvent;

// this demo app acquires pinching data from a pinching action and displays it in a text view
// it does not result in either enlargement or shrinking of an image on the screen.  That code is
// much more extensive

public class MainActivity extends AppCompatActivity {

    ScaleGestureDetector mScaleGestureDetector;
    TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.myTextView); //view that displays the pinching data

        mScaleGestureDetector = new ScaleGestureDetector(this, new MyOnScaleGestureListener());
        // create a new instance of a ScaleGestureDetector object and named it MyOnScaleGestureListener

    } // end of onCreate method, back to main activity

        @Override
        public boolean onTouchEvent (MotionEvent event){
            mScaleGestureDetector.onTouchEvent(event); // initialize the onTouchEvent callback method
                                    // on the MainActivity, which, in turn, calls the onTouchEvent method
                                    // of the ScaleGestureDetector class
            return true;
        }

        // we create a new class called MyOnScaleGestureListener that, by extension, acquires
        // the properties of the ScaleGestureDetector which detects scaling transformation by the users
        // pinching action.  The SimpleOnScaleGestureListener can notify the user when  the scaling gesture
        // occurs.
        public class MyOnScaleGestureListener  extends ScaleGestureDetector.SimpleOnScaleGestureListener {
            @Override
            // onScale, onScaleBegin, and onScaleEnd (below) are obligatory methods for the OnScaleGestureListener

            public boolean onScale(ScaleGestureDetector whatever) { // in this case, the method displays the scale factor
                    // onScale has set "whatever" as a detector object and expects a return of type detector.
                float scaleFactor = whatever.getScaleFactor();// the detector "whatever" is used to access the scale factor data
                                                                // and that data (a float) is assigned to scaleFactor, a variable of type float
                if (scaleFactor > 1) { // if a pinching out motion is detected
                    mTextView.setText("Zooming Out");
                } else { // the scaling motion must be an inward pinch
                    mTextView.setText("Zooming In");
                }
                return true; // returns a true value to the boolean, onScale(ScaleGestureDetector detector)
            }

            @Override
            public boolean onScaleBegin(ScaleGestureDetector detector) {
                return true;

            }

            @Override
            public void onScaleEnd(ScaleGestureDetector detector) {

            }
        }
    }
