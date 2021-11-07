package tecnologo.nosql.laboratorio.Error;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CodigoErrorRepository extends MongoRepository<CodigoError, Integer> {

    CodigoError findById(int id);
}
