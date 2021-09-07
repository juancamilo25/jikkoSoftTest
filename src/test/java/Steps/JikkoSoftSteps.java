package Steps;

import PageObjects.JikkoSoftPageObjects;
import net.thucydides.core.annotations.Step;

import java.util.List;

public class JikkoSoftSteps {

    JikkoSoftPageObjects jikkoSoftPageObjects;

    @Step
    public void abrirPaginaYLoguearse(String user, String pass){
        jikkoSoftPageObjects.open();
        jikkoSoftPageObjects.iniciarSesion(user, pass);
    }

    @Step
    public void actualizarInformacionPersonal(List<String> informacionPersonal){
       jikkoSoftPageObjects.actualizarInformacionPersonal(informacionPersonal);
    }

    @Step
    public void verificarModalDeConfirmacion(String textoParaVerificar){
        jikkoSoftPageObjects.verficarModalDeConfirmacion(textoParaVerificar);
    }

    @Step
    public void realizarPedidoConCupon(String producto){
        jikkoSoftPageObjects.seleccionarProductoConCupon(producto);
    }

    @Step
    public void realizarPedidoConCuponIncorrecto(String producto){
        jikkoSoftPageObjects.seleccionarProductoConCuponIncorrecto(producto);
    }

    @Step
    public void realizarPedidoSinCupon(String producto){
        jikkoSoftPageObjects.seleccionarProductoSinCupon(producto);
    }

    @Step
    public void verificarCamposObligatorios(){
        jikkoSoftPageObjects.guardarAndVerificarCamposObligatoriosEnPerfil();
    }

    @Step
    public void verificarCupon(){
        jikkoSoftPageObjects.verificarCupon();
    }

    @Step
    public void verificarPedido(){
        jikkoSoftPageObjects.verificarPedido();
    }

    @Step
    public void verificarQueElCuponDisminuye(){
        jikkoSoftPageObjects.verificarQueElCuponDisminuye();
    }
}
