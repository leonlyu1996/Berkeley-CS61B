import synthesizer.GuitarString;

public class GuitarHero {

    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

    public static void main(String[] args) {
        /* create two guitar strings, for concert A and C */

        GuitarString[] string = new GuitarString[keyboard.length()];

        for (int i = 0; i < keyboard.length(); i++) {
            string[i] = new GuitarString(440.0 * Math.pow(2, (i - 24) / 12));
        }


        while (true) {


            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if (index > -1) {
                    string[index].pluck();
                }
            }


            /* compute the superposition of samples */
            double sample = 0.0;
            for (GuitarString g : string) {
                sample += g.sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (GuitarString g : string) {
                g.tic();
            }


        }
    }
}
