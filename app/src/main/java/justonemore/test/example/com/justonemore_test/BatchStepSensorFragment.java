package justonemore.test.example.com.justonemore_test;

/**
 * Created by Darian on 3/21/2015.
 */
import android.app.Activity;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;


public class BatchStepSensorFragment extends Fragment {
    public static final String TAG = "StepSensorSample";

    // Card tags
    public static final String CARD_INTRO = "intro";
    public static final String CARD_REGISTER_DETECTOR = "register_detector";
    public static final String CARD_REGISTER_COUNTER = "register_counter";
    public static final String CARD_BATCHING_DESCRIPTION = "register_batching_description";
    public static final String CARD_COUNTING = "counting";
    public static final String CARD_EXPLANATION = "explanation";
    public static final String CARD_NOBATCHSUPPORT = "error";

    // Actions from REGISTER cards
    public static final int ACTION_REGISTER_DETECT_NOBATCHING = 10;
    public static final int ACTION_REGISTER_DETECT_BATCHING_5s = 11;
    public static final int ACTION_REGISTER_DETECT_BATCHING_10s = 12;
    public static final int ACTION_REGISTER_COUNT_NOBATCHING = 21;
    public static final int ACTION_REGISTER_COUNT_BATCHING_5s = 22;
    public static final int ACTION_REGISTER_COUNT_BATCHING_10s = 23;
    // Action from COUNTING card
    public static final int ACTION_UNREGISTER = 1;

    // State of application, used to register for sensors when app is restored
    public static final int STATE_OTHER = 0;
    public static final int STATE_COUNTER = 1;
    public static final int STATE_DETECTOR = 2;



    // Bundle tags used to store data when restoring application state
    private static final String BUNDLE_STATE = "state";
    private static final String BUNDLE_LATENCY = "latency";
    private static final String BUNDLE_STEPS = "steps";

    // max batch latency is specified in microseconds
    private static final int BATCH_LATENCY_0 = 0; // no batching
    private static final int BATCH_LATENCY_10s = 10000000;
    private static final int BATCH_LATENCY_5s = 5000000;

    /*
    For illustration we keep track of the last few events and show their delay from when the
    event occurred until it was received by the event listener.
    These variables keep track of the list of timestamps and the number of events.
     */
    // Number of events to keep in queue and display on card
    private static final int EVENT_QUEUE_LENGTH = 10;
    // List of timestamps when sensor events occurred
    private float[] mEventDelays = new float[EVENT_QUEUE_LENGTH];

    // number of events in event list
    private int mEventLength = 0;
    // pointer to next entry in sensor event list
    private int mEventData = 0;

    // Steps counted in current session
    private int mSteps = 0;
    // Value of the step counter sensor when the listener was registered.
    // (Total steps are calculated from this value.)
    private int mCounterSteps = 0;
    // Steps counted by the step counter previously. Used to keep counter consistent across rotation
    // changes
    private int mPreviousCounterSteps = 0;
    // State of the app (STATE_OTHER, STATE_COUNTER or STATE_DETECTOR)
    private int mState = STATE_OTHER;
    // When a listener is registered, the batch sensor delay in microseconds
    private int mMaxDelay = 0;

    @Override
    public void onResume() {
        super.onResume();

        /*CardStreamFragment stream = getCardStream();
        if (stream.getVisibleCardCount() < 1) {
            // No cards are visible, started for the first time
            // Prepare all cards and show the intro card.
            initialiseCards();
            showIntroCard();
            // Show the registration card if the hardware is supported, show an error otherwise
            if (isKitkatWithStepSensor()) {
                showRegisterCard();
            } else {
                showErrorCard();
            }
        }*/
    }

}
