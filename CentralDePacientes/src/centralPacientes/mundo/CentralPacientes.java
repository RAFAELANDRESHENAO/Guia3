/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad Ean (Bogot� - Colombia)
 * Departamento de Tecnolog�as de la Informaci�n y Comunicaciones
 * Licenciado bajo el esquema Academic Free License version 2.1
 * <p>
 * Proyecto Central de Pacientes.
 * Adaptado de CUPI2 (Uniandes)
 * Fecha: Febrero 2021
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package centralPacientes.mundo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Esta clase representa una central en la que se maneja una lista de pacientes
 */
public class CentralPacientes {
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Lista de pacientes
     */
    private ArrayList<Paciente> pacientes;

    /**
     * Vector de cl�nicas manejadas por la central
     */
    private ArrayList<String> listaClinicas;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea una nueva central sin pacientes y con una lista predefinida de cl�nicas
     */
    public CentralPacientes() {
        pacientes = new ArrayList<>();

        listaClinicas = new ArrayList<>();
        listaClinicas.add("Cl�nica del Country");
        listaClinicas.add("Cl�nica Palermo");
        listaClinicas.add("Cl�nica Reina Sof�a");
        listaClinicas.add("Cl�nica El Bosque");
        listaClinicas.add("Cl�nica San Ignacio");
        listaClinicas.add("Otra");
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Retorna el n�mero de pacientes de la cl�nica
     *
     * @return El n�mero de pacientes de la cl�nica
     */
    public int darNumeroPacientes() {
        return pacientes.size();
    }

    /**
     * Adiciona un paciente al principio de la lista
     *
     * @param pac El paciente a ser agregado al comienzo de la lista. <br>
     *            pac!=null y no existe un paciente con c�digo igual a pac.codigo
     */
    public void agregarPacienteAlComienzo(Paciente pac) {
        if (pac != null && localizar(pac.darCodigo()) == null) {
            pacientes.add(0, pac);
        }
    }

    /**
     * Adiciona un paciente al final de la lista. Si la lista est� vac�a el paciente queda de primero
     *
     * @param pac El paciente a ser agregado al final la lista. <br>
     *            pac!=null y no existe un paciente con c�digo igual a pac.codigo
     */
    public void agregarPacienteAlFinal(Paciente pac) {
        if (pac != null && localizar(pac.darCodigo()) == null) {
            pacientes.add(pac);
        }
    }

    /**
     * Adiciona un paciente a la lista de pacientes antes del paciente con el c�digo especificado. <br>
     */
    public void agregarPacienteAntesDe(int cod, Paciente pac) throws NoExisteException {
        Paciente pacienteRef = localizar(cod);
        if (pacienteRef == null) {
            throw new NoExisteException(cod);
        }
        if (pac != null && localizar(pac.darCodigo()) == null) {
            int index = pacientes.indexOf(pacienteRef);
            pacientes.add(index, pac);
        }
    }

    /**
     * Adiciona un paciente a la lista de pacientes despu�s del paciente con el c�digo especificado.
     */
    public void agregarPacienteDespuesDe(int cod, Paciente pac) throws NoExisteException {
        Paciente pacienteRef = localizar(cod);
        if (pacienteRef == null) {
            throw new NoExisteException(cod);
        }
        if (pac != null && localizar(pac.darCodigo()) == null) {
            int index = pacientes.indexOf(pacienteRef);
            pacientes.add(index + 1, pac);
        }
    }

    /**
     * Busca el paciente con el c�digo dado en la lista de pacientes.
     */
    public Paciente localizar(int codigo) {
        for (Paciente paciente : pacientes) {
            if (paciente.darCodigo() == codigo) {
                return paciente;
            }
        }
        return null;
    }

    /**
     * Elimina el paciente con el c�digo especificado.
     */
    public void eliminarPaciente(int cod) throws NoExisteException {
        Paciente paciente = localizar(cod);
        if (paciente == null) {
            throw new NoExisteException(cod);
        }
        pacientes.remove(paciente);
    }

    /**
     * Devuelve una lista con los pacientes de la central
     */
    public ArrayList<Paciente> darPacientes() {
        return pacientes;
    }

    /**
     * Retorna la lista de cl�nicas manejadas por la central
     */
    public ArrayList<String> darListaClinicas() {
        return listaClinicas;
    }

    /**
     * Retorna la longitud de la lista
     */
    private int darLongitud() {
        return pacientes.size();
    }

    // -----------------------------------------------------------------
    // Puntos de Extensi�n
    // -----------------------------------------------------------------

    /**
     * Retorna la cantidad de hombres que hay en la lista
     */
    public int cantHombres() {
        int count = 0;
        for (Paciente paciente : pacientes) {
            if (paciente.darSexo() == Paciente.HOMBRE) {
                count++;
            }
        }
        return count;
    }

    /**
     * Retorna la cantidad de mujeres que hay en la lista
     */
    public int cantMujeres() {
        int count = 0;
        for (Paciente paciente : pacientes) {
            if (paciente.darSexo() == Paciente.MUJER) {
                count++;
            }
        }
        return count;
    }

    /**
     * De las 6 opciones de cl�nicas que tiene la central
     * �Cu�l es el nombre de la m�s ocupada, la que tiene m�s pacientes?
     *
     * @return nombre de la cl�nica
     */
    public String metodo4() {
        HashMap<String, Integer> clinicaCount = new HashMap<>();
        for (Paciente paciente : pacientes) {
            String clinica = paciente.darClinica();
            clinicaCount.put(clinica, clinicaCount.getOrDefault(clinica, 0) + 1);
        }

        String clinicaMasOcupada = null;
        int maxPacientes = 0;
        for (Map.Entry<String, Integer> entry : clinicaCount.entrySet()) {
            if (entry.getValue() > maxPacientes) {
                maxPacientes = entry.getValue();
                clinicaMasOcupada = entry.getKey();
            }
        }
        return clinicaMasOcupada;
    }


}