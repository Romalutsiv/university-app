package org.university.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UniversityClassRepository extends ReactiveMongoRepository<UniversityClassRepository, String> {
}
