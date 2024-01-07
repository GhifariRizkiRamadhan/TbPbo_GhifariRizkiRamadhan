import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

public class store extends vaporista {

    // method login
    public void login() {

        System.out.println("\tSELAMAT DATANG DI " + judul1.toUpperCase());
        System.out.println("      -------------------------------");
        while (true) { // perulangan jka username dan password tidak valid

            System.out.print("username\t: ");
            inputUserName = scanStr.nextLine();

            System.out.print("password\t: ");
            inputPw = scanStr.nextLine();

            if (inputPw.equals(pw) && inputUserName.equals(username)) {// memberhentikan perulangan jika input valid
                System.out.println("");
                break;
            } else {
                System.out.println("username atau password salah\n"); // message jika input tidak valid
            }

        }

        while (true) {// perulangan bila input captcha tidak valid
            System.out.println("kode captcha\t: " + cap);
            System.out.print("entry cap\t: ");
            inputCap = scanStr.nextLine();

            if (inputCap.equalsIgnoreCase(cap)) {// meberhentikan perulangan jika input valid
                break;
            } else {
                System.out.println("\nsilahkan masukkan lagi"); // message jika input tidak valid
            }

        }
        System.out.println("==============================================\n");
    }

    @Override
    // method untuk melakukan pembelian dan memasukkan ke db
    public void beli() {
        connect();
        String sql3 = "SELECT * FROM liquid";
        try {
            stat = conn.createStatement();
            result = stat.executeQuery(sql3);

            if (result.next()) {

                Integer stok = result.getInt("jumlah");

                System.out.print("masukkan id pembelian\t\t: ");
                String id = scanStr.next();

                System.out.print("masukkan nama pelanggan\t\t: ");
                String nama = scanStr.next();

                System.out.print("masukkan nomor hp pelanggan\t: ");
                String nohp = scanStr.next();

                System.out.print("masukkan alamat pelanggan\t: ");
                String alamat = scanStr.next();

                System.out.print("masukkan kode Liquid\t\t: ");
                String kodeLiq = scanStr.next();

                System.out.print("masukkan liquid yang dibeli\t: ");
                String namaLiq = scanStr.next();

                System.out.print("masukkan jumlah beli\t\t: ");
                Integer jumlahBeli = scanStr.nextInt();

                System.out.print("masukkan harga liquid\t\t: ");
                Integer harga = scanStr.nextInt();

                Integer totalbayar = jumlahBeli * harga;

                Integer hasil = stok - jumlahBeli;

                String sql2 = "UPDATE liquid SET jumlah = %d WHERE kodeLiq = '%s'";
                sql2 = String.format(sql2, hasil, kodeLiq);

                try (Statement updateStatement = conn.createStatement()) {
                    updateStatement.executeUpdate(sql2);
                }

                String sql = "INSERT INTO pelanggan (id, nama, nomorHp, alamat, kodeLiq, namaLiq, jumlahBeli, harga, bayar) VALUE ('%s','%s','%s', '%s', '%s', '%s', %d, %d, %d )";
                sql = String.format(sql, id, nama, nohp, alamat, kodeLiq, namaLiq, jumlahBeli, harga, totalbayar);
                // stat.execute(sql);
                try (Statement updateStatement = conn.createStatement()) {
                    updateStatement.executeUpdate(sql);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    // method untuk cetak struk
    public void cetakStruk() {
        connect();
        System.out.print("masukkan id pembelian\t: ");
        String inputId = scanStr.nextLine();// scaner untuk memilih pembelian mna yang ingin di cetak

        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM pelanggan WHERE id =?");
            ps.setString(1, inputId);
            result = ps.executeQuery();

            while (result.next()) {
                String nama = result.getString("nama");
                String nomorHp = result.getString("nomorHp");
                String alamat = result.getString("alamat");
                String kodeLiq = result.getString("kodeLiq");
                String namaLiq = result.getString("namaLiq");
                Integer jumlahBeli = result.getInt("jumlahBeli");
                Integer harga = result.getInt("harga");
                Integer bayar = result.getInt("bayar");

                Date hariPesan = new Date();
                SimpleDateFormat tgl = new SimpleDateFormat("E dd/MM/yyyy"); // membuat objek untuk menampilkan tanggal
                SimpleDateFormat wkt = new SimpleDateFormat("hh:mm:ss zzzz"); // membuat objek untuk menampilkan waktu

                System.out.println("\n\t\t      " + judul1.toUpperCase());// uppercasse untuk nama toko
                System.out.println("----------------------------------------------");
                System.out.println("tanggal\t\t: " + tgl.format(hariPesan));// method date untuk menampilkan tanggal
                System.out.println("jam\t\t: " + wkt.format(hariPesan));// method date untuk menampilkan waktu
                System.out.println("==============================================");
                System.out.println("\n\t     " + judul2.toUpperCase());// uppercase untuk data pelanggan
                System.out.println("\t    ----------------");
                System.out.println("nama pelanggan\t: " + nama);
                System.out.println("nomor HP\t: " + nomorHp);
                System.out.println("alamat\t\t: " + alamat);
                System.out.println("==============================================");
                System.out.println("\n\t   " + judul3.toUpperCase());
                System.out.println("\t  ------------------------");
                System.out.println("kode barang\t: " + kodeLiq);
                System.out.println("nama barang\t: " + namaLiq);
                System.out.println("harga barang\t: " + harga);
                System.out.println("jumlah beli\t: " + jumlahBeli);
                System.out.println("total bayar\t: " + bayar);
                System.out.println("===============================================");
                System.out.println(" kasir : Ghifari Rizki Ramadhan");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
