package com.github.affandes.kuliah.pm;
import java.util.Stack;
import java.util.EmptyStackException;

public class Main {
    // buat sebuah variabel privat bertipe stack yang akan digunakan untuk menyimpan data nantinya
    private final Stack<String> historyStack;

    public Main() {
        //buat stack baru pada objek ini(historyStack)
        this.historyStack = new Stack<>();
    }
    //buat class browse yang akan menjadi alat untuk diterapkan fungsi nantinya dengan inputan data string dengan nama variable url
    public void browse(String url) {
        //masukan kedalam stack urs
        historyStack.push(url);
        //print url yang di input
        System.out.println("**BROWSED:** Masuk ke " + url);
    }

    //buat class untuk mengembalikan atau mengeluarkan lapisan paling atas di stack
    public String back() {
        try {
            //simpan nilai setelah dikeluarkan ke dalam variabel ini
            String prevUrl = historyStack.pop();
            //simpan lokasi aktual (saat ini)
            String currentUrl = viewCurrent();

            //tampilkan halaman sebelumnya atau yang sudah dikeluarkan tadi
            System.out.println("**BACKED:** Kembali dari " + prevUrl);
            //tampilkan saat ini dan jika kosong maka tampilkan "halaman awal"
            System.out.println("**CURRENT:** Sekarang di " + (currentUrl != null ? currentUrl : "Halaman Awal"));
            return prevUrl;

        } catch (EmptyStackException e) {
            System.out.println("**ERROR:** Riwayat kosong. Tidak bisa kembali.");
            return null;
        }
    }
    //class untuk menampilkan
    public void view() {
        System.out.println("\n--- data dari paling baru(atas) ---");

        if (historyStack.isEmpty()) {
            System.out.println("Riwayat browser kosong.");
            System.out.println("------------------------------------------\n");
            return;
        }

        // Iterasi terbalik: mulai dari elemen paling atas (yang paling baru)
        for (int i = historyStack.size() - 1; i >= 0; i--) {
            String url = historyStack.get(i);
            // Tanda (CURRENT) diberikan pada elemen teratas (yang paling baru)
            String status = (i == historyStack.size() - 1) ? " (CURRENT)" : "";
            System.out.println((i + 1) + ". " + url + status);
        }

        System.out.println("------------------------------------------\n");
    }
    //untuk menampilkan saat ini
    private String viewCurrent() {
        try {
            //tampilkan paling atas tapi jangan di keluarkan
            return historyStack.peek();
        } catch (EmptyStackException e) {
            return null;
        }
    }

    public static void main(String[] args) {
        Main browser = new Main();

        // Operasi 1: browse
        browser.browse("google.com");
        browser.browse("openai.com");
        browser.browse("github.com/user");

        // Operasi 2: view
        browser.view();

        // Operasi 3: back
        browser.back();
        browser.back();

        // Operasi baru
        browser.browse("flutter.dev");
        browser.view();

        // Uji kondisi kosong
        browser.back();
        browser.back();
        browser.back();
    }
}