package nl.codecentric.clean_hexagonal_onion_service.usecase;

public interface UseCase<I, O> {
    O execute(I request);
}
