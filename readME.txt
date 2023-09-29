-----------------------------*******MAYIN TARLASINDA*******-----------------------------

Methodların Görevleri ve Değişkenleri

- minesweeper : Araç methodlarını çağırarak oyunun oynanabilmesini sağlar, kazanma kaybetme kontrolü yapar. Oyun ekranını oluşturur. secim, oyunMatrisi, giris, yazdır methodlarını çağırır.
	
	secimler [Dizi](int) : secim methodundan gelen oyunun oluşturulacağı satır sutun boyutu ve mayın sayısını taşır.
	mayin (int) : mayın sayısı 
	içHazir [İkiBoyutluDizi](String) : secimlerden aldığı verilerle  oluşturulan mayınları ve mayın çevresindeki sayıları kurulu matrisi taşır.
	display [İkiBoyutluDizi](String) : oyuncu için ekrana verilecek matrisi taşır.
	galibiyet kontrol (int): 0 olarak başlar displaydeki her "-" karakteri için bir artar bu sayı mayın sayısına eşitse "kazanma ifi"ni tetikler.
	giris [Dizi] : Oyuncunun açmak istediği satır sutun bilgisini taşır.

- secim : minesweeper başında tetiklenir, tarlayı kurmak için satır sutun ve mayın bilgisini alır, mayın sayısının sıfırdan büyük maksimum alan sayısından en fazla 1 küçük olmasını kontrol eder.

	satir (int) : tarlanın kurulumu için satır verisini taşır.
	sutun (int) : tarlanın kurulumu için sutun verisini taşır.
	matris [Dizi](int): satir sutun ve mayin verisini taşır.

- sayiUret : alan sayısı ve altında sıfırdan büyük rastgele sayılar üretir.
	
	randomized (int) : üretilen sayı verisini taşır.

- sayiDiz : mayın sayısı kadar rastgele sayı üretir, eşitliği kontrol eder eşitse yeni sayılar üretir. Her sayı farklı olana kadar çalışır. sayiUret methoduyla çalışır.

	mayinDizimi [Dizi](int) : mayın sayısı kadar elemanlı bir dizidir, üretilen sayıları taşır. Çıkış bu değişkenle yapılır.
	kontrol (int) : 1 olarak başlar, dizi içerisinde eşit sayı kadar artar. "kontrol whileı" her başladığında sıfırlanır.

- koordinat : sayıları dizmek için soldan sağa sıraladığımızda son sıranın sutun sayısı katı olduğu görülüyor. Bunu kullanarak istenen konumun sütüna bölümü satırdaki, bölümden kalanı sutundaki konumu almamızı sağlar.

	satirVeri (int) : verilen konum sayısının matris koordinatı değerini taşır
	sutunVeri (int) : verilen konum sayısının matris koordinatı değerini taşır
	koordinat [Dizi](int) : verinin çıkışı için oluşturuluyor.

- oyunMatrisi : oyunun oynanacağın matrisi kuran method, mayın çevresindeki sayıların artması, mayınların konumlarına yerleştirilmesi özetle oynamaya hazır hale gelmesini sağlar. matrisStringe ve sayıDiz methodunu çağırır.

	inside [İkiBoyutluDizi](int) : mayınların konumları üzerinden sayıların bulunacağı dizi
	mayinDizimi [Dizi](int) : mayın dizmek için üretilmiş rastgele sayıları çağırır.
	maks (int) : satir ve sutunun çarpımı en büyük alanın sayı değerine eşit.
	mayin (int) : mayın adetini taşır.
	krdSatir (int) : koordinat methodu rastgele sayıyı koordinata çeviriyor, methodun çıkış verisi dizi koordinat(veri1, veri2[i])[0] satır verisini verir.
	krdSutun (int) : koordinat methodu rastgele sayıyı koordinata çeviriyor, methodun çıkış verisi dizi koordinat(veri1, veri2[i])[1] sutun verisini verir.
	mayinHazir [İkiBoyutluDizi](String) : içi tamamlanan inside dizisinin bombaları yerleştirilmiş ve stringe çevirilmiş halidir. matrisStringe methodu sonucudur.

- matrisStringe : sayıları yerleştirilmiş matrisi stringe çevirir ve mayınlarını dizer.
	
	satir (int) : satır sayısı
	sutun (int) : sutun sayısı
	om  [İkiBoyutluDizi](String) : çıkış verilecek dizi.

- yazdir : iki boyutlu dizileri yazdırır.

- giris : oyun alanında bölge açmak için satır sutun verisi alan, hem sınırlar içinde girdiğinizi hem de önceden girip girmediğinizi konrol eder.

	satirGiris : Oyun için satır girişi taşır.
	sutunGiris : Oyun için satır girişi taşır.
	giris [Dizi](int) : Çıkış için satır sutun verisini taşır.
