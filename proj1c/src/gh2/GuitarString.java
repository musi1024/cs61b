package gh2;
import deque.ArrayDeque61B;
import deque.Deque61B;

//Note: This file will not compile until you complete the Deque61B implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
     private Deque61B<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayDeque61B<>();
        int bufferSize = (int) Math.round(SR / frequency);
        for (int i = 0; i < bufferSize; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        Deque61B<Double> newBuffer = new ArrayDeque61B<>();
        for (double x : buffer) {
            newBuffer.addFirst(Math.random() - 0.5);
        }
        buffer = newBuffer;
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double removeSample = buffer.removeFirst();
        double firstSample = sample();
        double newSample = DECAY * 0.5 * (removeSample + firstSample);
        buffer.addLast(newSample);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}