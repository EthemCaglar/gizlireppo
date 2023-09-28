import java.util.Arrays;
import java.util.Scanner;
public class MayinTarlasinda {
    public static void main(String[] args) {
        minesweeper();//mayın ve sayıları yazıp oynat methodunu çağırır
    }
// direkt f3le arama yapabilmek 11 soruyu direkt değerlendirme formu diye isimlendirip madde madde sayılar verdim
// oyun burada çalışacak-------------- değerlendirme formu 1 ------------------------------------------------------
    public static void minesweeper(){
        int[] secimler = secim(); // kullanıcıya seçim yaptırıp
        int mayin = (secimler[0]*secimler[1])/4;
        String[][] icHazir = oyunMatrisi(secimler[0],secimler[1]);
        String[][] display = new String [icHazir.length][icHazir[0].length];
        int galibiyetKontrol = 0;

        for(int i=0 ; i < display.length ; i++){ // açılan display değişkeninin tüm segmentlerine - kondu
            for(int j=0 ; j < display[0].length ; j++){
                display[i][j] = "-";
            }
        }
        // değerlendirme formu 7
        while(true){ // oyunun merkezi - giris alır displayi değiştirir - kazandı kaybetti kontrolü yapar.
            yazdir(icHazir);
            System.out.println("------------------------------------------------------------");
            yazdir(display);
            int[] giris = giris(display);
            // değerlendirme formu 8
            display[giris[0]][giris[1]] = icHazir[giris[0]][giris[1]];
            if(icHazir[giris[0]][giris[1]] == "*"){ // kaybetme ifi - değerlendirme formu 9
                yazdir(icHazir);
                System.out.println("------------------------------------------------------------");
                yazdir(display);
                System.out.println("Mayına Bastın Kaybettin"); // değerlendirme formu 11
                break;
            }else{
                galibiyetKontrol=0;
                // değerlendirme formu 10
                for(int i=0 ; i < display.length ; i++){ // galibiyet kontrol ifi
                    for(int j=0 ; j < display[0].length ; j++){
                        if(display[i][j] == "-")
                        galibiyetKontrol++;
                    }
                }
                if(galibiyetKontrol == mayin){ // kazanma ifi
                    yazdir(icHazir);
                    System.out.println("------------------------------------------------------------");
                    yazdir(display);
                    System.out.println("-----!!!!! KAZANDINIZ !!!!!-----"); // değerlendirme formu 11
                    break;
                }
            }
        }
    }
// Satır sutun ve mayın sayısını alır ---------------------------------------------------------------
// değerlendirme formu 2
    public static int[] secim(){
        Scanner input = new Scanner(System.in);

        System.out.print("Tarlanızın Satır Sayısı: ");
        int satir = input.nextInt();

        System.out.print("Tarlanızın Sütun Sayısı: ");
        int sutun = input.nextInt();
        // değerlendirme formu 3
        int[] matris = {satir,sutun};
        return matris;   
    }
// sıfır hariç rastgele sayı üretir ---------------------------------------------------------------------------
// değerlendirme formu 2
    public static int sayiUret(int maks){
        // değerlendirme formu 4
        int randomized = (int)(Math.random() * maks);
        while(randomized == 0){
            randomized = (int)(Math.random() * maks);
            if(randomized != 0){
                break;
            }
        }
        return randomized;
    }
// sayiUret ile birlikte çalışır, birbirinden farklı rastgele sayıları diziye aktarır -------------------------
// değerlendirme formu 2
    public static int[] sayiDiz(int mayin, int maks){
        int[] mayinDizimi = new int [mayin];
        for(int i = 0 ; i < mayin ; i++){ // rastgele sayı üretme ve yerleştirme
            mayinDizimi[i] = sayiUret(maks);
        }
        int kontrol = 1; // girişte while = true olsun diye 0dan farklı olmalı
        while(kontrol != 0){ // kontrol whileı
            kontrol = 0; // kontrol değişkeni tekrar eden sayı olup olmadığını kontrol ediyor
            Arrays.sort(mayinDizimi); // eşiti arama için kolaylık amacıyla sıralıyoruz
            for(int i = 0 ; i < mayin ; i++){ 
                if((i+1) < mayin){ // son terimi hariç tutmak için, yoksa borderı geçiyor
                    if (mayinDizimi[i] == mayinDizimi[i+1]){
                        mayinDizimi[i+1] = sayiUret(mayin);
                        kontrol++;
                    }
                }
            }
        }
        return mayinDizimi;
    }
// mayindizimi dizisindeki rastgele sayıyı matris koordinatına çevirir-------------------------------------------
// değerlendirme formu 2
    public static int[] koordinat(int sutun, int konum){
        int satirVeri = konum/sutun;// soldan sağa dizildiğinde sütün kaçsa o kadar satıra eşit gelir
        int sutunVeri = (konum % sutun);
        if(sutunVeri == 0){// kalan sıfırsa aslında sutun kadar olacak
            sutunVeri = sutun; // örnek üzerinden anlatırsak 18/6=3 18%6=0 olduğunda 0'ı 6 ya 3'den de 1 çıkarıyoruz.
            satirVeri--;  // 3-0 yerine 2-6 verisi gibi düşünülebilir
        }
        sutunVeri--;// sutunlar 0dan başladığı için 1 çıkarmamız gerekiyor
        int[] koordinat = {satirVeri,sutunVeri};
        return koordinat;
    }
// inside matrisinde mayınların ve mayın çevresindeki sayıların oluşturulması ---------------------------------
// değerlendirme formu 2
    public static String[][] oyunMatrisi(int satir,int sutun){
        int[][] inside = new int [satir][sutun];
        int maks = satir*sutun;
        int mayin = maks/4;
        int [] mayinDizimi = sayiDiz(mayin, maks); // mayın sayısı kadar rastgele sayımızı istiyoruz

        for(int i=0 ; i < satir ; i++){ // tüm insideın içini 0
            for(int j=0 ; j < sutun ; j++){
                inside[i][j] = 0;
            }
        }

        for(int i = 0 ; i < mayinDizimi.length ; i++){ // random sayıları çeviriyoruz.
            int krdSatir = koordinat(sutun, mayinDizimi[i])[0]; // [-1][-1]  [-1][ 0] [-1][+1]
            int krdSutun = koordinat(sutun, mayinDizimi[i])[1]; // [ 0][-1]           [ 0][+1]
            // sınır kontrolü                                     [+1][-1]  [+1][ 0] [+1][+1]
            if(0 <= krdSatir-1 && 0 <= krdSutun-1){ // [-1][-1]
                inside[krdSatir-1][krdSutun-1]++;}

            if(0 <= krdSatir-1){
                inside[krdSatir-1][krdSutun]++;} // [-1][ 0]

            if(0 <= krdSatir-1 && krdSutun+1 < sutun){
                inside[krdSatir-1][krdSutun+1]++;} // [-1][+1]

            if(0 <= krdSutun-1){
                inside[krdSatir][krdSutun-1]++;} // [ 0][-1]
            
            if(krdSutun+1 < sutun){
                inside[krdSatir][krdSutun+1]++;} // [ 0][+1]
            
            if(krdSatir+1 < satir && 0 <= krdSutun-1){
                inside[krdSatir+1][krdSutun-1]++;} // [+1][-1]

            if(krdSatir+1 < satir){
                inside[krdSatir+1][krdSutun]++;} // [+1][ 0]

            if(krdSatir+1 < satir && krdSutun+1 < sutun){
                inside[krdSatir+1][krdSutun+1]++;} // [+1][+1]
        }
        String[][] mayinHazir = matrisStringe(inside, mayinDizimi);
        return mayinHazir;
    }
// mayınlarına göre sayıları verdiğimiz matrisi stringe çevirip mayını olan yerlere yıldız koyuyor--------------
// değerlendirme formu 2
    public static String[][] matrisStringe(int[][] inside, int[] mayinDizimi){
        int satir = inside.length;
        int sutun = inside[0].length;
        String[][] om = new String [satir][sutun];

        for(int i=0 ; i < inside.length ; i++){  // stringe çevirme foru
            for(int j=0 ; j < sutun ; j++){
                om[i][j] = Integer.toString(inside[i][j]);
            }
        }
        
        for(int i = 0 ; i < mayinDizimi.length ; i++){ // mayın yerleştirme ifi
            om[koordinat(sutun, mayinDizimi[i])[0]][koordinat(sutun, mayinDizimi[i])[1]] = "*";
        }
        return om;
    }
// çift eskenli matris yazdırmak için -------------------------------------------------------------------------
// değerlendirme formu 2
    public static void yazdir(String[][] yazdir){
        for(int i=0 ; i < yazdir.length ; i++){
            for(int j=0 ; j < yazdir[0].length ; j++){
                System.out.print(yazdir[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
// oyunu oynamak için açılacak satır ve sutunu istemek için ------------------------------------------------------
// değerlendirme formu 5
// değerlendirme formu 2
    public static int[] giris(String[][] display){
        Scanner input = new Scanner(System.in);
        int satirGiris;
        int sutunGiris;
        while(true){
            while(true){ // satır sayısı sınırın dışında olursa tekrar alıyor
                System.out.print("Seçtiğiniz satır: ");
                satirGiris = input.nextInt();
                // değerlendirme formu 6
                if(0 <= satirGiris && satirGiris < display.length ){
                    break;
                }else{
                    System.out.print("Hatalı Giriş!!!\n");
                }
            }

            while(true){ // sutun sayısı sınırın dışında olursa tekrar alıyor
                System.out.print("Seçtiğiniz sutun: ");
                sutunGiris = input.nextInt();
                // değerlendirme formu 6
                if(0 <= sutunGiris && sutunGiris < display[0].length ){
                    break;
                }else{
                    System.out.print("Hatalı Giriş !!!\n");
                }
            }
            // değerlendirme formu 6
            if(display[satirGiris][sutunGiris] == "-"){
                break;
            }else{
                System.out.print("Zaten Açtınız !!!\n");
            }
        }
        int[] giris = {satirGiris,sutunGiris};
        return giris;
    }
}
