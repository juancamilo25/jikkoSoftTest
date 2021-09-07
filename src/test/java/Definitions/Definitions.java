package Definitions;

import Steps.JikkoSoftSteps;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import java.util.List;

public class Definitions {

    @Steps
    JikkoSoftSteps jikkoSoftSteps;

    @Given("^el usuario ingresa al aplicativo y se loguea con su (.*) y (.*)$")
    public void elUsuarioIngresaAlAplicativoYSeLoguea(String user, String pass) {
        jikkoSoftSteps.abrirPaginaYLoguearse(user, pass);
    }

    @When("^el usuario actualiza su informacion con los siguientes datos (.*)$")
    public void elUsuarioActualizaSuInformacionConLosSiguientesDatosSrcTestResourcesDataDrivenTestImageJpgJuanCamiloAlvarezColombia(List<String> informacionPersonal) {
        jikkoSoftSteps.actualizarInformacionPersonal(informacionPersonal);
    }

    @Then("^el usuario debe ver un popUp con el siguiente (.*) que le indica que su informacion fue actulizada correctamente\\.$")
    public void elUsuarioDebeVerUnPopUpConElSiguienteQueLeIndicaQueSuInformacionFueActulizadaCorrectamente(String textoParaVerificar) {
        jikkoSoftSteps.verificarModalDeConfirmacion(textoParaVerificar);
    }

    @When("^el usuario obtiene un cupon y realiza un pedido del siguiente (.*)$")
    public void elUsuarioObtieneUnCuponYRealizaUnPedidoDelSiguiente(String producto) {
        jikkoSoftSteps.realizarPedidoConCupon(producto);
    }

    @Then("^el usuario debe ver un popUp con el siguiente (.*) que le indica que su pedido fue exitoso\\.$")
    public void elUsuarioDebeVerUnPopUpConElSiguienteMensajeQueLeIndicaQueSuPedidoFueExitoso(String textoParaVerificar ) {
        jikkoSoftSteps.verificarModalDeConfirmacion(textoParaVerificar);
    }

    @When("^el usuario realiza un pedido del siguiente (.*) sin cupon$")
    public void elUsuarioRealizaUnPedidoDelSiguienteSinCupon(String producto) {
        jikkoSoftSteps.realizarPedidoSinCupon(producto);
    }

    @Then("^el usuario debe ver las advertencias de los campos obligatorios$")
    public void elUsuarioDebeVerLasAdvertenciasDeLosCamposObligatorios() {
        jikkoSoftSteps.verificarCamposObligatorios();
    }


    @When("^el usuario obtiene un cupon incorrecto y realiza un pedido del siguiente (.*)$")
    public void elUsuarioObtieneUnCuponIncorrectoYRealizaUnPedidoDelSiguienteProducto(String producto) {
        jikkoSoftSteps.realizarPedidoConCuponIncorrecto(producto);
    }

    @Then("^el usuario debe ver un mensaje que le indica que el cupon es incorrecto$")
    public void elUsuarioDebeVerUnMensajeQueLeIndicaQueElCuponEsIncorrecto() {
        jikkoSoftSteps.verificarCupon();
    }

    @And("^verificar el pedido y que el cupon disminuye$")
    public void verificarElPedidoYQueElCuponDisminuye() {
        jikkoSoftSteps.verificarPedido();
        jikkoSoftSteps.verificarQueElCuponDisminuye();
    }
}
