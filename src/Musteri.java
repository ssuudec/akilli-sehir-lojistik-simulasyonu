public class Musteri {
    private String ad;
    private String id;
    private Konum konum;
    private boolean darSokakMi;

    public Musteri(String ad, String id, Konum konum, boolean darSokakMi) {
        this.ad = ad;
        this.id = id;
        this.konum = konum;
        this.darSokakMi = darSokakMi;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Konum getKonum() {
        return konum;
    }

    public void setKonum(Konum konum) {
        this.konum = konum;
    }

    public boolean isDarSokakMi() {
        return darSokakMi;
    }

    public void setDarSokakMi(boolean darSokakMi) {
        this.darSokakMi = darSokakMi;
    }

    @Override
    public String toString() {
        return "Musteri{" +
                "ID='" + id + '\'' +
                ", Ad='" + ad + '\'' +
                ", Konum=" + konum +
                ", DarSokakMi=" + darSokakMi +
                '}';
    }
}
