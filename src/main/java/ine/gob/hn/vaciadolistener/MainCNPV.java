package ine.gob.hn.vaciadolistener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ine.gob.hn.vaciadolistener.cnpv.Catalogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
public class MainCNPV {
    static class BeletaInfo {
        int idCspro;
        String guid;
        String questionnaire;
        String hash;
        String caseids;
        java.sql.Timestamp sourceModifiedTime;
    }

    static class EstadoDestino {
        String hash;
        String caseids;

        EstadoDestino(String hash, String caseids) {
            this.hash = hash;
            this.caseids = caseids;
        }
    }
    private static String safe(String valor) {
        return valor != null ? valor : "";
    }

    private static final String PROCESO_SYNC = "HPHC_HOGARES_DICT";
    private static final long INTERVALO_CICLO_MS = 5 * 60 * 1000L;
    private static final int HILOS_PROCESAMIENTO = 1;

    private static final String[] PERSONA_COLUMNAS = {
            "h_orden", "h_ch000_nper", "h_ch01_nombre", "h_ch02_parentesco", "h_ch03_sexo",
            "h_ch04_edad", "h_ch05a_anio", "h_ch05b_mes", "h_ch05c_dia", "h_ch06_rnp",
            "h_p01_pueblo", "h_p02_des", "h_p02_pueblo", "h_p02_esp", "h_p03_pueblo",
            "h_p04_pueblo_an", "h_p04_esp", "h_p05_lenguamat", "h_p06_lenguamat", "h_p07a_caminar",
            "h_p07a_caminar_c", "h_p07b_comuni", "h_p07b_comuni_c", "h_p07c_ver", "h_p07c_ver_c",
            "h_p07d_oir", "h_p07d_oir_c", "h_p07e_valerse", "h_p07e_valerse_c", "h_p07f_recordar",
            "h_p07f_recordar_c", "h_p07g_brazos", "h_p07g_brazos_c", "h_p08_lugar_na", "h_p09_depto",
            "h_p09_na_muni", "h_p10_na_pais", "h_p011a_llega_anio", "h_p011b_llega_mes", "h_p12_lugar_residencia",
            "h_p13_depto", "h_p13_muni_residencia", "h_p14_vivia_pais", "h_p15_munic_anio", "h_p16_razon_vino",
            "h_p17_esta_civil", "h_p18_asisten_edu", "h_p19a_nivel_educ", "h_p19b_grado_educ", "h_p20_ulti_carr",
            "h_p21_fin_carrera", "h_p22_leer_escri", "h_p23a_uso_pc", "h_p23b_uso_table", "h_p23c_uso_cel",
            "h_p23d_uso_basico", "h_p23e_uso_inter", "h_p24_ident_empleado", "h_p25_productos_agro", "h_p26_acti_reali",
            "h_p27_busc_trab", "h_p28_disponibilidad", "h_p29_act_princ", "h_p30_ocupacion", "h_p30_tarea_ocupacion",
            "h_p31_estab_trabajo", "h_p32_lugar_trab", "h_p32_depto", "h_p32_lugar_munic", "h_p32_lugar_pais",
            "h_p33_trabaj_como", "h_p34_acti_agro", "h_p35_cobertura_medica", "h_p36_hijos_tenido", "h_p37_cuantos_hijos",
            "h_p37_cuantas_hijas", "h_p38_edad_emb", "h_p39_total_vivos", "h_p39_total_hijos", "h_p39_total_hijas",
            "h_p40_anio_nac", "h_p40_mes_nac", "h_p41_sexo_ultimo", "h_p42_vivo_ultimo", "h_p43_productor_agropecuario",
            "h_p44_identidad", "h_add_another_persona", "h_keep_row", "visualizar_menores", "h_menor_nombre",
            "h_menor_add", "h__parentesco_menor", "h_sexo_menor", "h_edad_menor", "h_rnp_menor"
    };

    private static final String[][] PERSONA_ALIASES = {
            {"H_ORDEN"},
            {"H_CH000_NPER"},
            {"H_CH01_NOMBRE"},
            {"H_CH02_PARENTESCO"},
            {"H_CH03_SEXO"},
            {"H_CH04_EDAD"},
            {"H_CH05A_ANIO"},
            {"H_CH05B_MES"},
            {"H_CH05C_DIA"},
            {"H_CH06_RNP"},
            {"H_P01_PUEBLO"},
            {"H_P02_DES"},
            {"H_P02_PUEBLO"},
            {"H_P02_ESP"},
            {"H_P03_PUEBLO"},
            {"H_P04_PUEBLO_AN"},
            {"H_P04_ESP"},
            {"H_P05_LENGUAMAT", "H_P02_LENGUAMAT"},
            {"H_P06_LENGUAMAT", "H_P03_LENGUAMAT"},
            {"H_P07A_CAMINAR", "H_P05A_CAMINAR"},
            {"H_P07A_CAMINAR_C", "H_P05A_CAMINAR_C", "H_P05A_CAMINAR_E"},
            {"H_P07B_COMUNI", "H_P05B_COMUNI"},
            {"H_P07B_COMUNI_C", "H_P05B_COMUNI_C"},
            {"H_P07C_VER", "H_P05C_VER"},
            {"H_P07C_VER_C", "H_P05C_VER_C"},
            {"H_P07D_OIR", "H_P05D_OIR"},
            {"H_P07D_OIR_C", "H_P05D_OIR_C"},
            {"H_P07E_VALERSE", "H_P05E_VALERSE"},
            {"H_P07E_VALERSE_C", "H_P05E_VALERSE_C"},
            {"H_P07F_RECORDAR", "H_P05F_RECORDAR"},
            {"H_P07F_RECORDAR_C", "H_P05F_RECORDAR_C"},
            {"H_P07G_BRAZOS", "H_P05G_BRAZOS"},
            {"H_P07G_BRAZOS_C", "H_P05G_BRAZOS_C"},
            {"H_P08_LUGAR_NA", "H_P06_LUGAR_NA"},
            {"H_P09_DEPTO", "H_P07_DEPTO"},
            {"H_P09_NA_MUNI", "H_P07_NA_MUNI"},
            {"H_P10_NA_PAIS", "H_P08_NA_PAIS"},
            {"H_P011A_LLEGA_ANIO", "H_P09A_LLEGA_ANIO"},
            {"H_P011B_LLEGA_MES", "H_P09B_LLEGA_MES"},
            {"H_P12_LUGAR_RESIDENCIA", "H_P10_LUGAR_RESIDENCIA"},
            {"H_P13_DEPTO", "H_P11_DEPTO"},
            {"H_P13_MUNI_RESIDENCIA", "H_P11_MUNI_RESIDENCIA"},
            {"H_P14_VIVIA_PAIS", "H_P12_VIVIA_PAIS"},
            {"H_P15_MUNIC_ANIO", "H_P13_MUNIC_ANIO"},
            {"H_P16_RAZON_VINO", "H_P14_RAZON_VINO"},
            {"H_P17_ESTA_CIVIL", "H_P16_ESTA_CIVIL"},
            {"H_P18_ASISTEN_EDU", "H_P17_ASISTEN_EDU"},
            {"H_P19A_NIVEL_EDUC", "H_P18A_NIVEL_EDUC"},
            {"H_P19B_GRADO_EDUC", "H_P18B_GRADO_EDUC"},
            {"H_P20_ULTI_CARR", "H_P19_ULTI_CARR"},
            {"H_P21_FIN_CARRERA", "H_P20_FIN_CARRERA"},
            {"H_P22_LEER_ESCRI", "H_P21_LEER_ESCRI"},
            {"H_P23A_USO_PC", "H_P22A_USO_PC"},
            {"H_P23B_USO_TABLE", "H_P22B_USO_TABLE"},
            {"H_P23C_USO_CEL", "H_P22C_USO_CEL"},
            {"H_P23D_USO_BASICO", "H_P22D_USO_BASICO"},
            {"H_P23E_USO_INTER", "H_P22E_USO_INTER", "H_P22D_USO_INTER"},
            {"H_P24_IDENT_EMPLEADO", "H_P23_IDENT_EMPLEADO"},
            {"H_P25_PRODUCTOS_AGRO", "H_P24_PRODUCTOS_AGRO"},
            {"H_P26_ACTI_REALI", "H_P25_ACTI_REALI"},
            {"H_P27_BUSC_TRAB", "H_P26_BUSC_TRAB"},
            {"H_P28_DISPONIBILIDAD", "H_P27_DISPONIBILIDAD"},
            {"H_P29_ACT_PRINC", "H_P28_ACT_PRINC"},
            {"H_P30_OCUPACION", "H_P29_OCUPACION"},
            {"H_P30_TAREA_OCUPACION", "H_P29_TAREA_OCUPACION"},
            {"H_P31_ESTAB_TRABAJO", "H_P30_ESTAB_TRABAJO"},
            {"H_P32_LUGAR_TRAB", "H_P31_LUGAR_TRAB"},
            {"H_P32_DEPTO", "H_P31_DEPTO"},
            {"H_P32_LUGAR_MUNIC", "H_P31_LUGAR_MUNIC", "H_P31_UGAR_MUNIC"},
            {"H_P32_LUGAR_PAIS", "H_P31_LUGAR_PAIS"},
            {"H_P33_TRABAJ_COMO", "H_P32_TRABAJ_COMO"},
            {"H_P34_ACTI_AGRO", "H_P33_ACTI_AGRO"},
            {"H_P35_COBERTURA_MEDICA", "H_P34_COBERTURA_MEDICA"},
            {"H_P36_HIJOS_TENIDO", "H_P34_HIJOS_TENIDO"},
            {"H_P37_CUANTOS_HIJOS"},
            {"H_P37_CUANTAS_HIJAS", "H_P36_CUANTAS_HIJAS"},
            {"H_P38_EDAD_EMB", "H_P35_EDAD_EMB"},
            {"H_P39_TOTAL_VIVOS", "H_P38_TOTAL_VIVOS"},
            {"H_P39_TOTAL_HIJOS", "H_P40_TOTAL_HIJOS"},
            {"H_P39_TOTAL_HIJAS"},
            {"H_P40_ANIO_NAC", "H_P41_ANIO_NAC"},
            {"H_P40_MES_NAC", "H_P41_MES_NAC"},
            {"H_P41_SEXO_ULTIMO", "H_P42_SEXO_ULTIMO"},
            {"H_P42_VIVO_ULTIMO", "H_P43_VIVO_ULTIMO"},
            {"H_P43_PRODUCTOR_AGROPECUARIO", "H_P44_PRODUCTOR_AGROPECUARIO"},
            {"H_P44_IDENTIDAD", "H_CH06_IDENTIDAD", "H_F44_CODIGO_ENEE"},
            {"H_ADD_ANOTHER_PERSONA"},
            {"H_KEEP_ROW"},
            {"VISUALIZAR_MENORES"},
            {"H_MENOR_NOMBRE"},
            {"H_MENOR_ADD"},
            {"H__PARENTESCO_MENOR"},
            {"H_SEXO_MENOR"},
            {"H_EDAD_MENOR"},
            {"H_RNP_MENOR"}
    };

