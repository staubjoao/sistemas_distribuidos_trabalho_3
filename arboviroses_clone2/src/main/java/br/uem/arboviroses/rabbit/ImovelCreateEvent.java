package br.uem.arboviroses.rabbit;

public class ImovelCreateEvent {
    private String id;
    private String query;

    public ImovelCreateEvent() {
    }

    public ImovelCreateEvent(String id, String query) {
        this.id = id;
        this.query = query;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
