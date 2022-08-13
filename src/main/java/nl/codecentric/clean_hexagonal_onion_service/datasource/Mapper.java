package nl.codecentric.clean_hexagonal_onion_service.datasource;

public interface Mapper<D,P>{
    P toPersistence(D domain);
    D toDomain(P persistence);
}
