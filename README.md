# Akıllı Şehir Lojistik ve Dağıtım Simülasyonu

Bu proje, Java programlama dili kullanılarak Nesneye Yönelik Programlama (NYP) prensiplerine (Kalıtım, Polimorfizm, Soyutlama, Kapsülleme) uygun olarak geliştirilmiş bir kargo lojistik dağıtım simülasyonudur.

## Proje Özellikleri
- **Araç Tipleri ve Kısıtlar:** Kamyon (dar sokağa giremez), Motosiklet (10 kg üzeri paketlerde hızı %20 düşer) ve Drone (5 kg ve üzeri paket taşıyamaz).
- **Hata Yönetimi:** İş kuralları `DarSokakException`, `AgirPaketException` ve `YakitYetersizException` gibi özel hata sınıflarıyla yönetilir.
- **Loglama Mekanizması:** Çok biçimlilik (Polymorphism) kullanılarak tasarlanmış, hem konsola hem de dosyaya (`simulasyon_log.txt`) yazan loglama altyapısı mevcuttur.
- **Kuyruk Yapısı:** Paketler sisteme giriş sırasına göre (FIFO) kuyrukta tutulur ve sırayla dağıtılır.

## Projeyi Çalıştırma

Projeyi terminal üzerinden derlemek ve çalıştırmak için proje ana dizininde sırasıyla aşağıdaki komutları uygulayabilirsiniz:

```bash
# Sınıfları derleme
javac -d bin src/*.java

# Simülasyonu çalıştırma
java -cp bin Main
```
