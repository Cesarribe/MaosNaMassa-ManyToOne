import dao.AcessorioDao;
import dao.IAcessorioDao;
import domain.Acessorio;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AcessorioTest {

    private IAcessorioDao acessorioDao;

    @Before
    public void inicializar() {
        // Inicialização do DAO ou mock necessário
        acessorioDao = new AcessorioDao();
    }

    @After
    public void limparAcessorios() {
        if (acessorioDao != null) {
            List<Acessorio> acessorios = acessorioDao.listarAcessorios();
            if (acessorios != null) {
                for (Acessorio acessorio : acessorios) {
                    acessorioDao.deletarAcessorio(acessorio.getId());
                }
            }
        }
    }

    @Test
    public void salvarEAcessarAcessorio() {
        // Criação e salvamento de um acessório
        Acessorio acessorio = new Acessorio();
        acessorio.setNome("Sensor de Estacionamento");

        acessorioDao.salvarAcessorio(acessorio);

        // Verificação se o acessório foi salvo corretamente e pode ser carregado pelo ID
        assertNotNull(acessorio);
        assertNotNull(acessorio.getId());

        Acessorio acessorioSalvo = acessorioDao.encontrarAcessorioPorId(acessorio.getId());

        assertNotNull(acessorioSalvo);
        assertEquals("Sensor de Estacionamento", acessorioSalvo.getNome());
    }

    @Test
    public void deletarAcessorio() {
        // Criação e salvamento de um acessório
        Acessorio acessorio = new Acessorio();
        acessorio.setNome("Retrovisores Elétricos");

        acessorioDao.salvarAcessorio(acessorio);

        // Deleção do acessório e verificação se foi removido corretamente
        acessorioDao.deletarAcessorio(acessorio.getId());

        Acessorio acessorioDeletado = acessorioDao.encontrarAcessorioPorId(acessorio.getId());

        assertNull(acessorioDeletado);
    }

}
