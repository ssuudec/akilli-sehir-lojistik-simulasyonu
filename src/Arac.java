// bu sınıfta araçların ortak özellikleri ve metotları toplanır
// kamyon, motor ve drone teslimat kuralları bu sınıftan kalıtım alınarak yazılır
public abstract class Arac {
    private String plaka;
    private double kapasite;
    private double yakit;
    private double maksimumYakit;
    private double hiz;
    private Konum guncelKonum;

    public Arac(String plaka, double kapasite, double maksimumYakit, double hiz, Konum guncelKonum) {
        this.plaka = plaka;
        this.kapasite = kapasite;
        this.maksimumYakit = maksimumYakit;
        this.yakit = maksimumYakit;
        this.hiz = hiz;
        this.guncelKonum = guncelKonum;
    }

    // her aracın teslimat şekli farklı olabildiği için bu metot alt sınıflarda doldurulur
    public abstract void teslimatYap(Paket paket) throws Exception;

    // her aracın yakıt tüketim oranı farklı olduğu için bu katsayı alt sınıflarda belirlenir
    public abstract double getYakitTuketimOrani();

    // yakıt biterse veya yetmezse araç tekrar maksimum yakıtına çıkarılır
    public void yakitIkmal() {
        this.yakit = this.maksimumYakit;
    }

    public String getPlaka() {
        return plaka;
    }

    public void setPlaka(String plaka) {
        this.plaka = plaka;
    }

    public double getKapasite() {
        return kapasite;
    }

    public void setKapasite(double kapasite) {
        this.kapasite = kapasite;
    }

    public double getYakit() {
        return yakit;
    }

    public void setYakit(double yakit) {
        this.yakit = yakit;
    }

    public double getMaksimumYakit() {
        return maksimumYakit;
    }

    public void setMaksimumYakit(double maksimumYakit) {
        this.maksimumYakit = maksimumYakit;
    }

    public double getHiz() {
        return hiz;
    }

    public void setHiz(double hiz) {
        this.hiz = hiz;
    }

    public Konum getGuncelKonum() {
        return guncelKonum;
    }

    public void setGuncelKonum(Konum guncelKonum) {
        this.guncelKonum = guncelKonum;
    }

    @Override
    public String toString() {
        return "Arac{" +
                "Plaka='" + plaka + '\'' +
                ", Kapasite=" + kapasite +
                " kg, Yakit=" + String.format("%.2f", yakit) +
                "/" + maksimumYakit +
                ", Hiz=" + hiz +
                " km/s, Konum=" + guncelKonum +
                '}';
    }
}
