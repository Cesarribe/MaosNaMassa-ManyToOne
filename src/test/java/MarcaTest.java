import dao.IMarcaDao;
import dao.MarcaDao;
import domain.Marca;
import org.junit.After;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MarcaTest {

        private IMarcaDao marcaDAO;

        public MarcaTest() {
            marcaDAO = new MarcaDao();
        }

        @After
        public void limparMarcas() {
            List<Marca> marcas = marcaDAO.listarMarcas();
            for (Marca marca : marcas) {
                marcaDAO.deletarMarca(marca.getId());
            }
        }

        @Test
        public void cadastrarMarca() {
            Marca marca = new Marca();
            marca.setNome("Marca de Teste");

            marcaDAO.salvarMarca(marca);

            assertNotNull(marca.getId());
        }

        @Test
        public void encontrarMarcaPorId() {
            Marca marca = new Marca();
            marca.setNome("Marca de Teste");

            marcaDAO.salvarMarca(marca);

            Marca marcaEncontrada = marcaDAO.encontrarMarcaPorId(marca.getId());

            assertNotNull(marcaEncontrada);
            assertEquals(marca.getNome(), marcaEncontrada.getNome());
        }
    }

