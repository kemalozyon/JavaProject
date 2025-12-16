package MainTest;
import java.util.Scanner;

import Has_A_Classes.*;
import Is_A_Classes.BankAccount;
import Is_A_Classes.CheckingAccount;
import Is_A_Classes.SavingsAccount;
//============================================
//BAMS.java (Main Class)
//============================================
public class Main {
     // BankSystemManager oluştur
	    private static BankSystemManager system = new BankSystemManager();
	    private static Scanner scanner1 = new Scanner(System.in);
	    
	    public static void main(String[] args) {
	        System.out.println("╔════════════════════════════════════════════════╗");
	        System.out.println("║   BAMS - Bank Account Management System       ║");
	        System.out.println("║          İnteraktif Test Programı             ║");
	        System.out.println("╚════════════════════════════════════════════════╝\n");
	        
	        boolean running = true;
	        
	        while (running) {
	            displayMenu();
	            int choice = getIntInput("Seçiminiz: ");
	            System.out.println();
	            
	            switch (choice) {
	                case 1:
	                    addUserMenu();
	                    break;
	                case 2:
	                    addAccountMenu();
	                    break;
	                case 3:
	                    findUserMenu();
	                    break;
	                case 4:
	                    depositMenu();
	                    break;
	                case 5:
	                    withdrawMenu();
	                    break;
	                case 6:
	                    transferMenu();
	                    break;
	                case 7:
	                    applyInterestMenu();
	                    break;
	                case 8:
	                    displayUserMenu();
	                    break;
	                case 9:
	                    displayAllUsers();
	                    break;
	                case 10:
	                    deleteUserMenu();
	                    break;
	                case 11:
	                    showStatistics();
	                    break;
	                case 12:
	                    loadSampleData();
	                    break;
	                case 0:
	                    System.out.println("Programdan çıkılıyor...");
	                    running = false;
	                    break;
	                default:
	                    System.out.println("❌ Geçersiz seçim! Lütfen tekrar deneyin.\n");
	            }
	        }
	        
	        scanner1.close();
	    }
	    
	    private static void displayMenu() {
	        System.out.println("┌────────────────────────────────────────────────┐");
	        System.out.println("│              ANA MENÜ                          │");
	        System.out.println("├────────────────────────────────────────────────┤");
	        System.out.println("│  1.  Yeni Kullanıcı Ekle                       │");
	        System.out.println("│  2.  Hesap Ekle                                │");
	        System.out.println("│  3.  Kullanıcı Ara                             │");
	        System.out.println("│  4.  Para Yatır                                │");
	        System.out.println("│  5.  Para Çek                                  │");
	        System.out.println("│  6.  Transfer Yap                              │");
	        System.out.println("│  7.  Faiz Uygula (Savings)                     │");
	        System.out.println("│  8.  Kullanıcı Detaylarını Görüntüle           │");
	        System.out.println("│  9.  Tüm Kullanıcıları Görüntüle               │");
	        System.out.println("│  10. Kullanıcı Sil                             │");
	        System.out.println("│  11. Sistem İstatistikleri                     │");
	        System.out.println("│  12. Örnek Veri Yükle                          │");
	        System.out.println("│  0.  Çıkış                                     │");
	        System.out.println("└────────────────────────────────────────────────┘");
	    }
	    
	    private static void addUserMenu() {
	        System.out.println("═══ YENİ KULLANICI EKLEME ═══");
	        
	        int userId = getIntInput("Kullanıcı ID: ");
	        if(BankSystemManager.findUser(userId) == null) {
	        	System.out.print("Ad: ");
	 	        String firstName = scanner1.nextLine();
	 	        System.out.print("Soyad: ");
	 	        String lastName = scanner1.nextLine();
	 	        System.out.print("Email: ");
	 	        String email = scanner1.nextLine();
	 	        int age = getIntInput("Yaş: ");
	 	        System.out.print("Şifre: ");
	 	        String password = scanner1.nextLine();
	 	        
	 	        system.addUser(userId, firstName, lastName, email, age, password);
	 	        System.out.println();
	        }
	       
	    }
	    
