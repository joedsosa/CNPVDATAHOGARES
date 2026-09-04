
package ine.gob.hn.vaciadolistener.cnpv;

import java.util.HashMap;
import java.util.Map;

public class Catalogo {

    // Mapa que contiene todos los catálogos
    private static final Map<String, Map<String, Map<String, String>>> catalogos = new HashMap<>();

    static {
        Map<String, String> departamentoValores = new HashMap<>();
        departamentoValores.put("01", "Atlántida");
        departamentoValores.put("02", "Colon");
        departamentoValores.put("03", "Comayagua");
        departamentoValores.put("04", "Copan");
        departamentoValores.put("05", "Cortes");
        departamentoValores.put("06", "Choluteca");
        departamentoValores.put("07", "El Paraíso");
        departamentoValores.put("08", "Francisco Morazán");
        departamentoValores.put("09", "Gracias A Dios");
        departamentoValores.put("10", "Intubuca");
        departamentoValores.put("11", "Islas De La Bahía");
        departamentoValores.put("12", "La Paz");
        departamentoValores.put("13", "Lempira");
        departamentoValores.put("14", "Ocotepeque");
        departamentoValores.put("15", "Olancho");
        departamentoValores.put("16", "Santa Bárbara");
        departamentoValores.put("17", "Valle");
        departamentoValores.put("18", "Yoro");

        Map<String, Map<String, String>> grupoID = new HashMap<>();
        grupoID.put("H_DEPARTAMENTO", departamentoValores);

        catalogos.put("ID", grupoID);
        
        // Rellenamos el catálogo para VIVIENDA_REC y H_V01_TIPO_VIV
        Map<String, String> viviendaTipos = new HashMap<>();
        viviendaTipos.put("1", "Casa Independiente");
        viviendaTipos.put("2", "Apartamento");
        viviendaTipos.put("3", "Cuarto en meson o cuarteria");
        viviendaTipos.put("4", "Local no construido para vivienda");
        viviendaTipos.put("5", "Rancho (de materiales naturales)");
        viviendaTipos.put("6", "Casa improvisada (material de desecho)");
        viviendaTipos.put("7", "Otro tipo de vivienda particular");
        viviendaTipos.put("8", "Hotel, pensión, casa de huéspedes");
        viviendaTipos.put("9", "Hospital, sanatorio o clínica");
        viviendaTipos.put("10", "Orfanato");
        viviendaTipos.put("11", "Asilo");
        viviendaTipos.put("12", "Cuartel, batallón o posta policial");
        viviendaTipos.put("13", "Prisión o reformatorio");
        viviendaTipos.put("14", "Otro tipo de vivienda colectiva");

        Map<String, Map<String, String>> grupoViviendaRec = new HashMap<>();
        grupoViviendaRec.put("H_V01_TIPO_VIV", viviendaTipos);

        
        
        Map<String, String> paredesVivValores = new HashMap<>();
        paredesVivValores.put("1", "Bloque o ladrillo");
        paredesVivValores.put("2", "Adobe");
        paredesVivValores.put("3", "Madera ");
        paredesVivValores.put("4", "Piedra rajada o cantera");
        paredesVivValores.put("5", "Bahareque, vara, palo, caña o similar");
        paredesVivValores.put("6", "Material prefabricado");
        paredesVivValores.put("7", "Material de desecho");
        paredesVivValores.put("8", "Otro");

        
        grupoViviendaRec.put("H_V02_PAREDES_VIV", paredesVivValores);

        
        
        Map<String, String> techoVivValores = new HashMap<>();
        techoVivValores.put("1", "Lamina de zinc");
        techoVivValores.put("2", "Teja de barro");
        techoVivValores.put("3", "Lámina de aluzinc");
        techoVivValores.put("4", "Lamina de asbesto");
        techoVivValores.put("5", "Concreto");
        techoVivValores.put("6", "Madera, paja, palma o similar");
        techoVivValores.put("7", "Material de desecho");
        techoVivValores.put("8", "Otro");

        //Map<String, Map<String, String>> grupoViviendaRec = new HashMap<>();
        grupoViviendaRec.put("H_V03_TECHO_VIV", techoVivValores);

        
        
        Map<String, String> ocupVivValores = new HashMap<>();
        ocupVivValores.put("1", "Con personas presentes");
        ocupVivValores.put("2", "Con personas ausentes");
        ocupVivValores.put("3", "Para alquilar o vender");
        ocupVivValores.put("4", "De uso temporal");
        ocupVivValores.put("5", "En construcción o reparación");
        ocupVivValores.put("6", "Destruida o inhabitable");
        ocupVivValores.put("7", "Otro");

        //Map<String, Map<String, String>> grupoViviendaRec = new HashMap<>();
        grupoViviendaRec.put("H_V04_OCUP_VIV", ocupVivValores);

        
        
         Map<String, String> pisoVivValores = new HashMap<>();
        pisoVivValores.put("1", "Ladrillo de cemento, cerámica, granito o barro, parqué");
        pisoVivValores.put("2", "Plancha de cemento");
        pisoVivValores.put("3", "Tablones de madera");
        pisoVivValores.put("4", "Tierra");
        pisoVivValores.put("5", "Otro");

        //Map<String, Map<String, String>> grupoViviendaRec = new HashMap<>();
        grupoViviendaRec.put("H_V05_PISO_VIV", pisoVivValores);

        
        
        Map<String, String> dispoAguaValores = new HashMap<>();
        dispoAguaValores.put("1", "Por tuberia dentro de la vivienda");
        dispoAguaValores.put("2", "Por tuberia fuera de la vivienda, pero dentro del edificio lote o propiedad");
        dispoAguaValores.put("3", "Por tuberia fuera del edificio, lote o propiedad");
        dispoAguaValores.put("4", "No recibe agua por tuberia sino por otros medios");

        //Map<String, Map<String, String>> grupoViviendaRec = new HashMap<>();
        grupoViviendaRec.put("H_V06_DISPO_AGUA", dispoAguaValores);

        
        
        Map<String, String> provAguaValores = new HashMap<>();
        provAguaValores.put("1", "Del sistema público");
        provAguaValores.put("2", "Del sistema privado");
        provAguaValores.put("3", "De pozo con bomba");
        provAguaValores.put("4", "De pozo malacate");
        provAguaValores.put("5", "De otra vivienda");
        provAguaValores.put("6", "De río, riachuelo, manantial, ojo de agua, etc.");
        provAguaValores.put("7", "De vendedor o repartidor ambulante");
        provAguaValores.put("8", "Otro");

        //Map<String, Map<String, String>> grupoViviendaRec = new HashMap<>();
        grupoViviendaRec.put("H_V07_PROV_AGUA", provAguaValores);

        
        
        Map<String, String> accAlumValores = new HashMap<>();
        accAlumValores.put("1", "Electricidad del sistema público");
        accAlumValores.put("2", "Electricidad del sistema privado");
        accAlumValores.put("3", "Electricidad de motor propio");
        accAlumValores.put("4", "Candil o lámpara de gas (kerosene)");
        accAlumValores.put("5", "Vela");
        accAlumValores.put("6", "Ocote");
        accAlumValores.put("7", "Panel solar");
        accAlumValores.put("8", "Otro");

        //Map<String, Map<String, String>> grupoViviendaRec = catalogos.getOrDefault("VIVIENDA_REC", new HashMap<>());
        grupoViviendaRec.put("H_V08_ACC_ALUM", accAlumValores);
        
        
        
        Map<String, String> elimBasuraValores = new HashMap<>();
        elimBasuraValores.put("1", "Recolección domiciliaria (tren de aseo)");
        elimBasuraValores.put("2", "La deposita en contenedores");
        elimBasuraValores.put("3", "Paga a particulares para que la boten");
        elimBasuraValores.put("4", "La prepara para abono");
        elimBasuraValores.put("5", "La quema");
        elimBasuraValores.put("6", "La entierra");
        elimBasuraValores.put("7", "La tira en cualquier lugar");
        elimBasuraValores.put("8", "Otro");

        //Map<String, Map<String, String>> grupoViviendaRec = catalogos.getOrDefault("VIVIENDA_REC", new HashMap<>());
        grupoViviendaRec.put("H_V09_ELIM_BASURA", elimBasuraValores);
        
        Map<String, String> idenHogarValores = new HashMap<>();
        idenHogarValores.put("1", "Si, es un solo hogar");
        idenHogarValores.put("2", "No, hay más de un hogar");

        //Map<String, Map<String, String>> grupoViviendaRec = catalogos.getOrDefault("VIVIENDA_REC", new HashMap<>());
        grupoViviendaRec.put("H_V11_IDEN_HOGAR", idenHogarValores);
        catalogos.put("VIVIENDA_REC", grupoViviendaRec);
        
        Map<String, String> siNoValores = new HashMap<>();
        siNoValores.put("1", "Sí");
        siNoValores.put("2", "No");

        Map<String, Map<String, String>> grupoSiNo = catalogos.getOrDefault("SINO", new HashMap<>());
        grupoSiNo.put("SINO", siNoValores);
        catalogos.put("SINO", grupoSiNo);
        
        Map<String, String> cocinarCon = new HashMap<>();
        cocinarCon.put("1", "Leña");
        cocinarCon.put("2", "Gas (kerosene)");
        cocinarCon.put("3", "Gas propano (LPG)");
        cocinarCon.put("4", "Electricidad");
        cocinarCon.put("5", "Biogas");
        cocinarCon.put("6", "Otro");
        cocinarCon.put("7", "No cocina");
        
        Map<String, Map<String, String>> grupoHogaresRec = new HashMap<>();
        grupoHogaresRec.put("H_H04_COCINAN_CON", cocinarCon);
        
        
        Map<String, String> sanitarioHogar = new HashMap<>();
        sanitarioHogar.put("1", "Inodoro conectado a alcantarilla");
        sanitarioHogar.put("2", "Inodoro conectado a pozo séptico");
        sanitarioHogar.put("3", "Inodoro con desagüe al río, laguna, mar o lago");
        sanitarioHogar.put("4", "Taza campesina (agua tirada con balde)");
        sanitarioHogar.put("5", "Letrina con pozo séptico");
        sanitarioHogar.put("6", "Letrina con pozo negro");
        sanitarioHogar.put("7", "Otro");
        sanitarioHogar.put("8", "No tiene");

        grupoHogaresRec.put("H_H05_SANIT_HOG", sanitarioHogar);
        
        
        Map<String, String> tenenciaVivienda = new HashMap<>();
        tenenciaVivienda.put("1", "Propia");
        tenenciaVivienda.put("2", "Propia, pagando a plazos");
        tenenciaVivienda.put("3", "Propia, recuperada sin legalizar");
        tenenciaVivienda.put("4", "Alquilada");
        tenenciaVivienda.put("5", "Prestada o cedida sin pago");
        tenenciaVivienda.put("6", "Cedida por servicios de trabajo");

        grupoHogaresRec.put("H_H08_TEN_VIVI", tenenciaVivienda);
        
        Map<String, String> remesas = new HashMap<>();
        remesas.put("1", "Si");
        remesas.put("2", "No");
        remesas.put("3", "No sabe/No responde");

        grupoHogaresRec.put("H_E06_REME_ANIO", remesas);
        
        Map<String, String> lugarNacimientoMunicipio = new HashMap<>();
        lugarNacimientoMunicipio.put("0101", "0101 La Ceiba");
        lugarNacimientoMunicipio.put("0102", "0102 El Porvenir");
        lugarNacimientoMunicipio.put("0103", "0103 Esparta");
        lugarNacimientoMunicipio.put("0104", "0104 Jutiapa");
        lugarNacimientoMunicipio.put("0105", "0105 La Masica");
        lugarNacimientoMunicipio.put("0106", "0106 San Francisco");
        lugarNacimientoMunicipio.put("0107", "0107 Tela");
        lugarNacimientoMunicipio.put("0108", "0108 Arizona");
        lugarNacimientoMunicipio.put("0201", "0201 Trujillo");
        lugarNacimientoMunicipio.put("0202", "0202 Balfate");
        lugarNacimientoMunicipio.put("0203", "0203 Iriona");
        lugarNacimientoMunicipio.put("0204", "0204 Limon");
        lugarNacimientoMunicipio.put("0205", "0205 Santa Fe");
        lugarNacimientoMunicipio.put("0206", "0206 Santa Rosa de Aguan");
        lugarNacimientoMunicipio.put("0207", "0207 Sonaguera");
        lugarNacimientoMunicipio.put("0208", "0208 Saba");
        lugarNacimientoMunicipio.put("0209", "0209 Tocoa");
        lugarNacimientoMunicipio.put("0210", "0210 Bonito oriental");
        lugarNacimientoMunicipio.put("0301", "0301 Comayagua");
        lugarNacimientoMunicipio.put("0302", "0302 Ajuterique");
        lugarNacimientoMunicipio.put("0303", "0303 El Rosario");
        lugarNacimientoMunicipio.put("0304", "0304 Esquias");
        lugarNacimientoMunicipio.put("0305", "0305 Humuya");
        lugarNacimientoMunicipio.put("0306", "0306 La Libertad");
        lugarNacimientoMunicipio.put("0307", "0307 Lamani");
        lugarNacimientoMunicipio.put("0308", "0308 La Trinidad");
        lugarNacimientoMunicipio.put("0309", "0309 Lejamani");
        lugarNacimientoMunicipio.put("0310", "0310 Meambar");
        lugarNacimientoMunicipio.put("0311", "0311 Minas de oro");
        lugarNacimientoMunicipio.put("0312", "0312 ojos de Agua");
        lugarNacimientoMunicipio.put("0313", "0313 San Jeronimo");
        lugarNacimientoMunicipio.put("0314", "0314 San Jose de Comayagua");
        lugarNacimientoMunicipio.put("0315", "0315 San Jose del Potrero");
        lugarNacimientoMunicipio.put("0316", "0316 San Luis");
        lugarNacimientoMunicipio.put("0317", "0317 San Sebastian");
        lugarNacimientoMunicipio.put("0318", "0318 Siguatepeque");
        lugarNacimientoMunicipio.put("0319", "0319 Villa de San Antonio");
        lugarNacimientoMunicipio.put("0320", "0320 Las Lajas");
        lugarNacimientoMunicipio.put("0321", "0321 Taulabe");
        
        lugarNacimientoMunicipio.put("0401", "0401 Santa Rosa de Copan");
        lugarNacimientoMunicipio.put("0402", "0402 Cabanas");
        lugarNacimientoMunicipio.put("0403", "0403 Concepcion");
        lugarNacimientoMunicipio.put("0404", "0404 Copan Ruinas");
        lugarNacimientoMunicipio.put("0405", "0405 Corquin");
        lugarNacimientoMunicipio.put("0406", "0406 Cucuyagua");
        lugarNacimientoMunicipio.put("0407", "0407 Dolores");
        lugarNacimientoMunicipio.put("0408", "0408 Dulce Nombre");
        lugarNacimientoMunicipio.put("0409", "0409 El Paraiso");
        lugarNacimientoMunicipio.put("0410", "0410 Florida");
        lugarNacimientoMunicipio.put("0411", "0411 La Jigua");
        lugarNacimientoMunicipio.put("0412", "0412 La Union");
        lugarNacimientoMunicipio.put("0413", "0413 Nueva Arcadia");
        lugarNacimientoMunicipio.put("0414", "0414 San Agustin");
        lugarNacimientoMunicipio.put("0415", "0415 San Antonio");
        lugarNacimientoMunicipio.put("0416", "0416 San Jeronimo");
        lugarNacimientoMunicipio.put("0417", "0417 San Jose");
        lugarNacimientoMunicipio.put("0418", "0418 San Juan de opoa");
        lugarNacimientoMunicipio.put("0419", "0419 San Nicolas");
        lugarNacimientoMunicipio.put("0420", "0420 San Pedro");
        lugarNacimientoMunicipio.put("0421", "0421 Santa Rita");
        lugarNacimientoMunicipio.put("0422", "0422 Trinidad de Copan");
        lugarNacimientoMunicipio.put("0423", "0423 Veracruz");
        lugarNacimientoMunicipio.put("0501", "0501 San Pedro Sula");
        lugarNacimientoMunicipio.put("0502", "0502 Choloma");
        lugarNacimientoMunicipio.put("0503", "0503 Omoa");
        lugarNacimientoMunicipio.put("0504", "0504 Pimienta");
        lugarNacimientoMunicipio.put("0505", "0505 Potrerillos");
        lugarNacimientoMunicipio.put("0506", "0506 Puerto Cortes");
        lugarNacimientoMunicipio.put("0507", "0507 San Antonio de Cortes");
        lugarNacimientoMunicipio.put("0508", "0508 San Francisco de Yojoa");
        lugarNacimientoMunicipio.put("0509", "0509 San Manuel");
        lugarNacimientoMunicipio.put("0510", "0510 Santa Cruz de Yojoa");
        lugarNacimientoMunicipio.put("0511", "0511 Villanueva");
        lugarNacimientoMunicipio.put("0512", "0512 La Lima");
        lugarNacimientoMunicipio.put("0601", "0601 Choluteca");
        lugarNacimientoMunicipio.put("0602", "0602 Apacilagua");
        lugarNacimientoMunicipio.put("0603", "0603 Concepcion de Maria");
        lugarNacimientoMunicipio.put("0604", "0604 Duyure");
        lugarNacimientoMunicipio.put("0605", "0605 El Corpus");
        lugarNacimientoMunicipio.put("0606", "0606 El Triunfo");
        lugarNacimientoMunicipio.put("0607", "0607 Marcovia");
        lugarNacimientoMunicipio.put("0608", "0608 Morolica");
        lugarNacimientoMunicipio.put("0609", "0609 Namasigue");
        lugarNacimientoMunicipio.put("0610", "0610 Orocuina");
        lugarNacimientoMunicipio.put("0611", "0611 Pespire");
        lugarNacimientoMunicipio.put("0612", "0612 San Antonio de Flores");
        lugarNacimientoMunicipio.put("0613", "0613 San Isidro");
        lugarNacimientoMunicipio.put("0614", "0614 San Jose");
        lugarNacimientoMunicipio.put("0615", "0615 San Marcos de Colon");
        lugarNacimientoMunicipio.put("0616", "0616 Santa Ana de Yusguare");
        
        lugarNacimientoMunicipio.put("0701", "0701 Yuscaran");
        lugarNacimientoMunicipio.put("0702", "0702 Alauca");
        lugarNacimientoMunicipio.put("0703", "0703 Danli");
        lugarNacimientoMunicipio.put("0704", "0704 El Paraiso");
        lugarNacimientoMunicipio.put("0705", "0705 Guinope");
        lugarNacimientoMunicipio.put("0706", "0706 Jacaleapa");
        lugarNacimientoMunicipio.put("0707", "0707 Liure");
        lugarNacimientoMunicipio.put("0708", "0708 Moroceli");
        lugarNacimientoMunicipio.put("0709", "0709 Oropoli");
        lugarNacimientoMunicipio.put("0710", "0710 Potrerillos");
        lugarNacimientoMunicipio.put("0711", "0711 San Antonio de Flores");
        lugarNacimientoMunicipio.put("0712", "0712 San Lucas");
        lugarNacimientoMunicipio.put("0713", "0713 San Matias");
        lugarNacimientoMunicipio.put("0714", "0714 Soledad");
        lugarNacimientoMunicipio.put("0715", "0715 Teupasenti");
        lugarNacimientoMunicipio.put("0716", "0716 Texiguat");
        lugarNacimientoMunicipio.put("0717", "0717 Vado Ancho");
        lugarNacimientoMunicipio.put("0718", "0718 Yauyupe");
        lugarNacimientoMunicipio.put("0719", "0719 Trojes");
        lugarNacimientoMunicipio.put("0801", "0801 Distrito Central");
        lugarNacimientoMunicipio.put("0802", "0802 Alubaren");
        lugarNacimientoMunicipio.put("0803", "0803 Cedros");
        lugarNacimientoMunicipio.put("0804", "0804 Curaren");
        lugarNacimientoMunicipio.put("0805", "0805 El Porvenir");
        lugarNacimientoMunicipio.put("0806", "0806 Guaimaca");
        lugarNacimientoMunicipio.put("0807", "0807 La Libertad");
        lugarNacimientoMunicipio.put("0808", "0808 La Venta");
        lugarNacimientoMunicipio.put("0809", "0809 Lepaterique");
        lugarNacimientoMunicipio.put("0810", "0810 Maraita");
        lugarNacimientoMunicipio.put("0811", "0811 Marale");
        lugarNacimientoMunicipio.put("0812", "0812 Nueva Armenia");
        lugarNacimientoMunicipio.put("0813", "0813 Ojojona");
        lugarNacimientoMunicipio.put("0814", "0814 Orica");
        lugarNacimientoMunicipio.put("0815", "0815 Reitoca");
        lugarNacimientoMunicipio.put("0816", "0816 Sabanagrande");
        lugarNacimientoMunicipio.put("0817", "0817 San Antonio de Oriente");
        lugarNacimientoMunicipio.put("0818", "0818 San Buenaventura");
        lugarNacimientoMunicipio.put("0819", "0819 San Ignacio");
        lugarNacimientoMunicipio.put("0820", "0820 San Juan de Flores");
        lugarNacimientoMunicipio.put("0821", "0821 San Miguelito");
        lugarNacimientoMunicipio.put("0822", "0822 Santa Ana");
        lugarNacimientoMunicipio.put("0823", "0823 Santa Lucia");
        lugarNacimientoMunicipio.put("0824", "0824 Talanga");
        lugarNacimientoMunicipio.put("0825", "0825 Tatumbla");
        lugarNacimientoMunicipio.put("0826", "0826 Valle de Angeles");
        lugarNacimientoMunicipio.put("0827", "0827 Villa de San Francisco");
        lugarNacimientoMunicipio.put("0828", "0828 Vallecillo");
        lugarNacimientoMunicipio.put("0901", "0901 Puerto Lempira");
        lugarNacimientoMunicipio.put("0902", "0902 Brus Laguna");
        lugarNacimientoMunicipio.put("0903", "0903 Juan Francisco Bulnes");
        lugarNacimientoMunicipio.put("0904", "0904 Ahuas");
        lugarNacimientoMunicipio.put("0905", "0905 Wampusirpi");
        lugarNacimientoMunicipio.put("0906", "0906 Villeda Morales");
        lugarNacimientoMunicipio.put("1001", "1001 La Esperanza");
        lugarNacimientoMunicipio.put("1002", "1002 Camasca");
        lugarNacimientoMunicipio.put("1003", "1003 Colomoncagua");
        lugarNacimientoMunicipio.put("1004", "1004 Concepcion");
        lugarNacimientoMunicipio.put("1005", "1005 Dolores");
        lugarNacimientoMunicipio.put("1006", "1006 Intibuca");
        lugarNacimientoMunicipio.put("1007", "1007 Jesus de Otoro");
        lugarNacimientoMunicipio.put("1008", "1008 Magdalena");
        lugarNacimientoMunicipio.put("1009", "1009 Masaguara");
        lugarNacimientoMunicipio.put("1010", "1010 San Antonio");
        lugarNacimientoMunicipio.put("1011", "1011 San Isidro");
        lugarNacimientoMunicipio.put("1012", "1012 San Juan");
        lugarNacimientoMunicipio.put("1013", "1013 San Marcos de La Sierra");
        lugarNacimientoMunicipio.put("1014", "1014 San Miguelito");
        lugarNacimientoMunicipio.put("1015", "1015 Santa Lucia");
        lugarNacimientoMunicipio.put("1016", "1016 Yamaranguila");
        lugarNacimientoMunicipio.put("1017", "1017 San Francisco de Opalaca");
        
        lugarNacimientoMunicipio.put("1101", "1101 Roatan");
        lugarNacimientoMunicipio.put("1102", "1102 Guanaja");
        lugarNacimientoMunicipio.put("1103", "1103 Jose Santos Guardiola");
        lugarNacimientoMunicipio.put("1104", "1104 Utila");
        lugarNacimientoMunicipio.put("1201", "1201 La Paz");
        lugarNacimientoMunicipio.put("1202", "1202 Aguanqueterique");
        lugarNacimientoMunicipio.put("1203", "1203 Cabanas");
        lugarNacimientoMunicipio.put("1204", "1204 Cane");
        lugarNacimientoMunicipio.put("1205", "1205 Chinacla");
        lugarNacimientoMunicipio.put("1206", "1206 Guajiquiro");
        lugarNacimientoMunicipio.put("1207", "1207 Lauterique");
        lugarNacimientoMunicipio.put("1208", "1208 Marcala");
        lugarNacimientoMunicipio.put("1209", "1209 Mercedes de Oriente");
        lugarNacimientoMunicipio.put("1210", "1210 Opatoro");
        lugarNacimientoMunicipio.put("1211", "1211 San Antonio del Norte");
        lugarNacimientoMunicipio.put("1212", "1212 San Jose");
        lugarNacimientoMunicipio.put("1213", "1213 San Juan");
        lugarNacimientoMunicipio.put("1214", "1214 San Pedro de Tutule");
        lugarNacimientoMunicipio.put("1215", "1215 Santa Ana");
        lugarNacimientoMunicipio.put("1216", "1216 Santa Elena");
        lugarNacimientoMunicipio.put("1217", "1217 Santa Maria");
        lugarNacimientoMunicipio.put("1218", "1218 Santiago de Puringla");
        lugarNacimientoMunicipio.put("1219", "1219 Yarula");
        lugarNacimientoMunicipio.put("1301", "1301 Gracias");
        lugarNacimientoMunicipio.put("1302", "1302 Belen");
        lugarNacimientoMunicipio.put("1303", "1303 Candelaria");
        lugarNacimientoMunicipio.put("1304", "1304 Cololaca");
        lugarNacimientoMunicipio.put("1305", "1305 Erandique");
        lugarNacimientoMunicipio.put("1306", "1306 Gualcince");
        lugarNacimientoMunicipio.put("1307", "1307 Guarita");
        lugarNacimientoMunicipio.put("1308", "1308 La Campa");
        lugarNacimientoMunicipio.put("1309", "1309 La Iguala");
        lugarNacimientoMunicipio.put("1310", "1310 Las Flores");
        lugarNacimientoMunicipio.put("1311", "1311 La Union");
        lugarNacimientoMunicipio.put("1312", "1312 La Virtud");
        lugarNacimientoMunicipio.put("1313", "1313 Lepaera");
        lugarNacimientoMunicipio.put("1314", "1314 Mapulaca");
        lugarNacimientoMunicipio.put("1315", "1315 Piraera");
        lugarNacimientoMunicipio.put("1316", "1316 San Andres");
        lugarNacimientoMunicipio.put("1317", "1317 San Francisco");
        lugarNacimientoMunicipio.put("1318", "1318 San Juan Guarita");
        lugarNacimientoMunicipio.put("1319", "1319 San Manuel Colohete");
        lugarNacimientoMunicipio.put("1320", "1320 San Rafael");
        lugarNacimientoMunicipio.put("1321", "1321 San Sebastian");
        lugarNacimientoMunicipio.put("1322", "1322 Santa Cruz");
        lugarNacimientoMunicipio.put("1323", "1323 Talgua");
        lugarNacimientoMunicipio.put("1324", "1324 Tambla");
        lugarNacimientoMunicipio.put("1325", "1325 Tomala");
        lugarNacimientoMunicipio.put("1326", "1326 Valladolid");
        lugarNacimientoMunicipio.put("1327", "1327 Virginia");
        lugarNacimientoMunicipio.put("1328", "1328 San Marcos de Caiquin");
        lugarNacimientoMunicipio.put("1401", "1401 Ocotepeque");
        lugarNacimientoMunicipio.put("1402", "1402 Belen Gualcho");
        lugarNacimientoMunicipio.put("1403", "1403 Concepcion");
        lugarNacimientoMunicipio.put("1404", "1404 Dolores Merendon");
        lugarNacimientoMunicipio.put("1405", "1405 Fraternidad");
        lugarNacimientoMunicipio.put("1406", "1406 La Encarnacion");
        lugarNacimientoMunicipio.put("1407", "1407 La Labor");
        lugarNacimientoMunicipio.put("1408", "1408 Lucerna");
        lugarNacimientoMunicipio.put("1409", "1409 Mercedes");
        lugarNacimientoMunicipio.put("1410", "1410 San Fernando");
        lugarNacimientoMunicipio.put("1411", "1411 San Francisco del Valle");
        lugarNacimientoMunicipio.put("1412", "1412 San Jorge");
        lugarNacimientoMunicipio.put("1413", "1413 San Marcos");
        lugarNacimientoMunicipio.put("1414", "1414 Santa Fe");
        lugarNacimientoMunicipio.put("1415", "1415 Sensenti");
        lugarNacimientoMunicipio.put("1416", "1416 Sinuapa");

        // Agregar el catálogo al grupo principal con el nombre del campo
        grupoHogaresRec.put("H_P07_NA_MUNI", lugarNacimientoMunicipio);
        //catalogos.put("HOGARES_REC", grupoPersonasRec);
        
        catalogos.put("HOGARES_REC", grupoHogaresRec);
        
        Map<String, Map<String, String>> grupoEmigracionRec = new HashMap<>();
        Map<String, String> sexoEmigrante = new HashMap<>();
        sexoEmigrante.put("1", "Hombre");
        sexoEmigrante.put("2", "Mujer");

        grupoEmigracionRec.put("H_E02_SEXO_EMI", sexoEmigrante);
        
        Map<String, String> paisResidenciaEmigrante = new HashMap<>();
        paisResidenciaEmigrante.put("1", "Estados Unidos");
        paisResidenciaEmigrante.put("2", "España");
        paisResidenciaEmigrante.put("3", "México");
        paisResidenciaEmigrante.put("4", "Guatemala");
        paisResidenciaEmigrante.put("5", "Otro");
        paisResidenciaEmigrante.put("6", "No sabe");

        grupoEmigracionRec.put("H_E05_RESI_EMI", paisResidenciaEmigrante);
        catalogos.put("EMIGRACION_REC", grupoEmigracionRec);
        
        // PERSONAS
        Map<String, Map<String, String>> grupoPersonasRec = new HashMap<>();
        Map<String, String> parentesco = new HashMap<>();
        parentesco.put("1", "Jefe, jefa del hogar o persona de referencia");
        parentesco.put("2", "Cónyuge o pareja");
        parentesco.put("3", "Hijo(a)");
        parentesco.put("4", "Hijastro(a)");
        parentesco.put("5", "Yerno o nuera");
        parentesco.put("6", "Nieto(a)");
        parentesco.put("7", "Padre o madre");
        parentesco.put("8", "Suegro(a)");
        parentesco.put("9", "Hermano(a)");
        parentesco.put("10", "Cuñado(a)");
        parentesco.put("11", "Sobrino(a)");
        parentesco.put("12", "Abuela o abuelo de persona de referencia");
        parentesco.put("13", "Empleado(a) doméstico(a) con dormida adentro");
        parentesco.put("14", "Otro pariente");
        parentesco.put("15", "Otro no pariente");

        grupoPersonasRec.put("H_CH02_PARENTESCO", parentesco);
        
        Map<String, String> sexo = new HashMap<>();
        sexo.put("1", "Hombre");
        sexo.put("2", "Mujer");

        grupoPersonasRec.put("H_CH03_SEXO", sexo);
        
        Map<String, String> mesNacimiento = new HashMap<>();
        mesNacimiento.put("1", "Enero");
        mesNacimiento.put("2", "Febrero");
        mesNacimiento.put("3", "Marzo");
        mesNacimiento.put("4", "Abril");
        mesNacimiento.put("5", "Mayo");
        mesNacimiento.put("6", "Junio");
        mesNacimiento.put("7", "Julio");
        mesNacimiento.put("8", "Agosto");
        mesNacimiento.put("9", "Septiembre");
        mesNacimiento.put("10", "Octubre");
        mesNacimiento.put("11", "Noviembre");
        mesNacimiento.put("12", "Diciembre");
        mesNacimiento.put("99", "No sabe");

        grupoPersonasRec.put("H_CH05B_MES", mesNacimiento);
        
        Map<String, String> puebloIndigena = new HashMap<>();
        puebloIndigena.put("1", "Maya-Chortí");
        puebloIndigena.put("2", "Lenca");
        puebloIndigena.put("3", "Miskitu");
        puebloIndigena.put("4", "Nahualt");
        puebloIndigena.put("5", "Pesh");
        puebloIndigena.put("6", "Tolupán");
        puebloIndigena.put("7", "Tawahka");
        puebloIndigena.put("8", "Chorotega");
        puebloIndigena.put("9", "Garifuna");
        puebloIndigena.put("10", "Negro de habla inglesa");
        puebloIndigena.put("11", "Creole");
        puebloIndigena.put("12", "Mestizo");
        puebloIndigena.put("13", "Otro pueblo indígena, negro o afrodescendiente.");
        puebloIndigena.put("14", "Ninguna de las anteriores");

        grupoPersonasRec.put("H_P01_PUEBLO", puebloIndigena);
        
        Map<String, String> lenguaMaterna = new HashMap<>();
        lenguaMaterna.put("1", "Miskitu");
        lenguaMaterna.put("2", "Pesh");
        lenguaMaterna.put("3", "Tol");
        lenguaMaterna.put("4", "Tawahka");
        lenguaMaterna.put("5", "Garífuna");
        lenguaMaterna.put("6", "Inglés (isleño)");
        lenguaMaterna.put("7", "Maya-chorti");
        lenguaMaterna.put("8", "Español");
        lenguaMaterna.put("9", "Otro");

        grupoPersonasRec.put("H_P02_LENGUAMAT", lenguaMaterna);
        
        Map<String, String> dificultadCaminar = new HashMap<>();
        dificultadCaminar.put("1", "No tiene ninguna dificultad");
        dificultadCaminar.put("2", "Tiene cierta dificultad");
        dificultadCaminar.put("3", "Tiene mucha dificultad");
        dificultadCaminar.put("4", "Le resulta imposible");
        dificultadCaminar.put("5", "No sabe/ No responde");

        grupoPersonasRec.put("H_P05A_CAMINAR", dificultadCaminar);
        
        Map<String, String> deficienciaCaminar = new HashMap<>();
        deficienciaCaminar.put("1", "De nacimiento");
        deficienciaCaminar.put("2", "Enfermedad");
        deficienciaCaminar.put("3", "Accidente");
        deficienciaCaminar.put("4", "Edad avanzada");
        deficienciaCaminar.put("5", "Otra causa");

        grupoPersonasRec.put("H_P05A_CAMINAR_E", deficienciaCaminar);
        
        
        Map<String, String> dificultadComunicarse = new HashMap<>();
        dificultadComunicarse.put("1", "No tiene ninguna deficiencia o dificultad");
        dificultadComunicarse.put("2", "Tiene alguna deficiencia o dificultad");
        dificultadComunicarse.put("3", "Tiene mucha deficiencia o dificultad");
        dificultadComunicarse.put("4", "Le resulta imposible");
        dificultadComunicarse.put("5", "No sabe/ No responde");

        grupoPersonasRec.put("H_P05B_COMUNI", dificultadComunicarse);
        
        Map<String, String> causaDificultadComunicarse = new HashMap<>();
        causaDificultadComunicarse.put("1", "De nacimiento");
        causaDificultadComunicarse.put("2", "Enfermedad");
        causaDificultadComunicarse.put("3", "Accidente");
        causaDificultadComunicarse.put("4", "Edad avanzada");
        causaDificultadComunicarse.put("5", "Otra causa");

        grupoPersonasRec.put("H_P05B_COMUNI_C", causaDificultadComunicarse);
        
        
        Map<String, String> causaDificultadVer = new HashMap<>();
        causaDificultadVer.put("1", "De nacimiento");
        causaDificultadVer.put("2", "Enfermedad");
        causaDificultadVer.put("3", "Accidente");
        causaDificultadVer.put("4", "Edad avanzada");
        causaDificultadVer.put("5", "Otra causa");

        grupoPersonasRec.put("H_P05C_VER_C", causaDificultadVer);
        
        Map<String, String> dificultadParaOir = new HashMap<>();
        dificultadParaOir.put("1", "No tiene ninguna dificultad");
        dificultadParaOir.put("2", "Tiene cierta dificultad");
        dificultadParaOir.put("3", "Tiene mucha dificultad");
        dificultadParaOir.put("4", "Le resulta imposible");
        dificultadParaOir.put("5", "No sabe/ No responde");

        grupoPersonasRec.put("H_P05D_OIR", dificultadParaOir);  
        
        Map<String, String> dificultadParaValerse = new HashMap<>();
        dificultadParaValerse.put("1", "No tiene ninguna dificultad");
        dificultadParaValerse.put("2", "Tiene cierta dificultad");
        dificultadParaValerse.put("3", "Tiene mucha dificultad");
        dificultadParaValerse.put("4", "Le resulta imposible");
        dificultadParaValerse.put("5", "No sabe/ No responde");

        grupoPersonasRec.put("H_P05E_VALERSE", dificultadParaValerse);
        
        Map<String, String> causaDificultadValerse = new HashMap<>();
        causaDificultadValerse.put("1", "De nacimiento");
        causaDificultadValerse.put("2", "Enfermedad");
        causaDificultadValerse.put("3", "Accidente");
        causaDificultadValerse.put("4", "Edad avanzada");
        causaDificultadValerse.put("5", "Otra causa");

        grupoPersonasRec.put("H_P05E_VALERSE_C", causaDificultadValerse);
        
        
        Map<String, String> dificultadRecordar = new HashMap<>();
        dificultadRecordar.put("1", "No tiene ninguna deficiencia o dificultad");
        dificultadRecordar.put("2", "Tiene cierta deficiencia o dificultad");
        dificultadRecordar.put("3", "Tiene mucha deficiencia o dificultad");
        dificultadRecordar.put("4", "Le resulta imposible");
        dificultadRecordar.put("5", "No sabe/ No responde");

        // Agregar el catálogo al grupo principal con el nombre del campo
        grupoPersonasRec.put("H_P05F_RECORDAR", dificultadRecordar);
        
        Map<String, String> causaDificultadRecordar = new HashMap<>();
        causaDificultadRecordar.put("1", "De nacimiento");
        causaDificultadRecordar.put("2", "Enfermedad");
        causaDificultadRecordar.put("3", "Accidente");
        causaDificultadRecordar.put("4", "Edad avanzada");
        causaDificultadRecordar.put("5", "Otra causa");

        // Agregar el catálogo al grupo principal con el nombre del campo
        grupoPersonasRec.put("H_P05F_RECORDAR_C", causaDificultadRecordar);
        
        Map<String, String> lugarNacimiento = new HashMap<>();
        lugarNacimiento.put("1", "En este municipio");
        lugarNacimiento.put("2", "En otro municipio dentro del país");
        lugarNacimiento.put("3", "En otro país");

        // Agregar el catálogo al grupo principal con el nombre del campo
        grupoPersonasRec.put("H_P06_LUGAR_NA", lugarNacimiento);
        
        Map<String, String> lugarNacimientoPais = new HashMap<>();

        lugarNacimientoPais.put("1", "1 AFGANISTAN");
        lugarNacimientoPais.put("2", "2 AKROTIRI");
        lugarNacimientoPais.put("3", "3 ALBANIA");
        lugarNacimientoPais.put("4", "4 ALEMANIA");
        lugarNacimientoPais.put("5", "5 ANDORRA");
        lugarNacimientoPais.put("6", "6 ANGOLA");
        lugarNacimientoPais.put("7", "7 ANGUILA");
        lugarNacimientoPais.put("8", "8 ANTARTIDA");
        lugarNacimientoPais.put("9", "9 ANTIGUA Y BARBUDA");
        lugarNacimientoPais.put("10", "10 ARABIA SAUDI");
        lugarNacimientoPais.put("11", "11 ARCTIC OCEAN");
        lugarNacimientoPais.put("12", "12 ARGELIA");
        lugarNacimientoPais.put("13", "13 ARGENTINA");
        lugarNacimientoPais.put("14", "14 ARMENIA");
        lugarNacimientoPais.put("15", "15 ARUBA");
        lugarNacimientoPais.put("16", "16 ASHMORE AND CARTIER ISLANDS");
        lugarNacimientoPais.put("17", "17 ATLANTIC OCEAN");
        lugarNacimientoPais.put("18", "18 AUSTRALIA");
        lugarNacimientoPais.put("19", "19 AUSTRIA");
        lugarNacimientoPais.put("20", "20 AZERBAIYAN");
        lugarNacimientoPais.put("21", "21 BAHAMAS");
        lugarNacimientoPais.put("22", "22 BAHRAIN");
        lugarNacimientoPais.put("23", "23 BANGLADESH");
        lugarNacimientoPais.put("24", "24 BARBADOS");
        lugarNacimientoPais.put("25", "25 BELGICA");
        lugarNacimientoPais.put("26", "26 BELICE");
        lugarNacimientoPais.put("27", "27 BENIN");
        lugarNacimientoPais.put("28", "28 BERMUDAS");
        lugarNacimientoPais.put("29", "29 BIELORRUSIA");
        lugarNacimientoPais.put("30", "30 BIRMANIA; MYANMAR");
        lugarNacimientoPais.put("31", "31 BOLIVIA");
        lugarNacimientoPais.put("32", "32 BOSNIA Y HERCEGOVINA");
        lugarNacimientoPais.put("33", "33 BOTSUANA");
        lugarNacimientoPais.put("34", "34 BRASIL");
        lugarNacimientoPais.put("35", "35 BRUNEI");
        lugarNacimientoPais.put("36", "36 BULGARIA");
        lugarNacimientoPais.put("37", "37 BURKINA FASO");
        lugarNacimientoPais.put("38", "38 BURUNDI");
        lugarNacimientoPais.put("39", "39 BUTAN");
        lugarNacimientoPais.put("40", "40 CABO VERDE");
        lugarNacimientoPais.put("41", "41 CAMBOYA");
        lugarNacimientoPais.put("42", "42 CAMERUN");
        lugarNacimientoPais.put("43", "43 CANADA");
        lugarNacimientoPais.put("44", "44 CHAD");
        lugarNacimientoPais.put("45", "45 CHILE");
        lugarNacimientoPais.put("46", "46 CHINA");
        
        lugarNacimientoPais.put("47", "47 CHIPRE");
        lugarNacimientoPais.put("48", "48 CLIPPERTON ISLAND");
        lugarNacimientoPais.put("49", "49 COLOMBIA");
        lugarNacimientoPais.put("50", "50 COMORAS");
        lugarNacimientoPais.put("51", "51 CONGO");
        lugarNacimientoPais.put("52", "52 CORAL SEA ISLANDS");
        lugarNacimientoPais.put("53", "53 COREA DEL NORTE");
        lugarNacimientoPais.put("54", "54 COREA DEL SUR");
        lugarNacimientoPais.put("55", "55 COSTA DE MARFIL");
        lugarNacimientoPais.put("56", "56 COSTA RICA");
        lugarNacimientoPais.put("57", "57 CROACIA");
        lugarNacimientoPais.put("58", "58 CUBA");
        lugarNacimientoPais.put("59", "59 CURACAO");
        lugarNacimientoPais.put("60", "60 DHEKELIA");
        lugarNacimientoPais.put("61", "61 DINAMARCA");
        lugarNacimientoPais.put("62", "62 DOMINICA");
        lugarNacimientoPais.put("63", "63 ECUADOR");
        lugarNacimientoPais.put("64", "64 EGIPTO");
        lugarNacimientoPais.put("65", "65 EL SALVADOR");
        lugarNacimientoPais.put("66", "66 EL VATICANO");
        lugarNacimientoPais.put("67", "67 EMIRATOS ARABES UNIDOS");
        lugarNacimientoPais.put("68", "68 ERITREA");
        lugarNacimientoPais.put("69", "69 ESLOVAQUIA");
        lugarNacimientoPais.put("70", "70 ESLOVENIA");
        lugarNacimientoPais.put("71", "71 ESPAÑA");
        lugarNacimientoPais.put("72", "72 ESTADOS UNIDOS");
        lugarNacimientoPais.put("73", "73 ESTONIA");
        lugarNacimientoPais.put("74", "74 ETIOPIA");
        lugarNacimientoPais.put("75", "75 FILIPINAS");
        lugarNacimientoPais.put("76", "76 FINLANDIA");
        lugarNacimientoPais.put("77", "77 FIYI");
        lugarNacimientoPais.put("78", "78 FRANCIA");
        lugarNacimientoPais.put("79", "79 GABON");
        lugarNacimientoPais.put("80", "80 GAMBIA");
        lugarNacimientoPais.put("81", "81 GAZA STRIP");
        lugarNacimientoPais.put("82", "82 GEORGIA");
        lugarNacimientoPais.put("83", "83 GHANA");
        lugarNacimientoPais.put("84", "84 GIBRALTAR");
        lugarNacimientoPais.put("85", "85 GRANADA");
        lugarNacimientoPais.put("86", "86 GRECIA");
        lugarNacimientoPais.put("87", "87 GROENLANDIA");
        lugarNacimientoPais.put("88", "88 GUAM");
        lugarNacimientoPais.put("89", "89 GUATEMALA");
        lugarNacimientoPais.put("90", "90 GUERNSEY");
        lugarNacimientoPais.put("91", "91 GUINEA");
        lugarNacimientoPais.put("92", "92 GUINEA ECUATORIAL");
        lugarNacimientoPais.put("93", "93 GUINEA-BISSAU");
        lugarNacimientoPais.put("94", "94 GUYANA");
        lugarNacimientoPais.put("95", "95 HAITI");
        lugarNacimientoPais.put("96", "96 HONDURAS");
        lugarNacimientoPais.put("97", "97 HONG KONG");
        lugarNacimientoPais.put("98", "98 HUNGRIA");
        lugarNacimientoPais.put("99", "99 INDIA");
        lugarNacimientoPais.put("100", "100 INDIAN OCEAN");
        lugarNacimientoPais.put("101", "101 INDONESIA");
        lugarNacimientoPais.put("102", "102 IRAN");
        lugarNacimientoPais.put("103", "103 IRAQ");
        lugarNacimientoPais.put("104", "104 IRLANDA");
        lugarNacimientoPais.put("105", "105 ISLA BOUVET");
        lugarNacimientoPais.put("106", "106 ISLA CHRISTMAS");
        lugarNacimientoPais.put("107", "107 ISLA NORFOLK");
        lugarNacimientoPais.put("108", "108 ISLANDIA");
        lugarNacimientoPais.put("109", "109 ISLAS CAIMAN");
        lugarNacimientoPais.put("110", "110 ISLAS COCOS");
        
        lugarNacimientoPais.put("111", "ISLAS COOK");
        lugarNacimientoPais.put("112", "ISLAS FEROE");
        lugarNacimientoPais.put("113", "ISLAS GEORGIA DEL SUR Y SANDWICH DEL SUR");
        lugarNacimientoPais.put("114", "ISLAS HEARD Y MCDONALD");
        lugarNacimientoPais.put("115", "ISLAS MALVINAS");
        lugarNacimientoPais.put("116", "ISLAS MARIANAS DEL NORTE");
        lugarNacimientoPais.put("117", "ISLAS MARSHALL");
        lugarNacimientoPais.put("118", "ISLAS PITCAIRN");
        lugarNacimientoPais.put("119", "ISLAS SALOMON");
        lugarNacimientoPais.put("120", "ISLAS TURCAS Y CAICOS");
        lugarNacimientoPais.put("121", "ISLAS VIRGENES AMERICANAS");
        lugarNacimientoPais.put("122", "ISLAS VIRGENES BRITANICAS");
        lugarNacimientoPais.put("123", "ISRAEL");
        lugarNacimientoPais.put("124", "ITALIA");
        lugarNacimientoPais.put("125", "JAMAICA");
        lugarNacimientoPais.put("126", "JAN MAYEN");
        lugarNacimientoPais.put("127", "JAPON");
        lugarNacimientoPais.put("128", "JERSEY");
        lugarNacimientoPais.put("129", "JORDANIA");
        lugarNacimientoPais.put("130", "KAZAJISTAN");
        lugarNacimientoPais.put("131", "KENIA");
        lugarNacimientoPais.put("132", "KIRGUIZISTAN");
        lugarNacimientoPais.put("133", "KIRIBATI");
        lugarNacimientoPais.put("134", "KOSOVO");
        lugarNacimientoPais.put("135", "KUWAIT");
        lugarNacimientoPais.put("136", "LAOS");
        lugarNacimientoPais.put("137", "LESOTO");
        lugarNacimientoPais.put("138", "LETONIA");
        lugarNacimientoPais.put("139", "LIBANO");
        lugarNacimientoPais.put("140", "LIBERIA");
        lugarNacimientoPais.put("141", "LIBIA");
        lugarNacimientoPais.put("142", "LIECHTENSTEIN");
        lugarNacimientoPais.put("143", "LITUANIA");
        lugarNacimientoPais.put("144", "LUXEMBURGO");
        lugarNacimientoPais.put("145", "MACAO");
        lugarNacimientoPais.put("146", "MACEDONIA");
        lugarNacimientoPais.put("147", "MADAGASCAR");
        lugarNacimientoPais.put("148", "MALASIA");
        lugarNacimientoPais.put("149", "MALAUI");
        lugarNacimientoPais.put("150", "MALDIVAS");
        lugarNacimientoPais.put("151", "MALI");
        lugarNacimientoPais.put("152", "MALTA");
        lugarNacimientoPais.put("153", "MAN, ISLE OF");
        lugarNacimientoPais.put("154", "MARRUECOS");
        lugarNacimientoPais.put("155", "MAURICIO");
        lugarNacimientoPais.put("156", "MAURITANIA");
        lugarNacimientoPais.put("157", "MEXICO");
        lugarNacimientoPais.put("158", "MICRONESIA");
        lugarNacimientoPais.put("159", "MOLDAVIA");
        lugarNacimientoPais.put("160", "MONACO");
        lugarNacimientoPais.put("161", "MONGOLIA");
        lugarNacimientoPais.put("162", "MONTENEGRO");
        lugarNacimientoPais.put("163", "MONTSERRAT");
        lugarNacimientoPais.put("164", "MOZAMBIQUE");
        lugarNacimientoPais.put("165", "MUNDO");
        lugarNacimientoPais.put("166", "NAMIBIA");
        lugarNacimientoPais.put("167", "NAURU");
        lugarNacimientoPais.put("168", "NAVASSA ISLAND");
        lugarNacimientoPais.put("169", "NEPAL");
    
        lugarNacimientoPais.put("170", "NICARAGUA");
        lugarNacimientoPais.put("171", "NIGER");
        lugarNacimientoPais.put("172", "NIGERIA");
        lugarNacimientoPais.put("173", "NIUE");
        lugarNacimientoPais.put("174", "NORUEGA");
        lugarNacimientoPais.put("175", "NUEVA CALEDONIA");
        lugarNacimientoPais.put("176", "NUEVA ZELANDA");
        lugarNacimientoPais.put("177", "OMAN");
        lugarNacimientoPais.put("178", "PACIFIC OCEAN");
        lugarNacimientoPais.put("179", "PAISES BAJOS");
        lugarNacimientoPais.put("180", "PAKISTAN");
        lugarNacimientoPais.put("181", "PALAOS");
        lugarNacimientoPais.put("182", "PANAMA");
        lugarNacimientoPais.put("183", "PAPUA-NUEVA GUINEA");
        lugarNacimientoPais.put("184", "PARACEL ISLANDS");
        lugarNacimientoPais.put("185", "PARAGUAY");
        lugarNacimientoPais.put("186", "PERU");
        lugarNacimientoPais.put("187", "POLINESIA FRANCESA");
        lugarNacimientoPais.put("188", "POLONIA");
        lugarNacimientoPais.put("189", "PORTUGAL");
        lugarNacimientoPais.put("190", "PUERTO RICO");
        lugarNacimientoPais.put("191", "QATAR");
        lugarNacimientoPais.put("192", "REINO UNIDO");
        lugarNacimientoPais.put("193", "REPUBLICA CENTROAFRICANA");
        lugarNacimientoPais.put("194", "REPUBLICA DEMOCRATICA DEL CONGO");
        lugarNacimientoPais.put("195", "REPUBLICA DOMINICANA");
        lugarNacimientoPais.put("196", "RUANDA");
        lugarNacimientoPais.put("197", "RUMANIA");
        lugarNacimientoPais.put("198", "RUSIA");
        lugarNacimientoPais.put("199", "SAHARA OCCIDENTAL");
        lugarNacimientoPais.put("200", "SAMOA");
        lugarNacimientoPais.put("201", "SAMOA AMERICANA");
        lugarNacimientoPais.put("202", "SAN BARTOLOME");
        lugarNacimientoPais.put("203", "SAN CRISTOBAL Y NIEVES");
        lugarNacimientoPais.put("204", "SAN MARINO");
        lugarNacimientoPais.put("205", "SAN MARTIN");
        lugarNacimientoPais.put("206", "SAN PEDRO Y MIQUELON");
        lugarNacimientoPais.put("207", "SAN VICENTE Y LAS GRANADINAS");
        lugarNacimientoPais.put("208", "SANTA HELENA");
        lugarNacimientoPais.put("209", "SANTA LUCIA");
        lugarNacimientoPais.put("210", "SANTO TOME Y PRINCIPE");
        lugarNacimientoPais.put("211", "SENEGAL");
        lugarNacimientoPais.put("212", "SERBIA");
        lugarNacimientoPais.put("213", "SEYCHELLES");
        lugarNacimientoPais.put("214", "SIERRA LEONA");
        lugarNacimientoPais.put("215", "SINGAPUR");
        lugarNacimientoPais.put("216", "SINT MAARTEN");
        lugarNacimientoPais.put("217", "SIRIA");
        lugarNacimientoPais.put("218", "SOMALIA");
        lugarNacimientoPais.put("219", "SOUTHERN OCEAN");
        lugarNacimientoPais.put("220", "SPRATLY ISLANDS");
        
        lugarNacimientoPais.put("221", "SRI LANKA");
        lugarNacimientoPais.put("222", "SUAZILANDIA");
        lugarNacimientoPais.put("223", "SUDAFRICA");
        lugarNacimientoPais.put("224", "SUDAN");
        lugarNacimientoPais.put("225", "SUDAN DEL SUR");
        lugarNacimientoPais.put("226", "SUECIA");
        lugarNacimientoPais.put("227", "SUIZA");
        lugarNacimientoPais.put("228", "SURINAM");
        lugarNacimientoPais.put("229", "SVALBARD Y JAN MAYEN");
        lugarNacimientoPais.put("230", "TAILANDIA");
        lugarNacimientoPais.put("231", "TAIWAN");
        lugarNacimientoPais.put("232", "TANZANIA");
        lugarNacimientoPais.put("233", "TAYIKISTAN");
        lugarNacimientoPais.put("234", "TERRITORIO BRITANICO DEL OCEANO INDICO");
        lugarNacimientoPais.put("235", "TERRITORIOS AUSTRALES FRANCESES");
        lugarNacimientoPais.put("236", "TIMOR ORIENTAL");
        lugarNacimientoPais.put("237", "TOGO");
        lugarNacimientoPais.put("238", "TOKELAU");
        lugarNacimientoPais.put("239", "TONGA");
        lugarNacimientoPais.put("240", "TRINIDAD Y TOBAGO");
        lugarNacimientoPais.put("241", "TUNEZ");
        lugarNacimientoPais.put("242", "TURKMENISTAN");
        lugarNacimientoPais.put("243", "TURQUIA");
        lugarNacimientoPais.put("244", "TUVALU");
        lugarNacimientoPais.put("245", "UCRANIA");
        lugarNacimientoPais.put("246", "UGANDA");
        lugarNacimientoPais.put("247", "UNION EUROPEA");
        lugarNacimientoPais.put("248", "URUGUAY");
        lugarNacimientoPais.put("249", "UZBEKISTAN");
        lugarNacimientoPais.put("250", "VANUATU");
        lugarNacimientoPais.put("251", "VENEZUELA");
        lugarNacimientoPais.put("252", "VIETNAM");
        lugarNacimientoPais.put("253", "WAKE ISLAND");
        lugarNacimientoPais.put("254", "WALLIS Y FUTUNA");
        lugarNacimientoPais.put("255", "WEST BANK");
        lugarNacimientoPais.put("256", "YEMEN");
        lugarNacimientoPais.put("257", "YIBUTI");
        lugarNacimientoPais.put("258", "ZAMBIA");
        lugarNacimientoPais.put("259", "ZIMBABUE");
        lugarNacimientoPais.put("999", "No sabe");
        Map<String, Map<String, String>> paisesRec = new HashMap<>();
        paisesRec.put("PAIS", lugarNacimientoPais);
        catalogos.put("PAIS", paisesRec);
        
        
        Map<String, String> llegadaMes = new HashMap<>();
        
        llegadaMes.put("1", "Enero");
        llegadaMes.put("2", "Febrero");
        llegadaMes.put("3", "Marzo");
        llegadaMes.put("4", "Abril");
        llegadaMes.put("5", "Mayo");
        llegadaMes.put("6", "Junio");
        llegadaMes.put("7", "Julio");
        llegadaMes.put("8", "Agosto");
        llegadaMes.put("9", "Septiembre");
        llegadaMes.put("10", "Octubre");
        llegadaMes.put("11", "Noviembre");
        llegadaMes.put("12", "Diciembre");
        llegadaMes.put("99", "No sabe");

        grupoPersonasRec.put("H_P09B_LLEGA_MES", llegadaMes);


        Map<String, String> lugarResidencia = new HashMap<>();

        lugarResidencia.put("1", "Si, en este municipio");
        lugarResidencia.put("2", "No, anteriormente vivía en otro municipio");
        lugarResidencia.put("3", "No, anteriormente vivía en otro país");

        grupoPersonasRec.put("H_P10_LUGAR_RESIDENCIA", lugarResidencia);
        
        Map<String, String> razonVino = new HashMap<>();

        razonVino.put("1", "Búsqueda de empleo");
        razonVino.put("2", "Traslado por trabajo (incluido el servicio militar)");
        razonVino.put("3", "Educación y formación");
        razonVino.put("4", "Matrimonio, reunificación familiar o formación familiar (incluye los niños que migran con los padres)");
        razonVino.put("5", "Inseguridad ciudadana y violencia");
        razonVino.put("6", "Pérdida de cosechas");
        razonVino.put("7", "Desastres naturales");
        razonVino.put("8", "Otros");

        grupoPersonasRec.put("H_P14_RAZON_VINO", razonVino);

        
        Map<String, String> inscripcionRnp = new HashMap<>();

        inscripcionRnp.put("1", "Si");
        inscripcionRnp.put("2", "No");
        inscripcionRnp.put("3", "No sabe");

        grupoPersonasRec.put("H_P15_INSCRI_RNP", inscripcionRnp);
        
        
        Map<String, String> estadoCivil = new HashMap<>();

        estadoCivil.put("1", "Casado(a)");
        estadoCivil.put("2", "Unión libre, unión de hecho (juntado)");
        estadoCivil.put("3", "Separado(a)");
        estadoCivil.put("4", "Divorciado(a)");
        estadoCivil.put("5", "Viudo(a)");
        estadoCivil.put("6", "Soltero(a) (nunca casado)");

        grupoPersonasRec.put("H_P16_ESTA_CIVIL", estadoCivil);

        Map<String, String> asistenciaEdu = new HashMap<>();

        asistenciaEdu.put("1", "Si");
        asistenciaEdu.put("2", "No");
        asistenciaEdu.put("3", "No sabe");

        grupoPersonasRec.put("H_P17_ASISTEN_EDU", asistenciaEdu);
        
        
        Map<String, String> nivelEduc = new HashMap<>();

        nivelEduc.put("1", "Ninguno");
        nivelEduc.put("2", "Alfabetización");
        nivelEduc.put("3", "Prebásica (1, 2, 3)");
        nivelEduc.put("4", "Básica (grados: 1, 2, 3, 4, 5, 6, 7, 8, 9)");
        nivelEduc.put("5", "Media/Diversificado (grados: 10, 11, 12)");
        nivelEduc.put("6", "Técnico Superior (técnicos de carreras cortas en universidades) (grados: 1, 2)");
        nivelEduc.put("7", "Superior no universitaria (normal de profesorado, academias militares, de policía, seminarios mayores, diseño gráfico) (grados: 1, 2, 3, 4)");
        nivelEduc.put("8", "Universitaria (incluye medicina y cirugía) (años: 1, 2, 3, 4, 5, 6, 7, 8)");
        nivelEduc.put("9", "Especialidad (grados: 1, 2, 3)");
        nivelEduc.put("10", "Maestría (años: 1, 2)");
        nivelEduc.put("11", "Doctorado (no médico) (años: 1, 2, 3, 4)");
        nivelEduc.put("99", "No sabe");

        grupoPersonasRec.put("H_P18A_NIVEL_EDUC", nivelEduc);
        
        
        Map<String, String> empIdentificacion = new HashMap<>();

        empIdentificacion.put("1", "Trabajó para alguien más por un pago en dinero o especies (como empleado, jornalero o aprendiz)");
        empIdentificacion.put("2", "Trabajó en algún otro tipo de negocio");
        empIdentificacion.put("3", "Trabajó en la parcela propia o de su familia o se dedicó a actividades de pesca por su cuenta");
        empIdentificacion.put("4", "Ninguna de las anteriores");

        grupoPersonasRec.put("H_P23_SEM_PAS_TRA", empIdentificacion);
        
        Map<String, String> productosAgropecuarios = new HashMap<>();

        productosAgropecuarios.put("1", "Solamente para la venta (actividad comercial a cambio de dinero)");
        productosAgropecuarios.put("2", "En su mayoria para la venta (actividad comercial a cambio de dinero)");
        productosAgropecuarios.put("3", "En su mayoría para el consumo familiar");
        productosAgropecuarios.put("4", "Solamente para el consumo familiar");

        grupoPersonasRec.put("H_P24_PRODUCTOS_AGRO", productosAgropecuarios);
        
        Map<String, String> actividadesSemanaPasada = new HashMap<>();

        actividadesSemanaPasada.put("1", "Hizo algún otro negocio o actividad por pago, al menos por una hora (preparar y vender comida, lavar ropa ajena, cortar pelo, vender productos por catálogo, reparar ropa)");
        actividadesSemanaPasada.put("2", "Tenía un trabajo por pago o un negocio pero estaba temporalmente ausente");
        actividadesSemanaPasada.put("3", "Ayudó sin pago en un negocio familiar");
        actividadesSemanaPasada.put("4", "No hizo ninguna actividad para generar ingresos aunque fuera por una hora");
        actividadesSemanaPasada.put("5", "No sabe");

        grupoPersonasRec.put("H_P25_ACTI_REALI", actividadesSemanaPasada);
        
        Map<String, String> actividadPrincipal = new HashMap<>();

        actividadPrincipal.put("1", "Cuida del hogar y la familia");
        actividadPrincipal.put("2", "Estudia");
        actividadPrincipal.put("3", "Pasantía o entrenamiento sin pago");
        actividadPrincipal.put("4", "Voluntariado, servicio comunitario o trabajo caritativo sin pago");
        actividadPrincipal.put("5", "Retirado, pensionado o jubilado");
        actividadPrincipal.put("6", "Enfermo de larga duración, incapacitado o lesionado (sin pensión)");
        actividadPrincipal.put("7", "Rentistas");
        actividadPrincipal.put("8", "Otro");

        grupoPersonasRec.put("H_P28_ACT_PRINC", actividadPrincipal);
        
        Map<String, String> trabajoMovilidad = new HashMap<>();

        trabajoMovilidad.put("1", "Desde casa");
        trabajoMovilidad.put("2", "Fuera de casa y en este municipio");
        trabajoMovilidad.put("3", "En otro municipio (nombre departamento, municipio)");
        trabajoMovilidad.put("4", "En otro país (nombre del país)");
        trabajoMovilidad.put("5", "Sin ubicación fija");

        grupoPersonasRec.put("H_P31_LUGAR_TRAB", trabajoMovilidad);
        
        Map<String, String> categoriaOcupacional = new HashMap<>();

        categoriaOcupacional.put("1", "Empleado público");
        categoriaOcupacional.put("2", "Empleado privado");
        categoriaOcupacional.put("3", "Empleado doméstico");
        categoriaOcupacional.put("4", "Aprendiz o pasante con pago");
        categoriaOcupacional.put("5", "Empleador, patrón o socio activo (con empleados contratados permanentes)");
        categoriaOcupacional.put("6", "Trabajador independiente o por cuenta propia");
        categoriaOcupacional.put("7", "Ayudante (sin pago) en empresa o finca familiar");
        categoriaOcupacional.put("8", "Otro");

        grupoPersonasRec.put("H_P32_TRABAJ_COMO", categoriaOcupacional);
        
        
        Map<String, String> actividadesAgropecuarias = new HashMap<>();

        actividadesAgropecuarias.put("1", "Agricultura o cultivo de alimentos en un lote o jardín");
        actividadesAgropecuarias.put("2", "Recolección de leña o agua");
        actividadesAgropecuarias.put("3", "Cría de animales de granja");
        actividadesAgropecuarias.put("4", "Pesca");
        actividadesAgropecuarias.put("5", "Caza o recolección de frutos silvestres");
        actividadesAgropecuarias.put("6", "No hizo ninguna");

        grupoPersonasRec.put("H_P33_ACTI_AGRO", actividadesAgropecuarias);
        
        Map<String, String> sexoUltimoHijo = new HashMap<>();

        sexoUltimoHijo.put("1", "Hombre");
        sexoUltimoHijo.put("2", "Mujer");
        sexoUltimoHijo.put("9", "No sabe");

        grupoPersonasRec.put("H_P42_SEXO_ULTIMO", sexoUltimoHijo);
        
        Map<String, String> siNoNoSabe = new HashMap<>();

        siNoNoSabe.put("1", "Si");
        siNoNoSabe.put("2", "No");
        siNoNoSabe.put("9", "No sabe");

        grupoPersonasRec.put("SINONOSABE", siNoNoSabe);
        catalogos.put("PERSONAS_REC", grupoPersonasRec);
        
        Map<String, Map<String, String>> mortailidadRec = new HashMap<>();
        Map<String, String> causaMuerteEmbarazo = new HashMap<>();

        causaMuerteEmbarazo.put("1", "Embarazo");
        causaMuerteEmbarazo.put("2", "Parto");
        causaMuerteEmbarazo.put("3", "Dentro de las seis semanas después del termino del embarazo");
        causaMuerteEmbarazo.put("4", "Otra causa");

        mortailidadRec.put("H_MM02_MORT_EMB", causaMuerteEmbarazo);
        //catalogos.put("MORTALIDAD_REC", mortailidadRec);
        
        
        Map<String, Map<String, String>> VisitaRec = new HashMap<>();
        Map<String, String> resultadoVisita = new HashMap<>();

        resultadoVisita.put("1", "Entrevista en proceso");
        resultadoVisita.put("2", "Personas ausentes");
        resultadoVisita.put("3", "Rechazo");
        resultadoVisita.put("4", "Transformada");
        resultadoVisita.put("5", "Finalizada");

        mortailidadRec.put("H_RVISITA", resultadoVisita);
        catalogos.put("MORTALIDAD_REC", mortailidadRec);
        
        Map<String, String> controlVisitas = new HashMap<>();

        controlVisitas.put("1", "Negocio");
        controlVisitas.put("2", "Centro Educativo");
        controlVisitas.put("3", "Centro de salud");
        controlVisitas.put("4", "Referencia");

        VisitaRec.put("H_CONTROL_VS3", controlVisitas);
        
        
        catalogos.put("VISITA", VisitaRec);
         
            
    }

    /**
     * Obtiene el texto correspondiente al valor dado, según grupo y pregunta.
     * @param grupo El grupo del catálogo, ej: "VIVIENDA_REC"
     * @param pregunta La clave de la pregunta, ej: "H_V01_TIPO_VIV"
     * @param valor El valor a traducir, ej: "1"
     * @return El texto descriptivo o null si no se encontró.
     */
    public static String obtenerTexto(String grupo, String pregunta, String valor) {
        Map<String, Map<String, String>> grupoMap = catalogos.get(grupo);
        if (grupoMap == null) return null;

        Map<String, String> preguntaMap = grupoMap.get(pregunta);
        if (preguntaMap == null) return null;

        return preguntaMap.get(valor);
    }
    
    public static Map<String, Map<String, Map<String, String>>> getCatalogos() {
        return catalogos;
    }

    // Método de prueba
    public static void main(String[] args) {
        String texto = obtenerTexto("VIVIENDA_REC", "H_V01_TIPO_VIV", "1");
        System.out.println(texto);  // Debería imprimir: Casa Independiente
    }
}