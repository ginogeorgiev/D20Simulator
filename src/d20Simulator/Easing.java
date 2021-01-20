package d20Simulator;

public class Easing {
    // t = current time , b = start value, d = duration, c = change in Value
    // t and d can be frames or seconds/milliseconds)

    public static float quintEaseIn(float t, float b, float c, float d) {
        return c * (t /= d) * t * t * t * t + b;
    }

    public static float quintEaseOut(float t, float b, float c, float d) {
        return c * ((t = t / d - 1) * t * t * t * t + 1) + b;
    }

    public static float quintEaseInOut(float t, float b, float c, float d) {
        if ((t /= d / 2) < 1) return c / 2 * t * t * t * t * t + b;
        return c / 2 * ((t -= 2) * t * t * t * t + 2) + b;
    }
}
