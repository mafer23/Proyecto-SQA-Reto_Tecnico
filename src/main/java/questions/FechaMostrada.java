package questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FechaMostrada implements Question<String> {

    public static FechaMostrada enElCampo() {
        return new FechaMostrada();
    }

    @Override
    public String answeredBy(Actor actor) {

        WebDriver driver = BrowseTheWeb.as(actor).getDriver();

        // Identificar iframe correcto
        WebElement iframe = driver.findElement(By.cssSelector(".demo-frame"));

        // Cambiar al iframe solo si NO estamos ya dentro
        try {
            driver.switchTo().frame(iframe);
        } catch (Exception ignored) { }

        // Obtener el valor del campo datepicker
        String valor = driver.findElement(By.id("datepicker"))
                .getAttribute("value");

        // Regresar al DOM principal
        driver.switchTo().defaultContent();

        return valor;
    }
}
