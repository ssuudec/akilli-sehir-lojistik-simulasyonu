import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// loglayıcı arayüzü uygulanarak loglar dosyaya yazdırılır
public class DosyaLoglayici implements Loglayici {
    private String dosyaYolu;
    private DateTimeFormatter zamanFormatlayici;

    public DosyaLoglayici(String dosyaYolu) {
        this.dosyaYolu = dosyaYolu;
        this.zamanFormatlayici = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public void logYaz(String mesaj) {
        String tarih = LocalDateTime.now().format(zamanFormatlayici);
        String formatliMesaj = "[" + tarih + "] [LOG] " + mesaj;

        try (FileWriter fw = new FileWriter(dosyaYolu, true);
             PrintWriter pw = new PrintWriter(fw)) {
            pw.println(formatliMesaj);
        } catch (IOException e) {
            System.err.println("DosyaLoglayici: Dosyaya yazilirken hata olustu! " + e.getMessage());
        }
    }
}
