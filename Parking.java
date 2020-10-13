/**
 * La clase representa a un parking de una ciudad europea
 * que dispone de dos tarifas de aparcamiento para los clientes
 * que lo usen: la tarifa regular (que incluye una tarifa plana para
 * entradas "tempranas") y la tarifa comercial para clientes que trabajan
 * cerca del parking, aparcan un nº elevado de horas y se benefician de esta 
 * tarifa más económica
 * (leer enunciado)
 * 
 * @author Saúl Layos Iriso
 * 
 */
public class Parking
{
    private String nombre;
    private int cliente;
    private double importeTotal;
    private int regular;
    private int comercial;
    private int clientesLunes;
    private int clientesSabado;
    private int clientesDomingo;
    private int clienteMaximoComercial;
    private double importeMaximoComercial;

    private final char REGULAR = 'R';
    private final char COMERCIAL = 'C';
    private final double PRECIO_BASE_REGULAR = 2.0;
    private final double PRECIO_MEDIA_REGULAR_HASTA11 = 3.0;
    private final double PRECIO_MEDIA_REGULAR_DESPUES11 = 5.0;
    private final double HORA_INICIO_ENTRADA_TEMPRANA = 6 * 60;
    private final double HORA_FIN_ENTRADA_TEMPRANA = 8 * 60 + 30;
    private final double HORA_INICIO_SALIDA_TEMPRANA = 15 * 60;
    private final double HORA_FIN_SALIDA_TEMPRANA = 18 * 60;
    private final double PRECIO_TARIFA_PLANA_REGULAR = 15.0;
    private final double PRECIO_PRIMERAS3_COMERCIAL = 5.0;
    private final double PRECIO_MEDIA_COMERCIAL = 3.0;

    /**
     * Inicializa el parking con el nombre indicada por el parámetro.
     * El resto de atributos se inicializan a 0 
     */
    public Parking(String queNombre) {

        /*constructor*/
        nombre = queNombre;
        cliente = 0;
        importeTotal = 0;
        importeMaximoComercial = 0;
        regular = 0;
        comercial= 0;
        clientesLunes = 0;
        clientesSabado = 0;
        clientesDomingo = 0;
        clienteMaximoComercial = 0;
    }

