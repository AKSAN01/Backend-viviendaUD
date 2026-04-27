package co.edu.distrital.fis.vivienda_backend.repositories;

import co.edu.distrital.fis.vivienda_backend.documents.RegistroActividad;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroActividadRepository extends MongoRepository<RegistroActividad, String> {
    // MongoRepository ya trae los métodos save(), findAll(), etc. listos para usar
}