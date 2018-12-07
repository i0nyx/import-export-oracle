package by.intexsoft.importexport.repositories;

import by.intexsoft.importexport.pojo.Call;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CallRepository extends JpaRepository<Call, Integer> {

    Call findCallByCode(String code);
}
