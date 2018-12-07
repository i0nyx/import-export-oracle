package by.intexsoft.importexport.repositories;

import by.intexsoft.importexport.pojo.Mms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MmsRepository extends JpaRepository<Mms, Integer> {

    Mms findMmsByCode(final String code);
}
