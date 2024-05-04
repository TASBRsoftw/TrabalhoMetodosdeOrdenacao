import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

class Acomodacao implements Cloneable {
    private int roomId;
    private int hostId;
    private String roomType;
    private String country;
    private String city;
    private String neighbourhood;
    private int reviews;
    private double overallSatisfaction;
    private int accommodates;
    private double bedrooms;
    private double price;
    private String propertyType;

    public Acomodacao(int roomId, int hostId, String roomType, String country, String city, String neighbourhood,
                      int reviews, double overallSatisfaction, int accommodates, double bedrooms, double price,
                      String propertyType) {
        this.roomId = roomId;
        this.hostId = hostId;
        this.roomType = roomType;
        this.country = country;
        this.city = city;
        this.neighbourhood = neighbourhood;
        this.reviews = reviews;
        this.overallSatisfaction = overallSatisfaction;
        this.accommodates = accommodates;
        this.bedrooms = bedrooms;
        this.price = price;
        this.propertyType = propertyType;
    }

    public void imprimir() {
        System.out.printf("[%d ## %d ## %s ## %s ## %s ## %s ## %d ## %.1f ## %d ## %.1f ## %.1f ## %s]%n",
                          roomId, hostId, roomType, country, city, neighbourhood, reviews, overallSatisfaction,
                          accommodates, bedrooms, price, propertyType);
    }

    public static Acomodacao ler(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line == null)
            return null;

        String[] parts = line.split("\t");
        int roomId = Integer.parseInt(parts[0]);
        int hostId = Integer.parseInt(parts[1]);
        String roomType = parts[2];
        String country = parts[3];
        String city = parts[4];
        String neighbourhood = parts[5];
        int reviews = Integer.parseInt(parts[6]);
        double overallSatisfaction = Double.parseDouble(parts[7]);
        int accommodates = Integer.parseInt(parts[8]);
        double bedrooms = Double.parseDouble(parts[9]);
        double price = Double.parseDouble(parts[10]);
        String propertyType = parts[11];

        return new Acomodacao(roomId, hostId, roomType, country, city, neighbourhood, reviews,
                              overallSatisfaction, accommodates, bedrooms, price, propertyType);
    }

    // Getters and setters for all fields, not included here for brevity

    // Quicksort related methods
    public static void quickSort(Acomodacao[] array, int esq, int dir, QuicksortStats stats) {
        if (esq < dir) {
            int j = partition(array, esq, dir, stats);
            quickSort(array, esq, j - 1, stats);
            quickSort(array, j + 1, dir, stats);
        }
    }

    public static int partition(Acomodacao[] array, int esq, int dir, QuicksortStats stats) {
        Acomodacao pivot = array[dir];
        int i = (esq - 1);

        for (int j = esq; j < dir; j++) {
            stats.comparisons++; // Comparing elements for sorting
            if (compare(array[j], pivot) < 0) {
                i++;
                swap(array, i, j);
                stats.movements += 3; // Swap counts as three movements
            }
        }

        swap(array, i + 1, dir);
        stats.movements += 3; // Swap counts as three movements
        return i + 1;
    }

    private static int compare(Acomodacao a, Acomodacao b) {
        if (a.price < b.price) return -1;
        if (a.price > b.price) return 1;
        int roomTypeCompare = a.roomType.compareTo(b.roomType);
        if (roomTypeCompare != 0) return roomTypeCompare;
        return Integer.compare(a.roomId, b.roomId);
    }

    private static void swap(Acomodacao[] array, int i, int j) {
        Acomodacao temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

class QuicksortStats {
    public long timeTaken;
    public int comparisons = 0;
    public int movements = 0;
}

public class MethodQuicksort {

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/dados_airbnb.txt"))) {
            br.readLine(); // Skip header
            Acomodacao[] acomodacoes = new Acomodacao[127993];
            int i = 0;
            Acomodacao acomodacao;
            while ((acomodacao = Acomodacao.ler(br)) != null) {
                acomodacoes[i++] = acomodacao;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int ord = Integer.parseInt(reader.readLine().trim());
            Acomodacao[] acomodacoesOrdenadas = new Acomodacao[ord];

            for (i = 0; i < ord; i++) {
                int idBusca = Integer.parseInt(reader.readLine().trim());
                for (Acomodacao a : acomodacoes) {
                    if (a != null && a.getRoomId() == idBusca) {
                        acomodacoesOrdenadas[i] = a;
                        break;
                    }
                }
            }

            QuicksortStats stats = new QuicksortStats();
            long startTime = System.currentTimeMillis();
            Acomodacao.quickSort(acomodacoesOrdenadas, 0, acomodacoesOrdenadas.length - 1, stats);
            stats.timeTaken = System.currentTimeMillis() - startTime;

            for (Acomodacao a : acomodacoesOrdenadas) {
                if (a != null) a.imprimir();
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter("matricula_quicksort.txt"))) {
                writer.printf("%d\t%d\t%d\t%d%n", 740791, stats.timeTaken, stats.comparisons, stats.movements);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
