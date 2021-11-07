package tecnologo.nosql.laboratorio.Error;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ErrorRepository extends MongoRepository<Error, Integer> {

    Error findById(int id);
}
