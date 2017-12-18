public class NBody {

    public static double readRadius(String fileName) {
        
        In file = new In(fileName);

        int num = file.readInt();
        return file.readDouble();

    }
    
    public static Planet[] readPlanets(String fileName) {
        
        In file = new In(fileName);

        int num = file.readInt();
        file.readDouble();

        Planet[] planets = new Planet[num];

        for (int i = 0; i < num; i++) {
            double xxPos = file.readDouble();
            double yyPos = file.readDouble();
            double xxVel = file.readDouble();
            double yyVel = file.readDouble();
            double mass = file.readDouble();
            String imgFileName = file.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }

        return planets;
        
    }

    public static void main(String[] args) {

        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        String backgroundImg = "./images/starfield.jpg";

        StdDraw.setScale(-radius, radius);

        double time = 0;
        int planetsNum = planets.length;

        String backgroundMusic = "./audio/2001.mid";
        StdAudio.loop(backgroundMusic);

        while (time < T) {

            double[] xForces = new double[planetsNum];
            double[] yForces = new double[planetsNum];

            for (int i = 0; i < planetsNum; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planetsNum; i++) {
               planets[i].update(dt, xForces[i], yForces[i]);
            }

            // Clears the drawing window. 
            StdDraw.clear();

            // Draw the background image.
            StdDraw.picture(0, 0, backgroundImg);

            for (Planet p : planets) {
                p.draw();
            }
            
            // Pause the animation for 10 milliseconds. 
            StdDraw.show(10);

            time = time + dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
	        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
   		    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);	
        }		

    }

}
