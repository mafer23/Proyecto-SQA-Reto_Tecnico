package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class AbrirNavegador implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {

        // Crear y asignar WebDriver al actor
        WebDriver driver = new ChromeDriver();
        BrowseTheWeb.as(actor).setDriver(driver);

        // Maximizar ventana
        driver.manage().window().maximize();

        // Abrir URL
        actor.attemptsTo(
                Open.url("https://jqueryui.com/datepicker/")
        );
    }

    public static AbrirNavegador abrirWeb() {
        return instrumented(AbrirNavegador.class);
    }
}
