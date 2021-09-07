package PageObjects;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.junit.Assert;
import org.openqa.selenium.support.FindBy;

import java.io.File;
import java.util.List;

@DefaultUrl("https://automation-wappi.vercel.app/login")
public class JikkoSoftPageObjects extends PageObject {

    @FindBy(id = "username")
    WebElementFacade userBox;

    @FindBy(id = "password")
    WebElementFacade passBox;

    @FindBy(id = "button-login")
    WebElementFacade loginBtn;

    @FindBy(xpath = "//a[text()='Información personal']")
    WebElementFacade informacionPersonalLabel;

    @FindBy(xpath = "//input[@id='image']")
    WebElementFacade imagen;

    @FindBy(id = "name")
    WebElementFacade nombre;

    @FindBy(id = "lastName")
    WebElementFacade apellido;

    @FindBy(id = "bornDate")
    WebElementFacade fechaNacimiento;

    @FindBy(id = "country")
    WebElementFacade pais;

    @FindBy(id = "save-profile")
    WebElementFacade guardar;

    @FindBy(id = "welcome-coupon")
    WebElementFacade obtenerCupon;

    @FindBy(xpath = "//div[@class='modal opened']//following::p")
    WebElementFacade confirmacionModal;

    @FindBy(xpath = "//div[@id='confirmation-modal']//child::span")
    WebElementFacade cerrarConfirmacionDePedido;

    @FindBy(xpath = "//div[@id='coupon-modal']//child::span[@id='coupon-code']")
    WebElementFacade modalConCuponCode;

    @FindBy(xpath = "//input[@id='coupon']")
    WebElementFacade casillaParaAlmacenarCupon;

    @FindBy(xpath = "//div[@id='offer-modal']//child::button")
    WebElementFacade botonConfirmarPedido;

    @FindBy(xpath = "//div[@id='coupon-modal']//child::span[text()='×']")
    WebElementFacade cerrarCuponPopUp;

    @FindBy(xpath = "//tr[@id='coupon-0']//child::td[4]")
    WebElementFacade cantidadDeCupon;

    @FindBy(id = "e-bornDate")
    WebElementFacade mensajeDeErrorFechaNacimientoEsObligatoria;

    @FindBy(id ="e-coupon")
    WebElementFacade mensajeDeErrorCuponInvalido;

    @FindBy(id = "order-0")
    WebElementFacade pedido;


    public void seleccionarCategoria(String categoria) {
        WebElementFacade elem = $("//a[text()='" + categoria + "']");
        elem.click();
    }

    public void seleccionarSexo(String sexo) {
        WebElementFacade sex = $("//input[@id='" + sexo + "']");
        sex.click();
    }

    public void seleccionarBotonPedirSegunPosicionDelProducto(int posicionDelProducto) {
        WebElementFacade botonPedir = $("//tr[@id='offer-" + posicionDelProducto + "']//child::button");
        botonPedir.click();
    }

    public String cuponCode() {
        obtenerCupon.click();
        String cupon = modalConCuponCode.getText();
        cerrarCuponPopUp.click();
        return cupon;
    }

    public void iniciarSesion(String user, String pass) {
        userBox.type(user);
        passBox.type(pass);
        loginBtn.click();
    }

    public void actualizarInformacionPersonal(List<String> informacionPersonal) {
        seleccionarCategoria("Información personal");
        File file = new File(informacionPersonal.get(0));
        imagen.sendKeys(file.getAbsolutePath());
        nombre.type(informacionPersonal.get(1));
        apellido.type(informacionPersonal.get(2));
        fechaNacimiento.type(informacionPersonal.get(3));
        seleccionarSexo(informacionPersonal.get(4));
        pais.selectByVisibleText(informacionPersonal.get(5));
        guardar.click();
    }

    public void guardarAndVerificarCamposObligatoriosEnPerfil() {
        if (mensajeDeErrorFechaNacimientoEsObligatoria.isDisplayed()) {
            System.out.println("LOS CAMPOS OBLIGATORIOS ESTAN SIENDO REQUERIDOS");
        } else {
            Assert.fail("LOS CAMPOS OBLIGATORIOS SE PUEDEN GUARDAR VACIOS");
        }
    }

    public void seleccionarProductoConCupon(String producto) {
        String cupon = cuponCode();
        seleccionarCategoria("Inicio");
        seleccionarBotonPedirSegunPosicionDelProducto(1);
        verificarProductosEnModalResumen(producto);
        casillaParaAlmacenarCupon.type(cupon);
        botonConfirmarPedido.click();
    }

    public void seleccionarProductoConCuponIncorrecto(String producto) {
        String cupon = "AKSFDHSAKJHSADKJF";
        seleccionarCategoria("Inicio");
        seleccionarBotonPedirSegunPosicionDelProducto(1);
        verificarProductosEnModalResumen(producto);
        casillaParaAlmacenarCupon.type(cupon);
        botonConfirmarPedido.click();
    }

    public void verificarCupon(){
        if (mensajeDeErrorCuponInvalido.isDisplayed()){
            System.out.println("TEST PASSED, SE ESTAN VALIDANDO LOS CUPONES");
        } else {
            Assert.fail("CUPONES INVALIDOS ESTAN SIENDO ACEPTADOS");
        }
    }

    public void seleccionarProductoSinCupon(String producto) {
        seleccionarBotonPedirSegunPosicionDelProducto(0);
        verificarProductosEnModalResumen(producto);
        botonConfirmarPedido.click();
    }

    public void verficarModalDeConfirmacion(String textoParaVerificar) {
        if (confirmacionModal.getText().equals(textoParaVerificar)) {
            System.out.println("TESS PASSED");
        } else {
            Assert.fail("TESS FAILED, EL POPUP NO SE MOSTRÓ");
        }
    }

    public void verificarProductosEnModalResumen(String prod) {
        WebElementFacade producto = $("//div[@class='offer-main-info']//child::span[text()='" + prod + "']");
        if (producto.isDisplayed()) {
            System.out.println("EL PRODUCTO EN EL RESUMEN ES IGUAL AL ELEGIDO");
        } else {
            Assert.fail("EL PRODUCTO EN EL RESUMEN NO ES IGUAL AL ELEGIDO");
        }
    }

    public void verificarPedido(){
        cerrarConfirmacionDePedido.click();
        seleccionarCategoria("Mis pedidos");
        if(pedido.isPresent()){
            System.out.println("EL PEDIDO SE GUARDO CORRECTAMENTE");
        } else {
            Assert.fail("LOS PEDIDOS NO SE ESTAN GUARDANDO");
        }
    }

    public void verificarQueElCuponDisminuye(){
        seleccionarCategoria("Cupones");
        if (cantidadDeCupon.getText().equals("1")){
            System.out.println("LOS CUPÓNES DISMINUYEN CUANDO SE USAN");
        } else {
            Assert.fail("LOS CUPONES NO ESTAN DISMINUYENDO POR CADA USO");
        }
    }


}
