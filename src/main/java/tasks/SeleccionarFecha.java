package tasks;

import interactions.SwitchToDefault;
import interactions.SwitchToIframe;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import ui.DatepickerPage;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SeleccionarFecha implements Task {

    private final String dia;
    private final String mes;
    private final String año;

    public SeleccionarFecha(String dia, String mes, String año) {
        this.dia = dia;
        this.mes = mes;
        this.año = año;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        //  Entrar al iframe
        actor.attemptsTo(
                SwitchToIframe.usando(DatepickerPage.IFRAME_DEMO.resolveFor(actor))
        );

        // Abrir el calendario
        actor.attemptsTo(
                Click.on(DatepickerPage.CAMPO_FECHA)
        );

        // Navegar hasta el mes y año deseado
        while (true) {
            String monthShown = DatepickerPage.ETIQUETA_MES.resolveFor(actor).getText();
            String yearShown = DatepickerPage.ETIQUETA_ANO.resolveFor(actor).getText();

            if (monthShown.equals(mes) && yearShown.equals(año)) {
                break;
            }

            actor.attemptsTo(
                    Click.on(DatepickerPage.BOTON_SIGUIENTE)
            );
        }

        // Seleccionar el día
        actor.attemptsTo(
                Click.on(DatepickerPage.DIA(dia))
        );

        // Salir del iframe
        actor.attemptsTo(
                SwitchToDefault.salir()
        );
    }

    public static SeleccionarFecha en(String dia, String mes, String año) {
        return instrumented(SeleccionarFecha.class, dia, mes, año);
    }
}
