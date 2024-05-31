import java.util.ArrayList;
import java.util.Scanner;

abstract class Tanaman {
    protected int masaHidup;
    protected int lamaHidup;
    protected double prosesBerbuah;
    protected int buah;
    protected double perkembangan;

    public abstract void berkembang();

    public String status() {
        if (lamaHidup >= masaHidup) {
            return "Mati";
        } else {
            return "Hidup";
        }
    }

    public String toString() {
        return "Umur: " + lamaHidup + " hari, Buah: " + buah + ", Status: " + status();
    }

}

interface Perawatan {
    void treatment();
}

class Tomat extends Tanaman implements Perawatan {
    public Tomat() {
        masaHidup = 100;
        berbuah = 100;
        perkembangan = 0.25;
    }

    @Override
    public void berkembang() {
        prosesBerbuah += perkembangan;
        if (prosesBerbuah >= berbuah) {
            buah++;
            prosesBerbuah = 0;
        }
    }

    @Override
    public void treatment() {
        perkembangan += 0.05;
    }
}

class Stroberi extends Tanaman implements Perawatan {
    public Stroberi() {
        masaHidup = 60;
        berbuah = 150;
        perkembangan = 0.35;
    }

    @Override
    public void berkembang() {
        prosesBerbuah += perkembangan;
        if (prosesBerbuah >= berbuah) {
            buah++;
            prosesBerbuah = prosesBerbuah - berbuah;
        }
    }

    @Override
    public void treatment() {
        perkembangan += 0.05;
    }
}

class Persik extends Tanaman implements Perawatan {
    public Persik() {
        masaHidup = 180;
        berbuah = 250;
        perkembangan = 0.15;
    }

    @Override
    public void berkembang() {
        prosesBerbuah += perkembangan;
        if (prosesBerbuah >= berbuah) {
            buah++;
            prosesBerbuah = prosesBerbuah - berbuah;
        }
    }

    @Override
    public void treatment() {
        perkembangan += 0.025;
    }
}

class Data {
    private static ArrayList<Tanaman> tanamans = new ArrayList<>();
    private static ArrayList<Integer> lokasi = new ArrayList<>();

    public static void mulai() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Masukkan masa tanam (dalam bulan): ");
        int masaTanamBulan = scanner.nextInt();
        int masaTanamHari = masaTanamBulan * 30;

        tanamans.clear();
        lokasi.clear();

        tanamans.add(new Tomat());
        tanamans.add(new Stroberi());
        tanamans.add(new Persik());

        for (int i = 1; i <= masaTanamHari; i++) {
            System.out.println("Hari ke-" + i + "(umur " + i + " hari):");
            for (int j = 0; j < tanamans.size(); j++) {
                Tanaman tanaman = tanamans.get(j);
                if (!lokasi.contains(j)) {
                    tanaman.berkembang();
                    System.out.println(tanaman.getClass().getSimpleName() + ": prosesBerbuah = " + tanaman.prosesBerbuah + ", " + tanaman);
                } else {
                    System.out.println(tanaman.getClass().getSimpleName() + " : Mati");
                }
            }
            if (i % 90 == 0) {
                for (Tanaman tanaman : tanamans) {
                    if (!lokasi.contains(tanamans.indexOf(tanaman))) {
                        tanaman.treatment();
                    }
                }
            }
            for (int k = 0; k < tanamans.size(); k++) {
                Tanaman tanaman = tanamans.get(k);
                if (tanaman.status().equals("Mati")) {
                    lokasi.add(k);
                    System.out.println(tanaman.getClass().getSimpleName() + " Mati");
                    menanam();
                }
            }
        }
    }

    public static void menanam() {
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Tanaman mati, silakan pilih tanaman baru untuk ditanam:");
        System.out.println("1. Tomat");
        System.out.println("2. Stroberi");
        System.out.println("3. Persik");
        System.out.print("Pilih: ");
        int pilihan = scanner.nextInt();

        Tanaman tanamanBaru = null;
        switch (pilihan) {
            case 1:
                tanamanBaru = new Tomat();
                break;
            case 2:
                tanamanBaru = new Stroberi();
                break;
            case 3:
                tanamanBaru = new Persik();
                break;
            default:
                System.out.println("Pilihan tidak valid.");
        }

        if (tanamanBaru != null) {
            tanamans.add(tanamanBaru);
            System.out.println("Berhasil menanam " + tanamanBaru.getClass().getSimpleName());
        }
    }

    public static void info() {
        System.out.println("Laporan hasil masa tanam:");
        for (int i = 0; i < tanamans.size(); i++) {
            Tanaman tanaman = tanamans.get(i);
            System.out.println("Tanaman ke-" + (i + 1) + ": " + tanaman);
        }
    }
}

public class UAP {
    public static void main(String[] args) {
        Data.mulai();
        Data.info();
    }
}
