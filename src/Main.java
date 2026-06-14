import java.io.File;

// test verileri oluşturulup lojistik simülasyonu buradan başlatılır
public class Main {
    public static void main(String[] args) {
        String logDosyaYolu = "simulasyon_log.txt";
        File logDosyasi = new File(logDosyaYolu);
        if (logDosyasi.exists()) {
            logDosyasi.delete();
        }

        LojistikSistemi sistem = new LojistikSistemi();

        sistem.loglayiciEkle(new KonsolLoglayici());
        sistem.loglayiciEkle(new DosyaLoglayici(logDosyaYolu));

        Konum merkezDepo = new Konum(0.0, 0.0);
        Konum konumAhmet = new Konum(30.0, 0.0);
        Konum konumCanan = new Konum(60.0, 0.0);
        Konum konumMehmet = new Konum(90.0, 0.0);
        Konum konumElif = new Konum(10.0, 10.0);
        Konum konumCan = new Konum(5.0, 5.0);
        Konum konumBuse = new Konum(2.0, 2.0);

        Musteri m1 = new Musteri("Ahmet Yilmaz", "M01", konumAhmet, false);
        Musteri m2 = new Musteri("Canan Ozturk", "M02", konumCanan, false);
        Musteri m3 = new Musteri("Mehmet Kaya", "M03", konumMehmet, false);
        Musteri m4 = new Musteri("Elif Demir (Dar Sokak)", "M04", konumElif, true);
        Musteri m5 = new Musteri("Can Arslan", "M05", konumCan, false);
        Musteri m6 = new Musteri("Buse Yildiz", "M06", konumBuse, false);

        sistem.musteriEkle(m1);
        sistem.musteriEkle(m2);
        sistem.musteriEkle(m3);
        sistem.musteriEkle(m4);
        sistem.musteriEkle(m5);
        sistem.musteriEkle(m6);

        Arac drone = new Drone("34DRN01", 8.0, 20.0, 100.0, merkezDepo);
        Arac motor = new Motor("34MTR02", 30.0, 50.0, 80.0, merkezDepo);
        Arac kamyon = new Kamyon("34KMY03", 500.0, 150.0, 50.0, merkezDepo);

        sistem.aracEkle(drone);
        sistem.aracEkle(kamyon);
        sistem.aracEkle(motor);

        Paket p1 = new Paket("P01", 50.0, "Kitap Kolisi", m1);
        Paket p2 = new Paket("P02", 60.0, "Televizyon", m2);
        Paket p3 = new Paket("P03", 80.0, "Buzdolabi", m3);
        Paket p4 = new Paket("P04", 15.0, "Elektrikli Supurge", m4);
        Paket p5 = new Paket("P05", 6.0, "Kahve Makinasi", m5);
        Paket p6 = new Paket("P06", 2.0, "Kulaklik", m6);

        sistem.paketKuyrugunaEkle(p1);
        sistem.paketKuyrugunaEkle(p2);
        sistem.paketKuyrugunaEkle(p3);
        sistem.paketKuyrugunaEkle(p4);
        sistem.paketKuyrugunaEkle(p5);
        sistem.paketKuyrugunaEkle(p6);

        sistem.dagitimaBasla();
    }
}

class AgirPaketException extends Exception {
    public AgirPaketException(String mesaj) {
        super(mesaj);
    }
}

class DarSokakException extends Exception {
    public DarSokakException(String mesaj) {
        super(mesaj);
    }
}

class YakitYetersizException extends Exception {
    public YakitYetersizException(String mesaj) {
        super(mesaj);
    }
}
