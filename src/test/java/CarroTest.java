import dao.*;
import domain.Acessorio;
import domain.Carro;
import domain.Marca;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CarroTest {


        private ICarroDao carroDao;
        private IMarcaDao marcaDao;
        private IAcessorioDao acessorioDao;

        @Before
        public void inicializar() {
            // Inicialização dos DAOs ou mocks necessários
            carroDao = new CarroDao();
            marcaDao = new MarcaDao();
            acessorioDao = new AcessorioDao();
        }

        @After
        public void limparCarros() {
            // Lógica para limpar os carros após cada teste
            List<Carro> carros = carroDao.listarCarros();
            for (Carro carro : carros) {
                carroDao.deletarCarro(carro.getId());
            }
        }

        @Test
        public void salvarECarregarCarro() {
            // Criação de uma marca para associar ao carro
            Marca marca = new Marca();
            marca.setNome("Chevrolet");
            marcaDao.salvarMarca(marca);

            // Criação e salvamento de um carro
            Carro carro = new Carro();
            carro.setMarca(marca);
            carro.setModelo("Cruze");

            carroDao.salvarCarro(carro);

            // Verificação se o carro foi salvo corretamente e pode ser carregado pelo ID
            assertNotNull(carro);
            assertNotNull(carro.getId());

            Carro carroSalvo = carroDao.encontrarCarroPorId(carro.getId());

            assertNotNull(carroSalvo);
            assertEquals("Cruze", carroSalvo.getModelo());
            assertNotNull(carroSalvo.getMarca());
            assertEquals("Chevrolet", carroSalvo.getMarca().getNome());
        }

        @Test
        public void associarAcessoriosAoCarro() {
            // Criação e salvamento de acessórios
            Acessorio acessorio1 = new Acessorio();
            acessorio1.setNome("Ar Condicionado");
            acessorioDao.salvarAcessorio(acessorio1);

            Acessorio acessorio2 = new Acessorio();
            acessorio2.setNome("Teto Solar");
            acessorioDao.salvarAcessorio(acessorio2);

            // Criação e salvamento de um carro
            Carro carro = new Carro();
            carro.setModelo("Golf");

            List<Acessorio> acessorios = new ArrayList<>();
            acessorios.add(acessorio1);
            acessorios.add(acessorio2);

            carro.setAcessorios(acessorios);

            carroDao.salvarCarro(carro);

            // Verificação se os acessórios foram associados corretamente ao carro
            assertNotNull(carro);
            assertNotNull(carro.getId());
            assertNotNull(carro.getAcessorios());
            assertEquals(2, carro.getAcessorios().size());
        }


    }
