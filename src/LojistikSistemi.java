import java.util.ArrayList;
import java.util.List;

// lojistik yönetimi, araç/müşteri ekleme ve dağıtım işlemleri burada gerçekleştirilir
public class LojistikSistemi {
    private List<Musteri> musteriler;
    private List<Arac> araclar;
    private List<Paket> paketKuyrugu;
    private List<Loglayici> loglayicilar;

    public LojistikSistemi() {
        this.musteriler = new ArrayList<>();
        this.araclar = new ArrayList<>();
        this.paketKuyrugu = new ArrayList<>();
        this.loglayicilar = new ArrayList<>();
    }

    public void musteriEkle(Musteri m) {
        musteriler.add(m);
        logla("Sisteme yeni musteri eklendi: " + m.getAd() + " (ID: " + m.getId() + ")");
    }

    public void aracEkle(Arac a) {
        araclar.add(a);
        logla("Sisteme yeni arac eklendi: " + a.getPlaka() + " (" + a.getClass().getSimpleName() + ")");
    }

    public void loglayiciEkle(Loglayici l) {
        loglayicilar.add(l);
    }

    public void paketKuyrugunaEkle(Paket p) {
        paketKuyrugu.add(p);
        logla("Paket kuyruga eklendi -> Paket ID: " + p.getId() + ", Agirlik: " + p.getAgirlik() + " kg, Alici: " + p.getAlici().getAd());
    }

    private void logla(String mesaj) {
        for (Loglayici loglayici : loglayicilar) {
            loglayici.logYaz(mesaj);
        }
    }

    public void dagitimaBasla() {
        logla("\n=================================================================================");
        logla("                  LOJISTIK DAGITIM SIMULASYONU BASLIYOR                          ");
        logla("=================================================================================");
        logla("Toplam Paket Sayisi: " + paketKuyrugu.size());
        logla("Toplam Arac Sayisi: " + araclar.size());

        while (!paketKuyrugu.isEmpty()) {
            // kuyruktaki paketler fifo mantığına göre sırayla çekilip işleme alınır
            Paket paket = paketKuyrugu.remove(0);
            logla("\n---------------------------------------------------------------------------------");
            logla("Isleme Alinan Paket: " + paket.getId() + " | Icerik: " + paket.getIcerik() + " | Agirlik: " + paket.getAgirlik() + " kg");
            paket.setDurum(Paket.PaketDurumu.YOLDA);

            boolean teslimEdildi = false;
            List<Arac> denenenAraclar = new ArrayList<>();

            while (!teslimEdildi) {
                Arac secilenArac = uygunAracSec(paket, denenenAraclar);

                if (secilenArac == null) {
                    logla("[KRITIK HATA] Paket " + paket.getId() + " icin uygun tasima kapasitesine sahip bir arac bulunamadi! Teslimat IPTAL edildi.");
                    paket.setDurum(Paket.PaketDurumu.HAZIRLANIYOR);
                    break;
                }

                denenenAraclar.add(secilenArac);
                logla("Teslimat Denemesi -> Arac: " + secilenArac.getPlaka() + " (" + secilenArac.getClass().getSimpleName() + ")");

                try {
                    secilenArac.teslimatYap(paket);
                    teslimEdildi = true;
                    logla("[BASARILI] Paket " + paket.getId() + ", " + secilenArac.getPlaka() + " plakali " + 
                           secilenArac.getClass().getSimpleName() + " ile basariyla teslim edildi!");
                    logla("Arac Guncel Konumu: " + secilenArac.getGuncelKonum() + " | Kalan Yakit: " + String.format("%.2f", secilenArac.getYakit()));
                } catch (DarSokakException e) {
                    logla("[KISIT ENGELI] " + e.getMessage());
                    logla("Alternatif arac aranmaya devam ediyor...");
                } catch (AgirPaketException e) {
                    logla("[KISIT ENGELI] " + e.getMessage());
                    logla("Alternatif arac aranmaya devam ediyor...");
                } catch (YakitYetersizException e) {
                    logla("[YAKIT ENGELI] " + e.getMessage());
                    logla("Arac icin YakitIkmal() cagriliyor...");
                    
                    // teslimat sırasında yakıt yetersiz kalırsa araca ikmal yapılıp tekrar denenir
                    secilenArac.yakitIkmal();
                    logla("[IKMAL] " + secilenArac.getPlaka() + " plakali aracin deposu dolduruldu. Teslimat yeniden deneniyor...");
                    
                    try {
                        secilenArac.teslimatYap(paket);
                        teslimEdildi = true;
                        logla("[BASARILI - IKMAL SONRASI] Paket " + paket.getId() + ", " + secilenArac.getPlaka() + " plakali " + 
                               secilenArac.getClass().getSimpleName() + " ile basariyla teslim edildi!");
                        logla("Arac Guncel Konumu: " + secilenArac.getGuncelKonum() + " | Kalan Yakit: " + String.format("%.2f", secilenArac.getYakit()));
                    } catch (Exception ex) {
                        logla("[KRITIK HATA - IKMAL SONRASI] Yakit doldurulmasina ragmen beklenmeyen hata: " + ex.getMessage());
                    }
                } catch (Exception e) {
                    logla("[BEKLENMEDIK HATA] Teslimat sirasinda hata olustu: " + e.getMessage());
                }
            }
        }
        logla("\n=================================================================================");
        logla("                  LOJISTIK DAGITIM SIMULASYONU TAMAMLANDI                        ");
        logla("=================================================================================");
    }

    private Arac uygunAracSec(Paket paket, List<Arac> denenenAraclar) {
        for (Arac arac : araclar) {
            if (denenenAraclar.contains(arac)) {
                continue;
            }

            if (paket.getAgirlik() > arac.getKapasite()) {
                continue;
            }

            return arac;
        }
        return null;
    }
}
