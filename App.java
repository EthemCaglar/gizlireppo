import jakarta.persistence.*;

public class App {
    public static void main(String[] args){
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("market")){
            EntityManager em = emf.createEntityManager();

            EntityTransaction trs = em.getTransaction();

            trs.begin();
            trs.commit();
        }

    }

}
