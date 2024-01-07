import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        boolean pilih = true;
        vaporista gipa = new store();
        gipa.login();

        while (pilih) {
            System.out.println("\n        GIP VAPE STORE ");
            System.out.println("==============================");
            System.out.println("1. tambah produk");
            System.out.println("2. jual produk");
            System.out.println("3. restock produk");
            System.out.println("4. hapus produk");
            System.out.println("5. cetak struk");
            System.out.println("6. tampilkan stok");
            System.out.println("7. tampilkan isi treemap");
            System.out.println("0. keluar");
            System.out.println("");
            System.out.print("PILIHAN> ");

            Scanner scanStr = new Scanner(System.in);
            String input = scanStr.nextLine();

            switch (input) {
                case "0":
                    System.out.println("sampai jumpa kembali:)");
                    System.exit(0);
                    pilih = false;
                    break;

                case "1":
                    gipa.tambah();
                    break;

                case "2":
                    gipa.beli();
                    break;

                case "3":
                    gipa.update();
                    break;

                case "4":
                    gipa.delete();
                    break;

                case "5":
                    gipa.cetakStruk();
                    break;

                case "6":
                    gipa.show();
                    break;

                case "7":
                    gipa.viewTree();
                    break;

                default:
                    System.out.println("masukkan input yang benar:)");
            }
        }
    }
}
