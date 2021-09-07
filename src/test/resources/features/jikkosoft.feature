# Author: Juan Camilo Alvarez Barrios

@JikkoTest
Feature: JikkoSoft Test

  @ActualizacionDeDatos
  Scenario Outline: El usuario quiere actualizar su informacion.
    Given el usuario ingresa al aplicativo y se loguea con su <user> y <pass>
    When el usuario actualiza su informacion con los siguientes datos <imagen> , <nombre> , <apellido> , <fechaNacimiento>, <sexo>, <pais>
    Then el usuario debe ver un popUp con el siguiente <mensaje> que le indica que su informacion fue actulizada correctamente.
    Examples:
      | user       | pass       | imagen                                      | nombre      | apellido | fechaNacimiento | sexo | pais      | mensaje                                |
      | JuanCamilo | JuanCamilo | src\test\resources\dataDriven\testImage.jpg | Juan Camilo | Alvarez  | 25/09/1990      | M    | Colombia  | Tu información se guardó correctamente |


  @VerificarCamposObligatorios
  Scenario Outline: Verificar campos Obligatorios.
    Given el usuario ingresa al aplicativo y se loguea con su <user> y <pass>
    When el usuario actualiza su informacion con los siguientes datos <imagen> , <nombre> , <apellido> , <fechaNacimiento>, <sexo>, <pais>
    Then el usuario debe ver las advertencias de los campos obligatorios
    Examples:
      | user   | pass       | imagen                                      | nombre  | apellido | fechaNacimiento | sexo | pais      |
      | Andrea | Andrea0125 | src\test\resources\dataDriven\testImage.jpg | Armando | Lopz     |                 | M    | Argentina |

  @RealizarCompraConCupon
  Scenario Outline: El usuario quiere realizar una compra con cupon
    Given el usuario ingresa al aplicativo y se loguea con su <user> y <pass>
    When el usuario obtiene un cupon y realiza un pedido del siguiente <producto>
    Then el usuario debe ver un popUp con el siguiente <mensaje> que le indica que su pedido fue exitoso.
    And verificar el pedido y que el cupon disminuye
    Examples:
      | user       | pass       | producto   | mensaje                                                                       |
      | JuanCamilo | JuanCamilo | Pan tajado | Tu pedido ha sido confirmado, te mantendremos informado ante nuevas novedades |

  @RealizarCompraConCuponIncorrecto
  Scenario Outline: verificar cupon incorrecto
    Given el usuario ingresa al aplicativo y se loguea con su <user> y <pass>
    When el usuario obtiene un cupon incorrecto y realiza un pedido del siguiente <producto>
    Then el usuario debe ver un mensaje que le indica que el cupon es incorrecto
    Examples:
      | user       | pass       | producto   |
      | JuanCamilo | JuanCamilo | Pan tajado |

  @RealizarCompraSinCupon
  Scenario Outline: El usuario quiere realizar una compra sin cupon
    Given el usuario ingresa al aplicativo y se loguea con su <user> y <pass>
    When el usuario realiza un pedido del siguiente <producto> sin cupon
    Then el usuario debe ver un popUp con el siguiente <mensaje> que le indica que su pedido fue exitoso.
    Examples:
      | user       | pass       | producto          | mensaje                                                                       |
      | JuanCamilo | JuanCamilo | Aceite de Girasol | Tu pedido ha sido confirmado, te mantendremos informado ante nuevas novedades |



