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

public class MethodHeapsort {

    public static void heapSort(Acomodacao[] array) {
        long startTime = System.currentTimeMillis();
        int n = array.length;

        // Construir heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(array, n, i);

        // Extrair elementos do heap um por um
        for (int i = n - 1; i > 0; i--) {
            // Move a raiz atual para o final
            Acomodacao temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            // Chama max heapify na heap reduzida
            heapify(array, i, 0);
        }

        long endTime = System.currentTimeMillis();
        long tempoExecucao = endTime - startTime;

        int comparacoes = (n * (int)Math.ceil(Math.log(n) / Math.log(2))) - (n - 1);
        int movimentacoes = 3 * (n - 1);

        try (PrintWriter writer = new PrintWriter(new FileWriter("matricula_heapsort.txt"))) {
            writer.printf("%s\t%d\t%d\t%d%n", 740791|1427063, tempoExecucao, comparacoes, movimentacoes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void heapify(Acomodacao[] array, int n, int i) {
        int largest = i; // Inicializa o maior como raiz
        int left = 2 * i + 1; // Esquerda = 2*i + 1
        int right = 2 * i + 2; // Direita = 2*i + 2

        // Se o filho esquerdo é maior que a raiz
        if (left < n && (array[left].getReviews() > array[largest].getReviews() ||
                (array[left].getReviews() == array[largest].getReviews() && array[left].getRoomId() > array[largest].getRoomId())))
            largest = left;

        // Se o filho direito é maior que o maior até agora
        if (right < n && (array[right].getReviews() > array[largest].getReviews() ||
                (array[right].getReviews() == array[largest].getReviews() && array[right].getRoomId() > array[largest].getRoomId())))
            largest = right;

        // Se o maior não é a raiz
        if (largest != i) {
            Acomodacao swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;

            // Recursivamente heapify a subárvore afetada
            heapify(array, n, largest);
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/dados_airbnb.txt"))) {
            br.readLine(); // Ignorar a primeira linha

            Acomodacao[] acomodacoes = new Acomodacao[127993];
            int i = 0;
            Acomodacao acomodacao;
            while ((acomodacao = Acomodacao.ler(br)) != null) {
                acomodacoes[i++] = acomodacao;
            }

            BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
            String id = reader.readLine();
            int ord = Integer.parseInt(id);
            Acomodacao[] acomodacoesOrdenadas = new Acomodacao[ord];

            for (i = 0; i < ord; i++) {
                id = reader.readLine();
                int idBusca = Integer.parseInt(id);
                for (Acomodacao a : acomodacoes) {
                    if (a != null && a.getRoomId() == idBusca) {
                        acomodacoesOrdenadas[i] = a;
                        break;
                    }
                }
            }

            heapSort(acomodacoesOrdenadas);

            for (i = 0; i < ord; i++) {
                acomodacoesOrdenadas[i].imprimir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
