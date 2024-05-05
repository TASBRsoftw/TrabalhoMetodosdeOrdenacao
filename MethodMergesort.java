import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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

    public Acomodacao(Acomodacao outra) {
        this(outra.roomId, outra.hostId, outra.roomType, outra.country, outra.city, outra.neighbourhood,
                outra.reviews, outra.overallSatisfaction, outra.accommodates, outra.bedrooms, outra.price,
                outra.propertyType);
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

    @Override
    public Acomodacao clone() {
        return new Acomodacao(this);
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getHostId() {
        return hostId;
    }

    public void setHostId(int hostId) {
        this.hostId = hostId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public int getReviews() {
        return reviews;
    }

    public void setReviews(int reviews) {
        this.reviews = reviews;
    }

    public double getOverallSatisfaction() {
        return overallSatisfaction;
    }

    public void setOverallSatisfaction(double overallSatisfaction) {
        this.overallSatisfaction = overallSatisfaction;
    }

    public int getAccommodates() {
        return accommodates;
    }

    public void setAccommodates(int accommodates) {
        this.accommodates = accommodates;
    }

    public double getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(double bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }
}

public class MethodMergesort {

    // Método auxiliar para mesclar duas metades de um array.
    private static void merge(Acomodacao[] array, Acomodacao[] aux, int left, int mid, int right, int[] count) {
        int i = left, j = mid + 1, k = left;
        
        // Mesclando as metades em aux[]
        while (i <= mid && j <= right) {
            count[0]++; // contando comparações
            if (array[i].getHostId() < array[j].getHostId() ||
                    (array[i].getHostId() == array[j].getHostId() && array[i].getRoomId() < array[j].getRoomId())) {
                aux[k++] = array[i++];
            } else {
                aux[k++] = array[j++];
            }
            count[1]++; // contando movimentações
        }

        // Copiando o restante dos elementos da esquerda, se houver
        while (i <= mid) {
            aux[k++] = array[i++];
            count[1]++; // contando movimentações
        }

        // Copiando o restante dos elementos da direita, se houver
        while (j <= right) {
            aux[k++] = array[j++];
            count[1]++; // contando movimentações
        }

        // Copiando de volta para o array original
        for (i = left; i <= right; i++) {
            array[i] = aux[i];
            count[1]++; // contando movimentações
        }
    }

    // Método recursivo para aplicar MergeSort
    private static void mergeSort(Acomodacao[] array, Acomodacao[] aux, int left, int right, int[] count) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, aux, left, mid, count);
            mergeSort(array, aux, mid + 1, right, count);
            merge(array, aux, left, mid, right, count);
        }
    }

    // Método de ordenação que configura o MergeSort
    public static void sort(Acomodacao[] array) {
        long startTime = System.currentTimeMillis();
        Acomodacao[] aux = new Acomodacao[array.length];
        int[] count = new int[2]; // [0] para comparações, [1] para movimentações

        mergeSort(array, aux, 0, array.length - 1, count);

        long endTime = System.currentTimeMillis();
        long tempoExecucao = endTime - startTime;

        // Criando o arquivo de log
        try (PrintWriter writer = new PrintWriter(new FileWriter("matricula_mergesort.txt"))) {
            writer.printf("%s\t%d\t%d\t%d%n", "740791|1427063", tempoExecucao, count[0], count[1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
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

            sort(acomodacoesOrdenadas);

            for (i = 0; i < ord; i++) {
                acomodacoesOrdenadas[i].imprimir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
