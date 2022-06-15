package dataFactory;

import pojo.UsuarioPojo;

public class UsuarioDataFactory {
    public static UsuarioPojo criarUsuarioAdm(){

        UsuarioPojo usuarioPojo = new UsuarioPojo();
        usuarioPojo.setUsuarioLogin("admin");
        usuarioPojo.setUsuarioSenha("admin");

        return usuarioPojo;
    }
}
