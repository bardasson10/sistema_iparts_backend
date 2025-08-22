package br.com.estoque.iparts.security.enums;

public enum StatusEnum {
    ativo("Ativo", true, (byte) 1),
    inativo("Inativo", false, (byte) 2),
    recebido("Recebido", true, (byte) 3),
    aprovado("Aprovado", true, (byte) 4),
    pendente("Pendente", true, (byte) 5),
    rejeitado("Rejeitado", false, (byte) 6),
    entrada("Entrada", true, (byte) 7),
    devolvido("Devolvido", true, (byte) 8),
    saida("Saída", true, (byte) 9),
    visto("Lido", true, (byte) 10),
    naovisto("Não lido", false, (byte) 11),
    bloqueado("Bloqueado", false, (byte) 12),
    aberto("Aberto", true, (byte) 13),
    fechado("Fechado", false, (byte) 14),
    cancelado("Cancelado", false, (byte) 15),
    naorecebido("Não recebido", false, (byte) 16),
    naodevolvido("Não devolvido", false, (byte) 17);


    private final String status;
    private final boolean atividade;
    private final byte id;

    StatusEnum(String status, boolean atividade, byte id) {
        this.status = status;
        this.atividade = atividade;
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public byte getId() {
        return id;
    }

    public boolean isAtividade() {
        return atividade;
    }

    public static StatusEnum getById(byte id) {
        for (StatusEnum status : values()) {
            if (status.getId() == id) {
                return status;
            }
        }
        return null;
    }
}
