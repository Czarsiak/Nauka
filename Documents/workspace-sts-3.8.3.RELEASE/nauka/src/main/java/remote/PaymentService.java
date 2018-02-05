package remote;

public interface PaymentService {

    void pay(long cardId, double amount) throws NotEnoughMoneyException;
}







