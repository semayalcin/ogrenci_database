import java.util.*;

/**
 * Bu bir öğrenci yönetim sistemidir. Bu classta Tablolar classı çağrılıp tablo adında bir nesne oluşturulur. 
 * Bu da kullanıcının isteği üzerine Tablolar classındaki methodları çağırabilmemizi sağlar.
 */
/**
* @author Sema Yalcin
*
*/
 
public class StudentManagement { 
	
	static Scanner scr = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		Tablolar tablo = new Tablolar();
		 
		int sec;
		
		while(true) {
			
			System.out.println("\nDevam etmek için işlemlerden birini seçiniz.");
			System.out.println("1.Listele");
			System.out.println("2.Öğrenci ekle");
			System.out.println("3.Öğrenciyi güncelle");
			System.out.println("4.Öğrenci sorgula");
			System.out.println("5.Sil");
			System.out.println("6.Çıkış");
			System.out.println("Seçimin:");
			sec = scr.nextInt();
			
			if(sec == 1) {
				tablo.Listele();
			}
			else if(sec == 2) {
				tablo.Ogrenci_Ekle();
			}
			else if(sec == 3) {
				tablo.Ogrenci_Guncelle();
			}
			else if(sec == 4) {
				tablo.Ogrenci_Sorgulama();
			}
			else if(sec == 5) {
				tablo.Sil();
			}
			else if(sec == 6) {
				tablo.Cikis();
			}
			else {
				System.out.println("Geçersiz giriş");
			}
		}
	}
	
	
	
	
}
