package dao;

import domain.Marca;

import java.util.List;

public interface IMarcaDao {
    void salvarMarca(Marca marca);
    void atualizarMarca(Marca marca);
    void deletarMarca(Long id);
    Marca encontrarMarcaPorId(Long id);
    List<Marca> listarMarcas();
}
