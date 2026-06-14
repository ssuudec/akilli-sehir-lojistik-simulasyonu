// araç sınıfından türetilerek drone'a özel teslimat kuralları yazılır
public class Drone extends Arac {

    public Drone(String plaka, double kapasite, double maksimumYakit, double hiz, Konum guncelKonum) {
        super(plaka, kapasite, maksimumYakit, hiz, guncelKonum);
    }

    @Override
    public double getYakitTuketimOrani() {
        return 0.2;
    }

    @Override
    public void teslimatYap(Paket paket) throws AgirPaketException, YakitYetersizException {
        // drone 5 kg ve üzeri paket taşıyamadığı için burada hata fırlatılır
        if (paket.getAgirlik() >= 5.0) {
            throw new AgirPaketException("AgirPaketException: Drone (" + getPlaka() + ") 5 kg ve uzeri paket tasiyamaz! Paket Agirligi: " + paket.getAgirlik() + " kg");
        }

        Musteri alici = paket.getAlici();
        double mesafe = getGuncelKonum().mesafeHesapla(alici.getKonum());
        double gerekenYakit = mesafe * getYakitTuketimOrani();

        // gereken yakıt mevcut yakıttan fazlaysa teslimat yapılamayıp hata fırlatılır
        if (getYakit() < gerekenYakit) {
            throw new YakitYetersizException("YakitYetersizException: Drone (" + getPlaka() + ") yakiti yetersiz! Gereken: " + 
                String.format("%.2f", gerekenYakit) + ", Mevcut: " + String.format("%.2f", getYakit()));
        }

        setYakit(getYakit() - gerekenYakit);
        setGuncelKonum(alici.getKonum());
        paket.setDurum(Paket.PaketDurumu.TESLIM_EDILDI);
    }
}
