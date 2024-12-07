public class HQLUpdateExample {

    public static void main(String[] args) {
        // Step 1: Configure Hibernate
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Department.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        // Step 2: Open Session and Transaction
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            // Step 3: HQL Update Query with Positional Parameters
            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE departmentId = ?3";
            int updatedEntities = session.createQuery(hql)
                    .setParameter(1, "Updated Department Name")
                    .setParameter(2, "Updated Location")
                    .setParameter(3, 1) // Assume updating the department with ID 1
                    .executeUpdate();

            // Step 4: Commit Transaction
            transaction.commit();
            System.out.println("Records updated: " + updatedEntities);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}