    /**
     * Accesor para el nombre del parking
     *  
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Mutador para el nombre del parking
     *  
     */
    public void setNombre(String queNombre) {
        nombre = queNombre;
    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    tipoTarifa - un carácter 'R' o 'C'
     *    entrada - hora de entrada al parking
     *    salida – hora de salida del parking
     *    dia – nº de día de la semana (un valor entre 1 y 7)
     *    
     *    A partir de estos parámetros el método debe calcular el importe
     *    a pagar por el cliente y mostrarlo en pantalla 
     *    y  actualizará adecuadamente el resto de atributos
     *    del parking para poder mostrar posteriormente (en otro método) las estadísticas
     *   
     *    Por simplicidad consideraremos que un cliente entra y sale en un mismo día
     *    
     *    (leer enunciado del ejercicio)
     */
    public void facturarCliente(char tipoTarifa, int entrada, int salida, int dia) {
        double precio = 0;
        cliente ++;

        if (dia == 1){
            clientesLunes ++;
        } else if (dia == 6){
            clientesSabado ++;
        } else if (dia == 7){
            clientesDomingo ++;
        }

        double entradaAux = (entrada/100)*60 + (entrada % 100);
        double salidaAux = salida/100*60 + salida%100;

        switch(tipoTarifa){
            case 'R':
            if ((entradaAux >= HORA_INICIO_ENTRADA_TEMPRANA && entradaAux <= HORA_FIN_ENTRADA_TEMPRANA) &&
            (salidaAux >= HORA_INICIO_SALIDA_TEMPRANA && salidaAux <= HORA_FIN_SALIDA_TEMPRANA)){

                precio = PRECIO_TARIFA_PLANA_REGULAR;
                
                System.out.println("####################################################################");
                System.out.println("Cliente nº: " + cliente);
                if((entrada % 100)<10){
                    System.out.println("Hora entrada: " + (entrada / 100) + ":" + "0" + (entrada % 100));
                }else{
                    System.out.println("Hora entrada: " + (entrada / 100) + ":" + (entrada % 100));
                }

                if((salida % 100)<10){
                    System.out.println("Hora salida: " + (salida / 100) + ":" + "0" + (salida % 100));
                }else{
                    System.out.println("Hora salida: " + (salida / 100) + ":" + (salida % 100));
                }
                System.out.println("Tarifa a aplicar: REGULAR Y TEMPRANA");
                System.out.println("Importe a pagar: " + precio + "€");
                System.out.println("####################################################################\n");
                importeTotal += precio;
                regular ++;
            }else{

                if (entradaAux <= 11*60 && salidaAux <= 11*60){
                    double minsAux = salidaAux - entradaAux;
                    int minsAux2 = (int)(minsAux/30);
                    precio = PRECIO_BASE_REGULAR + (PRECIO_MEDIA_REGULAR_HASTA11 * minsAux2);

                }else if (entradaAux >= 11*60 && salidaAux >= 11*60){
                    double minsAux = salidaAux - entradaAux;
                    int minsAux2 = (int)(minsAux/30);
                    precio = PRECIO_BASE_REGULAR + (PRECIO_MEDIA_REGULAR_DESPUES11 * minsAux2);
                }else if (entradaAux <= 11*60 && salidaAux >= 11*60){
                    double mins1 = 11*60 - entradaAux;
                    int minsAux1 = (int)(mins1/30);

                    double mins2 = salidaAux - 11*60;
                    int minsAux2 = (int)(mins2/30);

                    precio = PRECIO_BASE_REGULAR + (PRECIO_MEDIA_REGULAR_HASTA11 * minsAux1) +
                    (PRECIO_MEDIA_REGULAR_DESPUES11 * minsAux2);

                } else{
                    System.out.println("Caso no soportado");
                }

                
                System.out.println("####################################################################");
                System.out.println("Cliente nº: " + cliente);

                if((entrada % 100)<10){
                    System.out.println("Hora entrada: " + (entrada / 100) + ":" + "0" + (entrada % 100));
                }else{
                    System.out.println("Hora entrada: " + (entrada / 100) + ":" + (entrada % 100));
                }

                if((salida % 100)<10){
                    System.out.println("Hora salida: " + (salida / 100) + ":" + "0" + (salida % 100));
                }else{
                    System.out.println("Hora salida: " + (salida / 100) + ":" + (salida % 100));
                }

                System.out.println("Tarifa a aplicar: REGULAR");
                System.out.println("Importe a pagar: " + precio + "€");
               System.out.println("####################################################################\n");
                importeTotal += precio;
                regular++;

            }   
            break;

            case 'C':

            
            if((salidaAux - entradaAux)<=180){
                precio = PRECIO_PRIMERAS3_COMERCIAL;
            }else{

                int mediasHoras = (int)(((salidaAux - entradaAux)-180)/30);

                precio = PRECIO_PRIMERAS3_COMERCIAL + mediasHoras * PRECIO_MEDIA_COMERCIAL;
            }

            

            System.out.println("####################################################################");
            
            System.out.println("Cliente nº: " + cliente);

            if((entrada % 100)<10){
                System.out.println("Hora entrada: " + (entrada / 100) + ":" + "0" + (entrada % 100));
            }else{
                System.out.println("Hora entrada: " + (entrada / 100) + ":" + (entrada % 100));
            }

            if((salida % 100)<10){
                System.out.println("Hora salida: " + (salida / 100) + ":" + "0" + (salida % 100));
            }else{
                System.out.println("Hora salida: " + (salida / 100) + ":" + (salida % 100));
            }

            System.out.println("Tarifa a aplicar: COMERCIAL");
            System.out.println("Importe a pagar: " + precio + "€");
            System.out.println("####################################################################\n");

            if (precio > importeMaximoComercial){
                importeMaximoComercial = precio;
                clienteMaximoComercial = cliente;
            }

            importeTotal += precio;
            comercial++;
            break;
        }

    }

    /**
     * Muestra en pantalla las estadísticcas sobre el parking  
     *   
     * (leer enunciado)
     *  
     *
     **/

    public void printEstadísticas(){
        System.out.println("####################################################################");
        System.out.println("Importe total entre todos los clientes: " + importeTotal + "€");
        System.out.println("Nº clientes tarifa regular: " + regular);
        System.out.println("Nº clientes tarifa comercial: " + comercial);
        System.out.println("Cliente tarifa COMERCIAL con factura máxima fue el nº " + clienteMaximoComercial);
        System.out.println("y pagó " + importeMaximoComercial + "€");
        System.out.println("####################################################################\n");
    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que más clientes han utilizado el parking - "SÁBADO"   "DOMINGO" o  "LUNES"
     */
    public String diaMayorNumeroClientes() { //public void diaMayorNumeroClientes(){
        if(clientesLunes > clientesSabado && clientesLunes > clientesDomingo)
        {
            return "LUNES";//System.out.println("Lunes: " + clientesLunes);
        }
        else if(clientesSabado > clientesDomingo)
        {
            return "SABADO";//System.out.println("Sabado: "+ clientesSabado);
        }
        else
        {
            return "DOMINGO";//System.out.println("Domingo:"+ clientesDomingo);
        }
    }
}
 