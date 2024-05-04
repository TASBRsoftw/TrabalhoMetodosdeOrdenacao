package MetodosIndividuais;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

public class MethodInsertion {

    public static void insertionSort(Acomodacao[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            Acomodacao key = array[i];
            int j = i - 1;

            while (j >= 0 && (array[j].getAccommodates() > key.getAccommodates() ||
                    (array[j].getAccommodates() == key.getAccommodates() &&
                            array[j].getRoomId() > key.getRoomId()))) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("/tmp/dados_airbnb.txt"))) {
            // /tmp/
            br.readLine();

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

            insertionSort(acomodacoesOrdenadas);

            for (i = 0; i < ord; i++) {
                acomodacoesOrdenadas[i].imprimir();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
