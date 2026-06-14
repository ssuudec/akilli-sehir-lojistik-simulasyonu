// araç sınıfından kalıtım alınarak kamyona özel teslimat kuralları yazılır
public class Kamyon extends Arac {

    public Kamyon(String plaka, double kapasite, double maksimumYakit, double hiz, Konum guncelKonum) {
        super(plaka, kapasite, maksimumYakit, hiz, guncelKonum);
    }

    @Override
    public double getYakitTuketimOrani() {
        return 2.0;
    }

    @Override
    public void teslimatYap(Paket paket) throws DarSokakException, YakitYetersizException {
        Musteri alici = paket.getAlici();
        
        // kamyon dar sokaklara giremediği için burada hata fırlatılır
        if (alici.isDarSokakMi()) {
            throw new DarSokakException("DarSokakException: Kamyon (" + getPlaka() + "), dar sokakta oturan " + alici.getAd() + " adli musteriye teslimat yapamaz!");
        }

        double mesafe = getGuncelKonum().mesafeHesapla(alici.getKonum());
        double gerekenYakit = mesafe * getYakitTuketimOrani();

        // gereken yakıt mevcut yakıttan fazlaysa teslimat yapılamayıp hata fırlatılır
        if (getYakit() < gerekenYakit) {
            throw new YakitYetersizException("YakitYetersizException: Kamyon (" + getPlaka() + ") yakiti yetersiz! Gereken: " + 
                String.format("%.2f", gerekenYakit) + ", Mevcut: " + String.format("%.2f", getYakit()));
        }

        setYakit(getYakit() - gerekenYakit);
        setGuncelKonum(alici.getKonum());
        paket.setDurum(Paket.PaketDurumu.TESLIM_EDILDI);
    }
}
