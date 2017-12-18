public class Planet {

    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double xxDis = p.xxPos - this.xxPos;
        double yyDis = p.yyPos - this.yyPos;
        return Math.sqrt(xxDis * xxDis + yyDis * yyDis);
    }

    public double calcForceExertedBy(Planet p) {
        double G = 6.67 * Math.pow(10, -11);
        return G * p.mass * this.mass / (this.calcDistance(p) * this.calcDistance(p));
    }

    public double calcForceExertedByX(Planet p) {
        double xxDis = p.xxPos - this.xxPos;
        return this.calcForceExertedBy(p) * xxDis / this.calcDistance(p);
    }
    
    public double calcForceExertedByY(Planet p) {
        double yyDis = p.yyPos - this.yyPos;
        return this.calcForceExertedBy(p) * yyDis / this.calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allP) {
        double xxNetForce = 0;
        for (int i = 0; i < allP.length; i++) {
            if (!this.equals(allP[i])) {
                xxNetForce += this.calcForceExertedByX(allP[i]);
            }
        }
        return xxNetForce;
    }

    public double calcNetForceExertedByY(Planet[] allP) {
        double yyNetForce = 0;
        for (Planet element : allP) {
            if (!this.equals(element)) {
                yyNetForce += this.calcForceExertedByY(element);
            }
        }
        return yyNetForce;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel = this.xxVel + dt * aX;
        this.yyVel = this.yyVel + dt * aY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
    }

    public void draw() {
        String path = "./images/" + this.imgFileName;
        StdDraw.picture(this.xxPos, this.yyPos, path);
    }

}
