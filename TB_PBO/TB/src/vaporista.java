import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

public class vaporista implements CRUD {
    public String driver = "com.mysql.jdbc.Driver"; // driver jdbc
    public String url = "jdbc:mysql://localhost:3306/tugasbesar"; // url untuk melakukan connect ke db
    public String user = "root";
    public String pass = "";

    public Connection conn;
    public Statement stat;
    public ResultSet result;

    String username = "gipa"; // username admin
    String inputUserName;
    String pw = "Ajijah14"; // pw admin
    String inputPw;
    String cap = "GP14"; // captcha
    String inputCap;
    String judul1 = "GVP";
    String judul2 = "data pelanggan";
    String judul3 = "data pembelian barang";

    TreeMap<Integer, String> liquid = new TreeMap<>();// buat objek treemap

    Scanner scanInt = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);

    @Override
    // method untuk melakukan koneksi ke database
    public void connect() {
        try {
            conn = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    // method untuk menambah produk yang dijual
    public void tambah() {
        connect(); // melakukan koneksi
        try {

            stat = conn.createStatement();

            System.out.print("Kode Liquid\t\t: ");
            String kodeLiq = scanStr.nextLine();

            System.out.print("Masukkan Nama brewery\t: ");
            String brewery = scanStr.nextLine();

            System.out.print("Masukkan nama liquid\t: ");
            String namaLiq = scanStr.nextLine();

            System.out.print("Masukkan jumlah nicotin\t: ");
            String nic = scanStr.nextLine();

            System.out.print("Masukkan volume liquid\t: ");
            String volume = scanStr.nextLine();

            System.out.print("Masukkan harga\t\t: ");
            Integer harga = scanInt.nextInt();

            System.out.print("Masukkan jumlah stok\t: ");
            Integer stok = scanInt.nextInt();

            liquid.put(stok, kodeLiq);// memasukkan isi treemap
            String sql = "INSERT INTO liquid (kodeLiq, brewery, namaLiq, nic, volume, harga, jumlah) VALUE ('%s', '%s', '%s', '%s', '%s', %d, %d )";
            sql = String.format(sql, kodeLiq, brewery, namaLiq, nic, volume, harga, stok);
            stat.execute(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // menampilkan iterasi treemap
    public void viewTree() {
        System.out.println("\nmenampilkan stok dari jumlah beli stok paling sedikit:");
        for (Entry<Integer, String> entry : liquid.entrySet()) {
            System.out.println("Stok\t: " + entry.getKey() + "\tkode Liquid\t: " +
                    entry.getValue());
        }
    }

    @Override
    // method untuk menghapus produk
    public void delete() {
        connect();
        try {
            stat = conn.createStatement();

            System.out.print("Masukkan kode liquid yang mau di hapus : ");
            String kodeLiq = scanStr.nextLine();

            String sql = String.format("DELETE FROM liquid WHERE kodeLiq = '%s'", kodeLiq);

            stat.execute(sql);
            System.out.println("Data terhapus");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    // method untuk menampilkan sisa stok
    public void show() {
        connect();
        String sql = "SELECT * FROM liquid";
        try {
            stat = conn.createStatement();
            result = stat.executeQuery(sql);

            System.out.println("\tDATA STOK GVP  ");
            System.out.println("------------------------------");

            while (result.next()) {
                String kodeLiq = result.getString("kodeLiq");
                String brewery = result.getString("brewery");
                String namaLiq = result.getString("namaLiq");
                String nic = result.getString("nic");
                String volume = result.getString("volume");
                Integer harga = result.getInt("harga");
                Integer stok = result.getInt("jumlah");

                System.out.println(
                        String.format(
                                "kode liquid\t: %s \nbrewery\t\t: %s \nnama liquid\t: %s \njumlah nicotin\t: %s \nvolume\t\t: %s \nharga\t\t: %d  \njumlah stok\t: %d \n ",
                                kodeLiq, brewery,
                                namaLiq, nic, volume, harga, stok));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    // method untuk menambah jumlah stok
    public void update() {
        connect();
        String sql2 = "SELECT * FROM liquid";
        try {

            stat = conn.createStatement();
            result = stat.executeQuery(sql2);

            if (result.next()) {
                Integer stok = result.getInt("jumlah");
                System.out.print("masukkan kode liquid yang dibeli\t: ");
                String kodeLiq = scanStr.nextLine();
                System.out.print("masukkan jumlah pembelian\t\t: ");
                Integer tambah = scanInt.nextInt();
                Integer hasil = stok + tambah;

                String sql = "UPDATE liquid SET jumlah = %d WHERE kodeLiq = '%s'";
                sql = String.format(sql, hasil, kodeLiq);

                try (Statement updateStatement = conn.createStatement()) {
                    updateStatement.executeUpdate(sql);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void beli() {

    }

    public void cetakStruk() {

    }

    public void login() {

    }

}