	    private static void addAccountMenu() {
	        System.out.println("═══ HESAP EKLEME ═══");
	        
	        int userId = getIntInput("Kullanıcı ID: ");
	        User user = system.findUser(userId);
	        
	        if (user == null) {
	            System.out.println("❌ Kullanıcı bulunamadı!\n");
	            return;
	        }
	        
	        System.out.println("Hesap Tipleri:");
	        System.out.println("1. Checking (Vadesiz)");
	        System.out.println("2. Savings (Vadeli)");
	        int typeChoice = getIntInput("Hesap tipi seçin (1-2): ");
	        
	        String accountType = (typeChoice == 1) ? "checking" : "savings";
	        double balance = getDoubleInput("Başlangıç bakiyesi: $");
	        
	        if (accountType.equals("checking")) {
	            double overdraft = getDoubleInput("Overdraft limiti: $");
	            system.addAccountToUser(userId, accountType, balance, overdraft);
	        } else {
	            double interestRate = getDoubleInput("Faiz oranı (%): ");
	            system.addAccountToUser(userId, accountType, balance, interestRate);
	        }
	        System.out.println();
	    }
	    
	    private static void findUserMenu() {
	        System.out.println("═══ KULLANICI ARAMA ═══");
	        
	        int userId = getIntInput("Aranacak kullanıcı ID: ");
	        User user = system.findUser(userId);
	        
	        if (user != null) {
	            System.out.println("✓ Kullanıcı bulundu:");
	            System.out.println("  Ad Soyad: " + user.getFirstName() + " " + user.getLastName());
	            System.out.println("  Email: " + user.getEmail());
	            System.out.println("  Yaş: " + user.getAge());
	            System.out.println("  Hesap Sayısı: " + user.getAccounts().size());
	        } else {
	            System.out.println("❌ Kullanıcı bulunamadı!");
	        }
	        System.out.println();
	    }
	    
	    private static void depositMenu() {
	        System.out.println("═══ PARA YATIRMA ═══");
	        
	        int userId = getIntInput("Kullanıcı ID: ");
	        User user = system.findUser(userId);
	        
	        if (user == null) {
	            System.out.println("❌ Kullanıcı bulunamadı!\n");
	            return;
	        }
	        
	        listUserAccounts(user);
	        int accountNumber = getIntInput("Hesap numarası: ");
	        double amount = getDoubleInput("Yatırılacak miktar: $");
	        
	        system.deposit(userId, accountNumber, amount);
	        System.out.println();
	    }
	    
	    private static void withdrawMenu() {
	        System.out.println("═══ PARA ÇEKME ═══");
	        
	        int userId = getIntInput("Kullanıcı ID: ");
	        User user = system.findUser(userId);
	        
	        if (user == null) {
	            System.out.println("❌ Kullanıcı bulunamadı!\n");
	            return;
	        }
	        
	        listUserAccounts(user);
	        int accountNumber = getIntInput("Hesap numarası: ");
	        double amount = getDoubleInput("Çekilecek miktar: $");
	        
	        system.withdraw(userId, accountNumber, amount);
	        System.out.println();
	    }
	    
	    private static void transferMenu() {
	        System.out.println("═══ TRANSFER İŞLEMİ ═══");
	        
	        int userId = getIntInput("Gönderen kullanıcı ID: ");
	        User user = system.findUser(userId);
	        
	        if (user == null) {
	            System.out.println("❌ Kullanıcı bulunamadı!\n");
	            return;
	        }
	        
	        listUserAccounts(user);
	        int accountNumber = getIntInput("Gönderen hesap numarası: ");
	        System.out.print("Alıcı IBAN: ");
	        String targetIban = scanner1.nextLine();
	        double amount = getDoubleInput("Transfer miktarı: $");
	        
	        system.transfer(userId, accountNumber, targetIban, amount);
	        System.out.println();
	    }
	    
