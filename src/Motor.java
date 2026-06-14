// araç sınıfından türetilerek motora özel teslimat kuralları yazılır
public class Motor extends Arac {

    public Motor(String plaka, double kapasite, double maksimumYakit, double hiz, Konum guncelKonum) {
        super(plaka, kapasite, maksimumYakit, hiz, guncelKonum);
    }

    @Override
    public double getYakitTuketimOrani() {
        return 0.5;
    }

    @Override
    public void teslimatYap(Paket paket) throws YakitYetersizException {
        Musteri alici = paket.getAlici();
        double mesafe = getGuncelKonum().mesafeHesapla(alici.getKonum());
        double gerekenYakit = mesafe * getYakitTuketimOrani();

        // gereken yakıt mevcut yakıttan fazlaysa teslimat yapılamayıp hata fırlatılır
        if (getYakit() < gerekenYakit) {
            throw new YakitYetersizException("YakitYetersizException: Motor (" + getPlaka() + ") yakiti yetersiz! Gereken: " + 
                String.format("%.2f", gerekenYakit) + ", Mevcut: " + String.format("%.2f", getYakit()));
        }

        // paket 10 kg'dan ağırsa motorun hızı yüzde 20 düşürülür
        double etkiliHiz = getHiz();
        boolean hizAzaldi = false;
        if (paket.getAgirlik() > 10.0) {
            etkiliHiz = getHiz() * 0.8;
            hizAzaldi = true;
        }

        double sure = mesafe / etkiliHiz;

        setYakit(getYakit() - gerekenYakit);
        setGuncelKonum(alici.getKonum());
        paket.setDurum(Paket.PaketDurumu.TESLIM_EDILDI);

        System.out.println("[BILGI] Motor (" + getPlaka() + ") teslimati " + String.format("%.2f", sure) + " saatte tamamladi.");
        if (hizAzaldi) {
            System.out.println("[BILGI] Motor (" + getPlaka() + ") agir paket ( > 10kg ) tasidigi icin hizi %20 dusuruldu. Guncel Hiz: " + etkiliHiz + " km/s");
        }
    }
}
