package br.com.pontofacil.pontofacilapi.enums;

public enum PlanoEmpresa {

    FREE(5, 1000),
    PRO(Integer.MAX_VALUE, Integer.MAX_VALUE);

    private final int maxUsuarios;
    private final int maxRegistrosMes;

    PlanoEmpresa(int maxUsuarios, int maxRegistrosMes) {
        this.maxUsuarios = maxUsuarios;
        this.maxRegistrosMes = maxRegistrosMes;
    }

    public int getMaxUsuarios() {
        return maxUsuarios;
    }

    public int getMaxRegistrosMes() {
        return maxRegistrosMes;
    }
}
