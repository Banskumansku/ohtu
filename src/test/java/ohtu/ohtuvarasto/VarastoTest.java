package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void noNegativeVarasto() {
        Varasto varasto1 = new Varasto(-123123);
        assertEquals(0.0, varasto1.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void noNegativeSaldoVarasto() {
        Varasto var = new Varasto(-123, -123);
        assertEquals(0.0, var.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, var.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void nonegativeMaara() {
        varasto.lisaaVarastoon(-12);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void noMoreTakenThanPossible() {
        varasto.lisaaVarastoon(10);
        double maara = varasto.otaVarastosta(123123);
        assertEquals(10, maara, vertailuTarkkuus);
    }

    @Test
    public void rightPrint() {
        String out = varasto.toString();
        assertEquals("saldo = 0.0, vielä tilaa 10.0", out);
    }

    @Test
    public void eiylitayttoa() {
        varasto.lisaaVarastoon(123213);
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void varastoTakaaSaldot() {
        Varasto var = new Varasto(1, 13);
        assertEquals(1, var.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void oikeamaaravarasto() {
        Varasto var = new Varasto(10, 1);
        assertEquals(8, var.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void rosvoryovariottaa() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(-12312);
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }
}