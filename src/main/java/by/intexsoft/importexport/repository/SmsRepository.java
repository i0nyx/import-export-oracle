package by.intexsoft.importexport.repository;

import by.intexsoft.importexport.pojo.Sms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Integer> {
    Sms findSmsByCode(final String code);
}