	    private static void applyInterestMenu() {
	        System.out.println("═══ FAİZ UYGULAMA ═══");
	        
	        int userId = getIntInput("Kullanıcı ID: ");
	        User user = system.findUser(userId);
	        
	        if (user == null) {
	            System.out.println("❌ Kullanıcı bulunamadı!\n");
	            return;
	        }
	        
	        System.out.println("Savings hesapları:");
	        for (BankAccount account : user.getAccounts()) {
	            if (account instanceof SavingsAccount) {
	                SavingsAccount savings = (SavingsAccount) account;
	                System.out.println("  Hesap No: " + savings.getAccountNumber() + 
	                                 " | Bakiye: $" + savings.getBalance() + 
	                                 " | Faiz: %" + savings.getInterestRate());
	            }
	        }
	        
	        int accountNumber = getIntInput("Hesap numarası: ");
	        
	        for (BankAccount account : user.getAccounts()) {
	            if (account.getAccountNumber() == accountNumber && account instanceof SavingsAccount) {
	                SavingsAccount savings = (SavingsAccount) account;
	                double interest = savings.calculateInterest();
	                System.out.println("Hesaplanacak faiz: $" + String.format("%.2f", interest));
	                savings.applyInterest();
	                System.out.println("✓ Faiz uygulandı! Yeni bakiye: $" + 
	                                 String.format("%.2f", savings.getBalance()));
	                System.out.println();
	                return;
	            }
	        }
	        
	        System.out.println("❌ Savings hesabı bulunamadı!\n");
	    }
	    
	    private static void displayUserMenu() {
	        System.out.println("═══ KULLANICI DETAYLARI ═══");
	        
	        int userId = getIntInput("Kullanıcı ID: ");
	        String details = system.display(userId);
	        System.out.println(details);
	        System.out.println();
	    }
	    
	    private static void displayAllUsers() {
	        System.out.println("═══ TÜM KULLANICILAR ═══");
	        System.out.println(system.displayAll());
	        System.out.println();
	    }
	    
	    private static void deleteUserMenu() {
	        System.out.println("═══ KULLANICI SİLME ═══");
	        
	        int userId = getIntInput("Silinecek kullanıcı ID: ");
	        system.deleteUser(userId);
	        System.out.println();
	    }
	    
	    private static void showStatistics() {
	        System.out.println("═══ SİSTEM İSTATİSTİKLERİ ═══");
	        System.out.println("Toplam kullanıcı sayısı: " + User.getTotalUsers());
	        System.out.println("Toplam hesap sayısı: " + BankAccount.getTotalAccountsCreated());
	        System.out.println("Toplam sistem bakiyesi: $" + 
	                         String.format("%.2f", system.calculateTotalSystemBalance()));
	        System.out.println();
	    }
	    
	    private static void loadSampleData() {
	        System.out.println("═══ ÖRNEK VERİ YÜKLENİYOR ═══");
	        
	        system.addUser(1, "Kemal", "Özyön", "kemal@example.com", 25, "pass123");
	        system.addUser(2, "Enes", "Özkan", "enes@example.com", 23, "pass456");
	        system.addUser(3, "Egemen", "Helvacı", "egemen@example.com", 24, "pass789");
	        
	        system.addAccountToUser(1, "checking", 10000.0, 500.0);
	        system.addAccountToUser(1, "savings", 5000.0, 3.5);
	        system.addAccountToUser(2, "savings", 3000.0, 2.5);
	        system.addAccountToUser(3, "checking", 2000.0, 300.0);
	        system.addAccountToUser(3, "savings", 8000.0, 4.0);
	        
	        System.out.println("✓ Örnek veri başarıyla yüklendi!");
	        System.out.println("  - 3 kullanıcı eklendi");
	        System.out.println("  - 5 hesap oluşturuldu\n");
	    }
	    
	    private static void listUserAccounts(User user) {
	        System.out.println("Hesaplar:");
	        for (BankAccount account : user.getAccounts()) {
	            String type = (account instanceof CheckingAccount) ? "Checking" : "Savings";
	            System.out.println("  [" + type + "] Hesap No: " + account.getAccountNumber() + 
	                             " | Bakiye: $" + account.getBalance() + 
	                             " | IBAN: " + account.getIban());
	        }
	    }
	    
	    private static int getIntInput(String prompt) {
	        while (true) {
	            try {
	                System.out.print(prompt);
	                int value = Integer.parseInt(scanner1.nextLine());
	                return value;
	            } catch (NumberFormatException e) {
	                System.out.println("❌ Geçerli bir sayı girin!");
	            }
	        }
	    }
	    
	    private static double getDoubleInput(String prompt) {
	        while (true) {
	            try {
	                System.out.print(prompt);
	                double value = Double.parseDouble(scanner1.nextLine());
	                return value;
	            } catch (NumberFormatException e) {
	                System.out.println("❌ Geçerli bir sayı girin!");
	            }
	        }
	    }
}