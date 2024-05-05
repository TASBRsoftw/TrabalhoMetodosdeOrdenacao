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

    public double getPrice() {
        return price;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getRoomId() {
        return roomId;
    }
}

public class MethodQuicksort {
    public static void quickSort(Acomodacao[] array, int left, int right, Contador contador) {
        if (left < right) {
            int pivotIndex = partition(array, left, right, contador);
            quickSort(array, left, pivotIndex - 1, contador);
            quickSort(array, pivotIndex + 1, right, contador);
        }
    }

    private static int partition(Acomodacao[] array, int left, int right, Contador contador) {
        Acomodacao pivot = array[right];
        int i = (left - 1);
        for (int j = left; j <= right - 1; j++) {
            contador.incrementaComparacoes();  // Contagem de comparações
            if (array[j].getPrice() < pivot.getPrice() ||
                    (array[j].getPrice() == pivot.getPrice() && array[j].getRoomType().compareTo(pivot.getRoomType()) < 0) ||
                    (array[j].getPrice() == pivot.getPrice() && array[j].getRoomType().equals(pivot.getRoomType()) && array[j].getRoomId() < pivot.getRoomId())) {
                i++;
                swap(array, i, j);
                contador.incrementaMovimentacoes();  // Contagem de movimentações
            }
        }
        swap(array, i + 1, right);
        contador.incrementaMovimentacoes();  // Contagem de movimentações
        return (i + 1);
    }

    private static void swap(Acomodacao[] array, int i, int j) {
        Acomodacao temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/dados_airbnb.txt"))) {
            br.readLine(); // Descartando a primeira linha

            Acomodacao[] acomodacoes = new Acomodacao[127993];
            int i = 0;
            Acomodacao acomodacao;
            while ((acomodacao = Acomodacao.ler(br)) != null) {
                acomodacoes[i++] = acomodacao;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int ord = Integer.parseInt(reader.readLine());
            Acomodacao[] acomodacoesOrdenadas = new Acomodacao[ord];

            for (i = 0; i < ord; i++) {
                int idBusca = Integer.parseInt(reader.readLine());
                for (Acomodacao a : acomodacoes) {
                    if (a != null && a.getRoomId() == idBusca) {
                        acomodacoesOrdenadas[i] = a;
                        break;
                    }
                }
            }

            Contador contador = new Contador();
            long startTime = System.currentTimeMillis();
            quickSort(acomodacoesOrdenadas, 0, ord - 1, contador);
            long endTime = System.currentTimeMillis();
            long tempoExecucao = endTime - startTime;

            try (PrintWriter writer = new PrintWriter(new FileWriter("matricula_quicksort.txt"))) {
                writer.printf("%s\t%d\t%d\t%d%n", "740791|1427063", tempoExecucao, contador.getComparacoes(), contador.getMovimentacoes());
            }

            for (i = 0; i < ord; i++) {
                acomodacoesOrdenadas[i].imprimir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Contador {
    private int comparacoes = 0;
    private int movimentacoes = 0;

    public void incrementaComparacoes() {
        comparacoes++;
    }

    public void incrementaMovimentacoes() {
        movimentacoes++;
    }

    public int getComparacoes() {
        return comparacoes;
    }

    public int getMovimentacoes() {
        return movimentacoes;
    }
}