    public static void main(String[] args) throws Exception {
        final Gson gson = new Gson();

        while (true) {
            long tiempoInicio = System.currentTimeMillis();
            Connection conOrigen = null;
            Connection conReplica = null;

            try {
                System.out.println("\n=== CICLO: " + new java.util.Date() + " ===");

                Mysql mysql = new Mysql();
                String hostOrigen = "censodb.cluster-c8x6gk0qqm17.us-east-1.rds.amazonaws.com";
                String userOrigen = "admin";
                String passOrigen = "wrHVc_0_76$80G>ZZml#R-Zc8hdl";
                final String hostDestino = "127.0.0.1:3306";
                final String userDestino = "root";
                final String passDestino = "$monitoreoINE#___";

                conOrigen = mysql.conectarMySQL(hostOrigen, "csweb", userOrigen, passOrigen);
                conReplica = mysql.conectarMySQL(hostDestino, "cnpv_data", userDestino, passDestino);

                if (conOrigen == null) {
                    throw new SQLException("No se pudo conectar a origen");
                }
                if (conReplica == null) {
                    throw new SQLException("No se pudo conectar a destino");
                }

                asegurarControlSincronizacion(conOrigen, conReplica);

                java.sql.Timestamp syncDesde = obtenerUltimaSincronizacion(conReplica);
                java.sql.Timestamp syncHasta = obtenerHoraActualOrigen(conOrigen);

                System.out.println("Ventana incremental: [" + syncDesde + ", " + syncHasta + ")");

                // No aplicar funciones como UNIX_TIMESTAMP() o DATE() sobre modified_time.
                // De esta forma MySQL puede utilizar un índice sobre esa columna.
                try (Statement drop = conOrigen.createStatement()) {
                    drop.executeUpdate("DROP TEMPORARY TABLE IF EXISTS boletas_cambios");
                }

                // No se trae UNCOMPRESS(questionnaire) en este primer paso: MySQL calcula el
                // hash internamente sin necesidad de mandar el texto completo a Java. Traer el
                // cuestionario de TODAS las boletas de la ventana (incluidas las que no cambiaron)
                // es lo que agotaba la memoria en ventanas grandes.
                String sqlCreateTemp = "CREATE TEMPORARY TABLE boletas_cambios AS "
                        + "SELECT id, HEX(guid) AS guid, caseids, "
                        + "MD5(UNCOMPRESS(questionnaire)) AS hash, modified_time "
                        + "FROM csweb.HPHC_HOGARES_DICT "
                        + "WHERE modified_time >= ? AND modified_time < ? "
                        + "ORDER BY modified_time ASC, id ASC";

                try (PreparedStatement stmtCreate = conOrigen.prepareStatement(sqlCreateTemp)) {
                    stmtCreate.setTimestamp(1, syncDesde);
                    stmtCreate.setTimestamp(2, syncHasta);
                    stmtCreate.executeUpdate();
                }

                Map<String, BeletaInfo> boletasCandidatas = new LinkedHashMap<>();
                String sqlCandidatas = "SELECT id, guid, caseids, hash, modified_time "
                        + "FROM boletas_cambios";

                try (Statement stmt = conOrigen.createStatement();
                     ResultSet rs = stmt.executeQuery(sqlCandidatas)) {
                    while (rs.next()) {
                        BeletaInfo info = new BeletaInfo();
                        info.idCspro = rs.getInt("id");
                        info.guid = rs.getString("guid");
                        info.hash = rs.getString("hash");
                        info.caseids = rs.getString("caseids");
                        info.sourceModifiedTime = rs.getTimestamp("modified_time");
                        boletasCandidatas.put(info.guid, info);
                    }
                }

                // Ya se cargaron solamente las boletas de la ventana actual en memoria.
                // La tabla temporal puede eliminarse antes del procesamiento paralelo.
                try (Statement drop = conOrigen.createStatement()) {
                    drop.executeUpdate("DROP TEMPORARY TABLE IF EXISTS boletas_cambios");
                }

                System.out.println("Boletas tocadas en la ventana: " + boletasCandidatas.size());

                if (boletasCandidatas.isEmpty()) {
                    actualizarUltimaSincronizacion(conReplica, syncHasta);
                    System.out.println("Sin cambios. Marca de sincronización actualizada.");
                    continue;
                }

                // Consultar en el destino únicamente los GUID que cambiaron en esta ventana.
                // Ya no se recorre toda la tabla registros_cnpv en cada ciclo.
                List<String> guidsCandidatos = new ArrayList<>(boletasCandidatas.keySet());
                Map<String, EstadoDestino> estadosDestino =
                        cargarEstadosDestino(conReplica, guidsCandidatos);

                List<BeletaInfo> boletasAProcesar = new ArrayList<>();
                int nuevas = 0;
                int modificadas = 0;
                int caseidsPendientes = 0;

                for (BeletaInfo info : boletasCandidatas.values()) {
                    EstadoDestino estadoDestino = estadosDestino.get(info.guid);

                    if (estadoDestino == null) {
                        nuevas++;
                        boletasAProcesar.add(info);
                        continue;
                    }

                    boolean cambioQuestionnaire = !sonIguales(
                            info.hash,
                            estadoDestino.hash
                    );
                    boolean cambioCaseids = !sonIguales(
                            info.caseids,
                            estadoDestino.caseids
                    );

                    if (cambioQuestionnaire || cambioCaseids) {
                        modificadas++;
                        if (!cambioQuestionnaire && cambioCaseids) {
                            caseidsPendientes++;
                        }
                        boletasAProcesar.add(info);
                    }
                }

                if (caseidsPendientes > 0) {
                    System.out.println(
                            "Boletas con questionnaire igual pero caseids pendiente/cambiado: "
                            + caseidsPendientes
                    );
                }

                System.out.println("Nuevas: " + nuevas
                        + " | Modificadas: " + modificadas
                        + " | Sin cambios de contenido: "
                        + (boletasCandidatas.size() - boletasAProcesar.size()));

                if (boletasAProcesar.isEmpty()) {
                    actualizarUltimaSincronizacion(conReplica, syncHasta);
                    System.out.println("No había cambios de contenido. Ciclo confirmado.");
                    continue;
                }

                // Traer el cuestionario completo (UNCOMPRESS) solo para las boletas que
                // realmente van a procesarse. En ventanas grandes esto es una fracción
                // pequeña del total, así que ya no se satura la memoria con boletas que
                // se iban a descartar de todas formas por hash igual.
                Map<String, BeletaInfo> aProcesarPorGuid = new HashMap<>();
                for (BeletaInfo info : boletasAProcesar) {
                    aProcesarPorGuid.put(info.guid, info);
                }

                List<String> guidsAProcesar = new ArrayList<>(aProcesarPorGuid.keySet());
                final int loteQuestionnaire = 500;

                for (int inicio = 0; inicio < guidsAProcesar.size(); inicio += loteQuestionnaire) {
                    List<String> lote = guidsAProcesar.subList(
                            inicio, Math.min(inicio + loteQuestionnaire, guidsAProcesar.size())
                    );

                    String placeholders = String.join(
                            ",", Collections.nCopies(lote.size(), "?")
                    );
                    String sqlQuestionnaire = "SELECT HEX(guid) AS guid, "
                            + "UNCOMPRESS(questionnaire) AS questionnaire "
                            + "FROM csweb.HPHC_HOGARES_DICT "
                            + "WHERE HEX(guid) IN (" + placeholders + ")";

                    try (PreparedStatement stmtQ = conOrigen.prepareStatement(sqlQuestionnaire)) {
                        for (int i = 0; i < lote.size(); i++) {
                            stmtQ.setString(i + 1, lote.get(i));
                        }
                        try (ResultSet rsQ = stmtQ.executeQuery()) {
                            while (rsQ.next()) {
                                BeletaInfo info = aProcesarPorGuid.get(rsQ.getString("guid"));
                                if (info != null) {
                                    info.questionnaire = rsQ.getString("questionnaire");
                                }
                            }
                        }
                    }
                }

                // Para cargas incrementales pequeñas no se deben desactivar índices.
                // ALTER TABLE DISABLE/ENABLE KEYS puede bloquear las tablas y cargar el servidor.
                ExecutorService executor = Executors.newFixedThreadPool(HILOS_PROCESAMIENTO);
                AtomicInteger errores = new AtomicInteger(0);
                AtomicInteger exitosas = new AtomicInteger(0);

                for (BeletaInfo info : boletasAProcesar) {
                    executor.submit(() -> {
                        Connection conThread = null;
                        try {
                            Mysql mysqlThread = new Mysql();
                            conThread = mysqlThread.conectarMySQL(
                                    hostDestino,
                                    "cnpv_data",
                                    userDestino,
                                    passDestino
                            );

                            if (conThread == null) {
                                throw new SQLException("No se pudo abrir conexión de destino para " + info.guid);
                            }

                            conThread.setAutoCommit(false);

                            // Es seguro llamarlo tanto para una nueva como para una modificada.
                            // Si no existe todavía, simplemente no elimina nada.
                            borrarDatosHijos(conThread, info.guid);
                            procesarBoleta(conThread, gson, info);

                            // El hash se confirma únicamente después de procesar correctamente
                            // la boleta y sus tablas hijas.
                            guardarRegistroSincronizado(conThread, info);

                            conThread.commit();
                            exitosas.incrementAndGet();
                            System.out.println("✓ Sincronizada " + info.guid);
                        } catch (Exception e) {
                            errores.incrementAndGet();
                            if (conThread != null) {
                                try {
                                    conThread.rollback();
                                } catch (SQLException rollbackError) {
                                    System.err.println("Error en rollback de " + info.guid + ": "
                                            + rollbackError.getMessage());
                                }
                            }
                            System.err.println("✗ Error en " + info.guid + ": " + e.getMessage());
                            e.printStackTrace(System.err);
                        } finally {
                            if (conThread != null) {
                                try {
                                    conThread.setAutoCommit(true);
                                    conThread.close();
                                } catch (SQLException ignored) {
                                    // Sin acción.
                                }
                            }
                        }
                    });
                }

                executor.shutdown();
                boolean terminado = executor.awaitTermination(10, TimeUnit.MINUTES);

                if (!terminado) {
                    executor.shutdownNow();
                    errores.incrementAndGet();
                    System.err.println("⚠ Timeout procesando boletas");
                }

                System.out.println("Procesadas correctamente: " + exitosas.get()
                        + " | Errores: " + errores.get());

                if (errores.get() == 0) {
                    // La ventana se avanza solamente cuando todas las boletas terminaron bien.
                    actualizarUltimaSincronizacion(conReplica, syncHasta);
                    System.out.println("✓ Ventana confirmada hasta " + syncHasta);
                } else {
                    // En el siguiente ciclo se vuelve a consultar la misma ventana.
                    // Las exitosas se omiten por hash y solamente se reintentan las fallidas.
                    System.err.println("⚠ La marca de sincronización NO avanzó; se reintentarán las fallidas.");
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Proceso interrumpido");
                return;
            } catch (Exception e) {
                System.err.println("Error general: " + e.getMessage());
                e.printStackTrace();
            } finally {
                cerrarSilenciosamente(conOrigen);
                cerrarSilenciosamente(conReplica);

                long tiempoTotal = System.currentTimeMillis() - tiempoInicio;
                long tiempoEspera = INTERVALO_CICLO_MS - tiempoTotal;

                System.out.println("Ciclo finalizado en " + (tiempoTotal / 1000.0) + "s");

                if (tiempoEspera > 0) {
                    try {
                        Thread.sleep(tiempoEspera);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
            }
        }
    }

    private static void asegurarControlSincronizacion(
            Connection conOrigen,
            Connection conReplica
    ) throws SQLException {
        String sqlCreate = "CREATE TABLE IF NOT EXISTS cnpv_data.sync_control ("
                + "process_name VARCHAR(100) NOT NULL PRIMARY KEY, "
                + "last_successful_sync DATETIME NOT NULL, "
                + "updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP "
                + "ON UPDATE CURRENT_TIMESTAMP"
                + ")";

        try (Statement stmt = conReplica.createStatement()) {
            stmt.executeUpdate(sqlCreate);
        }

        boolean existe;
        String sqlExiste = "SELECT 1 FROM cnpv_data.sync_control WHERE process_name = ?";
        try (PreparedStatement stmt = conReplica.prepareStatement(sqlExiste)) {
            stmt.setString(1, PROCESO_SYNC);
            try (ResultSet rs = stmt.executeQuery()) {
                existe = rs.next();
            }
        }

        if (!existe) {
            // Primera ejecución: hacer carga inicial completa.
            // Después de confirmar esta carga, los siguientes ciclos serán incrementales.
            java.sql.Timestamp inicioHistorico = java.sql.Timestamp.valueOf("1970-01-01 00:00:00");

            String sqlInsert = "INSERT INTO cnpv_data.sync_control "
                    + "(process_name, last_successful_sync) VALUES (?, ?)";
            try (PreparedStatement stmt = conReplica.prepareStatement(sqlInsert)) {
                stmt.setString(1, PROCESO_SYNC);
                stmt.setTimestamp(2, inicioHistorico);
                stmt.executeUpdate();
            }

            System.out.println("Primera ejecución: carga inicial completa desde " + inicioHistorico);
        }
    }

    private static java.sql.Timestamp obtenerUltimaSincronizacion(Connection con) throws SQLException {
        String sql = "SELECT last_successful_sync FROM cnpv_data.sync_control "
                + "WHERE process_name = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, PROCESO_SYNC);
            try (ResultSet rs = stmt.executeQuery()) {
                if (!rs.next()) {
                    throw new SQLException("No existe control de sincronización para " + PROCESO_SYNC);
                }
                return rs.getTimestamp("last_successful_sync");
            }
        }
    }

    private static java.sql.Timestamp obtenerHoraActualOrigen(Connection con) throws SQLException {
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT CURRENT_TIMESTAMP AS sync_hasta")) {
            if (!rs.next()) {
                throw new SQLException("No se pudo obtener la hora actual del origen");
            }
            return rs.getTimestamp("sync_hasta");
        }
    }

    private static void actualizarUltimaSincronizacion(
            Connection con,
            java.sql.Timestamp syncHasta
    ) throws SQLException {
        String sql = "UPDATE cnpv_data.sync_control "
                + "SET last_successful_sync = ? WHERE process_name = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setTimestamp(1, syncHasta);
            stmt.setString(2, PROCESO_SYNC);
            stmt.executeUpdate();
        }
    }

