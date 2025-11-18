package ui;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DatepickerPage {


    public static final Target IFRAME_DEMO = Target.the("iframe del datepicker")
            .located(By.cssSelector(".demo-frame"));


    public static final Target CAMPO_FECHA = Target.the("campo de fecha")
            .located(By.id("datepicker"));


    public static final Target BOTON_SIGUIENTE = Target.the("botón siguiente mes")
            .located(By.className("ui-datepicker-next"));


    public static final Target ETIQUETA_MES = Target.the("etiqueta del mes visible")
            .located(By.className("ui-datepicker-month"));


    public static final Target ETIQUETA_ANO = Target.the("etiqueta del año visible")
            .located(By.className("ui-datepicker-year"));

    public static Target DIA(String dia) {
        return Target.the("día " + dia + " del calendario")
                .located(By.xpath("//a[text()='" + dia + "']"));
    }
}