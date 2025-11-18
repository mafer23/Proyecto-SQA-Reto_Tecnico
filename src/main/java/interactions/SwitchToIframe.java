package interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SwitchToIframe implements Interaction {

    private final WebElement iframe;

    public SwitchToIframe(WebElement iframe) {
        this.iframe = iframe;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.switchTo().frame(iframe);
    }

    public static SwitchToIframe usando(WebElement iframe) {
        return instrumented(SwitchToIframe.class, iframe);
    }
}