    private static Map<String, EstadoDestino> cargarEstadosDestino(
            Connection con,
            List<String> guids
    ) throws SQLException {
        Map<String, EstadoDestino> estados = new HashMap<>();
        final int tamanoLote = 500;

        for (int inicio = 0; inicio < guids.size(); inicio += tamanoLote) {
            int fin = Math.min(inicio + tamanoLote, guids.size());
            List<String> lote = guids.subList(inicio, fin);

            StringBuilder placeholders = new StringBuilder();
            for (int i = 0; i < lote.size(); i++) {
                if (i > 0) {
                    placeholders.append(',');
                }
                placeholders.append('?');
            }

            String sql = "SELECT uiid_cspro, questionnaire_hash, caseids "
                    + "FROM cnpv_data.registros_cnpv "
                    + "WHERE uiid_cspro IN (" + placeholders + ")";

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                for (int i = 0; i < lote.size(); i++) {
                    stmt.setString(i + 1, lote.get(i));
                }

                try (ResultSet rs = stmt.executeQuery()) {
                    while (rs.next()) {
                        estados.put(
                                rs.getString("uiid_cspro"),
                                new EstadoDestino(
                                        rs.getString("questionnaire_hash"),
                                        rs.getString("caseids")
                                )
                        );
                    }
                }
            }
        }

