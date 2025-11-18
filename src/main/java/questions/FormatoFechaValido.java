package questions;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class FormatoFechaValido implements Question<Boolean> {

    public static FormatoFechaValido esCorrecto() {
        return new FormatoFechaValido();
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Cambiar al iframe del datepicker
        WebElement iframe = driver.findElement(By.cssSelector(".demo-frame"));
        driver.switchTo().frame(iframe);

        // Esperar a que el input sea visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement inputFecha = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("datepicker"))
        );

        String fecha = inputFecha.getAttribute("value");

        // Volver al contexto principal
        driver.switchTo().defaultContent();

        // Validar formato DD/MM/YYYY
        return fecha != null && fecha.matches("^\\d{2}/\\d{2}/\\d{4}$");
    }
}
