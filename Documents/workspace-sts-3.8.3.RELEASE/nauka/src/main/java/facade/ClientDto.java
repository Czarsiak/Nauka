package facade;

import java.io.Serializable;

public class ClientDto implements Serializable {

    private long cardId;
    private String login;

    public ClientDto(long cardId, String login){
        this.cardId = cardId;
        this.login = login;
    }

    public long getCardId() {
        return cardId;
    }

    public String getLogin() {
        return login;
    }
}
