import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {
    public static void main(String[] args){
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("market")){
            EntityManager em = emf.createEntityManager();
        }
        System.out.println("test");
    }

}
