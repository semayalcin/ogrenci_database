import java.util.*;

/**
 * Bu classta öğrenciye ait kişisel bilgiler ve ders bilgileri alınıp ders notlarıyla ilgili hesaplamalar yapılır. 
 */

/**
 * @author Sema Yalcin
 *
 */
public class Student {
 
	String ad;
	String soyad;
	String okulNo; 
	String sinif;
	int dersSayisi;
	String gmail;
	String tel;
	String veli;
	String veliTel;
	public static final int N = 2;
	
	float[][] vizeler;
	float[][] odevler;
	float[][] finaller; 
	float[] sonHal;
	String[] dersler;
	String[] harfNotlari;
	
	Scanner sc = new Scanner(System.in);
	
	public void bilgiler() {
		
		System.out.print("\nNo: ");
		okulNo = sc.nextLine();
		
		System.out.print("Adı: ");
		ad = sc.nextLine();
		ad = ad.toUpperCase();
		
		System.out.print("Soyadı: ");
		soyad = sc.nextLine();
		soyad = soyad.toUpperCase();
		
		System.out.print("Gmail: ");
		gmail = sc.nextLine();
		
		System.out.print("Tel: ");
		tel = sc.nextLine();
		
		System.out.print("Veli: ");
		veli = sc.nextLine();
		veli = veli.toUpperCase();
		
		System.out.print("Veli tel: ");
		veliTel = sc.nextLine();
		
		System.out.print("Sınıf: ");
		sinif = sc.nextLine();
		sinif = sinif.toUpperCase();

		System.out.print("Ders sayısı: ");
		dersSayisi = sc.nextInt();

		
	}
	
	
	/* Bu metotta kullanıcıdan ders sayısı kadar ders girilmesi istenmiştir. 
	 * Ders girildikten sonra da vize, ödev ve final notları yüzdeleriyle birlikte alınıp sonraki derse geçilmiştir.
	 */
	
	public void dersGirisi() {

		sc = new Scanner(System.in);
		dersler = new String[dersSayisi];
		vizeler = new float[dersSayisi][N];
		odevler = new float[dersSayisi][N];
		finaller = new float[dersSayisi][N];
		
		
		int i, j;
		String ders;

		for (i = 0; i < dersSayisi; i++) {

			System.out.print("Dersinizi giriniz: ");
			ders = sc.nextLine();
			ders = ders.toUpperCase();
			dersler[i] = ders;
			
			System.out.print("Önce vize notunuzu sonra yüzdesini giriniz.\n");
			System.out.print(dersler[i] + ": ");
			
			String dizi3[][] = new String[dersSayisi][N];
			
			for (j = 0; j < N; j++) {
				
				dizi3[i][j] = sc.nextLine();
				vizeler[i][j] = Float.parseFloat(dizi3[i][j]);
				
			}
		
			System.out.print("Önce ödev notunuzu sonra yüzdesini giriniz.\n");
			System.out.print(dersler[i] + ": ");
			
			String dizi2[][] = new String[dersSayisi][N];

			for (j = 0; j < N; j++) {
				
				dizi2[i][j] = sc.nextLine();
				odevler[i][j] = Float.parseFloat(dizi2[i][j]);
			}
			
			System.out.print("Önce final notunuzu sonra yüzdesini giriniz.\n");
			System.out.print(dersler[i] + ": ");
			
			String dizi[][] = new String[dersSayisi][N];
			
			for (j = 0; j < N; j++) {
				
				dizi[i][j] = sc.nextLine();
				finaller[i][j] = Float.parseFloat(dizi[i][j]);
			}
			

		} 
		
	}
	
