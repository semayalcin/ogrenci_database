import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

/**
 * Bu classta alınan öğrenci bilgileri MySql bağlantısı ile oluşturulan veritabanındaki tabloya listelenir
 * kullanıcın isteği üzerine ekleme, silme gibi çeşitli methodlar bulunmaktadır.
 */

/**
 * @author Sema Yalçın
 *
 */
public class Tablolar {
	
	static Connection con;
	static Scanner scr;
	
	/*Bu method MySql de veritabanına bağlantı kurmak içindir.*/
	public void baglanti() {
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ogrenciler_database", "root", "root123");
			
		
			Statement stmt = con.createStatement();	  
			String sql = "CREATE TABLE ogrenci_kisisel_bilgiler " +
	                   "(NO INTEGER not NULL, " +
	                   " AD VARCHAR(255), " + 
	                   " SOYAD VARCHAR(255), " + 
	                   " GMAİL VARCHAR(255), " +
	                   " TEL VARCHAR(255), " + 
	                   " VELİ VARCHAR(255), " + 
	                   " VELİ_TEL VARCHAR(255), " + 
	                   " PRIMARY KEY ( NO ))"; 

	         stmt.executeUpdate(sql);
	         
	         String sorgu = "CREATE TABLE ogrenci_ders_bilgileri " +
	                   "(NO INTEGER not NULL, " +
	                   " SINIF VARCHAR(255), " + 
	                   " AD VARCHAR(255), " + 
	                   " SOYAD VARCHAR(255), " +
	                   " EN_YUKSEK_NOT VARCHAR(255), " +
	                   " PRIMARY KEY ( NO ))"; 

	         stmt.executeUpdate(sorgu);
	  
					
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	
	/*Bu method kullanıcının sistemden çıkış yapmak istemesi üzerine çağrılıp sistemden çıkış yapar.*/
	public void Cikis() {
		
		baglanti();
		
		try {
			
			if(con != null) {
				
				con.close();
				System.out.println("Çıkış yaptınız.\n");
			}
			else {
				System.out.println("Başarısız.\n");
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		System.exit(0);
		
	}

	/*Bu method kullanıcının silinmesini istediği öğrenciyi siler.*/
	public void Sil() {
		
		baglanti();
		
		scr = new Scanner(System.in);
		
		try {
			
			//Listele();
			Statement state = con.createStatement();
			String sorgu = "select ogrenci_ders_bilgileri.*,ogrenci_kisisel_bilgiler.GMAIL, ogrenci_kisisel_bilgiler.TEl, ogrenci_kisisel_bilgiler.VELI, ogrenci_kisisel_bilgiler.VELI_TEL "
					+ "from ogrenci_ders_bilgileri, ogrenci_kisisel_bilgiler where ogrenci_ders_bilgileri.NO = ogrenci_kisisel_bilgiler.NO;";
			ResultSet sonuc = state.executeQuery(sorgu);
			
			while(sonuc.next()) {
				
				System.out.println(sonuc.getString("NO") + "  " + 
								   sonuc.getString("SINIF") + "  " + 
						           sonuc.getString("AD") + "  " + 
								   sonuc.getString("SOYAD") + "  " + 
						           sonuc.getString("EN_YUKSEK_NOT") + "  " +
								   sonuc.getString("GMAIL") + "  " +
						           sonuc.getString("TEL") + "  " +
								   sonuc.getString("VELI") + "  " +
						           sonuc.getString("VELİ_TEL"));
			}
			sonuc.close();
			state.close();
			
			
			System.out.println("\nSilinmesini istediğiniz öğrencinin numarasını giriniz: ");
			String sil = scr.nextLine();
			
			String sql = "Delete from ogrenci_ders_bilgileri Where NO = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, sil);
			int sonuc2 = ps.executeUpdate();
			
			String sql2 = "Delete from ogrenci_kisisel_bilgiler Where NO = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1, sil);
			int sonuc3 = ps2.executeUpdate();
			  
			if(sonuc2 == 1 && sonuc3 == 1) {
				
				System.out.println("Kayıt silindi.\n");
			}
			else {
				
				System.out.println("Bu numaraya sahip bir öğrenci bulunamadı.\n");
			}
			ps.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

	/*Bu method öğrencinin gmail ve telefon numarasının güncellenmesi içindir.*/
	public void Ogrenci_Guncelle() {
		
		baglanti();
		
		scr = new Scanner(System.in);
		
		try {
			
			//Listele();
			Statement state = con.createStatement();
			String sg = "select ogrenci_ders_bilgileri.*, ogrenci_kisisel_bilgiler.GMAIL, ogrenci_kisisel_bilgiler.TEl, ogrenci_kisisel_bilgiler.VELI, ogrenci_kisisel_bilgiler.VELI_TEL "
					+ "from ogrenci_ders_bilgileri, ogrenci_kisisel_bilgiler Where ogrenci_ders_bilgileri.NO = ogrenci_kisisel_bilgiler.NO;";
			ResultSet sonuc = state.executeQuery(sg);
			
			while(sonuc.next()) {
				
				System.out.println(sonuc.getString("NO") + "  " + 
								   sonuc.getString("SINIF") + "  " + 
						           sonuc.getString("AD") + "  " + 
								   sonuc.getString("SOYAD") + "  " + 
						           sonuc.getString("EN_YUKSEK_NOT") + "  " +
								   sonuc.getString("GMAİL") + "  " +
						           sonuc.getString("TEL") + "  " +
								   sonuc.getString("VELI") + "  " +
						           sonuc.getString("VELI_TEL"));
			}
			sonuc.close();
			state.close();
			
			System.out.print("\nGüncellemek istediğiniz öğrencinin numarasını giriniz:");
			String ogrNo = scr.nextLine();
			
			System.out.print("Yeni gmaili giriniz: ");
			String yeniG = scr.nextLine();
			
			System.out.print("Yeni telefon numarasını giriniz: ");
			String yeniT = scr.nextLine();
			
			String sorgu = "update ogrenci_kisisel_bilgiler set GMAIL = ?, TEL = ? where NO = ?";
		    PreparedStatement preparedStmt = con.prepareStatement(sorgu);
		     
		    preparedStmt.setString(1, yeniG);
		    preparedStmt.setString(2, yeniT);
		    preparedStmt.setString(3, ogrNo);

		    int sonuc2 = preparedStmt.executeUpdate();
		    if(sonuc2 == 1) {
		    	System.out.println("Kayıt Güncellendi.\n");
		    }
		    else {
		    	System.out.println("Bu numaraya sahip bir öğrenci bulunamadı.\n");
		    }
		    
		    
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}

	/*Bu method kullanıcıdan bilgileri alıp öğrenciyi listeye ekler.*/
	public void Ogrenci_Ekle() {
		
		baglanti();
		
		try {
			
			Student st = new Student();
	
			st.bilgiler();
			st.dersGirisi();
			st.notlarinSonHali();
			st.basariNotu();
			
			String sorgu = "insert into ogrenci_ders_bilgileri (NO, SINIF, AD, SOYAD, EN_YUKSEK_NOT) "
					+ "values (?,?,?,?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(sorgu);
			
			preparedStmt.setString(1, st.okulNo);
		    preparedStmt.setString(2, st.sinif);
		    preparedStmt.setString(3, st.ad);
		    preparedStmt.setString(4, st.soyad);
		    preparedStmt.setString(5, st.harfNotlari[st.index()]);
		    
		    String sorgu2 = "insert into ogrenci_kisisel_bilgiler (NO, AD, SOYAD, GMAIL, TEL, VELI, VELI_TEL)"
		    		+ " values (?,?,?,?,?,?,?)"; 
			PreparedStatement stmt = con.prepareStatement(sorgu2);
			
		    stmt.setString(1, st.okulNo);
		    stmt.setString(2, st.ad);
		    stmt.setString(3, st.soyad);
		    stmt.setString(4, st.gmail);
		    stmt.setString(5, st.tel);
		    stmt.setString(6, st.veli);
		    stmt.setString(7, st.veliTel);
			
			int sonuc = preparedStmt.executeUpdate();
			int sonuc2 = stmt.executeUpdate();
			
			if(sonuc == 1 && sonuc2 == 1) {
				
				System.out.print("Kayıt eklendi\n");
			}
			else {
				System.out.print("Ekleme yapılamadı.\n");
			}
			
			preparedStmt.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*Bu method kullanıcının bilgilerini görmek istediği öğrencinin bilgilerini listeden alıp System.out'a yazdırır.*/
    public void Ogrenci_Sorgulama() {
    	
    	baglanti();
    	scr = new Scanner(System.in);
		
		try {
			
			System.out.println("Bilgilerini görmek istediğiniz öğrencinin numarasını giriniz: ");
			String num = scr.nextLine();
			
			String sorgu = "Select * from ogrenci_ders_bilgileri Where NO = ?";
			PreparedStatement pss = con.prepareStatement(sorgu);
			pss.setString(1, num);
			
			ResultSet sonuc = pss.executeQuery();
			
			while(sonuc.next()) {
				
				System.out.println("Öğrencinin adı:\t\t\t  " + sonuc.getString("AD"));
				System.out.println("Öğrencinin soyadı:\t\t  " + sonuc.getString("SOYAD"));
				System.out.println("Öğrencinin sınıfı:\t\t  " + sonuc.getString("SINIF"));
				System.out.println("Öğrencinin en başarılı dersi:     " + sonuc.getString("EN_YUKSEK_NOT"));
			}
			
			String sorgu2 = "Select * from ogrenci_kisisel_bilgiler Where NO = ?";
			PreparedStatement pss2 = con.prepareStatement(sorgu2);
			pss2.setString(1, num);
			
			ResultSet sonuc2 = pss2.executeQuery();
			
			while(sonuc2.next()) {
				 
				System.out.println("Öğrencinin gmaili:\t\t  " + sonuc2.getString("GMAIL"));
				System.out.println("Öğrencinin telefon numarası:      " + sonuc2.getString("TEL"));
				System.out.println("Öğrencinin velisi:\t\t  " + sonuc2.getString("VELI"));
				System.out.println("Öğrencinin veli telefon numarası: " + sonuc2.getString("VELI_TEL"));
			}
			
			sonuc.close();
			pss.close();
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
	}
	 
    /*Bu method kullanıcının isteğine bağlı olarak öğrencinin dersler tablosunu, kişisel bilgiler tablosunu ya da 
     * birleşmiş halini listeden alıp System.out'a yazdırır. 
     */ 
	public void Listele() {
		
		baglanti();
		scr = new Scanner(System.in);
		
		try {
			
				System.out.print("Hangi tabloyu listelemek istiyorsunuz?\n"
						+ "1.Öğrencilerin dersler tablosu\n"
						+ "2.Öğrencilerin kişisel bilgiler tablosu\n"
						+ "3.Tabloların birleşmiş hali\n");
				
				int secim = scr.nextInt();
				
				if(secim == 1) {
					
					Statement state = con.createStatement();
					String sorgu = "select * from ogrenci_ders_bilgileri";
					ResultSet sonuc = state.executeQuery(sorgu);
					
					while(sonuc.next()) {
						
						System.out.println(sonuc.getString("NO") + "  " + 
										   sonuc.getString("SINIF") + "  " + 
								           sonuc.getString("AD") + "  " + 
										   sonuc.getString("SOYAD") + "  " + 
								           sonuc.getString("EN_YUKSEK_NOT"));
					}
					sonuc.close();
					state.close();
					
				}
				else if(secim == 2) {
					
					Statement state = con.createStatement();
					String sorgu = "select * from ogrenci_kisisel_bilgiler";
					ResultSet sonuc = state.executeQuery(sorgu);
					
					while(sonuc.next()) {
						
						System.out.println(sonuc.getString("NO") + "  " +  
								           sonuc.getString("AD") + "  " + 
										   sonuc.getString("SOYAD") + "  " +
										   sonuc.getString("GMAİL") + "  " +
								           sonuc.getString("TEL") + "  " +
										   sonuc.getString("VELI") + "  " +
								           sonuc.getString("VELİ_TEL"));
					}
					sonuc.close();
					state.close();
					
				}
				else if(secim == 3) {
					
					Statement state = con.createStatement();
					String sorgu = "select ogrenci_ders_bilgileri.*,ogrenci_kisisel_bilgiler.GMAIL,ogrenci_kisisel_bilgiler.TEL,ogrenci_kisisel_bilgiler.VELI,ogrenci_kisisel_bilgiler.VELI_TEL "
							+ "from ogrenci_ders_bilgileri,ogrenci_kisisel_bilgiler where ogrenci_ders_bilgileri.NO = ogrenci_kisisel_bilgiler.NO";
					ResultSet sonuc = state.executeQuery(sorgu);
					
					while(sonuc.next()) {
						
						System.out.println(sonuc.getString("NO") + "  " + 
										   sonuc.getString("SINIF") + "  " + 
								           sonuc.getString("AD") + "    " + 
										   sonuc.getString("SOYAD") + "    " + 
								           sonuc.getString("EN_YUKSEK_NOT") + "  " +
										   sonuc.getString("GMAİL") + "  " +
								           sonuc.getString("TEL") + "  " +
										   sonuc.getString("VELI") + "  " +
								           sonuc.getString("VELİ_TEL"));
					}
					sonuc.close();
					state.close();
					
				}
				else {
					
					System.out.println("1, 2 veya 3 değerlerinden birini giriniz.");
				}
				
				
			}catch(Exception e) {
			e.printStackTrace();
		}
	}  

	
	

}
