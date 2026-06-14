// loglayıcı arayüzü uygulanarak loglar konsola yazdırılır
public class KonsolLoglayici implements Loglayici {
    @Override
    public void logYaz(String mesaj) {
        System.out.println("[SIMULASYON LOG] " + mesaj);
    }
}
