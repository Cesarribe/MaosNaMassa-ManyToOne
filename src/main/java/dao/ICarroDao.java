package dao;

import domain.Carro;

import java.util.List;

public interface ICarroDao {
    void salvarCarro(Carro carro);
    void atualizarCarro(Carro carro);
    void deletarCarro(int id);
    Carro encontrarCarroPorId(int id);
    List<Carro> listarCarros();
    List<Carro> listarCarrosPorMarca(int idMarca);
}
