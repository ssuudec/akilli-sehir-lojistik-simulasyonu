public class Konum {
    private double x;
    private double y;

    public Konum(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    // iki konum arasındaki mesafe koordinatlar üzerinden hesaplanır
    public double mesafeHesapla(Konum hedef) {
        if (hedef == null) {
            return 0.0;
        }
        double farkX = this.x - hedef.getX();
        double farkY = this.y - hedef.getY();
        return Math.sqrt(farkX * farkX + farkY * farkY);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
