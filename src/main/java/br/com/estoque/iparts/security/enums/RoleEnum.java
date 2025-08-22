package br.com.estoque.iparts.security.enums;

public enum RoleEnum {
        ROLE_USER("Role Usuário", 1),
        ROLE_ADMIN("Role Administrador", 2),
        ROLE_MASTER("Role Master", 3);
        private String tipo;
        private int codigo;
    
    private RoleEnum(String tipo, int codigo) {
        this.tipo = tipo;
        this.codigo = codigo;
    }
    
    private RoleEnum(int codigo) {
        this.codigo = codigo;
    }
    
    public int getCodigo () {
        return codigo;
    }
    
    public String getTipo () {
        return tipo;
    }
    
}