        return estados;
    }

    private static boolean sonIguales(String valor1, String valor2) {
        if (valor1 == null) {
            return valor2 == null;
        }
        return valor1.equals(valor2);
    }

    private static void guardarRegistroSincronizado(
            Connection con,
            BeletaInfo info
    ) throws SQLException {
        String sql = "INSERT INTO cnpv_data.registros_cnpv "
                + "(id_cspro, uiid_cspro, size, caseids, questionnaire_hash, modified_time) "
                + "VALUES (?, ?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "id_cspro = VALUES(id_cspro), "
                + "size = VALUES(size), "
                + "caseids = VALUES(caseids), "
                + "questionnaire_hash = VALUES(questionnaire_hash), "
                + "modified_time = VALUES(modified_time)";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setInt(1, info.idCspro);
            stmt.setString(2, info.guid);
            stmt.setInt(3, info.questionnaire != null ? info.questionnaire.length() : 0);
            stmt.setString(4, info.caseids);
            stmt.setString(5, info.hash);
            stmt.setTimestamp(6, info.sourceModifiedTime);
            stmt.executeUpdate();
        }
    }

    private static void cerrarSilenciosamente(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ignored) {
                // Sin acción.
            }
        }
    }

    private static void disableIndexes(Connection con) throws SQLException {
        String[] tables = {
            "personas_rec", "emigracion_rec", "visita", "mortalidad_rec",
            "vivienda_rec", "hogares_rec", "metadatos_rec"
        };
        for (String table : tables) {
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("ALTER TABLE cnpv_data." + table + " DISABLE KEYS");
                stmt.close();
            } catch (Exception e) {
                //
            }
        }
    }
    private static void enableIndexes(Connection con) throws SQLException {
        String[] tables = {
            "personas_rec", "emigracion_rec", "visita", "mortalidad_rec",
            "vivienda_rec", "hogares_rec", "metadatos_rec"
        };
        for (String table : tables) {
            try {
                Statement stmt = con.createStatement();
                stmt.executeUpdate("ALTER TABLE cnpv_data." + table + " ENABLE KEYS");
                stmt.close();
            } catch (Exception e) {
                //
            }
        }
    }
    private static void borrarDatosHijos(Connection con, String guid) throws SQLException {
        List<Integer> levelIds = new ArrayList<>();

        String sqlIds = "SELECT `level-1-id` "
                + "FROM cnpv_data.`level-1` "
                + "WHERE `case-id` = ? "
                + "ORDER BY `level-1-id` ASC";

        try (PreparedStatement stmt = con.prepareStatement(sqlIds)) {
            stmt.setString(1, guid);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    levelIds.add(rs.getInt("level-1-id"));
                }
            }
        }

        if (levelIds.isEmpty()) {
            return;
        }

        String[] deletesHijos = {
            "DELETE FROM cnpv_data.personas_rec WHERE `level-1-id` = ?",
            "DELETE FROM cnpv_data.emigracion_rec WHERE `level-1-id` = ?",
            "DELETE FROM cnpv_data.visita WHERE `level-1-id` = ?",
            "DELETE FROM cnpv_data.mortalidad_rec WHERE `level-1-id` = ?",
            "DELETE FROM cnpv_data.vivienda_rec WHERE `level-1-id` = ?",
            "DELETE FROM cnpv_data.hogares_rec WHERE `level-1-id` = ?",
            "DELETE FROM cnpv_data.metadatos_rec WHERE `level-1-id` = ?"
        };

        // Eliminar los hijos de TODAS las filas duplicadas de la boleta.
        for (Integer levelId : levelIds) {
            for (String sql : deletesHijos) {
                try (PreparedStatement del = con.prepareStatement(sql)) {
                    del.setInt(1, levelId);
                    del.executeUpdate();
                }
            }
        }

        // Conservar únicamente el primer level-1-id de ese case-id.
        // Los demás son duplicados creados por ejecuciones anteriores.
        if (levelIds.size() > 1) {
            int levelIdConservar = levelIds.get(0);

            String sqlEliminarDuplicados = "DELETE FROM cnpv_data.`level-1` "
                    + "WHERE `case-id` = ? AND `level-1-id` <> ?";

            try (PreparedStatement delDuplicados = con.prepareStatement(sqlEliminarDuplicados)) {
                delDuplicados.setString(1, guid);
                delDuplicados.setInt(2, levelIdConservar);
                int eliminados = delDuplicados.executeUpdate();

                if (eliminados > 0) {
                    System.out.println("  ↳ Eliminados " + eliminados
                            + " level-1 duplicados de " + guid);
                }
            }
        }
    }

    private static void procesarBoleta(Connection conReplica, Gson gson, BeletaInfo info) throws SQLException {
        try {
            JsonObject cuestionarioJson = gson.fromJson(info.questionnaire, JsonObject.class);
            JsonObject identificacion = obtenerIdentificacion(gson, cuestionarioJson);

            if (identificacion == null) {
                throw new SQLException(
                        "La boleta " + info.guid
                        + " tiene questionnaire válido, pero no contiene el objeto id"
                );
            }

            if (info.caseids == null || info.caseids.trim().isEmpty()) {
                info.caseids = construirCaseId(identificacion);
            }

            int levelId = buscarLevelIdPorGuid(conReplica, info.guid);

            if (levelId > 0) {
                actualizarLevel1(conReplica, levelId, identificacion);
            } else {
                levelId = insertarLevel1(conReplica, info.guid, identificacion);
            }

            if (levelId <= 0) {
                throw new SQLException("No se pudo obtener level-1-id para " + info.guid);
            }

            insertarTablaHijas(conReplica, levelId, cuestionarioJson);
        } catch (Exception e) {
            throw new SQLException("Error en boleta " + info.guid, e);
        }
    }

    private static int buscarLevelIdPorGuid(Connection con, String guid) throws SQLException {
        String sql = "SELECT `level-1-id` "
                + "FROM cnpv_data.`level-1` "
                + "WHERE `case-id` = ? "
                + "ORDER BY `level-1-id` ASC "
                + "LIMIT 1";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, guid);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("level-1-id");
                }
            }
        }

        return -1;
    }

    private static void actualizarLevel1(
            Connection con,
            int levelId,
            JsonObject identificacion
    ) throws SQLException {
        String sql = "UPDATE cnpv_data.`level-1` SET "
                + "l1_departamento = ?, "
                + "l1_municipio = ?, "
                + "l1_aldea = ?, "
                + "l1_caserio = ?, "
                + "l1_apoyo_muni = ?, "
                + "l1_zona = ?, "
                + "l1_sector = ?, "
                + "l1_segmento = ?, "
                + "l1_cod_encuestador = ?, "
                + "l1_estructura = ?, "
                + "l1_vivienda = ?, "
                + "l1_hogar = ?, "
                + "l1_id_asig_gti = ? "
                + "WHERE `level-1-id` = ?";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            asignarTextoNullable(stmt, 1, obtenerValorJson(identificacion, "L1_DEPARTAMENTO"));
            asignarTextoNullable(stmt, 2, obtenerValorJson(identificacion, "L1_MUNICIPIO"));
            asignarTextoNullable(stmt, 3, obtenerValorJson(identificacion, "L1_ALDEA"));
            asignarTextoNullable(stmt, 4, obtenerValorJson(identificacion, "L1_CASERIO"));
            asignarTextoNullable(stmt, 5, obtenerValorJson(identificacion, "L1_APOYO_MUNI"));
            asignarTextoNullable(stmt, 6, obtenerValorJson(identificacion, "L1_ZONA"));
            asignarTextoNullable(stmt, 7, obtenerValorJson(identificacion, "L1_SECTOR"));
            asignarTextoNullable(stmt, 8, obtenerValorJson(identificacion, "L1_SEGMENTO"));
            asignarTextoNullable(stmt, 9, obtenerValorJson(identificacion, "L1_COD_ENCUESTADOR"));
            asignarTextoNullable(stmt, 10, obtenerValorJson(identificacion, "L1_ESTRUCTURA"));
            asignarTextoNullable(stmt, 11, obtenerValorJson(identificacion, "L1_VIVIENDA"));
            asignarTextoNullable(stmt, 12, obtenerValorJson(identificacion, "L1_HOGAR"));
            asignarTextoNullable(stmt, 13, obtenerValorJson(identificacion, "L1_ID_ASIG_GTI"));
            stmt.setInt(14, levelId);
            stmt.executeUpdate();
        }
    }

    private static int insertarLevel1(
            Connection con,
            String guid,
            JsonObject identificacion
    ) throws SQLException {
        String sql = "INSERT INTO cnpv_data.`level-1` "
                + "(`case-id`, l1_departamento, l1_municipio, l1_aldea, l1_caserio, "
                + "l1_apoyo_muni, l1_zona, l1_sector, l1_segmento, l1_cod_encuestador, "
                + "l1_estructura, l1_vivienda, l1_hogar, l1_id_asig_gti) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, guid);
            asignarTextoNullable(stmt, 2, obtenerValorJson(identificacion, "L1_DEPARTAMENTO"));
            asignarTextoNullable(stmt, 3, obtenerValorJson(identificacion, "L1_MUNICIPIO"));
            asignarTextoNullable(stmt, 4, obtenerValorJson(identificacion, "L1_ALDEA"));
            asignarTextoNullable(stmt, 5, obtenerValorJson(identificacion, "L1_CASERIO"));
            asignarTextoNullable(stmt, 6, obtenerValorJson(identificacion, "L1_APOYO_MUNI"));
            asignarTextoNullable(stmt, 7, obtenerValorJson(identificacion, "L1_ZONA"));
            asignarTextoNullable(stmt, 8, obtenerValorJson(identificacion, "L1_SECTOR"));
            asignarTextoNullable(stmt, 9, obtenerValorJson(identificacion, "L1_SEGMENTO"));
            asignarTextoNullable(stmt, 10, obtenerValorJson(identificacion, "L1_COD_ENCUESTADOR"));
            asignarTextoNullable(stmt, 11, obtenerValorJson(identificacion, "L1_ESTRUCTURA"));
            asignarTextoNullable(stmt, 12, obtenerValorJson(identificacion, "L1_VIVIENDA"));
            asignarTextoNullable(stmt, 13, obtenerValorJson(identificacion, "L1_HOGAR"));
            asignarTextoNullable(stmt, 14, obtenerValorJson(identificacion, "L1_ID_ASIG_GTI"));
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    return keys.getInt(1);
                }
            }
        }

        // Respaldo para drivers/configuraciones que no devuelvan generated keys.
        return buscarLevelIdPorGuid(con, guid);
    }

    private static void insertarTablaHijas(
            Connection con,
            int levelId,
            JsonObject cuestionarioJson
    ) throws SQLException {
        JsonObject vivienda = obtenerObjetoJson(cuestionarioJson, "VIVIENDA_REC");
        if (vivienda != null) {
            insertarVivienda(con, levelId, vivienda);
        }

        JsonObject hogares = obtenerObjetoJson(cuestionarioJson, "HOGARES_REC");
        if (hogares != null) {
            insertarHogares(con, levelId, hogares);
        }

        JsonArray personas = obtenerArregloJson(cuestionarioJson, "PERSONAS_REC");
        if (personas != null && personas.size() > 0) {
            insertarPersonasBatch(con, levelId, personas);
        }

        JsonArray emigracion = obtenerArregloJson(cuestionarioJson, "EMIGRACION_REC");
        if (emigracion != null && emigracion.size() > 0) {
            insertarEmigracionBatch(con, levelId, emigracion);
        }

        JsonArray visitas = obtenerArregloJson(cuestionarioJson, "VISITA");
        if (visitas != null && visitas.size() > 0) {
            insertarVisitaBatch(con, levelId, visitas);
        }

        JsonArray mortalidad = obtenerArregloJson(cuestionarioJson, "MORTALIDAD_REC");
        if (mortalidad != null && mortalidad.size() > 0) {
            insertarMortalidadBatch(con, levelId, mortalidad);
        }

        JsonObject metadatos = obtenerObjetoJson(cuestionarioJson, "METADATOS_REC");
        if (metadatos != null) {
            insertarMetadatos(
                    con,
                    levelId,
                    metadatos,
                    obtenerArregloJson(cuestionarioJson, "METADATOS_DEVICE_REC"),
                    visitas
            );
        }
    }

    private static void insertarVivienda(
            Connection con,
            int levelId,
            JsonObject vivienda
    ) throws SQLException {
        String[] columnas = {
                "h_tipo_estructura", "h_vive_estructura", "h_v00_tipo_viv", "h_v01_tipo_viv",
                "h_v02_paredes_viv", "h_v03_techo_viv", "h_v04_ocup_viv", "h_v05_piso_viv",
                "h_v06_dispo_agua", "h_v07_prov_agua", "h_v08_acc_alum", "h_v09_elim_basura",
                "h_v10_piezas_viv", "h_v11_iden_hogar", "h_v12_gru_hogar", "num_estruc_matriz"
        };

        String[][] aliases = {
                {"H_TIPO_ESTRUCTURA"}, {"H_VIVE_ESTRUCTURA"}, {"H_V00_TIPO_VIV"}, {"H_V01_TIPO_VIV"},
                {"H_V02_PAREDES_VIV"}, {"H_V03_TECHO_VIV"}, {"H_V04_OCUP_VIV"}, {"H_V05_PISO_VIV"},
                {"H_V06_DISPO_AGUA"}, {"H_V07_PROV_AGUA"}, {"H_V08_ACC_ALUM"}, {"H_V09_ELIM_BASURA"},
                {"H_V10_PIEZAS_VIV"}, {"H_V11_IDEN_HOGAR"}, {"H_V12_GRU_HOGAR"}, {"NUM_ESTRUC_MATRIZ"}
        };

        boolean[] usarCatalogo = {
                false, false, false, true,
                true, true, true, true,
                true, true, true, true,
                false, true, false, false
        };

        insertarRegistroSimple(
                con,
                "vivienda_rec",
                levelId,
                vivienda,
                columnas,
                aliases,
                usarCatalogo,
                "VIVIENDA_REC"
        );
    }

    private static void insertarHogares(
            Connection con,
            int levelId,
            JsonObject hogares
    ) throws SQLException {
        String[] columnas = {
                "h_h01_pieza_dormir", "h_h02_cocina_hog", "h_h03_uso_cocina", "h_h04_cocinan_con",
                "h_h05_sanit_hog", "h_h06_sani_exclu", "h_h07a_refri", "h_h07b_estufa",
                "h_h07c_aire", "h_h07d_lavadora", "h_h07e_radio", "h_h07f_tv",
                "h_h07g_pc", "h_h07h_tel_fijo", "h_h07i_tel_cel", "h_h07j_internet",
                "h_h07k_cable", "h_h07l_carro", "h_h07m_moto", "h_h07n_lancha",
                "h_h08_ten_vivi", "h_h09a_adultos", "h_h09b_adultos", "h_h09c_adultos",
                "h_h10a_adultos", "h_h10b_adultos", "h_h10c_adultos", "h_hm_de_18_anos",
                "h_h11a_menores", "h_h11b_menores", "h_h11c_menores", "h_h12a_menores",
                "h_h12b_menores", "h_h12c_menores", "h_m01_total_muert", "h_m01_cuantas",
                "h_ch00_num_per", "h_ch06_verifica", "h_e01_num_emi", "h_e06_reme_anio",
                "h_e01_tiempo", "h_e07_no_dest", "h_vdireccion", "h_ch00_hombres",
                "h_ch00_mujeres", "h_cambio_de_seccion"
        };

        String[][] aliases = {
                {"H_H01_PIEZA_DORMIR"}, {"H_H02_COCINA_HOG"}, {"H_H03_USO_COCINA"}, {"H_H04_COCINAN_CON"},
                {"H_H05_SANIT_HOG"}, {"H_H06_SANI_EXCLU"}, {"H_H07A_REFRI"}, {"H_H07B_ESTUFA"},
                {"H_H07C_AIRE"}, {"H_H07D_LAVADORA"}, {"H_H07E_RADIO"}, {"H_H07F_TV"},
                {"H_H07G_PC"}, {"H_H07H_TEL_FIJO"}, {"H_H07I_TEL_CEL"}, {"H_H07J_INTERNET"},
                {"H_H07K_CABLE"}, {"H_H07L_CARRO"}, {"H_H07M_MOTO"}, {"H_H07N_LANCHA"},
                {"H_H08_TEN_VIVI"}, {"H_H09A_ADULTOS"}, {"H_H09B_ADULTOS"}, {"H_H09C_ADULTOS"},
                {"H_H10A_ADULTOS"}, {"H_H10B_ADULTOS"}, {"H_H10C_ADULTOS"}, {"H_HM_DE_18_ANOS"},
                {"H_H11A_MENORES"}, {"H_H11B_MENORES"}, {"H_H11C_MENORES"}, {"H_H12A_MENORES"},
                {"H_H12B_MENORES"}, {"H_H12C_MENORES"}, {"H_M01_TOTAL_MUERT"}, {"H_M01_CUANTAS"},
                {"H_CH00_NUM_PER"}, {"H_CH06_VERIFICA"}, {"H_E01_NUM_EMI"}, {"H_E06_REME_ANIO"},
                {"H_E01_TIEMPO"}, {"H_E07_NO_DEST"}, {"H_VDIRECCION"}, {"H_CH00_HOMBRES"},
                {"H_CH00_MUJERES"}, {"H_CAMBIO_DE_SECCION"}
        };

        boolean[] usarCatalogo = {
                false, true, true, true,
                true, true, true, true,
                true, true, true, true,
                true, true, true, true,
                true, true, true, true,
                true, true, true, true,
                true, true, true, true,
                true, true, true, true,
                true, true, false, false,
                false, false, false, true,
                true, true, false, false,
                false, true
        };

        insertarRegistroSimple(
                con,
                "hogares_rec",
                levelId,
                hogares,
                columnas,
                aliases,
                usarCatalogo,
                "HOGARES_REC"
        );
    }

    private static void insertarPersonasBatch(
            Connection con,
            int levelId,
            JsonArray personas
    ) throws SQLException {
        if (personas == null || personas.size() == 0) {
            return;
        }

        if (PERSONA_COLUMNAS.length != PERSONA_ALIASES.length) {
            throw new SQLException("Configuración inválida de columnas y alias de PERSONAS_REC");
        }

        StringBuilder columnas = new StringBuilder("`level-1-id`, occ");
        for (String columna : PERSONA_COLUMNAS) {
            columnas.append(", `").append(columna).append("`");
        }

        String sql = "INSERT INTO cnpv_data.personas_rec("
                + columnas
                + ") VALUES ("
                + crearPlaceholders(PERSONA_COLUMNAS.length + 2)
                + ")";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            int occ = 1;

            for (JsonElement elemento : personas) {
                if (elemento == null || !elemento.isJsonObject()) {
                    continue;
                }

                JsonObject persona = elemento.getAsJsonObject();
                // Si CSPro no envia H_KEEP_ROW (slot del roster nunca visitado), se
                // asume 2 (borrar) por defecto, no 1. De lo contrario esos slots
                // vacios se insertan como filas fantasma (edad, sexo, nombre y
                // orden en blanco) en personas_rec.
                int mantenerFila = obtenerEnteroJson(persona, 2, "H_KEEP_ROW");

                // En el diccionario: 1 = mantener fila, 2 = borrar fila.
                if (mantenerFila != 1) {
                    continue;
                }

                int idx = 1;
                stmt.setInt(idx++, levelId);
                stmt.setInt(idx++, occ++);

                for (String[] nombresAlternativos : PERSONA_ALIASES) {
                    String valor = obtenerValorJson(persona, nombresAlternativos);
                    asignarTextoNullable(stmt, idx++, valor);
                }

                stmt.addBatch();
            }

            stmt.executeBatch();
        }
    }

    private static void insertarEmigracionBatch(
            Connection con,
            int levelId,
            JsonArray emigracion
    ) throws SQLException {
        String[] columnas = {
                "h_orden_e", "h_e01_nombre_emi", "h_e02_sexo_emi", "h_e03_edad_emi",
                "h_e04_anio_emi", "h_e05_resi_emi", "h_e05_especifique",
                "h_add_another_emigracion", "h_e_keep_row"
        };

        String[][] aliases = {
                {"H_ORDEN_E"}, {"H_E01_NOMBRE_EMI"}, {"H_E02_SEXO_EMI"}, {"H_E03_EDAD_EMI"},
                {"H_E04_ANIO_EMI"}, {"H_E05_RESI_EMI"}, {"H_E05_ESPECIFIQUE"},
                {"H_ADD_ANOTHER_EMIGRACION"}, {"H_E_KEEP_ROW"}
        };

        boolean[] usarCatalogo = {
                false, false, true, false,
                false, true, false,
                false, false
        };

        insertarRegistrosRepetidos(
                con,
                "emigracion_rec",
                levelId,
                emigracion,
                columnas,
                aliases,
                usarCatalogo,
                "EMIGRACION_REC",
                new String[]{"H_E_KEEP_ROW"}
        );
    }

    private static void insertarVisitaBatch(
            Connection con,
            int levelId,
            JsonArray visitas
    ) throws SQLException {
        String[] columnas = {
                "h_mt_visita", "h_nvisita", "h_fvisita", "h_hvisita", "h_rvisita", "h_control"
        };

        String[][] aliases = {
                {"H_MT_VISITA"}, {"H_NVISITA"}, {"H_FVISITA"}, {"H_HVISITA"}, {"H_RVISITA"}, {"H_CONTROL"}
        };

        boolean[] usarCatalogo = {
                false, false, false, false, true, false
        };

        insertarRegistrosRepetidos(
                con,
                "visita",
                levelId,
                visitas,
                columnas,
                aliases,
                usarCatalogo,
                "VISITA",
                null
        );
    }

    private static void insertarMortalidadBatch(
            Connection con,
            int levelId,
            JsonArray mortalidad
    ) throws SQLException {
        String[] columnas = {
                "h_orden_m", "h_m02_mort_nombre", "h_m03_mort_sexo", "h_m04_mort_edad",
                "h_m04_mes", "h_m04_an", "h_m05_mort_rnp", "h_mm01_mort_causa",
                "h_mm02_mort_emb", "h_add_another_mortalidad", "h_m_keep_row"
        };

        String[][] aliases = {
                {"H_ORDEN_M"}, {"H_M02_MORT_NOMBRE"}, {"H_M03_MORT_SEXO"}, {"H_M04_MORT_EDAD"},
                {"H_M04_MES"}, {"H_M04_AN"}, {"H_M05_MORT_RNP"}, {"H_MM01_MORT_CAUSA"},
                {"H_MM02_MORT_EMB"}, {"H_ADD_ANOTHER_MORTALIDAD"}, {"H_M_KEEP_ROW"}
        };

        boolean[] usarCatalogo = {
                false, false, true, false,
                true, false, true, true,
                true, false, false
        };

        insertarRegistrosRepetidos(
                con,
                "mortalidad_rec",
                levelId,
                mortalidad,
                columnas,
                aliases,
                usarCatalogo,
                "MORTALIDAD_REC",
                new String[]{"H_M_KEEP_ROW"}
        );
    }

    private static void insertarMetadatos(
            Connection con,
            int levelId,
            JsonObject metadatos,
            JsonArray dispositivos,
            JsonArray visitas
    ) throws SQLException {
        String sql = "INSERT INTO cnpv_data.metadatos_rec("
                + "`level-1-id`, h_fin, h_start_interview_time, h_latitud, h_longitud, h_end_interview_time, "
                + "h_concluir_entrevista, h_cod_ald, h_cod_caserio, h_cod_barrio, h_manzana, h_ur_area, "
                + "h_corpre_mun_segmento, h_tipo, h_cod_tipo, h_geocodigo, h_geocod_estructura, h_latitud_n, "
                + "h_longitud_n, h_distancia, h_error, h_sector, h_zona, h_device_id, h_recorrido) "
                + "VALUES(" + crearPlaceholders(25) + ")";

        String fin = obtenerValorJson(metadatos, "H_FIN");
        if (fin == null) {
            fin = obtenerUltimoValorArreglo(visitas, "H_FIN");
        }

        String deviceId = obtenerValorJson(metadatos, "H_DEVICE_ID");
        if (deviceId == null) {
            deviceId = obtenerPrimerValorArreglo(dispositivos, "H_DEVICE_ID");
        }

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            int idx = 1;
            stmt.setInt(idx++, levelId);
            asignarTextoNullable(stmt, idx++, fin);
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_START_INTERVIEW_TIME"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_LATITUD"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_LONGITUD"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_END_INTERVIEW_TIME"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_CONCLUIR_ENTREVISTA"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_COD_ALD"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_COD_CASERIO"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_COD_BARRIO"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_MANZANA"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_UR_AREA"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_CORPRE_MUN_SEGMENTO"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_TIPO"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_COD_TIPO"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_GEOCODIGO"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_GEOCOD_ESTRUCTURA"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_LATITUD_N"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_LONGITUD_N"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_DISTANCIA"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_ERROR"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_SECTOR"));
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_ZONA"));
            asignarTextoNullable(stmt, idx++, deviceId);
            asignarTextoNullable(stmt, idx++, obtenerValorJson(metadatos, "H_RECORRIDO"));
            stmt.executeUpdate();
        }
    }

    private static void insertarRegistroSimple(
            Connection con,
            String tabla,
            int levelId,
            JsonObject registro,
            String[] columnas,
            String[][] aliases,
            boolean[] usarCatalogo,
            String nombreRegistroCatalogo
    ) throws SQLException {
        validarConfiguracionMapeo(columnas, aliases, usarCatalogo, tabla);

        StringBuilder nombresColumnas = new StringBuilder("`level-1-id`");
        for (String columna : columnas) {
            nombresColumnas.append(", `").append(columna).append("`");
        }

        String sql = "INSERT INTO cnpv_data.`" + tabla + "`("
                + nombresColumnas
                + ") VALUES ("
                + crearPlaceholders(columnas.length + 1)
                + ")";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            int idx = 1;
            stmt.setInt(idx++, levelId);

            for (int i = 0; i < columnas.length; i++) {
                String valor = obtenerValorJson(registro, aliases[i]);
                if (usarCatalogo[i]) {
                    valor = obtenerTextoCatalogoOValor(
                            nombreRegistroCatalogo,
                            aliases[i][0],
                            valor
                    );
                }
                asignarTextoNullable(stmt, idx++, valor);
            }

            stmt.executeUpdate();
        }
    }

    private static void insertarRegistrosRepetidos(
            Connection con,
            String tabla,
            int levelId,
            JsonArray registros,
            String[] columnas,
            String[][] aliases,
            boolean[] usarCatalogo,
            String nombreRegistroCatalogo,
            String[] campoMantenerFila
    ) throws SQLException {
        if (registros == null || registros.size() == 0) {
            return;
        }

        validarConfiguracionMapeo(columnas, aliases, usarCatalogo, tabla);

        StringBuilder nombresColumnas = new StringBuilder("`level-1-id`, occ");
        for (String columna : columnas) {
            nombresColumnas.append(", `").append(columna).append("`");
        }

        String sql = "INSERT INTO cnpv_data.`" + tabla + "`("
                + nombresColumnas
                + ") VALUES ("
                + crearPlaceholders(columnas.length + 2)
                + ")";

        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            int occ = 1;

            for (JsonElement elemento : registros) {
                if (elemento == null || !elemento.isJsonObject()) {
                    continue;
                }

                JsonObject registro = elemento.getAsJsonObject();
                if (campoMantenerFila != null) {
                    int mantenerFila = obtenerEnteroJson(registro, 1, campoMantenerFila);
                    if (mantenerFila != 1) {
                        continue;
                    }
                }

                int idx = 1;
                stmt.setInt(idx++, levelId);
                stmt.setInt(idx++, occ++);

                for (int i = 0; i < columnas.length; i++) {
                    String valor = obtenerValorJson(registro, aliases[i]);
                    if (usarCatalogo[i]) {
                        valor = obtenerTextoCatalogoOValor(
                                nombreRegistroCatalogo,
                                aliases[i][0],
                                valor
                        );
                    }
                    asignarTextoNullable(stmt, idx++, valor);
                }

                stmt.addBatch();
            }

            stmt.executeBatch();
        }
    }

    private static void validarConfiguracionMapeo(
            String[] columnas,
            String[][] aliases,
            boolean[] usarCatalogo,
            String tabla
    ) throws SQLException {
        if (columnas.length != aliases.length || columnas.length != usarCatalogo.length) {
            throw new SQLException("Configuración inválida de mapeo para " + tabla);
        }
    }

    private static JsonObject obtenerIdentificacion(
            Gson gson,
            JsonObject cuestionarioJson
    ) {
        JsonObject identificacion = obtenerObjetoJson(cuestionarioJson, "id");

        if (identificacion != null) {
            return identificacion;
        }

        if (cuestionarioJson == null || !cuestionarioJson.has("level-1")) {
            return null;
        }

        JsonElement nivelElemento = cuestionarioJson.get("level-1");

        if (nivelElemento == null || nivelElemento.isJsonNull()) {
            return null;
        }

        try {
            JsonObject nivel;

            if (nivelElemento.isJsonObject()) {
                nivel = nivelElemento.getAsJsonObject();
            } else if (nivelElemento.isJsonPrimitive()
                    && nivelElemento.getAsJsonPrimitive().isString()) {
                nivel = gson.fromJson(nivelElemento.getAsString(), JsonObject.class);
            } else {
                return null;
            }

            return obtenerObjetoJson(nivel, "id");
        } catch (Exception e) {
            return null;
        }
    }

    private static JsonObject obtenerObjetoJson(JsonObject objeto, String nombre) {
        if (objeto == null || !objeto.has(nombre)) {
            return null;
        }

        JsonElement elemento = objeto.get(nombre);
        if (elemento == null || elemento.isJsonNull() || !elemento.isJsonObject()) {
            return null;
        }

        return elemento.getAsJsonObject();
    }

    private static JsonArray obtenerArregloJson(JsonObject objeto, String nombre) {
        if (objeto == null || !objeto.has(nombre)) {
            return null;
        }

        JsonElement elemento = objeto.get(nombre);
        if (elemento == null || elemento.isJsonNull() || !elemento.isJsonArray()) {
            return null;
        }

        return elemento.getAsJsonArray();
    }

    private static String construirCaseId(JsonObject identificacion) throws SQLException {
        String[] campos = {
            "L1_DEPARTAMENTO",
            "L1_MUNICIPIO",
            "L1_ALDEA",
            "L1_CASERIO",
            "L1_APOYO_MUNI",
            "L1_ZONA",
            "L1_SECTOR",
            "L1_SEGMENTO",
            "L1_COD_ENCUESTADOR",
            "L1_ESTRUCTURA",
            "L1_VIVIENDA",
            "L1_HOGAR",
            "L1_ID_ASIG_GTI"
        };

        StringBuilder caseId = new StringBuilder();

        for (String campo : campos) {
            String valor = obtenerValorJson(identificacion, campo);

            if (valor == null || valor.trim().isEmpty()) {
                throw new SQLException(
                        "No se puede construir caseids: falta el campo " + campo
                );
            }

            caseId.append(valor);
        }

        return caseId.toString();
    }

    private static String obtenerValorJson(JsonObject objeto, String... nombres) {
        if (objeto == null || nombres == null) {
            return null;
        }

        for (String nombre : nombres) {
            if (nombre == null || !objeto.has(nombre)) {
                continue;
            }

            JsonElement elemento = objeto.get(nombre);
            if (elemento == null || elemento.isJsonNull()) {
                return null;
            }

            if (elemento.isJsonPrimitive()) {
                return elemento.getAsString();
            }

            return elemento.toString();
        }

        return null;
    }

    private static int obtenerEnteroJson(
            JsonObject objeto,
            int valorPredeterminado,
            String... nombres
    ) {
        String valor = obtenerValorJson(objeto, nombres);
        if (valor == null || valor.trim().isEmpty()) {
            return valorPredeterminado;
        }

        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            return valorPredeterminado;
        }
    }

    private static String obtenerTextoCatalogoOValor(
            String registro,
            String campo,
            String valor
    ) {
        if (valor == null) {
            return null;
        }

        String texto = Catalogo.obtenerTexto(registro, campo, valor);
        if (texto == null || texto.trim().isEmpty()) {
            // Nunca perder el código original cuando el catálogo no lo reconoce.
            return valor;
        }

        return texto;
    }

    private static String obtenerPrimerValorArreglo(JsonArray arreglo, String campo) {
        if (arreglo == null) {
            return null;
        }

        for (JsonElement elemento : arreglo) {
            if (elemento != null && elemento.isJsonObject()) {
                String valor = obtenerValorJson(elemento.getAsJsonObject(), campo);
                if (valor != null) {
                    return valor;
                }
            }
        }

        return null;
    }

    private static String obtenerUltimoValorArreglo(JsonArray arreglo, String campo) {
        if (arreglo == null) {
            return null;
        }

        for (int i = arreglo.size() - 1; i >= 0; i--) {
            JsonElement elemento = arreglo.get(i);
            if (elemento != null && elemento.isJsonObject()) {
                String valor = obtenerValorJson(elemento.getAsJsonObject(), campo);
                if (valor != null) {
                    return valor;
                }
            }
        }

        return null;
    }

    private static void asignarTextoNullable(
            PreparedStatement stmt,
            int indice,
            String valor
    ) throws SQLException {
        if (valor == null) {
            stmt.setNull(indice, Types.LONGVARCHAR);
        } else {
            stmt.setString(indice, valor);
        }
    }

    private static String crearPlaceholders(int cantidad) {
        StringBuilder resultado = new StringBuilder();
        for (int i = 0; i < cantidad; i++) {
            if (i > 0) {
                resultado.append(',');
            }
            resultado.append('?');
        }
        return resultado.toString();
    }

    private static BeletaInfo leerBeletaDeBD(Connection con, String guid) throws SQLException {
        String sql = "SELECT guid, questionnaire, hash FROM boletas_cambios WHERE guid = ?";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, guid);
        ResultSet rs = stmt.executeQuery();
        BeletaInfo info = null;
        if (rs.next()) {
            info = new BeletaInfo();
            info.guid = rs.getString("guid");
            info.questionnaire = rs.getString("questionnaire");
            info.hash = rs.getString("hash");
        }
        rs.close();
        stmt.close();
        return info;
    }
}