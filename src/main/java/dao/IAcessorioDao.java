package dao;

import domain.Acessorio;

import java.util.List;

public interface IAcessorioDao {
    void salvarAcessorio(Acessorio acessorio);
    void deletarAcessorio(int id);
    Acessorio encontrarAcessorioPorId(int id);

    List<Acessorio> listarAcessorios();
}
