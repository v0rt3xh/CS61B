import synthesizer.GuitarString;

public class GuitarHero {

    private static final double CONCERT_A = 440.0;
    private static final int KEYNUM = 37;
    //create the GuitarStringList

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] GuitarStringAll = new GuitarString[KEYNUM];
        for (int i = 0; i < KEYNUM; i++) {
            GuitarStringAll[i] = new synthesizer.GuitarString(CONCERT_A * Math.pow(2, (i - 24.0) / 12.0));
        }


        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                //Just for convenience = =
                if (index < 0) {
                    index = 0;
                }
                GuitarStringAll[index].pluck();
            }

            /* compute the superposition of samples */
            double sample = 0.0 ;
            for (int j = 0; j < KEYNUM; j++) {
                sample += GuitarStringAll[j].sample();
            }


            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int j = 0; j < KEYNUM; j++) {
                GuitarStringAll[j].tic();
            }
        }
    }
}