	/* Bu metot derslerin vize, ödev ve final notlarını yüzdelerine göre hesaplayıp sonHal arrayine ekliyor.
	 * Ayrıca yüzdelerin toplamının 100 olması isteniyor.
	 */ 
	public float[] notlarinSonHali() {

		int i;
		float yuzdeToplam = 0;
		sonHal = new float[dersSayisi];
		
			
			for (i = 0; i < dersSayisi; i++) {
				
				yuzdeToplam = vizeler[i][1] + odevler[i][1]+ finaller[i][1];
				
				if(yuzdeToplam != 100) {
					
					System.out.print("Yüzde değerlerin toplamı 100 olmalıdır.\n");
					StudentManagement.main(dersler);
					
				}
				
				else {
					
					sonHal[i] = ((vizeler[i][0] * vizeler[i][1]) / 100) +
								((odevler[i][0] * odevler[i][1]) / 100) +
								((finaller[i][0] * finaller[i][1]) / 100);	
				}
				
			}

		return sonHal;
	}
	
	
	/*Bu metot sonHal arrayindeki ders notlarına göre harf notlarını belirler ve harfnotları arrayine ekler.*/
	public String[] basariNotu() {
		
		int i;
		harfNotlari = new String[dersSayisi];
		
		//System.out.println("Ders ortalamalarınız:");
		 
		for(i = 0 ; i < dersSayisi ; i++) {
		 	
			if(sonHal[i] <= 100 && sonHal[i] >= 90) {
				
				//System.out.println(dersler[i] + ": " + sonHal[i] + " AA Pekiyi");
				harfNotlari[i] = dersler[i] + ": " + sonHal[i] + " AA";
			}
			
			else if(sonHal[i] < 90 && sonHal[i] >= 85) {
				
				//System.out.println(dersler[i] + ": " + sonHal[i] + " BA İyi-Pekiyi");
				harfNotlari[i] = dersler[i] + ": " + sonHal[i] + " BA";
			}
			 
			else if(sonHal[i] < 85 && sonHal[i] >= 80) {
				
				//System.out.println(dersler[i] + ": " + sonHal[i] + " BB İyi");
				harfNotlari[i] = dersler[i] + ": " + sonHal[i] + " BB";
			}
			
			else if(sonHal[i] < 80 && sonHal[i] >= 70) {
				
				//System.out.println(dersler[i] + ": " + sonHal[i] + " CB Orta-Iyi");
				harfNotlari[i] = dersler[i] + ": " + sonHal[i] + " CB";
			}
			
			else if(sonHal[i] < 70 && sonHal[i] >= 60) {
				
				//System.out.println(dersler[i] + ": " + sonHal[i] + " CC Orta");
				harfNotlari[i] = dersler[i] + ": " + sonHal[i] + " CC";
			}
			
			else if(sonHal[i] < 60 && sonHal[i] >= 55) {
				
				//System.out.println(dersler[i] + ": " + sonHal[i] + " DC Geçer-Orta");
				harfNotlari[i] = dersler[i] + ": " + sonHal[i] + " DC";
			}
			
			else if(sonHal[i] < 55 && sonHal[i] >= 50) {
				
				//System.out.println(dersler[i] + ": " + sonHal[i] + " DD Geçer");
				harfNotlari[i] = dersler[i] + ": " + sonHal[i] + " DD";
			}
			
			else if(sonHal[i] < 50 && sonHal[i] >= 0) {
				
				//System.out.println(dersler[i] + ": " + sonHal[i] + " FF Zayıf");
				harfNotlari[i] = dersler[i] + ": " + sonHal[i] + " FF";
			}
			
		} 
		return harfNotlari;
	}
	
	
	/*Bu method sonHal dizisindeki en büyük notu bulup o notun indexini return eder.*/
	public int index() {
		float enbuyuk = 0;
		int index = -1;
        
        for(int i = 0; i < dersSayisi; i++)
        {
            if(sonHal[i] > enbuyuk) {
                enbuyuk = sonHal[i];
            }
        }
		
        for(int i = 0; i  < dersSayisi; i++){
        	 
            if(sonHal[i] == enbuyuk){
                index=i;
                break;
            }
        }
		
		return index;
	}
} 
