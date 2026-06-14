public class Paket {
    public enum PaketDurumu {
        HAZIRLANIYOR,
        YOLDA,
        TESLIM_EDILDI
    }

    private String id;
    private double agirlik;
    private String icerik;
    private PaketDurumu durum;
    private Musteri alici;

    public Paket(String id, double agirlik, String icerik, Musteri alici) {
        this.id = id;
        this.agirlik = agirlik;
        this.icerik = icerik;
        this.alici = alici;
        this.durum = PaketDurumu.HAZIRLANIYOR;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAgirlik() {
        return agirlik;
    }

    public void setAgirlik(double agirlik) {
        this.agirlik = agirlik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public PaketDurumu getDurum() {
        return durum;
    }

    public void setDurum(PaketDurumu durum) {
        this.durum = durum;
    }

    public Musteri getAlici() {
        return alici;
    }

    public void setAlici(Musteri alici) {
        this.alici = alici;
    }

    @Override
    public String toString() {
        return "Paket{" +
                "ID='" + id + '\'' +
                ", Agirlik=" + agirlik +
                " kg, Icerik='" + icerik + '\'' +
                ", Durum=" + durum +
                ", Alici=" + (alici != null ? alici.getAd() : "Bilinmiyor") +
                '}';
    }
}
